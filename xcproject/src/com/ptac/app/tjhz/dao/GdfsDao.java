package com.ptac.app.tjhz.dao;

import java.util.List;

import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.tjhz.bean.GdfsBean;

public interface GdfsDao {
	/**
	 * @param whereStr (String) sql������
	 * @param loginId (String) Ȩ�޹������� 
	 * @return (GdfsBean)
	 * @see ���緽ʽͳ�Ʋ�ѯ�͵���
	 * @author MingQingYong 2014-02-19
	 */
	public List<GdfsBean> queryGdfs(String whereStr, String loginId,String bz);
	/**
	 * @param list List(GdfsBean)
	 * @return (GdfsBean)
	 * @see ��վ������ ֱ����վ������ ת����վ������ �ܽ�� ֱ������(��Ԫ) ת������(���) ֱ�������(���) ת�������(���) 
	 * @author MingQingYong 2014-02-19
	 */
	public GdfsBean total(List<GdfsBean> list);
}
