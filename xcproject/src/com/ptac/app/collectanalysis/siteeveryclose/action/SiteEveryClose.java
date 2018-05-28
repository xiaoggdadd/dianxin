package com.ptac.app.collectanalysis.siteeveryclose.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.collectanalysis.siteeveryclose.bean.ApplyCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseFeesBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.SiteEveryCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.dao.SiteEveryCloseDao;
import com.ptac.app.collectanalysis.siteeveryclose.dao.SiteEveryCoseDaoImpl;


public class SiteEveryClose extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		if("chaxun".equals(command)|| "daochu".equals(command)){
			chaxundaochu(request, response);
		}else if("xiangqing1".equals(command)){
			xiangqing1(request, response);
		}else if("xiangqing2".equals(command)){
			xiangqing2(request, response);
		}else if("xiangqing3".equals(command)){
			xiangqing3(request, response);
		}
	}
	public void chaxundaochu(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		SiteEveryCloseDao dao = new SiteEveryCoseDaoImpl();
		
		String command = request.getParameter("command");//操作
		String shi = request.getParameter("shi");
		String beginyue = request.getParameter("entrytimeQS");
		String endyue = request.getParameter("entrytimeJS");
		String zdsx = request.getParameter("jzproperty");
		String zdlx = request.getParameter("stationtype");
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		
		List<SiteEveryCloseBean> beanlist = dao.getSiteEveryClose(shi, zdsx, zdlx, beginyue, endyue, loginId);
		int numcolor = beanlist.size();
		
		request.setAttribute("emcsList", beanlist);
		request.setAttribute("colornum",numcolor);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("jzproperty", zdsx);
		request.setAttribute("stationtype", zdlx);
		request.setAttribute("shi", shi);
		if("chaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/collectanalysis/siteeveryclose/siteeveryclose.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/collectanalysis/siteeveryclose/站点每月关闭情况统计导出.jsp").forward(request, response);
		}
		}
	public void xiangqing1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		SiteEveryCloseDao dao = new SiteEveryCoseDaoImpl();
		
		String shi = request.getParameter("shi");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		String zdsx = request.getParameter("zdsx");
		String zdlx = request.getParameter("zdlx");
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		
		List<ApplyCloseBean> beanlist = dao.getSiteApplyClose(shi, zdsx, zdlx, beginyue, endyue, loginId);
		int numcolor = beanlist.size();
		
		request.setAttribute("emcsList", beanlist);
		request.setAttribute("colornum",numcolor);
		request.getRequestDispatcher("/web/appJSP/collectanalysis/siteeveryclose/applyclose.jsp").forward(request, response);
		}
	public void xiangqing2(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		SiteEveryCloseDao dao = new SiteEveryCoseDaoImpl();
		
		String shi = request.getParameter("shi");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		String zdsx = request.getParameter("zdsx");
		String zdlx = request.getParameter("zdlx");
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		
		List<PassCloseBean> beanlist = dao.getSitePassClose(shi, zdsx, zdlx, beginyue, endyue, loginId);
		int numcolor = beanlist.size();
		
		request.setAttribute("emcsList", beanlist);
		request.setAttribute("colornum",numcolor);
		request.getRequestDispatcher("/web/appJSP/collectanalysis/siteeveryclose/passclose.jsp").forward(request, response);
		}
	public void xiangqing3(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		SiteEveryCloseDao dao = new SiteEveryCoseDaoImpl();
		
		String shi = request.getParameter("shi");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		String zdsx = request.getParameter("zdsx");
		String zdlx = request.getParameter("zdlx");
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		
		List<PassCloseFeesBean> beanlist = dao.getSitePassCloseFees(shi, zdsx, zdlx, beginyue, endyue, loginId);
		int numcolor = beanlist.size();
		
		request.setAttribute("emcsList", beanlist);
		request.setAttribute("colornum",numcolor);
		request.getRequestDispatcher("/web/appJSP/collectanalysis/siteeveryclose/passclosefees.jsp").forward(request, response);
		}
}
