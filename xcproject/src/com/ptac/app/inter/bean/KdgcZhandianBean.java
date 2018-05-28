package com.ptac.app.inter.bean;

import java.util.Date;

public class KdgcZhandianBean {
	private String shengid;
	private String sheng;
	private String shi;
	// ����
	private String xian;
	// ����,
	private String xiaoqu;
	// ����,
	private String jzname;
	// վ�����,
	private int id;
	// վ��ID,
	private String property;
	// վ������,
	private String stationtype;
	// վ������,
	private String jzlx;
	// /��վ����,
	private String yflx;
	// ��������,
	private String roomtype;
	// ������,
	private int roomarea;
	// �����,
	private int userarea;
	// ��ʹ�����,
	private int cabinetnum;
	// �������,
	private int usecnum;
	// ��ʹ�û������,
	private int unum;
	// U����,
	private int useunum;
	// ��ʹ��U��,
	private String address;
	// ��ַ,
	private String jflx;
	// �ַ�����,
	private String electrictype;
	// �õ�����,
	private String useesdate;
	// ���ܼ���ʹ��ʱ��,
	private String es_property;
	// "���ܼ���1",
	private String Es_Description;
	// ���ܼ��ż���Ӧ�����,
	private String bgsign;
	// �Ƿ���,
	private String entrytime;
	// ¼������,
	private String gdfs;
	// ���緽ʽ,
	private String jztype;
	// ���ű�������,
	private String qyzt;
	//�յ���
	private String kts;
	//
	private String es_id;
	
	

	public String getEs_id() {
		return es_id;
	}

	public void setEs_id(String esId) {
		es_id = esId;
	}

	public String getKts() {
		return kts;
	}

	public void setKts(String kts) {
		this.kts = kts;
	}

	public String getShengid() {
		return shengid;
	}

	public void setShengid(String shengid) {
		this.shengid = shengid;
	}

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
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

	public String getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	public String getJzlx() {
		return jzlx;
	}

	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
	}

	public String getYflx() {
		return yflx;
	}

	public void setYflx(String yflx) {
		this.yflx = yflx;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public int getRoomarea() {
		return roomarea;
	}

	public void setRoomarea(int roomarea) {
		this.roomarea = roomarea;
	}

	public int getUserarea() {
		return userarea;
	}

	public void setUserarea(int userarea) {
		this.userarea = userarea;
	}

	public int getCabinetnum() {
		return cabinetnum;
	}

	public void setCabinetnum(int cabinetnum) {
		this.cabinetnum = cabinetnum;
	}

	public int getUsecnum() {
		return usecnum;
	}

	public void setUsecnum(int usecnum) {
		this.usecnum = usecnum;
	}

	public int getUnum() {
		return unum;
	}

	public void setUnum(int unum) {
		this.unum = unum;
	}

	public int getUseunum() {
		return useunum;
	}

	public void setUseunum(int useunum) {
		this.useunum = useunum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJflx() {
		return jflx;
	}

	public void setJflx(String jflx) {
		this.jflx = jflx;
	}

	public String getElectrictype() {
		return electrictype;
	}

	public void setElectrictype(String electrictype) {
		this.electrictype = electrictype;
	}

	public String getUseesdate() {
		return useesdate;
	}

	public void setUseesdate(String useesdate) {
		this.useesdate = useesdate;
	}

	public String getEs_property() {
		return es_property;
	}

	public void setEs_property(String esProperty) {
		es_property = esProperty;
	}

	public String getEs_Description() {
		return Es_Description;
	}

	public void setEs_Description(String esDescription) {
		Es_Description = esDescription;
	}

	public String getBgsign() {
		return bgsign;
	}

	public void setBgsign(String bgsign) {
		this.bgsign = bgsign;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getGdfs() {
		return gdfs;
	}

	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}

	public String getJztype() {
		return jztype;
	}

	public void setJztype(String jztype) {
		this.jztype = jztype;
	}

	public String getQyzt() {
		return qyzt;
	}

	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}

	@Override
	public String toString() {
		jzlx = this.jzlx != null ? this.jzlx : "";
		roomtype = this.roomtype != null ? this.roomtype : "";
		electrictype = this.electrictype != null ? this.electrictype : "";
		jflx = this.jflx != null ? this.jflx : "";
		es_property = this.es_property != null ? this.es_property : "";
		Es_Description = this.Es_Description != null ? this.Es_Description : "";
		es_id = this.es_id != null ? this.es_id : "";

		if ("0".equals(jflx)) {
			jflx = "";
		}

		// if("null".equals(this.jzlx)){
		// jzlx = "";
		// }
		// if("null".equals(this.roomtype)){
		// roomtype = "";
		// }
		// if("null".equals(this.electrictype)){
		// electrictype = "";
		// }
		// if("null".equals(this.jflx)){
		// jflx = "";
		// }
		// if("null".equals(this.es_property)){
		// es_property ="";
		// }
		// if("null".equals(this.Es_Description)){
		// Es_Description = "";
		// }
		return this.shengid + "||" + this.sheng + "||" + this.shi + "||"
				+ this.xian + "||" + this.xiaoqu + "||" + this.jzname + "||"
				+ this.id + "||" + this.property + "||" + this.stationtype
				+ "||" + this.jzlx + "||" + this.yflx + "||" + this.roomtype
				+ "||" + this.electrictype + "||" + this.roomarea + "||"
				+ this.userarea + "||" + this.cabinetnum + "||" + this.usecnum
				+ "||" + this.unum + "||" + this.useunum + "||" + this.address
				+ "||" + this.jflx + "||" + this.electrictype + "||"
				+ this.useesdate + "" + "||"+ this.bgsign + "||"
				+ this.entrytime + "||" + this.gdfs + "||" + this.jztype + "||"
				+ this.qyzt+"||"+this.kts+"||"+this.es_id;
	}

}
