package com.noki.jizhan;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import com.noki.util.CTime;
import java.util.Date;

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
public class ZdZlInfo {
    public ZdZlInfo() {
    }

    public int optionData(ZLInfoForm form) {
        int k = 0;
        if (form.getId().equals("0")) {
            k = this.addData(form);
        } else {
            k = this.midifyData(form);
        }
        return k;
    }

    private synchronized int midifyData(ZLInfoForm form) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        CTime ctime = new CTime();

        StringBuffer sql = new StringBuffer();
        sql.append("update zdzlinfo set htid='" + form.getHtid() +
                   "',   htlx='" + form.getHtlx() + "',   jzdz='" +
                   form.getJzdz() + "',   fwmj='" + form.getFwmj() +
                   "',   louc='" + form.getLouc() + "',");
        sql.append("qydw='" + form.getQydw() + "',   fkfs='" + form.getFkfs() +
                   "',   skdwmc='" + form.getSkdwmc() + "',   skdwzh='" +
                   form.getSkdwzh() + "',");
        sql.append("skdwyh='" + form.getSkdwyh() + "',   ltqyr='" +
                   form.getLtqyr() + "',   htlxr='" + form.getHtlxr() +
                   "',   htlxtel='" + form.getHtlxtel() + "',");
        sql.append("fzjsjine='" + form.getFzjsjine() + "',   fzsfkp='" +
                   form.getFzsfkp() + "',   fzzzl='" + form.getFzzzl() +
                   "',   zhjntime=to_date('" + form.getZhjntime() + "','yyyy-mm-dd'),");
        sql.append("sxtime=to_date('" + form.getSxtime() + "','yyyy-mm-dd'),   xqtime='" + form.getXqtime() + "',   offtime=to_date('" + form.getOfftime() + "','yyyy-mm-dd'),   xqyjz='" + form.getXqyjz() + "',");
        sql.append("fzyjz='" + form.getFzyjz() + "',   memo='" + form.getMemo() +
                   "',xgtime=to_date('" + ctime.formatWholeDate(new Date()) +
                   "','yyyy-mm-dd hh24:mi:ss'),qstime=to_date('"+form.getQstime()+"','yyyy-mm-dd')");
        sql.append(" where id=" + form.getId());

        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();

            db.update(sql.toString());

            msg = 1;
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

    private synchronized int addData(ZLInfoForm form) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        CTime ctime = new CTime();

        StringBuffer sql = new StringBuffer();
        sql.append("insert into zdzlinfo(htid,htlx,jzdz,fwmj,louc,qydw,fkfs,skdwmc,");
        sql.append("skdwzh,skdwyh,ltqyr,htlxr,htlxtel,fzjsjine,");
        sql.append("fzsfkp,fzzzl,zhjntime,sxtime,xqtime,offtime,");
        sql.append("xqyjz,fzyjz,memo,zdid,qstime,xgtime)");
        sql.append(" values(");
        sql.append("'"+form.getHtid()+"','"+form.getHtlx()+"','"+form.getJzdz()+"','"+form.getFwmj()+"','"+form.getLouc()+"','"+form.getQydw()+"','"+form.getFkfs()+"','"+form.getSkdwmc()+"',");
        sql.append("'"+form.getSkdwzh()+"','"+form.getSkdwyh()+"','"+form.getLtqyr()+"','"+form.getHtlxr()+"','"+form.getHtlxtel()+"','"+form.getFzjsjine()+"',");
        sql.append("'"+form.getFzsfkp()+"','"+form.getFzzzl()+"',to_date('" + form.getZhjntime() + "','yyyy-mm-dd'),to_date('" + form.getSxtime() + "','yyyy-mm-dd'),'"+form.getXqtime()+"',to_date('" + form.getOfftime() + "','yyyy-mm-dd'),");
        sql.append("'"+form.getXqyjz()+"','"+form.getFzyjz()+"','"+form.getMemo()+"','"+form.getZdid()+"',to_date('"+form.getQstime()+"','yyyy-mm-dd'),to_date('"+ctime.formatWholeDate(new Date())+"','yyyy-mm-dd hh24:mi:ss'))");

        // sql.append(",0,'"  "')");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();

            db.update(sql.toString());

            msg = 1;
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
