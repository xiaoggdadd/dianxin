package com.noki.common;

public class MenuItemRsp {
	private String otherSystemMainId="";      	//��Χϵͳ����Id
	private String status="";               	//���˵�״̬
	private String abstract_="";               	//˵��  -3״̬��Ӧ�ķ���ʧ����Ϣ
	private String writeoffInstanceCode;        //���˵���
	private String spCertificateCode;        //sap����
	
	
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
