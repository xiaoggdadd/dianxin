package com.ptac.app.calibstat.calendarmonthelec.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.calendarmonthelec.bean.ProMonthElectriBean;
import com.ptac.app.calibstat.calendarmonthelec.dao.ProMonthElectriDao;
import com.ptac.app.calibstat.calendarmonthelec.dao.ProMonthElectriDaoImpl;
import com.ptac.app.calibstat.dao.CalibStatDao;
import com.ptac.app.calibstat.dao.CalibStatDaoImp;
/**
 * @author zhouxue
 * @see  ʡ����Ȼ���õ�ͳ�� ��ѯ
 * 
 * 
 * */

public class ProMonthElectriServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        doPost(request,response);
     }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
     String dfzflx = request.getParameter("dfzflx") != null ? request.getParameter("dfzflx") : "";// ����
     String zdqyzt = request.getParameter("zdqyzt") != null ? request.getParameter("zdqyzt") : "";// ����
     String zdsx = request.getParameter("jzproperty") != null ? request.getParameter("jzproperty") : "";//վ������
     String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "";//վ������
     String beginTime = request.getParameter("beginTime") != null ? request.getParameter("beginTime"): "";//���
     String endTime = request.getParameter("endTime") != null ? request.getParameter("endTime"): "";//���
     String whereStr = "";
   if (dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")) {
	whereStr = whereStr + " and D.dfzflx='" + dfzflx + "'";
   }
   if (zdqyzt != null && !zdqyzt.equals("") && !zdqyzt.equals("-1")) {
	whereStr = whereStr + " and Z.qyzt='" + zdqyzt + "'";
   }
   if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
 	whereStr = whereStr + " and Z.PROPERTY='" + zdsx + "'";
   }
   if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
	whereStr = whereStr + " AND Z.STATIONTYPE IN('" + zdlx + "')";
   }
 
	//ת�����ڸ�ʽ  ����ʼ�·ݡ������·�string�����ֶ�ת�������ڸ�ʽ����
	  DateFormat fmt =new SimpleDateFormat("yyyy-MM");
	   Date begindate = null;
	   Date enddate = null;
	   try {
		begindate = fmt.parse(beginTime);
		enddate = fmt.parse(endTime);
	    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	 //�������ڼ����  ����ѭ��    �������·�- ��ʼ�·ݣ�
	    Calendar startCalendar = Calendar.getInstance();  
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(begindate);  
        endCalendar.setTime(enddate);
        int yearNumber = endCalendar.get(endCalendar.YEAR) - startCalendar.get(endCalendar.YEAR);
        int monthNum = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
	    int l =  yearNumber*12 + monthNum+1;
	    
   List<Object> list = new ArrayList<Object>();
   ProMonthElectriDao dao = new ProMonthElectriDaoImpl();
   list = dao.proMonthSele(whereStr,begindate,enddate,beginTime,endTime,l);
   int num = list.size();
   request.setAttribute("beginTimedc", beginTime);//��ʼ�·ݴ�������ҳ��ʹ��
   request.setAttribute("yfcd", l);
   request.setAttribute("num", num);
   request.setAttribute("list", list);
   String action = request.getParameter("command");//��ȡҪ���Ĳ���
   
   if ("chaxun".equals(action)) {// ������ϸ��ѯҳ��
	request.getRequestDispatcher("/web/appJSP/calibstat/calendarmonthelec/provincemonthelec.jsp")
			.forward(request, response);
   }else if ("daochu".equals(request.getParameter("command"))) {// ������ϸ����ҳ��
	request.getRequestDispatcher("/web/appJSP/calibstat/calendarmonthelec/ʡ����Ȼ���õ�ͳ�Ƶ���.jsp")
			.forward(request, response);
   }
 }

}
