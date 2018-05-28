package com.noki.participateinfo;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParticipateinfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public ParticipateinfoServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
        ParticipateinfoBean bean = new ParticipateinfoBean();
   	    ParticipateinfoViewBean beanView = new ParticipateinfoViewBean();
        if ("getSelData".equals(action)){
        	 String xmmch = req.getParameter("xmmch");
        	 String cyrmch = req.getParameter("cyrmch");
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");
            	String min = req.getParameter("minnum");
        		ret = bean.getSelData(xmmch,cyrmch,max,min);          	

            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("insert".equals(action)){
        	 String cysj  = req.getParameter("cysj");    
        	 String zzms = req.getParameter("zzms");      
        	 String gsmm = req.getParameter("gsmm");      
        	 String cyrms = req.getParameter("cyrms");      
        	 String cyrmch = req.getParameter("cyrmch");     
        	 String xmmch = req.getParameter("xmmch");
        	 beanView.setXmmch(xmmch);
        	 beanView.setCyrmch(cyrmch);
        	 beanView.setCyrms(cyrms);
        	 beanView.setCysj(cysj);
        	 beanView.setGsmm(gsmm);
        	 beanView.setZzms(zzms);
        	 String ret = "";
        	 ret= bean.insertData(beanView);
        }else if ("updateSele".equals(action)){
        	String xmmch = req.getParameter("xmmch");
            String ret = "";
            try {
        		ret = bean.updateSel(xmmch);        	
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("updata".equals(action)){
       	 String cysj  = req.getParameter("cysj");    
    	 String zzms = req.getParameter("zzms");      
    	 String gsmm = req.getParameter("gsmm");      
    	 String cyrms = req.getParameter("cyrms");      
    	 String cyrmch = req.getParameter("cyrmch");     
    	 String xmmch = req.getParameter("xmmch");
    	 beanView.setXmmch(xmmch);
    	 beanView.setCyrmch(cyrmch);
    	 beanView.setCyrms(cyrms);
    	 beanView.setCysj(cysj);
    	 beanView.setGsmm(gsmm);
    	 beanView.setZzms(zzms);
        	 String ret = "";
        	 ret= bean.insertData(beanView);
        }else if ("del".equals(action)){
        	String xmmch = req.getParameter("xmmch");
            String ret = "";
            try {
        		ret = bean.delData(xmmch);        	
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("optionSele".equals(action)){
            String ret = "";
            try {
        		ret = bean.optionSele();        	
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
    	this.doPost(req, resp);
    }
}
