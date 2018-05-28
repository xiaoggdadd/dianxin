package com.ptac.app.calibstat.dao;

import java.util.ArrayList;

import com.ptac.app.calibstat.bean.CalibStatBean;

/**
 * @author 李靖
 * @see 定标统计Dao层接口
 */
public interface CalibStatDao {

	//明细查询、导出
	ArrayList queryMingXi(String whereStr, String loginId);
	//单站点超标分析查询
	CalibStatBean querySingleSite(String whereStr,String str,String loginId);

}
