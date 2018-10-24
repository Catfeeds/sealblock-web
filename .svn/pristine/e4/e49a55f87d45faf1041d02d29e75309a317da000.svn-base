package com.woodare.template.helper.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.jpa.model.SubMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.submerchantfundaccount.SubMerchantFundAccountData;

/**
 * 平台地址完整数据
 * 监控(目前只考虑单台主机情况)**
 * 
 * @author Luke
 */
public class CoinAddrs {
	private static Map<String, List<String>> coinFundAddrsMap = new ConcurrentHashMap<String, List<String>>();
	private static List<SubMerchantFundAccountData> fundList = new ArrayList<SubMerchantFundAccountData>();

	/**
	 * 确认交易地址是否在平台登记
	 * 
	 * @param coin
	 * @param from
	 * @param to
	 * @return 0 未匹配、1充值、2消费
	 */
	public static int acceptTrans(String coin, String from, String to) {
		int ret = 0;

		List<String> addrs = coinFundAddrsMap.get(coin);
		if (addrs.contains(from)) {
			ret = 2;
		}
		else if (addrs.contains(to)) {
			ret = 1;
		}

		return ret;
	}

	/**
	 * 获取平台账户
	 * 
	 * @param coin
	 * @param from
	 * @param to
	 * @return
	 */
	public static List<SubMerchantFundAccountData> getAllFunds() {
		return fundList;
	}

	/**
	 * 确认交易地址是否在平台登记
	 * 
	 * @param coin
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean acceptAddress(String coin, String address) {
		List<String> addrs = coinFundAddrsMap.get(coin);
		return addrs != null ? addrs.contains(address) : false;
	}

	/**
	 * 重置所有账户数据
	 * 
	 * @param fundAccounts
	 */
	public static synchronized void resetAll(List<SubMerchantFundAccount> fundAccounts) {

		List<SubMerchantFundAccountData> _fundList = new ArrayList<SubMerchantFundAccountData>();

		Map<String, List<String>> _coinFundAddrsMap = new ConcurrentHashMap<String, List<String>>();
		for (SubMerchantFundAccount fundAccount : fundAccounts) {
			if (!_coinFundAddrsMap.containsKey(fundAccount.getCoin())) {
				_coinFundAddrsMap.put(fundAccount.getCoin(), new ArrayList<String>());
			}
			_coinFundAddrsMap.get(fundAccount.getCoin()).add(fundAccount.getAddress());

			_fundList.add(SaftyBeanUtils.cloneTo(fundAccount, SubMerchantFundAccountData.class));
		}
		coinFundAddrsMap = _coinFundAddrsMap;
		fundList = _fundList;
	}

	/**
	 * 添加新数据
	 * 
	 * @param fundAccounts
	 */
	public static synchronized void add(SubMerchantFundAccount fundAccount) {
		if (!coinFundAddrsMap.containsKey(fundAccount.getCoin())) {
			coinFundAddrsMap.put(fundAccount.getCoin(), new ArrayList<String>());
		}
		coinFundAddrsMap.get(fundAccount.getCoin()).add(fundAccount.getAddress());
		fundList.add(SaftyBeanUtils.cloneTo(fundAccount, SubMerchantFundAccountData.class));
	}

}
