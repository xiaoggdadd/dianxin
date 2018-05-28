package com.ptac.app.electricmanage.bargainbill.dao;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillCountBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillMessageBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSaveBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSelectConditionsBean;

/**
 * ��ͬ�����ܵķ���DAO
 * @author rock
 *
 */
public interface BargainBillMethodsDAO {
	/**
	 * ��ѯ��ͬ����Ϣ
	 * @author rock
	 * @param bbscb ��ѯ����������
	 * @param loginID ��ǰ��ѯ��ID
	 * @return
	 */
	public List<BargainBillMessageBean> findBargainBills(BargainBillSelectConditionsBean bbscb,String loginID);
	
	/**
	 * �޸ĺ�ͬ����Ϣ
	 * @author rock
	 * @param formBean �޸����ݶ���
	 * @param id ��Ҫ�޸ĵĺ�ͬ����ID
	 * @param start
	 * @param end
	 * @return
	 */
	public String updateBargainBills(BargainBillSaveBean formBean,String id,String start,String end);
	
	/**
	 * ɾ����ͬ����Ϣ
	 * @author rock
	 * @param id ��Ҫɾ���ĸú�ͬ��ID
	 * @return
	 */
	public String deleteBargainBills(String id);

	/**
	 * ���Ӻ�ͬ����Ϣ
	 * @author rock
	 * @param formBean ��Ҫ���ӵĺ�ͬ������
	 * @param start
	 * @param end
	 * @return
	 */
	public String addBargainBill(BargainBillSaveBean formBean,String start,String end);
	
	/**
	 * ���е���
	 * @author rock
	 * @param content 
	 * @param cform
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector input(Vector content, CountForm cform, Account account,HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * �������ݽ��д���
	 * @author rock
	 * @param formBean
	 * @param bzw
	 * @param start
	 * @param end
	 * @return
	 */
	public String saveXls(BargainBillSaveBean formBean,
			String bzw,String start,String end);
	/**
	 * ��ѯ���ۣ�ȫʡ������������
	 * @param ammeterid
	 * @return
	 */
	//public BargainBillCountBean getCount(String ammeterid);
	
}
