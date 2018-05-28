package com.ptac.app.statisticcollect.province.estimatecontrast.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.estimatecontrast.bean.EstimateContrastBean;

/**确定后暂估数据对照查询Dao
 * @author WangYiXiao 2014-4-29
 *
 */
public interface EstimateContrastDao {
/**查询
 * @param zangumonth (String) 暂估月份
 * @return
 */
List<EstimateContrastBean> query(String zangumonth);
}
