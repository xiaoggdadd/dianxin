package com.noki.sysconfig.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.sysconfig.javabean.StaticDataBean;
import com.noki.sysconfig.javabean.StaticDataViewBean;


public class StaticDataServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String Content_type = "text/html;charset=UTF-8";
	public StaticDataServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType(Content_type);
    	StaticDataBean selStaticData = new StaticDataBean();
    	String typeVal = req.getParameter("typeVal");
    	String modfy = req.getParameter("Action");
    	if (modfy!=null && "modfy".equals(modfy)){
    		String code = req.getParameter("code");
    		String ret = selStaticData.getModfyStaticData(typeVal,code);
            PrintWriter out = null;
	   		try {
	   			out = resp.getWriter();
	   		} catch (IOException e) {
	   			e.printStackTrace();
	   		}
	            out.print(ret!=null?ret.substring(0,ret.length()-1):"");
    	}else{
            String ret = selStaticData.getStaticData(typeVal);
            PrintWriter out = null;
	   		try {
	   			out = resp.getWriter();
	   		} catch (IOException e) {
	   			e.printStackTrace();
	   		}
	            out.print(ret!=null?ret.substring(0,ret.length()-1):"");
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
