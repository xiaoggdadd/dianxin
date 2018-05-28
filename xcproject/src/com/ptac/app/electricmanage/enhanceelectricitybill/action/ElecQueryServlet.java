package com.ptac.app.electricmanage.enhanceelectricitybill.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;

/**
 * @author WangYiXiao 2014-4-15
 * @see ��ǿ���ѵ���ѯ����
 */
public class ElecQueryServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4203741684001355690L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/** 
	 * @author WangYiXiao 2014-4-15
	 * @see ǰ̨ҳ����Ҫ�Ĺ�������,�Լ���ѯ������list�����,���ص�ҳ����
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";// ����
		String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "";// ����
		String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "";// ����
		String jzname = request.getParameter("zdbh") != null ? request.getParameter("zdbh") : "";// վ������
		String stationtype = request.getParameter("stationtype") != null ? request.getParameter("stationtype"): "";// վ������
		String lastdatetime = request.getParameter("beginTimeQ") != null ? request.getParameter("beginTimeQ"): "";// �ϴγ���ʱ��
		String thisdatetime = request.getParameter("endTimeQ") != null ? request.getParameter("endTimeQ"): "";// ���γ���ʱ��
		String startmonth = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";// ��ʼ�·�
		String endmonth = request.getParameter("endTime1") != null ? request.getParameter("endTime1") : "";// �����·�
		String accountmonth = request.getParameter("accountmonth") != null ? request.getParameter("accountmonth"): "";// �����·�
		
		String whereStr = "";
		String str = "";

		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " and zd.shi='" + shi + "'";
			str = str + " and zd.shi='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " and zd.xian='" + xian + "'";
			str = str + " and zd.xian='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " and zd.xiaoqu='" + xiaoqu + "'";
			str = str + " and zd.xiaoqu='" + xiaoqu + "'";
		}
		if (lastdatetime != null && lastdatetime != ""
				&& !lastdatetime.equals("0")) {
			whereStr = whereStr + " and to_char(ad.lastdatetime,'yyyy-mm-dd')='" + lastdatetime + "'";
			str = str + " and to_char(ad.lastdatetime,'yyyy-mm-dd')='" + lastdatetime + "'";
		}
		if (thisdatetime != null && thisdatetime != ""
				&& !thisdatetime.equals("0")) {
			whereStr = whereStr + " and to_char(ad.thisdatetime,'yyyy-mm-dd')='" + thisdatetime + "'";
			str = str + " and to_char(ad.thisdatetime,'yyyy-mm-dd')='" + thisdatetime + "'";
		}
		if (jzname != null && jzname != "" && !jzname.equals("0")) {
			whereStr = whereStr + " and jzname like '%" + jzname + "%'";
		}
		if (startmonth != null && startmonth != "" && !startmonth.equals("0")) {
			whereStr = whereStr + " and to_char(ad.STARTMONTH,'yyyy-mm')='" + startmonth + "'";
		}
		if (endmonth != null && endmonth != "" && !endmonth.equals("0")) {
			whereStr = whereStr + " and to_char(ad.ENDMONTH,'yyyy-mm')='" + endmonth + "'";
		}
		if (stationtype != null && !stationtype.equals("")
				&& !stationtype.equals("0")) {
			whereStr = whereStr + " and zd.STATIONTYPE='" + stationtype + "'";
			str = str + " and zd.STATIONTYPE='" + stationtype + "'";
		}
		if (accountmonth != null && !accountmonth.equals("")
				&& !accountmonth.equals("0")) {
			whereStr = whereStr + " and to_char(ef.accountmonth,'yyyy-mm')='" + accountmonth + "'";
			str = str + " and to_char(ef.accountmonth,'yyyy-mm')='" + accountmonth + "'";
		}
		request.setAttribute("whereStr", whereStr);
		request.setAttribute("str", str);

		ElecBillDao dao = new ElecBillDaoImp();
		ArrayList list = new ArrayList();
		list = dao.queryElectric(whereStr,loginId);//�����
		int num = list.size();//�������
		request.setAttribute("num", num);//�������
		request.setAttribute("list", list);//�����
		
		String command = request.getParameter("command");
		if ("chaxun".equals(command)) {// ������ѵ���ѯҳ��
			request.getRequestDispatcher("/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBill.jsp")
					.forward(request, response);
		} else if ("daochu".equals(request.getParameter("command"))) {// ������ѵ�����ҳ��
			request.getRequestDispatcher("/web/appJSP/electricmanage/enhanceelectricitybill/��ѵ�����.jsp")
					.forward(request, response);
		}

	}

}
