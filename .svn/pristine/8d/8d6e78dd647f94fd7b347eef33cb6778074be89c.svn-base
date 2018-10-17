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
package com.woodare.template.helper.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;

/**
 * ClassName: DownMerchantFundAccounts
 * 
 * @description
 * @author Luke
 * @Date 2018年3月8日
 */
public class DownMerchantFundAccounts {
	private static Logger log = Logger.getLogger(DownMerchantFundAccounts.class);

	private static Map<String, DownMerchantFundAccountData> _cachedMap = new HashMap<String, DownMerchantFundAccountData>();
	private static Map<String, List<DownMerchantFundAccountData>> _cachedListMap = new HashMap<String, List<DownMerchantFundAccountData>>();

	/**
	 * @param mchNo
	 * @param productType
	 * @return
	 */
	public static DownMerchantFundAccountData getByMchNoAndCoin(String mchNo, String coin) {
		return _cachedMap.get(mchNo + "_" + coin);
	}

	/**
	 * @param mchNo
	 * @return
	 */
	public static List<DownMerchantFundAccountData> getByMchNo(String mchNo) {
		return _cachedListMap.get(mchNo);
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<DownMerchantFundAccountData> items) {
		if (items != null && items.size() > 0) {
			Map<String, DownMerchantFundAccountData> cachedMap = new HashMap<String, DownMerchantFundAccountData>();
			Map<String, List<DownMerchantFundAccountData>> cachedListMap = new HashMap<String, List<DownMerchantFundAccountData>>();

			for (DownMerchantFundAccountData item : items) {
				if (StringUtils.isNotEmpty(item.getMchNo()) && item.getCoin() != null && EnumDownUserStatus.ACTIVE.equals(item.getStatus())) {
					if (!cachedListMap.containsKey(item.getMchNo())) {
						cachedListMap.put(item.getMchNo(), new ArrayList<DownMerchantFundAccountData>());
					}
					cachedListMap.get(item.getMchNo()).add(item);
					cachedMap.put(item.getMchNo() + "_" + item.getCoin(), item);
				}
			}
			_cachedMap = cachedMap;
			_cachedListMap = cachedListMap;
		}
		log.info("DownMerchantFundAccounts[][Reloaded]");
	}
}
