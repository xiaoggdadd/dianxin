package com.ptac.app.calibstat.movefixedelec.bean;

import java.math.BigDecimal;

/**������������ͳ��bean
 * @author WangYiXiao 2014-10-13
 *
 */
public class MoveFixedElec {
	private String shi;//��
	private String accountsubject;//��ƿ�Ŀ
	private BigDecimal fixedfees;//��������
	private BigDecimal movefees;//��������
	private BigDecimal sumfees;//����С��
	private BigDecimal fixedelec;//��������
	private BigDecimal moveelec;//��������
	private BigDecimal sumelec;//����С��

	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getAccountsubject() {
		return accountsubject;
	}
	public void setAccountsubject(String accountsubject) {
		this.accountsubject = accountsubject;
	}
	public BigDecimal getFixedfees() {
		return fixedfees;
	}
	public void setFixedfees(BigDecimal fixedfees) {
		this.fixedfees = fixedfees;
	}
	public BigDecimal getMovefees() {
		return movefees;
	}
	public void setMovefees(BigDecimal movefees) {
		this.movefees = movefees;
	}
	public BigDecimal getSumfees() {
		return sumfees;
	}
	public void setSumfees(BigDecimal sumfees) {
		this.sumfees = sumfees;
	}
	public BigDecimal getFixedelec() {
		return fixedelec;
	}
	public void setFixedelec(BigDecimal fixedelec) {
		this.fixedelec = fixedelec;
	}
	public BigDecimal getMoveelec() {
		return moveelec;
	}
	public void setMoveelec(BigDecimal moveelec) {
		this.moveelec = moveelec;
	}
	public BigDecimal getSumelec() {
		return sumelec;
	}
	public void setSumelec(BigDecimal sumelec) {
		this.sumelec = sumelec;
	}
	
}
