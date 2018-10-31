package com.sealblock.wallet;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealblock.wallet.model.RequestData;
import com.sealblock.wallet.model.ResponseData;
import com.sealblock.wallet.utils.AesUtils;
import com.sealblock.wallet.utils.HttpUtils;
import com.sealblock.wallet.utils.MD5Util;

/**
 * @author
 */
public class SealBlockApi {
	private static Log log = LogFactory.getLog(SealBlockApi.class);

	// 服务器连接地址
	public static String URL = "http://localhost:8080/sealblock-web/ws/trans";
	// 机构号
	public static String MCH_NO = "T0001";
	// 加密密钥
	public static String ENC_KEY = "1234567812345678";
	// 签名密钥
	public static String SIGN_KEY = "1234";

	public interface Api {
		// 用户相关接口
		interface Merchant {
			// 创建用户信息
			String Create = "/subMerchant/createUser";
			// 更新用户信息
			String Update = "/subMerchant/updateUser";
			// 用户开通币
			String OpenCoin = "/subMerchant/openUserCoin";
			// 用户余额查询
			String BalQuery = "/subMerchant/userBalQuery";
		}

		// 交易相关
		interface Transaction {
			// 下单&发短信
			String MakeOrder = "/nocard/Transfer";
			// 短信确认
			String ConfirmOrder = "/nocard/confirmOrder";
			// 查询状态
			String OrderQuery = "/nocard/orderQuery";
			// 机构余额查询
			String AccBalQuery = "/nocard/accBalQuery";
			// 交易转账
			String BalanceTransfer = "/balanceTransfer/Transfer";
		}
	}

	/**
	 * @param contextUri
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doEncPost(String contextUri, JSONObject reqData) throws Exception {
		JSONObject respData = null;
		try {

			String payload = JSON.toJSONString(reqData);
			log.debug("业务报文【明文】=>" + payload);

			String encpayload = AesUtils.encrypt(payload, ENC_KEY);
			log.debug("业务报文【密文】=>" + encpayload);

			String signval = MD5Util.MD5Encode(encpayload + SIGN_KEY, "UTF-8").toUpperCase();
			log.debug("签名数值=>" + signval);

			RequestData req = new RequestData();
			req.setMchNo(MCH_NO);
			req.setPayload(encpayload);
			req.setSign(signval);

			String requestString = JSON.toJSONString(req);

			String responseString = HttpUtils.post(URL + contextUri, requestString);
			log.debug("返回报文=>" + responseString);

			ResponseData res = JSON.parseObject(responseString, ResponseData.class);

			// 验证返回结果
			if ("Successful".equals(res.getState())) {
				log.debug(String.format("[%s]%s", res.getCode(), res.getMessage()));

				String sign = MD5Util.MD5Encode(res.getPayload() + SIGN_KEY, "UTF-8").toUpperCase();
				log.debug("验签结果:" + sign.equals(res.getSign()));

				String payloadRespString = AesUtils.decrypt(res.getPayload(), ENC_KEY);
				log.debug("业务报文【明文】=>" + payloadRespString);

				respData = JSON.parseObject(payloadRespString);
				// log.debug("平台订单号:" + rspObj.getString("transNo"));
				// log.debug("支付地址:" + rspObj.getString("payUrl"));
				// log.debug("订单状态:" + rspObj.getString("statusDesc"));
			}
			else {
				log.error("业务异常：　" + String.format("[%s]%s", res.getCode(), res.getMessage()));
				throw new SealblockException(res.getCode(), res.getMessage());
			}
		} catch (SealblockException e) {
			throw e;
		} catch (InterruptedIOException e) {
			String kindMessage = "通讯处理异常，状态未知，可当作失败";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易，可当作失败";
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易，可当作失败";
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，下单状态未知，可当作失败";
			}
			log.debug("通讯异常原因=>" + kindMessage);

			throw new SealblockException(10000, kindMessage);
		} catch (Exception e) {
			log.error("通讯异常原因=>" + e.getMessage(), e);

			throw new SealblockException(10001, "未知异常原因");
		}

		return respData;
	}
}