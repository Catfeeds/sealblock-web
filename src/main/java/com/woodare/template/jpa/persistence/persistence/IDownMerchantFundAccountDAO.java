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

import java.math.BigDecimal;
import java.util.List;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.SearchDownMerchantFundAccountData;

/**
 * ClassName: IDownMerchantFundAccountDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/05
 */
public interface IDownMerchantFundAccountDAO extends ISimpleDAO<DownMerchantFundAccount> {

	IPagedList<DownMerchantFundAccount> searchItems(SearchDownMerchantFundAccountData searchData);

	/**
	 * 根据机构号和币查询账户
	 * 
	 * @param mchNo
	 * @param coin
	 * @return
	 */
	DownMerchantFundAccount findByMchNoAndCoin(String mchNo, String coin);

	/**
	 * 根据机构号查询
	 * 
	 * @param mchNo
	 * @return
	 */
	List<DownMerchantFundAccount> searchByMchNo(String mchNo);

	/**
	 * 更新账户余额
	 * 
	 * @param diffModel
	 * @param creditRatio
	 * @return
	 */
	int changeBalance(DownMerchantFundAccountData diffModel, long creditRatio);

	/**
	 * 根据主键更新数据
	 * 
	 * @param item
	 * @param id
	 * @return
	 */
	int updateById(DownMerchantFundAccount item, String id);

	/**
	 * 遍历存在资金变动的数据，备份至历史数据表内
	 * 
	 * @param settleDate
	 * @return
	 */
	int addToHistory(String settleDate);

	/**
	 * 隔日结算统计
	 * 
	 * @return
	 */
	int makeSettle(String settleDate, String settleBalanceNo);

	/**
	 * @param frozenAmt
	 * @param mchNo
	 * @return
	 */
	int frozenBalance(BigDecimal frozenAmt, String mchNo);
}
