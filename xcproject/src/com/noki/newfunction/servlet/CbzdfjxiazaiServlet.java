package com.noki.newfunction.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jtreport.shi.FileDownload;
import com.noki.newfunction.dao.CbzdReportDao1;

/**
 * Title: 数据文件上传Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28
 */
public class CbzdfjxiazaiServlet extends HttpServlet {
	private static final String Content_type = "application/x-msdownload";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");//站点唯一标识id
		String bzw = req.getParameter("bzw");//
		//String zdname = req.getParameter("zdname");//站点名称
		//String accountmonth = req.getParameter("accountmonth");//报账月份（对标月份）
		CbzdReportDao1 dao =new CbzdReportDao1();
		try {
				CbzdReportDao1 dao1 = dao.getFuJianPath(id,bzw);
				resp.reset();
	            resp.setContentType(Content_type);
	            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(dao1.getName(), "utf-8") + "\"");
				write(dao1.getUrl(),resp);//将第一个文件作为最终汇总文件进行下载
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将所有临时文件的第一个文件写入前台
	 * @param path
	 * @param resp
	 */
	public void write(String path,HttpServletResponse resp){
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
