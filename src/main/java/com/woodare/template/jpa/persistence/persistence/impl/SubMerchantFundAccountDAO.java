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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.SubMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.submerchantfundaccount.SearchSubMerchantFundAccountData;
import com.woodare.template.jpa.persistence.data.submerchantfundaccount.SubMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantFundAccountDAO;

/**
 * ClassName: SubMerchantFundAccountDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/10/15
 */
@Service
public class SubMerchantFundAccountDAO extends AbstractPagedDAO<SubMerchantFundAccount> implements ISubMerchantFundAccountDAO {

	@Override
	protected Class<SubMerchantFundAccount> getDomainClass() {
		return SubMerchantFundAccount.class;
	}

	@Override
	@Transactional(readOnly = true)
	public IPagedList<SubMerchantFundAccount> searchItems(SearchSubMerchantFundAccountData searchData) {

		StringBuffer sql = new StringBuffer("from SubMerchantFundAccount a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		conditions.add(new TypeCondition("deleteStatus", "a.deleteStatus = :deleteStatus", EnumDeleteStatus.VALID));

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
		TypedQuery<SubMerchantFundAccount> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), SubMerchantFundAccount.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(readOnly = true)
	public SubMerchantFundAccount findById(String id) {
		SubMerchantFundAccount item = this.findOne(id);
		if (item == null || item.getDeleteStatus() != EnumDeleteStatus.VALID) {
			item = null;
		}
		return item;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SubMerchantFundAccount findByUserNoAndCoin(String userNo, String coin) {
		TypedQuery<SubMerchantFundAccount> query = this.getEntityManager().createQuery("from SubMerchantFundAccount a where a.userNo = :userNo and a.coin = :coin", SubMerchantFundAccount.class);
		query.setMaxResults(1);
		query.setParameter("userNo", userNo);
		query.setParameter("coin", coin);
		List<SubMerchantFundAccount> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<SubMerchantFundAccount> findByUserNoAndCoins(String userNo, List<String> coins) {
		TypedQuery<SubMerchantFundAccount> query = this.getEntityManager().createQuery("from SubMerchantFundAccount a where a.userNo = :userNo and a.coin in (:coins)", SubMerchantFundAccount.class);
		query.setMaxResults(coins.size());
		query.setParameter("userNo", userNo);
		query.setParameter("coins", coins);
		return query.getResultList();
	}

	@Override
	public int updateById(SubMerchantFundAccount item, String id) {
		StringBuffer sql = new StringBuffer("update SubMerchantFundAccount a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// 修改时间
		conditions.add(new TypeCondition("updateDate", "a.updateDate = :updateDate", new Date()));
		// 状态
		if (item.getStatus() != null) {
			conditions.add(new TypeCondition("status", "a.status = :status", item.getStatus()));
		}
		// 用户姓名
		if (StringUtils.isNotEmpty(item.getAddress())) {
			conditions.add(new TypeCondition("address", "a.address = :address", item.getAddress()));
		}
		// 币地址
		if (StringUtils.isNotEmpty(item.getChangeDate())) {
			conditions.add(new TypeCondition("changeDate", "a.changeDate = :changeDate", item.getChangeDate()));
		}
		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" set ").append(this.joinConditions(conditions, ", "));
		}
		sql.append(" where a.id = :id");

		// Create query statements
		Query query = this.getEntityManager().createQuery(sql.toString());

		// Append conditions' variables
		this.addParameters(query, conditions);

		query.setParameter("id", id);

		return query.executeUpdate();
	}

	/**
	 * 变动当日余额
	 */
	@Override
	public int changeBalance(SubMerchantFundAccountData diffModel) {
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();
		List<TypeCondition> wheres = new ArrayList<TypeCondition>();

		// 设置资金状态变化标识
		conditions.add(new TypeCondition("changedFlag", "a.changedFlag = :changedFlag", true));

		// 限定本次更新的用户标识、币类型
		wheres.add(new TypeCondition("userNoCons", "a.userNo = :userNoCons", diffModel.getUserNo()));
		wheres.add(new TypeCondition("coinCons", "a.coin = :coin", diffModel.getCoin()));

		// // 期初余额
		// if (diffModel.getLastSettleAmt() != null) {
		// conditions.add(new TypeCondition("curInAmt", "a.curInAmt = a.curInAmt + :curInAmt", diffModel.getLastSettleAmt()));
		// }
		// 账户余额
		if (diffModel.getSettleInAmt() != null) {
			conditions.add(new TypeCondition("settleInAmt", "a.settleInAmt = a.settleInAmt + :settleInAmt", diffModel.getSettleInAmt()));
		}
		// 消费金额
		if (diffModel.getSettleOutAmt() != null) {
			conditions.add(new TypeCondition("settleOutAmt", "a.settleOutAmt = a.settleOutAmt + :settleOutAmt", diffModel.getSettleOutAmt()));
			wheres.add(new TypeCondition("settleOutAmtCon", "a.settleInAmt >= a.frozenAmt + a.settleOutAmt + :settleOutAmtCon", diffModel.getSettleOutAmt()));
		}
		// 消费金额
		if (diffModel.getSettleOutAmt() != null) {
			conditions.add(new TypeCondition("settleOutAmt", "a.settleOutAmt = a.settleOutAmt + :settleOutAmt", diffModel.getSettleOutAmt()));
		}
		// // 冻结金额
		// if (diffModel.getFrozenAmt() != null) {
		// conditions.add(new TypeCondition("frozenAmt", "a.frozenAmt = a.frozenAmt + :frozenAmt", diffModel.getFrozenAmt()));
		//
		// // String t1LeftFrozenEnough = "(a.settleInAmt >= a.frozenAmt + a.settleOutAmt and a.curInAmt >= a.curOutAmt + :curOutAmt)";
		// // String t1FrozenNotEnough = "(a.settleInAmt < a.frozenAmt + a.settleOutAmt and (a.settleInAmt - a.frozenAmt - a.settleOutAmt + a.curInAmt ) >= a.curOutAmt + :curOutAmt)";
		// // wheres.add(new TypeCondition("creditRatio", "(" + t1LeftFrozenEnough + " or " + t1FrozenNotEnough + ")", merchantData.getCreditRatio()));
		// }
		if (diffModel.getChangeDate() != null) {
			wheres.add(new TypeCondition("changeDate", "a.changeDate = :changeDate", diffModel.getChangeDate()));
		}

		StringBuffer sql = new StringBuffer();
		sql.append("update DownMerchantFundAccount a ");
		sql.append(" set " + this.joinConditions(conditions, ","));
		sql.append(" where " + this.joinConditions(wheres, " and "));

		conditions.addAll(wheres);

		Query executeQuery = this.getEntityManager().createQuery(sql.toString());
		this.addParameters(executeQuery, conditions);

		return executeQuery.executeUpdate();
	}

	/**
	 * 冻结账户余额
	 */
	@Override
	public int frozenBalance(BigDecimal frozenAmt, String userNo, String coin) {
		StringBuffer sql = new StringBuffer();
		sql.append("update SubMerchantFundAccount a ");
		sql.append("set a.frozenAmt = a.frozenAmt + " + frozenAmt + " ");
		sql.append("where a.userNo = '" + userNo + "' ");
		sql.append("and a.coin = '" + coin + "' ");
		if (frozenAmt.doubleValue() < 0) {
			sql.append("and a.frozenAmt + " + frozenAmt + " >= 0");
		}
		Query executeQuery = this.getEntityManager().createQuery(sql.toString());
		return executeQuery.executeUpdate();
	}

}
