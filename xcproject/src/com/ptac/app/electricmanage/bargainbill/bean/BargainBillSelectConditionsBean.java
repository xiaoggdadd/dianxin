package com.ptac.app.electricmanage.bargainbill.bean;

/**
 * 合同单查询，存放查询条件的JavaBean
 * @author rock
 *
 */
public class BargainBillSelectConditionsBean {
	
	
	
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
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getQssj() {
		return qssj;
	}
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getBzyf() {
		return bzyf;
	}
	public void setBzyf(String bzyf) {
		this.bzyf = bzyf;
	}
	public String getQyzt() {
		return qyzt;
	}
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	
	
	/**
	 * 
	 * 定义构造方法用来快速存储该Bean的值
	 * @author rock
	 * @param shi
	 * @param xian; 县
 	 * @param xiaoqu; 乡镇
	 * @param zdmc; 站点名称
	 * @param qssj; 起始时间
	 * @param jssj; 结束时间
	 * @param zdlx; 站点类型
	 * @param bzyf; 报账月份
	 * @param qyzt; 站点启用状态
	 */
	public BargainBillSelectConditionsBean(String shi,String xian,String xiaoqu,String zdmc,String qssj,String jssj,String zdlx,String bzyf,String qyzt){
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.zdmc = zdmc;
		this.qssj = qssj;
		this.jssj = jssj;
		this.zdlx = zdlx;
		this.bzyf = bzyf;
		this.qyzt = qyzt;
	}
	
	/**
	 * 定义空参的构造方法 
	 * @author rock
	 */
	public BargainBillSelectConditionsBean() {
	}
	

	private String shi;//市
	private String xian;//县
	private String xiaoqu;//乡镇
	private String zdmc;//站点名称
	private String qssj;//起始时间
	private String jssj;//结束时间
	private String zdlx;//站点类型
	private String bzyf;//报账月份
	private String qyzt;//站点启用状态
	
}
