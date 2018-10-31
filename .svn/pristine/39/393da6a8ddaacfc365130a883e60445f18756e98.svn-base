package com.woodare.sealblock.example;

import com.woodare.sealblock.SealblockApi;

/**
 * @author Luke
 */
public class A2_CreateWalletSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {
		// Account: {
		// "blockAddr": "1eb1734668a281c8bb9ed9a14ae4d68107fa596c",
		// "prikey": "AXUp7AaOocQnamgxM6ijHBWR+J2xNMASjLJgXXgS9oNhGSzDjhT/g8vbT6zKep44YEX/t4pgqOWZQdFacS4j8skockRoqjFJwYmOeGcA4TI="
		// }
		//
		String approverAddr = SealblockTestData.blockAddr;
		String coinType = SealblockTestData.coinType;

		String coinAddress = api.createWallet(coinType, approverAddr);
		System.out.println("Generated Coin Address: " + coinAddress);

		// 2018/10/23 10:30:46.418 [INFO ] [main] [SysProperties ] =============================
		// 2018/10/23 10:30:46.419 [INFO ] [main] [SysProperties ] Loading data: /D:/works/Sealblock/PJ_SRC/sealblock-web/target/classes/system.properties
		// 2018/10/23 10:30:47.136 [DEBUG] [main] [SealblockClient]
		// create_walletReqe[1540261846189|310ccc3c3241f329a8751a90e446e8a06c36905b]{"args":["310ccc3c3241f329a8751a90e446e8a06c36905b","ETH"],"headerMap":{"message_id":"7b388018815c48098c6594f78154aafb","response_to":"7b388018815c48098c6594f78154aafb","v":3},"methodName":"create_wallet"}
		// 2018/10/23 10:30:48.201 [DEBUG] [main] [SealblockClient]
		// create_walletResp[1540261846189|310ccc3c3241f329a8751a90e446e8a06c36905b][{"v":3,"message_id":"9ec986ee-0cd0-4533-b275-000000000007","response_to":"7b388018815c48098c6594f78154aafb"},"OK",["f217a383bec42c90504ee7f63b6ffe001fc265a1"]]
		// Generated Coin Address: f217a383bec42c90504ee7f63b6ffe001fc265a1
		api.shutdown();
	}
}
