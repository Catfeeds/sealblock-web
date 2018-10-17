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
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumQinfenStatus;

/**
 * 无卡交易自助提现追踪表
 * 
 * @author Luke
 */
@Entity
@EntityDescriptor(name = "无卡交易提现表", category = "content")
public class DownNoCardInvoiceTransfer extends AbstractBusiModel {
	private static final long serialVersionUID = 2788988413049005623L;

	/** 上游通道 */
	@Enumerated(EnumType.STRING)
	private EnumDownNoCardChannel channel;

	/** 渠道商户号 */
	@Column(length = 32)
	private String userId;

	/** 原交易流水号 */
	@Column(length = 64)
	private String transNo;

	/** 原交易订单时间, 格式：yyyyMMddHHmmss */
	@Column(length = 14)
	private String orderDate;

	/** 上游原交易水号 */
	@Column(length = 64)
	private String upNo;

	/** 实际提现流水号 */
	@Column(length = 64)
	private String transferNo;

	/** 实际提现日期 */
	@Column(length = 14)
	private String transferTime;

	/** 上游提现流水号 */
	@Column(length = 64)
	private String upTransferNo;

	/** 提现金额 */
	@Column(scale = 2, precision = 9)
	private BigDecimal transferPrice;

	/** 支付付款卡号 */
	@Column(length = 50)
	private String payCardNo;

	/** 收款人姓名 */
	@Column(length = 20)
	private String cardHolder;

	/** 收款卡号 */
	@Column(length = 30)
	private String cardNo;

	/** 收款联行行号 */
	@Column(length = 12)
	private String bankCode;

	/** 收款银行名称 */
	@Column(length = 128)
	private String bankName;

	/** 代付状态 */
	@Enumerated(EnumType.STRING)
	private EnumQinfenStatus qfStatus;

	/** 代付返回明细 */
	@Column(length = 20)
	private String qfRetCode;

	/** 代付返回明细 */
	@Column(length = 100)
	private String qfRetDesc;

	/** 状态变更时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date stsUpdTime;

	/** 备用字段1 */
	@Column(length = 128)
	private String extra1;

	/** 备用字段 2 */
	@Column(length = 128)
	private String extra2;

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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the transferNo
	 */
	public String getTransferNo() {
		return transferNo;
	}

	/**
	 * @param transferNo
	 *            the transferNo to set
	 */
	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	/**
	 * @return the transferTime
	 */
	public String getTransferTime() {
		return transferTime;
	}

	/**
	 * @param transferTime
	 *            the transferTime to set
	 */
	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}

	/**
	 * @return the upTransferNo
	 */
	public String getUpTransferNo() {
		return upTransferNo;
	}

	/**
	 * @param upTransferNo
	 *            the upTransferNo to set
	 */
	public void setUpTransferNo(String upTransferNo) {
		this.upTransferNo = upTransferNo;
	}

	/**
	 * @return the transferPrice
	 */
	public BigDecimal getTransferPrice() {
		return transferPrice;
	}

	/**
	 * @param transferPrice
	 *            the transferPrice to set
	 */
	public void setTransferPrice(BigDecimal transferPrice) {
		this.transferPrice = transferPrice;
	}

	/**
	 * @return the payCardNo
	 */
	public String getPayCardNo() {
		return payCardNo;
	}

	/**
	 * @param payCardNo
	 *            the payCardNo to set
	 */
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	/**
	 * @return the cardHolder
	 */
	public String getCardHolder() {
		return cardHolder;
	}

	/**
	 * @param cardHolder
	 *            the cardHolder to set
	 */
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode
	 *            the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the qfStatus
	 */
	public EnumQinfenStatus getQfStatus() {
		return qfStatus;
	}

	/**
	 * @param qfStatus
	 *            the qfStatus to set
	 */
	public void setQfStatus(EnumQinfenStatus qfStatus) {
		this.qfStatus = qfStatus;
	}

	/**
	 * @return the qfRetCode
	 */
	public String getQfRetCode() {
		return qfRetCode;
	}

	/**
	 * @param qfRetCode
	 *            the qfRetCode to set
	 */
	public void setQfRetCode(String qfRetCode) {
		this.qfRetCode = qfRetCode;
	}

	/**
	 * @return the qfRetDesc
	 */
	public String getQfRetDesc() {
		return qfRetDesc;
	}

	/**
	 * @param qfRetDesc
	 *            the qfRetDesc to set
	 */
	public void setQfRetDesc(String qfRetDesc) {
		this.qfRetDesc = qfRetDesc;
	}

	/**
	 * @return the stsUpdTime
	 */
	public Date getStsUpdTime() {
		return stsUpdTime;
	}

	/**
	 * @param stsUpdTime
	 *            the stsUpdTime to set
	 */
	public void setStsUpdTime(Date stsUpdTime) {
		this.stsUpdTime = stsUpdTime;
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
