package com.ptac.app.statisticcollect.province.unitpricePIC.bean;


/**
 * ������Ų�ѯ֮��Ľ������JavaBean
 * @author rock
 *
 */
public class ProSelectBean {

	
	
	
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

	/**
	 * ����ղεĹ��췽��
	 * @author rock
	 */
	public ProSelectBean(){
		
	}
	
	/**
	 * �����вεĹ��췽����Ŀ����ʵ������ʱ���Զ���ֵ
	 * @author rock
	 * @param shi
	 * @param unitprice
	 */
	public ProSelectBean(String shi,String bzyf1,String bzyf2,String dfshzt,String zdqyzt,String dbqyzt){
		this.shi = shi;
		this.bzyf1 = bzyf1;
		this.bzyf2 = bzyf2;
		this.dfshzt = dfshzt;
		this.zdqyzt = zdqyzt;
		this.dbqyzt = dbqyzt;
	}
	
	private String shi;//��
	private String bzyf1;//�����·�(ǰ)
	private String bzyf2;//�����·�(��)
	private String dfshzt;//������״̬
	private String zdqyzt;//վ������״̬
	private String dbqyzt;//�������״̬
	
}
