package com.noki.chaobiaorenyuan.bean;

public class DianBiao_new {
	private int id;						//id
	private int zdid;					//վ��id
	private String zdname;				//վ������
	private int dbid;					//���id
	private String dbname;				//�������
	private String userid;				//�û�ID
	private String accountname;			//�û�����
	private String bdtime;				//������
	
	public DianBiao_new() {
		super();
	}

	public DianBiao_new(int id, int zdid, String zdname, int dbid,
			String dbname,String userid,String accountname,String bdtime) {
		super();
		this.id = id;
		this.zdid = zdid;
		this.zdname = zdname;
		this.dbid = dbid;
		this.dbname = dbname;
		this.userid = userid;
		this.accountname = accountname;
	}

	public String getBdtime() {
		return bdtime;
	}

	public void setBdtime(String bdtime) {
		this.bdtime = bdtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public int getZdid() {
		return zdid;
	}

	public void setZdid(int zdid) {
		this.zdid = zdid;
	}

	public String getZdname() {
		return zdname;
	}

	public void setZdname(String zdname) {
		this.zdname = zdname;
	}

	public int getDbid() {
		return dbid;
	}

	public void setDbid(int dbid) {
		this.dbid = dbid;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
