package com.woodare.sealblock.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.util.SealblockException;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;

/**
 * @author Luke
 */
public class Test {
	public static String secret = "123456";
	public static String blockAddr = "5e365255b26af87531de2b3b9732773a4796047d";
	public static String privateKey = "nNpN3v0KLn4478ZjZVQoDDsKuq/NhVMDjTbNfs2WiZmMQAK3Nywj+i19qufNRD9z63icKZjx9/zY1ATT/8AN45+gOewB+/BveYylJkzizMY=";
	
	static SealblockApi api = new SealblockApi();

	public static void initData() {
		List<PasswayRouteMerchantData> items = new ArrayList<PasswayRouteMerchantData>();
		PasswayRouteMerchantData item = new PasswayRouteMerchantData();
		item.setCoin("ETH");
		item.setChannel(EnumDownNoCardChannel.Ethereum);

		String extra = "";
		extra += "// 单位Wei\n";
		extra += "decimals,18\n";
		extra += "txType,0000000000000000000000000000000000000000\n";
		extra += "// 前缀数据\n";
		extra += "prefix,Ethereum Signed Message:\n";

		item.setExtra(extra);
		item.setStatus(EnumDownUserStatus.ACTIVE);
		items.add(item);
		PasswayRouteMerchants.resetAll(items);
	}

	public static void main(String[] args) throws SealblockException {

		api.rebuildPrikey("AXUp7AaOocQnamgxM6ijHBWR+J2xNMASjLJgXXgS9oNhGSzDjhT/g8vbT6zKep44YEX/t4pgqOWZQdFacS4j8skockRoqjFJwYmOeGcA4TI=", "123456", "123456");

		int scale = 8;

		BigDecimal price = new BigDecimal("3000000000.00121354").setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(price);

		// 10^18
		double unit = Math.pow(10, 18);

		BigDecimal unitDecimal = new BigDecimal(unit);

		BigDecimal unitPrice = price.multiply(unitDecimal).setScale(0, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(unitPrice);

		System.out.println(unitPrice.divide(new BigDecimal(unit), scale, BigDecimal.ROUND_HALF_EVEN));
	}
}
