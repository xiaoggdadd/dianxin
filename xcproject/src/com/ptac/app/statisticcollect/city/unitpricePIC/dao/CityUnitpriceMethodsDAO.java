package com.ptac.app.statisticcollect.city.unitpricePIC.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceBean;
import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceSelectBean;

/**
 * ����ÿ��ƽ�����۵ķ����ӿ�
 * @author rock
 *
 */
public interface CityUnitpriceMethodsDAO {
	/**
	 * ��ѯ���
	 * @author rock
	 * @param loginID
	 * @param bean
	 * @return
	 */
	public List<CityUnitpriceBean> findUnitprices(String loginID,CityUnitpriceSelectBean bean);
}
