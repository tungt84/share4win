package org.tloss.share4win;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.tloss.share4win.model.Users;

public class UserService {

	public Users login(String mail, String password) throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=? AND status=1");
				// pstmt.setFloat(1, price);
				pstmt.setString(1, mail);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String email = rs.getString("email");
					String pass = rs.getString("password");
					int status = rs.getInt("status");
					String hash = rs.getString("hash");
					int level = rs.getInt("level");
					int mfp = rs.getInt("mfp");
					int sp = rs.getInt("sp");
					int exp = rs.getInt("exp");
					int mf_poision = rs.getInt("mf_poision");
					int s_poision = rs.getInt("s_poision");
					int diamon = rs.getInt("diamon");
					String phone = rs.getString("phone");
					int phone_actived = rs.getInt("phone_actived");
					Date mk_date = rs.getDate("mk_date");
					Date s_date = rs.getDate("s_date");
					String phoneactive = rs.getString("phoneactive");
					return new Users(id, email, pass, status, hash, level, mfp, sp, exp, mf_poision, s_poision,
							diamon, phone, phone_actived, mk_date, s_date, phoneactive);
				}
				return null;
				// pstmt.executeUpdate();

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

		} catch (Exception e) {
			throw e;
		}
	}

	public boolean activePhone(String mail, String code) throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("UPDATE users SET phone_actived=1  WHERE email=? AND phoneactive=?");
				// pstmt.setFloat(1, price);
				pstmt.setString(1, mail);
				pstmt.setString(2, code);
				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					return true;
				}
				return false;
				//

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

		} catch (Exception e) {
			throw e;
		}
	}

	public String genPhoneActiveCode(String mail) throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("UPDATE users SET phoneactive=? WHERE email=?");
				// pstmt.setFloat(1, price);
				String secret = Base32.random();
				Totp totp = new Totp(secret);
				String phoneActive = totp.now(); // 427773
				pstmt.setString(1, phoneActive);
				pstmt.setString(2, mail);
				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					return phoneActive;
				}
				return null;
				//

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

		} catch (Exception e) {
			throw e;
		}
	}

	public boolean checkHash(String mail, String hash) throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement("SELECT * FROM users WHERE email=? AND hash=?");
				// pstmt.setFloat(1, price);
				pstmt.setString(1, mail);
				pstmt.setString(2, hash);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					return true;
				}
				return false;
				// pstmt.executeUpdate();

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

		} catch (Exception e) {
			throw e;
		}
	}

	public String setUserPassword(String mail, String hash, String password, boolean activeUser) throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("UPDATE users SET hash=?,password=?,status=? WHERE hash=? AND email=?");
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(("" + mail + System.currentTimeMillis()).getBytes());
				byte[] mdbytes = md.digest();
				String newhash = Base64.encodeBase64String(mdbytes);
				pstmt.setString(1, newhash);
				pstmt.setString(2, password);
				pstmt.setInt(3, activeUser ? 1 : 0);
				pstmt.setString(4, hash);
				pstmt.setString(5, mail);
				int rs = pstmt.executeUpdate();
				// pstmt.executeUpdate();
				con.commit();
				if (rs > 0) {
					return newhash;
				} else {
					return null;
				}

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

		} catch (Exception e) {
			throw e;
		}
	}

	public String registerUser(String mail, String phone) throws Exception {
		try {
			DataSource dataSource = DatasourceFactory.getDataSource();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				con.setAutoCommit(false);
				pstmt = con.prepareStatement("INSERT INTO users (email,phone,hash) VALUES(?,?,?)");
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(("" + mail + System.currentTimeMillis()).getBytes());
				byte[] mdbytes = md.digest();
				String hash = Base64.encodeBase64String(mdbytes);
				pstmt.setString(1, mail);
				pstmt.setString(2, phone);
				pstmt.setString(3, hash);
				int rs = pstmt.executeUpdate();
				// pstmt.executeUpdate();
				con.commit();
				if (rs > 0) {
					return hash;
				} else {
					return null;
				}

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

		} catch (Exception e) {
			throw e;
		}
	}
}
