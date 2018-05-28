package com.noki.chaobiaorenyuan.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.noki.biaogan.model.ZhandianBean;
import com.noki.chaobiaorenyuan.bean.DianBiao_new;
import com.noki.chaobiaorenyuan.bean.TBL_APP_USER_BD;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class GuanLiQuYuDao {

	public ArrayList<ZhandianBean> SelectJZ(String shi, String xian,
			String xiaoqu, String keyword) {
		System.out.println("shi:" + shi);
		System.out.println("xian:" + xian);
		System.out.println("xiaoqu:" + xiaoqu);
		System.out.println("keyword:" + keyword);
		ArrayList<ZhandianBean> al = null;
		String sql = "SELECT ID,JZNAME FROM ZHANDIAN WHERE 1=1";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			sql += " and SHI = '" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			sql += " and XIAN = '" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			sql += " and XIAOQU = '" + xiaoqu + "'";
		}
		if (keyword != null && keyword.trim() != "") {
			sql += " and JZNAME like '%" + keyword + "%'";
		}
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			al = new ArrayList<ZhandianBean>();
			while (rs.next()) {
				ZhandianBean zhandian = new ZhandianBean();
				zhandian.setID(rs.getString("ID"));
				zhandian.setJZNAME(rs.getString("JZNAME"));
				al.add(zhandian);
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
		return al;
	}

	////��ȡ��ѯ�ı��ж����У����Է�Ϊ��ҳ
	public int COUNT(String shi, String xian,String xiaoqu,String zhandian, String keyword,String roleId,String loginId) {

		int a = 0; 		//���干������
		int b = 10;		//����ÿҳ����
		int c = 0;		//���干�м�ҳ
		
		ArrayList<DianBiao_new> al = null;
		String sql = "SELECT A.JZNAME, A.SHI,A.XIAN, A.XIAOQU, B.DBNAME, B.ZDID,B.ID FROM ZHANDIAN A INNER JOIN  DIANBIAO B ON A.ID = B.ZDID WHERE 1=1";
				if (shi != null && !shi.equals("")) {
					if(shi.equals("0")){
						sql= sql +  "AND SHI IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 2 and ACCOUNTID = "+loginId+")";
					}else{
						sql= sql +  "AND SHI IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 2 and ACCOUNTID = "+loginId+" and a.agcode = '"+shi+"')";
					}
				}
				if (xian != null && !xian.equals("")) {
					if(xian.equals("0")){
						sql= sql + "AND XIAN IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 3  and ACCOUNTID = "+loginId+")";
					}else{
						sql= sql + "AND XIAN IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 3  and ACCOUNTID = "+loginId+" and a.agcode = '"+xian+"')";
					}
				}
				if (xiaoqu != null && !xiaoqu.equals("")) {
					if(xiaoqu.equals("0")){
						sql= sql + "AND XIAOQU IN (SELECT a.agcode FROM per_area a  inner join d_area_grade b on a.agcode = b.agcode WHERE 1 = 1 and b.agrade = 4 and ACCOUNTID = "+loginId+")";
					}else{
						sql= sql + "AND XIAOQU IN (SELECT a.agcode FROM per_area a  inner join d_area_grade b on a.agcode = b.agcode WHERE 1 = 1 and b.agrade = 4 and ACCOUNTID = "+loginId+" and a.agcode = '"+xiaoqu+"')";
					}
				}
				if(zhandian != null && !zhandian.equals("")&& !zhandian.equals("0")){
					sql +=" and A.JZNAME like '%"+zhandian+"%'";
				}
				if (keyword != null && keyword.trim() != "") {
					sql += " and B.DBNAME like '%" + keyword + "%'";
				}
		String SqlString  = "SELECT COUNT(0) FROM(SELECT  C.SHI, C.XIAN, C.XIAOQU,C.JZNAME, C.ID, C.ZDID, C.DBNAME, D.userid,D.BDTIME,N.ACCOUNTNAME FROM ("+sql+")C LEFT JOIN TBL_APP_USER_BD D ON D.DBID = C.ID LEFT JOIN ACCOUNT N ON D.USERID = N.ACCOUNTID)";
		if(roleId.equals("445")){
			SqlString = "SELECT COUNT(0) FROM("+SqlString + " WHERE D.USERID = "+loginId+")";
		}
		System.out.println(SqlString);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(SqlString);
			al = new ArrayList<DianBiao_new>();
			if(rs.next()){
				a = rs.getInt(1);
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
		//�����Ƿ�����
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//���ع��м�ҳ
		return c; 
	}
	
	// �����û�����Ӧ�ĵ��ID�����е������
	public ArrayList<DianBiao_new> SelectZD(int curpage, String shi, String xian,String xiaoqu,String zhandian, String keyword,String roleId,String loginId) {
		//����Ӧ��ת������
		int x = curpage * 10;		
		int y = curpage * 10 - 9;
		System.out.println("shi:" + shi);
		System.out.println("xian:" + xian);
		System.out.println("xiaoqu:" + xiaoqu);
		System.out.println("keyword:" + keyword);
		ArrayList<DianBiao_new> al = null;
		String sql = "SELECT A.JZNAME, A.SHI,A.XIAN, A.XIAOQU, B.DBNAME, B.ZDID,B.ID FROM ZHANDIAN A INNER JOIN  DIANBIAO B ON A.ID = B.ZDID WHERE 1=1";
				if (shi != null && !shi.equals("")) {
					if(shi.equals("0")){
						sql= sql +  "AND SHI IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 2 and ACCOUNTID = "+loginId+")";
					}else{
						sql= sql +  "AND SHI IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 2 and ACCOUNTID = "+loginId+" and a.agcode = '"+shi+"')";
					}
				}
				if (xian != null && !xian.equals("")) {
					if(xian.equals("0")){
						sql= sql + "AND XIAN IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 3  and ACCOUNTID = "+loginId+")";
					}else{
						sql= sql + "AND XIAN IN (SELECT a.agcode FROM per_area a inner join d_area_grade b on a.agcode = b.agcode WHERE b.agrade = 3  and ACCOUNTID = "+loginId+" and a.agcode = '"+xian+"')";
					}
				}
				if (xiaoqu != null && !xiaoqu.equals("")) {
					if(xiaoqu.equals("0")){
						sql= sql + "AND XIAOQU IN (SELECT a.agcode FROM per_area a  inner join d_area_grade b on a.agcode = b.agcode WHERE 1 = 1 and b.agrade = 4 and ACCOUNTID = "+loginId+")";
					}else{
						sql= sql + "AND XIAOQU IN (SELECT a.agcode FROM per_area a  inner join d_area_grade b on a.agcode = b.agcode WHERE 1 = 1 and b.agrade = 4 and ACCOUNTID = "+loginId+" and a.agcode = '"+xiaoqu+"')";
					}
				}
				if(zhandian != null && !zhandian.equals("")&& !zhandian.equals("0")){
					sql +=" and A.JZNAME like '%"+zhandian+"%'";
				}
				if (keyword != null && keyword.trim() != "") {
					sql += " and B.DBNAME like '%" + keyword + "%'";
				}
		String SqlString  = "SELECT * FROM(SELECT ROWNUM R, C.SHI, C.XIAN, C.XIAOQU,C.JZNAME, C.ID, C.ZDID, C.DBNAME, D.userid,D.BDTIME,N.ACCOUNTNAME FROM ("+sql+")C LEFT JOIN TBL_APP_USER_BD D ON D.DBID = C.ID LEFT JOIN ACCOUNT N ON D.USERID = N.ACCOUNTID WHERE 1=1";
		if(roleId.equals("445")){
			SqlString = SqlString + " AND D.USERID = "+loginId+"";
		}
		SqlString = SqlString + "AND ROWNUM <= "+x+")WHERE R >= "+y+"";
		
		System.out.println(SqlString);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(SqlString);
			al = new ArrayList<DianBiao_new>();
			while (rs.next()) {
				DianBiao_new dianbiao = new DianBiao_new();
				dianbiao.setZdid(rs.getInt("ZDID")); 		// վ��ID
				dianbiao.setZdname(rs.getString("JZNAME")); // վ������
				/**
				 * ��ӵ��ʱĬ�ϵ���DBID�ֶκ�ZDID��ͬ��
				 * ���°�һ����ͬʱ�󶨸�վ������б�;
				 * �������ݿ���ֶ���Ȩ���ģ�����ʹ�õ����ID�ֶΡ�
				 */
				dianbiao.setDbid(rs.getInt("ID")); 			// ���ID
				dianbiao.setDbname(rs.getString("DBNAME"));	// �������
				dianbiao.setUserid(rs.getString("USERID")); // ������ID
				dianbiao.setAccountname(rs.getString("ACCOUNTNAME"));//����������
				dianbiao.setBdtime(rs.getString("BDTIME"));//��ʱ��
				al.add(dianbiao);
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
		return al;
	}

	// ִ����Ӱ󶨷���
	public int addBangDing(String uuid, String id) {
		//��ȡ��Ҫ����󶨱���ֶ�
		ArrayList<TBL_APP_USER_BD> bdal = new ArrayList<TBL_APP_USER_BD>();
		bdal = selectBD(id);
		String zdid,zdbm,dbbm;
		zdid = bdal.get(0).getZdid();
		zdbm = bdal.get(0).getZdbm();
		dbbm = bdal.get(0).getDbbm();
		
		//��ȡ��ǰ����Ϊ��ʱ��
        Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dangqianriqi = sdf.format(d);  
        System.out.println( dangqianriqi); 
       
		String sql = "insert into TBL_APP_USER_BD(USERID,ZDID,ZDBM,DBID,DBBM,BDTIME)values('" + uuid + "','" +zdid + "','"+zdbm+"','"+id+"','"+dbbm+"','"+dangqianriqi+"')";
		
		System.out.println("sql-----:"+sql.toString());
		
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);
			System.out.println(sql);
		} catch (Exception de) {
			de.printStackTrace();
		} finally {

			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	// ִ�н���󶨷���
	public int deleteBangDing(String uuid, String id) {

		String sql = "DELETE FROM TBL_APP_USER_BD WHERE DBID = "+id+"";
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);

		} catch (Exception de) {
			de.printStackTrace();
		} finally {

			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	//���ݵ�����Ʋ�ѯ�󶨱�Ӧ��ӵ�����
	public ArrayList<TBL_APP_USER_BD> selectBD(String id) {
		ArrayList<TBL_APP_USER_BD> al = null;
		String sql = "select a.zdid,a.dbbm,b.id,b.jzcode from dianbiao a left join zhandian b on a.zdid = b.id where a.id = "+id+"";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			al = new ArrayList<TBL_APP_USER_BD>();
			while (rs.next()) {
				TBL_APP_USER_BD BD = new TBL_APP_USER_BD();
				/**
				 * ���ڵ����� DBID��ZDID�ֶ���ͬ���޷�ȷ����Ҫ�󶨵������Ӧ��ID���ʴ˴�DDIDȡ��ֵΪ���������ID
				 */
				BD.setZdid(rs.getString("ZDID"));	//վ��ID
				BD.setZdbm(rs.getString("JZCODE"));	//վ�����
				//BD.setDbid(rs.getString("ID"));	//���ID
				BD.setDbbm(rs.getString("DBBM"));	//������
				al.add(BD);
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
		return al;
	}
}