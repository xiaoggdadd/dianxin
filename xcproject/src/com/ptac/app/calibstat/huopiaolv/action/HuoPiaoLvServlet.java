package com.ptac.app.calibstat.huopiaolv.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.huopiaolv.bean.HuoPiaoLv;
import com.ptac.app.calibstat.huopiaolv.dao.HuoPiaoLvDao;
import com.ptac.app.calibstat.huopiaolv.dao.HuoPiaoLvDaoImpl;

public class HuoPiaoLvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if(null==account){
			response.sendRedirect(request.getContextPath() + "/web/index.jsp");
		}else{
			String loginId = account.getAccountId();
			String sheng = account.getSheng();
			//------��ȡǰ̨������ʶ------
			String command = request.getParameter("command");
			//------��ȡǰ̨��������------
			String shi = request.getParameter("shi");//��
			String begintime = request.getParameter("begintime");
			String endtime = request.getParameter("endtime");
			HuoPiaoLvDao dao = new HuoPiaoLvDaoImpl();
			//------��ѯ�����------
			List<HuoPiaoLv> list = dao.getHuoPiaoLv(sheng, shi, begintime, endtime, loginId);
			//------��ý�����ͽ������------
			int num = list.size();//�������
			BigDecimal sumtax = null;
			BigDecimal sumelecfees = null;
			BigDecimal sumvat = null;
			if(0 != list.size()){
				BigDecimal[] total = dao.total(list);
				sumtax = total[0];
				sumelecfees = total[1];
				sumvat = total[2]; 
			}
			//------��ǰ̨ҳ�洫ֵ------
			request.setAttribute("sumtax", sumtax);
			request.setAttribute("sumelecfees", sumelecfees);
			request.setAttribute("sumvat", sumvat);
			request.setAttribute("num", num);//�������
			request.setAttribute("list", list);//�����
			//------����ǰ̨��ť��ʶ�ж��ύ����
			if("chaxun".equals(command)){//������ѯҳ�� 
				request.getRequestDispatcher("/web/appJSP/calibstat/huopiaolv/huoPiaoLv.jsp").forward(request, response);
			}else if("daochu".equals(command)){//��������ҳ��
				request.getRequestDispatcher("/web/appJSP/calibstat/huopiaolv/��Ʊ��ͳ�Ƶ���.jsp").forward(request, response);
			}
		}
	}

}
