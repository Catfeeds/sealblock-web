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
public class SendTransactionSample {
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
		Test.initData();

		String coinType = "ETH";
		// Coin-Address-From[ETH], Sealblock-Mine-Eth-Account
		String coinRealAddrSender = "f217a383bec42c90504ee7f63b6ffe001fc265a1";
		// Coin-Address-To[ETH], Luke-UFO-Account
		String coinRealAddrReceiver = "236dE24f511CeA2118C41dbe9149e54Eb4aB62ee";

		DownNoCardInvoice inv = new DownNoCardInvoice();
		inv.setTransNo(SDFFactory.getOrderNo());
		inv.setCoin(coinType);
		inv.setPrice(new BigDecimal("1110.01"));
		inv.setUserCoinAddr(coinRealAddrSender);
		inv.setMercCoinAddr(coinRealAddrReceiver);

		SubMerchant userData = new SubMerchant();
		userData.setAddress(Test.blockAddr);
		userData.setPrikey(Test.privateKey);
		userData.setGoogleSecret(Test.secret);

		TransactionData transaction = api.sendTransaction(inv, userData);
		System.out.println(JSON.toJSONString(transaction));

		api.shutdown();
	}
}
