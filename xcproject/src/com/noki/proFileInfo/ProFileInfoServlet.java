package com.noki.proFileInfo;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
public class ProFileInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public ProFileInfoServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
        ProFileInfoBean bean = new ProFileInfoBean();
   	    ProFileInfoViewBean beanView = new ProFileInfoViewBean();
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
        	 String xmmch  = req.getParameter("xmmch");    
        	 String wjbt = req.getParameter("wjbt");      
        	 String wjlx = req.getParameter("wjlx");      
        	 String wjjj = req.getParameter("wjjj");      
        	 String wjxx = req.getParameter("wjxx");   
        	 if (wjxx!=null && !"".equals(wjxx)){
            	 String[] parm = wjxx.split(File.separator+File.separator);

            	 beanView.setWjxx(parm[parm.length-1]);
        	 }else{
        		 beanView.setWjxx(wjxx);
        	 }
        	 beanView.setXmmch(xmmch);
        	 beanView.setWjbt(wjbt);
        	 beanView.setWjjj(wjjj);
        	 beanView.setWjlx(wjlx);
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
    	 String wjbt = req.getParameter("wjbt");      
    	 String wjlx = req.getParameter("wjlx");      
    	 String wjjj = req.getParameter("wjjj");      
    	 String wjxx = req.getParameter("wjxx");     
    	 beanView.setXmmch(xmmch);
    	 beanView.setWjbt(wjbt);
    	 beanView.setWjjj(wjjj);
    	 beanView.setWjlx(wjlx);
    	 if (wjxx!=null && !"".equals(wjxx)){
        	 String[] parm = wjxx.split(File.separator+File.separator);

        	 beanView.setWjxx(parm[parm.length-1]);
    	 }else{
    		 beanView.setWjxx(wjxx);
    	 }
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
