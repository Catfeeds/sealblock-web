package com.woodare.template.egw.base;

import com.alibaba.fastjson.JSONObject;
import com.thirdparty.passway._data.PasswayResponseData;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.model.DownNoCardInvoice;

/**
 * 异步回调通知
 * 
 */
public interface IPasswayNotifyEgw {

    /**
     * 
     * @param postDataString
     * @param getDataMap
     * @param invoice
     * @return
     */
    PasswayResponseData parseNotifyData(String postDataString, JSONObject getDataMap, DownNoCardInvoice invoice);

    /**
     * 
     * @param postDataString
     * @param getDataMap
     * @param deposit
     * @return
     */
    PasswayResponseData parseTransferNotifyData(String postDataString, JSONObject getDataMap, DownMerchantDeposit deposit);
}
