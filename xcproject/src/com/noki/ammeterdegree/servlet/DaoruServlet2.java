package com.noki.ammeterdegree.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.noki.jizhan.daoru.CountForm;
import com.noki.jizhan.daoru.DaoruInsert;
import com.noki.jizhan.daoru.DaoruInsert2;
import com.noki.jizhan.daoru.InsertZD;
import com.noki.jizhan.daoru.ReadXLS_ZD;
import com.noki.mobi.common.Account;
import com.noki.util.Path;
import com.noki.util.WriteExcle;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DaoruServlet2 extends HttpServlet {
	public DaoruServlet2() {
    }
 
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	    private String msg;
	    private String url;
	    private String filename = "";
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    HttpSession session = request.getSession();
    String path = request.getContextPath();
    url = path + "/web/msgdr.jsp";
    //sendUrl = "/mobilebuss/web/msg.jsp";
    String loginName = (String) session.getAttribute("loginName");
    Account account=(Account)session.getAttribute("account");
    String accountname=account.getAccountName();
    String action = request.getParameter("action");
    System.out.println(action+"action");
    msg = "����ʧ�ܣ������Ի���ϵ����Ա";
    System.out.println("daorusuoyou begin");
    if (action.equals("daorusuoyou2")) {
        try {
        	
            System.out.println("daorusuoyou begin");
            filename = upload(request, response, loginName);
            Path ppath = new Path();
            ppath.servletInitialize(getServletConfig().getServletContext());
            String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
            String filedir = dir1 + filename + ".xls";
            System.out.println("��ʱ�����ϴ�����Ϣ"+filedir);
            ReadXLS_ZD temp = new ReadXLS_ZD(); //ReadFileFactory_ZD().getReadFile(filedir);
            //Vector head=temp.getColumns(filename);
            Vector content = temp.getContent5(filedir);
            System.out.println("content.toString()" + content.toString());
            DaoruInsert2 sellin = new DaoruInsert2();
            CountForm form = new CountForm();
            Vector wrong = sellin.getWrong(content, form,accountname);
            sellin.closeDb();
            if (!wrong.isEmpty()) {
                msg = "�� " + form.getZg() + "  �����ɹ����� " + form.getCg() +
                      " ������ " + form.getBcg() + "  ������δ���룡";
                WriteExcle wr = new WriteExcle();
                System.out.println("222222222");
                String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
                wr.Write(wrong, loginName + "վ�㵼�벻�ɹ�������.xls", "���벻�ɹ�������",
                         "���벻�ɹ�����", 26, dir2);
            } else {
                msg = "ȫ������ɹ��������� " + form.getCg() + " ����";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("url", path + "/web/jizhan/daoru2.jsp");
        session.setAttribute("msg", msg);
        session.setAttribute("wfile",path + "/wrongdata/" + loginName +"վ�㵼�벻�ɹ�������.xls");

    }
    response.sendRedirect(url);
	}
	    public void doGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException,
      IOException {
  doPost(request, response);
}

public synchronized String upload(HttpServletRequest request,
                                HttpServletResponse response,
                                String loginName) throws
      Exception {
  PrintWriter out = response.getWriter();
  HttpSession session = request.getSession();
  filename = loginName + System.currentTimeMillis();
  // String store = "";
  ArrayList list = new ArrayList();
  DiskFileUpload fu = new DiskFileUpload();
  fu.setHeaderEncoding("utf-8");
  // ���������û��ϴ��ļ���С,��λ:�ֽڣ�������Ϊ2m
  fu.setSizeMax(5 * 1024 * 1024);
  // �������ֻ�������ڴ��д洢������,��λ:�ֽ�
  fu.setSizeThreshold(4096);
  // ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼
  Path path = new Path();
  path.servletInitialize(getServletConfig().getServletContext());
  String dir1 = path.getPhysicalPath("/indata/", 0); // ������
  System.out.println("dirl==" + dir1);
  fu.setRepositoryPath(dir1);
  // ��ʼ��ȡ�ϴ���Ϣ
  List fileItems = fu.parseRequest(request);
  // ���δ���ÿ���ϴ����ļ�
  Iterator iter = fileItems.iterator();
  System.out.println("dirl 22 ==" + dir1);
  // ����ƥ�䣬����·��ȡ�ļ���
  String regExp = ".+\\\\(.+)$";
  // ���˵����ļ�����
  // String[] errorType={".exe",".com",".cgi",".asp"};
  Pattern p = Pattern.compile(regExp);
  String zipname = null;
  int num = 0;
  File file = null;
  while (iter.hasNext()) {
      System.out.println("dirl 33 ==" + dir1);
      FileItem item = (FileItem) iter.next();
      // �������������ļ�������б���Ϣ
      if (!item.isFormField()) {
          System.out.println("dirl 44 ==" + dir1);
          String name = item.getName();
          long size = item.getSize();
          System.out.println("name==" + name);
          if ((name == null || name.equals("")) && size == 0) {
              continue;
          }
          System.out.println("dirl 5 ==" + dir1);
          Matcher m = p.matcher(name);
          System.out.println("dirl 6 ==" + dir1);
          boolean result = true; //m.find();
          System.out.println("dirl 7 ==" + result);
          if (result) {
              System.out.println("dirl 55 ==" + dir1);
              try {
                  // �����ϴ����ļ���ָ����Ŀ¼
                  String fileType = ".xls";
                  zipname = filename + fileType;
                  session.setAttribute("filename", dir1 + zipname);
                  System.out.println("�����ϴ����ļ���ָ����Ŀ¼"+dir1 + zipname);
                  file = new File(dir1 + zipname);
                  item.write(file);
              } catch (Exception e) {
                  // out.println(e);
                  e.printStackTrace();
              }

          } else {
              throw new IOException("fail to upload");
          }
      }
  }
  return filename;
}
}
