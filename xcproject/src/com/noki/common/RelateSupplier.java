package com.noki.common;

public class RelateSupplier {
	private String supplierCode="";        //供应商编码
	private String supplierName="";        //供应商名称   
	private String accountType="";         //会计科目类型  
	private String sum="";                 //付款金额
	private String inputTaxSum="";         //进项税金额
	private String invoiceSum="";         //发票金额
	
	
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
