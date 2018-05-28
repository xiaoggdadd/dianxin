package com.noki.query.gaojingmanage.javabean;

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

public class GaojingManageBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
     //按地市、按机房类型明细查询
    public synchronized ArrayList getPageDataCaijiEqu(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where substr(dag.agcode, 1, 3) = agcode) sheng,dag.agname shi,cjdata.GETDATETIME sj,count(cjdata.id) CJEQU_GZSUM,count(eqegzsnum) CJEQU_GZNUM,(case when count(cjdata.id) != 0 then count(eqegzsnum)/count(cjdata.id) end)*100 CJCG_BL,ind.name housename from zhandian zd, (select cj.id,decode(cj.flag,'0',cj.id) eqegzsnum,cj.stname,substr(cj.GETDATETIME,0,7) GETDATETIME  from tab1 cj) cjdata, dianbiao db, d_area_grade dag,indexs ind where zd.shi = dag.agcode and zd.id= db.zdid and db.id=cjdata.stname and ind.code=zd.jztype and ind.type='ZDLX' "+whereStr+" group by shi,agname, dag.agcode,ind.name,cjdata.GETDATETIME ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where substr(dag.agcode, 1, 3) = agcode) sheng,dag.agname shi,cjdata.GETDATETIME sj,count(cjdata.id) CJEQU_GZSUM,count(eqegzsnum) CJEQU_GZNUM,(case when count(cjdata.id) != 0 then count(eqegzsnum)/count(cjdata.id) end)*100 CJCG_BL,ind.name housename from zhandian zd, (select cj.id,decode(cj.flag,'0',cj.id) eqegzsnum,cj.stname,substr(cj.GETDATETIME,0,7) GETDATETIME  from tab1 cj) cjdata, dianbiao db, d_area_grade dag,indexs ind where zd.shi = dag.agcode and zd.id= db.zdid and db.id=cjdata.stname and ind.code=zd.jztype and ind.type='ZDLX' "+whereStr+" group by shi,agname, dag.agcode,ind.name,cjdata.GETDATETIME ) ");
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
   
  //1.3.2.1	采集设备数据异常统计
    public synchronized ArrayList getPageDataYichang(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where substr(zd.shi, 1, 3) = agcode) sheng,dag.agname shi,substr(cj.GETDATETIME, 0, 7) sj,ind.name housename, case when cj.flag = '0' then '线路不通' else case when cj.flag = '2' then '超过上限值' else case when cj.flag = '3' then '超过下限值'else '正常数据' end end end CJEQU_YCTYPE,count(cj.id) CJCG_FSCS  from zhandian zd,tab1 cj,dianbiao db,d_area_grade dag, indexs ind where zd.shi = dag.agcode and zd.id = db.zdid and db.id = cj.stname and ind.code = zd.jztype and ind.type = 'ZDLX' "+whereStr+" group by zd.shi,dag.agname,cj.flag,ind.name, substr(cj.GETDATETIME, 0, 7) ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where substr(zd.shi, 1, 3) = agcode) sheng,dag.agname shi,substr(cj.GETDATETIME, 0, 7) sj,ind.name housename, case when cj.flag = '0' then '线路不通' else case when cj.flag = '2' then '超过上限值' else case when cj.flag = '3' then '超过下限值'else '正常数据' end end end CJEQU_YCTYPE,count(cj.id) CJCG_FSCS  from zhandian zd,tab1 cj,dianbiao db,d_area_grade dag, indexs ind where zd.shi = dag.agcode and zd.id = db.zdid and db.id = cj.stname and ind.code = zd.jztype and ind.type = 'ZDLX' "+whereStr+" group by zd.shi,dag.agname,cj.flag,ind.name, substr(cj.GETDATETIME, 0, 7))");
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
   
    
    //1.3.2.2	动环系统数据异常统计
    public synchronized ArrayList getPageDataHbYichang(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj, case when t.housetype='1' then  '通信机房' else  case when t.housetype='2' then  '基站' else case when t.housetype='3' then  '数据中心' else  case when t.housetype='4' then  '接入局所'  else case when t.housetype='5' then  '室外机柜'  else case when t.housetype='6' then  '管理用房'  else  case when t.housetype='7' then '渠道用房'  else case when t.housetype='8' then '其他非生产用房' else '请选择' end end  end end  end end  end end  housename,ind.name CJEQU_YCTYPE,sum(t.CJCG_FSCS) CJCG_FSCS from gaojingmanage_cjdatayc t, d_area_grade dag,indexs ind where substr(t.areadm, 1, 5) = dag.agcode and ind.code=t.cjequ_yctype and ind.type='YCLX'and t.yc_bz = '2' "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,housetype,ind.name ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj, case when t.housetype='1' then  '通信机房' else  case when t.housetype='2' then  '基站' else case when t.housetype='3' then  '数据中心' else  case when t.housetype='4' then  '接入局所'  else case when t.housetype='5' then  '室外机柜'  else case when t.housetype='6' then  '管理用房'  else  case when t.housetype='7' then '渠道用房'  else case when t.housetype='8' then '其他非生产用房' else '请选择' end end  end end  end end  end end  housename,ind.name CJEQU_YCTYPE,sum(t.CJCG_FSCS) CJCG_FSCS from gaojingmanage_cjdatayc t, d_area_grade dag,indexs ind where substr(t.areadm, 1, 5) = dag.agcode and ind.code=t.cjequ_yctype and ind.type='YCLX'and t.yc_bz = '2' "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode,housetype,ind.name) ");
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
