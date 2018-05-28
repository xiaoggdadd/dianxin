package com.noki.biaogan.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.app.DlOcrServer;

public class CBUserDlImageMessageServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(CBUserDlImageMessageServlet.class.getName());
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		request.setCharacterEncoding("UTF-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getRealPath("/TTMobil_Web/temp_image");
		System.out.println("------------->"+path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		List<String> fileNames = new ArrayList<String>();
		try {
			// 可以上传多个文件
			@SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 如果是普通表单字段
					
				}
			}
			
			for(FileItem item : list){
				if (item.isFormField()) {
					
				}else {
					// 文件名
					String fileName = item.getName();
					String fileSuffix = fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length());
					if(fileName!=null&&!fileName.equals("")){
						if(fileSuffix.equals("jpg")||fileSuffix.equals("jpeg")){
							String _file_path = request.getRealPath("/");
							String uuid = UUID.randomUUID().toString().replaceAll("-", "");
							String file_path = _file_path+"dbimage\\"+uuid;
							String file_name = UUID.randomUUID().toString()+"."+fileSuffix;
							
							File file =new File(file_path);    
							//如果文件夹不存在则创建    
							if  (!file .exists()  && !file .isDirectory())      
							{       
							    file .mkdir();    
							} else   
							{  
								
							}  
							
							// 保存文件到本地文件夹
							OutputStream out = new FileOutputStream(new File(file_path,file_name));
							InputStream in = item.getInputStream();
							int length = 0;
							byte[] buf = new byte[1024];
							while ((length = in.read(buf)) != -1) {
								out.write(buf, 0, length);
							}
							in.close();
							out.close();
							//log.info(file_path+file_name);
							fileNames.add(file_path+"\\"+file_name);
						}
					}
				}
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		
//		for(int i=0;i<fileNames.size();i++){
//			String filename = fileNames.get(i);
//			log.info(filename);
//			Thread ocrThread = new Thread(new DlOcrServer(filename));
//			ocrThread.start();
//		}
		
		json.put("flag", true);
		json.put("message", "保存成功!");
		log.info(json.toString());
		response.getWriter().write(json.toString());
	}

}
