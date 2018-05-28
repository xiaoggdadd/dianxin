package com.noki.htnygl.dlsjbd.javabean;

import java.io.Serializable;

public class dlsjbdBean implements Serializable {

	/**
	 * @author gt
	 * @msg.
	 */
	private static final long serialVersionUID = 1L;

	private String shi;
	private String quxian;
	//private String jzname;
	private double eddl;
	private double cjdl;
	private double tqdl;

	private double cece;
	private double tece;

	private String cebl;
	private String tebl;

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getQuxian() {
		return quxian;
	}

	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}

//	public String getJzname() {
//		return jzname;
//	}
//
//	public void setJzname(String jzname) {
//		this.jzname = jzname;
//	}

	public double getEddl() {
		return eddl;
	}

	public void setEddl(double eddl) {
		this.eddl = eddl;
	}

	public double getCjdl() {
		return cjdl;
	}

	public void setCjdl(double cjdl) {
		this.cjdl = cjdl;
	}

	public double getTqdl() {
		return tqdl;
	}

	public void setTqdl(double tqdl) {
		this.tqdl = tqdl;
	}

	public double getCece() {
		return cece;
	}

	public void setCece(double cece) {
		this.cece = cece;
	}

	public double getTece() {
		return tece;
	}

	public void setTece(double tece) {
		this.tece = tece;
	}

	public String getCebl() {
		return cebl;
	}

	public void setCebl(String cebl) {
		this.cebl = cebl;
	}

	public String getTebl() {
		return tebl;
	}

	public void setTebl(String tebl) {
		this.tebl = tebl;
	}

}
