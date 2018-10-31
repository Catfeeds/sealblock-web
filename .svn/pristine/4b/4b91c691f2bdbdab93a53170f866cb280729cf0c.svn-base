package com.woodare.sealblock.example;

import com.woodare.sealblock.SealblockApi;

/**
 * @author Luke
 */
public class A4_GetBalanceSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {

		String coinType = SealblockTestData.coinType;
		String coinWalletAddress = SealblockTestData.coinETAddress;

		//
		String balance = api.getBalance(coinType, coinWalletAddress);

		System.out.println("Balance: " + balance);
		api.shutdown();
	}
}
