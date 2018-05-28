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

@SuppressWarnings("serial")
public class ProUnitpriceDaoChuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginID = request.getParameter("loginID");
		
		String shi = request.getParameter("shi");//��
		String bzyf1 = request.getParameter("bzyf1");//�����·ݣ�ǰ��
		String bzyf2 = request.getParameter("bzyf2");//�����·ݣ���
		String dfshzt = request.getParameter("dfshzt");//������״̬
		String zdqyzt = request.getParameter("zdqyzt");//վ������״̬
		String dbqyzt = request.getParameter("dbqyzt");//�������״̬
		ProSelectBean bean = new ProSelectBean(shi, bzyf1, bzyf2, dfshzt, zdqyzt, dbqyzt);
		
		
		ProUnitpriceMethodsDAO dao = new ProUnitpriceMethodsDAOImp();
		
		
		
		List<ProUnitpriceBean> ls = dao.findUnitprices(loginID, bean);//��ѯ����
		/*��ȡ�·ݼ���*/
		GetMonthes gm = new GetMonthes();
		List<String> Month_ls = gm.getMonthes(bzyf1, bzyf2);
		request.setAttribute("ls", ls);
		request.setAttribute("month", Month_ls);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/web/appJSP/statisticscollect/province/unitpricePIC/proUnitpriceOut.jsp").forward(request, response);
		
		
	}

}
