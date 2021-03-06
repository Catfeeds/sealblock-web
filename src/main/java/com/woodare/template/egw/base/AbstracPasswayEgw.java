package com.woodare.template.egw.base;

import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.SystemPropertiesConstant;

/**
 * 渠道网关基类实现
 * 
 * @author Luke
 */
public abstract class AbstracPasswayEgw {
	protected static SysProperties prop = SysProperties.getInstance();

	/**
	 * @param invId
	 * @return
	 */
	protected String getPayNotifyUrl(String invId) {        // "root.base.url.alias"
		String path = prop.getProperty(SystemPropertiesConstant.CODE_BASE_URL_ALIAS);
		return path + "/directpay/notify/" + invId;
	}

	/**
	 * @param invId
	 * @return
	 */
	protected String getTransferNotifyUrl(String invId) {
		String path = prop.getProperty(SystemPropertiesConstant.CODE_BASE_URL_ALIAS);
		return path + "/directpay/transferNotify/" + invId;
	}

	/**
	 * @param invId
	 * @return
	 */
	protected String getPayFrontUrl(String invId) {
		String path = prop.getProperty(SystemPropertiesConstant.CODE_BASE_URL_ALIAS);
		return path + "/transmit/result/" + invId;
	}
}
