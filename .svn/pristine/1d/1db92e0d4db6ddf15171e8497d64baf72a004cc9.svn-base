package com.woodare.sealblock;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woodare.framework.utils.SysProperties;
import com.woodare.sealblock.data.SealblockAccountData;
import com.woodare.sealblock.data.TransactionData;
import com.woodare.sealblock.data.WalletData;
import com.woodare.sealblock.util.SealAesUtils;
import com.woodare.sealblock.util.SealMD5Tool;
import com.woodare.sealblock.util.SealblockClient;
import com.woodare.sealblock.util.SealblockException;
import com.woodare.sealblock.util.SealblockRequestException;
import com.woodare.sealblock.util.Tool;
import com.woodare.template.helper.cache.PasswayRouteMerchants;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;
import com.woodare.template.web.viewdata.passwayroutemerchant.SealblockCoinData;

/**
 * Sealblock对接服务工具类
 * 
 * @author Luke
 */
public class SealblockApi implements IBlockApi {
	private String salt;
	private String address;

	public SealblockApi() {
		salt = SysProperties.getInstance().getProperty("sealblock.service.salt", "_LOVE$_$FISH_");
		address = SysProperties.getInstance().getProperty("sealblock.service.address", "tcp://43.254.45.31:4242");
	}

	public String createWallet(String coin, String blockAddr) throws SealblockException {
		String tagId = System.currentTimeMillis() + "|" + blockAddr;
		if (blockAddr.startsWith("0x")) {
			blockAddr = blockAddr.substring(2);
		}
		List<String> params = Arrays.asList(new String[] { blockAddr, coin });
		Object respData = SealblockClient.invoke(address, tagId, "create_wallet", params);

		return (String) respData;
	}

	public List<WalletData> listWallet(String blockAddr) throws SealblockException {
		String tagId = System.currentTimeMillis() + "|" + blockAddr;

		if (blockAddr.startsWith("0x")) {
			blockAddr = blockAddr.substring(2);
		}

		List<String> params = Arrays.asList(new String[] { blockAddr });
		Object respData = SealblockClient.invoke(address, tagId, "list_wallet", params);

		String data = JSON.toJSONString(respData);
		List<WalletData> wallets = JSON.parseArray(data, WalletData.class);
		//
		// for (WalletData wallet : wallets) {
		// log.info(wallet.getCoinType() + "=" + wallet.getAddr());
		// }
		return wallets;
	}

	public TransactionData sendTransaction(DownNoCardInvoice invoice, SubMerchant userData) throws SealblockException {
		String tagId = System.currentTimeMillis() + "|" + invoice.getTransNo();

		PasswayRouteMerchantViewData coinData = PasswayRouteMerchants.getByCoin(invoice.getCoin());

		SealblockCoinData sealblock = coinData.getSealblock();

		JSONObject reqData = new JSONObject();
		// 交易备注
		reqData.put("prefix", sealblock.getPrefix());
		// 货币标识，来自底层配置要求
		reqData.put("txtype", sealblock.getTxType());

		// 计算货币单位下金额数字
		String weiAmount = invoice.getPrice().multiply(BigDecimal.TEN.pow(Integer.valueOf(sealblock.getDecimals()))).toBigInteger().toString();
		// 交易金额
		reqData.put("weiAmount", weiAmount);
		// 用户钱包地址
		reqData.put("sender", invoice.getUserCoinAddr());
		// 机构钱包地址
		reqData.put("receiv", invoice.getMercCoinAddr());

		String signdata = null;

		try {
			signdata = makeTransactionData(userData.getAddress(), userData.getPrikey(), userData.getGoogleSecret(), reqData);
		}
		//
		catch (Exception e) {
			throw new SealblockRequestException(e.getMessage(), e);
		}

		List<String> params = Arrays.asList(new String[] { invoice.getCoin(), signdata, invoice.getTransNo() });

		String upTransNo = (String) SealblockClient.invoke(address, tagId, "send_transaction", params, SealblockClient.TRANSACTION_TIMEOUT);

		TransactionData respInvData = new TransactionData();
		respInvData.setTransaction_id(upTransNo);
		return respInvData;
	}

	public TransactionData getTransaction(DownNoCardInvoice invoice) throws SealblockException {
		String tagId = System.currentTimeMillis() + "|" + invoice.getTransNo();

		TransactionData transaction = null;
		if (StringUtils.isNotEmpty(invoice.getUpTransNo())) {
			List<String> params = Arrays.asList(new String[] { invoice.getUpTransNo(), invoice.getCoin() });

			Object respData = SealblockClient.invoke(address, tagId, "get_transaction", params, SealblockClient.TRANSACTION_TIMEOUT);

			String data = JSON.toJSONString(respData);
			transaction = JSON.parseObject(data, TransactionData.class);
		}
		else {
			// ） get_transaction_by_reference(reference) 其中 reference为客户端传入，返回值为transaction
			List<String> params = Arrays.asList(new String[] { invoice.getTransNo() });

			Object respData = SealblockClient.invoke(address, tagId, "get_transaction_by_reference", params, SealblockClient.TRANSACTION_TIMEOUT);

			String data = JSON.toJSONString(respData);
			transaction = JSON.parseObject(data, TransactionData.class);

		}

		return transaction;
	}

