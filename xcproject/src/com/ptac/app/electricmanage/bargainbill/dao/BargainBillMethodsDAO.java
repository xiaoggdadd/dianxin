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
 * 合同单功能的方法DAO
 * @author rock
 *
 */
public interface BargainBillMethodsDAO {
	/**
	 * 查询合同单信息
	 * @author rock
	 * @param bbscb 查询的条件对象
	 * @param loginID 当前查询人ID
	 * @return
	 */
	public List<BargainBillMessageBean> findBargainBills(BargainBillSelectConditionsBean bbscb,String loginID);
	
	/**
	 * 修改合同单信息
	 * @author rock
	 * @param formBean 修改内容对象
	 * @param id 需要修改的合同单的ID
	 * @param start
	 * @param end
	 * @return
	 */
	public String updateBargainBills(BargainBillSaveBean formBean,String id,String start,String end);
	
	/**
	 * 删除合同单信息
	 * @author rock
	 * @param id 需要删除的该合同单ID
	 * @return
	 */
	public String deleteBargainBills(String id);

	/**
	 * 增加合同单信息
	 * @author rock
	 * @param formBean 需要增加的合同单对象
	 * @param start
	 * @param end
	 * @return
	 */
	public String addBargainBill(BargainBillSaveBean formBean,String start,String end);
	
	/**
	 * 进行导入
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
	 * 导入数据进行储存
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
	 * 查询单价，全省定标电量，电费
	 * @param ammeterid
	 * @return
	 */
	//public BargainBillCountBean getCount(String ammeterid);
	
}
