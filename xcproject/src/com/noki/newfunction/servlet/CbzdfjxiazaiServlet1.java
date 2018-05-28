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

import com.noki.database.DataBase;
import com.noki.jtreport.shi.FileDownload;
import com.noki.newfunction.dao.CbzdReportDao1;

/**
 * Title: 数据文件上传Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28
 */
public class CbzdfjxiazaiServlet1 extends HttpServlet {
	private static final String Content_type = "application/x-msdownload";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");//站点唯一标识id
		String bzw = req.getParameter("bzw");//标识位
		//String filename = req.getParameter("sjfilename");//文件名称
		String filename ="数据文件.xls";
		if(bzw.equals("2")){
			filename="区县测试描述附件.xls";
			
		}else if(bzw.equals("4")){
			filename="区县整改附件.xls";
		}else if(bzw.equals("5")){
			filename="字段修改申请模板.xls";
		}else if(bzw.equals("6")){
			filename="字段修改申请附件.xls";
		}
		System.out.println("filename:"+filename);
		String modelUrl = getServletContext().getRealPath("/web/jzcbnewfunction/"+filename);//模板路径
		CbzdReportDao1 dao =new CbzdReportDao1();
		DataBase db=new DataBase();
		String accountmonth = req.getParameter("accountmonth");//月份
		String[] city = null;//参数占位
		try {
			if(dao != null) {
				dao.init(filename,accountmonth,city,modelUrl);
				List<String> errors = new ArrayList<String>();
				List<String> tmpPaths = dao.execute1(errors,id,filename,bzw);
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "utf-8") + "\"");
					write(tmpPaths.get(0),resp);//将第一个文件作为最终汇总文件进行下载
				}
				else{
					req.setAttribute("errors", errors);
					if(bzw.equals("2")){
						req.getRequestDispatcher("/web/jzcbnewfunction/shishxx.jsp?zdid="+id+"").forward(req, resp);
						
					}else if(bzw.equals("4")){
						req.getRequestDispatcher("/web/jzcbnewfunction/xfsjxx.jsp?zdid="+id+"").forward(req, resp);
					}
					
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
