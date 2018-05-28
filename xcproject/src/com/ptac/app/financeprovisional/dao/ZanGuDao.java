package com.ptac.app.financeprovisional.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.financeprovisional.bean.FinanceZanGuBean;

public interface ZanGuDao {
	//�ݹ����ݴ�������   �����У��ݹ�����ʱ�䣬�ݹ��·ݣ�������������¼��Ȩ��id��
	public String zanGuShuJu(String shi,String zgsj,String zgmonth,String wherestr,String loginId);
	
	//�ݹ����ݲ�ѯ��Ϣ     ������������
	public ArrayList<FinanceZanGuBean> zanGuChaXun(String whereStr,String loginId);
	
	//�����ݹ�����ܺ�
	public FinanceZanGuBean total(List<FinanceZanGuBean> list);
	
	//�ݹ����ݲ�ѯרҵ�߷�̯��Ϣ     ������������
	public ArrayList<FinanceZanGuBean> zanGuFenTan(String whereStr,String loginId);
}
