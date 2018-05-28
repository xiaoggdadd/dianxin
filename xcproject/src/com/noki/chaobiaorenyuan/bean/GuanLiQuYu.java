package com.noki.chaobiaorenyuan.bean;

public class GuanLiQuYu {
	private String userid;
	private int zdid;
	
	public GuanLiQuYu() {
		super();
	}

	public GuanLiQuYu(String userid, int zdid) {
		super();
		this.userid = userid;
		this.zdid = zdid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getZdid() {
		return zdid;
	}

	public void setZdid(int zdid) {
		this.zdid = zdid;
	}
	
}
