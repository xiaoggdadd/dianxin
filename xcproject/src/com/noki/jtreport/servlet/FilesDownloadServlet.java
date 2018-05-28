package com.noki.jtreport.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jtreport.sheng.download.javabean.ReportDao;
import com.noki.jtreport.sheng.download.javabean.ReportDaoFactory;
import com.noki.jtreport.shi.FileDownload;
import com.noki.jtreport.shi.JtReportBeanFactory;

public class FilesDownloadServlet extends HttpServlet {

	private static final String Content_type = "application/x-msdownload";
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");//��Ҫ�����ı��������������ֵ
		String filename = req.getParameter("id");
		System.out.println("-filename-"+filename);
		try {
			FileDownload dao = new FileDownload(filename);
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(dao.getName(), "utf-8") + "\"");
					write(dao.getPath(),resp);//����һ���ļ���Ϊ���ջ����ļ���������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������ʱ�ļ��ĵ�һ���ļ�д��ǰ̨
	 * @param path
	 * @param resp
	 */
	public void write(String path,HttpServletResponse resp){
		System.out.println(path+"---------------");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			ServletOutputStream servletOS = resp.getOutputStream();
			byte[] buff = new byte[1024];
			
            int readLength;
            while (((readLength = fis.read(buff)) != -1)) {
                servletOS.write(buff, 0, readLength);
            }
            
            servletOS.flush();
            servletOS.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}



