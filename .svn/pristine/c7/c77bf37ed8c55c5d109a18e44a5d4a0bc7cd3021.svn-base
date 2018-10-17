package com.woodare.template.jersery.webservice.busi.base;

import com.woodare.framework.exception.MessageWooException;
import com.woodare.template.jersery.servicedata.submerchant.SubMerchantServiceData;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * 子用户处理服务类
 * 
 * @author Luke
 */
public interface ISubMerchantService {

	/**
	 * 创建用户信息
	 * 
	 * @param reqData
	 * @param merchantData
	 * @return
	 * @throws MessageWooException
	 */
	SubMerchant createUser(SubMerchantServiceData reqData, DownMerchantData merchantData) throws MessageWooException;

	/**
	 * 修改用户信息
	 * 
	 * @param reqData
	 * @param model
	 * @param merchantData
	 * @return
	 * @throws MessageWooException
	 */
	SubMerchant updateUser(SubMerchantServiceData reqData, SubMerchant model, DownMerchantData merchantData) throws MessageWooException;

	/**
	 * 开通货币
	 * 
	 * @param reqData
	 * @param model
	 * @param merchantData
	 * @return
	 * @throws MessageWooException
	 */
	SubMerchant openCoin(SubMerchantServiceData reqData, SubMerchant model, DownMerchantData merchantData) throws MessageWooException;
}
