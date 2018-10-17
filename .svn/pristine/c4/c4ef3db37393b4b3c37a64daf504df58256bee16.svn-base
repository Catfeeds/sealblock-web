package com.thirdparty.passway.debit.aino.util;

import java.util.ArrayList;
import java.util.List;

import com.woodare.core.util.AesUtils;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;

/**
 * 爱农渠道
 * 
 * @author Luke
 */
public class AiNongConstant {
	// 交易生产地址
	public static String HOST_IP = "http://gpay.chinagpay.com";
	// 清算生产地址
	public static String HOST_IP_SETTLE = "http://remit.chinagpay.com";

	// www.bg8856.com 宝果商贸
	public static String MEC_BG8856 = "929070095023432";

	// www.baofeihua.com 哈尔滨堡斐桦
	public static String MEC_BAOFEIHUA = "929010095023431";

	// www.fccfx.cn 艾福斯商商城
	public static String MEC_FCCFX = "929010095023247";

	// www.haojingtech.com.cn  浩井
	public static String MEC_HAOJING = "929000095023561";

	//  银猫
	public static String MEC_YINMAO = "929080095023532";

	/**
	 * @author Luke
	 */
	public interface API {
		// 下单请求
		String MAKE_ORDER = HOST_IP + "/bas/FrontTrans";

		// 下单状态查询
		String ORDER_QUERY = HOST_IP + "/bas/BgTrans";

		// 余额查询
		String ACC_BAL_QUERY = HOST_IP + "/bas/BgTrans";

		// 代付请求
		String TransferApply = HOST_IP + "/bas/BgTrans";

		// 代付结果查询
		String TransferQuery = HOST_IP + "/bas/BgTrans";
	}

	/**
	 * 
	 */
	public static void initPasswayMerchants() {
		try {
			List<PasswayRouteMerchantData> items = new ArrayList<PasswayRouteMerchantData>();

			// 艾福斯商商城
			PasswayRouteMerchantData param = new PasswayRouteMerchantData();
			param.setChannel(EnumDownNoCardChannel.AINO);
			param.setSignKey(AesUtils.encrypt("JENB7ZXKeJ7DP7z7xjdgxY5zNc6X5Hsg", EnumDownNoCardChannel.AINO.getAesKey()));
			param.setExtra("http://www.fccfx.cn");
			param.setStatus(EnumDownUserStatus.ACTIVE);
			items.add(param);

			PasswayRouteMerchants.resetAll(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
