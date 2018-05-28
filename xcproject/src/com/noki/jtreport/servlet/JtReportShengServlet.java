package com.noki.jtreport.servlet;

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

import com.noki.jtreport.sheng.download.javabean.ReportDao;
import com.noki.jtreport.sheng.download.javabean.ReportDaoFactory;
import com.noki.jtreport.shi.JtReportBeanFactory;

public class JtReportShengServlet extends HttpServlet {
	private static final String Content_type = "application/x-msdownload";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		req.setCharacterEncoding("utf-8");
		String reportType = req.getParameter("reportType");
		System.out.println("模板表示..."+reportType);
		String action = req.getParameter("action");//将要导出的报表类型所代表的值
		String month = req.getParameter("month");
		//取出选择的城市...
		String[] city = req.getParameterValues("city");
		System.out.println("选择城市..."+city);
		String modelName = new JtReportBeanFactory().getReportNameByReportType(reportType);//将要导出的报表名称
		String modelUrl = getServletContext().getRealPath("/web/cx/"+modelName);
		ReportDao dao = ReportDaoFactory.create(action);//返回一个方法   不执行
		try {
			if(dao != null) {
				dao.init(modelName,month,city,modelUrl);
				List<String> errors = new ArrayList<String>();
				List<String> tmpPaths = dao.execute(errors);
				//查询 是否有符合条件的报表    如果有拿出来   放到报表里  ，没有返回提示信息
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(modelName, "utf-8") + "\"");
					write(tmpPaths.get(0),resp);//将第一个文件作为最终汇总文件进行下载
				}
				else{
					req.setAttribute("errors", errors);
					req.getRequestDispatcher("/web/cx/groupdata_sheng.jsp").forward(req, resp);
				}
				dao.delTempFile(tmpPaths);
			}
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
