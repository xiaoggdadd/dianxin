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
		
		if(null == confirm){//文件开始上传
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);								
	        factory.setRepository(tempfile);
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//文件上传大小限制为10M
	        
	        List<String> statusInfo = new ArrayList<String>();//错误信息
	        List<?> fileItems = null;
	        try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				statusInfo.add("上传文件超出大小限制（10M）");
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
						statusInfo.add("文件上传成功！");
					}
				}
			}
			
			sendStatusInfo(request,response,statusInfo);
		}
		else{//判断文件是否可以上传
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";
			out.println(result);
			out.flush();
			out.close();
		}
	}

	/**
	 * 将执行信息发送至当前页面
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
