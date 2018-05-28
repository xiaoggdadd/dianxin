package com.ptac.app.systemmanage.dao;

import java.util.List;

import com.ptac.app.systemmanage.bean.FangWenLiangBean;
import com.ptac.app.systemmanage.bean.LuRuLiangBean;

public interface LuRuLiangDao {

	List<LuRuLiangBean> queryLuRuCountjs(String shi, String startyear,String endyear);
	List<LuRuLiangBean> queryLuRuCountgl(String shi, String startyear,String endyear);
	List<LuRuLiangBean> getXiangXi(String shi, String startyear,String endyear);
	List<LuRuLiangBean> getXiangXigl(String shi, String startyear,String endyear);
	List<FangWenLiangBean> getFangWenLiang( String startyear,String endyear);
	List<FangWenLiangBean> getFangWenLiangmx( String startyear,String endyear);
}
