package com.ptac.app.statisticcollect.city.unitpricePIC.bean;

import java.util.List;

import com.ptac.app.statisticcollect.province.unitpricePIC.bean.UnitpriceInfoBean;

/**
 * ������Ų�ѯ֮��Ľ������JavaBean
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
	 * ����ղεĹ��췽��
	 * @author rock
	 */
	public CityUnitpriceBean(){
		
	}
	
	/**
	 * �����вεĹ��췽����Ŀ����ʵ������ʱ���Զ���ֵ
	 * @author rock
	 * @param shi
	 * @param unitprice
	 */
	public CityUnitpriceBean(String shi,String xian,List<UnitpriceInfoBean> ls){
		this.shi = shi;
		this.xian = xian;
		this.ls = ls;
	}
	
	private String shi;//��
	private String xian;//��
	private List<UnitpriceInfoBean> ls;
	
}
