package com.noki.jtreport.sheng.download.javabean;

import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * IDC�������ܼ���Ӧ��������ܱ�.xls�����ߣ�
 * @author Administrator
 *
 */
public class IDCJFJNJSYYQKHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		Integer startRow = 0;
		Integer endRow = 2;
		WritableWorkbook firstBook = books.get(0);//��ȡ�׸�������Ϊ��������Ļ��ܴ�
		WritableSheet sheet = firstBook.getSheet(0);
		
		int flag = 0;
		for(WritableWorkbook book:books){
			Integer tmp = book.getSheet(0).getRows()-2;//��ǰsheet�����ݵ�����
			startRow = endRow;
			endRow += tmp;
			if(flag++ == 0)continue;//��һ����������
			
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
		}
	}
}
