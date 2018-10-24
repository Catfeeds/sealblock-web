package com.woodare.template.component.schedule;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.sealblock.data.TransactionData;
import com.woodare.template.busi.coin.ICoinService;
import com.woodare.template.egw.base.IPasswayEgw;
import com.woodare.template.helper.cache.CoinAddrs;
import com.woodare.template.jpa.model.SubMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.submerchantfundaccount.SubMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantFundAccountDAO;

/**
 * @author Luke
 */
@Service
public class DelayLoadCacheSchedule {
	private static Logger log = Logger.getLogger(DelayLoadCacheSchedule.class);

	@Autowired
	private ISubMerchantFundAccountDAO subMerchantFundAccountDAO;

	private String lastLoadSubMerchantDate = null;

	@Autowired
	@Qualifier(value = "sealblockPasswayEgw")
	private IPasswayEgw sealblockPasswayEgw;

	@Autowired
	@Qualifier(value = "ethereumCoinService")
	private ICoinService ethereumCoinService;

	/**
	 * 延迟一分钟启动装载
	 */
	@Scheduled(fixedDelay = 30 * 1000, initialDelay = 20 * 1000)
	public void deplayLoadSubMerchantFundData() {
		String nowDate = SDFFactory.DATE.format(new Date());
		if (lastLoadSubMerchantDate == null || lastLoadSubMerchantDate.compareTo(nowDate) < 0) {
			List<SubMerchantFundAccount> fundAccounts = subMerchantFundAccountDAO.findCoinAndAddress();
			CoinAddrs.resetAll(fundAccounts);

			lastLoadSubMerchantDate = nowDate;
		}
	}

	/**
	 * 每1分钟执行一次
	 */
	@Scheduled(fixedDelay = 1 * 60 * 1000, initialDelay = 20 * 1000)
	public void syncFundAccountRecords() {
		if (lastLoadSubMerchantDate != null) {
			// TODO: 如何避免频繁进行数据库比对& 优化遍历查询的策略
			List<SubMerchantFundAccountData> fundAccounts = CoinAddrs.getAllFunds();
			for (SubMerchantFundAccountData fund : fundAccounts) {
				try {
					List<TransactionData> transactions = sealblockPasswayEgw.getTransactionHistory(fund.getCoin(), fund.getAddress());
					if (transactions != null) {
						for (TransactionData transaction : transactions) {
							boolean isChargeFlag = fund.getAddress().equals(transaction.getFromAddr()) ? true : false;
							ethereumCoinService.approveTrans(transaction, isChargeFlag);
						}
					}
				} catch (MessageWooException e) {
					log.error("Err[" + fund.getUserNo() + "/" + fund.getCoin() + ", " + fund.getAddress() + "]" + e.getMessage());
				}
			}
		}
	}
}
