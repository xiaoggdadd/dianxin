package com.ptac.app.statisticcollect.city.unitpricePIC.bean;


/**
 * ������Ų�ѯ֮��Ľ������JavaBean
 * @author rock
 *
 */
public class CityUnitpriceSelectBean {

	
	
	
	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}


	public String getBzyf1() {
		return bzyf1;
	}

	public void setBzyf1(String bzyf1) {
		this.bzyf1 = bzyf1;
	}



	public String getBzyf2() {
		return bzyf2;
	}

	public void setBzyf2(String bzyf2) {
		this.bzyf2 = bzyf2;
	}

	public String getDfshzt() {
		return dfshzt;
	}

	public void setDfshzt(String dfshzt) {
		this.dfshzt = dfshzt;
	}

	public String getZdqyzt() {
		return zdqyzt;
	}

	public void setZdqyzt(String zdqyzt) {
		this.zdqyzt = zdqyzt;
	}

	public String getDbqyzt() {
		return dbqyzt;
	}

	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
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
	public CityUnitpriceSelectBean(){
		
	}
	
	/**
	 * �����вεĹ��췽����Ŀ����ʵ������ʱ���Զ���ֵ
	 * @author rock
	 * @param shi
	 * @param xian
	 * @param bzyf1
	 * @param bzyf2
	 * @param dfshzt
	 * @param zdqyzt
	 * @param dbqyzt
	 */
	public CityUnitpriceSelectBean(String shi,String xian,String bzyf1,String bzyf2,String dfshzt,String zdqyzt,String dbqyzt){
		this.shi = shi;
		this.xian = xian;
		this.bzyf1 = bzyf1;
		this.bzyf2 = bzyf2;
		this.dfshzt = dfshzt;
		this.zdqyzt = zdqyzt;
		this.dbqyzt = dbqyzt;
	}
	
	private String shi;//��
	private String xian;//��
	private String bzyf1;//�����·�(ǰ)
	private String bzyf2;//�����·�(��)
	private String dfshzt;//������״̬
	private String zdqyzt;//վ������״̬
	private String dbqyzt;//�������״̬
	
}
