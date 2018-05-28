package com.noki.jizhan;

import com.noki.database.DbException;
import com.noki.database.DataBase;
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
public class ZddfInfo {
    public ZddfInfo() {
    }

    public int optionData(ZddfInfoForm form){
        int k = 0;
        if(form.getId().equals("0")){
            k = this.addData(form);
        }else{
            k = this.midifyData(form);
        }
        return k;
    }

    private synchronized int midifyData(ZddfInfoForm form) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        CTime ctime = new CTime();
      //新加agreementid,signdate, origindate,stopdate,powerunit,unitlinkman,unitphone,watchcost
        StringBuffer sql = new StringBuffer();
        sql.append("update zddfinfo set fkzq='" +
                   form.getFkzq() +
                   "',fkfs='" + form.getFkfs() +
                   "',skdwmc='" + form.getSkdwmc() + "',skdwzh='" +
                   form.getSkdwzh() + "',skdwyh='" +
                   form.getSkdwyh() + "',zbdyhh='" + form.getZbdyhh() + "',agreementid='"+
                   form.getAgreementid()+"',powerunit='"+
                   form.getPowerunit()+"',unitlinkman='"+form.getUnitlinkman()+"',unitphone='"+
                   form.getUnitphone()+"',watchcost='"+form.getWatchcost()+"'");
        sql.append(",scjftime=to_date('" + form.getScjftime() +
                   "','yyyy-mm-dd hh24:mi:ss'),signdate=to_date('"+form.getSigndate()+
                   "','yyyy-mm-dd hh24:mi:ss'),origindate=to_date('"+form.getOrigindate()+
                   "','yyyy-mm-dd hh24:mi:ss'),stopdate=to_date('"+form.getStopdate()+
                   "','yyyy-mm-dd hh24:mi:ss'),memo='" + form.getMemo() +
                   "' where id=" + form.getId());
        StringBuffer s2 = new StringBuffer();
        s2.append("update zhandian set gdfs='" + form.getGdfs() + "',dianfei='" +
                  form.getDfdj() + "' where id=" + form.getZdid());
        // sql.append(",0,'"  "')");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();
            db.setAutoCommit(false);
            db.update(sql.toString());
            db.update(s2.toString());
            db.commit();
            db.setAutoCommit(true);
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

    private synchronized int addData(ZddfInfoForm form) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        CTime ctime = new CTime();
        //新加agreementid,signdate, origindate,stopdate,powerunit,unitlinkman,unitphone,watchcost
        StringBuffer sql = new StringBuffer();
        sql.append("insert into zddfinfo(fkzq,fkfs,skdwmc,skdwzh,skdwyh,zbdyhh,agreementid,powerunit,unitlinkman,unitphone,watchcost,scjftime,signdate,origindate,stopdate,memo,zdid)");

        sql.append(" values('" + form.getFkzq() +
                   "','" + form.getFkfs() +
                   "','" + form.getSkdwmc() + "','" + form.getSkdwzh() + "','" +
                   form.getSkdwyh() + "','" + form.getZbdyhh() + "','"+form.getAgreementid()+"','"+form.getPowerunit()+"','"+form.getUnitlinkman()+"','"+form.getUnitphone()+"','"+form.getWatchcost()+"'");
        sql.append(",to_date('" + form.getScjftime() +
                   "','yyyy-mm-dd hh24:mi:ss'),to_date('"+form.getSigndate()+
                   "','yyyy-mm-dd hh24:mi:ss'),to_date('"+form.getOrigindate()+
                   "','yyyy-mm-dd hh24:mi:ss'),to_date('"+form.getStopdate()+
                   "','yyyy-mm-dd hh24:mi:ss'),'" + form.getMemo() + "'," +
                   form.getZdid() + ")");
        StringBuffer s2 = new StringBuffer();
        s2.append("update zhandian set gdfs='" + form.getGdfs() + "',dianfei='" +
                  form.getDfdj() + "' where id=" + form.getZdid());
        // sql.append(",0,'"  "')");
        System.out.println(sql.toString());

        DataBase db = new DataBase();
        try {
            db.connectDb();
            db.setAutoCommit(false);
            db.update(sql.toString());
            db.update(s2.toString());
            db.commit();
            db.setAutoCommit(true);
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
