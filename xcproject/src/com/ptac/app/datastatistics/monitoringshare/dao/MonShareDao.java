package com.ptac.app.datastatistics.monitoringshare.dao;

import java.util.ArrayList;

import com.ptac.app.datastatistics.monitoringshare.bean.MonShareBean;

/**
 * @author 李靖
 * @see 监测点分摊接口层
 */
public interface MonShareDao {

	ArrayList<MonShareBean> queryExport(String whereStr, String loginId);

}
