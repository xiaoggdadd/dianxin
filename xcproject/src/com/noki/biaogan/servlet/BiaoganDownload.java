package com.noki.biaogan.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noki.biaogan.BiaoganManage;
import com.noki.biaogan.model.BiaoganBean;
import com.noki.zwhd.servlet.WydfftDownload;

public class BiaoganDownload extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String yearmonth = request.getParameter("yearmonth");
		String gjz = request.getParameter("gjz");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT c.YEARMONTH,z.JZNAME,b.DBBM,c.ZLFH,c.YDXS,c.KTXS,c.ZQ,c.biaoganvalue"
				+ " FROM zhandian z,dianbiao b left join tbl_tt_biaogan_lishi c on b.dbid = c.dbid"
				+ " WHERE b.ZDID = z.ID");
		System.out.println(sql.toString());
		if (xian == null || xian.equals("0")) {
			if (shi == null || shi.equals("0")) {

			} else {
				sql.append(" and z.shi = '" + shi + "'");
			}
		} else {
			sql.append(" and z.xian = '" + xian + "'");
		}

		if (yearmonth == null||yearmonth.equals("")) {

		} else {
			sql.append(" and c.YEARMONTH = '" + yearmonth + "'");
		}

		if (gjz == null||gjz.equals("")) {

		} else {
			sql.append(" and (z.JZNAME = '" + gjz + "' or z.JZCODE = '" + gjz
					+ "')");
		}

		sql.append(" ORDER BY c.YEARMONTH desc");

		//BiaoganManage manage = new BiaoganManage();
		//ArrayList<BiaoganBean> biaoganlist = manage.getBiaoganListForExcl(shi,xian, yearmonth, gjz);

		request.setAttribute("beanType", "com.noki.biaogan.model.BiaoganExcelBean");
		request.setAttribute("sql", sql.toString());
		request.setAttribute("head", "年度月份,基站名称,电表编号,单日直流负荷,空调系数,用电系数,账期,标杆值");
		request.setAttribute("path", "web/sdttWeb/BiaoganManager/tempExcels/");
		request.setAttribute("fileName","基站标杆管理.xlsx");
		
		request.getRequestDispatcher("ExcelDownload").forward(request, response);

//		// 创建一个新的excel
//		XSSFWorkbook wb = new XSSFWorkbook();
//		// 创建sheet页
//		XSSFSheet sheet = wb.createSheet("Sheet1");
//		// 创建3行
//		XSSFRow[] row = new XSSFRow[biaoganlist.size() + 1];
//		row[0] = sheet.createRow(0);
//		XSSFCell[] headerCell_head = new XSSFCell[9];
//		headerCell_head[0] = row[0].createCell(0);
//		headerCell_head[0].setCellValue(new XSSFRichTextString("序号"));
//		headerCell_head[1] = row[0].createCell(1);
//		headerCell_head[1].setCellValue(new XSSFRichTextString("年度月份"));
//		headerCell_head[2] = row[0].createCell(2);
//		headerCell_head[2].setCellValue(new XSSFRichTextString("基站名称"));
//		headerCell_head[3] = row[0].createCell(3);
//		headerCell_head[3].setCellValue(new XSSFRichTextString("电表编号"));
//		headerCell_head[4] = row[0].createCell(4);
//		headerCell_head[4].setCellValue(new XSSFRichTextString("单日直流负荷"));
//		headerCell_head[5] = row[0].createCell(5);
//		headerCell_head[5].setCellValue(new XSSFRichTextString("空调系数"));
//		headerCell_head[6] = row[0].createCell(6);
//		headerCell_head[6].setCellValue(new XSSFRichTextString("用电系数"));
//		headerCell_head[7] = row[0].createCell(7);
//		headerCell_head[7].setCellValue(new XSSFRichTextString("账期"));
//		headerCell_head[8] = row[0].createCell(8);
//		headerCell_head[8].setCellValue(new XSSFRichTextString("标杆值"));
//
//		for (int i = 0; i < biaoganlist.size(); i++) {
//			BiaoganBean bianganBean = biaoganlist.get(i);
//			row[i + 1] = sheet.createRow(i + 1);
//			XSSFCell[] headerCell = new XSSFCell[9];
//			headerCell[0] = row[i + 1].createCell(0);
//			headerCell[0].setCellValue(new XSSFRichTextString(i + ""));
//
//			headerCell[1] = row[i + 1].createCell(1);
//			headerCell[1].setCellValue(new XSSFRichTextString(bianganBean
//					.getYEARMONTH()));
//
//			headerCell[2] = row[i + 1].createCell(2);
//			headerCell[2].setCellValue(new XSSFRichTextString(bianganBean
//					.getJZNAME()));
//
//			headerCell[3] = row[i + 1].createCell(3);
//			headerCell[3].setCellValue(new XSSFRichTextString(bianganBean
//					.getDBBM()));
//
//			headerCell[4] = row[i + 1].createCell(4);
//			headerCell[4].setCellValue(new XSSFRichTextString(bianganBean
//					.getZLFH()));
//
//			headerCell[5] = row[i + 1].createCell(5);
//			headerCell[5].setCellValue(new XSSFRichTextString(bianganBean
//					.getKTXS()));
//
//			headerCell[6] = row[i + 1].createCell(6);
//			headerCell[6].setCellValue(new XSSFRichTextString(bianganBean
//					.getYDXS()));
//
//			headerCell[7] = row[i + 1].createCell(7);
//			headerCell[7].setCellValue(new XSSFRichTextString(bianganBean
//					.getZQ()));
//
//			headerCell[8] = row[i + 1].createCell(8);
//			headerCell[8].setCellValue(new XSSFRichTextString(bianganBean
//					.getBGVALUE()));
//		}
//
//		FileOutputStream outputStream;
//		String basePath = BiaoganDownload.class.getClassLoader()
//				.getResource("").getPath();
//		basePath = basePath.split("WEB-INF")[0]
//				+ "web/sdttWeb/BiaoganManager/tempExcels/";
//		String exportFileName = "基站标杆管理.xlsx";
//		// 通过文件输出流生成Excel文件
//		File file = new File(basePath + exportFileName);
//		if (!file.exists()) {
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		/**
//		 * 输出信息，导出excel
//		 * 
//		 * response.setContentType("application/vnd.ms-excel;charset=UTF-8") ;
//		 * response.setHeader("Content-Type", "application/octet-stream");
//		 * OutputStream out = response.getOutputStream(); wb.write(out);
//		 * out.close();
//		 */
//		try {
//			outputStream = new FileOutputStream(file);
//			wb.write(outputStream);
//			outputStream.flush();
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			System.err.println("获取不到位置");
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		FileInputStream fis = new FileInputStream(file);
//		String filename = URLEncoder.encode(file.getName(), "utf-8"); // 解决中文文件名下载后乱码的问题
//		byte[] b = new byte[fis.available()];
//		fis.read(b);
//		response.setCharacterEncoding("utf-8");
//		response.setHeader("Content-Disposition", "attachment; filename="
//				+ filename + "");
//		OutputStream out = response.getOutputStream();
//		out.write(b);
//		out.flush();
//		out.close();
//	}
	}
}
