package com.noki.tongjichaxu;

public class YuefufeiBean {
	//预支
	private String Cityname;//1.城市
	private int count;//2.费用条数
	private Double ywmoney;//3.业务审核金额
	private Double cwmoney;//4.财务确认金额
	//private Double bili;//比例
	private Double wlyy;//5.网络运营
	private Double scjy;//6.市场经营
	private Double xzzh;//7.行政综合
	private Double xxhzc;//8.信息化支撑
	private Double tzjs;//9.投资建设
	private Double dddf;//10.代垫电费
	private Double qt;//其他
	//private Double ywhdbl;//业务活动比例
	private Double ydzy1;//11.移动专业-2G
	private Double ydzy2;//12.移动专业-3G
	private Double gwzy;//13.固网专业
	private Double gygtzy;//14.固移共同专业
	private Double ydgtzy;//15.移动共同专业
	private Double bkftzy;//16.不可分摊专业
	private int jszq;//结算周期
	private int diszdid;//按照每个月去重复后的站点id总和
	//private Double ywbl;//专业比例
	
	
	
	public String getCityname() {
		return Cityname;
	}
	public int getJszq() {
		return jszq;
	}
	public void setJszq(int jszq) {
		this.jszq = jszq;
	}
	public int getDiszdid() {
		return diszdid;
	}
	public void setDiszdid(int diszdid) {
		this.diszdid = diszdid;
	}
	public void setCityname(String cityname) {
		Cityname = cityname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Double getYwmoney() {
		return ywmoney;
	}
	public void setYwmoney(Double ywmoney) {
		this.ywmoney = ywmoney;
	}
	public Double getCwmoney() {
		return cwmoney;
	}
	public void setCwmoney(Double cwmoney) {
		this.cwmoney = cwmoney;
	}
	/*public Double getBili() {
		return bili;
	}
	public void setBili(Double bili) {
		this.bili = bili;
	}*/
	public Double getWlyy() {
		return wlyy;
	}
	public void setWlyy(Double wlyy) {
		this.wlyy = wlyy;
	}
	public Double getScjy() {
		return scjy;
	}
	public void setScjy(Double scjy) {
		this.scjy = scjy;
	}
	public Double getXzzh() {
		return xzzh;
	}
	public void setXzzh(Double xzzh) {
		this.xzzh = xzzh;
	}
	public Double getXxhzc() {
		return xxhzc;
	}
	public void setXxhzc(Double xxhzc) {
		this.xxhzc = xxhzc;
	}
	public Double getTzjs() {
		return tzjs;
	}
	public void setTzjs(Double tzjs) {
		this.tzjs = tzjs;
	}
	public Double getQt() {
		return qt;
	}
	public void setQt(Double qt) {
		this.qt = qt;
	}
	/*public Double getYwhdbl() {
		return ywhdbl;
	}
	public void setYwhdbl(Double ywhdbl) {
		this.ywhdbl = ywhdbl;
	}*/
	public Double getYdzy1() {
		return ydzy1;
	}
	public void setYdzy1(Double ydzy1) {
		this.ydzy1 = ydzy1;
	}
	public Double getYdzy2() {
		return ydzy2;
	}
	public void setYdzy2(Double ydzy2) {
		this.ydzy2 = ydzy2;
	}
	public Double getGwzy() {
		return gwzy;
	}
	public void setGwzy(Double gwzy) {
		this.gwzy = gwzy;
	}
	public Double getGygtzy() {
		return gygtzy;
	}
	public void setGygtzy(Double gygtzy) {
		this.gygtzy = gygtzy;
	}
	public Double getBkftzy() {
		return bkftzy;
	}
	public void setBkftzy(Double bkftzy) {
		this.bkftzy = bkftzy;
	}
	/*public Double getYwbl() {
		return ywbl;
	}
	public void setYwbl(Double ywbl) {
		this.ywbl = ywbl;
	}*/
	public Double getDddf() {
		return dddf;
	}
	public void setDddf(Double dddf) {
		this.dddf = dddf;
	}
	public Double getYdgtzy() {
		return ydgtzy;
	}
	public void setYdgtzy(Double ydgtzy) {
		this.ydgtzy = ydgtzy;
	}
	
	
}
