package com.noki.daoruelectricfees.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.daoruelectricfees.javabean.Daoruhtdbean;
import com.noki.electricfees.servlet.ReadFile;
import com.noki.electricfees.servlet.ReadFileFactory;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.noki.util.Path;
import com.noki.util.WriteExcle;

public class DaoruZhandianDanjia extends HttpServlet {
	 public DaoruZhandianDanjia() {
	  }
	  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 public void init() throws ServletException {}

	 private String msg;
	 private String url;
	 private String sendUrl;
	 private String filepath;

	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws
	     ServletException, IOException {
	   response.setContentType(CONTENT_TYPE);
	   PrintWriter out = response.getWriter();
	   HttpSession session = request.getSession();
	   Account account = (Account)session.getAttribute("account");
	   String accountname=account.getAccountName();
	   String loginId=account.getAccountId();
	   String path = request.getContextPath();
	   url = path + "/web/msgdr.jsp";
	   msg = "站点单价批量修改导入失败！";
	   String action =(String) request.getAttribute("action");
	   System.out.println(action+"====action===");
	   if(null==action||"".equals(action)){
		   action=request.getParameter("action");
	   }
	   String filename =(String) request.getAttribute("filename");
	   System.out.println(action);//////////////
	   if(action.equals("indata1")){
	     try {
	       Path pa=new Path();
	       Path ppath = new Path();
	    ppath.servletInitialize(getServletConfig().getServletContext());
	    String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
	    ReadFile temp = new ReadFileFactory().getReadFile(filename);
	      //Vector head=temp.getColumns(filename);
	      Vector content = temp.getContentdj(filename);
	      Daoruhtdbean sellin = new Daoruhtdbean();
	      //InsertDegreeFee sellin = new InsertDegreeFee();
	  	  //主键生成
	  	  CountForm cform=new CountForm();
	      //Vector wrong = sellin.getWrong(content,cform,accountname,loginId);
	      Vector wrong = sellin.getWrongdj(content,cform,accountname,loginId);
	     
	      sellin.closeDb();
	      //System.out.println("000000:"+wrong.size());
	      if (!wrong.isEmpty()) {
	          msg = "共 " + cform.getZg() + "  条。成功导入 " + cform.getCg() +
	                " 条！有 " + cform.getBcg() + "  条数据未导入！";
	          WriteExcle wr = new WriteExcle();
	          String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
	          wr.Write(wrong, account.getAccountName() + "站点单价批量修改导入不成功的数据.xls", "站点单价批量修改导入不成功的数据",
	                   "站点单价批量修改导入不成功数据", 32, dir2);
	      } else {
	          msg = "全部导入成功！共导入 " + cform.getCg() + " 条！";
	      }

	    }catch (Exception e) {
	      e.printStackTrace();
	    }
	    session.setAttribute("msg", msg);
	    session.setAttribute("url", path+"/web/jizhan/danjiatiaozheng.jsp");
	    session.setAttribute("wfile", path + "/wrongdata/" + account.getAccountName() +"站点单价批量修改导入不成功的数据.xls");
	   
	    
	    response.sendRedirect(url);
	   }

	}
	 public void doGet(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException,
	     IOException {
	   doPost(request, response);
	 }

}
