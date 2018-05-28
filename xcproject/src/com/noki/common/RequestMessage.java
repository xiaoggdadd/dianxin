package com.noki.common;

import java.util.List;

public class RequestMessage {
/*	private String processCode="";       
	private String processSuffixName="";*/         
	private List<BZItem> writeoffItems;
/*	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getProcessSuffixName() {
		return processSuffixName;
	}
	public void setProcessSuffixName(String processSuffixName) {
		this.processSuffixName = processSuffixName;
	}*/
	public List<BZItem> getWriteoffItems() {
		return writeoffItems;
	}
	public void setWriteoffItems(List<BZItem> writeoffItems) {
		this.writeoffItems = writeoffItems;
	}
}
