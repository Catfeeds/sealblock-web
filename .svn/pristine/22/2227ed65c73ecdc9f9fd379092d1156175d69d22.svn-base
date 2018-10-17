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
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositSumData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositUpSumData;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.SearchDownMerchantDepositData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDepositDAO;

/**
 * ClassName: DownMerchantDepositDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/03
 */
@Service
public class DownMerchantDepositDAO extends AbstractPagedDAO<DownMerchantDeposit> implements IDownMerchantDepositDAO {

	@Override
	protected Class<DownMerchantDeposit> getDomainClass() {
		return DownMerchantDeposit.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<DownMerchantDeposit> searchItems(SearchDownMerchantDepositData searchData) {

		StringBuffer sql = new StringBuffer("from DownMerchantDeposit a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		conditions.add(new TypeCondition("deleteStatus", "a.deleteStatus = :deleteStatus", EnumDeleteStatus.VALID));

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			conditions.add(new TypeCondition("mchNo", "a.mchNo = :mchNo", searchData.getMchNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			conditions.add(new TypeCondition("agentNo", "a.agentNo = :agentNo", searchData.getAgentNo()));
		}
		if (searchData.getChannel() != null) {
			conditions.add(new TypeCondition("channel", "a.channel = :channel", searchData.getChannel()));
		}
		if (searchData.getStartDate() != null) {
			conditions.add(new TypeCondition("startDate", "a.createDate >= :startDate", searchData.getStartDate()));
		}
		if (searchData.getEndDate() != null) {
			conditions.add(new TypeCondition("endDate", "a.createDate < :endDate", searchData.getEndDate()));
		}
		if (searchData.getStartDateSuccess() != null) {
			conditions.add(new TypeCondition("startDateSuccess", "a.statusChgDate >= :startDateSuccess", searchData.getStartDateSuccess()));
		}
		if (searchData.getEndDateSuccess() != null) {
			conditions.add(new TypeCondition("endDateSuccess", "a.statusChgDate < :endDateSuccess", searchData.getEndDateSuccess()));
		}
		if (searchData.getMode() != null) {
			conditions.add(new TypeCondition("mode", "a.mode = :mode", searchData.getMode()));
		}
		if (StringUtils.isNotEmpty(searchData.getTransNo())) {
			conditions.add(new TypeCondition("transNo", "a.transNo = :transNo", searchData.getTransNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getTradeNo())) {
			conditions.add(new TypeCondition("tradeNo", "a.tradeNo = :tradeNo", searchData.getTradeNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getStatus())) {
			conditions.add(new TypeCondition("status", "a.status = :status", searchData.getStatus()));
		}
		if (StringUtils.isNotEmpty(searchData.getStatusDesc())) {
			conditions.add(new TypeCondition("statusDesc", "a.statusDesc = :statusDesc", searchData.getStatusDesc()));
		}
		if (StringUtils.isNotEmpty(searchData.getAccCardNo())) {
			conditions.add(new TypeCondition("accCardNo", "a.accCardNo = :accCardNo", searchData.getAccCardNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords",
					"(a.accCardNo like :keywords " + "or a.transNo like :keywords " + "or a.tradeNo like :keywords " + "or a.mchNo like :keywords " + "or a.mchName like :keywords " + "or a.accName like :keywords)",
					"%" + searchData.getKeywords() + "%"));
		}

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("createDate desc");
		}

		// Create query statements
		TypedQuery<DownMerchantDeposit> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), DownMerchantDeposit.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateSelectiveByCons(DownMerchantDepositData values, DownMerchantDepositData cons) {
		List<TypeCondition> fields = parseNotNullFields(values, new ArrayList<TypeCondition>(), "");
		List<TypeCondition> wheres = parseNotNullFields(cons, new ArrayList<TypeCondition>(), "Condition");

		StringBuffer sql = new StringBuffer("update DownMerchantDeposit a");
		sql.append(" set ");
		sql.append(this.joinConditions(fields, ", "));
		sql.append(" where ");
		sql.append(this.joinConditions(wheres, " and "));

		fields.addAll(wheres);

		return executeUpdate(sql.toString(), fields);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownMerchantDeposit findByTradeNoAndMchNo(String tradeNo, String mchNo) {
		TypedQuery<DownMerchantDeposit> query = this.getEntityManager().createQuery("from DownMerchantDeposit a where a.tradeNo = :tradeNo and a.mchNo = :mchNo and a.deleteStatus=:deleteStatus", DownMerchantDeposit.class);
		query.setMaxResults(1);
		query.setParameter("deleteStatus", EnumDeleteStatus.VALID);
		query.setParameter("tradeNo", tradeNo);
		query.setParameter("mchNo", mchNo);
		List<DownMerchantDeposit> models = query.getResultList();
		return models != null && models.size() > 0 ? models.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownMerchantDeposit findByTransNo(String transNo) {
		TypedQuery<DownMerchantDeposit> query = this.getEntityManager().createQuery("from DownMerchantDeposit a where a.transNo = :transNo and a.deleteStatus=:deleteStatus", DownMerchantDeposit.class);
		query.setMaxResults(1);
		query.setParameter("deleteStatus", EnumDeleteStatus.VALID);
		query.setParameter("transNo", transNo);
		List<DownMerchantDeposit> models = query.getResultList();
		return models != null && models.size() > 0 ? models.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<DownMerchantDeposit> findNeedCheckByStatusChgDate(Date start, Date end) {
		String sql = "from DownMerchantDeposit a ";
		sql += "where a.status = :status ";
		sql += "and a.deleteStatus = :deleteStatus ";
		sql += "and a.statusChgDate >= :startDate ";
		sql += "and a.statusChgDate < :endDate";

		TypedQuery<DownMerchantDeposit> query = this.getEntityManager().createQuery(sql, DownMerchantDeposit.class);

		query.setParameter("deleteStatus", EnumDeleteStatus.VALID);

		// 明确为支付中的交易数据
		query.setParameter("status", "01");
		query.setParameter("startDate", start);
		query.setParameter("endDate", end);

		List<DownMerchantDeposit> models = query.getResultList();
		return models;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<DownMerchantDepositSumData> sumDeposit(SearchDownMerchantDepositData searchData) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("  m.*,");
		sb.append("  n.name");
		sb.append(" from (");
		sb.append("  select");
		sb.append("    sum(a.price) total_price,");
		sb.append("    count(a.id) total_count,");

		sb.append("    sum(case when a.status = '01' then a.price else 0 end) pending_price,");
		sb.append("    sum(case when a.status = '01' then 1 else 0 end) pending_count,");

		sb.append("    sum(case when a.status = '00' then a.price else 0 end) price,");
		sb.append("    sum(case when a.status = '00' then 1 else 0 end) count,");
		sb.append("    sum(case when a.status = '00' then a.down_draw_fee else 0 end) fee_price,");
		sb.append("    sum(case when a.status = '00' then a.agt_profit else 0 end) agt_profit,");
		sb.append("    sum(case when a.status = '00' then a.profit else 0 end) profit,");
		sb.append("    a.mch_no, a.mode");
		sb.append("  from down_merchant_deposit a");
		sb.append("  where a.delete_status = 'VALID'");
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			sb.append("   and a.mch_no = :mchNo");
		}
		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			sb.append("   and a.agent_no = :agentNo");
		}
		if (searchData.getStartDate() != null) {
			sb.append("   and a.create_date >= :startDate");
		}
		if (searchData.getEndDate() != null) {
			sb.append("   and a.create_date <= :endDate");
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			sb.append("   and (a.mch_name like :keywords or a.mch_no like :keywords)");
		}
		sb.append("  group by a.mch_no, a.mode ");
		sb.append("  order by a.mch_no, a.mode ");
		sb.append(" ) m");
		sb.append(" left join down_merchant n on m.mch_no = n.mch_no");
		// TypedQuery<InvoiceSumData>
		Query query = this.getEntityManager().createNativeQuery(sb.toString());

		if (searchData.getStartDate() != null) {
			query.setParameter("startDate", searchData.getStartDate());
		}
		if (searchData.getEndDate() != null) {
			query.setParameter("endDate", searchData.getEndDate());
		}
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			query.setParameter("mchNo", searchData.getMchNo());
		}
		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			query.setParameter("agentNo", searchData.getAgentNo());
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			query.setParameter("keywords", "%" + searchData.getKeywords() + "%");
		}
		@SuppressWarnings("unchecked")
		List<Object[]> items = query.getResultList();
		List<DownMerchantDepositSumData> results = new ArrayList<DownMerchantDepositSumData>();
		if (items != null) {
			for (Object[] item : items) {
				results.add(new DownMerchantDepositSumData(item));
			}
		}
		return results;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<DownMerchantDepositUpSumData> upSumDeposit(SearchDownMerchantDepositData searchData) {
		StringBuffer sb = new StringBuffer();
		sb.append("  select");
		sb.append("    sum(a.price) total_price,");
		sb.append("    count(a.id) total_count,");

		sb.append("    sum(case when a.status = '01' then a.price else 0 end) pending_price,");
		sb.append("    sum(case when a.status = '01' then 1 else 0 end) pending_count,");

		sb.append("    sum(case when a.status = '00' then a.price else 0 end) price,");
		sb.append("    sum(case when a.status = '00' then 1 else 0 end) count,");
		sb.append("    sum(case when a.status = '00' then a.down_draw_fee else 0 end) fee_price,");
		sb.append("    sum(case when a.status = '00' then a.agt_profit else 0 end) agt_profit,");
		sb.append("    sum(case when a.status = '00' then a.profit else 0 end) profit,");

		sb.append("    a.channel, a.channel_acc_no, a.mode, a.channel_acc_name");
		sb.append("  from down_merchant_deposit a");
		sb.append("  where a.delete_status = 'VALID'");
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			sb.append("   and a.mch_no = :mchNo");
		}
		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			sb.append("   and a.agent_no = :agentNo");
		}
		if (searchData.getStartDate() != null) {
			sb.append("   and a.create_date >= :startDate");
		}
		if (searchData.getEndDate() != null) {
			sb.append("   and a.create_date <= :endDate");
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			sb.append("   and (a.mch_name like :keywords or a.mch_no like :keywords)");
		}
		sb.append("  group by a.channel, a.channel_acc_no, a.mode, a.channel_acc_name ");
		sb.append("  order by a.channel, a.channel_acc_no, a.mode, a.channel_acc_name ");
		// TypedQuery<InvoiceSumData>
		Query query = this.getEntityManager().createNativeQuery(sb.toString());
		if (searchData.getStartDate() != null) {
			query.setParameter("startDate", searchData.getStartDate());
		}
		if (searchData.getEndDate() != null) {
			query.setParameter("endDate", searchData.getEndDate());
		}
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			query.setParameter("mchNo", searchData.getMchNo());
		}
		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			query.setParameter("agentNo", searchData.getAgentNo());
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			query.setParameter("keywords", "%" + searchData.getKeywords() + "%");
		}
		@SuppressWarnings("unchecked")
		List<Object[]> items = query.getResultList();
		List<DownMerchantDepositUpSumData> results = new ArrayList<DownMerchantDepositUpSumData>();
		if (items != null) {
			for (Object[] item : items) {
				results.add(new DownMerchantDepositUpSumData(item));
			}
		}
		return results;
	}

	/**
	 * @param data
	 * @param fields
	 * @param columnSuffix
	 * @return
	 */
	private List<TypeCondition> parseNotNullFields(DownMerchantDepositData data, List<TypeCondition> fields, String columnSuffix) {
		if (fields == null) {
			fields = new ArrayList<TypeCondition>();
		}
		if (StringUtils.isNotEmpty(data.getId())) {
			fields.add(new TypeCondition("id" + columnSuffix, "a.id =:id" + columnSuffix, data.getId()));
		}
		if (StringUtils.isNotEmpty(data.getStatus())) {
			fields.add(new TypeCondition("status" + columnSuffix, "a.status =:status" + columnSuffix, data.getStatus()));
		}
		if (StringUtils.isNotEmpty(data.getStatusDesc())) {
			fields.add(new TypeCondition("statusDesc" + columnSuffix, "a.statusDesc =:statusDesc" + columnSuffix, data.getStatusDesc()));
		}
		if (data.getStatusChgDate() != null) {
			fields.add(new TypeCondition("statusChgDate" + columnSuffix, "a.statusChgDate =:statusChgDate" + columnSuffix, data.getStatusChgDate()));
		}
		return fields;
	}
}
