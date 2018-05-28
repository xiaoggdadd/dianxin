package com.noki.dataLoad.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.jizhan.daoru.PoiWrite;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.priceover.countysigncheck.dao.CountySignCheckDao;
import com.ptac.app.priceover.countysigncheck.dao.CountySignCheckDaoImpl;

/**
 * Title: ����ļ��ϴ�Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28 
 */
public class FileUploadServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int curpage = 0;
		String whereStr = "";
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		DbLog log = new DbLog();// ��־
		Account account = new Account();

		HttpSession session = req.getSession();

		account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();// ��ȡ���� ��½ʱ�Զ���ɵ�

		String action = req.getParameter("action");
		String biaoji = req.getParameter("biaoji");
		//String templetname = req.getParameter("templetname");
		String  templetname="";
		try{
			templetname = java.net.URLDecoder.decode(req.getParameter("templetname"),"utf-8");
			 
		}catch(Exception e){
			System.out.println("这里执行了templetname 模板名为空传值,不是错误");
		}
		// templetname = java.net.URLDecoder.decode(templetname, "GB2312");
		System.out.println("action" + action);
		FileInputStream fis = null;

		String forward = "success";
		// ����ļ����ز鿴
		// ����ģ�� ��Ҫд�븽����Ϣ����ݵ�ģ��
		if ("download".equals(action)) {
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModel(curpage, whereStr, loginId);// ��ȡ���
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			// path1.append(Constants.sdtaxUploadPath + "\\");
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFilemm(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// ��ǿ���ѵ�ģ������
		// ����ģ�� ��Ҫд�븽����Ϣ����ݵ�ģ��
		if ("enhancedownload".equals(action)) {
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModelEnhance(curpage, whereStr, loginId);// ��ȡ���
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			// path1.append(Constants.sdtaxUploadPath + "\\");
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFileEnhancemm(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// Ԥ��������ļ����ز鿴
		// ����ģ�� ��Ҫд�븽����Ϣ����ݵ�ģ��
		if ("downloadpp".equals(action)) {
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModelpp(curpage, whereStr, loginId);// ��ȡ���
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");
			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFilepp(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// ����վ���������ģ��ͳ����վ��������Ϣģ���ļ����ز鿴
		// ����ģ�� ��Ҫд�븽����Ϣ����ݵ�ģ��
		if ("downloadzdcbglxx".equals(action)
				|| "downloadzdcbglzgxx".equals(action)) {
			String id = req.getParameter("idcz");
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModelzdcbglxx(curpage, id);// ��ȡ���
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			datafilepath = writeFilezdcbglxx(fylist, filePath, templetname,
					action);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		if ("downmodify".equals(action)) {
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getzhandianmodifg(curpage, whereStr, loginId, biaoji);
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			// path1.append(Constants.sdtaxUploadPath + "\\");
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFile(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// ������ԭվ��id�ĵ�ѵ�ģ��
		if ("downloadyid".equals(action)) {
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFileyid(filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// ���ش��Ա����û��ŵ�ģ��
		if ("downloadzb".equals(action)) {
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFileyid(filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// ����������
		if ("download1".equals(action)) {
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			AmmeterDegreeBean beanw = new AmmeterDegreeBean();
			ArrayList fylist1 = new ArrayList();
			// ��ȡ��Ҫ����Ϣ
			fylist1 = beanw.getPageDataModel1(curpage, whereStr, loginId);
			String datafilepath = "";
			// ������Ϣ������ֵ
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			//�������·�������
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");
			System.out.println("�������ص�ַ:"
					+ filePath);
			//��ʼ��֯ ����ģ��
			datafilepath = writeFile1(fylist1, filePath, templetname);
			
			//д��
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// ���������޸ĵ���
		if (action.equals("downloaddj")) {
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModeldj(curpage, whereStr, loginId);
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			// path1.append(Constants.sdtaxUploadPath + "\\");
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFiledj(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
		// ��ͬ����������
		if (action.equals("downloadhtd")) {
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModelhtd(curpage, whereStr, loginId);
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			// path1.append(Constants.sdtaxUploadPath + "\\");
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			datafilepath = writeFilehtd(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
		// 电表导入
		if (action.equals("dbtemp")) {
			PoiWrite write = new PoiWrite();
			String dir = req.getSession().getServletContext().getRealPath("/");
			String tempfile = dir + "/exceltemplet/电表批量导入模板.xls";
			String retfile = dir + "/exceltemplet/" + account.getAccountName()
					+ "电表批量导入模板.xls";

			String ret = "1";
			FileInputStream in = null;
			FileOutputStream out = null;
			HSSFWorkbook workbook = null;
			try {
				in = new FileInputStream(tempfile); // ��excel�ļ�תΪ������
				POIFSFileSystem fs = new POIFSFileSystem(in); // ����POIFSFileSystem�����������������
				workbook = new HSSFWorkbook(fs); // ������workbook�����POIFSFileSystem����

				out = new FileOutputStream(retfile);
				workbook.write(out);

			} catch (IOException e) {
				ret = "0";
				System.out.println(e.toString());
			} finally {
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			write.writeExcel(retfile, account.getAccountName());
			// write.
			resp.reset();
			String downfilename = account.getAccountName() + "电表批量导入模板.xls";
			resp.setContentType("application/x-msdownload");
			resp.addHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(downfilename, "utf-8") + "");
			String downpath = retfile;
			write(downpath, resp);
		}
		// ���ظ�����ģ�浼���д�������
		if (action.equals("downwrongdata")) {
			resp.reset();
			String downfilename = (String) session.getAttribute("wfile");
			downfilename = downfilename
					.substring(downfilename.lastIndexOf("/") + 1);

			resp.setContentType("application/x-msdownload");
			resp.addHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(downfilename, "utf-8") + "");
			// \"" + URLEncoder.encode(modelName, "utf-8") + "\""
			String downpath = req.getRealPath("/") + "wrongdata/"
					+ downfilename;

			write(downpath, resp);
		}

		// ���ؿյ�ģ�� ����Ҫд����ݵ�����ģ�壨�����
		if ("downtemplet".equals(action)) {
			// response.setContentType("application/doc;charset=GB2312");
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			// path1.append(Constants.sdtaxUploadPath + "\\");
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			/*resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));*/
			resp.setHeader("Content-disposition", "attachment;filename="
					+new String(templetname.getBytes("gbk"),"ISO8859-1") + ".xls");
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath+" templetname:"+templetname);
			try {
				fis = new FileInputStream(new File(filePath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if ("downloadzdxgsq".equals(action)) {// �Ա���� ������������ģ��
			String id = req.getParameter("idcz");
			System.out.println("dfdsfsd" + id);
			AmmeterDegreeBean bean = new AmmeterDegreeBean();
			ArrayList fylist = new ArrayList();
			fylist = bean.getPageDataModelzdxgsq(curpage, id, loginId);// ��ȡ���
			// �������ϴ��ļ�·��
			StringBuffer path1 = new StringBuffer();
			String datafilepath = "";
			// ������Ϣ������ֵ
			String primaryKey = "";
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");
			datafilepath = writeFilezdxgsqx(fylist, filePath, templetname,
					action);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		
		if ("downloadpriceover".equals(action)) {// ���۳�������ģ��
			String id = req.getParameter("idcz");
			System.out.println("dfdsfsd" + id);
			CountySignCheckDao dao = new CountySignCheckDaoImpl();
			ArrayList fylist = new ArrayList();
			fylist = dao.getPageDataModelpriceover( id, loginId);// ��ȡ���
			String datafilepath = "";
			resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");
			datafilepath = writeFilepriceover(fylist, filePath, templetname);
			try {
				fis = new FileInputStream(new File(datafilepath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	// ��ȡָ��·�����ļ� ���غ�ɾ�� ��ʱ�ļ�
	public void write(String path, HttpServletResponse resp) {
		File file = new File(path);
		
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			ServletOutputStream servletOS = resp.getOutputStream();
			byte[] buff = new byte[1024];

			int readLength;
			while (((readLength = fis.read(buff)) != -1)) {
				servletOS.write(buff, 0, readLength);
			}

			servletOS.flush();
			servletOS.close();
			fis.close();

			file.delete(); // ɾ����ʱ�ļ�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��������ʱ�˴�����ʱ ������
	 * 
	 * @param fylist
	 * @param creatFilePath
	 * @param templetname
	 * @return
	 */
	public String writeFile1(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			String sszy = SelPermissionCconfiguration("PJLX", "");
			String[] sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			String xianname = "", xiaoquname = "", jzname = "", ammeterid = "", lastdegree = "", lastdatetime = "", stationtype = "";
			int i = 2;
			if (fylist != null) {
				//��ȡ�ܹ����ֶ���
				int fycount = ((Integer) fylist.get(0)).intValue();
				System.out.println("3333333333:" + fycount);
				//��ʼѭ�� ����
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {
					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k+ fylist.indexOf("AGNAME"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					ammeterid = (String) fylist.get(k + fylist.indexOf("DBID"));
					ammeterid = ammeterid != null ? ammeterid : "";

					lastdegree = (String) fylist.get(k+ fylist.indexOf("THISDEGREE"));
					lastdegree = lastdegree != null ? lastdegree : "";

					lastdatetime = (String) fylist.get(k+ fylist.indexOf("THISDATETIME"));// stationtype
					lastdatetime = lastdatetime != null ? lastdatetime : "";
					
					stationtype = (String) fylist.get(k+ fylist.indexOf("STATIONTYPE"));// stationtype
					stationtype = stationtype != null ? stationtype : "";

					String d = "";
					Pattern pattern4 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{2}");
					if (!lastdatetime.trim().isEmpty()&& pattern4.matcher(lastdatetime).matches() == true) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date days;
						try {
							days = sdf.parse(lastdatetime.toString());
							Calendar cd = Calendar.getInstance();
							cd.setTime(days);
							cd.add(Calendar.DATE, 1);
							d = sdf.format(cd.getTime());
							System.out.println("����" + d);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					HSSFRow row = sheet.createRow(i);
					// System.out.println("6666666i6666:"+i);
					HSSFCell Cell0 = row.createCell(0);
					// ID
					Cell0.setCellValue(xianname);
					HSSFCell Cell1 = row.createCell(1);
					// վ�����
					Cell1.setCellValue(xiaoquname);
					// ���ID
					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(jzname);
					// ����ID
					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(ammeterid);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(lastdegree);

					HSSFCell Cell6 = row.createCell(6);
					Cell6.setCellValue(d);

					HSSFCell Cell15 = row.createCell(15);
					Cell15.setCellValue(stationtype);

					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	public String writeFilemm(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			String sszy = SelPermissionCconfiguration("PJLX", "");
			String[] sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			// System.out.println("010000000:"+fylist);
			String xianname = "", xiaoquname = "", jzname = "", ammeterid = "", lastdegree = "", stationtype = "", 
			lastdatetime = "", beilv = "", linelosstype = "", linelossvalue = "", danjia = "", csds = "",
			cssytime = "", zdlx = "",dbds = "",xgbzw = "";//����޸Ķ���������޸ı�־λ
			int i = 2;
			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				System.out.println("3333333333:" + fycount);
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������
					// System.out.println("5555555555:"+k);
					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("AGNAME"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					stationtype = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					stationtype = stationtype != null ? stationtype : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					ammeterid = (String) fylist.get(k + fylist.indexOf("DBID"));
					ammeterid = ammeterid != null ? ammeterid : "";

					lastdegree = (String) fylist.get(k
							+ fylist.indexOf("THISDEGREE"));
					lastdegree = lastdegree != null ? lastdegree : "";

					lastdatetime = (String) fylist.get(k
							+ fylist.indexOf("THISDATETIME"));
					lastdatetime = lastdatetime != null ? lastdatetime : "";

					danjia = (String) fylist.get(k + fylist.indexOf("DANJIA"));
					danjia = danjia != null ? danjia : "";

					beilv = (String) fylist.get(k + fylist.indexOf("BEILV"));
					beilv = beilv != null ? beilv : "";

					csds = (String) fylist.get(k + fylist.indexOf("CSDS"));
					csds = csds != null ? csds : "";

					cssytime = (String) fylist.get(k
							+ fylist.indexOf("CSSYTIME"));
					cssytime = cssytime != null ? cssytime : "";

					linelosstype = (String) fylist.get(k
							+ fylist.indexOf("LINELOSSTYPE"));
					linelosstype = linelosstype != null ? linelosstype : "";

					linelossvalue = (String) fylist.get(k
							+ fylist.indexOf("LINELOSSVALUE"));
					linelossvalue = linelossvalue != null ? linelossvalue : "";

					zdlx = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					zdlx = zdlx != null ? zdlx : "";
					dbds = (String) fylist.get(k
							+ fylist.indexOf("DBDS"));
					dbds = dbds != null ? dbds : "";
					xgbzw = (String) fylist.get(k
							+ fylist.indexOf("XGBZW"));
					xgbzw = xgbzw != null ? xgbzw : "";

					// System.out.println("lastdatetime"+lastdatetime);
					String d = "";
					Pattern pattern4 = Pattern
							.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{2}");
					if (!lastdatetime.trim().isEmpty()
							&& pattern4.matcher(lastdatetime).matches() == true) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date days;
						try {
							days = sdf.parse(lastdatetime.toString());
							Calendar cd = Calendar.getInstance();
							cd.setTime(days);
							cd.add(Calendar.DATE, 1);
							d = sdf.format(cd.getTime());
							// System.out.println("����"+d);
							// days=sdf.parse(lastdatetime);

							// days = (Date)sdf.parseObject(lastdatetime);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (lastdegree == null || lastdegree == " "
							|| lastdegree == "") {
						lastdegree = csds;
					}
					if("1".equals(xgbzw)){//���������޸ı�־λΪ1  �ϴε�������� ����޸Ķ�����Ϣ�޸ģ�
						lastdegree=dbds;
					}
					if (lastdatetime == null || lastdatetime == " "
							|| lastdatetime == "") {
						d = cssytime;
					}

					HSSFRow row = sheet.createRow(i);
					// System.out.println("6666666i6666:"+i);

					HSSFCell Cell0 = row.createCell(0);
					// ID
					Cell0.setCellValue(xianname);

					HSSFCell Cell1 = row.createCell(1);

					// վ�����
					Cell1.setCellValue(xiaoquname);
					// ���ID
					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(jzname);
					// ����ID
					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(ammeterid);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(lastdegree);

					HSSFCell Cell6 = row.createCell(6);
					Cell6.setCellValue(d);

					HSSFCell Cell13 = row.createCell(13);
					Cell13.setCellValue(danjia);

					HSSFCell Cell21 = row.createCell(21);
					Cell21.setCellValue(linelosstype);

					HSSFCell Cell22 = row.createCell(22);
					Cell22.setCellValue(linelossvalue);

					HSSFCell Cell23 = row.createCell(23);
					Cell23.setCellValue(beilv);

					HSSFCell Cell24 = row.createCell(24);
					Cell24.setCellValue(zdlx);

					if ("���������������ģ��".equals(templetname)) {
						// System.out.println("010000000:"+templetname);
						// Ʊ������
						// CellRangeAddressList regions = new
						// CellRangeAddressList(i,i,20,20);

						// �������������

						// DVConstraint constraint =
						// DVConstraint.createExplicitListConstraint(sszyValue);

						// �����������������

						// HSSFDataValidation data_validation = new
						// HSSFDataValidation(regions,constraint);

						// ��sheetҳ��Ч

						// sheet.addValidationData(data_validation);
					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}
	
	/**��ǿ���ѵ�ģ������
	 * @param fylist
	 * @param creatFilePath
	 * @param templetname
	 * @return
	 */
	public String writeFileEnhancemm(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			String sszy = SelPermissionCconfiguration("PJLX", "");
			String[] sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			// System.out.println("010000000:"+fylist);
			String xianname = "", xiaoquname = "", jzname = "", ammeterid = "",gdfs = "", lastdegree = "", lastdatetime = "", beilv = "", linelosstype = "", linelossvalue = "", danjia = "",dyjdanjia = "", csds = "", cssytime = "",changevalue = "", zdlx = "";
			String jfzb = "",jfz = "" ,bfzb = "",bfz = "",bpzb = "",bpz = "",bgzb = "",bgz = "";
			int i = 2;
			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				System.out.println("3333333333:" + fycount);
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������
					// System.out.println("5555555555:"+k);
					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("AGNAME"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					ammeterid = (String) fylist.get(k + fylist.indexOf("DBID"));
					ammeterid = ammeterid != null ? ammeterid : "";
					
					zdlx = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					zdlx = zdlx != null ? zdlx : "";
					
					gdfs = (String) fylist.get(k + fylist.indexOf("GDFS"));
					gdfs = gdfs != null ? gdfs : "";

					lastdegree = (String) fylist.get(k
							+ fylist.indexOf("THISDEGREE"));
					lastdegree = lastdegree != null ? lastdegree : "";

					lastdatetime = (String) fylist.get(k
							+ fylist.indexOf("THISDATETIME"));
					lastdatetime = lastdatetime != null ? lastdatetime : "";

					danjia = (String) fylist.get(k + fylist.indexOf("ZGDDANJIA"));
					danjia = danjia != null ? danjia : "";
					
					dyjdanjia = (String) fylist.get(k + fylist.indexOf("DYJDANJIA"));
					dyjdanjia = dyjdanjia != null ? dyjdanjia : "";

					beilv = (String) fylist.get(k + fylist.indexOf("BEILV"));
					beilv = beilv != null ? beilv : "";

					csds = (String) fylist.get(k + fylist.indexOf("CSDS"));
					csds = csds != null ? csds : "";

					cssytime = (String) fylist.get(k
							+ fylist.indexOf("CSSYTIME"));
					cssytime = cssytime != null ? cssytime : "";

					linelosstype = (String) fylist.get(k
							+ fylist.indexOf("LINELOSSTYPE"));
					linelosstype = linelosstype != null ? linelosstype : "";

					linelossvalue = (String) fylist.get(k
							+ fylist.indexOf("LINELOSSVALUE"));
					linelossvalue = linelossvalue != null ? linelossvalue : "";
					
					changevalue = (String) fylist.get(k
							+ fylist.indexOf("CHANGEVALUE"));
					changevalue = changevalue != null ? changevalue : "";
					
					jfzb = (String) fylist.get(k
							+ fylist.indexOf("JFZB"));
					jfzb = jfzb != null ? jfzb : "";
					jfz = (String) fylist.get(k
							+ fylist.indexOf("JFZ")); 
							jfz = jfz != null ? jfz : "";
					bfzb = (String) fylist.get(k
							+ fylist.indexOf("BFZB"));
					bfzb = bfzb != null ? bfzb : "";
					bfz = (String) fylist.get(k
							+ fylist.indexOf("BFZ"));
					bfz = bfz != null ? bfz : "";
					bpzb = (String) fylist.get(k
							+ fylist.indexOf("BPZB"));
					bpzb = bpzb != null ? bpzb : "";
					bpz = (String) fylist.get(k
							+ fylist.indexOf("BPZ"));
					bpz = bpz != null ? bpz : "";
					bgzb = (String) fylist.get(k
							+ fylist.indexOf("BGZB"));
					bgzb = bgzb != null ? bgzb : "";
					bgz = (String) fylist.get(k
							+ fylist.indexOf("BGZ"));
					bgz = bgz != null ? bgz : "";
					// System.out.println("lastdatetime"+lastdatetime);
					String d = "";
					Pattern pattern4 = Pattern
							.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{2}");
					if (!lastdatetime.trim().isEmpty()
							&& pattern4.matcher(lastdatetime).matches() == true) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date days;
						try {
							days = sdf.parse(lastdatetime.toString());
							Calendar cd = Calendar.getInstance();
							cd.setTime(days);
							cd.add(Calendar.DATE, 1);
							d = sdf.format(cd.getTime());
							// System.out.println("����"+d);
							// days=sdf.parse(lastdatetime);

							// days = (Date)sdf.parseObject(lastdatetime);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (lastdegree == null || lastdegree == " "
							|| lastdegree == "") {
						lastdegree = csds;
					}
					if (lastdatetime == null || lastdatetime == " "
							|| lastdatetime == "") {
						d = cssytime;
					}

					HSSFRow row = sheet.createRow(i);
					// System.out.println("6666666i6666:"+i);
					// ����
					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(xianname);
					// ����
					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(xiaoquname);
					// վ�����
					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(jzname);
					// ���ID
					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(ammeterid);
					//վ������
					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(zdlx);
					//���緽ʽ
					HSSFCell Cell5 = row.createCell(5);
					Cell5.setCellValue(gdfs);
					//�ϴε�����
					HSSFCell Cell6 = row.createCell(6);
					Cell6.setCellValue(lastdegree);
					//�ϴγ���ʱ��
					HSSFCell Cell8 = row.createCell(8);
					Cell8.setCellValue(d);
					//ת���絥��
					HSSFCell Cell15 = row.createCell(15);
					Cell15.setCellValue(danjia);
					//��ҵ�ֵ���
					HSSFCell Cell16 = row.createCell(16);
					Cell16.setCellValue(dyjdanjia);
					//��������
					HSSFCell Cell25 = row.createCell(25);
					Cell25.setCellValue(linelosstype);
					//����ֵ
					HSSFCell Cell26 = row.createCell(26);
					Cell26.setCellValue(linelossvalue);
					//����ֵ
					HSSFCell Cell27 = row.createCell(27);
					Cell27.setCellValue(changevalue);
					//����
					HSSFCell Cell28 = row.createCell(28);
					Cell28.setCellValue(beilv);
					//���ռ��
					HSSFCell Cell29 = row.createCell(29);
					Cell29.setCellValue(jfzb);
					//���ֵ
					HSSFCell Cell30 = row.createCell(30);
					Cell30.setCellValue(jfz);
					//����ռ��
					HSSFCell Cell31 = row.createCell(31);
					Cell31.setCellValue(bfzb);
					//����ֵ
					HSSFCell Cell32 = row.createCell(32);
					Cell32.setCellValue(bfz);
					//��ƽռ��
					HSSFCell Cell33 = row.createCell(33);
					Cell33.setCellValue(bpzb);
					//��ƽֵ
					HSSFCell Cell34 = row.createCell(34);
					Cell34.setCellValue(bpz);
					//����ռ��
					HSSFCell Cell35 = row.createCell(35);
					Cell35.setCellValue(bgzb);
					//����ֵ
					HSSFCell Cell36 = row.createCell(36);
					Cell36.setCellValue(bgz);

					if ("���������������ģ��".equals(templetname)) {
						// System.out.println("010000000:"+templetname);
						// Ʊ������
						// CellRangeAddressList regions = new
						// CellRangeAddressList(i,i,20,20);

						// �������������

						// DVConstraint constraint =
						// DVConstraint.createExplicitListConstraint(sszyValue);

						// �����������������

						// HSSFDataValidation data_validation = new
						// HSSFDataValidation(regions,constraint);

						// ��sheetҳ��Ч

						// sheet.addValidationData(data_validation);
					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	/**
	 * Ԥ��������ļ����ز鿴,����ģ�� ��Ҫд�븽����Ϣ����ݵ�ģ��
	 * 
	 * @param fylist
	 *            ��վ��͵����Ϣ
	 * @param creatFilePath
	 *            :ģ��·��
	 * @param templetname
	 *            :ģ�����
	 * @return
	 */
	public String writeFilepp(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			String sszy = SelPermissionCconfiguration("PJLX", "");
			String[] sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			String xianname = "", xiaoquname = "", zdid = "", jzname = "", ammeterid = "", lastdegree = "", stationtype = "", lastdatetime = "", danjia = "", csds = "", cssytime = "", startmonth = "";
			int i = 2;
			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������
					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("XIAOQU"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					stationtype = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					stationtype = stationtype != null ? stationtype : "";

					zdid = (String) fylist.get(k + fylist.indexOf("ID"));
					zdid = zdid != null ? zdid : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					ammeterid = (String) fylist.get(k + fylist.indexOf("DBID"));
					ammeterid = ammeterid != null ? ammeterid : "";

					lastdegree = (String) fylist.get(k
							+ fylist.indexOf("THISDEGREE"));
					lastdegree = lastdegree != null ? lastdegree : "";

					lastdatetime = (String) fylist.get(k
							+ fylist.indexOf("THISDATETIME"));
					lastdatetime = lastdatetime != null ? lastdatetime : "";

					danjia = (String) fylist.get(k + fylist.indexOf("DANJIA"));
					danjia = danjia != null ? danjia : "";

					csds = (String) fylist.get(k + fylist.indexOf("CSDS"));
					csds = csds != null ? csds : "";

					cssytime = (String) fylist.get(k
							+ fylist.indexOf("CSSYTIME"));
					cssytime = cssytime != null ? cssytime : "";

					startmonth = (String) fylist.get(k
							+ fylist.indexOf("ENDMONTH"));
					startmonth = startmonth != null ? startmonth : "";

					String d = "";
					Pattern pattern4 = Pattern
							.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{2}");
					if (!lastdatetime.trim().isEmpty()
							&& pattern4.matcher(lastdatetime).matches() == true) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date days;
						try {
							days = sdf.parse(lastdatetime.toString());
							Calendar cd = Calendar.getInstance();
							cd.setTime(days);
							cd.add(Calendar.DATE, 1);
							d = sdf.format(cd.getTime());

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (lastdegree == null || lastdegree == " "
							|| lastdegree == "") {
						lastdegree = csds;
					}
					if (lastdatetime == null || lastdatetime == " "
							|| lastdatetime == "") {
						d = cssytime;
					}

					HSSFRow row = sheet.createRow(i);

					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(xianname);

					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(xiaoquname);

					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(zdid);

					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(jzname);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(stationtype);

					HSSFCell Cell5 = row.createCell(5);
					Cell5.setCellValue(ammeterid);

					HSSFCell Cell6 = row.createCell(6);
					Cell6.setCellValue(lastdegree);
					// jzname = "",ammeterid = "",lastdegree="",stationtype="",
					// lastdatetime="",danjia="",csds="",cssytime="",startmonth="";

					HSSFCell Cell8 = row.createCell(8);
					Cell8.setCellValue(d);

					HSSFCell Cell12 = row.createCell(12);
					Cell12.setCellValue(startmonth);

					HSSFCell Cell14 = row.createCell(14);
					Cell14.setCellValue(danjia);

					if ("���������������ģ��".equals(templetname)) {
						// System.out.println("010000000:"+templetname);
						// Ʊ������
						// CellRangeAddressList regions = new
						// CellRangeAddressList(i,i,20,20);

						// �������������

						// DVConstraint constraint =
						// DVConstraint.createExplicitListConstraint(sszyValue);

						// �����������������

						// HSSFDataValidation data_validation = new
						// HSSFDataValidation(regions,constraint);

						// ��sheetҳ��Ч

						// sheet.addValidationData(data_validation);
					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	/**
	 * ����վ����������鿴,����ģ�� ��Ҫд�븽����Ϣ����ݵ�ģ��
	 * 
	 * @param fylist
	 *            ��վ��͵����Ϣ
	 * @param creatFilePath
	 *            :ģ��·��
	 * @param templetname
	 *            :ģ�����
	 * @return
	 */
	public String writeFilezdcbglxx(ArrayList fylist, String creatFilePath,
			String templetname, String bzw) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			HSSFSheet sheet = workbook.getSheetAt(0);
			String shiname = "", xianname = "", xiaoquname = "", zdid = "", jzname = "";
			int i = 1;
			if ("downloadzdcbglxx".equals(bzw)) {
				i = 1;
			} else if ("downloadzdcbglzgxx".equals(bzw)) {
				i = 2;
			}

			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������

					shiname = (String) fylist.get(k + fylist.indexOf("SHI"));
					shiname = shiname != null ? shiname : "";

					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("XIAOQU"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					zdid = (String) fylist.get(k + fylist.indexOf("ZDID"));
					zdid = zdid != null ? zdid : "";

					jzname = (String) fylist.get(k + fylist.indexOf("ZDNAME"));
					jzname = jzname != null ? jzname : "";

					HSSFRow row = sheet.createRow(i);

					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(shiname);

					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(xianname);

					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(xiaoquname);

					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(zdid);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(jzname);

					if ("����վ���������ģ��".equals(templetname)) {

					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	// �����ݵ�
	public String writeFile(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			String sszy = SelPermissionCconfiguration("PJLX", "");
			String[] sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			// System.out.println("010000000:"+fylist);
			String sheng = "", shi = "", xian = "", xiaoqu = "", jzname = "", zdcode = "", yid = "", bieming = "", area = "", address = "", property = "", jztype = "", stationtype = "", gxxx = "", zdcq = "", yflx = "", gsf = "", fzr = "", sydate = "", qyzt = "", lyjhjgs = "", dytype = "", g2 = "", g3 = "", kdsb = "", yysb = "", zpsl = "", zssl = "", kdsbsl = "", yysbsl = "", kt1 = "", kt2 = "", zlfh = "", jlfh = "", zgd = "", memo = "", dianfei = "", bgsign = "", jnglmk = "", gdfs = "", edhdl = "", fkzq = "", dfzflx = "", beilv = "", dbyt = "", fkfs = "", dbzbdyhh = "", watchcost = "", linelosstype = "", linelossvalue = "", csds = "", cssytime = "", sc = "", yy = "", bg = "", xxh = "", jstz = "", dbid = "", dddf = "";

			int i = 2;
			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				System.out.println("3333333333:" + fycount);
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������
					// System.out.println("5555555555:"+k);
					sheng = (String) fylist.get(k + fylist.indexOf("SHENG"));
					sheng = sheng != null ? sheng : "";

					shi = (String) fylist.get(k + fylist.indexOf("SHI"));
					shi = shi != null ? shi : "";

					xian = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xian = xian != null ? xian : "";

					xiaoqu = (String) fylist.get(k + fylist.indexOf("XIAOQU"));
					xiaoqu = xiaoqu != null ? xiaoqu : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					zdcode = (String) fylist.get(k + fylist.indexOf("ID"));
					zdcode = zdcode != null ? zdcode : "";

					yid = (String) fylist.get(k + fylist.indexOf("YID"));
					yid = yid != null ? yid : "";

					bieming = (String) fylist
							.get(k + fylist.indexOf("BIEMING"));
					bieming = bieming != null ? bieming : "";

					area = (String) fylist.get(k + fylist.indexOf("AREA"));
					area = area != null ? area : "";

					address = (String) fylist
							.get(k + fylist.indexOf("ADDRESS"));
					address = address != null ? address : "";

					property = (String) fylist.get(k
							+ fylist.indexOf("PROPERTY"));
					property = property != null ? property : "";

					jztype = (String) fylist.get(k + fylist.indexOf("JZTYPE"));
					jztype = jztype != null ? jztype : "";

					stationtype = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					stationtype = stationtype != null ? stationtype : "";

					gxxx = (String) fylist.get(k + fylist.indexOf("GXXX"));
					gxxx = gxxx != null ? gxxx : "";

					zdcq = (String) fylist.get(k + fylist.indexOf("ZDCQ"));
					zdcq = zdcq != null ? zdcq : "";

					yflx = (String) fylist.get(k + fylist.indexOf("YFLX"));
					yflx = yflx != null ? yflx : "";

					gsf = (String) fylist.get(k + fylist.indexOf("GSF"));
					gsf = gsf != null ? gsf : "";

					fzr = (String) fylist.get(k + fylist.indexOf("FZR"));
					fzr = fzr != null ? fzr : "";

					sydate = (String) fylist.get(k + fylist.indexOf("SYDATE"));
					sydate = sydate != null ? sydate : "";

					qyzt = (String) fylist.get(k + fylist.indexOf("QYZT"));
					qyzt = qyzt != null ? qyzt : "";

					lyjhjgs = (String) fylist
							.get(k + fylist.indexOf("LYJHJGS"));
					lyjhjgs = lyjhjgs != null ? lyjhjgs : "";

					dytype = (String) fylist.get(k + fylist.indexOf("DYTYPE"));
					dytype = dytype != null ? dytype : "";

					g2 = (String) fylist.get(k + fylist.indexOf("G2"));
					g2 = g2 != null ? g2 : "";

					g3 = (String) fylist.get(k + fylist.indexOf("G3"));
					g3 = g3 != null ? g3 : "";

					kdsb = (String) fylist.get(k + fylist.indexOf("KDSB"));
					kdsb = kdsb != null ? kdsb : "";

					yysb = (String) fylist.get(k + fylist.indexOf("YYSB"));
					yysb = yysb != null ? yysb : "";

					zpsl = (String) fylist.get(k + fylist.indexOf("ZPSL"));
					zpsl = zpsl != null ? zpsl : "";

					zssl = (String) fylist.get(k + fylist.indexOf("ZSSL"));
					zssl = zssl != null ? zssl : "";

					kdsbsl = (String) fylist.get(k + fylist.indexOf("KDSBSL"));
					kdsbsl = kdsbsl != null ? kdsbsl : "";

					yysbsl = (String) fylist.get(k + fylist.indexOf("YYSBSL"));
					yysbsl = yysbsl != null ? yysbsl : "";

					kt1 = (String) fylist.get(k + fylist.indexOf("KT1"));
					kt1 = kt1 != null ? kt1 : "";

					kt2 = (String) fylist.get(k + fylist.indexOf("KT2"));
					kt2 = kt2 != null ? kt2 : "";

					zlfh = (String) fylist.get(k + fylist.indexOf("ZLFH"));
					zlfh = zlfh != null ? zlfh : "";

					jlfh = (String) fylist.get(k + fylist.indexOf("JLFH"));
					jlfh = jlfh != null ? jlfh : "";

					zgd = (String) fylist.get(k + fylist.indexOf("ZGD"));
					zgd = zgd != null ? zgd : "";

					memo = (String) fylist.get(k + fylist.indexOf("MEMO"));
					memo = memo != null ? memo : "";

					dianfei = (String) fylist
							.get(k + fylist.indexOf("DIANFEI"));
					dianfei = dianfei != null ? dianfei : "";

					bgsign = (String) fylist.get(k + fylist.indexOf("BGSIGN"));
					bgsign = bgsign != null ? bgsign : "";

					jnglmk = (String) fylist.get(k + fylist.indexOf("JNGLMK"));
					jnglmk = jnglmk != null ? jnglmk : "";

					gdfs = (String) fylist.get(k + fylist.indexOf("GDFS"));
					gdfs = gdfs != null ? gdfs : "";

					edhdl = (String) fylist.get(k + fylist.indexOf("EDHDL"));
					edhdl = edhdl != null ? edhdl : "";

					fkzq = (String) fylist.get(k + fylist.indexOf("FKZQ"));
					fkzq = fkzq != null ? fkzq : "";

					dbid = (String) fylist.get(k + fylist.indexOf("DBID"));
					dbid = dbid != null ? dbid : "";

					dfzflx = (String) fylist.get(k + fylist.indexOf("DFZFLX"));
					dfzflx = dfzflx != null ? dfzflx : "";

					beilv = (String) fylist.get(k + fylist.indexOf("BEILV"));
					beilv = beilv != null ? beilv : "";

					dbyt = (String) fylist.get(k + fylist.indexOf("DBYT"));
					dbyt = dbyt != null ? dbyt : "";

					fkfs = (String) fylist.get(k + fylist.indexOf("FKFS"));
					fkfs = fkfs != null ? fkfs : "";

					dbzbdyhh = (String) fylist.get(k
							+ fylist.indexOf("DBZBDYHH"));
					dbzbdyhh = dbzbdyhh != null ? dbzbdyhh : "";

					watchcost = (String) fylist.get(k
							+ fylist.indexOf("WATCHCOST"));
					watchcost = watchcost != null ? watchcost : "";

					linelosstype = (String) fylist.get(k
							+ fylist.indexOf("LINELOSSTYPE"));
					linelosstype = linelosstype != null ? linelosstype : "";

					linelossvalue = (String) fylist.get(k
							+ fylist.indexOf("LINELOSSVALUE"));
					linelossvalue = linelossvalue != null ? linelossvalue : "";

					csds = (String) fylist.get(k + fylist.indexOf("CSDS"));
					csds = csds != null ? csds : "";

					cssytime = (String) fylist.get(k
							+ fylist.indexOf("CSSYTIME"));
					cssytime = cssytime != null ? cssytime : "";

					sc = (String) fylist.get(k + fylist.indexOf("SC"));
					sc = sc != null ? sc : "";

					yy = (String) fylist.get(k + fylist.indexOf("YY"));
					yy = yy != null ? yy : "";

					bg = (String) fylist.get(k + fylist.indexOf("BG"));
					bg = bg != null ? bg : "";

					xxh = (String) fylist.get(k + fylist.indexOf("XXH"));
					xxh = xxh != null ? xxh : "";

					jstz = (String) fylist.get(k + fylist.indexOf("JSTZ"));
					jstz = jstz != null ? jstz : "";

					dddf = (String) fylist.get(k + fylist.indexOf("DDDF"));
					dddf = dddf != null ? dddf : "";

					HSSFRow row = sheet.createRow(i);

					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(sheng);

					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(shi);

					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(xian);

					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(xiaoqu);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(jzname);

					HSSFCell Cell5 = row.createCell(5);
					Cell5.setCellValue(zdcode);

					HSSFCell Cell6 = row.createCell(6);
					Cell6.setCellValue(yid);

					HSSFCell Cell7 = row.createCell(7);
					Cell7.setCellValue(bieming);

					HSSFCell Cell8 = row.createCell(8);
					Cell8.setCellValue(area);

					HSSFCell Cell9 = row.createCell(9);
					Cell9.setCellValue(address);

					HSSFCell Cell10 = row.createCell(10);
					Cell10.setCellValue(property);
					HSSFCell Cell11 = row.createCell(11);
					Cell11.setCellValue(jztype);

					HSSFCell Cell12 = row.createCell(12);
					Cell12.setCellValue(stationtype);

					HSSFCell Cell13 = row.createCell(13);
					Cell13.setCellValue(gxxx);

					HSSFCell Cell14 = row.createCell(14);
					Cell14.setCellValue(zdcq);

					HSSFCell Cell15 = row.createCell(15);
					Cell15.setCellValue(yflx);

					HSSFCell Cell16 = row.createCell(16);
					Cell16.setCellValue(gsf);

					HSSFCell Cell17 = row.createCell(17);
					Cell17.setCellValue(fzr);

					HSSFCell Cell18 = row.createCell(18);
					Cell18.setCellValue(sydate);

					HSSFCell Cell19 = row.createCell(19);
					Cell19.setCellValue(qyzt);

					HSSFCell Cell20 = row.createCell(20);
					Cell20.setCellValue(lyjhjgs);

					HSSFCell Cell21 = row.createCell(21);
					Cell21.setCellValue(dytype);

					HSSFCell Cell22 = row.createCell(22);
					Cell22.setCellValue(g2);

					HSSFCell Cell23 = row.createCell(23);
					Cell23.setCellValue(g3);

					HSSFCell Cell24 = row.createCell(24);
					Cell24.setCellValue(kdsb);

					HSSFCell Cell25 = row.createCell(25);
					Cell25.setCellValue(yysb);

					HSSFCell Cell26 = row.createCell(26);
					Cell26.setCellValue(zpsl);

					HSSFCell Cell27 = row.createCell(27);
					Cell27.setCellValue(zssl);

					HSSFCell Cell28 = row.createCell(28);
					Cell28.setCellValue(kdsbsl);

					HSSFCell Cell29 = row.createCell(29);
					Cell29.setCellValue(yysbsl);

					HSSFCell Cell30 = row.createCell(30);
					Cell30.setCellValue(kt1);

					HSSFCell Cell31 = row.createCell(31);
					Cell31.setCellValue(kt2);

					HSSFCell Cell32 = row.createCell(32);
					Cell32.setCellValue(zlfh);

					HSSFCell Cell33 = row.createCell(33);
					Cell33.setCellValue(jlfh);

					HSSFCell Cell34 = row.createCell(34);
					Cell34.setCellValue(zgd);

					HSSFCell Cell35 = row.createCell(35);
					Cell35.setCellValue(memo);

					HSSFCell Cell36 = row.createCell(36);
					Cell36.setCellValue(dianfei);

					HSSFCell Cell37 = row.createCell(37);
					Cell37.setCellValue(bgsign);

					HSSFCell Cell38 = row.createCell(38);
					Cell38.setCellValue(jnglmk);

					HSSFCell Cell39 = row.createCell(39);
					Cell39.setCellValue(gdfs);

					HSSFCell Cell40 = row.createCell(40);
					Cell40.setCellValue(edhdl);

					HSSFCell Cell41 = row.createCell(41);
					Cell41.setCellValue(fkzq);

					HSSFCell Cell42 = row.createCell(42);
					Cell42.setCellValue(dbid);

					HSSFCell Cell43 = row.createCell(43);
					Cell43.setCellValue(dfzflx);

					HSSFCell Cell44 = row.createCell(44);
					Cell44.setCellValue(beilv);

					HSSFCell Cell45 = row.createCell(45);
					Cell45.setCellValue(dbyt);

					HSSFCell Cell46 = row.createCell(46);
					Cell46.setCellValue(fkfs);

					HSSFCell Cell47 = row.createCell(47);
					Cell47.setCellValue(dbzbdyhh);

					HSSFCell Cell48 = row.createCell(48);
					Cell48.setCellValue(watchcost);

					HSSFCell Cell49 = row.createCell(49);
					Cell49.setCellValue(linelosstype);

					HSSFCell Cell50 = row.createCell(50);
					Cell50.setCellValue(linelossvalue);

					HSSFCell Cell51 = row.createCell(51);
					Cell51.setCellValue(csds);

					HSSFCell Cell52 = row.createCell(52);
					Cell52.setCellValue(cssytime);

					HSSFCell Cell53 = row.createCell(53);
					Cell53.setCellValue(sc);

					HSSFCell Cell54 = row.createCell(54);
					Cell54.setCellValue(xxh);

					HSSFCell Cell55 = row.createCell(55);
					Cell55.setCellValue(bg);

					HSSFCell Cell56 = row.createCell(56);
					Cell56.setCellValue(yy);

					HSSFCell Cell57 = row.createCell(57);
					Cell57.setCellValue(jstz);

					HSSFCell Cell58 = row.createCell(58);
					Cell58.setCellValue(dddf);

					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	public String writeFileyid(String creatFilePath, String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	// ==
	// �����ݺ�ͬ��
	public String writeFilehtd(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			String sszy = SelPermissionCconfiguration("PJLX", "");
			String[] sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			// System.out.println("010000000:"+fylist);
			String xianname = "", xiaoquname = "", jzname = "", ammeterid = "", zdcode = "", startmonth = "", endmonth = "", stationtype = "", qsyf = "", xsyf = "";
			int i = 2;
			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				System.out.println("3333333333:" + fycount);
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������
					// System.out.println("5555555555:"+k);
					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					zdcode = (String) fylist.get(k + fylist.indexOf("ID"));
					zdcode = zdcode != null ? zdcode : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("AGNAME"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					stationtype = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					stationtype = stationtype != null ? stationtype : "";

					ammeterid = (String) fylist.get(k + fylist.indexOf("DBID"));
					ammeterid = ammeterid != null ? ammeterid : "";

					endmonth = (String) fylist.get(k
							+ fylist.indexOf("ENDMONTH"));
					endmonth = endmonth != null ? endmonth : "";
					qsyf = (String) fylist.get(k + fylist.indexOf("CSSYTIME"));
					qsyf = qsyf != null ? qsyf : "";
					if (null != endmonth && !"".equals(endmonth)) {
						xsyf = endmonth;
					} else {
						if (null != qsyf && !"".equals(qsyf)) {
							xsyf = qsyf.substring(0, 7);
						} else {
							xsyf = "";
						}
					}
					// System.out.println("lastdatetime"+lastdatetime);
					HSSFRow row = sheet.createRow(i);
					// System.out.println("6666666i6666:"+i);

					HSSFCell Cell0 = row.createCell(0);
					// ID
					Cell0.setCellValue(xianname);

					HSSFCell Cell1 = row.createCell(1);

					// վ�����
					Cell1.setCellValue(xiaoquname);
					// ���ID
					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(jzname);
					// ����ID
					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(stationtype);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(zdcode);

					HSSFCell Cell5 = row.createCell(5);
					Cell5.setCellValue(ammeterid);

					HSSFCell Cell7 = row.createCell(7);
					Cell7.setCellValue(xsyf);

					if ("��ͬ����������ģ��".equals(templetname)) {
						// System.out.println("010000000:"+templetname);
						// Ʊ������
						// CellRangeAddressList regions = new
						// CellRangeAddressList(i,i,20,20);

						// �������������

						// DVConstraint constraint =
						// DVConstraint.createExplicitListConstraint(sszyValue);

						// �����������������

						// HSSFDataValidation data_validation = new
						// HSSFDataValidation(regions,constraint);

						// ��sheetҳ��Ч

						// sheet.addValidationData(data_validation);
					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}

	// ������վ�㵥���޸�
	public String writeFiledj(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			// String sszy = SelPermissionCconfiguration("PJLX","");
			// String[]sszyValue = sszy.split(",");

			HSSFSheet sheet = workbook.getSheetAt(0);
			// System.out.println("010000000:"+fylist);
			String szdq = "", jzname = "", zdcode = "", dianfei = "", stationtype = "";

			int i = 2;
			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				System.out.println("3333333333:" + fycount);
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������
					// System.out.println("5555555555:"+k);
					szdq = (String) fylist.get(k + fylist.indexOf("SZDQ"));
					szdq = szdq != null ? szdq : "";

					zdcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
					zdcode = zdcode != null ? zdcode : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					stationtype = (String) fylist.get(k
							+ fylist.indexOf("STATIONTYPE"));
					stationtype = stationtype != null ? stationtype : "";

					dianfei = (String) fylist
							.get(k + fylist.indexOf("DIANFEI"));
					dianfei = dianfei != null ? dianfei : "";

					// System.out.println("lastdatetime"+lastdatetime);
					HSSFRow row = sheet.createRow(i);
					// System.out.println("6666666i6666:"+i);

					HSSFCell Cell0 = row.createCell(0);
					// ID
					Cell0.setCellValue(zdcode);

					// ���ID
					HSSFCell Cell2 = row.createCell(1);
					Cell2.setCellValue(jzname);

					HSSFCell Cell3 = row.createCell(2);
					Cell3.setCellValue(stationtype);

					HSSFCell Cell4 = row.createCell(3);
					Cell4.setCellValue(szdq);

					HSSFCell Cell5 = row.createCell(4);
					Cell5.setCellValue(dianfei);

					if ("վ�㵥�������޸ĵ���ģ��".equals(templetname)) {
						// System.out.println("010000000:"+templetname);
						// Ʊ������
						// CellRangeAddressList regions = new
						// CellRangeAddressList(i,i,20,20);

						// �������������

						// DVConstraint constraint =
						// DVConstraint.createExplicitListConstraint(sszyValue);

						// �����������������

						// HSSFDataValidation data_validation = new
						// HSSFDataValidation(regions,constraint);

						// ��sheetҳ��Ч

						// sheet.addValidationData(data_validation);
					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}


	/**����רҵ��̬�����
	 * @param itemname
	 * @param code
	 * @return
	 */
	public String SelPermissionCconfiguration(String itemname, String code) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select code,type,name from indexs  where 1=1 ");
		if (itemname != null && !"".equals(itemname)) {
			sql1.append(" and type ='" + itemname + "'");
		}
		if (code != null && !"".equals(code)) {
			sql1.append(" and code ='" + code + "'");
		}
		System.out.println("010000000:" + sql1);
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql1.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql1.toString());
				while (rs.next()) {
					ret.append(rs.getString("code") + ":");
					ret.append(rs.getString("name") + ",");
				}
			} catch (Exception de) {
				de.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
		}
		return ret.toString();
	}

	public String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	public String writeFilezdxgsqx(ArrayList fylist, String creatFilePath,
			String templetname, String bzw) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			HSSFSheet sheet = workbook.getSheetAt(0);
			String shiname = "", xianname = "", xiaoquname = "", zdid = "", jzname = "";
			int i = 1;
			if ("downloadzdcbglxx".equals(bzw)) {
				i = 1;
			} else if ("downloadzdcbglzgxx".equals(bzw)) {
				i = 2;
			}

			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������

					shiname = (String) fylist.get(k + fylist.indexOf("SHI"));
					shiname = shiname != null ? shiname : "";

					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("XIAOQU"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					zdid = (String) fylist.get(k + fylist.indexOf("ID"));
					zdid = zdid != null ? zdid : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					HSSFRow row = sheet.createRow(i);

					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(shiname);

					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(xianname);

					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(xiaoquname);

					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(zdid);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(jzname);

					if ("�Ա������������ģ��".equals(templetname)) {

					}
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}
	public String writeFilepriceover(ArrayList fylist, String creatFilePath,
			String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// ��excel�ļ�תΪ������
			POIFSFileSystem fs = new POIFSFileSystem(in);// ����POIFSFileSystem�����������������
			workbook = new HSSFWorkbook(fs);// ������workbook�����POIFSFileSystem����
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			HSSFSheet sheet = workbook.getSheetAt(0);
			String shiname = "", xianname = "", xiaoquname = "", zdid = "", jzname = "";
			int i = 1;

			if (fylist != null) {
				int fycount = ((Integer) fylist.get(0)).intValue();
				for (int k = fycount; k < fylist.size() - 1; k += fycount) {

					// numΪ��ţ���ͬҳ������������

					shiname = (String) fylist.get(k + fylist.indexOf("SHI"));
					shiname = shiname != null ? shiname : "";

					xianname = (String) fylist.get(k + fylist.indexOf("XIAN"));
					xianname = xianname != null ? xianname : "";

					xiaoquname = (String) fylist.get(k
							+ fylist.indexOf("XIAOQU"));
					xiaoquname = xiaoquname != null ? xiaoquname : "";

					zdid = (String) fylist.get(k + fylist.indexOf("ZDID"));
					zdid = zdid != null ? zdid : "";

					jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
					jzname = jzname != null ? jzname : "";

					HSSFRow row = sheet.createRow(i);

					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(shiname);

					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(xianname);

					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(xiaoquname);

					HSSFCell Cell3 = row.createCell(3);
					Cell3.setCellValue(zdid);

					HSSFCell Cell4 = row.createCell(4);
					Cell4.setCellValue(jzname);
					i = i + 1;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
			System.out.println("dataFilePath:" + dataFilePath);
			try {
				out = new FileOutputStream(dataFilePath);
				workbook.write(out);
			} catch (IOException e) {
				System.out.println(e.toString());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		return dataFilePath;
	}
}
