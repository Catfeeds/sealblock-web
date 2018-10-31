package com.woodare.sealblock.example;

import java.math.BigDecimal;
import java.util.List;

import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.sealblock.SealblockApi;
import com.woodare.sealblock.util.SealblockException;
import com.woodare.template.busi.service.InitializeDataService;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.PasswayRouteMerchant;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;

/**
 * @author Luke
 */
public class SealblockTestData {
	public static String network = "ETH";
	public static String coinType = "ETH_ET";
	public static String contractAddress = "a32c7ce801b060f9a7508bd0dd58e0e54777cf8b";

	public static String secret = "123456";
	public static String blockAddr = "5e365255b26af87531de2b3b9732773a4796047d";
	public static String privateKey = "nNpN3v0KLn4478ZjZVQoDDsKuq/NhVMDjTbNfs2WiZmMQAK3Nywj+i19qufNRD9z63icKZjx9/zY1ATT/8AN45+gOewB+/BveYylJkzizMY=";
	public static String coinETAddress = "9619d277d552ffcba22ed0f6fb5e2c7a9a813391";

	static SealblockApi api = new SealblockApi();

	public static void initData() {
		List<PasswayRouteMerchant> models = InitializeDataService.createPasswayRouteMerchants();
		PasswayRouteMerchants.resetAll(SaftyBeanUtils.cloneToList(models, PasswayRouteMerchantData.class));
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
