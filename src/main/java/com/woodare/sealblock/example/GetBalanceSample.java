package com.woodare.sealblock.example;

import com.woodare.sealblock.SealblockApi;

/**
 * @author Luke
 */
public class GetBalanceSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {

		String coinType = "ETH";
		String coinWalletAddress = "f217a383bec42c90504ee7f63b6ffe001fc265a1";

		//
		String balance = api.getBalance(coinType, coinWalletAddress);

		System.out.println("Balance: " + balance);
		api.shutdown();
	}
}
