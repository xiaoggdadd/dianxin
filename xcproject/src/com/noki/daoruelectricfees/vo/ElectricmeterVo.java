package com.noki.daoruelectricfees.vo;

/**
 * 电表相关信息
 * 
 * @author guoli
 * 
 */
public class ElectricmeterVo {

	private Integer id;
	private String gdfs; // 供电方式
	private String dj; // 单价
	private String zzsl; //税率
	private String beilv; //倍率
	private String jzId; //动环局站ID
	private String zhiliu;//直流功率
	private String jiaoliu; //交流功率
	private String zdid; //站点ID
	private String shi; //地市
	private String housetypeid; //房屋类型
	private String dbbm;
	private String shzt; //审核状态 0未上报1审核中2已通过
	private String dbqyzt; //启用状态
	private String shztlable;
	private String dbqyztlabel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGdfs() {
		return gdfs;
	}

	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getZzsl() {
		return zzsl;
	}

	public void setZzsl(String zzsl) {
		this.zzsl = zzsl;
	}

	public String getBeilv() {
		return beilv;
	}

	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}

	public String getJzId() {
		return jzId;
	}

	public void setJzId(String jzId) {
		this.jzId = jzId;
	}

	public String getZhiliu() {
		return zhiliu;
	}

	public void setZhiliu(String zhiliu) {
		this.zhiliu = zhiliu;
	}

	public String getJiaoliu() {
		return jiaoliu;
	}

	public void setJiaoliu(String jiaoliu) {
		this.jiaoliu = jiaoliu;
	}

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getHousetypeid() {
		return housetypeid;
	}

	public void setHousetypeid(String housetypeid) {
		this.housetypeid = housetypeid;
	}

	public String getDbbm() {
		return dbbm;
	}

	public void setDbbm(String dbbm) {
		this.dbbm = dbbm;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getDbqyzt() {
		return dbqyzt;
	}

	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
	}

	public String getShztlable() {
		return shztlable;
	}

	public void setShztlable(String shztlable) {
		this.shztlable = shztlable;
	}

	public String getDbqyztlabel() {
		return dbqyztlabel;
	}

	public void setDbqyztlabel(String dbqyztlabel) {
		this.dbqyztlabel = dbqyztlabel;
	}

}
