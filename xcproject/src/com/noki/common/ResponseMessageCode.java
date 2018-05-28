package com.noki.common;

import java.util.List;

public class ResponseMessageCode {
	private String TYPE="";            
	private List<CodeItemRsp> items;
	private String errorMsg="";
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public List<CodeItemRsp> getItems() {
		return items;
	}
	public void setItems(List<CodeItemRsp> items) {
		this.items = items;
	}
}
