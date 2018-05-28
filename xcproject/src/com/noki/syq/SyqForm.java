package com.noki.syq;

import java.sql.*;

import com.noki.database.*;

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
public class SyqForm {
    private String id;
    private String yuef;
    private String bzh;
    private String xinghao;
    private String nhlx;
    private String nhyt;
    private String zdid;
    private String nhsl;
    private String tzsl;
    private String sjsl;
    private String danjia;
    private String nhje;
    private String tzje;
    private String sjje;
    private String qss;
    private String jss;
    private String bdate;
    private String edate;
    private String sfr;
    private String sfdate;
    private String pjlx;
    private String pjh;
    private String kpdate;
    private String memo;
    private String shsign;
    private String zdname;
    public SyqForm() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setYuef(String yuef) {
        this.yuef = yuef;
    }

    public void setBzh(String bzh) {
        this.bzh = bzh;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public void setNhlx(String nhlx) {
        this.nhlx = nhlx;
    }

    public void setNhyt(String nhyt) {
        this.nhyt = nhyt;
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }

    public void setNhsl(String nhsl) {
        this.nhsl = nhsl;
    }

    public void setTzsl(String tzsl) {
        this.tzsl = tzsl;
    }

    public void setSjsl(String sjsl) {
        this.sjsl = sjsl;
    }

    public void setDanjia(String danjia) {
        this.danjia = danjia;
    }

    public void setNhje(String nhje) {
        this.nhje = nhje;
    }

    public void setTzje(String tzje) {
        this.tzje = tzje;
    }

    public void setSjje(String sjje) {
        this.sjje = sjje;
    }

    public void setQss(String qss) {
        this.qss = qss;
    }

    public void setJss(String jss) {
        this.jss = jss;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public void setSfr(String sfr) {
        this.sfr = sfr;
    }

    public void setSfdate(String sfdate) {
        this.sfdate = sfdate;
    }

    public void setPjlx(String pjlx) {
        this.pjlx = pjlx;
    }

    public void setPjh(String pjh) {
        this.pjh = pjh;
    }

    public void setKpdate(String kpdate) {
        this.kpdate = kpdate;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setShsign(String shsign) {
        this.shsign = shsign;
    }

    public void setZdname(String zdname) {
        this.zdname = zdname;
    }

    public String getId() {
        return id;
    }

    public String getYuef() {
        return yuef;
    }

    public String getBzh() {
        return bzh;
    }

    public String getXinghao() {
        return xinghao;
    }

    public String getNhlx() {
        return nhlx;
    }

    public String getNhyt() {
        return nhyt;
    }

    public String getZdid() {
        return zdid;
    }

    public String getNhsl() {
        return nhsl;
    }

    public String getTzsl() {
        return tzsl;
    }

    public String getSjsl() {
        return sjsl;
    }

    public String getDanjia() {
        return danjia;
    }

    public String getNhje() {
        return nhje;
    }

    public String getTzje() {
        return tzje;
    }

    public String getSjje() {
        return sjje;
    }

    public String getQss() {
        return qss;
    }

    public String getJss() {
        return jss;
    }

    public String getBdate() {
        return bdate;
    }

    public String getEdate() {
        return edate;
    }

    public String getSfr() {
        return sfr;
    }

    public String getSfdate() {
        return sfdate;
    }

    public String getPjlx() {
        return pjlx;
    }

    public String getPjh() {
        return pjh;
    }

    public String getKpdate() {
        return kpdate;
    }

    public String getMemo() {
        return memo;
    }

    public String getShsign() {
        return shsign;
    }

    public String getZdname() {
        return zdname;
    }

    public synchronized SyqForm getData(String id) {

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT YUEF,BZH,XINGHAO,NHLX,NHYT,ZDID,NHSL,TZSL,");
        sql.append("SJSL,DANJIA,NHJE,TZJE,SJJE,QSS,JSS,BDATE,");
        sql.append("EDATE,SFR,SFDATE,PJLX,PJH,KPDATE,MEMO");
        sql.append(",(SELECT JZNAME FROM ZHANDIAN WHERE ID=ZDID) AS ZDNAME");
        sql.append(" FROM SYQ  WHERE ID="+id);
        System.out.println(sql.toString());
        DataBase db = new DataBase();
        try {
            db.connectDb();

            ResultSet rs = db.queryAll(sql.toString());
            if (rs.next()) {
                this.setYuef(rs.getString(1));
                this.setBzh(rs.getString(2));
                this.setXinghao(rs.getString(3)!=null?rs.getString(3):"");
                this.setNhlx(rs.getString(4));
                this.setNhyt(rs.getString(5));
                this.setZdid(rs.getString(6)!=null?rs.getString(6):"");
                this.setNhsl(rs.getString(7));
                this.setTzsl(rs.getString(8));
                this.setSjsl(rs.getString(9));
                this.setDanjia(rs.getString(10));
                this.setNhje(rs.getString(11));
                this.setTzje(rs.getString(12));
                this.setSjje(rs.getString(13));
                this.setQss(rs.getString(14)!=null?rs.getString(14):"");
                this.setJss(rs.getString(15)!=null?rs.getString(15):"");
                this.setBdate(rs.getString(16)!=null?rs.getString(16).substring(0,10):"");
                this.setEdate(rs.getString(17)!=null?rs.getString(17).substring(0,10):"");
                this.setSfr(rs.getString(18)!=null?rs.getString(18):"");
                this.setSfdate(rs.getString(19)!=null?rs.getString(19).substring(0,10):"");
                this.setPjlx(rs.getString(20));
                this.setPjh(rs.getString(21)!=null?rs.getString(21):"");
                this.setKpdate(rs.getString(22)!=null?rs.getString(22).substring(0,10):"");
                this.setMemo(rs.getString(23)!=null?rs.getString(23):"");
                this.setZdname(rs.getString(24)!=null?rs.getString(24):"");
            }

        } catch (DbException de) {

            de.printStackTrace();
        } catch (SQLException de) {

            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return this;
    }

}
