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
package com.woodare.template.jpa.persistence.persistence;

import java.util.Date;
import java.util.List;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositSumData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositUpSumData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.SearchDownMerchantDepositData;

/**
 * 
 * ClassName: IDownMerchantDepositDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/03
 *
 */
public interface IDownMerchantDepositDAO extends ISimpleDAO<DownMerchantDeposit> {

	/**
	 * 
	 * @param searchData
	 * @return
	 */
	IPagedList<DownMerchantDeposit> searchItems(SearchDownMerchantDepositData searchData);

	/**
	 * 
	 * @param values
	 * @param cons
	 * @return
	 */
	int updateSelectiveByCons(DownMerchantDepositData values, DownMerchantDepositData cons);
	
	DownMerchantDeposit findByTradeNoAndMchNo(String tradeNo, String mchNo);

	DownMerchantDeposit findByTransNo(String transNo);

	List<DownMerchantDeposit> findNeedCheckByStatusChgDate(Date start, Date end);
	
	List<DownMerchantDepositSumData> sumDeposit(SearchDownMerchantDepositData searchData);

	List<DownMerchantDepositUpSumData> upSumDeposit(SearchDownMerchantDepositData searchData);
}

