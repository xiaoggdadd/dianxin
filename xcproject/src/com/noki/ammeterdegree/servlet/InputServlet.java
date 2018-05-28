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
		msg = "电量数据导入成功！";
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
		// 设置允许用户上传文件大小,单位:字节，这里设为2m
		fu.setSizeMax(5 * 1024 * 1024);
		// 设置最多只允许在内存中存储的数据,单位:字节
		fu.setSizeThreshold(4096);
		// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		Path path = new Path();
		path.servletInitialize(getServletConfig().getServletContext());
		String dir1 = path.getPhysicalPath("/indata/", 0); // 传参数
		fu.setRepositoryPath(dir1);
		// 开始读取上传信息
		List fileItems = fu.parseRequest(request);
		// 依次处理每个上传的文件
		Iterator iter = fileItems.iterator();
		// 正则匹配，过滤路径取文件名
		// String regExp = ".+\\\\(.+)$";
		// 过滤掉的文件类型
		// String[] errorType={".exe",".com",".cgi",".asp"};
		// Pattern p = Pattern.compile(regExp);
		String zipname = null;
		// int num = 0;
		File file = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			System.out.println("item" + item.toString());
			// 忽略其他不是文件域的所有表单信息
			if (!item.isFormField()) {
				// 硬编码方式解决上传文件乱码
				String name = new String(item.getName().getBytes(), "UTF-8");
				String extName = name.substring(name.lastIndexOf("\\") + 1);
				System.out.println("上传的电表Excel：" + extName);
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0) {
					continue;
				}
				if (true) {
					try {
						// 保存上传的文件到指定的目录
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
	 * 异常跳转 回显error 
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
