package com.ptac.app.statisticcollect.city.addsitequantity.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.addsitequantity.bean.CityNewAddSiteQuantityBean;
import com.ptac.app.statisticcollect.city.addsitequantity.bean.EleAndFeesBean;



/**
 * @author WangYiXiao 2014-2-14
 * @see ��������վ������dao��
 * @upate WangYiXiao 2014-5-14 ����վ����������
 */
public interface CityNewAddSiteQuantityDao {

	/**
	 * @param command (Stirng) ��ѯ���ǵ���
	 * @param shi (String)��
	 * @param beginyue (String)��ʼ��
	 * @param endyue (String) ������
	 * @param property (String) վ����
	 * @return List<CityNewAddSiteQuantityBean> ��������վ������bean
	 * @see ��������վ����������
	 * @author WangYiXiao 2014-5-21
	 */
	public List<CityNewAddSiteQuantityBean> export(String shi,String beginyue,String endyue,String property,String qyzt,String loginId);
	/**
	 * 
	 * @param bean (List<CityNewAddSiteQuantityBean>)��������վ������beanlist
	 * @return (long) ����������
	 * @see ��������վ������
	 * @author WangYiXiao 2014-2-15
	 */
	public String[] addSiteQuantiySum(List<CityNewAddSiteQuantityBean> beanlist);
	/**
	 * @see ����վ����������
	 * @param shi
	 * @param beginyue
	 * @param endyue
	 * @param property
	 * @param loginId
	 * @return
	 * @author WangYiXiao 2014-5-14
	 */
	public List<EleAndFeesBean> quaryEleAndFees(String shi,String beginyue,String endyue,String property,String qyzt,String loginId);
}
