package com.noki.baobiaohuizong.bean;

public class Idcjljqtlxdfbb {
	private String costtime;	//时间段
	private String shi;			//地市
	private String xian;		//区县
	private String xiaoqu;		//乡镇
	private String jzname;		//局站名称
	private String dbname;		//电表名称
	private String dianliang;	//用电量
	private String allmoney;	//电费
	private String price;		//单价
	private String jztype;		//局站类型
	
	public Idcjljqtlxdfbb() {
		super();
	}

	public Idcjljqtlxdfbb(String costtime, String shi, String xian,
			String xiaoqu, String jzname, String dbname, String dianliang,
			String allmoney, String price, String jztype) {
		super();
		this.costtime = costtime;
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.jzname = jzname;
		this.dbname = dbname;
		this.dianliang = dianliang;
		this.allmoney = allmoney;
		this.price = price;
		this.jztype = jztype;
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

	public String getDianliang() {
		return dianliang;
	}

	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getJztype() {
		return jztype;
	}

	public void setJztype(String jztype) {
		this.jztype = jztype;
	}
	
	
}
