package com.ptac.app.chinamobile.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.noki.daoruelectricfees.javabean.DaoruElectricfees;
import com.noki.electricfees.servlet.ReadFile;
import com.noki.electricfees.servlet.ReadFileFactory;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.ptac.app.chinamobile.dao.ChinaMobileBillCheckImp;
import com.ptac.app.electricmanage.input.dao.InputElectricBillImp;

public class CMCCUpXlsBillServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371798486616079452L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	/**
	 * Constructor of the object.
	 */
	public CMCCUpXlsBillServlet() {
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

		doPost(request, response);
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
		try {
			this.upload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.readXls(request, response);
		
	}
	/**
	 * 读取文件的生成 2m 设置读取文件的 参数
	 * @author gt_ptac
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked" })
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		@SuppressWarnings("unused")
		ArrayList list = new ArrayList();
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(5 * 1024 * 1024);
		fu.setSizeThreshold(4096);
		Path path = new Path();
		path.servletInitialize(getServletConfig().getServletContext());
		String dir1 = path.getPhysicalPath("/indata/", 0);
		fu.setRepositoryPath(dir1);
		List fileItems = fu.parseRequest(request);
		Iterator iter = fileItems.iterator();
		String zipname = null;
		File file = null;

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				String name = item.getName();
				String extName = name.substring(name.lastIndexOf("\\") + 1);
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0) {
					continue;
				}
				try {
					zipname = extName;
					request.setAttribute("filename", dir1 + zipname);
					file = new File(dir1 + zipname);
					item.write(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * @author gt_ptac
	 * 读取xls 文件
	 * 非xls 的返回
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void readXls(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		String msg = "移动电费电量导入失败！";
		String url;
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String path = request.getContextPath();
		String filename = (String) request.getAttribute("filename");
		String id = "";
		url = path + "/web/msgdr.jsp";

		try {
			Path phyfile = new Path();
			phyfile.servletInitialize(getServletConfig().getServletContext());
			UploadDaoQxfj dao = new UploadDaoQxfj(account, id, filename);
			boolean gs = dao.isUpload(filename);

			if (gs) {
				msg = "移动电费电量导入模板格式不正确，请在系统中下载模板！";
			} else {
				// 生成 读取文件的工具类 进行使用
				ReadFile temp = new ReadFileFactory().getReadFile(filename);
				// 开始读取文件
				Vector content = temp.getContent(filename);

				DaoruElectricfees sellin = new DaoruElectricfees();
				// 主键生成
				CountForm cform = new CountForm();
				long a = new Date().getTime();

				if ((content.size() - 1) <= 300) { // 条数判断
					Vector wrong = new ChinaMobileBillCheckImp().input(content, cform, account, request, response);
					long a1 = new Date().getTime();
					System.out.println("--" + a + "--" + a1 + "************"
							+ (a1 - a));
					sellin.closeDb();
					if (!wrong.isEmpty()) {
						msg = "共 " + cform.getZg() + "  条。成功导入 "
								+ cform.getCg() + " 条！有 " + cform.getBcg()
								+ "  条数据未导入！";
						WriteExcle wr = new WriteExcle();
						String dir2 = phyfile.getPhysicalPath("/wrongdata/", 0); // 传参数
						wr.Write(wrong, account.getAccountName()
								+ "电费单导入不成功的数据.xls", "电费单导入不成功的数据",
								"电费单导入不成功数据", 32, dir2);
					} else {

						if (cform.getCg() <= 0) {
							msg = "导入模板在格式转换过程中被损坏，请在系统中下载模板";
						} else {
							msg = "导入情况：共导入 " + cform.getCg() + " 条！";
						}

					}
				} else {
					msg = "导入条数要小于300条，请修改导入条数！";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("msg", msg);
		session.setAttribute("url", path
				+ "/web/appJSP/electricmanage/electricitybill/inputEleBill.jsp");
		session.setAttribute("wfile", path + "/wrongdata/"
				+ account.getAccountName() + "电费单导入不成功的数据.xls");

		response.sendRedirect(url);
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
