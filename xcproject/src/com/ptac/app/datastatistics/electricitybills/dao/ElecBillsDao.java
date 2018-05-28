package com.ptac.app.datastatistics.electricitybills.dao;

import java.util.List;
import com.ptac.app.datastatistics.electricitybills.bean.ElecBillsBean;

/**
 * @author 李靖
 * @see 电费缴纳明细Dao层
 */
public interface ElecBillsDao {

	/**
	 * @author 李靖
	 * @see 电费缴纳明细查询电费合计
	 */
	ElecBillsBean query(List<ElecBillsBean> list);

	/**
	 * @author 李靖
	 * @see 电费缴纳明细导出
	 */
	List<ElecBillsBean> export(String whereStr, String loginId);

}
