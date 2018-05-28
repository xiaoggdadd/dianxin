package com.ptac.app.statisticcollect.city.gdfselecbulletin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.city.gdfselecbulletin.bean.GdfsElecBulletinBean;
import com.ptac.app.statisticcollect.city.gdfselecbulletin.dao.GdfsElecBulletinDao;
import com.ptac.app.statisticcollect.city.gdfselecbulletin.dao.GdfsElecBulletinDaoImpl;

public class GdfsElecBulletinServlet extends HttpServlet {

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

		String action = request.getParameter("command");
		String accountmonth = request.getParameter("beginbztime");//报账月份
		String property = request.getParameter("property")==null?"0":request.getParameter("property");//站点属性
		String status = request.getParameter("status");//审核状态
		String shicode = request.getParameter("shi");//市
		String loginid = request.getParameter("loginid");//loginid
		
		String[] last1 = accountmonth.split("-");
		String nian1 = last1[0];
		Integer yue1 = Integer.valueOf(last1[1]);
		GdfsElecBulletinDao dao = new GdfsElecBulletinDaoImpl();
		List<GdfsElecBulletinBean> list = new ArrayList<GdfsElecBulletinBean>();
		list = dao.queryDetails(accountmonth, property, status, shicode, loginid);
		int num = list.size();
		String[] total = dao.total(list);

		request.setAttribute("list", list);
		request.setAttribute("num", num);
		request.setAttribute("nian1", nian1);
		request.setAttribute("yue1", yue1);
		request.setAttribute("total1", total[0]);
		request.setAttribute("total2", total[1]);
		request.setAttribute("total3", total[2]+"%");
		request.setAttribute("total4", total[3]);
		request.setAttribute("total5", total[4]+"%");
		if("chaxun".equals(action)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/gdfselecbulletin/gdfselecbulletin.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/gdfselecbulletin/gdfselecbulletinExport.jsp").forward(request, response);
		}
	}

}
