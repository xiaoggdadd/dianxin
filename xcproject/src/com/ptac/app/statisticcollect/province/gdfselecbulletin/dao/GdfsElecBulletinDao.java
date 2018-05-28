package com.ptac.app.statisticcollect.province.gdfselecbulletin.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.gdfselecbulletin.bean.GdfsElecBulletinBean;

public interface GdfsElecBulletinDao {
	/**获取供电方式电量通报详细信息
	 * @return
	 */
	/**
	 * @param yearyue 月份信息
	 * @param property 站点属性
	 * @param status 审核状态
	 * @param zzs 是否包含增值税
	 * @return
	 */
	List<GdfsElecBulletinBean> queryDetails(String yearyue,String property,String status);
	
	/** 合计
	 * @param list
	 * @return
	 */
	String[] total(List<GdfsElecBulletinBean> list);
	
}
