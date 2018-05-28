package com.ptac.app.calibstat.bean;

/**超标站点查询bean
 * @author WangYiXiao 2014-9-28
 *
 */
public class OverSite {
	
	private String zdid;//站点ID
	private String city;//城市
	private String xian;//区县
	private String zdname;//站点名称
	private String property;//站点属性
	private String zdlx;//站点类型
	private String month;//月份
	private String scb;//生产标
	private String rjhdl;//日均耗电量
	private String cbbl;//超标比例
	private String qsdbdl;//全省定标电量
	private String cbjdz;//超标绝对值
	
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getCbjdz() {
		return cbjdz;
	}
	public void setCbjdz(String cbjdz) {
		this.cbjdz = cbjdz;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	public String getRjhdl() {
		return rjhdl;
	}
	public void setRjhdl(String rjhdl) {
		this.rjhdl = rjhdl;
	}
	public String getCbbl() {
		return cbbl;
	}
	public void setCbbl(String cbbl) {
		this.cbbl = cbbl;
	}
	
}
