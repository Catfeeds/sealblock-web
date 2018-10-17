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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.woodare.core.util.SDFFactory;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.busi.service.CacheDataChangeService;
import com.woodare.template.helper.cache.DownMerchantFundAccounts;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.DownAgent;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumFundAccountType;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownAgentDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.web.viewdata.downagent.DownAgentViewData;
import com.woodare.template.web.viewdata.downagent.SearchDownAgentViewData;
import com.woodare.template.web.viewdata.downmerchantfundaccount.DownMerchantFundAccountViewData;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;

/**
 * ClassName: AdminUserController
 * 
 * @description
 * @author Luke
 * @Date Oct. 16, 2018
 */
@Controller
@RequestMapping("/admin/downAgent")
public class AdminDownAgentController extends BaseController {

	private static Logger log = Logger.getLogger(AdminDownAgentController.class);

	@Autowired
	private IDownAgentDAO downAgentDAO;

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/export" })
	public ModelAndView index(SearchDownAgentViewData searchData, HttpServletResponse response) throws ControllerException {
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			searchData.setPageSize(Integer.MAX_VALUE);
		}

		IPagedList<DownAgent> items = downAgentDAO.searchDownAgents(searchData);

		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String responName = "agent-" + SDFFactory.DATETIME.format(new Date()) + ".csv";
			return this.exportToResponse(response, responName, formatString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downAgent/index"));
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(DownAgentViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downAgent/add"));
		DownAgent model = null;

		List<DownMerchantFundAccount> fundModels = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			model = downAgentDAO.findOne(data.getId());
			fundModels = downMerchantFundAccountDAO.searchByMchNo(model.getAgentNo());
		}

		mav.addObject("item", convertDetails(model));
		// 产品列表
		mav.addObject("fundModels", getProducts(fundModels));

		return mav;
	}

