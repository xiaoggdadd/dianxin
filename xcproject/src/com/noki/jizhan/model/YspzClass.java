package com.noki.jizhan.model;

public class YspzClass {	
	private String yid;			//Ԥ����ID
	private String shi;			//����
	private String xian;		//����
	private String xiaoqu;		//����
	private String month;		//�·�
	private String money;		//����Ԫ��
	private String state;		//״̬ 0���� 1ɾ��
	private String inserttime;	//¼��ʱ��
	private String updatetime;	//�޸�ʱ��
	private String entryperson;	//¼����
	
	public YspzClass() {
		super();
	}
	public YspzClass(String shi,String xian,String xiaoqu, String month, String money,
			String inserttime, String updatetime, String entryperson) {
		super();
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.month = month;
		this.money = money;
		this.inserttime = inserttime;
		this.updatetime = updatetime;
		this.entryperson = entryperson;
	}
	public String getYid() {
		return yid;
	}
	public void setYid(String yid) {
		this.yid = yid;
	}
	public String getShi() {
		return shi;
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
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getInserttime() {
		return inserttime;
	}
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getEntryperson() {
		return entryperson;
	}
	public void setEntryperson(String entryperson) {
		this.entryperson = entryperson;
	}
		
}	
