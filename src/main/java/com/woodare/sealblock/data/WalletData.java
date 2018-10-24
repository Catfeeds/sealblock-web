package com.woodare.sealblock.data;

/**
 * @author Luke
 */
public class WalletData {
	// {
	// ->->"coinType": "ETH",
	// ->->"addr": "0084fedf205b1665db9a6fa856d7108849dfa012",
	// ->->"creationtime": {
	// ->->->->"data": "y0J2X1PPr4AA",
	// ->->->->"type": 13
	// ->->}
	// }

	private String coinType;

	private String addr;

	private WalletCreatetimeData creationtime;

	/**
	 * @return the coinType
	 */
	public String getCoinType() {
		return coinType;
	}

	/**
	 * @param coinType
	 *            the coinType to set
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the creationtime
	 */
	public WalletCreatetimeData getCreationtime() {
		return creationtime;
	}

	/**
	 * @param creationtime
	 *            the creationtime to set
	 */
	public void setCreationtime(WalletCreatetimeData creationtime) {
		this.creationtime = creationtime;
	}

	public static class WalletCreatetimeData {

		private String data;
		private Integer type;

		/**
		 * @return the data
		 */
		public String getData() {
			return data;
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public void setData(String data) {
			this.data = data;
		}

		/**
		 * @return the type
		 */
		public Integer getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(Integer type) {
			this.type = type;
		}

	}
}
