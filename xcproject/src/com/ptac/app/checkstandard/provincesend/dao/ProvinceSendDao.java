package com.ptac.app.checkstandard.provincesend.dao;

import java.util.List;

import com.ptac.app.checkstandard.provincesend.bean.DetailBean;
import com.ptac.app.checkstandard.provincesend.bean.ProvinceSendBean;

/** 核实标杆-省级更新及派单dao
 * @author WangYiXiao 2015-2-28
 *
 */
public interface ProvinceSendDao {
	/**
	 * @param shi
	 * @param xian
	 * @param zdsx
	 * @param sjzt 省级状态
	 * @param bl1
	 * @param bl2
	 * @param zdname
	 * @param zdid
	 * @return
	 */
	List<ProvinceSendBean>selectProvinceSend(String wherestr1,String wherestr2,String accountid);
	/**获取超链接站点缴费详情
	 * @param zdid
	 * @return
	 */
	List<DetailBean> getDetail(String zdid);
	/**
	 * @param bgid CHECKSTANDARD id
	 * @param personnal 省级派单人
	 * @return
	 */
	String paiDan(String[] bgid,String personnal);

}
