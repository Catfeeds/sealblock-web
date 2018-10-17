/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.core.component.sms;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.chuanglan.sms.request.SmsSendRequest;
import com.chuanglan.sms.response.SmsSendResponse;
import com.woodare.core.util.WooHttpClientPool;

/**
 * ClassName: ISmsServiceImpl
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Jul 9, 2015
 */
@Service("smsService")
public class SmsServiceImpl implements ISmsService {
	private static Logger log = Logger.getLogger(SmsServiceImpl.class);

	/**
	 * 
	 */
	public boolean sendSms(String mobiles, int code) throws Exception {
		boolean sendFlag = false;

		String msg = "您的短信验证码为：" + code + "。千万不能告诉别人此验证码";

		sendFlag = sendByContent(mobiles, msg);

		return sendFlag;
	}

	@Override
	public boolean sendByContent(String mobiles, String content) throws Exception {
		return sendBy253(mobiles, content);
	}

	public boolean sendBy253(String phone, String content) throws UnsupportedEncodingException {
		boolean sentflg = false;
		try {
			content += "【 快捷支付】";

			// 请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
			String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
			// 状态报告
			String report = "true";

			SmsSendRequest smsSingleRequest = new SmsSendRequest("N1869021", "BTjwvx9q6", content, phone, report);
			String requestJson = JSON.toJSONString(smsSingleRequest);
			String response = WooHttpClientPool.getInstance("SMS").doPostJsonData(smsSingleRequestServerUrl, requestJson);

			SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
			if ("0".equals(smsSingleResponse.getCode())) {
				sentflg = true;
			}
			else {
				log.debug("253短信至[" + phone + "]，失败原因：" + smsSingleResponse.getErrorMsg());
			}
		} catch (Exception e) {
			log.debug("253短信至" + phone + "，异常原因：" + e.getMessage());
		}
		return sentflg;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new SmsServiceImpl().sendSms("18951812474", 123456));
	}

}
