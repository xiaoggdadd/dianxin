package com.noki.tongjichaxu.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.function.CityQueryBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class Zhandianfentanmx {
	private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
    public ArrayList getData(String dbid){
    	
    	ArrayList list=new ArrayList();
    	String sql="select t.sheiebanid,t.mingcheng,t.guige,t.shuoshuzhuanye, t.shuoshuwangyuan,t.kjkmcode,t.qcbcode,t.zymxcode,t.dbili, t.sccj,t.zcbh,t.bccd,t.beizhu " +
    			"from sbgl t, dianbiao d, zhandian z,indexs i " +
    			"where i.code=z.property and z.id = d.zdid and t.dianbiaoid = d.dbid  and d.dbid='"+dbid+"' and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '263'))";
    	DataBase db=new DataBase();
    	ResultSet set=null;
    	System.out.println("站点分摊明细："+sql.toString());
    	try {
			set=db.queryAll(sql);
			Query query=new Query();
			list=query.query(set);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    //站点分摊明细
    public synchronized ArrayList getPageData(int start,String whereStr,String loginId) {
        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer sql = new StringBuffer();
		sql.append("select distinct z.jzname, i.name,t.dianbiaoid " +
				"from sbgl t, dianbiao d, zhandian z,indexs i" +
				" where i.code=z.property and z.id = d.zdid and z.qyzt='1' and t.dianbiaoid = d.dbid "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");
       
        
        DataBase db = new DataBase();
        ResultSet rs = null;
        
        System.out.println("--"+sql.toString());
        try {
            db.connectDb();
          /*  StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from sbgl t, dianbiao d, zhandian z,indexs i" +
    				" where i.code=z.property and z.id = d.zdid and z.qyzt='1' and t.dianbiaoid = d.dbid "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }*/
           // NPageBean nbean = new NPageBean();
            rs = db.queryAll(sql.toString());
           
            Query query = new Query();
            list = query.query(rs);
            System.out.println(list.toString());
            
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
    
    
    //基础查询里的专业分摊情况查询
    public synchronized ArrayList getFt(String whereStr,String loginId,String wherebz,String whereyc,String ww,String dd) {
        ArrayList list = new ArrayList();
        //ArrayList list1 = new ArrayList();
       // CityQueryBean bean=new CityQueryBean();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);//站点负责人，详细
        StringBuffer sql = new StringBuffer();
		sql.append("SELECT zza.szdq,zza.jzname,zza.id,zza.wc,zza.wf,zza.yc,zza.wc1,zza.wf1,zza.yc1,zza.stationtype,zza.property,zza.dbid,zza.fzr  " +
				"FROM (SELECT (CASE WHEN Z.SHI IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) || " +
				"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN)ELSE''END) ||" +
				"(CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU)ELSE''END) AS SZDQ," +
				"(select name from indexs i where i.code=z.stationtype and i.type='stationtype' )as stationtype," +
				"(select name from indexs i where i.code=z.property and i.type='ZDSX' )as property,z.jzname,z.id,d.dbid,z.fzr," +
				"COUNT(DISTINCT (CASE WHEN aa.bili=100 THEN d.id END ))AS wc,COUNT(DISTINCT (CASE WHEN aa.bili=0 OR aa.bili IS NULL THEN d.id END))AS wf," +
				"COUNT(DISTINCT (CASE WHEN aa.bili<>0 AND aa.bili <>100 AND aa.bili IS NOT NULL  THEN d.id END))AS yc," +
				"COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))AS wc1," +
				"COUNT(DISTINCT d.id )-COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))- COUNT(DISTINCT(CASE WHEN cc.bili<>0 AND cc.bili <>100 AND cc.bili IS NOT NULL  THEN d.id END))as wf1," +
				"COUNT(DISTINCT(CASE WHEN cc.bili<>0 AND cc.bili <>100 AND cc.bili IS NOT NULL  THEN d.id END))AS yc1 " +
				"FROM (SELECT sss.dianbiaoid,SUM(sss.dbili)AS bili from(SELECT  DISTINCT s.dianbiaoid AS dianbiaoid,S.SHEIEBANID, S.DBILI FROM sbgl s)sss GROUP BY sss.dianbiaoid)aa," +
				"(SELECT S.DIANBIAOID,S.SHEIEBANID,S.XJID,S.SHUOSHUZHUANYE,S.KJKMCODE,S.QCBCODE,S.ZYMXCODE,s.xjbili FROM SBGL S) BB, " +
				"(SELECT s.dianbiaoid,sum(s.xjbili)/count(DISTINCT s.sheiebanid)AS bili FROM sbgl s  GROUP  BY s.dianbiaoid)cc,zhandian z,dianbiao d " +ww+
				" WHERE z.id=d.zdid AND d.dbid=aa.DIANBIAOID(+) AND d.dbid=bb.DIANBIAOID(+) AND d.dbid=cc.dianbiaoid(+)  " +dd+
				" AND d.dbyt='dbyt01' AND z.shsign='1' AND z.qyzt='1' AND d.dbqyzt='1' "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"')) " +
				" "+wherebz+" GROUP  BY z.shi,z.jzname,z.id,Z.XIAN,Z.xiaoqu,z.stationtype,z.property,d.dbid,z.fzr)zza WHERE 1=1 "+whereyc+"");   
 // -- and d.dbid in(select a.ammeterid_fk from dianduview a,dianfeiview e where a.ammeterdegreeid=e.ammeterdegreeid_fk  and e.accountmonth='2013-01')  --报账月份条件
 
       
          
        DataBase db = new DataBase();
        ResultSet rs = null;
        
        System.out.println("基础查询监测点查询："+sql.toString());
        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            while(rs.next()){
            	 CityQueryBean bean=new CityQueryBean();
            	//bean.setShi(rs.getString("shi")!=null?rs.getString("shi"):"");
            	//bean.setXian(rs.getString("xian")!=null?rs.getString("xian"):"");
            	bean.setAddress(rs.getString("szdq")!=null?rs.getString("szdq"):"");
            	bean.setJzname(rs.getString("jzname")!=null?rs.getString("jzname"):"");
            	bean.setZdid(rs.getString("id")!=null?rs.getString("id"):"");
            	bean.setZdsx(rs.getString("property")!=null?rs.getString("property"):"");
            	bean.setZdtype(rs.getString("stationtype")!=null?rs.getString("stationtype"):"");
            	bean.setDbid(rs.getString("dbid")!=null?rs.getString("dbid"):"");
            	bean.setWc(rs.getString("wc")!=null?rs.getString("wc"):"");
            	bean.setWf(rs.getString("wf")!=null?rs.getString("wf"):"");
            	bean.setYc(rs.getString("yc")!=null?rs.getString("yc"):"");
            	bean.setWc1(rs.getString("wc1")!=null?rs.getString("wc1"):"");
            	bean.setWf1(rs.getString("wf1")!=null?rs.getString("wf1"):"");
            	bean.setYc1(rs.getString("yc1")!=null?rs.getString("yc1"):"");
            	bean.setFzr(rs.getString("fzr")!=null?rs.getString("fzr"):"");
            	
            	//System.out.println("2222222222222"+bean.getJzname());
            	list.add(bean);
            }
            
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
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
    //监测点详细分信息
    public ArrayList getFtxx(String dbid,String loginId){
    	
    	ArrayList list=new ArrayList();
    	StringBuffer sql=new StringBuffer();
    	sql.append("SELECT D.DBID,T.XJID,T.MINGCHENG,T.GUIGE," +
    			"(SELECT QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.SHUOSHUZHUANYE)shuoshuzhuanye," +
    			"(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.KJKMCODE)kjkmcode," +
    			"(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.QCBCODE)qcbcode" +
    			//"(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.ZYMXCODE)zymxcode," +
    			",T.DBILI,T.XJBILI,T.SHUOSHUWANGYUAN,T.BCCD   " +
    			"FROM ZHANDIAN Z,DIANBIAO D,SBGL T WHERE Z.ID=D.ZDID AND D.DBID=T.DIANBIAOID " +
    			"AND T.DIANBIAOID='"+dbid+"' AND  (Z.XIAOQU IN (SELECT P.AGCODE FROM PER_AREA P WHERE P.ACCOUNTID='"+loginId+"'))");
    	DataBase db=new DataBase();
    	ResultSet rs=null;
    	System.out.println("站点分摊明细："+sql.toString());
    	try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            while(rs.next()){
            	CityQueryBean bean=new CityQueryBean();
            	bean.setDbid(rs.getString("dbid")!=null?rs.getString("dbid"):"");
            	bean.setXjid(rs.getString("xjid")!=null?rs.getString("xjid"):"");
            	bean.setMingcheng(rs.getString("mingcheng")!=null?rs.getString("mingcheng"):"");
            	bean.setGuige(rs.getString("guige")!=null?rs.getString("guige"):"");
            	bean.setSszy(rs.getString("shuoshuzhuanye")!=null?rs.getString("shuoshuzhuanye"):"");
            	bean.setKjkm(rs.getString("kjkmcode")!=null?rs.getString("kjkmcode"):"");
            	bean.setQcb(rs.getString("qcbcode")!=null?rs.getString("qcbcode"):"");
            	//bean.setZymx(rs.getString("zymxcode")!=null?rs.getString("zymxcode"):"");
            	bean.setDbili(rs.getString("dbili")!=null?rs.getString("dbili"):"");
            	bean.setXjbili(rs.getString("xjbili")!=null?rs.getString("xjbili"):"");
            	bean.setSswy(rs.getString("shuoshuwangyuan")!=null?rs.getString("shuoshuwangyuan"):"");
            	bean.setBchd(rs.getString("bccd")!=null?rs.getString("bccd"):"");
            	list.add(bean);
            }
            
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
