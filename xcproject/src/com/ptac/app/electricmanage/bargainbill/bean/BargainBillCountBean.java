package com.ptac.app.electricmanage.bargainbill.bean;

/**
 * 用于做计算的时候存放需要用到的值的Bean.
 * @author rock
 *
 */
public class BargainBillCountBean {
	
	
	
	public String getDianfei() {
		return dianfei;
	}
	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	
	
	public BargainBillCountBean(String qsdbdl,String edhdl,String dianfei){
		this.qsdbdl = qsdbdl;
		this.edhdl = edhdl;
		this.dianfei = dianfei;
	}
	
	/**
	 * 补一个空参的构造方法
	 */
	public BargainBillCountBean() {
	}
	
	private String qsdbdl;//全省定标电量
	private String edhdl;//额定耗电量
	private String dianfei;//电费
}
