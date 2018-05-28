package com.noki.common;

public class MenuItem {
	private String writeoffInstanceCode="";      	//报账单号
	private String otherSystemMainId="";      	//外围系统主单Id
	public String getWriteoffInstanceCode() {
		return writeoffInstanceCode;
	}
	public void setWriteoffInstanceCode(String writeoffInstanceCode) {
		this.writeoffInstanceCode = writeoffInstanceCode;
	}
	public String getOtherSystemMainId() {
		return otherSystemMainId;
	}
	public void setOtherSystemMainId(String otherSystemMainId) {
		this.otherSystemMainId = otherSystemMainId;
	}
}
