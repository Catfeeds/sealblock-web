package com.woodare.template.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;

/**
 * @author Luke
 */
@Entity
@EntityDescriptor(name = "公链币配置", category = "content")
public class PasswayRouteMerchant extends AbstractBusiModel {
	private static final long serialVersionUID = -552716943758151832L;

	/** 公链类型 **/
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private EnumDownNoCardChannel channel;

	/** 币标识, eth, usdt */
	@Column(length = 10)
	private String coin;

	/** 币名称 */
	@Column(length = 20)
	private String coinName;

	/** 精度位数 */
	private Integer priceScale;

	/** 签名KEY密文 */
	@Column(length = 512)
	private String signKey;

	/** 加密KEY密文 */
	@Column(length = 512)
	private String encKey;

	/** 额外参数 **/
	@Lob
	private String extra;

	/** 额外参数 **/
	@Column(length = 128)
	private String remark;

	/** 信息, **/
	@Column(length = 500)
	private String feeText;

	/** 开始时间 **/
	@Column(length = 6)
	private String startTime;

	/** 结束时间 **/
	@Column(length = 6)
	private String endTime;

	/** precision交易额 */
	@Column(length = 20)
	private String minPerAmt;

	/** 单笔最大额 */
	@Column(length = 20)
	private String maxPerAmt;

	/** 日累计交易额 */
	@Column(length = 20)
	private String maxTotAmt;

	/** 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

	/**
	 * @return the maxTotAmt
	 */
	public String getMaxTotAmt() {
		return maxTotAmt;
	}

	/**
	 * @param maxTotAmt
	 *            the maxTotAmt to set
	 */
	public void setMaxTotAmt(String maxTotAmt) {
		this.maxTotAmt = maxTotAmt;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	 * @return the coinName
	 */
	public String getCoinName() {
		return coinName;
	}

	/**
	 * @param coinName
	 *            the coinName to set
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	/**
	 * @return the minPerAmt
	 */
	public String getMinPerAmt() {
		return minPerAmt;
	}

	/**
	 * @param minPerAmt
	 *            the minPerAmt to set
	 */
	public void setMinPerAmt(String minPerAmt) {
		this.minPerAmt = minPerAmt;
	}

	/**
	 * @return the maxPerAmt
	 */
	public String getMaxPerAmt() {
		return maxPerAmt;
	}

	/**
	 * @param maxPerAmt
	 *            the maxPerAmt to set
	 */
	public void setMaxPerAmt(String maxPerAmt) {
		this.maxPerAmt = maxPerAmt;
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
	 * @return the priceScale
	 */
	public Integer getPriceScale() {
		return priceScale;
	}

	/**
	 * @param priceScale
	 *            the priceScale to set
	 */
	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
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
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the feeText
	 */
	public String getFeeText() {
		return feeText;
	}

	/**
	 * @param feeText
	 *            the feeText to set
	 */
	public void setFeeText(String feeText) {
		this.feeText = feeText;
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
