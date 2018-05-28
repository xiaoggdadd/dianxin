package com.ptac.app.statisticcollect.province.accountwithout.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;
import com.ptac.app.statisticcollect.province.accountwithout.dao.AccountWithoutDao;
import com.ptac.app.statisticcollect.province.accountwithout.dao.AccountWithoutDaoImp;

/**
 * @author lijing
 * @see 长期未报账汇总
 */
public class AccountWithoutServlet extends HttpServlet {

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
		
		String bztime = request.getParameter("bztime");//截止月份
		StringBuffer whereStr = new StringBuffer();//查询条件
		if (bztime != null && bztime != "" && !bztime.equals("0")){
			whereStr.append(" AND Z.ZGMONTH ='" + bztime + "'");
		}
		
		AccountWithoutDao dao = new AccountWithoutDaoImp();
		List<AccountWithoutBean> list = dao.queryExport(whereStr.toString(), loginId);
		int num = list.size();
		String[] sum = dao.getSum(list);
		AccountWithoutBean sumbean = new AccountWithoutBean();
		
		sumbean.setZdcountsum(sum[0]);
		sumbean.setCountlsum(sum[1]);
		sumbean.setCountlzbsum(sum[2]);
		sumbean.setCountssum(sum[3]);
		sumbean.setCountszbsum(sum[4]);
		
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.setAttribute("sumbean", sumbean);
		
		String command = request.getParameter("command");
		if("chaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/accountwithout/accountwithout.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/accountwithout/export.jsp").forward(request, response);
		}
	}

}
