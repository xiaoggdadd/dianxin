package com.noki.baobiaohuizong.bean;

public class Bqyjzpuemxb {
	private String station_no;	//局站ID
	private String jzname;		//局站名称
	private String shi;			//地市
	private String xian;		//区县
	private String xiaoqu;		//乡镇
	private String issupervisor;//是否监控
	private String housetype_name;//房屋类型
	private String rjydl;		//日均用电量
	private String costtime;	//月度
	private String yuedu;		//实际电量
	private String edhdl;		//额定电量
	private String pjfzzdl;		//平均负载总电流
	private String puezhi;		//PUE值
	private String bzr;			//负责人
	private String memo;		//备注
	
	public Bqyjzpuemxb() {
		super();
	}

	public Bqyjzpuemxb(String station_no, String jzname, String shi,
			String xian, String xiaoqu, String issupervisor,
			String housetype_name, String rjydl, String costtime, String yuedu,
			String edhdl, String pjfzzdl, String puezhi, String bzr, String memo) {
		super();
		this.station_no = station_no;
		this.jzname = jzname;
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.issupervisor = issupervisor;
		this.housetype_name = housetype_name;
		this.rjydl = rjydl;
		this.costtime = costtime;
		this.yuedu = yuedu;
		this.edhdl = edhdl;
		this.pjfzzdl = pjfzzdl;
		this.puezhi = puezhi;
		this.bzr = bzr;
		this.memo = memo;
	}

	public String getStation_no() {
		return station_no;
	}

	public void setStation_no(String station_no) {
		this.station_no = station_no;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
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

	public String getIssupervisor() {
		return issupervisor;
	}

	public void setIssupervisor(String issupervisor) {
		this.issupervisor = issupervisor;
	}

	public String getHousetype_name() {
		return housetype_name;
	}

	public void setHousetype_name(String housetype_name) {
		this.housetype_name = housetype_name;
	}

	public String getRjydl() {
		return rjydl;
	}

	public void setRjydl(String rjydl) {
		this.rjydl = rjydl;
	}

	public String getCosttime() {
		return costtime;
	}

	public void setCosttime(String costtime) {
		this.costtime = costtime;
	}

	public String getYuedu() {
		return yuedu;
	}

	public void setYuedu(String yuedu) {
		this.yuedu = yuedu;
	}

	public String getEdhdl() {
		return edhdl;
	}

	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}

	public String getPjfzzdl() {
		return pjfzzdl;
	}

	public void setPjfzzdl(String pjfzzdl) {
		this.pjfzzdl = pjfzzdl;
	}

	public String getPuezhi() {
		return puezhi;
	}

	public void setPuezhi(String puezhi) {
		this.puezhi = puezhi;
	}

	public String getBzr() {
		return bzr;
	}

	public void setBzr(String bzr) {
		this.bzr = bzr;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
