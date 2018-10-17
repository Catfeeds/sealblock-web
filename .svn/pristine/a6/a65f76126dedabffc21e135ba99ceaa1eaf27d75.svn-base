package com.woodare.template.jersery.webservice.busi.base;

import com.woodare.template.jersery.servicedata.downmerchantdeposit.DownMerchantDepositServiceData;
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * 机构代付业务处理服务
 * 
 * @author Luke
 */
public interface IDownMerchantDepositService {
	/**
	 * 机构余额查询
	 * 
	 * @param deposit
	 * @return
	 * @throws Exception
	 */
	DownMerchant orgBalQuery(DownMerchantDeposit deposit) throws Exception;

	/**
	 * 代付提交
	 * 
	 * @param reqData
	 * @param downMerchant
	 * @param agent
	 * @return
	 * @throws Exception
	 */
	DownMerchantDeposit transferApply(DownMerchantDepositServiceData reqData, DownMerchantData downMerchant, DownAgentData agent) throws Exception;

	/**
	 * 代付结果查询
	 * 
	 * @param deposit
	 * @param refreshFlag
	 *            刷新返回最新交易数据
	 * @return
	 * @throws Exception
	 */
	DownMerchantDeposit transferQuery(DownMerchantDeposit deposit, boolean refreshFlag) throws Exception;
}
