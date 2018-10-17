package com.woodare.template.egw.debit;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.thirdparty.passway._data.PasswayRequestData;
import com.thirdparty.passway._data.PasswayResponseData;
import com.thirdparty.passway.debit.aino.AiNongApi;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.egw.base.AbstracPasswayEgw;
import com.woodare.template.egw.base.IPasswayAccountEgw;
import com.woodare.template.egw.base.IPasswayEgw;
import com.woodare.template.egw.base.IPasswayNotifyEgw;
import com.woodare.template.egw.base.IPasswayTransferEgw;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.persistence.data.downmerchantdeposit.DownMerchantDepositData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;
import com.woodare.template.jpa.persistence.data.passwayroutemerchant.PasswayRouteMerchantData;

/**
 * 爱农渠道
 * 
 * @author Luke
 */
@Service(value = "aiNongPasswayEgw")
public class AiNongPasswayEgw extends AbstracPasswayEgw implements IPasswayAccountEgw, IPasswayTransferEgw, IPasswayNotifyEgw, IPasswayEgw {
	private static AiNongApi api = new AiNongApi();

	/**
	 * H5下单功能
	 */
	@Override
	public PasswayResponseData makeOrder(DownNoCardInvoice invoice, DownNoCardInvoiceData viewData) throws AbstractWooException {
		PasswayRequestData reqData = new PasswayRequestData();
		reqData.setInvoice(invoice);
		reqData.setFrontUrl(this.getPayFrontUrl(invoice.getId()));
		reqData.setNotifyUrl(this.getPayNotifyUrl(invoice.getId()));
		return api.makeOrder(reqData);
	}

	/**
	 * 订单同步查询
	 */
	@Override
	public PasswayResponseData orderQuery(DownNoCardInvoice invoice) throws AbstractWooException {
		PasswayRequestData reqData = new PasswayRequestData();
		reqData.setInvoice(invoice);
		return api.orderQuery(reqData);	
	}

	/**
	 * 页面同步时，是否发起远程状态查询
	 */
	@Override
	public boolean requireRemoteQueryOnPaymentCheck() {
		return true;
	}

	/**
	 * 代付提交
	 */
	@Override
	public PasswayResponseData doTransferApply(DownMerchantDeposit deposit, DownMerchantDepositData viewData) throws AbstractWooException {
		PasswayRequestData reqData = new PasswayRequestData();
		reqData.setDeposit(deposit);
		return api.transferApply(reqData);	
	}

	/**
	 * 代付查证
	 */
	@Override
	public PasswayResponseData transferQuery(DownMerchantDeposit deposit) throws AbstractWooException {
		PasswayRequestData reqData = new PasswayRequestData();
		reqData.setDeposit(deposit);
		return api.transferQuery(reqData);	
	}
	
	/**
	 * 交易结果异步通知
	 */
	@Override
	public PasswayResponseData parseNotifyData(String postDataString, JSONObject getDataMap, DownNoCardInvoice invoice) {
		return api.parseNotifyData(postDataString, getDataMap, invoice);
	}

	/**
	 * 代付结果异步通知
	 */
	@Override
	public PasswayResponseData parseTransferNotifyData(String postDataString, JSONObject getDataMap, DownMerchantDeposit deposit) {
		return api.parseTransferNotifyData(postDataString, getDataMap, deposit);
	}

	@Override
	public PasswayResponseData doAccQuery(PasswayRouteMerchantData merchant) throws AbstractWooException {
		PasswayRequestData req = new PasswayRequestData();
		
		DownNoCardInvoice invoice = new DownNoCardInvoice();
		invoice.setChannel(merchant.getChannel());
		req.setInvoice(invoice);
		
		PasswayResponseData resp = api.accBalQuery(req);

		if (EnumPasswayOrderStatus.S.equals(resp.getStatus())) {
			System.out.println("余额查询成功");
			
			JSONObject jsonObj = (JSONObject)resp.getExtraObject();
			
			PasswayRouteMerchantData merchantResp = new PasswayRouteMerchantData();
			// merchantResp.setBalanceAmt(Long.parseLong(jsonObj.getString("balance")));
			// merchantResp.setSettleAmt(Long.parseLong(jsonObj.getString("curInAmt")));
			// merchantResp.setCreditAmt(Long.parseLong(jsonObj.getString("creditLines")));
			// merchantResp.setFrozenAmt(Long.parseLong(jsonObj.getString("frozenAmt")));
			// merchantResp.setCurOutAmt(Long.parseLong(jsonObj.getString("curOutAmt")));
			
			resp.setExtraObject(merchantResp);
		}
		
		return resp;
	}
}
