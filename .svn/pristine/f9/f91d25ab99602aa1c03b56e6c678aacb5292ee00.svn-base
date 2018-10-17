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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumMercCategory;

/**
 * ClassName: DownMerchant
 * 
 * @description
 * @author Luke
 * @Date Oct. 26, 2018
 */
@Entity
@EntityDescriptor(name = "下游机构", category = "content")
public class DownMerchant extends AbstractBusiModel {
	private static final long serialVersionUID = -5303765049019581524L;

	/** 类别 */
	@Enumerated(EnumType.STRING)
	private EnumMercCategory mercCategory;

	/** 机构编号 */
	@Column(length = 20, unique = true)
	private String mchNo;

	/** 名称 */
	@Column(length = 100)
	private String name;

	/** 代理商ID */
	@Column(length = 20)
	private String agentNo;

	/** 加密Key */
	@Column(length = 64)
	private String encKey;

	/** 签名Key */
	@Column(length = 64)
	private String signKey;

	/** 代付KEY */
	@Column(length = 64)
	private String payKey;

	/** 信任IP */
	@Column(length = 256)
	private String limitIps;

	/** 通知消息 */
	private Boolean enableNotify;

	/** 开启资金明细 */
	private Boolean enableBalChgFlg;

	/** 已开通币类型，多个逗号分隔开 */
	@Column(length = 511)
	private String supportCoin;

	/** T0授信资金比，单位：百分之 */
	private Long creditRatio;

	/** 下一笔结算日期(由系统自动维护) */
	private String settleDate;

	/** 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

	/**
	 * @return the mercCategory
	 */
	public EnumMercCategory getMercCategory() {
		return mercCategory;
	}

	/**
	 * @param mercCategory
	 *            the mercCategory to set
	 */
	public void setMercCategory(EnumMercCategory mercCategory) {
		this.mercCategory = mercCategory;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the encKey
	 */
	public String getEncKey() {
		return encKey;
	}

	/**
	 * @param encKey
	 *            the encKey to set
	 */
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	/**
	 * @return the signKey
	 */
	public String getSignKey() {
		return signKey;
	}

	/**
	 * @param signKey
	 *            the signKey to set
	 */
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	/**
	 * @return the payKey
	 */
	public String getPayKey() {
		return payKey;
	}

	/**
	 * @param payKey
	 *            the payKey to set
	 */
	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	/**
	 * @return the limitIps
	 */
	public String getLimitIps() {
		return limitIps;
	}

	/**
	 * @param limitIps
	 *            the limitIps to set
	 */
	public void setLimitIps(String limitIps) {
		this.limitIps = limitIps;
	}

	/**
	 * @return the enableNotify
	 */
	public Boolean getEnableNotify() {
		return enableNotify;
	}

	/**
	 * @param enableNotify
	 *            the enableNotify to set
	 */
	public void setEnableNotify(Boolean enableNotify) {
		this.enableNotify = enableNotify;
	}

	/**
	 * @return the enableBalChgFlg
	 */
	public Boolean getEnableBalChgFlg() {
		return enableBalChgFlg;
	}

	/**
	 * @param enableBalChgFlg
	 *            the enableBalChgFlg to set
	 */
	public void setEnableBalChgFlg(Boolean enableBalChgFlg) {
		this.enableBalChgFlg = enableBalChgFlg;
	}

	/**
	 * @return the supportCoin
	 */
	public String getSupportCoin() {
		return supportCoin;
	}

	/**
	 * @param supportCoin
	 *            the supportCoin to set
	 */
	public void setSupportCoin(String supportCoin) {
		this.supportCoin = supportCoin;
	}

	/**
	 * @return the creditRatio
	 */
	public Long getCreditRatio() {
		return creditRatio;
	}

	/**
	 * @param creditRatio
	 *            the creditRatio to set
	 */
	public void setCreditRatio(Long creditRatio) {
		this.creditRatio = creditRatio;
	}

	/**
	 * @return the settleDate
	 */
	public String getSettleDate() {
		return settleDate;
	}

	/**
	 * @param settleDate
	 *            the settleDate to set
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
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
}
