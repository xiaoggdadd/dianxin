package com.noki.common;

import java.util.List;

public class BZItem {
	private String otherSystemMainId="";      //��Χϵͳ����Id 
	private String account="";                //�������˺�
	private String fillInName="";             //����������
	private String fillInOrgCode="";          //������֯����
	private String sapCompayCode="";          //��˾����    
	private String economicItemCode="";       //�����������
	private String economicItemName="";       //������������
	private String paymentType="";            //��֧��ʽ
	private String happenDate="";             //���÷�����
	private String budgetSet="";              //�����ڼ�
	private String bizType = "";              //ҵ������
	private String isStaffPayment="";         //�Ƿ�Ա������   
	//private String contractNo="";             //��ͬ����/ҵ�����
	//private String contractName="";           //��ͬ����/ҵ������
	private String sum="";                    //���˽��
	private String desc="";                   //˵��
	private String invoiceType="";            //Ʊ������  
	private String isNeedImage="";            //�Ƿ���Ҫ�ֹ�ɨ��
	private List<BZLineItem> lineItems;
	private List<RelateSupplier> relateSuppliers;
	private List<PayMentItem> payMentItems;
	private String pickingMode="";            //ҵ�񳡾�������ģʽ��
	private String businessHappenTimeFlag=""; //ҵ����ʱ����־
	private String isRealGift="";             //�Ƿ����ʵ������
	private String realGiftTaxSum="";         //ʵ������˰��
	private String currency="";               //��λ����
	public String getOtherSystemMainId() {
		return otherSystemMainId;
	}
	public void setOtherSystemMainId(String otherSystemMainId) {
		this.otherSystemMainId = otherSystemMainId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
/*	public String getFillInName() {
		return fillInName;
	}
	public void setFillInName(String fillInName) {
		this.fillInName = fillInName;
	}*/
	public String getFillInOrgCode() {
		return fillInOrgCode;
	}
	public void setFillInOrgCode(String fillInOrgCode) {
		this.fillInOrgCode = fillInOrgCode;
	}
	public String getSapCompayCode() {
		return sapCompayCode;
	}
	public void setSapCompayCode(String sapCompayCode) {
		this.sapCompayCode = sapCompayCode;
	}
	public String getEconomicItemCode() {
		return economicItemCode;
	}
	public void setEconomicItemCode(String economicItemCode) {
		this.economicItemCode = economicItemCode;
	}
	public String getEconomicItemName() {
		return economicItemName;
	}
	public void setEconomicItemName(String economicItemName) {
		this.economicItemName = economicItemName;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	public String getBudgetSet() {
		return budgetSet;
	}
	public void setBudgetSet(String budgetSet) {
		this.budgetSet = budgetSet;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getIsStaffPayment() {
		return isStaffPayment;
	}
	public void setIsStaffPayment(String isStaffPayment) {
		this.isStaffPayment = isStaffPayment;
	}
/*	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}*/
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getIsNeedImage() {
		return isNeedImage;
	}
	public void setIsNeedImage(String isNeedImage) {
		this.isNeedImage = isNeedImage;
	}
	public List<BZLineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<BZLineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public List<RelateSupplier> getRelateSuppliers() {
		return relateSuppliers;
	}
	public void setRelateSuppliers(List<RelateSupplier> relateSuppliers) {
		this.relateSuppliers = relateSuppliers;
	}
	public String getPickingMode() {
		return pickingMode;
	}
	public void setPickingMode(String pickingMode) {
		this.pickingMode = pickingMode;
	}
	public String getBusinessHappenTimeFlag() {
		return businessHappenTimeFlag;
	}
	public void setBusinessHappenTimeFlag(String businessHappenTimeFlag) {
		this.businessHappenTimeFlag = businessHappenTimeFlag;
	}
	public String getIsRealGift() {
		return isRealGift;
	}
	public void setIsRealGift(String isRealGift) {
		this.isRealGift = isRealGift;
	}
	public String getRealGiftTaxSum() {
		return realGiftTaxSum;
	}
	public void setRealGiftTaxSum(String realGiftTaxSum) {
		this.realGiftTaxSum = realGiftTaxSum;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    public String getFillInName()
    {
        return fillInName;
    }
    public void setFillInName(String fillInName)
    {
        this.fillInName = fillInName;
    }
	public List<PayMentItem> getPayMentItems() {
		return payMentItems;
	}
	public void setPayMentItems(List<PayMentItem> payMentItems) {
		this.payMentItems = payMentItems;
	}

	
}
