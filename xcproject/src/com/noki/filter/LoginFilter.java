package com.noki.filter;



import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;

public class LoginFilter
    implements Filter {
  protected String encoding = null;
  protected FilterConfig filterConfig = null;

  public void destroy() {
    this.encoding = null;
    this.filterConfig = null;

  }

  private String path = "";

  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		StringBuffer ul = httpRequest.getRequestURL();//用来获取url的值

		String referer = httpRequest.getHeader("Referer");


		//系统登录时，为了系统安全，把原session清空，重新建立一个新的系统安全
		if (ul.toString().contains("servlet/login")) {
			String sessionId=httpRequest.getSession().getId();
			System.out.println("sessionId>>>>>>>>"+sessionId);
			HttpSession session = httpRequest.getSession(false);
			if (session != null && !session.isNew()) {// isNew() Returns true if
														// the client does not
														// yet know about the
														// session or if the
														// client chooses not to
														// join the session.
				HttpSession sessionOld = session;
				 Account account = (Account)session.getAttribute("account");
				 String sCode = String.valueOf(session.getAttribute("rCode"));
				 
					String loginName = (String)session.getAttribute("loginName");
					String accountSheng = (String)session.getAttribute("accountSheng");
					String accountShi = (String)session.getAttribute("accountShi");
					String accountxian = (String)session.getAttribute("accountxian");
					String accountRole = (String)session.getAttribute("accountRole");
					String accountid = (String)session.getAttribute("accountid");
					String accountCthrnumber = (String)session.getAttribute("accountCthrnumber");
					String title = (String)session.getAttribute("title");
					String url_str = (String)session.getAttribute("url");
				 
				sessionOld.invalidate();
				session = httpRequest.getSession(true);
				session.setAttribute("account", account);
				session.setAttribute("rCode", sCode);
				
				session.setAttribute("loginName", loginName);
				session.setAttribute("accountSheng", accountSheng);
				session.setAttribute("accountShi", accountShi);
				session.setAttribute("accountxian", accountxian);
				session.setAttribute("accountRole", accountRole);
				session.setAttribute("accountid", accountid);
				session.setAttribute("accountCthrnumber",accountCthrnumber);
				session.setAttribute("title", title);
				session.setAttribute("url",url_str);
				
				
				String sessionId2=httpRequest.getSession().getId();
				System.out.println("sessionId2>>>>>>>>"+sessionId2);

			}
		}
		String pat11 = "";
		 try {
			 pat11=( (HttpServletRequest) request).getContextPath();
		    }
		    catch (Exception e) {
		      System.out.println(e);
		    }
		if(referer==null){
			if(ul.toString().contains(pat11)){
				chain.doFilter(request, response);
			}
		}else{
			if((referer.trim().contains(pat11))){
				chain.doFilter(request, response);
			}
		}
	
  }

  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    Properties pro = new Properties();

   // System.out.println("session:>" + path);
    try {
   /*   pro.load(new FileInputStream("dbproperty.properties"));
      path = pro.getProperty("path");
      
      */
    	
    	path="mobi";
    	System.out.println("fileter session:" + path);  
    }
    catch (Exception e) {
      e.printStackTrace();
    }


  }

}
