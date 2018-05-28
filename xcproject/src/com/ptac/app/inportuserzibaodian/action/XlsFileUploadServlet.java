package com.ptac.app.inportuserzibaodian.action;

import java.io.File;
import java.io.IOException;
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
import com.ptac.app.inportuserzibaodian.dao.InputUserZibaodian;
import com.ptac.app.inportuserzibaodian.dao.InputUserZibaodianImp;

/**
 * �Ա����û����� xls ��ʱ�ļ� ����
 * 
 * @author rock
 * 
 */
@SuppressWarnings( { "deprecation" })
public class XlsFileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		try {
			this.upload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String biaozhi = request.getParameter("biaoji");
		this.readXls(biaozhi,request, response);
	}

	/**
	 * �Ա����û�����
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked" })
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
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
		// ���˵����ļ�����
		// String[] errorType={".exe",".com",".cgi",".asp"};
		String zipname = null;
		// int num = 0;
		File file = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// �������������ļ�������б���Ϣ
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
	 * ��ȡxls �ļ� ��xls �ķ���
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@SuppressWarnings( { "static-access", "unchecked" })
	public void readXls(String biaozhi,HttpServletRequest request, HttpServletResponse response)
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
				
				if ((content.size() - 1) <= 30000) { // �����ж�

					InputUserZibaodian iuz = new InputUserZibaodianImp();
					Vector wrong = iuz.input(content,
							cform, account,biaozhi,request,response);
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
								+ "�Ա����û��ŵ�ѵ����벻�ɹ�������.xls", "�Ա����û��ŵ�ѵ����벻�ɹ�������", "�Ա����û��ŵ�ѵ����벻�ɹ�����",
								23, dir2);
							} else {
								if(cform.getCg()<=0){
					  	  				msg="����ģ���ڸ�ʽת�������б��𻵣�����ϵͳ������ģ��";
					  	  			}else{
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
				+ account.getAccountName() + "�Ա����û��ŵ�ѵ����벻�ɹ�������.xls");

		response.sendRedirect(url);
		
	}
}
