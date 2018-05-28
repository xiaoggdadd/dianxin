package com.ptac.app.sitemanage.dbstationmanage.dao;

import java.util.List;

import com.ptac.app.sitemanage.dbstationmanage.bean.DbStationManageBean;

/**多表站点维护--查询,更改
 * @author WangYiXiao 2014-12-16
 *
 */
public interface DbStationManageDao {

	/**多表站点维护--站点信息查询
	 * @param whereStr (String) 过滤条件
	 * @return (List<DbStationManageBean>) 站点信息
	 */
	List<DbStationManageBean> getStationInfo(String whereStr,String loginId);
	/**多表站点维护--更改多表状态
	 * @param wherezdid (String) 站点ID
	 * @param status (String) 多表状态
	 * @return
	 */
	String checkStatus(String wherezdid,String status);
}
