package com.woodare.sealblock.example;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.woodare.core.util.SDFFactory;
import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.data.TransactionData;
import com.woodare.sealblock.util.SealAesUtils;
import com.woodare.sealblock.util.SealMD5Tool;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.SubMerchant;

/**
 * // "approverAddress": "0x0f7bb0aef075feb963484b4838a3bf358d491ca8",
 * // "approverKey": "9b0843eb53c0d3c3eeefb73cc44bc0abdadbd99d83becd22729f80558945661f"
 * 
 * @author Luke
 */
public class SendTransactionSample2 {

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
		String k = "";
		String s = "";
		k = "Kzk0bWlQMm4nuyTL4bc7NoQITTAYv2DXV+6Sn+579iDRG3TP5wQCi3bzLMvJafiQQ4MUbhUNwzqw4cCZVb585GkMjiNJEfIjACqGOvFN8dc=";
		s = "VBPSIH64X7YYDU7A";
		try {
			System.out.println("==============================");
			System.out.println(SealAesUtils.decrypt(k, SealMD5Tool.md5(s + "_LOVE$_$FISH_")));
		}
		catch(Exception e) {}
		
		try {
			System.out.println("==============================");
			System.out.println(SealAesUtils.decrypt(k, SealMD5Tool.md5(s + "")));
		}
		catch(Exception e) {}
		
		try {
			System.out.println("==============================");
			System.out.println(SealAesUtils.decrypt(k, SealMD5Tool.md5(s + "@LOVE$_$FISH@")));
		}
		catch(Exception e) {}
		
		try {
			System.out.println("==============================");
			System.out.println(SealAesUtils.decrypt(k, SealMD5Tool.md5(s + null)));
		}
		catch(Exception e) {}
		
	}
}
