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
package com.woodare.template.jpa.persistence.data.downnocardinvoice;

import java.math.BigDecimal;

import com.woodare.framework.utils.CommonUtils;

/**
 * ClassName: DownInvoiceSumData
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Jan 4, 2017
 */
public class DownNoCardInvoiceUpSumData {

	/** 通道号 * */
	private String channel;

	/** 通道商户编号* */
	private String channelAccNo;

	/** 通道商户名称 * */
	private String channelAccName;

	private String payType;

	/** 总交易金额 */
	private BigDecimal totalPrice;

	/** 总交易笔数 */
	private Long totalCount;

	/** 交易金额 */
	private BigDecimal price;

	/** 成功交易笔数 */
	private Long count;

	/** 清算金额 */
	private BigDecimal realPrice;

	/** 手续费金额 */
	private BigDecimal feePrice;

	/** 代理分润 */
	private BigDecimal agtProfit;

	/** 平台分润 */
	private BigDecimal profit;

	@SuppressWarnings("unchecked")
	private <T> T convert(Object o, Class<T> target) {
		try {
			if (o == null || target == null) {
				return null;
			}
			else if (target.isAssignableFrom(Long.class)) {
				return (T) CommonUtils.toLong(o);
			}
			else if (target.isAssignableFrom(String.class)) {
				return (T) CommonUtils.toStr(o);
			}
			else if (target.isAssignableFrom(BigDecimal.class)) {
				return (T) new BigDecimal(o.toString());
			}
			else {
				return (T) o;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DownNoCardInvoiceUpSumData() {
	}

	public DownNoCardInvoiceUpSumData(Object[] data) {

		// sb.append(" a.channel, a.channel_acc_no, a.channel_acc_name, ");
		// sb.append(" sum(a.price) total_price,");
		// sb.append(" count(a.id) total_count,");
		// sb.append(" sum(case when a.status='00' then a.price else 0 end) price,");
		// sb.append(" count(case when a.status='00' then a.id else 0 end) count,");
		// sb.append(" sum(case when a.status='00' then a.down_realt1price else 0 end) real_price,");
		// sb.append(" sum(case when a.status='00' then a.down_pay_fee else 0 end) fee_price");
		//
		this.channel = convert(data[0], String.class);
		this.channelAccNo = convert(data[1], String.class);
		this.channelAccName = convert(data[2], String.class);
		this.totalPrice = convert(data[3], BigDecimal.class);
		this.totalCount = convert(data[4], Long.class);
		this.price = convert(data[5], BigDecimal.class);
		this.count = convert(data[6], Long.class);
		this.realPrice = convert(data[7], BigDecimal.class);
		this.feePrice = convert(data[8], BigDecimal.class);
		this.agtProfit = convert(data[9], BigDecimal.class);
		this.profit = convert(data[10], BigDecimal.class);
		this.payType = convert(data[11], String.class);
	}

	/**
	 * @return the agtProfit
	 */
	public BigDecimal getAgtProfit() {
		return agtProfit;
	}

	/**
	 * @param agtProfit
	 *            the agtProfit to set
	 */
	public void setAgtProfit(BigDecimal agtProfit) {
		this.agtProfit = agtProfit;
	}

	/**
	 * @return the profit
	 */
	public BigDecimal getProfit() {
		return profit;
	}

	/**
	 * @param profit
	 *            the profit to set
	 */
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the channelAccName
	 */
	public String getChannelAccName() {
		return channelAccName;
	}

	/**
	 * @param channelAccName
	 *            the channelAccName to set
	 */
	public void setChannelAccName(String channelAccName) {
		this.channelAccName = channelAccName;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getFeePrice() {
		return feePrice;
	}

	public void setFeePrice(BigDecimal feePrice) {
		this.feePrice = feePrice;
	}

	/**
	 * @return the channelAccNo
	 */
	public String getChannelAccNo() {
		return channelAccNo;
	}

	/**
	 * @param channelAccNo
	 *            the channelAccNo to set
	 */
	public void setChannelAccNo(String channelAccNo) {
		this.channelAccNo = channelAccNo;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
