package com.noki.jizhan;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class ZLInfoForm {
    private String htid;
    private String htlx;
    private String jzdz;
    private String fwmj;
    private String louc;
    private String qydw;
    private String fkfs;
    private String skdwmc;
    private String skdwzh;
    private String skdwyh;
    private String ltqyr;
    private String htlxr;
    private String htlxtel;
    private String fzjsjine;
    private String fzsfkp;
    private String fzzzl;
    private String zhjntime;
    private String sxtime;
    private String xqtime;
    private String offtime;
    private String xqyjz;
    private String fzyjz;
    private String memo;
    private String zdid;
    private String id;
    private String qstime;
    public ZLInfoForm() {
    }

    public void setHtid(String htid) {
        this.htid = htid;
    }

    public void setHtlx(String htlx) {
        this.htlx = htlx;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
    }

    public void setFwmj(String fwmj) {
        this.fwmj = fwmj;
    }

    public void setLouc(String louc) {
        this.louc = louc;
    }

    public void setQydw(String qydw) {
        this.qydw = qydw;
    }

    public void setFkfs(String fkfs) {
        this.fkfs = fkfs;
    }

    public void setSkdwmc(String skdwmc) {
        this.skdwmc = skdwmc;
    }

    public void setSkdwzh(String skdwzh) {
        this.skdwzh = skdwzh;
    }

    public void setSkdwyh(String skdwyh) {
        this.skdwyh = skdwyh;
    }

    public void setLtqyr(String ltqyr) {
        this.ltqyr = ltqyr;
    }

    public void setHtlxr(String htlxr) {
        this.htlxr = htlxr;
    }

    public void setHtlxtel(String htlxtel) {
        this.htlxtel = htlxtel;
    }

    public void setFzjsjine(String fzjsjine) {

        this.fzjsjine = fzjsjine;
    }

    public void setFzsfkp(String fzsfkp) {
        this.fzsfkp = fzsfkp;
    }

    public void setFzzzl(String fzzzl) {
        this.fzzzl = fzzzl;
    }

    public void setZhjntime(String zhjntime) {
        this.zhjntime = zhjntime;
    }

    public void setSxtime(String sxtime) {
        this.sxtime = sxtime;
    }

    public void setXqtime(String xqtime) {
        this.xqtime = xqtime;
    }

    public void setOfftime(String offtime) {
        this.offtime = offtime;
    }

    public void setXqyjz(String xqyjz) {
        this.xqyjz = xqyjz;
    }

    public void setFzyjz(String fzyjz) {
        this.fzyjz = fzyjz;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQstime(String qstime) {
        this.qstime = qstime;
    }

    public String getHtid() {
        return htid;
    }

    public String getHtlx() {
        return htlx;
    }

    public String getJzdz() {
        return jzdz;
    }

    public String getFwmj() {
        return fwmj;
    }

    public String getLouc() {
        return louc;
    }

    public String getQydw() {
        return qydw;
    }

    public String getFkfs() {
        return fkfs;
    }

    public String getSkdwmc() {
        return skdwmc;
    }

    public String getSkdwzh() {
        return skdwzh;
    }

    public String getSkdwyh() {
        return skdwyh;
    }

    public String getLtqyr() {
        return ltqyr;
    }

    public String getHtlxr() {
        return htlxr;
    }

    public String getHtlxtel() {
        return htlxtel;
    }

    public String getFzjsjine() {

        return fzjsjine;
    }

    public String getFzsfkp() {
        return fzsfkp;
    }

    public String getFzzzl() {
        return fzzzl;
    }

    public String getZhjntime() {
        return zhjntime;
    }

    public String getSxtime() {
        return sxtime;
    }

    public String getXqtime() {
        return xqtime;
    }

    public String getOfftime() {
        return offtime;
    }

    public String getXqyjz() {
        return xqyjz;
    }

    public String getFzyjz() {
        return fzyjz;
    }

    public String getMemo() {
        return memo;
    }

    public String getZdid() {
        return zdid;
    }

    public String getId() {
        return id;
    }

    public String getQstime() {
        return qstime;
    }
//站点租赁信息查询
    public synchronized ZLInfoForm getJizhan(String zdid) {

        StringBuffer sql = new StringBuffer();
        sql.append("select id,htid,htlx,jzdz,fwmj,louc,qydw,fkfs,skdwmc,skdwzh,skdwyh,ltqyr,htlxr,htlxtel,fzjsjine,fzsfkp,");
        sql.append(
                "fzzzl,zhjntime,sxtime,xqtime,offtime,xqyjz,fzyjz,memo,zdid,qstime");
        sql.append(
                " from zdzlinfo  where zdid=" + zdid);
        System.out.println(sql);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            if (rs.next()) {
                this.setId(rs.getString("id"));
                this.setHtid(rs.getString("htid")!=null?rs.getString("htid"):"");
                this.setHtlx(rs.getString("htlx"));
                this.setJzdz(rs.getString("jzdz")!=null?rs.getString("jzdz"):"");
                this.setFwmj(rs.getString("fwmj")!=null?rs.getString("fwmj"):"");
                this.setLouc(rs.getString("louc")!=null?rs.getString("louc"):"");
                this.setQydw(rs.getString("qydw")!=null?rs.getString("qydw"):"");
                this.setFkfs(rs.getString("fkfs")!=null?rs.getString("fkfs"):"0");
                this.setSkdwmc(rs.getString("skdwmc")!=null?rs.getString("skdwmc"):"");
                this.setSkdwzh(rs.getString("skdwzh")!=null?rs.getString("skdwzh"):"");
                this.setSkdwyh(rs.getString("skdwyh")!=null?rs.getString("skdwyh"):"");
                this.setLtqyr(rs.getString("ltqyr")!=null?rs.getString("ltqyr"):"");
                this.setHtlxr(rs.getString("htlxr")!=null?rs.getString("htlxr"):"");
                this.setHtlxtel(rs.getString("htlxtel")!=null?rs.getString("htlxtel"):"");
                this.setFzjsjine(rs.getString("fzjsjine")!=null?rs.getString("fzjsjine"):"");
                this.setFzsfkp(rs.getString("fzsfkp")!=null?rs.getString("fzsfkp"):"0");
                this.setFzzzl(rs.getString("fzzzl")!=null?rs.getString("fzzzl"):"");
                this.setZhjntime(rs.getString("zhjntime")!=null?rs.getString("zhjntime").substring(0,10):"");
                this.setSxtime(rs.getString("sxtime")!=null?rs.getString("sxtime").substring(0,10):"");
                this.setXqtime(rs.getString("xqtime")!=null?rs.getString("xqtime"):"");
                this.setOfftime(rs.getString("offtime")!=null?rs.getString("offtime").substring(0,10):"");
                this.setXqyjz(rs.getString("xqyjz")!=null?rs.getString("xqyjz"):"");
                this.setFzyjz(rs.getString("fzyjz")!=null?rs.getString("fzyjz"):"");
                this.setMemo(rs.getString("memo")!=null?rs.getString("memo"):"");
                this.setQstime(rs.getString("qstime")!=null?rs.getString("qstime").substring(0,10):"");

            } else {
                this.setHtid("");
                this.setHtlx("0");
                this.setJzdz("");
                this.setFwmj("");
                this.setLouc("");
                this.setQydw("");
                this.setFkfs("0");
                this.setSkdwmc("");
                this.setSkdwzh("");
                this.setSkdwyh("");
                this.setLtqyr("");
                this.setHtlxr("");
                this.setHtlxtel("");
                this.setFzjsjine("");
                this.setFzsfkp("0");
                this.setFzzzl("");
                this.setZhjntime("");
                this.setSxtime("");
                this.setXqtime("");
                this.setOfftime("");
                this.setXqyjz("");
                this.setFzyjz("");
                this.setMemo("");
                this.setQstime("");
                this.setId("0");

            }
            this.setZdid(zdid);

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

        return this;
    }


}
