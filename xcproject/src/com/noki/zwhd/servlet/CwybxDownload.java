package com.noki.zwhd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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

import com.noki.mobi.common.Account;
import com.noki.zwhd.manage.CwManage;
import com.noki.zwhd.model.CwybxBean;

public class CwybxDownload extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String xiaoqu = request.getParameter("xiaoqu");
		String hdzt = request.getParameter("hdzt");
		String pc = request.getParameter("pc");
		String zdbm = request.getParameter("zdbm");
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		String sheng = (String) request.getSession().getAttribute(
				"accountSheng");
		Account account = (Account) request.getSession()
				.getAttribute("account");
		String loginId = account.getAccountId();
		CwManage cwManage = new CwManage();
		List<CwybxBean> cwybxList = cwManage.searchCwybx_fy(loginName,loginId,9999999, 1,sheng,shi,xian,xiaoqu,hdzt,"0",pc,zdbm,null,null);
		// 创建一个新的excel
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建sheet页
		XSSFSheet sheet = wb.createSheet("Sheet1");
		// 创建3行
		XSSFRow[] row = new XSSFRow[cwybxList.size() + 1];
		row[0] = sheet.createRow(0);
		XSSFCell[] headerCell_head = new XSSFCell[21];
		headerCell_head[0] = row[0].createCell(0);
		headerCell_head[0].setCellValue(new XSSFRichTextString("序号"));
		headerCell_head[1] = row[0].createCell(1);
		headerCell_head[1].setCellValue(new XSSFRichTextString("批次"));
		headerCell_head[2] = row[0].createCell(2);
		headerCell_head[2].setCellValue(new XSSFRichTextString("核对状态"));
		headerCell_head[3] = row[0].createCell(3);
		headerCell_head[3].setCellValue(new XSSFRichTextString("日期"));
		headerCell_head[4] = row[0].createCell(4);
		headerCell_head[4].setCellValue(new XSSFRichTextString("报销类型"));
		headerCell_head[5] = row[0].createCell(5);
		headerCell_head[5].setCellValue(new XSSFRichTextString("单据编号"));
		headerCell_head[6] = row[0].createCell(6);
		headerCell_head[6].setCellValue(new XSSFRichTextString("摘要"));
		headerCell_head[7] = row[0].createCell(7);
		headerCell_head[7].setCellValue(new XSSFRichTextString("单据状态"));
		headerCell_head[8] = row[0].createCell(8);
		headerCell_head[8].setCellValue(new XSSFRichTextString("部门"));
		headerCell_head[9] = row[0].createCell(9);
		headerCell_head[9].setCellValue(new XSSFRichTextString("实付金额"));
		headerCell_head[10] = row[0].createCell(10);
		headerCell_head[10].setCellValue(new XSSFRichTextString("发票含税金额"));
		headerCell_head[11] = row[0].createCell(11);
		headerCell_head[11].setCellValue(new XSSFRichTextString("发票不含税金额"));
		headerCell_head[12] = row[0].createCell(12);
		headerCell_head[12].setCellValue(new XSSFRichTextString("缴费票据类型"));
		headerCell_head[13] = row[0].createCell(13);
		headerCell_head[13].setCellValue(new XSSFRichTextString("结算单号"));
		headerCell_head[14] = row[0].createCell(14);
		headerCell_head[14].setCellValue(new XSSFRichTextString("站点名称"));
		headerCell_head[15] = row[0].createCell(15);
		headerCell_head[15].setCellValue(new XSSFRichTextString("站点编号"));
		headerCell_head[16] = row[0].createCell(16);
		headerCell_head[16].setCellValue(new XSSFRichTextString("从物业分摊还原到财务报销金额"));
		headerCell_head[17] = row[0].createCell(17);
		headerCell_head[17].setCellValue(new XSSFRichTextString("差异"));
		headerCell_head[18] = row[0].createCell(18);
		headerCell_head[18].setCellValue(new XSSFRichTextString("物业分摊金额"));
		headerCell_head[19] = row[0].createCell(19);
		headerCell_head[19].setCellValue(new XSSFRichTextString("税负因子"));
		headerCell_head[20] = row[0].createCell(20);
		headerCell_head[20].setCellValue(new XSSFRichTextString("备注"));

		for (int i = 0; i < cwybxList.size(); i++) {
			CwybxBean cwybx = cwybxList.get(i);
			row[i + 1] = sheet.createRow(i + 1);
			XSSFCell[] headerCell = new XSSFCell[27];
			headerCell[0] = row[i + 1].createCell(0);
			headerCell[0].setCellValue(new XSSFRichTextString(cwybx
					.getRownum() + ""));
			headerCell[1] = row[i + 1].createCell(1);
			headerCell[1].setCellValue(new XSSFRichTextString(cwybx
					.getYEARMONTH()));
			headerCell[2] = row[i + 1].createCell(2);
			headerCell[2]
					.setCellValue(new XSSFRichTextString(cwybx.getHDZT()));
			headerCell[3] = row[i + 1].createCell(3);
			headerCell[3]
					.setCellValue(new XSSFRichTextString(cwybx.getRQ()));
			headerCell[4] = row[i + 1].createCell(4);
			headerCell[4]
					.setCellValue(new XSSFRichTextString(cwybx.getBXLX()));
			headerCell[5] = row[i + 1].createCell(5);
			headerCell[5]
					.setCellValue(new XSSFRichTextString(cwybx.getDJPH()));
			headerCell[6] = row[i + 1].createCell(6);
			headerCell[6]
					.setCellValue(new XSSFRichTextString(cwybx.getZY()));
			headerCell[7] = row[i + 1].createCell(7);
			headerCell[7].setCellValue(new XSSFRichTextString(cwybx.getDJZT()));
			headerCell[8] = row[i + 1].createCell(8);
			headerCell[8].setCellValue(new XSSFRichTextString(cwybx.getBM()));
			headerCell[9] = row[i + 1].createCell(9);
			headerCell[9]
					.setCellValue(new XSSFRichTextString(cwybx.getSFJE()));
			headerCell[10] = row[i + 1].createCell(10);
			headerCell[10]
					.setCellValue(new XSSFRichTextString(cwybx.getFPHSJE()));
			headerCell[11] = row[i + 1].createCell(11);
			headerCell[11]
					.setCellValue(new XSSFRichTextString(cwybx.getFPBHSJE()));
			headerCell[12] = row[i + 1].createCell(12);
			headerCell[12].setCellValue(new XSSFRichTextString(cwybx.getBXLX()));
			headerCell[13] = row[i + 1].createCell(13);
			headerCell[13].setCellValue(new XSSFRichTextString(cwybx.getJSDH()));
			headerCell[14] = row[i + 1].createCell(14);
			headerCell[14].setCellValue(new XSSFRichTextString(cwybx.getZDMC()));
			headerCell[15] = row[i + 1].createCell(15);
			headerCell[15]
					.setCellValue(new XSSFRichTextString(cwybx.getZDBH()));
			headerCell[16] = row[i + 1].createCell(16);
			headerCell[16]
					.setCellValue(new XSSFRichTextString(cwybx.getCWYFTHYDCWBXJE()));
			headerCell[17] = row[i + 1].createCell(17);
			headerCell[17]
					.setCellValue(new XSSFRichTextString(cwybx.getCY()));
			headerCell[18] = row[i + 1].createCell(18);
			headerCell[18]
					.setCellValue(new XSSFRichTextString(cwybx.getYWFTJE()));
			headerCell[19] = row[i + 1].createCell(19);
			headerCell[19].setCellValue(new XSSFRichTextString(cwybx.getFSYZ()));
			headerCell[20] = row[i + 1].createCell(20);
			headerCell[20]
					.setCellValue(new XSSFRichTextString(cwybx.getBZ()));
		}

		FileOutputStream outputStream;
		String basePath = WydfftDownload.class.getClassLoader().getResource("")
				.getPath();
		basePath = basePath.split("WEB-INF")[0] + "web/wyftdfsh/tempExcels/";
		String exportFileName = "财务数据已报销.xlsx";
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
