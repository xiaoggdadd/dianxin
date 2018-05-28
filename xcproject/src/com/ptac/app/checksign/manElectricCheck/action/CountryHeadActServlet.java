package com.ptac.app.checksign.manElectricCheck.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.manElectricCheck.dao.CountryHeadActDao;
import com.ptac.app.checksign.manElectricCheck.dao.CountryHeadActImpl;

public class CountryHeadActServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2221946403510752206L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		if("checkcity".equals(command) || "checkcityno11".equals(command) 
				|| "checkcityno2".equals(command) || "checkcity1".equals(command) 
				|| "checkcityno1".equals(command) || "checkcityno".equals(command)){//���
			
			check(request,response);
		}else if("checkcity6".equals(command) || "checkcityno16".equals(command) 
				|| "checkcityno26".equals(command) || "checkcity16".equals(command) 
				|| "checkcityno17".equals(command) || "checkcityno6".equals(command)){//�������
			
			check1(request,response);
		}
	}

	public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String command = request.getParameter("command");//��ȡҳ�����
	    String url = "",msg = "";//·������ʾ��Ϣ
		String path = request.getContextPath();//��·��
		CountryHeadActDao dao = new CountryHeadActImpl();
	    DbLog log = new DbLog();//��־
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal = account.getAccountName();//�����Ա
		PrintWriter out = response.getWriter();

		if("checkcity".equals(command)){//ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp" ;
	      	String countryheadstatus = "1";//���ͨ��
	        msg = dao.CheckCityFees(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "������������ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno2".equals(command)){//��ͨ������
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp" ;
	      	String countryheadstatus = "2";//��˲�ͨ��
	        msg = dao.CheckCityFees(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "��������������ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno".equals(command)){//ȡ��ͨ��
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp" ;
	      	String countryheadstatus = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "������������ȡ��ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity1".equals(command)){//ͨ��,ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp" ;
	      	String countryheadstatus = "1";//���ͨ��
	        msg = dao.CheckCityFees(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "������������ͨ��"); 
	        String m="";
	        if(msg=="��������ʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno1".equals(command)){//ȡ��ͨ����ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp" ;
	  	  	String countryheadstatus = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "������������ȡ��ͨ��");
	        String m="";
	        if(msg=="��������ʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno11".equals(command)){//��ͨ��ajax����
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//Ԥ����uuid 
	        url = path + "/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp" ;
	  	  	String countryheadstatus = "2";//��˲�ͨ��
	        msg = dao.CheckCityFees(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "��������������ͨ��");
	        String m="";
	        if(msg=="��������ʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}
	}
	
	/**
	 * @author lijing
	 * @see ���������������ͨ������ͨ����ȡ��ͨ������
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void check1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String command = request.getParameter("command");//��ȡҳ�����
	    String url = "",msg = "";//·������ʾ��Ϣ
		String path = request.getContextPath();//��·��
		CountryHeadActDao dao = new CountryHeadActImpl();
	    DbLog log = new DbLog();//��־
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal = account.getAccountName();//�����Ա
		PrintWriter out = response.getWriter();

		if("checkcity6".equals(command)){//ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp" ;
	      	String countryheadstatus = "1";//���ͨ��
	        msg = dao.CheckCityFees1(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"1","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "����������������ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno26".equals(command)){//��ͨ������
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	    	url = path + "/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp" ;
	      	String countryheadstatus = "2";//��˲�ͨ��
	        msg = dao.CheckCityFees1(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"2",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "������������������ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno6".equals(command)){//ȡ��ͨ��
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	url = path + "/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp" ;
	      	String countryheadstatus = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees1(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"0","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "����������������ȡ��ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity16".equals(command)){//ͨ��,ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	url = path + "/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp" ;
	      	String countryheadstatus = "1";//���ͨ��
	        msg = dao.CheckCityFees1(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"1","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "����������������ͨ��"); 
	        String m="";
	        if(msg=="��������ʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno17".equals(command)){//ȡ��ͨ����ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	url = path + "/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp" ;
	  	  	String countryheadstatus = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees1(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"0","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "����������������ȡ��ͨ��");
	        String m="";
	        if(msg=="��������ʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno16".equals(command)){//��ͨ��ajax����
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//Ԥ����uuid 
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	    	url = path + "/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp" ;
	  	  	String countryheadstatus = "2";//��˲�ͨ��
	        msg = dao.CheckCityFees1(personnal,countryheadstatus,chooseIdStr1,chooseIdStr2,"2",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "������������������ͨ��");
	        String m="";
	        if(msg=="��������ʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}
	}
	
}
