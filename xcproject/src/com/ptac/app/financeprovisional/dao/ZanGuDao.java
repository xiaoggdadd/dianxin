package com.ptac.app.financeprovisional.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.financeprovisional.bean.FinanceZanGuBean;

public interface ZanGuDao {
	//暂估数据处理并保存   （地市，暂估结束时间，暂估月份，过滤条件，登录人权限id）
	public String zanGuShuJu(String shi,String zgsj,String zgmonth,String wherestr,String loginId);
	
	//暂估数据查询信息     （过滤条件）
	public ArrayList<FinanceZanGuBean> zanGuChaXun(String whereStr,String loginId);
	
	//计算暂估金额总和
	public FinanceZanGuBean total(List<FinanceZanGuBean> list);
	
	//暂估数据查询专业线分摊信息     （过滤条件）
	public ArrayList<FinanceZanGuBean> zanGuFenTan(String whereStr,String loginId);
}
