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
		String id="";//��ʽ�жϴ��Ŀ�ֵû������
		url = path + "/web/msgdr.jsp";
		msg = "Ԥ������������ʧ�ܣ�";
		String action = (String) request.getAttribute("action");
		if (null == action || "".equals(action)) {
			action = request.getParameter("action");
		}

		String filename = (String) request.getAttribute("filename");
		if (action.equals("indata")) { // Ԥ������Ϣ����
			try {
				Path pa = new Path();
				Path ppath = new Path();
				ppath.servletInitialize(getServletConfig().getServletContext());

				String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
				
				UploadDaoQxfj dao = new UploadDaoQxfj(account, id, filename);//���汾��ʽ�ж���
			    boolean gs = dao.isUpload(filename);//���汾��ʽ�жϷ���
				
			
		   if(gs){//���汾��ʽ�ж�
				    	msg = "����ģ���ʽ����ȷ������ϵͳ������ģ�壡";
				    }else{
				    	ReadFile temp = new ReadFileFactory().getReadFile(filename);
						Vector content = temp.getContent(filename);
						InsertPrepay sellin = new InsertPrepay();
						// ��������
						CountForm cform = new CountForm();
						long a = new Date().getTime();
					
					if((content.size()-1)<=300){//�����ж�	
						Vector wrong = sellin.getWrong(content, cform, accountname,
								loginId);
						long a1 = new Date().getTime();
						sellin.closeDb();
				    	if (!wrong.isEmpty()) {
				    		msg = "�� " + cform.getZg() + "  �����ɹ����� " + cform.getCg()
								+ " ������ " + cform.getBcg() + "  ������δ���룡";
						WriteExcle wr = new WriteExcle();
						String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
						wr.Write(wrong, account.getAccountName()
							+ "Ԥ������Ϣ���벻�ɹ�������.xls", "Ԥ������Ϣ���벻�ɹ�������", "Ԥ������Ϣ���벻�ɹ�����",
							32, dir2);
				    	} else {
				    		 if(cform.getCg()<=0){
				  	  				msg="����ģ���ڸ�ʽת�������б��𻵣�����ϵͳ������ģ��";
				  	  			}else{
				  	  				msg = "��������������� " + cform.getCg() + " ����";
				  	  			}
				    	}
					}else{
			    	msg = "��������ҪС��300�������޸ĵ���������";
			    	
		    	}
		     }
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("msg", msg);
			session.setAttribute("url", path
					+ "/web/electricfees/prepaymentList.jsp");
			session.setAttribute("wfile", path + "/wrongdata/"
					+ account.getAccountName() + "Ԥ������Ϣ���벻�ɹ�������.xls");

			response.sendRedirect(url);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
