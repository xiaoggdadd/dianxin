package com.ptac.app.statisticcollect.city.unitAveShi.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.city.unitAveShi.bean.UnitAveShiBean;
import com.ptac.app.statisticcollect.city.unitAveShi.dao.UnitAveShiDao;
import com.ptac.app.statisticcollect.city.unitAveShi.dao.UnitAveShiDaoImp;

/**
 * @see �м�����ƽ����ƽ������.Servlet
 * @author ZengJin 2014-3-30 
 */
public class UnitAveShiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ------��ȡ��¼�˻���Ϣ------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		// ------��ȡǰ̨��ť��ʶ------
		String command = request.getParameter("command");
		// ------��ȡǰ̨��������------
		String shi = request.getParameter("shi");// ��
		String xian = request.getParameter("xian");// ����
		String gdfs = request.getParameter("gdfs");// ���緽ʽ
		String zdsx = request.getParameter("jzproperty");// վ������
		String bzyfstart = request.getParameter("bzyfstart");// �����·���
		String bzyfend = request.getParameter("bzyfend");// �����·�ֹ

		String autoauditstatus = request.getParameter("autoauditstatus");// �Զ����״̬
		String manualauditstatus = request.getParameter("manauditstatus");// �˹����״̬
		String countryheadstatus = request.getParameter("countryheadstatus");// ������˱�־
		String cityaudit = request.getParameter("citystatus");// �м����״̬
		String cityzrauditstatus = request.getParameter("cityzrauditstatus");// �м��������״̬

		// ------���ݻ�ȡ��ֵƴ��sql��ѯ���-----
		String whereStr = "";
		
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
		}

		if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")) {
			whereStr = whereStr + " AND ZD.GDFS='" + gdfs + "'";
		}

		if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
			whereStr = whereStr + " AND ZD.PROPERTY='" + zdsx + "'";
		}

		if (autoauditstatus != null && !autoauditstatus.equals("") && !autoauditstatus.equals("5")) {
			whereStr = whereStr + " AND E.AUTOAUDITSTATUS='" + autoauditstatus + "'";
		}

		if (manualauditstatus != null && !manualauditstatus.equals("") && !manualauditstatus.equals("5")) {
			if ("1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS>='" + manualauditstatus + "'";
			}
			if ("2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + manualauditstatus + "'";
			}
			if ("0".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + manualauditstatus + "'";
			}
			if ("-2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + manualauditstatus + "'";
			}
			if ("-1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS<'2'";
			}
		}
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("5")) {
			whereStr = whereStr + " AND E.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}

		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("5")) {
			whereStr = whereStr + " AND E.CITYAUDIT='" + cityaudit + "'";
		}
		if (cityzrauditstatus != null && !cityzrauditstatus.equals("") && !cityzrauditstatus.equals("5")) {
			whereStr = whereStr + " AND E.CITYZRAUDITSTATUS='" + cityzrauditstatus + "'";
		}

		if (bzyfstart != null && !bzyfstart.equals("") && !bzyfstart.equals("0") 
		 && bzyfend != null && !bzyfend.equals("") && !bzyfend.equals("0")
		 && shi != null && !shi.equals("") && !shi.equals("0")) {

			// ------��ý�����ͽ������------
			UnitAveShiDao dao = new UnitAveShiDaoImp();

			List<UnitAveShiBean> list = new ArrayList<UnitAveShiBean>();

			list = dao.queryElectric(whereStr, loginId, shi, bzyfstart, bzyfend);//�����
			int num = list.size();//�������

			// ------��ǰ̨ҳ�洫ֵ------

			request.setAttribute("num", num);// �������
			request.setAttribute("list", list);// �����
			// ------����ǰ̨��ť��ʶ�ж��ύ����
			if ("chaxun".equals(command)) {// ������ѯҳ��
				request.getRequestDispatcher(
						"/web/appJSP/statisticscollect/city/unitAveShi/unitAveShiQuery.jsp")
						.forward(request, response);
			} else if ("daochu".equals(command)) {// ��������ҳ��
				request.getRequestDispatcher(
						"/web/appJSP/statisticscollect/city/unitAveShi/unitAveShiExport.jsp")
						.forward(request, response);
			}
		}
	}
}
