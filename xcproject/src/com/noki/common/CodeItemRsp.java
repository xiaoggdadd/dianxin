package com.noki.common;

import java.util.List;

public class CodeItemRsp {
	private String principalAccount="";      	//Ա���˺�   ����ͳһ�û�Ŀ¼�˺ţ�ȫ��Ψһ��
	private String principalName="";               //Ա������
	private String tel="";               			//��ϵ�绰
	private List<OrgAndRole> orgAndRoles;       //��֯��λ��Ϣ
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
