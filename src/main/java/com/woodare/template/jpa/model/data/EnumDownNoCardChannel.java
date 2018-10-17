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
package com.woodare.template.jpa.model.data;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: EnumDownNoCardChannel
 * 
 * @description
 * @author Luke
 * @Date Feb 26, 2018
 */
public enum EnumDownNoCardChannel {
	AINO("aiNong", "0102030405060708", null, true),  // 爱农渠道, 支持网银、银联在线
	// 比特网
	Bitcoin("bitcoin", "0102030405060708", null, true),
	// 以太坊
	Ethereum("ethereum", "0102030405060708", null, true),;

	private String egwName;
	private String aeskey;
	private String extra;

	// 是否异步创建订单
	private Boolean async;

	private EnumDownNoCardChannel refer;

	/**
	 * @param egwName
	 *            渠道适配器
	 * @param aeskey
	 * @param extra
	 */
	EnumDownNoCardChannel(String egwName, String aeskey, String extra, Boolean async) {
		this.egwName = egwName;
		this.aeskey = aeskey;
		this.extra = extra;
		this.async = async;
	}

	public Boolean getAsync() {
		return async;
	}

	public EnumDownNoCardChannel getRefer() {
		return refer;
	}

	public String getEgwName() {
		return this.egwName;
	}

	public String getAesKey() {
		return this.aeskey;
	}

	public String getExtra() {
		return this.extra;
	}

	private static final Map<String, EnumDownNoCardChannel> stringToEnum = new HashMap<String, EnumDownNoCardChannel>();
	static {
		// Initialize map from constant name to enum constant
		for (EnumDownNoCardChannel blah : values()) {
			stringToEnum.put(blah.toString(), blah);
		}
	}

	public static EnumDownNoCardChannel fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

}
