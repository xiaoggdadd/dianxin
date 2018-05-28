package com.noki.hetongguanli.hetong;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class hetongBean {
	public synchronized ArrayList getAgcode(String pcode, String agcode, String account) {
		if (null == pcode || "".equals(pcode)) {
			pcode = "0";
		}
		int sublen = pcode.length() + 2;

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select agcode,agname from d_area_grade where fagcode='"
					+ pcode + "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			// StringBuffer s37 = new StringBuffer();
			// s37.append("select id from per_area t where t.agcode='"+pcode+"' and accountname='"
			// +account + "'");
			// rs = db.queryAll(s37.toString());

			StringBuffer s2 = new StringBuffer();
			s2.append("select distinct(substr(agcode,0," + sublen
					+ ")) from per_area where accountname='" + account + "'");
			sql.append(" and agcode in(" + s2.toString() + ") ORDER BY AGCODE");

			System.out.println("Áª¶¯" + sql);
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
