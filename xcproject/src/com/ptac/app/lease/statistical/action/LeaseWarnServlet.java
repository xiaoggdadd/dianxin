package com.ptac.app.lease.statistical.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.lease.statistical.bean.LeaseStatBean;
import com.ptac.app.lease.statistical.dao.LeaseWarnDao;
import com.ptac.app.lease.statistical.dao.LeaseWarnDaoImp;

public class LeaseWarnServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";//城市
		String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "";//区县
		String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "";//乡镇
		String zdname = request.getParameter("zdname") != null ? request.getParameter("zdname"): "";//站点名称
		String leasename = request.getParameter("leasename") != null ? request.getParameter("leasename") : "";//合同名称
		String jlts = request.getParameter("jlts") != null ? request.getParameter("jlts") : "";//距缴费天数
		
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " and zd.shi='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " and zd.xian='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " and zd.xiaoqu='" + xiaoqu + "'";
		}
		if (leasename != null && leasename != "" && !leasename.equals("0")) {
			whereStr = whereStr + " and LB.LEASENAME like '%" + leasename + "%'";
		}
		if (zdname != null && zdname != "" && !zdname.equals("0")) {
			whereStr = whereStr + " and zd.JZNAME like '%" + zdname + "%'";
		}
		
		ArrayList list = new ArrayList();
		LeaseWarnDao dao = new LeaseWarnDaoImp();
		list = dao.queryZlyjMsg(whereStr,loginId,jlts);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		
		String command = request.getParameter("command");
		if ("chaxun".equals(command)) {// 传到租赁费缴费预警查询页面
			request.getRequestDispatcher("/web/appJSP/lease/statistical/leaseFeeWarn.jsp")
					.forward(request, response);
		} else if ("daochu".equals(request.getParameter("command"))) {// 传到租赁费缴费预警导出页面
			request.getRequestDispatcher("/web/appJSP/lease/statistical/租赁费缴费预警.jsp")
					.forward(request, response);
		}
	}

}
