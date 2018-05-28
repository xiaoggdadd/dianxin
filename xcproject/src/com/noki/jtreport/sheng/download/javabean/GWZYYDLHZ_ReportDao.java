package com.noki.jtreport.sheng.download.javabean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;

/**
 * ����רҵ�õ������ܱ�.xls(����)
 * @author Administrator
 *
 */
public class GWZYYDLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		
		String url = getUrl();
		url = url.substring(0,url.lastIndexOf("_"))+".xls";
		setUrl(url);
		
		InputStream is = new FileInputStream(getUrl());	//��ȡģ����
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//��ʱ�ļ�·��
		getTmpPathList().add(0,tmpPath);//����ʱ·��������ӵ�ǰ�ļ���·��
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//��ģ�����һ��WorkBook
		Integer sheetNum = newBook.getNumberOfSheets();
		for(int i=0;i<books.size();i++){
			String shiCode = getShiCode().get(i);
			String sql = "select t.agname from d_area_grade t where t.agcode = '"+shiCode+"'";
			ResultSet rs = null;
			rs = db.queryAll(sql);
			if(rs.next()){
				String shiName = rs.getString(1);
				for(int r = 0;r<sheetNum;r++){
					String sheetName = newBook.getSheet(r).getName();
					if(shiName.indexOf(sheetName)>-1){//�ҵ���Ӧ�ĳ�������
//						Sheet city = books.get(i).getSheet(0);
//						newBook.importSheet(sheetName,r,city);//����������-----------------------------
						ReportCopyOperate.copyCells(books.get(i).getSheet(0), newBook.getSheet(r), 0, 0, 55, 51, 0, 0);
					}
				}
			}
		}
		books.add(0,newBook);
	}
}
