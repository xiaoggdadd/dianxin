package com.ptac.app.calibstat.dao;

import java.util.ArrayList;

import com.ptac.app.calibstat.bean.CalibStatBean;

/**
 * @author �
 * @see ����ͳ��Dao��ӿ�
 */
public interface CalibStatDao {

	//��ϸ��ѯ������
	ArrayList queryMingXi(String whereStr, String loginId);
	//��վ�㳬�������ѯ
	CalibStatBean querySingleSite(String whereStr,String str,String loginId);

}
