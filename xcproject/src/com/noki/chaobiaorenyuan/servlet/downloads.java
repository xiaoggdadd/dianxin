package com.noki.chaobiaorenyuan.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.chaobiaorenyuan.Dao.uploadDao;

public class downloads extends HttpServlet {	//��δʹ��

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

		this.doPost(request, response);
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
	    uploadDao dao = new uploadDao();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
        // �õ�Ҫ���ص��ļ���
        String fileName = request.getParameter("wenjianname"); 
        fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
        
        String fileSaveRootPath = dao.filebaocun();	//�����ݿ�ȡ��·��
        // �õ�Ҫ���ص��ļ�
        File file = new File(fileSaveRootPath + "\\" + fileName);
        // ����ļ�������
        if (!file.exists()) {
        	System.out.println("�ļ������ڣ�");
            return;
        }
        // �����ļ���(��ԭ�ļ�����)
        String realname = fileName.substring(fileName.indexOf("_") + 1);
        // ������Ӧͷ��������������ظ��ļ�
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        // ��ȡҪ���ص��ļ������浽�ļ�������
        FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
        // ���������
        OutputStream out = response.getOutputStream();
        // ����������
        byte buffer[] = new byte[1024];
        int len = 0;
        // ѭ�����������е����ݶ�ȡ������������
        while ((len = in.read(buffer)) > 0) {
            // ��������������ݵ��������ʵ���ļ�����
            out.write(buffer, 0, len);
        }
        // �ر��ļ�������
        in.close();
        // �ر������
        out.close();
	}

}
