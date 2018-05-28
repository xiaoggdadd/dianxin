package com.noki.baobiaohuizong.bean;

public class Dfcypldbg {
	private String costtime;	//时间段
	private String shi;			//地市
	private String xian;		//区县
	private String xiaoqu;		//乡镇
	private String jzname;		//局站名称
	private String dbname;		//电表名称
	private String allmoney;	//实际电费
	private String dedf;		//定额电费
	private String cypld;		//差异偏离度（实际电费-定额电费）/定额电费
	
	public Dfcypldbg() {
		super();
	}

	public Dfcypldbg(String costtime, String shi, String xian, String xiaoqu,
			String jzname, String dbname, String allmoney, String dedf,
			String cypld) {
		super();
		this.costtime = costtime;
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.jzname = jzname;
		this.dbname = dbname;
		this.allmoney = allmoney;
		this.dedf = dedf;
		this.cypld = cypld;
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

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getDedf() {
		return dedf;
	}

	public void setDedf(String dedf) {
		this.dedf = dedf;
	}

	public String getCypld() {
		return cypld;
	}

	public void setCypld(String cypld) {
		this.cypld = cypld;
	}
	
	
}
