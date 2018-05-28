package com.noki.common;

import java.util.List;

public class ResponseMessageMenu {
	private String TYPE="";          //  选项：S 成功,E 错误,W 警告,I 信息,A 中断
	private List<MenuItemRsp> items;
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
	public List<MenuItemRsp> getItems() {
		return items;
	}
	public void setItems(List<MenuItemRsp> items) {
		this.items = items;
	}
}
