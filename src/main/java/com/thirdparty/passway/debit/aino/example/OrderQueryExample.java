package com.thirdparty.passway.debit.aino.example;

import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.thirdparty.passway._data.PasswayRequestData;
import com.thirdparty.passway._data.PasswayResponseData;
import com.thirdparty.passway.debit.aino.AiNongApi;
import com.thirdparty.passway.debit.aino.util.AiNongConstant;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * 
 * @author Luke
 *
 */
public class OrderQueryExample {
	static AiNongApi api = new AiNongApi();

    public static void main(String[] args) throws Exception {
		// 初始配置渠道参数
		AiNongConstant.initPasswayMerchants();
		
		
		PasswayRequestData req = new PasswayRequestData();
		
		DownNoCardInvoice invoice = new DownNoCardInvoice();
		invoice.setChannel(EnumDownNoCardChannel.AINO);
		invoice.setTransNo("D1802231749212395100");
		req.setInvoice(invoice);
		
		PasswayResponseData resp = api.orderQuery(req);
		System.out.println(JsonUtils.toJson(resp));

		// {"resultMsg":"调用失败，测试环境成功!!","resultCode":"1213","errorCc":"56754","xsDm":"","serial":"AADCBDCDBJIGAGAEFDDI"}
		if (EnumPasswayOrderStatus.S.equals(resp.getStatus())) {
			System.out.println("交易成功，请求流水号：" + invoice.getTransNo() + ", 下发流水号：" + resp.getPasswayOrderId());
		}
		else if (EnumPasswayOrderStatus.R.equals(resp.getStatus())) {
			System.out.println("交易待查证，" + resp.getRetCode() + "：" + resp.getRetDesc());
		}
		else {
			System.out.println("交易失败，" + resp.getRetCode() + "：" + resp.getRetDesc());
		}
		
		api.shutdown();
	}
}
