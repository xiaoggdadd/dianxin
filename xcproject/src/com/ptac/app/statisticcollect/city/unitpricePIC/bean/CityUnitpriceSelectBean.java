package com.ptac.app.statisticcollect.city.unitpricePIC.bean;


/**
 * 用来存放查询之后的结果集的JavaBean
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
	 * 定义空参的构造方法
	 * @author rock
	 */
	public CityUnitpriceSelectBean(){
		
	}
	
	/**
	 * 定义有参的构造方法，目的是实例化的时候自动赋值
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
	
	private String shi;//市
	private String xian;//县
	private String bzyf1;//报账月份(前)
	private String bzyf2;//报账月份(后)
	private String dfshzt;//电费审核状态
	private String zdqyzt;//站点启用状态
	private String dbqyzt;//电表启用状态
	
}
