package com.woodare.template.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * @author Luke
 */
@Entity
@EntityDescriptor(name = "下游无卡交易附加信息表", category = "content")
public class DownNoCardInvoiceExtra extends AbstractBusiModel {
	private static final long serialVersionUID = 2788988413049005623L;

	/** 上游通道 */
	@Enumerated(EnumType.STRING)
	private EnumDownNoCardChannel channel;

	/** 付款卡号 */
	@Column(length = 50)
	private String payCardNo;

	/** 交易状态, 01支付链接；02支付网页内容 */
	@Column(length = 4)
	private String status;

	/** 交易状态描述 */
	@Column(length = 128)
	private String statusDesc;

	/** 渠道返回支付链接 */
	@Column(length = 256)
	private String payUrl;

	/** 渠道返回支付页面内容 */
	@Lob
	private String payHtml;
	
	/**
	 * @return the payCardNo
	 */
	public String getPayCardNo() {
		return payCardNo;
	}

	/**
	 * @param payCardNo the payCardNo to set
	 */
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc
	 *            the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the payUrl
	 */
	public String getPayUrl() {
		return payUrl;
	}

	/**
	 * @param payUrl
	 *            the payUrl to set
	 */
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	/**
	 * @return the payHtml
	 */
	public String getPayHtml() {
		return payHtml;
	}

	/**
	 * @param payHtml
	 *            the payHtml to set
	 */
	public void setPayHtml(String payHtml) {
		this.payHtml = payHtml;
	}
}
