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
package com.woodare.template.jpa.persistence.data.downmerchantdeposit;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import java.math.BigDecimal;
import com.woodare.template.jpa.model.data.EnumDepositMode;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * 
 * ClassName: DownMerchantDepositData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class DownMerchantDepositData extends AbstractData {
	private static final long serialVersionUID = -8425020894521497833L;

	private String id;

	private Date createDate;

	private Integer versionNo;

	private String mchNo;

	private String mchName;

	private String agentNo;

	private EnumDepositMode mode;

	private String transNo;

	private String transDate;

	private String realTransNo;

	private String realOrderDate;

	private EnumDownNoCardChannel channel;

	private String channelAccName;

	private String channelAccNo;

	private String tradeNo;

	private String orderDate;

	private BigDecimal price;

	private String purpose;

	private String merResv1;

	private String notifyUrl;

	private String accCardNo;

	private String accName;

	private String accIdCard;

	private String accTel;

	private String accBankCode;

	private String accBankName;

	private BigDecimal drawFeeRatio;

	private BigDecimal addDrawFeeAmt;

	private BigDecimal downDrawFee;

	private BigDecimal agtProfit;

	private BigDecimal profit;

	private String status;

	private String statusDesc;

	private Date statusChgDate;

	private String upNo;

	private String extra1;

	private String extra2;

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

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
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

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public EnumDepositMode getMode() {
		return mode;
	}

	public void setMode(EnumDepositMode mode) {
		this.mode = mode;
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

	public String getRealTransNo() {
		return realTransNo;
	}

	public void setRealTransNo(String realTransNo) {
		this.realTransNo = realTransNo;
	}

	public String getRealOrderDate() {
		return realOrderDate;
	}

	public void setRealOrderDate(String realOrderDate) {
		this.realOrderDate = realOrderDate;
	}

	public EnumDownNoCardChannel getChannel() {
		return channel;
	}

	public void setChannel(EnumDownNoCardChannel channel) {
		this.channel = channel;
	}

	public String getChannelAccName() {
		return channelAccName;
	}

	public void setChannelAccName(String channelAccName) {
		this.channelAccName = channelAccName;
	}

	public String getChannelAccNo() {
		return channelAccNo;
	}

	public void setChannelAccNo(String channelAccNo) {
		this.channelAccNo = channelAccNo;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getMerResv1() {
		return merResv1;
	}

	public void setMerResv1(String merResv1) {
		this.merResv1 = merResv1;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAccCardNo() {
		return accCardNo;
	}

	public void setAccCardNo(String accCardNo) {
		this.accCardNo = accCardNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccIdCard() {
		return accIdCard;
	}

	public void setAccIdCard(String accIdCard) {
		this.accIdCard = accIdCard;
	}

	public String getAccTel() {
		return accTel;
	}

	public void setAccTel(String accTel) {
		this.accTel = accTel;
	}

	public String getAccBankCode() {
		return accBankCode;
	}

	public void setAccBankCode(String accBankCode) {
		this.accBankCode = accBankCode;
	}

	public String getAccBankName() {
		return accBankName;
	}

	public void setAccBankName(String accBankName) {
		this.accBankName = accBankName;
	}

	public BigDecimal getDrawFeeRatio() {
		return drawFeeRatio;
	}

	public void setDrawFeeRatio(BigDecimal drawFeeRatio) {
		this.drawFeeRatio = drawFeeRatio;
	}

	public BigDecimal getAddDrawFeeAmt() {
		return addDrawFeeAmt;
	}

	public void setAddDrawFeeAmt(BigDecimal addDrawFeeAmt) {
		this.addDrawFeeAmt = addDrawFeeAmt;
	}

	public BigDecimal getDownDrawFee() {
		return downDrawFee;
	}

	public void setDownDrawFee(BigDecimal downDrawFee) {
		this.downDrawFee = downDrawFee;
	}

	public BigDecimal getAgtProfit() {
		return agtProfit;
	}

	public void setAgtProfit(BigDecimal agtProfit) {
		this.agtProfit = agtProfit;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getUpNo() {
		return upNo;
	}

	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

}
