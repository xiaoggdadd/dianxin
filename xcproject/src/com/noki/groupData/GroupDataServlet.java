package com.noki.groupData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.imports.ImportsBean;
import com.noki.util.Path;

public class GroupDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	   public void doPost(HttpServletRequest req, HttpServletResponse resp){
	        String action = req.getParameter("action");
	        resp.setContentType(Content_type);
	        GroupDataBean bean = new GroupDataBean();
            String ret = "";
            String path = req.getRealPath("/exceltemplet");
            //基站用电量汇总分析表
	        if ("getSelData".equals(action)){
                String shi  = req.getParameter("shi");
                if ("0".equals(shi)){
                	shi = "";
                }
                String yearMonth  = req.getParameter("yearMonth");
	            try {
	            	ret = bean.getSelData(shi,yearMonth,path);  

	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	        }
	        //通信局房用电量汇总表
	        if ("getTxjData".equals(action)){
                String shi  = req.getParameter("shi");
                if ("0".equals(shi)){
                	shi = "";
                }
                String yearMonth  = req.getParameter("yearMonth");
	            try {
	            	ret = bean.getSelData(shi,yearMonth,path);  

	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	        }
	        //下载
            try {
            	File f = new File(ret);
            	if(!f.exists()){
            		resp.sendError(404,"File not found!");
            	return;
            	}
            	BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
            	byte[] buf = new byte[1024];
            	int len = 0;

            	resp.reset(); //非常重要
            	if(false){ //在线打开方式
            	URL u = new URL("file:///"+ret);
            	resp.setContentType(u.openConnection().getContentType());
            	resp.setHeader("Content-Disposition", "inline; filename="+f.getName());
            	//文件名应该编码成UTF-8
            	}
            	else{ //纯下载方式
            		resp.setContentType("application/x-msdownload;charset=GB2312"); 

            		resp.setHeader("Content-Disposition", "attachment; filename=" +toUtf8String(f.getName())); 
            	}
            	OutputStream out = resp.getOutputStream();
            	while((len = br.read(buf)) >0)
            	out.write(buf,0,len);
            	out.flush();
            	br.close();
            	out.close();

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }
	    public void doGet(HttpServletRequest req, HttpServletResponse resp){
	    	this.doPost(req, resp);
	    }
		public String toUtf8String(String s) {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes("utf-8");
					} catch (Exception ex) {
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
			return sb.toString();
		}
}
