package com.ptac.app.inter.service;

import java.util.List;

import com.ptac.app.inter.bean.AuditBean;
import com.ptac.app.inter.bean.SjInterFace;

public interface SjzcInterfaceDao {
	/**
	 * @param month 帐期时间
	 * @param btime 开始时间
	 * @param etime 结束时间
	 * @return
	 */
	public List<AuditBean> getSjzcData(String month,String btime,String etime);
	
	/**计算总量信息
	 * @param month
	 * @param btime
	 * @param etime
	 * @return
	 */
	public SjInterFace getCount(String month,String btime,String etime);
	
}
