package com.noki.query.sortanalysis.javabean;

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

public class SortAnalysisBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

   
    //综合能耗
    public synchronized ArrayList getPageDataZhnl(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select ROWNUM ,t.* from (select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) +sum(t.trqxhl) sum_hz from znhtj_zhhn t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode " + whereStr + " group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode order by sum_hz) t");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) +sum(t.trqxhl) sum_hz from znhtj_zhhn t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode  " + whereStr + " group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode order by sum_hz) t ");
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
    public synchronized ArrayList getPageDatahdl(int start,String whereStr,String sort) {
    	System.out.println("CityHouseBean-getPageDataZhnl:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("SELECT ROWNUM, T.* FROM (SELECT '山东省'as sheng,aa.shiname,to_char(a.startmonth,'yyyy-mm') as sj,SUM(a.blhdl)dl FROM ALLINFO_VIEW AA, AMMETERDEGREES A, ELECTRICFEES E WHERE AA.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND e.manualauditstatus>='1' "+whereStr+" AND aa.dbyt='dbyt01' GROUP BY aa.shiname,a.startmonth)T");
        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println("省级总耗电量排名查询："+s2.toString());
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
   
  //1.1.1.3.1	地市能耗统计查询--综合能耗
    public synchronized ArrayList getPageDataZhnlCity(int start,String whereStr) {
    	System.out.println("CityHouseBean-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select ROWNUM, t.* from (select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) + sum(t.trqxhl) sum_hz from znhtj_zhhn_city t, d_area_grade  dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode " + whereStr + " group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode, zd.jzname order by sum_hz) t ");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
        		"(select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) + sum(t.trqxhl) sum_hz from znhtj_zhhn_city t, d_area_grade  dag,zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode " + whereStr + " group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode, zd.jzname order by sum_hz) t ");

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
    public synchronized ArrayList getPageDatahdlCity(int start,String whereStr,String sort,String pm) {
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();        
        s2.append("select ROWNUM, t.* from (select  dag.agname shi,zdlxhdl.jzname,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0)) +sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SUM_HJ from (select zd.shi,zd.jzname,zd.property,ad.blhdl,to_char(ad.endmonth,'yyyy-mm') sj from zhandian zd,ammeterdegrees ad,ammeters am where zd.id = am.stationid and zd.qyzt='1' and ad.manualauditstatus='1' and zd.xuni='0' and am.ammeteruse='dbyt01' and am.ammeterid = ad.ammeterid_fk) zdlxhdl, d_area_grade dag where zdlxhdl.shi = dag.agcode "+whereStr+" group by dag.agname,zdlxhdl.jzname, zdlxhdl.sj order by sum_hj "+sort+") t");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println("市级总耗电量排名查询："+s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select ROWNUM, t.* from (select  dag.agname shi,zdlxhdl.jzname,zdlxhdl.sj sj,sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0)) +sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0)) + sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SUM_HJ from (select zd.shi,zd.jzname,zd.property,ad.blhdl,to_char(ad.endmonth,'yyyy-mm') sj from zhandian zd,ammeterdegrees ad,ammeters am where zd.id = am.stationid and zd.qyzt='1' and ad.manualauditstatus='1' and zd.xuni='0' and am.ammeteruse='dbyt01' and am.ammeterid = ad.ammeterid_fk) zdlxhdl, d_area_grade dag where zdlxhdl.shi = dag.agcode "+whereStr+" group by dag.agname,zdlxhdl.jzname, zdlxhdl.sj order by sum_hj "+sort+") t)");
            System.out.println("市级总耗电量排名查询分页："+s2.toString());
            rs = db.queryAll(strall.toString());            
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQuery(start, s2.toString(),pm));
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
