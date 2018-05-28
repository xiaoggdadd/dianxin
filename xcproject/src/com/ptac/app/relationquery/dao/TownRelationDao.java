package com.ptac.app.relationquery.dao;

import java.util.List;

import com.ptac.app.relationquery.bean.RelationQueryBean;

/**
 * @author lijing
 * @see 外租站点与主站点关联市级查询接口
 */
public interface TownRelationDao {

	List<RelationQueryBean> getTownRelation(String whereStr, String loginId);

	List<RelationQueryBean> getXiangQing1(String xian);

	List<RelationQueryBean> getXiangQing2(String xian);
}
