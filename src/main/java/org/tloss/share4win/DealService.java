package org.tloss.share4win;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.sql.DataSource;

public class DealService {
	public void getActiveDeal() throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("UPDATE COFFEES " + "SET PRICE = ? " + "WHERE COF_NAME = ?");
				// pstmt.setFloat(1, price);
				// pstmt.setString(2, cofName);
				pstmt.executeUpdate();
				con.commit();

			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (con != null)
						con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (PropertyVetoException e) {
			throw new ServletException(e);
		}
	}
}
