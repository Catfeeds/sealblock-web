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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;

/**
 * ClassName: TransactionEthereum
 * 
 * @description
 * @author Luke
 * @Date Oct. 20, 2018
 */
@Entity
@EntityDescriptor(name = "以太坊交易明细", category = "content")
@Table(
		//
		indexes = {
				// 记录时间
				@Index(columnList = "create_date"), })

public class TransactionEthereum extends AbstractBusiModel {
	private static final long serialVersionUID = -5914795942449489794L;

	// 主键采用外部流水号来唯一识别
	// /** 唯一标识 */
	// @Column(name = "tx_hash_key", length = 32, nullable = false)
	// private String txHashKey;

	/** 原始单号 */
	@Column(name = "block_hash", length = 128)
	private String blockHash;

	/** 记录日期 */
	@Column(name = "record_date", length = 8, nullable = false)
	private String recordDate;

	/** 用户标识 */
	@Column(name = "user_no", length = 32, nullable = false)
	private String userNo;

	/** 用户姓名 */
	@Column(length = 30, nullable = false)
	private String userName;

	/** 交易类型 */
	@Column(nullable = false)
	private Boolean payFlag;

	/** 付款地址 */
	@Column(length = 50, nullable = false)
	private String fromAddress;

	/** 收款地址 */
	@Column(length = 128, nullable = false)
	private String toAddress;

	// 区块高度
	@Column(nullable = false)
	private Long blockNumber;

	// 金额
	@Column(nullable = false)
	private String amount;

	//
	@Column(nullable = false)
	private Long gas;

	@Column(nullable = false)
	private Long gasPrice;

	@Column(length = 20)
	private String gasWeight;

	/** 备用字段1 */
	@Column(length = 128)
	private String extra1;

	/** 备用字段 2 */
	@Column(length = 128)
	private String extra2;

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the blockHash
	 */
	public String getBlockHash() {
		return blockHash;
	}

	/**
	 * @param blockHash
	 *            the blockHash to set
	 */
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
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
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress
	 *            the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress
	 *            the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * @return the blockNumber
	 */
	public Long getBlockNumber() {
		return blockNumber;
	}

	/**
	 * @param blockNumber
	 *            the blockNumber to set
	 */
	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}

	/**
	 * @return the gas
	 */
	public Long getGas() {
		return gas;
	}

	/**
	 * @param gas
	 *            the gas to set
	 */
	public void setGas(Long gas) {
		this.gas = gas;
	}

	/**
	 * @return the gasPrice
	 */
	public Long getGasPrice() {
		return gasPrice;
	}

	/**
	 * @param gasPrice
	 *            the gasPrice to set
	 */
	public void setGasPrice(Long gasPrice) {
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
