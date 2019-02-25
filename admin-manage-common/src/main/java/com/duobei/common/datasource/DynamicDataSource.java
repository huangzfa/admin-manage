package com.duobei.common.datasource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	private AtomicInteger counter = new AtomicInteger();
	/**
	 * 主数据库
	 */
	private DataSource master;
	/**
	 * 从数据库
	 */
	private List<DataSource> slaves;

	@Override
	protected Object determineCurrentLookupKey() {
		return null;
	}

	@Override
	protected DataSource determineTargetDataSource() {
		DataSource targetDataSource = null;
		String dbFlag = "主";
		if (DynamicDataSourceSwitch.isSlave()) {
			if (this.slaves != null && this.slaves.size() > 0) {
				int slaveSize = this.slaves.size();
				int index = 0;
				if (slaveSize > 1) {
					int count = this.counter.incrementAndGet();
					if (count > 1000000) {
						this.counter.set(0);
					}
					index = count % slaveSize;
				}
				targetDataSource = (DataSource) this.slaves.get(index);
				dbFlag = "从";
			}
		} else {
			targetDataSource = this.master;
		}
		if (targetDataSource == null) {
			dbFlag = "主";
			targetDataSource = this.master;
		}

		if ((logger.isDebugEnabled()) && ((targetDataSource instanceof DruidDataSource))) {
			@SuppressWarnings("resource")
			DruidDataSource druidDataSource = (DruidDataSource) targetDataSource;
			logger.debug(">>>>>>>>:" + dbFlag + ":当前数据库连接：{}", druidDataSource.getUrl());
		}

		return targetDataSource;
	}

	@Override
	public void afterPropertiesSet() {
	}

	public DataSource getMaster() {
		return master;
	}

	public void setMaster(DataSource master) {
		this.master = master;
	}

	public List<DataSource> getSlaves() {
		return slaves;
	}

	public void setSlaves(List<DataSource> slaves) {
		this.slaves = slaves;
	}

}
