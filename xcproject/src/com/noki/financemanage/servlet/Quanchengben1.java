package com.noki.financemanage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.financemanage.javabean.QuanchengbenBean1;
import com.noki.financemanage.javabean.QuanchengbenFromBean;

public class Quanchengben1 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Quanchengben1() {
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
    	//String sz = req.getParameter("sz");
    	//System.out.println("11111"+sz);
         QuanchengbenBean1 FSBean = new QuanchengbenBean1();
        if ("select".equals(action)){
        	String sz=req.getParameter("sz");
        	if(sz==null){
        		sz="";
        	}
       	System.out.println("select:"+sz);
            HttpSession session = req.getSession();
            String path = req.getContextPath();
            String ret = "";
            try {
            	String max = req.getParameter("maxnum");;
            	String min = req.getParameter("minnum");
                ret =FSBean.getQuanchengben1(fqcbcode,max,min,sz);
            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
            		ret=ret.substring(0,ret.length()-1);
            	
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else if ("ajaxareaData".equals(action)){
        	String sz = req.getParameter("sz");
        	if(sz==null){
        		sz="";
        	}else{
        		sz=" and bzw='"+sz+"'";
        		
        	}
        	System.out.println("ajaxareaData:"+sz);
            String ret = "";
            try {
            	if (fqcbcode!=null){
                	String max = req.getParameter("maxnum");;
                	String min = req.getParameter("minnum");
            		ret = FSBean.getQuanchengben1(fqcbcode,max,min,sz);          	
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
        	bean.setBzw(bzw);
        	if (areaCode!=null && areaCode.length()==2){
        		bean.setQcbgrade("5");
        	}
        	if (areaCode!=null && areaCode.length()==5){
        		bean.setQcbgrade("2");
        	}
        	if (areaCode!=null && areaCode.length()==6){
        		bean.setQcbgrade("5");
        	}
        	if (areaCode!=null && areaCode.length()==7){
        		bean.setQcbgrade("3");
        	}
        	if (areaCode!=null && areaCode.length()==9){
        		bean.setQcbgrade("4");
        	}
        	if (areaCode!=null && areaCode.length()==6){
        		bean.setQcbgrade("1");
        	}
        	bean.setFqcbcode(fatherCode);
        	String ret = FSBean.insertData1(bean);
        	
        }else if ("del".equals(action)){
            	String qcbCode = req.getParameter("id");
            	String ret = "";
            	if (qcbCode!=null){
            		ret=FSBean.delData1(qcbCode);
            	}

            }else if ("updateSele".equals(action)){
            	String fqcbCode = req.getParameter("fqcbCode");
            	String qcbCode = req.getParameter("qcbCode");
            	String id = req.getParameter("idd");
            	System.out.println("8888:"+id);
            	String ret = "";
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
            	String	bzw="";
            	if(qcbCode.length()==6){
            	bzw="1";
            	}if(qcbCode.length()==2){
            		bzw="2";
            	}
            	String qcbgrade = req.getParameter("qcbgrade");
            	int id =Integer.parseInt( req.getParameter("id"));
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
        	 String bzw=req.getParameter("sz");
        	 String qcb=req.getParameter("qcbcode");
             System.out.println("selOption: "+bzw);
             if(bzw==null){
         		bzw="";
         		}
             else{
             	bzw=" and bzw='"+bzw+"'";
             	
             }
        String ret = "";
            try {
            	if (fqcbcode!=null){
            		ret = FSBean.getData1(fqcbcode,bzw);          	
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
