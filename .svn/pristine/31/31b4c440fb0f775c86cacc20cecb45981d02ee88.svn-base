package com.woodare.sealblock.example;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.woodare.core.util.SDFFactory;
import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.data.TransactionData;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.SubMerchant;

/**
 * // "approverAddress": "0x0f7bb0aef075feb963484b4838a3bf358d491ca8",
 * // "approverKey": "9b0843eb53c0d3c3eeefb73cc44bc0abdadbd99d83becd22729f80558945661f"
 * 
 * @author Luke
 */
public class B1_SendTransactionSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * coinConfigJSON.put("ETH_txType","0000000000000000000000000000000000000000");
	 * coinConfigJSON.put("ETH_decimal",18);
	 * coinConfigJSON.put("BTC_txType","BTC-BTC-BTC-BTC-BTC-BTC-BTC-BTC-BTC-BTC-");
	 * coinConfigJSON.put("BTC_decimal",8);
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {
		SealblockTestData.initData();

		String coinType = SealblockTestData.coinType;
		// Coin-Address-From[ETH], Sealblock-Mine-Eth-Account
		String coinRealAddrSender = SealblockTestData.coinETAddress;
		// Coin-Address-To[ETH], Luke-UFO-Account
		String coinRealAddrReceiver = "0xB3822a42212662C621CD8Ce442525ADE337431a9";

		DownNoCardInvoice inv = new DownNoCardInvoice();
		inv.setTransNo(SDFFactory.getOrderNo());
		inv.setCoin(coinType);
		inv.setPrice(new BigDecimal("0.42"));
		inv.setUserCoinAddr(coinRealAddrSender);
		inv.setMercCoinAddr(coinRealAddrReceiver);

		SubMerchant userData = new SubMerchant();
		userData.setAddress(SealblockTestData.blockAddr);
		userData.setPrikey(SealblockTestData.privateKey);
		userData.setGoogleSecret(SealblockTestData.secret);

		TransactionData transaction = api.sendTransaction(inv, userData);
		System.out.println(JSON.toJSONString(transaction));

		api.shutdown();
	}
}
