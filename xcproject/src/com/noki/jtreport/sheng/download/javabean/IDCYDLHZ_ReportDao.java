package com.noki.jtreport.sheng.download.javabean;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.noki.util.CTime;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * IDC用电量汇总表.xls（表四）
 * @author Administrator
 *
 */
public class IDCYDLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		Integer startRow = 0;
		Integer endRow = 3;
		WritableWorkbook firstBook = books.get(0);//获取首个报表，作为其他报表的汇总处
		WritableSheet sheet = firstBook.getSheet(0);
		int flag = 0;
		
		NumberFormat nf = new NumberFormat("0.00");
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10);
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf,nf); 
		
		try {
			wcf.setAlignment(jxl.format.Alignment.RIGHT);
			wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		ArrayList list=ReportCopyOperate.shixs();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();
		String shi="";
		String shi1="";
		for(int e=0;e<books.size();e++){
			shi1 = books.get(0).getSheet(0).getCell(1,3).getContents();
			String shiName3 = books.get(e).getSheet(0).getCell(1,3).getContents().toString();
			list2.add(shiName3);
		}
		//获取excel中有数据的行数
		WritableSheet currentSheetyl = books.get(0).getSheet(0);
		for(int i=0;i<list.size();i++){
			shi=(String) list.get(i);//获取排序的市名
			boolean a = false;
			for(int n=0;n<books.size();n++){
				String shiName2 = books.get(n).getSheet(0).getCell(1,3).getContents();//获取有此报表 的市名			
				for(int l=0;l<list2.size();l++){  //有数据的报表的市名
					if(shi.equals(list2.get(l))){  //有数据的市名和排序的市名相等
						a = true;
					}
				}	
				WritableSheet currentSheet = books.get(n).getSheet(0);
				startRow = endRow;
				if(shi.equals(shi1)){
					int ii=0;
					//for(WritableWorkbook book:books){
						   for (int r = 3; r < books.get(n).getSheet(0).getRows(); r++) {
				                Vector rows = new Vector();
				                boolean isNull = true;
				                for (int j = 0; j < sheet.getColumns(); j++) {
				                    Cell cell = sheet.getCell(j, r);
				                    String result = cell.getContents();           
				                    rows.add(result);
				                    if (result != null && !result.equals("")&& !result.equals(" ")) {
				                        isNull = false;
				                    }
				                }
				                if (!isNull) {
				                	ii++;
				                }
				                else{
				                	System.out.println("行空 了  结束了");
				                	break;
				                }
				            }
						   System.out.println("--"+ii);
						//Integer tmp = book.getSheet(0).getRows()-3;//当前sheet中数据的条数
						 Integer tmp=ii;  
						startRow = endRow;
						endRow += tmp;
						//if(flag++ == 0)continue;//第一个报表跳过
						//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 31, tmp+3, 0, startRow);
					//}
					//endRow += 1;
					flag=flag+1;
					list1.add(shi);
					break;//第一个报表跳过
				}else{
					if(shiName2.equals(shi)){	
						int ii=0;
						//for(WritableWorkbook book:books){
							   for (int r = 3; r < books.get(n).getSheet(0).getRows(); r++) {
					                Vector rows = new Vector();
					                boolean isNull = true;
					                for (int j = 0; j < sheet.getColumns(); j++) {
					                    Cell cell = sheet.getCell(j, r);
					                    String result = cell.getContents();           
					                    rows.add(result);
					                    if (result != null && !result.equals("")&& !result.equals(" ")) {
					                        isNull = false;
					                    }
					                }
					                if (!isNull) {
					                	ii++;
					                }
					                else{
					                	System.out.println("行空 了  结束了");
					                	break;
					                }
					            }
							   System.out.println("--"+ii);
							//Integer tmp = book.getSheet(0).getRows()-3;//当前sheet中数据的条数
							 Integer tmp=ii;  
						
							startRow = endRow;
							endRow += tmp;
							//if(flag++ == 0)continue;//第一个报表跳过
							ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 3, 31, tmp+3, 0, startRow);
					//	}
						
						//ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 3, 31, 3, 0, startRow);

						list1.add(shi);
						//endRow += 1;
					}else if(!a){
						boolean b = true;
						for(int j=0;j<list1.size();j++){
							if(shi==list1.get(j)||shiName2==list1.get(j)){
								b = false;
							}
						}
						if(b){
							
							sheet.insertRow(startRow);
							Label label = new Label(1,startRow,list.get(i).toString(),wcf);
							sheet.addCell(label);
							list1.add(shi);
							endRow += 1;
						}	
					}			
				}
				
				//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
			}	
		}
		
		/*		int flag = 0;
		int ii=0;
		for(WritableWorkbook book:books){
			   for (int i = 3; i < book.getSheet(0).getRows(); i++) {
	                Vector rows = new Vector();
	                boolean isNull = true;
	                for (int j = 0; j < sheet.getColumns(); j++) {
	                    Cell cell = sheet.getCell(j, i);
	                    String result = cell.getContents();           
	                    rows.add(result);
	                    if (result != null && !result.equals("")&& !result.equals(" ")) {
	                        isNull = false;
	                    }
	                }
	                if (!isNull) {
	                	ii++;
	                }
	                else{
	                	System.out.println("行空 了  结束了");
	                	break;
	                }
	            }
			   System.out.println("--"+ii);
			//Integer tmp = book.getSheet(0).getRows()-3;//当前sheet中数据的条数
			 Integer tmp=ii;  
			System.out.println("---"+ book.getSheet(0).getRows());
			startRow = endRow;
			endRow += tmp;
			if(flag++ == 0)continue;//第一个报表跳过
			System.out.println("---"+ book.getSheet(0).getRows());
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 31, tmp+3, 0, startRow);
		}*/
		
		
		
		
		
		
		
		for(int i=3;i<sheet.getRows();i++){
			String content1=sheet.getWritableCell(27,i).getContents();
			CTime time=new CTime();
//			if(null==content||content.equals("")||content.equals(" ")){
//				break;
//			}else{
//				 if(content.contains("-")||content.contains("/")){
//					 sheet.addCell(new Label(28,i,content));
//				 }
//				 else{
//			System.out.println(content);
//			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd");  
//			content=dateformat1.format(new Date(content));
//			System.out.println(content);
//			//content=time.formatRealDate(new Date(content));
//			sheet.addCell(new Label(28,i,content));
//				 }
//			
//		}}
			if(null==content1||content1.equals("")||content1.equals(" ")){
				break;
			}else{
				System.out.println("--"+content1);
				 if(content1.contains("-")||content1.contains("/")){
					 sheet.addCell(new Label(27,i,content1));
				 }
				 else{
					 System.out.println("日期"+content1);
					 content1=time.formatRealMonth(new Date(content1));
				     sheet.addCell(new Label(27,i,content1));
				 }
			 }
			String content=sheet.getWritableCell(28,i).getContents();
			if(null==content||content.equals("")||content.equals(" ")){
				break;
			}else{
				System.out.println("--"+content);
				 if(content.contains("-")||content.contains("/")){
					 sheet.addCell(new Label(28,i,content));
				 }
				 else{
					 content=time.formatRealDate(new Date(content));
				     sheet.addCell(new Label(28,i,content));
				 }
			 }
			
		}
		
	}
 }
