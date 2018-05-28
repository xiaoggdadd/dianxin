package com.ptac.app.weixin.model;

public class TextMessage extends BaseMessage {
	private Filter filter;
	private Text text;

	public Filter getFilter() {
		return filter;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
