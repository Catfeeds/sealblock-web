package com.woodare.template.jersery.webservice.busi.base;

import com.woodare.template.jersery.servicedata.downnocardinvoice.DownNoCardInvoiceServiceData;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * 机构交易业务处理服务
 * 
 * @author Luke
 */
public interface IDownNoCardInvoiceService {

	/**
	 * 下单支付
	 * 
	 * @param reqData
	 * @param downMerchant
	 * @param agent
	 * @return
	 * @throws Exception
	 */
	DownNoCardInvoice makeOrder(DownNoCardInvoiceServiceData reqData, DownMerchantData downMerchant, SubMerchant subMerchant) throws Exception;

	/**
	 * 重发支付短信
	 * 
	 * @param reqData
	 * @param model
	 * @param downMerchant
	 * @param subMerchant
	 * @return
	 * @throws Exception
	 */
	DownNoCardInvoice resendSms(DownNoCardInvoiceServiceData reqData, DownNoCardInvoice model) throws Exception;

	/**
	 * 支付确认
	 * 
	 * @param reqData
	 * @param model
	 * @param downMerchant
	 * @param subMerchant
	 * @return
	 * @throws Exception
	 */
	DownNoCardInvoice confirmOrder(DownNoCardInvoiceServiceData reqData, DownNoCardInvoice model) throws Exception;

	/**
	 * TODO:
	 * 
	 * @param downInvoice
	 * @param refreshFlag
	 *            刷新返回最新交易数据
	 * @return
	 * @throws Exception
	 */
	DownNoCardInvoice orderQuery(DownNoCardInvoice downInvoice, boolean refreshFlag) throws Exception;
}
