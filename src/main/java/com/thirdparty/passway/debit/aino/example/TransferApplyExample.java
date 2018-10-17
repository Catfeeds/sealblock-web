package com.thirdparty.passway.debit.aino.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.thirdparty.passway._data.PasswayRequestData;
import com.thirdparty.passway._data.PasswayResponseData;
import com.thirdparty.passway.debit.aino.AiNongApi;
import com.thirdparty.passway.debit.aino.util.AiNongConstant;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.model.data.EnumDepositMode;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * @author Luke
 */
public class TransferApplyExample {
	static AiNongApi api = new AiNongApi();

	static class AA {
		String accNo;
		BigDecimal price;
		String orderNo;
		String cardName;
		String cardNo;
		EnumDepositMode mode;

		AA(String accNo, BigDecimal price, String orderNo, String cardName, String cardNo, EnumDepositMode mode) {
			this.accNo = accNo;
			this.price = price;
			this.orderNo = orderNo;
			this.cardName = cardName;
			this.cardNo = cardNo;
			this.mode = mode;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("socksProxySet", "true");
		System.setProperty("socksProxyHost", "127.0.0.1");
		System.setProperty("socksProxyPort", "27070");

		// 罗启槟  6216915501467571   民生银行
		// 6222021001048752858   赵鹏  工行 
		String cardNo = "6216915501467571".replaceAll(" ", "");
		String cardName = "罗启槟";

		List<AA> items = new ArrayList<AA>();
//		// www.bg8856.com 宝果商贸
//		items.add(new AA(AiNongConstant.MEC_BG8856, new BigDecimal("5700.0"), SDFFactory.getOrderNo(), cardName, cardNo, EnumDepositMode.S1));
//		// www.baofeihua.com 哈尔滨堡斐桦
//		items.add(new AA(AiNongConstant.MEC_BAOFEIHUA, new BigDecimal("99.00"), MD5Tool.md5("BAOFEIHUA1803231517001232"), cardName, cardNo, EnumDepositMode.S1));
//		// www.fccfx.cn 艾福斯商商城
//		items.add(new AA(AiNongConstant.MEC_FCCFX, new BigDecimal("1276"), MD5Tool.md5("FCCFX180323151700123211"), cardName, cardNo, EnumDepositMode.S1));
//		// www.haojingtech.com.cn  浩井
//		items.add(new AA(AiNongConstant.MEC_HAOJING, new BigDecimal("6246.00"), MD5Tool.md5("HAOJING1803231517001233"), cardName, cardNo, EnumDepositMode.S1));
//		//  银猫
//		items.add(new AA(AiNongConstant.MEC_YINMAO, new BigDecimal("546"), MD5Tool.md5("YINMAO1803231517001234"), cardName, cardNo, EnumDepositMode.S1));
//		//  银猫
		// items.add(new AA(AiNongConstant.MEC_YINMAO, new BigDecimal("11700.0"), SDFFactory.getOrderNo(), cardName, cardNo, EnumDepositMode.S0));
		items.add(new AA(AiNongConstant.MEC_BG8856, new BigDecimal("8339.11"), "S0A1805211909381082604", cardName, cardNo, EnumDepositMode.S1));
		
		// 初始配置渠道参数
		AiNongConstant.initPasswayMerchants();

		for (AA item : items) {
			PasswayRequestData req = new PasswayRequestData();

			DownMerchantDeposit deposit = new DownMerchantDeposit();
			deposit.setChannel(EnumDownNoCardChannel.AINO);
			deposit.setChannelAccNo(item.accNo);
			// 单位：元
			deposit.setPrice(item.price);
			deposit.setMode(item.mode);
			deposit.setTransNo(item.orderNo);
			deposit.setOrderDate(SDFFactory.DATETIME.format(new Date()));
			deposit.setAccCardNo(item.cardNo);
			deposit.setAccName(item.cardName);
			deposit.setPurpose("提现");
			req.setNotifyUrl("http://api.leelaotou.top/dgateway/directpay/notify/TESTTRADEJSSY_NOTIFY");
			// req.setReqNo(IDUtils.uuid());
			req.setDeposit(deposit);

			PasswayResponseData resp = api.transferApply(req);
			System.out.println(JsonUtils.toJson(resp));

			// {"resultMsg":"调用失败，测试环境成功!!","resultCode":"1213","errorCc":"56754","xsDm":"","serial":"AADCBDCDBJIGAGAEFDDI"}
			if (EnumPasswayOrderStatus.S.equals(resp.getStatus())) {
				System.out.println("代付成功，金额：" + item.price + ",请求流水号：" + deposit.getTransNo() + ", 下发流水号：" + resp.getPasswayOrderId());
			}
			else if (EnumPasswayOrderStatus.R.equals(resp.getStatus())) {
				// txnAmt
				System.out.println("代付待查证，金额：" + item.price + ",请求流水号：" + deposit.getTransNo() + ", 下发流水号：" + resp.getPasswayOrderId());
				System.out.println("代付结果描述，" + resp.getRetCode() + "：" + resp.getRetDesc());
			}
			else {
				System.out.println("代付失败，" + resp.getRetCode() + "：" + resp.getRetDesc());
			}
		}

		api.shutdown();
	}
}
