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
package com.woodare.template.web.viewdata.downnocardinvoice;

import com.woodare.framework.data.ISearchData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.SearchDownNoCardInvoiceData;

/**
 * ClassName: SearchDownNoCardInvoiceViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/01/09
 */
public class SearchDownNoCardInvoiceViewData extends SearchDownNoCardInvoiceData implements ISearchData {
	private static final long serialVersionUID = 4644858587558158916L;

	@Override
	public String getSearchFields() {
		return "keywords";
	}


}
