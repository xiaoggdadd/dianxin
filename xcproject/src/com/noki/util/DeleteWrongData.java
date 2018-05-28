package com.noki.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;

public class DeleteWrongData {
	//清除隔天取得数据（多倍）
	public static ArrayList getPageData(int start) {
		ArrayList list = new ArrayList();
		ArrayList<String> Daylist = new ArrayList<String>();
		ArrayList<String> sqllist = new ArrayList<String>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		
		           Date d1 = df.parse("2012-05-17");  
			           System.out.println("d1=="+df.format(d1));  
			           for(int i=0;i<=54;i++){
				            Calendar  g = Calendar.getInstance();  
				             g.setTime(d1); 
				            g.add(Calendar.DAY_OF_MONTH,-1);     
				            d1 = g.getTime();  
				            Daylist.add(df.format(d1).toString());
			            }
			
			db.connectDb();
			StringBuffer SQL = new StringBuffer();
			for(int i=0;i<Daylist.size()-1;i++){
				SQL.setLength(0);
				SQL.append("select t.ammeterid_fk,t.ammeterdegreeid from ammeterdegrees t where t.thisdatetime = '"+Daylist.get(i)+"'");
				System.out.println(SQL.toString());
				ResultSet rs1 = null;

				rs1 = db.queryAll(SQL.toString());
				int t=0;
				while(rs1.next()){
					
					String SQL1="delete from ammeterdegrees  a where a.ammeterdegreeid="+rs1.getInt(2)+"  and  (select count(t.ammeterdegreeid) from ammeterdegrees t where t.ammeterid_fk='"+rs1.getString(1)+"' and  t.thisdatetime = '"+Daylist.get(i+1)+"')!=1 ";
					//System.out.println(t++);
					sqllist.add(SQL1);
					/*db.update(SQL1);
					if(i%20==0){
						db.close();
						db.connectDb();
					}*/
					
				}
				
				
				
				
				
			
			}
			
			for(int i=0;i<sqllist.size();i++){
				
			//	System.out.println(sqllist.size()+"  "+i+"  "+sqllist.get(i));
				//db.update(sqllist.get(i));
				if(i%20==0){
					db.close();
					db.connectDb();
				}
			}

			//
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	public static void deleteExceptionData() {
		ArrayList<String> Daylist = new ArrayList<String>();
		ArrayList<String> sqllist = new ArrayList<String>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		
		           Date d1 = df.parse("2012-05-20");  
			           System.out.println("d1=="+df.format(d1));  
			           for(int i=0;i<=57;i++){
				            Calendar  g = Calendar.getInstance();  
				             g.setTime(d1); 
				            g.add(Calendar.DAY_OF_MONTH,-1);     
				            d1 = g.getTime();  
				            Daylist.add(df.format(d1).toString());
			            }
			
			db.connectDb();
			StringBuffer SQL = new StringBuffer();
			for(int i=0;i<Daylist.size()-1;i++){
				SQL.setLength(0);
				SQL.append("select t.ammeterid_fk,t.ammeterdegreeid,t.ACTUALDEGREE from ammeterdegrees t where t.thisdatetime = '"+Daylist.get(i)+"'");
				//System.out.println(SQL.toString());
				ResultSet rs1 = null;

				rs1 = db.queryAll(SQL.toString());
				while(rs1.next()){
					
					//String SQL1="delete from ammeterdegrees  a where a.ammeterid_fk='"+rs1.getString(1)+"' and a.ammeterdegreeid="+rs1.getInt(2)+"  and (select avg(z.ACTUALDEGREE) from ammeterdegrees z where z.ammeterid_fk='"+rs1.getString(1)+"')>=8 and to_number('"+rs1.getString(3)+"')<=2 ";
					//String SQL1="delete from ammeterdegrees  a where a.ammeterid_fk='"+rs1.getString(1)+"' and a.ammeterdegreeid="+rs1.getInt(2)+"  and (select avg(z.ACTUALDEGREE) from ammeterdegrees z where z.ammeterid_fk='"+rs1.getString(1)+"')<=8 and to_number('"+rs1.getString(3)+"')>=20";
					
					//System.out.println(SQL1);
					//sqllist.add(SQL1);
					
					
				}
				
				
				
				
				
			
			}
			
			for(int i=0;i<sqllist.size();i++){
				System.out.println(i+"语句"+sqllist.get(i));
				db.update(sqllist.get(i));
				if(i%20==0){
					db.close();
					db.connectDb();
				}
			}

			//
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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
	}
	public static void main(String[] args) {
		//getPageData(0);
		deleteExceptionData();
	}
}
