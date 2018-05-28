package com.ptac.app.statisticcollect.city.readingrate.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateMessageBean;
import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateSelectBean;
import com.ptac.app.statisticcollect.city.readingrate.dao.ReadingRateMethodsDAO;
import com.ptac.app.statisticcollect.city.readingrate.dao.ReadingRateMethodsDAOImp;

/**
 * 用于导出的Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class ReadingRateOutputServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
		String loginID = request.getParameter("loginId");
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String startmonth = request.getParameter("startmonth");
		String endmonth = request.getParameter("endmonth");
		String zdlx = request.getParameter("zdlx");
		String zdsx = request.getParameter("zdsx");
		String gdfs = request.getParameter("gdfs");
		//构造Bean
		ReadingRateSelectBean bean = new ReadingRateSelectBean(shi, xian, startmonth, endmonth, zdlx, zdsx,gdfs);
		ReadingRateMethodsDAO dao = new ReadingRateMethodsDAOImp();
		StringBuffer SQL = new StringBuffer();//过滤条件
		StringBuffer SQL1 = new StringBuffer();//过滤条件
		
		if(shi!=null&&!"".equals(shi)){
			SQL.append(" and zd.shi='");
			SQL.append(shi);
			SQL.append("' ");
		}
		if(xian!=null&&!"".equals(xian)){
			SQL.append(" and zd.xian='");
			SQL.append(xian);
			SQL.append("' ");
		}
		if(startmonth!=null&&!"".equals(startmonth)){
			SQL1.append(" and to_char(DD.THISDATETIME,'yyyy-mm-dd') >'");
			SQL1.append(dao.getBeginTime(startmonth));
			SQL1.append("' ");
		}
		if(endmonth!=null&&!"".equals(endmonth)){
			SQL1.append(" and to_char(DD.THISDATETIME,'yyyy-mm-dd')<'");
			SQL1.append(dao.getEndTime(endmonth));
			SQL1.append("' ");
		}
		if(zdlx!=null&&!"".equals(zdlx)){
			SQL.append(" and zd.STATIONTYPE='");
			SQL.append(zdlx);
			SQL.append("' ");
		}
		if(zdsx!=null&&!"".equals(zdsx)){
			SQL.append(" and zd.PROPERTY='");
			SQL.append(zdsx);
			SQL.append("' ");
		}
		if(gdfs!=null&&!"".equals(gdfs)){
			SQL.append(" and zd.GDFS='");
			SQL.append(gdfs);
			SQL.append("' ");
		}
		
		List<ReadingRateMessageBean> ls = dao.findReadingRate(bean, loginID,SQL.toString(),SQL1.toString());		
		request.setAttribute("Info",ls);
		request.setAttribute("num",	ls.size());
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/readingrate/ReadingRateOutput.jsp").forward(request, response);
	}

}
