package com.ptac.app.collectanalysis.onesitedb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.collectanalysis.onesitedb.bean.OneSiteDb;

public class OneSiteDbDaoImpl implements OneSiteDbDao {

	@Override
	public List<OneSiteDb> getOneSiteDb(String shi, String zdqyzt,
			String dbqyzt, String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "select rndiqu(a.agcode) city,decode(b.zds,null,0,b.zds) zds,decode(b.yzyb,null,0,b.yzyb) yzyb,decode(b.yzdb,null,0,b.yzdb) yzdb,decode(b.hsyzdb,null,0,b.hsyzdb) hsyzdb,"
      +" decode(b.yzlb,null,0,b.yzlb) yzlb from (select dag.agcode from d_area_grade dag where "+("-1".equals(shi)?"dag.fagcode='137'":("dag.agcode='"+shi+"'"))+")a,"
 	+"(select zb.shi,zb.zds,zb.yzyb,zb.yzdb,zb.hsyzdb,zb.yzlb"
  	+" from (select sj.shi shi, count(sj.zdid) zds,count(decode(sj.ca, 1, sj.zdid)) yzyb,count(case when sj.ca > 1 then sj.zdid end) yzdb, count(case when sj.cc > 1 then sj.zdid end) hsyzdb, count(decode(sj.ca, 0, sj.zdid)) yzlb"
     +"	from (select zd.shi, zd.id zdid, count("+("-1".equals(dbqyzt)?"db.dbid":("decode(db.dbqyzt,'"+dbqyzt+"',db.dbid )"))+") ca,"
     +" count(case when "+("-1".equals(dbqyzt)?"":("db.dbqyzt = '"+dbqyzt+"' and "))+"  zd.property != 'zdsx04'"
       +" and zd.jzname not like '%ªÿ ’%' then db.dbid end) cc from zhandian zd, dianbiao db"
                +" where zd.id = db.zdid and zd.xuni = '0' and zd.caiji = '0' and zd.shsign = '1'"
                  + ("-1".equals(shi)?"":("and zd.shi='"+shi+"'"))
                  +("-1".equals(zdqyzt)?"":("and zd.qyzt='"+zdqyzt+"'"))
                   +"and db.dbyt = 'dbyt01' "
                   +"and zd.xiaoqu in (select agcode from per_area where accountid ='"+loginId+"') "
                +" group by zd.shi, zd.id) sj group by sj.shi) zb) b where a.agcode = b.shi(+) order by a.agcode";
		System.out.println("sql:"+sql);
		Statement ps = null;
		ResultSet rs = null;
		List<OneSiteDb> list = null; 
		try {
			list = new ArrayList<OneSiteDb>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while(rs.next()){
				OneSiteDb bean = new OneSiteDb();
				bean.setCity(rs.getString("city"));
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

}
