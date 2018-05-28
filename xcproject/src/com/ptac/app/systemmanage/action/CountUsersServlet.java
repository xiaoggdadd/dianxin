package com.ptac.app.systemmanage.action;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.noki.database.Test;
import com.noki.mobi.common.Account;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.systemmanage.bean.CountUsers;
import com.ptac.app.systemmanage.dao.CountUsersDao;
import com.ptac.app.systemmanage.dao.CountUsersDaoImpl;

public class CountUsersServlet extends HttpServlet {
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

		String shi = request.getParameter("shi");
	
		
		String whereStr = "";
		if(shi != null && !"".equals(shi)&& !" ".equals(shi) && !"0".equals(shi)){
			whereStr = whereStr + " and a.shi= '"+ shi +"'";
		}
		
		if(!"263".equals(loginId))
			whereStr = whereStr + " and (a.shi in(select t.agcode from per_area t where t.accountid = '" + loginId + "')) ";
		
		
		CountUsersDao dao = new CountUsersDaoImpl();
		List<CountUsers> list = new ArrayList<CountUsers>();	
		list = dao.queryCountUsers(whereStr, loginId);
		
		
		int num = list.size();
		
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/web/sys/countUsers.jsp")
			.forward(request, response);
		
	}
}
