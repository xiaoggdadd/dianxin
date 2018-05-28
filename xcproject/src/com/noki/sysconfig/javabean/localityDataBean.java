package com.noki.sysconfig.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class localityDataBean {

	//检索
	public  String getLoclityData(String fagcode,String maxnum,String minnum,String role) {
		
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow from d_area_grade  where 1=1 ");
		if(fagcode!=null){
			maxSql.append(" and fagcode='"+fagcode+"' and agcode in(select p.agcode  from per_area p where p.accountid='"+role+"')");
		}
		
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select agcode,agname,fagcode,agrade from d_area_grade  where 1=1 ");
		if(fagcode!=null){
			sql.append(" and fagcode='"+fagcode+"' and agcode in(select p.agcode  from per_area p where p.accountid='"+role+"')");
		}
		
		sql.append(" ) a1  where 1=1  ");
		if (maxnum!=null&&!"".equals(maxnum)){
           sql.append(" and rownum<="+maxnum);
		}
		sql.append(") where 1=1 ");
		if (minnum!=null&&!"".equals(minnum)){
			sql.append(" and rwn>="+minnum);
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
        ResultSet maxRs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println("测试"+maxSql.toString());//
				 maxRs = db.queryAll(maxSql.toString());
				 String maxRow = "";
	              while (maxRs.next()) {
	            	  maxRow = maxRs.getString("maxrow");
	              }
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("agcode")+"|");
            	   ret.append(rs.getString("agname")+"|");
            	   ret.append(rs.getString("agrade")+"|");
            	   ret.append(rs.getString("fagcode")+"|");
            	   ret.append(maxRow+"|");
            	   ret.append(rs.getString("rwn")+",");
                }                
			}catch (Exception de) {
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
		return ret.toString();
	}
	//add 检索
	public  String getAddData(String fagcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname,fagcode,agrade,agid from d_area_grade  where 1=1 ");
		if(fagcode!=null){
			sql.append(" and agcode='"+fagcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   ret.append(rs.getString("agcode")+"|");
            	   ret.append(rs.getString("agname")+"|");
            	   ret.append(rs.getString("fagcode")+"|");
            	   ret.append(rs.getString("agrade")+"|");
            	   ret.append(rs.getInt("agid")+",");
                }                
			}catch (Exception de) {
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
		return ret.toString();
	}
	
	//插入
	public String insertData(localityViewDataBean bean,String logname,String logid){
		localityViewDataBean be = new localityViewDataBean();
		String ret = "";
		String sql = "select t.* from d_area_grade t where 1=1 ";//查询数据库有没有这个数据
		if (!"".equals(bean.getAgcode())){
			sql = sql+"and t.agcode = '"+bean.getAgcode()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		String qx="insert into per_area(ACCOUNTID,ACCOUNTNAME,AGCODE) values('"+logid+"','"+logname+"','"+bean.getAgcode()+"')";
		//String xg="update per_area set ACCOUNTID='"+logid+"',ACCOUNTNAME='"+logname+"',AGCODE='"+bean.getAgcode()+"' where AGCODE='"+bean.getAgcode()+"'";
		System.out.println("插入权限"+qx.toString());
		String sql1="select count(*) from d_area_grade";
		try{
			db.connectDb();
			rs = db.queryAll(sql1);
			System.out.println("最大值;"+rs);
			if(rs.next()){
				be.setCountb(rs.getString(1));
				System.out.println("数据个数"+be.getCountb());
			}
			if(!"0".equals(be.getCountb())){
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into d_area_grade(agid,agcode,agname,fagcode,agrade )values( ";
				insertSql = insertSql+"(select max(AGID) from d_area_grade )+1,"+"'"+bean.getAgcode()+"','"+bean.getAgname()+"','"+bean.getFagcode()+"','"+bean.getAgrade()+"')";
				System.out.println("插入地区"+insertSql);
				db.update(insertSql);
				db.update(qx);
				
			}else{
				String updateSql = "update d_area_grade set agname='"
				+bean.getAgname()+"',agrade='"+bean.getAgrade()+"', fagcode = '"+bean.getFagcode()+"' where 1=1 ";
				updateSql = updateSql+" and  agcode ='" + bean.getAgcode()+"'";
				System.out.println("如果有数据就修改："+updateSql.toString());
				db.update(updateSql.toString());
			}	
			}else{
				String insertSq= " insert into d_area_grade(agid,agcode,agname,fagcode,agrade )values( ";
				insertSq = insertSq+"'1',"+"'"+bean.getAgcode()+"','"+bean.getAgname()+"','"+bean.getFagcode()+"','"+bean.getAgrade()+"')";
				System.out.println("为空时插入地区"+insertSq);
				db.update(insertSq);
				db.update(qx);
			}
			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
				 if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException se) {
		                    se.printStackTrace();
		                }
		            }
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return ret;
	 }
	//删除
	public int delData(String areaCode){
		ResultSet rs = null;
		localityViewDataBean bean = new localityViewDataBean();
		String sql2="delete per_area where agcode='"+areaCode+"'";
		String sqll="select agrade from d_area_grade where agcode = '"+areaCode+"'";
		
		String sql = "delete from d_area_grade t  where 1=1 ";
		if (!"".equals(areaCode)){
			sql = sql+"and t.agcode = '"+areaCode+"'";
		}		
		DataBase db = new DataBase();
		try{
			db.connectDb();
			System.out.println("编码级别："+sqll.toString());
			rs=db.queryAll(sqll.toString());
			if(rs.next()){
				 bean.setShu(rs.getInt(1));
				
				 System.out.println("num:"+bean.getShu());
			}
			
			if (this.code(areaCode,bean.getShu()).equals("2")) {
				return 2;
			}
			System.out.println("删除地区维护："+sql.toString());
			System.out.println("删除地区分配权限："+sql2.toString());
			int ret111 = db.update(sql.toString());
			int ret2 = db.update(sql2);

			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
				 if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException se) {
		                    se.printStackTrace();
		                }
		            }
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return 1;
	 }
	//删除的地区里是否有站点
	public String code(String areaCode,int num){
		
		String StrWhere = "";
		ResultSet rs = null;//站点信息
		ResultSet rs1 = null;//子信息    乡镇
	    //select agrade from d_area_grade where agcode=''
        if (!"".equals(areaCode)){
			
				
		switch(num)
		{
			
			case(1) :StrWhere="zd.sheng='"+areaCode+"'";break;
			case(2) :StrWhere="zd.shi='"+areaCode+"'";break;
			case(3) :StrWhere="zd.xian='"+areaCode+"'";break;
			case(4) :StrWhere="zd.xiaoqu='"+areaCode+"'";break;
			
		}
        }
	    String sql = "select * from zhandian zd join d_area_grade d on zd.sheng=d.agcode where "+ StrWhere;
		String sql1 = "select * from d_area_grade where fagcode="+areaCode;
		DataBase db = new DataBase();
		try{
			db.connectDb();
			System.out.println("查询站点："+sql.toString());
			System.out.println("查询子信息："+sql1.toString());
			rs1 = db.queryAll(sql1.toString());
			rs = db.queryAll(sql.toString());
			if(rs.next()||rs1.next()){
				return "2";
			}

			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
				 if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException se) {
		                    se.printStackTrace();
		                }
		            }
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return "0";
	 }
	
	
	
	
	//更新
	public String updateData(localityViewDataBean bean){
	String ret = "";
	String sql = "select t.* from d_area_grade t where 1=1 ";
	if (!"".equals(bean.getAgcode())){
		sql = sql+"and t.agid = "+bean.getAgid()+"";
	}		
	DataBase db = new DataBase();
	ResultSet rs = null;
	try{
		System.out.println("-"+sql.toString());
		db.connectDb();
		rs = db.queryAll(sql);
		if (!rs.next()){
			String insertSql= " insert into d_area_grade(agid,agcode,agname,fagcode,agrade )values( ";
			insertSql = insertSql+"(select max(AGID) from d_area_grade )+1,"+"'"+bean.getAgcode()+"','"+bean.getAgname()+"','"+bean.getFagcode()+"','"+bean.getAgrade()+"')";
			db.update(insertSql);
		}else{
			String updateSql = "update d_area_grade set agname='"
			+bean.getAgname()+"',agrade='"+bean.getAgrade()+"',agcode='"+bean.getAgcode()+"', fagcode = '"+bean.getFagcode()+"' where 1=1 ";
			updateSql = updateSql+" and  agid =" + bean.getAgid();
			db.update(updateSql.toString());
			System.out.println("-"+updateSql.toString());
		}	
		}catch(Exception e){
		      try {
			        db.rollback();
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      e.printStackTrace();
		}finally {
			 if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException se) {
	                    se.printStackTrace();
	                }
	            }
		      try {
			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
		}
		return ret;
	}
	//检索下拉框
	public  String getData(String fagcode,String role) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname,fagcode,agrade from d_area_grade  where 1=1 ");
		if(fagcode!=null){
			System.out.println("fagcode1111"+fagcode);
			if(fagcode.equals("000")){
				sql.append(" and fagcode='"+fagcode+"' ");
				System.out.println("fagcode"+fagcode);
			}else{
				sql.append(" and fagcode='"+fagcode+"' and agcode in(select p.agcode  from per_area p where p.accountid='"+role+"')");
			}
			
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println("检索下拉框:"+sql.toString());/////////////////////////////////////////////////////
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {

            	   ret.append(rs.getString("agcode")+"|");
            	   ret.append(rs.getString("agname")+"|");
            	   ret.append(rs.getString("agrade")+"|");
            	   ret.append(rs.getString("fagcode")+",");
                }                
			}catch (Exception de) {
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
		return ret.toString();
	}
	
	
	//重复数据
	public  boolean cfcode(String agcode,String agname) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname from d_area_grade  where adcode='"+agcode+"'");
		boolean bl=false;
        DataBase db = new DataBase();
        ResultSet rs = null;
		
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 
               if (rs.next()) {
            	   bl=true;
                }                
			}catch (Exception de) {
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
	
		return bl;
	}
}
