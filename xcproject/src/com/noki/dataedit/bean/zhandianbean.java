package com.noki.dataedit.bean;

import java.io.Serializable;

public class zhandianbean implements Serializable{
	public zhandianbean(){
		
		
	}
	 //共享信息    远供rru  移动共享设备数量    电信共享设备数量
    // 站点属性    站点类型     全省定标电量
	private String gxxx;
	private double rru;
	private double ydgxsbsl;
	private double dxgxsbsl;
	private String zdsx;
	private String zdlx;
	private double qsdbdl;
	private double kdsbdk;
	private double yysbdk;
	private double ktyps;
	private double kteps;
	private String qyzt;
	  private String fzr;
	    private String memo;
	    private String area;
	    private String gdfs;
	    private String jzname;
	    private String bieming;
	    private String address;
	    private String bgsign;
	    private String jnglmk;
	    private String jztype;
	    private String jzproperty;
	    private String yflx;
	    private String shi;
	    private String xian;
	    private String id;
	    private String zdcode;
	    private String dianfei;
	    private String xiaoqu;
	    private String typename;
	    private String kzid;
	    private String ckkd;
	    private String ysymj;
	    private String jggs;
	    private String ysygs;
	    private String jfgd;
	    private String sdwd;
	    private String sffs;
	    private String lyfs;
	    private int PUE;
	    private String zzjgbm;
	    private String xuni;
	    private String czzd;
	    private String gczt;
	    private String gcxm;
	    private String zdcq;
	    private String zdcb;
	    private String zlfh;
	    private String jnjsms;
	    private String czzdid;
	    private String gzqk;
	    private String nhzb;
	    private String zpsl;
	    private String zgry;
	    private String ktsl;
	    private String pcsl;
	    private String rll;
	    private String nhjcdy;
	    private String ERPbh;
	    private String dhID;
	    private String zhwgID;
	    private String dzywID;
	    private String longitude;
	    private String latitude;
	    private String caiji;
	    private String edhdl;
	    private String ljzs;
	    private String jzxlx;
	    private String jflx;
	    private String txj;
	    private String ugs;
	    private String ysyugs;
	    private String jnjslx;
	    private String ydlx;
	    private String jrwtype;
	    private String kongtiao;
	    private String gsf;
	    private String stationtype;
	    private String signtypenum;//标杆类型编号
	    private String zhan;//站点总数
	    private String dianbiao;//电表数
	    private String jiaofei;//电表缴费数
	    private String zidong;//自动审核数
	    private String rengong;//人工审核数
	    private String shiji;//市级审核数
	    private String caiwu;//财务审核数
	    private String liuchenghao;//有流程号的数量
	    private String code;
	    private String counn3;
	    private String dfzflx;
	    private String fkzq;
	    private String dfel;//交费次数
	    private String dfpay;//最后交费时间
	    //========== 新增专业
	    private int zynum;//专业站点数
	     private String zywc;//专业完成
	     private String zywf;//专业未分
	     private String zyyc;//专业异常
	     private double zybl;//专业比例
	     //=========新增详细
	     private int xxnum;//详细站点数
	     private String xxwc;//详细完成
	     private String xxwf;//详细未分
	     private String xxyc;//详细异常
	     private double xxbl;//详细比例
	     private String shicode;
	     //==================SDF.ENTRYPENSONNEL,ACC.MOBILE,ACC.EMAIL,ACC.POSITION,ACC.BUMEN
	     private String entrypensonnel;
	     private String MOBILE;
	     private String EMAIL;
	     private String POSITION;
	     private String BUMEN;
	    private String sex;
	    private String accname;
	    private String rolename;
	     //ACCOUNTNAME, ACC.ROLENAME,
		public String getGxxx() {
			return gxxx;
		}
		public void setGxxx(String gxxx) {
			this.gxxx = gxxx;
		}
		public double getRru() {
			return rru;
		}
		public void setRru(double rru) {
			this.rru = rru;
		}
		public double getYdgxsbsl() {
			return ydgxsbsl;
		}
		public void setYdgxsbsl(double ydgxsbsl) {
			this.ydgxsbsl = ydgxsbsl;
		}
		public double getDxgxsbsl() {
			return dxgxsbsl;
		}
		public void setDxgxsbsl(double dxgxsbsl) {
			this.dxgxsbsl = dxgxsbsl;
		}
		public String getZdsx() {
			return zdsx;
		}
		public void setZdsx(String zdsx) {
			this.zdsx = zdsx;
		}
		public String getZdlx() {
			return zdlx;
		}
		public void setZdlx(String zdlx) {
			this.zdlx = zdlx;
		}
		public double getQsdbdl() {
			return qsdbdl;
		}
		public void setQsdbdl(double qsdbdl) {
			this.qsdbdl = qsdbdl;
		}
		public double getKdsbdk() {
			return kdsbdk;
		}
		public void setKdsbdk(double kdsbdk) {
			this.kdsbdk = kdsbdk;
		}
		public double getYysbdk() {
			return yysbdk;
		}
		public void setYysbdk(double yysbdk) {
			this.yysbdk = yysbdk;
		}
		public double getKtyps() {
			return ktyps;
		}
		public void setKtyps(double ktyps) {
			this.ktyps = ktyps;
		}
		public double getKteps() {
			return kteps;
		}
		public void setKteps(double kteps) {
			this.kteps = kteps;
		}
		public String getQyzt() {
			return qyzt;
		}
		public void setQyzt(String qyzt) {
			this.qyzt = qyzt;
		}
		public String getFzr() {
			return fzr;
		}
		public void setFzr(String fzr) {
			this.fzr = fzr;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getGdfs() {
			return gdfs;
		}
		public void setGdfs(String gdfs) {
			this.gdfs = gdfs;
		}
		public String getJzname() {
			return jzname;
		}
		public void setJzname(String jzname) {
			this.jzname = jzname;
		}
		public String getBieming() {
			return bieming;
		}
		public void setBieming(String bieming) {
			this.bieming = bieming;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getBgsign() {
			return bgsign;
		}
		public void setBgsign(String bgsign) {
			this.bgsign = bgsign;
		}
		public String getJnglmk() {
			return jnglmk;
		}
		public void setJnglmk(String jnglmk) {
			this.jnglmk = jnglmk;
		}
		public String getJztype() {
			return jztype;
		}
		public void setJztype(String jztype) {
			this.jztype = jztype;
		}
		public String getJzproperty() {
			return jzproperty;
		}
		public void setJzproperty(String jzproperty) {
			this.jzproperty = jzproperty;
		}
		public String getYflx() {
			return yflx;
		}
		public void setYflx(String yflx) {
			this.yflx = yflx;
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getZdcode() {
			return zdcode;
		}
		public void setZdcode(String zdcode) {
			this.zdcode = zdcode;
		}
		public String getDianfei() {
			return dianfei;
		}
		public void setDianfei(String dianfei) {
			this.dianfei = dianfei;
		}
		public String getXiaoqu() {
			return xiaoqu;
		}
		public void setXiaoqu(String xiaoqu) {
			this.xiaoqu = xiaoqu;
		}
		public String getTypename() {
			return typename;
		}
		public void setTypename(String typename) {
			this.typename = typename;
		}
		public String getKzid() {
			return kzid;
		}
		public void setKzid(String kzid) {
			this.kzid = kzid;
		}
		public String getCkkd() {
			return ckkd;
		}
		public void setCkkd(String ckkd) {
			this.ckkd = ckkd;
		}
		public String getYsymj() {
			return ysymj;
		}
		public void setYsymj(String ysymj) {
			this.ysymj = ysymj;
		}
		public String getJggs() {
			return jggs;
		}
		public void setJggs(String jggs) {
			this.jggs = jggs;
		}
		public String getYsygs() {
			return ysygs;
		}
		public void setYsygs(String ysygs) {
			this.ysygs = ysygs;
		}
		public String getJfgd() {
			return jfgd;
		}
		public void setJfgd(String jfgd) {
			this.jfgd = jfgd;
		}
		public String getSdwd() {
			return sdwd;
		}
		public void setSdwd(String sdwd) {
			this.sdwd = sdwd;
		}
		public String getSffs() {
			return sffs;
		}
		public void setSffs(String sffs) {
			this.sffs = sffs;
		}
		public String getLyfs() {
			return lyfs;
		}
		public void setLyfs(String lyfs) {
			this.lyfs = lyfs;
		}
		public int getPUE() {
			return PUE;
		}
		public void setPUE(int pUE) {
			PUE = pUE;
		}
		public String getZzjgbm() {
			return zzjgbm;
		}
		public void setZzjgbm(String zzjgbm) {
			this.zzjgbm = zzjgbm;
		}
		public String getXuni() {
			return xuni;
		}
		public void setXuni(String xuni) {
			this.xuni = xuni;
		}
		public String getCzzd() {
			return czzd;
		}
		public void setCzzd(String czzd) {
			this.czzd = czzd;
		}
		public String getGczt() {
			return gczt;
		}
		public void setGczt(String gczt) {
			this.gczt = gczt;
		}
		public String getGcxm() {
			return gcxm;
		}
		public void setGcxm(String gcxm) {
			this.gcxm = gcxm;
		}
		public String getZdcq() {
			return zdcq;
		}
		public void setZdcq(String zdcq) {
			this.zdcq = zdcq;
		}
		public String getZdcb() {
			return zdcb;
		}
		public void setZdcb(String zdcb) {
			this.zdcb = zdcb;
		}
		public String getZlfh() {
			return zlfh;
		}
		public void setZlfh(String zlfh) {
			this.zlfh = zlfh;
		}
		public String getJnjsms() {
			return jnjsms;
		}
		public void setJnjsms(String jnjsms) {
			this.jnjsms = jnjsms;
		}
		public String getCzzdid() {
			return czzdid;
		}
		public void setCzzdid(String czzdid) {
			this.czzdid = czzdid;
		}
		public String getGzqk() {
			return gzqk;
		}
		public void setGzqk(String gzqk) {
			this.gzqk = gzqk;
		}
		public String getNhzb() {
			return nhzb;
		}
		public void setNhzb(String nhzb) {
			this.nhzb = nhzb;
		}
		public String getZpsl() {
			return zpsl;
		}
		public void setZpsl(String zpsl) {
			this.zpsl = zpsl;
		}
		public String getZgry() {
			return zgry;
		}
		public void setZgry(String zgry) {
			this.zgry = zgry;
		}
		public String getKtsl() {
			return ktsl;
		}
		public void setKtsl(String ktsl) {
			this.ktsl = ktsl;
		}
		public String getPcsl() {
			return pcsl;
		}
		public void setPcsl(String pcsl) {
			this.pcsl = pcsl;
		}
		public String getRll() {
			return rll;
		}
		public void setRll(String rll) {
			this.rll = rll;
		}
		public String getNhjcdy() {
			return nhjcdy;
		}
		public void setNhjcdy(String nhjcdy) {
			this.nhjcdy = nhjcdy;
		}
		public String getERPbh() {
			return ERPbh;
		}
		public void setERPbh(String eRPbh) {
			ERPbh = eRPbh;
		}
		public String getDhID() {
			return dhID;
		}
		public void setDhID(String dhID) {
			this.dhID = dhID;
		}
		public String getZhwgID() {
			return zhwgID;
		}
		public void setZhwgID(String zhwgID) {
			this.zhwgID = zhwgID;
		}
		public String getDzywID() {
			return dzywID;
		}
		public void setDzywID(String dzywID) {
			this.dzywID = dzywID;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getCaiji() {
			return caiji;
		}
		public void setCaiji(String caiji) {
			this.caiji = caiji;
		}
		public String getEdhdl() {
			return edhdl;
		}
		public void setEdhdl(String edhdl) {
			this.edhdl = edhdl;
		}
		public String getLjzs() {
			return ljzs;
		}
		public void setLjzs(String ljzs) {
			this.ljzs = ljzs;
		}
		public String getJzxlx() {
			return jzxlx;
		}
		public void setJzxlx(String jzxlx) {
			this.jzxlx = jzxlx;
		}
		public String getJflx() {
			return jflx;
		}
		public void setJflx(String jflx) {
			this.jflx = jflx;
		}
		public String getTxj() {
			return txj;
		}
		public void setTxj(String txj) {
			this.txj = txj;
		}
		public String getUgs() {
			return ugs;
		}
		public void setUgs(String ugs) {
			this.ugs = ugs;
		}
		public String getYsyugs() {
			return ysyugs;
		}
		public void setYsyugs(String ysyugs) {
			this.ysyugs = ysyugs;
		}
		public String getJnjslx() {
			return jnjslx;
		}
		public void setJnjslx(String jnjslx) {
			this.jnjslx = jnjslx;
		}
		public String getYdlx() {
			return ydlx;
		}
		public void setYdlx(String ydlx) {
			this.ydlx = ydlx;
		}
		public String getJrwtype() {
			return jrwtype;
		}
		public void setJrwtype(String jrwtype) {
			this.jrwtype = jrwtype;
		}
		public String getKongtiao() {
			return kongtiao;
		}
		public void setKongtiao(String kongtiao) {
			this.kongtiao = kongtiao;
		}
		public String getGsf() {
			return gsf;
		}
		public void setGsf(String gsf) {
			this.gsf = gsf;
		}
		public String getStationtype() {
			return stationtype;
		}
		public void setStationtype(String stationtype) {
			this.stationtype = stationtype;
		}
		public String getSigntypenum() {
			return signtypenum;
		}
		public void setSigntypenum(String signtypenum) {
			this.signtypenum = signtypenum;
		}
		public String getZhan() {
			return zhan;
		}
		public void setZhan(String zhan) {
			this.zhan = zhan;
		}
		public String getDianbiao() {
			return dianbiao;
		}
		public void setDianbiao(String dianbiao) {
			this.dianbiao = dianbiao;
		}
		public String getJiaofei() {
			return jiaofei;
		}
		public void setJiaofei(String jiaofei) {
			this.jiaofei = jiaofei;
		}
		public String getZidong() {
			return zidong;
		}
		public void setZidong(String zidong) {
			this.zidong = zidong;
		}
		public String getRengong() {
			return rengong;
		}
		public void setRengong(String rengong) {
			this.rengong = rengong;
		}
		public String getShiji() {
			return shiji;
		}
		public void setShiji(String shiji) {
			this.shiji = shiji;
		}
		public String getCaiwu() {
			return caiwu;
		}
		public void setCaiwu(String caiwu) {
			this.caiwu = caiwu;
		}
		public String getLiuchenghao() {
			return liuchenghao;
		}
		public void setLiuchenghao(String liuchenghao) {
			this.liuchenghao = liuchenghao;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getCounn3() {
			return counn3;
		}
		public void setCounn3(String counn3) {
			this.counn3 = counn3;
		}
		public String getDfzflx() {
			return dfzflx;
		}
		public void setDfzflx(String dfzflx) {
			this.dfzflx = dfzflx;
		}
		public String getFkzq() {
			return fkzq;
		}
		public void setFkzq(String fkzq) {
			this.fkzq = fkzq;
		}
		public String getDfel() {
			return dfel;
		}
		public void setDfel(String dfel) {
			this.dfel = dfel;
		}
		public String getDfpay() {
			return dfpay;
		}
		public void setDfpay(String dfpay) {
			this.dfpay = dfpay;
		}
		public int getZynum() {
			return zynum;
		}
		public void setZynum(int zynum) {
			this.zynum = zynum;
		}
		public String getZywc() {
			return zywc;
		}
		public void setZywc(String zywc) {
			this.zywc = zywc;
		}
		public String getZywf() {
			return zywf;
		}
		public void setZywf(String zywf) {
			this.zywf = zywf;
		}
		public String getZyyc() {
			return zyyc;
		}
		public void setZyyc(String zyyc) {
			this.zyyc = zyyc;
		}
		public double getZybl() {
			return zybl;
		}
		public void setZybl(double zybl) {
			this.zybl = zybl;
		}
		public int getXxnum() {
			return xxnum;
		}
		public void setXxnum(int xxnum) {
			this.xxnum = xxnum;
		}
		public String getXxwc() {
			return xxwc;
		}
		public void setXxwc(String xxwc) {
			this.xxwc = xxwc;
		}
		public String getXxwf() {
			return xxwf;
		}
		public void setXxwf(String xxwf) {
			this.xxwf = xxwf;
		}
		public String getXxyc() {
			return xxyc;
		}
		public void setXxyc(String xxyc) {
			this.xxyc = xxyc;
		}
		public double getXxbl() {
			return xxbl;
		}
		public void setXxbl(double xxbl) {
			this.xxbl = xxbl;
		}
		public String getShicode() {
			return shicode;
		}
		public void setShicode(String shicode) {
			this.shicode = shicode;
		}
		public String getEntrypensonnel() {
			return entrypensonnel;
		}
		public void setEntrypensonnel(String entrypensonnel) {
			this.entrypensonnel = entrypensonnel;
		}
		public String getMOBILE() {
			return MOBILE;
		}
		public void setMOBILE(String mOBILE) {
			MOBILE = mOBILE;
		}
		public String getEMAIL() {
			return EMAIL;
		}
		public void setEMAIL(String eMAIL) {
			EMAIL = eMAIL;
		}
		public String getPOSITION() {
			return POSITION;
		}
		public void setPOSITION(String pOSITION) {
			POSITION = pOSITION;
		}
		public String getBUMEN() {
			return BUMEN;
		}
		public void setBUMEN(String bUMEN) {
			BUMEN = bUMEN;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAccname() {
			return accname;
		}
		public void setAccname(String accname) {
			this.accname = accname;
		}
		public String getRolename() {
			return rolename;
		}
		public void setRolename(String rolename) {
			this.rolename = rolename;
		}
	
		
		
}
