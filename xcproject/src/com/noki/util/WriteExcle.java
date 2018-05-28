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
      // ������д���Excel������
      WritableWorkbook wwb = Workbook.createWorkbook(new File(wpath+fileName));
      // ����Excel������
      WritableSheet ws = wwb.createSheet(sheet, 0);
      jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.
          ARIAL, 20,
          WritableFont.BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE2);
      jxl.write.WritableCellFormat wcfFc = new jxl.write.WritableCellFormat(wfc);
      wcfFc.setAlignment(jxl.format.Alignment.CENTRE);
      jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.
          createFont("����"), 11,
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
            // ���Label����
          }
          ws.setColumnView(j, 20);
          //test
          //��Ӵ���������ɫ��Formatting����
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
      // д��Exel������
      wwb.write();
      // �ر�Excel����������
      wwb.close();
      System.out.println("�ɹ�д��EXCEL�ļ���");

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
