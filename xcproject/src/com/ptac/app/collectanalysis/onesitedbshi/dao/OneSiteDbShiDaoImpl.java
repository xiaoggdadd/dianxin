package com.ptac.app.collectanalysis.onesitedbshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShi;
import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShiDetails;

public class OneSiteDbShiDaoImpl implements OneSiteDbShiDao {

	@Override
	public List<OneSiteDbShi> getOneSiteDb(String shi, String zdqyzt,
			String dbqyzt, String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "select rndiqu(a.agcode) city,a.agcode xiancode,decode(b.zds,null,0,b.zds) zds,decode(b.yzyb,null,0,b.yzyb) yzyb,decode(b.yzdb,null,0,b.yzdb) yzdb,decode(b.hsyzdb,null,0,b.hsyzdb) hsyzdb,"
      +" decode(b.yzlb,null,0,b.yzlb) yzlb from (select dag.agcode from d_area_grade dag where dag.fagcode='"+shi+"')a,"
 	+"(select zb.xian,zb.zds,zb.yzyb,zb.yzdb,zb.hsyzdb,zb.yzlb"
  	+" from (select sj.xian xian, count(sj.zdid) zds,count(decode(sj.ca, 1, sj.zdid)) yzyb,count(case when sj.ca > 1 then sj.zdid end) yzdb, count(case when sj.cc > 1 then sj.zdid end) hsyzdb, count(decode(sj.ca, 0, sj.zdid)) yzlb"
     +"	from (select zd.xian, zd.id zdid, count("+("-1".equals(dbqyzt)?"db.dbid":("decode(db.dbqyzt,'"+dbqyzt+"',db.dbid )"))+") ca,"
     +" count(case when "+("-1".equals(dbqyzt)?"":("db.dbqyzt = '"+dbqyzt+"' and "))+"  zd.property != 'zdsx04'"
       +" and zd.jzname not like '%回收%' then db.dbid end) cc from zhandian zd, dianbiao db"
                +" where zd.id = db.zdid and zd.xuni = '0' and zd.caiji = '0' and zd.shsign = '1' and zd.shi='"+shi+"'"
                  +("-1".equals(zdqyzt)?"":("and zd.qyzt='"+zdqyzt+"'"))
                   +"and db.dbyt = 'dbyt01' "
                   +"and zd.xiaoqu in (select agcode from per_area where accountid ='"+loginId+"') "
                +" group by zd.xian, zd.id) sj group by sj.xian) zb) b where a.agcode = b.xian(+) order by a.agcode";
		System.out.println("sql:"+sql);
		Statement ps = null;
		ResultSet rs = null;
		List<OneSiteDbShi> list = null; 
		try {
			list = new ArrayList<OneSiteDbShi>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while(rs.next()){
				OneSiteDbShi bean = new OneSiteDbShi();
				bean.setCity(rs.getString("city"));
				bean.setXiancode(rs.getString("xiancode"));
				bean.setZds(rs.getString("zds"));
				bean.setYzyb(rs.getString("yzyb"));
				bean.setYzdb(rs.getString("yzdb"));
				bean.setFhsyzdb(rs.getString("hsyzdb"));
				bean.setYzlb(rs.getString("yzlb"));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<OneSiteDbShiDetails> getOneSiteDbDetails(String xian,String fwzbzw,String zdqyzt,String dbqyzt,
			String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "SELECT RNDIQU(Z.SHI) SHI,RNDIQU(Z.XIAN) XIAN,RNDIQU(Z.XIAOQU) XIAOQU,Z.JZNAME ZDNAME,RTNAME(Z.PROPERTY) PROPERTY,Z.ID ZDID "
			+"FROM ZHANDIAN Z WHERE Z.ID IN (SELECT CASE WHEN SJ.C > 1 THEN SJ.ZDID END FROM (SELECT ZD.XIAN, ZD.ID ZDID,"
            +"COUNT("+("1".equals(fwzbzw)?(("-1".equals(dbqyzt)?"db.dbid":("decode(db.dbqyzt,'"+dbqyzt+"',db.dbid )")))
            		:("CASE WHEN "+("-1".equals(dbqyzt)?"":("DB.DBQYZT = '"+dbqyzt+"' AND "))+" ZD.PROPERTY != 'zdsx04' AND ZD.JZNAME NOT LIKE '%回收%' THEN DB.DBID END"))+") C "
            +"FROM ZHANDIAN ZD, DIANBIAO DB WHERE ZD.ID = DB.ZDID AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND ZD.SHSIGN = '1' AND ZD.XIAN = '"+xian+"' "
            +("-1".equals(zdqyzt)?"":("AND ZD.QYZT='"+zdqyzt+"'"))
            +" AND DB.DBYT = 'dbyt01' AND ZD.XIAOQU IN (SELECT AGCODE FROM PER_AREA WHERE ACCOUNTID = '"+loginId+"') GROUP BY ZD.XIAN, ZD.ID) SJ)";
		Statement ps = null;
		ResultSet rs = null;
		List<OneSiteDbShiDetails> list = null; 
		try {
			list = new ArrayList<OneSiteDbShiDetails>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("sql:"+sql);
			rs = ps.executeQuery(sql);
			while(rs.next()){
				OneSiteDbShiDetails bean = new OneSiteDbShiDetails();
				bean.setShi(rs.getString("SHI"));
				bean.setXian(rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU"));
				bean.setZdname(rs.getString("ZDNAME"));
				bean.setProperty(rs.getString("PROPERTY"));
				bean.setZdid(rs.getString("ZDID"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

}
