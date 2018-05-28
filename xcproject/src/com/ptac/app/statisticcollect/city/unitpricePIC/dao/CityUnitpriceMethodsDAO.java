package com.ptac.app.statisticcollect.city.unitpricePIC.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceBean;
import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceSelectBean;

/**
 * 地是每月平均单价的方法接口
 * @author rock
 *
 */
public interface CityUnitpriceMethodsDAO {
	/**
	 * 查询语句
	 * @author rock
	 * @param loginID
	 * @param bean
	 * @return
	 */
	public List<CityUnitpriceBean> findUnitprices(String loginID,CityUnitpriceSelectBean bean);
}
