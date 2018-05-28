package com.ptac.app.priceover.documentquery.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.priceover.citycheck.bean.CityCheckBean;
import com.ptac.app.priceover.citycheck.dao.CityCheckDao;
import com.ptac.app.priceover.citycheck.dao.CityCheckDaoImp;
import com.ptac.app.priceover.documentquery.bean.*;
import com.ptac.app.priceover.documentquery.dao.DocumentQueryDao;
import com.ptac.app.priceover.documentquery.dao.DocumentQueryDaoImp;
import com.ptac.app.priceover.provinceauditing.dao.ProAuditingDao;
import com.ptac.app.priceover.provinceauditing.dao.ProAuditingDaoImp;

/**
 * @author lijing
 * @see 单价超标市审核
 */
public class DocumentQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String loginName = account.getAccountName();
		String path = request.getContextPath();
		String command = request.getParameter("command");
		//PrintWriter out = response.getWriter();
		
		if("chaxun".equals(command) || "daochu".equals(command)){
			//市审核查询、导出
			String shi = request.getParameter("shi");//市
			String xian = request.getParameter("xian");//区县	
			String property = request.getParameter("property");//站点属性
			String accountmonth = request.getParameter("accountmonth");//报账月份
			String beginbl = request.getParameter("beginbl");//比例
			String endbl = request.getParameter("endbl");//比例
			String zdmc = request.getParameter("zdmc");//站点名称
			String zdid = request.getParameter("zdid");//站点ID	
			String gdfs = request.getParameter("gdfs");//供电方式
			
			StringBuffer whereStr = new StringBuffer();//查询条件
			if (shi != null && !shi.equals("") && !shi.equals("0")){
				 whereStr.append(" AND P.SHI='" + shi + "'");
			}
			if (xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr.append(" AND P.XIAN='" + xian + "'"); 
			}
			if (property != null && !property.equals("") && !property.equals("0")){
				whereStr.append(" AND P.PROPERTY='" + property + "'");
			}
			if (accountmonth != null && accountmonth != "" && !accountmonth.equals("0")){
				whereStr.append(" AND TO_CHAR(P.ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'");
			}
			if (zdmc != null && !zdmc.equals("")){
				whereStr.append(" AND P.JZNAME  like '%" + zdmc + "%'");
			}
			if (zdid != null && !zdid.equals("")){
				whereStr.append(" AND P.ZDID='" + zdid + "'");	
			}
			if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")){
				whereStr.append(" AND P.GDFS='" + gdfs + "'");	
			}
			
			DocumentQueryDao dao = new DocumentQueryDaoImp();
			List<DocumentQueryBean> list = dao.queryExport(whereStr.toString(),loginId,beginbl,endbl);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			
			if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/documentquery/documentquery.jsp").forward(request, response);
			}else if("daochu".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/documentquery/documentqueryExport.jsp").forward(request, response);
			}
		}else if("info".equals(command)){
			//市级 、省级审核详细信息
			String id = request.getParameter("id");
			String bzyf = request.getParameter("bzyf");
			CityCheckDao dao = new CityCheckDaoImp();
			CityCheckBean bean = dao.getInfo(id,bzyf);
			request.setAttribute("bean",bean);
			request.getRequestDispatcher("/web/appJSP/priceover/citycheck/info.jsp").forward(request, response);
		}
	
	}

}
