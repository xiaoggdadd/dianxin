package com.noki.baobiaohuizong.bean;

public class Bsdpjpuebb {
	private String costtime;	//����
	private String shi;			//����
	private String jzname;		//�ɷ�վ��
	private String dianliang;	//�ɷѶ���
	private String bgdl;		//�������
	private String puezhi;		//PUEֵ
	
	public Bsdpjpuebb() {
		super();
	}

	public Bsdpjpuebb(String costtime, String shi, String jzname,
			String dianliang, String bgdl, String puezhi) {
		super();
		this.costtime = costtime;
		this.shi = shi;
		this.jzname = jzname;
		this.dianliang = dianliang;
		this.bgdl = bgdl;
		this.puezhi = puezhi;
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

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getDianliang() {
		return dianliang;
	}

	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}

	public String getBgdl() {
		return bgdl;
	}

	public void setBgdl(String bgdl) {
		this.bgdl = bgdl;
	}

	public String getPuezhi() {
		return puezhi;
	}

	public void setPuezhi(String puezhi) {
		this.puezhi = puezhi;
	}
	
}
