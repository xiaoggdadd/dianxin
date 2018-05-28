package com.ptac.app.statisticcollect.city.unitpricePIC.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.city.unitpricePIC.tool.PICUtil;


public class CityPICServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chartType=request.getParameter("chart");
		 String strXML=PICUtil.getChartMulCulomn();
		 request.getSession().setAttribute("w", "700");
		 request.getSession().setAttribute("h", "400");
		 String chart=request.getContextPath()+"/charts/"+chartType+".swf";
			request.getSession().setAttribute("strXML",strXML); //Êý¾Ý
			request.getSession().setAttribute("chart",chart);  //ÊµÀý
		request.getRequestDispatcher("").forward(request, response);
	}

}
