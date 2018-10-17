package com.woodare.template.egw.base;

import com.thirdparty.passway._data.PasswayResponseData;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;

/**
 * 渠道账户类
 * 
 */
public interface IPasswayAccountEgw {

    /**
     * 
     * @param merchant
     * @return
     * @throws AbstractWooException
     */
    PasswayResponseData doAccQuery(PasswayRouteMerchantData merchant) throws AbstractWooException ;
}
