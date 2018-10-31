package com.woodare.template.jersery.webservice;

import java.awt.print.PrinterIOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.woodare.core.base.AbstractBusiWebService;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.EnumError;
import com.woodare.template.egw.base.PasswayChannelVerifyHelper;
import com.woodare.template.helper.cache.DownMerchantFundAccounts;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.servicedata.downnocardinvoice.DownNoCardInvoiceServiceData;
import com.woodare.template.jersery.webservice.busi.DownNoCardInvoiceService;
import com.woodare.template.jersery.webservice.busi.base.IDownNoCardInvoiceService;
import com.woodare.template.jersery.webservice.utils.TransExpireSet;
import com.woodare.template.jersery.webservice.utils.ValidHelper;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.DownTradeLog;
import com.woodare.template.jpa.model.SubMerchant;
import com.woodare.template.jpa.model.SubMerchantFundAccount;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumOrderStatus;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownTradeLogDAO;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantDAO;
import com.woodare.template.jpa.persistence.persistence.ISubMerchantFundAccountDAO;
import com.woodare.template.web.viewdata.passwayroutemerchant.PasswayRouteMerchantViewData;

/**
 * ClassName: 数字货币转账接口
 * 
 * @author luke
 * @Date 2018/10/31
 */
@Service
@Scope("request")
public class TransBalanceTransferWebService extends AbstractBusiWebService{
	private Log log = LogFactory.getLog(TransNocardWebService.class);

	private static TransExpireSet<String> uniquedIds = new TransExpireSet<String>(10);
	
	@Autowired
	private ISubMerchantDAO subMerchantDAO;
	
	@Autowired
	private ISubMerchantFundAccountDAO subMerchantFundAccountDAO;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;
	
	@Autowired
	private IDownNoCardInvoiceDAO downNoCardInvoiceDAO;
	
	@Autowired
	private IDownNoCardInvoiceService downNoCardInvoiceService;
	
	@Autowired
	private IDownTradeLogDAO downTradeLogDAO;
	
	
	/**
	 * 	转账交易
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownNoCardInvoiceServiceData Transfer(DownNoCardInvoiceServiceData reqData)throws Exception {
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
			ValidHelper.notEmpty(reqData.getSubject(), "交易备注信息 [subject]不能为空 ", EnumError.ERR_2001);
			if (reqData.getSubject().length() > 50) {
				throw new MessageWooException("交易备注信息[subject]超过允许最大长度50", EnumError.ERR_2001);
			}
			
			ValidHelper.notEmpty(reqData.getUserNo(), "平台用户标识[userNo]不能为空 ", EnumError.ERR_2001);
			
			ValidHelper.notEmpty(reqData.getCoin(), "货币类型[coin]不能为空 ", EnumError.ERR_2001);
			
			ValidHelper.notEmpty(reqData.getUserCoinAddr(), "下游接收方货币地址[userCoinAddr]不能为空", EnumError.ERR_2001);
			
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
				throw new MessageWooException("机构未开通[" + coinItem.getCoin() + "]交易", EnumError.ERR_3001);
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
			else if (subMerchant.getEnabledCoin() == null || !Arrays.asList(subMerchant.getEnabledCoin().split(",")).contains(coinItem.getCoin())) {
				throw new MessageWooException("用户未开通[" + merchantFundData.getCoin() + "]交易", EnumError.ERR_3003);
			}
			
			
			// 下游用户收款地址
			reqData.setMercCoinAddr(reqData.getUserCoinAddr());
			
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

			SubMerchantFundAccount userFundAccount = subMerchantFundAccountDAO.findByUserNoAndCoin(reqData.getUserNo(), reqData.getCoin());
			if (userFundAccount.getSettleInAmt().subtract(userFundAccount.getSettleOutAmt()).subtract(userFundAccount.getFrozenAmt()).compareTo(reqData.getPrice()) < 0) {
				throw new MessageWooException("账户余额不足", EnumError.ERR_4004);
			}
			reqData.setUserCoinAddr(userFundAccount.getAddress());

			DownNoCardInvoice model = downNoCardInvoiceService.makeOrder(reqData, downMerchant, subMerchant);

			resp = DownNoCardInvoiceService.toServiceData(reqData, model);
			// 处理中，判定为发送短信成功
			if (EnumOrderStatus.PROCESSING.equals(resp.getStatus())) {
				resp.setStatus(EnumOrderStatus.SUCCESS);
			}
			// 其他统一当作发送失败处理
			else {
				resp.setStatus(EnumOrderStatus.FAIL);
			}
			return resp;
		}
		//
		catch (Exception e) {
			throw processException(reqData, e, startDate);
		}
	}
	
	
	
	/**
	 * @param reqData
	 * @param respObj
	 * @throws Exception
	 */
	private Exception processException(DownNoCardInvoiceServiceData reqData, Exception respObj, Date startDate) throws Exception {
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
		}
		return exception;
	}

	private String trimSaftyValue(String val, int maxLength) {
		if (val != null && val.length() > maxLength) {
			return val.substring(0, maxLength - 3) + "...";
		}
		return val;
	}
}
