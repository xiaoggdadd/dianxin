package com.noki.common.oss.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="root")
@XmlType(propOrder = {"querytime", "querysystem"})
public class RequestMessage {
	/**
	 * <?xml version="1.0" encoding="UTF-8"?>
	 * <root> 
	 * <querytime>��ѯʱ��-��ʽyyyy-mm-dd hh24:mi:ss</querytime>
	 * <querysystem>��ѯϵͳ����-����xx����</querysystem> 
	 * </root>
	 */
	private String querytime;
	
	private String querysystem;

	@XmlElement
	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}

	@XmlElement
	public String getQuerysystem() {
		return querysystem;
	}

	public void setQuerysystem(String querysystem) {
		this.querysystem = querysystem;
	}

}
