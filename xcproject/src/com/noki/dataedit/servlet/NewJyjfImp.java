package com.noki.dataedit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.dataedit.bean.zhandianbean;
import com.noki.dataedit.dao.InsertSession;
import com.noki.electricfees.servlet.ReadFile;
import com.noki.electricfees.servlet.ReadFileFactory;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.noki.util.Path;

public class NewJyjfImp extends HttpServlet {
	  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
		 public void init() throws ServletException {}

		 private String msg;
		 private String url;
		 private String sendUrl;
		 private String filepath;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		   response.setContentType(CONTENT_TYPE);
		   PrintWriter out = response.getWriter();
		   HttpSession session = request.getSession();
		   Account account = (Account)session.getAttribute("account");
		   String accountname=account.getAccountName();
		   String loginId=account.getAccountId();
		   String path = request.getContextPath();
		   url = path + "/web/dataedit/jyjf.jsp";
		   msg = "电量电费批量导入失败！";
		   String action =(String) request.getAttribute("action");
		   if(null==action||"".equals(action)){
			   action=request.getParameter("action");
		   }
		   String filename =(String) request.getAttribute("filename");
		   System.out.println("导入的文件名称："+filename);
		   
		   
		   System.out.println(action);//////////////
		 //电费单导入
		   List<zhandianbean> lst=new ArrayList<zhandianbean>();
		   Map map=new HashMap();
		     try {
		       Path pa=new Path();
		       Path ppath = new Path();
		    ppath.servletInitialize(getServletConfig().getServletContext());
		    String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
		    ReadFile temp = new ReadFileFactory().getReadFile(filename);
		      //Vector head=temp.getColumns(filename);
		      Vector content = temp.getContent(filename);
		      InsertSession sellin = new InsertSession();
		      //InsertDegreeFee sellin = new InsertDegreeFee();
		  	  //主键生成
		  	  CountForm cform=new CountForm();
		      //Vector wrong = sellin.getWrong(content,cform,accountname,loginId);
		      long a =new Date().getTime();
		   
		  	 map= sellin.getlsjyjf(content,cform,accountname,loginId);
		  	 Vector wrong =null;
		  	long a1 =new Date().getTime();
		  	System.out.println("--"+a+"--"+a1+"************"+(a1-a));
		      //System.out.println("000000:"+wrong.size());

		    }catch (Exception e) {
		      e.printStackTrace();
		    }
		    session.setAttribute("lstjyjf", map);
		 //    session.setAttribute("url", path+"/web/electricfees/newelectricFeesInput.jsp");
		   //  session.setAttribute("wfile", path + "/wrongdata/" + account.getAccountName() +"电费单导入不成功的数据.xls");
		   
		    
		    response.sendRedirect(url);
}

}
