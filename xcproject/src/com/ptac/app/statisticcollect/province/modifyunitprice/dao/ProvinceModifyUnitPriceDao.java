package com.ptac.app.statisticcollect.province.modifyunitprice.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.modifyunitprice.bean.ProvinceModifyUnitPriceBean;

public interface ProvinceModifyUnitPriceDao {

	
	/**��ѯ
	 * @return
	 */
	List<ProvinceModifyUnitPriceBean> quaryModifyUnitPrice(String whereStr,String whereStr1,String loginId,String beginyue,String endyue,String bzw); 
	
	/**���
	 * @return
	 */
	String[] getSum(List<ProvinceModifyUnitPriceBean> beanlist);
}
