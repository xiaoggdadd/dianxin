package com.ptac.app.statisticcollect.province.addsitequantity.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.addsitequantity.bean.ProvinceNewAddSiteQuantityBean;





/**
 * @author WangYiXiao 2014-2-14
 * @see 地市新增站点数量dao层
 */
public interface ProvinceNewAddSiteQuantityDao {

	/**
	 * 
	 * @param bean (List<CityNewAddSiteQuantityBean>)地市新增站点数量beanlist
	 * @return (long) 新增总数量
	 * @see 地市新增站点总数
	 * @author WangYiXiao 2014-2-15
	 */
	public String[] addSiteQuantiySum(List<ProvinceNewAddSiteQuantityBean> beanlist);
	/**
	 * @param shi (String)市
	 * @param beginyue (String)起始月
	 * @param endyue (String) 结束月
	 * @param property (String) 站属性
	 * @return List<CityNewAddSiteQuantityBean> 地市新增站点数量bean
	 * @see 地市新增站点数量导出
	 * @author WangYiXiao 2014-5-20
	 */
	public List<ProvinceNewAddSiteQuantityBean> quaryExport(String shi,String beginyue,String endyue,String property,String qyzt,String loginId);
}
