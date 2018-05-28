package com.noki.dianfei;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.jfree.util.Log;


import com.noki.database.DataBase;
import com.noki.database.DbException;


public class Dfmanager {
	public List<DianfeiBean> searchDianfeiChartByZdid(String zdid, String year) {
		List<DianfeiBean> biaoganList = new ArrayList<DianfeiBean>();
		for (int i = 1; i <= 12; i++) {
			DianfeiBean biaogan = new DianfeiBean();
			biaogan.setBeilv("0");
			String month = "";
			if (i < 10) {
				month = "0" + i;
			}else{
				month=""+i;
			}
			biaogan.setEntrytime(year + "-" + month);
			biaoganList.add(biaogan);
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select cast(e.actualpay as decimal (38,2))as df,to_char(e.accountmonth,'yyyy-mm')as accountmonth from electricfees e,ammeterdegrees d where e.AMMETERDEGREEID_FK=d.ammeterdegreeid and d.ammeterid_fk='"+zdid+"' and e.entrytime like '" +year+ "%'");
	    System.out.println(sql.toString());
		Log.info("[µç·ÑÍ¼±í]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		DianfeiBean _dianfei=null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String bgValue = rs.getString("df");
					String yearmonth = rs.getString("accountmonth");
					for (int i = 0; i < biaoganList.size(); i++) {
						_dianfei = biaoganList.get(i);
						String _yearmonth = _dianfei.getEntrytime();
						String _bgValue = _dianfei.getbeilv();
						if (_yearmonth.equals(yearmonth)) {
							biaoganList.remove(_dianfei);
							System.out.println(bgValue);
							_dianfei.setBeilv(bgValue);
							biaoganList.add(_dianfei);
						}
					}
					
				}
			} catch (Exception de) {
				de.printStackTrace();
			} finally {
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

		return biaoganList;
	}
}
