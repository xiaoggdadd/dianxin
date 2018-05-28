package com.noki.common;

import java.util.List;

public class CodeItemRsp {
	private String principalAccount="";      	//员工账号   集团统一用户目录账号（全国唯一）
	private String principalName="";               //员工名称
	private String tel="";               			//联系电话
	private List<OrgAndRole> orgAndRoles;       //组织岗位信息
	public String getPrincipalAccount() {
		return principalAccount;
	}
	public void setPrincipalAccount(String principalAccount) {
		this.principalAccount = principalAccount;
	}
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	public List<OrgAndRole> getOrgAndRoles() {
		return orgAndRoles;
	}
	public void setOrgAndRoles(List<OrgAndRole> orgAndRoles) {
		this.orgAndRoles = orgAndRoles;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
