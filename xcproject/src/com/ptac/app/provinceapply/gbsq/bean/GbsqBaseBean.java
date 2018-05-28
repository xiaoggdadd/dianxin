package com.ptac.app.provinceapply.gbsq.bean;

/**标杆审核基本信息
 * @author WangYiXiao 2015-3-15
 *
 */
public class GbsqBaseBean {
	private String qskzid;//qskz id
	private String zdid;//站点id
	private String oldqsdb;//老生产标
	private String newqsdb;//新生产标
	private String oldqsdbdl;//老全省定标电量
	private String newzlfh;//新直流负荷
	private String newjlfh;//新交流负荷
	private String newproperty;//新站点属性
	private String flgg;//更改了那些省级审核字段
	private String bftg;//省级已经通过了哪些字段
	private String bfbtg;//省级已经不通过哪些字段
	public String getQskzid() {
		return qskzid;
	}
	public void setQskzid(String qskzid) {
		this.qskzid = qskzid;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getOldqsdb() {
		return oldqsdb;
	}
	public void setOldqsdb(String oldqsdb) {
		this.oldqsdb = oldqsdb;
	}
	public String getNewqsdb() {
		return newqsdb;
	}
	public void setNewqsdb(String newqsdb) {
		this.newqsdb = newqsdb;
	}
	public String getOldqsdbdl() {
		return oldqsdbdl;
	}
	public void setOldqsdbdl(String oldqsdbdl) {
		this.oldqsdbdl = oldqsdbdl;
	}
	public String getNewzlfh() {
		return newzlfh;
	}
	public void setNewzlfh(String newzlfh) {
		this.newzlfh = newzlfh;
	}
	public String getNewjlfh() {
		return newjlfh;
	}
	public void setNewjlfh(String newjlfh) {
		this.newjlfh = newjlfh;
	}
	public String getNewproperty() {
		return newproperty;
	}
	public void setNewproperty(String newproperty) {
		this.newproperty = newproperty;
	}
	public String getFlgg() {
		return flgg;
	}
	public void setFlgg(String flgg) {
		this.flgg = flgg;
	}
	public String getBftg() {
		return bftg;
	}
	public void setBftg(String bftg) {
		this.bftg = bftg;
	}
	public String getBfbtg() {
		return bfbtg;
	}
	public void setBfbtg(String bfbtg) {
		this.bfbtg = bfbtg;
	}
	
}
