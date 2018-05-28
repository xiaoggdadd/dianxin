package com.ptac.app.statisticcollect.province.sitedetailcount.dao;

import java.util.List;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean1;

/**
 * @author lijing
 * @see ���˵����ͳ��dao��ӿ�
 */
public interface SiteDetailCountDao {

	/**
	 * @author lijing
	 * @param whereStr
	 * @param str 
	 * @param whereStr str String ��ѯ��������
	 * @return List<SiteDetailCountBean> ���˵����ͳ��ͨ����ѯ���
	 */
	List<SiteDetailCountBean> quarySiteDetailCount(StringBuffer whereStr,
			StringBuffer str, String loginId);

	/**
	 * @author lijing
	 * @param whereStr 
	 * @param str 
	 * @param whereStr str String ��ѯ��������
	 * @return List<SiteDetailCountBean1> ���˵���˲�ͨ��ͳ�Ʋ�ѯ���
	 */
	List<SiteDetailCountBean1> quarySiteDetailCount1(StringBuffer whereStr,
			StringBuffer str, String loginId);
}
