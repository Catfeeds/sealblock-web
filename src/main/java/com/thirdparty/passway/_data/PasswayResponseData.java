package com.thirdparty.passway._data;

import java.math.BigDecimal;
import java.util.Map;

import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;

/**
 * 返回参数
 * 
 * @author Luke
 */
public class PasswayResponseData {
	
	

	private EnumPasswayOrderStatus status;

	// 订单状态 N2 C Code返回值0000时返回: 1 成功\ 2 失败\ 3 处理中 \ 4 通讯异常
	private EnumPasswayOrderStatus settleStatus;

	// 交易返回码 ANS20 C 订单应答码，见附件
	private String retCode;

	// 交易返回码描述 ANS1..1024 C 订单返回码描述
	private String retDesc;

	// 清算金额
	private BigDecimal settleAmt;

	// 代付结果描述
	private String retSettleDesc;

	// 代付金额
	private String retSettleAmt;

	// 通道订单编号
	private String passwayOrderId;

	// 通道绑定ID
	private String passwayBindId;
	
	// 自定义字段1
	private String extra1;

	// 自定义字段2
	private String extra2;

	private Map<String, ?> extraResp;

	private Object extraObject;

	private String replyText;

	private Boolean dupliBindedFlg = false;

	private Boolean requiredBindCard = false;

	// 转义后状态码
	private String localStatusCode;
	// 转义后状态描述
	private String localStatusDesc;

	// 其他字段数据
	private DownNoCardInvoiceData extraInvoice;

	
	
	/**
	 * @return the extraInvoice
	 */
	public DownNoCardInvoiceData getExtraInvoice() {
		return extraInvoice;
	}

	/**
	 * @param extraInvoice
	 *            the extraInvoice to set
	 */
	public void setExtraInvoice(DownNoCardInvoiceData extraInvoice) {
		this.extraInvoice = extraInvoice;
	}

	/**
	 * @return the settleAmt
	 */
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	/**
	 * @param settleAmt
	 *            the settleAmt to set
	 */
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	/**
	 * @return the retSettleAmt
	 */
	public String getRetSettleAmt() {
		return retSettleAmt;
	}

	/**
	 * @param retSettleAmt
	 *            the retSettleAmt to set
	 */
	public void setRetSettleAmt(String retSettleAmt) {
		this.retSettleAmt = retSettleAmt;
	}

	/**
	 * @return the localStatusCode
	 */
	public String getLocalStatusCode() {
		return localStatusCode;
	}

	/**
	 * @param localStatusCode
	 *            the localStatusCode to set
	 */
	public void setLocalStatusCode(String localStatusCode) {
		this.localStatusCode = localStatusCode;
	}

	/**
	 * @return the localStatusDesc
	 */
	public String getLocalStatusDesc() {
		return localStatusDesc;
	}

	/**
	 * @param localStatusDesc
	 *            the localStatusDesc to set
	 */
	public void setLocalStatusDesc(String localStatusDesc) {
		this.localStatusDesc = localStatusDesc;
	}

	/**
	 * @return the requiredBindCard
	 */
	public Boolean getRequiredBindCard() {
		return requiredBindCard;
	}

	/**
	 * @param requiredBindCard
	 *            the requiredBindCard to set
	 */
	public void setRequiredBindCard(Boolean requiredBindCard) {
		this.requiredBindCard = requiredBindCard;
	}

	/**
	 * @return the retSettleDesc
	 */
	public String getRetSettleDesc() {
		return retSettleDesc;
	}

	/**
	 * @param retSettleDesc
	 *            the retSettleDesc to set
	 */
	public void setRetSettleDesc(String retSettleDesc) {
		this.retSettleDesc = retSettleDesc;
	}

	/**
	 * @return the dupliBindedFlg
	 */
	public Boolean getDupliBindedFlg() {
		return dupliBindedFlg;
	}

	/**
	 * @param dupliBindedFlg
	 *            the dupliBindedFlg to set
	 */
	public void setDupliBindedFlg(Boolean dupliBindedFlg) {
		this.dupliBindedFlg = dupliBindedFlg;
	}

	/**
	 * @return the replyText
	 */
	public String getReplyText() {
		return replyText;
	}

	/**
	 * @param replyText
	 *            the replyText to set
	 */
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public EnumPasswayOrderStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(EnumPasswayOrderStatus settleStatus) {
		this.settleStatus = settleStatus;
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

	public String getPasswayBindId() {
		return passwayBindId;
	}

	public void setPasswayBindId(String passwayBindId) {
		this.passwayBindId = passwayBindId;
	}

	public String getPasswayOrderId() {
		return passwayOrderId;
	}

	public void setPasswayOrderId(String passwayOrderId) {
		this.passwayOrderId = passwayOrderId;
	}

	public EnumPasswayOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EnumPasswayOrderStatus status) {
		this.status = status;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetDesc() {
		return retDesc;
	}

	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}

	public Map<String, ?> getExtraResp() {
		return extraResp;
	}

	public void setExtraResp(Map<String, ?> extraResp) {
		this.extraResp = extraResp;
	}

	public Object getExtraObject() {
		return extraObject;
	}

	public void setExtraObject(Object extraObject) {
		this.extraObject = extraObject;
	}

}
