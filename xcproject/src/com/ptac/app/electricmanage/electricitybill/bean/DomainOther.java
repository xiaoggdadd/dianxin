package com.ptac.app.electricmanage.electricitybill.bean;

/**
 * @author gt_web 辅助 检查信息 domain 所属专业 dbzb 电表占比 qcbkm 全成本科目 kjkm 会计科目 zymx 专业明细
 *         ftbl 分摊比例
 */
public class DomainOther {
	// 辅助 检查信息
	private String domain;
	private String dbzb;
	private String qcbkm;
	private String kjkm;
	private String zymx;
	private String ftbl;

	public DomainOther(){
		this.dbzb = "0";
		this.ftbl = "0.00";
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDbzb() {
		return dbzb;
	}

	public void setDbzb(String dbzb) {
		this.dbzb = dbzb;
	}

	public String getQcbkm() {
		return qcbkm;
	}

	public void setQcbkm(String qcbkm) {
		this.qcbkm = qcbkm;
	}

	public String getKjkm() {
		return kjkm;
	}

	public void setKjkm(String kjkm) {
		this.kjkm = kjkm;
	}

	public String getZymx() {
		return zymx;
	}

	public void setZymx(String zymx) {
		this.zymx = zymx;
	}

	public String getFtbl() {
		return ftbl;
	}

	public void setFtbl(String ftbl) {
		this.ftbl = ftbl;
	}

}
