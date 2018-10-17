package com.thirdparty.passway.debit.aino;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thirdparty.passway._base.AbstractPasswayApi;
import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.thirdparty.passway._data.AbstractRequestData;
import com.thirdparty.passway._data.PasswayRequestData;
import com.thirdparty.passway._data.PasswayResponseData;
import com.thirdparty.passway.debit.aino.util.AiNongConstant;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.utils.Md5Utils;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.SystemPropertiesConstant;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.data.EnumDepositMode;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;

/**
 * @author Luke
 */
public class AiNongApi extends AbstractPasswayApi {
	private static final Log log = LogFactory.getLog(AiNongApi.class);

	// 需内容做Base64加密
	private static String[] base64Keys = { "subject", "body", "remark", "respMsg" };
	// 需内容做Base64加密,并所有子域采用json数据格式
	private static String[] base64JsonKeys = { "customerInfo", "accResv", "riskRateInfo", "billQueryInfo", "billDetailInfo" };

	// 应答码 应答描述
	public static Map<String, String> map = new HashMap<String, String>();
	public static Map<String, String> transferFailMap = new HashMap<String, String>();

	static {
		map.put("0000", "接受通知成功（异步交易时才会出现）");
		map.put("0001", "参数错误");
		map.put("0002", "签名错误");
		map.put("1001", "交易成功");
		map.put("1002", "交易失败");
		map.put("1003", "已退款");
		map.put("1111", "交易进行中");
		map.put("2000", "无效商户");
		map.put("2001", "重复交易");
		map.put("2002", "查无此交易");
		map.put("2003", "交易金额超限");
		map.put("2004", "原交易不存在或状态不正确");
		map.put("2005", "与原交易信息不符");
		map.put("2006", "已超过最大查询次数或操作过于频繁");
		map.put("2007", "风控受限");
		map.put("2008", "交易不在受理时间范围内");
		map.put("2009", "扣款成功但交易超过指定支付时间");
		map.put("2010", "订单金额不匹配");
		map.put("2011", "订单币种不匹配");
		map.put("2012", "卡信息或银行预留手机号有误");
		map.put("2013", "密码错误次数超限");
		map.put("2014", "密码验证失败");
		map.put("2015", "短信验证码错误");
		map.put("2016", "CVN验证失败");
		map.put("2017", "身份证号有误");
		map.put("2018", "短信验证码发送次数超限");
		map.put("2019", "短信验证码发送频率过高");
		map.put("2020", "短信验证码验证错误次数超限");
		map.put("2021", "短信校验码已过期");
		map.put("2022", "姓名有误");
		map.put("2023", "银行预留手机号有误");
		map.put("2024", "银行卡无效或状态有误");
		map.put("2025", "余额不足");
		map.put("2026", "请持卡人与发卡银行联系");
		map.put("9999", "系统繁忙");

		transferFailMap.put("0001", "参数错误");
		transferFailMap.put("0002", "签名错误");
		transferFailMap.put("1002", "交易失败");
		transferFailMap.put("2000", "无效商户");
		transferFailMap.put("2002", "查无此交易");
		transferFailMap.put("2003", "交易金额超限");
		transferFailMap.put("2004", "原交易不存在或状态不正确");
		transferFailMap.put("2007", "风控受限");
		transferFailMap.put("2008", "交易不在受理时间范围内");
		transferFailMap.put("2010", "订单金额不匹配");
		transferFailMap.put("2011", "订单币种不匹配");
		transferFailMap.put("2012", "卡信息或银行预留手机号有误");
		transferFailMap.put("2013", "密码错误次数超限");
		transferFailMap.put("2014", "密码验证失败");
		transferFailMap.put("2015", "短信验证码错误");
		transferFailMap.put("2016", "CVN验证失败");
		transferFailMap.put("2017", "身份证号有误");
		transferFailMap.put("2018", "短信验证码发送次数超限");
		transferFailMap.put("2019", "短信验证码发送频率过高");
		transferFailMap.put("2020", "短信验证码验证错误次数超限");
		transferFailMap.put("2021", "短信校验码已过期");
		transferFailMap.put("2022", "姓名有误");
		transferFailMap.put("2023", "银行预留手机号有误");
		transferFailMap.put("2024", "银行卡无效或状态有误");
		transferFailMap.put("2025", "余额不足");
		transferFailMap.put("2026", "请持卡人与发卡银行联系");

	}

