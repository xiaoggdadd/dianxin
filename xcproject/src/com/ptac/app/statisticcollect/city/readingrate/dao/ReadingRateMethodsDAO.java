package com.ptac.app.statisticcollect.city.readingrate.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateMessageBean;
import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateSelectBean;

/**
 * 抄表率功能的方法接口
 * @author rock
 *
 */
public interface ReadingRateMethodsDAO {
	
	/**
	 * 用来查询抄表率的方法
	 * @author rock
	 * @param bean
	 * @param loginID
	 * @return
	 */
	public List<ReadingRateMessageBean> findReadingRate(ReadingRateSelectBean bean,String loginID,String sql,String sql1);
	
	/**
	 * 获取起始时间
	 * @param time
	 * @return
	 */
	public String getBeginTime(String time);
	/**
	 * 获取结束时间
	 * @param time
	 * @return
	 */
	public String getEndTime(String time);
}
