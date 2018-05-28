package com.ptac.app.tjhz.dao;

import java.util.List;

import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.tjhz.bean.GdfsBean;

public interface GdfsDao {
	/**
	 * @param whereStr (String) sql语句后半段
	 * @param loginId (String) 权限过滤条件 
	 * @return (GdfsBean)
	 * @see 供电方式统计查询和导出
	 * @author MingQingYong 2014-02-19
	 */
	public List<GdfsBean> queryGdfs(String whereStr, String loginId,String bz);
	/**
	 * @param list List(GdfsBean)
	 * @return (GdfsBean)
	 * @see 总站点数量 直供电站点数量 转供电站点数量 总金额 直供电金额(万元) 转供电金额(万度) 直供电电量(万度) 转供电电量(万度) 
	 * @author MingQingYong 2014-02-19
	 */
	public GdfsBean total(List<GdfsBean> list);
}
