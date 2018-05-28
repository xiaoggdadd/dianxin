package com.ptac.app.calibstat.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.bean.OverSite;
import com.ptac.app.calibstat.bean.OverSiteDetial;
import com.ptac.app.calibstat.dao.OverSiteDao;
import com.ptac.app.calibstat.dao.OverSiteDaoImpl;

/**
 * @author WangYiXiao
 * @see 定标统计查询（超标站点查询、导出、详细）
 */
public class OverSiteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		if("chaxun".equals(command) || "daochu".equals(command)){
			addSiteExport(request,response);
		}else if("xiangqing".equals(command)){
			xiangQing(request,response);
		}
}

	/**
	 * @see 超标站点 查询，导出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSiteExport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		//-----dao-----
		OverSiteDao dao = new OverSiteDaoImpl();
		//-----查询条件-----
		String shi = request.getParameter("shi");//市
		String month = request.getParameter("month");//月份
		String property = request.getParameter("property");//站点属性
		String zdlx = request.getParameter("zdlx");//站点类型
		String cbmc = request.getParameter("cbmc");//超标前n名
		String cbbl = request.getParameter("cbbl");//超标比例大于
		String cbjdz = request.getParameter("cbjdz");//超标绝对值
		
		if (property == null || ("").equals(property) || ("0").equals(property)) {
			property = "";
		}
		if (zdlx == null || ("").equals(zdlx) || ("0").equals(zdlx)) {
			zdlx = "";
		}
	
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();

		//-----查询-----
		List<OverSite> beanlist = dao.getOverSite(shi,month,property,zdlx,cbmc.trim(),cbbl.trim(),cbjdz.trim(),loginId);
		//-----结果条数-----
		int num = beanlist.size();

		request.setAttribute("beanlist", beanlist);
		request.setAttribute("num", num);
		//-----跳转页面-----
		if("chaxun".equals(command)){//新增站点查询, 导出
			request.getRequestDispatcher("/web/appJSP/calibstat/overshotSite/overshotSite.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/overshotSite/超标站点查询导出.jsp").forward(request, response);
		}	
	}
	public void xiangQing(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//-----dao-----
		OverSiteDao dao = new OverSiteDaoImpl();
		//-----查询条件-----
		String zdid = request.getParameter("zdid");//市

	
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();

		//-----查询-----
		OverSiteDetial bean = dao.getOverSiteDetial(zdid);
		OverSiteDetial sdb = dao.getOverSiteSDB(zdid);
		List<OverSiteDetial> other = dao.getOverSiteDL(zdid);
		request.setAttribute("bean", bean);
		request.setAttribute("sdb", sdb);
		request.setAttribute("other", other);
		//-----跳转页面-----
		request.getRequestDispatcher("/web/appJSP/calibstat/overshotSite/xiangxi.jsp").forward(request, response);
			
	}

}
