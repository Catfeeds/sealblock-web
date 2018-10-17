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
package com.woodare.template.jpa.persistence.data.downnocardinvoice;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import java.math.BigDecimal;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumOrderStatus;

/**
 * 
 * ClassName: DownNoCardInvoiceData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class DownNoCardInvoiceData extends AbstractData {
	private static final long serialVersionUID = 3645420330704734673L;

	private String id;

	private Date createDate;

	private EnumDownNoCardChannel channel;

	private String coin;

	private String coinName;

	private String mchNo;

	private String mchName;

	private String transNo;

	private String transDate;

	private String subject;

	private String tradeNo;

	private String orderDate;

	private String callbackUrl;

	private String notifyUrl;

	private String agentNo;

	private BigDecimal price;

	private BigDecimal feeRatio;

	private BigDecimal addFeeAmt;

	private BigDecimal subUserFee;

	private BigDecimal downRealPrice;

	private BigDecimal merchantFee;

	private BigDecimal agtProfitAmt;

	private BigDecimal profit;

	private String userNo;

	private String userName;

	private String userCertId;

	private String userPhone;

	private String merResv1;

	private String merResv2;

	private String upTransNo;

	private EnumOrderStatus status;

	private String statusDesc;

	private Date statusChgDate;

	private EnumOrderStatus fundStatus;

	private String fundStatusDesc;

	private Date fundChgDate;

	private String payLink;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public EnumDownNoCardChannel getChannel() {
		return channel;
	}

	public void setChannel(EnumDownNoCardChannel channel) {
		this.channel = channel;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getFeeRatio() {
		return feeRatio;
	}

	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}

	public BigDecimal getAddFeeAmt() {
		return addFeeAmt;
	}

	public void setAddFeeAmt(BigDecimal addFeeAmt) {
		this.addFeeAmt = addFeeAmt;
	}

	public BigDecimal getSubUserFee() {
		return subUserFee;
	}

	public void setSubUserFee(BigDecimal subUserFee) {
		this.subUserFee = subUserFee;
	}

	public BigDecimal getDownRealPrice() {
		return downRealPrice;
	}

	public void setDownRealPrice(BigDecimal downRealPrice) {
		this.downRealPrice = downRealPrice;
	}

	public BigDecimal getMerchantFee() {
		return merchantFee;
	}

	public void setMerchantFee(BigDecimal merchantFee) {
		this.merchantFee = merchantFee;
	}

	public BigDecimal getAgtProfitAmt() {
		return agtProfitAmt;
	}

	public void setAgtProfitAmt(BigDecimal agtProfitAmt) {
		this.agtProfitAmt = agtProfitAmt;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCertId() {
		return userCertId;
	}

	public void setUserCertId(String userCertId) {
		this.userCertId = userCertId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getMerResv1() {
		return merResv1;
	}

	public void setMerResv1(String merResv1) {
		this.merResv1 = merResv1;
	}

	public String getMerResv2() {
		return merResv2;
	}

	public void setMerResv2(String merResv2) {
		this.merResv2 = merResv2;
	}

	public String getUpTransNo() {
		return upTransNo;
	}

	public void setUpTransNo(String upTransNo) {
		this.upTransNo = upTransNo;
	}

	public EnumOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EnumOrderStatus status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Date getStatusChgDate() {
		return statusChgDate;
	}

	public void setStatusChgDate(Date statusChgDate) {
		this.statusChgDate = statusChgDate;
	}

	public EnumOrderStatus getFundStatus() {
		return fundStatus;
	}

	public void setFundStatus(EnumOrderStatus fundStatus) {
		this.fundStatus = fundStatus;
	}

	public String getFundStatusDesc() {
		return fundStatusDesc;
	}

	public void setFundStatusDesc(String fundStatusDesc) {
		this.fundStatusDesc = fundStatusDesc;
	}

	public Date getFundChgDate() {
		return fundChgDate;
	}

	public void setFundChgDate(Date fundChgDate) {
		this.fundChgDate = fundChgDate;
	}

	public String getPayLink() {
		return payLink;
	}

	public void setPayLink(String payLink) {
		this.payLink = payLink;
	}

}
