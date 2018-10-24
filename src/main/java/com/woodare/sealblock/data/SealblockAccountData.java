package com.woodare.sealblock.data;

/**
 * @author Luke
 */
public class SealblockAccountData {

	// processJson.put("approverAddress", "0x" + sAddress);
	// processJson.put("approverKey", sPrivatekeyInHex);
	private String prikey;

	private String blockAddr;

	/**
	 * @return the prikey
	 */
	public String getPrikey() {
		return prikey;
	}

	/**
	 * @param prikey
	 *            the prikey to set
	 */
	public void setPrikey(String prikey) {
		this.prikey = prikey;
	}

	/**
	 * @return the blockAddr
	 */
	public String getBlockAddr() {
		return blockAddr;
	}

	/**
	 * @param blockAddr
	 *            the blockAddr to set
	 */
	public void setBlockAddr(String blockAddr) {
		this.blockAddr = blockAddr;
	}

}
