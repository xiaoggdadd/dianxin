package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.ammeterdegree.javabean.BargainBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

/**
 * Electricfees entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ElectricFeesFormBean implements java.io.Serializable {
	private static final long serialVersionUID = 8189676678118646005L;

	private String biaozhi;//区分电费和预付费(js用)
	private String sort;//区分电费和预付费(显示用)

	 //电费分摊
	//生产//办公 //营业//信息化 //建设投资//代垫电费
    private String scdff;
    private String bgdf;
    private String yydf;
    private String xxhdf;
    private String jstzdf;
    private String dddfdl;//代垫电量
    private String dddf;
    //电量分摊
	//生产//办公 //营业//信息化 //建设投资
    private String scdl;
    private String bgdl;
    private String yydl;
    private String xxhdl;
    private String jstzdl;
    private String dddfdf;//代垫电费
    private String zdcq;
    private String dedhdl;//电量抄表比例
  
   
	// Fields

	private String electricfeeId;
	private String ammeterdegreeidFk;
	private String unitprice;
	private String floatpay;
	private String actualpay;
	private String notetypeid;
	private String noteno;
	private String notetime;
	private String payoperator;
	private String paydatetime;
	private String inputoperator;
	private String inputdatetime;
	private String autoauditstatus;
	private String autoauditdescription;
	private String manualauditstatus;
	private String manualauditname;
	private String manualauditcomment;
	private String manualauditdatetime;
	private String financialoperator;
	private String financialdatetime;
	private String memo;
	private String accountmonth;
	private String kptime;
	private String financialauditcomment;
	private String cityaudit;
	private String entrypensonnel;
	private String entrytime;
	private String cityaudittime;
	private String cityauditpensonnel;
	private String dayinren;//打印人
	private String dayintime;//打印时间
	private String kjyf;//会计月份
	
	//审核条数信息
	private String count;//电费缴纳明细 总条数
	private String zdno;//自动审核未通过
	private String rgno;//人工审核未通过
	private String rg;//人工审核通过
	private String shish;//二级审核未通过
	private String cwno;//财务审核 未通过
	private String cw;//财务审核 通过
	private String bzdy;//报账打印条数   打印后才会有流程号
	private String zdzs;//站点总数
	private String jdno;//站点未审核的
	private String zd;//站点审核通过的
	private String jsdb;//结算电表
	private String gldb;//管理电表
	private String caijizd;//采集站点
	

	//电表信息
	private String shi;
	private String xian;
	private String xiaoqu;
	private String ammererid;
	private String dbid;
	private String professionaltypeid;
	private String multiplyingpower;
	private String ammerertype;
	private String initialdegree;
	private String initialdate;
	private String dfzflx;
	private String dbxh;//电表型号
	private String dllx;//电流类型
	private String ydsb;//用电设备
	private String xunisign;//虚拟站点标志
	private String zlfh;//直流负荷
	private String kongtiao;//空调
	private String yflx;//用房类型
	private String gdfs;//供电方式
	private String sydate;//投入使用日期
	private String bieming;//别名
	private String erpcode;//ERP代码
	private String fzr;//负责人
	private String jnglmk;//节能管理模块0，没有，1：有
	private String area; //面积
	private String dianfei;//电费
	private String gsf;//归属方
	private String yid;//原系统站点id
	private String dbzbdyhh;//电表自报电用户号
	private String bgsign;//是否标杆
	private String id;
	//电量信息
	private String ammeterdegreeid;
    private String floatdegree;
    private String actualdegree;
    private String lastdegree;
    private String lastdatetime;
    private String thisdegree;
    private String thisdatetime;
    private String inputoperatoram;
    private String inputdatetimeam;
    private String startmonth;
    private String endmonth;
    private String memoam;
    private int flag;
    private String blhdl;
    private String currentmonth;
    private String dianliang;
    private String liuchenghao;//流程号
    private String edhdl;//额定耗电量
    private String dlshzt;//电量审核状态
    private String dlsh;//电量审核标志
    private String feebz;//
    private String jszq;
    private String startdegree;//起始读数
    private String stopdegree;//终止读数
    private String startdate;//起始日期
    private String stopdate;//结束日期
    //2014-4-15新增---------------------------------{
    private String lastelecfees = "";//上期电费
    private String lastelecdegree = ""; //上期电量
    private String lastunitprice = ""; //上期单价
    //----------------------------------------------}
    //2014-6-26
    private String floatdegreeandbl;//电量调整*倍率
    private String lastfloatdegreeandbl;//上期电量调整*倍率
    private String lastactualdegree;//上期电表用电量
    private String cshibdfbl;//超市标电费比例
    private String csbdld;//超省标电量度
    private String cshibdld;//超市标电量度
    private String csbdfjdz;//超省标电费绝对值
    private String cshibdjdz;//超市标电费绝对值
    private String glbrjl;//管理表日均量
    private String bzrj;//报账日均电量
    private String lineandchangeandbl;//线变损电量
    
    //基站信息
    private String zdid;//站点id
	private String stationname;
    private String zdcode;
    private String provinceid;
    private String cityid;
    private String countryid;
    private String stationtypeid;
    private String isbenchmarkstation;
    private String stationaliasname;
    private String sysprice;
    private String stationid;
    private String jlfh;//交流
    //===新增站点信息 电费uuid：dfuuid， 站点名称：jzname,集团表表类型 jztype,属性property，抄表比例cbbl,站点类型stationtype,所在地区：szdq,线损类型：linelosstype,线损值linelossvalue,倍率：beilv
   private String jzname;
    private String jztype;
    private String property;
    private String cbbl;
    private String stationtype;
    private String szdq;
    private String linelosstype;
    private String linelossvalue;
    private String beilv;
    private String dfuuid;
    //=======countts,summoney,tg, wtg
    private String countts;
    private String summoney;
    private String tg;
    private String wtg;
    private String dlshenhe;
	// Constructors
   
    private String bargainid;
    private String  dlshenhezhuangtai;
    private double pjje;
    private String dbyt;
    private String qsdbdl;//全省定标电量
    
    
    //预支充减查询
   
	private String prepayment_ammeterid;//电表id
	private String money;//金额
    private String pjjeyf;//预付费发票金额   发票
    private String pjjedf;//电费单发票金额   发票
    private String sjyf;//预付费收据金额   收据
    private String sjdf;//电费单收据金额 收据
    
    private String pjjedy;//打印时用的票据金额
    private String thisactualpay;//本次报账金额
    private String yffye;//预付费余额
    private String ce;//差额
    //ydlbz,ef.memo as dftzbz
    private String ydlbz;
    private String dftzbz;
    private String dbname;
    //d.csds, d.cssytime,
    private String csds;
    private String cssytime;
    private String dfbzw;
    private String sdbdl;//省定标电量
    private String cwsh;//财务审核状态
    public String getCwsh() {
		return cwsh;
	}
	public void setCwsh(String cwsh) {
		this.cwsh = cwsh;
	}
	//PAYOPERATOR
	//新增饮水机台数、微机台数、营业办公空调、机房生产空调、远供rru数量、电信共享设备数量、移动共享身设备数量   站点启用状态
	private String ysjts;
	private String wjts;
	private String yybgkt;
	private String jfsckt;
	private String rru;
	private String dxgxsbsl;
	private String ydgxsbsl;
    private String kts;
    private String ktzgl;
    private String ktyps;
    private String kteps;
    private String zdqyzt;
    private String dbqyzt;
    private String bzw;//标志位    1表示电费表里的单子，2表示预付费表的单子
    
    public String getDbqyzt() {
		return dbqyzt;
	}
	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
	}
	private String sdbdf;//省定标电费
    private String csdbdfe;// 超省定标电费额
    
    //--------------2014/03/05新增----------------------
    
    private String zdname;
    private String electricfeeid;
    private String uuid;
    private String csbbl;
    private String csbl;
    private String memo1;
    private String quxian;
    private String rgshzt;
    private String sjshzt;
    private String pjsj;
    private String pjbh;
    private String pjlx;
    private String jfsj;
    private String dbydl;
    private String bzydl;
    private String csdbdfbz;
    
    private String cityzrauditstatus;
    private String countyauditstatus;
    private String countyauditname;
    private String countyaudittime;
    private String cityzrauditname;
    private String cityzraudittime;
    
    private String rgshtgyy;//人工审核通过原因
    
    private String tbtzsq;////特别调整申请
    private String dlid;//电量ID
    
    private String lastyue;//上期余额
    
    private String scb;//生产标
    
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	public String getLastyue() {
		return lastyue;
	}
	public void setLastyue(String lastyue) {
		this.lastyue = lastyue;
	}
	public String getBzw() {
		return bzw;
	}
	public void setBzw(String bzw) {
		this.bzw = bzw;
	}
	public String getDlid() {
		return dlid;
	}
	public void setDlid(String dlid) {
		this.dlid = dlid;
	}
	public String getTbtzsq() {
		return tbtzsq;
	}
	public void setTbtzsq(String tbtzsq) {
		this.tbtzsq = tbtzsq;
	}
	public String getRgshtgyy() {
		return rgshtgyy;
	}
	public void setRgshtgyy(String rgshtgyy) {
		this.rgshtgyy = rgshtgyy;
	}
	public String getCountyauditstatus() {
		return countyauditstatus;
	}
	public void setCountyauditstatus(String countyauditstatus) {
		this.countyauditstatus = countyauditstatus;
	}
	public String getCountyauditname() {
		return countyauditname;
	}
	public void setCountyauditname(String countyauditname) {
		this.countyauditname = countyauditname;
	}
	public String getCountyaudittime() {
		return countyaudittime;
	}
	public void setCountyaudittime(String countyaudittime) {
		this.countyaudittime = countyaudittime;
	}
	public String getCityzrauditname() {
		return cityzrauditname;
	}
	public void setCityzrauditname(String cityzrauditname) {
		this.cityzrauditname = cityzrauditname;
	}
	public String getCityzraudittime() {
		return cityzraudittime;
	}
	public void setCityzraudittime(String cityzraudittime) {
		this.cityzraudittime = cityzraudittime;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getCityzrauditstatus() {
		return cityzrauditstatus;
	}
	public void setCityzrauditstatus(String cityzrauditstatus) {
		this.cityzrauditstatus = cityzrauditstatus;
	}
	public String getElectricfeeid() {
		return electricfeeid;
	}
	public void setElectricfeeid(String electricfeeid) {
		this.electricfeeid = electricfeeid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCsbbl() {
		return csbbl;
	}
	public void setCsbbl(String csbbl) {
		this.csbbl = csbbl;
	}
	public String getCsbl() {
		return csbl;
	}
	public void setCsbl(String csbl) {
		this.csbl = csbl;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getQuxian() {
		return quxian;
	}
	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}
	public String getRgshzt() {
		return rgshzt;
	}
	public void setRgshzt(String rgshzt) {
		this.rgshzt = rgshzt;
	}
	public String getSjshzt() {
		return sjshzt;
	}
	public void setSjshzt(String sjshzt) {
		this.sjshzt = sjshzt;
	}
	public String getPjsj() {
		return pjsj;
	}
	public void setPjsj(String pjsj) {
		this.pjsj = pjsj;
	}
	public String getPjbh() {
		return pjbh;
	}
	public void setPjbh(String pjbh) {
		this.pjbh = pjbh;
	}
	public String getPjlx() {
		return pjlx;
	}
	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}
	public String getJfsj() {
		return jfsj;
	}
	public void setJfsj(String jfsj) {
		this.jfsj = jfsj;
	}
	public String getDbydl() {
		return dbydl;
	}
	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
	}
	public String getBzydl() {
		return bzydl;
	}
	public void setBzydl(String bzydl) {
		this.bzydl = bzydl;
	}
	public String getCsdbdfbz() {
		return csdbdfbz;
	}
	public void setCsdbdfbz(String csdbdfbz) {
		this.csdbdfbz = csdbdfbz;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSdbdf() {
		return sdbdf;
	}
	public void setSdbdf(String sdbdf) {
		this.sdbdf = sdbdf;
	}
	public String getCsdbdfe() {
		return csdbdfe;
	}
	public void setCsdbdfe(String csdbdfe) {
		this.csdbdfe = csdbdfe;
	}
	public String getZdqyzt() {
		return zdqyzt;
	}
	public void setZdqyzt(String zdqyzt) {
		this.zdqyzt = zdqyzt;
	}
	public String getKtyps() {
		return ktyps;
	}
	public void setKtyps(String ktyps) {
		this.ktyps = ktyps;
	}
	public String getKteps() {
		return kteps;
	}
	public void setKteps(String kteps) {
		this.kteps = kteps;
	}
	public String getKts() {
		return kts;
	}
	public void setKts(String kts) {
		this.kts = kts;
	}
	public String getKtzgl() {
		return ktzgl;
	}
	public void setKtzgl(String ktzgl) {
		this.ktzgl = ktzgl;
	}
	public String getYsjts() {
		return ysjts;
	}
	public void setYsjts(String ysjts) {
		this.ysjts = ysjts;
	}
	public String getWjts() {
		return wjts;
	}
	public void setWjts(String wjts) {
		this.wjts = wjts;
	}
	public String getYybgkt() {
		return yybgkt;
	}
	public void setYybgkt(String yybgkt) {
		this.yybgkt = yybgkt;
	}
	public String getJfsckt() {
		return jfsckt;
	}
	public void setJfsckt(String jfsckt) {
		this.jfsckt = jfsckt;
	}
	public String getRru() {
		return rru;
	}
	public void setRru(String rru) {
		this.rru = rru;
	}
	public String getDxgxsbsl() {
		return dxgxsbsl;
	}
	public void setDxgxsbsl(String dxgxsbsl) {
		this.dxgxsbsl = dxgxsbsl;
	}
	public String getYdgxsbsl() {
		return ydgxsbsl;
	}
	public void setYdgxsbsl(String ydgxsbsl) {
		this.ydgxsbsl = ydgxsbsl;
	}
	public String getScdff() {
		return scdff;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getDddfdl() {
		return dddfdl;
	}

	public void setDddfdl(String dddfdl) {
		this.dddfdl = dddfdl;
	}

	public String getDddfdf() {
		return dddfdf;
	}

	public void setDddfdf(String dddfdf) {
		this.dddfdf = dddfdf;
	}
	public String getDddf() {
		return dddf;
	}

	public void setDddf(String dddf) {
		this.dddf = dddf;
	}
	public String getDedhdl() {
		return dedhdl;
	}
	public void setDedhdl(String dedhdl) {
		this.dedhdl = dedhdl;
	}
	public String getZdcq() {
		return zdcq;
	}
	public void setZdcq(String zdcq) {
		this.zdcq = zdcq;
	}
	public String getScdl() {
		return scdl;
	}
	public void setScdl(String scdl) {
		this.scdl = scdl;
	}
	public String getBgdl() {
		return bgdl;
	}
	public void setBgdl(String bgdl) {
		this.bgdl = bgdl;
	}
	public String getYydl() {
		return yydl;
	}
	public void setYydl(String yydl) {
		this.yydl = yydl;
	}
	public String getXxhdl() {
		return xxhdl;
	}
	public void setXxhdl(String xxhdl) {
		this.xxhdl = xxhdl;
	}
	public String getJstzdl() {
		return jstzdl;
	}
	public void setJstzdl(String jstzdl) {
		this.jstzdl = jstzdl;
	}
	public void setScdff(String scdff) {
		this.scdff = scdff;
	}
	public String getBgdf() {
		return bgdf;
	}
	public void setBgdf(String bgdf) {
		this.bgdf = bgdf;
	}
	public String getYydf() {
		return yydf;
	}
	public void setYydf(String yydf) {
		this.yydf = yydf;
	}
	public String getXxhdf() {
		return xxhdf;
	}
	public void setXxhdf(String xxhdf) {
		this.xxhdf = xxhdf;
	}
	public String getJstzdf() {
		return jstzdf;
	}
	public void setJstzdf(String jstzdf) {
		this.jstzdf = jstzdf;
	}
	public String getKjyf() {
		return kjyf;
	}
	public String getSdbdl() {
		return sdbdl;
	}
	public void setSdbdl(String sdbdl) {
		this.sdbdl = sdbdl;
	}
	public void setKjyf(String kjyf) {
		this.kjyf = kjyf;
	}
	public String getJlfh() {
		return jlfh;
	}
	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}
	public String getSjyf() {
		return sjyf;
	}
	public void setSjyf(String sjyf) {
		this.sjyf = sjyf;
	}
	public String getSjdf() {
		return sjdf;
	}
	public void setSjdf(String sjdf) {
		this.sjdf = sjdf;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDayinren() {
		return dayinren;
	}
	public void setDayinren(String dayinren) {
		this.dayinren = dayinren;
	}
	public String getDayintime() {
		return dayintime;
	}
	public void setDayintime(String dayintime) {
		this.dayintime = dayintime;
	}
	public String getStartdate() {
		return startdate;
	}
	public String getYffye() {
		return yffye;
	}
	public void setYffye(String yffye) {
		this.yffye = yffye;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getStopdate() {
		return stopdate;
	}
	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}
	public String getStartdegree() {
		return startdegree;
	}
	public void setStartdegree(String startdegree) {
		this.startdegree = startdegree;
	}
	public String getStopdegree() {
		return stopdegree;
	}
	public void setStopdegree(String stopdegree) {
		this.stopdegree = stopdegree;
	}
	public String getJszq() {
		return jszq;
	}
	public void setJszq(String jszq) {
		this.jszq = jszq;
	}
	public String getBgsign() {
		return bgsign;
	}
	public void setBgsign(String bgsign) {
		this.bgsign = bgsign;
	}
	public String getDbzbdyhh() {
		return dbzbdyhh;
	}
	public void setDbzbdyhh(String dbzbdyhh) {
		this.dbzbdyhh = dbzbdyhh;
	}
	public String getYid() {
		return yid;
	}
	public void setYid(String yid) {
		this.yid = yid;
	}
	public String getCw() {
		return cw;
	}
	public void setCw(String cw) {
		this.cw = cw;
	}
	public String getDbxh() {
		return dbxh;
	}
	public void setDbxh(String dbxh) {
		this.dbxh = dbxh;
	}

	public String getGsf() {
		return gsf;

	}

	public String getPjjedy() {
		return pjjedy;
	}
	public void setPjjedy(String pjjedy) {
		this.pjjedy = pjjedy;
	}

	public void setGsf(String gsf) {
		this.gsf = gsf;
	}
	public String getDianfei() {
		return dianfei;
	}
	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}    
	public String getJnglmk() {
		return jnglmk;
	}
	public void setJnglmk(String jnglmk) {
		this.jnglmk = jnglmk;
	}   
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}     
	public String getErpcode() {
		return erpcode;
	}
	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}
	public String getBieming() {
		return bieming;
	}
	public void setBieming(String bieming) {
		this.bieming = bieming;
	}
	public String getSydate() {
		return sydate;
	}
	public void setSydate(String sydate) {
		this.sydate = sydate;
	}
	public String getYflx() {
		return yflx;
	}
	public void setYflx(String yflx) {
		this.yflx = yflx;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getKongtiao() {
		return kongtiao;
	}
	public void setKongtiao(String kongtiao) {
		this.kongtiao = kongtiao;
	}
	public String getZlfh() {
		return zlfh;
	}
	public void setZlfh(String zlfh) {
		this.zlfh = zlfh;
	}
	public String getXunisign() {
		return xunisign;
	}
	public void setXunisign(String xunisign) {
		this.xunisign = xunisign;
	}

	public String getDfbzw() {
		return dfbzw;
	}
	public void setDfbzw(String dfbzw) {
		this.dfbzw = dfbzw;
	}
	public String getCsds() {
		return csds;
	}
	public void setCsds(String csds) {
		this.csds = csds;
	}
	public String getCssytime() {
		return cssytime;
	}
	public void setCssytime(String cssytime) {
		this.cssytime = cssytime;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getYdlbz() {
		return ydlbz;
	}
	public void setYdlbz(String ydlbz) {
		this.ydlbz = ydlbz;
	}
	public String getDftzbz() {
		return dftzbz;
	}
	public void setDftzbz(String dftzbz) {
		this.dftzbz = dftzbz;
	}
	public String getDbyt() {
		return dbyt;
	}
	public void setDbyt(String dbyt) {
		this.dbyt = dbyt;
	}
	public double getPjje() {
		return pjje;
	}
	public void setPjje(double pjje) {
		this.pjje = pjje;
	}
	public String getDlshenhezhuangtai() {
		return dlshenhezhuangtai;
	}
	
	
	

	public String getCe() {
		return ce;
	}
	public void setCe(String ce) {
		this.ce = ce;
	}
	public String getThisactualpay() {
		return thisactualpay;
	}
	public void setThisactualpay(String thisactualpay) {
		this.thisactualpay = thisactualpay;
	}
	public String getPjjedf() {
		return pjjedf;
	}
	public void setPjjedf(String pjjedf) {
		this.pjjedf = pjjedf;
	}
	public String getPjjeyf() {
		return pjjeyf;
	}
	public void setPjjeyf(String pjjeyf) {
		this.pjjeyf = pjjeyf;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getPrepayment_ammeterid() {
		return prepayment_ammeterid;
	}
	public void setPrepayment_ammeterid(String prepaymentAmmeterid) {
		prepayment_ammeterid = prepaymentAmmeterid;
	}
	public void setDlshenhezhuangtai(String dlshenhezhuangtai) {
	this.dlshenhezhuangtai = dlshenhezhuangtai;
}
	public String getDlshenhe() {
		return dlshenhe;
	}
	public void setDlshenhe(String dlshenhe) {
		this.dlshenhe = dlshenhe;
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
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getFeebz() {
		return feebz;
	}
	public void setFeebz(String feebz) {
		this.feebz = feebz;
	}
	public String getDlsh() {
		return dlsh;
	}
	public void setDlsh(String dlsh) {
		this.dlsh = dlsh;
	}
	public String getDlshzt() {
		return dlshzt;
	}
	public void setDlshzt(String dlshzt) {
		this.dlshzt = dlshzt;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getCountts() {
		return countts;
	}
	public void setCountts(String countts) {
		this.countts = countts;
	}
	public String getSummoney() {
		return summoney;
	}
	public void setSummoney(String summoney) {
		this.summoney = summoney;
	}
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	public String getWtg() {
		return wtg;
	}
	public void setWtg(String wtg) {
		this.wtg = wtg;
	}
	public String getYdsb() {
		return ydsb;
	}
	public void setYdsb(String ydsb) {
		this.ydsb = ydsb;
	}
	public String getDllx() {
		return dllx;
	}
	public void setDllx(String dllx) {
		this.dllx = dllx;
	}
	public String getLiuchenghao() {
		return liuchenghao;
	}
	public void setLiuchenghao(String liuchenghao) {
		this.liuchenghao = liuchenghao;
	}
	public String getDfuuid() {
		return dfuuid;
	}
	public void setDfuuid(String dfuuid) {
		this.dfuuid = dfuuid;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getJztype() {
		return jztype;
	}
	public void setJztype(String jztype) {
		this.jztype = jztype;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCbbl() {
		return cbbl;
	}
	public void setCbbl(String cbbl) {
		this.cbbl = cbbl;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getSzdq() {
		return szdq;
	}
	public void setSzdq(String szdq) {
		this.szdq = szdq;
	}
	public String getLinelosstype() {
		return linelosstype;
	}
	public void setLinelosstype(String linelosstype) {
		this.linelosstype = linelosstype;
	}
	public String getLinelossvalue() {
		return linelossvalue;
	}
	public void setLinelossvalue(String linelossvalue) {
		this.linelossvalue = linelossvalue;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getDianliang() {
		return dianliang;
	}
	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}
	public String getCurrentmonth() {
		return currentmonth;
	}
	public void setCurrentmonth(String currentmonth) {
		this.currentmonth = currentmonth;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
	}
	/** default constructor */
	public String getCityaudittime() {
		return cityaudittime;
	}
	public void setCityaudittime(String cityaudittime) {
		this.cityaudittime = cityaudittime;
	}
	public String getCityauditpensonnel() {
		return cityauditpensonnel;
	}
	public void setCityauditpensonnel(String cityauditpensonnel) {
		this.cityauditpensonnel = cityauditpensonnel;
	}
	public ElectricFeesFormBean() {
	}
	public String getBargainid() {
		return bargainid;
	}
	public void setBargainid(String bargainid) {
		this.bargainid = bargainid;
	}
	public String getElectricfeeId() {
		return electricfeeId;
	}

	public void setElectricfeeId(String electricfeeId) {
		this.electricfeeId = electricfeeId;
	}

	public String getAmmeterdegreeidFk() {
		return ammeterdegreeidFk;
	}

	public void setAmmeterdegreeidFk(String ammeterdegreeidFk) {
		this.ammeterdegreeidFk = ammeterdegreeidFk;
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

	public String getNotetypeid() {
		return notetypeid;
	}

	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}

	public String getNoteno() {
		return noteno;
	}

	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}

	public String getPayoperator() {
		return payoperator;
	}

	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}

	public String getPaydatetime() {
		return paydatetime;
	}

	public void setPaydatetime(String paydatetime) {
		this.paydatetime = paydatetime;
	}

	public String getInputoperator() {
		return inputoperator;
	}

	public void setInputoperator(String inputoperator) {
		this.inputoperator = inputoperator;
	}

	public String getInputdatetime() {
		return inputdatetime;
	}

	public void setInputdatetime(String inputdatetime) {
		this.inputdatetime = inputdatetime;
	}

	public String getAutoauditstatus() {
		return autoauditstatus;
	}

	public void setAutoauditstatus(String autoauditstatus) {
		this.autoauditstatus = autoauditstatus;
	}

	public String getAutoauditdescription() {
		return autoauditdescription;
	}

	public void setAutoauditdescription(String autoauditdescription) {
		this.autoauditdescription = autoauditdescription;
	}

	public String getManualauditstatus() {
		return manualauditstatus;
	}

	public void setManualauditstatus(String manualauditstatus) {
		this.manualauditstatus = manualauditstatus;
	}

	public String getManualauditname() {
		return manualauditname;
	}

	public void setManualauditname(String manualauditname) {
		this.manualauditname = manualauditname;
	}

	public String getManualauditcomment() {
		return manualauditcomment;
	}

	public void setManualauditcomment(String manualauditcomment) {
		this.manualauditcomment = manualauditcomment;
	}

	public String getManualauditdatetime() {
		return manualauditdatetime;
	}

	public void setManualauditdatetime(String manualauditdatetime) {
		this.manualauditdatetime = manualauditdatetime;
	}

	public String getFinancialoperator() {
		return financialoperator;
	}

	public void setFinancialoperator(String financialoperator) {
		this.financialoperator = financialoperator;
	}

	public String getFinancialdatetime() {
		return financialdatetime;
	}

	public void setFinancialdatetime(String financialdatetime) {
		this.financialdatetime = financialdatetime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAccountmonth() {
		return accountmonth;
	}

	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	
	 public String getFinancialauditcomment() {
			return financialauditcomment;
	}

	public void setFinancialauditcomment(String financialauditcomment) {
		this.financialauditcomment = financialauditcomment;
	}
	
	
	  public synchronized ElectricFeesFormBean getElectricFeesInfo(String degreeid) {
		ElectricFeesFormBean bean = new ElectricFeesFormBean();
	    StringBuffer sql = new StringBuffer();
	    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
	    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
	    sql.append("  select e.*,a.endmonth,z.xian,z.dfzflx from electricfees e, ammeterdegrees a, allinfo_view z  " +
	    		"where z.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk and e.electricfee_id = '"+degreeid+"'");
	    
	    DataBase db = new DataBase();
	    try {
	    	System.out.println("getElectricFeesInfo:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	bean.setAccountmonth(rs.getString("accountmonth") != null ? rs.getString("accountmonth") : "");
	    	bean.setActualpay(rs.getString("actualpay") != null ? rs.getString("actualpay") : "");
	    	bean.setAmmeterdegreeidFk(rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "");
	    	bean.setAutoauditdescription(rs.getString("autoauditdescription") != null ? rs.getString("autoauditdescription") : "");
	    	bean.setAutoauditstatus(rs.getString("autoauditstatus") != null ? rs.getString("autoauditstatus") : "");
	    	bean.setElectricfeeId(rs.getString("electricfee_Id") != null ? rs.getString("electricfee_Id") : "");
	    	bean.setFinancialdatetime(rs.getString("financialdatetime") != null ? rs.getString("financialdatetime") : "");
	    	bean.setFinancialoperator(rs.getString("financialoperator") != null ? rs.getString("financialoperator") : "");
	    	bean.setFloatpay(rs.getString("floatpay") != null ? rs.getString("floatpay") : "");
	    	bean.setInputdatetime(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");	    
	    	bean.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");	    
	    	bean.setManualauditcomment(rs.getString("manualauditcomment") != null ? rs.getString("manualauditcomment") : "");	    	
	    	bean.setManualauditdatetime(rs.getString("manualauditdatetime") != null ? rs.getString("manualauditdatetime") : "");
	    	bean.setManualauditname(rs.getString("manualauditname") != null ? rs.getString("manualauditname") : "");
	    	bean.setManualauditstatus(rs.getString("manualauditstatus") != null ? rs.getString("manualauditstatus") : "");
	        bean.setFinancialauditcomment(rs.getString("financialauditcomment") != null ? rs.getString("financialauditcomment") : "");
	    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");
	    	bean.setNoteno(rs.getString("noteno") != null ? rs.getString("noteno") : "");
	    	bean.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : "");
	    	bean.setNotetime(rs.getString("notetime") != null ? rs.getString("notetime") : "");
	    	bean.setPaydatetime(rs.getString("paydatetime") != null ? rs.getString("paydatetime") : "");
	    	bean.setPayoperator(rs.getString("payoperator") != null ? rs.getString("payoperator") : "");
	    	bean.setUnitprice(rs.getString("unitprice") != null ? rs.getString("unitprice") : "");
	    	bean.setKptime(rs.getString("kptime") != null ? rs.getString("kptime") : "");
	    	bean.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx") : "");
	        bean.setCountryid(rs.getString("xian"));
	        bean.setEndmonth(rs.getString("endmonth"));
	      }
	      rs.close();
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }	  
	  //财务与市级审核
	  public synchronized ElectricFeesFormBean getElectricFees(String degreeid) {
			ElectricFeesFormBean bean = new ElectricFeesFormBean();
		    StringBuffer sql = new StringBuffer();
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    sql.append("  select d.dfzflx,e.electricfee_Id from electricview e,dianbiao d where d.dbid=e.dbid and  e.electricfee_id = '"+degreeid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getElectricFeesInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	bean.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx") : "");
		    	bean.setElectricfeeId(rs.getString("electricfee_Id") != null ? rs.getString("electricfee_Id") : "");

		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //电费市级审核获取电量标识
	  public synchronized int getFlag(String degreeid) {
			int flag=0;
		    StringBuffer sql = new StringBuffer();
		    
		    sql.append("  select flag  from ammeterdegrees where ammeterdegreeid = '"+degreeid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getElectricFeesInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	flag=rs.getInt(1);
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return flag;
		  }
	  //取电量表的自动审核状态
	  public synchronized String getZdshbz(String degreeid) {
			String zdshbz="";
		    StringBuffer sql = new StringBuffer();
		    
		    sql.append("  select AUTOAUDITSTATUS  from ammeterdegrees where ammeterdegreeid = '"+degreeid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("AUTOAUDITSTATUS:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	  zdshbz=rs.getString(1);
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return zdshbz;
		  }
	 
	  public synchronized ElectricFeesFormBean getElectricFeesAllInfo(String degreeid) {
		ElectricFeesFormBean bean = new ElectricFeesFormBean();
	    StringBuffer sql = new StringBuffer();
	    sql.append("select am.ammeterid,am.multiplyingpower,am.ammeteruse,am.initialdegree,am.initialdate,(select t.name from indexs t where t.code = am.professionaltypeid and t.type='SSZY') as professionaltypeid," +
	    		"amd.floatdegree,amd.actualdegree,amd.ammeterdegreeid,amd.lastdegree,amd.lastdatetime," +
	    		"amd.thisdegree,amd.thisdatetime,amd.inputoperator,amd.inputdatetime,amd.startmonth,amd.endmonth,amd.memo,zd.jzname,zd.zdcode,dag.agname as xian,(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 5 )) as shi,(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 3 )) as sheng," +
	    		"(select name from indexs ind where ind.type='ZDLX' and zd.jztype=ind.code ) jztype,case when zd.bgsign='1' then '是' else '否' end bgsign," +
	    		"zd.bieming  from zhandian zd, ammeters am, dianduview amd,d_area_grade dag " +
	    		"where zd.id = am.stationid and am.ammeterid = amd.ammeterid_fk and zd.xian=dag.agcode " +
	    		" and amd.ammeterdegreeid="+degreeid);
	    
	    DataBase db = new DataBase();
	    try {
	    	System.out.println("getElectricFeesInfo:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	/*bean.setAccountmonth(rs.getString("accountmonth") != null ? rs.getString("accountmonth") : "");
	    	bean.setActualpay(rs.getString("actualpay") != null ? rs.getString("actualpay") : "");
	    	bean.setAmmeterdegreeidFk(rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "");
	    	bean.setAutoauditdescription(rs.getString("autoauditdescription") != null ? rs.getString("autoauditdescription") : "");
	    	bean.setAutoauditstatus(rs.getString("autoauditstatus") != null ? rs.getString("autoauditstatus") : "");
	    	bean.setElectricfeeId(rs.getString("electricfee_Id") != null ? rs.getString("electricfee_Id") : "");
	    	bean.setFinancialdatetime(rs.getString("financialdatetime") != null ? rs.getString("financialdatetime") : "");
	    	bean.setFinancialoperator(rs.getString("financialoperator") != null ? rs.getString("financialoperator") : "");
	    	bean.setFloatpay(rs.getString("floatpay") != null ? rs.getString("floatpay") : "");
	    	bean.setInputdatetime(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");	    
	    	bean.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");	    
	    	bean.setManualauditcomment(rs.getString("manualauditcomment") != null ? rs.getString("manualauditcomment") : "");	    	
	    	bean.setManualauditdatetime(rs.getString("manualauditdatetime") != null ? rs.getString("manualauditdatetime") : "");
	    	bean.setManualauditname(rs.getString("manualauditname") != null ? rs.getString("manualauditname") : "");
	    	bean.setManualauditstatus(rs.getString("manualauditstatus") != null ? rs.getString("manualauditstatus") : "");
	    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");
	    	bean.setNoteno(rs.getString("noteno") != null ? rs.getString("noteno") : "");
	    	bean.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : "");
	    	bean.setPaydatetime(rs.getString("paydatetime") != null ? rs.getString("paydatetime") : "");
	    	bean.setPayoperator(rs.getString("payoperator") != null ? rs.getString("payoperator") : "");
	    	bean.setUnitprice(rs.getString("unitprice") != null ? rs.getString("unitprice") : "");
	    	*/
	    	//站点 
	    	bean.setStationname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
	    	bean.setZdcode(rs.getString("zdcode") != null ? rs.getString("zdcode") : "");
	    	bean.setStationaliasname(rs.getString("bieming") != null ? rs.getString("bieming") : "");
	    	bean.setStationtypeid(rs.getString("jztype") != null ? rs.getString("jztype") : "");
	    	bean.setProvinceid(rs.getString("sheng") != null ? rs.getString("sheng") : "");
	    	bean.setCityid(rs.getString("shi") != null ? rs.getString("shi") : "");
	    	bean.setCountryid(rs.getString("xian") != null ? rs.getString("xian") : "");
	    	bean.setIsbenchmarkstation(rs.getString("bgsign") != null ? rs.getString("bgsign") : "");
	    	//电表
	    	bean.setAmmererid(rs.getString("ammeterid") != null ? rs.getString("ammeterid") : "");
	    	bean.setProfessionaltypeid(rs.getString("professionaltypeid") != null ? rs.getString("professionaltypeid") : "");
	    	bean.setAmmerertype(rs.getString("ammeteruse") != null ? rs.getString("ammeteruse") : "");
	    	bean.setMultiplyingpower(rs.getString("multiplyingpower") != null ? rs.getString("multiplyingpower") : "");
	    	bean.setInitialdegree(rs.getString("initialdegree") != null ? rs.getString("initialdegree") : "");
	    	bean.setInitialdate(rs.getString("initialdate") != null ? rs.getString("initialdate") : "");
	    	//电量
	    	bean.setAmmeterdegreeid(rs.getString("ammeterdegreeid") != null ? rs.getString("ammeterdegreeid") : "");
	    	bean.setFloatdegree(rs.getString("floatdegree") != null ? rs.getString("floatdegree") : "");
	    	bean.setActualdegree(rs.getString("actualdegree") != null ? rs.getString("actualdegree") : "");
	        
	    	bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
	    	bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
	    	bean.setThisdegree(rs.getString("thisdegree") != null ? rs.getString("thisdegree") : "");
	    	bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs.getString("thisdatetime") : "");
	    	bean.setInputoperatoram(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");
	    	bean.setInputdatetimeam(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");
	    	bean.setStartmonth(rs.getString("startmonth") != null ? rs.getString("startmonth") : "");
	    	bean.setEndmonth(rs.getString("endmonth") != null ? rs.getString("endmonth") : "");
	    	bean.setMemoam(rs.getString("memo") != null ? rs.getString("memo") : "");
	    	
	      }
	      rs.close();
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }
	  
	  
	  /**
	   * 电费录入编辑修改
	   * @param accountId String
	   * @return AccountFormBean
	   */
	  public synchronized ElectricFeesFormBean getElectricDegreeFeesInfo(String degreeid) {
		ElectricFeesFormBean bean = new ElectricFeesFormBean();
	    StringBuffer sql = new StringBuffer();
	    sql.append("select ad.ammeterdegreeid,ad.floatdegree,ad.actualdegree,ad.ammeterid_fk,ad.lastdegree,to_char(ad.lastdatetime,'yyyy-mm-dd') lastdatetime,ad.thisdegree," +
	    		"to_char(ad.thisdatetime,'yyyy-mm-dd') thisdatetime,ad.inputoperator,to_char(ad.inputdatetime,'yyyy-mm-dd') inputdatetime,to_char(ad.startmonth,'yyyy-mm') startmonth,to_char(ad.endmonth,'yyyy-mm') endmonth,ad.blhdl,ad.NETWORKHDL,ad.ADMINISTRATIVEHDL,ad.MARKETHDL,ad.INFORMATIZATIONHDL,ad.BUILDHDL," +
	    		"AD.DDDF AS DDDFDL,AD.MEMO AS MEMOAM," +
	    		"ef.pjje,to_char(ef.accountmonth,'yyyy-mm') accountmonth,ef.actualpay,ef.AMMETERDEGREEID_FK,ef.autoauditdescription,ef.autoauditstatus,ef.electricfee_Id," +
	    		" to_char(ef.financialdatetime,'yyyy-mm-dd') financialdatetime,ef.financialoperator,ef.floatpay,to_char(ef.inputdatetime,'yyyy-mm-dd') inputdatetime,ef.inputoperator,ef.manualauditcomment," +
	    		"to_char(ef.manualauditdatetime,'yyyy-mm-dd') manualauditdatetime,ef.manualauditname,ef.manualauditstatus,ef.financialauditcomment,ef.noteno,ef.notetypeid," +
	    		"to_char(ef.notetime,'yyyy-mm-dd') notetime,to_char(ef.paydatetime,'yyyy-mm-dd') paydatetime,ef.payoperator,ef.unitprice,to_char(ef.kptime,'yyyy-mm-dd') kptime,ef.NETWORKDF,ef.ADMINISTRATIVEDF,ef.MARKETDF,ef.INFORMATIZATIONDF,ef.BUILDDF," +
	    		"EF.DDDF AS DDDFDF,EF.MEMO AS MEMOEF " +
	    		"from dianduview ad,dianfeiview ef " +
	    		"where ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ELECTRICFEE_ID="+degreeid);
	    
	    DataBase db = new DataBase();
	    try {
	    	System.out.println("获取电费电量信息:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	  //电费信息
	    	  bean.setPjje(rs.getDouble("pjje"));
	    	    bean.setAccountmonth(rs.getString("accountmonth") != null ? rs.getString("accountmonth") : "");
		    	bean.setActualpay(rs.getString("actualpay") != null ? rs.getString("actualpay") : "");
		    	bean.setAmmeterdegreeidFk(rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "");
		    	bean.setAutoauditdescription(rs.getString("autoauditdescription") != null ? rs.getString("autoauditdescription") : "");
		    	bean.setAutoauditstatus(rs.getString("autoauditstatus") != null ? rs.getString("autoauditstatus") : "");
		    	bean.setElectricfeeId(rs.getString("electricfee_Id") != null ? rs.getString("electricfee_Id") : "");
		    	bean.setFinancialdatetime(rs.getString("financialdatetime") != null ? rs.getString("financialdatetime") : "");
		    	bean.setFinancialoperator(rs.getString("financialoperator") != null ? rs.getString("financialoperator") : "");
		    	bean.setFloatpay(rs.getString("floatpay") != null ? rs.getString("floatpay") : "");
		    	bean.setInputdatetime(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");	    
		    	bean.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");	    
		    	bean.setManualauditcomment(rs.getString("manualauditcomment") != null ? rs.getString("manualauditcomment") : "");	    	
		    	bean.setManualauditdatetime(rs.getString("manualauditdatetime") != null ? rs.getString("manualauditdatetime") : "");
		    	bean.setManualauditname(rs.getString("manualauditname") != null ? rs.getString("manualauditname") : "");
		    	bean.setManualauditstatus(rs.getString("manualauditstatus") != null ? rs.getString("manualauditstatus") : "");
		    	bean.setFinancialauditcomment(rs.getString("financialauditcomment") != null ? rs.getString("financialauditcomment") : "");
		    	bean.setMemo(rs.getString("memoef") != null ? rs.getString("memoef") : "");
		    	bean.setNoteno(rs.getString("noteno") != null ? rs.getString("noteno") : "");
		    	bean.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : "");
		    	bean.setNotetime(rs.getString("notetime") != null ? rs.getString("notetime") : "");
		    	bean.setPaydatetime(rs.getString("paydatetime") != null ? rs.getString("paydatetime") : "");
		    	bean.setPayoperator(rs.getString("payoperator") != null ? rs.getString("payoperator") : "");
		    	bean.setUnitprice(rs.getString("unitprice") != null ? rs.getString("unitprice") : "");
		    	bean.setKptime(rs.getString("kptime") != null ? rs.getString("kptime") : "");
		    	bean.setScdff(rs.getString("NETWORKDF")!= null ? rs.getString("NETWORKDF") : "");
		    	bean.setBgdf(rs.getString("ADMINISTRATIVEDF")!= null ? rs.getString("ADMINISTRATIVEDF") : "");
		    	bean.setYydf(rs.getString("MARKETDF")!= null ? rs.getString("MARKETDF") : "");
		    	bean.setXxhdf(rs.getString("INFORMATIZATIONDF")!= null ? rs.getString("INFORMATIZATIONDF") : "");
		    	bean.setJstzdf(rs.getString("BUILDDF")!= null ? rs.getString("BUILDDF") : "");
		    	bean.setDddfdf(rs.getString("DDDFDF")!= null ? rs.getString("DDDFDF") : "");//代垫电费

	    	//电量
	    	bean.setAmmeterdegreeid(rs.getString("ammeterdegreeid") != null ? rs.getString("ammeterdegreeid") : "");
	    	bean.setFloatdegree(rs.getString("floatdegree") != null ? rs.getString("floatdegree") : "");
	    	bean.setActualdegree(rs.getString("actualdegree") != null ? rs.getString("actualdegree") : "");	    	
	        bean.setAmmererid(rs.getString("ammeterid_fk") != null ? rs.getString("ammeterid_fk") : "");
	    	bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
	    	bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
	    	bean.setThisdegree(rs.getString("thisdegree") != null ? rs.getString("thisdegree") : "");
	    	bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs.getString("thisdatetime") : "");
	    	bean.setInputoperatoram(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");
	    	bean.setInputdatetimeam(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");
	    	bean.setStartmonth(rs.getString("startmonth") != null ? rs.getString("startmonth") : "");
	    	bean.setEndmonth(rs.getString("endmonth") != null ? rs.getString("endmonth") : "");
	    	bean.setMemoam(rs.getString("memoam") != null ? rs.getString("memoam") : "");
	    	bean.setBlhdl(rs.getString("blhdl") != null ? rs.getString("blhdl") : "");
	    	bean.setScdl(rs.getString("NETWORKHDL")!= null ? rs.getString("NETWORKHDL") : "");
	    	bean.setBgdl(rs.getString("ADMINISTRATIVEHDL")!= null ? rs.getString("ADMINISTRATIVEHDL") : "");
	    	bean.setYydl(rs.getString("MARKETHDL")!= null ? rs.getString("MARKETHDL") : "");
	    	bean.setXxhdl(rs.getString("INFORMATIZATIONHDL")!= null ? rs.getString("INFORMATIZATIONHDL") : "");
	    	bean.setJstzdl(rs.getString("BUILDHDL")!= null ? rs.getString("BUILDHDL") : "");
	    	bean.setDddfdl(rs.getString("DDDFDL")!= null ? rs.getString("DDDFDL") : "");//代垫电量
	      }
	      rs.close();
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }
	  //获取站点电费单价
	  public synchronized ElectricFeesFormBean getJizhanPrice(String ammeterdegreeid) {
		  ElectricFeesFormBean bean = new ElectricFeesFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append(" select distinct zd.dianfei sysprice from zhandian zd, dianbiao am, ammeterdegrees ad where ad.ammeterid_fk = am.dbid and  zd.id  = am.zdid and ad.ammeterdegreeid ='"+ammeterdegreeid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getJizhanPrice:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//系统单价信息
		        bean.setSysprice(rs.getString("sysprice") != null ? rs.getString("sysprice") : "");
		        		     
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  public synchronized List<ElectricFeesFormBean> getzhandian(String shi,String xian,String fzr1,String property,String gsf,String bgsign,String dbyt,String gdfs,
			  String zdcq,String g2,String g3,String kt1,String kt2,String kdsb,String yysb) {
		  List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		  DataBase db = new DataBase();
	   	 
		  try{
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
		    sql.append("(select i.name from indexs i where z.jztype = i.code and i.type = 'ZDLX')as jztype,");
		    sql.append("(select i.name from indexs i where z.property = i.code and i.type = 'ZDSX')as property,");
		    sql.append("(select i.name from indexs i where z.stationtype = i.code  and i.type = 'stationtype')as stationtype,");
		    sql.append("z.jzname,manualauditname_station,");
		    sql.append("(select i2.name from indexs i2 where i2.code=z.yflx)as yftype,");
		    sql.append("(case z.bgsign when '1' then '是' when '0' then '否' END)as bgsign,");
		    sql.append("(select i3.name from indexs i3 where i3.code = z.gdfs)as gdtype,");
		    sql.append("z.fzr,d.dbid,(select i.name from indexs i where d.dfzflx = i.code and i.type='dfzflx') as dfzflx,");
		    sql.append("(select i3.name from indexs i3 where i3.code = z.zdcq)as zdcq,z.id ");
			sql.append("from zhandian z,dianbiao d ");
			sql.append("where z.id=d.zdid(+) and d.dbyt(+)='dbyt01' and z.fzr = '"+fzr1+"' and shi='"+shi+"' and xian='"+xian+"'");
			if(property != null && !property.equals("-1"))sql.append(" and property = '"+property+"'");
			if(gsf != null && !gsf.equals("-1"))sql.append(" and gsf = '"+gsf+"'");
			if(bgsign != null && !bgsign.equals("-1"))sql.append(" and z.bgsign = '"+bgsign+"'");
			if(dbyt != null && !dbyt.equals("-1"))sql.append(" and d.dbyt = '"+dbyt+"'");
			if(gdfs != null && !gdfs.equals("-1"))sql.append(" and z.gdfs = '"+gdfs+"'");
			if(zdcq != null && !zdcq.equals("-1"))sql.append(" and z.zdcq = '"+zdcq+"'");
			if(g2 != null && !g2.equals("-1"))sql.append(" and z.g2 = '"+g2+"'");
			if(g3 != null && !g3.equals("-1"))sql.append(" and z.g3 = '"+g3+"'");
			if(kt1 != null && !kt1.equals("-1"))sql.append(" and z.kt1 = '"+kt1+"'");
			if(kt2 != null && !kt2.equals("-1"))sql.append(" and z.kt2 = '"+kt2+"'");
			if(kdsb != null && !kdsb.equals("-1"))sql.append(" and z.kdsb = '"+kdsb+"'");
			if(yysb != null && !yysb.equals("-1"))sql.append(" and z.yysb = '"+yysb+"'");
			System.out.println(sql);
			ResultSet rs=null;
			rs = db.queryAll(sql.toString()); 
			
	     	 while (rs.next()) {
	     		   ElectricFeesFormBean formbean=new ElectricFeesFormBean();
					formbean.setJztype(rs.getString(1)!=null?rs.getString(1):"");
					formbean.setProperty(rs.getString(2)!=null?rs.getString(2):"");
					formbean.setStationtype(rs.getString(3)!=null?rs.getString(3):"");
					formbean.setJzname(rs.getString(4)!=null?rs.getString(4):"");
					formbean.setManualauditname(rs.getString(5)!=null?rs.getString(5):"");
					formbean.setYflx(rs.getString(6)!=null?rs.getString(6):"");
					formbean.setBgsign(rs.getString(7)!=null?rs.getString(7):"");
					formbean.setGdfs(rs.getString(8)!=null?rs.getString(8):"");
					formbean.setFzr(rs.getString(9)!=null?rs.getString(9):"");
					formbean.setDbid(rs.getString(10)!=null?rs.getString(10):"");
					formbean.setDfzflx(rs.getString(11)!=null?rs.getString(11):"");
					formbean.setZdcq(rs.getString(12)!=null?rs.getString(12):"");
					formbean.setId(rs.getString(13)!=null?rs.getString(13):"");
					list.add(formbean);
			   }
	     	 rs.close();//rsg
			}catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return list;
		  }
	  //合同单获取站点电费单价(通过电表ID获取)
	  public String getJizhanPriceAm(String ammeterid) {
		  //ElectricFeesFormBean bean = new ElectricFeesFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append(" select  zd.dianfei sysprice from zhandian zd, dianbiao d where zd.id = d.zdid and zd.qyzt='1' and d.dbid='"+ammeterid+"'");
		    String jzpriceam = "";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("合同单获取站点电费单价:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//系统单价信息
		    	  jzpriceam = rs.getString("sysprice") != null ? rs.getString("sysprice") : "";
		        		     
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return jzpriceam;
		  }
	  
	  
	  //根据电度   获取所在县code 王代军
	  public synchronized String getXian(String degreeid) {
		    StringBuffer sql = new StringBuffer();
		    sql.append("select z.xian from zhandian z,ammeters d, ammeterdegrees a where a.AMMETERDEGREEID='"+degreeid+"'  and eand a.ammeterid_fk=d.ammeterid and d.stationid=z.id");
		    String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getXian:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      if (rs.next()) {
		    	  result=rs.getString(1) != null ? rs.getString(1) : "";
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return result;
		  }
 
	  
	public String getAmmererid() {
		return ammererid;
	}

	public void setAmmererid(String ammererid) {
		this.ammererid = ammererid;
	}

	public String getMultiplyingpower() {
		return multiplyingpower;
	}

	public void setMultiplyingpower(String multiplyingpower) {
		this.multiplyingpower = multiplyingpower;
	}

	public String getAmmerertype() {
		return ammerertype;
	}

	public void setAmmerertype(String ammerertype) {
		this.ammerertype = ammerertype;
	}

	public String getInitialdegree() {
		return initialdegree;
	}

	public void setInitialdegree(String initialdegree) {
		this.initialdegree = initialdegree;
	}

	public String getInitialdate() {
		return initialdate;
	}

	public void setInitialdate(String initialdate) {
		this.initialdate = initialdate;
	}

	public String getFloatdegree() {
		return floatdegree;
	}

	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}

	public String getActualdegree() {
		return actualdegree;
	}

	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getStationtypeid() {
		return stationtypeid;
	}

	public void setStationtypeid(String stationtypeid) {
		this.stationtypeid = stationtypeid;
	}

	public String getIsbenchmarkstation() {
		return isbenchmarkstation;
	}

	public void setIsbenchmarkstation(String isbenchmarkstation) {
		this.isbenchmarkstation = isbenchmarkstation;
	}

	public String getStationaliasname() {
		return stationaliasname;
	}

	public void setStationaliasname(String stationaliasname) {
		this.stationaliasname = stationaliasname;
	}

	public String getAmmeterdegreeid() {
		return ammeterdegreeid;
	}

	public void setAmmeterdegreeid(String ammeterdegreeid) {
		this.ammeterdegreeid = ammeterdegreeid;
	}

	public String getSysprice() {
		return sysprice;
	}

	public void setSysprice(String sysprice) {
		this.sysprice = sysprice;
	}

	public String getZdcode() {
		return zdcode;
	}

	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
	}

	public String getProfessionaltypeid() {
		return professionaltypeid;
	}

	public void setProfessionaltypeid(String professionaltypeid) {
		this.professionaltypeid = professionaltypeid;
	}

	public String getLastdegree() {
		return lastdegree;
	}

	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}

	public String getLastdatetime() {
		return lastdatetime;
	}

	public void setLastdatetime(String lastdatetime) {
		this.lastdatetime = lastdatetime;
	}

	public String getThisdegree() {
		return thisdegree;
	}

	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}

	public String getThisdatetime() {
		return thisdatetime;
	}

	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}

	public String getInputoperatoram() {
		return inputoperatoram;
	}

	public void setInputoperatoram(String inputoperatoram) {
		this.inputoperatoram = inputoperatoram;
	}

	public String getInputdatetimeam() {
		return inputdatetimeam;
	}

	public void setInputdatetimeam(String inputdatetimeam) {
		this.inputdatetimeam = inputdatetimeam;
	}

	public String getStartmonth() {
		return startmonth;
	}

	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public String getMemoam() {
		return memoam;
	}

	public void setMemoam(String memoam) {
		this.memoam = memoam;
	}

	public String getNotetime() {
		return notetime;
	}

	public void setNotetime(String notetime) {
		this.notetime = notetime;
	}

	public String getKptime() {
		return kptime;
	}

	public void setKptime(String kptime) {
		this.kptime = kptime;
	}
	public void setCityaudit(String cityaudit) {
		this.cityaudit = cityaudit;
	}
	public String getCityaudit() {
		return cityaudit;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}
	public String getEntrypensonnel() {
		return entrypensonnel;
	}
	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}
	public String getEntrytime() {
		return entrytime;
	}
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getZdno() {
		return zdno;
	}
	public void setZdno(String zdno) {
		this.zdno = zdno;
	}
	public String getRgno() {
		return rgno;
	}
	public void setRgno(String rgno) {
		this.rgno = rgno;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getShish() {
		return shish;
	}
	public void setShish(String shish) {
		this.shish = shish;
	}
	public String getCwno() {
		return cwno;
	}
	public void setCwno(String cwno) {
		this.cwno = cwno;
	}
	public String getBzdy() {
		return bzdy;
	}
	public void setBzdy(String bzdy) {
		this.bzdy = bzdy;
	}
	public String getZdzs() {
		return zdzs;
	}
	public void setZdzs(String zdzs) {
		this.zdzs = zdzs;
	}
	public String getJdno() {
		return jdno;
	}
	public void setJdno(String jdno) {
		this.jdno = jdno;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getJsdb() {
		return jsdb;
	}
	public void setJsdb(String jsdb) {
		this.jsdb = jsdb;
	}
	public String getGldb() {
		return gldb;
	}
	public void setGldb(String gldb) {
		this.gldb = gldb;
	}
	public String getCaijizd() {
		return caijizd;
	}
	public void setCaijizd(String caijizd) {
		this.caijizd = caijizd;
	}
	 public String getZdid() {
			return zdid;
		}
	public void setZdid(String zdid) {
			this.zdid = zdid;
		}
	public void setBiaozhi(String biaozhi) {
		this.biaozhi = biaozhi;
	}
	public String getBiaozhi() {
		return biaozhi;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSort() {
		return sort;
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
	public String getFloatdegreeandbl() {
		return floatdegreeandbl;
	}
	public void setFloatdegreeandbl(String floatdegreeandbl) {
		this.floatdegreeandbl = floatdegreeandbl;
	}
	public String getLastfloatdegreeandbl() {
		return lastfloatdegreeandbl;
	}
	public void setLastfloatdegreeandbl(String lastfloatdegreeandbl) {
		this.lastfloatdegreeandbl = lastfloatdegreeandbl;
	}
	public String getLastactualdegree() {
		return lastactualdegree;
	}
	public void setLastactualdegree(String lastactualdegree) {
		this.lastactualdegree = lastactualdegree;
	}
	public String getCshibdfbl() {
		return cshibdfbl;
	}
	public void setCshibdfbl(String cshibdfbl) {
		this.cshibdfbl = cshibdfbl;
	}
	public String getCsbdld() {
		return csbdld;
	}
	public void setCsbdld(String csbdld) {
		this.csbdld = csbdld;
	}
	public String getCshibdld() {
		return cshibdld;
	}
	public void setCshibdld(String cshibdld) {
		this.cshibdld = cshibdld;
	}
	public String getCsbdfjdz() {
		return csbdfjdz;
	}
	public void setCsbdfjdz(String csbdfjdz) {
		this.csbdfjdz = csbdfjdz;
	}
	public String getCshibdjdz() {
		return cshibdjdz;
	}
	public void setCshibdjdz(String cshibdjdz) {
		this.cshibdjdz = cshibdjdz;
	}
	public String getGlbrjl() {
		return glbrjl;
	}
	public void setGlbrjl(String glbrjl) {
		this.glbrjl = glbrjl;
	}
	public String getBzrj() {
		return bzrj;
	}
	public void setBzrj(String bzrj) {
		this.bzrj = bzrj;
	}
	public String getLineandchangeandbl() {
		return lineandchangeandbl;
	}
	public void setLineandchangeandbl(String lineandchangeandbl) {
		this.lineandchangeandbl = lineandchangeandbl;
	}

}