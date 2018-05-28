package com.noki.newfunction.javabean;

public class Zdinfo {

private String jydgxsb;
private String jdxgxsb;
private String jg2xq;
private String jg3sq;
private String shicode;
private String id;
private String idd;
private String shi;
private String xian;
private String xiaoqu;
private String zdname;//站点名称
private String property;//站点属性
private String zdid;//站点id
private String shijxf;//市级下发
//原始11大类
private String g2;//是否2g
private String g3;//是否3g
private String changjia;//厂家
private String zp;//载频数量
private String zs;//载扇数量
private String jztype;//基站类型
private String shebei;//设备
private String bbu;//bbu数量
private String rru;//rru数量
private String ydshebei;//移动设备数量
private String gxgwsl;//共享固网数量
private String nhbz;//能耗标准
private String chanquan;//产权
private String sjdl;//实际电量
private String cbdl;//超标电量
private String bzpld;//标准片偏离度
private String zlfh;//直流负荷
private String jlfh;//交流负荷
private String edhdl;//额定耗电量
private String cbsj;//超标月份(报账月份)
private String sjpd;//省级派单 0 未  1 已  2 地市退单
private String sjpdsj;//省级派单时间
private String  dsqs;//地市签收状态 0 未 1 已
private String dsqssj;//地市签收时间
private String dspd;//地市派单
private String  dspdsj;//地市派单时间
private String qxqs;//区县签收 状态 0 未  1 已
private String qxqssj;//区县签收时间
private String qxpd;//区县派单状态 0 未 1 已
private String qxpdsj;//区县派单时间
private String qxtjsh;//区县提交审核(表示该点已经进行了核实) 0未核实  1已核实
private String  shijsh;//市级审核标志 0未审核  1 审核不通过  2 审核通过
private String shijshsj;//市级审核时间
private String shengjsh;//省级审核标志 0未审核 1 审核不通过 2 审核通过
private String shengjshsj;//省级审核时间
private String sjxf;//省级下发
private String qxzgtj;//区县整改提交标志（下发）
private String sjshbz;//市级审核标志（下发）
private String shengjshbz;//省级审核标志（下发）
private String ydgxsb;
private String g2xq;//2g小区
private String g3sq;//3g扇区

//新十一大类
private String  xg2;//是否2g
private String  xg3;//是否3g
private String  xchangjia;//厂家
private int 	xzp;//载频数量
private int 	xzs;//载扇数量
private String  xjztype;//基站类型
private String  xshebei;//设备
private int 	xbbu;//bbu数量
private int 	xrru;//rru数量
private int 	xydshebei;//移动设备数量
private int 	xgxgwsl;//共享固网数量

private String lrsj;//录入时间
private String lrr;//录入人


//-----------------------------cbzdxx字段-----
private String cid;//cbzdxx主键id
private String wjid;//外键id
private String zgyq;//整改要求
private String sjzrr;//省级负责人
private String lch;//整改批次号
private String sjlrr;//省级录入人
private String sjlrsj;//省级录入时间
private String csms;//区县审核后描述
private String yyfx;//区县原因分析
private String cszrr;//区县测试责任人
private String dsfj;//地市核实附件
private String  sjfj;//省级下发整改文件
private String qxlrr;//区县录入人
private String qxlrsj;//区县录入时间
private String sjyqwcsj;//省级要求完成时间
private String tdyy;//省级退单原因
private String sjtdyy;//市级退单原因
private String FKSJ;//反馈时间
private String sjyqwtgyy;//省级要求未通过原因
private String dgpch;//单个站点批次号
private String yppch;//一批站点批次号
private String bt;//标题
private String wcsj1;//完成时间 //超标站点查询
private String sjname;//省级附件名称
private String qxname;//区县附件名称
private String zhssdl;//找回损失电量
private String yssdbdl;//验收省定标电量
//--------------------------------// 起码	止码	上次抄表时间	本次抄表时间	周期	用电量	费用	 报账月份------------
private String qm;
private String zm;
private String bccbsj;
private String sccbsj;
private String zq;
private String ydl;
private String fy;
private String bzyf;
//--------------------新增--------------------------------------
private	String szdq;//所在地区  XFZGYQ WCSJ

private	String xfzgyq;//整改要求（下发）

private	String wcsj;//要求完成时间
//---------------------cbzdxf表中字段-------------------------------------
private String bwjid;//外键id
private String shengjlrr;//省级录入人
private String shengjlrsj;//省级录入时间
private String xffj;//下发整改要求附件
private String wcsm;//完成说明
private String qxwcsj;//区县实际完成时间
private String zgzrr;//整改负责人
private String zgfj;//整改附件
private String filepath;//附件地址
private String qxpath;//区县附件路径
private String sjpath;//省级附件名称(下发)
private String qxname1;///区县附件名称(下发)
//-----------------------cbzdxx 新增字段----------------------------------------------
private String dbds;//电表读数
private String KGDYZLFH;//开关电源直流负荷
private String YDGXSBZLFH;//移动共享设备直流负荷
private String DXGXSBZLFH;//电信共享设备直流负荷
private String GYGXSBZLFH;//固移共享设备直流负荷
private String ZYYGSBZLFH;//直流远供设备直流负荷
private String dxgxsb;
private String kgdyzlfh;//开关电源直流负荷
//-------2013-09-28新增字段------------------------------------------------------------
private String zlzfh;//直流总负荷
private String jlzfh;//交流总负荷
private String bdhdl;//本地额定耗电量
private String qsdb;//全省定标
private String qsdbdl;//全省定标电量
//-------SCB新增字段------------------------------------------------------------
private String scb;//站点生产标（省定标电量（不含空调））
//-------新增字段------------------------------------------------------------
//ZDDL.DL,ZDDL.BL,ZDDL.DLCB,(ZDDL.DLCB * 0.9) AS DFCB
private String sjhdl;//实际耗电量 /天
private String bl;//比例超标比例 （实际-省定标电量）/全省定标电量
private String dlcb;//电量超标/天
private String dfcb;//电费超标/天

//-------新增字段------------------------------------------------------------
private String sjshsj;
private String zhdlwcsj;
private String shyj;
private String cbbl;//超标比例


public String getSjhdl() {
	return sjhdl;
}
public void setSjhdl(String sjhdl) {
	this.sjhdl = sjhdl;
}
public String getBl() {
	return bl;
}
public void setBl(String bl) {
	this.bl = bl;
}
public String getDlcb() {
	return dlcb;
}
public void setDlcb(String dlcb) {
	this.dlcb = dlcb;
}
public String getDfcb() {
	return dfcb;
}
public void setDfcb(String dfcb) {
	this.dfcb = dfcb;
}
public String getQsdbdl() {
	return qsdbdl;
}
public void setQsdbdl(String qsdbdl) {
	this.qsdbdl = qsdbdl;
}
public String getProperty() {
	return property;
}
public void setProperty(String property) {
	this.property = property;
}
public String getScb() {
	return scb;
}
public void setScb(String scb) {
	this.scb = scb;
}
public String getKgdyzlfh() {
	return kgdyzlfh;
}
public void setKgdyzlfh(String kgdyzlfh) {
	this.kgdyzlfh = kgdyzlfh;
}
public String getJydgxsb() {
	return jydgxsb;
}
public void setJydgxsb(String jydgxsb) {
	this.jydgxsb = jydgxsb;
}
public String getJdxgxsb() {
	return jdxgxsb;
}
public void setJdxgxsb(String jdxgxsb) {
	this.jdxgxsb = jdxgxsb;
}
public String getJg2xq() {
	return jg2xq;
}
public void setJg2xq(String jg2xq) {
	this.jg2xq = jg2xq;
}
public String getJg3sq() {
	return jg3sq;
}
public void setJg3sq(String jg3sq) {
	this.jg3sq = jg3sq;
}
public String getDxgxsb() {
	return dxgxsb;
}
public void setDxgxsb(String dxgxsb) {
	this.dxgxsb = dxgxsb;
}
public String getShicode() {
	return shicode;
}
public void setShicode(String shicode) {
	this.shicode = shicode;
}
public String getIdd() {
	return idd;
}
public void setIdd(String idd) {
	this.idd = idd;
}
public String getQxzgtj() {
	return qxzgtj;
}
public void setQxzgtj(String qxzgtj) {
	this.qxzgtj = qxzgtj;
}
public String getShengjshbz() {
	return shengjshbz;
}
public void setShengjshbz(String shengjshbz) {
	this.shengjshbz = shengjshbz;
}
public String getSjxf() {
	return sjxf;
}
public void setSjxf(String sjxf) {
	this.sjxf = sjxf;
}
public String getSjshbz() {
	return sjshbz;
}
public void setSjshbz(String sjshbz) {
	this.sjshbz = sjshbz;
}
public String getCid() {
	return cid;
}
public void setCid(String cid) {
	this.cid = cid;
}
public String getTdyy() {
	return tdyy;
}
public void setTdyy(String tdyy) {
	this.tdyy = tdyy;
}


public String getDgpch() {
	return dgpch;
}
public void setDgpch(String dgpch) {
	this.dgpch = dgpch;
}
public String getYppch() {
	return yppch;
}
public void setYppch(String yppch) {
	this.yppch = yppch;
}
public String getBt() {
	return bt;
}
public void setBt(String bt) {
	this.bt = bt;
}
public String getSjyqwtgyy() {
	return sjyqwtgyy;
}
public void setSjyqwtgyy(String sjyqwtgyy) {
	this.sjyqwtgyy = sjyqwtgyy;
}
public String getFKSJ() {
	return FKSJ;
}
public void setFKSJ(String fKSJ) {
	FKSJ = fKSJ;
}
public String getSjyqwcsj() {
	return sjyqwcsj;
}
public void setSjyqwcsj(String sjyqwcsj) {
	this.sjyqwcsj = sjyqwcsj;
}
public String getYdgxsb() {
	return ydgxsb;
}
public void setYdgxsb(String ydgxsb) {
	this.ydgxsb = ydgxsb;
}
public String getShijxf() {
	return shijxf;
}
public void setShijxf(String shijxf) {
	this.shijxf = shijxf;
}

public String getXfzgyq() {
	return xfzgyq;
}
public void setXfzgyq(String xfzgyq) {
	this.xfzgyq = xfzgyq;
}
public String getWcsj() {
	return wcsj;
}
public void setWcsj(String wcsj) {
	this.wcsj = wcsj;
}
public String getXg2() {
	return xg2;
}
public void setXg2(String xg2) {
	this.xg2 = xg2;
}
public String getXg3() {
	return xg3;
}
public void setXg3(String xg3) {
	this.xg3 = xg3;
}
public String getXchangjia() {
	return xchangjia;
}
public void setXchangjia(String xchangjia) {
	this.xchangjia = xchangjia;
}
public int getXzp() {
	return xzp;
}
public void setXzp(int xzp) {
	this.xzp = xzp;
}
public int getXzs() {
	return xzs;
}
public void setXzs(int xzs) {
	this.xzs = xzs;
}
public String getXjztype() {
	return xjztype;
}
public void setXjztype(String xjztype) {
	this.xjztype = xjztype;
}
public String getXshebei() {
	return xshebei;
}
public void setXshebei(String xshebei) {
	this.xshebei = xshebei;
}
public int getXbbu() {
	return xbbu;
}
public void setXbbu(int xbbu) {
	this.xbbu = xbbu;
}
public int getXrru() {
	return xrru;
}
public void setXrru(int xrru) {
	this.xrru = xrru;
}
public int getXydshebei() {
	return xydshebei;
}
public void setXydshebei(int xydshebei) {
	this.xydshebei = xydshebei;
}
public int getXgxgwsl() {
	return xgxgwsl;
}
public void setXgxgwsl(int xgxgwsl) {
	this.xgxgwsl = xgxgwsl;
}


public String getLrsj() {
	return lrsj;
}
public void setLrsj(String lrsj) {
	this.lrsj = lrsj;
}
public String getLrr() {
	return lrr;
}
public void setLrr(String lrr) {
	this.lrr = lrr;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getSzdq() {
	return szdq;
}
public void setSzdq(String szdq) {
	this.szdq = szdq;
}
public String getQm() {
	return qm;
}
public void setQm(String qm) {
	this.qm = qm;
}
public String getZm() {
	return zm;
}
public void setZm(String zm) {
	this.zm = zm;
}
public String getBccbsj() {
	return bccbsj;
}
public void setBccbsj(String bccbsj) {
	this.bccbsj = bccbsj;
}
public String getSccbsj() {
	return sccbsj;
}
public void setSccbsj(String sccbsj) {
	this.sccbsj = sccbsj;
}
public String getZq() {
	return zq;
}
public void setZq(String zq) {
	this.zq = zq;
}
public String getYdl() {
	return ydl;
}
public void setYdl(String ydl) {
	this.ydl = ydl;
}
public String getFy() {
	return fy;
}
public void setFy(String fy) {
	this.fy = fy;
}
public String getBzyf() {
	return bzyf;
}
public void setBzyf(String bzyf) {
	this.bzyf = bzyf;
}
public String getWjid() {
	return wjid;
}
public void setWjid(String wjid) {
	this.wjid = wjid;
}
public String getZgyq() {
	return zgyq;
}
public void setZgyq(String zgyq) {
	this.zgyq = zgyq;
}
public String getSjzrr() {
	return sjzrr;
}
public void setSjzrr(String sjzrr) {
	this.sjzrr = sjzrr;
}
public String getLch() {
	return lch;
}
public void setLch(String lch) {
	this.lch = lch;
}
public String getSjlrr() {
	return sjlrr;
}
public void setSjlrr(String sjlrr) {
	this.sjlrr = sjlrr;
}
public String getSjlrsj() {
	return sjlrsj;
}
public void setSjlrsj(String sjlrsj) {
	this.sjlrsj = sjlrsj;
}
public String getCsms() {
	return csms;
}
public void setCsms(String csms) {
	this.csms = csms;
}
public String getYyfx() {
	return yyfx;
}
public void setYyfx(String yyfx) {
	this.yyfx = yyfx;
}
public String getCszrr() {
	return cszrr;
}
public void setCszrr(String cszrr) {
	this.cszrr = cszrr;
}
public String getDsfj() {
	return dsfj;
}
public void setDsfj(String dsfj) {
	this.dsfj = dsfj;
}
public String getSjfj() {
	return sjfj;
}
public void setSjfj(String sjfj) {
	this.sjfj = sjfj;
}
public String getQxlrr() {
	return qxlrr;
}
public void setQxlrr(String qxlrr) {
	this.qxlrr = qxlrr;
}
public String getQxlrsj() {
	return qxlrsj;
}
public void setQxlrsj(String qxlrsj) {
	this.qxlrsj = qxlrsj;
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
public String getZdname() {
	return zdname;
}
public void setZdname(String zdname) {
	this.zdname = zdname;
}
public String getZdid() {
	return zdid;
}
public void setZdid(String zdid) {
	this.zdid = zdid;
}
public String getG2() {
	return g2;
}
public void setG2(String g2) {
	this.g2 = g2;
}
public String getG3() {
	return g3;
}
public void setG3(String g3) {
	this.g3 = g3;
}
public String getChangjia() {
	return changjia;
}
public void setChangjia(String changjia) {
	this.changjia = changjia;
}
public String getZp() {
	return zp;
}
public void setZp(String zp) {
	this.zp = zp;
}
public String getZs() {
	return zs;
}
public void setZs(String zs) {
	this.zs = zs;
}
public String getJztype() {
	return jztype;
}
public void setJztype(String jztype) {
	this.jztype = jztype;
}
public String getShebei() {
	return shebei;
}
public void setShebei(String shebei) {
	this.shebei = shebei;
}
public String getBbu() {
	return bbu;
}
public void setBbu(String bbu) {
	this.bbu = bbu;
}
public String getRru() {
	return rru;
}
public void setRru(String rru) {
	this.rru = rru;
}
public String getYdshebei() {
	return ydshebei;
}
public void setYdshebei(String ydshebei) {
	this.ydshebei = ydshebei;
}
public String getGxgwsl() {
	return gxgwsl;
}
public void setGxgwsl(String gxgwsl) {
	this.gxgwsl = gxgwsl;
}
public String getNhbz() {
	return nhbz;
}
public void setNhbz(String nhbz) {
	this.nhbz = nhbz;
}
public String getChanquan() {
	return chanquan;
}
public void setChanquan(String chanquan) {
	this.chanquan = chanquan;
}
public String getSjdl() {
	return sjdl;
}
public void setSjdl(String sjdl) {
	this.sjdl = sjdl;
}
public String getCbdl() {
	return cbdl;
}
public void setCbdl(String cbdl) {
	this.cbdl = cbdl;
}
public String getBzpld() {
	return bzpld;
}
public void setBzpld(String bzpld) {
	this.bzpld = bzpld;
}
public String getZlfh() {
	return zlfh;
}
public void setZlfh(String zlfh) {
	this.zlfh = zlfh;
}
public String getJlfh() {
	return jlfh;
}
public void setJlfh(String jlfh) {
	this.jlfh = jlfh;
}
public String getEdhdl() {
	return edhdl;
}
public void setEdhdl(String edhdl) {
	this.edhdl = edhdl;
}
public String getCbsj() {
	return cbsj;
}
public void setCbsj(String cbsj) {
	this.cbsj = cbsj;
}
public String getSjpd() {
	return sjpd;
}
public void setSjpd(String sjpd) {
	this.sjpd = sjpd;
}
public String getSjpdsj() {
	return sjpdsj;
}
public void setSjpdsj(String sjpdsj) {
	this.sjpdsj = sjpdsj;
}
public String getDsqs() {
	return dsqs;
}
public void setDsqs(String dsqs) {
	this.dsqs = dsqs;
}
public String getDsqssj() {
	return dsqssj;
}
public void setDsqssj(String dsqssj) {
	this.dsqssj = dsqssj;
}
public String getDspd() {
	return dspd;
}
public void setDspd(String dspd) {
	this.dspd = dspd;
}
public String getDspdsj() {
	return dspdsj;
}
public void setDspdsj(String dspdsj) {
	this.dspdsj = dspdsj;
}
public String getQxqs() {
	return qxqs;
}
public void setQxqs(String qxqs) {
	this.qxqs = qxqs;
}
public String getQxqssj() {
	return qxqssj;
}
public void setQxqssj(String qxqssj) {
	this.qxqssj = qxqssj;
}
public String getQxpd() {
	return qxpd;
}
public void setQxpd(String qxpd) {
	this.qxpd = qxpd;
}
public String getQxpdsj() {
	return qxpdsj;
}
public void setQxpdsj(String qxpdsj) {
	this.qxpdsj = qxpdsj;
}
public String getQxtjsh() {
	return qxtjsh;
}
public void setQxtjsh(String qxtjsh) {
	this.qxtjsh = qxtjsh;
}
public String getShijsh() {
	return shijsh;
}
public void setShijsh(String shijsh) {
	this.shijsh = shijsh;
}
public String getShijshsj() {
	return shijshsj;
}
public void setShijshsj(String shijshsj) {
	this.shijshsj = shijshsj;
}
public String getShengjsh() {
	return shengjsh;
}
public void setShengjsh(String shengjsh) {
	this.shengjsh = shengjsh;
}
public String getShengjshsj() {
	return shengjshsj;
}
public void setShengjshsj(String shengjshsj) {
	this.shengjshsj = shengjshsj;
}
public String getBwjid() {
	return bwjid;
}
public void setBwjid(String bwjid) {
	this.bwjid = bwjid;
}
public String getShengjlrr() {
	return shengjlrr;
}
public void setShengjlrr(String shengjlrr) {
	this.shengjlrr = shengjlrr;
}
public String getShengjlrsj() {
	return shengjlrsj;
}
public void setShengjlrsj(String shengjlrsj) {
	this.shengjlrsj = shengjlrsj;
}
public String getXffj() {
	return xffj;
}
public void setXffj(String xffj) {
	this.xffj = xffj;
}
public String getWcsm() {
	return wcsm;
}
public void setWcsm(String wcsm) {
	this.wcsm = wcsm;
}
public String getQxwcsj() {
	return qxwcsj;
}
public void setQxwcsj(String qxwcsj) {
	this.qxwcsj = qxwcsj;
}
public String getZgzrr() {
	return zgzrr;
}
public void setZgzrr(String zgzrr) {
	this.zgzrr = zgzrr;
}
public String getZgfj() {
	return zgfj;
}
public void setZgfj(String zgfj) {
	this.zgfj = zgfj;
}
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public String getQxpath() {
	return qxpath;
}
public void setQxpath(String qxpath) {
	this.qxpath = qxpath;
}
public String getDbds() {
	return dbds;
}
public void setDbds(String dbds) {
	this.dbds = dbds;
}
public String getKGDYZLFH() {
	return KGDYZLFH;
}
public void setKGDYZLFH(String kGDYZLFH) {
	KGDYZLFH = kGDYZLFH;
}
public String getYDGXSBZLFH() {
	return YDGXSBZLFH;
}
public void setYDGXSBZLFH(String yDGXSBZLFH) {
	YDGXSBZLFH = yDGXSBZLFH;
}
public String getDXGXSBZLFH() {
	return DXGXSBZLFH;
}
public void setDXGXSBZLFH(String dXGXSBZLFH) {
	DXGXSBZLFH = dXGXSBZLFH;
}
public String getGYGXSBZLFH() {
	return GYGXSBZLFH;
}
public void setGYGXSBZLFH(String gYGXSBZLFH) {
	GYGXSBZLFH = gYGXSBZLFH;
}
public String getZYYGSBZLFH() {
	return ZYYGSBZLFH;
}
public void setZYYGSBZLFH(String zYYGSBZLFH) {
	ZYYGSBZLFH = zYYGSBZLFH;
}
public String getG2xq() {
	return g2xq;
}
public void setG2xq(String g2xq) {
	this.g2xq = g2xq;
}
public String getG3sq() {
	return g3sq;
}
public void setG3sq(String g3sq) {
	this.g3sq = g3sq;
}
public String getWcsj1() {
	return wcsj1;
}
public void setWcsj1(String wcsj1) {
	this.wcsj1 = wcsj1;
}
public String getSjname() {
	return sjname;
}
public void setSjname(String sjname) {
	this.sjname = sjname;
}
public String getQxname() {
	return qxname;
}
public void setQxname(String qxname) {
	this.qxname = qxname;
}
public String getSjpath() {
	return sjpath;
}
public void setSjpath(String sjpath) {
	this.sjpath = sjpath;
}
public String getQxname1() {
	return qxname1;
}
public void setQxname1(String qxname1) {
	this.qxname1 = qxname1;
}

public String getZlzfh() {
	return zlzfh;
}
public void setZlzfh(String zlzfh) {
	this.zlzfh = zlzfh;
}
public String getJlzfh() {
	return jlzfh;
}
public void setJlzfh(String jlzfh) {
	this.jlzfh = jlzfh;
}
public String getBdhdl() {
	return bdhdl;
}
public void setBdhdl(String bdhdl) {
	this.bdhdl = bdhdl;
}
public String getQsdb() {
	return qsdb;
}
public void setQsdb(String qsdb) {
	this.qsdb = qsdb;
}

public String getSjtdyy() {
	return sjtdyy;
}
public void setSjtdyy(String sjtdyy) {
	this.sjtdyy = sjtdyy;
}
public String getZhssdl() {
	return zhssdl;
}
public void setZhssdl(String zhssdl) {
	this.zhssdl = zhssdl;
}
public String getYssdbdl() {
	return yssdbdl;
}
public void setYssdbdl(String yssdbdl) {
	this.yssdbdl = yssdbdl;
}
public String getSjshsj() {
	return sjshsj;
}
public void setSjshsj(String sjshsj) {
	this.sjshsj = sjshsj;
}
public String getZhdlwcsj() {
	return zhdlwcsj;
}
public void setZhdlwcsj(String zhdlwcsj) {
	this.zhdlwcsj = zhdlwcsj;
}
public String getShyj() {
	return shyj;
}
public void setShyj(String shyj) {
	this.shyj = shyj;
}
public String getCbbl() {
	return cbbl;
}
public void setCbbl(String cbbl) {
	this.cbbl = cbbl;
}



}
