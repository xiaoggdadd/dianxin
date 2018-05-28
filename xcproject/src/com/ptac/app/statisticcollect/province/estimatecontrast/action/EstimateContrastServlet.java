package com.ptac.app.statisticcollect.province.estimatecontrast.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.estimatecontrast.bean.EstimateContrastBean;
import com.ptac.app.statisticcollect.province.estimatecontrast.dao.EstimateContrastDao;
import com.ptac.app.statisticcollect.province.estimatecontrast.dao.EstimateContrastDaoImpl;

/**ȷ�����ݹ����ݶ��ղ�ѯ
 * @author WangYiXiao 2014-4-29
 *
 */
public class EstimateContrastServlet extends HttpServlet {

	private static final long serialVersionUID = 1512259845623956352L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		//-----����command��ͨѡ��ͨ������------
		if("chaxun".equals(command) || "daochu".equals(command)){//��ѯ������
			queryexport(request,response);
		}
	}
	
	/**��ѯ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//------��ȡǰ̨������ʶ------
		String command = request.getParameter("command");
		String zangumonth = request.getParameter("zangumonth");
		
		EstimateContrastDao ecdao = new EstimateContrastDaoImpl();
		List<EstimateContrastBean> list = new ArrayList<EstimateContrastBean>();
		list = ecdao.query(zangumonth);
		int num = list.size();
		
		request.setAttribute("num", num);
		request.setAttribute("zangumonth", zangumonth);
		request.setAttribute("list", list);

		//------����ǰ̨��ť��ʶ�ж��ύ����
		if("chaxun".equals(command)){//������ѯҳ�� 
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/estimatecontrast/EstimateContrast.jsp").forward(request, response);
		}else if("daochu".equals(command)){//��������ҳ��
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/estimatecontrast/ȷ�����ݹ����ݶ��յ���.jsp").forward(request, response);
		}
		
	}

}
