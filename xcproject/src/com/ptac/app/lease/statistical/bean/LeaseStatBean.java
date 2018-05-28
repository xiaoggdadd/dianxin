package com.ptac.app.lease.statistical.bean;

/**
 * @author 李靖
 * @see 租赁统计
 */
public class LeaseStatBean {
	
	private String area;//地区
	private String city;//城市
	private String xian;//区县 
	private String jzname;//站点名称
	private String leasename;//合同名称
	private String begintime;//合同开始时间
	private String endtime;//合同到期时间
	private Double money;//合同金额
	private Double paymoney;//缴费金额
	private Double totalPayMoney;//已缴总金额
	private Double noPayMoney;//未缴金额
	private String latesttime;//最近一次缴费时间
	private int count;//缴费次数
	private String paycircle;//付款周期
	private String xcjfsj;//下次缴费时间
	private long jfts;//距缴费天数

	public Double getTotalPayMoney() {
		return totalPayMoney;
	}
	public void setTotalPayMoney(Double totalPayMoney) {
		this.totalPayMoney = totalPayMoney;
	}
	public Double getNoPayMoney() {
		return noPayMoney;
	}
	public void setNoPayMoney(Double noPayMoney) {
		this.noPayMoney = noPayMoney;
	}
	public long getJfts() {
		return jfts;
	}
	public void setJfts(long jfts) {
		this.jfts = jfts;
	}
	public String getXcjfsj() {
		return xcjfsj;
	}
	public void setXcjfsj(String xcjfsj) {
		this.xcjfsj = xcjfsj;
	}
	public String getPaycircle() {
		return paycircle;
	}
	public void setPaycircle(String paycircle) {
		this.paycircle = paycircle;
	}
	public String getArea() {
		return area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
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
	public Double getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}
	public String getLatesttime() {
		return latesttime;
	}
	public void setLatesttime(String latesttime) {
		this.latesttime = latesttime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
