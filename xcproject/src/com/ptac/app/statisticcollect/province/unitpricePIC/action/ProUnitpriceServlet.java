package com.ptac.app.statisticcollect.province.unitpricePIC.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProSelectBean;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProUnitpriceBean;
import com.ptac.app.statisticcollect.province.unitpricePIC.dao.ProUnitpriceMethodsDAO;
import com.ptac.app.statisticcollect.province.unitpricePIC.dao.imp.ProUnitpriceMethodsDAOImp;
import com.ptac.app.statisticcollect.province.unitpricePIC.util.GetMonthes;
/**
 * 进行查询的Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class ProUnitpriceServlet extends HttpServlet {

	/**
	 * 调用用post方法来处理数据结果集和图表
	 * @author rock
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	/**
	 * 进行查询的方法
	 * @author rock
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginID = request.getParameter("loginID");
		
		String shi = request.getParameter("shi");//市
		String bzyf1 = request.getParameter("bzyf1");//报账月份（前）
		String bzyf2 = request.getParameter("bzyf2");//报账月份（后）
		String dfshzt = request.getParameter("dfshzt");//电费审核状态
		String zdqyzt = request.getParameter("zdqyzt");//站点启用状态
		String dbqyzt = request.getParameter("dbqyzt");//电表启用状态
		ProSelectBean bean = new ProSelectBean(shi, bzyf1, bzyf2, dfshzt, zdqyzt, dbqyzt);
		
		
		ProUnitpriceMethodsDAO dao = new ProUnitpriceMethodsDAOImp();
		
		
		
		List<ProUnitpriceBean> ls = dao.findUnitprices(loginID, bean);//查询数据
		/*获取月份集合*/
		GetMonthes gm = new GetMonthes();
		List<String> Month_ls = gm.getMonthes(bzyf1, bzyf2);
		request.setAttribute("ls", ls);
		request.setAttribute("month", Month_ls);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/web/appJSP/statisticscollect/province/unitpricePIC/proviceUnitprice.jsp").forward(request, response);

		
	}

}
