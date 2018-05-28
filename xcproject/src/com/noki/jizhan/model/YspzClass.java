package com.noki.jizhan.model;

public class YspzClass {	
	private String yid;			//预算表的ID
	private String shi;			//地市
	private String xian;		//区县
	private String xiaoqu;		//乡镇
	private String month;		//月份
	private String money;		//金额（万元）
	private String state;		//状态 0可用 1删除
	private String inserttime;	//录入时间
	private String updatetime;	//修改时间
	private String entryperson;	//录入人
	
	public YspzClass() {
		super();
	}
	public YspzClass(String shi,String xian,String xiaoqu, String month, String money,
			String inserttime, String updatetime, String entryperson) {
		super();
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.month = month;
		this.money = money;
		this.inserttime = inserttime;
		this.updatetime = updatetime;
		this.entryperson = entryperson;
	}
	public String getYid() {
		return yid;
	}
	public void setYid(String yid) {
		this.yid = yid;
	}
	public String getShi() {
		return shi;
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
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getInserttime() {
		return inserttime;
	}
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getEntryperson() {
		return entryperson;
	}
	public void setEntryperson(String entryperson) {
		this.entryperson = entryperson;
	}
		
}	
