package com.noki.util;

/**
 * <p>Title:Path </p>
 * <p>Description: 文件路径的处理</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company:ldsoft </p>
 * @author xuyw
 * @version 1.0
 */

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

public class Path {

	protected ServletContext m_application;// 应用环境

	private boolean m_denyPhysicalPath;// 物理路径

	public Path() {

	}

	/**
	 * jsp中初始化应用环境
	 * 
	 * @param pageContext
	 * @throws ServletException
	 */
	public final void jspInitialize(PageContext pageContext)
			throws ServletException {
		m_application = pageContext.getServletContext();

	}

	/**
	 * servlet中初始化应用环境
	 * 
	 * @param servletContext
	 * @throws ServletException
	 */
	public final void servletInitialize(ServletContext servletContext)
			throws ServletException {
		m_application = servletContext;

	}

	/**
	 * 得到文件的物理路径
	 * 
	 * @param filePathName
	 *            应用环境中的路径
	 * @param option
	 * @return
	 * @throws IOException
	 */
	public String getPhysicalPath(String filePathName, int option)
			throws IOException {
		String path = new String();
		String fileName = new String();
		String fileSeparator = new String();
		boolean isPhysical = false;
		fileSeparator = System.getProperty("file.separator");
		if (filePathName == null)
			throw new IllegalArgumentException(
					"There is no specified destination file (1140).");
		if (filePathName.equals(""))
			throw new IllegalArgumentException(
					"There is no specified destination file (1140).");
		if (filePathName.lastIndexOf("\\") >= 0) {
			path = filePathName.substring(0, filePathName.lastIndexOf("\\"));
			fileName = filePathName
					.substring(filePathName.lastIndexOf("\\") + 1);
		}
		if (filePathName.lastIndexOf("/") >= 0) {
			path = filePathName.substring(0, filePathName.lastIndexOf("/"));
			fileName = filePathName
					.substring(filePathName.lastIndexOf("/") + 1);
		}
		path = path.length() != 0 ? path : "/";
		java.io.File physicalPath = new java.io.File(path);
		if (physicalPath.exists())
			isPhysical = true;
		if (option == 0) {
			if (isVirtual(path)) {
				path = m_application.getRealPath(path);
				if (path.endsWith(fileSeparator))
					path = path + fileName;
				else
					path = String.valueOf((new StringBuffer(String
							.valueOf(path))).append(fileSeparator).append(
							fileName));
				return path;
			}
			if (isPhysical) {
				if (m_denyPhysicalPath)
					throw new IllegalArgumentException(
							"Physical path is denied (1125).");
				else
					return filePathName;
			} else {
				throw new IllegalArgumentException(
						"This path does not exist (1135).");
			}
		}
		if (option == 1) {
			if (isVirtual(path)) {
				path = m_application.getRealPath(path);
				if (path.endsWith(fileSeparator))
					path = path + fileName;
				else
					path = String.valueOf((new StringBuffer(String
							.valueOf(path))).append(fileSeparator).append(
							fileName));
				return path;
			}
			if (isPhysical)
				throw new IllegalArgumentException(
						"The path is not a virtual path.");
			else
				throw new IllegalArgumentException(
						"This path does not exist (1135).");
		}
		if (option == 2) {
			if (isPhysical)
				if (m_denyPhysicalPath)
					throw new IllegalArgumentException(
							"Physical path is denied (1125).");
				else
					return filePathName;
			if (isVirtual(path))
				throw new IllegalArgumentException(
						"The path is not a physical path.");
			else
				throw new IllegalArgumentException(
						"This path does not exist (1135).");
		}

		else {
			return null;
		}

	}

	private boolean isVirtual(String pathName) // 判断是否是虚拟路径
	{
		if (m_application.getRealPath(pathName) != null) {
			java.io.File virtualFile = new java.io.File(m_application
					.getRealPath(pathName));
			return virtualFile.exists();
		}

		else {
			return false;
		}
	}

}
