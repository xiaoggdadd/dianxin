package com.noki.baobiaohuizong.bean;

public class Bqyyswcjd {
	private String station_no;	//局站ID
	private String jzname;		//局站名称
	private String shi;			//地市
	private String xian;		//区县
	private String xiaoqu;		//乡镇
	private String issupervisor;//是否监控
	private String housetype_name;//房屋类型
	private String rjydl;		//日均用电量
	private String allmoney;	//实际金额
	private String sjdianliang;	//实际电量
	private String edhdl;		//额定用电量
	private String ysje;		//预算金额	（暂无）
	private String wcjd;		//完成进度	（暂无）
	private String bzr;			//负责人
	private String memo;		//备注
	
	public Bqyyswcjd() {
		super();
	}

	public Bqyyswcjd(String station_no, String jzname, String shi, String xian,
			String xiaoqu, String issupervisor, String housetype_name,
			String rjydl, String allmoney, String sjdianliang, String edhdl,String ysje,String wcjd,
			String bzr, String memo) {
		super();
		this.station_no = station_no;
		this.jzname = jzname;
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.issupervisor = issupervisor;
		this.housetype_name = housetype_name;
		this.rjydl = rjydl;
		this.allmoney = allmoney;
		this.sjdianliang = sjdianliang;
		this.edhdl = edhdl;
		this.ysje = ysje;
		this.wcjd = wcjd;
		this.bzr = bzr;
		this.memo = memo;
	}

	public String getYsje() {
		return ysje;
	}

	public void setYsje(String ysje) {
		this.ysje = ysje;
	}

	public String getWcjd() {
		return wcjd;
	}

	public void setWcjd(String wcjd) {
		this.wcjd = wcjd;
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

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getSjdianliang() {
		return sjdianliang;
	}

	public void setSjdianliang(String sjdianliang) {
		this.sjdianliang = sjdianliang;
	}

	public String getEdhdl() {
		return edhdl;
	}

	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
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
