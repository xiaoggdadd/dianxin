package com.noki.util;

import java.util.Vector;
import jxl.write.Label;
import jxl.write.biff.RowsExceededException;
import jxl.write.WriteException;
import java.io.IOException;
import java.util.Iterator;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.format.UnderlineStyle;
import jxl.write.WritableWorkbook;
import java.io.File;
import jxl.Workbook;
import jxl.write.WritableSheet;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class WriteExcle {
    public WriteExcle() {
    }
    public void Write(Vector content, String fileName, String sheet, String title,
                    int coum,String wpath) {

    try {
      // 创建可写入的Excel工作薄
      WritableWorkbook wwb = Workbook.createWorkbook(new File(wpath+fileName));
      // 创建Excel工作表
      WritableSheet ws = wwb.createSheet(sheet, 0);
      jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.
          ARIAL, 20,
          WritableFont.BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE2);
      jxl.write.WritableCellFormat wcfFc = new jxl.write.WritableCellFormat(wfc);
      wcfFc.setAlignment(jxl.format.Alignment.CENTRE);
      jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.
          createFont("宋体"), 11,
          WritableFont.NO_BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      jxl.write.WritableCellFormat wcfFc1 = new jxl.write.WritableCellFormat(
          wfc1);
      Label labelC = new jxl.write.Label(0, 0, title, wcfFc);

      ws.addCell(labelC);
      //test
      WritableFont wfc2 = new WritableFont(WritableFont.ARIAL, 10,
                                           WritableFont.NO_BOLD, false,
                                           UnderlineStyle.NO_UNDERLINE,
                                           jxl.format.Colour.RED);
      WritableCellFormat wcfFC2 = new WritableCellFormat(wfc2);
      //test end
      ws.mergeCells(0, 0, coum, 0);
      int i = 1;
      for (Iterator it = content.iterator(); it.hasNext(); ) {
        Vector rows = (Vector) it.next();
        int j = 0;
        Iterator iter = rows.iterator();
        int n = 0;
        while (iter.hasNext()) {
          if (j == coum) {
            j = 0;
            i++;
          }
          //System.out.println("j:"+j);
          //System.out.println("i:"+i);
          Object value = iter.next();
          //System.out.println(value);
          if (value == null) {
            value = "";
            // 添加Label对象
          }
          ws.setColumnView(j, 20);
          //test
          //添加带有字体颜色的Formatting对象
          if (String.valueOf(value).startsWith("red")) {
            String temp = value.toString().substring(3, value.toString().length());
            Label labelCF = new Label(j, i, temp, wcfFC2);
            ws.addCell(labelCF);
          }
          else {
            //test
            Label label = new Label(j, i, value.toString(), wcfFc1);
            ws.addCell(label);
          }
          n++;
          j++;
        }
        i++;
      }
      // 写入Exel工作表
      wwb.write();
      // 关闭Excel工作薄对象
      wwb.close();
      System.out.println("成功写入EXCEL文件！");

    }
    catch (RowsExceededException e) {
      e.printStackTrace();
    }
    catch (WriteException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
