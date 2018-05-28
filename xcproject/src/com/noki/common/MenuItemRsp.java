package com.noki.common;

public class MenuItemRsp {
	private String otherSystemMainId="";      	//外围系统主单Id
	private String status="";               	//报账单状态
	private String abstract_="";               	//说明  -3状态对应的发起失败信息
	private String writeoffInstanceCode;        //报账单号
	private String spCertificateCode;        //sap单号
	
	
	public String getSpCertificateCode()
    {
        return spCertificateCode;
    }
    public void setSpCertificateCode(String spCertificateCode)
    {
        this.spCertificateCode = spCertificateCode;
    }
    public String getOtherSystemMainId() {
		return otherSystemMainId;
	}
	public void setOtherSystemMainId(String otherSystemMainId) {
		this.otherSystemMainId = otherSystemMainId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAbstract_() {
		return abstract_;
	}
	public void setAbstract_(String abstract1) {
		abstract_ = abstract1;
	}
	public String getWriteoffInstanceCode() {
		return writeoffInstanceCode;
	}
	public void setWriteoffInstanceCode(String writeoffInstanceCode) {
		this.writeoffInstanceCode = writeoffInstanceCode;
	}
}
