package com.sealblock.sample.trans;

import com.alibaba.fastjson.JSONObject;
import com.sealblock.wallet.SealBlockApi;

/**
 * 重新发送短信
 * 
 * @author
 */
public class B02_ResendOrderSample {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		resendSMSCode();
	}

	/**
	 * @throws Exception
	 */
	public static void resendSMSCode() throws Exception {
		JSONObject reqData = new JSONObject();

		// 机构号
		reqData.put("mchNo", SealBlockApi.MCH_NO);
		// 平台订单号
		reqData.put("transNo", "12345");

		try {
			JSONObject respData = SealBlockApi.doEncPost(SealBlockApi.Api.Transaction.OrderQuery, reqData);
			System.out.println("平台订单号： " + respData.getString("transNo"));
			System.out.println("*重发状态： " + respData.getString("status"));
			System.out.println("*重发描述： " + respData.getString("statusDesc"));
		}
		//
		catch (Exception e) {
			// TODO: 需注意网络超时情况，需查证确认结果
			e.printStackTrace();
		}

	}

}
