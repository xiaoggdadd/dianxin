package com.ptac.app.electricmanage.electricitybill.bean;

/**
 * @author lijing
 * @see 电费单查询页面所要显示的基本信息
 */
public class ElecQuery {

	private String jzname;//站点名称
	private String area;//地区
	private String stationtype;//站点类型	
	private String zdcode;//站点code
	private String ammeterdegreeid;
	private String electricfeeId;
	private String manualauditstatus;
	private String unitprice;//本次单价
	private String floatpay;//费用调整
	private double actualpay;//实际电费金额
	private String notetypeid;//票据类型	
	private String noteno;//票据编号
	private String paydatetime;//交费时间
	private String payoperator;//交费操作员
	
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
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
	public String getZdcode() {
		return zdcode;
	}
	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
	}
	public String getAmmeterdegreeid() {
		return ammeterdegreeid;
	}
	public void setAmmeterdegreeid(String ammeterdegreeid) {
		this.ammeterdegreeid = ammeterdegreeid;
	}
	public String getElectricfeeId() {
		return electricfeeId;
	}
	public void setElectricfeeId(String electricfeeId) {
		this.electricfeeId = electricfeeId;
	}
	public String getManualauditstatus() {
		return manualauditstatus;
	}
	public void setManualauditstatus(String manualauditstatus) {
		this.manualauditstatus = manualauditstatus;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public double getActualpay() {
		return actualpay;
	}
	public void setActualpay(double actualpay) {
		this.actualpay = actualpay;
	}
	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getNoteno() {
		return noteno;
	}
	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}
	public String getPaydatetime() {
		return paydatetime;
	}
	public void setPaydatetime(String paydatetime) {
		this.paydatetime = paydatetime;
	}
	public String getPayoperator() {
		return payoperator;
	}
	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}
	
}
