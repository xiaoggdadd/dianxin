package com.noki.jtreport.sheng.download.javabean;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.util.CTime;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 通信局房用电量汇总表.xls（表五）
 * @author Administrator
 *
 */
public class TXJFYDLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		Integer startRow = 0;
		Integer endRow = 4;
		WritableWorkbook firstBook = books.get(0);//获取首个报表，作为其他报表的汇总处
		WritableSheet sheet = firstBook.getSheet(0);
		
		WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
		wcf.setAlignment(jxl.format.Alignment.RIGHT);
		wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
		int flag = 0;
		
		ArrayList list=ReportCopyOperate.shixs();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String shi="";
		String shi1="";
		for(int e=0;e<books.size();e++){
			shi1 = books.get(0).getSheet(0).getCell(1,4).getContents();
			String shiName3 = books.get(e).getSheet(0).getCell(1,4).getContents().toString();
			list2.add(shiName3);
		}
		//获取excel中有数据的行数
		WritableSheet currentSheetyl = books.get(0).getSheet(0);
		int tmpyl=0;String resultyl="";
		for (int m = 4; m < currentSheetyl.getRows(); m++) {
             Cell cell = currentSheetyl.getCell(1, m);
             resultyl = cell.getContents();
             if (resultyl == null || resultyl.equals("")||resultyl.equals(" ")) {
            	 break;
             }
             tmpyl++;
		}
		for(int i=0;i<list.size();i++){
			shi=(String) list.get(i);
			boolean a = false;
			for(int n=0;n<books.size();n++){
				String shiName2 = books.get(n).getSheet(0).getCell(1,4).getContents();						
				for(int l=0;l<list2.size();l++){
					if(shi.equals(list2.get(l))){
						a = true;
					}
				}	
				WritableSheet currentSheet = books.get(n).getSheet(0);
				int tmp=0;String result="";

				//获取excel中有数据的行数
				for (int m = 4; m < currentSheet.getRows(); m++) {
	                 Cell cell = currentSheet.getCell(1, m);
	                 result = cell.getContents();
		             if (result == null || result.equals("")||result.equals(" ")) {
		            	 break;
		             }
	                 tmp++;
				}
				startRow = endRow;
				if(shi.equals(shi1)){
					endRow += tmpyl;
					flag=flag+1;
					list1.add(shi);
					break;//第一个报表跳过
				}else{
					if(shiName2.equals(shi)){						
						ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 4, 31, tmp+4, 0, startRow);

						list1.add(shi);
						endRow += tmp;
					}else if(!a){
						boolean b = true;
						for(int j=0;j<list1.size();j++){
							if(shi==list1.get(j)||shiName2==list1.get(j)){
								b = false;
							}
						}
						if(b){
							sheet.insertRow(startRow);
							Label label1 = new Label(0,startRow,"山东",wcf);
							Label label = new Label(1,startRow,list.get(i).toString(),wcf);
							sheet.addCell(label1);
							sheet.addCell(label);
							list1.add(shi);
							endRow += 1;
						}	
					}			
				}
				
				//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
			}	
		}
		
		
		
		
		/*for(WritableWorkbook book:books){
			//Integer tmp = book.getSheet(0).getRows()-4;//当前sheet中数据的条数
			WritableSheet currentSheet = book.getSheet(0);
			int n=0;String result="";
			for (int m = 4; m < currentSheet.getRows(); m++) {
	                 Cell cell = currentSheet.getCell(0, m);
	                  result = cell.getContents();
	                 System.out.println(result+"resultresultresult");
		             if (result == null || result.equals("")||result.equals(" ")) {
		            	 break;
		             }
		             n++;
				}
			startRow = endRow;
			endRow += n;
			if(flag++ == 0)continue;//第一个报表跳过
			
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 4, 31, n+4, 0, startRow);
		}*/
		for(int i=4;i<sheet.getRows();i++){
			String content1=sheet.getWritableCell(21,i).getContents();
			CTime time=new CTime();
			if(null==content1||content1.equals("")||content1.equals(" ")){
				break;
			}else{
				System.out.println("--"+content1);
				 if(content1.contains("-")||content1.contains("/")){
					 sheet.addCell(new Label(21,i,content1,wcf));
				 }
				 else{
					 content1=time.formatRealMonth(new Date(content1));
				     sheet.addCell(new Label(21,i,content1,wcf));
				 }
			 }
			String content=sheet.getWritableCell(22,i).getContents();
			if(null==content||content.equals("")||content.equals(" ")){
				break;
			}else{
				System.out.println("--"+content);
				 if(content.contains("-")||content.contains("/")){
					 sheet.addCell(new Label(22,i,content,wcf));
				 }
				 else{
					 content=time.formatRealDate(new Date(content));
				     sheet.addCell(new Label(22,i,content,wcf));
				 }
			 }
			
		}
	}
}
