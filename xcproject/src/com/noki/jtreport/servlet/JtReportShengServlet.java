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
		System.out.println("ģ���ʾ..."+reportType);
		String action = req.getParameter("action");//��Ҫ�����ı��������������ֵ
		String month = req.getParameter("month");
		//ȡ��ѡ��ĳ���...
		String[] city = req.getParameterValues("city");
		System.out.println("ѡ�����..."+city);
		String modelName = new JtReportBeanFactory().getReportNameByReportType(reportType);//��Ҫ�����ı�������
		String modelUrl = getServletContext().getRealPath("/web/cx/"+modelName);
		ReportDao dao = ReportDaoFactory.create(action);//����һ������   ��ִ��
		try {
			if(dao != null) {
				dao.init(modelName,month,city,modelUrl);
				List<String> errors = new ArrayList<String>();
				List<String> tmpPaths = dao.execute(errors);
				//��ѯ �Ƿ��з��������ı���    ������ó���   �ŵ�������  ��û�з�����ʾ��Ϣ
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(modelName, "utf-8") + "\"");
					write(tmpPaths.get(0),resp);//����һ���ļ���Ϊ���ջ����ļ���������
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
	 * ��������ʱ�ļ��ĵ�һ���ļ�д��ǰ̨
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
