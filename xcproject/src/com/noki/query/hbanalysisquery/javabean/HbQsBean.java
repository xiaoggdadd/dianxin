package com.noki.query.hbanalysisquery.javabean;

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

public class HbQsBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
    
    
    //省级综合能耗
    public  ArrayList getData(String[] months,List shi) {
    	System.out.println("CityHouseBean-getPageDataSjZhnQs:"+months);
        //ArrayList rerurnList = new ArrayList();
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        ResultSet rs = null;
        for(int j=0;j<shi.size();j++){
          for(int i=0;i<months.length;i++){
        	   StringBuffer s2 = new StringBuffer();
               s2.append("select sum(t.dl_total) from MONTHSUMMARY t where t.shi='"+shi.get(j)+"' and t.summarymonth='"+months[i]+"'");
               
               try {
                   db.connectDb();
                   System.out.println(s2.toString());
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
        	
        	
        }
     
        return list;
    }
    
    //市级耗电数据查询
    public synchronized ArrayList getShiData(ArrayList xianCode,String[] months) {
    	 ArrayList list = new ArrayList();
         DataBase db = new DataBase();
         ResultSet rs = null;
         for(int j=0;j<xianCode.size();j++){
           for(int i=0;i<months.length;i++){
         	   StringBuffer s2 = new StringBuffer();
                s2.append("select sum(t.dl_total) from MONTHSUMMARY t where t.xian='"+xianCode.get(j)+"' and t.summarymonth='"+months[i]+"'");
                System.out.println("--"+s2.toString());
                try {
                    db.connectDb();
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
         }
        return list;
    }
    //市级 图 耗电数据查询
    public synchronized ArrayList getShiChartData(ArrayList xianCode,String[] months) {
    	 ArrayList list = new ArrayList();
         DataBase db1 = new DataBase();
         ResultSet rs = null;
         for(int j=0;j<xianCode.size();j++){
           for(int i=0;i<months.length;i++){
         	   StringBuffer s2 = new StringBuffer();
                s2.append("select sum(t.dl_total) from MONTHSUMMARY t where t.xian='"+xianCode.get(j)+"' and t.summarymonth='"+months[i]+"'");
                try {
                    db1.connectDb();
                    rs = db1.queryAll(s2.toString());
                    if(rs.next()) {
                      list.add(null!=rs.getString(1)?rs.getString(1):"0");
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
                        db1.close();
                    } catch (DbException de) {
                        de.printStackTrace();
                    }

                }
           }
         }
        return list;
    }
  
}
