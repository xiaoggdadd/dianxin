package com.noki.baobiaohuizong.bean;

public class Zdshl {
	private String createdate;	//����
	private String shi;			//����
	private String xian;		//����
	private String xiaoqu;		//����
	private String jzname;		//�ɷ�վ��
	private String zdshs;		//�Զ��������
	private String zdshl;		//�Զ����ͨ����
	
	public Zdshl() {
		super();
	}

	public Zdshl(String createdate, String shi, String xian,String xiaoqu, String jzname,
			String zdshs, String zdshl) {
		super();
		this.createdate = createdate;
		this.shi = shi;
		this.xian = xian;
		this.jzname = jzname;
		this.zdshs = zdshs;
		this.zdshl = zdshl;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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
	public String getXiaoqu(){
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu){
		this.xiaoqu = xiaoqu;
	}
	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getZdshs() {
		return zdshs;
	}

	public void setZdshs(String zdshs) {
		this.zdshs = zdshs;
	}

	public String getZdshl() {
		return zdshl;
	}

	public void setZdshl(String zdshl) {
		this.zdshl = zdshl;
	}
	
}
