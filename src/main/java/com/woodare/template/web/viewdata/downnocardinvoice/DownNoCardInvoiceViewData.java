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
package com.woodare.template.web.viewdata.downnocardinvoice;

import java.math.BigDecimal;
import java.util.Date;

import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;

/**
 * ClassName: DownNoCardInvoiceViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/01/09
 */
public class DownNoCardInvoiceViewData extends DownNoCardInvoiceData {
	private static final long serialVersionUID = 4644858587558158916L;
	private String regcode;

	private String accNameFormat;

	private String cardNoFormat;

	private String accIdCardFormat;

	private String payCardNoFormat;

	private String userTokenFormat;

	private String preOrderId;

	private BigDecimal fixedDownDrawFee;
	private BigDecimal fixedDownPayFee;

	// 0: 标记交易状态为成功；1：标记清算状态为成功
	private String oprStatusTyp;

	private String payCardBankName;

	private String settleCardBankName;

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

	/**
	 * @return the settleCardBankName
	 */
	public String getSettleCardBankName() {
		return settleCardBankName;
	}

	/**
	 * @param settleCardBankName
	 *            the settleCardBankName to set
	 */
	public void setSettleCardBankName(String settleCardBankName) {
		this.settleCardBankName = settleCardBankName;
	}

	/**
	 * @return the oprStatusTyp
	 */
	public String getOprStatusTyp() {
		return oprStatusTyp;
	}

	/**
	 * @param oprStatusTyp
	 *            the oprStatusTyp to set
	 */
	public void setOprStatusTyp(String oprStatusTyp) {
		this.oprStatusTyp = oprStatusTyp;
	}

	/**
	 * @return the payCardBankName
	 */
	public String getPayCardBankName() {
		return payCardBankName;
	}

	/**
	 * @param payCardBankName
	 *            the payCardBankName to set
	 */
	public void setPayCardBankName(String payCardBankName) {
		this.payCardBankName = payCardBankName;
	}

	/**
	 * @return the fixedDownDrawFee
	 */
	public BigDecimal getFixedDownDrawFee() {
		return fixedDownDrawFee;
	}

	/**
	 * @param fixedDownDrawFee
	 *            the fixedDownDrawFee to set
	 */
	public void setFixedDownDrawFee(BigDecimal fixedDownDrawFee) {
		this.fixedDownDrawFee = fixedDownDrawFee;
	}

	/**
	 * @return the fixedDownPayFee
	 */
	public BigDecimal getFixedDownPayFee() {
		return fixedDownPayFee;
	}

	/**
	 * @param fixedDownPayFee
	 *            the fixedDownPayFee to set
	 */
	public void setFixedDownPayFee(BigDecimal fixedDownPayFee) {
		this.fixedDownPayFee = fixedDownPayFee;
	}

	public String getPreOrderId() {
		return preOrderId;
	}

	public void setPreOrderId(String preOrderId) {
		this.preOrderId = preOrderId;
	}

	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}

	public String getAccNameFormat() {
		return accNameFormat;
	}

	public void setAccNameFormat(String accNameFormat) {
		this.accNameFormat = accNameFormat;
	}

	public String getAccIdCardFormat() {
		return accIdCardFormat;
	}

	public void setAccIdCardFormat(String accIdCardFormat) {
		this.accIdCardFormat = accIdCardFormat;
	}

	public String getPayCardNoFormat() {
		return payCardNoFormat;
	}

	public void setPayCardNoFormat(String payCardNoFormat) {
		this.payCardNoFormat = payCardNoFormat;
	}

	public String getCardNoFormat() {
		return cardNoFormat;
	}

	public void setCardNoFormat(String cardNoFormat) {
		this.cardNoFormat = cardNoFormat;
	}

	public String getUserTokenFormat() {
		return userTokenFormat;
	}

	public void setUserTokenFormat(String userTokenFormat) {
		this.userTokenFormat = userTokenFormat;
	}

}
