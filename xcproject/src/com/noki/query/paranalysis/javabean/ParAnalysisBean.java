package com.noki.query.paranalysis.javabean;

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
import java.text.*;

public class ParAnalysisBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
//获取各地市 查询月份（time）的耗电量（王代军）
    public synchronized ArrayList getShiData(ArrayList shiCode,String time) {
   	 ArrayList list = new ArrayList();
     DataBase db = new DataBase();
     ResultSet rs = null;
     for(int j=0;j<shiCode.size();j++){
     	   StringBuffer s2 = new StringBuffer();
            s2.append("select sum(t.dl_total) from MONTHSUMMARY t where t.shi='"+shiCode.get(j)+"' and t.summarymonth='"+time+"'");
            try {
                db.connectDb();
               // System.out.println(s2.toString()+"SQL");
                rs = db.queryAll(s2.toString());
                DecimalFormat mat=new DecimalFormat("0.0");
                Double dl=0.0;
                String hdl="0";
                if(rs.next()) {
                  dl=Double.parseDouble(null!=rs.getString(1)?rs.getString(1):"0");
                  hdl=mat.format(dl);
                  list.add(hdl);
                }
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
     }
    return list;
}
   
  //获取 （某市）各区县 查询月份（time）的耗电量（王代军）

    public synchronized ArrayList getXianData(ArrayList xianCode,String time) {
    	System.out.println(xianCode.size()+"xianCode.size()");
      	 ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        ResultSet rs = null;
        for(int j=0;j<xianCode.size();j++){
        	   StringBuffer s2 = new StringBuffer();
               s2.append("select sum(t.dl_total) from MONTHSUMMARY t where t.xian='"+xianCode.get(j)+"' and t.summarymonth='"+time+"'");
               try {
                   db.connectDb();
                   Double dl=0.0;
                   String hdl="0";
                  System.out.println("---"+s2.toString());
                   rs = db.queryAll(s2.toString());
                   DecimalFormat mat=new DecimalFormat("0.0");
                   if(rs.next()) {
                	  dl=Double.parseDouble(null!=rs.getString(1)?rs.getString(1):"0");
                	  hdl=mat.format(dl);
                     list.add(hdl);
                   }
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
        }
       return list;
   }
    //一般站点和样本站点之间对比
      public synchronized ArrayList getPageDataZd(int start,String whereStr,String topn) {
      	  System.out.println("CityHouseBean-getPageData:"+whereStr+topn);
      	  if(topn==null){
      		topn = "15";
      	  }
          ArrayList list = new ArrayList();
          CTime ct = new CTime();
          String kjnd = ct.formatShortDate().substring(0, 4);
          StringBuffer s2 = new StringBuffer();
          s2.append("select B.* from (select ROWNUM rnum, t.* from (select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) +sum(t.trqxhl) sum_hz,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl   from znhtj_zhhn_city t, d_area_grade dag, zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode  group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode, zd.jzname order by sum_hz) t) B where rnum <="+topn);

          DataBase db = new DataBase();
          ResultSet rs = null;

          try {
              db.connectDb();
              StringBuffer strall = new StringBuffer();
              strall.append("select count(*) from " +
          		"(select B.* from (select ROWNUM rnum, t.* from (select dag.agname shi,zd.jzname,t.sj,sum(t.hdl) + sum(t.sxhl) + sum(t.qyxhl) + sum(t.cyxhl) +sum(t.trqxhl) sum_hz,sum(t.hdl) hdl,sum(t.sxhl) sxhl,sum(t.qyxhl) qyxhl,sum(t.cyxhl) cyxhl,sum(t.trqxhl) trqxhl   from znhtj_zhhn_city t, d_area_grade dag, zhandian zd where t.zd_id = zd.id and substr(zd.shi, 1, 5) = dag.agcode  group by substr(dag.agcode, 1, 5), sj, agname, dag.agcode, zd.jzname order by sum_hz) t) B where rnum <="+topn+")");

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
