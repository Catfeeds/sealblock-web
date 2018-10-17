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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;

/**
 * ClassName: SubMerchantFundAccount
 * 
 * @description
 * @author Luke
 * @Date Oct. 15, 2018
 */
@Entity
@EntityDescriptor(name = "子商户货币账户", category = "content")
@Table(
		// 联合唯一索引键
		uniqueConstraints = {
				// 机构日期 + 机构流水号
				@UniqueConstraint(columnNames = { "user_no", "coin" }) },
		//
		indexes = {
				// 变动时间
				@Index(columnList = "change_date"),
				// 币地址
				@Index(columnList = "address") })
public class SubMerchantFundAccount extends AbstractBusiModel {
	private static final long serialVersionUID = -5914795942449489794L;

	/** 代理商编号 */
	@Column(length = 20, nullable = false)
	private String agentNo;

	/** 机构号 */
	@Column(name = "mch_no", length = 20, nullable = false)
	private String mchNo;

	/** 机构名称 **/
	@Column(length = 100)
	private String mchName;

	/** 机构用户标识 */
	@Column(name = "mch_user_no", length = 32, nullable = false)
	private String mchUserNo;

	/** 平台用户标识 */
	@Column(name = "user_no", length = 32, nullable = false)
	private String userNo;

	/** 币标识, eth, usdt */
	@Column(name = "coin", length = 10, nullable = false)
	private String coin;

	/** 用户币地址 */
	@Column(name = "address", length = 128, nullable = false)
	private String address;

	/** 用户姓名 */
	@Column(length = 30, nullable = false)
	private String userName;

	/** 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

	/** 期初余额 */
	@Column(scale = 8, precision = 18, nullable = false)
	private BigDecimal lastSettleAmt;

	/** 账户余额 */
	@Column(scale = 8, precision = 18, nullable = false)
	private BigDecimal settleInAmt;

	/** 消费金额 */
	@Column(scale = 8, precision = 18, nullable = false)
	private BigDecimal settleOutAmt;

	/** 冻结金额 */
	@Column(scale = 8, precision = 18, nullable = false)
	private BigDecimal frozenAmt;

	/** 变动日期 */
	@Column(name = "change_date", length = 8, nullable = false)
	private String changeDate;

	/** 备用字段1 */
	@Column(length = 128)
	private String extra1;

	/** 备用字段 2 */
	@Column(length = 128)
	private String extra2;

	/**
	 * @return the status
	 */
	public EnumDownUserStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

	/**
	 * @return the coin
	 */
	public String getCoin() {
		return coin;
	}

	/**
	 * @param coin
	 *            the coin to set
	 */
	public void setCoin(String coin) {
		this.coin = coin;
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
	 * @return the mchUserNo
	 */
	public String getMchUserNo() {
		return mchUserNo;
	}

	/**
	 * @param mchUserNo
	 *            the mchUserNo to set
	 */
	public void setMchUserNo(String mchUserNo) {
		this.mchUserNo = mchUserNo;
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
	 * @return the lastSettleAmt
	 */
	public BigDecimal getLastSettleAmt() {
		return lastSettleAmt;
	}

	/**
	 * @param lastSettleAmt
	 *            the lastSettleAmt to set
	 */
	public void setLastSettleAmt(BigDecimal lastSettleAmt) {
		this.lastSettleAmt = lastSettleAmt;
	}

	/**
	 * @return the settleInAmt
	 */
	public BigDecimal getSettleInAmt() {
		return settleInAmt;
	}

	/**
	 * @param settleInAmt
	 *            the settleInAmt to set
	 */
	public void setSettleInAmt(BigDecimal settleInAmt) {
		this.settleInAmt = settleInAmt;
	}

	/**
	 * @return the settleOutAmt
	 */
	public BigDecimal getSettleOutAmt() {
		return settleOutAmt;
	}

	/**
	 * @param settleOutAmt
	 *            the settleOutAmt to set
	 */
	public void setSettleOutAmt(BigDecimal settleOutAmt) {
		this.settleOutAmt = settleOutAmt;
	}

	/**
	 * @return the frozenAmt
	 */
	public BigDecimal getFrozenAmt() {
		return frozenAmt;
	}

	/**
	 * @param frozenAmt
	 *            the frozenAmt to set
	 */
	public void setFrozenAmt(BigDecimal frozenAmt) {
		this.frozenAmt = frozenAmt;
	}

	/**
	 * @return the changeDate
	 */
	public String getChangeDate() {
		return changeDate;
	}

	/**
	 * @param changeDate
	 *            the changeDate to set
	 */
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
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
