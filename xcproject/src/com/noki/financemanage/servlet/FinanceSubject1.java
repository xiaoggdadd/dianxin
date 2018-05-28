package com.noki.financemanage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.financemanage.javabean.FinanceSubjectBean1;
import com.noki.financemanage.javabean.QuanchengbenFromBean;

public class FinanceSubject1 extends HttpServlet {



	/**
	 * Constructor of the object.
	 */
	public FinanceSubject1() {
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
         FinanceSubjectBean1 FSBean = new FinanceSubjectBean1();
        if ("select".equals(action)){
        	String bzw=req.getParameter("sz");
        	if(bzw==null){
        		bzw="";
        		}
        	else{
        		
        		bzw=" and bzw='"+bzw+"'";
        	}
            HttpSession session = req.getSession();
            String path = req.getContextPath();
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");;
            	String min = req.getParameter("minnum");
                ret =FSBean.getFinanceSubject1(fkmcode,max,min,bzw);
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("ajaxareaData".equals(action)){
        	String bzw = req.getParameter("sz");
        	if(bzw==null){
        		bzw="";
        	}else{
        		bzw=" and bzw='"+bzw+"'";
        		
        	}
            String ret = "";
            try {
            	if (fkmcode!=null){
                	String max = req.getParameter("maxnum");;
                	String min = req.getParameter("minnum");
            		ret = FSBean.getFinanceSubject1(fkmcode,max,min,bzw);          	
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
      	String bzw = req.getParameter("bzw");
      	System.out.println("±êÖ¾Î»£º111£º"+bzw);
      	System.out.println("areaCode£º111£º"+areaCode);
      	QuanchengbenFromBean bean = new QuanchengbenFromBean();
      	bean.setQcbcode(areaCode);
      	bean.setQcbname(areaName);
      	bean.setBzw("1");
    	  
    	  
        	//String fatherCode = req.getParameter("fatherCode");
        	//String areaCode = req.getParameter("areaCode");
        	//String areaName = req.getParameter("areaName");
        	//FinanceSubjectFromBean bean = new FinanceSubjectFromBean();
        	//bean.setKmcode(areaCode);
        	//bean.setKmname(areaName);
        	if (areaCode!=null && areaCode.length()==7){
        		bean.setQcbgrade("3");
        	}
        	if (areaCode!=null && areaCode.length()==6){
        		bean.setQcbgrade("1");
        	}
        	if (areaCode!=null && areaCode.length()==8){
        		bean.setQcbgrade("3");
        	}
        	if (areaCode!=null && areaCode.length()==14){
        		bean.setQcbgrade("4");
        	}
        	bean.setFqcbcode(fatherCode);
        	String ret = FSBean.insertData1(bean);
        	
        }else if ("del".equals(action)){
            	String kmCode = req.getParameter("idde");
            	String ret = "";
            	if (kmCode!=null){
            		ret=FSBean.delData1(kmCode);
            	}

            }else if ("updateSele".equals(action)){
            	String fqcbCode = req.getParameter("fqcbcode");
            	String qcbCode = req.getParameter("qcbCode");
            	String ret = "";
            	String id = req.getParameter("idd");
                try {
                	ret = FSBean.getAddData1(qcbCode,id);          	
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
            	String	bzw="1";
            	
            	String qcbgrade = req.getParameter("qcbgrade");
            	int id =Integer.parseInt( req.getParameter("id"));
            	//String fkmCode = req.getParameter("fkmcode");
            	//String kmCode = req.getParameter("kmCode");
            	//String kmName = req.getParameter("kmName");
            	//String kmgrade = req.getParameter("kmgrade");
            	//int id =Integer.parseInt( req.getParameter("id"));
            	QuanchengbenFromBean bean = new QuanchengbenFromBean();
            	bean.setId(id);
            	bean.setQcbcode(qcbCode);
            	bean.setQcbname(qcbName);
            	bean.setQcbgrade(qcbgrade);
            	bean.setFqcbcode(fqcbCode);
            	bean.setBzw(bzw);
            	String ret = FSBean.updateData1(bean);
            }
        else if ("selOption".equals(action)){
        String ret = "";
        String bzw=req.getParameter("sz");
        System.out.println("selOption: "+bzw);
        if(bzw==null){
    		bzw="";
    		}
        else{
        	bzw=" and bzw='"+bzw+"'";
        	
        }
            try {
            	if (fkmcode!=null){
            		ret = FSBean.getData1(fkmcode,bzw);          	
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
