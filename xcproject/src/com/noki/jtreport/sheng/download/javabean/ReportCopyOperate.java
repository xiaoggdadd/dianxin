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
 * jxl��û�ж��еĸ��Ʋ���������д��һ�������ķ���
 * @author wanghai
 */
public class ReportCopyOperate {
	/**
	 * ��Ԫ��Ŀ���
	 * @param fromSheet Դsheet
	 * @param toSheet Ŀ��sheet
	 * @param fromStartCol Դsheet��ʼ��
	 * @param fromStartRow Դsheet��ʼ��
	 * @param fromEndCol Դsheet������
	 * @param fromEndRow Դsheet������
	 * @param toStartCol Ŀ��sheet��ʼ��
	 * @param toStartRow Ŀ��sheet��ʼ��
	 * @return boolean �Ƿ�ɹ����ɹ�true��ʧ��false
	 * @throws IOException
	 */
	public static boolean copyCells(WritableSheet fromSheet, WritableSheet toSheet,
			int fromStartCol, int fromStartRow, int fromEndCol, int fromEndRow, int toStartCol,
			int toStartRow) throws IOException {

		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat nf = new NumberFormat("0.00");
		//WritableCellFormat headerFormat = new WritableCellFormat(HEADER_FONT_STYLE);
		WritableFont wf = new WritableFont(WritableFont.createFont("����"), 10);
		//WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf,nf); 
		
		try {
			wcf.setAlignment(jxl.format.Alignment.RIGHT);
			wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

				
          System.out.println("����-"+fromStartCol+"-"+"-"+fromStartRow+"-"+"-"+fromEndCol+"-"+"-"+fromEndRow+"-"+"-"+toStartCol+"-"+"-"+toStartRow+"-");
		// ���Ʊ��ĸߺͳ�
		try {
			// ��������Ⱥϲ���Ԫ��
			for (int i = 0; i < (fromEndRow - fromStartRow + 1); i++) {
				// ѡ��������һ��
//				toSheet.insertRow(toStartRow + i);
				toSheet.setRowView(toStartRow + i, fromSheet.getRowHeight(fromStartRow + i));
				// �Բ����е��н��д�������Ԫ��
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
		list.add("����");
		list.add("�ൺ");
		list.add("�Ͳ�");
		list.add("��ׯ");
		list.add("��Ӫ");
		list.add("��̨");
		list.add("Ϋ��");
		list.add("����");
		list.add("̩��");
		list.add("����");
		list.add("����");
		list.add("����");
		list.add("����");
		list.add("����");
		list.add("�ĳ�");
		list.add("����");
		list.add("����");
		return list;
	}
	
}
