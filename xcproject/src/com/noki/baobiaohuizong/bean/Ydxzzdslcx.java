package com.noki.baobiaohuizong.bean;

public class Ydxzzdslcx {
	private String cjtime;	//����
	private String shi;		//����
	private String xian;	//����
	private String xiaoqu;	//���� 
	private String jzname;	//������վ����
	private String jfdj;	//��վ�ȼ�
	private String stationtype;	//��վ����
	private String zdcq;		//��Ȩ
	private String longitude;	//����
	private String latitude;	//γ��
	
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
