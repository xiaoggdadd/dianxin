package com.ptac.app.inter.bean;

public class AuditBean {
	private String month_id;// 报账月份
	private String city_id;// 市code
	private String city_name;// 市名称
	private String county_id;// 县code
	private String county_name;// 县名称
	private String towns_id;// 乡镇code
	private String towns_name;// 乡镇名称
	private String jz_id;// 站点ID
	private String jz_name;// 站点名称
	private String property_id;// 站点属性code
	private String property;// 站点属性名称
	private String stationtype_id;// 站点类型
	private String stationtype;// 站点类型名称
	private String dfzflx_id;// 电费支付类型code
	private String dfzflx_name;// 电费支付类型名称
	private String gdfs_id;// 供电方式
	private String gdfs_name;// 供电方式名称
	private String dbid;// 电表ID
	private String lastdegree;// 上次抄表读数
	private String thisdegree;// 本次抄表读数
	private String lastdatetime;// 上次抄表时间
	private String thisdatetime;// 本次抄表时间
	private double power_count;// 报账电量
	private double unitprice;// 本次单价
	private double floatpay;// 电费调整
	private double power_fee;// 报账电费
	private int period;// 结算周期
	private String entrytime;// 电费单录入时间
	private String entrypensonnel;// 电费单录入人员
	private String manualauditdatetime;// 人工审核时间
	private String manualauditname;// 人工审核员
	private String financialdatetime;// 财务审核时间
	private String financialoperator;// 财务审核员
	private double beilv;// 电表倍率
	private String notetype_id;// 票据类型
	private String notetype_name;// 票据类型名称

	public String getMonth_id() {
		return month_id;
	}

	public void setMonth_id(String monthId) {
		month_id = monthId;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String cityId) {
		city_id = cityId;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String cityName) {
		city_name = cityName;
	}

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String countyId) {
		county_id = countyId;
	}

	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String countyName) {
		county_name = countyName;
	}

	public String getTowns_id() {
		return towns_id;
	}

	public void setTowns_id(String townsId) {
		towns_id = townsId;
	}

	public String getTowns_name() {
		return towns_name;
	}

	public void setTowns_name(String townsName) {
		towns_name = townsName;
	}

	public String getJz_id() {
		return jz_id;
	}

	public void setJz_id(String jzId) {
		jz_id = jzId;
	}

	public String getJz_name() {
		return jz_name;
	}

	public void setJz_name(String jzName) {
		jz_name = jzName;
	}

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String propertyId) {
		property_id = propertyId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getStationtype_id() {
		return stationtype_id;
	}

	public void setStationtype_id(String stationtypeId) {
		stationtype_id = stationtypeId;
	}

	public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	public String getDfzflx_id() {
		return dfzflx_id;
	}

	public void setDfzflx_id(String dfzflxId) {
		dfzflx_id = dfzflxId;
	}

	public String getDfzflx_name() {
		return dfzflx_name;
	}

	public void setDfzflx_name(String dfzflxName) {
		dfzflx_name = dfzflxName;
	}

	public String getGdfs_id() {
		return gdfs_id;
	}

	public void setGdfs_id(String gdfsId) {
		gdfs_id = gdfsId;
	}

	public String getGdfs_name() {
		return gdfs_name;
	}

	public void setGdfs_name(String gdfsName) {
		gdfs_name = gdfsName;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getLastdegree() {
		return lastdegree;
	}

	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}

	public String getThisdegree() {
		return thisdegree;
	}

	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}

	public String getLastdatetime() {
		return lastdatetime;
	}

	public void setLastdatetime(String lastdatetime) {
		this.lastdatetime = lastdatetime;
	}

	public String getThisdatetime() {
		return thisdatetime;
	}

	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}

	public double getPower_count() {
		return power_count;
	}

	public void setPower_count(double powerCount) {
		power_count = powerCount;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public double getFloatpay() {
		return floatpay;
	}

	public void setFloatpay(double floatpay) {
		this.floatpay = floatpay;
	}

	public double getPower_fee() {
		return power_fee;
	}

	public void setPower_fee(double powerFee) {
		power_fee = powerFee;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getEntrypensonnel() {
		return entrypensonnel;
	}

	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}

	public String getManualauditdatetime() {
		return manualauditdatetime;
	}

	public void setManualauditdatetime(String manualauditdatetime) {
		this.manualauditdatetime = manualauditdatetime;
	}

	public String getManualauditname() {
		return manualauditname;
	}

	public void setManualauditname(String manualauditname) {
		this.manualauditname = manualauditname;
	}

	public String getFinancialdatetime() {
		return financialdatetime;
	}

	public void setFinancialdatetime(String financialdatetime) {
		this.financialdatetime = financialdatetime;
	}

	public String getFinancialoperator() {
		return financialoperator;
	}

	public void setFinancialoperator(String financialoperator) {
		this.financialoperator = financialoperator;
	}

	public double getBeilv() {
		return beilv;
	}

	public void setBeilv(double beilv) {
		this.beilv = beilv;
	}

	public String getNotetype_id() {
		return notetype_id;
	}

	public void setNotetype_id(String notetypeId) {
		notetype_id = notetypeId;
	}

	public String getNotetype_name() {
		return notetype_name;
	}

	public void setNotetype_name(String notetypeName) {
		notetype_name = notetypeName;
	}

	String s = "0x01";
	String e = "0x0A";
	int b = Integer.parseInt(s.replaceAll("^0[x|X]", ""), 16);
	int l = Integer.parseInt(e.replaceAll("^0[x|X]", ""), 16);

	// System.out.println((char)b);

	@Override
	public String toString() {
		return month_id + (char)b + city_id + (char)b + city_name + (char)b + county_id + (char)b
				+ county_name + (char)b + towns_id + (char)b + towns_name + (char)b + jz_id + (char)b
				+ jz_name + (char)b + property_id + (char)b + property + (char)b + stationtype_id
				+ (char)b + stationtype + (char)b + dfzflx_id + (char)b + dfzflx_name + (char)b
				+ gdfs_id + (char)b + gdfs_name + (char)b + dbid + (char)b + lastdegree + (char)b
				+ thisdegree + (char)b + lastdatetime + (char)b + thisdatetime + (char)b
				+ power_count + (char)b + unitprice + (char)b + floatpay + (char)b + power_fee
				+ (char)b + // 报账电费
				period + (char)b + // 结算周期
				entrytime + (char)b + // 电费单录入时间
				entrypensonnel + (char)b + // 电费单录入人员
				manualauditdatetime + (char)b + // 人工审核时间
				manualauditname + (char)b + // 人工审核员
				financialdatetime + (char)b + // 财务审核时间
				financialoperator + (char)b + // 财务审核员
				beilv + (char)b + // 电表倍率
				notetype_id + (char)b + // 票据类型
				notetype_name ;// 票据类型名称
	}
}
