package com.ptac.app.statisticcollect.province.pricecriticism.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;
import com.ptac.app.statisticcollect.province.pricecriticism.bean.PriceCriticismBean;

public interface PriceCriticismDao {
	/**
	 * @see  电价超标站点通报
	 * @param wherestr 过滤条件
	 * @param cbbly   超标比例1
	 * @param cbble   超标比例2
	 * @return list 
	 */
	List<PriceCriticismBean> checkPrice(String wherestr,String cbbly,String cbble);
	/**
	 * @see  计算数据之和 返回数组
	 * @param list
	 * @return
	 */
	String[] getSum(List<PriceCriticismBean> list);
}
