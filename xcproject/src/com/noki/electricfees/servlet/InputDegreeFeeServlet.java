package com.noki.electricfees.servlet;

import javax.servlet.http.HttpServlet;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.noki.mobi.common.Account;
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
public class InputDegreeFeeServlet
    extends HttpServlet {
  public InputDegreeFeeServlet() {
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
    //url = "/mobilebuss/web/basedata/endmanager.jsp";
    //sendUrl = "/mobilebuss/web/msg.jsp";
    try {
      this.upload(request, response);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    msg = "上传文件";
    //session.setAttribute("msg",msg);
    //session.setAttribute("url",url);
    //response.sendRedirect(sendUrl);
    String servletName = request.getParameter("servletname");
    request.setAttribute("action", "indata");

    RequestDispatcher dispatcher = request.getRequestDispatcher("/servlet/indatadegreefee");//电费单导入
    //RequestDispatcher dispatcher = request.getRequestDispatcher("/servlet/sellmantype.do");
    dispatcher.forward(request, response);
  }

  public void upload(HttpServletRequest request, HttpServletResponse response) throws
      Exception {
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");
    // String store = "";
    ArrayList list = new ArrayList();
    DiskFileUpload fu = new DiskFileUpload();
    // 设置允许用户上传文件大小,单位:字节，这里设为2m
    fu.setSizeMax(5 * 1024 * 1024);
    // 设置最多只允许在内存中存储的数据,单位:字节
    fu.setSizeThreshold(4096);
    // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
    Path path = new Path();
    path.servletInitialize(getServletConfig().getServletContext());
    String dir1 = path.getPhysicalPath("/indata/", 0); // 传参数
    fu.setRepositoryPath(dir1);
    // 开始读取上传信息
    List fileItems = fu.parseRequest(request);
    // 依次处理每个上传的文件
    Iterator iter = fileItems.iterator();
    // 正则匹配，过滤路径取文件名
    String regExp = ".+\\\\(.+)$";
    // 过滤掉的文件类型
    // String[] errorType={".exe",".com",".cgi",".asp"};
    Pattern p = Pattern.compile(regExp);
    String zipname = null;
    //int num = 0;
    File file = null;
    while (iter.hasNext()) {
      FileItem item = (FileItem) iter.next();
      // 忽略其他不是文件域的所有表单信息
      if (!item.isFormField()) {
        String name = item.getName();
        String   extName   =   name.substring(name.lastIndexOf("\\")+1); 

      // System.out.println("inputServletdegreefee:"+extName);
        long size = item.getSize();
       // System.out.println("filesize"+size);
        if ( (name == null || name.equals("")) && size == 0) {
          continue;
        }
     /*   Matcher m = p.matcher(name);
        boolean result = m.find();
        System.out.println(result);
        if (result) {*/
          try {
            // 保存上传的文件到指定的目录
            String fileType = ".xls";
            //zipname = "test" + fileType;
            zipname = extName;
            session.setAttribute("filename", dir1 + zipname);
            request.setAttribute("filename", dir1 + zipname);
            file = new File(dir1 + zipname);
            item.write(file);
            System.out.println("----");
          }
          catch (Exception e) {
            // out.println(e);
            e.printStackTrace();
          }

          /*}
        else {
          throw new IOException("fail to upload");
        }*/
      }
    }
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
      IOException {
    doPost(request, response);
  }

}
