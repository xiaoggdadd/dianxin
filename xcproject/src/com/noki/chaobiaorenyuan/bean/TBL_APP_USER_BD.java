package com.noki.chaobiaorenyuan.bean;

public class TBL_APP_USER_BD {
	private String id;		//主键
	private String userid;	//抄表员编号
	private String zdid;	//站点id
	private String zdbm;	//站点编码
	private String dbid;	//电表id
	private String dbbm;	//电表编码
	private String bdtime;	//绑定时间
	private String delete_flag; //删除标志	
	private String create_userid;//创建用户id
	private String create_date;	//创建日期
	private String update_userid;//修改用户id
	private String undate_date; //修改日期
	private String remark;		//备注
	
	public TBL_APP_USER_BD() {
		super();
	}

	public TBL_APP_USER_BD(String id, String userid, String zdid, String zdbm,
			String dbid, String dbbm, String bdtime, String delete_flag,
			String create_userid, String create_date, String update_userid,
			String undate_date, String remark) {
		super();
		this.id = id;
		this.userid = userid;
		this.zdid = zdid;
		this.zdbm = zdbm;
		this.dbid = dbid;
		this.dbbm = dbbm;
		this.bdtime = bdtime;
		this.delete_flag = delete_flag;
		this.create_userid = create_userid;
		this.create_date = create_date;
		this.update_userid = update_userid;
		this.undate_date = undate_date;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getDbbm() {
		return dbbm;
	}

	public void setDbbm(String dbbm) {
		this.dbbm = dbbm;
	}

	public String getBdtime() {
		return bdtime;
	}

	public void setBdtime(String bdtime) {
		this.bdtime = bdtime;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getCreate_userid() {
		return create_userid;
	}

	public void setCreate_userid(String create_userid) {
		this.create_userid = create_userid;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_userid() {
		return update_userid;
	}

	public void setUpdate_userid(String update_userid) {
		this.update_userid = update_userid;
	}

	public String getUndate_date() {
		return undate_date;
	}

	public void setUndate_date(String undate_date) {
		this.undate_date = undate_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
