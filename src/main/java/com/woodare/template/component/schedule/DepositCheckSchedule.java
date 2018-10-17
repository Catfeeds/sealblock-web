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
package com.woodare.template.component.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.webservice.busi.base.IDownMerchantDepositService;
import com.woodare.template.jpa.model.DownMerchantDeposit;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDepositDAO;

/**
 * ClassName: DepositCheckSchedule
 * 
 * @description
 * @author Luke
 * @Date Mar 3, 2018
 */
@Service
public class DepositCheckSchedule implements ICommandJob {

	private static Logger log = Logger.getLogger(DepositCheckSchedule.class);

	@Autowired
	private IDownMerchantDepositService downMerchantDepositService;

	@Autowired
	private IDownMerchantDepositDAO downMerchantDepositDAO;

	/**
	 * 每60s查询一次，5-15分钟内发起代付的数据。
	 */
	@Scheduled(fixedDelay = 30 * 1000)
	public void needCheckStatus_1min() {
		String tagName = "DepositCheckJob1Min";
		if (verifyEnabled(tagName)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, -2);
			Date end = c.getTime();

			c.add(Calendar.MINUTE, -15);
			Date start = c.getTime();

			this.processingTasks(tagName, start, end);
		}
	}

	/**
	 * 每5m查询一次，15~120分钟内发起代付的数据。
	 */
	@Scheduled(fixedDelay = 5 * 60 * 1000)
	public void needCheckStatus_5mins() {
		String tagName = "DepositCheckJob5Min";
		if (verifyEnabled(tagName)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, -16);
			Date end = c.getTime();

			c.add(Calendar.MINUTE, -120);
			Date start = c.getTime();

			this.processingTasks(tagName, start, end);
		}
	}

	/**
	 * 每1h查询一次，2~4h内发起代付的数据。
	 */
	@Scheduled(fixedDelay = 1 * 60 * 60 * 1000)
	public void needCheckStatus_2hours() {
		String tagName = "DepositCheckJob2Hour";
		if (verifyEnabled(tagName)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, -5);
			c.add(Calendar.HOUR, -2);
			Date end = c.getTime();

			c.add(Calendar.HOUR, -4);
			Date start = c.getTime();

			this.processingTasks(tagName, start, end);
		}
	}

	private boolean verifyEnabled(String tagName) {
		boolean runnning = SysProperties.getInstance().getBooleanProperty("run.timer", false);
		if (!runnning) {
			return false;
		}

		DownMerchantData merc = DownMerchants.getByMchNo("A0001");
		if (merc == null) {
			return false;
		}
		if (!SDFFactory.DATE.format(new Date()).equals(merc.getSettleDate())) {
			log.info("[" + tagName + "]系统正在执行跑批，暂停自动查询");
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	private void processingTasks(String tagName, Date start, Date end) {
		List<DownMerchantDeposit> models = downMerchantDepositDAO.findNeedCheckByStatusChgDate(start, end);
		if (models != null) {
			for (DownMerchantDeposit model : models) {
				try {
					downMerchantDepositService.transferQuery(model, false);
				} catch (AbstractWooException e) {
					log.warn(String.format("[" + tagName + "]AutoSync[%s]%s", model.getTransNo(), e.getMessage()));
				} catch (Exception e) {
					log.error(String.format("[" + tagName + "]AutoSync[%s]%s", model.getTransNo(), e.getMessage()), e);
				}
			}
		}
	}

	@Override
	public void execute(Object param) {
		try {
		} catch (Exception e) {
			log.error(e, e);
		}
	}
}
