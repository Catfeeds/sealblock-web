package com.woodare.template.egw.base;

import com.thirdparty.passway._data.PasswayResponseData;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositData;

/**
 * 代付功能类
 * 
 */
public interface IPasswayTransferEgw {

    /**
     * 
     * @param deposit
     * @param viewData
     * @return
     */
    PasswayResponseData doTransferApply(DownMerchantDeposit deposit, DownMerchantDepositData viewData) throws AbstractWooException ;

    /**
     * 
     * @param deposit
     * @return
     */
    PasswayResponseData transferQuery(DownMerchantDeposit deposit) throws AbstractWooException ;
}
