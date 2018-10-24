package com.woodare.sealblock.example;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.data.TransactionData;

/**
 * @author Luke
 */
public class GetTransactionHistorySample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {

		String coinType = "ETH";
		String coinWalletAddress = "05f22e707ab9cc5ff4cd6a2cfee92bcb7b3744af";

		//
		List<TransactionData> transactions = api.getTransactionHistory(coinType, coinWalletAddress);// .getTransaction(coinType, transactionId);

		System.out.println("transaction: " + JSON.toJSONString(transactions));
		api.shutdown();
	}
}
