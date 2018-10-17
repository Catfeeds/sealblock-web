/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.template.jersery.webservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.woodare.core.base.AbstractBusiWebService;
import com.woodare.core.util.SDFFactory;
import com.woodare.core.util.ValidatorUtils;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.EnumError;
import com.woodare.template.egw.base.PasswayChannelVerifyHelper;
import com.woodare.template.helper.cache.DownAgents;
import com.woodare.template.helper.cache.DownMerchantFundAccounts;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.servicedata.downmerchant.DownMerchantServiceData;
import com.woodare.template.jersery.servicedata.downnocardinvoice.DownNoCardInvoiceServiceData;
import com.woodare.template.jersery.webservice.busi.DownNoCardInvoiceService;
import com.woodare.template.jersery.webservice.busi.base.IDownNoCardInvoiceService;
import com.woodare.template.jersery.webservice.utils.TransExpireSet;
import com.woodare.template.jersery.webservice.utils.ValidHelper;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.DownTradeLog;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumOrderStatus;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownTradeLogDAO;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantDAO;
import com.woodare.template.web.viewdata.downmerchant.DownMerchantViewData;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;

/**
 * ClassName: 数字货币快捷交易接口
 * 
 * @description 进行入参基本格式效验、拼装返回结果、共通数据库日志记录
 * @author luke
 * @Date 2018/10/17
 */
@Service
@Scope("request")
public class TransNocardWebService extends AbstractBusiWebService {
	private Log log = LogFactory.getLog(TransNocardWebService.class);

	private static TransExpireSet<String> uniquedIds = new TransExpireSet<String>(10);
	// private static TransExpireSet<String> transferIds = new TransExpireSet<String>(10);

	@Autowired
	private IDownNoCardInvoiceService downNoCardInvoiceService;
	//
	// @Autowired
	// private IDownMerchantDepositService downMerchantDepositService;

	@Autowired
	private IDownTradeLogDAO downTradeLogDAO;

	@Autowired
	private IDownNoCardInvoiceDAO downNoCardInvoiceDAO;
	//
	// @Autowired
	// private IDownMerchantDepositDAO downMerchantDepositDAO;

