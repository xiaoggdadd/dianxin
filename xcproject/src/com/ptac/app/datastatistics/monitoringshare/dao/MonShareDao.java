package com.ptac.app.datastatistics.monitoringshare.dao;

import java.util.ArrayList;

import com.ptac.app.datastatistics.monitoringshare.bean.MonShareBean;

/**
 * @author �
 * @see �����̯�ӿڲ�
 */
public interface MonShareDao {

	ArrayList<MonShareBean> queryExport(String whereStr, String loginId);

}
