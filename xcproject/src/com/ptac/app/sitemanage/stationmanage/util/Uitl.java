package com.ptac.app.sitemanage.stationmanage.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class Uitl {
	/**վ�㵼�빤����
	 * @author WnagYiXiao 2014-9-22
	 *
	 */
	public static  class  Util {
		/**��ȡ����Ϊ*�ĵ���agcode(�жϸ�ʡ�Ƿ����)
		 * @author WangYiXiao 2014-9-22
		 * @param str
		 */
		static synchronized String getAreaCode(String name) {
	        String code = "";
	        DataBase db = null;
	        Connection connc = null;
	        Statement st = null;
	        ResultSet rs =null;
	        db = new DataBase();
	        try {
				connc = db.getConnection();
				 st = connc.createStatement();
			        String sql = "SELECT AGCODE FROM D_AREA_GRADE WHERE AGNAME = '" + name.trim() + "'";
			        rs = st.executeQuery(sql);
			        if(rs.next()){
			        	code = rs.getString("AGCODE");
			        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				db.free(rs, st, connc);
			}
	        return code;
		}
		
		/**��ȡ����Ϊ*�ĵ���agcode (�жϸ�ʡ���Ƿ��и��У���������Ƿ��и��أ���������Ƿ��и�С��)
		 * @author WangYiXiao 2014-9-22
		 * @param str
		 */
		static synchronized String getAreaCode(String name,String fagName) {
	        String code = "";
	        DataBase db = null;
	        Connection connc = null;
	        Statement st = null;
	        ResultSet rs =null;
	        db = new DataBase();
	        try {
				connc = db.getConnection();
				 st = connc.createStatement();
			        String sql = "SELECT AGCODE FROM D_AREA_GRADE WHERE AGNAME = '" +
			       		 name.trim() + "' AND FAGCODE IN (SELECT AGCODE FROM D_AREA_GRADE WHERE AGNAME = '" +fagName.trim() + "')";
			        rs = st.executeQuery(sql);
			        if(rs.next()){
			        	code = rs.getString("AGCODE");
			        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				db.free(rs, st, connc);
			}
	        return code;
		}
		
		/**��ȡ����Ϊ*��code (�жϸ�վ�������Ƿ��ڸ�վ��������)
		 * @author WangYiXiao 2014-9-22
		 * @param str
		 */
		static synchronized String getPropertyCode(String name,String fagName) {
	        String code = "";
	        DataBase db = null;
	        Connection connc = null;
	        Statement st = null;
	        ResultSet rs =null;
	        db = new DataBase();
	        try {
				connc = db.getConnection();
				 st = connc.createStatement();
			        String sql = "SELECT T.CODE CODE FROM ZDSX T WHERE T.NAME = '"+name.trim()+"' AND T.FJCODE IN (SELECT IDS.CODE FROM INDEXS IDS WHERE IDS.NAME = '"+fagName.trim()+"')";
			        rs = st.executeQuery(sql);
			        if(rs.next()){
			        	code = rs.getString("CODE");
			        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				db.free(rs, st, connc);
			}
	        return code;
		}
		
		/**�ж��Ƿ��иõ���Ȩ��
		 * @author WangYiXiao 2014-9-22
		 * @param str
		 */
		static synchronized String getLimitCode(String accountname,String xiaoqu) {
	        String code = "";
	        DataBase db = null;
	        Connection connc = null;
	        Statement st = null;
	        ResultSet rs =null;
	        db = new DataBase();
	        try {
				connc = db.getConnection();
				 st = connc.createStatement();
			        String sql = "SELECT AA.AGCODE FROM PER_AREA AA WHERE AA.ACCOUNTNAME ='"+accountname+"' AND AA.AGCODE='"+xiaoqu+"'";
			        rs = st.executeQuery(sql);
			        if(rs.next()){
			        	code = rs.getString("AGCODE");
			        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				db.free(rs, st, connc);
			}
	        return code;
		}
		
		/**�ж��Ƿ��и�����Ȩ��
		 * @author WangYiXiao 2014-9-22
		 * @param str
		 */
		static synchronized String getProperty(String name,String type) {
	        String code = "";
	        DataBase db = null;
	        Connection connc = null;
	        Statement st = null;
	        ResultSet rs =null;
	        db = new DataBase();
	        try {
				connc = db.getConnection();
				 st = connc.createStatement();
			        String sql = "SELECT T.CODE FROM INDEXS T WHERE T.NAME = '"+name+"' AND T.TYPE = '"+type+"'";
			        rs = st.executeQuery(sql);
			        if(rs.next()){
			        	code = rs.getString("CODE");
			        }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				db.free(rs, st, connc);
			}
	        return code;
		}
		
		
	}

}
