package com.noki.dataLoad.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportExcelError {
	public static <T> void importExecl(HttpServletResponse response,String title, String[] rowName,List<T> entitys){
		  List<Object[]>  dataList = new ArrayList<Object[]>();
		    Object[] objs = null;
		   for(T t : entitys){
			   objs = new Object[rowName.length];
			   Class clazz = t.getClass();
		        Field[] fields =  clazz.getDeclaredFields();
			    for (int i = 0; i < rowName.length; i++) {
			        try {
			        	if(i!=0){
			        		fields[i-1].setAccessible(true);
			        	
			        		
			        			objs[i] = fields[i-1].get(t);
			        		
			        	}else{
			        		objs[i]=i;
			        	}
			        	
					} catch (Exception e) {
						e.printStackTrace();
					} 
			    }
			    dataList.add(objs);
		   }
		       try{
		            HSSFWorkbook workbook = new HSSFWorkbook();                        // ��������������
		            HSSFSheet sheet = workbook.createSheet(title);                     // ����������		            
		            // ������������
		            HSSFRow rowm = sheet.createRow(0);
		            HSSFCell cellTiltle = rowm.createCell(0);
		            
		            //sheet��ʽ���塾getColumnTopStyle()/getStyle()��Ϊ�Զ��巽�� - ������  - ����չ��
		            HSSFCellStyle columnTopStyle = ExportExcelError.getColumnTopStyle(workbook);//��ȡ��ͷ��ʽ����
		            HSSFCellStyle style = ExportExcelError.getStyle(workbook);                    //��Ԫ����ʽ����
		            
		            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));  
		            cellTiltle.setCellStyle(columnTopStyle);
		            cellTiltle.setCellValue("Ϊ�ջ����");
		            
		            // ������������
		            int columnNum = rowName.length;
		            HSSFRow rowRowName = sheet.createRow(2);                // ������2��λ�ô�����(��˵��п�ʼ�ĵڶ���)
		            
		            // ����ͷ���õ�sheet�ĵ�Ԫ����
		            for(int n=0;n<columnNum;n++){
		                HSSFCell  cellRowName = rowRowName.createCell(n);                //������ͷ��Ӧ�����ĵ�Ԫ��
		                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);                //������ͷ��Ԫ�����������
		                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
		                cellRowName.setCellValue(text);                                    //������ͷ��Ԫ���ֵ
		                cellRowName.setCellStyle(columnTopStyle);                        //������ͷ��Ԫ����ʽ
		            }
		            
		            //����ѯ�����������õ�sheet��Ӧ�ĵ�Ԫ����
		            for(int i=0;i<dataList.size();i++){
		                
		                Object[] obj = dataList.get(i);//����ÿ������
		                HSSFRow row = sheet.createRow(i+3);//�������������
		                
		                for(int j=0; j<obj.length; j++){
		                    HSSFCell  cell = null;   //���õ�Ԫ�����������
		                    if(j == 0){
		                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
		                        cell.setCellValue(i+1);    
		                    }else{
		                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
		                        if(!"".equals(obj[j]) && obj[j] != null){
		                            cell.setCellValue(obj[j].toString());                        //���õ�Ԫ���ֵ
		                        }
		                    }
		                    cell.setCellStyle(style);                                    //���õ�Ԫ����ʽ
		                }
		            }
		            //���п����ŵ������г��Զ���Ӧ
		            for (int colNum = 0; colNum < columnNum; colNum++) {
		                int columnWidth = sheet.getColumnWidth(colNum) / 256;
		                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
		                    HSSFRow currentRow;
		                    //��ǰ��δ��ʹ�ù�
		                    if (sheet.getRow(rowNum) == null) {
		                        currentRow = sheet.createRow(rowNum);
		                    } else {
		                        currentRow = sheet.getRow(rowNum);
		                    }
		                    if (currentRow.getCell(colNum) != null) {
		                        HSSFCell currentCell = currentRow.getCell(colNum);
		                        if(currentCell != null && !"".equals(currentCell)){
		                        	
									if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
										columnWidth = 30;
										/*String sValue = currentCell.getStringCellValue();
										if (sValue == null) {
											sValue = "";
										}
										int length = sValue.getBytes().length;
										if (columnWidth < length) {
											columnWidth = length;
										}*/
									}
		                        }
		                    }
		                }
		                if(colNum == 0){
		                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
		                }else{
		                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
		                }
		            }
		            
		            if(workbook !=null){
		                try
		                {
		                    String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
		                    String headStr = "attachment; filename=\"" + fileName + "\"";
		                    
		                    System.out.println(headStr);
		                    
		                    response.reset();
		                    response.setContentType("APPLICATION/OCTET-STREAM");
		                    response.setHeader("Content-Disposition", headStr);
		                    OutputStream out = response.getOutputStream();
		                    
//		                    File tempFile=File.createTempFile("temp",".xls");
//
//		                    tempFile.deleteOnExit();
//		                    
//		                    OutputStream tempout = new FileOutputStream(tempFile);
//		                    
//		                    System.out.println(tempFile);
//		                    
//		                    workbook.write(tempout);
		                    
		                   
		                    workbook.write(out);
		                   	out.close();
		                    
//		                    tempout.close();			//�ر���

		                }
		                catch (IOException e)
		                {
		                    e.printStackTrace();
		                }
		            }

		        }catch(Exception e){
		            e.printStackTrace();
		        }
	}
	
	  /* 
     * ��ͷ��Ԫ����ʽ
     */    
      public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
          
            // ��������
          HSSFFont font = workbook.createFont();
          //���������С
          font.setFontHeightInPoints((short)11);
          //����Ӵ�
          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
          //������������ 
          font.setFontName("Courier New");
          //������ʽ; 
          HSSFCellStyle style = workbook.createCellStyle();
          //���õױ߿�; 
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
          //���õױ߿���ɫ;  
          style.setBottomBorderColor(HSSFColor.BLACK.index);
          //������߿�;   
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
          //������߿���ɫ; 
          style.setLeftBorderColor(HSSFColor.BLACK.index);
          //�����ұ߿�; 
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);
          //�����ұ߿���ɫ; 
          style.setRightBorderColor(HSSFColor.BLACK.index);
          //���ö��߿�; 
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);
          //���ö��߿���ɫ;  
          style.setTopBorderColor(HSSFColor.BLACK.index);
          //����ʽ��Ӧ�����õ�����;  
          style.setFont(font);
          //�����Զ�����; 
          style.setWrapText(false);
          //����ˮƽ�������ʽΪ���ж���;  
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          //���ô�ֱ�������ʽΪ���ж���; 
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
          
          return style;
          
      }
      
      /*  
     * ��������Ϣ��Ԫ����ʽ
     */  
      public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
            // ��������
            HSSFFont font = workbook.createFont();
            //���������С
            //font.setFontHeightInPoints((short)10);
            //����Ӵ�
            //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //������������ 
            font.setFontName("Courier New");
            //������ʽ; 
            HSSFCellStyle style = workbook.createCellStyle();
            //���õױ߿�; 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //���õױ߿���ɫ;  
            style.setBottomBorderColor(HSSFColor.BLACK.index);
            //������߿�;   
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            //������߿���ɫ; 
            style.setLeftBorderColor(HSSFColor.BLACK.index);
            //�����ұ߿�; 
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            //�����ұ߿���ɫ; 
            style.setRightBorderColor(HSSFColor.BLACK.index);
            //���ö��߿�; 
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //���ö��߿���ɫ;  
            style.setTopBorderColor(HSSFColor.BLACK.index);
            //����ʽ��Ӧ�����õ�����;  
            style.setFont(font);
            //�����Զ�����; 
            style.setWrapText(false);
            //����ˮƽ�������ʽΪ���ж���;  
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //���ô�ֱ�������ʽΪ���ж���; 
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
           
            return style;
      
      }
      // �ж��ļ��Ƿ����
      public static void judeFileExists(File file) {

          if (file.exists()) {
              System.out.println("file exists");
          } else {
              System.out.println("file not exists, create it ...");
              try {
                  file.createNewFile();
                  
              } catch (IOException e) {
            	  
                  e.printStackTrace();
              }
          }

      }

      // �ж��ļ����Ƿ����
      public static void judeDirExists(File file) {

          if (file.exists()) {
              if (file.isDirectory()) {
                  System.out.println("dir exists");
              } else {
                  System.out.println("the same name file exists, can not create dir");
              }
          } else {
              System.out.println("dir not exists, create it ...");
              file.mkdir();
          }

      }
}
