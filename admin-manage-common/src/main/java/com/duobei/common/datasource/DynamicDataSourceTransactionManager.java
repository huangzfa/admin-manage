package com.duobei.common.datasource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

public class DynamicDataSourceTransactionManager extends
		DataSourceTransactionManager {
	private static final long serialVersionUID = 3526745217881842854L;

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (definition.isReadOnly()) {
			DynamicDataSourceSwitch.setSlave();
		} else {
			DynamicDataSourceSwitch.setMaster();
		}
		super.doBegin(transaction, definition);
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		DynamicDataSourceSwitch.clearDataSource();
	}

}
