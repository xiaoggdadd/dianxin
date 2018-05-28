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
    msg = "�ϴ��ļ�";
    //session.setAttribute("msg",msg);
    //session.setAttribute("url",url);
    //response.sendRedirect(sendUrl);
    String servletName = request.getParameter("servletname");
    request.setAttribute("action", "indata");

    RequestDispatcher dispatcher = request.getRequestDispatcher("/servlet/indatadegreefee");//��ѵ�����
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
    // ���������û��ϴ��ļ���С,��λ:�ֽڣ�������Ϊ2m
    fu.setSizeMax(5 * 1024 * 1024);
    // �������ֻ�������ڴ��д洢������,��λ:�ֽ�
    fu.setSizeThreshold(4096);
    // ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼
    Path path = new Path();
    path.servletInitialize(getServletConfig().getServletContext());
    String dir1 = path.getPhysicalPath("/indata/", 0); // ������
    fu.setRepositoryPath(dir1);
    // ��ʼ��ȡ�ϴ���Ϣ
    List fileItems = fu.parseRequest(request);
    // ���δ���ÿ���ϴ����ļ�
    Iterator iter = fileItems.iterator();
    // ����ƥ�䣬����·��ȡ�ļ���
    String regExp = ".+\\\\(.+)$";
    // ���˵����ļ�����
    // String[] errorType={".exe",".com",".cgi",".asp"};
    Pattern p = Pattern.compile(regExp);
    String zipname = null;
    //int num = 0;
    File file = null;
    while (iter.hasNext()) {
      FileItem item = (FileItem) iter.next();
      // �������������ļ�������б���Ϣ
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
            // �����ϴ����ļ���ָ����Ŀ¼
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
