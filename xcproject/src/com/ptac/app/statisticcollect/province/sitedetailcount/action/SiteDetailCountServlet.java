package com.ptac.app.statisticcollect.province.sitedetailcount.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean1;
import com.ptac.app.statisticcollect.province.sitedetailcount.dao.SiteDetailCountDao;
import com.ptac.app.statisticcollect.province.sitedetailcount.dao.SiteDetailCountDaoImpl;

/**
 * @author lijing
 * @see 报账单审核统计
 */
public class SiteDetailCountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		if("chaxun".equals(command) || "daochu".equals(command)){
			queryInfo(request,response);
			
		}else if("chaxun1".equals(command) || "daochu1".equals(command)){
			queryInfo1(request,response);
		}
	}

	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see 报账单审核统计通过查询及导出
	 */
	private void queryInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
	    
		String shi = request.getParameter("shi");
//		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
//		String bztime = request.getParameter("bztime");
		
		StringBuffer whereStr = new StringBuffer();
		StringBuffer str = new StringBuffer();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr.append(" and z.shi='" + shi + "'");
			str.append(" and zd.shi='"+shi+"'");
		}
//		if(xian != null && !xian.equals("") && !xian.equals("0")){
//			whereStr.append(" and z.xian='" + xian + "'");
//		}
		
		if(beginyue != null && !beginyue.equals("") && !beginyue.equals("0") && endyue != null && !endyue.equals("") && !endyue.equals("0")){
			whereStr.append(" and TO_CHAR(yf.ACCOUNTMONTH,'yyyy-mm')> = '"+beginyue+"'and TO_CHAR(yf.ACCOUNTMONTH,'yyyy-mm')< = '"+endyue+"'");
		}
		
//		if(bztime != null && !bztime.equals("") && !bztime.equals("0")){
//			whereStr.append(" and yf.ACCOUNTMONTH = '"+bztime+"'");
//		}
		
		SiteDetailCountDao dao = new SiteDetailCountDaoImpl();
		List<SiteDetailCountBean> list = dao.quarySiteDetailCount(whereStr,str,loginId);
		int intnum = list.size();
		
		request.setAttribute("list", list);
		request.setAttribute("intnum", intnum);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		
		String command = request.getParameter("command");
		if("chaxun".equals(command)){//传到查询页面 
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/sitedetailcount/SiteDetailCount.jsp").forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/sitedetailcount/站点明细统计导出.jsp").forward(request, response);
		}
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws ServletException
	 * @throws IOException
	 * @see 报账单审核不通过统计查询及导出
	 */
	private void queryInfo1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");
//		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		
		StringBuffer whereStr = new StringBuffer();
		StringBuffer str = new StringBuffer();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr.append(" and z.shi='" + shi + "'");
			str.append(" and zd.shi='"+shi+"'");
		}
//		if(xian != null && !xian.equals("") && !xian.equals("0")){
//			whereStr.append(" and z.xian='" + xian + "'");
//		}
		if(beginyue != null && !beginyue.equals("") && !beginyue.equals("0") && endyue != null && !endyue.equals("") && !endyue.equals("0")){
			whereStr.append(" and TO_CHAR(yf.ACCOUNTMONTH,'yyyy-mm')> = '"+beginyue+"'and TO_CHAR(yf.ACCOUNTMONTH,'yyyy-mm')< = '"+endyue+"'");
		}
		
		SiteDetailCountDao dao = new SiteDetailCountDaoImpl();
		List<SiteDetailCountBean1> list1 = dao.quarySiteDetailCount1(whereStr,str,loginId);
		int intnum = list1.size();
		
		request.setAttribute("list1", list1);
		request.setAttribute("intnum", intnum);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		
		String command = request.getParameter("command");
		if("chaxun1".equals(command)){//传到审核不通过查询页面 
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/sitedetailcount/sitedetailcount1.jsp").forward(request, response);
		}else if("daochu1".equals(command)){//传到审核不通过导出页面
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/sitedetailcount/站点明细统计导出1.jsp").forward(request, response);
		}
	}
	
}
