package com.noki.jtreport.shi;


/**
 * 集团报表bean，存放当前所有的报表名称
 * @author Administrator
 *
 */
public class JtReportBean {
	private String name;
	private String reportType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
