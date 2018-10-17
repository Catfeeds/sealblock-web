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
package com.woodare.template.jpa.persistence.data.submerchantfundaccount;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import java.math.BigDecimal;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;

/**
 * 
 * ClassName: SubMerchantFundAccountData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class SubMerchantFundAccountData extends AbstractData {
	private static final long serialVersionUID = -5914795942449489794L;

	private String id;

	private Date createDate;

	private String agentNo;

	private String mchNo;

	private String mchName;

	private String mchUserNo;

	private String userNo;

	private String coin;

	private String address;

	private String userName;

	private EnumDownUserStatus status;

	private BigDecimal lastSettleAmt;

	private BigDecimal settleInAmt;

	private BigDecimal settleOutAmt;

	private BigDecimal frozenAmt;

	private String changeDate;

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

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
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

	public String getMchUserNo() {
		return mchUserNo;
	}

	public void setMchUserNo(String mchUserNo) {
		this.mchUserNo = mchUserNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public EnumDownUserStatus getStatus() {
		return status;
	}

	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

	public BigDecimal getLastSettleAmt() {
		return lastSettleAmt;
	}

	public void setLastSettleAmt(BigDecimal lastSettleAmt) {
		this.lastSettleAmt = lastSettleAmt;
	}

	public BigDecimal getSettleInAmt() {
		return settleInAmt;
	}

	public void setSettleInAmt(BigDecimal settleInAmt) {
		this.settleInAmt = settleInAmt;
	}

	public BigDecimal getSettleOutAmt() {
		return settleOutAmt;
	}

	public void setSettleOutAmt(BigDecimal settleOutAmt) {
		this.settleOutAmt = settleOutAmt;
	}

	public BigDecimal getFrozenAmt() {
		return frozenAmt;
	}

	public void setFrozenAmt(BigDecimal frozenAmt) {
		this.frozenAmt = frozenAmt;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
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