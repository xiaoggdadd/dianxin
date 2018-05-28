package com.ptac.app.checksign.citymanagercheck.dao;

import java.util.List;
import com.ptac.app.checksign.citymanagercheck.bean.CityMngCheckBean;

/**
 * @author lijing
 * @see �м��������dao��ӿ�
 */
public interface CityMngCheckDao {

	/**
	 * @author lijing
	 * @param whereStr String ��ѯ��������
	 * @param loginId String Ȩ�޹�������
	 * @return List<CityMngCheckBean> �м�������˲�ѯ���
	 */
	public List<CityMngCheckBean> queryCityMngCheck(String whereStr,String loginId,String lrbz1,String lrbz2);

	public List<CityMngCheckBean> getCityMngCheckInfo(String dbid, String dfzflx, String dfbzw, String accountmonth);
	
	 /**
	  * @author lijing
	  * @param personnal (String) �����
	  * @param cityzrauditstatus (String) ��˱�־
	  * @param chooseIdStr1 (String) ���uuid
	  * @param chooseIdStr2 ��String�� Ԥ����uuid
	  * @param bz (String) msg��־
	  * @see �м�������
	  * @param accountId (String) �˺�id
	  * @return String �����Ϣmsg
	  */
	public String CheckCityFees(String personnal,String cityzrauditstatus,String chooseIdStr1,String chooseIdStr2,String bz);
	
}
