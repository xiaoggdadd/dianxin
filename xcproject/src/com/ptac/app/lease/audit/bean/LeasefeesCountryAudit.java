package com.ptac.app.lease.audit.bean;

public class LeasefeesCountryAudit {
	private String leasefeeid;//租赁费用id
	private String shi;//市
	private String xian;//县
	private String xiaoqu;//小区
	private String stationtype;//站点类型
	private String zdname;//站点名称
	private String leasename;//租赁合同名称
	private String accountmonth;//报账月份
	private String paybegintime;//缴费开始时间
	private String payendtime;//缴费结束时间
	private double paymoney;//缴费金额
	private String payhandler;//付款经办人
	private String countryauditstatus;//区县审核状态
	private String countryaudittime;//区县审核时间
	private String countryauditor;//区县审核人
	private String cityauditstatus;//市级审核状态
	private String cityaudittime;//市级审核时间
	private String cityauditor;//市级审核人
	
	public String getLeasefeeid() {
		return leasefeeid;
	}
	public void setLeasefeeid(String leasefeeid) {
		this.leasefeeid = leasefeeid;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getLeasename() {
		return leasename;
	}
	public void setLeasename(String leasename) {
		this.leasename = leasename;
	}	
	public String getPaybegintime() {
		return paybegintime;
	}
	public void setPaybegintime(String paybegintime) {
		this.paybegintime = paybegintime;
	}
	public String getPayendtime() {
		return payendtime;
	}
	public void setPayendtime(String payendtime) {
		this.payendtime = payendtime;
	}
	public double getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(double paymoney) {
		this.paymoney = paymoney;
	}
	public String getPayhandler() {
		return payhandler;
	}
	public void setPayhandler(String payhandler) {
		this.payhandler = payhandler;
	}
	public String getCountryauditstatus() {
		return countryauditstatus;
	}
	public void setCountryauditstatus(String countryauditstatus) {
		this.countryauditstatus = countryauditstatus;
	}
	public String getCityauditstatus() {
		return cityauditstatus;
	}
	public void setCityauditstatus(String cityauditstatus) {
		this.cityauditstatus = cityauditstatus;
	}
	public String getCountryaudittime() {
		return countryaudittime;
	}
	public void setCountryaudittime(String countryaudittime) {
		this.countryaudittime = countryaudittime;
	}
	public String getCountryauditor() {
		return countryauditor;
	}
	public void setCountryauditor(String countryauditor) {
		this.countryauditor = countryauditor;
	}
	public String getCityaudittime() {
		return cityaudittime;
	}
	public void setCityaudittime(String cityaudittime) {
		this.cityaudittime = cityaudittime;
	}
	public String getCityauditor() {
		return cityauditor;
	}
	public void setCityauditor(String cityauditor) {
		this.cityauditor = cityauditor;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
}
