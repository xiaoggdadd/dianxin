package com.ptac.app.statisticcollect.province.gdfselecbulletin.bean;

/**
 * 供电方式电量分析通报bean
 * @author WangYiXiao 2015-3-23
 *
 */
public class GdfsElecBulletinBean {
	private String city;//地市
	private String zdl;//当月总电量   
	private String zhigd;//直供电电量
	private String zhigdzb;//直供电电量占比
	private String zhuangd;//转供电电量
	private String zhuangdzb;//转供电电量占比

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZdl() {
		return zdl;
	}
	public void setZdl(String zdl) {
		this.zdl = zdl;
	}
	public String getZhigd() {
		return zhigd;
	}
	public void setZhigd(String zhigd) {
		this.zhigd = zhigd;
	}
	public String getZhigdzb() {
		return zhigdzb;
	}
	public void setZhigdzb(String zhigdzb) {
		this.zhigdzb = zhigdzb;
	}
	public String getZhuangd() {
		return zhuangd;
	}
	public void setZhuangd(String zhuangd) {
		this.zhuangd = zhuangd;
	}
	public String getZhuangdzb() {
		return zhuangdzb;
	}
	public void setZhuangdzb(String zhuangdzb) {
		this.zhuangdzb = zhuangdzb;
	}

}
