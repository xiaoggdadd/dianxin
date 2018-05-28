package com.noki.baobiaohuizong.bean;

public class Bsdpjpuebb {
	private String costtime;	//年月
	private String shi;			//地市
	private String jzname;		//缴费站点
	private String dianliang;	//缴费度数
	private String bgdl;		//定额电量
	private String puezhi;		//PUE值
	
	public Bsdpjpuebb() {
		super();
	}

	public Bsdpjpuebb(String costtime, String shi, String jzname,
			String dianliang, String bgdl, String puezhi) {
		super();
		this.costtime = costtime;
		this.shi = shi;
		this.jzname = jzname;
		this.dianliang = dianliang;
		this.bgdl = bgdl;
		this.puezhi = puezhi;
	}

	public String getCosttime() {
		return costtime;
	}

	public void setCosttime(String costtime) {
		this.costtime = costtime;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getDianliang() {
		return dianliang;
	}

	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}

	public String getBgdl() {
		return bgdl;
	}

	public void setBgdl(String bgdl) {
		this.bgdl = bgdl;
	}

	public String getPuezhi() {
		return puezhi;
	}

	public void setPuezhi(String puezhi) {
		this.puezhi = puezhi;
	}
	
}
