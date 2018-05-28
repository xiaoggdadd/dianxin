package com.noki.chaobiaorenyuan.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.chaobiaorenyuan.Dao.uploadDao;

public class deleteFile extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    //����DAO����
	    uploadDao dao = new uploadDao();
	    
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//��ȡҪɾ�����ļ����ƺ�ID
		String wjid  = (String)request.getParameter("wenjianid");
		String wjName  = (String)request.getParameter("wenjianname");
		System.out.println("��ȡɾ���ļ�������ID"+wjid);
		String savePath = dao.filebaocun();	//�����ݿ�ȡ��·��
		
		String s = savePath+"\\"+wjName;// �ļ��ľ���·��
		File file = new File(s);
		System.out.println(s);
		if (file.exists()) {
			boolean d = file.delete();
			if (d) {
				System.out.print("�ļ�ɾ���ɹ���");
				//ɾ�����ݿ��ֶ�
				int jieguo = dao.delect(wjid);
			    if(jieguo > 0){
			    	System.out.println("���ݿ��ֶ�ɾ���ɹ�");
			    }else{
			    	System.out.println("���ݿ��ֶ�ɾ��ʧ��");
			    }
			} else {
				System.out.print("ɾ��ʧ�ܣ�");
			}
		}
	}

}
