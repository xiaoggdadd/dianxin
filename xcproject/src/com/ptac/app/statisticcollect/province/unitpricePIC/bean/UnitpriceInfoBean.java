package com.ptac.app.statisticcollect.province.unitpricePIC.bean;
/**
 * 用来存储地市和单价信息一对多的Bean
 * @author rock
 *
 */
public class UnitpriceInfoBean {
	
	
	public String getBzyf() {
		return bzyf;
	}
	public void setBzyf(String bzyf) {
		this.bzyf = bzyf;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public UnitpriceInfoBean(){
		
	}
	public UnitpriceInfoBean(String bzyf,String unitprice){
		this.bzyf = bzyf;
		this.unitprice = unitprice;
	}
	private String bzyf;//报账月份
	private String unitprice;//单价
}
