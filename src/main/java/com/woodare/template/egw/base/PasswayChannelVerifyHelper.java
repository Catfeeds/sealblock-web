package com.woodare.template.egw.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.template.constant.EnumError;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jersery.servicedata.downnocardinvoice.DownNoCardInvoiceServiceData;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;

/**
 * 交易通道验证器
 * 
 * @author Luke
 */
public class PasswayChannelVerifyHelper {

	public static void main(String[] args) throws MessageWooException {
		DownNoCardInvoiceServiceData invoice = new DownNoCardInvoiceServiceData();
		invoice.setPrice(new BigDecimal(1099));
		invoice.setMchNo("11");
		invoice.setCoin("eth");

		List<PasswayRouteMerchantData> items = new ArrayList<>(16);
		PasswayRouteMerchantData item = new PasswayRouteMerchantData();
		item.setChannel(EnumDownNoCardChannel.Ethereum);
		item.setCoin("eth");
		item.setMaxPerAmt("0");
		item.setMinPerAmt("0.01");
		item.setStartTime("000000");
		item.setEndTime("153000");
		item.setStatus(EnumDownUserStatus.ACTIVE);
		items.add(item);
		PasswayRouteMerchants.resetAll(items);

		PasswayRouteMerchantViewData merchantItem = getEnsureRouteValid(invoice);
		System.out.println(merchantItem.getChannel() + "/" + merchantItem.getCoin());
	}

	/**
	 * @param channel
	 * @param order
	 * @return
	 */
	public static PasswayRouteMerchantViewData getEnsureRouteValid(DownNoCardInvoiceServiceData order) throws MessageWooException {
		PasswayRouteMerchantViewData coinItem = PasswayRouteMerchants.getByCoin(order.getCoin());
		if (coinItem == null || !EnumDownUserStatus.ACTIVE.equals(coinItem.getStatus())) {
			throw new MessageWooException("暂不受理[" + order.getCoin() + "]交易", EnumError.ERR_8001);
		}
		else {
			// 判断为测试商户时, 不进行效验
			if ("100000".equals(order.getMchNo())) {
				return coinItem;
			}
			String time = SDFFactory.TIME_SHORT.format(new Date());
			// 开始时间
			if (StringUtils.isNotEmpty(coinItem.getStartTime()) && !"000000".equals(coinItem.getStartTime()) && time.compareTo(coinItem.getStartTime()) < 0) {
				throw new MessageWooException("当前时段不受理[" + order.getCoin() + "]交易", EnumError.ERR_8001);
			}
			// 结束时间
			else if (StringUtils.isNotEmpty(coinItem.getEndTime()) && !"000000".equals(coinItem.getEndTime()) && time.compareTo(coinItem.getEndTime()) >= 0) {
				throw new MessageWooException("当前时段不受理[" + order.getCoin() + "]交易", EnumError.ERR_8001);
			}
			// 验证单笔交易额是否满足
			else if (coinItem.getMaxPerAmtNum() != null && order.getPrice().compareTo(coinItem.getMaxPerAmtNum()) > 0) {
				throw new MessageWooException("[" + order.getCoin() + "]超出最大交易额[" + coinItem.getMaxPerAmt() + "]", EnumError.ERR_8001);
			}
			// 验证最小金额是否满足
			else if (coinItem.getMaxPerAmtNum() != null && order.getPrice().compareTo(coinItem.getMaxPerAmtNum()) < 0) {
				throw new MessageWooException("[" + order.getCoin() + "]交易额不能低于[" + coinItem.getMinPerAmt() + "]", EnumError.ERR_8001);
			}
		}

		return coinItem;
	}
}
