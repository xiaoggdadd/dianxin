package com.ptac.app.calibstat.citysiteanalysis.dao;
import java.util.List;
import com.ptac.app.calibstat.citysiteanalysis.bean.CityBean;

/**
 * @author 李靖
 * @see 地市站点超标分析接口层
 */
public interface CityDao {

	/**
	 * @see 地市站点超标分析具体图表信息
	 */
	CityBean getXiangXi(String str, String loginId);

	/**
	 * @see 地市站点超标分析 查询、导出
	 */
	List<CityBean> getQueryExport(String str, String loginId);

}
