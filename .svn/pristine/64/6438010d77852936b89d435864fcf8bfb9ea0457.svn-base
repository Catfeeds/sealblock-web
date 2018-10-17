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

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.utils.ExcelUtils;
import com.woodare.framework.utils.ExcelUtils.ExportSetInfo;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.jersery.webservice.busi.base.IDownMerchantDepositService;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.model.NotifyRecord;
import com.woodare.template.jpa.model.data.EnumDateCate;
import com.woodare.template.jpa.model.data.EnumNotifyRecordType;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositSumData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositUpSumData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDepositDAO;
import com.woodare.template.jpa.persistence.persistence.INotifyRecordDAO;
import com.woodare.template.web.controller.helper.CellFormatterFactory;
import com.woodare.template.web.viewdata.downmerchantdeposit.DownMerchantDepositViewData;
import com.woodare.template.web.viewdata.downmerchantdeposit.SearchDownMerchantDepositViewData;

/**
 * ClassName: AdminDownMerchantDepositController
 * 
 * @description 代付功能管理
 * @author Luke
 * @Date Mar 3, 2018
 */
@Controller
@RequestMapping("/admin/downMerchantDeposit")
public class AdminDownMerchantDepositController extends BaseController {

	private static Logger log = Logger.getLogger(AdminDownMerchantDepositController.class);

	@Autowired
	private IDownMerchantDepositDAO downMerchantDepositDAO;

	@Autowired
	private IDownMerchantDepositService downNoCardInvoiceService;

	@Autowired
	private INotifyRecordDAO notifyRecordDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/sum", "/exportSum" })
	public ModelAndView sum(SearchDownMerchantDepositViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);

		List<DownMerchantDepositSumData> items = downMerchantDepositDAO.sumDeposit(searchData);
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String fileName = "deposit-sum-" + SDFFactory.DATETIME_DASH.format(new Date()) + ".csv";
			return this.exportToResponse(response, fileName, formatSumString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantDeposit/sum"));
		mav.addObject("items", items);
		mav.addObject("search", searchData);
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/upSum", "/exportUpSum" })
	public ModelAndView upSum(SearchDownMerchantDepositViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);

		List<DownMerchantDepositUpSumData> items = downMerchantDepositDAO.upSumDeposit(searchData);
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String fileName = "deposit-channel-" + SDFFactory.DATETIME_DASH.format(new Date()) + ".csv";
			return this.exportToResponse(response, fileName, formatUpSumString(items));
		}
		
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantDeposit/upSum"));
		mav.addObject("items", items);
		mav.addObject("search", searchData);
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/export" })
	public ModelAndView index(SearchDownMerchantDepositViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);
		
