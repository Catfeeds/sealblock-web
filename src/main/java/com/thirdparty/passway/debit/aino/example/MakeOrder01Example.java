package com.thirdparty.passway.debit.aino.example;

import java.math.BigDecimal;
import java.util.Date;

import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.thirdparty.passway._data.PasswayRequestData;
import com.thirdparty.passway._data.PasswayResponseData;
import com.thirdparty.passway.debit.aino.AiNongApi;
import com.thirdparty.passway.debit.aino.util.AiNongConstant;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * @author Luke
 */
public class MakeOrder01Example {
	static AiNongApi api = new AiNongApi();

	public static void main(String[] args) throws Exception {
		// 初始配置渠道参数
		AiNongConstant.initPasswayMerchants();

		TestSampleData sampleData = TestSampleData.testCard(TestSampleData.TEST_CARD_CHOSEN_INDEX);

		PasswayRequestData req = new PasswayRequestData();

		DownNoCardInvoice invoice = new DownNoCardInvoice();
		invoice.setChannel(EnumDownNoCardChannel.AINO);
		invoice.setPrice(new BigDecimal(10.5));
		invoice.setTransNo(SDFFactory.getOrderNo());
		invoice.setOrderDate(SDFFactory.DATETIME.format(new Date()));
		invoice.setSubject("测试");

		// 选择银行
		// 邮储银行 01000000
		// 徽商银行 04403600
		// 工商银行 01020000
		// 农业银行 01030000
		// 中国银行 01040000
		// 建设银行 01050000
		// 交通银行 03010000
		// 中信银行 03020000
		// 光大银行 03030000
		// 华夏银行 03040000
		// 民生银行 03050000
		// 广发银行 03060000
		// 深发银行 03070000
		// 招商银行 03080000
		// invoice.setAccTel(sampleData.mobile);
		// invoice.setAccIdCard(sampleData.certId);
		// invoice.setAccName(sampleData.cardName);

		req.setFrontUrl("http://www.woodare.com/QuickPayWeb/directpay/result/f225293f5b2942a1beaae62308217314");
		req.setNotifyUrl("http://www.woodare.com/QuickPayWeb/directpay/notify/TESTTRADEJSSY_NOTIFY");
		// req.setReqNo(IDUtils.uuid());
		req.setInvoice(invoice);

		PasswayResponseData resp = api.makeOrder(req);
		System.out.println(JsonUtils.toJson(resp));

		// {"resultMsg":"调用失败，测试环境成功!!","resultCode":"1213","errorCc":"56754","xsDm":"","serial":"AADCBDCDBJIGAGAEFDDI"}
		if (EnumPasswayOrderStatus.S.equals(resp.getStatus())) {
			System.out.println("下单成功，请求流水号：" + invoice.getTransNo() + ", 下发流水号：" + resp.getPasswayOrderId());
			System.out.println("支付地址： " + resp.getExtra2());
		}
		else {
			System.out.println("下单失败，" + resp.getRetCode() + "：" + resp.getRetDesc());
		}

		api.shutdown();
	}
}
