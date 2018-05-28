package com.noki.jizhan.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *供应商
 */
public class GongysBean {
	private String id;
	private String name;//供应商名称
	private String code;//供应商编号
	private String provider_id;//财辅供应商ID
	private List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String providerId) {
		provider_id = providerId;
	}
	public List<GongysAccountBean> getGongysAccountBeans() {
		return gongysAccountBeans;
	}
	public void setGongysAccountBeans(List<GongysAccountBean> gongysAccountBeans) {
		this.gongysAccountBeans = gongysAccountBeans;
	}
	
	

}