	@Autowired
	private ISubMerchantDAO subMerchantDAO;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	/**
	 * 下单支付
	 * 
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownNoCardInvoiceServiceData makeOrder(DownNoCardInvoiceServiceData reqData) throws Exception {
		Date startDate = new Date();
		DownNoCardInvoiceServiceData resp = null;
		try {
			// 【1】基本参数信息效验
			ValidHelper.notNull(reqData, "解密失败，请求参数为空", EnumError.ERR_9001);

			ValidHelper.notEmpty(reqData.getMchNo(), "机构号[mchNo]不能为空", EnumError.ERR_2001);
			if (reqData.getMchNo().length() > 20) {
				throw new MessageWooException("机构号[mchNo]值非法", EnumError.ERR_2001);
			}
			ValidHelper.notEmpty(reqData.getTradeNo(), "订单号[tradeNo]不能为空", EnumError.ERR_2001);
			if (reqData.getTradeNo().length() > 50) {
				throw new MessageWooException("订单号[tradeNo]超过允许最大长度50", EnumError.ERR_2001);
			}
			ValidHelper.notEmpty(reqData.getOrderDate(), "下单时间[orderDate]不能为空， 格式为yyyyMMddHHmmss", EnumError.ERR_2001);
			ValidHelper.validDate(reqData.getOrderDate(), SDFFactory.DATETIME, "下单时间[orderDate]格式不正确， 格式为yyyyMMddHHmmss", EnumError.ERR_2001);
			if (reqData.getNotifyUrl() != null) {
				if (reqData.getNotifyUrl().length() > 200) {
					throw new MessageWooException("异步回调地址[notifyUrl]超过允许最大长度200", EnumError.ERR_2001);
				}
				else if (!reqData.getNotifyUrl().startsWith("http://") && !reqData.getNotifyUrl().startsWith("https://")) {
					throw new MessageWooException("异步回调地址[notifyUrl]非http合法地址", EnumError.ERR_2001);
				}
			}
			// if (reqData.getCallbackUrl() != null) {
			// if (reqData.getCallbackUrl().length() > 200) {
			// throw new MessageWooException("同步回调地址[callbackUrl]超过允许最大长度200", EnumError.ERR_2001);
			// }
			// else if (!reqData.getCallbackUrl().startsWith("http://") && !reqData.getCallbackUrl().startsWith("https://")) {
			// throw new MessageWooException("同步回调地址[callbackUrl]非http合法地址", EnumError.ERR_2001);
			// }
			// }
			ValidHelper.notEmpty(reqData.getSubject(), "商品标题[subject]不能为空 ", EnumError.ERR_2001);
			if (reqData.getSubject().length() > 50) {
				throw new MessageWooException("商品标题[subject]超过允许最大长度50", EnumError.ERR_2001);
			}

			ValidHelper.notEmpty(reqData.getUserNo(), "平台用户标识[userNo]不能为空 ", EnumError.ERR_2001);

			ValidHelper.notEmpty(reqData.getCoin(), "货币类型[coin]不能为空 ", EnumError.ERR_2001);
			// 提取交易渠道信息
			PasswayRouteMerchantViewData coinItem = PasswayChannelVerifyHelper.getEnsureRouteValid(reqData);
			// 通道类别
			reqData.setChannel(coinItem.getChannel());
			// 币名称
			reqData.setCoinName(coinItem.getCoinName());
			// 价格精度
			int priceScale = coinItem.getPriceScale();

			if (!"dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
				if (uniquedIds.containsKey(reqData.getTradeNo() + reqData.getMchNo())) {
					throw new MessageWooException("相同订单号[tradeNo]并发请求，请确认后再试", EnumError.ERR_4003);
				}
			}

			// 【2】关联的机构权限信息效验
			DownMerchantData downMerchant = DownMerchants.getByMchNo(reqData.getMchNo());
			if (downMerchant == null) {
				throw new MessageWooException("机构号[mchNo]不存在或已停用", EnumError.ERR_3001);
			}
			else if (downMerchant.getStatus() != EnumDownUserStatus.ACTIVE) {
				throw new MessageWooException("机构号[mchNo]未启用", EnumError.ERR_3001);
			}

			if (!SDFFactory.DATE.format(new Date()).equals(downMerchant.getSettleDate())) {
				throw new MessageWooException("正在进行日终跑批，请稍后再试", EnumError.ERR_8003);
			}

			// 机构交易货币下的费率配置
			DownMerchantFundAccountData merchantFundData = DownMerchantFundAccounts.getByMchNoAndCoin(downMerchant.getMchNo(), coinItem.getCoin());
			if (merchantFundData == null || !EnumDownUserStatus.ACTIVE.equals(merchantFundData.getStatus())) {
				throw new MessageWooException("机构未开通[" + merchantFundData.getCoin() + "]交易", EnumError.ERR_3001);
			}

			SubMerchant subMerchant = this.subMerchantDAO.findByUserNo(reqData.getUserNo());
			// 用户不存在或所属机构身份不匹配
			if (subMerchant == null || !subMerchant.getMchNo().equals(downMerchant.getMchNo())) {
				throw new MessageWooException("用户不存在", EnumError.ERR_2004);
			}
			// 用户锁定
			else if (!EnumDownUserStatus.ACTIVE.equals(subMerchant.getStatus())) {
				throw new MessageWooException("用户状态不可用", EnumError.ERR_3002);
			}
			// 未开通当前交易货币
			else if (subMerchant.getEnabledCoin() == null || Arrays.asList(subMerchant.getEnabledCoin().split(",")).contains(coinItem.getCoin())) {
				throw new MessageWooException("用户未开通[" + merchantFundData.getCoin() + "]交易", EnumError.ERR_3003);
			}

			// 交易费率
			reqData.setFeeRatio(merchantFundData.getFeeRatio());
			// 单笔交易手续费
			reqData.setAddFeeAmt(merchantFundData.getAddFeeAmt());
			// 机构交易手续费
			BigDecimal merchantFee = reqData.getPrice().multiply(reqData.getFeeRatio()).divide(new BigDecimal("1000")).add(reqData.getAddFeeAmt()).setScale(priceScale, BigDecimal.ROUND_CEILING);
			merchantFee = merchantFee.add(reqData.getAddFeeAmt());
			reqData.setMerchantFee(merchantFee.setScale(priceScale, BigDecimal.ROUND_CEILING));
			// 机构清算金额
			BigDecimal downRealPrice = reqData.getPrice().subtract(merchantFee).setScale(priceScale, BigDecimal.ROUND_FLOOR);
			reqData.setDownRealPrice(downRealPrice);
			// 获取代理商
			DownAgentData agent = downMerchant.getAgentNo() != null ? DownAgents.getByCode(downMerchant.getAgentNo()) : null;
			// 代理商的接入成本费用（默认无代理，使用机构成本费用）
			BigDecimal agentFeeAmt = merchantFee;
			// 代理商利润
			BigDecimal agentProfitAmt = new BigDecimal("0");
			if (agent != null) {
				DownMerchantFundAccountData agentFundData = DownMerchantFundAccounts.getByMchNoAndCoin(agent.getAgentNo(), coinItem.getCoin());
				if (agentFundData != null) {
					agentFeeAmt = reqData.getPrice().multiply(agentFundData.getFeeRatio()).divide(new BigDecimal("1000")).add(agentFundData.getAddFeeAmt()).setScale(priceScale, BigDecimal.ROUND_CEILING);
					agentProfitAmt = merchantFee.subtract(agentFeeAmt);
				}
			}
			reqData.setAgtProfitAmt(agentProfitAmt);

			// 平台利润, 默认为代理商的支付成本
			BigDecimal profitAmt = agentFeeAmt;
			reqData.setProfit(profitAmt);

			// 设置并发拦截
			if (!"dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
				uniquedIds.add(reqData.getTradeNo() + reqData.getMchNo());
			}
			// 【验证原交易是否已存在】
			DownNoCardInvoice existedModel = this.downNoCardInvoiceDAO.findByTradeNoAndMchNo(reqData.getTradeNo(), reqData.getMchNo());
			if (existedModel != null) {
				throw new MessageWooException("订单号已存在，请确认是否重复下单", EnumError.ERR_4002);
			}

			// 效验累计交易额度
			DownMerchantFundAccount fundAccount = downMerchantFundAccountDAO.findByMchNoAndCoin(reqData.getMchNo(), reqData.getCoin());
			// 當日累記金額是否達到風控
			if (coinItem.getMaxPerAmtNum() != null) {
				if (reqData.getPrice().doubleValue() + fundAccount.getCurInAmt().doubleValue() >= coinItem.getMaxPerAmtNum().doubleValue()) {
					throw new MessageWooException("机构日交易额度超限", EnumError.ERR_2001);
				}
			}

			DownNoCardInvoice model = downNoCardInvoiceService.makeOrder(reqData, downMerchant, subMerchant);

			resp = DownNoCardInvoiceService.toServiceData(reqData, model);
		}
		//
		catch (Exception e) {
			saveToLogRecords(reqData, e, startDate);
		}
		return resp;
	}

	/**
	 * 重新发送短信
	 * 
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownNoCardInvoiceServiceData resendSms(DownNoCardInvoiceServiceData reqData) throws Exception {
		// 【1】基本参数信息效验
		ValidHelper.notNull(reqData, "解密失败，请求参数为空", EnumError.ERR_9001);

		if (StringUtils.isEmpty(reqData.getTransNo())) {
			throw new MessageWooException("平台订单号[transNo]不能为空", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getMchNo(), "机构号[mchNo]不能为空", EnumError.ERR_2001);
		if (reqData.getMchNo().length() > 20) {
			throw new MessageWooException("机构号[mchNo]值非法", EnumError.ERR_2001);
		}

		DownMerchantData downMerchant = DownMerchants.getByMchNo(reqData.getMchNo());
		if (downMerchant == null) {
			throw new MessageWooException("机构号[mchNo]不存在或已停用", EnumError.ERR_3001);
		}
		DownNoCardInvoice model = null;
		if (StringUtils.isNotEmpty(reqData.getTransNo())) {
			model = this.downNoCardInvoiceDAO.findByTransNo(reqData.getTransNo());
		}
		else if (StringUtils.isNotEmpty(reqData.getTradeNo())) {
			model = this.downNoCardInvoiceDAO.findByTradeNoAndMchNo(reqData.getTradeNo(), reqData.getMchNo());
		}
		if (model == null) {
			throw new MessageWooException("原交易不存在", EnumError.ERR_4001);
		}
		else {
			if (!EnumOrderStatus.PROCESSING.equals(model.getStatus())) {
				throw new MessageWooException("订单状态错误，请使用状态同步接口", EnumError.ERR_4005);
			}
			//
			else if (new Date().getTime() - model.getStatusChgDate().getTime() < 30) {
				throw new MessageWooException("发送验证码过于频繁", EnumError.ERR_4003);
			}
		}

		model = downNoCardInvoiceService.resendSms(reqData, model);

		return DownNoCardInvoiceService.toServiceData(reqData, model);
	}

	/**
	 * 确认交易
	 * 
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownNoCardInvoiceServiceData confirmOrder(DownNoCardInvoiceServiceData reqData) throws Exception {
		// 【1】基本参数信息效验
		ValidHelper.notNull(reqData, "解密失败，请求参数为空", EnumError.ERR_9001);

		if (StringUtils.isEmpty(reqData.getTransNo())) {
			throw new MessageWooException("平台订单号[transNo]不能为空", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getMchNo(), "机构号[mchNo]不能为空", EnumError.ERR_2001);
		if (reqData.getMchNo().length() > 20) {
			throw new MessageWooException("机构号[mchNo]值非法", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getSmsCode(), "短信验证码不能为空", EnumError.ERR_2001);
		if (ValidatorUtils.isNumber(reqData.getSmsCode())) {
			throw new MessageWooException("短信验证码不合法", EnumError.ERR_2001);
		}

		DownMerchantData downMerchant = DownMerchants.getByMchNo(reqData.getMchNo());
		if (downMerchant == null) {
			throw new MessageWooException("机构号[mchNo]不存在或已停用", EnumError.ERR_3001);
		}
		DownNoCardInvoice model = null;
		if (StringUtils.isNotEmpty(reqData.getTransNo())) {
			model = this.downNoCardInvoiceDAO.findByTransNo(reqData.getTransNo());
		}
		else if (StringUtils.isNotEmpty(reqData.getTradeNo())) {
			model = this.downNoCardInvoiceDAO.findByTradeNoAndMchNo(reqData.getTradeNo(), reqData.getMchNo());
		}
		if (model == null) {
			throw new MessageWooException("原交易不存在", EnumError.ERR_4001);
		}
		else {
			if (!EnumOrderStatus.PROCESSING.equals(model.getStatus())) {
				throw new MessageWooException("订单状态错误，请查证", EnumError.ERR_4005);
			}
		}

		model = downNoCardInvoiceService.confirmOrder(reqData, model);

		return DownNoCardInvoiceService.toServiceData(reqData, model);
	}

	/**
	 * 订单状态查询
	 * 
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownNoCardInvoiceServiceData orderQuery(DownNoCardInvoiceServiceData reqData) throws Exception {
		// 【1】基本参数信息效验
		ValidHelper.notNull(reqData, "解密失败，请求参数为空", EnumError.ERR_9001);

		if (StringUtils.isEmpty(reqData.getTransNo())) {
			throw new MessageWooException("平台订单号[transNo]不能为空", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getMchNo(), "机构号[mchNo]不能为空", EnumError.ERR_2001);
		if (reqData.getMchNo().length() > 20) {
			throw new MessageWooException("机构号[mchNo]值非法", EnumError.ERR_2001);
		}

		DownMerchantData downMerchant = DownMerchants.getByMchNo(reqData.getMchNo());
		if (downMerchant == null) {
			throw new MessageWooException("机构号[mchNo]不存在或已停用", EnumError.ERR_3001);
		}
		DownNoCardInvoice model = null;
		if (StringUtils.isNotEmpty(reqData.getTransNo())) {
			model = this.downNoCardInvoiceDAO.findByTransNo(reqData.getTransNo());
		}
		// else if (StringUtils.isNotEmpty(reqData.getTradeNo())) {
		// model = this.downNoCardInvoiceDAO.findByTradeNoAndMchNo(reqData.getTradeNo(), reqData.getMchNo());
		// }
		if (model == null || !model.getMchNo().equals(reqData.getMchNo())) {
			throw new MessageWooException("原交易不存在", EnumError.ERR_4001);
		}
		return DownNoCardInvoiceService.toServiceData(reqData, model);
	}

	/**
	 * 账户余额查询
	 * 
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownMerchantServiceData accBalQuery(DownMerchantServiceData reqData) throws Exception {
		// 【1】基本参数信息效验
		ValidHelper.notNull(reqData, "解密失败，请求参数为空", EnumError.ERR_9001);

		ValidHelper.notEmpty(reqData.getMchNo(), "机构号[mchNo]不能为空", EnumError.ERR_2001);
		if (reqData.getMchNo().length() > 20) {
			throw new MessageWooException("机构号[mchNo]值非法", EnumError.ERR_2001);
		}

		DownMerchantViewData downMerchant = DownMerchants.getByMchNo(reqData.getMchNo());
		if (downMerchant == null) {
			throw new MessageWooException("机构号[mchNo]不存在或已停用", EnumError.ERR_3001);
		}
		else if (downMerchant.getStatus() != EnumDownUserStatus.ACTIVE) {
			throw new MessageWooException("机构号[mchNo]未启用", EnumError.ERR_3001);
		}

		List<DownMerchantFundAccount> fundingModels = this.downMerchantFundAccountDAO.searchByMchNo(reqData.getMchNo());

		List<DownMerchantFundAccountData> coinBals = new ArrayList<>(16);
		for (DownMerchantFundAccount fundingModel : fundingModels) {
			if (downMerchant.getSupportCoins() != null && downMerchant.getSupportCoins().contains(fundingModel.getCoin())) {
				DownMerchantFundAccountData coinBal = new DownMerchantFundAccountData();
				coinBal.setCoin(fundingModel.getCoin());
				// respData.setCoinName(coinName);
				// coinBal.setLastSettleAmt(fundingModel.getLastSettleAmt());
				coinBal.setSettleInAmt(fundingModel.getSettleInAmt());
				coinBal.setSettleOutAmt(fundingModel.getSettleOutAmt());
				coinBal.setCurInAmt(fundingModel.getCurInAmt());
				coinBal.setCurOutAmt(fundingModel.getCurOutAmt());
				coinBal.setFrozenAmt(fundingModel.getFrozenAmt());

				coinBals.add(coinBal);
			}
		}

		DownMerchantServiceData respData = new DownMerchantServiceData();
		respData.setMchNo(downMerchant.getMchNo());
		respData.setMchName(downMerchant.getName());
		respData.setCoinBals(coinBals);

		return respData;
	}

	/**
	 * @param reqData
	 * @param respObj
	 * @throws Exception
	 */
	private void saveToLogRecords(DownNoCardInvoiceServiceData reqData, Exception respObj, Date startDate) throws Exception {
		Exception exception = respObj;
		if (exception != null) {
			try {
				String fulltime = SDFFactory.DATETIME.format(startDate);

				DownTradeLog tradeLog = new DownTradeLog();
				tradeLog.setThreadName(Thread.currentThread().getName());

				tradeLog.setCreateDate(startDate);
				tradeLog.setRecordDt(fulltime.substring(0, 8));
				tradeLog.setReceiveDtime(fulltime.substring(8));
				tradeLog.setReplyDtime(SDFFactory.TIME.format(new Date()));
				if (reqData.getChannel() != null) {
					tradeLog.setChannel(reqData.getChannel().toString());
				}
				tradeLog.setPrice(reqData.getPrice());
				tradeLog.setTradeNo(trimSaftyValue(reqData.getTradeNo(), 64));

				tradeLog.setMchNo(trimSaftyValue(reqData.getMchNo(), 50));
				DownMerchantData downMerchant = DownMerchants.getByMchNo(tradeLog.getMchNo());
				if (downMerchant != null) {
					tradeLog.setMchName(downMerchant.getName());
				}

				if (exception instanceof MessageWooException) {
					MessageWooException msgException = (MessageWooException) exception;
					tradeLog.setErrCode(msgException.getCode() + "");
					if (msgException.getDetailMessage() != null) {
						tradeLog.setErrMsg(trimSaftyValue(msgException.getDetailMessage(), 128));
					}
					else {
						tradeLog.setErrMsg(trimSaftyValue(msgException.getMessage(), 128));
					}
				}
				else {
					tradeLog.setErrCode("Error");
					tradeLog.setErrMsg(trimSaftyValue(StringUtils.isNotEmpty(exception.getMessage()) ? exception.getMessage() : exception.getClass().getSimpleName(), 128));
				}
				this.downTradeLogDAO.saveForce(tradeLog);
			} catch (Exception e) {
				log.error("IMPORTANT_ERR_LOG_ISSUE[]" + e.getMessage(), e);
			}
			throw exception;
		}
	}

	private String trimSaftyValue(String val, int maxLength) {
		if (val != null && val.length() > maxLength) {
			return val.substring(0, maxLength - 3) + "...";
		}
		return val;
	}
}
