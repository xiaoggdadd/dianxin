package com.noki.baobiaohuizong.bean;

public class Ydxzzdslcx {
	private String cjtime;	//年月
	private String shi;		//地市
	private String xian;	//区县
	private String xiaoqu;	//乡镇 
	private String jzname;	//新增局站名称
	private String jfdj;	//局站等级
	private String stationtype;	//局站类型
	private String zdcq;		//产权
	private String longitude;	//经度
	private String latitude;	//纬度
	
	public Ydxzzdslcx() {
		super();
	}

	public Ydxzzdslcx(String cjtime, String shi, String xian,String xiaoqu, String jzname,
			String jfdj, String stationtype, String zdcq, String longitude,
			String latitude) {
		super();
		this.cjtime = cjtime;
		this.shi = shi;
		this.xian = xian;
		this.jzname = jzname;
		this.jfdj = jfdj;
		this.stationtype = stationtype;
		this.zdcq = zdcq;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCjtime() {
		return cjtime;
	}

	public void setCjtime(String cjtime) {
		this.cjtime = cjtime;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getXiaoqu(){
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu){
		this.xian = xiaoqu;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getJfdj() {
		return jfdj;
	}

	public void setJfdj(String jfdj) {
		this.jfdj = jfdj;
	}

	public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	public String getZdcq() {
		return zdcq;
	}

	public void setZdcq(String zdcq) {
		this.zdcq = zdcq;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}
