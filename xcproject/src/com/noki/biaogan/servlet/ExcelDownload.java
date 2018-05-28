package com.noki.biaogan.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.util.Log;

import com.noki.biaogan.BiaoganManage;

public class ExcelDownload extends HttpServlet {
	private static org.apache.commons.logging.Log log = LogFactory
			.getLog(ExcelDownload.class.getName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String beanType = (String)request.getAttribute("beanType");
		String sql = (String)request.getAttribute("sql");
		sql=indextof(sql);
		String head = (String)request.getAttribute("head");
		String path = (String)request.getAttribute("path");//"web/sdttWeb/BiaoganManager/tempExcels/"
		String fileName = (String)request.getAttribute("fileName");//"基站标杆管理.xlsx"
		String[] header = head.split(",");

		BiaoganManage manage = new BiaoganManage();
		
		List<Object> objList = manage.doSqlSelect(beanType,sql);

		int CellCount = header.length;
		int RowCount = objList.size();
		
		log.info("[总列数]"+CellCount);
		log.info("[总行数]"+RowCount);
		
		// 创建一个新的excel
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建sheet页
		XSSFSheet sheet = wb.createSheet("Sheet1");
		
		
	//__________设置样式start____________	
		 //表头样式
        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        XSSFFont titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 25);//字体高度
       // titleFont.setColor(XSSFFont.COLOR_RED); //字体颜色
       // titleFont.setFontName("黑体"); //字体     
        titleFont.setBoldweight((short) 700);//字体宽度
        //titleFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); //字体加粗
        titleStyle.setFont(titleFont);
        // 列头样式
        XSSFCellStyle headerStyle = wb.createCellStyle();
       // headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//        headerStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
        XSSFFont headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerStyle.setFont(headerFont);
        // 单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
       // cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        XSSFFont cellFont = wb.createFont();
        cellFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        //设置单元格的行宽 列宽
        sheet.setDefaultRowHeightInPoints(15);//设置缺省值列高
        sheet.setDefaultColumnWidth(25);//设置缺省列宽
      //设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
      //sheet.setColumnWidth(cellFont.getIndex(), 256 * 50);

      //__________设置样式end____________	
		
		XSSFRow[] row = new XSSFRow[RowCount+1];
		row[0] = sheet.createRow(0);
		XSSFCell[] headerCell_head = new XSSFCell[CellCount];

		for (int i = 0; i < CellCount; i++) {
			headerCell_head[i] = row[0].createCell(i);
			headerCell_head[i].setCellValue(new XSSFRichTextString(header[i]));
			headerCell_head[i].setCellStyle(headerStyle);
		}

		
		for (int i = 0; i < RowCount; i++) {
			row[i+1] = sheet.createRow(i+1);
			XSSFCell[] headerCell = new XSSFCell[CellCount];
			Object obj = objList.get(i);
			Field[] field = obj.getClass().getDeclaredFields();
	//		log.info("[总列数矫正]"+field.length);
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				name = name.toUpperCase();
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = null;
					try {
						m = obj.getClass().getMethod("get" + name);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					String value = null;
					try {
						value = (String) m.invoke(obj);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} 
					// 调用getter方法获取属性值
					if (value != null) {
						headerCell[j] = row[i+1].createCell(j);
						headerCell[j].setCellValue(new XSSFRichTextString(value));
						headerCell[j].setCellStyle(cellStyle);
						 
					}
				}
			}
		}

		FileOutputStream outputStream;
		String basePath = BiaoganDownload.class.getClassLoader()
				.getResource("").getPath();
		basePath = basePath.split("WEB-INF")[0]
				+path ;
		String exportFileName = fileName;
		// 通过文件输出流生成Excel文件
		File file = new File(basePath + exportFileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

		FileInputStream fis = new FileInputStream(file);
		String filename = URLEncoder.encode(file.getName(), "utf-8"); // 解决中文文件名下载后乱码的问题
		byte[] b = new byte[fis.available()];
		fis.read(b);
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ filename + "");
		OutputStream out = response.getOutputStream();
		out.write(b);
		out.flush();
		out.close();
		
	}
	public  String indextof (String sql){
		System.out.println("替换前"+sql);
		       sql= sql.replace("‘", "'");
		       System.out.println("替换后"+sql);
		  return sql;
	}
	

}
