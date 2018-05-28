package com.ptac.app.checkstandard.countyverify.dao;

import java.util.List;

import com.ptac.app.checkstandard.countyverify.bean.CountyVerifyBean;

/**
 * @author lijing
 * @see 区县签收及核实信息接口层
 */
public interface CountyVerifyDao {

	/**
	 * @see 查询区县签收及核实信息
	 * @param string
	 * @param loginId
	 * @return list
	 */
	List<CountyVerifyBean> queryCountyVerify(String string, String loginId, String beginbl, String endbl);

	/**
	 * 区县退单
	 * @param id
	 * @param loginName
	 * @return
	 */
	int TDUpdate(String id, String loginName);

	/**
	 * 区县核实信息查询
	 * @param zdid
	 * @return
	 */
	List<CountyVerifyBean> getInfo(String zdid);

	/**
	 * @see 区县核实信息保存
	 * @return
	 */
	String commit(CountyVerifyBean bean, String loginName);

}
