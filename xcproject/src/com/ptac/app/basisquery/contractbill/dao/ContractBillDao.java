package com.ptac.app.basisquery.contractbill.dao;

import java.util.List;
import com.ptac.app.basisquery.contractbill.bean.ContractBill;

/**
 * @author lijing
 * @see ��ͬ����ѯDao��ӿ�
 *
 */
public interface ContractBillDao {
	
	/**
	 * @author lijing
	 * @see ��ͬ����ѯ������
	 * @param whereStr (String)	//��������
	 * @param loginId (String) //Ȩ�޹������� 
	 * @return (ContractBill)
	 */
	public List<ContractBill> getConBill(String whereStr,String loginId);

}
