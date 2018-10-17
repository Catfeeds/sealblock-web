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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumOrderStatus;

/**
 * ClassName: SubMerchant
 * 
 * @description
 * @author Luke
 * @Date Oct. 15, 2018
 */
@Entity
@EntityDescriptor(name = "机构子商户", category = "content")
@Table(
		// 联合唯一索引键
		uniqueConstraints = {
				// 机构号 + 机构用户标识
				@UniqueConstraint(columnNames = { "mch_no", "mch_user_no" }),
				// 机构号 + 身份证号
				@UniqueConstraint(columnNames = { "mch_no", "user_cert_id" }),
				// 机构号 + 手机号
				@UniqueConstraint(columnNames = { "mch_no", "user_cert_id" }) },
		// 清算时间
		indexes = {
				// 平台用户标识
				@Index(columnList = "user_no", unique = true),
				// 用户币地址
				@Index(columnList = "address", unique = true),
		//
		})
public class SubMerchant extends AbstractBusiModel {
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

	/** 用户币地址 */
	@Column(name = "address", length = 128, nullable = false)
	private String address;

	/** 用户姓名 */
	@Column(length = 30, nullable = false)
	private String userName;

	/** 用户身份证号 */
	@Column(name = "user_cert_id", length = 30, nullable = false)
	private String userCertId;

	/** 用户联系电话 */
	@Column(name = "user_phone", length = 20, nullable = false)
	private String userPhone;

	/** 用户状态 */
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private EnumDownUserStatus status;

	/** 鉴权状态 */
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private EnumOrderStatus authStatus;

	/** 鉴权结果 */
	@Column(length = 50)
	private String authMessage;

	/** 鉴权日期 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date authDate;

	/** 已开通币类型，多个逗号分隔开 */
	@Column(length = 511)
	private String enabledCoin;

	// 谷歌验证key
	@Column(length = 16, nullable = false)
	private String googleSecret;

	/** 备用字段1 */
	@Column(length = 128)
	private String extra1;

	/** 备用字段 2 */
	@Column(length = 128)
	private String extra2;

	 

	/**
	 * @return the googleSecret
	 */
	public String getGoogleSecret() {
		return googleSecret;
	}

	/**
	 * @param googleSecret the googleSecret to set
	 */
	public void setGoogleSecret(String googleSecret) {
		this.googleSecret = googleSecret;
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
	 * @return the userCertId
	 */
	public String getUserCertId() {
		return userCertId;
	}

	/**
	 * @param userCertId
	 *            the userCertId to set
	 */
	public void setUserCertId(String userCertId) {
		this.userCertId = userCertId;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone
	 *            the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

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
	 * @return the authStatus
	 */
	public EnumOrderStatus getAuthStatus() {
		return authStatus;
	}

	/**
	 * @param authStatus
	 *            the authStatus to set
	 */
	public void setAuthStatus(EnumOrderStatus authStatus) {
		this.authStatus = authStatus;
	}

	/**
	 * @return the authMessage
	 */
	public String getAuthMessage() {
		return authMessage;
	}

	/**
	 * @param authMessage
	 *            the authMessage to set
	 */
	public void setAuthMessage(String authMessage) {
		this.authMessage = authMessage;
	}

	/**
	 * @return the authDate
	 */
	public Date getAuthDate() {
		return authDate;
	}

	/**
	 * @param authDate
	 *            the authDate to set
	 */
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	/**
	 * @return the enabledCoin
	 */
	public String getEnabledCoin() {
		return enabledCoin;
	}

	/**
	 * @param enabledCoin
	 *            the enabledCoin to set
	 */
	public void setEnabledCoin(String enabledCoin) {
		this.enabledCoin = enabledCoin;
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
