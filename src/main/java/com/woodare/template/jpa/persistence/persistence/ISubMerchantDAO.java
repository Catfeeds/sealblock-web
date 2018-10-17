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

import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.persistence.data.submerchant.SearchSubMerchantData;

/**
 * ClassName: ISubMerchantDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/10/15
 */
public interface ISubMerchantDAO extends ISimpleDAO<SubMerchant> {

	IPagedList<SubMerchant> searchItems(SearchSubMerchantData searchData);

	SubMerchant findById(String id);

	/**
	 * @param userNo
	 * @return
	 */
	SubMerchant findByUserNo(String userNo);

	/**
	 * @param address
	 * @return
	 */
	SubMerchant findByAddress(String address);

	/**
	 * @param mchNo
	 * @param certId
	 * @return
	 */
	SubMerchant findByMchNoAndCertId(String mchNo, String certId);

	/**
	 * @param mchNo
	 * @param mobile
	 * @return
	 */
	SubMerchant findByMchNoAndMobile(String mchNo, String mobile);

	/**
	 * @param mchNo
	 * @param mchUserNo
	 * @return
	 */
	SubMerchant findByMchNoAndMchUserNo(String mchNo, String mchUserNo);
}
