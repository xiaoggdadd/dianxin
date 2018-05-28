package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class CaiwuzongchaxunBean {
	
	 private int allPage;
	    public void setAllPage(int allpage) {
	        this.allPage = allpage;

	    }

	    public int getAllPage() {
	        return this.allPage;
	    }
//财务总查询
	    public synchronized ArrayList getCaiwu( String whereStr, String loginId) {
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer();
	        CTime ct = new CTime();
	        String kjnd = ct.formatShortDate().substring(0, 4);
	        DataBase db = new DataBase();
	        ResultSet rs = null;
	        
	        try {
	            db.connectDb();
	    /*     sql.append("select sum(e.NETWORKDF)AS SC,sum(e.informatizationdf)AS XXHZC,sum(e.administrativedf)AS BG," +
	         		"sum(e.marketdf)AS YY,sum(e.builddf)AS JSTZ,count(e.electricfee_id)AS TIAOSHU" +
	         		" from (select sb.shuoshuzhuanye,el.networkdf,el.informatizationdf,el.administrativedf," +
	         		"el.marketdf,el.builddf,el.electricfee_id,sb.shuoshuzhuanye,sb.qcbcode" +
	         		" from sbgl sb, dianbiao db, ammeterdegrees amm, electricfees el,zhandian zd " +
	         		" where db.dbid = sb.dianbiaoid and  zd.id=db.zdid and amm.ammeterid_fk = db.dbid" +
	         		" and amm.ammeterdegreeid = el.ammeterdegreeid_fk   "+whereStr+" " +
	         		" and (zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"')))e");*/

	            
	            sql.append("select s.shuoshuzhuanye,s.qcbcode,s.kjkmcode,s.zymxcode,count( distinct e.ammeterid_fk)tiaoshu," +
	            		"sum(e.networkdf*s.xjbili/100)e,sum(e.informatizationdf*s.xjbili/100)a," +
	            		"sum(e.administrativedf*s.xjbili/100)b,sum(e.marketdf*s.xjbili/100)c," +
	            		"sum(e.builddf*s.xjbili/100)d,sum(e.dddf*s.xjbili/100)f," +
	            		"(select distinct qcbname from qcbdf qc where s.qcbcode=qc.qcbcode) as qcb," +
	            		"(select distinct qcbname from qcbdf qc where qc.qcbcode=s.kjkmcode) as kjkm," +
	            		"(select name from indexs i where i.code=s.zymxcode and i.type='ZYMX') as zymx," +
	            		"(select distinct qcbname from qcbdf qc where qc.qcbcode=s.shuoshuzhuanye ) as sszy" +
	            		" from zhandian z, dianbiao d, sbgl s, caiwuquery_view e" +
	            		" where z.id = d.zdid and d.dbid = s.dianbiaoid and d.dbid = e.ammeterid_fk   "+whereStr+"" +
	            		"  and (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))" +
	            		" group by s.shuoshuzhuanye,s.qcbcode,s.kjkmcode,s.zymxcode"); 
	          
	            //e.manualauditstatus>='1' and e.cityaudit='1'
	         System.out.println("财务总查询"+sql);
	     	rs = db.queryAll(sql.toString());
		    Query query = new Query();
			list = query.query(rs);
	        }

	        catch (DbException de) {
	            de.printStackTrace();
	        } 

	        finally {
	                try {
	                	if (rs != null) {
	                    rs.close();
	                }
	                } catch (SQLException se) {
	                    se.printStackTrace();
	                }
	            try {
	            	if(db!=null){
	                db.close();
	            	}
	            } catch (DbException de) {
	                de.printStackTrace();
	            }
	        }

	        return list;
	    }

	    
	  //财务下级查询
	    public synchronized ArrayList getCaixiaji( String whereStr, String loginId) {
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer();
	        CTime ct = new CTime();
	        String kjnd = ct.formatShortDate().substring(0, 4);
	        DataBase db = new DataBase();
	        ResultSet rs = null;
	        
	        try {
	            db.connectDb();
/*	         sql.append("select z.id,z.jzname,d.dbid,e.accountmonth,s.dbili,e.networkdf ,e.informatizationdf," +
	         		"e.administrativedf,e.marketdf,e.builddf,e.actualpay,a.ammeterdegreeid " +
	         		" from zhandian z, dianbiao d, sbgl s," +
	         		" ammeterdegrees a, electricfees e where z.id = d.zdid and d.dbid = s.dianbiaoid " +
	         		"and d.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk   "+whereStr+" " +
	         		" and (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");*/
	         sql.append("select z.id,z.jzname,s.xjbili,e.ammeterid_fk,sum(e.networkdf*s.xjbili/100) networkdf,sum(e.informatizationdf*s.xjbili/100) informatizationdf," +
	         		"sum(e.administrativedf*s.xjbili/100) administrativedf,sum(e.marketdf*s.xjbili/100) marketdf,sum(e.builddf*s.xjbili/100) builddf,sum(e.dddf*s.xjbili/100) dddf," +
	         		" sum(e.actualpay)actualpay,(select name from indexs n where n.code=d.dfzflx and n.type='dfzflx' )dfzflx from zhandian z," +
	         		" dianbiao d, sbgl s,caiwuquery_view e where z.id = d.zdid and d.dbid = s.dianbiaoid " +
	         		"and d.dbid = e.ammeterid_fk   "+whereStr+"" +
	         		"and (z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))" +
	         		"group by e.ammeterid_fk,z.jzname,s.qcbcode,s.kjkmcode,s.zymxcode,s.shuoshuzhuanye ,s.xjbili,z.id,d.dfzflx");
	        //and e.manualauditstatus>='1' and e.cityaudit='1'  
	         System.out.println("财务下级查询"+sql);
	     	rs = db.queryAll(sql.toString());
		    Query query = new Query();
			list = query.query(rs);
	        }

	        catch (DbException de) {
	            de.printStackTrace();
	        } 

	        finally {
	            
	                try {
	                	if (rs != null) {
	                    rs.close();
	                }
	                } catch (SQLException se) {
	                    se.printStackTrace();
	                }
	            
	            try {
	            	if(db != null){
	                db.close();
	            	}
	            } catch (DbException de) {
	                de.printStackTrace();
	            }

	        }

	        return list;
	    }
	    
	    
	    //财务：分摊详细信息
	    public synchronized ArrayList getFentan( String whereStr, String loginId,String dfzflx) {
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer();
	        CTime ct = new CTime();
	        String kjnd = ct.formatShortDate().substring(0, 4);
	        DataBase db = new DataBase();
	        ResultSet rs = null;
	        
	        try {
	            db.connectDb();
/*	         sql.append("    select z.jzname,d.dbid,e.accountmonth,(select qcbname from qcbdf qc where s.qcbcode = qc.qcbcode) as qcb," +
	         		"(select qcbname from qcbdf qc where qc.qcbcode = s.kjkmcode) as kjkm," +
	         		"(select name from indexs i where i.code = s.zymxcode and i.type = 'ZYMX') as zymx," +
	         		"(select qcbname from qcbdf qc where qc.qcbcode = s.shuoshuzhuanye) as sszy,s.shuoshuzhuanye,s.xjbili," +
	         		"e.networkdf,e.informatizationdf,e.administrativedf,e.marketdf,e.builddf " +
	         		" from zhandian z,dianbiao d,sbgl s,ammeterdegrees a,electricfees e where z.id = d.zdid" +
	         		" and d.dbid = s.dianbiaoid and d.dbid = a.ammeterid_fk  " +
	         		" and a.ammeterdegreeid = e.ammeterdegreeid_fk  "+whereStr+" " +
	         		" and (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");*/
	         if("月结".equals(dfzflx)||"预支".equals(dfzflx)){
	         sql.append("select z.jzname,a.ammeterid_fk,to_char(e.accountmonth,'yyyy-mm') accountmonth,s.shuoshuzhuanye,sum(e.actualpay)actualpay,sum(e.networkdf )networkdf," +
	         		"sum(e.informatizationdf )informatizationdf,sum(e.administrativedf )administrativedf,sum(e.marketdf) marketdf," +
	         		"sum(e.builddf ) builddf,sum(e.dddf ) dddf,s.dbili,sum(e.networkdf * s.xjbili / 100) e,sum(e.informatizationdf * s.xjbili / 100) a," +
	         		"sum(e.administrativedf * s.xjbili / 100) b,sum(e.marketdf * s.xjbili / 100) c,sum(e.builddf * s.xjbili / 100) d,sum(e.dddf * s.xjbili / 100)f," +
	         		"s.xjbili,(select distinct qcbname from qcbdf qc where s.qcbcode = qc.qcbcode) as qcb," +
	         		"(select distinct qcbname from qcbdf qc where qc.qcbcode = s.kjkmcode) as kjkm,(select name from indexs i " +
	         		"where i.code = s.zymxcode and i.type = 'ZYMX') as zymx,(select distinct qcbname from qcbdf qc where qc.qcbcode = s.shuoshuzhuanye) as sszy," +
	         		"(select name from indexs n where n.code=d.dfzflx and n.type='dfzflx' )dfzflx " +
	         		"from zhandian z, dianbiao d, sbgl s, ammeterdegrees a, dianfeiview e where z.id = d.zdid and d.dbid = s.dianbiaoid " +
	         		"and d.dbid = a.ammeterid_fk  and a.ammeterdegreeid = e.ammeterdegreeid_fk   "+whereStr+"" +
	         		" and (z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))group by s.shuoshuzhuanye," +
	         		"s.qcbcode,s.kjkmcode,s.zymxcode,a.ammeterid_fk,s.xjbili,s.dbili, z.jzname,e.accountmonth,d.dfzflx");
	         	//and e.manualauditstatus>='1' and e.cityaudit='1'
	         	}else if("合同".equals(dfzflx)||"插卡".equals(dfzflx)){
	        	    sql.append("select z.jzname,e.prepayment_ammeterid ammeterid_fk,to_char(e.accountmonth,'yyyy-mm') accountmonth,s.shuoshuzhuanye,sum(e.money)actualpay,sum(e.networkdf )networkdf," +
	    	         		"sum(e.informatizationdf )informatizationdf,sum(e.administrativedf )administrativedf,sum(e.marketdf) marketdf," +
	    	         		"sum(e.builddf ) builddf,sum(e.dddf ) dddf,s.dbili,sum(e.networkdf * s.xjbili / 100) e,sum(e.informatizationdf * s.xjbili / 100) a," +
	    	         		"sum(e.administrativedf * s.xjbili / 100) b,sum(e.marketdf * s.xjbili / 100) c,sum(e.builddf * s.xjbili / 100) d,sum(e.dddf * s.xjbili / 100)f," +
	    	         		"s.xjbili,(select distinct qcbname from qcbdf qc where s.qcbcode = qc.qcbcode) as qcb," +
	    	         		"(select distinct qcbname from qcbdf qc where qc.qcbcode = s.kjkmcode) as kjkm,(select name from indexs i " +
	    	         		"where i.code = s.zymxcode and i.type = 'ZYMX') as zymx,(select distinct qcbname from qcbdf qc where qc.qcbcode = s.shuoshuzhuanye) as sszy," +
	    	         		"(select name from indexs n where n.code=d.dfzflx and n.type='dfzflx' )dfzflx " +
	    	         		"from zhandian z, dianbiao d, sbgl s, yufufeiview e where z.id = d.zdid and d.dbid = s.dianbiaoid " +
	    	         		"  and d.dbid = e.prepayment_ammeterid   "+whereStr+"" +
	    	         		" and (z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))group by s.shuoshuzhuanye," +
	    	         		"s.qcbcode,s.kjkmcode,s.zymxcode,s.xjbili,s.dbili, z.jzname,e.accountmonth,d.dfzflx,e.prepayment_ammeterid");
	        	 //and e.cityaudit='1'
	        	 
	        	 
	         }
	        
	         System.out.println("财务分摊详细信息"+sql);
	     	rs = db.queryAll(sql.toString());
		    Query query = new Query();
			list = query.query(rs);
	        }

	        catch (DbException de) {
	            de.printStackTrace();
	        } 

	        finally {
	            
	                try {
	                	if (rs != null) {
	                    rs.close();
	                }
	                } catch (SQLException se) {
	                    se.printStackTrace();
	                }
	           
	            try {
	            	if(db !=null){
	                db.close();
	            	}
	            } catch (DbException de) {
	                de.printStackTrace();
	            }

	        }

	        return list;
	    }
}
