package com.noki.ammeterdegree.servlet;

import java.util.Vector;
import jxl.Workbook;
import jxl.Sheet;
import jxl.WorkbookSettings;

import java.io.File;

import com.noki.ammeterdegree.servlet.ReadFile;

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
public class ReadXLS
    implements ReadFile {
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
      Sheet sheet = book.getSheet(0);
      Vector v = new Vector();
      System.out.println("getRows==" + sheet.getRows());
      for (int i = 1; i < sheet.getRows(); i++) {
        Vector rows = new Vector();
        boolean isNull = true;
        for (int j = 0; j < sheet.getColumns(); j++) {
          Cell cell = sheet.getCell(j, i);
          String result = cell.getContents();
          rows.add(result);
          if (result != null && !result.equals("")) {
            isNull = false;
          }
        }
        if (!isNull) {
          content.add(rows);
        }
        else{
        	System.out.println("行空  结束了");
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
