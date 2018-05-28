package com.ptac.app.statisticcollect.province.electricitycriticism.dao;

import java.util.List;
import com.ptac.app.statisticcollect.province.electricitycriticism.bean.EleCriticismBean;

public interface EleCriticismDao {
	/**
	 * @see  电量超标站点通报
	 * @param wherestr 过滤条件
	 * @param cbbly   超标比例1
	 * @param cbble   超标比例2
	 * @return list 
	 */
	List<EleCriticismBean> checkPrice(String wherestr,String cbbly,String cbble);
	/**
	 * @see  计算数据之和 返回数组
	 * @param list
	 * @return
	 */
	String[] getSum(List<EleCriticismBean> list);
}
