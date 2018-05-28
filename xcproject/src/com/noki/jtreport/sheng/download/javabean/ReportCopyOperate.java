package com.noki.jtreport.sheng.download.javabean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * jxl中没有对行的复制操作，这里写了一个这样的方法
 * @author wanghai
 */
public class ReportCopyOperate {
	/**
	 * 单元格的拷贝
	 * @param fromSheet 源sheet
	 * @param toSheet 目标sheet
	 * @param fromStartCol 源sheet开始列
	 * @param fromStartRow 源sheet开始行
	 * @param fromEndCol 源sheet结束列
	 * @param fromEndRow 源sheet结束行
	 * @param toStartCol 目标sheet开始列
	 * @param toStartRow 目标sheet开始行
	 * @return boolean 是否成功：成功true，失败false
	 * @throws IOException
	 */
	public static boolean copyCells(WritableSheet fromSheet, WritableSheet toSheet,
			int fromStartCol, int fromStartRow, int fromEndCol, int fromEndRow, int toStartCol,
			int toStartRow) throws IOException {

		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat nf = new NumberFormat("0.00");
		//WritableCellFormat headerFormat = new WritableCellFormat(HEADER_FONT_STYLE);
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10);
		//WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf,nf); 
		
		try {
			wcf.setAlignment(jxl.format.Alignment.RIGHT);
			wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

				
          System.out.println("复制-"+fromStartCol+"-"+"-"+fromStartRow+"-"+"-"+fromEndCol+"-"+"-"+fromEndRow+"-"+"-"+toStartCol+"-"+"-"+toStartRow+"-");
		// 复制表格的高和长
		try {
			// 制作表格，先合并单元格
			for (int i = 0; i < (fromEndRow - fromStartRow + 1); i++) {
				// 选中区域下一行
//				toSheet.insertRow(toStartRow + i);
				toSheet.setRowView(toStartRow + i, fromSheet.getRowHeight(fromStartRow + i));
				// 对插入行的列进行处理，即单元格
				for (int j = 0; j < (fromEndCol - fromStartCol + 1); j++) {
                    
//					CellFormat cf = fromSheet.getWritableCell(fromStartCol + j, fromStartRow + i).getCellFormat();
					String content = fromSheet.getCell(fromStartCol + j, fromStartRow + i).getContents();
					boolean dd=content.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
					if(content!=null&&content!=""&&dd==true){
						double con=Double.parseDouble(content);
						 jxl.write.Number number = new jxl.write.Number (toStartCol + j, toStartRow + i, con,wcf);
						 toSheet.addCell(number);
					}else{
						toSheet.addCell(new Label(fromStartCol + j, toStartRow + i, content,wcf));
					}
					
					
//					if (cf == null) {
//						toSheet.addCell(new Label(fromStartCol + j, toStartRow + i, content));
//					} else {
//						toSheet.addCell(new Label(fromStartCol + j, toStartRow + i, content, cf));
//					}
				}
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
			return false;
		} catch (WriteException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static ArrayList shixs(){
		ArrayList list=new ArrayList();
		list.add("济南");
		list.add("青岛");
		list.add("淄博");
		list.add("枣庄");
		list.add("东营");
		list.add("烟台");
		list.add("潍坊");
		list.add("济宁");
		list.add("泰安");
		list.add("威海");
		list.add("日照");
		list.add("莱芜");
		list.add("临沂");
		list.add("德州");
		list.add("聊城");
		list.add("滨州");
		list.add("菏泽");
		return list;
	}
	
}
