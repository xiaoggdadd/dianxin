package com.noki.query.tjhzquery.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import java.util.Date;
import com.noki.util.MD5;

public class SjZnhtjBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

    public synchronized ArrayList getPageDataNhl(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select sumt.*,t.* from " +
        		"(select  sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
        		"sum(trqxhl) sum_trqxhl from ( select   (select agname  from d_area_grade " +
        		"where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,sum(t.hdl) hdl," +
        		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl from znhtj_nhl t, " +
        		"d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
        		"agname,dag.agcode)) sumt,(select  (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng," +
        		"dag.agname shi,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
        		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_nhl t, " +
        		"d_area_grade dag  where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
        		"agname,dag.agcode) t ");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select  sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
            		"sum(trqxhl) sum_trqxhl from ( select   (select agname  from d_area_grade " +
            		"where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,sum(t.hdl) hdl," +
            		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl from znhtj_nhl t, " +
            		"d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
            		"agname,dag.agcode)) sumt,(select  (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng," +
            		"dag.agname shi,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
            		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_nhl t, " +
            		"d_area_grade dag  where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
            		"agname,dag.agcode) t ");
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
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

        return list;
    }
    //综合能耗
    public synchronized ArrayList getPageDataZhnl(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select sumt.*,t.* from " +
        		"(select  sum(sum_hz) sum_hz_hj,sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
        		"sum(trqxhl) sum_trqxhl from ( select sum(t.hdl)+ sum(t.sxhl)+sum(t.qyxhl)+sum(t.cyxhl)+sum(t.trqxhl) sum_hz,sum(t.hdl) hdl," +
        		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl from znhtj_zhhn t, " +
        		"d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
        		"agname,dag.agcode)) sumt,(select  (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng," +
        		"dag.agname shi,t.sj,sum(t.hdl)+ sum(t.sxhl)+sum(t.qyxhl)+sum(t.cyxhl)+sum(t.trqxhl) sum_hz," +
        		"sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
        		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_zhhn t, " +
        		"d_area_grade dag  where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
        		"agname,dag.agcode) t ");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select  sum(sum_hz) sum_hz_hj,sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
            		"sum(trqxhl) sum_trqxhl from ( select sum(t.hdl)+ sum(t.sxhl)+sum(t.qyxhl)+sum(t.cyxhl)+sum(t.trqxhl) sum_hz,sum(t.hdl) hdl," +
            		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl from znhtj_zhhn t, " +
            		"d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
            		"agname,dag.agcode)) sumt,(select  (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng," +
            		"dag.agname shi,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
            		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_zhhn t, " +
            		"d_area_grade dag  where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
            		"agname,dag.agcode) t ");
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
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

        return list;
    }
  //1.1.1.2.2	耗电量统计查询
    public synchronized ArrayList getPageDataHdltj(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname sheng,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) + sum(t.trqxhl) sum_hz," +
        		"sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl " +
        		"from znhtj_zhhn t, d_area_grade dag where substr(t.areadm, 1, 3) = dag.agcode and t.sj in(2011,2010) group by  sj, agname");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select  sum(sum_hz) sum_hz_hj,sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
            		"sum(trqxhl) sum_trqxhl from ( select sum(t.hdl)+ sum(t.sxhl)+sum(t.qyxhl)+sum(t.cyxhl)+sum(t.trqxhl) sum_hz,sum(t.hdl) hdl," +
            		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl from znhtj_zhhn t, " +
            		"d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
            		"agname,dag.agcode)) sumt,(select  (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng," +
            		"dag.agname shi,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
            		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_zhhn t, " +
            		"d_area_grade dag  where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj," +
            		"agname,dag.agcode) t ");
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
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

        return list;
    }
    
  //1.1.1.2.3	各地市耗电量统计查询
    public synchronized ArrayList getPageDatahdl(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where substr(zdlxhdl.shi, 1, 3) = agcode) sheng, " +
        				"dag.agname shi,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0))+" +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0))+" +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0)) " +
        				"SCYFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0)) JZHDL," +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SJZXHDL from (select zd.shi,zd.property,ad.blhdl,to_char(ad.endmonth,'yyyy-mm') sj " +
        				"from zhandian zd, ammeterdegrees ad, dianbiao am,electricfees e where zd.id=am.zdid and zd.xuni='0' and zd.qyzt='1' and am.dbyt='dbyt01' " +
        				"and e.manualauditstatus>='1' and ad.ammeterdegreeid=e.ammeterdegreeid_fk and am.dbid = ad.ammeterid_fk ) zdlxhdl,d_area_grade dag " +
        				"where zdlxhdl.shi=dag.agcode "+whereStr+"group by dag.agname ,zdlxhdl.shi,zdlxhdl.sj");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println("各地市耗电量统计查询"+s2.toString());
        try {
            db.connectDb();
           
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    
    //各地市耗电量统计查询导出
    
    public synchronized ArrayList getPageDatahdl(String whereStr) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select t.*,tt.* from (select sum(SUM_HJ) SUM_ZHJ,sum(SCYFHDL) SUM_SCYFHDL,sum(TXJFHDL) SUM_TXJFHDL,sum(JZHDL) SUM_JZHDL,sum(SJZXHDL) SUM_SJZXHDL from(select (select agname from d_area_grade where substr(zdlxhdl.shi, 1, 3) = agcode) sheng, dag.agname shi,zdlxhdl.sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0)) SCYFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0)) JZHDL,sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SJZXHDL from (select zd.shi,zd.property,ad.blhdl,to_char(ad.endmonth,'yyyy-mm') sj from zhandian zd, ammeterdegrees ad, ammeters am where zd.id=am.stationid  and zd.xuni='0' and am.ammeteruse='dbyt01' and am.ammeterid = ad.ammeterid_fk ) zdlxhdl,d_area_grade dag where zdlxhdl.shi=dag.agcode "+whereStr+" group by dag.agname ,zdlxhdl.shi,zdlxhdl.sj)) t,(select (select agname from d_area_grade where substr(zdlxhdl.shi, 1, 3) = agcode) sheng, dag.agname shi,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0)) SCYFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0)) JZHDL,sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SJZXHDL from (select zd.shi,zd.property,ad.blhdl,to_char(ad.endmonth,'yyyy-mm') sj from zhandian zd, ammeterdegrees ad, ammeters am where zd.id=am.stationid and zd.xuni='0' and zd.qyzt='1' and am.ammeteruse='dbyt01' and ad.manualauditstatus='1' and am.ammeterid = ad.ammeterid_fk ) zdlxhdl,d_area_grade dag where zdlxhdl.shi=dag.agcode "+whereStr+"group by dag.agname ,zdlxhdl.shi,zdlxhdl.sj) tt");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(s2.toString());
        try {
            db.connectDb();
          
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    //1.1.1.3.1	地市能耗统计查询--能耗量
    public synchronized ArrayList getPageDataNhlCity(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select sumt.*,t.* from " +
        		"(select  sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
        		"sum(trqxhl) sum_trqxhl from ( select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) hdl," +
        		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl " +
        		"from znhtj_nhl_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname)) sumt,(select " +
        		"dag.agname shi,zd.jzname,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
        		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_nhl_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname ) t ");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
        		"(select  sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
        		"sum(trqxhl) sum_trqxhl from ( select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) hdl," +
        		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl " +
        		"from znhtj_nhl_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname)) sumt,(select " +
        		"dag.agname shi,zd.jzname,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
        		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_nhl_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname ) t ");

            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
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

        return list;
    }
  //1.1.1.3.1	地市能耗统计查询--综合能耗
    public synchronized ArrayList getPageDataZhnlCity(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select sumt.*,t.* from " +
        		"(select  sum(sum_hz) sum_hz_hj,sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
        		"sum(trqxhl) sum_trqxhl from ( select sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) +sum(t.trqxhl) sum_hz," +
        		"sum(t.hdl) hdl," +
        		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl " +
        		"from znhtj_zhhn_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname)) sumt,(select " +
        		"dag.agname shi,zd.jzname,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) +sum(t.trqxhl) sum_hz,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
        		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_zhhn_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname ) t ");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
        		"(select  sum(hdl) sum_hdl,sum(sxhl) sum_sxhl,sum(qyxhl) sum_qyxhl,sum(cyxhl) sum_cyxhl," +
        		"sum(trqxhl) sum_trqxhl from ( select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) hdl," +
        		"sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl " +
        		"from znhtj_zhhn_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname)) sumt,(select " +
        		"dag.agname shi,zd.jzname,t.sj,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl, " +
        		"sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl  from znhtj_zhhn_city t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,zd.jzname ) t ");

            rs = db.queryAll(strall.toString());
            if (rs.next()) {
                this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
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

        return list;
    }
    
    //1.1.1.3.2	站点耗电量分项统计查询
    public synchronized ArrayList getPageDatahdlCity(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select t.*, tt.* from " +
        		"(select sum(SUM_HJ) SUM_ZHJ,sum(SCYFHDL) SUM_SCYFHDL,sum(TXJFHDL) SUM_TXJFHDL,sum(JZHDL) SUM_JZHDL,sum(SJZXHDL) SUM_SJZXHDL " +
        		"from (select  dag.agname shi,zdlxhdl.jzname,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) + " +
        		"sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree),0)) +sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree),0)) " +
        		"+ sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.actualdegree),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) SCYFHDL," +
        		"sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree), 0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree), 0)) JZHDL," +
        		" sum(nvl(decode(zdlxhdl.property, 'zdsx04',zdlxhdl.actualdegree),0)) SJZXHDL from (select zd.shi,zd.jzname,zd.property,ad.actualdegree,ad.endmonth sj from zhandian zd,ammeterdegrees ad,ammeters am" +
        		" where zd.id = am.stationid and am.ammeterid = ad.ammeterid_fk) zdlxhdl, d_area_grade dag where zdlxhdl.shi = dag.agcode" + whereStr+"" +
        		" group by dag.agname,zdlxhdl.jzname, zdlxhdl.sj)) t,(select  dag.agname shi,zdlxhdl.jzname,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) + " +
        		"sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree),0)) +sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree),0)) +" +
        		" sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.actualdegree),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) SCYFHDL," +
        		"sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree), 0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree), 0)) JZHDL, " +
        		"sum(nvl(decode(zdlxhdl.property, 'zdsx04',zdlxhdl.actualdegree),0)) SJZXHDL from (select zd.shi,zd.jzname,zd.property,ad.actualdegree,ad.endmonth sj" +
        		" from zhandian zd,ammeterdegrees ad,ammeters am where zd.id = am.stationid and am.ammeterid = ad.ammeterid_fk) zdlxhdl, d_area_grade dag where " +
        		"zdlxhdl.shi = dag.agcode " + whereStr+" group by dag.agname,zdlxhdl.jzname, zdlxhdl.sj) tt");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select t.*, tt.* from (select sum(SUM_HJ) SUM_ZHJ,sum(SCYFHDL) SUM_SCYFHDL,sum(TXJFHDL) SUM_TXJFHDL,sum(JZHDL) SUM_JZHDL,sum(SJZXHDL) SUM_SJZXHDL from (select  dag.agname shi,zdlxhdl.jzname,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree),0)) +sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.actualdegree),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) SCYFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree), 0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree), 0)) JZHDL, sum(nvl(decode(zdlxhdl.property, 'zdsx04',zdlxhdl.actualdegree),0)) SJZXHDL from (select zd.shi,zd.jzname,zd.property,ad.actualdegree,ad.endmonth sj from zhandian zd,ammeterdegrees ad,ammeters am where zd.id = am.stationid and am.ammeterid = ad.ammeterid_fk) zdlxhdl, d_area_grade dag where zdlxhdl.shi = dag.agcode" + whereStr+" group by dag.agname,zdlxhdl.jzname, zdlxhdl.sj)) t,(select  dag.agname shi,zdlxhdl.jzname,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree),0)) +sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.actualdegree),0)) SUM_HJ,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.actualdegree),0)) SCYFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.actualdegree), 0)) TXJFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.actualdegree), 0)) JZHDL, sum(nvl(decode(zdlxhdl.property, 'zdsx04',zdlxhdl.actualdegree),0)) SJZXHDL from (select zd.shi,zd.jzname,zd.property,ad.actualdegree,ad.endmonth sj from zhandian zd,ammeterdegrees ad,ammeters am where zd.id = am.stationid and am.ammeterid = ad.ammeterid_fk) zdlxhdl, d_area_grade dag where zdlxhdl.shi = dag.agcode " + whereStr+" group by dag.agname,zdlxhdl.jzname, zdlxhdl.sj) tt)");
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
                this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
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

        return list;
    }
}
