package com.noki.mobi.up;

import javax.servlet.http.HttpServlet;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import java.util.Vector;
import java.net.URLEncoder;
import com.noki.util.Path;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class IndataServlet extends HttpServlet {
  public IndataServlet() {
  }
  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
 public void init() throws ServletException {}

 private String msg;
 private String url;
 private String sendUrl;

 public void doPost(HttpServletRequest request, HttpServletResponse response) throws
     ServletException, IOException {
   response.setContentType(CONTENT_TYPE);
   PrintWriter out = response.getWriter();
   HttpSession session = request.getSession();
   String path = request.getContextPath();
   url = path+"/web/up/uplist.jsp";
   //sendUrl = "/mobilebuss/web/msg.jsp";

   String action =(String) request.getAttribute("action");
   String filename = path+"//indata//test.xls";
   if(action.equals("indata")){
     try {
       Path pa=new Path();
       //System.out.println(pa.getPhysicalPath(filename,0));
       //System.out.println(pa.getPhysicalPath(filename,1));
       //System.out.println(pa.getPhysicalPath(filename,2));
       Path ppath = new Path();
    ppath.servletInitialize(getServletConfig().getServletContext());
    String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
    System.out.println("dir1=="+dir1);
    filename=dir1+"test.xls";
      ReadFile temp = new ReadFileFactory().getReadFile(filename);
      //Vector head=temp.getColumns(filename);
      Vector content = temp.getContent(filename);
      Insert sellin = new Insert();
      Vector wrong = sellin.getWrong(content);
      sellin.closeDb();
      System.out.println("000000"+wrong.size());
      if (wrong.size() >= 1) {
                                System.out.println("1111111");
        String title = "导入不成功数据";
        title = URLEncoder.encode(title, "UTF-8");


        String s = "/indata/error.xls";
        System.out.println("33333333");


      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }

   }

   response.sendRedirect(url);
 }



 public void doGet(HttpServletRequest request,
                   HttpServletResponse response) throws ServletException,
     IOException {
   doPost(request, response);
 }

}
