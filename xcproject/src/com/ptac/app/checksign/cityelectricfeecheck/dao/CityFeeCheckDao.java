package com.ptac.app.checksign.cityelectricfeecheck.dao;

import java.io.IOException;
import java.util.List;

import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;

/**
 * @see �м�������dao��ӿ�
 * @author WangYiXiao 2014-1-23
 */
public interface CityFeeCheckDao {

	/**
	 * @param whereStr (String) ��ѯ����
	 * @param loginId (String) Ȩ�޹�������
	 * @param lrbz1 (String) ���¼���־
	 * @param lrbz2 (String) Ԥ����¼���־
	 * @author WangYiXiao 2014-1-23
	 * @return (List<CityElectricFeeCheck>) �м������˲�ѯ����ֶ�
	 * @see �м������˲�ѯ
	 */
	public List<CityElectricFeeCheck> queryCityFeeCheck(String whereStr,String loginId,String lrbz1,String lrbz2);
	
	/**
	 * @param (List<CityElectricFeeCheck>)
	 * @return (String[]) list[0]�����ͣ�list[1]��Ѻ�
	 * @see ��������Ѻ�
	 * @author WangYiXiao 2014-1-24
	 */
	public String[] total(List<CityElectricFeeCheck> list);
	
	 /**
	  * @param personnal (String) �����
	  * @param cityaudit (String) ��˱�־
	  * @param chooseIdStr1 (String) ���uuid
	  * @param chooseIdStr2 ��String�� Ԥ����uuid
	  * @param bz (String) msg��־
	  * @see �м�������
	  * @param accountId (String) �˺�id
	  * @author WngYiXio 2014-1-24
	  * @return String �����Ϣmsg
	  */
	public String CheckCityFees(String personnal,String cityaudit,String chooseIdStr1,String chooseIdStr2,String bz);

	/**
	 * @author lijing
	 * @see �����м�������ͨ������ͨ����ȡ��ͨ������
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String CheckCityFees1(String personnal, String cityaudit,
			String chooseIdStr1, String chooseIdStr2, String bz,
			String cause);
	
}
