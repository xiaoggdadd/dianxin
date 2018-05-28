package com.noki.map.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ZhandianMapServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String Content_type = "text/html;charset=UTF-8";
	public ZhandianMapServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
    	
    	SelZhandianBean selZhandian = new SelZhandianBean();
        if ("ajaxareaData".equals(action)){
        	String zhanDianId = req.getParameter("zhanDian");
        	String ret=new String();
            try {
                if (zhanDianId!=null){
                	 HttpSession session = req.getSession();
                     String accountid = (String) session.getAttribute("loginName");
            		ret = selZhandian.getshiData(zhanDianId,accountid);
            	}
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("sel".equals(action)){
        	String ret=new String();
        	String shi = req.getParameter("shi");
        	String xian = req.getParameter("xian");
        	String xiaoqu = req.getParameter("xiaoqu");
        	String zdmc = req.getParameter("zdmc");
        	//String biaogan = req.getParameter("biaogan");
        	//String fangwuleixing = req.getParameter("fangwuleixing");
            try {

            	ret = selZhandian.getZhandianData(shi,xian,xiaoqu,zdmc);

            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("sellocality".equals(action)){
        	String ret=new String();
        	String shi = req.getParameter("shi");
        	String xian = req.getParameter("xian");
        	String xiaoqu = req.getParameter("xiaoqu");
        	String zdmc = req.getParameter("zdmc");
            try {

            	ret = selZhandian.getLocality(shi,xian,xiaoqu,zdmc);

            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	//System.out.println("+++++"+ret.toString());
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
    private String getString(String input){
        String output = "";
        if(input==null||input.equals("null")){
            output = "";
        }else{
            return input;
        }
        return output;
    }
}
