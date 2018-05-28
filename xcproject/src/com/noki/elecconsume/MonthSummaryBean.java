package com.noki.elecconsume;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class MonthSummaryBean {
	private String endmonth;
	private String county;

	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public List getSummary(String chooseIdStr1){
		DataBase db=new DataBase();
		
		List<MonthSummaryBean> list=new ArrayList<MonthSummaryBean>();
		List rslist=new ArrayList();
		 String hzsql="   SELECT A.ENDMONTH,Z.XIAN FROM ELECTRICFEES E, AMMETERDEGREES A, ALLINFO_VIEW Z  " +
   		"WHERE Z.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND E.DFUUID IN ("+chooseIdStr1+")";
   		//"(select  dfuuid from electricfees where ELECTRICFEE_ID='"+dfid+"')";
		 System.out.println("getSummary:"+hzsql.toString());
	    try {
			db.connectDb();
			ResultSet rs=null;
			Query query=new Query();
			rs=db.queryAll(hzsql);
			rslist=query.query(rs);
			int fycount = ((Integer)rslist.get(0)).intValue();
			for( int k = fycount;k<rslist.size()-1;k+=fycount){
			MonthSummaryBean bean=new MonthSummaryBean();
				System.out.println("--"+k);
		     //num为序号，不同页中序号是连续的
		   bean.setEndmonth((String)rslist.get(k+rslist.indexOf("ENDMONTH")));
		   bean.setCounty((String)rslist.get(k+rslist.indexOf("XIAN")));
		   list.add(bean);
		}
			System.out.println("--"+rslist.toString());
			System.out.println("--"+hzsql.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}

	

}
