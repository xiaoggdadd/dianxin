package com.ptac.app.statisticcollect.province.unitpricePIC.bean;
/**
 * �����洢���к͵�����Ϣһ�Զ��Bean
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
	private String bzyf;//�����·�
	private String unitprice;//����
}
