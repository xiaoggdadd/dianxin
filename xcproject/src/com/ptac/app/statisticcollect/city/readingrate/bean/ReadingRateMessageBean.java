package com.ptac.app.statisticcollect.city.readingrate.bean;
/**
 * 存放抄表率功能查询信息的bean
 * @author rock
 *
 */
public class ReadingRateMessageBean {
	
	
	
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
	public String getZdsl() {
		return zdsl;
	}
	public void setZdsl(String zdsl) {
		this.zdsl = zdsl;
	}
	public String getJsbsl() {
		return jsbsl;
	}
	public void setJsbsl(String jsbsl) {
		this.jsbsl = jsbsl;
	}
	public String getGlbsl() {
		return glbsl;
	}
	public void setGlbsl(String glbsl) {
		this.glbsl = glbsl;
	}
	public String getJscbcs() {
		return jscbcs;
	}
	public void setJscbcs(String jscbcs) {
		this.jscbcs = jscbcs;
	}
	public String getGlcbcs() {
		return glcbcs;
	}
	public void setGlcbcs(String glcbcs) {
		this.glcbcs = glcbcs;
	}
	
	public ReadingRateMessageBean(){
		
	}
	
	public ReadingRateMessageBean(String shi,String xian,String zdsl,String jsbsl,String glbsl,String jscbcs,String glcbcs){
		this.shi = shi;
		this.xian = xian;
		this.zdsl=zdsl;
		this.jsbsl = jsbsl;
		this.glbsl = glbsl;
		this.jscbcs = jscbcs;
		this.glcbcs = glcbcs;
	}
	
	private String shi;
	private String xian;
	private String zdsl;
	private String jsbsl;
	private String glbsl;
	private String jscbcs;
	private String glcbcs;
}
