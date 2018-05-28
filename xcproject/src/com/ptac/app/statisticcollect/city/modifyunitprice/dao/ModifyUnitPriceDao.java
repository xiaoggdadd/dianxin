package com.ptac.app.statisticcollect.city.modifyunitprice.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.modifyunitprice.bean.ModifyUnitPriceBean;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.UnitPriceDetailsBean;

public interface ModifyUnitPriceDao {

	
	/**��ѯ
	 * @return
	 */
	List<ModifyUnitPriceBean> quaryModifyUnitPrice(String whereStr,String whereStr1,String loginid,String beginyue,String endyue,String command); 
	
	/**���
	 * @return
	 */
	String[] getSum(List<ModifyUnitPriceBean> beanlist);
	
	/**�޸ĵ�������
	 * @return
	 */
	List<UnitPriceDetailsBean> getModifyUnitPriceDetails(String beginyue,String endyue ,String whereStr,String command);
}
