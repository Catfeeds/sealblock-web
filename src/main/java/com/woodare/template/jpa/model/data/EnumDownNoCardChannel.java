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

/**
 * ClassName: EnumDownNoCardChannel
 * 
 * @description
 * @author Luke
 * @Date Feb 26, 2018
 */
public enum EnumDownNoCardChannel {
	// 比特网
	Bitcoin("sealblock", "比特网", "bitcoin", "0102030405060708", "BTC"),

	// 以太坊
	Ethereum("sealblock", "以太坊", "ethereum", "0102030405060708", "ETH"),;

	private String friendName;
	private String serviceName;
	private String egwName;
	private String aesKey;
	private String networkName;

	/**
	 * @param egwName
	 *            渠道适配器
	 * @param aeskey
	 * @param extra
	 */
	EnumDownNoCardChannel(String egwName, String friendName, String serviceName, String aesKey, String networkName) {
		this.friendName = friendName;
		this.serviceName = serviceName;
		this.egwName = egwName;
		this.aesKey = aesKey;
		this.networkName = networkName;
	}

	/**
	 * @return the friendName
	 */
	public String getFriendName() {
		return friendName;
	}

	/**
	 * @return the simpleName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @return the egwName
	 */
	public String getEgwName() {
		return egwName;
	}

	/**
	 * @return the aesKey
	 */
	public String getAesKey() {
		return aesKey;
	}

	/**
	 * @return
	 */
	public String getNetworkName() {
		return networkName;
	}
}
