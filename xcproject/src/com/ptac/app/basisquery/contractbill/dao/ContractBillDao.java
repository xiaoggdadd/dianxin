package com.ptac.app.basisquery.contractbill.dao;

import java.util.List;
import com.ptac.app.basisquery.contractbill.bean.ContractBill;

/**
 * @author lijing
 * @see 合同单查询Dao层接口
 *
 */
public interface ContractBillDao {
	
	/**
	 * @author lijing
	 * @see 合同单查询及导出
	 * @param whereStr (String)	//过虑条件
	 * @param loginId (String) //权限过滤条件 
	 * @return (ContractBill)
	 */
	public List<ContractBill> getConBill(String whereStr,String loginId);

}
