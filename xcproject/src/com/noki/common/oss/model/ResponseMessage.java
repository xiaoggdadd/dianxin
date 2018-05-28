package com.noki.common.oss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="root")
@XmlType(propOrder = {"returntime", "returncode", "faultdesc", "csvurl"})
public class ResponseMessage {
	/**
	 * <?xml version="1.0" encoding="UTF-8"?>
	 * <root>
	 * <returntime>执行查询后返回的时间点</returntime> 
	 * <returncode>返回代码：0表示成功 1表示失败</returncode > 
	 * <faultdesc>当查询失败时，此处填写失败原因 </ faultdesc >
	 * <csvurl>数据文件的url地址（http方式下载即可）</ csvurl > 
	 * </root>
	 */
	
	private String returntime;
	private String returncode;
	private String faultdesc;
	private String csvurl;

	public String getReturntime() {
		return returntime;
	}
	@XmlElement
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}

	public String getReturncode() {
		return returncode;
	}
	@XmlElement
	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getFaultdesc() {
		return faultdesc;
	}
	@XmlElement
	public void setFaultdesc(String faultdesc) {
		this.faultdesc = faultdesc;
	}

	public String getCsvurl() {
		return csvurl;
	}
	@XmlElement
	public void setCsvurl(String csvurl) {
		this.csvurl = csvurl;
	}

}
