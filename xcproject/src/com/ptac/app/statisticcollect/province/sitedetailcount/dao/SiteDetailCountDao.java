package com.ptac.app.statisticcollect.province.sitedetailcount.dao;

import java.util.List;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean1;

/**
 * @author lijing
 * @see 报账单审核统计dao层接口
 */
public interface SiteDetailCountDao {

	/**
	 * @author lijing
	 * @param whereStr
	 * @param str 
	 * @param whereStr str String 查询过滤条件
	 * @return List<SiteDetailCountBean> 报账单审核统计通过查询结果
	 */
	List<SiteDetailCountBean> quarySiteDetailCount(StringBuffer whereStr,
			StringBuffer str, String loginId);

	/**
	 * @author lijing
	 * @param whereStr 
	 * @param str 
	 * @param whereStr str String 查询过滤条件
	 * @return List<SiteDetailCountBean1> 报账单审核不通过统计查询结果
	 */
	List<SiteDetailCountBean1> quarySiteDetailCount1(StringBuffer whereStr,
			StringBuffer str, String loginId);
}
