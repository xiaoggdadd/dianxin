package com.noki.query.jcanalysis.javabean;

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

public class JcAnalysisBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
     //节能设备应用比例           按地市、按机房类型明细查询     导出   
    public synchronized ArrayList getPageDataJnEquBl(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
		s2
				.append("select dag.agname shi,count(zdnum) zdnum_sum,count(jnzdnum) zdnum_jn,count(fjnzdnum) " +
						"zdnum_fjn from (select zd.shi,zd.id zdnum,decode(zd.jnglmk,'是',zd.id) jnzdnum, decode(zd.jnglmk,'否',zd.id) fjnzdnum" +
						" from zhandian zd where zd.qyzt='1' ) jnsbbl,d_area_grade dag where jnsbbl.shi = dag.agcode "+whereStr+" group by shi,dag.agname  ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname,count(zdnum) zdnum_sum,count(jnzdnum) zdnum_jn,count(fjnzdnum)" +
            		" zdnum_fjn from (select zd.shi,zd.id zdnum,decode(zd.jnglmk,'是',zd.id) jnzdnum, decode(zd.jnglmk,'否',zd.id) fjnzdnum " +
            		"from zhandian zd where zd.qyzt='1' ) jnsbbl,d_area_grade dag where jnsbbl.shi = dag.agcode "+whereStr+" group by shi,dag.agname  ) ");
            rs = db.queryAll(strall.toString());
            System.out.println(strall);
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
   
  //全省按地市统计查询    用电情况排名  导出
    public synchronized ArrayList getPageDataSort(int start,String whereStr,String sort) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname shi,count(distinct zd.id) ZDNUM_SUM,sum(ad.blhdl) HDL_SUM from zhandian zd, dianbiao d, ammeterdegrees ad,d_area_grade dag where zd.id = d.zdid and zd.xuni='0' and zd.qyzt='1' and d.dbyt='dbyt01' and ad.manualauditstatus='1' and d.dbid = ad.ammeterid_fk and zd.shi=dag.agcode "+whereStr+" group by dag.agname order by HDL_SUM "+sort);
        System.out.println("全省按地市统计查询(用电情况排名)"+s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname shi,count(distinct zd.id) ZDNUM_SUM,sum(ad.blhdl) HDL_SUM from zhandian zd, dianbiao d, ammeterdegrees ad,d_area_grade dag where zd.id = d.zdid  and zd.xuni='0' and zd.qyzt='1' and d.dbid='dbyt01' and ad.manualauditstatus='1'  and d.dbid = ad.ammeterid_fk and zd.shi=dag.agcode "+whereStr+" group by dag.agname order by HDL_SUM) ");
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
    
    
    //全省按机房类型查询
    public synchronized ArrayList getPageDataHouse(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj, case when t.housetype='1' then  '通信机房' else  case when t.housetype='2' then  '基站' else case when t.housetype='3' then  '数据中心' else  case when t.housetype='4' then  '接入局所'  else case when t.housetype='5' then  '接入局所'  else case when t.housetype='6' then  '接入局所'  else  case when t.housetype='7' then '接入局所'  else case when t.housetype='8' then '接入局所' else '请选择' end end  end end  end end  end end  housename,sum(t.CJNUM) CJNUM,sum(t.CJFGHOUSENUM) CJFGHOUSENUM,sum(t.HOUSENUM) HOUSENUM from caijipoint_city_house t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,housetype ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj, case when t.housetype='1' then  '通信机房' else  case when t.housetype='2' then  '基站' else case when t.housetype='3' then  '数据中心' else  case when t.housetype='4' then  '接入局所'  else case when t.housetype='5' then  '接入局所'  else case when t.housetype='6' then  '接入局所'  else  case when t.housetype='7' then '接入局所'  else case when t.housetype='8' then '接入局所' else '请选择' end end  end end  end end  end end  housename,sum(t.CJNUM) CJNUM,sum(t.CJFGHOUSENUM) CJFGHOUSENUM,sum(t.HOUSENUM) HOUSENUM from caijipoint_city_house t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,housetype) ");
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
