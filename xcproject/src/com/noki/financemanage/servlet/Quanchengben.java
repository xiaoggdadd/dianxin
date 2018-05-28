package com.noki.financemanage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.financemanage.javabean.FinanceSubjectBean;
import com.noki.financemanage.javabean.FinanceSubjectFromBean;
import com.noki.financemanage.javabean.QuanchengbenBean;
import com.noki.financemanage.javabean.QuanchengbenFromBean;

public class Quanchengben extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Quanchengben() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	}

	
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
    	String fqcbcode = req.getParameter("fqcbcode");
         QuanchengbenBean FSBean = new QuanchengbenBean();
        if ("select".equals(action)){
            HttpSession session = req.getSession();
            String path = req.getContextPath();
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");;
            	String min = req.getParameter("minnum");
                ret =FSBean.getQuanchengben(fqcbcode,max,min);
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
            	if (fqcbcode!=null){
                	String max = req.getParameter("maxnum");;
                	String min = req.getParameter("minnum");
            		ret = FSBean.getQuanchengben(fqcbcode,max,min);          	
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
        	QuanchengbenFromBean bean = new QuanchengbenFromBean();
        	bean.setQcbcode(areaCode);
        	bean.setQcbname(areaName);
        	if (areaCode!=null && areaCode.length()==2){
        		bean.setQcbgrade("1");
        	}
        	if (areaCode!=null && areaCode.length()==5){
        		bean.setQcbgrade("2");
        	}
        	if (areaCode!=null && areaCode.length()==7){
        		bean.setQcbgrade("3");
        	}
        	if (areaCode!=null && areaCode.length()==9){
        		bean.setQcbgrade("4");
        	}
        	bean.setFqcbcode(fatherCode);
        	String ret = FSBean.insertData(bean);
        	
        }else if ("del".equals(action)){
            	String qcbCode = req.getParameter("qcbCode");
            	String ret = "";
            	if (qcbCode!=null){
            		ret=FSBean.delData(qcbCode);
            	}

            }else if ("updateSele".equals(action)){
            	String fqcbCode = req.getParameter("fqcbCode");
            	String qcbCode = req.getParameter("qcbCode");
            	String ret = "";
                try {
                	ret = FSBean.getAddData(qcbCode);          	
                	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
                		ret=ret.substring(0,ret.length()-1);
                	
                	}
                	PrintWriter out =  resp.getWriter();
                	out.write(ret);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }else if ("updata".equals(action)){
            	String fqcbCode = req.getParameter("fqcbcode");
            	String qcbCode = req.getParameter("qcbCode");
            	String qcbName = req.getParameter("qcbName");
            	String qcbgrade = req.getParameter("qcbgrade");
            	int id =Integer.parseInt( req.getParameter("id"));
            	QuanchengbenFromBean bean = new QuanchengbenFromBean();
            	bean.setId(id);
            	bean.setQcbcode(qcbCode);
            	bean.setQcbname(qcbName);
            	bean.setQcbgrade(qcbgrade);
            	bean.setFqcbcode(fqcbCode);
            	String ret = FSBean.updateData(bean);
            }
        else if ("selOption".equals(action)){
        String ret = "";
            try {
            	if (fqcbcode!=null){
            		ret = FSBean.getData(fqcbcode);          	
            	}
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        } else if ("selOption1".equals(action)){
        String ret = "";
            try {
            	if (fqcbcode!=null){
            		ret = FSBean.getData1(fqcbcode);          	
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
	public void init() throws ServletException {
		// Put your code here
	}

}
