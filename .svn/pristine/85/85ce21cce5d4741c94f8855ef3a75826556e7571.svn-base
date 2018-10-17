package com.woodare.template.web.controller.content;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.jersey.IWebService;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.servicedata.downnocardinvoice.DownNoCardInvoiceServiceData;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.DownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.web.viewdata.downmerchantbalance.SearchDownMerchantBalanceViewData;

/**
 * @author lu_feng
 */
@Controller
@RequestMapping("/content/home")
public class ContentHomeController extends BaseController {
	private static Logger log = Logger.getLogger(ContentHomeController.class);

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/" })
	public ModelAndView index(SearchDownMerchantBalanceViewData searchData) throws ControllerException {
		List<DownMerchantFundAccount> fundAccounts = downMerchantFundAccountDAO.searchByMchNo(this.getUsername());
		DownMerchantData mch = DownMerchants.getByMchNo(this.getUsername());

		ModelAndView mav = new ModelAndView(getTemplate("/content/home"));
		mav.addObject("mch", mch);
		mav.addObject("fundAccounts", SaftyBeanUtils.cloneTo(fundAccounts, DownMerchantFundAccountData.class));
		mav.addObject("allowFlag", isAllowedManuallyAccount(this.getUsername()) ? "Y" : "N");

		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/directPay", method = RequestMethod.GET)
	public ModelAndView directPay() throws ControllerException {
		DownMerchantData mch = DownMerchants.getByMchNo(this.getUsername());
		ModelAndView mav = new ModelAndView(getTemplate("/content/home/directPay"));
		mav.addObject("mch", mch);
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/directPay", method = RequestMethod.POST)
	public ModelAndView directPay(DownNoCardInvoiceServiceData reqData) throws ControllerException, MessageWooException {
		DownMerchantData downMerchant = DownMerchants.getByMchNo(getUsername());

		if (!ContentHomeController.isAllowedManuallyAccount(this.getUsername())) {
			return this.alertFailed("/content/home/directPay", reqData, "未开通此权限");
		}
		// 商户号
		reqData.setMchNo(downMerchant.getMchNo());
		// 商户名
		reqData.setMchName(downMerchant.getName());
		// 支付方式
		// reqData.setPayType(reqData.getPayType());
		// 支付银行
		// reqData.setPayBankCode(reqData.getPayBankCode());
		// 交易金额
		// reqData.setPrice(reqData.getPrice());
		// 商品标题
		reqData.setSubject("面对面付款");
		// 下游交易流水号
		reqData.setTradeNo(SDFFactory.getLogId());
		// 订单时间, 格式：yyyyMMddHHmmss
		reqData.setOrderDate(SDFFactory.DATETIME.format(new Date()));
		// 同步回调地址
		reqData.setCallbackUrl("http://www.baidu.com");
		// 异步通知地址
		reqData.setNotifyUrl("http://www.baidu.com");

		String errMsg = null;
		DownNoCardInvoiceServiceData respData = null;
		String oriThreadName = Thread.currentThread().getName();
		try {
			String newThreadName = SDFFactory.getLogId();
			Thread.currentThread().setName(newThreadName);

			IWebService webService = (IWebService) ApplicationContextHolder.getApplicationContext().getBean("transNocardWebService");
			Method actionMethod = webService.getActionMethod("makeOrder");
			respData = (DownNoCardInvoiceServiceData) webService.executeWithNoTransaction(null, actionMethod, reqData);
			if (!"00".equals(respData.getStatus())) {
				errMsg = respData.getStatusDesc();
			}
			else {
				return new ModelAndView("redirect:" + respData.getPayUrl());
			}
		} catch (Exception e) {
			log.error(e, e);

			errMsg = e.getMessage();
		} finally {
			Thread.currentThread().setName(oriThreadName);
		}

		ModelAndView mav = new ModelAndView(getTemplate("/content/home/directPay"));
		mav.addObject("error", errMsg);
		mav.addObject("item", reqData);
		return mav;
	}

	public static boolean isAllowedManuallyAccount(String username) {
		String enabledMchNos = SysProperties.getInstance().getProperty("manual.operate.enable.mch", "");
		return Arrays.asList(enabledMchNos.split(",", -1)).contains(username);
	}
}
