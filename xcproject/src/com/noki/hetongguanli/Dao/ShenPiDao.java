package com.noki.hetongguanli.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.hetongguanli.javabean.ShenPiClass;
import com.noki.page.NPageBean;

public class ShenPiDao {
	/**
	 * ��ѯ��ͬ��˱���ַ���
	 * @param city����ѯ��
	 * @return list(���ز�ѯ���ݼ���)
	 *  
	 */
	//����TBL_HETONG_SHENPI��ȫ�����ݹ��ƶ���ҳ��
	//û�й̶�����������ģ����ѯ����������ҳ���ҳ
	public int SelectCount(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		int a = 0; 		//��ʼ����������
		int b = 5;		//��ʼ��ÿҳ����
		int c = 0;		//��ʼ�����м�ҳ
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
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
	//����TBL_HETONG_SHENPI��ȫ�����ݹ��ƶ���ҳ��
	//�̶�����Ϊ����ˣ�����ģ����ѯ�����ڸ澯ҳ���ҳ
	public int SelectCountZhuangTai(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		int a = 0; 		//��ʼ����������
		int b = 5;		//��ʼ��ÿҳ����
		int c = 0;		//��ʼ�����м�ҳ
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
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
	
	//����TBL_HETONG_SHENPI��ȫ�����ݸ��ݼ׷����ҷ����Լ���ͬ���ƣ���ͬ״̬���з�����ģ����ѯ��
	//û�й̶��������˷���������ˣ�
	public ArrayList<ShenPiClass> SelectHeTong(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		System.out.println(curpage);
		//����Ӧ��ת������
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			System.out.println(sql.toString());
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//����TBL_HETONG_SHENPI��ȫ�����ݸ��ݼ׷����ҷ����Լ���ͬ���ƣ���ͬ״̬���з�����ģ����ѯ��
	//����Ϊ�Ѿ���ˣ��˷������ڸ澯��
	public ArrayList<ShenPiClass> SelectHeTongGaoJing(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		System.out.println(curpage);
		//����Ӧ��ת������
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			System.out.println(sql.toString());
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	/**
	 * ���淽���ں�ͬ�����澯ҳ����δʹ��
	 * ��ɾ����ͬά������ҳ��ʹ�����淽��
	 */
	//����TBL_HETONG_SHENPI��ȫ������
	public ArrayList<ShenPiClass> Select() {
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//���ݺ�ͬ�׷�����ģ����ѯ
	public ArrayList<ShenPiClass> SelectPartya(String partya) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where PARTYA like '%"+partya+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//���ݺ�ͬ�ҷ�����ģ����ѯ
	public ArrayList<ShenPiClass> SelectPartyb(String partyb) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where PARTYB like '%"+partyb+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//���ݺ�ͬ���ƽ���ģ����ѯ
	public ArrayList<ShenPiClass> SelectContractname(String contractname) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where CONTRACTNAME like '%"+contractname+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//���ݹؼ��ֽ���ģ����ѯ
	public ArrayList<ShenPiClass> SelectKeyword(String keyword) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where PARTYA like '%"+keyword+"%' or PARTYB like '%"+keyword+"%' or contractname like '%"+keyword+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//���ݱ�ID��ѯ��������
	public ArrayList<ShenPiClass> SelectID(String ID) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where ID  = "+ID+"";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//�޸�����״̬
	public int update(String id,String zt) {
		String sql = "UPDATE TBL_HETONG_SHENPI SET ZHUANGTAI = "+zt+"  WHERE ID = "+id+"";	
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);
				if(rs>0){
					System.out.println(rs);
				}
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
	//ɾ��������ĸ�ID�ĺ�ͬ��
	public int delete(String id) {
		String sql = "DELETE FROM TBL_HETONG_SHENPI WHERE ID = "+id+"";		
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
	//���ݱ�Ŀ�ʼʱ�����ģ����ѯ
	public ArrayList<ShenPiClass> SelectStarttime(String starttime) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where STARTTIME like '"+starttime+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//���ݱ�Ľ���ʱ�����ģ����ѯ
	public ArrayList<ShenPiClass> SelectEndtime(String endtime) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where ENDTIME like '"+endtime+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	/**
	 * ��д����ʱ���ѯ��ҳ
	 */
	//�����ͬʱ���������һ�ܵĽ����
	public ArrayList<ShenPiClass> SelectTime1(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//��ȡһ�ܺ��ʱ��
        String xianzaiDate = "";   //��������
        String yizhouhouDate = ""; //���������
        Date date = new Date();//��ǰ����
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//ʱ���ʽ
        xianzaiDate = sdf.format(date); //��ǰ����תΪString
		try {
			yizhouhouDate = sdf.format(getDate(xianzaiDate, 7));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(yizhouhouDate);
		//��ȡʱ��εĺ�ͬ
        
		//����Ӧ��ת������
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+yizhouhouDate+"' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//�����ͬʱ��������ж��ܵĽ����
	public ArrayList<ShenPiClass> SelectTime2(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//��ȡһ�ܺ��ʱ��
        String xianzaiDate = "";   //��������
        String erzhouhouDate = ""; //ʮ���������
        Date date = new Date();//��ǰ����
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//ʱ���ʽ
        xianzaiDate = sdf.format(date); //��ǰ����תΪString
		try {
			erzhouhouDate = sdf.format(getDate(xianzaiDate, 14));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(erzhouhouDate);
		//��ȡʱ��εĺ�ͬ
        
		//����Ӧ��ת������
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+erzhouhouDate+"' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//�����ͬʱ������������ܵĽ����
	public ArrayList<ShenPiClass> SelectTime3(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//��ȡһ�ܺ��ʱ��
        String xianzaiDate = "";   //��������
        String sanzhouhouDate = ""; //��ʮһ�������
        Date date = new Date();//��ǰ����
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//ʱ���ʽ
        xianzaiDate = sdf.format(date); //��ǰ����תΪString
		try {
			sanzhouhouDate = sdf.format(getDate(xianzaiDate, 21));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(sanzhouhouDate);
		//��ȡʱ��εĺ�ͬ
        
		//����Ӧ��ת������
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+sanzhouhouDate+"' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	
	//����TBL_HETONG_SHENPI��ȫ�����ݹ��ƶ���ҳ��
	//�̶�����Ϊ�����,һ���ڵ�ʱ�䣬����ģ����ѯ�����ڸ澯ҳ���ҳ
	public int SelectCountSelectTime1(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//��ȡһ�ܺ��ʱ��
        String xianzaiDate = "";   //��������
        String yizhouhouDate = ""; //���������
        Date date = new Date();//��ǰ����
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//ʱ���ʽ
        xianzaiDate = sdf.format(date); //��ǰ����תΪString
		try {
			yizhouhouDate = sdf.format(getDate(xianzaiDate, 7));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(yizhouhouDate);
		int a = 0; 		//��ʼ����������
		int b = 5;		//��ʼ��ÿҳ����
		int c = 0;		//��ʼ�����м�ҳ
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+yizhouhouDate+"'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
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
	//����TBL_HETONG_SHENPI��ȫ�����ݹ��ƶ���ҳ��
	//�̶�����Ϊ�����,�����ڵ�ʱ�䣬����ģ����ѯ�����ڸ澯ҳ���ҳ
	public int SelectCountSelectTime2(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//��ȡһ�ܺ��ʱ��
        String xianzaiDate = "";   //��������
        String erzhouhouDate = ""; //���������
        Date date = new Date();//��ǰ����
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//ʱ���ʽ
        xianzaiDate = sdf.format(date); //��ǰ����תΪString
		try {
			erzhouhouDate = sdf.format(getDate(xianzaiDate, 14));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(erzhouhouDate);
		int a = 0; 		//��ʼ����������
		int b = 5;		//��ʼ��ÿҳ����
		int c = 0;		//��ʼ�����м�ҳ
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+erzhouhouDate+"'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
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
	//����TBL_HETONG_SHENPI��ȫ�����ݹ��ƶ���ҳ��
	//�̶�����Ϊ�����,һ���ڵ�ʱ�䣬����ģ����ѯ�����ڸ澯ҳ���ҳ
	public int SelectCountSelectTime3(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//��ȡһ�ܺ��ʱ��
        String xianzaiDate = "";   //��������
        String sanzhouhouDate = ""; //���������
        Date date = new Date();//��ǰ����
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//ʱ���ʽ
        xianzaiDate = sdf.format(date); //��ǰ����תΪString
		try {
			sanzhouhouDate = sdf.format(getDate(xianzaiDate, 7));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(sanzhouhouDate);
		int a = 0; 		//��ʼ����������
		int b = 5;		//��ʼ��ÿҳ����
		int c = 0;		//��ʼ�����м�ҳ
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+sanzhouhouDate+"'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
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
	
	/**   
	* ��������������ַ��� �� ��ǰ���� ��
	* ��� ָ��������ǰ��������ڶ���
	* @param dateString ���ڶ��� ����ʽ�� yyyy-MM-dd
	* @param lazyDays ���Ƶ�����
	* @return ָ�����ڵ���ָ������������ڶ���
	* @throws ParseException
	*/
		public static Date getDate(String dateString , int beforeDays) throws ParseException{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear+beforeDays );
			return cal.getTime();
	}
}
