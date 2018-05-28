package com.ptac.app.priceover.countysigncheck.action;

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

import jxl.common.Logger;

import com.ptac.app.priceover.countysigncheck.dao.DownLoadUtil;

public class DownLoadAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "application/x-msdownload";
	private Logger logger = Logger.getLogger(DownLoadAction.class);
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String pid = req.getParameter("wyid");//唯一标识id
		String bzw = req.getParameter("bzw");//标识位
		String filename ="数据文件.xls";
		String zdm = "";
		if(bzw.equals("1")){
			filename="用电合同附件.xls";
			zdm = "YDHTFJ";
		}else if(bzw.equals("2")){
			filename="忙时增益依据.xls";
			zdm = "MSZYYJ";
		}else if(bzw.equals("3")){
			filename="业主尖峰电量明细.xls";
			zdm = "YZJFDLMX";
		}
		System.out.println("filename:"+filename);
		DownLoadUtil dao =new DownLoadUtil();
		try {
			if(dao != null) {
				List<String> errors = new ArrayList<String>();
				List<String> tmpPaths = dao.execute1(zdm, "PRICEPROCEDURE", "ID", pid,errors,filename);
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "utf-8") + "\"");
		           write(tmpPaths.get(0),resp);//将第一个文件作为最终汇总文件进行下载
				}
				else{
					req.setAttribute("statusInfo", errors);
					if(bzw.equals("1")|| bzw.equals("2") || bzw.equals("3")){
						req.getRequestDispatcher("/web/appJSP/priceover/countysigncheck/check.jsp").forward(req, resp);
					}
				}
				dao.delTempFile(tmpPaths);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("附件下载出错:"+e.getMessage());
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
			logger.error("附件下载输出文件到前台出错:"+e.getMessage());
		}
	}
}
