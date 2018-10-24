package com.woodare.sealblock.example;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.data.WalletData;

/**
 * @author Luke
 */
public class ListWalletSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {
		String approverAddr = Test.blockAddr;
		List<WalletData> walletAddress = api.listWallet(approverAddr);
		System.out.println(approverAddr + ":\n" + JSON.toJSONString(walletAddress));
		api.shutdown();
	}
}
