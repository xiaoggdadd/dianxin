package com.ptac.app.electricmanage.bargainbill.bean;

/**
 * ��ͬ����ѯ����Ų�ѯ������JavaBean
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
	 * ���幹�췽���������ٴ洢��Bean��ֵ
	 * @author rock
	 * @param shi
	 * @param xian; ��
 	 * @param xiaoqu; ����
	 * @param zdmc; վ������
	 * @param qssj; ��ʼʱ��
	 * @param jssj; ����ʱ��
	 * @param zdlx; վ������
	 * @param bzyf; �����·�
	 * @param qyzt; վ������״̬
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
	 * ����ղεĹ��췽�� 
	 * @author rock
	 */
	public BargainBillSelectConditionsBean() {
	}
	

	private String shi;//��
	private String xian;//��
	private String xiaoqu;//����
	private String zdmc;//վ������
	private String qssj;//��ʼʱ��
	private String jssj;//����ʱ��
	private String zdlx;//վ������
	private String bzyf;//�����·�
	private String qyzt;//վ������״̬
	
}
