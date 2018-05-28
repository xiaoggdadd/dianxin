package com.noki.projectinfo;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectinfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public ProjectinfoServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
        ProjectinfoBean bean = new ProjectinfoBean();
   	    ProjectinfoViewBean beanView = new ProjectinfoViewBean();
        if ("getSelData".equals(action)){
        	 String xmmch = req.getParameter("xmmch");
        	 String xmzrr = req.getParameter("xmzrr");
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");
            	String min = req.getParameter("minnum");
        		ret = bean.getSelData(xmmch,xmzrr,max,min);          	

            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("insert".equals(action)){
        	 String xmmch  = req.getParameter("xmmch");    
        	 String xmlx = req.getParameter("xmlx");      
        	 String jfmch = req.getParameter("jfmch");      
        	 String xmfzr = req.getParameter("xmfzr");      
        	 String xmmsh = req.getParameter("xmmsh");     
        	 String kshsj = req.getParameter("kshsj");     
        	 String shjjshshj = req.getParameter("shjjshshj"); 
        	 String shgdw = req.getParameter("shgdw");     
        	 String jldw = req.getParameter("jldw");      
        	 String cyrsh = req.getParameter("cyrsh");     
        	 String yjjshshj = req.getParameter("yjjshshj");  
        	 beanView.setXmmch(xmmch);
        	 beanView.setXmlx(xmlx);
        	 beanView.setJfmch(jfmch);
        	 beanView.setXmfzr(xmfzr);
        	 beanView.setXmmsh(xmmsh);
        	 beanView.setKshsj(kshsj);
        	 beanView.setShjjshshj(shjjshshj);
        	 beanView.setShgdw(shgdw);
        	 beanView.setJldw(jldw);
        	 beanView.setCyrsh(cyrsh);
        	 beanView.setYjjshshj(yjjshshj);
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
	       	 String xmmch  = req.getParameter("xmmch");    
	    	 String xmlx = req.getParameter("xmlx");      
	    	 String jfmch = req.getParameter("jfmch");      
	    	 String xmfzr = req.getParameter("xmfzr");      
	    	 String xmmsh = req.getParameter("xmmsh");     
	    	 String kshsj = req.getParameter("kshsj");     
	    	 String shjjshshj = req.getParameter("shjjshshj"); 
	    	 String shgdw = req.getParameter("shgdw");     
	    	 String jldw = req.getParameter("jldw");      
	    	 String cyrsh = req.getParameter("cyrsh");     
	    	 String yjjshshj = req.getParameter("yjjshshj");  
	    	 beanView.setXmmch(xmmch);
	    	 beanView.setXmlx(xmlx);
	    	 beanView.setJfmch(jfmch);
	    	 beanView.setXmfzr(xmfzr);
	    	 beanView.setXmmsh(xmmsh);
	    	 beanView.setKshsj(kshsj);
	    	 beanView.setShjjshshj(shjjshshj);
	    	 beanView.setShgdw(shgdw);
	    	 beanView.setJldw(jldw);
	    	 beanView.setCyrsh(cyrsh);
	    	 beanView.setYjjshshj(yjjshshj);
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
        }
        
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
    	this.doPost(req, resp);
    }
}
