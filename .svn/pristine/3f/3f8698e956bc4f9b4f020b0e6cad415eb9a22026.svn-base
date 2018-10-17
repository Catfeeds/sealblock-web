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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.web.viewdata.downmerchant.DownMerchantViewData;

/**
 * ClassName: DownMerchants
 * 
 * @description
 * @author Luke
 * @Date 2018年3月8日
 */
public class DownMerchants {
	private static Logger log = Logger.getLogger(DownMerchants.class);
	private static Map<String, DownMerchantViewData> _cachedMap = new HashMap<String, DownMerchantViewData>();

	public static DownMerchantViewData getByMchNo(String code) {
		if (code == null) {
			return null;
		}
		return _cachedMap.get(code);
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<DownMerchantData> items) {
		if (items != null && items.size() > 0) {
			Map<String, DownMerchantViewData> cachedMap = new HashMap<String, DownMerchantViewData>();
			for (DownMerchantData item : items) {
				DownMerchantViewData viewData = SaftyBeanUtils.cloneTo(item, DownMerchantViewData.class);
				if (StringUtils.isNotEmpty(viewData.getSupportCoin())) {
					viewData.setSupportCoins(Arrays.asList(viewData.getSupportCoin().split(",")));
				}
				cachedMap.put(item.getMchNo(), viewData);
			}
			_cachedMap = cachedMap;
		}
		log.info("DownMerchants[][Reloaded]");
	}
}
