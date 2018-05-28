package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.javabean.Zdinfo;
//完成整改提交待审
public class ZGShenHeDao {
	
	
	
	
	public List<Zdinfo> getZdinfo(String whereStr){//完成整改提交待审--查询//888888888888
		String sql ="select cc.zdid,cc.zdname,cc.xian,cc.xiaoqu,cc.cbsj,cc.cbbl,zz.csms,zz.yyfx,zz.cszrr,cb.xfzgyq,to_char(cb.wcsj,'yyyy-mm-dd'),zz.qxtjsh,cc.id as ccid,zz.id,zz.sjshbz" +
				" from CBZD cc, CBZDXX zz,CBZDXF cb, zhandian zd" +
				" where cc.id = zz.wjid and zd.id = cc.zdid and zz.id = cb.bwjid  and zz.shijxf='1'"+whereStr;
		System.out.println("提交待审:"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			List<Zdinfo> list = new ArrayList<Zdinfo>();
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
				zd.setQxtjsh(rs.getString(12));
				zd.setId(rs.getString("ccid"));
				zd.setCid(rs.getString(14));
				zd.setSjshbz(rs.getString(15));
				list.add(zd);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		return null;
	}
	public synchronized int TJSHsave(String loginName,String wcsm,String zhssdl, String beginTime1,String zgfzr,String id,String path,String name,String zdid){//
		int msg=0;
		if(!path.equals("")){
			path=path+"\\"+name;
		}
		
		// name=id+zdid+"ID"+name;
		
		String sql="update cbzdxx c set c.qxtjsh='1',c.qxtjshsj=sysdate,c.qxtjshr='"+loginName+"',c.zhssdl=to_number('"+zhssdl+"') "+"' where c.id in("+id+")";
		String sql1="update cbzdxf cb set cb.wcsm='"+wcsm+"',cb.qxwcsj=to_date('"+beginTime1+"','YYYY/MM/DD')," +
				"cb.zgzrr='"+zgfzr+"',cb.qxlrr='"+loginName+"',cb.qxpath='"+path+"',cb.qxname='"+name+"' where cb.bwjid in("+id+")";
		DataBase db = new DataBase();
		System.out.println("区县整改保存修改："+sql);
		System.out.println("区县整改保存修改："+sql1);
		try {	
			db.connectDb();
			db.update(sql.toString());
			db.update(sql1.toString());
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
	
	public synchronized int TJSHsave1(String loginName,String jlzfh,String zlzfh, String qsdb,String zhssdl,String zhdlwcsj,String yssdbdl,String id){//
		
		int msg=0;
	
		String sql="update cbzdxx c set c.qxtjsh='1',c.qxtjshsj=sysdate,c.qxtjshr='"+loginName+"',c.jlzfh='"+jlzfh+"',c.zlzfh='"+zlzfh+
					"',c.qsdb='"+qsdb+"',c.zhssdl=to_number('"+zhssdl+"'),"+"c.zhdlwcsj=to_date('"+zhdlwcsj+"','YYYY-MM'),"+"c.yssdbdl=to_number('"+yssdbdl+"')"+
					" where c.id in("+id+")";
		
		DataBase db = new DataBase();
		System.out.println("区县整改保存修改："+sql);
		
		try {	
			db.connectDb();
			
			int a=db.update(sql.toString());
			
			db.commit();
			if(a>=1){
			msg = 1;
			}
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
	
	  public synchronized int SHchexiao(String chooseIdStr){//完成整改提交待审撤销
			int msg=0;
			//sql.append("update CBZDXX c set c.qxtjsh='0' where c.shijxf='1' and c.id in ("+chooseIdStr+")");
			String sql="update CBZDXX c set c.qxtjsh='0',c.qxtjshsj='',c.qxtjshr='' where c.shijxf='1' and c.id in ("+chooseIdStr+")";
			String sql1="update CBZDXF cc set cc.wcsm='',cc.qxwcsj='',cc.zgzrr='',cc.qxlrr='',cc.qxpath='',cc.qxname='' where cc.bwjid in("+chooseIdStr+")";
			DataBase db = new DataBase();
			System.out.println("完成整改提交待审撤销："+sql.toString());
			System.out.println("完成整改提交待审撤销："+sql1.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.update(sql1.toString());
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
	  public synchronized int TJSHSave(String loginName,String wcsm,String zhssdl,String beginTime1,String zgfzr,String id,String path,String name){//
			int msg=0;
			if(!path.equals("")){
				path=path+"\\"+name;
			}
			
			// name=id+zdid+"ID"+name;
			
			String sql="update cbzdxx c set c.qxtjsh='1',c.qxtjshsj=sysdate,c.qxtjshr='"+loginName+"',c.zhssdl=to_number('"+zhssdl+"') "+"where c.id in("+id+")";
			String sql1="update cbzdxf cb set cb.wcsm='"+wcsm+"',cb.qxwcsj=to_date('"+beginTime1+"','YYYY-MM-DD')," +
					"cb.zgzrr='"+zgfzr+"',cb.qxlrr='"+loginName+"',cb.qxpath='"+path+"',cb.qxname='"+name+"' where cb.bwjid in("+id+")";
			DataBase db = new DataBase();
			System.out.println("区县整改保存修改："+sql);
			System.out.println("区县整改保存修改："+sql1);
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.update(sql1.toString());
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
