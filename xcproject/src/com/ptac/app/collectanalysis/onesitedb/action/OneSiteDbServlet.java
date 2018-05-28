package com.ptac.app.collectanalysis.onesitedb.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.collectanalysis.onesitedb.bean.OneSiteDb;
import com.ptac.app.collectanalysis.onesitedb.dao.OneSiteDbDao;
import com.ptac.app.collectanalysis.onesitedb.dao.OneSiteDbDaoImpl;
import com.ptac.app.collectanalysis.onesitedb.util.UtilIneSiteDb;

public class OneSiteDbServlet extends HttpServlet {


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
		String command = request.getParameter("command");//²Ù×÷
		String shi = request.getParameter("shi");
		String zdqyzt = request.getParameter("state");
		String dbqyzt = request.getParameter("ammerterStyleId");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		OneSiteDbDao dao = new OneSiteDbDaoImpl();
		List<OneSiteDb> list = new ArrayList<OneSiteDb>();
		list = dao.getOneSiteDb(shi, zdqyzt, dbqyzt, loginId);
		long[] lon = new UtilIneSiteDb().getSum(list);
		
		int colornum = list.size();
		request.setAttribute("emcsList", list);
		request.setAttribute("shi", shi);
		request.setAttribute("state", zdqyzt);
		request.setAttribute("colornum", colornum);
		request.setAttribute("ammerterStyleId", dbqyzt);
		request.setAttribute("s", lon[0]);
		request.setAttribute("s1", lon[1]);
		request.setAttribute("s2", lon[2]);
		request.setAttribute("s3", lon[3]);
		request.setAttribute("s4", lon[4]);
		if("chaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/collectanalysis/onesitedb/osmm.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/collectanalysis/onesitedb/convert.jsp").forward(request, response);
		}
	}

}
