package org.tloss.share4win.model;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5664897206154137939L;
	private int id;
	private String email;
	private String password;
	private int status;
	private String hash;
	private int level;
	private int mfp;
	private int sp;
	private int exp;
	private int mf_poision;
	private int s_poision;
	private int diamon;
	private String phone;
	private int phone_actived;
	private Date mk_date;
	private Date s_date;
	private String phoneactive;

	public Users() {

	}

	public Users(int id, String email, String password, int status, String hash, int level, int mfp, int sp, int exp,
			int mf_poision, int s_poision, int diamon, String phone, int phone_actived, Date mk_date, Date s_date,
			String phoneactive) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.status = status;
		this.hash = hash;
		this.level = level;
		this.mfp = mfp;
		this.sp = sp;
		this.exp = exp;
		this.mf_poision = mf_poision;
		this.s_poision = s_poision;
		this.diamon = diamon;
		this.phone = phone;
		this.phone_actived = phone_actived;
		this.mk_date = mk_date;
		this.s_date = s_date;
		this.phoneactive = phoneactive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMfp() {
		return mfp;
	}

	public void setMfp(int mfp) {
		this.mfp = mfp;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMf_poision() {
		return mf_poision;
	}

	public void setMf_poision(int mf_poision) {
		this.mf_poision = mf_poision;
	}

	public int getS_poision() {
		return s_poision;
	}

	public void setS_poision(int s_poision) {
		this.s_poision = s_poision;
	}

	public int getDiamon() {
		return diamon;
	}

	public void setDiamon(int diamon) {
		this.diamon = diamon;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPhone_actived() {
		return phone_actived;
	}

	public void setPhone_actived(int phone_actived) {
		this.phone_actived = phone_actived;
	}

	public Date getMk_date() {
		return mk_date;
	}

	public void setMk_date(Date mk_date) {
		this.mk_date = mk_date;
	}

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

	public String getPhoneactive() {
		return phoneactive;
	}

	public void setPhoneactive(String phoneactive) {
		this.phoneactive = phoneactive;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", status=" + status + ", hash="
				+ hash + ", level=" + level + ", mfp=" + mfp + ", sp=" + sp + ", exp=" + exp + ", mf_poision="
				+ mf_poision + ", s_poision=" + s_poision + ", diamon=" + diamon + ", phone=" + phone
				+ ", phone_actived=" + phone_actived + ", mk_date=" + mk_date + ", s_date=" + s_date + ", phoneactive="
				+ phoneactive + "]";
	}
	
}
