package com.ptac.app.calibstat.bean;

/**
 * @author 李靖
 * @see 定标统计查询Bean
 */
public class CalibStatBean {
	
	private String city;//地市	
	private String xian;//区县	
	private String xiaoqu;//乡镇	
	private String zdname;//站点名称
	private String zdid;//站点ID	
	private String zdsx;//站点属性
	private String zdlx;//站点类型
	private String zlfh;//直流负荷(A)	
	private String jlfh;//交流负荷(A)	
	private String bddb;//本地定标(度/天)	
	private String scb;//生产标(度/天)	
	private String nhjcz;//能耗基础值	
	private String sjz;//实际值(度/天)	
	private String jyscb;//建议生产标(度/天)	
	private String bddbzb;//本地定标占比	
	private String jyscbzb;//建议生产标占比	
	private String scbzb;//生产标占比	
	private String nhjczzb;//能耗基础值占比	
	private String sjzzb;//实际值占比	
	private String hdl1;//xx年01月耗电量(度/天)	
	private String hdl2;//xx年02月耗电量(度/天)	
	private String hdl3;//xx年03月耗电量(度/天)	
	private String hdl4;//xx年04月耗电量(度/天)	
	private String hdl5;//xx年05月耗电量(度/天)	
	private String hdl6;//xx年06月耗电量(度/天)	
	private String hdl7;//xx年07月耗电量(度/天)	
	private String hdl8;//xx年08月耗电量(度/天)	
	private String hdl9;//xx年09月耗电量(度/天)	
	private String hdl10;//xx年10月耗电量(度/天)	
	private String hdl11;//xx年11月耗电量(度/天)	
	private String hdl12;//xx年12月耗电量(度/天)
	
	private String edhdl;//额定耗电量
	private String fwlx;//房屋类型
	private String ktgs;//空调个数
	
	private String qsdbdl01;//全省定标电量1月
	private String qsdbdl02;//全省定标电量2月
	private String qsdbdl03;//全省定标电量3月
	private String qsdbdl04;//全省定标电量4月
	private String qsdbdl05;//全省定标电量5月
	private String qsdbdl06;//全省定标电量6月
	private String qsdbdl07;//全省定标电量7月
	private String qsdbdl08;//全省定标电量8月
	private String qsdbdl09;//全省定标电量9月
	private String qsdbdl10;//全省定标电量10月
	private String qsdbdl11;//全省定标电量11月
	private String qsdbdl12;//全省定标电量12月
	private String cbbl01;//抄表比例1月
	private String cbbl02;//抄表比例2月
	private String cbbl03;//抄表比例3月
	private String cbbl04;//抄表比例4月
	private String cbbl05;//抄表比例5月
	private String cbbl06;//抄表比例6月
	private String cbbl07;//抄表比例7月
	private String cbbl08;//抄表比例8月
	private String cbbl09;//抄表比例9月
	private String cbbl10;//抄表比例10月
	private String cbbl11;//抄表比例11月
	private String cbbl12;//抄表比例12月
	
