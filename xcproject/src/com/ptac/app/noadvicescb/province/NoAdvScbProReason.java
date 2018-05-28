package com.ptac.app.noadvicescb.province;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.noki.database.DataBase;

/**
 * @author lijing
 * @see 省级无建议生产标原因
 */
public class NoAdvScbProReason {
	
	private String city;//城市
	private String reason;//原因说明
	private String num;//站点总数
	private String hj;//合计
	
    public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getHj() {
		return hj;
	}
	public void setHj(String hj) {
		this.hj = hj;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * @see 省级无建议生产标原因(站点启用)
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	public synchronized NoAdvScbProReason getProReason1( String whereStr, String loginId) {
       
		NoAdvScbProReason bean = new NoAdvScbProReason();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
        
		sql.append(" SELECT COUNT(DISTINCT Z.ID) FROM ZHANDIAN Z" 
				 +" WHERE (Z.ZLFH <= 0 OR Z.ZLFH IS NULL OR Z.JLFH <= 0 OR Z.JLFH IS NULL OR" 
				 +" Z.SCB <= 0 OR Z.SCB IS NULL OR Z.EDHDL <= 0 OR Z.EDHDL IS NULL OR"
				 +" Z.QYZT = 0) AND Z.CAIJI = '0' AND Z.XUNI = '0'  AND Z.SHSIGN = '1'"
				 + whereStr
				 +" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
		System.out.println("省级无建议生产标原因(站点启用)："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	String num = rs.getString(1) != null ? rs.getString(1) : "0";//站点总数
            	
            	bean.setNum(num);
            	bean.setReason("不满足：站点启用");
            }
        }catch (Exception de) {
            de.printStackTrace();
        }finally {
        	db.free(rs, ps, conn);
        }
        return bean;
    }
	
	/**
	 * @see 省级无建议生产标原因(倍率为正值)
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	public synchronized NoAdvScbProReason getProReason2( String whereStr, String loginId) {
       
		NoAdvScbProReason bean = new NoAdvScbProReason();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		sql.append(" SELECT COUNT(DISTINCT D.ZDID) FROM DIANBIAO D,ZHANDIAN Z" 
				 +" WHERE Z.ID=D.ZDID AND D.DBYT='dbyt01' AND D.BEILV<=0 " + whereStr
				 +" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
		System.out.println("省级无建议生产标原因(倍率为正值)："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	String num = rs.getString(1) != null ? rs.getString(1) : "0";//站点总数
            	
            	bean.setNum(num);
            	bean.setReason("不满足：倍率为正值");
            }
        }catch (Exception de) {
            de.printStackTrace();
        }finally {
        	db.free(rs, ps, conn);
        }
        return bean;
    }
	
	/**
	 * @see 省级无建议生产标原因(本次读数、上次读数必须大于等于0)
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	public synchronized NoAdvScbProReason getProReason3( String whereStr, String loginId) {
       
		NoAdvScbProReason bean = new NoAdvScbProReason();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
        
		sql.append(" SELECT COUNT(DISTINCT Z.ID) FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES AM, ELECTRICFEES EF" 
				 +" WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK  AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK" 
				 +" AND (AM.THISDEGREE <= 0 OR AM.THISDEGREE < AM.LASTDEGREE OR (TO_NUMBER((AM.THISDATETIME) - (AM.LASTDATETIME)) <= 0))"
				 + whereStr
				 +" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
		System.out.println("省级无建议生产标原因(本次读数、上次读数必须大于等于0)："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	String num = rs.getString(1) != null ? rs.getString(1) : "0";//站点总数
            	
            	bean.setNum(num);
            	bean.setReason("不满足：本次读数、上次读数必须大于等于0");
            }
        }catch (Exception de) {
            de.printStackTrace();
        }finally {
        	db.free(rs, ps, conn);
        }
        return bean;
    }
	
	/**
	 * @see 省级无建议生产标原因详情(查询城市)
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	public synchronized ArrayList<NoAdvScbProReason> getProCity1( String whereStr, String loginId) {
	       
    	ArrayList<NoAdvScbProReason> list = new ArrayList<NoAdvScbProReason>();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
        
		sql.append(" SELECT rndiqu(Z.SHI),COUNT(DISTINCT Z.ID) FROM ZHANDIAN Z" 
				 +" WHERE (Z.ZLFH <= 0 OR Z.ZLFH IS NULL OR Z.JLFH <= 0 OR Z.JLFH IS NULL OR" 
				 +" Z.SCB <= 0 OR Z.SCB IS NULL OR Z.EDHDL <= 0 OR Z.EDHDL IS NULL OR"
				 +" Z.QYZT = 0) AND Z.CAIJI = '0' AND Z.XUNI = '0'  AND Z.SHSIGN = '1'"
				 + whereStr
				 +" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by SHI");
		
		System.out.println("省级无建议生产标原因详情(站点启用)："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	NoAdvScbProReason bean = new NoAdvScbProReason();
            	String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
            	String num = rs.getString(2) != null ? rs.getString(2) : "0";//站点总数
            	
            	bean.setCity(city);
            	bean.setNum(num);
				list.add(bean);
            }
        }catch (Exception de) {
            de.printStackTrace();
        }finally {
        	db.free(rs, ps, conn);
        }
        return list;
    }
	
	/**
	 * @see 省级无建议生产标原因详情(查询城市)
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	public synchronized ArrayList<NoAdvScbProReason> getProCity2( String whereStr, String loginId) {
	       
    	ArrayList<NoAdvScbProReason> list = new ArrayList<NoAdvScbProReason>();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
        
		sql.append(" SELECT rndiqu(Z.SHI),COUNT(DISTINCT D.ZDID) FROM DIANBIAO D,ZHANDIAN Z" 
				 +" WHERE Z.ID=D.ZDID AND D.DBYT='dbyt01' AND D.BEILV<=0 " + whereStr
				 +" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by SHI");

		System.out.println("省级无建议生产标原因详情(倍率为正值)："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	NoAdvScbProReason bean = new NoAdvScbProReason();
            	String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
            	String num = rs.getString(2) != null ? rs.getString(2) : "0";//站点总数
            	
            	bean.setCity(city);
            	bean.setNum(num);
				list.add(bean);
            }
        }catch (Exception de) {
            de.printStackTrace();
        }finally {
        	db.free(rs, ps, conn);
        }
        return list;
    }
	
	/**
	 * @see 省级无建议生产标原因详情(查询城市)
	 * @param whereStr
	 * @param loginId
	 * @return list
	 */
	public synchronized ArrayList<NoAdvScbProReason> getProCity3( String whereStr, String loginId) {
	       
    	ArrayList<NoAdvScbProReason> list = new ArrayList<NoAdvScbProReason>();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
        
		sql.append(" SELECT rndiqu(Z.SHI),COUNT(DISTINCT Z.ID) FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES AM, ELECTRICFEES EF" 
				 +" WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK  AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK" 
				 +" AND (AM.THISDEGREE <= 0 OR AM.THISDEGREE < AM.LASTDEGREE OR (TO_NUMBER((AM.THISDATETIME) - (AM.LASTDATETIME)) <= 0))"
				 + whereStr
				 +" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by SHI");

		System.out.println("省级无建议生产标原因详情(本次读数、上次读数必须大于等于0)："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	NoAdvScbProReason bean = new NoAdvScbProReason();
            	String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
            	String num = rs.getString(2) != null ? rs.getString(2) : "0";//站点总数
            	
            	bean.setCity(city);
            	bean.setNum(num);
				list.add(bean);
            }
        }catch (Exception de) {
            de.printStackTrace();
        }finally {
        	db.free(rs, ps, conn);
        }
        return list;
    }
}
