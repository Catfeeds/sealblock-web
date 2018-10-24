package com.woodare.sealblock.data;

/**
 * 
 * @author Luke
 *
 */
public class TransactionData {
	// {
	// "fromAddr": "0x0084fedf205b1665Db9a6Fa856D7108849dfa012",
	// "toAddr": "0xf33ee4888bC5D2857737622277C272E85fF0e16A",
	// "amount": "50000000000000000",
	// "transaction_id": "0xf9a7609aa5c45af40ae5d401363c4aa3f27aba75b5babacc6a42a9b4d52d77d0",
	// "status": "confirmed",
	// "confirmation": "",
	// "raw_transaction": ""
	// }
	private String fromAddr;

	private String toAddr;

	private String amount;

	private String transaction_id;

	private String status;

	private String confirmation;

	private String raw_transaction;

	/**
	 * @return the fromAddr
	 */
	public String getFromAddr() {
		return fromAddr;
	}

	/**
	 * @param fromAddr
	 *            the fromAddr to set
	 */
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	/**
	 * @return the toAddr
	 */
	public String getToAddr() {
		return toAddr;
	}

	/**
	 * @param toAddr
	 *            the toAddr to set
	 */
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}

	/**
	 * @param transaction_id
	 *            the transaction_id to set
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the confirmation
	 */
	public String getConfirmation() {
		return confirmation;
	}

	/**
	 * @param confirmation
	 *            the confirmation to set
	 */
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	/**
	 * @return the raw_transaction
	 */
	public String getRaw_transaction() {
		return raw_transaction;
	}

	/**
	 * @param raw_transaction
	 *            the raw_transaction to set
	 */
	public void setRaw_transaction(String raw_transaction) {
		this.raw_transaction = raw_transaction;
	}

}
