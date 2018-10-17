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
package com.woodare.template.web.viewdata.downmerchantdeposit;

import java.util.Date;

import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositData;

/**
 * ClassName: DownMerchantDepositViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2018/03/03
 */
public class DownMerchantDepositViewData extends DownMerchantDepositData {
	private static final long serialVersionUID = 4644858587558158916L;

	/** 异步通知是否成功 */
	private Boolean notifySuccess;

	/** 异步通知次数 */
	private Integer notifyTimes;

	/** 末次异步通知时间 */
	private Date notifyLastDate;

	/**
	 * @return the notifySuccess
	 */
	public Boolean getNotifySuccess() {
		return notifySuccess;
	}

	/**
	 * @param notifySuccess
	 *            the notifySuccess to set
	 */
	public void setNotifySuccess(Boolean notifySuccess) {
		this.notifySuccess = notifySuccess;
	}

	/**
	 * @return the notifyTimes
	 */
	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	/**
	 * @param notifyTimes
	 *            the notifyTimes to set
	 */
	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	/**
	 * @return the notifyLastDate
	 */
	public Date getNotifyLastDate() {
		return notifyLastDate;
	}

	/**
	 * @param notifyLastDate
	 *            the notifyLastDate to set
	 */
	public void setNotifyLastDate(Date notifyLastDate) {
		this.notifyLastDate = notifyLastDate;
	}

}
