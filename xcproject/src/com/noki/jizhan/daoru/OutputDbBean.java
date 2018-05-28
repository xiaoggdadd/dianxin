package com.noki.jizhan.daoru;

import com.noki.database.DataBase;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.biff.RowsExceededException;
import jxl.write.WriteException;
import jxl.write.Label;
import java.util.ArrayList;
import com.noki.jizhan.DataComm;
import com.noki.database.DbException;
import java.sql.SQLException;
import com.noki.util.Query;
import java.sql.ResultSet;


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
public class OutputDbBean {


    public DataBase db;

    public void insertData(WritableSheet dataSheet,
                            String loginName) throws
            RowsExceededException, WriteException {
        DataComm dataComm = new DataComm();
        ResultSet rs = null;
        try {
            db = new DataBase();
            db.connectDb();
            String fzarea = "", fzzdid = "",loginId="";
            //fzarea = dataComm.getFuzeArea(db, loginName);

            //fzzdid = dataComm.getFuzeZdid(db, loginName, fzarea, "0");
            loginId=dataComm.getLoginId(db, loginName);

            StringBuffer s2 = new StringBuffer();
            StringBuffer querystr = new StringBuffer();
            s2.append("select (case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end) as xianname"+
                       ",(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as xiaoquname"+
                       ", z.jzname,z.id,z.shi,z.xian,z.xiaoqu from zhandian z where 1=1  " +
                       " and ((z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))) order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");

/*            if (!fzarea.equals("'0'") || !fzzdid.equals("0")) { //有负责分配负责区域或者负责站点

                if (!fzarea.equals("'0'")) { //有分配负责区域
                    s2.append(" and z.xiaoqu in(" +
                              dataComm.getFuzeArea(db, loginName) +
                              ") ");

                } else {
                    s2.append(" and " + fzzdid);

                }
            } else { //没有 负责分配负责区域或者负责站点
                s2.append(" and z.xiaoqu in('0')");

            }

            if (!fzarea.equals("'0'") && !fzzdid.equals("0")) {
                querystr.append(dataComm.getQueryStr_zd_temp(fzzdid,
                        s2.toString()));

            } else {
                s2.append(" order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
                querystr = s2;

            }*/

            rs = db.queryAll(s2.toString());
            int row = 2;
            Label lb1 = null, lb2 = null, lb3 = null, lb4 = null;
            while (rs.next()) {
                lb1 = new Label(0, row, rs.getString(1));
                lb2 = new Label(1, row, rs.getString(2));
                lb3 = new Label(2, row, rs.getString(3));
                lb4 = new Label(3, row, rs.getString(4));
                dataSheet.addCell(lb1);
                dataSheet.addCell(lb2);
                dataSheet.addCell(lb3);
                dataSheet.addCell(lb4);
            }
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
            de.printStackTrace();
        }

        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }


    }


}
