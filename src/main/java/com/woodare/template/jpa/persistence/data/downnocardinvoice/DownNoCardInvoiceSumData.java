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
 * ClassName: DownNoCardInvoiceSumData
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Jan 4, 2017
 */
public class DownNoCardInvoiceSumData {

	/** 商户号 * */
	private String mchNo;

	/** 商户名 * */
	private String mchName;

	/** 交易方式 */
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

	@SuppressWarnings("unchecked")
	public static <T> T convert(Object o, Class<T> target) {
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

	public DownNoCardInvoiceSumData() {
	}

	public DownNoCardInvoiceSumData(Object[] data) {
		this.mchNo = convert(data[0], String.class);
		this.totalPrice = convert(data[1], BigDecimal.class);
		this.totalCount = convert(data[2], Long.class);
		this.price = convert(data[3], BigDecimal.class);
		this.count = convert(data[4], Long.class);
		this.realPrice = convert(data[5], BigDecimal.class);
		this.feePrice = convert(data[6], BigDecimal.class);
		this.agtProfit = convert(data[7], BigDecimal.class);
		this.profit = convert(data[8], BigDecimal.class);
		this.payType = convert(data[9], String.class);
		this.mchName = convert(data[10], String.class);
	}

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
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

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

}
