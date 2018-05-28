package com.noki.daoruelectricfees.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.daoruelectricfees.javabean.DaoruElectricfees;
import com.noki.electricfees.servlet.ReadFile;
import com.noki.electricfees.servlet.ReadFileFactory;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.util.Path;
import com.noki.util.WriteExcle;

public class NewDaoruElectricfeesServlet extends HttpServlet {

	 public NewDaoruElectricfeesServlet() {
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
	   String id="";//��ʽ�жϴ��Ŀ�ֵû������
	   String path = request.getContextPath();
	   url = path + "/web/msgdr.jsp";
	   msg = "���������������ʧ�ܣ�";
	   String action =(String) request.getAttribute("action");
	   if(null==action||"".equals(action)){
		   action=request.getParameter("action");
	   }
	   String filename =(String) request.getAttribute("filename");
	   System.out.println(action);//////////////
	   if(action.equals("indata")){ //��ѵ�����
	     try {
	       Path pa=new Path();
	       Path ppath = new Path();
	    ppath.servletInitialize(getServletConfig().getServletContext());
	    String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
	    UploadDaoQxfj dao = new UploadDaoQxfj(account, id, filename);//���汾��ʽ�ж���
	    boolean gs = dao.isUpload(filename);//���汾��ʽ�жϷ���
	    
	   
	    if(gs){//���汾��ʽ�ж�
	    	msg = "��ѵ�����ģ���ʽ����ȷ������ϵͳ������ģ�壡";
	    }else{
	    	 ReadFile temp = new ReadFileFactory().getReadFile(filename);
		      //Vector head=temp.getColumns(filename);
		      Vector content = temp.getContent(filename);
		      DaoruElectricfees sellin = new DaoruElectricfees();
		      //InsertDegreeFee sellin = new InsertDegreeFee();
		  	  //��������
		  	  CountForm cform=new CountForm();
		      long a =new Date().getTime();
		      
	    	
	    	
	    	if((content.size()-1)<=300){ //�����ж�
	  	  	Vector wrong = sellin.getWrong(content,cform,accountname,loginId);
	  	  	long a1 =new Date().getTime();
	  	  	System.out.println("--"+a+"--"+a1+"************"+(a1-a));
	  	  	sellin.closeDb();
	      //System.out.println("000000:"+wrong.size());
	  	  		if (!wrong.isEmpty()) {
	          msg = "�� " + cform.getZg() + "  �����ɹ����� " + cform.getCg() +
	                " ������ " + cform.getBcg() + "  ������δ���룡";
	          WriteExcle wr = new WriteExcle();
	          String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
	          wr.Write(wrong, account.getAccountName() + "��ѵ����벻�ɹ�������.xls", "��ѵ����벻�ɹ�������",
	                   "��ѵ����벻�ɹ�����", 32, dir2);
	  	  		} else {
	  	  			
	  	  			if(cform.getCg()<=0){
	  	  				msg="����ģ���ڸ�ʽת�������б��𻵣�����ϵͳ������ģ��";
	  	  			}else{
	  	  				msg = "��������������� " + cform.getCg() + " ����";
	  	  			}
	          
	  	  		}
	   		}else{
	    	msg = "��������ҪС��300�������޸ĵ���������";
	    	
	    	}
	   }
	    }catch (Exception e) {
	      e.printStackTrace();
	    }
	    session.setAttribute("msg", msg);
	    session.setAttribute("url", path+"/web/electricfees/newelectricFeesInput.jsp");
	    session.setAttribute("wfile", path + "/wrongdata/" + account.getAccountName() +"��ѵ����벻�ɹ�������.xls");
	   
	    
	    response.sendRedirect(url);
	   }

	}
	 public void doGet(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException,
	     IOException {
	   doPost(request, response);
	 }

}
