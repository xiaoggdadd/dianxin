package com.ptac.app.datastatistics.powermode.dao;

import java.util.ArrayList;

import com.ptac.app.datastatistics.powermode.bean.PowerBean;

/**
 * @author 李靖
 * @see 供电方式缴费统计接口层
 */
public interface PowerDao {

	ArrayList<PowerBean> queryExport(String whereStr, String loginId);

	/**
	 * @author 李靖
	 * @see 计算总的报账电费
	 */
	PowerBean totalMoney(ArrayList<PowerBean> list);

}
