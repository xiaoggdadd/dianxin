package com.ptac.app.statisticcollect.province.unitpricePIC.bean;

import java.util.List;

/**
 * ������Ų�ѯ֮��Ľ������JavaBean
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
	 * ����ղεĹ��췽��
	 * @author rock
	 */
	public ProUnitpriceBean(){
		
	}
	
	/**
	 * �����вεĹ��췽����Ŀ����ʵ������ʱ���Զ���ֵ
	 * @author rock
	 * @param shi
	 * @param unitprice
	 */
	public ProUnitpriceBean(String shi,List<UnitpriceInfoBean> ls){
		this.shi = shi;
		this.ls = ls;
	}
	
	private String shi;//��
	private List<UnitpriceInfoBean> ls;
	
}
