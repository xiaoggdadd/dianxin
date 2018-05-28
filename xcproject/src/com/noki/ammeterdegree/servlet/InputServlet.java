package com.noki.ammeterdegree.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.noki.mobi.common.Account;
import com.noki.util.Path;

public class InputServlet extends HttpServlet {
	public InputServlet() {
	}

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void init() throws ServletException {
	}

	private String msg;
	private String url;
	private String sendUrl;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		url = path + "/web/msgdr.jsp";
		try {
			this.upload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "�������ݵ���ɹ���";
		// session.setAttribute("msg",msg);
		// session.setAttribute("url",url);
		// response.sendRedirect(sendUrl);
		String servletName = request.getParameter("servletname");
		request.setAttribute("action", "indata");

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/servlet/indatadegree");
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("/servlet/sellmantype.do");
		dispatcher.forward(request, response);
	}


	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		// String store = "";
		ArrayList list = new ArrayList();
		DiskFileUpload fu = new DiskFileUpload();
		// ���������û��ϴ��ļ���С,��λ:�ֽڣ�������Ϊ2m
		fu.setSizeMax(5 * 1024 * 1024);
		// �������ֻ�������ڴ��д洢������,��λ:�ֽ�
		fu.setSizeThreshold(4096);
		// ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼
		Path path = new Path();
		path.servletInitialize(getServletConfig().getServletContext());
		String dir1 = path.getPhysicalPath("/indata/", 0); // ������
		fu.setRepositoryPath(dir1);
		// ��ʼ��ȡ�ϴ���Ϣ
		List fileItems = fu.parseRequest(request);
		// ���δ���ÿ���ϴ����ļ�
		Iterator iter = fileItems.iterator();
		// ����ƥ�䣬����·��ȡ�ļ���
		// String regExp = ".+\\\\(.+)$";
		// ���˵����ļ�����
		// String[] errorType={".exe",".com",".cgi",".asp"};
		// Pattern p = Pattern.compile(regExp);
		String zipname = null;
		// int num = 0;
		File file = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			System.out.println("item" + item.toString());
			// �������������ļ�������б���Ϣ
			if (!item.isFormField()) {
				// Ӳ���뷽ʽ����ϴ��ļ�����
				String name = new String(item.getName().getBytes(), "UTF-8");
				String extName = name.substring(name.lastIndexOf("\\") + 1);
				System.out.println("�ϴ��ĵ��Excel��" + extName);
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0) {
					continue;
				}
				if (true) {
					try {
						// �����ϴ����ļ���ָ����Ŀ¼
						String fileType = ".xls";
						zipname = extName;
						session.setAttribute("filename", dir1 + zipname);
						request.setAttribute("filename", dir1 + zipname);
						file = new File(dir1 + zipname);
						item.write(file);
					} catch (Exception e) {
						// out.println(e);
						e.printStackTrace();
					}

				}
			}
		}
	}
	/**
	 * 
	 * �쳣��ת ����error 
	 * @param request
	 * @param response
	 * @param statusInfo
	 * @param bz
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response, List<?> statusInfo, String bz) {
		request.setAttribute("statusInfo", statusInfo);
		try {
			if ("upload".equals(bz) || bz == "upload") {
				request.getRequestDispatcher("web/dianliang/input.jsp").forward(request, response);
			} else if ("modifyzd".equals(bz) || bz == "modifyzd") {
				request.getRequestDispatcher("web/dianliang/input.jsp").forward(request, response);
			} else if ("zg".equals(bz)) {
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("web/dianliang/input.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
