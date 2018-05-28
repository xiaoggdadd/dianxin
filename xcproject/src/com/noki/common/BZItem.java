package com.noki.common;

import java.util.List;

public class BZItem {
	private String otherSystemMainId="";      //外围系统主单Id 
	private String account="";                //报账人账号
	private String fillInName="";             //报账人姓名
	private String fillInOrgCode="";          //报账组织编码
	private String sapCompayCode="";          //公司代码    
	private String economicItemCode="";       //经济事项编码
	private String economicItemName="";       //经济事项名称
	private String paymentType="";            //收支方式
	private String happenDate="";             //费用发生日
	private String budgetSet="";              //报账期间
	private String bizType = "";              //业务类型
	private String isStaffPayment="";         //是否员工代垫   
	//private String contractNo="";             //合同编码/业务编码
	//private String contractName="";           //合同名称/业务名称
	private String sum="";                    //报账金额
	private String desc="";                   //说明
	private String invoiceType="";            //票据类型  
	private String isNeedImage="";            //是否需要手工扫描
	private List<BZLineItem> lineItems;
	private List<RelateSupplier> relateSuppliers;
	private List<PayMentItem> payMentItems;
	private String pickingMode="";            //业务场景（挑对模式）
	private String businessHappenTimeFlag=""; //业务发生时间点标志
	private String isRealGift="";             //是否存在实物赠送
	private String realGiftTaxSum="";         //实物赠送税额
	private String currency="";               //本位币种
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
