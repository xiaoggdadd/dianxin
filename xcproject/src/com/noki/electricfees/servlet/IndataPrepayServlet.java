package com.noki.electricfees.servlet;

import javax.servlet.http.HttpServlet;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Vector;
import java.net.URLEncoder;

import com.noki.daoruelectricfees.javabean.DaoruElectricfees;
import com.noki.electricfees.servlet.ReadFile;
import com.noki.electricfees.servlet.ReadFileFactory;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.util.Path;
import com.noki.util.WriteExcle;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class IndataPrepayServlet extends HttpServlet {
	public IndataPrepayServlet() {
	}

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void init() throws ServletException {
	}

	private String msg;
	private String url;
	private String sendUrl;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		Account account = (Account) session.getAttribute("account");
		String accountname = account.getAccountName();
		String loginId = account.getAccountId();
		String path = request.getContextPath();
		String id="";//格式判断传的空值没有作用
		url = path + "/web/msgdr.jsp";
		msg = "预付费批量导入失败！";
		String action = (String) request.getAttribute("action");
		if (null == action || "".equals(action)) {
			action = request.getParameter("action");
		}

		String filename = (String) request.getAttribute("filename");
		if (action.equals("indata")) { // 预付费信息导入
			try {
				Path pa = new Path();
				Path ppath = new Path();
				ppath.servletInitialize(getServletConfig().getServletContext());

				String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
				
				UploadDaoQxfj dao = new UploadDaoQxfj(account, id, filename);//表格版本格式判断类
			    boolean gs = dao.isUpload(filename);//表格版本格式判断方法
				
			
		   if(gs){//表格版本格式判断
				    	msg = "导入模板格式不正确，请在系统中下载模板！";
				    }else{
				    	ReadFile temp = new ReadFileFactory().getReadFile(filename);
						Vector content = temp.getContent(filename);
						InsertPrepay sellin = new InsertPrepay();
						// 主键生成
						CountForm cform = new CountForm();
						long a = new Date().getTime();
					
					if((content.size()-1)<=300){//条数判断	
						Vector wrong = sellin.getWrong(content, cform, accountname,
								loginId);
						long a1 = new Date().getTime();
						sellin.closeDb();
				    	if (!wrong.isEmpty()) {
				    		msg = "共 " + cform.getZg() + "  条。成功导入 " + cform.getCg()
								+ " 条！有 " + cform.getBcg() + "  条数据未导入！";
						WriteExcle wr = new WriteExcle();
						String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
						wr.Write(wrong, account.getAccountName()
							+ "预付费信息导入不成功的数据.xls", "预付费信息导入不成功的数据", "预付费信息导入不成功数据",
							32, dir2);
				    	} else {
				    		 if(cform.getCg()<=0){
				  	  				msg="导入模板在格式转换过程中被损坏，请在系统中下载模板";
				  	  			}else{
				  	  				msg = "导入情况：共导入 " + cform.getCg() + " 条！";
				  	  			}
				    	}
					}else{
			    	msg = "导入条数要小于300条，请修改导入条数！";
			    	
		    	}
		     }
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("msg", msg);
			session.setAttribute("url", path
					+ "/web/electricfees/prepaymentList.jsp");
			session.setAttribute("wfile", path + "/wrongdata/"
					+ account.getAccountName() + "预付费信息导入不成功的数据.xls");

			response.sendRedirect(url);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
