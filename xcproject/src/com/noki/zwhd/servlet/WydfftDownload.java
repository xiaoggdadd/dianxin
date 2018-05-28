package com.noki.zwhd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;

public class WydfftDownload extends HttpServlet {

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
		WyManage wyManage = new WyManage();
		List<WydfftBean> wydfftList = wyManage.searchWydfft_fy(loginName,
				loginId, 999999, 1, sheng, shi, xian, xiaoqu, hdzt, "0", pc,zdbm,null,null);
		// ����һ���µ�excel
		XSSFWorkbook wb = new XSSFWorkbook();
		// ����sheetҳ
		XSSFSheet sheet = wb.createSheet("Sheet1");
		// ����3��
		XSSFRow[] row = new XSSFRow[wydfftList.size() + 1];
		row[0] = sheet.createRow(0);
		XSSFCell[] headerCell_head = new XSSFCell[27];
		headerCell_head[0] = row[0].createCell(0);
		headerCell_head[0].setCellValue(new XSSFRichTextString("���"));
		headerCell_head[1] = row[0].createCell(1);
		headerCell_head[1].setCellValue(new XSSFRichTextString("����"));
		headerCell_head[2] = row[0].createCell(2);
		headerCell_head[2].setCellValue(new XSSFRichTextString("�˶�״̬"));
		headerCell_head[3] = row[0].createCell(3);
		headerCell_head[3].setCellValue(new XSSFRichTextString("ʡ�ݹ�˾"));
		headerCell_head[4] = row[0].createCell(4);
		headerCell_head[4].setCellValue(new XSSFRichTextString("���зֹ�˾"));
		headerCell_head[5] = row[0].createCell(5);
		headerCell_head[5].setCellValue(new XSSFRichTextString("վ������"));
		headerCell_head[6] = row[0].createCell(6);
		headerCell_head[6].setCellValue(new XSSFRichTextString("վ�����"));
		headerCell_head[7] = row[0].createCell(7);
		headerCell_head[7].setCellValue(new XSSFRichTextString("����"));
		headerCell_head[8] = row[0].createCell(8);
		headerCell_head[8].setCellValue(new XSSFRichTextString("����"));
		headerCell_head[9] = row[0].createCell(9);
		headerCell_head[9].setCellValue(new XSSFRichTextString("�ɷѽ��"));
		headerCell_head[10] = row[0].createCell(10);
		headerCell_head[10].setCellValue(new XSSFRichTextString("�ɷ�����"));
		headerCell_head[11] = row[0].createCell(11);
		headerCell_head[11].setCellValue(new XSSFRichTextString("���˱�ʶ"));
		headerCell_head[12] = row[0].createCell(12);
		headerCell_head[12].setCellValue(new XSSFRichTextString("�ɷ�Ʊ������"));
		headerCell_head[13] = row[0].createCell(13);
		headerCell_head[13].setCellValue(new XSSFRichTextString("���緽����"));
		headerCell_head[14] = row[0].createCell(14);
		headerCell_head[14].setCellValue(new XSSFRichTextString("�ͻ�"));
		headerCell_head[15] = row[0].createCell(15);
		headerCell_head[15].setCellValue(new XSSFRichTextString("��̯����(%)"));
		headerCell_head[16] = row[0].createCell(16);
		headerCell_head[16].setCellValue(new XSSFRichTextString("˰������(%)"));
		headerCell_head[17] = row[0].createCell(17);
		headerCell_head[17].setCellValue(new XSSFRichTextString("��̯���"));
		headerCell_head[18] = row[0].createCell(18);
		headerCell_head[18].setCellValue(new XSSFRichTextString("��Ʊ����"));
		headerCell_head[19] = row[0].createCell(19);
		headerCell_head[19].setCellValue(new XSSFRichTextString("��������"));
		headerCell_head[20] = row[0].createCell(20);
		headerCell_head[20].setCellValue(new XSSFRichTextString("����ԭ��"));
		headerCell_head[21] = row[0].createCell(21);
		headerCell_head[21].setCellValue(new XSSFRichTextString("������"));
		headerCell_head[22] = row[0].createCell(22);
		headerCell_head[22].setCellValue(new XSSFRichTextString("��ע"));
		headerCell_head[23] = row[0].createCell(23);
		headerCell_head[23].setCellValue(new XSSFRichTextString("��Ӧ���������"));
		headerCell_head[24] = row[0].createCell(24);
		headerCell_head[24].setCellValue(new XSSFRichTextString("��Ӧ����������"));
		headerCell_head[25] = row[0].createCell(25);
		headerCell_head[25].setCellValue(new XSSFRichTextString("�ϸ߽��"));
		headerCell_head[26] = row[0].createCell(26);
		headerCell_head[26].setCellValue(new XSSFRichTextString("����"));

