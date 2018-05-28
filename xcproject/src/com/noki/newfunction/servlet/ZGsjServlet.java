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

		request.setCharacterEncoding("utf-8");//设置输入编码格式。
		response.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		
		String path = request.getContextPath();
		
		
		HttpSession session = request.getSession();
		Account account =(Account)session.getAttribute("account");
		String loginName = account.getAccountName();
		String loginId = account.getAccountId();
		
		
    	DbLog log = new DbLog();	
	
    	
		
		String action = request.getParameter("action");
		if(action.equals("sjesh")){//审核通过
			String url = path+ "/web/jzcbnewfunction/zgsjsh2.jsp",msg="";
			zgsjdao dao = new zgsjdao();
			String id = request.getParameter("chooseIdStr1");
			System.out.println("-----------"+id);
			int rs = dao.sjeshtg(id,loginName);
			if(rs==1){
				msg="市级领导审核通过成功";
			}else if(rs==0){
				msg="市级领导审核通过失败";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}else if(action.equals("sjeshbu")){//审核不通过
			String url = path+ "/web/jzcbnewfunction/zgsjsh2.jsp",msg="";
			zgsjdao dao = new zgsjdao();
			String id = request.getParameter("chooseIdStr1");
			int rs = dao.sjeshbtg(id,loginName);
			if(rs==1){
				msg="市级领导审核不通过成功";
			}else if(rs==0){
				msg="市级领导审核不通过失败";
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
				msg="市级领导审核取消审核成功";
			}else if(rs==0){
				msg="市级领导审核取消审核失败";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}else if ("cwzdshyes".equals(action)) {//暂估财务审核通过
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    //	String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	       String  url = path + "/web/jzcbnewfunction/zgcwsh.jsp" ;
	       String  msg = bean.zgcwshtg(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估财务审核通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");

	    }else if("cwzdshno".equals(action)){//暂估财务审核不通过
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgcwsh.jsp" ;
	    	String  msg = bean.zgcwshno(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估财务审核不通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("cwzdshqx".equals(action)){//暂估财务审核取消
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String  url = path + "/web/jzcbnewfunction/zgcwsh.jsp" ;
	    	String  msg = bean.zgcwshqx(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估财务审核取消"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("rgzdshyes".equals(action)){//暂估人工审核通过
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgrgsh.jsp" ;
	    	String msg = bean.zgrgshtg(chooseIdStr1,loginName);
	        if("财务审核成功！".equals(msg)){
	        	log.write(msg, account.getName(), request.getRemoteAddr(), "暂估人工审核通过"); 
	        }
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("rgzdshno".equals(action)){//暂估人工审核不通过
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgrgsh.jsp" ;
	    	String msg = bean.zgrgshno(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估人工审核未通过"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    }else if("rgzdshqx".equals(action)){//暂估人工审核取消
	    	String chooseIdStr1 = request.getParameter("chooseIdstr1");
	    	//String loginName = request.getParameter("loginName");
	    	zgsjdao bean = new zgsjdao();
	    	String url = path + "/web/jzcbnewfunction/zgrgsh.jsp" ;
	    	String msg = bean.zgrgshqx(chooseIdStr1,loginName);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估人工审核取消"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
	    	
	    } else if (action.equals("zgshtg")) {
	    	zgsjdao zd=new zgsjdao();
			String longinid = request.getParameter("chooseIdStr1");
			//String longinname = request.getParameter("loginName");
	    	 
	       String url = path + "/web/jzcbnewfunction/zgsjshenhe.jsp" ;
	         String   msg = zd.zgsjshenhetg(longinid,loginName);
	            log.write(msg, account.getName(), request.getRemoteAddr(), "暂估市级审核"); 
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
		        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估市级审核不通过"); 
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
		        log.write(msg, account.getName(), request.getRemoteAddr(), "暂估市级审核未审核"); 
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
    				msg="暂估静态数据保存失败";
    			}else if("2".equals(ret)){
    				msg="暂估静态数据保存成功";
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
