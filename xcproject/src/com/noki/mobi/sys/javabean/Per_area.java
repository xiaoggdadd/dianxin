package com.noki.mobi.sys.javabean;

import com.noki.database.DbException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.util.Query;
import com.noki.page.NPageBean;

/**
 * <p>Title: 人员分配区域</p>
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
public class Per_area {
    public Per_area() {
    }

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

    public synchronized ArrayList getPageData(int start, String Shengcode) {
        ArrayList list = new ArrayList();

        StringBuffer s2 = new StringBuffer();
        s2.append(
                "select agid,agcode,agname,fagcode,agrade from d_area_grade order by agcode");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            /*
                         StringBuffer strall = new StringBuffer();
                         strall.append("select count(*) from d_area_grade");
                         rs = db.queryAll(strall.toString());
                         if (rs.next()) {
                this.setAllPage(rs.getInt(1) / 15 + 1);
                         }
                         NPageBean nbean = new NPageBean();
             rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
             */
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        }
        /*catch (SQLException de) {
         de.printStackTrace();
                }*/

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
    //分配负责区域
    public synchronized ArrayList getPageData(String ssagcode) {
        ArrayList list = new ArrayList();

        StringBuffer s2 = new StringBuffer();
      //  s2.append(
      //          "select agid,agcode,agname,fagcode,agrade from d_area_grade");
      // s2.append(" where agcode like '"+ssagcode+"%' order by agcode");
        if(ssagcode.equals("admins")){
       	 s2.append("SELECT D.AGID, D.AGCODE, D.AGNAME, D.FAGCODE, D.AGRADE FROM D_AREA_GRADE D ORDER BY D.AGCODE" );
       }else{
       	 s2.append("SELECT D.AGID, D.AGCODE, D.AGNAME, D.FAGCODE, D.AGRADE FROM D_AREA_GRADE D, PER_AREA P" +
            		" WHERE D.AGCODE = P.AGCODE  AND P.ACCOUNTNAME = '"+ssagcode+"' ORDER BY D.AGCODE");
       }
       
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            /*
                         StringBuffer strall = new StringBuffer();
                         strall.append("select count(*) from d_area_grade");
                         rs = db.queryAll(strall.toString());
                         if (rs.next()) {
                this.setAllPage(rs.getInt(1) / 15 + 1);
                         }
                         NPageBean nbean = new NPageBean();
             rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
             */
            System.out.println("分配负责区域:"+s2.toString());
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        }
        /*catch (SQLException de) {
         de.printStackTrace();
                }*/

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

    //分配负责区域添加
    public synchronized String changePer_area(String[] agcode, String accountid) {
        StringBuffer list = new StringBuffer();
        list.append("no");

        DataBase db = new DataBase();
         List<String> sqlList = new ArrayList<String>();
	     String sql1="delete from per_area where  accountid=" + accountid;
	     try {
			db.connectDb();
			db.update(sql1);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		boolean flag=false;
		try {
			for (int i = 0; i < agcode.length; i++) {
				sql.setLength(0);
				sql.append("insert into per_area(accountid,accountname,agcode)");
				sql.append(" select " + accountid + ",accountname,'"+ agcode[i] + "' from account where accountid=" + accountid);
				sqlList.add(sql.toString());
			}
			System.out.println("分配负责区域添加:" + sqlList);
			String[] sqlArr = sqlList.toArray(new String[sqlList.size()]);
			db.updateBatch(sqlArr);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
//         boolean flag=false;
//       for(int i=0;i<agcode.length;i++){
//    	   sql.setLength(0);
//    	   sql.append("insert into per_area(accountid,accountname,agcode)");
//           sql.append(" select " + accountid + ",accountname,'" + agcode[i] +"' from account where accountid=" + accountid);
//           System.out.println("分配负责区域添加:"+sql.toString());
//        try {
//            db.connectDb();
//            if (db.update(sql.toString())<=0) {
//            	flag=true;
//            }
//        }
//
//        catch (DbException de) {
//            de.printStackTrace();
//        }
//
//        finally {
//
//            try {
//                db.close();
//            } catch (DbException de) {
//                de.printStackTrace();
//            }
//
//        }
//       }
       if (flag){
    	  list.setLength(0);
    	  list.append("添加区域成功"); 
       }
        
        return list.toString();
    }

    public synchronized String getParea(String accountid) {
        StringBuffer list = new StringBuffer();
        list.append(",");
        DataBase db = new DataBase();
        StringBuffer sql = new StringBuffer();
        sql.append("select agcode from per_area  where accountid=" + accountid);

        try {
            db.connectDb();

            ResultSet rs = db.queryAll(sql.toString());
            while (rs.next()) {
                list.append(rs.getString(1) + ",");
            }

        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
            de.printStackTrace();
        }

        finally {

            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }

        return list.toString();
    }

    public synchronized int matchPer_areat(String accountid, String[] rightId) {
        int ret = 0;

        DataBase db = new DataBase();
        try {
            db.connectDb();
            StringBuffer sql = new StringBuffer();
            StringBuffer del = new StringBuffer();
            del.append("delete from per_area where accountid=" + accountid);
            db.setAutoCommit(false);
            db.update(del.toString());
            if(rightId!=null){
                for (int i = 0; i < rightId.length; i++) {
                    sql.setLength(0);
                    sql.append(
                            "insert into per_area(accountid,accountname,agcode)");
                    sql.append(" select " + accountid + ",accountname,'" +
                               rightId[i] +
                               "' from account where accountid=" + accountid);
                    db.update(sql.toString());
                }
            }
            db.commit();
            db.setAutoCommit(true);
            ret = 1;

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
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return ret;
    }
    
    //分配负责地市
    public synchronized ArrayList getPageDatads(String ssagcode) {
        ArrayList list = new ArrayList();

        StringBuffer s2 = new StringBuffer();
//        s2.append(
//                "select agid,agcode,agname,fagcode,agrade from d_area_grade");
//       s2.append(" where agcode like '"+ssagcode+"%' and agrade<='3' order by agcode");
       
       if(ssagcode.equals("admins")){
      	 s2.append("SELECT D.AGID, D.AGCODE, D.AGNAME, D.FAGCODE, D.AGRADE FROM D_AREA_GRADE D WHERE AGRADE<='3' ORDER BY D.AGCODE" );
      }else{
      	 s2.append("SELECT D.AGID, D.AGCODE, D.AGNAME, D.FAGCODE, D.AGRADE FROM D_AREA_GRADE D, PER_AREA P" +
           		" WHERE D.AGCODE = P.AGCODE  AND P.ACCOUNTNAME = '"+ssagcode+"' AND D.AGRADE<='3' ORDER BY D.AGCODE");
      }
     
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            System.out.println("分配负责区域地市:"+s2.toString());
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        }
        /*catch (SQLException de) {
         de.printStackTrace();
                }*/

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
    //分配负责地市添加
    public synchronized String changePer_areads(String[] agcode, String accountid) {
        StringBuffer list = new StringBuffer();
        list.append("no");

        DataBase db = new DataBase();       
         String  sql1="delete from per_area where  accountid=" + accountid;
         try {
			db.connectDb();
			db.update(sql1);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         boolean flag=false;
         ResultSet rs = null;
       for(int i=0;i<agcode.length;i++){
    	   String sql2="";
    	   if(agcode[i].length()>5){
	    	   sql2 = "select agcode from d_area_grade where agcode like '"+agcode[i]+"%'";
		       System.out.println("分配负责地市----查询匹配站点:"+sql2.toString());
    	   }else{
	    	   sql2 = "select agcode from d_area_grade where agcode='"+agcode[i]+"'";
		       System.out.println("分配负责地市----市级查询匹配站点:"+sql2.toString());
    	   }
    	   try {
			rs = db.queryAll(sql2);
			StringBuffer sql = new StringBuffer();
			int a=0;
			while(rs.next()){
		        a++;
		    	//sql.setLength(0);
		    	sql.append("insert into per_area(accountid,accountname,agcode) select " + accountid + ",accountname,'" + rs.getString(1)+"' from account where accountid=" + accountid+"の");
		       // sql.append(" select " + accountid + ",accountname,'" + rs.getString(1)+"' from account where accountid=" + accountid+"");
		        System.out.println("分配负责地市添加:"+sql.toString());
			}     
		     try {
		            db.connectDb();
		            db.updateBatchDr(sql.toString().split("の"));//批量处理大量数据插入
		            System.out.println("分配负责地市添加:"+a);
		            flag=true;         	
		         
		        }catch (DbException de) {
		            de.printStackTrace();
		        }
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
       if (!flag){
    	  list.setLength(0);
    	  list.append("ok"); 
       } 
        return list.toString();
    }


}
