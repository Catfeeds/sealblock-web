package com.thirdparty.passway.debit.aino.example;

/**
 * @author Luke
 */
public class TestSampleData {
	public static int TEST_CARD_00_LF = 0; // 测试环境专用
	public static int TEST_CARD_01_LF = 1;

	public static int TEST_CARD_CHOSEN_INDEX = 0;

	private static TestSampleData[] TESTCARDS = { 
			new TestSampleData("", "4367480059593577", "卢峰", 
			"18951812474", "033", "0822", "321323198606045338",
			// 报备下发信息
			"", "卢峰", "6217001370031080435", "建设银行", 
			"建设银行", "中国建设银行股份有限公司南京铁心桥分理处"),
		};

	/**
	 * @param idx
	 * @return
	 */
	public static TestSampleData testCard(int idx) {
		return TESTCARDS[idx];
	}

	/**
	 * @param bindId
	 * @param cardNo
	 * @param cardName
	 * @param mobile
	 * @param cvv
	 * @param validDate
	 * @param certId
	 * @param passwayMercNo
	 */
	public TestSampleData(String bindId, String cardNo, String cardName, String mobile, String cvv, String expiredDate, String certId, String passwayMercNo, String chargeCardName, String chargeCardNo, String payBankNm, String chargeBankNm,
			String chargeBankSubNm) {
		this.bindId = bindId;
		this.cardNo = cardNo;
		this.cardName = cardName;
		this.mobile = mobile;
		this.cvv = cvv;
		this.expiredDate = expiredDate;
		this.certId = certId;
		this.passwayMercNo = passwayMercNo;
		this.chargeCardName = chargeCardName;
		this.chargeCardNo = chargeCardNo;

		this.payBankNm = payBankNm;
		this.chargeBankNm = chargeBankNm;
		this.chargeBankSubNm = chargeBankSubNm;
	}

	public String bindId;
	public String cardNo;
	public String cardName;
	public String mobile;
	public String cvv;
	public String expiredDate;
	public String certId;
	public String passwayMercNo;

	public String chargeCardName;
	public String chargeCardNo;
	public String payBankNm;
	public String chargeBankNm;
	public String chargeBankSubNm;
}
