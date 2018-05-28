package com.noki.electricfees.javabean;

public class DianfeidandayinBean {
	
	private String jzname;
    private String szdq;
    private String jztype;
    private String inputdatetime;
    private String inputoperator;
    private String lastdatetime;
    private String thisdatetime;
    private String accountmonth;
    private String lastdegree;
    private String thisdegree;
    private String actualdegree;
    private String floatdegree;
    private String electricfee_id;
    private String unitprice;
    private String floatpay;
    private String actualpay;
    private String fffs;
    private String blhdl;
    private String beilv;
    private String statmonth;
	private String endmonth;
	private String shi;
	private String xian;
	private String NETWORKDF;            //网络运营线电费（生产）
	private String INFORMATIZATIONDF;     //信息化支撑线电费
	private String ADMINISTRATIVEDF;     //行政综合线电费（办公）
	private String MARKETDF;             //市场营销线电费（营业）
	private String BUILDDF;               //建设投资线电费
	private String dddf;               //代垫电费电费
	private String qcb; 
	private String kjkm;   
	private String zymx;   
	private String sszy;   
	private String memo;
	private String notetypeid;//票据类型
	private String pjje;//票据金额
	
    //2014-4-15新增---------------------------------{
    private String lastelecfees = "";//上期电费
    private String lastelecdegree = ""; //上期电量
    private String lastunitprice = ""; //上期单价
    private String gdfs = "";//供电方式
    //----------------------------------------------}
	
	
	
	
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getDddf() {
		return dddf;
	}

	public void setDddf(String dddf) {
		this.dddf = dddf;
	}
	public String getPjje() {
		return pjje;
	}
	public void setPjje(String pjje) {
		this.pjje = pjje;
	}
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
	        
	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
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
	
    
    public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getSzdq() {
		return szdq;
	}
	public void setSzdq(String szdq) {
		this.szdq = szdq;
	}
	public String getJztype() {
		return jztype;
	}
	public void setJztype(String jztype) {
		this.jztype = jztype;
	}
	public String getInputdatetime() {
		return inputdatetime;
	}
	public void setInputdatetime(String inputdatetime) {
		this.inputdatetime = inputdatetime;
	}
	public String getInputoperator() {
		return inputoperator;
	}
	public void setInputoperator(String inputoperator) {
		this.inputoperator = inputoperator;
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
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
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
	public String getActualdegree() {
		return actualdegree;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}
	public String getFloatdegree() {
		return floatdegree;
	}
	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}
	public String getElectricfee_id() {
		return electricfee_id;
	}
	public void setElectricfee_id(String electricfeeId) {
		electricfee_id = electricfeeId;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
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
	public void setFffs(String fffs) {
		this.fffs = fffs;
	}
	public String getFffs() {
		return fffs;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getBeilv() {
		return beilv;
	}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public String getMemo() {
			return memo;
		}
		public void setNotetypeid(String notetypeid) {
			this.notetypeid = notetypeid;
		}
		public String getNotetypeid() {
			return notetypeid;
		}
		public String getLastelecfees() {
			return lastelecfees;
		}
		public void setLastelecfees(String lastelecfees) {
			this.lastelecfees = lastelecfees;
		}
		public String getLastelecdegree() {
			return lastelecdegree;
		}
		public void setLastelecdegree(String lastelecdegree) {
			this.lastelecdegree = lastelecdegree;
		}
		public String getLastunitprice() {
			return lastunitprice;
		}
		public void setLastunitprice(String lastunitprice) {
			this.lastunitprice = lastunitprice;
		}
    
    
}
