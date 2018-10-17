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
package com.woodare.template.jpa.model;

import javax.persistence.Entity;

import com.woodare.framework.annotation.EntityDescriptor;

/**
 * ClassName: DownMerchantFundAccountHis
 * 
 * @description
 * @author Luke
 * @Date Mar 5, 2018
 */
@Entity
@EntityDescriptor(name = "下游机构账户历史", category = "content")
public class DownMerchantFundAccountHis extends DownMerchantFundAccount {
	private static final long serialVersionUID = 8980357326990752236L;

}
