package com.ptac.app.statisticcollect.province.unitpricePIC.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProSelectBean;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProUnitpriceBean;

public interface ProUnitpriceMethodsDAO {
	/**
	 * ≤È—Ø”Ôæ‰
	 * @author rock
	 * @param loginID
	 * @param bean
	 * @return
	 */
	public List<ProUnitpriceBean> findUnitprices(String loginID,ProSelectBean bean);
}
