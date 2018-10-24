package com.woodare.template.web.viewdata.passwayroutemerchant;

/**
 * @author Luke
 */
public class SealblockCoinData {

	// 单位Wei
	private String decimals;

	// 货币编码
	private String txType;

	// 前缀数据
	private String prefix;

	/**
	 * @return the decimals
	 */
	public String getDecimals() {
		return decimals;
	}

	/**
	 * @param decimals
	 *            the decimals to set
	 */
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}

	/**
	 * @return the txType
	 */
	public String getTxType() {
		return txType;
	}

	/**
	 * @param txType
	 *            the txType to set
	 */
	public void setTxType(String txType) {
		this.txType = txType;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
