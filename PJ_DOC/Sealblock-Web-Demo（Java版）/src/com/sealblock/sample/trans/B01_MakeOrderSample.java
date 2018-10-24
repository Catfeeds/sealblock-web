package com.sealblock.sample.trans;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.sealblock.wallet.SealBlockApi;
import com.sealblock.wallet.utils.SDFFactory;

/**
 * 下单
 * 
 * @author
 */
public class B01_MakeOrderSample {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		makeOrder();
	}

	/**
	 * @throws Exception
	 */
	public static void makeOrder() throws Exception {
		JSONObject reqData = new JSONObject();

		// 机构号
		reqData.put("mchNo", SealBlockApi.MCH_NO);
		// userNo 平台用戶标识
		reqData.put("userNo", "F66A005345CC4CBEA2D44415163684DE");
		// coin 货币类型
		reqData.put("coin", "ETH");
		// price 交易金额
		reqData.put("price", "0.05");
		// tradeNo 机构流水号
		reqData.put("tradeNo", SDFFactory.getMemoryUniquedOrderNo());
		// subject 标题
		reqData.put("subject", "Demo测试");
		// orderDate 订单日期
		reqData.put("orderDate", SDFFactory.DATETIME.format(new Date()));
		// notifyUrl 异步通知URL
		reqData.put("notifyUrl", "http://www.baidu.com");

		try {
			JSONObject respData = SealBlockApi.doEncPost(SealBlockApi.Api.Transaction.MakeOrder, reqData);
			System.out.println("平台订单号： " + respData.getString("transNo"));
			System.out.println("*下单状态： " + respData.getString("status"));
			System.out.println("*下单描述： " + respData.getString("statusDesc"));

		}
		//
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
