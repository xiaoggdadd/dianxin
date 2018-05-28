package com.noki.query.hnlcomparison.javabean;

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

public class HnlComparisonBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
     //按地市、按机房类型明细查询
    public synchronized ArrayList getPageDataZhnSjys(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname sheng,t.sj,sum(t.HNL_SJ) HNL_SJ, sum(t.HNL_YS) HNL_YS from hnl_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 3) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 3),sj,agname ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname sheng,t.sj,sum(t.HNL_SJ) HNL_SJ, sum(t.HNL_YS) HNL_YS from hnl_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 3) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 3),sj,agname) ");
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
   
  //1、	省公司实际能耗与能耗预算对比查询-分项
    public synchronized ArrayList getPageDataZhnSjysFX(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname sheng,t.sj,sum(t.hdl_sj) hdl_sj,sum(t.sxhl_sj) sxhl_sj,sum(t.qyxhl_sj) qyxhl_sj,sum(t.cyxhl_sj) cyxhl_sj,sum(t.trqxhl_sj) trqxhl_sj,sum(t.hdl_ys) hdl_ys,sum(t.sxhl_ys) sxhl_ys,sum(t.qyxhl_ys) qyxhl_ys,sum(t.cyxhl_ys) cyxhl_ys,sum(t.trqxhl_ys) trqxhl_ys  from hnlfx_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 3) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 3),sj,agname,dag.agcode ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname sheng,t.sj,sum(t.hdl_sj) hdl_sj,sum(t.sxhl_sj) sxhl_sj,sum(t.qyxhl_sj) qyxhl_sj,sum(t.cyxhl_sj) cyxhl_sj,sum(t.trqxhl_sj) trqxhl_sj,sum(t.hdl_ys) hdl_ys,sum(t.sxhl_ys) sxhl_ys,sum(t.qyxhl_ys) qyxhl_ys,sum(t.cyxhl_ys) cyxhl_ys,sum(t.trqxhl_ys) trqxhl_ys  from hnlfx_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 3) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 3),sj,agname,dag.agcode) ");
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
   
    //按地市、按机房类型明细查询
    public synchronized ArrayList getPageDataZhnSjysCity(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname shi,t.sj,sum(t.HNL_SJ) HNL_SJ, sum(t.HNL_YS) HNL_YS from hnl_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname shi,t.sj,sum(t.HNL_SJ) HNL_SJ, sum(t.HNL_YS) HNL_YS from hnl_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname) ");
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
   
  //1、	省公司实际能耗与能耗预算对比查询-分项
    public synchronized ArrayList getPageDataZhnSjysFXCity(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname shi,t.sj,sum(t.hdl_sj) hdl_sj,sum(t.sxhl_sj) sxhl_sj,sum(t.qyxhl_sj) qyxhl_sj,sum(t.cyxhl_sj) cyxhl_sj,sum(t.trqxhl_sj) trqxhl_sj,sum(t.hdl_ys) hdl_ys,sum(t.sxhl_ys) sxhl_ys,sum(t.qyxhl_ys) qyxhl_ys,sum(t.cyxhl_ys) cyxhl_ys,sum(t.trqxhl_ys) trqxhl_ys  from hnlfx_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname shi,t.sj,sum(t.hdl_sj) hdl_sj,sum(t.sxhl_sj) sxhl_sj,sum(t.qyxhl_sj) qyxhl_sj,sum(t.cyxhl_sj) cyxhl_sj,sum(t.trqxhl_sj) trqxhl_sj,sum(t.hdl_ys) hdl_ys,sum(t.sxhl_ys) sxhl_ys,sum(t.qyxhl_ys) qyxhl_ys,sum(t.cyxhl_ys) cyxhl_ys,sum(t.trqxhl_ys) trqxhl_ys  from hnlfx_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5),sj,agname,dag.agcode) ");
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
    
    
    //按地市、按机房类型明细查询
    public synchronized ArrayList getPageDataNhYsGj(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,trunc((case when sum(t.HNL_YS) != 0 then sum(t.HNL_SJ)/sum(t.HNL_YS) else 0 end)*100,2) HNL_SJ_YS  from hnl_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname,agcode ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,trunc((case when sum(t.HNL_YS) != 0 then sum(t.HNL_SJ)/sum(t.HNL_YS) else 0 end)*100,2) HNL_SJ_YS  from hnl_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname,agcode) ");
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
   
  //1、	省公司实际能耗与能耗预算对比查询-分项
    public synchronized ArrayList getPageDataNhYsGjFX(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select(select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,trunc((case when sum(t.hdl_ys) != 0 then sum(t.hdl_sj) / sum(t.hdl_ys) else 0 end)*100, 2) hdl_sj_ys,trunc((case  when sum(t.sxhl_ys) != 0 then sum(t.sxhl_sj) / sum(t.sxhl_ys) else 0 end)*100, 2) sxhl_sj_ys,trunc((case when sum(t.qyxhl_ys) != 0 then sum(t.qyxhl_sj) / sum(t.qyxhl_ys) else  0 end)*100, 2) qyxhl_sj_ys,trunc((case when sum(t.cyxhl_ys) != 0 then sum(t.cyxhl_sj) /sum(t.cyxhl_ys) else  0 end)*100, 2) cyxhl_sj_ys,trunc((case when sum(t.trqxhl_ys) != 0 then sum(t.trqxhl_sj) / sum(t.trqxhl_ys) else 0 end)*100, 2) trqxhl_sj_ys from hnlfx_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select(select agname from d_area_grade where agcode = substr(dag.agcode, 1, 3)) as sheng,dag.agname shi,t.sj,trunc(case when sum(t.hdl_ys) != 0 then sum(t.hdl_sj) / sum(t.hdl_ys) else 0 end, 2) hdl_sj_ys,trunc(case  when sum(t.sxhl_ys) != 0 then sum(t.sxhl_sj) / sum(t.sxhl_ys) else 0 end, 2) sxhl_sj_ys,trunc(case when sum(t.qyxhl_ys) != 0 then sum(t.qyxhl_sj) / sum(t.qyxhl_ys) else  0 end, 2) qyxhl_sj_ys,trunc(case when sum(t.cyxhl_ys) != 0 then sum(t.cyxhl_sj) /sum(t.cyxhl_ys) else  0 end, 2) cyxhl_sj_ys,trunc(case when sum(t.trqxhl_ys) != 0 then sum(t.trqxhl_sj) / sum(t.trqxhl_ys) else 0 end, 2) trqxhl_sj_ys from hnlfx_sj_ys t, d_area_grade dag where substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode) ");
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
   
    
    //按地市、按机房类型明细查询
    public synchronized ArrayList getPageDataNhYsCityGj(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname shi,zd.jzname,t.sj,trunc((case when sum(t.HNL_YS) != 0 then sum(t.HNL_SJ) / sum(t.HNL_YS) else 0 end) * 100,2) HNL_SJ_YS  from hnl_sj_ys t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(t.areadm, 1, 5) = dag.agcode  "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname, agcode,zd.jzname ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname shi,zd.jzname,t.sj,trunc((case when sum(t.HNL_YS) != 0 then sum(t.HNL_SJ) / sum(t.HNL_YS) else 0 end) * 100,2) HNL_SJ_YS  from hnl_sj_ys t, d_area_grade dag,zhandian zd where t.zd_id = zd.id and substr(t.areadm, 1, 5) = dag.agcode  "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname, agcode,zd.jzname) ");
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
   
  //1、	省公司实际能耗与能耗预算对比查询-分项
    public synchronized ArrayList getPageDataNhYsGjCityFX(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select dag.agname shi,zd.jzname,t.sj,trunc((case when sum(t.hdl_ys) != 0 then sum(t.hdl_sj) / sum(t.hdl_ys) else 0 end)*100, 2) hdl_sj_ys,trunc((case  when sum(t.sxhl_ys) != 0 then sum(t.sxhl_sj) / sum(t.sxhl_ys) else 0 end)*100, 2) sxhl_sj_ys,trunc((case when sum(t.qyxhl_ys) != 0 then sum(t.qyxhl_sj) / sum(t.qyxhl_ys) else  0 end)*100, 2) qyxhl_sj_ys,trunc((case when sum(t.cyxhl_ys) != 0 then sum(t.cyxhl_sj) /sum(t.cyxhl_ys) else  0 end)*100, 2) cyxhl_sj_ys,trunc((case when sum(t.trqxhl_ys) != 0 then sum(t.trqxhl_sj) / sum(t.trqxhl_ys) else 0 end)*100, 2) trqxhl_sj_ys from hnlfx_sj_ys t, d_area_grade dag,zhandian zd where  t.zd_id = zd.id and substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode,zd.jzname ");
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select dag.agname shi,zd.jzname,t.sj,trunc(case when sum(t.hdl_ys) != 0 then sum(t.hdl_sj) / sum(t.hdl_ys) else 0 end, 2) hdl_sj_ys,trunc(case  when sum(t.sxhl_ys) != 0 then sum(t.sxhl_sj) / sum(t.sxhl_ys) else 0 end, 2) sxhl_sj_ys,trunc(case when sum(t.qyxhl_ys) != 0 then sum(t.qyxhl_sj) / sum(t.qyxhl_ys) else  0 end, 2) qyxhl_sj_ys,trunc(case when sum(t.cyxhl_ys) != 0 then sum(t.cyxhl_sj) /sum(t.cyxhl_ys) else  0 end, 2) cyxhl_sj_ys,trunc(case when sum(t.trqxhl_ys) != 0 then sum(t.trqxhl_sj) / sum(t.trqxhl_ys) else 0 end, 2) trqxhl_sj_ys from hnlfx_sj_ys t, d_area_grade dag,zhandian zd where  t.zd_id = zd.id and substr(t.areadm, 1, 5) = dag.agcode "+whereStr+" group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode,zd.jzname) ");
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
