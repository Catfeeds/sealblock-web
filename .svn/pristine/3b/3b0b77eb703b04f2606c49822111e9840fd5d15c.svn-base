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
import com.woodare.template.jpa.model.DownBalanceTransfer;
import com.woodare.template.jpa.persistence.persistence.IDownBalanceTransferDAO;
import com.woodare.template.web.viewdata.downbalancetransfer.SearchDownBalanceTransferViewData;
import com.woodare.template.web.viewdata.downbalancetransfer.DownBalanceTransferViewData;

/**
 * 
 * ClassName: AdminDownBalanceTransferController
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2015/11/15
 * 
 */
@Controller
@RequestMapping("/admin/downBalanceTransfer")
public class AdminDownBalanceTransferController extends BaseController {
	private static Logger log = Logger.getLogger(AdminDownBalanceTransferController.class);

	@Autowired
	private IDownBalanceTransferDAO downBalanceTransferDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchDownBalanceTransferViewData searchData) throws ControllerException {
		return showIndex("/admin/downBalanceTransfer/index", searchData);
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(DownBalanceTransferViewData searchData) throws ControllerException {
		ModelAndView mav = new ModelAndView("/admin/downBalanceTransfer/add");
		DownBalanceTransferViewData item = null;
		if (StringUtils.isNotEmpty(searchData.getId())) {
			DownBalanceTransfer model = downBalanceTransferDAO.findOne(searchData.getId());
			item = convertDetails(model);
		} else {
			item = new DownBalanceTransferViewData();
		}
		mav.addObject("item", item);
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(DownBalanceTransferViewData item) {
		DownBalanceTransfer model = null;

		String error = validData(item);
		if (StringUtils.isNotEmpty(error)) {
			return alertFailed("/admin/downBalanceTransfer/add", item, error);
		}

		if (StringUtils.isNotEmpty(item.getId())) {
			model = downBalanceTransferDAO.findOne(item.getId());
		} else {
			model = new DownBalanceTransfer();
		}
		SaftyBeanUtils.copyProperties(item, model, new String[] { "id", "deleteStatus", "createDate", "updateDate" });
		if (StringUtils.isNotEmpty(item.getId())) {
			this.downBalanceTransferDAO.update(model);
		} else {
			this.downBalanceTransferDAO.save(model);
		}
		return alertSuccess("/admin/downBalanceTransfer/add", convertDetails(model));
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
				DownBalanceTransfer model = downBalanceTransferDAO.findOne(itemId);
				if (model == null || model.getDeleteStatus() == EnumDeleteStatus.DELETED) {
					message = "数据已被删除，请重新刷新画面！";
				} else {
					downBalanceTransferDAO.deleteWithLogical(model);
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

	private ModelAndView showIndex(String jsp, SearchDownBalanceTransferViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		IPagedList<DownBalanceTransfer> items = downBalanceTransferDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	private ListResponseData<DownBalanceTransferViewData> convertToList(IPagedList<DownBalanceTransfer> items) {
		ListResponseData<DownBalanceTransferViewData> response = new ListResponseData<DownBalanceTransferViewData>();
		if (items != null) {
			for (DownBalanceTransfer item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private String validData(DownBalanceTransferViewData item) {
		String error = "";
		return error;
	}

	private DownBalanceTransferViewData convert(DownBalanceTransfer model) {
		DownBalanceTransferViewData item = SaftyBeanUtils.cloneTo(model, DownBalanceTransferViewData.class, new String[] { "deleteStatus" });
		return item;
	}

	private DownBalanceTransferViewData convertDetails(DownBalanceTransfer model) {
		DownBalanceTransferViewData item = convert(model);
		return item;
	}
}
