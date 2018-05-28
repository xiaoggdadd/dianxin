package com.ptac.app.checksign.financecheck.dao;

import java.util.List;

import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.ptac.app.checksign.financecheck.bean.FinanceSelectBean;

/**
 * 查询数据和做导出的类
 * @author rock
 */
public interface FinanceSelectDAO {
	/**
	 * 用来查询数据
	 * @author rock
	 * @param fsb FinanceSelectBean：用来查询数据的JavaBean
	 * @return List<ElectricFeesFormBean> 返回一个ElectricFeesFormBean的集合
	 */
	List<ElectricFeesFormBean> MessageSelect(FinanceSelectBean fsb);//数据查询
	
	/**
	 * 用来做导出的方法
	 * @param whereStr
	 * @param loginId
	 * @param str1
	 * @param str2
	 * @return
	 */
	List<ElectricFeesFormBean> daochu(String whereStr,String loginId,String str1,String str2);//导出查询
}
