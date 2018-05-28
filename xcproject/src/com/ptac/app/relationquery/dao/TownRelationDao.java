package com.ptac.app.relationquery.dao;

import java.util.List;

import com.ptac.app.relationquery.bean.RelationQueryBean;

/**
 * @author lijing
 * @see ����վ������վ������м���ѯ�ӿ�
 */
public interface TownRelationDao {

	List<RelationQueryBean> getTownRelation(String whereStr, String loginId);

	List<RelationQueryBean> getXiangQing1(String xian);

	List<RelationQueryBean> getXiangQing2(String xian);
}
