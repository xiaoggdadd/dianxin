package com.noki.jizhan.model;
/**
 * �½��ֵ��Bean
 * name: FuXiuLi
 * time: 2018/1/15
 */
public class BasicDataBean {
	private int id;				//����ID
	private String name;		//����
	private String code;		//ֵ
	private String describe;	//˵��
	
	//�޲ι���
	public BasicDataBean() {
		super();
	}
	//�вι���
	public BasicDataBean(int id, String name, String code, String describe) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.describe = describe;
	}
	//set/get ����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
