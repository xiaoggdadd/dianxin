package com.noki.zdymess.dao;

public class session {
	private String logintime = "";
	private String username = "";
	private String status = "";
	private String machine = "";
	private String sqlexectime = "";
	private String mehord = "";

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getSqlexectime() {
		return sqlexectime;
	}

	public void setSqlexectime(String sqlexectime) {
		this.sqlexectime = sqlexectime;
	}

	public String getMehord() {
		return mehord;
	}

	public void setMehord(String mehord) {
		this.mehord = mehord;
	}

}
