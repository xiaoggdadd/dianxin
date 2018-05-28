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
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.util.Path;
import com.noki.util.WriteExcle;

public class Daoruhtd extends HttpServlet {

	 public Daoruhtd() {
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
	   String id="";//��ʽ�жϴ��Ŀ�ֵû������
	   url = path + "/web/msgdr.jsp";
	   msg = "��ͬ����������ʧ�ܣ�";
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
	    String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
	    UploadDaoQxfj dao = new UploadDaoQxfj(account, id, filename);//���汾��ʽ�ж���
	    boolean gs = dao.isUpload(filename);//���汾��ʽ�жϷ���
	    
	    
	   
	      //System.out.println("000000:"+wrong.size());
	      if(gs){//���汾��ʽ�ж�
		    	msg = "��ͬ������ģ���ʽ����ȷ������ϵͳ������ģ�壡";
		    }else{
		    	 ReadFile temp = new ReadFileFactory().getReadFile(filename);
			      Vector content = temp.getContenthtd(filename);
			      Daoruhtdbean sellin = new Daoruhtdbean();
			  	  //��������
			  	  CountForm cform=new CountForm();
			      
			    if((content.size()-1)<=300){//�����ж�
			    	 Vector wrong = sellin.getWronghtd(content,cform,accountname,loginId);
				      sellin.closeDb();
		    		if (!wrong.isEmpty()) {
		    			msg = "�� " + cform.getZg() + "  �����ɹ����� " + cform.getCg() +
		    					" ������ " + cform.getBcg() + "  ������δ���룡";
		    			WriteExcle wr = new WriteExcle();
		    			String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
		    			wr.Write(wrong, account.getAccountName() + "��ͬ�����벻�ɹ�������.xls", "��ͬ�����벻�ɹ�������",
	                   "��ͬ�����벻�ɹ�����", 32, dir2);
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
	    session.setAttribute("url", path+"/web/electricfees/bargainDan.jsp");
	    session.setAttribute("wfile", path + "/wrongdata/" + account.getAccountName() +"��ͬ�����벻�ɹ�������.xls");
	   
	    
	    response.sendRedirect(url);
	   }

	}
	 public void doGet(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException,
	     IOException {
	   doPost(request, response);
	 }

}
