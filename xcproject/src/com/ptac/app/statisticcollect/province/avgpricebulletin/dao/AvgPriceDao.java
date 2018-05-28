package com.ptac.app.statisticcollect.province.avgpricebulletin.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.avgpricebulletin.bean.AvgPriceBean;

public interface AvgPriceDao {

	List<AvgPriceBean> queryExport(String string, String loginId);

	String[] getSum(List<AvgPriceBean> list);

}
