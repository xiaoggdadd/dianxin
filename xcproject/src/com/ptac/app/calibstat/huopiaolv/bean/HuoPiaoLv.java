package com.ptac.app.calibstat.huopiaolv.bean;

import java.math.BigDecimal;

/**��Ʊ��bean
 * @author xWangYiXiao 2014-10-15
 *
 */
public class HuoPiaoLv {
	private String shi;//����
	private BigDecimal tax;//˰��
	private BigDecimal elecfees;//�����ܵ��(����˰��)(��Ԫ)
	private BigDecimal vat;//Ʊ������Ϊ��ֵ˰��Ʊ���(����˰��)(��Ԫ)
	private BigDecimal ratio;//��Ʊ��ռ��
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
