package com.ptac.app.checksign.provincecheck.dao;

import java.util.List;
import com.ptac.app.checksign.provincecheck.bean.ProvCheckBean;

/**
 * @author lijing
 * @see ʡ������վ����˽ӿ�
 */
public interface ProvCheckDao {

	/**
	 * @see ʡ������վ����˲�ѯ
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	List<ProvCheckBean> queryProvince(StringBuffer whereStr, String loginId);

	/**
	 * @see ʡ������վ�����
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	int checkProvFees(String[] ids, String shsign, String personnal);
}
