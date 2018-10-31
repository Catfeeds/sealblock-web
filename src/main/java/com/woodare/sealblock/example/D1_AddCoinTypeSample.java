package com.woodare.sealblock.example;

import com.woodare.sealblock.SealblockApi;

/**
 * @author Luke
 */
public class D1_AddCoinTypeSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {
		String coinType = "ETH_XT";
		String network = "ETH";
		String contractAddr = "1111111111111111111";

		boolean ret = api.addCoinType(coinType, network, contractAddr);
		System.out.println("添加币支持结果： " + ret);

		api.shutdown();
	}
}
