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
package com.woodare.template.jersery.servicedata.downnocardinvoice;

import com.woodare.core.jersery.IMchServiceData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;

/**
 * ClassName: DownNoCardInvoiceServiceData
 * 下游无卡交易 发票
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/01/09
 */
public class DownNoCardInvoiceServiceData extends DownNoCardInvoiceData implements IMchServiceData {
	private static final long serialVersionUID = 4644858587558158916L;

	private String payUrl;

	private String payStatus;

	private String token;

	private String smsCode;

	private String mobilePhone;

	private String downMercNo;// 下游商户号

	private String upChl;// 上游通道号

	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus
	 *            the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return the payUrl
	 */
	public String getPayUrl() {
		return payUrl;
	}

	/**
	 * @param payUrl
	 *            the payUrl to set
	 */
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDownMercNo() {
		return downMercNo;
	}

	public void setDownMercNo(String downMercNo) {
		this.downMercNo = downMercNo;
	}

	public String getUpChl() {
		return upChl;
	}

	public void setUpChl(String upChl) {
		this.upChl = upChl;
	}

}
