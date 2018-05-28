package com.ptac.app.statisticcollect.province.unitpricePIC.bean;

import java.util.List;

/**
 * 用来存放查询之后的结果集的JavaBean
 * @author rock
 *
 */
public class ProUnitpriceBean {

	
	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	
	public List<UnitpriceInfoBean> getLs() {
		return ls;
	}
	public void setLs(List<UnitpriceInfoBean> ls) {
		this.ls = ls;
	}
	/**
	 * 定义空参的构造方法
	 * @author rock
	 */
	public ProUnitpriceBean(){
		
	}
	
	/**
	 * 定义有参的构造方法，目的是实例化的时候自动赋值
	 * @author rock
	 * @param shi
	 * @param unitprice
	 */
	public ProUnitpriceBean(String shi,List<UnitpriceInfoBean> ls){
		this.shi = shi;
		this.ls = ls;
	}
	
	private String shi;//市
	private List<UnitpriceInfoBean> ls;
	
}
