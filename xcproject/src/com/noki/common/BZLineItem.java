package com.noki.common;

public class BZLineItem {
	private String otherSystemDetailId="";   //外围系统子单Id    
	private String usageCode="";             //用途编码
	//private String usageName="";             //用途名称
	private String budgetType="";            //列账属性
	private String sum="";                   //子单金额
	private String desc="";                  //摘要
	private String budgetItemCode="";        //预算科目编码
	private String budgetItemName="";        //预算科目名称
	private String budgetOrgCode="";         //预算责任中心编码
	private String budgetOrgName="";         //预算责任中心名称
	private String sapCostCenterCode="";     //记账成本中心编码     
	private String sapCostCenterName="";     //记账成本中心名称
	private String count="";                 //数量
	private String price="";                 //价格
	private String unit="";                  //单位
	private String currency="";              //币种
	private String exchangeRate="";          //汇率
	private String currencySum="";           //币种金额
	//private String chargecode="";            //费用明细编码
	public String getOtherSystemDetailId() {
		return otherSystemDetailId;
	}
	public void setOtherSystemDetailId(String otherSystemDetailId) {
		this.otherSystemDetailId = otherSystemDetailId;
	}
	public String getUsageCode() {
		return usageCode;
	}
	public void setUsageCode(String usageCode) {
		this.usageCode = usageCode;
	}
/*	public String getUsageName() {
		return usageName;
	}
	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}*/
	public String getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}
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
	public String getBudgetItemCode() {
		return budgetItemCode;
	}
	public void setBudgetItemCode(String budgetItemCode) {
		this.budgetItemCode = budgetItemCode;
	}
	public String getBudgetItemName() {
		return budgetItemName;
	}
	public void setBudgetItemName(String budgetItemName) {
		this.budgetItemName = budgetItemName;
	}
	public String getBudgetOrgCode() {
		return budgetOrgCode;
	}
	public void setBudgetOrgCode(String budgetOrgCode) {
		this.budgetOrgCode = budgetOrgCode;
	}
	public String getBudgetOrgName() {
		return budgetOrgName;
	}
	public void setBudgetOrgName(String budgetOrgName) {
		this.budgetOrgName = budgetOrgName;
	}
	public String getSapCostCenterCode() {
		return sapCostCenterCode;
	}
	public void setSapCostCenterCode(String sapCostCenterCode) {
		this.sapCostCenterCode = sapCostCenterCode;
	}
	public String getSapCostCenterName() {
		return sapCostCenterName;
	}
	public void setSapCostCenterName(String sapCostCenterName) {
		this.sapCostCenterName = sapCostCenterName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getCurrencySum() {
		return currencySum;
	}
	public void setCurrencySum(String currencySum) {
		this.currencySum = currencySum;
	}
/*	public String getChargecode() {
		return chargecode;
	}
	public void setChargecode(String chargecode) {
		this.chargecode = chargecode;
	} */                
}
