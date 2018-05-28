package com.noki.filter;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.RightBean;

import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;

public class SessionFilter
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

	  HttpSession session = ( (HttpServletRequest) request).getSession();
 //  session.setMaxInactiveInterval(-11);//设置session永不过期
    String path="";
    Account account = null;
    try {
      account = (Account) session.getAttribute("account");
       path=( (HttpServletRequest) request).getContextPath();
    }
    catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("accountaccountaccount:" + account);
    if (account == null) {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      
      out.println("parent.location='"+path+"/index.jsp'");
      out.println("</script>");
      out.flush();
      out.close();
    }else{
    	//------------判断是否有次jsp的权限
    	//是否有权限的标示
    	boolean hasAuthority = false;
    	ArrayList rightList = new ArrayList();
    	RightBean rightBean = new RightBean();
    	String roleId = account.getRoleId();
		rightList = rightBean.getAllRights(roleId);
		HttpServletRequest hreq = (HttpServletRequest) request;
		String methodName = hreq.getRequestURI().toString();
		String suffix = methodName.substring(methodName.lastIndexOf(".") + 1);
		//System.out.println("methodName>>>>>>>>"+methodName);
		if(suffix.equals("jsp")){
			if(methodName.contains("web/sdttWeb/index.jsp") || methodName.contains("accountName.jsp") || methodName.contains("error.html") || methodName.contains("msg.jsp") || methodName.contains("workAlert.jsp")|| methodName.contains("sys/modifyPass1.jsp")){
				hasAuthority = true;
			}else{
				if(rightList!=null){
					int count = ((Integer)rightList.get(0)).intValue();
					String url="",checked="";
					for(int i = count;i<rightList.size()-1;i+=count){
		               
		                url = (String)rightList.get(i+rightList.indexOf("URL")); 
		                checked = (String)rightList.get(i+rightList.indexOf("CHECKED"));
		             //   boolean status = ;  
		                if(url!=null){
		                	 if(methodName.contains(url) && checked.equals("1")){ 
		                     	hasAuthority = true;
		                     	break;
		                     }
		                }
		               
					}
				}
			}
			

			if(hasAuthority==true){
				chain.doFilter(request, response);
			}else{
				 response.setContentType("text/html; charset=UTF-8");
			      PrintWriter out = response.getWriter();
			      out.println("<script>");
			      
			      out.println("parent.location='"+path+"/NOTaccess.jsp'");
			      out.println("</script>");
			      out.flush();
			      out.close();
			}
		}else{
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
