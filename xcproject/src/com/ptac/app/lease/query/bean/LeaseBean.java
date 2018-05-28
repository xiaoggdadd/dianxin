package com.ptac.app.lease.query.bean;

public class LeaseBean {

	private int leaseid;//租赁合同id
	private String dbid_fk;//电表id
	private String leasenum;//租赁合同编号
	private String leasename;//租赁合同名称
	private String begintime;//合同起始时间
	private String endtime;//合同结束时间
	private Double money;//合同金额
	private Double agelimit;//合同年限
	private String leasername;//出租方姓名
	private String leaserphone;//出租方电话
	private String leaserbank;//出租方开户行
	private String leaseraccount;//出租方账号
	private String payway;//支付方式
	private String paycircle;//付款周期
	private String bargainhandler;//合同经办人
	private String auditstatus;//审核状态（ 1：通过 0：未审核 2：不通过）
	private String auditor;//审核人
	private String audittime;//审核时间
	private String area;//地区
	private String stationtype;//站点类型
	private String jzname;//站点名称
	private String mj;//面积
	private String address;//详细地址
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getMj() {
		return mj;
	}
	public void setMj(String mj) {
		this.mj = mj;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLeaseid() {
		return leaseid;
	}
	public void setLeaseid(int leaseid) {
		this.leaseid = leaseid;
	}
	public String getDbid_fk() {
		return dbid_fk;
	}
	public void setDbid_fk(String dbidFk) {
		dbid_fk = dbidFk;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getAgelimit() {
		return agelimit;
	}
	public void setAgelimit(Double agelimit) {
		this.agelimit = agelimit;
	}
	public String getLeasername() {
		return leasername;
	}
	public void setLeasername(String leasername) {
		this.leasername = leasername;
	}
	public String getLeaserphone() {
		return leaserphone;
	}
	public void setLeaserphone(String leaserphone) {
		this.leaserphone = leaserphone;
	}
	public String getLeaserbank() {
		return leaserbank;
	}
	public void setLeaserbank(String leaserbank) {
		this.leaserbank = leaserbank;
	}
	public String getLeaseraccount() {
		return leaseraccount;
	}
	public void setLeaseraccount(String leaseraccount) {
		this.leaseraccount = leaseraccount;
	}
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	public String getPaycircle() {
		return paycircle;
	}
	public void setPaycircle(String paycircle) {
		this.paycircle = paycircle;
	}
	public String getBargainhandler() {
		return bargainhandler;
	}
	public void setBargainhandler(String bargainhandler) {
		this.bargainhandler = bargainhandler;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getAudittime() {
		return audittime;
	}
	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}
}
