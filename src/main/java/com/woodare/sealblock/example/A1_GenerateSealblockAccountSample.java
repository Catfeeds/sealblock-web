package com.woodare.sealblock.example;

import com.alibaba.fastjson.JSON;
import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.data.SealblockAccountData;

/**
 * 0x0f7bb0aef075feb963484b4838a3bf358d491ca8
 * "approverKey": "9b0843eb53c0d3c3eeefb73cc44bc0abdadbd99d83becd22729f80558945661f"
 * ETH: 0x8cae25e5e9ce1802610f86647e14aa495ed2b017
 * 
 * @author Luke
 */
public class A1_GenerateSealblockAccountSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {
		String secret = SealblockTestData.secret;

		SealblockAccountData account = api.generateAccount(secret);
		// Account: {
		// "blockAddr": "5e365255b26af87531de2b3b9732773a4796047d",
		// "prikey": "nNpN3v0KLn4478ZjZVQoDDsKuq/NhVMDjTbNfs2WiZmMQAK3Nywj+i19qufNRD9z63icKZjx9/zY1ATT/8AN45+gOewB+/BveYylJkzizMY="
		// }

		System.out.println("Account: " + JSON.toJSONString(account));

		api.shutdown();
	}
}
