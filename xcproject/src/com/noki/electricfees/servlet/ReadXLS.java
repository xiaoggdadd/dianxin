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
    	//解决数组越界的问题  有的数据小数点很多  数据很长：
    	//1.修改JXL源代码中WriteAccessRecord文件代码，重新设置变量data的长度，例如：data = new byte[astring.getBytes().length];  
        //2.代码中强制设置变量WorkbookSettings.writeAccess的值即可
    	WorkbookSettings settings = new WorkbookSettings ();  
    	settings.setWriteAccess(null);  
    	

      Workbook book = Workbook.getWorkbook(new File(filename));
      // 第一个sheet 页开始 开始是0 
      Sheet sheet = book.getSheet(0);
      Vector v = new Vector();
      System.out.println("getRows==" + sheet.getRows());
      //遍历总的  开始是行数
      for (int i = 1; i < sheet.getRows(); i++) {
    	  // 用于分行  添加数据使用
        Vector rows = new Vector();
        /// isNull 用于在最后完整终止的 rows 的添加而是用的标志位
        boolean isNull = true;
        //遍历  开始 列数
        for (int j = 0; j < sheet.getColumns(); j++) {
        	/// col row 
          Cell cell = sheet.getCell(j, i);
          String result = cell.getContents();
          //Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
          if(!"".equals(result)&&result!=null){
        	  //if(pattern.matcher(result).matches()==true){
        	  //J=3 为电表ID  
        	  if(j==3){
        		  System.out.println("-result1111-"+result);
        	  }else{
        		  // 针对  单个cell 分数字进行 添加
        		  if(cell.getType().equals(CellType.NUMBER)){
                	  NumberCell numberCell = (NumberCell)cell;
                      double namberValue = numberCell.getValue();
                     // System.out.println("--cel--"+namberValue);
                      result=namberValue+"";
                      
                  }

        		  
        	  }
        	  
          }
          System.out.println("-result-"+result);
          // 单行的数据的插入
          rows.add(result);
          //做 最终结束的判断 是否为空
          if (result != null && !result.equals("")) {
            isNull = false;
          }
        }
        if (!isNull) {
          content.add(rows);
        }
        else{
        	System.out.println("行空后  结束了");
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
  
  /**新站点导入
 * @param filename
 * @return
 */
public Vector getContentzd(String filename) {
	    //System.out.println("here ============================");
	    Vector content = new Vector();
	    try {
	    	//解决数组越界的问题  有的数据小数点很多  数据很长：
	    	//1.修改JXL源代码中WriteAccessRecord文件代码，重新设置变量data的长度，例如：data = new byte[astring.getBytes().length];  
	        //2.代码中强制设置变量WorkbookSettings.writeAccess的值即可
	    	WorkbookSettings settings = new WorkbookSettings ();  
	    	settings.setWriteAccess(null);  
	    	

	      Workbook book = Workbook.getWorkbook(new File(filename));
	      // 第一个sheet 页开始 开始是0 
	      Sheet sheet = book.getSheet(0);
	      Vector v = new Vector();
	      System.out.println("getRows==" + sheet.getRows());
	      //遍历总的  开始是行数
	      for (int i = 1; i < sheet.getRows(); i++) {
	    	  // 用于分行  添加数据使用
	        Vector rows = new Vector();
	        /// isNull 用于在最后完整终止的 rows 的添加而是用的标志位
	        boolean isNull = true;
	        //遍历  开始 列数
	        for (int j = 0; j < sheet.getColumns(); j++) {
	        	/// col row 
	          Cell cell = sheet.getCell(j, i);
	          String result = cell.getContents();
	          //Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
	          if(!"".equals(result)&&result!=null){
	        	  //if(pattern.matcher(result).matches()==true){
	        	  //J=3 为电表ID  
	        	  if(j==26 || j==35 || j==36 || j==38){
	        		  System.out.println("-result1111-"+result);
	        	  }else{
	        		  // 针对  单个cell 分数字进行 添加
	        		  if(cell.getType().equals(CellType.NUMBER)){
	                	  NumberCell numberCell = (NumberCell)cell;
	                      double namberValue = numberCell.getValue();
	                      result=namberValue+"";
	                  }

	        		  
	        	  }
	        	  
	          }
	          System.out.println("-result-"+result);
	          // 单行的数据的插入
	          rows.add(result);
	          //做 最终结束的判断 是否为空
	          if (result != null && !result.equals("")) {
	            isNull = false;
	          }
	        }
	        if (!isNull) {
	          content.add(rows);
	        }
	        else{
	        	System.out.println("行空后  结束了");
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
	        	System.out.println("行空后  结束了");
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
	        	System.out.println("行空后  结束了");
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
