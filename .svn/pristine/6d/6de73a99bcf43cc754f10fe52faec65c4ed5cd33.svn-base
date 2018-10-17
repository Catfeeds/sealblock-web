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
package com.woodare.template.jpa.persistence.data.downmerchantdeposit;

import java.math.BigDecimal;

import com.woodare.framework.utils.CommonUtils;

/**
 * ClassName: DownMerchantDepositSumData
 * 
 * @description
 * @author Luke
 * @Date Mar 3, 2018
 */
public class DownMerchantDepositSumData {

	/** 商户号 */
	private String mchNo;

	/** 商户名 */
	private String mchName;

	/** 代付类型 */
	private String mode;

	/** 总交易金额 */
	private BigDecimal totalPrice;

	/** 总交易笔数 */
	private Long totalCount;

	/** 交易金额 */
	private BigDecimal price;

	/** 总待查金额 */
	private BigDecimal pendingPrice;

	/** 总待查笔数 */
	private Long pendingCount;

	/** 成功交易笔数 */
	private Long count;

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

	public DownMerchantDepositSumData() {
	}

	public DownMerchantDepositSumData(Object[] data) {
		int idx = 0;

		this.totalPrice = convert(data[idx++], BigDecimal.class);
		this.totalCount = convert(data[idx++], Long.class);

		this.pendingPrice = convert(data[idx++], BigDecimal.class);
		this.pendingCount = convert(data[idx++], Long.class);

		this.price = convert(data[idx++], BigDecimal.class);
		this.count = convert(data[idx++], Long.class);
		this.feePrice = convert(data[idx++], BigDecimal.class);
		
		this.agtProfit = convert(data[idx++], BigDecimal.class);
		this.profit = convert(data[idx++], BigDecimal.class);

		this.mchNo = convert(data[idx++], String.class);
		this.mode = convert(data[idx++], String.class);
		this.mchName = convert(data[idx++], String.class);
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
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the pendingPrice
	 */
	public BigDecimal getPendingPrice() {
		return pendingPrice;
	}

	/**
	 * @param pendingPrice
	 *            the pendingPrice to set
	 */
	public void setPendingPrice(BigDecimal pendingPrice) {
		this.pendingPrice = pendingPrice;
	}

	/**
	 * @return the pendingCount
	 */
	public Long getPendingCount() {
		return pendingCount;
	}

	/**
	 * @param pendingCount
	 *            the pendingCount to set
	 */
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * @return the mchNo
	 */
	public String getMchNo() {
		return mchNo;
	}

	/**
	 * @param mchNo
	 *            the mchNo to set
	 */
	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	/**
	 * @return the mchName
	 */
	public String getMchName() {
		return mchName;
	}

	/**
	 * @param mchName
	 *            the mchName to set
	 */
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the feePrice
	 */
	public BigDecimal getFeePrice() {
		return feePrice;
	}

	/**
	 * @param feePrice
	 *            the feePrice to set
	 */
	public void setFeePrice(BigDecimal feePrice) {
		this.feePrice = feePrice;
	}

}
