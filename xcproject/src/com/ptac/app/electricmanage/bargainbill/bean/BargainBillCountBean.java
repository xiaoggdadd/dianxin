package com.ptac.app.electricmanage.bargainbill.bean;

/**
 * �����������ʱ������Ҫ�õ���ֵ��Bean.
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
	 * ��һ���ղεĹ��췽��
	 */
	public BargainBillCountBean() {
	}
	
	private String qsdbdl;//ȫʡ�������
	private String edhdl;//��ĵ���
	private String dianfei;//���
}
