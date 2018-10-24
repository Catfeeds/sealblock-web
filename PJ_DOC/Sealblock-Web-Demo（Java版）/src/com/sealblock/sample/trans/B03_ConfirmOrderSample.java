package com.sealblock.sample.trans;

import com.alibaba.fastjson.JSONObject;
import com.sealblock.wallet.SealBlockApi;

/**
 * 确认完成交易
 * 
 * @author
 */
public class B03_ConfirmOrderSample {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		confirmOrder();
	}

	/**
	 * @throws Exception
	 */
	public static void confirmOrder() throws Exception {
		JSONObject reqData = new JSONObject();

		// 机构号
		reqData.put("mchNo", SealBlockApi.MCH_NO);
		// 平台订单号
		reqData.put("transNo", "12345");
		// 短信验证码
		reqData.put("smsCode", "123456");

		try {
			JSONObject respData = SealBlockApi.doEncPost(SealBlockApi.Api.Transaction.OrderQuery, reqData);
			System.out.println("平台订单号： " + respData.getString("transNo"));
			System.out.println("交易状态： " + respData.getString("status"));
			System.out.println("状态描述： " + respData.getString("statusDesc"));
		}
		//
		catch (Exception e) {
			// TODO: 需注意网络超时情况，需查证确认结果
			e.printStackTrace();
		}

	}

}
