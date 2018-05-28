package com.ptac.app.electricmanage.electricitybill.bean;

/**
 * @author lijing
 * @see 电费单添加页面辅助信息所需字段
 */
public class AssistInfo {

	private String blhdl;//上次用电量 = 实际用电量   blhdl
	private double actualpay;//上次电费 = 实际电费金额  actualpay
	private String entrytime;//上次录入时间
	private double zlfh;//直流负荷
	private double jlfh;//交流负荷
	private double pue;//PUE值
	private double qsdbdl;//省定标电量(度)
	private String unitprice;//上次单价
	private String lastactualdegree;//上期电表用电量
	private String lastfloatdegree;//上期电量调整
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public double getActualpay() {
		return actualpay;
	}
	public void setActualpay(double actualpay) {
		this.actualpay = actualpay;
	}
	public double getZlfh() {
		return zlfh;
	}
	public void setZlfh(double zlfh) {
		this.zlfh = zlfh;
	}
	public double getJlfh() {
		return jlfh;
	}
	public void setJlfh(double jlfh) {
		this.jlfh = jlfh;
	}
	public double getPue() {
		return pue;
	}
	public void setPue(double pue) {
		this.pue = pue;
	}
	public double getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(double qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getLastactualdegree() {
		return lastactualdegree;
	}
	public void setLastactualdegree(String lastactualdegree) {
		this.lastactualdegree = lastactualdegree;
	}
	public String getLastfloatdegree() {
		return lastfloatdegree;
	}
	public void setLastfloatdegree(String lastfloatdegree) {
		this.lastfloatdegree = lastfloatdegree;
	}
	
	
}
