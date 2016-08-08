package org.tloss.share4win;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				pstmt = con.prepareStatement("SELECT * FROM deal");
				// pstmt.setFloat(1, price);
				// pstmt.setString(2, cofName);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String userid = rs.getString("title");
					String username = rs.getString("content");
				}
				//pstmt.executeUpdate();

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
