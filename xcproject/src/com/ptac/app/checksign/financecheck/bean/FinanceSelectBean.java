package com.ptac.app.checksign.financecheck.bean;

/**
 * 
 * ���ڴ�Ų�ѯ������JavaBean(�������ݲ�ѯ��������������ѯ֮��Ĳ�ѯ����ͬ��)
 * @author rock
 *
 */
public class FinanceSelectBean {
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
	}
	public String getLch() {
		return lch;
	}
	public void setLch(String lch) {
		this.lch = lch;
	}
	public String getBzyf() {
		return bzyf;
	}
	public void setBzyf(String bzyf) {
		this.bzyf = bzyf;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getZdqyzt() {
		return zdqyzt;
	}
	public void setZdqyzt(String zdqyzt) {
		this.zdqyzt = zdqyzt;
	}
	public String getDbqyzt() {
		return dbqyzt;
	}
	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
	}
	public String getCwshzt() {
		return cwshzt;
	}
	public void setCwshzt(String cwshzt) {
		this.cwshzt = cwshzt;
	}
	public String getPjlx() {
		return pjlx;
	}
	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}
	
	@Override
	public String toString() {
		return "FinanceSelectBean [bzyf=" + bzyf + ", cwshzt=" + cwshzt
				+ ", dbqyzt=" + dbqyzt + ", lch=" + lch + ", loginId="
				+ loginId + ", lrrq=" + lrrq + ", pjlx=" + pjlx + ", shi="
				+ shi + ", xian=" + xian + ", xiaoqu=" + xiaoqu + ", zdlx="
				+ zdlx + ", zdmc=" + zdmc + ", zdqyzt=" + zdqyzt + ", zdsx="
				+ zdsx + "]";
	}
	
	private String loginId;
	private String  shi ;//��
	private String  xian;//��
	private String   xiaoqu;//С��
	private String   zdmc;//վ������
	private String   zdlx;//վ������
	private String   zdsx;//վ������
	private String   lch;//���̺�
	private String   bzyf;//�����·�
	private String   lrrq;//¼������
	private String   zdqyzt;//վ������״̬
	private String   dbqyzt;//�������״̬
	private String   cwshzt;//�������״̬
	private String   pjlx;//Ʊ������
}
