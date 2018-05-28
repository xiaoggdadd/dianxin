package com.ptac.app.electricmanage.electricitybill.bean;

/**
 * 电费专业占比分摊
 * 
 * @author gt_web zylx01 生产 zylx02 营业 zylx03 办公 zylx04 信息化支撑 zylx05 建设投资 zylx06
 *         代垫电费
 */
public class DomainType {
	private String zylx01;
	private String zylx02;
	private String zylx03;
	private String zylx04;
	private String zylx05;
	private String zylx06;
    
	public DomainType() {
		// TODO Auto-generated constructor stub
		this.zylx01 = "0";
		this.zylx02 = "0";
		this.zylx03 = "0";
		this.zylx04 = "0";
		this.zylx05 = "0";
		this.zylx06 = "0";
		
	}
	public String getZylx01() {
		return zylx01;
	}

	public void setZylx01(String zylx01) {
		this.zylx01 = zylx01;
	}

	public String getZylx02() {
		return zylx02;
	}

	public void setZylx02(String zylx02) {
		this.zylx02 = zylx02;
	}

	public String getZylx03() {
		return zylx03;
	}

	public void setZylx03(String zylx03) {
		this.zylx03 = zylx03;
	}

	public String getZylx04() {
		return zylx04;
	}

	public void setZylx04(String zylx04) {
		this.zylx04 = zylx04;
	}

	public String getZylx05() {
		return zylx05;
	}

	public void setZylx05(String zylx05) {
		this.zylx05 = zylx05;
	}

	public String getZylx06() {
		return zylx06;
	}

	public void setZylx06(String zylx06) {
		this.zylx06 = zylx06;
	}

}
