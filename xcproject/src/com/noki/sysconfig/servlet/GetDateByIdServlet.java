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


public class GetDateByIdServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String Content_type = "text/html;charset=UTF-8";
	public GetDateByIdServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
  //      String type = req.getParameter("type");
        resp.setContentType(Content_type);
//    	String staticType = req.getParameter("staticType");
        String id = req.getParameter("id");
    	StaticDataBean selStaticData = new StaticDataBean();
        String ret = selStaticData.getDateById(Integer.parseInt(id));
         PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
         out.print(ret.substring(0,ret.length()-1));
    	
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
    	this.doPost(req, resp);
    }
    
  /*  public  static void main(String [] args){
    	StaticDataBean selStaticData = new StaticDataBean();
    	String ret  = selStaticData.getZhandianData("");
    	System.out.println(ret);
    	
    }*/
 
}
