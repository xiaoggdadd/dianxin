package com.ptac.app.collectanalysis.onesitedbshi.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShi;
import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShiDetails;
import com.ptac.app.collectanalysis.onesitedbshi.dao.OneSiteDbShiDao;
import com.ptac.app.collectanalysis.onesitedbshi.dao.OneSiteDbShiDaoImpl;
import com.ptac.app.collectanalysis.onesitedbshi.util.UtilIneSiteDbShi;

public class OneSiteDbShiServlet extends HttpServlet {


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
		String command = request.getParameter("command");//����
		
		if("chaxun".equals(command) || "daochu".equals(command)){
			doChaXunDaoChu(request, response);
		}else if("xiangqing1".equals(command)){
			xiangxi1(request, response);
		}
	}
	public void doChaXunDaoChu(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//����
		String shi = request.getParameter("shi");
		String zdqyzt = request.getParameter("state");
		String dbqyzt = request.getParameter("ammerterStyleId");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		OneSiteDbShiDao dao = new OneSiteDbShiDaoImpl();
		List<OneSiteDbShi> list = new ArrayList<OneSiteDbShi>();
		list = dao.getOneSiteDb(shi, zdqyzt, dbqyzt, loginId);
		long[] lon = new UtilIneSiteDbShi().getSum(list);
		
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
			request.getRequestDispatcher("/web/appJSP/collectanalysis/onesitedb/osmmshi.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/collectanalysis/onesitedb/convertshi.jsp").forward(request, response);
		}
	}
	public void xiangxi1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String fwzbzw = request.getParameter("fwzbzw");//fwzbzw 1:һվ���  2:���������һվ���
		String xian = request.getParameter("xian");//��
		String zdqyzt = request.getParameter("zdqyzt");//վ������״̬
		String dbqyzt = request.getParameter("dbqyzt");//�������״̬
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		OneSiteDbShiDao dao = new OneSiteDbShiDaoImpl();
		List<OneSiteDbShiDetails> list = new ArrayList<OneSiteDbShiDetails>();
		list = dao.getOneSiteDbDetails(xian,fwzbzw,zdqyzt,dbqyzt,loginId);
		request.setAttribute("emcsList", list);
		request.getRequestDispatcher("/web/appJSP/collectanalysis/onesitedb/details.jsp").forward(request, response);
	}

}
