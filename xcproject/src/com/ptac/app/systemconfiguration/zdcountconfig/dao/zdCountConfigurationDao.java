package com.ptac.app.systemconfiguration.zdcountconfig.dao;

import java.util.List;

import com.ptac.app.systemconfiguration.zdcountconfig.bean.zdCountConfigBean;

public interface zdCountConfigurationDao {
	List<zdCountConfigBean> getPageData();
	String modifyAutoAudit(zdCountConfigBean formBean);
}
