package com.noki.tongjichaxu;
//自动审核分析
public class FeixiBean {
	private String zdid;//站点ID
	private String quxian;//区县
	private String jzname;//站点名称
	private String jftime;//缴费时间
	private String bybegin;//月本起始日期
	private String byend;//月本结束日期;
	private String DBbegin;//电表起始数
	private String DBend;//电表终止数
	private String wenti;//电费问题类型
	private String wen;//电量问题类型
	public String getWen() {
		return wen;
	}
	public void setWen(String wen) {
		this.wen = wen;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getQuxian() {
		return quxian;
	}
	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getJftime() {
		return jftime;
	}
	public void setJftime(String jftime) {
		this.jftime = jftime;
	}
	public String getBybegin() {
		return bybegin;
	}
	public void setBybegin(String bybegin) {
		this.bybegin = bybegin;
	}
	public String getByend() {
		return byend;
	}
	public void setByend(String byend) {
		this.byend = byend;
	}
	public String getDBbegin() {
		return DBbegin;
	}
	public void setDBbegin(String dBbegin) {
		DBbegin = dBbegin;
	}
	public String getDBend() {
		return DBend;
	}
	public void setDBend(String dBend) {
		DBend = dBend;
	}
	public String getWenti() {
		return wenti;
	}
	public void setWenti(String wenti) {
		this.wenti = wenti;
	}
	
}
