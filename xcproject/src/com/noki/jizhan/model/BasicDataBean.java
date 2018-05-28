package com.noki.jizhan.model;
/**
 * 新建字典表Bean
 * name: FuXiuLi
 * time: 2018/1/15
 */
public class BasicDataBean {
	private int id;				//主键ID
	private String name;		//名称
	private String code;		//值
	private String describe;	//说明
	
	//无参构造
	public BasicDataBean() {
		super();
	}
	//有参构造
	public BasicDataBean(int id, String name, String code, String describe) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.describe = describe;
	}
	//set/get 方法
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
