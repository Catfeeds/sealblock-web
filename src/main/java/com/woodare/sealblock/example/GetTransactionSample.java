package com.woodare.sealblock.example;

import com.alibaba.fastjson.JSON;
import com.woodare.framework.utils.CommonUtils;
import com.woodare.sealblock.SealblockApi;
import com.woodare.template.jpa.model.DownNoCardInvoice;

/**
 * @author Luke
 */
public class GetTransactionSample {
	static SealblockApi api = new SealblockApi();

	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {

		String coinType = "ETH";
		String transactionId = "0x49905d13ea0970b4c88dfd816a5420a4e9bee805c5b23e5d2ebb15f6929eb8d3";

		DownNoCardInvoice inv = new DownNoCardInvoice();
		inv.setCoin(coinType);
		inv.setTransNo(CommonUtils.uuid());
		inv.setUpTransNo(transactionId);
		//
		Object transaction = api.getTransaction(inv);// .getTransaction(coinType, transactionId);

		// {"fromAddr":"0x0084fedf205b1665Db9a6Fa856D7108849dfa012","toAddr":"0xf33ee4888bC5D2857737622277C272E85fF0e16A","amount":"50000000000000000","transaction_id":"0xf9a7609aa5c45af40ae5d401363c4aa3f27aba75b5babacc6a42a9b4d52d77d0","status":"confirmed","confirmation":"","raw_transaction":""}

		System.out.println("transaction: " + JSON.toJSONString(transaction));
		api.shutdown();
	}
}
