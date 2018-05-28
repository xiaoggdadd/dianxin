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
 * Title: �����ļ��ϴ�Action Copyright: Copyright (c) 2010 Company: CVIC SE
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
		String id = req.getParameter("id");//վ��Ψһ��ʶid
		String bzw = req.getParameter("bzw");//��ʶλ
		//String filename = req.getParameter("sjfilename");//�ļ�����
		String filename ="�����ļ�.xls";
		if(bzw.equals("2")){
			filename="���ز�����������.xls";
			
		}else if(bzw.equals("4")){
			filename="�������ĸ���.xls";
		}else if(bzw.equals("5")){
			filename="�ֶ��޸�����ģ��.xls";
		}else if(bzw.equals("6")){
			filename="�ֶ��޸����븽��.xls";
		}
		System.out.println("filename:"+filename);
		String modelUrl = getServletContext().getRealPath("/web/jzcbnewfunction/"+filename);//ģ��·��
		CbzdReportDao1 dao =new CbzdReportDao1();
		DataBase db=new DataBase();
		String accountmonth = req.getParameter("accountmonth");//�·�
		String[] city = null;//����ռλ
		try {
			if(dao != null) {
				dao.init(filename,accountmonth,city,modelUrl);
				List<String> errors = new ArrayList<String>();
				List<String> tmpPaths = dao.execute1(errors,id,filename,bzw);
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "utf-8") + "\"");
					write(tmpPaths.get(0),resp);//����һ���ļ���Ϊ���ջ����ļ���������
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
			
			file.delete();		//ɾ����ʱ�ļ�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
