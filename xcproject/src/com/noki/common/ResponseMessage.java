package com.noki.common;

import java.util.List;

public class ResponseMessage {
	private String TYPE="";            
	private List<BZItemRsp> items;
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public List<BZItemRsp> getItems() {
		return items;
	}
	public void setItems(List<BZItemRsp> items) {
		this.items = items;
	}
}
