package com.noki.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class zanguBean {
	public String insertDate(String uuid,String lrren,ArrayList<CityQueryBean> list,String shi,String zgmonth,String bzw){		
		String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		DataBase db = new DataBase();
		ResultSet rs = null;
		String ret="1";
		
		//更改-------------------------------------
		StringBuffer sql=new StringBuffer();
		StringBuffer sql1=new StringBuffer();
		
		sql1.append("DELETE FROM ZANGU Z  WHERE Z.SHI='"+shi+"' AND Z.ZGMONTH='"+zgmonth+"' ");
		
		for(CityQueryBean bean:list){
		sql.append("insert into zangu(JZNAME,ADDRESS,STATIONTYPE,DFZFLX,LASTACCOUNTMONTH,ZANGUSTARTMONTH,ZANGUENDMONTH,TIANSHU,DANJIA,ZANGUMONEY,SAVETIME,uuid,zdid,ENTRYPENSONNEL,DBID,DBNAME,SHI,ZGMONTH) values('" +
				bean.getJzname()+"','"+bean.getAddress()+"','"+bean.getStationtype()+"','"+bean.getDfzflx()+"','"+bean.getLastaccountmonth()+"','"+bean.getZangustartmonth()+"','"+
				bean.getZanguendmonth()+"','"+bean.getTianshu()+"','"+bean.getDianfei()+"','"+bean.getZangumoney()+"',"+s+",'"+uuid+"','"+bean.getZdid()+"','"+lrren+"','"+bean.getDbid()+"','"+bean.getDbname()+"','"+bean.getShi()+"','"+bean.getZgmonth()+"')の");
		}
		
		try {
			db.connectDb();
			//调用方法更改
			if(bzw.equals("you")){
				System.out.println("删除本月已经暂估的静态数据："+sql1.toString());
				db.update(sql1.toString());
			}
			 db.updateBatchDr(sql.toString().split("の"));
			ret="2";
			db.close();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			try {
				db.rollback();
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		return ret;
	}
	
	public String InsertTX(CityQueryBean bean,String uuid,String lrren,String qsyf){		
		String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		DataBase db = new DataBase();
		String ret="1";
		ResultSet rs = null;
		String sql="insert into jtshujutx(ZDNAME,ZDID,DBID,SZDQ,DFZFLX,HTBH,TXJE,TXYE,PCH,LRREN,LRSJ,QSYF) values('" +
				bean.getJzname()+"','"+bean.getZdid()+"','"+bean.getDbid()+"','"+bean.getAddress()+"','"+bean.getDfzflx()+"','"+
				bean.getHtbh()+"','"+bean.getTxje()+"','"+bean.getTxye()+"','"+uuid+"','"+lrren+"',"+s+",'"+qsyf+"')";
		
		System.out.println("静态数据摊销："+sql);
		
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ret="2";
			db.close();
		} catch (DbException e) {
			// TODO Auto-generated catch block
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
			} catch (DbException de) {
				de.printStackTrace();
			}

		}	
		return ret;
	}
	
	//判断是否有暂估数据   zangu
	public boolean getRepeat(String zgmonth,String shi){
		boolean flag = false;
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT ZG.ID FROM ZANGU ZG WHERE ZG.ZGMONTH = ? AND ZG.SHI = ?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, zgmonth);
			ps.setString(2, shi);
			rs = ps.executeQuery();
			System.out.println("暂估重复判断："+sql);
			if(rs.next()){
					flag = true;//重复
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return flag;
//		String sql="SELECT DISTINCT SUBSTR(Z.ZANGUENDMONTH,1,7) FROM ZANGU Z,ZHANDIAN ZD WHERE Z.ZDID=ZD.ZDCODE"+
//          " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";
//		try {
//			db.connectDb();
//			System.out.println("暂估重复判断："+sql);
//			rs = db.queryAll(sql);
//			while(rs.next()){
//				CityQueryBean bean=new CityQueryBean();
//			    bean.setEndtime(rs.getString(1).trim());
//			list.add(bean);	
//			}
//			db.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			try {
//				db.close();
//			} catch (DbException de) {
//				de.printStackTrace();
//			}
//
//		}
//		System.out.println("zzzzzz"+list.size());
//		return list;
	}
	//判断是否有暂估数据  最新暂估表 zangushuju
	public boolean getZanGu(String zgmonth,String shi){
		boolean flag = false;
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT ZG.ID FROM ZANGUSHUJU ZG WHERE ZG.ZGMONTH = ? AND ZG.SHI = ?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, zgmonth);
			ps.setString(2, shi);
			rs = ps.executeQuery();
			System.out.println("暂估重复判断："+sql);
			if(rs.next()){
					flag = true;//重复
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return flag;
	}
	public List<CityQueryBean> getCfTx(String loginId){
		List<CityQueryBean> list=new ArrayList<CityQueryBean>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql="SELECT DISTINCT QSYF FROM JTSHUJUTX Z,ZHANDIAN ZD WHERE Z.ZDID=ZD.ZDCODE"+
          " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";
		try {
			db.connectDb();
			System.out.println("摊销重复判断："+sql);
			rs = db.queryAll(sql);
			
			while(rs.next()){
				CityQueryBean beans=new CityQueryBean();
				beans.setQsyf(rs.getString(1).trim());//去掉空格
			    list.add(beans);	
			}
			db.close();
		} catch (Exception e) {
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
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		//System.out.println("gggg"+list.size());
		return list;
	}
}
