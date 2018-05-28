package com.ptac.app.chinamobile.dao;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.ptac.app.chinamobile.bean.ChinaMobileBillBean;

public interface ChinaMobileBillCheck {
	/**
	 * 中国移动泰安公司电费单导入的导出数据的查询
	 * 
	 * @author gt_ptac
	 * @param loginid
	 * @return
	 */
	public ArrayList<ChinaMobileBillBean> getDownLoadData(String loginid);
	/**
	 * 获取没有本次超标时间的 电表的初始使用时间
	 * @author gt_ptac
	 * 
	 * @param dianbiaoid
	 * @return
	 */
	public String getCSDS(String dianbiaoid);
	
	/**
	 * 开始导入数据，总共的导入 ，并且记录单条的导入记录
	 * @author gt_ptac
	 * 
	 * @param content
	 * @param cform
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	public Vector input(Vector content, CountForm cform, Account account,
			HttpServletRequest request, HttpServletResponse response);
}
