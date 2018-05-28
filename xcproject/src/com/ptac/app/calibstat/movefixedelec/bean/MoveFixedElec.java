package com.ptac.app.calibstat.movefixedelec.bean;

import java.math.BigDecimal;

/**移网固网电量统计bean
 * @author WangYiXiao 2014-10-13
 *
 */
public class MoveFixedElec {
	private String shi;//市
	private String accountsubject;//会计科目
	private BigDecimal fixedfees;//固网费用
	private BigDecimal movefees;//移网费用
	private BigDecimal sumfees;//费用小计
	private BigDecimal fixedelec;//固网电量
	private BigDecimal moveelec;//移网电量
	private BigDecimal sumelec;//电量小计

	
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
