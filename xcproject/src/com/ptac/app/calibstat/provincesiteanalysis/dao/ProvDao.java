package com.ptac.app.calibstat.provincesiteanalysis.dao;

import java.util.List;
import com.ptac.app.calibstat.provincesiteanalysis.bean.ProvBean;

/**
 * @author 李靖
 * @see 全省站点超标分析接口层
 */
public interface ProvDao {

	/**
	 * @see 全省站点超标分析 查询、导出
	 */
	List<ProvBean> getQueryExport(String str, String loginId);

	/**
	 * @see 全省站点超标分析具体图表信息
	 */
	ProvBean getXiangXi(String str, String loginId);

}
