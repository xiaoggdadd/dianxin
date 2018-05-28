package com.noki.comm.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.comm.bean.SeachItemBean;
import com.noki.comm.service.SearchService;

public class SeachServlet extends HttpServlet  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
    	resp.setContentType(Content_type);
    	String dbitem =req.getParameter("dbitem");
    	String quiryTable= req.getParameter("quiryTaeble");
    	String loginName = (String)req.getSession().getAttribute("loginName");
    	SearchService seach = new SearchService();
    	SeachItemBean delBean = new SeachItemBean();
    	delBean.setUserId(loginName);
    	delBean.setUserTable(quiryTable);
    	seach.searchdel(delBean);
    	if (dbitem!=null && !"".equals(dbitem)){
    		String[] temp= dbitem.split(",");
    		if (temp!=null && temp.length>0){
    	    	SeachItemBean seachBean = new SeachItemBean();
    			for(String dd :temp){
    				String[]tempItem =dd.split("/");
    				if (tempItem!=null && tempItem.length>0){
						seachBean.setUserTable(quiryTable);
						seachBean.setUserId(loginName);
						seachBean.setLeftId(tempItem[0]);
						seachBean.setItem(tempItem[1]);
						seachBean.setCompareItem(tempItem[2]);
						seachBean.setCompareData(tempItem[3]);
						seachBean.setConettion(tempItem[4]);
						seachBean.setRightId(tempItem[5]);
						seach.insertData(seachBean);
    				}
    			}
    		}
    		
    	}
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
    	this.doPost(req, resp);
    }
}
