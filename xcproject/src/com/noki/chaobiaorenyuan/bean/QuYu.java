package com.noki.chaobiaorenyuan.bean;

public class QuYu {
	private String shi;
	private String xian;
	private String xiaoqu;
	
	public QuYu() {
		super();
	}

	public QuYu(String shi, String xian, String xiaoqu) {
		super();
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
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
	
}
