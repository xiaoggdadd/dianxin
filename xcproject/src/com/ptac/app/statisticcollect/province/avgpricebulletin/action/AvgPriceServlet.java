package com.ptac.app.statisticcollect.province.avgpricebulletin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.province.avgpricebulletin.bean.AvgPriceBean;
import com.ptac.app.statisticcollect.province.avgpricebulletin.dao.AvgPriceDao;
import com.ptac.app.statisticcollect.province.avgpricebulletin.dao.AvgPriceDaoImp;

/**
 * @author lijing
 * @see 平均单价通报
 */
public class AvgPriceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String beginbztime = request.getParameter("beginbztime");//报账月份
		String endbztime = request.getParameter("endbztime");//报账月份
		String property = request.getParameter("property");//站点属性
		String status = request.getParameter("status");//审核状态

		StringBuffer whereStr = new StringBuffer();//查询条件
		
		if (beginbztime != null && beginbztime != "" && !beginbztime.equals("0")){
			whereStr.append(" AND to_char(ACCOUNTMONTH,'yyyy-mm') >='" + beginbztime + "'");
		}
		if (endbztime != null && endbztime != "" && !endbztime.equals("0")){
			whereStr.append(" AND to_char(ACCOUNTMONTH,'yyyy-mm') <='" + endbztime + "'");
		}
		if ("1".equals(status)){
			whereStr.append(" AND CITYZRAUDITSTATUS='" + status + "'");	
		}else if("2".equals(status)){
			whereStr.append(" AND EFMANUALAUDITSTATUS='" + status + "'");
		}
		
		if (property != null && !property.equals("") && !property.equals("0")){
			whereStr.append(" AND ZPROPERTY='" + property + "'");
		}
		
		String year = beginbztime.substring(0,4);
		String beginMonth = beginbztime.substring(5,7);
		String endMonth = endbztime.substring(5,7);
		
		AvgPriceDao dao = new AvgPriceDaoImp();
		List<AvgPriceBean> list = dao.queryExport(whereStr.toString(), loginId);
		int num = list.size();
		String[] sum = dao.getSum(list);
		AvgPriceBean sumbean = new AvgPriceBean();
		sumbean.setJcdjsum(sum[0]);//基础电价合计
		sumbean.setPjdjsum(sum[1]);//平均电价合计
		sumbean.setPjdjpldsum(sum[2]);//平均电价偏离度合计 
		sumbean.setZgddjsum(sum[3]);//转供电平均电价合计
		sumbean.setZgdpldsum(sum[4]);//转供电平均电价偏离度合计
		
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.setAttribute("sumbean", sumbean);
		request.setAttribute("year", year);
		request.setAttribute("beginmonth", beginMonth);
		request.setAttribute("endmonth", endMonth);
		String command = request.getParameter("command");
		if("chaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/avgpricebulletin/avgprice.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/avgpricebulletin/export.jsp").forward(request, response);
		}
	}

}
