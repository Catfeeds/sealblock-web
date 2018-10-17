package com.woodare.template.busi.service;

import org.springframework.stereotype.Service;

import com.woodare.framework.component.SystemCache;

@Service
public class CacheDataChangeService {

	/**
	 * @param objClass
	 */
	public void notifyOthers(final Class<?> objClass) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5 * 1000);
					SystemCache.fireLoadEvent(objClass);
					Thread.sleep(1 * 1000);
				} catch (Exception e) {
				}
			}
		}).start();
	}

	/**
	 * @param objClass
	 */
	public void notifyOthers(final Class<?> objClass, final String pk) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5 * 1000);
					SystemCache.fireLoadEventByPk(objClass, pk);

					Thread.sleep(1 * 1000);
				} catch (Exception e) {
				}
			}
		}).start();
	}
}
