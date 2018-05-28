package com.noki.jtreport.sheng.download.javabean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 基站用电量汇总分析表.xls(表二)
 * @author Administrator
 *
 */
public class JZYDLHZFX_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		System.out.println("第二个");
		// TODO Auto-generated method stub
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
			shi1 = books.get(0).getSheet(0).getCell(0,3).getContents();
			String shiName3 = books.get(e).getSheet(0).getCell(0,3).getContents().toString();
			list2.add(shiName3);
		}
		//获取excel中有数据的行数
		WritableSheet currentSheetyl = books.get(0).getSheet(0);
		for(int i=0;i<list.size();i++){
			shi=(String) list.get(i);
			boolean a = false;
			for(int n=0;n<books.size();n++){
				String shiName2 = books.get(n).getSheet(0).getCell(0,3).getContents();						
				for(int l=0;l<list2.size();l++){
					if(shi.equals(list2.get(l))){
						a = true;
					}
				}	
				WritableSheet currentSheet = books.get(n).getSheet(0);
				startRow = endRow;
				if(shi.equals(shi1)){
					endRow += 1;
					flag=flag+1;
					list1.add(shi);
					break;//第一个报表跳过
				}else{
					if(shiName2.equals(shi)){						
						ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 3, 16, 3, 0, startRow);

						list1.add(shi);
						endRow += 1;
					}else if(!a){
						boolean b = true;
						for(int j=0;j<list1.size();j++){
							if(shi==list1.get(j)||shiName2==list1.get(j)){
								b = false;
							}
						}
						if(b){
							
							sheet.insertRow(startRow);
							Label label = new Label(0,startRow,list.get(i).toString(),wcf);
							sheet.addCell(label);
							list1.add(shi);
							endRow += 1;
						}	
					}			
				}
				
				//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
			}	
		}
		
		
		
	/*	for(WritableWorkbook book:books){
			startRow = endRow;
			endRow += 1;
			if(flag++ == 0)continue;//第一个报表跳过
			System.out.println(endRow+"---"+startRow);
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 16, 3, 0, startRow);
			
		}*/
		
		//----------------------统计----------------------
		ReportCopyOperate.copyCells(sheet,sheet, 0, 3, 15, 3, 0, endRow);
		Label heji = new Label(0,endRow,"合计");
		sheet.addCell(heji);
		DecimalFormat mat=new DecimalFormat("0.00");
		for(int i = 1;i<16;i++){//逐列遍历
			double lx1=0;
			for(int r = 3;r<endRow+1;r++){//逐行遍历
				String leixing = sheet.getCell(1, r).getContents();//获取该行第二列的内容，即属于哪个类型
				String content = "";
				double yb=100.00;
				if(r<endRow){//统计之前的数据
					content = sheet.getCell(i, r).getContents();//获取该单元格内容
					if(i==5&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/100.00);
					}
					if(i==8&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/yb);
					}
					if(i==11&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/yb);
					}
					if(i==14&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/yb);
					}
					lx1 += content.equals("")?0:Double.valueOf(content);
					System.out.println("lx1"+lx1);
				}
				else{//准备统计
					content += lx1;
					System.out.println("content"+content);
				    //content=mat.format(Double.parseDouble(content));
					if(i==5&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					if(i==8&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					if(i==11&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					if(i==14&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					boolean dd=content.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
					if(content!=null&&content!=""&&dd==true){
						double con=Double.parseDouble(content);
						 jxl.write.Number number = new jxl.write.Number (i, r, con,wcf);
						 sheet.addCell(number);
					}else{
						sheet.addCell(new Label(i, r, content,wcf));
					}
					
					//Label cell = new Label(i,r,content,wcf);
					//sheet.addCell(cell);
				}
			}
		}
	}
}
