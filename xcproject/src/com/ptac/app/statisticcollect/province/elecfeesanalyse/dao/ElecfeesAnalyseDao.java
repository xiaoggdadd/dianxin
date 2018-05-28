package com.ptac.app.statisticcollect.province.elecfeesanalyse.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.elecfeesanalyse.bean.ElecFeesAnalyseBean;

public interface ElecfeesAnalyseDao {
	/**获取电费分析详细信息
	 * @return
	 */
	/**
	 * @param yearyue 月份信息
	 * @param property 站点属性
	 * @param status 审核状态
	 * @param zzs 是否包含增值税
	 * @param loginid 用户id
	 * @return
	 */
	List<ElecFeesAnalyseBean> queryDetails(String[] yearyue,String property,String status,String zzs);
	String[] total(List<ElecFeesAnalyseBean> list);
}
