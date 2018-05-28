package com.noki.htnygl.dlsjbd.javabean;

import java.io.Serializable;

public class zdDlsjbdBean implements Serializable {

	/**
	 * @author xuzehua
	 */
	private static final long serialVersionUID = 1L;

	private String quxian;
	private String jzname;
	private double edhdl;
	private double cjdl;
	private double tqdl;

	public String getQuxian() {
		return quxian;
	}

	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public double getEdhdl() {
		return edhdl;
	}

	public void setEdhdl(double edhdl) {
		this.edhdl = edhdl;
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

}