	/**
	 * @param existedFundItems
	 * @return
	 */
	private List<DownMerchantFundAccountViewData> getProducts(List<DownMerchantFundAccount> existedFundItems) {
		List<DownMerchantFundAccountViewData> products = new ArrayList<DownMerchantFundAccountViewData>();

		for (PasswayRouteMerchantViewData coinItem : PasswayRouteMerchants.getAll()) {
			DownMerchantFundAccountViewData fundItem = null;
			DownMerchantFundAccount existProduct = null;
			if (existedFundItems != null) {
				for (DownMerchantFundAccount tmpProduct : existedFundItems) {
					if (coinItem.getCoin().equals(tmpProduct.getCoin())) {
						existProduct = tmpProduct;
						break;
					}
				}
			}
			if (existProduct != null) {
				fundItem = SaftyBeanUtils.cloneTo(existProduct, DownMerchantFundAccountViewData.class);
			}
			else {
				fundItem = new DownMerchantFundAccountViewData();
				fundItem.setCoin(coinItem.getCoin());
			}

			fundItem.setCoinName(coinItem.getCoinName());
			if (fundItem.getFeeRatio() == null || new BigDecimal("-1").compareTo(fundItem.getFeeRatio()) == 0) {
				fundItem.setFeeRatio(new BigDecimal("0"));
			}
			if (fundItem.getAddFeeAmt() == null || new BigDecimal("-1").compareTo(fundItem.getAddFeeAmt()) == 0) {
				fundItem.setAddFeeAmt(new BigDecimal("0"));
			}

			if (EnumDownUserStatus.PENDING.equals(fundItem.getStatus())) {
				if (fundItem.getFeeRatio() != null && new BigDecimal("-1").compareTo(fundItem.getFeeRatio()) == 0) {
					fundItem.setFeeRatio(null);
				}
				if (fundItem.getAddFeeAmt() != null && new BigDecimal("-1").compareTo(fundItem.getAddFeeAmt()) == 0) {
					fundItem.setAddFeeAmt(null);
				}
			}
			else if (fundItem.getStatus() == null) {
				fundItem.setStatus(EnumDownUserStatus.PENDING);
			}
			products.add(fundItem);
		}
		return products;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addP(DownAgentViewData data) throws ControllerException {
		List<DownMerchantFundAccount> fundItems = new ArrayList<DownMerchantFundAccount>();
		if (data.getCoinArr() != null) {
			List<String> selProductTypes = data.getSelCoinArr() != null ? Arrays.asList(data.getSelCoinArr()) : new ArrayList<String>();

			for (int i = 0; i < data.getCoinArr().length; i++) {
				DownMerchantFundAccount fundItem = new DownMerchantFundAccount();
				fundItem.setMchNo(data.getAgentNo());
				fundItem.setCoin(data.getCoinArr()[i]);

				fundItem.setMchName(data.getName());
				fundItem.setAddFeeAmt(StringUtils.isNotEmpty(data.getAddFeeAmtArr()[i]) ? new BigDecimal(data.getAddFeeAmtArr()[i]) : new BigDecimal("-1"));
				fundItem.setFeeRatio(StringUtils.isNotEmpty(data.getFeeRatioArr()[i]) ? new BigDecimal(data.getFeeRatioArr()[i]) : new BigDecimal("-1"));

				if (selProductTypes.contains(fundItem.getCoin())) {
					fundItem.setStatus(EnumDownUserStatus.ACTIVE);
				}
				else {
					fundItem.setStatus(EnumDownUserStatus.PENDING);
				}
				fundItems.add(fundItem);
			}
		}

		String error = validError(data);
		if (StringUtils.isEmpty(error)) {
			DownAgent model = null;
			if (StringUtils.isNotEmpty(data.getId())) {
				model = downAgentDAO.findOne(data.getId());
			}
			boolean update = true;
			if (model == null) {
				model = new DownAgent();
				update = false;
			}

			SaftyBeanUtils.copyProperties(data, model, new String[] { "id", "agentNo" });
			if (update) {
				downAgentDAO.update(model);
			}
			else {
				model.setAgentNo(data.getAgentNo());
				downAgentDAO.save(model);
			}

			// 保存机构开通产品费率信息
			for (int i = 0; i < fundItems.size(); i++) {
				DownMerchantFundAccount fundItem = fundItems.get(i);

				// 生成账户余额表数据
				DownMerchantFundAccount fundModel = downMerchantFundAccountDAO.findByMchNoAndCoin(model.getAgentNo(), fundItem.getCoin());
				if (fundModel == null) {
					fundModel = fundItem;
					// SaftyBeanUtils.copyNotEmptyProperties(fundItem, fundModel, new String[] { "id" });

					fundModel.setMchNo(model.getAgentNo());
					fundModel.setMchName(model.getName());
					fundModel.setAccountType(EnumFundAccountType.Merchant);
					// 期初余额
					fundModel.setLastSettleAmt(new BigDecimal("0"));
					// 账户余额
					fundModel.setLastSettleAmt(new BigDecimal("0"));
					// 清算金额
					fundModel.setSettleOutAmt(new BigDecimal("0"));
					// 冻结金额
					fundModel.setFrozenAmt(new BigDecimal("0"));
					// 当日入账额
					fundModel.setCurInAmt(new BigDecimal("0"));
					// 提现金额
					fundModel.setCurOutAmt(new BigDecimal("0"));
					// 最后变更日期
					fundModel.setChangeDate(SDFFactory.DATE.format(new Date()));

					downMerchantFundAccountDAO.save(fundModel);
				}
				else {
					downMerchantFundAccountDAO.updateById(fundItem, fundModel.getId());
				}
			}

			cacheDataChangeService.notifyOthers(DownAgentData.class);
			cacheDataChangeService.notifyOthers(DownMerchantFundAccountData.class);

			return alertSuccess(getTemplate("/admin/downAgent/add"), convertDetails(model));
		}
		else {
			return alertFailed(getTemplate("/admin/downAgent/add"), data, error);
		}
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
				DownAgent model = downAgentDAO.findOne(itemId);
				if (model == null || model.getDeleteStatus() == EnumDeleteStatus.DELETED) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					downAgentDAO.deleteWithLogical(model);

					cacheDataChangeService.notifyOthers(DownAgentData.class);

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

	private String formatString(List<DownAgent> items) {
		StringBuffer sb = new StringBuffer();
		sb.append("代理商编号,姓名,电话,结算卡银行,结算卡卡号,状态\n");
		if (items != null) {
			for (DownAgent item : items) {
				sb.append(formatValue(item.getAgentNo(), true));
				sb.append(formatValue(item.getName()));
				sb.append(formatValue(item.getPhone(), true));
				sb.append(formatValue(item.getBankName()));
				sb.append(formatValue(item.getCardNo(), true));
				if (item.getStatus() == EnumDownUserStatus.ACTIVE) {
					sb.append(formatValue("使用中"));
				}
				else if (item.getStatus() == EnumDownUserStatus.PENDING) {
					sb.append(formatValue("未使用"));
				}
				else if (item.getStatus() == EnumDownUserStatus.HOLD) {
					sb.append(formatValue("暂停使用"));
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private ListResponseData<DownAgentViewData> convertToList(IPagedList<DownAgent> items) {
		ListResponseData<DownAgentViewData> response = new ListResponseData<DownAgentViewData>();
		if (items != null) {
			for (DownAgent item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownAgentViewData convert(DownAgent item) {
		DownAgentViewData viewData = SaftyBeanUtils.cloneTo(item, DownAgentViewData.class);

		if (item != null && StringUtils.isNotEmpty(item.getAgentNo())) {
			List<DownMerchantFundAccountData> products = DownMerchantFundAccounts.getByMchNo(item.getAgentNo());
			String fundSummry = "-";
			if (products != null && products.size() > 0) {
				fundSummry = "";
				for (DownMerchantFundAccountData product : products) {
					fundSummry += String.format("<span class='lbl-summary'>%s</span>= %s /%s", product.getCoin(), product.getFeeRatio() + "‰", product.getAddFeeAmt()) + "<br>";
				}
			}
			viewData.setFundSummary(fundSummry);
		}

		return viewData;
	}

	private DownAgentViewData convertDetails(DownAgent item) {
		DownAgentViewData viewData = convert(item);
		return viewData;
	}

	private String validError(DownAgentViewData data) {
		String error = "";
		return error;
	}
}
