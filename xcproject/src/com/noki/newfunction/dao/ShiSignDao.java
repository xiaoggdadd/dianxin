package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.CTime;
//地市
public class ShiSignDao {
	 public synchronized int Update(String loginId,String loginName){
		 	int msg=1;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("update CBZD c set c.dsqs ='1',c.dsqsr='"+loginName+"',c.dsqssj=sysdate where c.id in (select cb.id from zhandian z, cbzd cb where z.id = cb.zdid and cb.sjpd = '1' and cb.dsqs = '0'and (z.xiaoqu in (select t.agcode from per_area t where t.accountid ='"+loginId+"')))");
			DataBase db = new DataBase();
			System.out.println("地市派单签收修改："+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 0;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 public synchronized int Check(String loginId){//地市查询
		 String sql =" select cb.id from zhandian z,cbzd cb where z.id = cb.zdid and cb.sjpd='1' and cb.dsqs='0'and(z.xiaoqu in (select t.agcode from per_area t where t.accountid='"+loginId+"'))";
		 System.out.println("地市查询"+sql);
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		 int i =0;
		 try{
			 db.connectDb();
			 rs = db.queryAll(sql);
			 while(rs.next()){
				// System.out.println("qqqqq"+rs.next());
				// System.out.println("11111111111111111111111");
				 i=1;
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 if(rs!=null){
				 try{
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 try{
				 db.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 System.out.println("sdsdsdsdsas"+i);
		 return i;
	 }
	 public synchronized List<Zdinfo> getZdinfo(String whereStr){
		String sql = "select cc.zdid, cc.zdname, cc.xian,cc.xiaoqu,cc.cbsj,cc.cbbl,zz.zgyq,zz.sjname," +
				"zz.dgpch,zz.qxlrr,zz.qxlrsj,cc.dspd,cc.id,zd.id as idd,zz.bt,zz.yppch,cc.qxqs,cc.nhbz from CBZD cc,CBZDXX zz,zhandian zd " +
				"where cc.id=zz.wjid and zd.id=cc.zdid and cc.zdid=zz.zdid and cc.cbsj=zz.cbsj and cc.sjpd = '1' and cc.dsqs = '1' "+whereStr;//and cc.sjpd = '1' and cc.dsqs = '1'
		System.out.println("地市派单查询"+sql);
		DataBase db = new DataBase();
		ResultSet rs =null;
		List<Zdinfo> list  = new ArrayList<Zdinfo>();
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			
			while(rs.next()){
				Zdinfo zd = new Zdinfo();
				zd.setZdid(rs.getString(1));
				zd.setZdname(rs.getString(2));
				zd.setXian(rs.getString(3));
				zd.setXiaoqu(rs.getString(4));
				zd.setCbsj(rs.getString(5));
				zd.setCbbl(rs.getString(6));
				zd.setZgyq(rs.getString(7));
				zd.setSjname(rs.getString(8));
				zd.setLch(rs.getString(9));
				zd.setQxlrr(rs.getString(10));
				zd.setQxlrsj(rs.getString(11));
				zd.setDspd(rs.getString(12));
				//System.out.println(rs.getString(12)+"2222222");
				zd.setId(rs.getString(13));
				zd.setIdd(rs.getString(14));
				zd.setBt(rs.getString(15));
				zd.setYppch(rs.getString(16));
				zd.setQxqs(rs.getString(17));
				zd.setNhbz(rs.getString(18));
				list.add(zd);
			}return list;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;
	 }
	 public synchronized int TDUpdate(String id,String loginName){
		 	int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			//StringBuffer sql = new StringBuffer();
			//sql.append("update CBZD c set c.dsqs='0',c.sjpd='2',c.DSPD='3',c.DSPDR='"+loginName+"',c.DSPDSJ=sysdate where c.dsqs='1' and c.sjpd='1' and c.id in ("+id+")");
			String sql="update CBZD c set c.dsqs='0',c.sjpd='2',c.DSPD='0',c.DSPDR='"+loginName+"',c.DSPDSJ=sysdate where c.dsqs='1' and c.sjpd='1' and c.id in ("+id+")";
			String sql1="delete cbzdxx x where x.wjid in ("+id+")";
			DataBase db = new DataBase();
			
			
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.update(sql1.toString());
				System.out.println("地市退单："+sql.toString());
				System.out.println("地市退单关联信息删除："+sql1.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 public synchronized int PDUpdate(String id,String loginName){
		 	int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("update CBZD c set c.dspd='1',c.qxqs='0',c.qxpd='0',c.dspdr='"+loginName+"',c.dspdsj=sysdate where c.dsqs='1' and c.sjpd='1' and c.id in ("+id+")");
			DataBase db = new DataBase();
			System.out.println("地市派单----："+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 public synchronized int CHUpdate(String id,String loginName){
		 	int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("update CBZD c set c.dspd='0',c.qxqs='0',c.dspdr='"+loginName+"',c.dspdsj=sysdate where c.dsqs='1' and c.sjpd='1' and c.id in ("+id+")");
			DataBase db = new DataBase();
			System.out.println("地市派单："+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 
	 public List<Zdinfo> getdsxf(String whereStr,String loginid){
			String sql = "select cc.zdid, cc.zdname, cc.xian,cc.xiaoqu,cc.cbsj,cc.cbbl,zz.csms," +
					"zz.yyfx,zz.cszrr,cb.xfzgyq,to_char(cb.wcsj,'yyyy-mm-dd'),cc.id,zz.shijxf,z.id as idd,zz.sjshbz,zz.bt,zz.yppch,zz.dgpch  " +
					"from CBZD cc,CBZDXX zz,CBZDXF cb,zhandian z " +
					"where z.id=cc.zdid and cc.id=zz.wjid and zz.id=cb.bwjid " +
					//" and zz.sjxf='1' " +
					""+whereStr+" " +
					"AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginid + "'))";//and cc.sjpd = '1' and cc.dsqs = '1'
			System.out.println("地市下发查询"+sql);
			DataBase db = new DataBase();
			ResultSet rs =null;
			List<Zdinfo> list  = new ArrayList<Zdinfo>();
			try{
				db.connectDb();
				rs = db.queryAll(sql);
				
				while(rs.next()){
					Zdinfo zd = new Zdinfo();
					zd.setZdid(rs.getString(1));
					zd.setZdname(rs.getString(2));
					zd.setXian(rs.getString(3));
					zd.setXiaoqu(rs.getString(4));
					zd.setCbsj(rs.getString(5));
					zd.setCbbl(rs.getString(6));
					zd.setCsms(rs.getString(7));
					zd.setYyfx(rs.getString(8));
					zd.setCszrr(rs.getString(9));
					zd.setXfzgyq(rs.getString(10));
					zd.setWcsj(rs.getString(11));
					
					zd.setId(rs.getString(12));
				
					zd.setShijxf(rs.getString(13));
					zd.setIdd(rs.getString(14));
					zd.setSjshbz(rs.getString(15));
					zd.setBt(rs.getString(16));
					zd.setYppch(rs.getString(17));
					zd.setDgpch(rs.getString(18));
					list.add(zd);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception se) {
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
	 public synchronized int PDUpdate1(String id,String loginName){ 
		 int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("update CBZDXX c set c.shijxf='1',c.shijxfr='"+loginName+"',c.shijxfsj=sysdate where  c.sjxf='1' and c.wjid in ("+id+")");
			DataBase db = new DataBase();
			System.out.println("地市下发："+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 public synchronized int CHUpdate1(String id,String loginName){ 
		 int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("update CBZDXX c set c.shijxf='0',c.shijxfr='"+loginName+"',c.shijxfsj=sysdate where  c.sjxf='1' and c.wjid in ("+id+")");
			DataBase db = new DataBase();
			System.out.println("地市撤回："+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 
}
