package com.noki.baobiaohuizong.bean;

public class Dfcypldbg {
	private String costtime;	//ʱ���
	private String shi;			//����
	private String xian;		//����
	private String xiaoqu;		//����
	private String jzname;		//��վ����
	private String dbname;		//�������
	private String allmoney;	//ʵ�ʵ��
	private String dedf;		//������
	private String cypld;		//����ƫ��ȣ�ʵ�ʵ��-�����ѣ�/������
	
	public Dfcypldbg() {
		super();
	}

	public Dfcypldbg(String costtime, String shi, String xian, String xiaoqu,
			String jzname, String dbname, String allmoney, String dedf,
			String cypld) {
		super();
		this.costtime = costtime;
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.jzname = jzname;
		this.dbname = dbname;
		this.allmoney = allmoney;
		this.dedf = dedf;
		this.cypld = cypld;
	}

	public String getCosttime() {
		return costtime;
	}

	public void setCosttime(String costtime) {
		this.costtime = costtime;
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

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getDedf() {
		return dedf;
	}

	public void setDedf(String dedf) {
		this.dedf = dedf;
	}

	public String getCypld() {
		return cypld;
	}

	public void setCypld(String cypld) {
		this.cypld = cypld;
	}
	
	
}
