package com.noki.tongjichaxu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class FeixiDao {
public List<FeixiBean> getFX(String whereStr,String Str){
		
		List list = null;
		String sql = "select zd.id, zd.jzname,d.agname,to_char(ef.paydatetime,'yyyy-mm-dd'),ef.autoauditdescription,ad.autoauditdescription,to_char(ad.lastdatetime,'yyyy-mm-dd'), to_char(ad.thisdatetime,'yyyy-mm-dd'),ad.lastdegree,ad.thisdegree " +
				"from zhandian zd, dianbiao db,dianduview ad,dianfeiview ef,d_area_grade d " +
				"where zd.id = db.zdid and db.dbid = ad.ammeterid_fk and ad.ammeterdegreeid = ef.ammeterdegreeid_fk and d.agcode = zd.xian and (ef.manualauditstatus = '0' or ef.manualauditstatus is null) and zd.qyzt = '1'"+whereStr+" and (ef.autoauditdescription = '0'"+Str+") and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '263'))) " +
						"group by zd.id,zd.jzname,d.agname,ef.paydatetime,ef.autoauditdescription,ad.autoauditdescription,ad.thisdegree,ad.lastdegree,ad.thisdatetime,ad.lastdatetime";
		System.out.println("×Ô¶¯ÉóºË:"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
			FeixiBean fx = new FeixiBean();
			fx.setZdid(rs.getString(1));
			fx.setJzname(rs.getString(2));
			fx.setQuxian(rs.getString(3));
			fx.setJftime(rs.getString(4));
			fx.setWenti(rs.getString(5)!=null?rs.getString(5):"");
			fx.setWen(rs.getString(6)!=null?rs.getString(6):"");
			fx.setBybegin(rs.getString(7));
			fx.setByend(rs.getString(8));
			fx.setDBbegin(rs.getString(9));
			fx.setDBend(rs.getString(10));
			list.add(fx);
			
			}
		return 	list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		return null;
		}
}
