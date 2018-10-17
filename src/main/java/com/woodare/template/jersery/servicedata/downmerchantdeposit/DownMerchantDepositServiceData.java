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
package com.woodare.template.jersery.servicedata.downmerchantdeposit;

import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositData;

/**
 * ClassName: DownMerchantDepositServiceData
 * 下游商户存款数据
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2018/03/03
 */
public class DownMerchantDepositServiceData extends DownMerchantDepositData {
	private static final long serialVersionUID = 50910143613446108L;

	// 进入IP地址
	private String comingIpAddress;

	/**
	 * @return the comingIpAddress
	 */
	public String getComingIpAddress() {
		return comingIpAddress;
	}

	/**
	 * @param comingIpAddress
	 *            the comingIpAddress to set
	 */
	public void setComingIpAddress(String comingIpAddress) {
		this.comingIpAddress = comingIpAddress;
	}

}
