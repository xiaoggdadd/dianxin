package com.noki.common.oss.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;


import com.noki.common.oss.DateUtils;
import com.noki.common.oss.model.OSSAccount;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.model.DianbiaoBean;

public class OSSAccountDao {
	//对OSS_ACCOUNT表进行添加
	public int addOssAccount(OSSAccount os) {
		int rs = 0;
		String sql = "INSERT INTO OSS_ACCOUNT (" +
				"AREA_NAME," +
				"AREA_ID," +
				"ORG_NAME," +
				"STAFF_ID," +
				"STAFF_NAME," +
				"USERNAME," +
				"PASS_WORD," +
				"MOBILE_TEL," +
				"EMAIL," +
				"OFFICE_TEL,"+
				"JOB_NAME,"+
				"SEX,"+
				"NATION,"+
				"BIRTHDAY,"+
				"ID_CARD,"+
				"STAFF_LEVEL,"+
				"WORK_AREA,"+
				"BEGINWORKDATE,"+
				"NOWWORKDATE,"+
				"WORK_TYPE,"+
				"HOME_ADDRESS,"+
				"EDU_LEVEL,"+
				"GETEDUDATE,"+
				"STATE,"+
				"IS_DW,"+
				"UPDATE_DATE,"+
				"CREATE_DATE,"+
				"ISONWORKTEXT," +
				"AREA_CODE," +
				"ORG_ID," +
				"ORG_CODE"+
				") VALUES (" +
						"'"+os.getAreaName()+"'," +
						"'"+os.getAreaId()+"'," +
						"'"+os.getOrgName()+"'," +
						"'"+os.getStaffId()+"'," +
						"'"+os.getStaffName()+"'," +
						"'"+os.getUsername()+"'," +
						"'"+os.getPassWord()+"'," +
						"'"+os.getMobileTel()+"'," +
						"'"+os.getEmail()+"'," +
						"'"+os.getOfficeTel()+"'," +
						"'"+os.getJobName()+"'," +
						"'"+os.getSex()+"'," +
						"'"+os.getNation()+"'," +
						"to_date('"+DateUtils.toStringLong(os.getBirthday())+"', 'yyyy-MM-dd hh24:mi:ss')," +
						"'"+os.getIdCard()+"'," +
						"'"+os.getStaffLevel()+"'," +
						"'"+os.getWorkArea()+"'," +
						"to_date('"+DateUtils.toStringLong(os.getBeginworkdate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
						"'"+os.getNowworkdate()+"'," +
						"'"+os.getWorkType()+"'," +
						"'"+os.getHomeAddress()+"'," +
						"'"+os.getEduLevel()+"'," +
						"to_date('"+DateUtils.toStringLong(os.getGetedudate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
						"'"+os.getState()+"'," +
						"'"+os.getIsDw()+"'," +
						"to_date('"+DateUtils.toStringLong(os.getUpdateDate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
						"to_date('"+DateUtils.toStringLong(os.getCreateDate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
						"'"+os.getIsonworktext()+"'," +
						"'"+os.getAreaCode()+"'," +
						"'"+os.getOrgId()+"'," +
						"'"+os.getOrgCode()+"')";
		DataBase db = new DataBase();
		System.out.println("OSS_ACCOUNT表添加sql："+sql);
		try {
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
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
	/**
	 * 硕 18-4-8
	 * @param oos账号根据用户名
	 * @return 
	 */
	public String addOssPrivilegedwang(String MOBILE_TEL) {
		String stf = "";
		StringBuffer sql = new StringBuffer();
		OSSAccount bean = new OSSAccount();
		sql.append("SELECT O.AREA_NAME,O.ORG_NAME,O.STAFF_NAME,O.MOBILE_TEL FROM OSS_ACCOUNT O WHERE O.STATE='1' AND O.username ='"+MOBILE_TEL+"'");
		System.out.println("[通过接收ossid来确定以及是否登陆]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				System.out.println("此次申请登陆账号的电话号码为："+MOBILE_TEL+"名字为："+rs.getString("STAFF_NAME")+"地市名称为："+rs.getString("AREA_NAME")+"单位为："+rs.getString("ORG_NAME"));
				if(rs.getString("MOBILE_TEL")!=""||rs.getString("MOBILE_TEL")!=null){
					stf=rs.getString("MOBILE_TEL");
				}else if(rs.getString("STAFF_NAME")!=""||rs.getString("STAFF_NAME")!=null){
					stf="wu";
				}else{
					stf="wu";
				}
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return  stf;}
	/**
	 * 硕
	 * @param oos账号查询确定
	 * @return
	 */
	public String addOssPrivileged(String MOBILE_TEL) {
		String stf = "";
		StringBuffer sql = new StringBuffer();
		OSSAccount bean = new OSSAccount();
		sql.append("SELECT O.AREA_NAME,O.ORG_NAME,O.STAFF_NAME,O.MOBILE_TEL FROM OSS_ACCOUNT O WHERE O.STATE='1' AND O.STAFF_ID ='"+MOBILE_TEL+"'");
		System.out.println("[通过接收ossid来确定以及是否登陆]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				System.out.println("此次申请登陆账号的电话号码为："+MOBILE_TEL+"名字为："+rs.getString("STAFF_NAME")+"地市名称为："+rs.getString("AREA_NAME")+"单位为："+rs.getString("ORG_NAME"));
				if(rs.getString("MOBILE_TEL")!=""||rs.getString("MOBILE_TEL")!=null){
					stf=rs.getString("MOBILE_TEL");
				}else if(rs.getString("STAFF_NAME")!=""||rs.getString("STAFF_NAME")!=null){
					stf="wu";
				}else{
					stf="wu";
				}
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return  stf;}

	//查询表OSS_ACCOUNT
	public ArrayList<OSSAccount> queryOssAccount(){
		ArrayList<OSSAccount> al = new ArrayList<OSSAccount>();	//创建返回集合

		String sql = "SELECT ID," +
				"AREA_NAME," +
				"AREA_ID," +
				"ORG_NAME," +
				"STAFF_ID," +
				"STAFF_NAME," +
				"USERNAME," +
				"PASS_WORD," +
				"MOBILE_TEL," +
				"EMAIL," +
				"OFFICE_TEL,"+
				"JOB_NAME,"+
				"SEX,"+
				"NATION,"+
				"BIRTHDAY,"+
				"ID_CARD,"+
				"STAFF_LEVEL,"+
				"WORK_AREA,"+
				"BEGINWORKDATE,"+
				"NOWWORKDATE,"+
				"WORK_TYPE,"+
				"HOME_ADDRESS,"+
				"EDU_LEVEL,"+
				"GETEDUDATE,"+
				"STATE,"+
				"IS_DW,"+
				"UPDATE_DATE,"+
				"CREATE_DATE,"+
				"ISONWORKTEXT," +
				"AREA_CODE," +
				"ORG_ID," +
				"ORG_CODE"+
				" FROM OSS_ACCOUNT where STATE=1";
		System.out.println("查询表OSS_ACCOUNTsql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				OSSAccount oss = new OSSAccount();	//实体类
				System.out.println(rs.getString("ORG_NAME")+"010");
				oss.setId(rs.getInt("ID"));
				oss.setAreaName(rs.getString("AREA_NAME"));
				oss.setAreaId(rs.getInt("AREA_ID"));
				oss.setOrgName(rs.getString("ORG_NAME"));
				oss.setStaffId(rs.getInt("STAFF_ID"));
				oss.setStaffName(rs.getString("STAFF_NAME"));
				oss.setUsername(rs.getString("USERNAME"));
				oss.setPassWord(rs.getString("PASS_WORD"));
				oss.setMobileTel(rs.getString("MOBILE_TEL"));
				oss.setEmail(rs.getString("EMAIL"));
				oss.setOfficeTel(rs.getString("OFFICE_TEL"));
				oss.setJobName(rs.getString("JOB_NAME"));
				oss.setSex(rs.getString("SEX"));
				oss.setNation(rs.getString("NATION"));
				oss.setBirthday(rs.getDate("BIRTHDAY"));
				oss.setIdCard(rs.getString("ID_CARD"));
				oss.setStaffLevel(rs.getString("STAFF_LEVEL"));
				oss.setWorkArea(rs.getString("WORK_AREA"));
				oss.setBeginworkdate(rs.getDate("BEGINWORKDATE"));
				oss.setNowworkdate(rs.getString("NOWWORKDATE"));
				oss.setWorkType(rs.getString("WORK_TYPE"));
				oss.setHomeAddress(rs.getString("HOME_ADDRESS"));
				oss.setEduLevel(rs.getString("EDU_LEVEL"));
				oss.setGetedudate(rs.getDate("GETEDUDATE"));
				oss.setState(rs.getInt("STATE"));
				oss.setIsDw(rs.getInt("IS_DW"));
				oss.setUpdateDate(rs.getTimestamp("UPDATE_DATE")); //rs.getDate()会丢失时分秒部分
				oss.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				oss.setIsonworktext(rs.getString("ISONWORKTEXT"));
				oss.setAreaCode(rs.getString("AREA_CODE"));
				oss.setOrgId(rs.getInt("ORG_ID"));
				oss.setOrgCode(rs.getString("ORG_CODE"));
				al.add(oss);
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
	//批量插入
	public int addsOssAccount(ArrayList<OSSAccount> al) {
		int rs = 0;
		DataBase db = new DataBase();
		try {
			db.connectDb();
			for (int i = 0; i < al.size(); i++) {
				String sql = "INSERT INTO OSS_ACCOUNT (" +
						"AREA_NAME," +
						"AREA_ID," +
						"ORG_NAME," +
						"STAFF_ID," +
						"STAFF_NAME," +
						"USERNAME," +
						"PASS_WORD," +
						"MOBILE_TEL," +
						"EMAIL," +
						"OFFICE_TEL,"+
						"JOB_NAME,"+
						"SEX,"+
						"NATION,"+
						"BIRTHDAY,"+
						"ID_CARD,"+
						"STAFF_LEVEL,"+
						"WORK_AREA,"+
						"BEGINWORKDATE,"+
						"NOWWORKDATE,"+
						"WORK_TYPE,"+
						"HOME_ADDRESS,"+
						"EDU_LEVEL,"+
						"GETEDUDATE,"+
						"STATE,"+
						"IS_DW,"+
						"UPDATE_DATE,"+
						"CREATE_DATE,"+
						"ISONWORKTEXT," +
						"AREA_CODE," +
						"ORG_ID," +
						"ORG_CODE"+
						") VALUES (" +
								"'"+al.get(i).getAreaName()+"'," +
								"'"+al.get(i).getAreaId()+"'," +
								"'"+al.get(i).getOrgName()+"'," +
								"'"+al.get(i).getStaffId()+"'," +
								"'"+al.get(i).getStaffName()+"'," +
								"'"+al.get(i).getUsername()+"'," +
								"'"+al.get(i).getPassWord()+"'," +
								"'"+al.get(i).getMobileTel()+"'," +
								"'"+al.get(i).getEmail()+"'," +
								"'"+al.get(i).getOfficeTel()+"'," +
								"'"+al.get(i).getJobName()+"'," +
								"'"+al.get(i).getSex()+"'," +
								"'"+al.get(i).getNation()+"'," +
								"to_date('"+DateUtils.toStringLong(al.get(i).getBirthday())+"', 'yyyy-MM-dd hh24:mi:ss')," +
								"'"+al.get(i).getIdCard()+"'," +
								"'"+al.get(i).getStaffLevel()+"'," +
								"'"+al.get(i).getWorkArea()+"'," +
								"to_date('"+DateUtils.toStringLong(al.get(i).getBeginworkdate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
								"'"+al.get(i).getNowworkdate()+"'," +    
								"'"+al.get(i).getWorkType()+"'," +
								"'"+al.get(i).getHomeAddress()+"'," +
								"'"+al.get(i).getEduLevel()+"'," +
								"to_date('"+DateUtils.toStringLong(al.get(i).getGetedudate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
								"'"+al.get(i).getState()+"'," +
								"'"+al.get(i).getIsDw()+"'," +
								"to_date('"+DateUtils.toStringLong(al.get(i).getUpdateDate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
								"to_date('"+DateUtils.toStringLong(al.get(i).getCreateDate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
								"'"+al.get(i).getIsonworktext()+"'," +
								"'"+al.get(i).getAreaCode()+"'," +
								"'"+al.get(i).getOrgId()+"'," +
								"'"+al.get(i).getOrgCode()+"')";
								rs = db.update(sql);
				System.out.println("OSS_ACCOUNT表批量添加sql："+sql);
				} 
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
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
	
	//修改STATE人员状态
	public int reviseState(int staffId,String state) {
		int rs = 0;
		String sql = "UPDATE OSS_ACCOUNT SET STATE = '"+state+"' WHERE STAFF_ID = "+staffId+"";
		DataBase db = new DataBase();
		System.out.println("OSS_ACCOUNT表修改State列值sql："+sql);
		try {
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
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
	//根据OSS_ACCOUNT表STAFF_ID字段 修改数据
	public int updateOSSAccount(OSSAccount os) {
		int rs = 0;
		String sql = "UPDATE OSS_ACCOUNT SET " +
				"AREA_NAME = '"+os.getAreaName()+"'," +
				"AREA_ID = '"+os.getAreaId()+"'," +
				"ORG_NAME = '"+os.getOrgName()+"'," +
				"STAFF_NAME = '"+os.getStaffName()+"'," +
				"USERNAME = '"+os.getUsername()+"'," +
				"PASS_WORD = '"+os.getPassWord()+"'," +
				"MOBILE_TEL = '"+os.getMobileTel()+"'," +
				"EMAIL = '"+os.getEmail()+"'," +
				"OFFICE_TEL = '"+os.getOfficeTel()+"'," +
				"SEX = '"+os.getSex()+"'," +
				"NATION = '"+os.getNation()+"'," +
				"BIRTHDAY = to_date('"+DateUtils.toStringLong(os.getBirthday())+"', 'yyyy-MM-dd hh24:mi:ss')," +
				"ID_CARD = '"+os.getIdCard()+"'," +
				"STAFF_LEVEL = '"+os.getStaffLevel()+"'," +
				"WORK_AREA = '"+os.getWorkArea()+"'," +
				"BEGINWORKDATE = to_date('"+DateUtils.toStringLong(os.getBeginworkdate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
				"NOWWORKDATE = '"+os.getNowworkdate()+"'," +
				"WORK_TYPE = '"+os.getWorkType()+"'," +
				"HOME_ADDRESS = '"+os.getHomeAddress()+"'," +
				"EDU_LEVEL = '"+os.getEduLevel()+"'," +
				"GETEDUDATE = to_date('"+DateUtils.toStringLong(os.getGetedudate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
				"STATE = '"+os.getState()+"'," +
				"IS_DW = '"+os.getIsDw()+"'," +
				"UPDATE_DATE = to_date('"+DateUtils.toStringLong(os.getUpdateDate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
				"CREATE_DATE = to_date('"+DateUtils.toStringLong(os.getCreateDate())+"', 'yyyy-MM-dd hh24:mi:ss')," +
				"ISONWORKTEXT = '"+os.getIsonworktext()+"'," +
				"AREA_CODE = '"+os.getAreaCode()+"'," +
				"ORG_ID = '"+os.getOrgId()+"'," +
				"ORG_CODE = '"+os.getOrgCode()+"'" +
				"WHERE STAFF_ID = "+os.getStaffId()+"";
		DataBase db = new DataBase();
		System.out.println("OSS_ACCOUNT表根据staffId修改内容sql："+sql);
		try {
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
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
//    public static void main(String[] args) {
//    	插入可用
//    	OSSAccount oss = new OSSAccount(0, "地市名称",0,"单位",001,"姓名","维护人员oos账号","密码","手机","邮箱","办公电话","职位","性别","民族",new Date(),"省份号码","职称","工作地区",new Date(),"现港工作时长","维护专业","家庭住址","文化程度",new Date(),0,101,new Date(),new Date(),"是否在岗","地区code",101,"单位code");
//    	addOssAccount(oss);
//    	查询可用
//    	ArrayList<OSSAccount> al = new ArrayList<OSSAccount>();
//    	al = queryOssAccount();
//    	for (int i = 0; i < al.size(); i++) {
//    		System.out.println(i);
//			System.out.println(al.get(i).getAreaName());
//			System.out.println(al.get(i).getPassWord());
//		}
//    	
//    }
}	
