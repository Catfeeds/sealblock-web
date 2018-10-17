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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.data.EnumOrderStatus;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceSumData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceUpSumData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.SearchDownNoCardInvoiceData;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceDAO;

/**
 * ClassName: DownNoCardInvoiceDAO
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/01/09
 */
@Service
public class DownNoCardInvoiceDAO extends AbstractPagedDAO<DownNoCardInvoice> implements IDownNoCardInvoiceDAO {

	@Override
	protected Class<DownNoCardInvoice> getDomainClass() {
		return DownNoCardInvoice.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<DownNoCardInvoice> searchDownNoCardInvoices(SearchDownNoCardInvoiceData searchData) {

		StringBuffer sql = new StringBuffer("from DownNoCardInvoice a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}

		if (searchData.getStartDate() != null) {
			if (StringUtils.isNotEmpty(searchData.getStartTime())) {
				String date = SDFFactory.DATE.format(searchData.getStartDate()) + searchData.getStartTime().replaceAll(":", "");
				conditions.add(new TypeCondition("startDate", "a.createDate >= :startDate", SDFFactory.DATETIME.format(date)));
			}
			else {
				conditions.add(new TypeCondition("startDate", "a.createDate >= :startDate", searchData.getStartDate()));
			}
		}
		if (searchData.getEndDate() != null) {
			if (StringUtils.isNotEmpty(searchData.getEndTime())) {
				String date = SDFFactory.DATE.format(searchData.getEndDate()) + searchData.getEndTime().replaceAll(":", "");
				conditions.add(new TypeCondition("endDate", "a.createDate < :endDate", SDFFactory.DATETIME.format(date)));
			}
			else {
				conditions.add(new TypeCondition("endDate", "a.createDate < :endDate", searchData.getEndDate()));
			}
		}

		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			conditions.add(new TypeCondition("agentNo", "a.agentNo = :agentNo", searchData.getAgentNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			conditions.add(new TypeCondition("mchNo", "a.mchNo = :mchNo", searchData.getMchNo()));
		}

		if (searchData.getChannel() != null) {
			conditions.add(new TypeCondition("channel", "a.channel = :channel", searchData.getChannel()));
		}
		if (StringUtils.isNotEmpty(searchData.getTransNo())) {
			conditions.add(new TypeCondition("transNo", "a.transNo = :transNo", searchData.getTransNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getTradeNo())) {
			conditions.add(new TypeCondition("tradeNo", "a.tradeNo = :tradeNo", searchData.getTradeNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getUserPhone())) {
			conditions.add(new TypeCondition("userPhone", "a.userPhone = :userPhone", searchData.getUserPhone()));
		}

		if (searchData.getStatus() != null) {
			conditions.add(new TypeCondition("status", "a.status = :status", searchData.getStatus()));
		}
		if (searchData.getFundStatus() != null) {
			conditions.add(new TypeCondition("fundStatus", "a.fundStatus = :fundStatus", searchData.getFundStatus()));
		}
		 if (StringUtils.isNotEmpty(searchData.getKeywords())) {
		 conditions.add(new TypeCondition("keywords", "(a.id like :keywords)", "%" + searchData.getKeywords() + "%"));
		 }
		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		// Create query statements
		TypedQuery<DownNoCardInvoice> query = this.getEntityManager().createQuery(sql.toString() + " order by a.createDate desc", DownNoCardInvoice.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownNoCardInvoice findByTradeNoAndMchNo(String tradeNo, String mchNo) {
		TypedQuery<DownNoCardInvoice> query = this.getEntityManager().createQuery("from DownNoCardInvoice a where a.tradeNo = :tradeNo and a.mchNo = :mchNo", DownNoCardInvoice.class);
		query.setMaxResults(1);
		query.setParameter("tradeNo", tradeNo);
		query.setParameter("mchNo", mchNo);
		List<DownNoCardInvoice> models = query.getResultList();
		return models != null && models.size() > 0 ? models.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownNoCardInvoice findByTransNo(String transNo) {
		TypedQuery<DownNoCardInvoice> query = this.getEntityManager().createQuery("from DownNoCardInvoice a where a.transNo = :transNo", DownNoCardInvoice.class);
		query.setMaxResults(1);
		query.setParameter("transNo", transNo);
		List<DownNoCardInvoice> models = query.getResultList();
		return models != null && models.size() > 0 ? models.get(0) : null;
	}

	// TODO: 待处理成异步确认 Funding Status
	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<DownNoCardInvoice> findNeedCheckByStatusChgDate(Date start, Date end) {

		String sql = "from DownNoCardInvoice a ";
		sql += "where a.status = :status ";
		sql += "and a.deleteStatus = :deleteStatus ";
		sql += "and a.createDate >= :startDate ";
		sql += "and a.createDate < :endDate";

		TypedQuery<DownNoCardInvoice> query = this.getEntityManager().createQuery(sql, DownNoCardInvoice.class);

		// 查询待查证数据
		query.setParameter("status", EnumOrderStatus.PENDING);
		query.setParameter("startDate", start);
		query.setParameter("endDate", end);

		List<DownNoCardInvoice> models = query.getResultList();
		return models;
	}

	/**
	 * @param data
	 * @param fields
	 */
	private List<TypeCondition> parseNotNullFields(DownNoCardInvoiceData data, List<TypeCondition> fields, String columnSuffix) {
		if (fields == null) {
			fields = new ArrayList<TypeCondition>();
		}
		if (StringUtils.isNotEmpty(data.getId())) {
			fields.add(new TypeCondition("id" + columnSuffix, "a.id =:id" + columnSuffix, data.getId()));
		}
		if (data.getSubUserFee() != null) {
			fields.add(new TypeCondition("subUserFee" + columnSuffix, "a.subUserFee =:subUserFee" + columnSuffix, data.getSubUserFee()));
		}
		if (data.getStatus() != null) {
			fields.add(new TypeCondition("status" + columnSuffix, "a.status =:status" + columnSuffix, data.getStatus()));
		}
		if (StringUtils.isNotEmpty(data.getStatusDesc())) {
			fields.add(new TypeCondition("statusDesc" + columnSuffix, "a.statusDesc =:statusDesc" + columnSuffix, data.getStatusDesc()));
		}
		if (data.getStatusChgDate() != null) {
			fields.add(new TypeCondition("statusChgDate" + columnSuffix, "a.statusChgDate =:statusChgDate" + columnSuffix, data.getStatusChgDate()));
		}
		if (data.getFundStatus() != null) {
			fields.add(new TypeCondition("fundStatus" + columnSuffix, "a.fundStatus =:fundStatus" + columnSuffix, data.getFundStatus()));
		}
		if (StringUtils.isNotEmpty(data.getFundStatusDesc())) {
			fields.add(new TypeCondition("fundStatusDesc" + columnSuffix, "a.fundStatusDesc =:fundStatusDesc" + columnSuffix, data.getFundStatusDesc()));
		}
		if (data.getFundChgDate() != null) {
			fields.add(new TypeCondition("fundChgDate" + columnSuffix, "a.fundChgDate =:fundChgDate" + columnSuffix, data.getFundChgDate()));
		}
		if (StringUtils.isNotEmpty(data.getPayLink())) {
			fields.add(new TypeCondition("payLink" + columnSuffix, "a.payLink =:payLink" + columnSuffix, data.getPayLink()));
		}
		if (StringUtils.isNotEmpty(data.getUpTransNo())) {
			fields.add(new TypeCondition("upTransNo" + columnSuffix, "a.upTransNo =:upTransNo" + columnSuffix, data.getUpTransNo()));
		}
		return fields;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateSelectiveByCons(DownNoCardInvoiceData values, DownNoCardInvoiceData cons) {
		List<TypeCondition> fields = parseNotNullFields(values, new ArrayList<TypeCondition>(), "");
		List<TypeCondition> wheres = parseNotNullFields(cons, new ArrayList<TypeCondition>(), "Condition");

		StringBuffer sql = new StringBuffer("update DownNoCardInvoice a");
		sql.append(" set ");
		sql.append(this.joinConditions(fields, ", "));
		sql.append(" where ");
		sql.append(this.joinConditions(wheres, " and "));

		fields.addAll(wheres);

		return executeUpdate(sql.toString(), fields);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<DownNoCardInvoiceSumData> sumInvoice(SearchDownNoCardInvoiceData searchData) {

		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("  m.*,");
		sb.append("  n.name");
		sb.append(" from (");
		sb.append("  select");
		sb.append("    a.mch_no,");
		sb.append("    sum(a.price) total_price,");
		sb.append("    count(a.id) total_count,");
		sb.append("    sum(case when a.status = '00' then a.price else 0 end) price,");
		sb.append("    sum(case when a.status = '00' then 1 else 0 end) count,");
		sb.append("    sum(case when a.status = '00' then a.down_realt1price else 0 end) real_price,");
		sb.append("    sum(case when a.status = '00' then a.down_pay_fee else 0 end) fee_price,");
		sb.append("    sum(case when a.status = '00' then a.agt_profit else 0 end) agt_profit,");
		sb.append("    sum(case when a.status = '00' then a.profit else 0 end) profit,");
		sb.append("    pay_type");
		sb.append("  from down_no_card_invoice a");
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
		sb.append("  group by a.mch_no,pay_type order by a.mch_no,pay_type");
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
		List<DownNoCardInvoiceSumData> results = new ArrayList<DownNoCardInvoiceSumData>();
		if (items != null) {
			for (Object[] item : items) {
				results.add(new DownNoCardInvoiceSumData(item));
			}
		}
		return results;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownNoCardInvoiceSumData sumMchByStatusChgDate(String mchNo, Date startDate, Date endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("  select");
		sb.append("    sum(a.price) price,");
		sb.append("    sum(case when a.status = '00' then a.down_pay_fee else 0 end) fee_price");
		sb.append("  from down_no_card_invoice a");
		sb.append("  where a.delete_status = 'VALID'");
		sb.append("   and a.mch_no = :mchNo");
		sb.append("   and a.status = '00'");
		sb.append("   and a.create_date >= :startDate");
		sb.append("   and a.create_date < :endDate");
		// TypedQuery<InvoiceSumData>
		Query query = this.getEntityManager().createNativeQuery(sb.toString());
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("mchNo", mchNo);

		@SuppressWarnings("unchecked")
		List<Object[]> items = query.getResultList();

		DownNoCardInvoiceSumData sum = null;
		if (items != null && items.size() > 0) {
			Object[] objs = items.get(0);
			sum = new DownNoCardInvoiceSumData();
			sum.setPrice(DownNoCardInvoiceSumData.convert(objs[0], BigDecimal.class));
			sum.setFeePrice(DownNoCardInvoiceSumData.convert(objs[1], BigDecimal.class));
		}
		return sum;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<DownNoCardInvoiceUpSumData> upSumInvoice(SearchDownNoCardInvoiceData searchData) {
		StringBuffer sb = new StringBuffer();

		sb.append("select");
		sb.append("    a.channel, a.channel_acc_no, a.channel_acc_name, ");
		sb.append("    sum(a.price) total_price,");
		sb.append("    count(a.id) total_count,");
		sb.append("    sum(case when a.status='00' then a.price else 0 end) price,");
		sb.append("    sum(case when a.status='00' then 1 else 0 end) count,");
		sb.append("    sum(case when a.status='00' then a.down_realt1price else 0 end) real_price,");
		sb.append("    sum(case when a.status='00' then a.down_pay_fee else 0 end) fee_price,");
		sb.append("    sum(case when a.status='00' then a.agt_profit else 0 end) agt_profit,");
		sb.append("    sum(case when a.status='00' then a.profit else 0 end) profit,");
		sb.append("    pay_type");
		sb.append("  from down_no_card_invoice a");
		sb.append("  where a.delete_status = 'VALID'");
		if (searchData.getChannel() != null) {
			sb.append("   and a.channel = :channel");
		}
		if (searchData.getStartDate() != null) {
			sb.append("   and a.create_date >= :startDate");
		}
		if (searchData.getEndDate() != null) {
			sb.append("   and a.create_date <= :endDate");
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			sb.append("   and a.channel like :keywords");
		}
		sb.append("  group by a.channel, a.channel_acc_no, a.channel_acc_name, a.pay_type " + "order by a.channel, a.channel_acc_no, a.channel_acc_name, a.pay_type");
		// TypedQuery<InvoiceSumData>
		Query query = this.getEntityManager().createNativeQuery(sb.toString());
		if (searchData.getStartDate() != null) {
			query.setParameter("startDate", searchData.getStartDate());
		}
		if (searchData.getEndDate() != null) {
			query.setParameter("endDate", searchData.getEndDate());
		}
		if (searchData.getChannel() != null) {
			query.setParameter("channel", searchData.getChannel().toString());
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			query.setParameter("keywords", "%" + searchData.getKeywords() + "%");
		}
		@SuppressWarnings("unchecked")
		List<Object[]> items = query.getResultList();
		List<DownNoCardInvoiceUpSumData> results = new ArrayList<DownNoCardInvoiceUpSumData>();
		if (items != null) {
			for (Object[] item : items) {
				results.add(new DownNoCardInvoiceUpSumData(item));
			}
		}
		return results;
	}

}
