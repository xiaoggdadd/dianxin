package com.ptac.app.news.dao;

import java.util.List;

import com.ptac.app.news.bean.GGBean;

public interface NewGongGaoDao {

	/** ��ȡδ�������¹���
	 * @return
	 */
	List<GGBean> getNewGongGao(String accountname);
	void insertNews(String ggid,String accountid);
}
