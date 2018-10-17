package com.woodare.template.egw.base;

import com.thirdparty.passway._data.PasswayResponseData;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;

/**
 * 通道交易核心处理接口
 * 
 * @author Luke
 *
 */
public interface IPasswayEgw {
    
    /**
     * 生成订单
     * 
     * @param reqData
     * @param viewData
     * @return
     * @throws AbstractWooException
     */
    PasswayResponseData makeOrder(DownNoCardInvoice invoice, DownNoCardInvoiceData viewData) throws AbstractWooException;
    
    /**
     * 支付结果同步
     * 
     * @param reqData
     * @return
     * @throws AbstractWooException
     */
    PasswayResponseData orderQuery(DownNoCardInvoice invoice) throws AbstractWooException;
    
    /**
     * 支付成功后处理
     * 
     * @param order
     */
    void afterPaidHandler(DownNoCardInvoice model);

	/**
	 * 
	 * 前端同步检查是否需远程查询
	 * 
	 * @param invoice
	 */
	boolean requireRemoteQueryOnPaymentCheck();
}
