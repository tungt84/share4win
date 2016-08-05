package org.tloss.share4win;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatasourceFactory {
	static ComboPooledDataSource cpds = null;

	public synchronized static DataSource getDataSource() throws PropertyVetoException {
		if (cpds == null) {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			// loads the jdbc driver
			cpds.setJdbcUrl("jdbc:mysql://579fba0989f5cfa17b0000ec-4win.rhcloud.com:44591/share");
			cpds.setUser("adminCfleqRA");
			cpds.setPassword("cchIS-zJz8SI");
			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(50);
		}
		return cpds;
	}
}
