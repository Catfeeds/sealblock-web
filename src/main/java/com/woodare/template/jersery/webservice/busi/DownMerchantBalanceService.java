package com.woodare.template.jersery.webservice.busi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thirdparty.passway._data.PasswayResponseData;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.template.jersery.webservice.busi.base.IDownMerchantBalanceService;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;

/**
 * @author Luke
 */
@Service(value = "downMerchantBalanceService")
public class DownMerchantBalanceService implements IDownMerchantBalanceService {

    // @Autowired
    // private ISubMerchantFundAccountDAO subMerchantFundAccountDAO;

	@Autowired
	private IDownMerchantDAO downMerchantDAO;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Override
	public boolean recordInvoice(DownNoCardInvoice invoice, int direction, String remark) throws MessageWooException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recordDeposit(DownMerchantDeposit deposit, int direction, String remark) throws MessageWooException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DownMerchantDeposit handleDepositResult(DownMerchantDeposit oriModel, PasswayResponseData respData) throws MessageWooException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DownNoCardInvoice handleInvoiceOrderResult(DownNoCardInvoice oriModel, PasswayResponseData respData) throws MessageWooException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean recordSettle(String settleDate) throws Exception {
		downMerchantFundAccountDAO.makeSettle(settleDate);
		downMerchantDAO.finishSettle(settleDate);

		// TODO: 将当前统计数据存入历史表数据

		return false;
	}

}
