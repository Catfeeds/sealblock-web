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

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantDAO;
import com.woodare.template.web.viewdata.submerchant.SearchSubMerchantViewData;
import com.woodare.template.web.viewdata.submerchant.SubMerchantViewData;

/**
 * 
 * ClassName: SubMerchantController
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2015/11/15
 * 
 */
@Controller
@RequestMapping("/admin/subMerchant")
public class SubMerchantController extends BaseController {
	private static Logger log = Logger.getLogger(SubMerchantController.class);

	@Autowired
	private ISubMerchantDAO subMerchantDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchSubMerchantViewData searchData) throws ControllerException {
		return showIndex("/admin/subMerchant/index", searchData);
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(SubMerchantViewData searchData) throws ControllerException {
		ModelAndView mav = new ModelAndView("/admin/subMerchant/add");
		SubMerchantViewData item = null;
		if (StringUtils.isNotEmpty(searchData.getId())) {
			SubMerchant model = subMerchantDAO.findOne(searchData.getId());
			item = convertDetails(model);
		} else {
			item = new SubMerchantViewData();
		}
		mav.addObject("item", item);
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(SubMerchantViewData item) {
		SubMerchant model = null;

		String error = validData(item);
		if (StringUtils.isNotEmpty(error)) {
			return alertFailed("/admin/subMerchant/add", item, error);
		}

		if (StringUtils.isNotEmpty(item.getId())) {
			model = subMerchantDAO.findOne(item.getId());
		} else {
			model = new SubMerchant();
		}
		SaftyBeanUtils.copyProperties(item, model, new String[] { "id", "deleteStatus", "createDate", "updateDate" });
		if (StringUtils.isNotEmpty(item.getId())) {
			this.subMerchantDAO.update(model);
		} else {
			this.subMerchantDAO.save(model);
		}
		return alertSuccess("/admin/subMerchant/add", convertDetails(model));
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
				SubMerchant model = subMerchantDAO.findOne(itemId);
				if (model == null || model.getDeleteStatus() == EnumDeleteStatus.DELETED) {
					message = "数据已被删除，请重新刷新画面！";
				} else {
					subMerchantDAO.deleteWithLogical(model);
					message = "删除成功！";
					ret.setState(EResponseState.Successful);
				}
			} else {
				message = "数据已被删除，请重新刷新画面！";
			}
		} catch (Exception e) {
			log.error(e, e);
			message = "删除失败！ " + e.getMessage();
		}
		ret.setMessage(message);
		return ret;
	}

	private ModelAndView showIndex(String jsp, SearchSubMerchantViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		IPagedList<SubMerchant> items = subMerchantDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	private ListResponseData<SubMerchantViewData> convertToList(IPagedList<SubMerchant> items) {
		ListResponseData<SubMerchantViewData> response = new ListResponseData<SubMerchantViewData>();
		if (items != null) {
			for (SubMerchant item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private String validData(SubMerchantViewData item) {
		String error = "";
		return error;
	}

	private SubMerchantViewData convert(SubMerchant model) {
		SubMerchantViewData item = SaftyBeanUtils.cloneTo(model, SubMerchantViewData.class, new String[] { "deleteStatus" });
		return item;
	}

	private SubMerchantViewData convertDetails(SubMerchant model) {
		SubMerchantViewData item = convert(model);
		return item;
	}
}
