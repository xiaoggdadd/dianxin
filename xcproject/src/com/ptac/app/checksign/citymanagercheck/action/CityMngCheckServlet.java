package com.ptac.app.checksign.citymanagercheck.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.citymanagercheck.bean.CityMngCheckBean;
import com.ptac.app.checksign.citymanagercheck.dao.CityMngCheckDao;
import com.ptac.app.checksign.citymanagercheck.dao.CityMngCheckDaoImpl;

public class CityMngCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 5364627537549533598L;//serialVersionUID ����������Ĳ�ͬ�汾��ļ�����

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		//-----����command��ͨѡ��ͨ������------
		if("chaxun".equals(command) || "daochu".equals(command)){//��ѯ������
			queryexport(request,response);
		}else if("xiangqing".equals(command)){//����
			detail(request,response);
		}else if("checkcity".equals(command) || "checkcityno11".equals(command) 
				|| "checkcityno2".equals(command) || "checkcity1".equals(command) 
				|| "checkcityno1".equals(command) || "checkcityno".equals(command)){//���
			check(request,response);
		}	
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @see ��ѯ����
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����	
		String xiaoqu = request.getParameter("xiaoqu");//����	
		String zdname = request.getParameter("zdname");//վ������
		String property = request.getParameter("property");//վ������
		String bztime = request.getParameter("bztime");//�����·�
		String cityzrauditstatus = request.getParameter("cityzrauditstatus");//�м��������״̬
		String getlrbz = request.getParameter("getlrbz");//¼���־
		
		
		StringBuffer whereStr = new StringBuffer();//��ѯ����
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" and z.shi='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" and z.xian='" + xian + "'"); 
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr.append(" and z.xiaoqu='" + xiaoqu + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" and z.jzname  like '%" + zdname + "%'");
		}
		
		if (bztime != null && bztime != "" && !bztime.equals("0")){
			whereStr.append(" and to_char(e.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'");
		}
		if (cityzrauditstatus != null && !cityzrauditstatus.equals("") && !cityzrauditstatus.equals("-1")){
			whereStr.append(" and e.cityzrauditstatus='" + cityzrauditstatus + "'");	
		}
		
		if (property != null && !property.equals("") && !property.equals("0")){
			whereStr.append(" and z.PROPERTY='" + property + "'");
		}
		//2014-8-13 ���getlrbz==null������sql����ѣ�Ԥ���ѣ���ִ�У����getlrbz==1,��sql1ִ�У�sql2��ִ�У�and 1=2�������getlrbz==2����sql2ִ�У�sql1��ִ�У�and 1=2��
		String lrbz1="";
		String lrbz2="";
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){//ֻ�ӵ�ѱ��ѯ
				lrbz2="AND 1=2";
			}else if("2".equals(getlrbz)){//ֻ��Ԥ���ѱ��ѯ
				lrbz1="AND 1=2";
			}
		}
		
		//------��ѯ�����------
		CityMngCheckDao dao = new CityMngCheckDaoImpl();
		List<CityMngCheckBean> list = dao.queryCityMngCheck(whereStr.toString(), loginId,lrbz1,lrbz2);
		//------��ý�����ͽ������------
		int num = list.size();//�������
		request.setAttribute("num", num);//����ҳ��������
		request.setAttribute("list", list);//����ҳ������
		
		String command = request.getParameter("command");//��ȡǰ̨��ť��ʶ�ж��ύ����
		if("chaxun".equals(command)){//������ѯҳ�� 
			request.getRequestDispatcher("/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp").forward(request, response);
		}else if("daochu".equals(command)){//��������ҳ��
			request.getRequestDispatcher("/web/appJSP/checksign/citymanagercheck/�м�������˵���.jsp").forward(request, response);
		}
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @see ��ϸ��Ϣҳ��
	 * @throws ServletException
	 * @throws IOException
	 */
	public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//����
		String statusi = request.getParameter("statusi");
		String dbid = request.getParameter("dbid"+statusi);
		String dfzflx = request.getParameter("dfzflx"+statusi);
		String dfbzw = request.getParameter("dfbzw"+statusi);
		String accountmonth = request.getParameter("accountmonth"+statusi);
//		CityMngCheckDao dao = new CityMngCheckDaoImpl();
//		List<CityMngCheckBean> list = dao.getCityMngCheckInfo(dbid,dfzflx,dfbzw,accountmonth);
//		//------��ý�����ͽ������------
//		int num = list.size();//�������
//		request.setAttribute("num", num);//����ҳ��������
//		request.setAttribute("list", list);//����ҳ������
		
		request.setAttribute("dbid", dbid);
       	request.setAttribute("dfzflx", dfzflx);
       	request.setAttribute("dfbzw", dfbzw);
       	request.setAttribute("bzyf", accountmonth);
       	//��תҳ��
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp").forward(request, response);
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @see �м��������
	 * @throws ServletException
	 * @throws IOException
	 */
	public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		CityMngCheckDao dao = new CityMngCheckDaoImpl();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//�����Ա
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();//��־
	    String url = "",msg = "";//·������ʾ��Ϣ
		String command = request.getParameter("command");
		if("checkcity".equals(command)){//ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); 
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "1";//���ͨ��
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�м�������"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity1".equals(command)){
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "1";//���ͨ��
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�м�������"); 
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno11".equals(command)){//��˲�ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	  	  	String cityzrauditstatus = "2";//��˲�ͨ��
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�м�������");
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno2".equals(command)){//��ͨ������
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "2";//��˲�ͨ��
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�м�������"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno1".equals(command)){//ȡ��ͨ��
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	  	  	String cityzrauditstatus = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�м�������");
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno".equals(command)){//ȡ��ͨ��
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�м�������"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");

		}
	}

}
