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
import com.ptac.app.checksign.manElectricCheck.dao.CountryElecDao;
import com.ptac.app.checksign.manElectricCheck.dao.CountryElecImpl;

public class CountryElecServlet extends HttpServlet{
	private static final long serialVersionUID = -585716063983538984L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String command = request.getParameter("command");//获取页面操作
	    String url = "",msg = "";//路径，提示信息
		String path = request.getContextPath();//根路径
		CountryElecDao dao = new CountryElecImpl();
	    DbLog log = new DbLog();//日志
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal = account.getAccountName();//审核人员
		PrintWriter out = response.getWriter();
	
		//人工审核通过不通过取消通过方法
		if("checkcity".equals(command)){//通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	String manpassmemo = request.getParameter("manpassmemo"); //通过原因
	        url = path + "/web/check/checkFeesManual.jsp" ;
	      	String manualauditstatus = "1";//审核通过
	        msg = dao.CheckCityFees(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"1", manpassmemo);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "人工审核批量通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno2".equals(command)){//不通过部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	        url = path + "/web/check/checkFeesManual.jsp" ;
	      	String manualauditstatus = "-2";//审核不通过
	        msg = dao.CheckCityFees(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"-2", "");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "人工审核批量不通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno".equals(command)){//取消通过
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	        url = path + "/web/check/checkFeesManual.jsp" ;
	      	String manualauditstatus = "0";//取消通过
	        msg = dao.CheckCityFees(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"0", "");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "人工审核批量取消通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity1".equals(command)){//通过,ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	String manpassmemo = request.getParameter("manpassmemo"); //通过原因
	        url = path + "/web/check/checkFeesManual.jsp" ;
	      	String manualauditstatus = "1";//审核通过
	        msg = dao.CheckCityFees(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"1", manpassmemo);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "人工审核批量通过"); 
	        String m="";
	        if(msg=="批量操作失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno1".equals(command)){//取消通过，ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	        url = path + "/web/check/checkFeesManual.jsp" ;
	  	  	String manualauditstatus = "0";//取消通过
	        msg = dao.CheckCityFees(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"0", "");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "人工审核批量取消通过");
	        String m="";
	        if(msg=="批量操作失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno11".equals(command)){//不通过ajax部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//预付费uuid 
	        url = path + "/web/check/checkFeesManual.jsp" ;
	  	  	String manualauditstatus = "-2";//审核不通过
	        msg = dao.CheckCityFees(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"-2", "");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "人工审核批量不通过");
	        String m="";
	        if(msg=="批量操作失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}
		
		//济宁人工审核通过不通过取消通过方法
		if("checkcity6".equals(command)){//通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	String manpassmemo = request.getParameter("manpassmemo"); //通过原因
	        url = path + "/web/check/jncheckFeesManual.jsp" ;
	      	String manualauditstatus = "1";//审核通过
	        msg = dao.CheckCityFees1(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"1", manpassmemo,"");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁人工审核批量通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno26".equals(command)){//不通过部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	        url = path + "/web/check/jncheckFeesManual.jsp" ;
	      	String manualauditstatus = "-2";//审核不通过
	        msg = dao.CheckCityFees1(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"-2", "",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁人工审核批量不通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno6".equals(command)){//取消通过
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	        url = path + "/web/check/jncheckFeesManual.jsp" ;
	      	String manualauditstatus = "0";//取消通过
	        msg = dao.CheckCityFees1(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"0", "","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁人工审核批量取消通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity16".equals(command)){//通过,ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	String manpassmemo = request.getParameter("manpassmemo"); //通过原因
	        url = path + "/web/check/jncheckFeesManual.jsp" ;
	      	String manualauditstatus = "1";//审核通过
	        msg = dao.CheckCityFees1(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"1", manpassmemo,"");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁人工审核批量通过"); 
	        String m="";
	        if(msg=="批量操作失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno17".equals(command)){//取消通过，ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	        url = path + "/web/check/jncheckFeesManual.jsp" ;
	  	  	String manualauditstatus = "0";//取消通过
	        msg = dao.CheckCityFees1(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"0", "","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁人工审核批量取消通过");
	        String m="";
	        if(msg=="批量操作失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno16".equals(command)){//不通过ajax部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//预付费uuid 
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	        url = path + "/web/check/jncheckFeesManual.jsp" ;
	  	  	String manualauditstatus = "-2";//审核不通过
	        msg = dao.CheckCityFees1(personnal,manualauditstatus,chooseIdStr1,chooseIdStr2,"-2", "",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁人工审核批量不通过");
	        String m="";
	        if(msg=="批量操作失败！"){
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
