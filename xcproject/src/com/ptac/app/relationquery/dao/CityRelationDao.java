package com.ptac.app.relationquery.dao;

import java.util.List;
import com.ptac.app.relationquery.bean.RelationQueryBean;

/**
 * @author lijing
 * @see ����վ������վ�����ʡ����ѯ�ӿ�
 */
public interface CityRelationDao {

	List<RelationQueryBean> getCityRelation(String whereStr, String loginId);

}
