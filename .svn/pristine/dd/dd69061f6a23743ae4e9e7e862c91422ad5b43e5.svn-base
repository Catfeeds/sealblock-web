package com.woodare.template.component.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.spring.ApplicationContextHolder;

/**
 * @author Luke
 */
public class SimpleTaskJob implements Runnable {
	private Log log = LogFactory.getLog(SimpleTaskJob.class);

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

	/**
	 * @param serviceName
	 * @param obj
	 */
	public static void execute(String serviceName, Object obj) {
		fixedThreadPool.execute(new SimpleTaskJob(serviceName, obj));
	}

	private String serviceName;
	private Object obj;

	public SimpleTaskJob(String serviceName, Object obj) {
		this.serviceName = serviceName;
		this.obj = obj;
	}

	@Override
	public void run() {
		String oriThreadName = Thread.currentThread().getName();
		String newThreadName = SDFFactory.getRestLogId();
		Thread.currentThread().setName(newThreadName);
		log.debug(String.format("currentThread.setName[%s]->[%s]", oriThreadName, newThreadName));
		try {
			ApplicationContext context = ApplicationContextHolder.getApplicationContext();
			((ITaskJob) context.getBean(serviceName)).executeTask(obj);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			Thread.currentThread().setName(oriThreadName);
		}
	}

}
