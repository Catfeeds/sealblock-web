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
public class MakeOrderExample {
	static AiNongApi api = new AiNongApi();

	public static void main(String[] args) throws Exception {
		// 初始配置渠道参数
		AiNongConstant.initPasswayMerchants();

		TestSampleData sampleData = TestSampleData.testCard(TestSampleData.TEST_CARD_CHOSEN_INDEX);

		PasswayRequestData req = new PasswayRequestData();

		DownNoCardInvoice invoice = new DownNoCardInvoice();
		invoice.setChannel(EnumDownNoCardChannel.AINO);
		invoice.setPrice(new BigDecimal(0.1));
		invoice.setTransNo(SDFFactory.getOrderNo());
		invoice.setOrderDate(SDFFactory.DATETIME.format(new Date()));
		invoice.setSubject("测试");
		// 银联在线
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