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

	// �ϴ��ļ��ı���·��
	private String configPath = "e:/saveFile/";
	// ��ʱ�ļ�·��
	private String dirTemp = "temp/";

	public File upload(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (isMultipart) {

			// �ļ�����Ŀ¼·��
			String savePath = configPath;
			// ��ʱ�ļ�Ŀ¼
			String tempPath = req.getSession().getServletContext().getRealPath("/") + dirTemp;
			System.out.println("�ļ��ϴ���ʱĿ¼" + tempPath);
			// �����ļ���
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// ������ʱ�ļ���
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
				System.out.println("�ļ��ϴ���������" + e.getMessage());
			}
			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (fileItem.isFormField()) {
					// ��ͨ��x
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