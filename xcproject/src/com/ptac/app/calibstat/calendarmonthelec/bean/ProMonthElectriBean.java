package com.ptac.app.calibstat.calendarmonthelec.bean;
/** 
 * @author zhouxue
 * @see 省级自然月用电统计
 */
public class ProMonthElectriBean {
	 private String shi;//城市
	 private String zds;//站点数
	 private String dl;//电量和
	 private String df;//电费和
	 private String dfdts;//电费单条数
	
	
    public String getShi(){
    	return shi;
    }
    public void setShi(String shi){
		this.shi=shi;
	}
	public String getZds() {
		return zds;
	}
	public void setZds(String zds) {
		this.zds = zds;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public String getDfdts() {
		return dfdts;
	}
	public void setDfdts(String dfdts) {
		this.dfdts = dfdts;
	}
    
}
