package com.noki.jizhan.model;

/**
 * @author Administrator
 *��Ӧ���˺���Ϣ��
 */
public class GongysAccountBean {
	private String account_id;//����
	private String provider_id;//��Ӧ�̹���id
	private String provider_code;//����ʡ
	private String city;//������
	private String bank_type;//��������
	private String bank;//��������
	private String accountName;//������
	private String account_no;//
	private String brnch;
	private String delete_flag;
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String accountId) {
		account_id = accountId;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String providerId) {
		provider_id = providerId;
	}
	public String getProvider_code() {
		return provider_code;
	}
	public void setProvider_code(String providerCode) {
		provider_code = providerCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bankType) {
		bank_type = bankType;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String accountNo) {
		account_no = accountNo;
	}
	
	public String getBrnch() {
		return brnch;
	}
	public void setBrnch(String brnch) {
		this.brnch = brnch;
	}
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String deleteFlag) {
		delete_flag = deleteFlag;
	}
	
	
}
