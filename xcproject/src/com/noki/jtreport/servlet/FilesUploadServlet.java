package com.noki.jtreport.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.jtreport.shi.FilesUpload;
import com.noki.mobi.common.Account;
@SuppressWarnings("serial")
public class FilesUploadServlet extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		Account account = (Account)(request.getSession().getAttribute("account"));
		
		String month = request.getParameter("month");
		String confirm = request.getParameter("confirm");
		System.out.println("----");
		
		FilesUpload dao = new FilesUpload(account,month);
		
		if(null == confirm){//�ļ���ʼ�ϴ�
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);								
	        factory.setRepository(tempfile);
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//�ļ��ϴ���С����Ϊ10M
	        
	        List<String> statusInfo = new ArrayList<String>();//������Ϣ
	        List<?> fileItems = null;
	        try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
				sendStatusInfo(request,response,statusInfo);
				return;
			}
			
			
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				if(!file.isFormField()){
					if(statusInfo.size()==0){
						try {
							String path = getServletContext().getRealPath("/aaa");
							// path = "z://aaa";
							 System.out.println("--"+path);
							String name = file.getName();
							//int ii=name.indexOf(".");
							//long time=new Date().getTime();
							//name=name.substring(0,ii)+time+name.substring(ii);
							System.out.println("name--"+name);
								file.write(new File(path+"/"+name));
									dao.insertToDB(path,name);
								} catch (Exception e) {
									e.printStackTrace();
							}
						statusInfo.add("�ļ��ϴ��ɹ���");
					}
				}
			}
			
			sendStatusInfo(request,response,statusInfo);
		}
		else{//�ж��ļ��Ƿ�����ϴ�
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";
			out.println(result);
			out.flush();
			out.close();
		}
	}

	/**
	 * ��ִ����Ϣ��������ǰҳ��
	 * @param request
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo){
		request.setAttribute("statusInfo", statusInfo);
		try {
			request.getRequestDispatcher("/web/cx/filesupload.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
