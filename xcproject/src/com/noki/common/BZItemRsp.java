package com.noki.common;

public class BZItemRsp {
	private String otherSystemMainId="";      	//��Χϵͳ����Id 
	private String resultCode="";               //״̬
	private String errorMsg="";          		//������Ϣ
	public String getOtherSystemMainId() {
		return otherSystemMainId;
	}
	public void setOtherSystemMainId(String otherSystemMainId) {
		this.otherSystemMainId = otherSystemMainId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
