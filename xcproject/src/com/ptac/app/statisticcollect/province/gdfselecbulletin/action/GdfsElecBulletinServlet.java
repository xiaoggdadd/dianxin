package com.ptac.app.statisticcollect.province.gdfselecbulletin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.gdfselecbulletin.bean.GdfsElecBulletinBean;
import com.ptac.app.statisticcollect.province.gdfselecbulletin.dao.GdfsElecBulletinDao;
import com.ptac.app.statisticcollect.province.gdfselecbulletin.dao.GdfsElecBulletinDaoImpl;

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
		String accountmonth = request.getParameter("beginbztime");//�����·�
		String property = request.getParameter("property")==null?"0":request.getParameter("property");//վ������
		String status = request.getParameter("status");//���״̬
		
		String[] last1 = accountmonth.split("-");
		String nian1 = last1[0];
		Integer yue1 = Integer.valueOf(last1[1]);
		GdfsElecBulletinDao dao = new GdfsElecBulletinDaoImpl();
		List<GdfsElecBulletinBean> list = new ArrayList<GdfsElecBulletinBean>();
		list = dao.queryDetails(accountmonth, property, status);
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
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/gdfselecbulletin/gdfselecbulletin.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/gdfselecbulletin/gdfselecbulletinExport.jsp").forward(request, response);
		}
	}

}
