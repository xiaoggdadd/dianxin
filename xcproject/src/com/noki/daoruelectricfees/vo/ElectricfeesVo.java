package com.noki.daoruelectricfees.vo;

/**
 * 电量电费导入数据实体类
 * 
 * @author guoli
 * 
 */
public class ElectricfeesVo {

	private String beilv; // 倍率
	private String starttime; // 开始日期
	private String endtime; // 结束日期
	private String sqds; // 上期读数
	private String bqds; // 本期读数
	private String dianliang; // 电量
	private String allmoney; // 总金额
	private String diansun; // 电损
	private String money; // 金额
	private String tax; // 税额
	private String sqdj; // 上期单价
	private String price; // 单价
	private String daynum; // 用电天数
	private String rjydl; // 日均用电量
	private String sqrjydl; // 上期日均用电量
	private String dlpls; // 电量偏离数
	private String rqpls; // 日期偏离数
	private String puezhi; // pue值
	private String bgdl; // 标杆电量
	private String bgpll; //标杆偏离率
	private String state; //状态
	private String dbid; // 电表ID
	private String dfbzw; //标志位 0导入的电费  1录入的电费
	private String gdfs; //供电方式 
	
	//手机抄表
	private String starttime_c; // 开始日期
	private String endtime_c; // 结束日期
	private String sqds_c; // 上期读数
	private String bqds_c; // 本期读数
	private String dianliang_c; // 电量
	private String daynum_c; // 用电天数
	private String rjydl_c; // 日均用电量
	
	private ElectricCurrentVo currentVo; //监控电流对象
	
	public String getBeilv() {
		return beilv;
	}

	public void setBeilv(String beilv) {
		this.beilv = beilv;
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

	public String getSqds() {
		return sqds;
	}

	public void setSqds(String sqds) {
		this.sqds = sqds;
	}

	public String getBqds() {
		return bqds;
	}

	public void setBqds(String bqds) {
		this.bqds = bqds;
	}

	public String getDianliang() {
		return dianliang;
	}

	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getDiansun() {
		return diansun;
	}

	public void setDiansun(String diansun) {
		this.diansun = diansun;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSqdj() {
		return sqdj;
	}

	public void setSqdj(String sqdj) {
		this.sqdj = sqdj;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

	public String getRjydl() {
		return rjydl;
	}

	public void setRjydl(String rjydl) {
		this.rjydl = rjydl;
	}

	public String getSqrjydl() {
		return sqrjydl;
	}

	public void setSqrjydl(String sqrjydl) {
		this.sqrjydl = sqrjydl;
	}

	public String getDlpls() {
		return dlpls;
	}

	public void setDlpls(String dlpls) {
		this.dlpls = dlpls;
	}

	public String getRqpls() {
		return rqpls;
	}

	public void setRqpls(String rqpls) {
		this.rqpls = rqpls;
	}

	public String getPuezhi() {
		return puezhi;
	}

	public void setPuezhi(String puezhi) {
		this.puezhi = puezhi;
	}

	public String getBgdl() {
		return bgdl;
	}

	public void setBgdl(String bgdl) {
		this.bgdl = bgdl;
	}

	public String getBgpll() {
		return bgpll;
	}

	public void setBgpll(String bgpll) {
		this.bgpll = bgpll;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getDfbzw() {
		return dfbzw;
	}

	public void setDfbzw(String dfbzw) {
		this.dfbzw = dfbzw;
	}

	public String getGdfs() {
		return gdfs;
	}

	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}

	public String getStarttime_c() {
		return starttime_c;
	}

	public void setStarttime_c(String starttime_c) {
		this.starttime_c = starttime_c;
	}

	public String getEndtime_c() {
		return endtime_c;
	}

	public void setEndtime_c(String endtime_c) {
		this.endtime_c = endtime_c;
	}

	public String getSqds_c() {
		return sqds_c;
	}

	public void setSqds_c(String sqds_c) {
		this.sqds_c = sqds_c;
	}

	public String getBqds_c() {
		return bqds_c;
	}

	public void setBqds_c(String bqds_c) {
		this.bqds_c = bqds_c;
	}

	public String getDianliang_c() {
		return dianliang_c;
	}

	public void setDianliang_c(String dianliang_c) {
		this.dianliang_c = dianliang_c;
	}

	public String getDaynum_c() {
		return daynum_c;
	}

	public void setDaynum_c(String daynum_c) {
		this.daynum_c = daynum_c;
	}

	public String getRjydl_c() {
		return rjydl_c;
	}

	public void setRjydl_c(String rjydl_c) {
		this.rjydl_c = rjydl_c;
	}

	public ElectricCurrentVo getCurrentVo() {
		return currentVo;
	}

	public void setCurrentVo(ElectricCurrentVo currentVo) {
		this.currentVo = currentVo;
	}

}
