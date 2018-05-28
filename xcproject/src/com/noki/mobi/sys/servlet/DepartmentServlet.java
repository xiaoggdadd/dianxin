package com.noki.mobi.sys.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.noki.mobi.sys.javabean.DepartmentBean;
import com.noki.mobi.sys.javabean.DepartmentFromBean;
import com.noki.sysconfig.javabean.localityViewDataBean;


public class DepartmentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
    	String fdeptcode = req.getParameter("fdeptcode");
         DepartmentBean departmentBean = new DepartmentBean();
        if ("select".equals(action)){
            HttpSession session = req.getSession();
            String path = req.getContextPath();
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");;
            	String min = req.getParameter("minnum");
                ret =departmentBean.getDepartment(fdeptcode,max,min);
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("ajaxareaData".equals(action)){
            String ret = "";
            try {
            	if (fdeptcode!=null){
                	String max = req.getParameter("maxnum");;
                	String min = req.getParameter("minnum");
            		ret = departmentBean.getDepartment(fdeptcode,max,min);          	
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
    	DepartmentFromBean bean = new DepartmentFromBean();
    	bean.setDeptcode(areaCode);
    	bean.setDeptname(areaName);
    	if (areaCode!=null && areaCode.length()==4){
    		bean.setDeptgrade("1");
    	}
    	if (areaCode!=null && areaCode.length()==6){
    		bean.setDeptgrade("2");
    	}
    	if (areaCode!=null && areaCode.length()==8){
    		bean.setDeptgrade("3");
    	}
    	if (areaCode!=null && areaCode.length()==10){
    		bean.setDeptgrade("4");
    	}
    	bean.setFdeptcode(fatherCode);
    	String ret = departmentBean.insertData(bean);
    	
    }else if ("del".equals(action)){
        	String deptCode = req.getParameter("deptCode");
        	String ret = "";
        	if (deptCode!=null){
        		ret=departmentBean.delData(deptCode);
        	}

        }else if ("updateSele".equals(action)){
        	String fdeptCode = req.getParameter("fdeptCode");
        	String deptCode = req.getParameter("deptCode");
        	String ret = "";
            try {
            	ret = departmentBean.getAddData(deptCode);          	
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("updata".equals(action)){
        	String fdeptCode = req.getParameter("fdeptcode");
        	String deptCode = req.getParameter("deptCode");
        	String deptName = req.getParameter("deptName");
        	String deptgrade = req.getParameter("deptgrade");
        	int id =Integer.parseInt( req.getParameter("id"));
        	DepartmentFromBean bean = new DepartmentFromBean();
        	bean.setId(id);
        	bean.setDeptcode(deptCode);
        	bean.setDeptname(deptName);
        	bean.setDeptgrade(deptgrade);
        	bean.setFdeptcode(fdeptCode);
        	String ret = departmentBean.updateData(bean);
        }
        else if ("selOption".equals(action)){
        String ret = "";
            try {
            	if (fdeptcode!=null){
            		ret = departmentBean.getData(fdeptcode);          	
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
