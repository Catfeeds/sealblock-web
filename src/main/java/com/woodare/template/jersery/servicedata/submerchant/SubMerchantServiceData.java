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
package com.woodare.template.jersery.servicedata.submerchant;

import java.util.List;

import com.woodare.core.jersery.IMchServiceData;
import com.woodare.template.jpa.persistence.data.submerchant.SubMerchantData;
import com.woodare.template.jpa.persistence.data.submerchantfundaccount.SubMerchantFundAccountData;

/**
 * ClassName: SubMerchantServiceData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/10/15
 */
public class SubMerchantServiceData extends SubMerchantData implements IMchServiceData {
	private static final long serialVersionUID = -5914795942449489794L;

	// 开通货币
	private String[] enabledCoinArr;

	// 已开通货币账户明细
	private List<SubMerchantFundAccountData> enabledCoinItems;

	/**
	 * @return the enabledCoinArr
	 */
	public String[] getEnabledCoinArr() {
		return enabledCoinArr;
	}

	/**
	 * @param enabledCoinArr
	 *            the enabledCoinArr to set
	 */
	public void setEnabledCoinArr(String[] enabledCoinArr) {
		this.enabledCoinArr = enabledCoinArr;
	}

	/**
	 * @return the enabledCoinItems
	 */
	public List<SubMerchantFundAccountData> getEnabledCoinItems() {
		return enabledCoinItems;
	}

	/**
	 * @param enabledCoinItems
	 *            the enabledCoinItems to set
	 */
	public void setEnabledCoinItems(List<SubMerchantFundAccountData> enabledCoinItems) {
		this.enabledCoinItems = enabledCoinItems;
	}

}
