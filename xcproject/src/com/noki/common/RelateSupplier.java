package com.noki.common;

public class RelateSupplier {
	private String supplierCode="";        //��Ӧ�̱���
	private String supplierName="";        //��Ӧ������   
	private String accountType="";         //��ƿ�Ŀ����  
	private String sum="";                 //������
	private String inputTaxSum="";         //����˰���
	private String invoiceSum="";         //��Ʊ���
	
	
	public String getInvoiceSum()
    {
        return invoiceSum;
    }
    public void setInvoiceSum(String invoiceSum)
    {
        this.invoiceSum = invoiceSum;
    }
    public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getInputTaxSum() {
		return inputTaxSum;
	}
	public void setInputTaxSum(String inputTaxSum) {
		this.inputTaxSum = inputTaxSum;
	}                  

}
