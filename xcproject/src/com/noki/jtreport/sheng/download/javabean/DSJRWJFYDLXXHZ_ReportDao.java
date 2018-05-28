package com.noki.jtreport.sheng.download.javabean;

import java.util.ArrayList;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 地市接入网机房用电量信息汇总.xls（表十五）
 * @author 王海2012-01
 *
 */
public class DSJRWJFYDLXXHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		Integer startRow = 0;
		Integer endRow = 3;
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
			shi1 = books.get(0).getSheet(0).getCell(0,3).getContents();
			String shiName3 = books.get(e).getSheet(0).getCell(0,3).getContents().toString();
			list2.add(shiName3);
		}
		//获取excel中有数据的行数
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
				startRow = endRow;
				if(shi.equals(shi1)){
					endRow += 13;
					flag=flag+1;
					list1.add(shi);
					break;//第一个报表跳过
				}else{
					if(shiName2.equals(shi)){						
						ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 3, 22, 25, 0, startRow);
						sheet.mergeCells(0, endRow, 0, endRow+12);
						list1.add(shi);
						endRow += 13;
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
							System.out.println("没有报表的市名："+list.get(i));
							sheet.addCell(label);
							sheet.mergeCells(0, endRow, 0, endRow+12);
							list1.add(shi);
							endRow += 13;
						}	
					}			
				}
				
				//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
			}	
		}
		/*for(WritableWorkbook book:books){
			startRow = endRow;
			endRow += 13;
			if(flag++ == 0)continue;//第一个报表跳过
			
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 22, 25, 0, startRow);
			sheet.mergeCells(0, startRow, 0, endRow-1);
		}*/
		//----------------------统计----------------------
		ReportCopyOperate.copyCells(sheet,sheet, 0, 3, 22, 15, 0, endRow);
		Label heji = new Label(0,endRow,"合计");
		sheet.addCell(heji);
		sheet.mergeCells(0, endRow, 0, endRow+12);
//		Pattern pattern = Pattern.compile("^[+\\-]?\\d+(.\\d+)?$");
		
		for(int i = 2;i<22;i++){//逐列遍历
			float lx1=0,lx2=0,lx3=0,lx4=0,lx5=0,lx6=0,lx7=0,lx8=0,lx9=0,lx10=0,lx11=0,lx12=0,lx13=0;
			for(int r = 3;r<endRow+13;r++){//逐行遍历
				String leixing = sheet.getCell(1, r).getContents().trim();//获取该行第二列的内容，即属于哪个类型
				String content = "";
				if(r<endRow){//统计之前的数据
					content = sheet.getCell(i, r).getContents();//获取该单元格内容
					if(leixing.equals("类型1"))lx1 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型2"))lx2 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型3"))lx3 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型4"))lx4 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型5"))lx5 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型6"))lx6 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型7"))lx7 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型8"))lx8 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型9"))lx9 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型10"))lx10 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型11"))lx11 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型12"))lx12 += content.equals("")?0:Double.valueOf(content);
					else if(leixing.equals("室外型机房"))lx13 += content.equals("")?0:Double.valueOf(content);
					
				}
				else{//准备统计
					if(leixing.equals("类型1"))content += Math.round(lx1*100)/100f;
					else if(leixing.equals("类型2"))content += Math.round(lx2*100)/100f;
					else if(leixing.equals("类型3"))content += Math.round(lx3*100)/100f;
					else if(leixing.equals("类型4"))content += Math.round(lx4*100)/100f;
					else if(leixing.equals("类型5"))content += Math.round(lx5*100)/100f;
					else if(leixing.equals("类型6"))content += Math.round(lx6*100)/100f;
					else if(leixing.equals("类型7"))content += Math.round(lx7*100)/100f;
					else if(leixing.equals("类型8"))content += Math.round(lx8*100)/100f;
					else if(leixing.equals("类型9"))content += Math.round(lx9*100)/100f;
					else if(leixing.equals("类型10"))content += Math.round(lx10*100)/100f;
					else if(leixing.equals("类型11"))content += Math.round(lx11*100)/100f;
					else if(leixing.equals("类型12"))content += Math.round(lx12*100)/100f;
					else if(leixing.equals("室外型机房"))content += Math.round(lx13*100)/100f;
					
					
//					Matcher matcher = pattern.matcher(content);
//					
//					if(matcher.find()){
//						jxl.write.Number currentCell = new jxl.write.Number(r,i,Double.valueOf(content));
//						sheet.addCell(currentCell);
//					}
//					else{
					boolean dd=content.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
					if(content!=null&&content!=""&&dd==true){
						double con=Double.parseDouble(content);
						 jxl.write.Number number = new jxl.write.Number (i, r, con,wcf);
						 sheet.addCell(number);
					}else{
						sheet.addCell(new Label(i, r, content,wcf));
					}
					
					
//					Label currentCell = new Label(i,r,content,wcf);
//					sheet.addCell(currentCell);
//					currentCell.setCellFormat(wcf);
//					}
					
//					if(!content.equals("")){
//						
//						DisplayFormat DisplayFormat = NumberFormats.FORMAT10;
//						WritableCellFormat wcfF = new WritableCellFormat(DisplayFormat);
//						
//						Double dContent = Double.valueOf(content);
//						
//						jxl.write.Number currentCell = new jxl.write.Number(r,i,dContent,wcfF);
//						
//						sheet.addCell(currentCell);
//					}
				}
			}
		}

			
		
	}
}
