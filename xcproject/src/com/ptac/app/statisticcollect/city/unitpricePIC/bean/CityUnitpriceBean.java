package com.ptac.app.statisticcollect.city.unitpricePIC.bean;

import java.util.List;

import com.ptac.app.statisticcollect.province.unitpricePIC.bean.UnitpriceInfoBean;

/**
 * 用来存放查询之后的结果集的JavaBean
 * @author rock
 *
 */
public class CityUnitpriceBean {

	
	
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
	
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	/**
	 * 定义空参的构造方法
	 * @author rock
	 */
	public CityUnitpriceBean(){
		
	}
	
	/**
	 * 定义有参的构造方法，目的是实例化的时候自动赋值
	 * @author rock
	 * @param shi
	 * @param unitprice
	 */
	public CityUnitpriceBean(String shi,String xian,List<UnitpriceInfoBean> ls){
		this.shi = shi;
		this.xian = xian;
		this.ls = ls;
	}
	
	private String shi;//市
	private String xian;//县
	private List<UnitpriceInfoBean> ls;
	
}
