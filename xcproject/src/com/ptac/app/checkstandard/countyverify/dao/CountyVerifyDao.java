package com.ptac.app.checkstandard.countyverify.dao;

import java.util.List;

import com.ptac.app.checkstandard.countyverify.bean.CountyVerifyBean;

/**
 * @author lijing
 * @see ����ǩ�ռ���ʵ��Ϣ�ӿڲ�
 */
public interface CountyVerifyDao {

	/**
	 * @see ��ѯ����ǩ�ռ���ʵ��Ϣ
	 * @param string
	 * @param loginId
	 * @return list
	 */
	List<CountyVerifyBean> queryCountyVerify(String string, String loginId, String beginbl, String endbl);

	/**
	 * �����˵�
	 * @param id
	 * @param loginName
	 * @return
	 */
	int TDUpdate(String id, String loginName);

	/**
	 * ���غ�ʵ��Ϣ��ѯ
	 * @param zdid
	 * @return
	 */
	List<CountyVerifyBean> getInfo(String zdid);

	/**
	 * @see ���غ�ʵ��Ϣ����
	 * @return
	 */
	String commit(CountyVerifyBean bean, String loginName);

}
