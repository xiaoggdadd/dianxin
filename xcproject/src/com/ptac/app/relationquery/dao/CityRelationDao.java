package com.ptac.app.relationquery.dao;

import java.util.List;
import com.ptac.app.relationquery.bean.RelationQueryBean;

/**
 * @author lijing
 * @see 外租站点与主站点关联省级查询接口
 */
public interface CityRelationDao {

	List<RelationQueryBean> getCityRelation(String whereStr, String loginId);

}
