package com.ptac.app.calibstat.calendarmonthelec.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ptac.app.calibstat.calendarmonthelec.bean.ProMonthElectriBean;

public interface ProMonthElectriDao {
	
	//ʡ����Ȼ���õ�ͳ�� ��ѯ
	/**
	 * @author zx
	 * @param whereStr ҳ�洫�Ĺ�������ƴ�ӵ��ַ���
	 * @param  begindate  ��ʼ�·�  date����
	 * @param  enddate  �����·�  date����
	 * @param  beginTime  ��ʼ�·� String����
	 * @param  endTime  �����·�  String����
	 * @param  l    ����ʼ�·�-�����·�+1��  �������ֵ�����ڼ������
	 * @return  list ����
	 * */
	List<Object> proMonthSele(String whereStr,Date begindate,Date enddate,String beginTime,String endTime,int l );

}
