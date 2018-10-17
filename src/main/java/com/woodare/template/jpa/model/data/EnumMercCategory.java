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
 * ClassName: EnumMercCategory
 * 
 * @description
 * @author Luke
 * @Date Dec 30, 2017
 */
public enum EnumMercCategory {
	// 网关&银联在线
	NONE("默认"),
	// 网关
	GATEWAY("网关支付"),
	// 银联在线
	UNIONPAY("银联在线"),;

	EnumMercCategory(String name) {
		this.name = name;
	}

	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the name
	 */
	public String getCode() {
		return this.toString();
	}
}
