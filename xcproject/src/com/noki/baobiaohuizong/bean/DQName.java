package com.noki.baobiaohuizong.bean;

public class DQName {
	private String agcode;	//��������
	private String agname;	//��������
	
	public DQName() {
		super();
	}

	public DQName(String agcode, String agname) {
		super();
		this.agcode = agcode;
		this.agname = agname;
	}

	public String getAgcode() {
		return agcode;
	}

	public void setAgcode(String agcode) {
		this.agcode = agcode;
	}

	public String getAgname() {
		return agname;
	}

	public void setAgname(String agname) {
		this.agname = agname;
	}
	
	
}
