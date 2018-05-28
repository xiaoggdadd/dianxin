package com.ptac.app.statisticcollect.city.sitedetailcount.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.CitySiteDetailCountBean;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.CitySiteDetailCountBean1;
import com.ptac.app.statisticcollect.city.sitedetailcount.bean.Info;
import com.ptac.app.statisticcollect.city.sitedetailcount.dao.CitySiteDetailCountDao;
import com.ptac.app.statisticcollect.city.sitedetailcount.dao.CitySiteDetailCountDaoImpl;

/**
 * @author lijing
 * @see 报账单审核统计
 */
public class CitySiteDetailCountServlet extends HttpServlet {

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
		}else if("xiangxi".equals(command)){
			xiangxi(request,response);
		}else if("xiangxi1".equals(command)){
			xiangxi1(request,response);
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
		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		
		StringBuffer whereStr = new StringBuffer();
		StringBuffer str = new StringBuffer();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr.append(" and z.shi='" + shi + "'");
			str.append(" and zd.shi='"+shi+"'");
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" and z.xian='" + xian + "'");
		}
		
		if(beginyue != null && !beginyue.equals("") && !beginyue.equals("0") && endyue != null && !endyue.equals("") && !endyue.equals("0")){
			whereStr.append(" and to_char(yf.ACCOUNTMONTH,'yyyy-mm')> = '"+beginyue+"'and to_char(yf.ACCOUNTMONTH,'yyyy-mm')< = '"+endyue+"'");
		}
		
		CitySiteDetailCountDao dao = new CitySiteDetailCountDaoImpl();
		
		List<CitySiteDetailCountBean> list = dao.quarySiteDetailCount(whereStr,str,loginId);
		int intnum = list.size();
		
		request.setAttribute("list", list);
		request.setAttribute("intnum", intnum);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		
		String command = request.getParameter("command");
		if("chaxun".equals(command)){//传到查询页面 
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/sitedetailcount/sitedetailcount.jsp").forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/sitedetailcount/报账单审核统计导出.jsp").forward(request, response);
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
	 * @see 报账单审核统计不通过查询及导出
	 */
	private void queryInfo1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		
		StringBuffer whereStr = new StringBuffer();
		StringBuffer str = new StringBuffer();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr.append(" and z.shi='" + shi + "'");
			str.append(" and zd.shi='"+shi+"'");
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" and z.xian='" + xian + "'");
		}
		if(beginyue != null && !beginyue.equals("") && !beginyue.equals("0") && endyue != null && !endyue.equals("") && !endyue.equals("0")){
			whereStr.append(" and to_char(yf.ACCOUNTMONTH,'yyyy-mm')> = '"+beginyue+"'and to_char(yf.ACCOUNTMONTH,'yyyy-mm')< = '"+endyue+"'");
		}
		
		CitySiteDetailCountDao dao = new CitySiteDetailCountDaoImpl();
		List<CitySiteDetailCountBean1> list1 = dao.quarySiteDetailCount1(whereStr,str,loginId);
		int intnum = list1.size();
		
		request.setAttribute("list1", list1);
		request.setAttribute("intnum", intnum);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		
		String command = request.getParameter("command");
		if("chaxun1".equals(command)){//传到审核不通过查询页面 
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/sitedetailcount/sitedetailcount1.jsp").forward(request, response);
		}else if("daochu1".equals(command)){//传到审核不通过导出页面
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/sitedetailcount/报账单审核统计导出1.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @see 报账单审核统计详细页面(审核通过)
	 */
	private void xiangxi(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
	    
	    String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		
		StringBuffer str1 = new StringBuffer();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			str1.append(" AND Z.SHI='"+shi+"'");
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			str1.append(" AND Z.XIAN='" + xian + "'");
		}
		if(beginyue != null && !beginyue.equals("") && !beginyue.equals("0") && endyue != null && !endyue.equals("") && !endyue.equals("0")){
			str1.append(" AND to_char(A.ACCOUNTMONTH,'yyyy-mm')> = '"+beginyue+"'and to_char(A.ACCOUNTMONTH,'yyyy-mm')< = '"+endyue+"'");
		}
		
		CitySiteDetailCountDao dao = new CitySiteDetailCountDaoImpl();
		List<Info> list = null;
		int intnum = 0;
		String cs = request.getParameter("cs");
		if(cs.equals("zd")){
			str1.append(" AND A.AUTOAUDITSTATUS = '1'");
			list = dao.info(loginId,str1);
		}else if(cs.equals("xgly")){
			StringBuffer str = new StringBuffer();
			str.append("A.MANUALAUDITNAME,TO_CHAR(A.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,A.MANPASSMEMO");
			str1.append(" AND (A.MANUALAUDITSTATUS = '1' or A.MANUALAUDITSTATUS = '2' or A.MANUALAUDITSTATUS = '-1') ");
			list = dao.info1(loginId,str,str1);
		}else if(cs.equals("qxzr")){
			StringBuffer str = new StringBuffer();
			str.append("A.COUNTYAUDITNAME,TO_CHAR(A.COUNTYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') COUNTYAUDITTIME");
			str1.append(" AND A.COUNTYAUDITSTATUS = '1'");
			list = dao.info11(loginId,str,str1);
		}else if(cs.equals("sgly")){
			StringBuffer str = new StringBuffer();
			str.append("A.CITYAUDITPENSONNEL,TO_CHAR(A.CITYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYAUDITTIME");
			str1.append(" AND A.CITYAUDIT = '1'");
			list = dao.info11(loginId,str,str1);
		}else if(cs.equals("szr")){
			StringBuffer str = new StringBuffer();
			str.append("A.CITYZRAUDITNAME,TO_CHAR(A.CITYZRAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYZRAUDITTIME");
			str1.append(" AND A.CITYZRAUDITSTATUS = '1'");
			list = dao.info11(loginId,str,str1);
		}else if(cs.equals("cw")){
			StringBuffer str = new StringBuffer();
			str.append("A.FINANCIALOPERATOR,TO_CHAR(A.FINANCIALDATETIME,'yyyy-mm-dd hh24:mi:ss') FINANCIALDATETIME");
			str1.append(" AND A.MANUALAUDITSTATUS = '2'");
			list = dao.info11(loginId,str,str1);
		}else if(cs.equals("dy")){
			str1.append(" AND A.LIUCHENGHAO IS NOT NULL");
			list = dao.info(loginId,str1);
		}
		
		intnum = list.size();
		request.setAttribute("list", list);
		request.setAttribute("intnum", intnum);
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/sitedetailcount/xiangxi.jsp").forward(request, response);

	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ServletException 
	 * @see 报账单审核统计详细页面(审核不通过)
	 */
	private void xiangxi1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
	    
	    String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		
		StringBuffer str1 = new StringBuffer();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			str1.append(" AND Z.SHI='"+shi+"'");
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			str1.append(" AND Z.XIAN='" + xian + "'");
		}
		if(beginyue != null && !beginyue.equals("") && !beginyue.equals("0") && endyue != null && !endyue.equals("") && !endyue.equals("0")){
			str1.append(" AND to_char(A.ACCOUNTMONTH,'yyyy-mm')> = '"+beginyue+"'and to_char(A.ACCOUNTMONTH,'yyyy-mm')< = '"+endyue+"'");
		}
		
		CitySiteDetailCountDao dao = new CitySiteDetailCountDaoImpl();
		List<Info> list = null;
		int intnum = 0;
		String cs1 = request.getParameter("cs1");
		if(cs1.equals("zd")){
			StringBuffer str = new StringBuffer();
			str.append("A.AUTOAUDITSTATUS");
			str1.append(" AND A.AUTOAUDITSTATUS <> '1'");
			list = dao.info3(loginId,str,str1);
		}else if(cs1.equals("xgly")){
			StringBuffer str = new StringBuffer();
			str.append("A.MANUALAUDITNAME,TO_CHAR(A.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,A.MANUALAUDITSTATUS");
			str1.append(" AND A.MANUALAUDITSTATUS <> '1' AND A.MANUALAUDITSTATUS <> '2' AND A.MANUALAUDITSTATUS <> '-1' ");
			list = dao.info2(loginId,str,str1);
		}else if(cs1.equals("qxzr")){
			StringBuffer str = new StringBuffer();
			str.append("A.COUNTYAUDITNAME,TO_CHAR(A.COUNTYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') COUNTYAUDITTIME,A.COUNTYAUDITSTATUS");
			str1.append(" AND A.COUNTYAUDITSTATUS <> '1'");
			list = dao.info4(loginId,str,str1);
		}else if(cs1.equals("sgly")){
			StringBuffer str = new StringBuffer();
			str.append("A.CITYAUDITPENSONNEL,TO_CHAR(A.CITYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYAUDITTIME,A.CITYAUDIT");
			str1.append(" AND A.CITYAUDIT <> '1'");
			list = dao.info2(loginId,str,str1);
		}else if(cs1.equals("szr")){
			StringBuffer str = new StringBuffer();
			str.append("A.CITYZRAUDITNAME,TO_CHAR(A.CITYZRAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYZRAUDITTIME,A.CITYAUDIT");
			str1.append(" AND A.CITYZRAUDITSTATUS <> '1'");
			list = dao.info4(loginId,str,str1);
		}else if(cs1.equals("cw")){
			StringBuffer str = new StringBuffer();
			str.append("A.FINANCIALOPERATOR,TO_CHAR(A.FINANCIALDATETIME,'yyyy-mm-dd hh24:mi:ss') FINANCIALDATETIME,A.MANUALAUDITSTATUS");
			str1.append(" AND A.MANUALAUDITSTATUS <> '2'");
			list = dao.info5(loginId,str,str1);
		}
		
		intnum = list.size();
		request.setAttribute("list", list);
		request.setAttribute("intnum", intnum);
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/sitedetailcount/xiangxi1.jsp").forward(request, response);
		
	}

}
