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

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.persistence.data.submerchant.SearchSubMerchantData;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantDAO;

/**
 * ClassName: SubMerchantDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/10/15
 */
@Service
public class SubMerchantDAO extends AbstractPagedDAO<SubMerchant> implements ISubMerchantDAO {

	@Override
	protected Class<SubMerchant> getDomainClass() {
		return SubMerchant.class;
	}

	@Override
	@Transactional(readOnly = true)
	public IPagedList<SubMerchant> searchItems(SearchSubMerchantData searchData) {

		StringBuffer sql = new StringBuffer("from SubMerchant a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// conditions.add(new TypeCondition("deleteStatus", "a.deleteStatus = :deleteStatus", EnumDeleteStatus.VALID));

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		// change the key
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords", "(a.id like :keywords)", "%" + searchData.getKeywords() + "%"));
		}
		// TODO: add more conditions

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("createDate desc");
		}

		// Create query statements
		TypedQuery<SubMerchant> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), SubMerchant.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(readOnly = true)
	public SubMerchant findById(String id) {
		SubMerchant item = this.findOne(id);
		if (item == null || item.getDeleteStatus() != EnumDeleteStatus.VALID) {
			item = null;
		}
		return item;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SubMerchant findByUserNo(String userNo) {
		TypedQuery<SubMerchant> query = this.getEntityManager().createQuery("from SubMerchant a where a.userNo = :userNo ", SubMerchant.class);
		query.setMaxResults(1);
		query.setParameter("userNo", userNo);
		List<SubMerchant> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SubMerchant findByAddress(String address) {
		TypedQuery<SubMerchant> query = this.getEntityManager().createQuery("from SubMerchant a where a.address = :address ", SubMerchant.class);
		query.setMaxResults(1);
		query.setParameter("address", address);
		List<SubMerchant> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SubMerchant findByMchNoAndCertId(String mchNo, String userCertId) {
		TypedQuery<SubMerchant> query = this.getEntityManager().createQuery("from SubMerchant a where a.mchNo = :mchNo and a.userCertId = :userCertId ", SubMerchant.class);
		query.setMaxResults(1);
		query.setParameter("mchNo", mchNo);
		query.setParameter("userCertId", userCertId);
		List<SubMerchant> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SubMerchant findByMchNoAndMobile(String mchNo, String userPhone) {
		TypedQuery<SubMerchant> query = this.getEntityManager().createQuery("from SubMerchant a where a.mchNo = :mchNo and a.userPhone = :userPhone ", SubMerchant.class);
		query.setMaxResults(1);
		query.setParameter("mchNo", mchNo);
		query.setParameter("userPhone", userPhone);
		List<SubMerchant> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public SubMerchant findByMchNoAndMchUserNo(String mchNo, String mchUserNo) {
		TypedQuery<SubMerchant> query = this.getEntityManager().createQuery("from SubMerchant a where a.mchNo = :mchNo and a.mchUserNo = :mchUserNo ", SubMerchant.class);
		query.setMaxResults(1);
		query.setParameter("mchNo", mchNo);
		query.setParameter("mchUserNo", mchUserNo);
		List<SubMerchant> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

}
