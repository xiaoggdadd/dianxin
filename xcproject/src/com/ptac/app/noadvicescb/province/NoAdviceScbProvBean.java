package com.ptac.app.noadvicescb.province;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.noki.database.DataBase;

/**
 * @author lijing
 * @see 省级无建议生产标
 */
public class NoAdviceScbProvBean {
	
	private String city;//城市
	private String num;//无建议生产标个数
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

	public synchronized ArrayList<NoAdviceScbProvBean> getProvince( String whereStr, String loginId) {
       
    	ArrayList<NoAdviceScbProvBean> list = new ArrayList<NoAdviceScbProvBean>();
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		sql.append(" select rndiqu(hh.shi), count(hh.zdid) from ( SELECT Z.SHI shi,Z.ID zdid"
				 +" FROM ZHANDIAN Z WHERE (Z.ZLFH <= 0 OR Z.ZLFH IS NULL OR Z.JLFH <= 0 OR"
				 +" Z.JLFH IS NULL OR Z.SCB <= 0 OR Z.SCB IS NULL OR Z.EDHDL <= 0 OR"
				 +" Z.EDHDL IS NULL OR Z.QYZT = 0)  AND Z.CAIJI = '0' AND Z.XUNI = '0' AND Z.SHSIGN = '1'"+ whereStr
				 +" union (select z.shi, z.id from dianbiao d, zhandian z where z.id = d.zdid and d.dbyt = 'dbyt01'" 
				 +" and d.beilv <= 0"+ whereStr +" group by z.id, z.shi) union (select z.shi, z.id from zhandian z,dianbiao d," 
				 +" ammeterdegrees am, electricfees ef  where z.id = d.zdid and d.dbid = am.ammeterid_fk"
				 +" and am.ammeterdegreeid = ef.ammeterdegreeid_fk and (am.thisdegree <= 0 or am.thisdegree < am.lastdegree or"
				 +" (to_number((am.thisdatetime) - (am.lastdatetime)) <= 0))"+ whereStr +" group by z.id, z.shi)) hh" 
				 +" group by hh.shi order by hh.shi ");
//				 +" AND (z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
		System.out.println("省级无建议生产标："+sql);
		
        try {
            db.connectDb();
            conn = db.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
  
            while(rs.next()){
            	
            	NoAdviceScbProvBean bean = new NoAdviceScbProvBean();
            	String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
            	String num = rs.getString(2) != null ? rs.getString(2) : "0";//无建议生产标个数
            	
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
