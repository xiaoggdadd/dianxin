package com.ptac.app.systemconfiguration.EnhanceEleConfig.dao;

import java.util.ArrayList;

import com.ptac.app.systemconfiguration.EnhanceEleConfig.bean.EnhanceEleConfigBean;

public interface EnhanceEleConfigurationDao {
	ArrayList getPageData(int start,String whereStr);
	String modifyAutoAudit(EnhanceEleConfigBean formBean);
	EnhanceEleConfigBean getEnhEleConfigInfo(String ItemID);
}
