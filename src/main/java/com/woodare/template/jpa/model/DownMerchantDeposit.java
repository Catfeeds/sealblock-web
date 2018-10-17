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
package com.woodare.template.jpa.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDepositMode;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * ClassName: DownMerchantDeposit
 * 唯一索引：transNo
 * 唯一索引：mchNo + orderNo
 * 索引：transDate
 * 
 * @description
 * @author Luke
 * @Date Feb 28, 2018
 */
@Entity
@EntityDescriptor(name = "机构提现表", category = "content")
public class DownMerchantDeposit extends AbstractBusiModel {
	private static final long serialVersionUID = -8425020894521497833L;

	/** 接口版本号 */
	@Column(length = 64)
	private Integer versionNo;

	/** 商户号 **/
	@Column(length = 20, nullable = false)
	private String mchNo;

	/** 商户名 **/
	@Column(length = 100)
	private String mchName;

	/** 代理商 **/
	@Column(length = 256)
	private String agentNo;

	/** 代付模式 */
	@Enumerated(EnumType.ORDINAL)
	private EnumDepositMode mode;

	/** 平台代付流水号 */
	@Column(length = 64, nullable = false)
	private String transNo;

	/** 代付登记日期 */
	@Column(length = 8, nullable = false)
	private String transDate;

	/** 外发渠道流水号 */
	@Column(length = 64, nullable = false)
	private String realTransNo;

	/** 外发渠道代付日期YYYYMMDDhhmmss */
	@Column(length = 14, nullable = false)
	private String realOrderDate;

	/** 上游通道 */
	@Enumerated(EnumType.STRING)
	private EnumDownNoCardChannel channel;

	/** 通道账户名称 */
	@Column(length = 20)
	private String channelAccName;

	/** 通道账户编号 */
	@Column(length = 64)
	private String channelAccNo;

	/** 下游交易流水号 */
	@Column(length = 64, nullable = false)
	private String tradeNo;

	/** 订单时间, 格式：yyyyMMddHHmmss */
	@Column(length = 14, nullable = false)
	private String orderDate;

	/** 交易金额 */
	@Column(scale = 2, precision = 9)
	private BigDecimal price;

	/** 用途 */
	@Column(length = 50)
	private String purpose;

	/** 保留字 */
	@Column(length = 128)
	private String merResv1;

	/** 异步通知地址 */
	@Column(length = 512)
	private String notifyUrl;

	/** 结算卡号 */
	@Column(length = 30)
	private String accCardNo;

	/** 结算卡户名 */
	@Column(length = 100)
	private String accName;

	/** 结算卡身份证 */
	@Column(length = 30)
	private String accIdCard;

	/** 结算卡预留电话 */
	@Column(length = 20)
	private String accTel;

	/** 结算卡联行行号 */
	@Column(length = 20)
	private String accBankCode;

	/** 结算卡银行名称 */
	@Column(length = 80)
	private String accBankName;

	/** 代付费率，单位：千分之 */
	@Column(scale = 3, precision = 6, nullable = false)
	private BigDecimal drawFeeRatio;

	/** 单笔代付加收费，单位：元 */
	@Column(scale = 2, precision = 9, nullable = false)
	private BigDecimal addDrawFeeAmt;

	/** 代付手续费 */
	@Column(scale = 2, precision = 9, nullable = false)
	private BigDecimal downDrawFee;

	/** 代理商利润 */
	@Column(scale = 2, precision = 9)
	private BigDecimal agtProfit;

	/** 平台利润 */
	@Column(scale = 2, precision = 9)
	private BigDecimal profit;

	/** 代付状态 */
	@Column(length = 2)
	private String status;

	/** 代付状态描述 */
	@Column(length = 128)
	private String statusDesc;

	/** 状态变更时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date statusChgDate;

	/** 上游流水号 */
	@Column(length = 64)
	private String upNo;

	/* 备用字段1 */
	@Column(length = 128)
	private String extra1;

	/* 备用字段2 */
	@Column(length = 128)
	private String extra2;

	/**
	 * @return the agentNo
	 */
	public String getAgentNo() {
		return agentNo;
	}

