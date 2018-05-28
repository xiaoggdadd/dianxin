package com.noki.felecconsume.bean;

public class ShenEnergy {

	public int id;//id主键
	public String province;//省
	public String producthouse;//生产用房耗电量
	public String commadroit;//通信机房耗电量
	public String basestage;//基站耗电量
	public String datacenter;//数据中心耗电量
	public String connlimit;//接入局耗电量
	public String outroom;//室外机柜耗电量
	public String notproducthouse;//非生产用户耗电量
	public String managerhouse;//管理用房耗电量
    public String channelhouse;//渠道用户耗电量
    public String other;//其它非生产用户耗电量
    public String agcode;//省code
    public String con_yearmonth;//年
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProducthouse() {
		return producthouse;
	}
	public void setProducthouse(String producthouse) {
		this.producthouse = producthouse;
	}
	public String getCommadroit() {
		return commadroit;
	}
	public void setCommadroit(String commadroit) {
		this.commadroit = commadroit;
	}
	public String getBasestage() {
		return basestage;
	}
	public void setBasestage(String basestage) {
		this.basestage = basestage;
	}
	public String getDatacenter() {
		return datacenter;
	}
	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
	}
	public String getConnlimit() {
		return connlimit;
	}
	public void setConnlimit(String connlimit) {
		this.connlimit = connlimit;
	}
	public String getOutroom() {
		return outroom;
	}
	public void setOutroom(String outroom) {
		this.outroom = outroom;
	}
	public String getNotproducthouse() {
		return notproducthouse;
	}
	public void setNotproducthouse(String notproducthouse) {
		this.notproducthouse = notproducthouse;
	}
	public String getManagerhouse() {
		return managerhouse;
	}
	public void setManagerhouse(String managerhouse) {
		this.managerhouse = managerhouse;
	}
	public String getChannelhouse() {
		return channelhouse;
	}
	public void setChannelhouse(String channelhouse) {
		this.channelhouse = channelhouse;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getAgcode() {
		return agcode;
	}
	public void setAgcode(String agcode) {
		this.agcode = agcode;
	}
	public String getCon_yearmonth() {
		return con_yearmonth;
	}
	public void setCon_yearmonth(String con_yearmonth) {
		this.con_yearmonth = con_yearmonth;
	}
}
