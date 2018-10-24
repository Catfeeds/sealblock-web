package com.sealblock.sample.trans;

import com.alibaba.fastjson.JSONObject;
import com.sealblock.wallet.SealBlockApi;

/**
 * @author
 */
public class B04_QueryOrderSample {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		queryOrder();
	}

	/**
	 * @throws Exception
	 */
	public static void queryOrder() throws Exception {
		JSONObject reqData = new JSONObject();

		// 机构号
		reqData.put("mchNo", SealBlockApi.MCH_NO);

		//////////////////////////////////////
		// 平台订单号与 机构订单号 二选一

		// userNo 平台用戶标识
		reqData.put("userNo", "F66A005345CC4CBEA2D44415163684DE");
		// 机构订单号
		reqData.put("tradeNo", "");

		// 平台订单号
		reqData.put("transNo", "");

		try {
			JSONObject respData = SealBlockApi.doEncPost(SealBlockApi.Api.Transaction.OrderQuery, reqData);
			System.out.println("平台订单号： " + respData.getString("transNo"));
			System.out.println("交易状态： " + respData.getString("status"));
			System.out.println("交易描述： " + respData.getString("statusDesc"));
		}
		//
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
