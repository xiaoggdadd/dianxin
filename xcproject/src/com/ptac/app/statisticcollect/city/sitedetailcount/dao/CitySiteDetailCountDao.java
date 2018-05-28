package com.ptac.app.statisticcollect.city.sitedetailcount.dao;

import java.util.List;


import com.ptac.app.statisticcollect.city.sitedetailcount.bean.CitySiteDetailCountBean;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.CitySiteDetailCountBean1;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.Info;

/**
 * @author lijing
 * @see ���˵����ͳ��dao��ӿ�
 */
public interface CitySiteDetailCountDao {

	/**
	 * @author lijing
	 * @param whereStr 
	 * @param str 
	 * @param whereStr str String ��ѯ��������
	 * @return List ���˵����ͳ�����ͨ����ѯ���
	 */
	List<CitySiteDetailCountBean> quarySiteDetailCount(StringBuffer whereStr,
			StringBuffer str, String loginId);

	/**
	 * @author lijing
	 * @param whereStr 
	 * @param str
	 * @param whereStr str String ��ѯ��������
	 * @return List ���˵����ͳ����˲�ͨ����ѯ���
	 */
	List<CitySiteDetailCountBean1> quarySiteDetailCount1(StringBuffer whereStr,
			StringBuffer str, String loginId);

	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(���ͨ��)
	 */
	List<Info> info(String loginId, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(���ͨ��)
	 */
	List<Info> info1(String loginId,StringBuffer str,StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(�м����˹���ˣ���ͨ����δ���)
	 */
	List<Info> info2(String loginId, StringBuffer str, StringBuffer str1);
	
	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(�Զ�δ���)
	 */
	List<Info> info3(String loginId, StringBuffer str, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(�����Ρ�����������ˣ���ͨ����δ���)
	 */
	List<Info> info4(String loginId, StringBuffer str, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(������ˣ���ͨ����δ���)
	 */
	List<Info> info5(String loginId, StringBuffer str, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String ��ѯ��������
	 * @param loginId 
	 * @param str1 
	 * @return List ���˵����ͳ����ϸ��ѯ(���ͨ��)
	 */
	List<Info> info11(String loginId, StringBuffer str, StringBuffer str1);

}
