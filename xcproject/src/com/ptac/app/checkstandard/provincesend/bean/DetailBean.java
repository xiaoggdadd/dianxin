package com.ptac.app.checkstandard.provincesend.bean;

/**核实标杆--省手动更新及派单 详情bean
 * @author WangYiXiao 2015-3-2
 *
 */
public class DetailBean {
	private String zdname;//站点名称
	private String lasttime;//上次抄表时间
	private String thistime;//本次抄表时间
	private String lastdegree;//上次电表读数
	private String thisdegree;//本次电表读数
	private String beilv;//倍率
	private String dbydl;//电表用电量
	private String bzdf;//报账电费
	private String bzdl;//报账电量
	private String accountmonth;//报账月份
	private String cityzrauditstatus;//市级主任审核状态
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public String getThisdegree() {
		return thisdegree;
	}
	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getDbydl() {
		return dbydl;
	}
	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
	}
	public String getBzdf() {
		return bzdf;
	}
	public void setBzdf(String bzdf) {
		this.bzdf = bzdf;
	}
	public String getBzdl() {
		return bzdl;
	}
	public void setBzdl(String bzdl) {
		this.bzdl = bzdl;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getCityzrauditstatus() {
		return cityzrauditstatus;
	}
	public void setCityzrauditstatus(String cityzrauditstatus) {
		this.cityzrauditstatus = cityzrauditstatus;
	}
	
}
