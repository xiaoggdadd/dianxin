package com.noki.zwhd.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelModelDownload extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRealPath("/web/wyftdfsh/downloadExcels");
		String fileType = request.getParameter("templetname");
		
		String filename = "";
		if(fileType.equals("wydfft")){
			filename = "物业数据电费分摊模版";
		}else if(fileType.equals("cwybx")){
			filename = "财务数据已报销模版";
		}else if(fileType.equals("lt")){
			filename = "联通电费单模版";
		}

		// 设置文件MIME类型
		response.setContentType(getServletContext().getMimeType(filename));
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename);

		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String outFileName = filename;
		if (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) {// IE
			outFileName = URLEncoder.encode(outFileName, "UTF-8");
			outFileName = outFileName.replace("+", "%20");// 处理空格变“+”的问题
		} else {// FF
			outFileName = new String(outFileName.getBytes("UTF-8"),
					"iso-8859-1");
		}

		response.setHeader("Content-disposition", "attachment;filename="
				+ outFileName + ".xlsx");
		String filePath = path + "/" + filename + ".xlsx";
		System.out.println(filePath);
		InputStream in = new FileInputStream(filePath);
		OutputStream out = response.getOutputStream();

		// 写文件
		int b;
		while ((b = in.read()) != -1){
			out.write(b);
		}

		in.close();
		out.close();
	}
}
