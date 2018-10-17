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
package com.woodare.template.jpa.persistence.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.template.jpa.model.DownNoCardInvoiceTransfer;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceTransferDAO;
import com.woodare.template.jpa.persistence.data.downnocardinvoicetransfer.SearchDownNoCardInvoiceTransferData;

/**
 * 
 * ClassName: DownNoCardInvoiceTransferDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2017/12/21
 * 
 */
@Service
public class DownNoCardInvoiceTransferDAO extends AbstractPagedDAO<DownNoCardInvoiceTransfer> implements IDownNoCardInvoiceTransferDAO {

	@Override
	protected Class<DownNoCardInvoiceTransfer> getDomainClass() {
		return DownNoCardInvoiceTransfer.class;
	}
	
	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<DownNoCardInvoiceTransfer> searchItems(SearchDownNoCardInvoiceTransferData searchData) {
		
		StringBuffer sql = new StringBuffer("from DownNoCardInvoiceTransfer a");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		conditions.add(new TypeCondition("deleteStatus", "a.deleteStatus = :deleteStatus", EnumDeleteStatus.VALID));

		// Add filter conditions
		if(StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if(searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		// change the key
		if(StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords", "(a.id like :keywords)", "%" + searchData.getKeywords() + "%"));
		}
		// TODO: add more conditions
		
		// Append conditions
		if(conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		
		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("createDate desc");
		}
		
		// Create query statements
		TypedQuery<DownNoCardInvoiceTransfer> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), DownNoCardInvoiceTransfer.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);
		
		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}
	
	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownNoCardInvoiceTransfer findById(String id) {
		DownNoCardInvoiceTransfer item = this.findOne(id);
		if (item == null || item.getDeleteStatus() != EnumDeleteStatus.VALID) {
			item = null;
		}
		return item;
	}
}


