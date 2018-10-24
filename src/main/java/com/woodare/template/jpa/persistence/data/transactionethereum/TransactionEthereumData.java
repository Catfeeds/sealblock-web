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
package com.woodare.template.jpa.persistence.data.transactionethereum;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;


/**
 * 
 * ClassName: TransactionEthereumData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class TransactionEthereumData extends AbstractData {
	private static final long serialVersionUID = -5914795942449489794L;

	private String id;

	private Date createDate;

	private String blockHash;

	private String recordDate;

	private String userNo;

	private String userName;

	private Boolean payFlag;

	private String fromAddress;

	private String toAddress;

	private Long blockNumber;

	private String amount;

	private Long gas;

	private Long gasPrice;

	private String gasWeight;

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

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
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

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Long getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getGas() {
		return gas;
	}

	public void setGas(Long gas) {
		this.gas = gas;
	}

	public Long getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(Long gasPrice) {
		this.gasPrice = gasPrice;
	}

	public String getGasWeight() {
		return gasWeight;
	}

	public void setGasWeight(String gasWeight) {
		this.gasWeight = gasWeight;
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
