package com.ptac.app.financeprovisional.bean;

public class FinanceZanGuBean {
	//新加功能里面需要的bean
	private String shi;//市
	private String xian;//县
	private String xiaoqu;//乡镇
	private String zdid;//站点id
	private String zdtype;//站点类型
	private String zdsx;//站点属性
	private String zdcount;//站点数量
	private String fzr;//站点负责人
	private String ydl;//用电量
	private String yfjine;//应付金额
	private String sfjine;//实付金额
	private String zaipins;//载频数
	private String jzname;//站点名称
	private String address;//所属地区
	private String stationtype;//站点类型
	private String dfzflx;//电费支付类型
	private String starttime;//暂估起始时间
	private String endtime;//暂估结束时间
	private String tianshu;//暂估天数
	private String dianfei;//单价（元/天）
	private String lastaccountmonth; //最后一次报账抄表时间
	private String zangustartmonth;//暂估起始时间
	private String zanguendmonth;//暂估结束时间
	private String zangumoney;//暂估金额
	private String txje;//摊销金额
	private String dbid;//电表id
	private String dbname;//电表名称
	private String property;//站点属性
	private String qsdbdl;//全省定标电量
	private String zgmonth;//暂估月份
	private String bzw;//暂估起始时间的标志位
	private double zgmoneyhj;//暂估金额总和

	private String bzyf;//报账月份
	private String fzyx;//分专业线(基础查询 专业分摊详细查询)
	private String xxft;//详细分摊(基础查询 专业分摊详细查询)
	private String cssytime;
	private String actualpay;
	private String edhdl;
	//T.XJID,T.MINGCHENG,T.GUIGE
	private String xjid;//监测点下级id
	private String mingcheng;//监测点名称
	private String guige;//监测点规格
	private String sszy;//所属专业
	private String kjkm;//会计科目
	private String qcb;//全成本
	private String zymx;//专业明细
	private String dbili;//所属专业占比
	private String xjbili;//下级占比
	private String sswy;//所属网元
	private String bchd;//标称耗电
	private String wc;//分专业线完成
	private String wf;//未分
	private String yc;//异常
	private String wc1;//详细分摊完成
	private String wf1;//未分
	private String yc1;//异常
	private String htbh;//合同编号
	private String networkdf;////网络运营线电费（生产）
	//合同摊销明细字段
	private String htzje;//合同总金额
	private String qsyf;//开始月份
	private String jsyf;//结束月份
	private String ljff;//累计付费
	private String ljtxje;//累计摊销金额
	private String ljsj;//累计收据金额
	private String ljfp;//累计发票金额
	private String bfp;//补发票金额
	private String lrren;//摊销静态数据录入人
	private String txye;//摊销余额
	private String jsyf1;//开始月份  做判断
	private String ksyf1;//结束月份  做判断
	private String bchtzje;//本次合同总金额





