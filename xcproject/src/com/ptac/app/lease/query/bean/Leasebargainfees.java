package com.ptac.app.lease.query.bean;

public class Leasebargainfees {
	private String leasefeeid;//租赁费用id
	private String shi;//市
	private String xian;//县
	private String xiaoqu;//小区
	private String stationtype;//站点类型
	private String zdname;//站点名称
	private double area;//面积
	private String address;//详细地址
	private String leaseid_fk;//租赁合同id
	private String leasenum;//租赁合同编号
	private String leasename;//租赁合同名称
	private String begintime;//合同起始时间
	private String endtime;//合同结束时间
	private double money;//合同金额
	private double agelimit;//合同年限
	private String paybegintime;//缴费开始时间
	private String payendtime;//缴费结束时间
	private double paymoney;//缴费金额
	private String payhandler;//付款经办人
	private String accountmonth;//报账月份
	private String countryauditstatus;//区县审核状态
	private String cityauditstatus;//市级审核状态
	
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
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLeaseid_fk() {
		return leaseid_fk;
	}
	public void setLeaseid_fk(String leaseidFk) {
		leaseid_fk = leaseidFk;
	}
	public String getLeasenum() {
		return leasenum;
	}
	public void setLeasenum(String leasenum) {
		this.leasenum = leasenum;
	}
	public String getLeasename() {
		return leasename;
	}
	public void setLeasename(String leasename) {
		this.leasename = leasename;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getAgelimit() {
		return agelimit;
	}
	public void setAgelimit(double agelimit) {
		this.agelimit = agelimit;
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
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
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
}