	// 2）get_transaction_history: function(addr, cointype) 其中addr为目标钱包地址，cointype 为网络类型
	public List<TransactionData> getTransactionHistory(String coinType, String coinWalletAddress) throws SealblockException {
		String tagId = System.currentTimeMillis() + "|" + coinType + "_" + coinWalletAddress;
		if (coinWalletAddress.startsWith("0x")) {
			coinWalletAddress = coinWalletAddress.substring(2);
		}
		List<TransactionData> transaction = null;
		List<String> params = Arrays.asList(new String[] { coinWalletAddress, coinType });

		Object respData = SealblockClient.invoke(address, tagId, "get_transaction_history", params, SealblockClient.TRANSACTION_TIMEOUT);

		String data = JSON.toJSONString(respData);
		transaction = JSON.parseArray(data, TransactionData.class);

		return transaction;
	}

	// 2）get_transaction_history: function(addr, cointype) 其中addr为目标钱包地址，cointype 为网络类型

	public String getBalance(String coinType, String coinWalletAddress) throws SealblockException {
		String tagId = System.currentTimeMillis() + "|" + coinType + "_" + coinWalletAddress;
		if (coinWalletAddress.startsWith("0x")) {
			coinWalletAddress = coinWalletAddress.substring(2);
		}
		List<String> params = Arrays.asList(new String[] { coinWalletAddress, coinType });

		Object respData = SealblockClient.invoke(address, tagId, "get_balance", params, SealblockClient.TRANSACTION_TIMEOUT);

		return (String) respData;
	}

	/**
	 * @param secret
	 * @return
	 * @throws SealblockException
	 */
	public SealblockAccountData generateAccount(String secret) throws SealblockException {
		try {
			ECKeyPair ecKeyPair = Keys.createEcKeyPair();
			BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
			String sPrivatekeyInHex = privateKeyInDec.toString(16);

			String key = SealMD5Tool.md5(secret + salt);
			WalletFile aWallet = Wallet.createLight(key, ecKeyPair);
			String sAddress = aWallet.getAddress();

			SealblockAccountData blockAccount = new SealblockAccountData();

			blockAccount.setBlockAddr(sAddress);
			blockAccount.setPrikey(SealAesUtils.encrypt(sPrivatekeyInHex, key));

			return blockAccount;
		}
		//
		catch (Exception e) {
			throw new SealblockException(e.getMessage(), e);
		}
	}

	/**
	 * 更换用户加密密钥
	 * 
	 * @param prikey
	 * @param osecret
	 * @param nsecret
	 * @return
	 * @throws SealblockException
	 */
	public String rebuildPrikey(String prikey, String osecret, String nsecret) throws SealblockException {
		try {
			String okey = SealMD5Tool.md5(osecret + salt);
			String nkey = SealMD5Tool.md5(nsecret + salt);

			String plainkey = SealAesUtils.decrypt(prikey, okey);

			return SealAesUtils.encrypt(plainkey, nkey);
		} catch (Exception e) {
			throw new SealblockException(e.getMessage(), e);
		}
	}

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
	public String rebuildPrikey(String prikey, String secret, String osalt, String nsalt) throws SealblockException {
		try {
			String okey = SealMD5Tool.md5(secret + osalt);
			String nkey = SealMD5Tool.md5(secret + nsalt);

			return SealAesUtils.encrypt(SealAesUtils.decrypt(prikey, okey), nkey);
		} catch (Exception e) {
			throw new SealblockException(e.getMessage(), e);
		}
	}

	/**
	 * @param blockAddr
	 * @param prikey
	 * @param reqData
	 * @return
	 * @throws SealblockException
	 */
	private String makeTransactionData(String blockAddr, String prikey, String secret, JSONObject reqData) throws Exception {
		String key = SealMD5Tool.md5(secret + salt);
		prikey = SealAesUtils.decrypt(prikey, key);

		System.out.println("Prikey: " + prikey);
		// String prefix = "Ethereum Signed Message:\n";
		// 交易备注
		String prefix = reqData.getString("prefix");
		// 货币标识，来自底层配置要求
		String txtype = reqData.getString("txtype");
		// 金额
		String weiAmount = reqData.getString("weiAmount");
		// 转出钱包地址
		String sender = reqData.getString("sender");
		// 转入钱包地址
		String receiv = reqData.getString("receiv");

		// 生成随机数
		String nonce = String.valueOf(new Date().getTime());
		while (nonce.length() < 16) {
			nonce = nonce + (int) (Math.random() * 9);
		}

		// 拼接加密内容
		String data = String.format("%s:%s:%s:%s:%s", txtype, sender, receiv, weiAmount, nonce).toLowerCase();
		String message = prefix + "\n" + data.length() + data;

		System.out.println("message: " + message);

		// 计算签名数据
		byte[] messageBytes = message.getBytes();

		byte[] x = Tool.hexStringToByteArray("19");
		byte[] px = new byte[x.length + messageBytes.length];

		System.arraycopy(x, 0, px, 0, x.length);
		System.arraycopy(messageBytes, 0, px, x.length, messageBytes.length);

		Credentials credentials = Credentials.create(prikey);
		Sign.SignatureData signature = Sign.signMessage(px, credentials.getEcKeyPair());

		byte[] bytes = new byte[signature.getR().length + signature.getS().length + 1];
		System.arraycopy(signature.getR(), 0, bytes, 0, signature.getR().length);
		System.arraycopy(signature.getS(), 0, bytes, signature.getR().length, signature.getS().length);
		bytes[bytes.length - 1] = signature.getV();
		String signedValue = Numeric.toHexString(bytes);
		System.out.println("signedValue: " + signedValue);
		// signedValue = signedValue.substring(2);

		return String.format("%s|%s|%s|%s|%s|%s|%s", txtype, sender, receiv, weiAmount, nonce, blockAddr, signedValue);
	}

	@Override
	public void shutdown() {

	}
}
