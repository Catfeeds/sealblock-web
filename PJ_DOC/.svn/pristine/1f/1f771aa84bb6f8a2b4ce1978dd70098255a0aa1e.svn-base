

import org.json.JSONObject;
import org.json.JSONException;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.utils.Numeric;


import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class SealBlockWallet {
	private final String NOT_EXIST = "NOT_EXIST";
	private final String KEY_PREFIX = "SKWALLET_";
	private JSONObject walletJSON;
	private JSONObject coinConfigJSON;
	private SharedPreferences sharedPreferences;

	public SealBlockWallet(Context context) throws JSONException {
        sharedPreferences = context.getSharedPreferences("SK", Context.MODE_PRIVATE);
		walletJSON = null;
		coinConfigJSON = loadJsonFromPreferences("coinConfigJSON");
		if (coinConfigJSON == null) {
			coinConfigJSON = new JSONObject();
			coinConfigJSON.put("ETH_txType","0000000000000000000000000000000000000000");
			coinConfigJSON.put("ETH_decimal",18);
		}
    }
	public boolean loadWallet(String account_id,boolean create_if_not_exist) throws Exception {
		String KEY = KEY_PREFIX+account_id;
		walletJSON = loadJsonFromPreferences(KEY);
		if (walletJSON == null) {
			if (create_if_not_exist) {
				//create wallet
				walletJSON = createWallet(KEY);
				//save walletJSON to permanent storage
				saveJsonToPreferences(KEY,walletJSON);
			} else {
				throw new Exception("Wallet Not Exist");
			}
		}
		return true;
	}
    public boolean loadWallet(String account_id) throws Exception {
        String KEY = KEY_PREFIX+account_id;
        walletJSON = loadJsonFromPreferences(KEY);
        if (walletJSON == null) {
            //create wallet
            walletJSON = createWallet(KEY);
            //save walletJSON to permanent storage
            saveJsonToPreferences(KEY,walletJSON);
        }
        return true;
    }



	private void saveJsonToPreferences(String key,JSONObject value) {
		SharedPreferences.Editor edit = sharedPreferences.edit();
		edit.putString(key, value.toString());
		edit.commit();
	}

	private JSONObject loadJsonFromPreferences(String key) throws JSONException {
		String value = sharedPreferences.getString(key,NOT_EXIST);
		JSONObject result = null;
		if (value != NOT_EXIST) {
			result = new JSONObject(value);
		}
		return result;
	}

	public String getApproverAddress() throws JSONException {
		return walletJSON.get("approverAddress").toString();
	}

	public void updateCoinConfig(JSONObject coinConfigJSON) throws JSONException {
		String key = "coinConfigJSON";
		saveJsonToPreferences(key,coinConfigJSON);
		coinConfigJSON = loadJsonFromPreferences(key);
	}
	
	public JSONObject approveTransaction(String coinType, String fromAddr, String toAddr, float amount) throws JSONException {
		JSONObject result = new JSONObject();
		String txType = coinConfigJSON.getString(coinType+"_txType");
		int decimal = coinConfigJSON.getInt(coinType+"_decimal");
//		BigDecimal amount_ex = BigDecimal.valueOf(amount * 10 ^ decimal);
		BigDecimal amount_ex = BigDecimal.valueOf(Math.pow(amount * 10, decimal));
		String approver = getApproverAddress();

        String nonce = String.valueOf(new Date().getTime());
        while (nonce.length() < 16) {
            nonce = nonce + (int) (Math.random() * 9);
        }

		//construct message for signing
        String prefix = "Ethereum Signed Message:\n";
		String amountStr = amount_ex.toPlainString();
        String message = txType + ':' + fromAddr + ':' + toAddr + ':' + amountStr + ':' + nonce;
        String padded_message = prefix + message.length() + message;
        String params = padded_message;
        byte[] paramsByte = params.toString().getBytes();
        byte[] x = Tool.hexStringToByteArray("19");
        byte[] px = new byte[x.length + paramsByte.length];
        System.arraycopy(x, 0, px, 0, x.length);
        System.arraycopy(paramsByte, 0, px, x.length, paramsByte.length);

        String privateKey = walletJSON.getString("approverKey");

		//sign the message
        Credentials credentials = Credentials.create(privateKey);
//        Credentials credentials = Credentials.create(localPrivateKey);
        Sign.SignatureData signature = Sign.signMessage(px, credentials.getEcKeyPair());
        byte[] bytes = new byte[signature.getR().length + signature.getS().length + 1];

		//get signature
        System.arraycopy(signature.getR(), 0, bytes, 0, signature.getR().length);
        System.arraycopy(signature.getS(), 0, bytes, signature.getR().length, signature.getS().length);
        bytes[bytes.length - 1] = signature.getV();

        String signed_approval = txType + '|' + fromAddr + '|' + toAddr + '|' + amountStr + '|' + nonce + '|' + approver + '|' + Numeric.toHexString(bytes);
        result.put("signed_approval",signed_approval);
		return result;
	}

    private JSONObject createWallet(String seed) throws Exception {
        JSONObject processJson = new JSONObject();
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
		String sPrivatekeyInHex = privateKeyInDec.toString(16);
		WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
		String sAddress = aWallet.getAddress();

		processJson.put("approverAddress", "0x" + sAddress);
		processJson.put("approverKey", sPrivatekeyInHex);

        return processJson;
    }
}
