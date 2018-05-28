package com.noki.jtreport.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.jtreport.shi.JtReportBeanFactory;
import com.noki.jtreport.shi.download.javabean.ReportDao;
import com.noki.jtreport.shi.download.javabean.ReportDaoFactory;
import com.noki.mobi.common.Account;
/**
 * Title: �����ļ��ϴ�Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28
 */
public class JtReportServlet extends HttpServlet {
	private static final String Content_type = "application/x-msdownload";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String reportType = req.getParameter("reportType");
		
		
		String month = req.getParameter("monthUp");
		String accountmonth=req.getParameter("accountmonth");
		Account account = (Account) session.getAttribute("account");
		String action = req.getParameter("action");
		String modelName = new JtReportBeanFactory().getReportNameByReportType(reportType);//��Ҫ�����ı�������  ��Ҫ�޸�
		String modelUrl = getServletContext().getRealPath("/web/cx/"+modelName);
		String isModel = req.getParameter("isModel");//������  ���������� �ı�־λ
		System.out.println("shi--����ѡ������Ҫ���ص��Ǹ������ǰ̨valueֵ="+action);
		ReportDao dao = ReportDaoFactory.create(action);
		
		try {
			if(dao != null) {
				dao.init(modelName,modelUrl,account,month,accountmonth);
				String result = dao.execute(isModel);
				String status = result.substring(0,1);
				String tempFilePath = result.substring(1);
				if("1".equals(status)){//�ɹ�
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(modelName, "utf-8") + "\"");
		            write(tempFilePath,resp);//ɾ����ʱ·��������ļ�
				}
				else if("0".equals(status)){//ʧ��
					dao.delTempFile(tempFilePath);
					resp.sendRedirect("/energy/web/right.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void write(String path,HttpServletResponse resp){
		File file = new File(path);
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			ServletOutputStream servletOS = resp.getOutputStream();
			byte[] buff = new byte[1024];
			
            int readLength;
            while (((readLength = fis.read(buff)) != -1)) {
                servletOS.write(buff, 0, readLength);
            }
            
            servletOS.flush();
            servletOS.close();
			fis.close();
			
			file.delete();		//ɾ����ʱ�ļ�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