	@Override
	protected String getPasswayName() {
		return "AiNong";
	}

	@Override
	protected void preHandler(AbstractRequestData reqData) {
	}

	/**
	 * @param postDataString
	 * @param getDataMap
	 * @param invoice
	 * @return
	 */
	public PasswayResponseData parseNotifyData(String postDataString, JSONObject getDataMap, DownNoCardInvoice invoice) {
		PasswayResponseData respData = new PasswayResponseData();
		respData.setReplyText("99999");

		Map<String, String> reqData = null;
		if (getDataMap.isEmpty()) {
			reqData = recoverLinkString2Map(postDataString);
		}
		else {
			reqData = new HashMap<String, String>();
			for (String k : getDataMap.keySet()) {
				reqData.put(k, getDataMap.getString(k));
			}
		}

		deconverData(reqData);

		String signature = reqData.get("signature");

		PasswayRouteMerchantData routeMerchantSetting = null;// PasswayRouteMerchants.get(invoice.getChannel(), invoice.getChannelAccNo());

		String signVal = getSignVal(reqData, routeMerchantSetting.getSignKey());
		// 验签成功
		if (signature.equals(signVal)) {
			// 应答码 respCode
			String respCode = reqData.get("respCode");
			// 应答信息 respMsg
			String respMsg = reqData.get("respMsg");

			// 成功
			if ("0000".equals(respCode) || "1001".equals(respCode)) {
				respMsg = "交易成功";
				respData.setStatus(EnumPasswayOrderStatus.S);

				// accessType=0&bizType=000000&channelId=chinagpay&currency=CNY&merId=929010095023247&merOrderId=D1803010202294263100&merResv1=&respCode=1001&respMsg=交易成功&resv=&settleAmount=&settleCurrency=&settleDate=20180301&succTime=20180301020448&txnAmt=0000000000000010&txnSubType=01&txnTime=20180301001520&txnType=01&version=1.0.0JENB7ZXKeJ7DP7z7xjdgxY5zNc6X5Hsg

				// 清算金额 settleAmount M
				// respData.setSettleAmt(new BigDecimal(reqData.get("settleAmount")).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				// 清算日期 settleDate M
				// 交易成功时间 succTime M
				// 可退金额 canRefAmt M
			}
			// 处理中
			else if ("1111".equals(respCode) || "2006".equals(respCode) || "2009".equals(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}
			// 失败
			else if (map.containsKey(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
			// 不能识别错误码当作待查证
			else {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}

			respData.setRetCode(respCode);
			respData.setRetDesc(respMsg);

			respData.setReplyText("success");
		}
		else {
			respData.setReplyText("验签失败");
		}

		return respData;
	}

	/**
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayResponseData makeOrder(PasswayRequestData reqData) throws AbstractWooException {
		DownNoCardInvoice invoice = reqData.getInvoice();
		/// ** 上游通道 */ ///** 通道账户编号 */
		PasswayRouteMerchantData routeMerchantSetting = null;// PasswayRouteMerchants.get(invoice.getChannel(), invoice.getChannelAccNo());

		Map<String, String> requestMap = new HashMap<String, String>();
		// 签名方法 signMethod M 取值:MD5
		requestMap.put("signMethod", "MD5");
		// 消息版本号 version M 取值：1.0.0
		requestMap.put("version", "1.0.0");
		// 交易类型 txnType M 取值：01
		requestMap.put("txnType", "01");
		// 交易子类型 txnSubType M 取值：00
		requestMap.put("txnSubType", "00");
		// 产品类型 bizType M 取值：000000
		requestMap.put("bizType", "000000");
		// 接入类型 accessType M 取值：0
		requestMap.put("accessType", "0");
		// 接入方式 accessMode M 取值：01
		requestMap.put("accessMode", "01");
		// 商户号 merId M 由支付平台分配给商户的唯一编号
		// 商户订单号 merOrderId M 商户订单号
		requestMap.put("merOrderId", invoice.getTransNo());
		// 订单发送时间 txnTime M 格式：yyyyMMddHHmmss
		requestMap.put("txnTime", invoice.getOrderDate());
		// 交易金额 txnAmt M 交易单位为分
		requestMap.put("txnAmt", invoice.getPrice().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_EVEN).toString());
		// 交易币种 currency M 三位 ISO 货币代码，目前仅支持人民币 CNY
		requestMap.put("currency", "CNY");
		// 前台通知地址 frontUrl M 用户在银行端支付完成后返回商户端的地址
		requestMap.put("frontUrl", reqData.getFrontUrl());
		// 后台通知地址 backUrl M 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知
		requestMap.put("backUrl", reqData.getNotifyUrl());
		// 支付方式 payType M 取值：0203
		// requestMap.put("payType", "0203");
		// 商品标题 subject O 上送报文时需BASE64
		requestMap.put("subject", invoice.getSubject());
		// 商品描述 body O 上送报文时需BASE64
		requestMap.put("body", "技术服务费"); // invoice.getDescription()
		// 请求保留域 merResv1 O
		requestMap.put("merResv1", "");

		// 01网银；02银联在线
		String payType = null;// invoice.getPayType();
		if ("01".equals(payType)) {
			requestMap.put("payType", "0201");
			// 收银台模式 00， 指定银行模式01
			requestMap.put("txnSubType", "00");
			// if (StringUtils.isNotEmpty(invoice.getPayBankCode())) {
			// requestMap.put("txnSubType", "01");
			// requestMap.put("bankId", invoice.getPayBankCode());
			// }
			// else {
			// requestMap.put("txnSubType", "00");
			// }
		}
		else if ("02".equals(payType)) {
			requestMap.put("payType", "0203");
		}

		// String extraHtml = null;
		// if(StringUtils.isNotEmpty(invoice.getPayCardNo())) {
		// extraHtml = "<script>";
		// extraHtml += "try{startAlert(\"<img src='images/s_loadding.gif' style='vertical-align: middle;' />处理中，请稍候...\", 3000);}catch(e){}";
		// extraHtml += "$(function(){";
		// //extraHtml += "try{$(\"body\").append(\"<div style='position:fixed;top: 0;right: 0; bottom: 0;left: 0;z-index:9999;overflow: hidden;background: rgba(0,0,0,0.1);color:#fff;'><div style='margin:35% auto;width:140px;'><img
		// src='images/s_loadding.gif' style='vertical-align: middle;'>处理中，请稍候...</div></div>")}catch(e){}";
		// extraHtml += "$('input[name=accNo]').val('" + invoice.getPayCardNo() + "').closest('form').submit();";
		// extraHtml += "})";
		// extraHtml += "</script>";
		// }
		// 签名信息 signature M 签名信息，详见签名机制
		requestMap.put("signature", getSignVal(requestMap, routeMerchantSetting.getSignKey()));

		PasswayResponseData respData = new PasswayResponseData();
		try {
			String now = String.valueOf(System.currentTimeMillis());
			JSONObject respJson = null;// this.postDataProxy("MakeOrder", now, AiNongConstant.API.MAKE_ORDER, requestMap, routeMerchantSetting, invoice);

			respData.setExtraObject(respJson);

			if ("0000".equals(respJson.getString("code"))) {
				respData.setRetCode(respJson.getString("code"));
				respData.setRetDesc("下单成功");
				respData.setPasswayOrderId(invoice.getTransNo());
				respData.setStatus(EnumPasswayOrderStatus.S);
				respData.setExtra2(respJson.getString("url"));
			}
			else {
				respData.setRetCode(respJson.getString("code"));
				respData.setRetDesc(respJson.getString("message"));
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
		} catch (Exception e) {
			log.error(e, e);
			respData.setStatus(EnumPasswayOrderStatus.F);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	public PasswayResponseData orderQuery(PasswayRequestData reqData) throws AbstractWooException {
		DownNoCardInvoice invoice = reqData.getInvoice();

		PasswayRouteMerchantData routeMerchantSetting = null;// PasswayRouteMerchants.get(invoice.getChannel(), invoice.getChannelAccNo());

		Map<String, String> requestMap = new HashMap<String, String>();
		// 签名方法 signMethod M 取值:MD5
		requestMap.put("signMethod", "MD5");
		// 消息版本号 version M 取值：1.0.0
		requestMap.put("version", "1.0.0");
		// 交易类型 txnType M 取值：00
		requestMap.put("txnType", "00");
		// 交易子类型 txnSubType M 交易查询取值：01\退款查询取值：00
		requestMap.put("txnSubType", "01");
		// 商户号 merId M 由支付平台分配给商户的唯一编号
		// 商户订单号 merOrderId M 商户订单号
		requestMap.put("merOrderId", invoice.getTransNo());

		// 签名信息 signature M 签名信息，详见签名机制
		requestMap.put("signature", getSignVal(requestMap, routeMerchantSetting.getSignKey()));

		PasswayResponseData respData = new PasswayResponseData();
		try {
			String now = String.valueOf(System.currentTimeMillis());
			JSONObject respJson = this.postData("OrderQuery", now, AiNongConstant.API.ORDER_QUERY, requestMap, routeMerchantSetting);

			respData.setExtraObject(respJson);
			// 应答码 respCode
			String respCode = respJson.getString("respCode");
			// 应答信息 respMsg
			String respMsg = respJson.getString("respMsg");

			// 成功
			if ("0000".equals(respCode) || "1001".equals(respCode)) {
				respMsg = "交易成功";
				respData.setStatus(EnumPasswayOrderStatus.S);

				// 清算金额 settleAmount M
				// respData.setSettleAmt(new BigDecimal(respJson.getString("settleAmount")).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				// 清算日期 settleDate M
				// 交易成功时间 succTime M
				// 可退金额 canRefAmt M
			}
			// 处理中
			else if ("1111".equals(respCode) || "2006".equals(respCode) || "2009".equals(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}
			// 失败
			else if (map.containsKey(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
			// 不能识别错误码当作待查证
			else {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}

			respData.setRetCode(respCode);
			respData.setRetDesc(respMsg);
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumPasswayOrderStatus.R);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			log.error(e, e);
			respData.setStatus(EnumPasswayOrderStatus.R);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}

		log.debug("orderQuery Leave");
		return respData;
	}

	/**
	 * 账户余额查询
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayResponseData accBalQuery(PasswayRequestData reqData) throws AbstractWooException {
		DownNoCardInvoice invoice = reqData.getInvoice();

		PasswayRouteMerchantData routeMerchantSetting = null;// PasswayRouteMerchants.get(invoice.getChannel(), invoice.getChannelAccNo());

		Map<String, String> requestMap = new HashMap<String, String>();

		// 签名方法 signMethod M 取值：MD5
		requestMap.put("signMethod", "MD5");
		// 消息版本号 version M 默认1.0.0
		requestMap.put("version", "1.0.0");
		// 交易类型 txnType M 71：余额查询【虚拟账户余额查询】？确定查询来源
		requestMap.put("txnType", "71");
		// 交易子类型 txnSubType M 默认：00
		requestMap.put("txnSubType", "00");
		// 商户号 merId M
		// 签名信息 signature M 签名信息，详见签名机制
		requestMap.put("signature", getSignVal(requestMap, routeMerchantSetting.getSignKey()));

		PasswayResponseData respData = new PasswayResponseData();
		try {
			String now = String.valueOf(System.currentTimeMillis());
			JSONObject respJson = this.postData("AccBalQuery", now, AiNongConstant.API.ACC_BAL_QUERY, requestMap, routeMerchantSetting);

			respData.setExtraObject(respJson);

			// 应答码 respCode
			String respCode = respJson.getString("respCode");
			// 应答信息 respMsg
			String respMsg = respJson.getString("respMsg");

			// 消息版本号 version M
			// 交易类型 txnType M 71：余额查询
			// 交易子类型 txnSubType M 默认：00
			// 商户号 merId M
			// 账户可用余额 balance M 单位：“分”
			// T0授信额度 creditLines M 单位：“分”
			// 冻结余额 frozenAmt M 单位：“分”
			// 欠费金额 owedAmt M 单位：“分”
			// 当日入金 curInAmt M 单位：“分”
			// 当日出金 curOutAmt M 单位：“分”

			// 成功
			if ("0000".equals(respCode)) {
				respMsg = "查询成功";
				respData.setStatus(EnumPasswayOrderStatus.S);
			}
			// 失败
			else {
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
			respData.setRetCode(respCode);
			respData.setRetDesc(respMsg);
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumPasswayOrderStatus.F);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			log.error(e, e);
			respData.setStatus(EnumPasswayOrderStatus.F);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 代付结果查证
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayResponseData transferQuery(PasswayRequestData reqData) throws AbstractWooException {
		if ("dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
			System.setProperty("socksProxySet", "true");
			System.setProperty("socksProxyHost", "127.0.0.1");
			System.setProperty("socksProxyPort", "27070");
		}

		DownMerchantDeposit deposit = reqData.getDeposit();

		PasswayRouteMerchantData routeMerchantSetting = null;// PasswayRouteMerchants.get(deposit.getChannel(), deposit.getChannelAccNo());

		Map<String, String> requestMap = new HashMap<String, String>();

		// 签名方法 signMethod M 取值：MD5
		requestMap.put("signMethod", "MD5");
		// 消息版本号 version M 1.0.0
		requestMap.put("version", "1.0.0");
		// 交易类型 txnType M 00：查询交易
		requestMap.put("txnType", "00");
		// 交易子类型 txnSubType M 默认01
		requestMap.put("txnSubType", "01");
		// 商户号 merId M
		// 商户订单号 merOrderId M 商户订单号
		requestMap.put("merOrderId", deposit.getTransNo());
		// 签名信息 signature M 签名信息，详见签名机制
		requestMap.put("signature", getSignVal(requestMap, routeMerchantSetting.getSignKey()));

		PasswayResponseData respData = new PasswayResponseData();
		try {
			String now = String.valueOf(System.currentTimeMillis());
			JSONObject respJson = this.postData("TransferQuery", now, AiNongConstant.API.TransferQuery, requestMap, routeMerchantSetting);

			respData.setExtraObject(respJson);

			// 应答码 respCode
			String respCode = respJson.getString("respCode");
			// 应答信息 respMsg
			String respMsg = respJson.getString("respMsg");

			// 1001 交易成功
			if ("1001".equals(respCode)) {
				respMsg = "代付成功";
				respData.setStatus(EnumPasswayOrderStatus.S);
			}
			else if (transferFailMap.containsKey(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
			else {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}
			respData.setRetCode(respCode);
			respData.setRetDesc(respMsg);
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumPasswayOrderStatus.R);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			log.error(e, e);
			respData.setStatus(EnumPasswayOrderStatus.R);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 代付登记
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayResponseData transferApply(PasswayRequestData reqData) throws AbstractWooException {
		DownMerchantDeposit deposit = reqData.getDeposit();

		PasswayRouteMerchantData routeMerchantSetting = null;// PasswayRouteMerchants.get(deposit.getChannel(), deposit.getChannelAccNo());

		Map<String, String> requestMap = new HashMap<String, String>();

		// 签名方法 signMethod M 取值：MD5
		requestMap.put("signMethod", "MD5");
		// 签名信息 signature M
		// 消息版本号 version M
		requestMap.put("version", "1.0.0");
		// 交易类型 txnType M 12：代付
		requestMap.put("txnType", "12");
		// 交易子类型 txnSubType M 01：消费
		requestMap.put("txnSubType", "01");
		// 产品类型 bizType M 000401：代付
		requestMap.put("bizType", "000401");
		// 接入类型 accessType M 0：商户直连接入
		requestMap.put("accessType", "0");
		// 接入方式 accessMode M 01：web
		requestMap.put("accessMode", "01");
		// 商户号 merId M
		// 商户订单号 merOrderId M 商户订单号
		requestMap.put("merOrderId", deposit.getTransNo());
		// 账号 accNo M 银行卡卡号
		requestMap.put("accNo", deposit.getAccCardNo());
		// 账号类型 accType O 账号类型：01：借记卡、02：贷记卡、03：存折、04：公司账号
		requestMap.put("accType", "01");
		// 银行卡验证信息及身份信息 customerInfo M 详见2.3 其中姓名（customerNm）为必填项,如果ppFlag为00对公，issInsName（开户银行名）为必填
		Map<String, String> customerInfo = new HashMap<String, String>();
		// 开户行省 issInsProvince
		// 开户行市 issInsCity
		// 开户银行名称 issInsName
		// 证件类型 certifTp
		// 证件号码 certify_id
		// 姓名 customerNm
		customerInfo.put("customerNm", deposit.getAccName());
		// 手机号 phoneNo
		// 短信验证码 smsCode
		// 有效期 expired
		// CVV2 cvv2
		requestMap.put("customerInfo", JSON.toJSONString(customerInfo));

		// 订单发送时间 txnTime M yyyyMMddHHmmss
		requestMap.put("txnTime", deposit.getOrderDate());
		// 交易金额 txnAmt M 单位为分
		requestMap.put("txnAmt", deposit.getPrice().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_EVEN).toString());
		// 交易币种 currency M 三位 ISO 货币代码，目前仅支持人民币 CNY
		requestMap.put("currency", "CNY");
		// 后台通知地址 backUrl M
		requestMap.put("backUrl", reqData.getNotifyUrl());
		// 支付方式 payType M T1代付：0401、T0代付：0402
		if (EnumDepositMode.S1.equals(deposit.getMode())) {
			requestMap.put("payType", "0401");
		}
		else if (EnumDepositMode.S0.equals(deposit.getMode())) {
			requestMap.put("payType", "0402");
		}

		// 银行编号 bankId O 对私选填，对公必填
		// requestMap.put("bankId", "");
		// 商品标题 subject O BASE64（验签勿编码）
		// requestMap.put("subject", deposit.getSubject());
		// 商品描述 body O BASE64（验签勿编码）
		// requestMap.put("body", deposit.getDescription());
		// 对公对私标志 ppFlag M 00：对公、01：对私
		requestMap.put("ppFlag", "01");
		// 用途 purpose O
		requestMap.put("purpose", deposit.getPurpose());
		// 请求保留域 merResv1 O
		// requestMap.put("merResv1", "");
		// 签名信息 signature M 签名信息，详见签名机制
		requestMap.put("signature", getSignVal(requestMap, routeMerchantSetting.getSignKey()));

		PasswayResponseData respData = new PasswayResponseData();
		try {
			String now = String.valueOf(System.currentTimeMillis());

			JSONObject respJson = this.postData("TransferApply", now, AiNongConstant.API.TransferApply, requestMap, routeMerchantSetting);

			respData.setExtraObject(respJson);

			// 应答码 respCode
			String respCode = respJson.getString("respCode");
			// 应答信息 respMsg
			String respMsg = respJson.getString("respMsg");

			// 1001 交易成功
			if ("1001".equals(respCode)) {
				respMsg = "代付成功";
				respData.setStatus(EnumPasswayOrderStatus.S);
			}
			else if (transferFailMap.containsKey(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
			else {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}
			respData.setRetCode(respCode);
			respData.setRetDesc(respMsg);
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumPasswayOrderStatus.F);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				respData.setStatus(EnumPasswayOrderStatus.R);
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				respData.setStatus(EnumPasswayOrderStatus.R);
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			log.error(e, e);
			respData.setStatus(EnumPasswayOrderStatus.R);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}

		return respData;
	}

	/**
	 * @param postDataString
	 * @param getDataMap
	 * @param invoice
	 * @return
	 */
	public PasswayResponseData parseTransferNotifyData(String postDataString, JSONObject getDataMap, DownMerchantDeposit deposit) {
		PasswayResponseData respData = new PasswayResponseData();
		respData.setReplyText("99999");

		Map<String, String> reqData = null;
		if (getDataMap.isEmpty()) {
			reqData = recoverLinkString2Map(postDataString);
		}
		else {
			reqData = new HashMap<String, String>();
			for (String k : getDataMap.keySet()) {
				reqData.put(k, getDataMap.getString(k));
			}
		}

		deconverData(reqData);

		String signature = reqData.get("signature");

		PasswayRouteMerchantData routeMerchantSetting = null; // PasswayRouteMerchants.get(deposit.getChannel(), deposit.getChannelAccNo());

		String signVal = getSignVal(reqData, routeMerchantSetting.getSignKey());
		// 验签成功
		if (signature.equals(signVal)) {
			// 应答码 respCode
			String respCode = reqData.get("respCode");
			// 应答信息 respMsg
			String respMsg = reqData.get("respMsg");

			// 1001 交易成功
			if ("1001".equals(respCode)) {
				respMsg = "代付成功";
				respData.setStatus(EnumPasswayOrderStatus.S);
			}
			else if (transferFailMap.containsKey(respCode)) {
				respData.setStatus(EnumPasswayOrderStatus.F);
			}
			else {
				respData.setStatus(EnumPasswayOrderStatus.R);
			}
			respData.setRetCode(respCode);
			respData.setRetDesc(respMsg);

			respData.setReplyText("success");
		}
		else {
			respData.setReplyText("验签失败");
		}

		return respData;
	}

	/**
	 * @param tagName
	 * @param tagId
	 * @param url
	 * @param reqData
	 * @param encKey
	 * @return
	 * @throws Exception
	 */
	private JSONObject postData(String tagName, String tagId, String url, Map<String, String> reqData, PasswayRouteMerchantData routeMerchantSetting) throws Exception {
		Date startTime = new Date();
		try {
			if ("TransferApply".equals(tagName)) {
				if ("dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
					System.setProperty("socksProxySet", "true");
					System.setProperty("socksProxyHost", "127.0.0.1");
					System.setProperty("socksProxyPort", "27070");
				}
			}

			String contentString = JSON.toJSONString(reqData);
			log.info("[" + getPasswayName() + tagName + "Reqe1][" + tagId + "]" + contentString);

			converData(reqData);

			String respBody = this.postForm(url, reqData);
			log.info("[" + getPasswayName() + tagName + "Resp2][" + tagId + "][" + ((int) ((new Date().getTime() - startTime.getTime()) / 1000.0)) + "]" + respBody);

			Map<String, String> respMap = recoverLinkString2Map(respBody);

			deconverData(respMap);

			String respPureString = JSON.toJSONString(respMap);
			log.info("[" + getPasswayName() + tagName + "Resp1][" + tagId + "]" + respPureString);

			String signature = respMap.remove("signature");
			if (!signature.equals(getSignVal(respMap, routeMerchantSetting.getSignKey()))) {
				throw new AbstractWooException("签名效验失败");
			}

			return JSON.parseObject(respPureString);
		} catch (IOException e) {
			log.info("[" + getPasswayName() + tagName + "Err][" + tagId + "][" + ((int) ((new Date().getTime() - startTime.getTime()) / 1000.0)) + "]" + e.getMessage());
			throw e;
		} finally {
			if ("TransferApply".equals(tagName)) {
				if ("dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
					System.clearProperty("socksProxySet");
					System.clearProperty("socksProxyHost");
					System.clearProperty("socksProxyPort");
				}
			}
		}
	}

	/**
	 * @param requestMap
	 * @param md5key
	 * @return
	 */
	private String getSignVal(Map<String, String> requestMap, String md5key) {
		List<String> keys = new ArrayList<String>(requestMap.keySet());
		Collections.sort(keys);

		StringBuffer param = new StringBuffer();
		for (String key : keys) {
			if (!"signMethod".equals(key) && !"signature".equals(key)) {
				param.append("&" + key + "=" + toEmpty(requestMap.get(key)));
			}
		}
		String signData = param.substring(1) + md5key;
		log.debug("SIGN_DATA[]" + signData);
		return Md5Utils.md5Base64(signData, "UTF8");
	}

	private static String toEmpty(String val) {
		return val == null ? "" : val.trim();
	}

	private void deconverData(Map<String, String> paramMap) {
		Charset charset = Charset.forName("UTF8");
		for (int i = 0; i < base64Keys.length; i++) {
			String key = base64Keys[i];
			String value = (String) paramMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				paramMap.put(key, new String(Base64.decodeBase64(value), charset));
			}
		}
		for (int i = 0; i < base64JsonKeys.length; i++) {
			String key = base64JsonKeys[i];
			String value = (String) paramMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				paramMap.put(key, new String(Base64.decodeBase64(value), charset));
			}
		}
	}

	private void converData(Map<String, String> paramMap) {
		Charset charset = Charset.forName("UTF8");
		for (int i = 0; i < base64Keys.length; i++) {
			String key = base64Keys[i];
			String value = (String) paramMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				paramMap.put(key, Base64.encodeBase64String(value.getBytes(charset)));
			}
		}
		for (int i = 0; i < base64JsonKeys.length; i++) {
			String key = base64JsonKeys[i];
			String value = (String) paramMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				paramMap.put(key, Base64.encodeBase64String(value.getBytes(charset)));
			}
		}
	}
}