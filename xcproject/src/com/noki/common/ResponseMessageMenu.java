package com.noki.common;

import java.util.List;

public class ResponseMessageMenu {
	private String TYPE="";          //  ѡ�S �ɹ�,E ����,W ����,I ��Ϣ,A �ж�
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