		IPagedList<DownMerchantDeposit> items = downMerchantDepositDAO.searchItems(searchData);

		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String fileName = "deposit-" + SDFFactory.DATETIME_DASH.format(new Date()) + ".xls";
			return this.exportToResponse(response, fileName, formatString(items));
		}
		
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantDeposit/index"));
		mav.addObject("search", searchData);
		mav.addObject("res", toViewData(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(DownMerchantDepositViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantDeposit/detail"));
		DownMerchantDeposit model = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			model = downMerchantDepositDAO.findOne(data.getId());
		}

		DownMerchantDepositViewData viewData = toViewData(model);
		if (viewData != null) {
			NotifyRecord notifyRecord = notifyRecordDAO.findOne(viewData.getId());
			if (notifyRecord != null) {
				viewData.setNotifySuccess(notifyRecord.getNotifySuccess());
				viewData.setNotifyTimes(notifyRecord.getNotifyTimes());
				viewData.setNotifyLastDate(notifyRecord.getNotifyLastDate());
			}
		}

		mav.addObject("item", viewData);
		return mav;
	}

	@RequestMapping(value = "/notify/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseData<Boolean> notify(@PathVariable String itemId) throws ControllerException, MessageWooException {
		AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);

		DownMerchantDeposit model = downMerchantDepositDAO.findOne(itemId);

		addToNotifyRecord(model);

		ret.setMessage("已加入队列");
		ret.setState(EResponseState.Successful);
		return ret;
	}

	// 更换清算代付流水号
	@RequestMapping(value = "/countSettleNo/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseData<Boolean> countSettleNo(@PathVariable String itemId, DownMerchantDepositViewData data) throws ControllerException, MessageWooException {
		AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
		ret.setMessage("该渠道不支持此功能");
		return ret;
	}

	@RequestMapping(value = "/check/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseData<Boolean> check(@PathVariable String itemId) throws ControllerException, MessageWooException {
		AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
		DownMerchantDeposit model = downMerchantDepositDAO.findOne(itemId);

		String message = "查询失败";
		try {
			model = this.downNoCardInvoiceService.transferQuery(model, true);
			message = "[" + model.getStatus() + "]" + model.getStatusDesc();
			ret.setState(EResponseState.Successful);
		} catch (AbstractWooException e) {
			message = e.getMessage();
		} catch (Exception e) {
			log.error(e, e);
			message = e.getMessage();
		}
		ret.setMessage(message);

		return ret;
	}

	/**
	 * @param searchData
	 */
	private void formatSearchData(SearchDownMerchantDepositViewData searchData) {
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			searchData.setPageSize(Integer.MAX_VALUE);
		}

		if (searchData.getDateCate() == null) {
			searchData.setDateCate(EnumDateCate.CUSTOM);
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MILLISECOND, -1);
		switch (searchData.getDateCate()) {
		case TODAY:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case YESTERDAY:
			c.add(Calendar.DAY_OF_MONTH, -1);
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case LAST_7_DAYS:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -7);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case LAST_30_DAYS:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -30);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		default:
			if (searchData.getEndDate() == null) {
				searchData.setEndDate(c.getTime());
			}
			else {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(searchData.getEndDate());
				c1.set(Calendar.HOUR_OF_DAY, 0);
				c1.set(Calendar.MINUTE, 0);
				c1.set(Calendar.SECOND, 0);
				c1.set(Calendar.MILLISECOND, 0);
				c1.add(Calendar.DAY_OF_MONTH, 1);
				c1.add(Calendar.MILLISECOND, -1);
				searchData.setEndDate(c1.getTime());
			}
			if (searchData.getStartDate() == null) {
				c.add(Calendar.DAY_OF_MONTH, -1);
				c.add(Calendar.MILLISECOND, 1);
				searchData.setStartDate(c.getTime());
			}
			else {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(searchData.getStartDate());
				c1.set(Calendar.HOUR_OF_DAY, 0);
				c1.set(Calendar.MINUTE, 0);
				c1.set(Calendar.SECOND, 0);
				c1.set(Calendar.MILLISECOND, 0);
				searchData.setStartDate(c1.getTime());
			}
			
			// 如果使用精确条件检索，则忽略掉时间戳
			if(StringUtils.isEmpty(searchData.getTradeNo()) && StringUtils.isEmpty(searchData.getTransNo())) {
				if (SDFFactory.DATE.format(searchData.getEndDate()).equals(SDFFactory.DATE.format(searchData.getStartDate()))) {
					String date = SDFFactory.DATE.format(searchData.getEndDate());
					try {
						if (StringUtils.isNotEmpty(searchData.getStartTime())) {
							searchData.setStartDate(SDFFactory.DATETIME.parse(date + searchData.getStartTime().replaceAll(":", "")));
						}
						if (StringUtils.isNotEmpty(searchData.getEndTime())) {
							searchData.setEndDate(SDFFactory.DATETIME.parse(date + searchData.getEndTime().replaceAll(":", "")));
						}
					} catch (Exception e) {
					}
				}	
			}
			break;
		}
	}

	/**
	 * @param model
	 */
	private void addToNotifyRecord(DownMerchantDeposit model) {
		NotifyRecord record = notifyRecordDAO.findOne(model.getId());
		if (record == null) {
			record = new NotifyRecord();
			record.setId(model.getId());
			record.setNotifyType(EnumNotifyRecordType.DEPOSIT);
			record.setTradeNo(model.getTradeNo());
			record.setTransNo(model.getTransNo());
			record.setNotifyTimes(0);
			record.setNotifySuccess(false);
			record.setNotifyUrl(model.getNotifyUrl());
			record.setNotifyLastDate(new Date());
			notifyRecordDAO.saveForce(record);
		}
		else {
			record.setNotifyTimes(0);
			record.setNotifyType(EnumNotifyRecordType.DEPOSIT);
			record.setCreateDate(new Date());
			record.setNotifySuccess(false);
			record.setNotifyLastDate(new Date());
			notifyRecordDAO.updateForce(record);
		}
	}

	private byte[] formatString(List<DownMerchantDeposit> items) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String[]> headNames = new ArrayList<String[]>();
		headNames.add(new String[] { "渠道", "渠道商户", "平台流水号", "机构号", "机构名称", "清算类型", "用途", "代付金额", "手续费", "收款帐号", "收款人", "机构流水号", "机构订单时间", "代付状态", "状态描述" });
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "channel", "channelAccName", "transNo", "mchNo", "mchName", "mode", "purpose", "price", "downDrawFee", "accCardNo", "accName", "tradeNo", "orderDate", "status", "statusDesc" });

		ExportSetInfo setInfo = new ExportSetInfo();
		setInfo.addFomatter("channel", CellFormatterFactory.NO_CARD_CHANNEL);
		setInfo.addFomatter("status", CellFormatterFactory.TRANSFER_STATUS);
		setInfo.addFomatter("mode", CellFormatterFactory.TRANSFER_MODE);

		LinkedHashMap<String, List<?>> objsMap = new LinkedHashMap<String, List<?>>();
		objsMap.put("数据", items);
		setInfo.setObjsMap(objsMap);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "代付记录" });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(baos);
		try {
			ExcelUtils.export2Excel(setInfo);
		} catch (Exception e) {
			log.error(e, e);
		}
		return baos.toByteArray();
	}

	/**
	 * @param items
	 * @return
	 * @throws ParseException
	 */
	private String formatSumString(List<DownMerchantDepositSumData> items) {
		StringBuffer sb = new StringBuffer();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		sb.append("机构号,机构名称,代付成功率,总代付金额,总笔数,成功金额,成功数,总手续费\n");
		if (items != null) {
			for (DownMerchantDepositSumData item : items) {
				sb.append(formatValue(item.getMchNo(), true));
				sb.append(formatValue(item.getMchName()));

				if (item.getTotalCount() > 0) {
					if (item.getCount() > 0L) {
						sb.append(formatValue(numberFormat.format((float) item.getCount() / (float) (item.getCount()) * 100) + "%"));
					}
					else {
						sb.append(formatValue("0%"));
					}
				}
				else {
					sb.append(formatValue("0%"));
				}
				sb.append(formatValue(item.getTotalPrice()));
				sb.append(formatValue(item.getTotalCount()));
				sb.append(formatValue(item.getPrice()));
				sb.append(formatValue(item.getCount()));
				sb.append(formatValue(item.getFeePrice()));
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private String formatUpSumString(List<DownMerchantDepositUpSumData> items) {
		StringBuffer sb = new StringBuffer();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		sb.append("通道号,通道商户,代付成功率,总代付金额,总笔数,成功金额,成功数,总费率\n");
		if (items != null) {
			for (DownMerchantDepositUpSumData item : items) {
				sb.append(formatValue(item.getChannel()));
				sb.append(formatValue(item.getChannelAccName()));
				if (item.getTotalCount() > 0) {
					sb.append(formatValue(numberFormat.format((float) item.getCount() / (float) item.getTotalCount() * 100) + "%"));
				}
				else {
					sb.append(formatValue("0%"));
				}
				sb.append(formatValue(item.getTotalPrice()));
				sb.append(formatValue(item.getTotalCount()));
				sb.append(formatValue(item.getPrice()));
				sb.append(formatValue(item.getCount()));
				sb.append(formatValue(item.getFeePrice()));
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * @param items
	 * @return
	 */
	private ListResponseData<DownMerchantDepositViewData> toViewData(IPagedList<DownMerchantDeposit> items) {
		ListResponseData<DownMerchantDepositViewData> response = new ListResponseData<DownMerchantDepositViewData>();
		if (items != null) {
			for (DownMerchantDeposit item : items) {
				response.addItem(toViewData(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	/**
	 * @param item
	 * @return
	 */
	private DownMerchantDepositViewData toViewData(DownMerchantDeposit item) {
		DownMerchantDepositViewData viewData = SaftyBeanUtils.cloneTo(item, DownMerchantDepositViewData.class);
		if (viewData != null) {
		}
		return viewData;
	}
}
