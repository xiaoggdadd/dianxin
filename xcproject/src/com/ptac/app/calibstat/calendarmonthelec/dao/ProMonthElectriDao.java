package com.ptac.app.calibstat.calendarmonthelec.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ptac.app.calibstat.calendarmonthelec.bean.ProMonthElectriBean;

public interface ProMonthElectriDao {
	
	//省级自然月用电统计 查询
	/**
	 * @author zx
	 * @param whereStr 页面传的过滤条件拼接的字符串
	 * @param  begindate  起始月份  date类型
	 * @param  enddate  结束月份  date类型
	 * @param  beginTime  起始月份 String类型
	 * @param  endTime  结束月份  String类型
	 * @param  l    （起始月份-结束月份+1）  日期相减值；日期间隔长度
	 * @return  list 类型
	 * */
	List<Object> proMonthSele(String whereStr,Date begindate,Date enddate,String beginTime,String endTime,int l );

}
