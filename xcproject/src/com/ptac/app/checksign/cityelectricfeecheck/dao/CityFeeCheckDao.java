package com.ptac.app.checksign.cityelectricfeecheck.dao;

import java.io.IOException;
import java.util.List;

import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;

/**
 * @see 市级电费审核dao层接口
 * @author WangYiXiao 2014-1-23
 */
public interface CityFeeCheckDao {

	/**
	 * @param whereStr (String) 查询条件
	 * @param loginId (String) 权限过滤条件
	 * @param lrbz1 (String) 电费录入标志
	 * @param lrbz2 (String) 预付费录入标志
	 * @author WangYiXiao 2014-1-23
	 * @return (List<CityElectricFeeCheck>) 市级电费审核查询结果字段
	 * @see 市级电费审核查询
	 */
	public List<CityElectricFeeCheck> queryCityFeeCheck(String whereStr,String loginId,String lrbz1,String lrbz2);
	
	/**
	 * @param (List<CityElectricFeeCheck>)
	 * @return (String[]) list[0]电量和，list[1]电费和
	 * @see 电量，电费和
	 * @author WangYiXiao 2014-1-24
	 */
	public String[] total(List<CityElectricFeeCheck> list);
	
	 /**
	  * @param personnal (String) 审核人
	  * @param cityaudit (String) 审核标志
	  * @param chooseIdStr1 (String) 电费uuid
	  * @param chooseIdStr2 （String） 预付费uuid
	  * @param bz (String) msg标志
	  * @see 市级电费审核
	  * @param accountId (String) 账号id
	  * @author WngYiXio 2014-1-24
	  * @return String 审核信息msg
	  */
	public String CheckCityFees(String personnal,String cityaudit,String chooseIdStr1,String chooseIdStr2,String bz);

	/**
	 * @author lijing
	 * @see 济宁市级电费审核通过、不通过、取消通过方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String CheckCityFees1(String personnal, String cityaudit,
			String chooseIdStr1, String chooseIdStr2, String bz,
			String cause);
	
}
