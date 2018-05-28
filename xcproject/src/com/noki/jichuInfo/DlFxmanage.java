package com.noki.jichuInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;

import com.noki.biaogan.model.BiaoganBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class DlFxmanage {
	public List<DianLiangBean> searchBiaoganChartByZdid(String zdid, String year) {
		List<DianLiangBean> biaoganList = new ArrayList<DianLiangBean>();
		for (int i = 1; i <= 12; i++) {
			DianLiangBean biaogan = new DianLiangBean();
			biaogan.setBlhdl("0");
			String month = "";
			if (i < 10) {
				month = "0" + i;
			}else{
				month = ""+i;
			}
			biaogan.setThisdatetime(year + "-" + month);
			biaoganList.add(biaogan);
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select a.blhdl,to_char(a.thisdate,'yyyy-mm')as thisdate from ammeterdegrees a where a.AMMETERID_FK='"+zdid+"' and thisdatetime like '" + year + "%'");
		System.out.println(sql);
		Log.info("[±ê¸ËÍ¼±í]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		DianLiangBean _biaogan=null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String bgValue = rs.getString("blhdl");
					String yearmonth = rs.getString("thisdate");
					String _yearmonth=null;
					String _bgValue=null;
					for (int i = 0; i < biaoganList.size(); i++) {
						 _biaogan = biaoganList.get(i);
						 
						 _yearmonth = _biaogan.getThisdatetime();
						 _bgValue = _biaogan.getBlhdl();
						if (_yearmonth.equals(yearmonth)) {
							biaoganList.remove(_biaogan);
							_biaogan.setBlhdl(bgValue);
							biaoganList.add(_biaogan);
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
