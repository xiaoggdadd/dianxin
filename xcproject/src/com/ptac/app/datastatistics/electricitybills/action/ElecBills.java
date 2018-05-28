package com.ptac.app.datastatistics.electricitybills.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.datastatistics.electricitybills.bean.ElecBillsBean;
import com.ptac.app.datastatistics.electricitybills.dao.ElecBillsDao;
import com.ptac.app.datastatistics.electricitybills.dao.ElecBillsDaoImp;

/**
 * @author �
 * @see ��ѽ�����ϸ����ѯ��������
 */
public class ElecBills extends HttpServlet {

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
	
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����	
		String xiaoqu = request.getParameter("xiaoqu");//����	
		String zdsx = request.getParameter("zdsx");//վ������
		String qyzt = request.getParameter("qyzt");//վ������״̬
		String dbqyzt = request.getParameter("dbqyzt");//�������״̬
		String beginTime = request.getParameter("beginTime");//��ʼ�����·�
		String endTime = request.getParameter("endTime");//���������·�
		String cwzt = request.getParameter("manauditstatus");//�������״̬,
		String sjzrsh = request.getParameter("sjzrsh");//�м��������״̬
		String gdfs = request.getParameter("gdfs");//���緽ʽ
		String stationtype = request.getParameter("zdlx");//վ������	
		
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
		}
		if(zdsx != null && !"".equals(zdsx) && !"0".equals(zdsx)){
			whereStr = whereStr+" AND ZD.PROPERTY='"+zdsx+"' ";
		}
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
			whereStr = whereStr + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
		}
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")) {
			whereStr = whereStr + " AND D.DBQYZT='" + dbqyzt + "'";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")) {
			whereStr = whereStr + " AND ZD.QYZT='" + qyzt + "'";
		}
		if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")) {
			whereStr = whereStr + " AND ZD.GDFS='" + gdfs + "'";
		}
		if (sjzrsh != null && !sjzrsh.equals("") && !sjzrsh.equals("-1")) {
			whereStr = whereStr + " AND E.CITYZRAUDITSTATUS='" + sjzrsh + "'";
		}
		if (beginTime != null && !beginTime.equals("") && !beginTime.equals("0")) {
			whereStr = whereStr + " AND to_char(E.ACCOUNTMONTH,'yyyy-mm')>='" + beginTime + "'";
		}
		if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
			whereStr = whereStr + " AND to_char(E.ACCOUNTMONTH,'yyyy-mm')<='" + endTime + "'";
		}
		if (cwzt != null && !cwzt.equals("") && !cwzt.equals("3")) {
			if ("1".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS>='" + cwzt + "'";
			}
			if ("2".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + cwzt + "'";
			}
			if ("0".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + cwzt + "'";
			}
			if ("-2".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + cwzt + "'";
			}
			if ("-1".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS<'2' ";
			}
		}
		
		List<ElecBillsBean> list = new ArrayList<ElecBillsBean>();	
		ElecBillsDao dao = new ElecBillsDaoImp();
		list = dao.export(whereStr,loginId);
		int num = list.size();
		
		ElecBillsBean total = new ElecBillsBean();
		Double totalMoney = 0.00;
		if(list != null){
			total = dao.query(list);
			totalMoney = total.getTotalmoney();
		}
		
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.setAttribute("total", totalMoney);
		
		String action = request.getParameter("command");//��ȡҪ���Ĳ���
		if ("chaxun".equals(action)) {// ������ϸ��ѯҳ��
			request.getRequestDispatcher("/web/appJSP/datastatistics/electricitybills/electricitybills.jsp")
					.forward(request, response);
		}else if ("daochu".equals(request.getParameter("command"))) {// ������ϸ����ҳ��
			request.getRequestDispatcher("/web/appJSP/datastatistics/electricitybills/��ѽ�����ϸ����.jsp")
					.forward(request, response);
		}
	}
}
