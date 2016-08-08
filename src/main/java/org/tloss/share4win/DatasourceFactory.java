package org.tloss.share4win;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatasourceFactory {
	static ComboPooledDataSource cpds = null;

	public static void init(String driver, String url, String user, String pass) throws Exception {
		if (cpds == null) {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(driver);
			// loads the jdbc driver
			cpds.setJdbcUrl(url);
			cpds.setUser(user);
			cpds.setPassword(pass);
			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(2);
			cpds.setMaxPoolSize(50);
		}
	}

	public synchronized static DataSource getDataSource() throws Exception {
		if (cpds == null) {
			throw new Exception("Datasource is null");
		}
		return cpds;
	}
}
