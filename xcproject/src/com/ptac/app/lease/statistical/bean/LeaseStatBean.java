package com.ptac.app.lease.statistical.bean;

/**
 * @author �
 * @see ����ͳ��
 */
public class LeaseStatBean {
	
	private String area;//����
	private String city;//����
	private String xian;//���� 
	private String jzname;//վ������
	private String leasename;//��ͬ����
	private String begintime;//��ͬ��ʼʱ��
	private String endtime;//��ͬ����ʱ��
	private Double money;//��ͬ���
	private Double paymoney;//�ɷѽ��
	private Double totalPayMoney;//�ѽ��ܽ��
	private Double noPayMoney;//δ�ɽ��
	private String latesttime;//���һ�νɷ�ʱ��
	private int count;//�ɷѴ���
	private String paycircle;//��������
	private String xcjfsj;//�´νɷ�ʱ��
	private long jfts;//��ɷ�����

	public Double getTotalPayMoney() {
		return totalPayMoney;
	}
	public void setTotalPayMoney(Double totalPayMoney) {
		this.totalPayMoney = totalPayMoney;
	}
	public Double getNoPayMoney() {
		return noPayMoney;
	}
	public void setNoPayMoney(Double noPayMoney) {
		this.noPayMoney = noPayMoney;
	}
	public long getJfts() {
		return jfts;
	}
	public void setJfts(long jfts) {
		this.jfts = jfts;
	}
	public String getXcjfsj() {
		return xcjfsj;
	}
	public void setXcjfsj(String xcjfsj) {
		this.xcjfsj = xcjfsj;
	}
	public String getPaycircle() {
		return paycircle;
	}
	public void setPaycircle(String paycircle) {
		this.paycircle = paycircle;
	}
	public String getArea() {
		return area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getLeasename() {
		return leasename;
	}
	public void setLeasename(String leasename) {
		this.leasename = leasename;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}
	public String getLatesttime() {
		return latesttime;
	}
	public void setLatesttime(String latesttime) {
		this.latesttime = latesttime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
