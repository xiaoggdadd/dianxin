package com.ptac.app.electricmanage.bargainbill.action;

import java.io.File;
import java.io.IOException;
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
import com.ptac.app.electricmanage.bargainbill.dao.BargainBillMethodsDAO;
import com.ptac.app.electricmanage.bargainbill.dao.Imp.BargainBillMethodsDAOImp;

@SuppressWarnings( { "serial", "deprecation" })
public class BargainBillInputServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 用于合同单导入的Servlet
	 * 
	 * @author rock
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		try {
			this.upload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.readXls(request, response);
	}

	/**
	 * 用于加载XML
	 * 
	 * @author rock
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked" })
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
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
		// 过滤掉的文件类型
		String zipname = null;
		File file = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// 忽略其他不是文件域的所有表单信息
			if (!item.isFormField()) {
				String name = item.getName();
				String extName = name.substring(name.lastIndexOf("\\") + 1);

				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0) {
					continue;
				}
				try {
					zipname = extName;
					session.setAttribute("filename", dir1 + zipname);
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
	 * 读取xls 文件 非xls 的返回
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@SuppressWarnings( { "static-access", "unchecked" })
	public void readXls(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		String msg = "电量电费批量导入失败！";
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
				msg = "电费单导入模板格式不正确，请在系统中下载模板！";
			} else {
				// 生成 读取文件的工具类 进行使用
				ReadFile temp = new ReadFileFactory().getReadFile(filename);
				// 开始读取文件 update WnagYiXiao 2014-5-21 getContent --> getContenthtd
				//Vector content = temp.getContent(filename);
				Vector content = temp.getContenthtd(filename);
				DaoruElectricfees sellin = new DaoruElectricfees();
				// 主键生成
				CountForm cform = new CountForm();
				
				if ((content.size() - 1) <= 300) { // 条数判断

					BargainBillMethodsDAO bbm = new BargainBillMethodsDAOImp();
					Vector wrong = bbm.input(content,
							cform, account,request,response);//合同导入
					sellin.closeDb();
					if (!wrong.isEmpty()) {
		    			msg = "共 " + cform.getZg() + "  条。成功导入 " + cform.getCg() +
    					" 条！有 " + cform.getBcg() + "  条数据未导入！";
    			WriteExcle wr = new WriteExcle();
    			String dir2 = phyfile.getPhysicalPath("/wrongdata/", 0); // 传参数
    			wr.Write(wrong, account.getAccountName() + "合同单导入不成功的数据.xls", "合同单导入不成功的数据",
               "合同单导入不成功数据", 32, dir2);
    		} else {
								if(cform.getCg()<=0){
					  	  				msg="导入模板在格式转换过程中被损坏，请在系统中下载模板";
					  	  			}else{
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
				+ "/web/appJSP/electricmanage/bargainbill/bargainBillInput.jsp");
	    session.setAttribute("wfile", path + "/wrongdata/" + account.getAccountName() +"合同单导入不成功的数据.xls");
	    
	    response.sendRedirect(url);
		
	}

}
