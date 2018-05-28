package com.noki.newfunction.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.jtreport.sheng.download.javabean.ReportDao;
import com.noki.jtreport.sheng.download.javabean.ReportDao1;
import com.noki.jtreport.shi.JtReportBeanFactory;

import com.noki.jtreport.shi.download.javabean.ReportDaoFactory;
import com.noki.mobi.common.Account;
/**
 * Title: 数据文件上传Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28
 */
public class Cbzdxiazai extends HttpServlet {
	private static final String Content_type = "application/x-msdownload";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		req.setCharacterEncoding("utf-8");
		//String month = req.getParameter("actualmonth");
		//String[] city = req.getParameterValues("shi");
		//String filename = req.getParameter("filename");
		//String modelUrl = getServletContext().getRealPath("/web/cx/"+filename);
		String id = req.getParameter("id");
		CbzdDao dao =new CbzdDao();
		DataBase db=new DataBase();
		try {
			if(dao != null) {
				dao.init(id);
				List<String> errors = new ArrayList<String>();
				System.out.println("11111111111111");
				List<String> tmpPaths = dao.execute1(errors);
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(id, "utf-8") + "\"");
					write(tmpPaths.get(0),resp);//将第一个文件作为最终汇总文件进行下载
				}
				else{
					req.setAttribute("errors", errors);
					req.getRequestDispatcher("/web/jzcbnewfunction/cbzdshishenhe.jsp").forward(req, resp);
				}
				dao.delTempFile(tmpPaths);
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
			
			file.delete();		//删除临时文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
