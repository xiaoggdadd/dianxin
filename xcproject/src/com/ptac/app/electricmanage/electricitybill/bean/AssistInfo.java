package com.ptac.app.electricmanage.electricitybill.bean;

/**
 * @author lijing
 * @see ��ѵ����ҳ�渨����Ϣ�����ֶ�
 */
public class AssistInfo {

	private String blhdl;//�ϴ��õ��� = ʵ���õ���   blhdl
	private double actualpay;//�ϴε�� = ʵ�ʵ�ѽ��  actualpay
	private String entrytime;//�ϴ�¼��ʱ��
	private double zlfh;//ֱ������
	private double jlfh;//��������
	private double pue;//PUEֵ
	private double qsdbdl;//ʡ�������(��)
	private String unitprice;//�ϴε���
	private String lastactualdegree;//���ڵ���õ���
	private String lastfloatdegree;//���ڵ�������
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public double getActualpay() {
		return actualpay;
	}
	public void setActualpay(double actualpay) {
		this.actualpay = actualpay;
	}
	public double getZlfh() {
		return zlfh;
	}
	public void setZlfh(double zlfh) {
		this.zlfh = zlfh;
	}
	public double getJlfh() {
		return jlfh;
	}
	public void setJlfh(double jlfh) {
		this.jlfh = jlfh;
	}
	public double getPue() {
		return pue;
	}
	public void setPue(double pue) {
		this.pue = pue;
	}
	public double getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(double qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getLastactualdegree() {
		return lastactualdegree;
	}
	public void setLastactualdegree(String lastactualdegree) {
		this.lastactualdegree = lastactualdegree;
	}
	public String getLastfloatdegree() {
		return lastfloatdegree;
	}
	public void setLastfloatdegree(String lastfloatdegree) {
		this.lastfloatdegree = lastfloatdegree;
	}
	
	
}
