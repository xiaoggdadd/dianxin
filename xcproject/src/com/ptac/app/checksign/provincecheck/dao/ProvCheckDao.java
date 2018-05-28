package com.ptac.app.checksign.provincecheck.dao;

import java.util.List;
import com.ptac.app.checksign.provincecheck.bean.ProvCheckBean;

/**
 * @author lijing
 * @see 省级新增站点审核接口
 */
public interface ProvCheckDao {

	/**
	 * @see 省级新增站点审核查询
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	List<ProvCheckBean> queryProvince(StringBuffer whereStr, String loginId);

	/**
	 * @see 省级新增站点审核
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	int checkProvFees(String[] ids, String shsign, String personnal);
}
