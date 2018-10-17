package com.woodare.template.jersery.webservice.busi;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.exception.RollbackMessageWooException;
import com.woodare.template.constant.EnumError;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.webservice.busi.base.IFundAccountService;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.data.EnumOrderStatus;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;
import com.woodare.template.jpa.persistence.data.submerchantfundaccount.SubMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceDAO;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantFundAccountDAO;

/**
 * 账户处理服务类
 * 
 * @author Luke
 */
@Service(value = "fundAccountService")
public class FundAccountService implements IFundAccountService {
	private Log log = LogFactory.getLog(FundAccountService.class);

	@Autowired
	private IDownNoCardInvoiceDAO downNoCardInvoiceDAO;

	@Autowired
	private ISubMerchantFundAccountDAO subMerchantFundAccountDAO;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;
	//
	// @Autowired
	// private IDownMerchantBalanceDAO downMerchantBalanceDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DownNoCardInvoice settleInvoice(DownNoCardInvoice invoice, DownMerchantData merchantData) throws MessageWooException {
		try {
			log.info(String.format("InvoiceFundSettleBal[Start]%s->%s", invoice.getMchNo(), invoice.getTransNo()));

			DownMerchantData merchant = DownMerchants.getByMchNo(invoice.getMchNo());
			if (!SDFFactory.DATE.format(new Date()).equals(merchant.getSettleDate())) {
				throw new MessageWooException("正在进行日终跑批，请稍后再试", EnumError.ERR_8003);
			}

			// 交易金额
			BigDecimal priceAmt = invoice.getPrice();
			// 商户清算金额
			BigDecimal downRealPrice = invoice.getDownRealPrice();
			// // 机构交易手续费
			// BigDecimal merchantFee = invoice.getMerchantFee();
			// 代理商利润
			BigDecimal agtProfitAmt = invoice.getAgtProfitAmt();

			// 扣除用户账户余额
			SubMerchantFundAccountData diffModelUser = new SubMerchantFundAccountData();
			diffModelUser.setUserNo(invoice.getUserNo());
			diffModelUser.setCoin(invoice.getCoin());
			diffModelUser.setChangeDate(invoice.getTransDate());
			diffModelUser.setSettleOutAmt(priceAmt.multiply(new BigDecimal("-1")));

			// 修改用户状态
			int effectRow = subMerchantFundAccountDAO.changeBalance(diffModelUser);

			// 限定更新的条件
			DownNoCardInvoiceData conModel = new DownNoCardInvoiceData();
			conModel.setId(invoice.getId());
			conModel.setStatus(invoice.getStatus());

			DownNoCardInvoiceData newModel = new DownNoCardInvoiceData();
			// 收取用户账户余额失败
			if (effectRow == 0) {
				// 交易状态描述
				newModel.setStatus(EnumOrderStatus.FAIL);
				// 交易状态描述
				newModel.setStatusDesc("账户余额不足");
				// 状态更新时间
				newModel.setStatusChgDate(new Date());

				// 更新数据
				effectRow = this.downNoCardInvoiceDAO.updateSelectiveByCons(newModel, conModel);
			}
			// 收取用户账户余额成功
			else {
				// 交易状态描述
				newModel.setStatus(EnumOrderStatus.SUCCESS);
				// 交易状态描述
				newModel.setStatusDesc("交易成功");
				// 状态更新时间
				newModel.setStatusChgDate(new Date());

				// 交易状态描述
				newModel.setFundStatus(EnumOrderStatus.PROCESSING);
				// 交易状态描述
				newModel.setFundStatusDesc("处理中");
				// 状态更新时间
				newModel.setFundChgDate(new Date());

				// 更新数据
				effectRow = this.downNoCardInvoiceDAO.updateSelectiveByCons(newModel, conModel);
				if (effectRow == 0) {
					log.info(String.format("InvoiceFundSettleBal[ErrInv1]%s->%s", invoice.getMchNo(), invoice.getTransNo()));
					throw new RollbackMessageWooException("数据异常，请查证确认数据状态");
				}
			}

			// 计算机构与代理商分润
			if (EnumOrderStatus.SUCCESS.equals(newModel.getStatus())) {
				DownMerchantFundAccountData diffModel = new DownMerchantFundAccountData();
				diffModel.setCurInAmt(downRealPrice);
				diffModel.setCoin(invoice.getCoin());
				diffModel.setMchNo(invoice.getMchNo());
				diffModel.setChangeDate(invoice.getTransDate());
				effectRow = downMerchantFundAccountDAO.changeBalance(diffModel, merchantData.getCreditRatio());

				if (effectRow == 0) {
					log.info(String.format("InvoiceFundSettleBal[ErrMerc1]%s->%s", invoice.getMchNo(), invoice.getTransNo()));
					throw new RollbackMessageWooException("数据异常，请查证确认数据状态");
				}

				// 仅记录分润不为0的交易
				if (StringUtils.isNotEmpty(merchant.getAgentNo()) && agtProfitAmt.compareTo(new BigDecimal("0")) != 0) {
					// DownAgentData agent = DownAgents.getByCode(merchant.getAgentNo());
					DownMerchantFundAccountData diffAgentModel = new DownMerchantFundAccountData();
					diffAgentModel.setCurInAmt(downRealPrice);
					diffAgentModel.setCoin(invoice.getCoin());
					diffAgentModel.setMchNo(merchant.getAgentNo());
					diffModel.setChangeDate(invoice.getTransDate());
					effectRow = downMerchantFundAccountDAO.changeBalance(diffAgentModel, 100);
					if (effectRow == 0) {
						log.info(String.format("InvoiceFundSettleBal[ErrAgent1]%s->%s", invoice.getMchNo(), invoice.getTransNo()));
						throw new RollbackMessageWooException("数据异常，请查证确认数据状态");
					}
				}
			}
			log.info(String.format("InvoiceFundSettleBal[End]%s->%s", invoice.getMchNo(), invoice.getTransNo()));
			return downNoCardInvoiceDAO.findOne(invoice.getId());
		}
		// 回滚事务
		catch (RollbackMessageWooException e) {
			log.info(String.format("InvoiceFundSettleBal[Rollback]%s->%s", invoice.getMchNo(), invoice.getTransNo()));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new MessageWooException(e.getMessage());
		}
	}
}
