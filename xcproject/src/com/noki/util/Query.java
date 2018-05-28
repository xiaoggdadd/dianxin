package com.noki.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;

public class Query {
	/**
	 * @param rs
	 * @return
	 */
	public ArrayList query(ResultSet rs) {

		ResultSet result = rs;
		try {

			// result=stmt.executeQuery(sql.toString());
			ResultSetMetaData rsmd = result.getMetaData();

			ArrayList alist = new ArrayList();

			// ��ӵ�0������Ϊ�ֶε�����
			alist.add(new Integer(rsmd.getColumnCount()));

			// ���ÿ���ֶ�����

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				alist.add(rsmd.getColumnName(i).toUpperCase());
			}

			// ��Ӿ�������
			while (result.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {

					alist.add(result.getString(i) != null ? result.getString(i)
							: "");

				}
			}
			/*
			 * for(int j=0;j<alist.size();j++) {
			 * //System.out.println(alist.get(j)); }
			 */

			return alist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
					try {
						if (result != null) {
							result.close();
						}
						if(rs != null){
						rs.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				

		}
	}
	public ArrayList query2(ResultSet rs) {

		ResultSet result = rs;
		try {

			// result=stmt.executeQuery(sql.toString());
			ResultSetMetaData rsmd = result.getMetaData();

			ArrayList alist = new ArrayList();

			// ��Ӿ�������
			while (result.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {

					alist.add(result.getString(i) != null ? result.getString(i)
							: "");

				}
			}
			/*
			 * for(int j=0;j<alist.size();j++) {
			 * //System.out.println(alist.get(j)); }
			 */

			return alist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
					try {
						if (result != null) {
							result.close();
						}
						if(rs != null){
						rs.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				

		}
	}
}
