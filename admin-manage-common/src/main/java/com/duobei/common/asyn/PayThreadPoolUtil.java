package com.duobei.common.asyn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PayThreadPoolUtil {

	private PayThreadPoolUtil() {
		
	}
	private static final PayThreadPoolUtil INSTANCE = new PayThreadPoolUtil();
	public static final PayThreadPoolUtil getInstance() {
		return INSTANCE;
	}
	
	private volatile ExecutorService asynService;
	/**
	 * 异步执行线程池
	 * 
	 * @return
	 */
	public ExecutorService getAsynThread() {
		if (asynService == null) {
			synchronized (ExecutorService.class) {
				if (asynService == null) {
					asynService = Executors.newCachedThreadPool();
				}
			}
		}
		return asynService;
	}
	
	public static void execute(Runnable run){
		PayThreadPoolUtil.getInstance().getAsynThread().execute(run);
	}
}
