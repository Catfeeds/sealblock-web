package com.woodare.template.helper.cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.woodare.core.util.AesUtils;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;

/**
 * 通道商户缓存表
 * 
 * @author Luke
 */
public class PasswayRouteMerchants {
	private static Logger log = Logger.getLogger(PasswayRouteMerchants.class);

	private static Map<String, PasswayRouteMerchantViewData> _cachedMap = new HashMap<String, PasswayRouteMerchantViewData>();
	private static Map<EnumDownNoCardChannel, List<PasswayRouteMerchantViewData>> _channelCoins = new HashMap<EnumDownNoCardChannel, List<PasswayRouteMerchantViewData>>();
	private static List<PasswayRouteMerchantViewData> _allCoins = new ArrayList<PasswayRouteMerchantViewData>();

	public static void main(String[] args) throws Exception {
		String c = AesUtils.encrypt("JENB7ZXKeJ7DP7z7xjdgxY5zNc6X5Hsg", EnumDownNoCardChannel.AINO.getAesKey());
		System.out.println(c);
		System.out.println(AesUtils.decrypt(c, EnumDownNoCardChannel.AINO.getAesKey()));
	}

	/**
	 * @param chanal
	 * @return
	 */
	public static PasswayRouteMerchantViewData getByCoin(String coin) {
		return _cachedMap.get(coin);
	}

	/**
	 * @param routeMerchantNo
	 * @return
	 */
	public static List<PasswayRouteMerchantViewData> getByChannel(EnumDownNoCardChannel channel) {
		return _channelCoins.get(channel);
	}

	/**
	 * @param routeMerchantNo
	 * @return
	 */
	public static List<PasswayRouteMerchantViewData> getAll() {
		return _allCoins;
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<PasswayRouteMerchantData> items) {
		if (items != null && items.size() > 0) {
			Map<String, PasswayRouteMerchantViewData> cachedMap = new HashMap<String, PasswayRouteMerchantViewData>();
			List<PasswayRouteMerchantViewData> allCoins = new ArrayList<PasswayRouteMerchantViewData>();
			Map<EnumDownNoCardChannel, List<PasswayRouteMerchantViewData>> channelMerchants = new HashMap<EnumDownNoCardChannel, List<PasswayRouteMerchantViewData>>();
			for (PasswayRouteMerchantData item : items) {
				if (item != null && item.getChannel() != null && StringUtils.isNotEmpty(item.getCoin()) && EnumDownUserStatus.ACTIVE.equals(item.getStatus())) {
					PasswayRouteMerchantViewData cloneItem = SaftyBeanUtils.cloneTo(item, PasswayRouteMerchantViewData.class);
					String aesKey = item.getChannel().getAesKey();

					if (StringUtils.isNotEmpty(aesKey)) {
						try {
							if (StringUtils.isNotEmpty(cloneItem.getEncKey())) {
								cloneItem.setEncKey(AesUtils.decrypt(cloneItem.getEncKey(), aesKey));
							}
							if (StringUtils.isNotEmpty(cloneItem.getSignKey())) {
								cloneItem.setSignKey(AesUtils.decrypt(cloneItem.getSignKey(), aesKey));
							}

							if (cloneItem.getMinPerAmt() != null && !"0".equals(cloneItem.getMinPerAmt())) {
								cloneItem.setMinPerAmtNum(new BigDecimal(cloneItem.getMinPerAmt()));
							}
							if (cloneItem.getMaxPerAmt() != null && !"0".equals(cloneItem.getMaxPerAmt())) {
								cloneItem.setMaxPerAmtNum(new BigDecimal(cloneItem.getMaxPerAmt()));
							}
							if (cloneItem.getMaxTotAmt() != null && !"0".equals(cloneItem.getMaxTotAmt())) {
								cloneItem.setMaxTotAmtNum(new BigDecimal(cloneItem.getMaxTotAmt()));
							}

							// String feeText = cloneItem.getFeeText();
							// if (StringUtils.isNotEmpty(feeText)) {
							// String[] fees = feeText.split("\n", -1);
							// for (String fee : fees) {
							// String[] values = fee.split(",", -1);
							// if (values != null && values.length > 5 && EnumProductType.getByMode(values[0]) != null) {
							// cloneItem.getPayFees().put(values[0], new PasswayRouteMerchantFeeData(values));
							// }
							// }
							// }
						} catch (Exception e) {
							log.error(String.format("LoadPasswayMerchant[%s][%s]%s", cloneItem.getChannel(), cloneItem.getCoin(), e.getMessage()), e);
						}
					}
					if (!channelMerchants.containsKey(cloneItem.getChannel())) {
						channelMerchants.put(cloneItem.getChannel(), new ArrayList<PasswayRouteMerchantViewData>());
					}
					channelMerchants.get(cloneItem.getChannel()).add(cloneItem);
					cachedMap.put(cloneItem.getCoin(), cloneItem);

					allCoins.add(cloneItem);
				}
			}
			_allCoins = allCoins;
			_cachedMap = cachedMap;
			_channelCoins = channelMerchants;
		}
		log.info("PasswayRouteMerchants[][Reloaded]");
	}
}
