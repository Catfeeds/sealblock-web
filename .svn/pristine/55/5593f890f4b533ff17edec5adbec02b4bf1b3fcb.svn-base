package com.woodare.sealblock;

import java.util.List;

import com.woodare.sealblock.data.SealblockAccountData;
import com.woodare.sealblock.data.TransactionData;
import com.woodare.sealblock.data.WalletData;
import com.woodare.sealblock.util.SealblockException;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.SubMerchant;

/**
 * @author Luke
 */
public interface IBlockApi {
	/**
	 * @param coin
	 *            货币标识, ETH
	 * @param blockAddr
	 *            SealBlock用户地址, 0x0f7bb0aef075feb963484b4838a3bf358d491ca8
	 * @return
	 * @throws SealblockException
	 */
	String createWallet(String coin, String blockAddr) throws SealblockException;

	/**
	 * @param blockAddr
	 *            SealBlock用户地址, 0x0f7bb0aef075feb963484b4838a3bf358d491ca8
	 * @return
	 * @throws SealblockException
	 */
	List<WalletData> listWallet(String blockAddr) throws SealblockException;

	/**
	 * @param invoice
	 * @param userData
	 * @return
	 * @throws SealblockException
	 */
	TransactionData sendTransaction(DownNoCardInvoice invoice, SubMerchant userData) throws SealblockException;

	/**
	 * @param coinType
	 * @param transactionId
	 * @return
	 * @throws SealblockException
	 */
	TransactionData getTransaction(DownNoCardInvoice invoice) throws SealblockException;

	/**
	 * @param coinType
	 * @param coinWalletAddress
	 * @return
	 * @throws SealblockException
	 */
	List<TransactionData> getTransactionHistory(String coinType, String coinWalletAddress) throws SealblockException;

	/**
	 * @param coinType
	 * @param coinWalletAddress
	 *            SealBlock用户地址, 0x0f7bb0aef075feb963484b4838a3bf358d491ca8
	 * @return 单位Wei
	 * @throws SealblockException
	 */
	String getBalance(String coinType, String coinWalletAddress) throws SealblockException;

	/**
	 * @param secret
	 * @return
	 * @throws SealblockException
	 */
	SealblockAccountData generateAccount(String secret) throws SealblockException;

	/**
	 * 更换用户加密密钥
	 * 
	 * @param prikey
	 * @param osecret
	 * @param nsecret
	 * @return
	 * @throws SealblockException
	 */
	String rebuildPrikey(String prikey, String osecret, String nsecret) throws SealblockException;

	/**
	 * 更换平台密钥
	 * 
	 * @param prikey
	 * @param secret
	 * @param osalt
	 * @param nsalt
	 * @return
	 * @throws SealblockException
	 */
	String rebuildPrikey(String prikey, String secret, String osalt, String nsalt) throws SealblockException;

	/**
	 * 
	 */
	void shutdown();
}
