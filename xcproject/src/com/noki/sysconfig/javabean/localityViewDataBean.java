package com.noki.sysconfig.javabean;

public class localityViewDataBean {
public String agcode;
public String agname;
public String fagcode;
public String agrade;//区域等级
public String agid;
private int shu;//区域等级（整数）
public String countb;//d_area_grade 表里的数据个数



public String getCountb() {
	return countb;
}
public void setCountb(String countb) {
	this.countb = countb;
}
public int getShu() {
	return shu;
}
public void setShu(int shu) {
	this.shu = shu;
}
public String getAgcode() {
	return agcode;
}
public void setAgcode(String agcode) {
	this.agcode = agcode;
}
public String getAgname() {
	return agname;
}
public void setAgname(String agname) {
	this.agname = agname;
}
public String getFagcode() {
	return fagcode;
}
public void setFagcode(String fagcode) {
	this.fagcode = fagcode;
}
public String getAgrade() {
	return agrade;
}
public void setAgrade(String agrade) {
	this.agrade = agrade;
}
public String getAgid() {
	return agid;
}
public void setAgid(String agid) {
	this.agid = agid;
}
}
