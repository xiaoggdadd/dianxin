package com.noki.sysconfig.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.jizhan.JiZhanBean;
import com.noki.log.DbLog;
import com.noki.map.servlet.SelZhandianBean;
import com.noki.map.servlet.ZbandianVeiwBean;
import com.noki.mobi.common.Account;
import com.noki.sysconfig.javabean.localityDataBean;
import com.noki.sysconfig.javabean.localityViewDataBean;

public class LocalityDataServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public LocalityDataServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
    	String zhanDianId = req.getParameter("zhanDian");
    	localityDataBean localityBean = new localityDataBean();
    	
    	
    	String path = req.getContextPath();
		DbLog log = new DbLog();
		Account account = new Account();
		HttpSession session = req.getSession();
		String url = path + "/web/localityData/localityList.jsp", ms= "";
		String accountid = (String) session.getAttribute("loginName");
		String accountSheng = (String) session.getAttribute("accountSheng");
		
		 
       
    	
    	
        if ("select".equals(action)){
            System.out.println("1234567456789");
            //String url = path + "/web/zhandianMep/zhandianMep.jsp", msg = "";
            //List ret = new ArrayList();
            String ret = "";
            try {
            	String role = req.getParameter("role");
            	String max = req.getParameter("maxnum");
            	String min = req.getParameter("minnum");
            	System.out.println("11权限"+role);
                ret = localityBean.getLoclityData(zhanDianId,max,min,role);
//            	req.setAttribute("retList", ret);
//				req.getRequestDispatcher("/web/localityData/localityList.jsp").forward(req, resp);
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
            //session.setAttribute("url", url);
            //session.setAttribute("msg", msg);
        }else if ("ajaxareaData".equals(action)){
            String ret = "";
            try {
            	if (zhanDianId!=null){
            		String role = req.getParameter("role");
                	String max = req.getParameter("maxnum");
                	String min = req.getParameter("minnum");
            		ret = localityBean.getLoclityData(zhanDianId,max,min,role);          	
            	}
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("insert".equals(action)){
        	
        	String fatherCode = req.getParameter("fatherCode");
        	String areaCode = req.getParameter("areaCode");
        	String areaName = req.getParameter("areaName");
        	System.out.println("区域编码"+areaCode);
        	String logname=req.getParameter("logname");
        	String logid=req.getParameter("logid");
        	
        	localityViewDataBean bean = new localityViewDataBean();
        	bean.setAgcode(areaCode);
        	bean.setAgname(areaName);
        	if (areaCode!=null && areaCode.length()==3){
        		bean.setAgrade("1");
        	}
        	if (areaCode!=null && areaCode.length()==5){
        		bean.setAgrade("2");
        	}
        	if (areaCode!=null && areaCode.length()==7){
        		bean.setAgrade("3");
        	}
        	if (areaCode!=null && areaCode.length()==9){
        		bean.setAgrade("4");
        	}
        	bean.setFagcode(fatherCode);
        	String ret = localityBean.insertData(bean,logname,logid);
        	
        }else if ("del".equals(action)){

        	url = path + "/web/localityData/localityList.jsp";
        	ms = "删除地区失败！请重试或与管理员联系！";
        	String areaCode = req.getParameter("areaCode");
        	int ret=0;
        	if (areaCode!=null){
        		ret=localityBean.delData(areaCode);
        		if (ret == 1) {
    				ms = "删除地区成功！";
    			} else if (ret == 2) {
    				ms = "有关联的站点或市区县乡镇信息，不允许删除，请先删除相关联的信息";
    			}
        	}
        	
        	log.write(ms, accountid, req.getRemoteAddr(), "删除地区");
        	
			session.setAttribute("url", url);
			session.setAttribute("msg", ms);
			resp.sendRedirect(path + "/web/msg.jsp");
			
        }else if ("updateSele".equals(action)){
        	String fatherCode = req.getParameter("fatherCode");
        	String areaCode = req.getParameter("areaCode");
        	String ret = "";
            try {
            	ret = localityBean.getAddData(areaCode);          	
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("updata".equals(action)){
        	String fatherCode = req.getParameter("fagcode");
        	String areaCode = req.getParameter("areaCode");
        	String areaName = req.getParameter("areaName");
        	String agrade = req.getParameter("agrade");
        	String agid = req.getParameter("agid");
        	localityViewDataBean bean = new localityViewDataBean();
        	bean.setAgid(agid);
        	bean.setAgcode(areaCode);
        	bean.setAgname(areaName);
        	bean.setAgrade(agrade);
        	bean.setFagcode(fatherCode);
        	String ret = localityBean.updateData(bean);
        }else if ("selOption".equals(action)){
            String ret = "";
            try {
            	String role = req.getParameter("role");
            	if (zhanDianId!=null){
            		ret = localityBean.getData(zhanDianId,role);          	
            	}
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
    	this.doPost(req, resp);
    }
}
