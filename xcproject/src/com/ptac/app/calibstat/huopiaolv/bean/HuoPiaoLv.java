package com.ptac.app.calibstat.huopiaolv.bean;

import java.math.BigDecimal;

/**获票率bean
 * @author xWangYiXiao 2014-10-15
 *
 */
public class HuoPiaoLv {
	private String shi;//地市
	private BigDecimal tax;//税额
	private BigDecimal elecfees;//财务总电费(不含税额)(万元)
	private BigDecimal vat;//票据类型为增值税发票金额(不含税额)(万元)
	private BigDecimal ratio;//获票率占比
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getElecfees() {
		return elecfees;
	}
	public void setElecfees(BigDecimal elecfees) {
		this.elecfees = elecfees;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
}
