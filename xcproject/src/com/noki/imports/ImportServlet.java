package com.noki.imports;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImportServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public ImportServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
        ImportsBean bean = new ImportsBean();
        if ("getSelData".equals(action)){

            String ret = "";
            try {
        		ret = bean.getSelData();          	
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("impDate".equals(action)){
        	String zjb = req.getParameter("zjb");
            String ret = "";
            try {
        		ret = bean.impData(zjb);          	
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
