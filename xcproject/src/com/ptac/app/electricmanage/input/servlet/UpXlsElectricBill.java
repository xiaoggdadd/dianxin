package com.ptac.app.electricmanage.input.servlet;

import java.io.File;
import java.io.IOException;
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
import com.ptac.app.electricmanage.input.dao.InputElectricBillImp;

/**
 * ��ѵ����� xls ��ʱ�ļ� ����
 * 
 * @author GT
 * 
 */
@SuppressWarnings("deprecation")
public class UpXlsElectricBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpXlsElectricBill() {
	}

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void init() throws ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		try {
			this.upload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.readXls(request, response);
		// RequestDispatcher dispatcher = request
		// .getRequestDispatcher("/servlet/NewDaoruElectricfeesServlet");
		// dispatcher.forward(request, response);
	}

	/**
	 * ��ȡ�ļ������� 2m ���ö�ȡ�ļ��� ����
	 * 
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
	 * ��ȡxls �ļ�
	 * ��xls �ķ���
	 * @param request
	 * @param response
	 * @throws IOException
	 * @update ����ʧ��������32��Ϊ26
	 */
	public void readXls(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		String msg = "���������������ʧ�ܣ�";
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
				msg = "��ѵ�����ģ���ʽ����ȷ������ϵͳ������ģ�壡";
			} else {
				// ���� ��ȡ�ļ��Ĺ����� ����ʹ��
				ReadFile temp = new ReadFileFactory().getReadFile(filename);
				// ��ʼ��ȡ�ļ�
				Vector content = temp.getContent(filename);

				DaoruElectricfees sellin = new DaoruElectricfees();
				// ��������
				CountForm cform = new CountForm();
				long a = new Date().getTime();

				if ((content.size() - 1) <= 300) { // �����ж�

					Vector wrong = new InputElectricBillImp().input(content,
							cform, account,request,response);
					long a1 = new Date().getTime();
					System.out.println("--" + a + "--" + a1 + "************"
							+ (a1 - a));
					sellin.closeDb();
					if (!wrong.isEmpty()) {
						msg = "�� " + cform.getZg() + "  �����ɹ����� "
								+ cform.getCg() + " ������ " + cform.getBcg()
								+ "  ������δ���룡";
						WriteExcle wr = new WriteExcle();
						String dir2 = phyfile.getPhysicalPath("/wrongdata/", 0); // ������
						wr.Write(wrong, account.getAccountName()
								+ "��ѵ����벻�ɹ�������.xls", "��ѵ����벻�ɹ�������",
								"��ѵ����벻�ɹ�����", 26, dir2);//32
					} else {

						if (cform.getCg() <= 0) {
							msg = "����ģ���ڸ�ʽת�������б��𻵣�����ϵͳ������ģ��";
						} else {
							msg = "��������������� " + cform.getCg() + " ����";
						}

					}
				} else {
					msg = "��������ҪС��300�������޸ĵ���������";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("msg", msg);
		session.setAttribute("url", path
				+ "/web/appJSP/electricmanage/electricitybill/inputEleBill.jsp");
		session.setAttribute("wfile", path + "/wrongdata/"
				+ account.getAccountName() + "��ѵ����벻�ɹ�������.xls");

		response.sendRedirect(url);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