	public String getNetworkdf() {
		return networkdf;
	}
	public void setNetworkdf(String networkdf) {
		this.networkdf = networkdf;
	}
	public double getZgmoneyhj() {
		return zgmoneyhj;
	}
	public void setZgmoneyhj(double zgmoneyhj) {
		this.zgmoneyhj = zgmoneyhj;
	}
	public String getBzw() {
		return bzw;
	}
	public void setBzw(String bzw) {
		this.bzw = bzw;
	}
	public String getZgmonth() {
		return zgmonth;
	}
	public void setZgmonth(String zgmonth) {
		this.zgmonth = zgmonth;
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
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getBchtzje() {
		return bchtzje;
	}
	public void setBchtzje(String bchtzje) {
		this.bchtzje = bchtzje;
	}
	public String getJsyf1() {
		return jsyf1;
	}
	public void setJsyf1(String jsyf1) {
		this.jsyf1 = jsyf1;
	}
	public String getKsyf1() {
		return ksyf1;
	}
	public void setKsyf1(String ksyf1) {
		this.ksyf1 = ksyf1;
	}
	public String getLrren() {
		return lrren;
	}
	public void setLrren(String lrren) {
		this.lrren = lrren;
	}
	public String getTxye() {
		return txye;
	}
	public void setTxye(String txye) {
		this.txye = txye;
	}
	public String getBfp() {
		return bfp;
	}
	public void setBfp(String bfp) {
		this.bfp = bfp;
	}
	public String getHtzje() {
		return htzje;
	}
	public void setHtzje(String htzje) {
		this.htzje = htzje;
	}
	public String getQsyf() {
		return qsyf;
	}
	public void setQsyf(String qsyf) {
		this.qsyf = qsyf;
	}
	public String getJsyf() {
		return jsyf;
	}
	public void setJsyf(String jsyf) {
		this.jsyf = jsyf;
	}

	public String getLjff() {
		return ljff;
	}
	public void setLjff(String ljff) {
		this.ljff = ljff;
	}
	public String getLjtxje() {
		return ljtxje;
	}
	public void setLjtxje(String ljtxje) {
		this.ljtxje = ljtxje;
	}
	public String getLjsj() {
		return ljsj;
	}
	public void setLjsj(String ljsj) {
		this.ljsj = ljsj;
	}
	public String getLjfp() {
		return ljfp;
	}
	public void setLjfp(String ljfp) {
		this.ljfp = ljfp;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getLastaccountmonth() {
		return lastaccountmonth;
	}
	public void setLastaccountmonth(String lastaccountmonth) {
		this.lastaccountmonth = lastaccountmonth;
	}
	public String getZangustartmonth() {
		return zangustartmonth;
	}
	public void setZangustartmonth(String zangustartmonth) {
		this.zangustartmonth = zangustartmonth;
	}
	public String getZanguendmonth() {
		return zanguendmonth;
	}
	public void setZanguendmonth(String zanguendmonth) {
		this.zanguendmonth = zanguendmonth;
	}
	public String getZangumoney() {
		return zangumoney;
	}
	public void setZangumoney(String zangumoney) {
		this.zangumoney = zangumoney;
	}

	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getWc() {
		return wc;
	}
	public void setWc(String wc) {
		this.wc = wc;
	}
	public String getWf() {
		return wf;
	}
	public void setWf(String wf) {
		this.wf = wf;
	}
	public String getYc() {
		return yc;
	}
	public void setYc(String yc) {
		this.yc = yc;
	}
	public String getWc1() {
		return wc1;
	}
	public void setWc1(String wc1) {
		this.wc1 = wc1;
	}
	public String getWf1() {
		return wf1;
	}
	public void setWf1(String wf1) {
		this.wf1 = wf1;
	}
	public String getYc1() {
		return yc1;
	}
	public void setYc1(String yc1) {
		this.yc1 = yc1;
	}
	public String getBchd() {
		return bchd;
	}
	public void setBchd(String bchd) {
		this.bchd = bchd;
	}
	public String getSswy() {
		return sswy;
	}
	public void setSswy(String sswy) {
		this.sswy = sswy;
	}
	public String getBzyf() {
		return bzyf;
	}
	public void setBzyf(String bzyf) {
		this.bzyf = bzyf;
	}
	public String getXjid() {
		return xjid;
	}
	public void setXjid(String xjid) {
		this.xjid = xjid;
	}
	public String getMingcheng() {
		return mingcheng;
	}
	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getSszy() {
		return sszy;
	}
	public void setSszy(String sszy) {
		this.sszy = sszy;
	}
	public String getKjkm() {
		return kjkm;
	}
	public void setKjkm(String kjkm) {
		this.kjkm = kjkm;
	}
	public String getQcb() {
		return qcb;
	}
	public void setQcb(String qcb) {
		this.qcb = qcb;
	}
	public String getZymx() {
		return zymx;
	}
	public void setZymx(String zymx) {
		this.zymx = zymx;
	}
	public String getDbili() {
		return dbili;
	}
	public void setDbili(String dbili) {
		this.dbili = dbili;
	}
	public String getXjbili() {
		return xjbili;
	}
	public void setXjbili(String xjbili) {
		this.xjbili = xjbili;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getFzyx() {
		return fzyx;
	}
	public void setFzyx(String fzyx) {
		this.fzyx = fzyx;
	}
	public String getXxft() {
		return xxft;
	}
	public void setXxft(String xxft) {
		this.xxft = xxft;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getCssytime() {
		return cssytime;
	}
	public void setCssytime(String cssytime) {
		this.cssytime = cssytime;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getTxje() {
		return txje;
	}
	public void setTxje(String txje) {
		this.txje = txje;
	}

	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTianshu() {
		return tianshu;
	}
	public void setTianshu(String tianshu) {
		this.tianshu = tianshu;
	}
	public String getDianfei() {
		return dianfei;
	}
	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
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
	public String getZdtype() {
		return zdtype;
	}
	public void setZdtype(String zdtype) {
		this.zdtype = zdtype;
	}
	public String getZdcount() {
		return zdcount;
	}
	public void setZdcount(String zdcount) {
		this.zdcount = zdcount;
	}
	public String getYdl() {
		return ydl;
	}
	public void setYdl(String ydl) {
		this.ydl = ydl;
	}
	public String getYfjine() {
		return yfjine;
	}
	public void setYfjine(String yfjine) {
		this.yfjine = yfjine;
	}
	public String getSfjine() {
		return sfjine;
	}
	public void setSfjine(String sfjine) {
		this.sfjine = sfjine;
	}
	public String getZaipins() {
		return zaipins;
	}
	public void setZaipins(String zaipins) {
		this.zaipins = zaipins;
	}


}
