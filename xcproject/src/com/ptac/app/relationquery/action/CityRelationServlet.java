package com.ptac.app.relationquery.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.relationquery.bean.RelationQueryBean;
import com.ptac.app.relationquery.dao.CityRelationDao;
import com.ptac.app.relationquery.dao.CityRelationDaoImp;

/**
 * @author lijing
 * @see ����վ������վ�����ʡ����ѯ
 */
public class CityRelationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String command = request.getParameter("command");
		String shi = request.getParameter("shi");//��
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND SHI='" + shi + "'";
		}
		
		CityRelationDao dao = new CityRelationDaoImp();
		List<RelationQueryBean> list = dao.getCityRelation(whereStr,loginId);
		int num = list.size();//�������

		request.setAttribute("num", num);//�������
		request.setAttribute("list", list);//�����
		
		if("chaxun".equals(command)){//������ѯҳ�� 
				request.getRequestDispatcher("/web/appJSP/relationquery/cityRelationQuery.jsp").forward(request, response);
		}else if("daochu".equals(command)){//��������ҳ��
				request.getRequestDispatcher("/web/appJSP/relationquery/����վ������վ��ʡ����ѯ����.jsp").forward(request, response);
		}
	}

}
