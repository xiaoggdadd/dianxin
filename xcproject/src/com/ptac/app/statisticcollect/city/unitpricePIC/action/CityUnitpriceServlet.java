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
/**
 * ���в�ѯ��Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class CityUnitpriceServlet extends HttpServlet {

	/**
	 * ������post�������������ݽ������ͼ��
	 * @author rock
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	/**
	 * ���в�ѯ�ķ���
	 * @author rock
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginID = request.getParameter("loginID");
		
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//��
		String bzyf1 = request.getParameter("bzyf1");//�����·ݣ�ǰ��
		String bzyf2 = request.getParameter("bzyf2");//�����·ݣ���
		String dfshzt = request.getParameter("dfshzt");//������״̬
		String zdqyzt = request.getParameter("zdqyzt");//վ������״̬
		String dbqyzt = request.getParameter("dbqyzt");//�������״̬
		
		CityUnitpriceSelectBean bean = new CityUnitpriceSelectBean(shi,xian, bzyf1, bzyf2, dfshzt, zdqyzt, dbqyzt);
		
		CityUnitpriceMethodsDAO dao = new CityUnitpriceMethodsDAOImp();
		
		
		
		List<CityUnitpriceBean> ls = dao.findUnitprices(loginID, bean);//��ѯ����
		/*��ȡ�·ݼ���*/
		GetMonthes gm = new GetMonthes();
		List<String> Month_ls = gm.getMonthes(bzyf1, bzyf2);
		request.setAttribute("ls", ls);
		request.setAttribute("month", Month_ls);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/unitpricePIC/cityUnitprice.jsp").forward(request, response);

		
		
	}

}
