package com.woodare.template.busi.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdparty.passway._base.IPasswayApi.EnumPasswayOrderStatus;
import com.thirdparty.passway._data.PasswayRequestData;
import com.thirdparty.passway._data.PasswayResponseData;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.template.component.job.ITaskJob;
import com.woodare.template.component.job.SimpleTaskJob;
import com.woodare.template.egw.base.IPasswayEgw;
import com.woodare.template.egw.base.PasswayEgwHelper;
import com.woodare.template.jpa.model.DownNoCardInvoice;
import com.woodare.template.jpa.model.DownNoCardInvoiceExtra;
import com.woodare.template.jpa.persistence.data.downnocardinvoice.DownNoCardInvoiceData;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownNoCardInvoiceExtraDAO;

/**
 * @author Luke
 */
@Service("passwayAssitService")
public class PasswayAssitService implements ITaskJob {
	private Log log = LogFactory.getLog(PasswayAssitService.class);
	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);

	/**
	 * @param obj
	 */
	public static void asyncExecute(Object obj) {
		fixedThreadPool.execute(new SimpleTaskJob("passwayAssitService", obj));
	}

	@Autowired
	private IDownNoCardInvoiceDAO downNoCardInvoiceDAO;

	@Autowired
	private IDownNoCardInvoiceExtraDAO downNoCardInvoiceExtraDAO;

	@Override
	public void executeTask(Object obj) {
		String oriThreadName = Thread.currentThread().getName();
		Thread.currentThread().setName(SDFFactory.getNotifyId());
		try {
			if (obj instanceof DownNoCardInvoiceExtra) {
				DownNoCardInvoiceExtra invoiceExtra = (DownNoCardInvoiceExtra) obj;
				handleDownNoCardInvoiceExtra(invoiceExtra);
			}
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			Thread.currentThread().setName(oriThreadName);
		}
	}

	/**
	 * @param extra
	 */
	private void handleDownNoCardInvoiceExtra(DownNoCardInvoiceExtra extra) {
		DownNoCardInvoice oriModel = downNoCardInvoiceDAO.findOne(extra.getId());

		PasswayResponseData respData = new PasswayResponseData();

		PasswayRequestData reqData = new PasswayRequestData();
		reqData.setInvoice(oriModel);
		try {
			respData = PasswayEgwHelper.getService(extra.getChannel(), IPasswayEgw.class).makeOrder(oriModel, null);
		} catch (MessageWooException e) {
			respData.setStatus(EnumPasswayOrderStatus.F);
			respData.setRetCode("Error");
			respData.setRetDesc(e.getMessage());
		} catch (Exception e) {
			log.error(e, e);
			respData.setStatus(EnumPasswayOrderStatus.F);
			respData.setRetCode("Error");
			respData.setRetDesc("通道下单失败");
		}
	}
}
