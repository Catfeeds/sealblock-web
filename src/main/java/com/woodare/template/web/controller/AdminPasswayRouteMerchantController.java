/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.template.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.thirdparty.passway._base.MD5Tool;
import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.util.AesUtils;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.busi.service.CacheDataChangeService;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.PasswayRouteMerchant;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;
import com.woodare.template.jpa.persistence.persistence.IPasswayRouteMerchantDAO;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;
import com.woodare.template.web.viewdata.passwayroutemerchant.SearchPasswayRouteMerchantViewData;

/**
 * ClassName: AdminPasswayRouteMerchantController
 * 
 * @description 通道路由商户配置
 * @author Luke
 * @Date Mar 6, 2018
 */
@Controller
@RequestMapping("/admin/passwayRouteMerchant")
public class AdminPasswayRouteMerchantController extends BaseController {
	private static Logger log = Logger.getLogger(AdminPasswayRouteMerchantController.class);

	@Autowired
	private IPasswayRouteMerchantDAO passwayRouteMerchantDAO;

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchPasswayRouteMerchantViewData searchData) throws ControllerException {
		return showIndex(getTemplate("/admin/passwayRouteMerchant/index"), searchData);
	}

	/**
	 * @param data
	 * @return
	 * @throws ControllerException
	 */
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(PasswayRouteMerchantViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/passwayRouteMerchant/add"));
		PasswayRouteMerchant item = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			item = passwayRouteMerchantDAO.findOne(data.getId());
		}
		mav.addObject("item", convertToItem(item));
		return mav;
	}

	/**
	 * @param data
	 * @return
	 * @throws ControllerException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addP(PasswayRouteMerchantViewData data) throws ControllerException {
		String error = validError(data);
		if (StringUtils.isEmpty(error)) {
			PasswayRouteMerchant item = null;

			PasswayRouteMerchantData existed = PasswayRouteMerchants.getByCoin(data.getCoin());
			if (existed != null) {
				if (!existed.getId().equals(data.getId())) {
					return alertFailed(getTemplate("/admin/passwayRouteMerchant/add"), data, "渠道[" + data.getChannel() + "]已存在相同数据[" + data.getCoin() + "]");
				}
			}
			if (StringUtils.isNotEmpty(data.getId())) {
				item = passwayRouteMerchantDAO.findOne(data.getId());
			}
			boolean update = true;
			if (item == null) {
				item = new PasswayRouteMerchant();
				update = false;
			}

			try {
				// 渠道不为空，且加密密钥不为空
				if (data.getChannel() != null && StringUtils.isNotEmpty(data.getChannel().getAesKey())) {
					if (StringUtils.isNotEmpty(data.getSignKeyPlain())) {
						data.setSignKey(AesUtils.encrypt(data.getSignKeyPlain(), data.getChannel().getAesKey()));
					}
					if (StringUtils.isNotEmpty(data.getEncKeyPlain())) {
						data.setEncKey(AesUtils.encrypt(data.getEncKeyPlain(), data.getChannel().getAesKey()));
					}
				}
			} catch (Exception e) {
				return alertFailed(getTemplate("/admin/passwayRouteMerchant/add"), data, "数据加密处理失败");
			}

			SaftyBeanUtils.copyProperties(data, item, new String[] { "id", "createDate", "updateDate", "deleteStatus", "balanceAmt", "creditAmt", "frozenAmt", "settleAmt", "curOutAmt" });

			if (update) {
				passwayRouteMerchantDAO.update(item);
			}
			else {
				passwayRouteMerchantDAO.save(item);
			}
			log.info("ChangePasswayRouteMerchant[" + this.getUserId() + "]" + JSONObject.toJSONString(data));

			cacheDataChangeService.notifyOthers(PasswayRouteMerchantData.class);
			return alertSuccess(getTemplate("/admin/passwayRouteMerchant/add"), convertToItem(item));
		}
		else {
			return alertFailed(getTemplate("/admin/passwayRouteMerchant/add"), data, error);
		}
	}

	/**
	 * @param jsp
	 * @param searchData
	 * @return
	 */
	private ModelAndView showIndex(String jsp, SearchPasswayRouteMerchantViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("updateDate desc");
		}
		IPagedList<PasswayRouteMerchant> items = passwayRouteMerchantDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	/**
	 * @param items
	 * @return
	 */
	private ListResponseData<PasswayRouteMerchantViewData> convertToList(IPagedList<PasswayRouteMerchant> items) {
		ListResponseData<PasswayRouteMerchantViewData> response = new ListResponseData<PasswayRouteMerchantViewData>();
		if (items != null) {
			for (PasswayRouteMerchant item : items) {
				PasswayRouteMerchantViewData viewData = convert(item);
				if (item.getUpdateDate() == null) {
					viewData.setViewUpdateDate(item.getCreateDate());
				}
				else {
					viewData.setViewUpdateDate(item.getUpdateDate());
				}
				response.addItem(viewData);
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private PasswayRouteMerchantViewData convertToItem(PasswayRouteMerchant item) {
		PasswayRouteMerchantViewData viewData = convert(item);
		if (viewData != null) {
			try {
				// 渠道不为空，且加密密钥不为空
				if (viewData.getChannel() != null && StringUtils.isNotEmpty(viewData.getChannel().getAesKey())) {
					if (StringUtils.isNotEmpty(viewData.getSignKey())) {
						viewData.setSignKeyMd5(MD5Tool.md5(AesUtils.decrypt(viewData.getSignKey(), viewData.getChannel().getAesKey())).substring(0, 8));
					}
					if (StringUtils.isNotEmpty(viewData.getEncKey())) {
						viewData.setEncKeyMd5(MD5Tool.md5(AesUtils.decrypt(viewData.getEncKey(), viewData.getChannel().getAesKey())).substring(0, 8));
					}
				}
			} catch (Exception e) {
				log.warn("生成效验值处理失败");
			}
		}

		return viewData;
	}

	private PasswayRouteMerchantViewData convert(PasswayRouteMerchant item) {
		PasswayRouteMerchantViewData viewData = SaftyBeanUtils.cloneTo(item, PasswayRouteMerchantViewData.class);
		return viewData;
	}

	private String validError(PasswayRouteMerchantViewData data) {
		String error = "";
		return error;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/delete/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseData<Boolean> delete(@PathVariable String itemId) {
		AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
		ret.setState(EResponseState.CustomMsg);
		String message = "";
		try {
			if (StringUtils.isNotEmpty(itemId)) {
				PasswayRouteMerchant model = passwayRouteMerchantDAO.findOne(itemId);
				if (model == null || model.getDeleteStatus() == EnumDeleteStatus.DELETED) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					passwayRouteMerchantDAO.deleteWithLogical(model);
					message = "删除成功！";
					ret.setState(EResponseState.Successful);
				}
			}
			else {
				message = "数据已被删除，请重新刷新画面！";
			}
		} catch (Exception e) {
			log.error(e, e);
			message = "删除失败！ " + e.getMessage();
		}
		ret.setMessage(message);
		return ret;
	}
}
