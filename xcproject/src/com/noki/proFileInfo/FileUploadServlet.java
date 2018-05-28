package com.noki.proFileInfo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

	private String uploadPath = "e:\\temp"; // 上传文件的目录
    File tempPathFile;

	/**
	 * Constructor of the object.
	 */
	public FileUploadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 try {
		 DiskFileItemFactory factory = new DiskFileItemFactory();
         // Set factory constraints
         factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
         factory.setRepository(tempPathFile);// 设置缓冲区目录
         // Create a new file upload handler
         ServletFileUpload upload = new ServletFileUpload(factory);
         // Set overall request size constraint
         upload.setSizeMax(41943040); // 设置最大文件尺寸，这里是4MB
         List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
         Iterator<FileItem> i = items.iterator();
         while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            String fileName = fi.getName();
            if (fileName != null) {
                File fullFile = new File(fi.getName());
                File savedFile = new File(uploadPath, fullFile.getName());
                fi.write(savedFile);
            }
         }
         System.out.print("upload succeed");
     } catch (Exception e) {
         // 可以跳转出错页面
         e.printStackTrace();
     }
}

	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	    this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
	       if (!uploadFile.exists()) {
	           uploadFile.mkdirs();
	       }
	    }
	

}
