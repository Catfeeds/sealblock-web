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
package com.woodare.template.jpa.persistence.data.passwayroutemerchant;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;

/**
 * 
 * ClassName: PasswayRouteMerchantData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class PasswayRouteMerchantData extends AbstractData {
	private static final long serialVersionUID = -552716943758151832L;

	private String id;

	private Date createDate;

	private EnumDownNoCardChannel channel;

	private String coin;

	private String coinName;

	private Integer priceScale;

	private String signKey;

	private String encKey;

	private String extra;

	private String remark;

	private String feeText;

	private String startTime;

	private String endTime;

	private String minPerAmt;

	private String maxPerAmt;

	private String maxTotAmt;

	private EnumDownUserStatus status;

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

	public Integer getPriceScale() {
		return priceScale;
	}

	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getEncKey() {
		return encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFeeText() {
		return feeText;
	}

	public void setFeeText(String feeText) {
		this.feeText = feeText;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMinPerAmt() {
		return minPerAmt;
	}

	public void setMinPerAmt(String minPerAmt) {
		this.minPerAmt = minPerAmt;
	}

	public String getMaxPerAmt() {
		return maxPerAmt;
	}

	public void setMaxPerAmt(String maxPerAmt) {
		this.maxPerAmt = maxPerAmt;
	}

	public String getMaxTotAmt() {
		return maxTotAmt;
	}

	public void setMaxTotAmt(String maxTotAmt) {
		this.maxTotAmt = maxTotAmt;
	}

	public EnumDownUserStatus getStatus() {
		return status;
	}

	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

}
