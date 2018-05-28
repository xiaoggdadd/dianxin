package com.noki.syq;

import java.util.Date;
import com.noki.database.DbException;
import com.noki.database.DataBase;
import com.noki.jizhan.KZForm;
import com.noki.util.CTime;

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
public class SyqBean {
    public SyqBean() {
    }

    public synchronized int addData(String yuef, String bzh, String xinghao,
                                    String nhlx, String nhyt, String zdid,
                                    String nhsl, String tzsl, String sjsl,
                                    String danjia, String nhje, String tzje,
                                    String sjje, String qss, String jss,
                                    String bdate, String edate, String sfr,
                                    String sfdate, String pjlx, String pjh,
                                    String kpdate, String memo, String cjr) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        CTime ctime = new CTime();
        String inserttime = ctime.formatWholeDate(new Date());
        StringBuffer sql = new StringBuffer();
        sql.append(
                "INSERT INTO SYQ(YUEF,BZH,XINGHAO,NHLX,NHYT,ZDID,NHSL,TZSL,");
        sql.append(
                "SJSL,DANJIA,NHJE,TZJE,SJJE,QSS,JSS,BDATE,");
        sql.append(
                "EDATE,SFR,SFDATE,PJLX,PJH,KPDATE,MEMO,CJR,CJTIME)");
        sql.append("VALUES ('" + yuef + "','" + bzh + "','" + xinghao + "','" +
                   nhlx + "','" + nhyt + "','" + zdid + "'," + nhsl +
                   ",");
        sql.append("" + tzsl + "," + sjsl + "," + danjia + "," +
                   nhje + "," + tzje + "," + sjje + ",'" + qss + "','" +
                   jss + "'");
        sql.append(",to_date('" + bdate + "','yyyy-mm-dd'),to_date('" + edate +
                   "','yyyy-mm-dd'),'" + sfr +
                   "',to_date('" + sfdate + "','yyyy-mm-dd')");
        sql.append(",'" + pjlx + "','" + pjh + "',to_date('" + kpdate +
                   "','yyyy-mm-dd'),'" + memo +
                   "','" + cjr + "'");
        sql.append(",to_date('" + inserttime + "','yyyy-mm-dd hh24:mi:ss'))");

        // sql.append(",0,'"  "')");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();

            msg = db.update(sql.toString());

        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    public synchronized int delData(String[] ids) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;

        StringBuffer sql = new StringBuffer();
        StringBuffer sid = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                sid.append(ids[i]);
            } else {
                sid.append("," + ids[i]);
            }
        }

        sql.append("delete from syq where id in(" + sid.toString() + ")");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();

            msg = db.update(sql.toString());

        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }


    public synchronized int SHData(String[] ids,String shsign) {
            //birthday = birthday.length()>0?birthday:null;
            int msg = 0;

            StringBuffer sql = new StringBuffer();
            StringBuffer sid = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {
                if (i == 0) {
                    sid.append(ids[i]);
                } else {
                    sid.append("," + ids[i]);
                }
            }

            sql.append("update syq set shsign='"+shsign+"' where id in(" + sid.toString() + ")");
            System.out.println(sql.toString());

            DataBase db = new DataBase();
            try {
                db.connectDb();

                db.update(sql.toString());
                msg=1;
            } catch (DbException de) {
                try {
                    db.rollback();
                } catch (DbException dee) {
                    dee.printStackTrace();
                }
                de.printStackTrace();
            } finally {
                try {

                    db.close();
                } catch (Exception de) {
                    de.printStackTrace();
                }
            }

            return msg;
    }
    public synchronized int modifyData_ys(String yuef, String bzh,
                                          String xinghao, String nhlx,
                                          String nhyt, String zdid,
                                          String nhsl, String tzsl, String sjsl,
                                          String danjia, String nhje,
                                          String tzje, String sjje, String qss,
                                          String jss, String bdate,
                                          String edate,
                                          String sfr, String sfdate,
                                          String pjlx, String pjh,
                                          String kpdate,
                                          String memo, String id) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        StringBuffer sql = new StringBuffer();
        sql.append(
                "UPDATE SYQ SET YUEF='" + yuef + "',BZH='" + bzh +
                "',XINGHAO='" + xinghao + "',NHLX='" + nhlx + "',NHYT='" + nhyt +
                "',ZDID='" + zdid + "',NHSL=" + nhsl + ",TZSL=" + tzsl + ",");
        sql.append("SJSL=" + sjsl + ",DANJIA=" + danjia + ",NHJE=" + nhje + ",");
        sql.append("TZJE=" + tzje + ",SJJE=" + sjje + ",QSS='" + qss + "',");
        sql.append("JSS='" + jss + "'");
        sql.append(",BDATE=to_date('" + bdate + "','yyyy-mm-dd'),");
        /*
         sql.append(
                "SJSL=" + sjsl + ",DANJIA=" + danjia + ",NHJE=" + nhje +
                ",TZJE=" + tzje + ",SJJE=" + sjje + ",QSS='" + qss +
                "',JSS='" += jss + "',BDATE=to_date('" + bdate +
                "','yyyy-mm-dd'),");

         */
        sql.append(
                "EDATE=to_date('" + edate + "','yyyy-mm-dd'),SFR='" + sfr +
                "',SFDATE=to_date('" + sfdate + "','yyyy-mm-dd'),PJLX='" + pjlx +
                "',PJH='" + pjh + "',KPDATE=to_date('" + kpdate +
                "','yyyy-mm-dd'),MEMO='" + memo + "'");

        sql.append(" WHERE ID=" + id);

        // sql.append(",0,'"  "')");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();

            msg = db.update(sql.toString());

        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    public synchronized int modifyZbxs(String bh, String newdata) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ZBXS SET XISHU='" + newdata + "' WHERE BH=" + bh);

        // sql.append(",0,'"  "')");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();

            msg = db.update(sql.toString());

        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }


}
