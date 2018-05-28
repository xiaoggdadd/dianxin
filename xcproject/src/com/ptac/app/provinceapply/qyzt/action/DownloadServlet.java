package com.ptac.app.provinceapply.qyzt.action;

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

public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "application/x-msdownload";
	private Logger logger = Logger.getLogger(DownloadServlet.class);
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");//唯一标识id
		String bzw = req.getParameter("bzw");//标识位
		String filename ="数据文件.xls";
		String zdm = "";
		if("1".equals(bzw)||"2".equals(bzw)||"3".equals(bzw)){
			filename="字段修改申请附件.xls";
			zdm = "FJNR";
		}
		System.out.println("filename:"+filename);
		DownLoadUtil dao =new DownLoadUtil();
		try {
			if(dao != null) {
				List<String> errors = new ArrayList<String>();
				List<String> tmpPaths = dao.execute1(zdm, "QSKZ", "ID", id,errors,filename);
				if(errors.size()==0){
					resp.reset();
		            resp.setContentType(Content_type);
		            resp.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "utf-8") + "\"");
		           write(tmpPaths.get(0),resp);//将第一个文件作为最终汇总文件进行下载
				}
				else{
					req.setAttribute("statusInfo", errors);
					if("1".equals(bzw)){
						req.getRequestDispatcher("/web/zdqxkz/gbsqshenhe.jsp").forward(req, resp);
					}else if("2".equals(bzw)){
						req.getRequestDispatcher("/web/zdqxkz/qyztshenhe.jsp").forward(req, resp);
					}else if("3".equals(bzw)){
						req.getRequestDispatcher("/web/zdqxkz/gdfsshenhe.jsp").forward(req, resp);
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
