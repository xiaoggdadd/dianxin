package com.ptac.app.lease.query.bean;

public class LeaseBean {

	private int leaseid;//���޺�ͬid
	private String dbid_fk;//���id
	private String leasenum;//���޺�ͬ���
	private String leasename;//���޺�ͬ����
	private String begintime;//��ͬ��ʼʱ��
	private String endtime;//��ͬ����ʱ��
	private Double money;//��ͬ���
	private Double agelimit;//��ͬ����
	private String leasername;//���ⷽ����
	private String leaserphone;//���ⷽ�绰
	private String leaserbank;//���ⷽ������
	private String leaseraccount;//���ⷽ�˺�
	private String payway;//֧����ʽ
	private String paycircle;//��������
	private String bargainhandler;//��ͬ������
	private String auditstatus;//���״̬�� 1��ͨ�� 0��δ��� 2����ͨ����
	private String auditor;//�����
	private String audittime;//���ʱ��
	private String area;//����
	private String stationtype;//վ������
	private String jzname;//վ������
	private String mj;//���
	private String address;//��ϸ��ַ
	
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