		for (int i = 0; i < wydfftList.size(); i++) {
			WydfftBean wydfft = wydfftList.get(i);
			row[i + 1] = sheet.createRow(i + 1);
			XSSFCell[] headerCell = new XSSFCell[27];
			headerCell[0] = row[i + 1].createCell(0);
			headerCell[0].setCellValue(new XSSFRichTextString(wydfft
					.getRownum() + ""));
			headerCell[1] = row[i + 1].createCell(1);
			headerCell[1].setCellValue(new XSSFRichTextString(wydfft
					.getYEARMONTH()));
			headerCell[2] = row[i + 1].createCell(2);
			headerCell[2]
					.setCellValue(new XSSFRichTextString(wydfft.getHDZT()));
			headerCell[3] = row[i + 1].createCell(3);
			headerCell[3]
					.setCellValue(new XSSFRichTextString(wydfft.getSFGS()));
			headerCell[4] = row[i + 1].createCell(4);
			headerCell[4]
					.setCellValue(new XSSFRichTextString(wydfft.getDSFGS()));
			headerCell[5] = row[i + 1].createCell(5);
			headerCell[5]
					.setCellValue(new XSSFRichTextString(wydfft.getZDMC()));
			headerCell[6] = row[i + 1].createCell(6);
			headerCell[6]
					.setCellValue(new XSSFRichTextString(wydfft.getZDBM()));
			headerCell[7] = row[i + 1].createCell(7);
			headerCell[7].setCellValue(new XSSFRichTextString(wydfft.getDH()));
			headerCell[8] = row[i + 1].createCell(8);
			headerCell[8].setCellValue(new XSSFRichTextString(wydfft.getZQ()));
			headerCell[9] = row[i + 1].createCell(9);
			headerCell[9]
					.setCellValue(new XSSFRichTextString(wydfft.getJFJE()));
			headerCell[10] = row[i + 1].createCell(10);
			headerCell[10]
					.setCellValue(new XSSFRichTextString(wydfft.getJFRQ()));
			headerCell[11] = row[i + 1].createCell(11);
			headerCell[11]
					.setCellValue(new XSSFRichTextString(wydfft.getXZBS()));
			headerCell[12] = row[i + 1].createCell(12);
			headerCell[12].setCellValue(new XSSFRichTextString(wydfft
					.getJFPJLX()));
			headerCell[13] = row[i + 1].createCell(13);
			headerCell[13].setCellValue(new XSSFRichTextString(wydfft
					.getGDFMC()));
			headerCell[14] = row[i + 1].createCell(14);
			headerCell[14].setCellValue(new XSSFRichTextString(wydfft.getKH()));
			headerCell[15] = row[i + 1].createCell(15);
			headerCell[15]
					.setCellValue(new XSSFRichTextString(wydfft.getFTBL()));
			headerCell[16] = row[i + 1].createCell(16);
			headerCell[16]
					.setCellValue(new XSSFRichTextString(wydfft.getFSYZ()));
			headerCell[17] = row[i + 1].createCell(17);
			headerCell[17]
					.setCellValue(new XSSFRichTextString(wydfft.getFTJE()));
			headerCell[18] = row[i + 1].createCell(18);
			headerCell[18]
					.setCellValue(new XSSFRichTextString(wydfft.getKPLX()));
			headerCell[19] = row[i + 1].createCell(19);
			headerCell[19].setCellValue(new XSSFRichTextString(wydfft
					.getTZHJE()));
			headerCell[20] = row[i + 1].createCell(20);
			headerCell[20]
					.setCellValue(new XSSFRichTextString(wydfft.getTZYY()));
			headerCell[21] = row[i + 1].createCell(21);
			headerCell[21]
					.setCellValue(new XSSFRichTextString(wydfft.getTZR()));
			headerCell[22] = row[i + 1].createCell(22);
			headerCell[22].setCellValue(new XSSFRichTextString(wydfft.getBZ()));
			headerCell[23] = row[i + 1].createCell(23);
			headerCell[23].setCellValue(new XSSFRichTextString(wydfft
					.getDYCWBXJE()));
			headerCell[24] = row[i + 1].createCell(24);
			headerCell[24].setCellValue(new XSSFRichTextString(wydfft
					.getDYCWHXJE()));
			headerCell[25] = row[i + 1].createCell(25);
			headerCell[25]
					.setCellValue(new XSSFRichTextString(wydfft.getJGJE()));
			headerCell[26] = row[i + 1].createCell(26);
			headerCell[26].setCellValue(new XSSFRichTextString(wydfft.getCY()));
		}

		FileOutputStream outputStream;
		String basePath = WydfftDownload.class.getClassLoader().getResource("")
				.getPath();
		basePath = basePath.split("WEB-INF")[0] + "web/wyftdfsh/tempExcels/";
		String exportFileName = "��ҵ���ݵ�ѷ�̯.xlsx";
		// ͨ���ļ����������Excel�ļ�
		File file = new File(basePath + exportFileName);
		/**
		 * �����Ϣ������excel
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
			System.err.println("��ȡ����λ��");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream  fis = new FileInputStream(file);  
        String filename=URLEncoder.encode(file.getName(),"utf-8"); //��������ļ������غ����������  
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
