package com.noki.baobiaohuizong.bean;

public class Zhdjbb {
	private String costtime;	//����
	private String shi;			//����
	private String xian;		//����
	private String xiaoqu;		//С��
	private String jzname;		//��վ����
	private String dbname;		//�������
	private String zhdanjia;	//�ۺϵ�ۣ��ܵ��/�ܵ�����
	
	public Zhdjbb() {
		super();
	}

	public Zhdjbb(String costtime, String shi, String xian, String xiaoqu,
			String jzname, String dbname, String zhdanjia) {
		super();
		this.costtime = costtime;
		this.shi = shi;
		this.xian = xian;
		this.xiaoqu = xiaoqu;
		this.jzname = jzname;
		this.dbname = dbname;
		this.zhdanjia = zhdanjia;
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

	public String getZhdanjia() {
		return zhdanjia;
	}

	public void setZhdanjia(String zhdanjia) {
		this.zhdanjia = zhdanjia;
	}
	
}
