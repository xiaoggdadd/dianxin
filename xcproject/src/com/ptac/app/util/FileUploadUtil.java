package com.ptac.app.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {

	// 上传文件的保存路径
	private String configPath = "e:/saveFile/";
	// 临时文件路径
	private String dirTemp = "temp/";

	public File upload(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (isMultipart) {

			// 文件保存目录路径
			String savePath = configPath;
			// 临时文件目录
			String tempPath = req.getSession().getServletContext().getRealPath("/") + dirTemp;
			System.out.println("文件上传临时目录" + tempPath);
			// 创建文件夹
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// 创建临时文件夹
			File dirTempFile = new File(tempPath);
			if (!dirTempFile.exists()) {
				dirTempFile.mkdirs();
			}

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(tempPath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(30 * 1024 * 1024);// 30M
			upload.setSizeMax(50 * 1024 * 1024); // 50M
			List items = new ArrayList();
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				e.printStackTrace();
				System.out.println("文件上传发生错误" + e.getMessage());
			}
			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (fileItem.isFormField()) {
					// 普通表单x
					System.out.println(fileItem.getFieldName() + "|" + fileItem.getName() + "|"
							+ new String(fileItem.getString().getBytes("iso8859-1"), "utf-8"));
				} else {
					System.out.println(fileItem.getFieldName() + "|" + fileItem.getName() + "|"
							+ fileItem.isInMemory() + "| " + fileItem.getContentType() + "|"
							+ fileItem.getSize());
					File uploadfile = new File(savePath, fileItem.getName());
					fileItem.write(uploadfile);

					return uploadfile;
				}
			}
		}
		return null;
	}
}