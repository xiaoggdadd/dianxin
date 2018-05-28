package com.noki.electricfees.servlet;

import java.util.Vector;
import java.util.regex.Pattern;

import jxl.CellReferenceHelper;
import jxl.CellType;
import jxl.FormulaCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.Sheet;
import jxl.WorkbookSettings;

import java.io.File;

import com.noki.electricfees.servlet.ReadFile;

import jxl.Cell;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ReadXLS implements ReadFile {
  public Vector getColumns(String filename) {
    Vector columns = new Vector();
    try {
      Workbook book = Workbook.getWorkbook(new File(filename));
      //get the first sheet
      Sheet sheet = book.getSheet(0);
      //get(0,0)
      for (int j = 0; j < sheet.getColumns(); j++) {
        Cell cell = sheet.getCell(j, 0);
        String result = cell.getContents();
        columns.add(result);
      }
      book.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return columns;
  }

  public Vector getContent(String filename) {
    //System.out.println("here ============================");
    Vector content = new Vector();
    try {
    	//�������Խ�������  �е�����С����ܶ�  ���ݺܳ���
    	//1.�޸�JXLԴ������WriteAccessRecord�ļ����룬�������ñ���data�ĳ��ȣ����磺data = new byte[astring.getBytes().length];  
        //2.������ǿ�����ñ���WorkbookSettings.writeAccess��ֵ����
    	WorkbookSettings settings = new WorkbookSettings ();  
    	settings.setWriteAccess(null);  
    	

      Workbook book = Workbook.getWorkbook(new File(filename));
      // ��һ��sheet ҳ��ʼ ��ʼ��0 
      Sheet sheet = book.getSheet(0);
      Vector v = new Vector();
      System.out.println("getRows==" + sheet.getRows());
      //�����ܵ�  ��ʼ������
      for (int i = 1; i < sheet.getRows(); i++) {
    	  // ���ڷ���  �������ʹ��
        Vector rows = new Vector();
        /// isNull ���������������ֹ�� rows ����Ӷ����õı�־λ
        boolean isNull = true;
        //����  ��ʼ ����
        for (int j = 0; j < sheet.getColumns(); j++) {
        	/// col row 
          Cell cell = sheet.getCell(j, i);
          String result = cell.getContents();
          //Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
          if(!"".equals(result)&&result!=null){
        	  //if(pattern.matcher(result).matches()==true){
        	  //J=3 Ϊ���ID  
        	  if(j==3){
        		  System.out.println("-result1111-"+result);
        	  }else{
        		  // ���  ����cell �����ֽ��� ���
        		  if(cell.getType().equals(CellType.NUMBER)){
                	  NumberCell numberCell = (NumberCell)cell;
                      double namberValue = numberCell.getValue();
                     // System.out.println("--cel--"+namberValue);
                      result=namberValue+"";
                      
                  }

        		  
        	  }
        	  
          }
          System.out.println("-result-"+result);
          // ���е����ݵĲ���
          rows.add(result);
          //�� ���ս������ж� �Ƿ�Ϊ��
          if (result != null && !result.equals("")) {
            isNull = false;
          }
        }
        if (!isNull) {
          content.add(rows);
        }
        else{
        	System.out.println("�пպ�  ������");
        	break;
        }
      }
      book.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }
  
  /**��վ�㵼��
 * @param filename
 * @return
 */
public Vector getContentzd(String filename) {
	    //System.out.println("here ============================");
	    Vector content = new Vector();
	    try {
	    	//�������Խ�������  �е�����С����ܶ�  ���ݺܳ���
	    	//1.�޸�JXLԴ������WriteAccessRecord�ļ����룬�������ñ���data�ĳ��ȣ����磺data = new byte[astring.getBytes().length];  
	        //2.������ǿ�����ñ���WorkbookSettings.writeAccess��ֵ����
	    	WorkbookSettings settings = new WorkbookSettings ();  
	    	settings.setWriteAccess(null);  
	    	

	      Workbook book = Workbook.getWorkbook(new File(filename));
	      // ��һ��sheet ҳ��ʼ ��ʼ��0 
	      Sheet sheet = book.getSheet(0);
	      Vector v = new Vector();
	      System.out.println("getRows==" + sheet.getRows());
	      //�����ܵ�  ��ʼ������
	      for (int i = 1; i < sheet.getRows(); i++) {
	    	  // ���ڷ���  �������ʹ��
	        Vector rows = new Vector();
	        /// isNull ���������������ֹ�� rows ����Ӷ����õı�־λ
	        boolean isNull = true;
	        //����  ��ʼ ����
	        for (int j = 0; j < sheet.getColumns(); j++) {
	        	/// col row 
	          Cell cell = sheet.getCell(j, i);
	          String result = cell.getContents();
	          //Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
	          if(!"".equals(result)&&result!=null){
	        	  //if(pattern.matcher(result).matches()==true){
	        	  //J=3 Ϊ���ID  
	        	  if(j==26 || j==35 || j==36 || j==38){
	        		  System.out.println("-result1111-"+result);
	        	  }else{
	        		  // ���  ����cell �����ֽ��� ���
	        		  if(cell.getType().equals(CellType.NUMBER)){
	                	  NumberCell numberCell = (NumberCell)cell;
	                      double namberValue = numberCell.getValue();
	                      result=namberValue+"";
	                  }

	        		  
	        	  }
	        	  
	          }
	          System.out.println("-result-"+result);
	          // ���е����ݵĲ���
	          rows.add(result);
	          //�� ���ս������ж� �Ƿ�Ϊ��
	          if (result != null && !result.equals("")) {
	            isNull = false;
	          }
	        }
	        if (!isNull) {
	          content.add(rows);
	        }
	        else{
	        	System.out.println("�пպ�  ������");
	        	break;
	        }
	      }
	      book.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return content;
	  }
  
  public Vector getContenthtd(String filename) {
	    //System.out.println("here ============================");
	    Vector content = new Vector();
	    try {
	      Workbook book = Workbook.getWorkbook(new File(filename));
	      Sheet sheet = book.getSheet(0);
	      Vector v = new Vector();
	      System.out.println("getRows==" + sheet.getRows());
	      for (int i = 1; i < sheet.getRows(); i++) {
	        Vector rows = new Vector();
	        boolean isNull = true;
	        for (int j = 0; j < sheet.getColumns(); j++) {
	          Cell cell = sheet.getCell(j, i);
	          String result = cell.getContents();
	          //Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
	          if(!"".equals(result)&&result!=null){
	        	  //if(pattern.matcher(result).matches()==true){
	        	  if(j==4||j==5||j==17){
	        		  System.out.println("-result1111-"+result);
	        	  }else{
	        		  if(cell.getType().equals(CellType.NUMBER)){
	            		  
	                	  NumberCell numberCell = (NumberCell)cell;
	                      double namberValue = numberCell.getValue();
	                      System.out.println("--cel--"+namberValue);
	                      result=namberValue+"";
	                  }
	        	  }
	        	  
	          }
	          System.out.println("-result-"+result);
	          rows.add(result);
	          if (result != null && !result.equals("")) {
	            isNull = false;
	          }
	        }
	        if (!isNull) {
	          content.add(rows);
	        }
	        else{
	        	System.out.println("�пպ�  ������");
	        	break;
	        }
	      }
	      book.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return content;
	  }
  public Vector getContentdj(String filename) {
	    //System.out.println("here ============================");
	    Vector content = new Vector();
	    try {
	      Workbook book = Workbook.getWorkbook(new File(filename));
	      Sheet sheet = book.getSheet(0);
	      Vector v = new Vector();
	      System.out.println("getRows==" + sheet.getRows());
	      for (int i = 1; i < sheet.getRows(); i++) {
	        Vector rows = new Vector();
	        boolean isNull = true;
	        for (int j = 0; j < sheet.getColumns(); j++) {
	          Cell cell = sheet.getCell(j, i);
	          String result = cell.getContents();
	          //Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
	          if(!"".equals(result)&&result!=null){
	        	  //if(pattern.matcher(result).matches()==true){
	        	  if(j==1||j==2||j==0||j==3){
	        		  System.out.println("-result1111-"+result);
	        	  }else{
	        		  if(cell.getType().equals(CellType.NUMBER)){
	            		  
	                	  NumberCell numberCell = (NumberCell)cell;
	                      double namberValue = numberCell.getValue();
	                      System.out.println("--cel--"+namberValue);
	                      result=namberValue+"";
	                  }
	        	  }
	        	  
	          }
	          System.out.println("-result-"+result);
	          rows.add(result);
	          if (result != null && !result.equals("")) {
	            isNull = false;
	          }
	        }
	        if (!isNull) {
	          content.add(rows);
	        }
	        else{
	        	System.out.println("�пպ�  ������");
	        	break;
	        }
	      }
	      book.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return content;
	  }
}
