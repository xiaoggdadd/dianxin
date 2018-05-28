package com.noki.newfunction.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.function.CityQueryBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.zgsjdao;
import com.noki.newfunction.javabean.zgsj;

public class ZGsjServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");//������������ʽ��
		response.setContentType("text/html;charsetType=utf-8");//������������ʽ��
		
		String path = request.getContextPath();
		
		
		HttpSession session = request.getSession();
		Account account =(Account)session.getAttribute("account");
		String loginName = account.getAccountName();
		String loginId = account.getAccountId();
		
		
    	DbLog log = new DbLog();	
	
    	
		
		String action = request.getParameter("action");
		if(action.equals("sjesh")){//���ͨ��
			String url = path+ "/web/jzcbnewfunction/zgsjsh2.jsp",msg="";
			zgsjdao dao = new zgsjdao();
			String id = request.getParameter("chooseIdStr1");
			System.out.println("-----------"+id);
			int rs = dao.sjeshtg(id,loginName);
			if(rs==1){
				msg="�м��쵼���ͨ���ɹ�";
			}else if(rs==0){
				msg="�м��쵼���ͨ��ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}else if(action.equals("sjeshbu")){//��˲�ͨ��
			String url = path+ "/web/jzcbnewfunction/zgsjsh2.jsp",msg="";
			zgsjdao dao = new zgsjdao();
			String id = request.getParameter("chooseIdStr1");
			int rs = dao.sjeshbtg(id,loginName);
			if(rs==1){
				msg="�м��쵼��˲�ͨ���ɹ�";
			}else if(rs==0){
				msg="�м��쵼��˲�ͨ��ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}else if(action.equals("sjeshqxsh")){
			String url = path+ "/web/jzcbnewfunction/zgsjsh2.jsp",msg="";
			zgsjdao dao = new zgsjdao();
			String id = request.getParameter("chooseIdStr1");
			int rs = dao.sjeshqxsh(id,loginName);
			if(rs==1){
				msg="�м��쵼���ȡ����˳ɹ�";
			}else if(rs==0){
				msg="�м��쵼���ȡ�����ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}else if ("cwzdshyes".equals(action)) {//�ݹ��������ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    //	String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	       String  url = path + "/web/jzcbnewfunction/zgcwsh.jsp" ;
	       String  msg = bean.zgcwshtg(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��������ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");

	    }else if("cwzdshno".equals(action)){//�ݹ�������˲�ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgcwsh.jsp" ;
	    	String  msg = bean.zgcwshno(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ�������˲�ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("cwzdshqx".equals(action)){//�ݹ��������ȡ��
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String  url = path + "/web/jzcbnewfunction/zgcwsh.jsp" ;
	    	String  msg = bean.zgcwshqx(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��������ȡ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("rgzdshyes".equals(action)){//�ݹ��˹����ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgrgsh.jsp" ;
	    	String msg = bean.zgrgshtg(chooseIdStr1,loginName);
	        if("������˳ɹ���".equals(msg)){
	        	log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��˹����ͨ��"); 
	        }
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("rgzdshno".equals(action)){//�ݹ��˹���˲�ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgrgsh.jsp" ;
	    	String msg = bean.zgrgshno(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��˹����δͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("rgzdshqx".equals(action)){//�ݹ��˹����ȡ��
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgrgsh.jsp" ;
	    	String msg = bean.zgrgshqx(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��˹����ȡ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    } else if (action.equals("zgshtg")) {
	    	zgsjdao zd=new zgsjdao();
			String longinid = request.getParameter("chooseIdStr1");
			//String longinname = request.getParameter("loginName");
	    	 
	       String url = path + "/web/jzcbnewfunction/zgsjshenhe.jsp" ;
	         String   msg = zd.zgsjshenhetg(longinid,loginName);
	            log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��м����"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
			}	
	        else if(action.equals("zgsjshenheno")){
	        	zgsjdao zd=new zgsjdao();
		    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
		    	//loginName = request.getParameter("loginName");
		    	String url = path + "/web/jzcbnewfunction/zgsjshenhe.jsp" ;
		    	String msg = zd.zgsjshenheno(chooseIdStr1,loginName);
		        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��м���˲�ͨ��"); 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        response.sendRedirect(path + "/web/msg.jsp");
	        }
	        else if(action.equals("zgsjquxiaosh")){
	        	zgsjdao zd=new zgsjdao();
		    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
		    	// loginName = request.getParameter("loginName");
		       String url = path + "/web/jzcbnewfunction/zgsjshenhe.jsp" ;
		        String msg = zd.zgsjqxsh(chooseIdStr1,loginName);
		        log.write(msg, account.getName(), request.getRemoteAddr(), "�ݹ��м����δ���"); 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        response.sendRedirect(path + "/web/msg.jsp");
		
		
	        }else if(action.equals("save")){
	            String lrren=request.getParameter("lrren");
	            String url = path + "/web/jzcbnewfunction/addZangudan.jsp"; 
	            String zt = request.getParameter("zt");
	     	    ArrayList<zgsj> list=new ArrayList<zgsj>();
	     	    zgsjdao dao = new zgsjdao();
	            if(null!=request.getSession().getAttribute("jzname")){
	    			list=(ArrayList<zgsj>)request.getSession().getAttribute("jzname");
	    			// System.out.println("wwwwww"+list.size());
	    		}
	            String msg = "";
	            String ret="";
	            long uuid1=System.currentTimeMillis();
	            String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
	    		String uuid = uuid2+Long.toString(uuid1).substring(3);
	    		if(zt.equals("2")){
	    			for(zgsj b:list){
	    				String shi1 = b.getShi();
	    				String xian1 = b.getQuxian();
	    				String zgyf1 = b.getZgyf();
	    				String zdid = b.getZdid();
	    				String dbid = b.getDbid();
	    				dao.Delete1(shi1,xian1,zgyf1,zdid,dbid);
	    			}
	    			ret = dao.insertDate2(list,uuid,lrren);
	    		}else{
	    			for(zgsj b:list){
	    				String shi1 = b.getShi();
	    				String xian1 = b.getQuxian();
	    				String zgyf1 = b.getZgyf();
	    				dao.Delete(shi1,xian1,zgyf1);
	    			}
	    			if(list.size()>0){
	    				ret = dao.insertDate(list,uuid,lrren);
	    			}
	    		}
	    		if("1".equals(ret)){
    				msg="�ݹ���̬���ݱ���ʧ��";
    			}else if("2".equals(ret)){
    				msg="�ݹ���̬���ݱ���ɹ�";
    			}
	    			
	    		
	    		log.write(msg, loginName, request.getRemoteAddr(), msg);
	    		session.setAttribute("url", url);
	    		session.setAttribute("msg", msg);
	    		try {
	    		    response.sendRedirect(path + "/web/msg.jsp");
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	          }
		}
}
