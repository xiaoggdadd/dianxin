package com.noki.mobi.up;

import java.util.Vector;
import jxl.Workbook;
import jxl.Sheet;
import java.io.File;
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
    System.out.println("here ============================");
    Vector content = new Vector();
    try {
      Workbook book = Workbook.getWorkbook(new File(filename));
      Sheet sheet = book.getSheet(0);
      Vector v = new Vector();
      //v.add("Ãû³Æ");
      //v.add("ÊýÁ¿");

      //content.add(v);
      for (int i = 1; i < sheet.getRows() + 1; i++) {
        Vector rows = new Vector();
        boolean isNull = true;
        System.out.println("coloumns==" + sheet.getColumns());
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
      }
      book.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }

}
