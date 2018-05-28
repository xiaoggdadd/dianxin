package com.noki.mobi.up;

import java.util.Vector;
import java.util.Iterator;
import com.noki.database.DataBase;

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
public class Insert {

  StringBuffer sql = new StringBuffer();

  Vector wrongContent = new Vector();
  public DataBase db;
  public Insert() {
    try {
      db = new DataBase();
      db.connectDb();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void closeDb() {
    try {
      db.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public synchronized Vector getWrong(Vector content) {
    int m = 0, i = 0;
    for (Iterator it = content.iterator(); it.hasNext(); ) {
      m++;
      i = 0;
      Vector row = (Vector) it.next();
      Iterator iter = row.iterator();
      sql.setLength(0);
      sql.append(
          "INSERT INTO INDATA(PNAME,PCOUNT) VALUES( ");

      String con = "";
      while (iter.hasNext()) {

        i++;
        Object value = iter.next();
        if (value == null) {
          value = "";
        }
        con = value.toString();

        if (i == 2) {
          sql.append("'" + con + "',");
        }

        else if (i == 3) {
          sql.append(con);
        }

      }
      sql.append(")");
      try {
        System.out.println(sql.toString());
        int tt = db.update(sql.toString());

        if (tt == 0) {
          row.add("ÓÐ´íÎó");
          wrongContent.add(row);
        }

      }
      catch (Exception e) {
        e.printStackTrace();
        row.add(e.toString());
        wrongContent.add(row);
      }

    }
    System.out.println(wrongContent);
    return wrongContent;
  }

}
