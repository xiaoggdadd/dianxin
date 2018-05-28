package com.ptac.app.statisticcollect.province.overpowerbulletin.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.overpowerbulletin.bean.OverPowerBean;

public interface OverPowerDao {

	List<OverPowerBean> queryExport(String string, String loginId);

	String[] getSum(List<OverPowerBean> list);

}