	/**
	 * @param agentNo
	 *            the agentNo to set
	 */
	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	/**
	 * @return the mode
	 */
	public EnumDepositMode getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(EnumDepositMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the realOrderDate
	 */
	public String getRealOrderDate() {
		return realOrderDate;
	}

	/**
	 * @param realOrderDate
	 *            the realOrderDate to set
	 */
	public void setRealOrderDate(String realOrderDate) {
		this.realOrderDate = realOrderDate;
	}

	/**
	 * @return the realTransNo
	 */
	public String getRealTransNo() {
		return realTransNo;
	}

	/**
	 * @param realTransNo
	 *            the realTransNo to set
	 */
	public void setRealTransNo(String realTransNo) {
		this.realTransNo = realTransNo;
	}

	/**
	 * @return the versionNo
	 */
	public Integer getVersionNo() {
		return versionNo;
	}

	/**
	 * @param versionNo
	 *            the versionNo to set
	 */
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * @return the mchNo
	 */
	public String getMchNo() {
		return mchNo;
	}

	/**
	 * @param mchNo
	 *            the mchNo to set
	 */
	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	/**
	 * @return the mchName
	 */
	public String getMchName() {
		return mchName;
	}

	/**
	 * @param mchName
	 *            the mchName to set
	 */
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	/**
	 * @return the transNo
	 */
	public String getTransNo() {
		return transNo;
	}

	/**
	 * @param transNo
	 *            the transNo to set
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 * @return the transDate
	 */
	public String getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate
	 *            the transDate to set
	 */
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	/**
	 * @return the channel
	 */
	public EnumDownNoCardChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(EnumDownNoCardChannel channel) {
		this.channel = channel;
	}

	/**
	 * @return the channelAccName
	 */
	public String getChannelAccName() {
		return channelAccName;
	}

	/**
	 * @param channelAccName
	 *            the channelAccName to set
	 */
	public void setChannelAccName(String channelAccName) {
		this.channelAccName = channelAccName;
	}

	/**
	 * @return the channelAccNo
	 */
	public String getChannelAccNo() {
		return channelAccNo;
	}

	/**
	 * @param channelAccNo
	 *            the channelAccNo to set
	 */
	public void setChannelAccNo(String channelAccNo) {
		this.channelAccNo = channelAccNo;
	}

	/**
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * @param tradeNo
	 *            the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the merResv1
	 */
	public String getMerResv1() {
		return merResv1;
	}

	/**
	 * @param merResv1
	 *            the merResv1 to set
	 */
	public void setMerResv1(String merResv1) {
		this.merResv1 = merResv1;
	}

	/**
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * @param notifyUrl
	 *            the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	/**
	 * @return the accCardNo
	 */
	public String getAccCardNo() {
		return accCardNo;
	}

	/**
	 * @param accCardNo
	 *            the accCardNo to set
	 */
	public void setAccCardNo(String accCardNo) {
		this.accCardNo = accCardNo;
	}

	/**
	 * @return the accName
	 */
	public String getAccName() {
		return accName;
	}

	/**
	 * @param accName
	 *            the accName to set
	 */
	public void setAccName(String accName) {
		this.accName = accName;
	}

	/**
	 * @return the accIdCard
	 */
	public String getAccIdCard() {
		return accIdCard;
	}

	/**
	 * @param accIdCard
	 *            the accIdCard to set
	 */
	public void setAccIdCard(String accIdCard) {
		this.accIdCard = accIdCard;
	}

	/**
	 * @return the accTel
	 */
	public String getAccTel() {
		return accTel;
	}

	/**
	 * @param accTel
	 *            the accTel to set
	 */
	public void setAccTel(String accTel) {
		this.accTel = accTel;
	}

	/**
	 * @return the accBankCode
	 */
	public String getAccBankCode() {
		return accBankCode;
	}

	/**
	 * @param accBankCode
	 *            the accBankCode to set
	 */
	public void setAccBankCode(String accBankCode) {
		this.accBankCode = accBankCode;
	}

	/**
	 * @return the accBankName
	 */
	public String getAccBankName() {
		return accBankName;
	}

	/**
	 * @param accBankName
	 *            the accBankName to set
	 */
	public void setAccBankName(String accBankName) {
		this.accBankName = accBankName;
	}

	/**
	 * @return the drawFeeRatio
	 */
	public BigDecimal getDrawFeeRatio() {
		return drawFeeRatio;
	}

	/**
	 * @param drawFeeRatio
	 *            the drawFeeRatio to set
	 */
	public void setDrawFeeRatio(BigDecimal drawFeeRatio) {
		this.drawFeeRatio = drawFeeRatio;
	}

	/**
	 * @return the addDrawFeeAmt
	 */
	public BigDecimal getAddDrawFeeAmt() {
		return addDrawFeeAmt;
	}

	/**
	 * @param addDrawFeeAmt
	 *            the addDrawFeeAmt to set
	 */
	public void setAddDrawFeeAmt(BigDecimal addDrawFeeAmt) {
		this.addDrawFeeAmt = addDrawFeeAmt;
	}

	/**
	 * @return the downDrawFee
	 */
	public BigDecimal getDownDrawFee() {
		return downDrawFee;
	}

	/**
	 * @param downDrawFee
	 *            the downDrawFee to set
	 */
	public void setDownDrawFee(BigDecimal downDrawFee) {
		this.downDrawFee = downDrawFee;
	}

	/**
	 * @return the agtProfit
	 */
	public BigDecimal getAgtProfit() {
		return agtProfit;
	}

	/**
	 * @param agtProfit
	 *            the agtProfit to set
	 */
	public void setAgtProfit(BigDecimal agtProfit) {
		this.agtProfit = agtProfit;
	}

	/**
	 * @return the profit
	 */
	public BigDecimal getProfit() {
		return profit;
	}

	/**
	 * @param profit
	 *            the profit to set
	 */
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc
	 *            the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the statusChgDate
	 */
	public Date getStatusChgDate() {
		return statusChgDate;
	}

	/**
	 * @param statusChgDate
	 *            the statusChgDate to set
	 */
	public void setStatusChgDate(Date statusChgDate) {
		this.statusChgDate = statusChgDate;
	}

	/**
	 * @return the upNo
	 */
	public String getUpNo() {
		return upNo;
	}

	/**
	 * @param upNo
	 *            the upNo to set
	 */
	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}

	/**
	 * @return the extra1
	 */
	public String getExtra1() {
		return extra1;
	}

	/**
	 * @param extra1
	 *            the extra1 to set
	 */
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	/**
	 * @return the extra2
	 */
	public String getExtra2() {
		return extra2;
	}

	/**
	 * @param extra2
	 *            the extra2 to set
	 */
	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

}
