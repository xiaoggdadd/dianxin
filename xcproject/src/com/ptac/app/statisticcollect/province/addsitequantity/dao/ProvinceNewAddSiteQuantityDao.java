package com.ptac.app.statisticcollect.province.addsitequantity.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.addsitequantity.bean.ProvinceNewAddSiteQuantityBean;





/**
 * @author WangYiXiao 2014-2-14
 * @see ��������վ������dao��
 */
public interface ProvinceNewAddSiteQuantityDao {

	/**
	 * 
	 * @param bean (List<CityNewAddSiteQuantityBean>)��������վ������beanlist
	 * @return (long) ����������
	 * @see ��������վ������
	 * @author WangYiXiao 2014-2-15
	 */
	public String[] addSiteQuantiySum(List<ProvinceNewAddSiteQuantityBean> beanlist);
	/**
	 * @param shi (String)��
	 * @param beginyue (String)��ʼ��
	 * @param endyue (String) ������
	 * @param property (String) վ����
	 * @return List<CityNewAddSiteQuantityBean> ��������վ������bean
	 * @see ��������վ����������
	 * @author WangYiXiao 2014-5-20
	 */
	public List<ProvinceNewAddSiteQuantityBean> quaryExport(String shi,String beginyue,String endyue,String property,String qyzt,String loginId);
}