	public String getQsdbdl01() {
		return qsdbdl01;
	}
	public void setQsdbdl01(String qsdbdl01) {
		this.qsdbdl01 = qsdbdl01;
	}
	public String getQsdbdl02() {
		return qsdbdl02;
	}
	public void setQsdbdl02(String qsdbdl02) {
		this.qsdbdl02 = qsdbdl02;
	}
	public String getQsdbdl03() {
		return qsdbdl03;
	}
	public void setQsdbdl03(String qsdbdl03) {
		this.qsdbdl03 = qsdbdl03;
	}
	public String getQsdbdl04() {
		return qsdbdl04;
	}
	public void setQsdbdl04(String qsdbdl04) {
		this.qsdbdl04 = qsdbdl04;
	}
	public String getQsdbdl05() {
		return qsdbdl05;
	}
	public void setQsdbdl05(String qsdbdl05) {
		this.qsdbdl05 = qsdbdl05;
	}
	public String getQsdbdl06() {
		return qsdbdl06;
	}
	public void setQsdbdl06(String qsdbdl06) {
		this.qsdbdl06 = qsdbdl06;
	}
	public String getQsdbdl07() {
		return qsdbdl07;
	}
	public void setQsdbdl07(String qsdbdl07) {
		this.qsdbdl07 = qsdbdl07;
	}
	public String getQsdbdl08() {
		return qsdbdl08;
	}
	public void setQsdbdl08(String qsdbdl08) {
		this.qsdbdl08 = qsdbdl08;
	}
	public String getQsdbdl09() {
		return qsdbdl09;
	}
	public void setQsdbdl09(String qsdbdl09) {
		this.qsdbdl09 = qsdbdl09;
	}
	public String getQsdbdl10() {
		return qsdbdl10;
	}
	public void setQsdbdl10(String qsdbdl10) {
		this.qsdbdl10 = qsdbdl10;
	}
	public String getQsdbdl11() {
		return qsdbdl11;
	}
	public void setQsdbdl11(String qsdbdl11) {
		this.qsdbdl11 = qsdbdl11;
	}
	public String getQsdbdl12() {
		return qsdbdl12;
	}
	public void setQsdbdl12(String qsdbdl12) {
		this.qsdbdl12 = qsdbdl12;
	}
	public String getCbbl01() {
		return cbbl01;
	}
	public void setCbbl01(String cbbl01) {
		this.cbbl01 = cbbl01;
	}
	public String getCbbl02() {
		return cbbl02;
	}
	public void setCbbl02(String cbbl02) {
		this.cbbl02 = cbbl02;
	}
	public String getCbbl03() {
		return cbbl03;
	}
	public void setCbbl03(String cbbl03) {
		this.cbbl03 = cbbl03;
	}
	public String getCbbl04() {
		return cbbl04;
	}
	public void setCbbl04(String cbbl04) {
		this.cbbl04 = cbbl04;
	}
	public String getCbbl05() {
		return cbbl05;
	}
	public void setCbbl05(String cbbl05) {
		this.cbbl05 = cbbl05;
	}
	public String getCbbl06() {
		return cbbl06;
	}
	public void setCbbl06(String cbbl06) {
		this.cbbl06 = cbbl06;
	}
	public String getCbbl07() {
		return cbbl07;
	}
	public void setCbbl07(String cbbl07) {
		this.cbbl07 = cbbl07;
	}
	public String getCbbl08() {
		return cbbl08;
	}
	public void setCbbl08(String cbbl08) {
		this.cbbl08 = cbbl08;
	}
	public String getCbbl09() {
		return cbbl09;
	}
	public void setCbbl09(String cbbl09) {
		this.cbbl09 = cbbl09;
	}
	public String getCbbl10() {
		return cbbl10;
	}
	public void setCbbl10(String cbbl10) {
		this.cbbl10 = cbbl10;
	}
	public String getCbbl11() {
		return cbbl11;
	}
	public void setCbbl11(String cbbl11) {
		this.cbbl11 = cbbl11;
	}
	public String getCbbl12() {
		return cbbl12;
	}
	public void setCbbl12(String cbbl12) {
		this.cbbl12 = cbbl12;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getFwlx() {
		return fwlx;
	}
	public void setFwlx(String fwlx) {
		this.fwlx = fwlx;
	}
	public String getKtgs() {
		return ktgs;
	}
	public void setKtgs(String ktgs) {
		this.ktgs = ktgs;
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
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
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
	public String getBddb() {
		return bddb;
	}
	public void setBddb(String bddb) {
		this.bddb = bddb;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	public String getNhjcz() {
		return nhjcz;
	}
	public void setNhjcz(String nhjcz) {
		this.nhjcz = nhjcz;
	}
	public String getSjz() {
		return sjz;
	}
	public void setSjz(String sjz) {
		this.sjz = sjz;
	}
	public String getJyscb() {
		return jyscb;
	}
	public void setJyscb(String jyscb) {
		this.jyscb = jyscb;
	}
	public String getBddbzb() {
		return bddbzb;
	}
	public void setBddbzb(String bddbzb) {
		this.bddbzb = bddbzb;
	}
	public String getJyscbzb() {
		return jyscbzb;
	}
	public void setJyscbzb(String jyscbzb) {
		this.jyscbzb = jyscbzb;
	}
	public String getScbzb() {
		return scbzb;
	}
	public void setScbzb(String scbzb) {
		this.scbzb = scbzb;
	}
	public String getNhjczzb() {
		return nhjczzb;
	}
	public void setNhjczzb(String nhjczzb) {
		this.nhjczzb = nhjczzb;
	}
	public String getSjzzb() {
		return sjzzb;
	}
	public void setSjzzb(String sjzzb) {
		this.sjzzb = sjzzb;
	}
	public String getHdl1() {
		return hdl1;
	}
	public void setHdl1(String hdl1) {
		this.hdl1 = hdl1;
	}
	public String getHdl2() {
		return hdl2;
	}
	public void setHdl2(String hdl2) {
		this.hdl2 = hdl2;
	}
	public String getHdl3() {
		return hdl3;
	}
	public void setHdl3(String hdl3) {
		this.hdl3 = hdl3;
	}
	public String getHdl4() {
		return hdl4;
	}
	public void setHdl4(String hdl4) {
		this.hdl4 = hdl4;
	}
	public String getHdl5() {
		return hdl5;
	}
	public void setHdl5(String hdl5) {
		this.hdl5 = hdl5;
	}
	public String getHdl6() {
		return hdl6;
	}
	public void setHdl6(String hdl6) {
		this.hdl6 = hdl6;
	}
	public String getHdl7() {
		return hdl7;
	}
	public void setHdl7(String hdl7) {
		this.hdl7 = hdl7;
	}
	public String getHdl8() {
		return hdl8;
	}
	public void setHdl8(String hdl8) {
		this.hdl8 = hdl8;
	}
	public String getHdl9() {
		return hdl9;
	}
	public void setHdl9(String hdl9) {
		this.hdl9 = hdl9;
	}
	public String getHdl10() {
		return hdl10;
	}
	public void setHdl10(String hdl10) {
		this.hdl10 = hdl10;
	}
	public String getHdl11() {
		return hdl11;
	}
	public void setHdl11(String hdl11) {
		this.hdl11 = hdl11;
	}
	public String getHdl12() {
		return hdl12;
	}
	public void setHdl12(String hdl12) {
		this.hdl12 = hdl12;
	}
}
