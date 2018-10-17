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
import javax.persistence.Index;
import javax.persistence.Table;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;

/**
 * ClassName: SubMerchantFundAccount
 * 
 * @description
 * @author Luke
 * @Date Oct. 15, 2018
 */
@Entity
@EntityDescriptor(name = "子商户货币明细", category = "content")
@Table(
		//
		indexes = {
				// 记录时间
				@Index(columnList = "create_date"),
				// 记录时间
				@Index(columnList = "create_date"),
				// 记录时间
				@Index(columnList = "tx_hash_no", unique = true) })
public class SubMerchantFundInvoice extends AbstractBusiModel {
	private static final long serialVersionUID = -5914795942449489794L;
	// TxHash:0xc4426f42af79b271ee92bedd11ebcf110206369a2943ae72e731ff8d24f385cc
	// TxReceipt Status:Success
	// Block Height:6269801 (250532 Block Confirmations)
	// TimeStamp:41 days 4 hrs ago (Sep-04-2018 10:35:23 AM +UTC)
	// From:0xb3822a42212662c621cd8ce442525ade337431a9
	// To:Contract 0xfc9d38f5be5f204bd7f4cfa98255ecad78190d3b
	// Tokens Transfered:
	// From 0xb3822a42212662c621cd8ce442525ade337431a9
	// To 0xd60fbc17421ec7d89f959889311cbd01e2f77509
	// for 100 ERC-20 (RGIT)
	// Value:0 Ether ($0.00)
	// Gas Limit: 77980
	// Gas Used By Transaction:51987
	// Gas Price:0.000000005
	// Ether (5 Gwei)
	// Actual Tx Cost/Fee:0.000259935
	// Ether ($0.05)
	// Nonce & {Position}:52 | {92}
	/** 平台用户标识 */
	@Column(name = "user_no", length = 32, nullable = false)
	private String userNo;

	/** 记录日期 */
	@Column(name = "record_date", length = 8, nullable = false)
	private String recordDate;

	/** 用户姓名 */
	@Column(length = 30, nullable = false)
	private String userName;

	/** 订单号 */
	@Column(name = "tx_hash_no", length = 128, nullable = false)
	private String txHashNo;

	/** 是否支付交易 */
	@Column(nullable = false)
	private Boolean payFlag;

	/** 用户地址 */
	@Column(name = "address", length = 128, nullable = false)
	private String address;

	/** 关联地址 */
	@Column(name = "refer_address", length = 128, nullable = false)
	private String referAddress;

	/** 交易额（交易额） */
	@Column(scale = 10, precision = 18, nullable = false)
	private BigDecimal price;

	/** 手续费（仅出帐有值） */
	@Column(scale = 10, precision = 18, nullable = false)
	private BigDecimal feeAmt;

	/** 变动日期 */
	@Column(name = "change_date", length = 8, nullable = false)
	private Date tradeTime;

	@Column(length = 20)
	private String gasPrice;

	@Column(length = 20)
	private String gasWeight;

	/** 备用字段1 */
	@Column(length = 128)
	private String extra1;

	/** 备用字段 2 */
	@Column(length = 128)
	private String extra2;

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo
	 *            the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return the recordDate
	 */
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * @param recordDate
	 *            the recordDate to set
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the txHashNo
	 */
	public String getTxHashNo() {
		return txHashNo;
	}

	/**
	 * @param txHashNo
	 *            the txHashNo to set
	 */
	public void setTxHashNo(String txHashNo) {
		this.txHashNo = txHashNo;
	}

	/**
	 * @return the payFlag
	 */
	public Boolean getPayFlag() {
		return payFlag;
	}

	/**
	 * @param payFlag
	 *            the payFlag to set
	 */
	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the referAddress
	 */
	public String getReferAddress() {
		return referAddress;
	}

	/**
	 * @param referAddress
	 *            the referAddress to set
	 */
	public void setReferAddress(String referAddress) {
		this.referAddress = referAddress;
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
	 * @return the feeAmt
	 */
	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * @param feeAmt
	 *            the feeAmt to set
	 */
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	/**
	 * @return the tradeTime
	 */
	public Date getTradeTime() {
		return tradeTime;
	}

	/**
	 * @param tradeTime
	 *            the tradeTime to set
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	/**
	 * @return the gasPrice
	 */
	public String getGasPrice() {
		return gasPrice;
	}

	/**
	 * @param gasPrice
	 *            the gasPrice to set
	 */
	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}

	/**
	 * @return the gasWeight
	 */
	public String getGasWeight() {
		return gasWeight;
	}

	/**
	 * @param gasWeight
	 *            the gasWeight to set
	 */
	public void setGasWeight(String gasWeight) {
		this.gasWeight = gasWeight;
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
