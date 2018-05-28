package com.noki.chaobiaorenyuan.bean;

public class ZhanDian {
	private int id; //站点ID
	private String jzname; //站点名称
	private String userid; //用户ID

	public ZhanDian() {
		super();
	}

	public ZhanDian(int id, String jzname) {
		super();
		this.id = id;
		this.jzname = jzname;
	}

	public ZhanDian(int id, String jzname,String userid) {
		super();
		this.id = id;
		this.jzname = jzname;
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
