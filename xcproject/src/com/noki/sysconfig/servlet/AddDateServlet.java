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


public class AddDateServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String Content_type = "text/html;charset=UTF-8";
	public AddDateServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType(Content_type);
//    	var params = "?dataType="+dataType+"&index1="+index1+"&code="+code+"&name="+name;
        String type = req.getParameter("dataType");
        String name=req.getParameter("name");
        String desc =req.getParameter("index2");
        String typevalue = req.getParameter("index1");
        String code = req.getParameter("code");
        StaticDataViewBean bean = new StaticDataViewBean();
        StaticDataBean selStaticData = new StaticDataBean();
        bean.setCode(code);
        bean.setType(type);
        bean.setName(name);
        bean.setIndexs1(typevalue);
        bean.setIndexs2(desc);
    	
        selStaticData.insertData(bean);
        PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
         out.print("Ok");
    	
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
