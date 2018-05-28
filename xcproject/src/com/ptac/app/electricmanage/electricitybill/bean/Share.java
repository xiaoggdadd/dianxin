package com.ptac.app.electricmanage.electricitybill.bean;

/**
 * @author lijing
 * @see 电费单添加页面分摊信息所需字段
 */
public class Share {

	/*电量分摊*/
	private double networkhdl;//生产  
	private double administrativehdl;//办公  
	private double markethdl;//营业  
	private double informatizationhdl;//信息化支撑  
	private double buildhdl;//建设投资  
	private double dddl;//代垫电量 
	
	/*电费分摊*/
	private double networkdf;//生产  
	private double administrativedf;//办公  
	private double marketdf;//营业  
	private double informatizationdf;//信息化支撑  
	private double builddf;//建设投资  
	private double dddf;//代垫电费 
	
	public double getNetworkhdl() {
		return networkhdl;
	}
	public void setNetworkhdl(double networkhdl) {
		this.networkhdl = networkhdl;
	}
	public double getAdministrativehdl() {
		return administrativehdl;
	}
	public void setAdministrativehdl(double administrativehdl) {
		this.administrativehdl = administrativehdl;
	}
	public double getMarkethdl() {
		return markethdl;
	}
	public void setMarkethdl(double markethdl) {
		this.markethdl = markethdl;
	}
	public double getInformatizationhdl() {
		return informatizationhdl;
	}
	public void setInformatizationhdl(double informatizationhdl) {
		this.informatizationhdl = informatizationhdl;
	}
	public double getBuildhdl() {
		return buildhdl;
	}
	public void setBuildhdl(double buildhdl) {
		this.buildhdl = buildhdl;
	}
	public void setDddl(double dddl) {
		this.dddl = dddl;
	}
	public double getDddl() {
		return dddl;
	}
	public double getNetworkdf() {
		return networkdf;
	}
	public void setNetworkdf(double networkdf) {
		this.networkdf = networkdf;
	}
	public double getAdministrativedf() {
		return administrativedf;
	}
	public void setAdministrativedf(double administrativedf) {
		this.administrativedf = administrativedf;
	}
	public double getMarketdf() {
		return marketdf;
	}
	public void setMarketdf(double marketdf) {
		this.marketdf = marketdf;
	}
	public double getInformatizationdf() {
		return informatizationdf;
	}
	public void setInformatizationdf(double informatizationdf) {
		this.informatizationdf = informatizationdf;
	}
	public double getBuilddf() {
		return builddf;
	}
	public void setBuilddf(double builddf) {
		this.builddf = builddf;
	}
	public double getDddf() {
		return dddf;
	}
	public void setDddf(double dddf) {
		this.dddf = dddf;
	}
}
