package com.duobei.common.http;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdleConnectionMonitorThread extends Thread {

	private final HttpClientConnectionManager connMgr;
	private volatile boolean shutdown;

	private static Logger log = LoggerFactory.getLogger(IdleConnectionMonitorThread.class);

	public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
		super();
		this.connMgr = connMgr;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(30000);
					try {
						 // 关闭失效的连接
						connMgr.closeExpiredConnections();
						// 可选的, 关闭30秒内不活动的连接
						connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
					} catch (Exception e) {
						log.error("", e);
					}
					
				}
			}
		} catch (InterruptedException ex) {
			log.error("", ex);
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}
