package com.noki.bgfenxi.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noki.bgfenxi.Model.DxdffxBean;
import com.noki.bgfenxi.manage.Dxdffxmanage;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.DxdfftqrBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.servlet.WydfftDownload;

public class DxdfftresultDownload extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String xiaoqu = request.getParameter("xiaoqu");
		String zdmc = (String) request.getSession().getAttribute("zdmc");
		String zdbm = (String) request.getSession().getAttribute("zdbm");
		System.out.println(zdmc+zdbm);
		String loginName = (String) request.getSession().getAttribute("loginName");
		String sheng = (String) request.getSession().getAttribute("accountSheng");
		Account account = (Account) request.getSession().getAttribute("account");
		String loginId = account.getAccountId();
		Dxdffxmanage wyManage = new Dxdffxmanage();
		List<DxdffxBean> wydfftList=null;
		try {
			wydfftList = wyManage.searchdxdfftqr(loginName,loginId,9999999,1,sheng,shi,xian,xiaoqu,zdmc,zdbm);
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 创建一个新的excel
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建sheet页
		XSSFSheet sheet = wb.createSheet("Sheet1");
		// 创建3行
		XSSFRow[] row = new XSSFRow[wydfftList.size()+1];
		row[0] = sheet.createRow(0);
		XSSFCell[] headerCell_head = new XSSFCell[11];
		headerCell_head[0] = row[0].createCell(0);
		headerCell_head[0].setCellValue(new XSSFRichTextString("序号"));
//		headerCell_head[1] = row[0].createCell(1);
//		headerCell_head[1].setCellValue(new XSSFRichTextString("批次"));
//		headerCell_head[2] = row[0].createCell(2);
//		headerCell_head[2].setCellValue(new XSSFRichTextString("确认状态"));
		headerCell_head[1] = row[0].createCell(1);
		headerCell_head[1].setCellValue(new XSSFRichTextString("城市"));
		headerCell_head[2] = row[0].createCell(2);
		headerCell_head[2].setCellValue(new XSSFRichTextString("区县"));
		headerCell_head[3] = row[0].createCell(3);
		headerCell_head[3].setCellValue(new XSSFRichTextString("站点名称"));
		headerCell_head[4] = row[0].createCell(4);
		headerCell_head[4].setCellValue(new XSSFRichTextString("站点编码"));
		headerCell_head[5] = row[0].createCell(5);
		headerCell_head[5].setCellValue(new XSSFRichTextString("站址类型"));
		headerCell_head[6] = row[0].createCell(6);
		headerCell_head[6].setCellValue(new XSSFRichTextString("供电方式"));
		headerCell_head[7] = row[0].createCell(7);
		headerCell_head[7].setCellValue(new XSSFRichTextString("结算方式"));
		headerCell_head[8] = row[0].createCell(8);
		headerCell_head[8].setCellValue(new XSSFRichTextString("周期（天）"));
		headerCell_head[9] = row[0].createCell(9);
		headerCell_head[9].setCellValue(new XSSFRichTextString("缴费总金额（元）"));
		headerCell_head[10] = row[0].createCell(10);
		headerCell_head[10].setCellValue(new XSSFRichTextString("分摊总金额（元）"));
		

		for (int i = 0; i < wydfftList.size(); i++) {
			DxdffxBean wydfft = wydfftList.get(i);
			row[i + 1] = sheet.createRow(i + 1);
			XSSFCell[] headerCell = new XSSFCell[11];
			headerCell[0] = row[i + 1].createCell(0);
			headerCell[0].setCellValue(new XSSFRichTextString(wydfft.getRownum() + ""));
//			headerCell[1] = row[i + 1].createCell(1);
//			headerCell[1].setCellValue(new XSSFRichTextString(wydfft.getYEARMONTH()));
//			headerCell[2] = row[i + 1].createCell(2);
//			headerCell[2].setCellValue(new XSSFRichTextString(wydfft.getYYSZT()));
			headerCell[1] = row[i + 1].createCell(1);
			headerCell[1].setCellValue(new XSSFRichTextString(wydfft.getDw()));
			headerCell[2] = row[i + 1].createCell(2);
			headerCell[2].setCellValue(new XSSFRichTextString(wydfft.getQX()));
			headerCell[3] = row[i + 1].createCell(3);
			headerCell[3].setCellValue(new XSSFRichTextString(wydfft.getZDMC()));
			headerCell[4] = row[i + 1].createCell(4);
			headerCell[4].setCellValue(new XSSFRichTextString(wydfft.getZDBM()));
			headerCell[5] = row[i + 1].createCell(5);
			headerCell[5].setCellValue(new XSSFRichTextString(wydfft.getZdlx()));
			headerCell[6] = row[i + 1].createCell(6);
			headerCell[6].setCellValue(new XSSFRichTextString(wydfft.getGDFS()));
			headerCell[7] = row[i + 1].createCell(7);
			headerCell[7].setCellValue(new XSSFRichTextString(wydfft.getJSFS()));
			headerCell[8] = row[i + 1].createCell(8);
			headerCell[8].setCellValue(new XSSFRichTextString(wydfft.getTs()));
			headerCell[9] = row[i + 1].createCell(9);
			headerCell[9].setCellValue(new XSSFRichTextString(wydfft.getXfje()));
			headerCell[10] = row[i + 1].createCell(10);
			headerCell[10].setCellValue(new XSSFRichTextString(wydfft.getFTJE()));
		}
		FileOutputStream outputStream;
		String basePath = WydfftDownload.class.getClassLoader().getResource("").getPath();
		basePath = basePath.split("WEB-INF")[0] + "web/wyftdfsh/tempExcels/";
		String exportFileName = "电信电费单包干分析结果.xlsx";
		// 通过文件输出流生成Excel文件
		File file = new File(basePath + exportFileName);
		/**
		 * 输出信息，导出excel
		 * 
		 * response.setContentType("application/vnd.ms-excel;charset=UTF-8") ;
		 * response.setHeader("Content-Type", "application/octet-stream");
		 * OutputStream out = response.getOutputStream(); wb.write(out);
		 * out.close();
		 */
		try {
			outputStream = new FileOutputStream(file);
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.err.println("获取不到位置");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream  fis = new FileInputStream(file);  
        String filename=URLEncoder.encode(file.getName(),"utf-8"); //解决中文文件名下载后乱码的问题  
        byte[] b = new byte[fis.available()];  
        fis.read(b);  
        response.setCharacterEncoding("utf-8");  
        response.setHeader("Content-Disposition","attachment; filename="+filename+"");  
		OutputStream out = response.getOutputStream();
		out.write(b);
		out.flush();  
        out.close();
	}

}
