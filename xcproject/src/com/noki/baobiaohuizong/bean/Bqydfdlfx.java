package com.noki.baobiaohuizong.bean;

public class Bqydfdlfx {
	private String station_no;	//局站ID
	private String jzname;		//局站名称
	private String shi;			//地市
	private String xian;		//区县
	private String xiaoqu;		//乡镇
	private String issupervisor;//是否监控
	private String housetype_name;//房屋类型
	private String rjydl;		//日均用电量
	private String costtime;	//月度/季度/年度
	private String allmoney;	//总缴纳金额
	private String dianliang;	//总缴费电量
	private String dlpls;		//电量偏离度
	private String pjfzzdl;		//平均负载总电流
	private String bzr;			//负责人
	private String memo;		//备注
	
	public Bqydfdlfx() {
		super();
	}

	public Bqydfdlfx(String station_no, String jzname, String shi, String xian,
			String xiaoqu, String issupervisor, String housetype_name,
			String rjydl, String costtime, String allmoney, String dianliang,
			String dlpls, String pjfzzdl, String bzr, String memo) {
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
		this.allmoney = allmoney;
		this.dianliang = dianliang;
		this.dlpls = dlpls;
		this.pjfzzdl = pjfzzdl;
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

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getDianliang() {
		return dianliang;
	}

	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}

	public String getDlpls() {
		return dlpls;
	}

	public void setDlpls(String dlpls) {
		this.dlpls = dlpls;
	}

	public String getPjfzzdl() {
		return pjfzzdl;
	}

	public void setPjfzzdl(String pjfzzdl) {
		this.pjfzzdl = pjfzzdl;
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
