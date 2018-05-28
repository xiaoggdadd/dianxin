package com.noki.cashInfo;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CashinfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public CashinfoServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
        CashinfoBean bean = new CashinfoBean();
   	    CashinfoViewBean beanView = new CashinfoViewBean();
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
        	 String ysztz  = req.getParameter("ysztz");    
        	 String sjztz = req.getParameter("sjztz");      
        	 String yjjsdf = req.getParameter("yjjsdf");      
        	 String sjjydf = req.getParameter("sjjydf");      
        	 String yssbtz = req.getParameter("yssbtz");     
        	 String sjsbtz = req.getParameter("sjsbtz");     
        	 String yssgtz = req.getParameter("yssgtz"); 
        	 String sjsgtz = req.getParameter("sjsgtz");     
        	 String yswhtz = req.getParameter("yswhtz");      
        	 String sjfhtz = req.getParameter("sjfhtz");     
        	 String yjtzl = req.getParameter("yjtzl");  
        	 String yjtzj = req.getParameter("yjtzj"); 
        	 String xmmch = req.getParameter("xmmch");
        	 beanView.setSjfhtz(sjfhtz);
        	 beanView.setSjjydf(sjjydf);
        	 beanView.setSjsbtz(sjsbtz);
        	 beanView.setSjsgtz(sjsgtz);
        	 beanView.setSjztz(sjztz);
        	 beanView.setXmmch(xmmch);
        	 beanView.setYjjsdf(yjjsdf);
        	 beanView.setYjtzj(yjtzj);
        	 beanView.setYjtzl(yjtzl);
        	 beanView.setYssbtz(yssbtz);
        	 beanView.setYssgtz(yssgtz);
        	 beanView.setYswhtz(yswhtz);
        	 beanView.setYsztz(ysztz);
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
		   	 String ysztz  = req.getParameter("ysztz");    
			 String sjztz = req.getParameter("sjztz");      
			 String yjjsdf = req.getParameter("yjjsdf");      
			 String sjjydf = req.getParameter("sjjydf");      
			 String yssbtz = req.getParameter("yssbtz");     
			 String sjsbtz = req.getParameter("sjsbtz");     
			 String yssgtz = req.getParameter("yssgtz"); 
			 String sjsgtz = req.getParameter("sjsgtz");     
			 String yswhtz = req.getParameter("yswhtz");      
			 String sjfhtz = req.getParameter("sjfhtz");     
			 String yjtzl = req.getParameter("yjtzl");  
			 String yjtzj = req.getParameter("yjtzj"); 
			 String xmmch = req.getParameter("xmmch");
			 beanView.setSjfhtz(sjfhtz);
			 beanView.setSjjydf(sjjydf);
			 beanView.setSjsbtz(sjsbtz);
			 beanView.setSjsgtz(sjsgtz);
			 beanView.setSjztz(sjztz);
			 beanView.setXmmch(xmmch);
			 beanView.setYjjsdf(yjjsdf);
			 beanView.setYjtzj(yjtzj);
			 beanView.setYjtzl(yjtzl);
			 beanView.setYssbtz(yssbtz);
			 beanView.setYssgtz(yssgtz);
			 beanView.setYswhtz(yswhtz);
			 beanView.setYsztz(ysztz);
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
