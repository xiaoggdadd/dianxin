package com.ptac.app.chinamobile.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.ptac.app.chinamobile.bean.ChinaMobileBillBean;
import com.ptac.app.chinamobile.dao.ChinaMobileBillCheck;
import com.ptac.app.chinamobile.dao.ChinaMobileBillCheckImp;
import com.ptac.app.inportuserzibaodian.util.Format;

public class TempletDownLoadServlet extends HttpServlet {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5505019185564112040L;

	/**
	 * Constructor of the object.
	 */
	public TempletDownLoadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		final String Content_type = "text/html;charset=UTF-8";
		resp.setContentType(Content_type);
		HttpSession session = req.getSession();
		Account account = new Account();
		account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		String action = req.getParameter("action");
		String templetname = req.getParameter("templetname");
		FileInputStream fis = null;
		ArrayList<ChinaMobileBillBean> fylist = new ArrayList<ChinaMobileBillBean>();
		String datafilepath = "";
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-disposition", "attachment;filename="
				+ toUtf8String(templetname + ".xls"));
		String filePath = req.getRealPath("/exceltemplet/" + templetname
				+ ".xls");
		if ("download".equals(action)) {
			ChinaMobileBillCheck bean = new ChinaMobileBillCheckImp();
			fylist = bean.getDownLoadData(loginId);
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
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * 字符串的转换
	 * 
	 * @param s
	 * @return
	 */
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

	/**
	 * 查询的数据写入
	 * 
	 * @param fylist
	 * @param creatFilePath
	 * @param templetname
	 * @return
	 */
	public String writeFilemm(ArrayList<ChinaMobileBillBean> fylist,
			String creatFilePath, String templetname) {
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		String dataFilePath = "";
		System.out.println(creatFilePath);
		try {
			in = new FileInputStream(creatFilePath);// 将excel文件转为输入流
			POIFSFileSystem fs = new POIFSFileSystem(in);// 构建POIFSFileSystem类对象，用输入流构建
			workbook = new HSSFWorkbook(fs);// 创建个workbook，根据POIFSFileSystem对象
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			HSSFSheet sheet = workbook.getSheetAt(0);
			String zdid, jzname, dianbiaid, startdegree, starttime, dianfei;
			int i = 1;
			if (fylist != null) {
				System.out.println("------" + fylist.size());
				for (ChinaMobileBillBean bean : fylist) {
					zdid = bean.getZdid();
					zdid = zdid != null ? zdid : "";
					jzname = bean.getJzname();
					jzname = jzname != null ? jzname : "";
					dianbiaid = bean.getDianbiaoid();
					dianbiaid = dianbiaid != null ? dianbiaid : "";
					startdegree = bean.getStartdegree() + "";
					startdegree = startdegree != null ? startdegree : "";
					starttime = bean.getStarttime();
					starttime = starttime != null ? starttime : "";
					if (Format.isTime(starttime)) {
						if (Format.isTime02(starttime)) {
							starttime = Format.getTime(starttime);
						}
					}
					dianfei = bean.getDianfei() + "";
					dianfei = dianfei != null ? dianfei : "";

					HSSFRow row = sheet.createRow(i);
					HSSFCell Cell0 = row.createCell(0);
					Cell0.setCellValue(zdid);
					HSSFCell Cell1 = row.createCell(1);
					Cell1.setCellValue(jzname);
					HSSFCell Cell2 = row.createCell(2);
					Cell2.setCellValue(dianbiaid);
					HSSFCell Cell3 = row.createCell(4);
					Cell3.setCellValue(startdegree);
					HSSFCell Cell4 = row.createCell(8);
					Cell4.setCellValue(starttime);

					HSSFCell Cell6 = row.createCell(10);
					Cell6.setCellValue(dianfei);

					i++;
				}
			}

			FileOutputStream out = null;
			dataFilePath = creatFilePath.substring(0,
					creatFilePath.length() - 4)
					+ "data.xls";
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
	 * 所属专业静态表检索
	 * 
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

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
