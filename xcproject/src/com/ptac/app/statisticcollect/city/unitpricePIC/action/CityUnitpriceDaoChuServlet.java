package com.ptac.app.statisticcollect.city.unitpricePIC.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceBean;
import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceSelectBean;
import com.ptac.app.statisticcollect.city.unitpricePIC.dao.CityUnitpriceMethodsDAO;
import com.ptac.app.statisticcollect.city.unitpricePIC.dao.imp.CityUnitpriceMethodsDAOImp;
import com.ptac.app.statisticcollect.province.unitpricePIC.util.GetMonthes;

@SuppressWarnings("serial")
public class CityUnitpriceDaoChuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginID = request.getParameter("loginID");
		
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//县
		String bzyf1 = request.getParameter("bzyf1");//报账月份（前）
		String bzyf2 = request.getParameter("bzyf2");//报账月份（后）
		String dfshzt = request.getParameter("dfshzt");//电费审核状态
		String zdqyzt = request.getParameter("zdqyzt");//站点启用状态
		String dbqyzt = request.getParameter("dbqyzt");//电表启用状态
		
		CityUnitpriceSelectBean bean = new CityUnitpriceSelectBean(shi,xian, bzyf1, bzyf2, dfshzt, zdqyzt, dbqyzt);
		
		CityUnitpriceMethodsDAO dao = new CityUnitpriceMethodsDAOImp();
		
		
		
		List<CityUnitpriceBean> ls = dao.findUnitprices(loginID, bean);//查询数据
		/*获取月份集合*/
		GetMonthes gm = new GetMonthes();
		List<String> Month_ls = gm.getMonthes(bzyf1, bzyf2);
		request.setAttribute("ls", ls);
		request.setAttribute("month", Month_ls);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/unitpricePIC/CityUnitpriceOut.jsp").forward(request, response);
												  
	}

}
