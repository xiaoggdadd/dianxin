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
import com.noki.mobi.sys.javabean.DepartmentFromBean;





public class FinanceSubject extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FinanceSubject() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
    	String fkmcode = req.getParameter("fkmcode");
         FinanceSubjectBean FSBean = new FinanceSubjectBean();
        if ("select".equals(action)){
            HttpSession session = req.getSession();
            String path = req.getContextPath();
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");;
            	String min = req.getParameter("minnum");
                ret =FSBean.getFinanceSubject(fkmcode,max,min);
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
            	if (fkmcode!=null){
                	String max = req.getParameter("maxnum");;
                	String min = req.getParameter("minnum");
            		ret = FSBean.getFinanceSubject(fkmcode,max,min);          	
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
        	FinanceSubjectFromBean bean = new FinanceSubjectFromBean();
        	bean.setKmcode(areaCode);
        	bean.setKmname(areaName);
        	if (areaCode!=null && areaCode.length()==4){
        		bean.setKmgrade("1");
        	}
        	if (areaCode!=null && areaCode.length()==6){
        		bean.setKmgrade("2");
        	}
        	if (areaCode!=null && areaCode.length()==8){
        		bean.setKmgrade("3");
        	}
        	if (areaCode!=null && areaCode.length()==14){
        		bean.setKmgrade("4");
        	}
        	bean.setFkmcode(fatherCode);
        	String ret = FSBean.insertData(bean);
        	
        }else if ("del".equals(action)){
            	String kmCode = req.getParameter("kmCode");
            	String ret = "";
            	if (kmCode!=null){
            		ret=FSBean.delData(kmCode);
            	}

            }else if ("updateSele".equals(action)){
            	String fkmCode = req.getParameter("fkmCode");
            	String kmCode = req.getParameter("kmCode");
            	String ret = "";
                try {
                	ret = FSBean.getAddData(kmCode);          	
                	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
                		ret=ret.substring(0,ret.length()-1);
                	
                	}
                	PrintWriter out =  resp.getWriter();
                	out.write(ret);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }else if ("updata".equals(action)){
            	String fkmCode = req.getParameter("fkmcode");
            	String kmCode = req.getParameter("kmCode");
            	String kmName = req.getParameter("kmName");
            	String kmgrade = req.getParameter("kmgrade");
            	int id =Integer.parseInt( req.getParameter("id"));
            	FinanceSubjectFromBean bean = new FinanceSubjectFromBean();
            	bean.setId(id);
            	bean.setKmcode(kmCode);
            	bean.setKmname(kmName);
            	bean.setKmgrade(kmgrade);
            	bean.setFkmcode(fkmCode);
            	String ret = FSBean.updateData(bean);
            }
        else if ("selOption".equals(action)){
        String ret = "";
            try {
            	if (fkmcode!=null){
            		ret = FSBean.getData(fkmcode);          	
            	}
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("selOption1".equals(action)){
        String ret = "";
            try {
            	if (fkmcode!=null){
            		ret = FSBean.getData1(fkmcode);          	
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
