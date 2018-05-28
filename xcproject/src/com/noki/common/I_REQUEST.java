package com.noki.common;

public class I_REQUEST {
	private BaseInfoReq BASEINFO;
	private Message MESSAGE;

	public BaseInfoReq getBASEINFO() {
		return BASEINFO;
	}

	public void setBASEINFO(BaseInfoReq bASEINFO) {
		BASEINFO = bASEINFO;
	}

	public Message getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(Message mESSAGE) {
		MESSAGE = mESSAGE;
	}


/*	private BizMessage bizMessage;

	public BizMessage getBizMessage() {
		return bizMessage;
	}

	public void setBizMessage(BizMessage bizMessage) {
		this.bizMessage = bizMessage;
	}*/
}
