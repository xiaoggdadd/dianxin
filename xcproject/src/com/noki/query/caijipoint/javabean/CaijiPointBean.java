package com.noki.query.caijipoint.javabean;

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

public class CaijiPointBean {

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
     //按地市、按机房类型明细查询
    public synchronized ArrayList getPageDataCityHouse(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where substr(cjpoint.shi, 1, 3)=agcode) sheng,dag.agname shi,cjpoint.sj,ind.name HOUSENAME,cjpoint.cjnum,fghouse.CJFGHOUSENUM,house.housenum from d_area_grade dag,indexs ind,(select count(distinct cj.stname) CJNUM, substr(cj.GETDATETIME, 0, 7) sj,zd.shi,zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7),zd.shi,cj.stname,zd.jztype) cjpoint,(select  count(distinct zd.id) CJFGHOUSENUM, substr(cj.GETDATETIME, 0, 7) sj,zd.shi,zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7),zd.shi,zd.jztype) fghouse,(select count(distinct zd.id) housenum,zd.shi,zd.jztype from  zhandian zd group by zd.shi,zd.jztype) house where dag.agcode=cjpoint.shi and cjpoint.shi=house.shi and cjpoint.jztype=house.jztype and house.jztype=ind.code and fghouse.sj=cjpoint.sj and fghouse.shi=cjpoint.shi and fghouse.jztype= cjpoint.jztype "+whereStr);
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where substr(cjpoint.shi, 1, 3)=agcode) sheng,dag.agname shi,cjpoint.sj,ind.name HOUSENAME,cjpoint.cjnum,fghouse.CJFGHOUSENUM,house.housenum from d_area_grade dag,indexs ind,(select count(distinct cj.stname) CJNUM, substr(cj.GETDATETIME, 0, 7) sj,zd.shi,zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7),zd.shi,cj.stname,zd.jztype) cjpoint,(select  count(distinct zd.id) CJFGHOUSENUM, substr(cj.GETDATETIME, 0, 7) sj,zd.shi,zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7),zd.shi,zd.jztype) fghouse,(select count(distinct zd.id) housenum,zd.shi,zd.jztype from  zhandian zd group by zd.shi,zd.jztype) house where dag.agcode=cjpoint.shi and cjpoint.shi=house.shi and cjpoint.jztype=house.jztype and house.jztype=ind.code and fghouse.sj=cjpoint.sj and fghouse.shi=cjpoint.shi and fghouse.jztype= cjpoint.jztype "+whereStr+" )");
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
   
  //全省按地市统计查询
    public synchronized ArrayList getPageDataCity(int start,String whereStr) {
    	System.out.println("getPageDataCityHouse-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select (select agname from d_area_grade where substr(cjpoint.shi, 1, 3) = agcode) sheng,dag.agname shi,cjpoint.sj,cjpoint.cjnum,fghouse.CJFGHOUSENUM, house.housenum from d_area_grade dag,(select count(distinct cj.stname) CJNUM, substr(cj.GETDATETIME, 0, 7) sj, zd.shi from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.shi) cjpoint,(select count(distinct zd.id) CJFGHOUSENUM, substr(cj.GETDATETIME, 0, 7) sj, zd.shi from tab1 cj, zhandian zd, dianbiao db  where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.shi) fghouse,(select count(distinct zd.id) housenum, zd.shi from zhandian zd group by zd.shi) house where dag.agcode = cjpoint.shi and cjpoint.shi = house.shi and fghouse.sj = cjpoint.sj and fghouse.shi = cjpoint.shi "+whereStr);
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select (select agname from d_area_grade where substr(cjpoint.shi, 1, 3) = agcode) sheng,dag.agname shi,cjpoint.sj,cjpoint.cjnum,fghouse.CJFGHOUSENUM, house.housenum from d_area_grade dag,(select count(distinct cj.stname) CJNUM, substr(cj.GETDATETIME, 0, 7) sj, zd.shi from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.shi) cjpoint,(select count(distinct zd.id) CJFGHOUSENUM, substr(cj.GETDATETIME, 0, 7) sj, zd.shi from tab1 cj, zhandian zd, dianbiao db  where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.shi) fghouse,(select count(distinct zd.id) housenum, zd.shi from zhandian zd group by zd.shi) house where dag.agcode = cjpoint.shi and cjpoint.shi = house.shi and fghouse.sj = cjpoint.sj and fghouse.shi = cjpoint.shi "+whereStr+" ) ");
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
        s2.append("select  dag.agname sheng,cjpoint.sj, ind.name HOUSENAME, cjpoint.cjnum,fghouse.CJFGHOUSENUM,house.housenum from d_area_grade dag,indexs ind,(select count(distinct cj.stname) CJNUM,substr(cj.GETDATETIME, 0, 7) sj,zd.sheng, zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.sheng, cj.stname, zd.jztype) cjpoint,(select count(distinct zd.id) CJFGHOUSENUM, substr(cj.GETDATETIME, 0, 7) sj, zd.sheng, zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.sheng, zd.jztype) fghouse,(select count(distinct zd.id) housenum, zd.sheng, zd.jztype from zhandian zd group by zd.sheng, zd.jztype) house where dag.agcode = cjpoint.sheng and cjpoint.sheng = house.sheng and cjpoint.jztype = house.jztype and house.jztype = ind.code and fghouse.sj = cjpoint.sj and fghouse.sheng = cjpoint.sheng and fghouse.jztype = cjpoint.jztype "+whereStr);
        System.out.println(s2);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from " +
            		"(select  dag.agname sheng,cjpoint.sj, ind.name HOUSENAME, cjpoint.cjnum,fghouse.CJFGHOUSENUM,house.housenum from d_area_grade dag,indexs ind,(select count(distinct cj.stname) CJNUM,substr(cj.GETDATETIME, 0, 7) sj,zd.sheng, zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.sheng, cj.stname, zd.jztype) cjpoint,(select count(distinct zd.id) CJFGHOUSENUM, substr(cj.GETDATETIME, 0, 7) sj, zd.sheng, zd.jztype from tab1 cj, zhandian zd, dianbiao db where zd.id = db.zdid and db.id = cj.stname group by substr(cj.GETDATETIME, 0, 7), zd.sheng, zd.jztype) fghouse,(select count(distinct zd.id) housenum, zd.sheng, zd.jztype from zhandian zd group by zd.sheng, zd.jztype) house where dag.agcode = cjpoint.sheng and cjpoint.sheng = house.sheng and cjpoint.jztype = house.jztype and house.jztype = ind.code and fghouse.sj = cjpoint.sj and fghouse.sheng = cjpoint.sheng and fghouse.jztype = cjpoint.jztype "+whereStr+" ) ");
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
    
    //电表耗电量排名
    public synchronized ArrayList getPageDataHDLQuery(int start,String whereStr,String loginId) {
    	System.out.println("getPageDataHDLQuery:"+whereStr);
        ArrayList list = new ArrayList();
       // String  where[]=whereStr.split(";");
        StringBuffer s2 = new StringBuffer();
        s2.append("select zd.jzname,(select name from indexs where code = zd.jztype and type = 'ZDLX') zdlx, db.dbname as dbid,(select name from indexs where code = db.sszy and type = 'SSZY') sszy, db.dllx, sum(a.actualdegree)  sumdl  from zhandian zd, dianbiao db, ammeterdegrees a where db.dbyt = 'dbyt02' and db.zdid = zd.id and db.dbqyzt='1' and a.ammeterid_fk = db.dbid and  zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+" group by zd.jzname, zd.jztype,db.dbname,db.dllx,db.sszy order by sumdl desc");
        System.out.println("电表耗电量排名："+s2.toString());
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            String s3="select count(dbid)  from (select zd.jzname,(select name from indexs where code = zd.jztype and type = 'ZDLX') zdlx, db.dbid,(select name from indexs where code = db.sszy and type = 'SSZY') sszy, db.dllx, sum(a.actualdegree) sumdl from zhandian zd, dianbiao db, ammeterdegrees a where db.dbyt = 'dbyt02' and db.zdid = zd.id and db.dbqyzt='1' and a.ammeterid_fk = db.dbid and  zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+" group by zd.jzname, zd.jztype,db.dbid,db.dllx,db.sszy order by sumdl desc)";
            
            ResultSet rs3 = null;
            rs3=db.queryAll(s3);
            if(rs3.next()){
            	this.setAllPage((rs3.getInt(1)+14)/15);
            }
           NPageBean nbean = new NPageBean();
           rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
           Query query = new Query();
           list = query.query(rs);
           rs3.close();
           
          
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
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
    //站点耗电量排名
    public synchronized ArrayList getPageDataZDHDLQuery(int start,String whereStr,String loginId) {
    	System.out.println("getPageDataZDHDLQuery:"+whereStr);
        ArrayList list = new ArrayList();
       // String  where[]=whereStr.split(";");
        StringBuffer s2 = new StringBuffer();
        s2.append("select zd.jzname,(select name from indexs where code = zd.jztype and type = 'ZDLX') zdlx, sum(a.actualdegree) as sumdl  from zhandian zd, dianbiao db, ammeterdegrees a where db.dbyt = 'dbyt02' and db.zdid = zd.id and a.ammeterid_fk = db.dbid and  zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+" group by zd.jzname, zd.jztype order by sumdl desc");
        System.out.println(s2.toString());
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            String s3="select count(jzname)  from (select zd.jzname,(select name from indexs where code = zd.jztype and type = 'ZDLX') zdlx, sum(a.actualdegree) as sumdl from zhandian zd, dianbiao db, ammeterdegrees a where db.dbyt = 'dbyt02' and db.zdid = zd.id and a.ammeterid_fk = db.dbid and  zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+" group by zd.jzname, zd.jztype order by sumdl desc)";
            
            ResultSet rs3 = null;
            rs3=db.queryAll(s3);
            if(rs3.next()){
            	this.setAllPage((rs3.getInt(1)+14)/15);
            }
           NPageBean nbean = new NPageBean();
           rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
           Query query = new Query();
           list = query.query(rs);
           rs3.close();
           
          
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
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
    //异常报告
    public synchronized ArrayList getPageDataException(int start,String whereStr,String loginId) {
    	System.out.println("getPageDataException:"+whereStr);
        ArrayList list = new ArrayList();
       // String  where[]=whereStr.split(";");
        StringBuffer s2 = new StringBuffer();
        s2.append("select zd.jzname, t.stname,t.getdatetime,t.datavalue, i.name  from tab1 t, indexs i,zhandian zd, dianbiao db where t.flag != 1 and db.dbyt ='dbyt02' and  t.stname= db.dbid and db.zdid=zd.id and i.code = To_Char(t.flag)  and i.type = 'YCLX' and  zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+"order by t.getdatetime");
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            String s3="select count(t.stname) from tab1 t, indexs i,zhandian zd, dianbiao db where t.flag != 1 and db.dbyt ='dbyt02' and  t.stname= db.dbid and db.zdid=zd.id and i.code = To_Char(t.flag)  and i.type = 'YCLX' and  zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+"order by t.getdatetime";
            
            ResultSet rs3 = null;
            rs3=db.queryAll(s3);
            if(rs3.next()){
            	this.setAllPage((rs3.getInt(1)+14)/15);
            }
			
           NPageBean nbean = new NPageBean();
           rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
           Query query = new Query();
           list = query.query(rs);
           rs3.close();
           
          
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
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
  //省级管理类电表月耗电趋势
    public synchronized ArrayList getShengguanli(int start,String shi,String begintime,String endtime) throws SQLException {
        ArrayList list = new ArrayList();
       
        int beginN=Integer.parseInt(begintime.substring(0,4));
        int beginY=Integer.parseInt(begintime.substring(5,7));
        int endN=Integer.parseInt(endtime.substring(0,4));
        int endY=Integer.parseInt(endtime.substring(5,7));
        int time=0;
        
        time=(endN-beginN)*12+endY-beginY+1;
        List<String> year_month = new ArrayList<String>();
        for(int i = 0;i<time;i++){
        	String yue = String.valueOf(beginY);
        	if(yue.length()==1)yue = "0"+yue;
        	String year_month_tmp = beginN+"-"+yue;
        	beginY ++;
        	if(beginY>12){
        		beginY = 1;
        		beginN ++;
        	}
        	year_month.add(year_month_tmp);
        }
        String s2 = "";
        DataBase db = new DataBase();
        ResultSet rs = null;
        for(String year_month_tmp:year_month){
        s2="select sum(a.actualdegree) as hdl  from ammeterdegrees a,dianbiao d,zhandian z,d_area_grade g " +
        		"where  a.ammeterid_fk=d.dbid and d.zdid=z.id  and d.dbyt='dbyt02' and a.thisdatetime>='"+year_month_tmp+"-01' " +
        		"and a.thisdatetime<='"+year_month_tmp+"-31' and z.shi='"+shi+"' and z.shi=g.agcode group by g.agname";
        System.out.println(s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            Query query = new Query();
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(s2.toString());
            Boolean flag = false;
            while(rs.next()){
            	flag = true;
            String res=rs.getString("hdl");
            
            
            list.add(res+","+year_month_tmp);
            }
            if(!flag)list.add("0"+","+year_month_tmp);
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
    }
        return list;
    }
    //市级管理类电表月耗电趋势
    public synchronized ArrayList getShiguanli(int start,String xian,String begintime,String endtime) throws SQLException {
        ArrayList list = new ArrayList();
       
        int beginN=Integer.parseInt(begintime.substring(0,4));
        int beginY=Integer.parseInt(begintime.substring(5,7));
        int endN=Integer.parseInt(endtime.substring(0,4));
        int endY=Integer.parseInt(endtime.substring(5,7));
        int time=0;
        
        time=(endN-beginN)*12+endY-beginY+1;
        List<String> year_month = new ArrayList<String>();
        for(int i = 0;i<time;i++){
        	String yue = String.valueOf(beginY);
        	if(yue.length()==1)yue = "0"+yue;
        	String year_month_tmp = beginN+"-"+yue;
        	beginY ++;
        	if(beginY>12){
        		beginY = 1;
        		beginN ++;
        	}
        	year_month.add(year_month_tmp);
        }
        String s2 = "";
        DataBase db = new DataBase();
        ResultSet rs = null;
        for(String year_month_tmp:year_month){
        s2="select sum(a.blhdl) as hdl  from ammeterdegrees a,dianbiao d,zhandian z,d_area_grade g " +
        		"where  a.ammeterid_fk=d.dbid and d.zdid=z.id  and d.dbyt='dbyt02' and to_char(a.thisdatetime,'yyyy-mm-dd')>='"+year_month_tmp+"-01' " +
        		"and to_char(a.thisdatetime,'yyyy-mm-dd')<='"+year_month_tmp+"-31' and z.xian='"+xian+"' and z.xian=g.agcode group by g.agname";
        System.out.println("--"+s2.toString());
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            Query query = new Query();
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(s2.toString());
            Boolean flag = false;
            while(rs.next()){
            	flag = true;
            String res=rs.getString("hdl");
            
            
            list.add(res+","+year_month_tmp);
            }
            if(!flag)list.add("0"+","+year_month_tmp);
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
    }
        return list;
    }
   // 地区管理类电表数据统计
    public synchronized ArrayList getAgcode(String pcode,String userid,String begintime,String endtime,String str) {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        String time1="",time2="";
        time1=begintime+"-01";
        time2=endtime+"-31";
        System.out.println(time1+"--"+time2);
        if(pcode.equals("0")){
            return null;
        }else{
            sql.append("select a.agname,(select count(z.id) from zhandian z,indexs tt where z.jztype = tt.code "+str+" and a.agcode=z.shi) as zdcount," +
            		"(select count(d.id) from dianbiao d,indexs tt,zhandian z  where z.jztype = tt.code "+str+" and a.agcode=z.shi  and z.id=d.zdid and d.dbqyzt='1' and d.dbyt='dbyt02' ) as dbcount, " +
            		"(select sum(t.actualdegree) from ammeterdegrees t where t.thisdatetime>='"+time1+"' and t.thisdatetime<='"+time2+"' and t.ammeterid_fk in (select a.ammeterid " +
            		"from ammeters a  where a.ammeteruse = 'dbyt02' and a.stationid in (select z.id  from zhandian z,indexs tt  where 1 = 1 and z.jztype=tt.code "+str+" and z.shi=a.agcode and z.xuni = '0'))) as ydcount " +
            		"from d_area_grade a where a.fagcode='"+pcode+"' and agcode in (select t.agcode from per_area t where t.accountid='"+userid+"')");
        }
       System.out.println("地区电表数量统计："+sql.toString());
       DataBase db = new DataBase();
       ResultSet rs = null;

       try {
         db.connectDb();

         rs = db.queryAll(sql.toString());
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
           }
           catch (SQLException se) {
             se.printStackTrace();
           }
         }
         try {
           db.close();
         }
         catch (DbException de) {
           de.printStackTrace();
         }

       }


        return list;
      }
}
