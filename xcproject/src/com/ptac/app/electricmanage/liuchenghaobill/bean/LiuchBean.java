package com.ptac.app.electricmanage.liuchenghaobill.bean;

public class LiuchBean {

	private String liuchenghao;
	private String city;
	private String xian;
	private String dyr;
	private String drtime;
	private String zje;//总金额
	private String zse;//增值税额
	private String jshj;//价税合计
	private String diqu;//地区
	private String zdname;
	private String zdlx;
	private String lasttime;//上次抄表时间
	private String thistime;//本次抄表时间
	private String lastdegree;//上次电表读数
	private String thisdegree;//本次电表读数
	private String beilv;//倍率
	private String floatdegree;//用电量调整	
	private String blhdl;//报账电量
	private String price;//单价
	private String floatpay;//电费调整
	private String actualpay;//报账电费
	private String accountmonth;//报账月份
	private String zflx;//支付类型
	private String pjje;//票据金额
	private String sqdf;//上期电费
	private String sqdl;//上期电量
	private String lastprice;//上期单价
	private String gdfs;//供电方式
	private String memo;//备注
    private String NETWORKDF;            //网络运营线电费（生产）
	private String INFORMATIZATIONDF;     //信息化支撑线电费
	private String ADMINISTRATIVEDF;     //行政综合线电费（办公）
	private String MARKETDF;             //市场营销线电费（营业）
	private String BUILDDF;               //建设投资线电费
	private String dddf;               //代垫电费电费
	private String sszy; 
	private String qcb; 
	private String kjkm;   
	private String zymx;
	
	public String getQcb() {
		return qcb;
	}
	public void setQcb(String qcb) {
		this.qcb = qcb;
	}
	public String getKjkm() {
		return kjkm;
	}
	public void setKjkm(String kjkm) {
		this.kjkm = kjkm;
	}
	public String getZymx() {
		return zymx;
	}
	public void setZymx(String zymx) {
		this.zymx = zymx;
	}
	public String getSszy() {
		return sszy;
	}
	public void setSszy(String sszy) {
		this.sszy = sszy;
	}
	public String getNETWORKDF() {
		return NETWORKDF;
	}
	public void setNETWORKDF(String nETWORKDF) {
		NETWORKDF = nETWORKDF;
	}
	public String getINFORMATIZATIONDF() {
		return INFORMATIZATIONDF;
	}
	public void setINFORMATIZATIONDF(String iNFORMATIZATIONDF) {
		INFORMATIZATIONDF = iNFORMATIZATIONDF;
	}
	public String getADMINISTRATIVEDF() {
		return ADMINISTRATIVEDF;
	}
	public void setADMINISTRATIVEDF(String aDMINISTRATIVEDF) {
		ADMINISTRATIVEDF = aDMINISTRATIVEDF;
	}
	public String getMARKETDF() {
		return MARKETDF;
	}
	public void setMARKETDF(String mARKETDF) {
		MARKETDF = mARKETDF;
	}
	public String getBUILDDF() {
		return BUILDDF;
	}
	public void setBUILDDF(String bUILDDF) {
		BUILDDF = bUILDDF;
	}
	public String getDddf() {
		return dddf;
	}
	public void setDddf(String dddf) {
		this.dddf = dddf;
	}
	public String getLiuchenghao() {
		return liuchenghao;
	}
	public void setLiuchenghao(String liuchenghao) {
		this.liuchenghao = liuchenghao;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getDyr() {
		return dyr;
	}
	public void setDyr(String dyr) {
		this.dyr = dyr;
	}
	public String getDrtime() {
		return drtime;
	}
	public void setDrtime(String drtime) {
		this.drtime = drtime;
	}
	public String getZje() {
		return zje;
	}
	public void setZje(String zje) {
		this.zje = zje;
	}
	public String getZse() {
		return zse;
	}
	public void setZse(String zse) {
		this.zse = zse;
	}
	public String getJshj() {
		return jshj;
	}
	public void setJshj(String jshj) {
		this.jshj = jshj;
	}
	public String getDiqu() {
		return diqu;
	}
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;
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
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getFloatdegree() {
		return floatdegree;
	}
	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getZflx() {
		return zflx;
	}
	public void setZflx(String zflx) {
		this.zflx = zflx;
	}
	public String getPjje() {
		return pjje;
	}
	public void setPjje(String pjje) {
		this.pjje = pjje;
	}
	public String getSqdf() {
		return sqdf;
	}
	public void setSqdf(String sqdf) {
		this.sqdf = sqdf;
	}
	public String getSqdl() {
		return sqdl;
	}
	public void setSqdl(String sqdl) {
		this.sqdl = sqdl;
	}
	public String getLastprice() {
		return lastprice;
	}
	public void setLastprice(String lastprice) {
		this.lastprice = lastprice;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
