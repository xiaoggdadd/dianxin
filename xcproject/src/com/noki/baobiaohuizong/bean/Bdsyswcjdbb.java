package com.noki.baobiaohuizong.bean;

public class Bdsyswcjdbb {
	private String costtime;	//时间段
	private String datetime;	//日期
	private String shi;			//地市
	private String ysdfje;		//预算电费金额
	private String allmoney;	//报账电费费用
	private String yswcjx;		//预算完成进行
	
	public Bdsyswcjdbb() {
		super();
	}

	public Bdsyswcjdbb(String costtime, String datetime, String shi,
			String ysdfje, String allmoney, String yswcjx) {
		super();
		this.costtime = costtime;
		this.datetime = datetime;
		this.shi = shi;
		this.ysdfje = ysdfje;
		this.allmoney = allmoney;
		this.yswcjx = yswcjx;
	}

	public String getCosttime() {
		return costtime;
	}

	public void setCosttime(String costtime) {
		this.costtime = costtime;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getYsdfje() {
		return ysdfje;
	}

	public void setYsdfje(String ysdfje) {
		this.ysdfje = ysdfje;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getYswcjx() {
		return yswcjx;
	}

	public void setYswcjx(String yswcjx) {
		this.yswcjx = yswcjx;
	}
	
}
