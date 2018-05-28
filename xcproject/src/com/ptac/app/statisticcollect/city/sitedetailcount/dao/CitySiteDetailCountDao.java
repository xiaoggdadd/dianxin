package com.ptac.app.statisticcollect.city.sitedetailcount.dao;

import java.util.List;


import com.ptac.app.statisticcollect.city.sitedetailcount.bean.CitySiteDetailCountBean;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.CitySiteDetailCountBean1;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.Info;

/**
 * @author lijing
 * @see 报账单审核统计dao层接口
 */
public interface CitySiteDetailCountDao {

	/**
	 * @author lijing
	 * @param whereStr 
	 * @param str 
	 * @param whereStr str String 查询过滤条件
	 * @return List 报账单审核统计审核通过查询结果
	 */
	List<CitySiteDetailCountBean> quarySiteDetailCount(StringBuffer whereStr,
			StringBuffer str, String loginId);

	/**
	 * @author lijing
	 * @param whereStr 
	 * @param str
	 * @param whereStr str String 查询过滤条件
	 * @return List 报账单审核统计审核不通过查询结果
	 */
	List<CitySiteDetailCountBean1> quarySiteDetailCount1(StringBuffer whereStr,
			StringBuffer str, String loginId);

	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(审核通过)
	 */
	List<Info> info(String loginId, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(审核通过)
	 */
	List<Info> info1(String loginId,StringBuffer str,StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(市级、人工审核：不通过，未审核)
	 */
	List<Info> info2(String loginId, StringBuffer str, StringBuffer str1);
	
	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(自动未审核)
	 */
	List<Info> info3(String loginId, StringBuffer str, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(市主任、区县主任审核：不通过，未审核)
	 */
	List<Info> info4(String loginId, StringBuffer str, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(财务审核：不通过，未审核)
	 */
	List<Info> info5(String loginId, StringBuffer str, StringBuffer str1);

	/**
	 * @author lijing
	 * @param str
	 * @param str String 查询过滤条件
	 * @param loginId 
	 * @param str1 
	 * @return List 报账单审核统计详细查询(审核通过)
	 */
	List<Info> info11(String loginId, StringBuffer str, StringBuffer str1);

}
