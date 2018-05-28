package com.ptac.app.statisticcollect.city.readingrate.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateMessageBean;
import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateSelectBean;

/**
 * �����ʹ��ܵķ����ӿ�
 * @author rock
 *
 */
public interface ReadingRateMethodsDAO {
	
	/**
	 * ������ѯ�����ʵķ���
	 * @author rock
	 * @param bean
	 * @param loginID
	 * @return
	 */
	public List<ReadingRateMessageBean> findReadingRate(ReadingRateSelectBean bean,String loginID,String sql,String sql1);
	
	/**
	 * ��ȡ��ʼʱ��
	 * @param time
	 * @return
	 */
	public String getBeginTime(String time);
	/**
	 * ��ȡ����ʱ��
	 * @param time
	 * @return
	 */
	public String getEndTime(String time);
}
