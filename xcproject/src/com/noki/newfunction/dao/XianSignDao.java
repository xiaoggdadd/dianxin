package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.CTime;
//区县签收及派单
public class XianSignDao {
	public synchronized int PDUpdate(String loginId,String loginName){//区县派单签收修改
		int msg=1;
		StringBuffer sql = new StringBuffer();
		sql.append("update CBZD c set c.qxqs = '1',c.qxqsr='"+loginName+"',c.qxqssj=sysdate where c.id in (select cb.id from zhandian z, cbzd cb where z.id = cb.zdid and cb.dspd = '1' and cb.qxqs = '0'and (z.xiaoqu in (select t.agcode from per_area t where t.accountid ='"+loginId+"')))");
		DataBase db = new DataBase();
		System.out.println("区县派单签收修改："+sql.toString());
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
	public synchronized int PDChenk(String loginId){//区县派单是否签收查询
		String sql ="select cb.id from zhandian z,cbzd cb where z.id = cb.zdid and cb.dspd='1' and cb.qxqs='0'and(z.xiaoqu in (select t.agcode from per_area t where t.accountid='"+loginId+"'))";
		System.out.println("区县派单是否签收查询:"+sql);
		DataBase db = new DataBase();
		ResultSet rs =null;
		int i=0;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				String a = rs.getString("id");
				System.out.println("id"+a);
				i=1;
			}
			 
		} catch (Exception e) {
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
		System.out.println("idddd"+i);
		return i;		
		
	}
	public List<Zdinfo> getZdinfo(String whereStr){
		String sql ="select cc.zdid, cc.zdname, cc.xian,cc.xiaoqu,cc.cbsj,cc.cbbl,zz.zgyq,zz.dsfj,zz.dgpch,zz.qxlrr,zz.qxlrsj,cc.qxpd,CC.id,zd.id as idd,zz.bt,zz.yppch,zz.SJNAME,CC.QXTJSH,cc.nhbz " +
					"from CBZD cc,CBZDXX zz,zhandian zd " +
					"where cc.id=zz.wjid and zd.id=cc.zdid and cc.zdid=zz.zdid and cc.cbsj=zz.cbsj and cc.dspd='1' and cc.qxqs='1'"+whereStr;//and cc.sjpd='1' and cc.dspd='1' and cc.qxqs='1'
		System.out.println("区县及签收查询sql:"+sql);
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
				zd.setZgyq(rs.getString(7));
				zd.setDsfj(rs.getString(8));
				zd.setLch(rs.getString(9));
				zd.setQxlrr(rs.getString(10));
				zd.setQxlrsj(rs.getString(11));
				zd.setQxpd(rs.getString(12));
				zd.setId(rs.getString(13));
				zd.setIdd(rs.getString(14));
				zd.setBt(rs.getString(15));
				zd.setYppch(rs.getString(16));
				zd.setSjname(rs.getString(17));
				zd.setQxtjsh(rs.getString(18));
				zd.setNhbz(rs.getString(19));
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
	public synchronized int TDUpdate(String id,String loginName){//区县退单
		int msg=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update CBZD c set c.dspd='2',c.qxqs='0',c.QXPDR='"+loginName+"',c.QXPDSJ=sysdate where c.dspd='1' and c.qxqs='1' and c.id in ("+id+")");
		DataBase db = new DataBase();
		System.out.println("区县退单："+sql.toString());
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
   public synchronized int PDupdate(String chooseIdStr,String loginName){//区县派单
		int msg=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update CBZD c set c.qxpd='1',c.shijsh='0',c.qxpdr='"+loginName+"',c.qxpdsj=sysdate where c.dspd='1' and c.qxqs='1' and c.id in ("+chooseIdStr+")");
		DataBase db = new DataBase();
		System.out.println("区县派单："+sql.toString());
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
   public synchronized int CXupdate(String chooseIdStr){//区县派单撤销
		int msg=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update CBZD c set c.qxpd='0' where c.dspd='1' and c.qxqs='1' and c.id in ("+chooseIdStr+")");
		DataBase db = new DataBase();
		System.out.println("区县派单撤销："+sql.toString());
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
   public synchronized Zdinfo getShiXX(String id,String loginId) {	//完成整改提交待审 连接页面查询SQL	
   		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
       ResultSet rs = null;
       Zdinfo formbean=new Zdinfo();
		try {
			sql = "SELECT C.ZDID,C.ZDNAME,C.SHI, C.XIAN,C.XIAOQU,C.CBSJ,C.CBBL,X.SJLRR," +
					"to_char(X.SJLRSJ,'yyyy-mm-dd hh24:MI:SS'),X.ZGYQ,C.G2,C.G3,C.ZP,C.ZS,C.CHANGJIA,C.JZTYPE,C.SHEBEI,C.BBU,C.RRU," +
					"C.GXGWSL,C.Dxgxsb,C.YDGXSB,C.G2XQ,C.G3SQ,X.G2,X.G3,X.ZP,X.ZS," +
					"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.CHANGJIA AND T.TYPE = 'cj') as CHANGJIA," +
					" (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.JZTYPE AND T.TYPE = 'jzlx2') as JZTYPE," +
					"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.SHEBEI AND T.TYPE = 'sblx') as SHEBEI," +
					"X.BBU, X.RRU,X.GXGWSL,X.Dxgxsb,X.Ydgxsb,X.G2xq,X.G3sq,X.YYFX,X.CSZRR,x.dbds,x.kgdyzlfh," +
					"x.ydgxsbzlfh,x.dxgxsbzlfh,x.gygxsbzlfh,x.zyygsbzlfh,f.xfzgyq,to_char(f.wcsj,'yyyy-mm-dd'),f.sjpath,f.wcsm,f.qxwcsj,f.zgzrr,x.zlzfh,x.jlzfh,x.qsdb,x.bdedhdl,c.nhbz,x.zhssdl,x.yssdbdl " +
					"FROM ZHANDIAN Z, CBZD C, CBZDXX X, CBZDXF F WHERE Z.ID = C.ZDID AND C.ID = X.WJID AND X.Id = F.BWJID " +
					"AND C.ID ="+id+" AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = "+loginId+")))";
           System.out.println("完成整改提交待审--超标站点市审核详细信息查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				formbean.setZdid(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setZdname(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setShi(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setXian(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setXiaoqu(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setCbsj(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setCbbl(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setSjlrr(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setSjlrsj(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setZgyq(rs.getString(10)!=null?rs.getString(10):"");//整改要求
				formbean.setG2(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setG3(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setZp(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setZs(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setChangjia(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setJztype(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setShebei(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setBbu(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setRru(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setGxgwsl(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setJdxgxsb(rs.getString(21)!=null?rs.getString(21):"");
				formbean.setJydgxsb(rs.getString(22)!=null?rs.getString(22):"");
				formbean.setJg2xq(rs.getString(23)!=null?rs.getString(23):"");
				formbean.setJg3sq(rs.getString(24)!=null?rs.getString(24):"");
				formbean.setXg2(rs.getString(25)!=null?rs.getString(25):"");
				formbean.setXg3(rs.getString(26)!=null?rs.getString(26):"");
				formbean.setXzp(rs.getInt(27));
				formbean.setXzs(rs.getInt(28));
				formbean.setXchangjia(rs.getString(29)!=null?rs.getString(29):"");
				formbean.setXjztype(rs.getString(30)!=null?rs.getString(30):"");
				formbean.setXshebei(rs.getString(31)!=null?rs.getString(31):"");
				formbean.setXbbu(rs.getInt(32));
				formbean.setXrru(rs.getInt(33));
				formbean.setXgxgwsl(rs.getInt(34));
				formbean.setDxgxsb(rs.getString(35)!=null?rs.getString(35):"");
				formbean.setYdgxsb(rs.getString(36)!=null?rs.getString(36):"");
				formbean.setG2xq(rs.getString(37)!=null?rs.getString(37):"");
				formbean.setG3sq(rs.getString(38)!=null?rs.getString(38):"");
				formbean.setYyfx(rs.getString(39)!=null?rs.getString(39):"");
				formbean.setCszrr(rs.getString(40)!=null?rs.getString(40):"");
				formbean.setDbds(rs.getString(41)!=null?rs.getString(41):"");
				formbean.setKGDYZLFH(rs.getString(42)!=null?rs.getString(42):"");
				formbean.setYDGXSBZLFH(rs.getString(43)!=null?rs.getString(43):"0");
				formbean.setDXGXSBZLFH(rs.getString(44)!=null?rs.getString(44):"0");
				formbean.setGYGXSBZLFH(rs.getString(45)!=null?rs.getString(45):"0");
				formbean.setZYYGSBZLFH(rs.getString(46)!=null?rs.getString(46):"0");
			    formbean.setXfzgyq(rs.getString(47)!=null?rs.getString(47):"");
			    formbean.setWcsj(rs.getString(48)!=null?rs.getString(48):"");
			   // formbean.setCid(rs.getString(49)!=null?rs.getString(49):"");
			    formbean.setSjpath(rs.getString(49)!=null?rs.getString(49):"");
			    formbean.setWcsm(rs.getString(50)!=null?rs.getString(50):"");
			    formbean.setQxwcsj(rs.getString(51)!=null?rs.getString(51):"");
			    formbean.setZgzrr(rs.getString(52)!=null?rs.getString(52):"");
			    formbean.setZlzfh(rs.getString(53)!=null?rs.getString(53):"");
			    formbean.setJlzfh(rs.getString(54)!=null?rs.getString(54):"");
			    formbean.setQsdb(rs.getString(55)!=null?rs.getString(55):"");
			    formbean.setBdhdl(rs.getString(56)!=null?rs.getString(56):"");
			    formbean.setNhbz(rs.getString(57)!=null?rs.getString(57):"");
			    formbean.setZhssdl(rs.getString(58)!=null?rs.getString(58):"");
			    formbean.setYssdbdl(rs.getString(59)!=null?rs.getString(59):"");
				}
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return formbean;
	}
   
   public synchronized Zdinfo getShiXX1(String id,String loginId) {	//省级整改审核
  		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
      ResultSet rs = null;
      Zdinfo formbean=new Zdinfo();
		try {
			sql = "SELECT C.ZDID,C.ZDNAME,C.SHI, C.XIAN,C.XIAOQU,C.CBSJ,C.CBBL,X.SJLRR," +
					"to_char(X.SJLRSJ,'yyyy-mm-dd hh24:MI:SS'),X.ZGYQ,C.G2,C.G3,C.ZP,C.ZS,C.CHANGJIA,C.JZTYPE,C.SHEBEI,C.BBU,C.RRU," +
					"C.GXGWSL,C.Dxgxsb,C.YDGXSB,C.G2XQ,C.G3SQ,X.G2,X.G3,X.ZP,X.ZS," +
					"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.CHANGJIA AND T.TYPE = 'cj') as CHANGJIA," +
					" (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.JZTYPE AND T.TYPE = 'jzlx2') as JZTYPE," +
					"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.SHEBEI AND T.TYPE = 'sblx') as SHEBEI," +
					"X.BBU, X.RRU,X.GXGWSL,X.Dxgxsb,X.Ydgxsb,X.G2xq,X.G3sq,X.YYFX,X.CSZRR,x.dbds,x.kgdyzlfh," +
					"x.ydgxsbzlfh,x.dxgxsbzlfh,x.gygxsbzlfh,x.zyygsbzlfh,f.xfzgyq,to_char(f.wcsj,'yyyy-mm-dd'),f.sjpath,f.wcsm,f.qxwcsj,f.zgzrr,x.zlzfh,x.jlzfh,x.qsdb,x.bdedhdl,c.nhbz,x.zhssdl,x.yssdbdl,x.shyj,x.zhdlwcsj,c.shijshsj,x.sjshsj,x.csms,c.id,x.sjname " +
					"FROM ZHANDIAN Z, CBZD C, CBZDXX X, CBZDXF F WHERE Z.ID = C.ZDID AND C.ID = X.WJID AND X.Id = F.BWJID " +
					"AND X.ID ="+id+" AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = "+loginId+")))";
          System.out.println("省级整改审核详细："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				formbean.setZdid(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setZdname(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setShi(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setXian(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setXiaoqu(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setCbsj(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setCbbl(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setSjlrr(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setSjlrsj(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setZgyq(rs.getString(10)!=null?rs.getString(10):"");//整改要求
				formbean.setG2(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setG3(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setZp(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setZs(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setChangjia(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setJztype(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setShebei(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setBbu(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setRru(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setGxgwsl(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setJdxgxsb(rs.getString(21)!=null?rs.getString(21):"");
				formbean.setJydgxsb(rs.getString(22)!=null?rs.getString(22):"");
				formbean.setJg2xq(rs.getString(23)!=null?rs.getString(23):"");
				formbean.setJg3sq(rs.getString(24)!=null?rs.getString(24):"");
				formbean.setXg2(rs.getString(25)!=null?rs.getString(25):"");
				formbean.setXg3(rs.getString(26)!=null?rs.getString(26):"");
				formbean.setXzp(rs.getInt(27));
				formbean.setXzs(rs.getInt(28));
				formbean.setXchangjia(rs.getString(29)!=null?rs.getString(29):"");
				formbean.setXjztype(rs.getString(30)!=null?rs.getString(30):"");
				formbean.setXshebei(rs.getString(31)!=null?rs.getString(31):"");
				formbean.setXbbu(rs.getInt(32));
				formbean.setXrru(rs.getInt(33));
				formbean.setXgxgwsl(rs.getInt(34));
				formbean.setDxgxsb(rs.getString(35)!=null?rs.getString(35):"");
				formbean.setYdgxsb(rs.getString(36)!=null?rs.getString(36):"");
				formbean.setG2xq(rs.getString(37)!=null?rs.getString(37):"");
				formbean.setG3sq(rs.getString(38)!=null?rs.getString(38):"");
				formbean.setYyfx(rs.getString(39)!=null?rs.getString(39):"");
				formbean.setCszrr(rs.getString(40)!=null?rs.getString(40):"");
				formbean.setDbds(rs.getString(41)!=null?rs.getString(41):"");
				formbean.setKGDYZLFH(rs.getString(42)!=null?rs.getString(42):"");
				formbean.setYDGXSBZLFH(rs.getString(43)!=null?rs.getString(43):"0");
				formbean.setDXGXSBZLFH(rs.getString(44)!=null?rs.getString(44):"0");
				formbean.setGYGXSBZLFH(rs.getString(45)!=null?rs.getString(45):"0");
				formbean.setZYYGSBZLFH(rs.getString(46)!=null?rs.getString(46):"0");
			    formbean.setXfzgyq(rs.getString(47)!=null?rs.getString(47):"");
			    formbean.setWcsj(rs.getString(48)!=null?rs.getString(48):"");
			   // formbean.setCid(rs.getString(49)!=null?rs.getString(49):"");
			    formbean.setSjpath(rs.getString(49)!=null?rs.getString(49):"");
			    formbean.setWcsm(rs.getString(50)!=null?rs.getString(50):"");
			    formbean.setQxwcsj(rs.getString(51)!=null?rs.getString(51):"");
			    formbean.setZgzrr(rs.getString(52)!=null?rs.getString(52):"");
			    formbean.setZlzfh(rs.getString(53)!=null?rs.getString(53):"");
			    formbean.setJlzfh(rs.getString(54)!=null?rs.getString(54):"");
			    formbean.setQsdb(rs.getString(55)!=null?rs.getString(55):"");
			    formbean.setBdhdl(rs.getString(56)!=null?rs.getString(56):"");
			    formbean.setNhbz(rs.getString(57)!=null?rs.getString(57):"");
			    formbean.setZhssdl(rs.getString(58)!=null?rs.getString(58):"");
			    formbean.setYssdbdl(rs.getString(59)!=null?rs.getString(59):"");
			    formbean.setShyj(rs.getString(60)!=null?rs.getString(60):"");
			    formbean.setZhdlwcsj(rs.getString(61)!=null?rs.getString(61):"");
			    formbean.setShijshsj(rs.getString(62)!=null?rs.getString(62):"");
			    formbean.setSjshsj(rs.getString(63)!=null?rs.getString(63):"");
			    formbean.setCsms(rs.getString(64)!=null?rs.getString(64):"");
			    formbean.setId(rs.getString(65)!=null?rs.getString(65):"");
			    formbean.setSjname(rs.getString(66)!=null?rs.getString(66):"");
				}
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return formbean;
	}
   
   public synchronized Zdinfo getXinXi(String id,String loginId) {	//超标站点状态查询 连接页面查询SQL	
		String sql = "SELECT C.ID, C.ZDID, C.ZDNAME, C.SHI, C.XIAN, C.XIAOQU, C.BZPLD, C.Cbsj,c.sjlrr, to_char(c.sjlrsj,'yyyy-mm-dd hh24:MI:SS')," +
				" x.zgyq,C.G2,C.G3, C.ZP, C.ZS, C.CHANGJIA, C.JZTYPE,C.SHEBEI, C.BBU, C.RRU, C.GXGWSL, C.Dxgxsb, " +
				"C.Ydgxsb, C.g2xq, C.g3sq,X.G2, X.G3, X.ZP,X.ZS, (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.CHANGJIA  AND T.TYPE = 'cj') as CHANGJIA, (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.JZTYPE AND T.TYPE = 'stationtype') as JZTYPE," +
				" (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.SHEBEI AND T.TYPE = 'sblx') as SHEBEI, X.BBU," +
				" X.RRU, X.GXGWSL, x.dxgxsb,x.ydgxsb, x.g2xq, x.g3sq,x.sjyqwcsj, x.dbds, x.kgdyzlfh, " +
				"x.ydgxsbzlfh, x.dxgxsbzlfh,x.gygxsbzlfh, x.zyygsbzlfh, x.yyfx,x.cszrr, f.xfzgyq,to_char(f.wcsj,'yyyy-mm-dd'), " +
				"f.wcsm,to_char(f.qxwcsj,'yyyy-mm-dd'),f.zgzrr,c.tdyy,to_char(c.fksj,'yyyy-mm-dd'),x.sjyqwtgyy,to_char(x.sjyqwcsj,'yyyy-mm-dd'),x.DGPCH,x.YPPCH,x.BT,to_char(x.wcsj,'yyyy-mm-dd'),x.sjname,x.qxname,f.sjpath,f.qxname,x.zlzfh,x.jlzfh,x.qsdb,x.bdedhdl,c.nhbz,x.zhssdl,x.yssdbdl " +
				"FROM CBZD C, CBZDXX X, CBZDXF F, ZHANDIAN Z WHERE Z.ID = C.ZDID AND C.ID = X.WJID(+) AND X.ID = F.BWJID(+)" +
				" and c.id ="+id+" AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID ="+loginId+")))";
		
		DataBase db = new DataBase();
      ResultSet rs = null;
      Zdinfo form=new Zdinfo();
		try {
			
          System.out.println("完成整改提交待审--超标站点市审核详细信息查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				form.setId(rs.getString(1)!=null ? rs.getString(1):"");
				form.setZdid(rs.getString(2)!=null ? rs.getString(2):"");
				form.setZdname(rs.getString(3)!=null ? rs.getString(3):"");
				form.setShi(rs.getString(4)!=null ? rs.getString(4):"");
				form.setXian(rs.getString(5)!=null ? rs.getString(5):"");
				form.setXiaoqu(rs.getString(6)!=null ? rs.getString(6):"");
				form.setBzpld(rs.getString(7)!=null ? rs.getString(7):"");
				form.setCbsj(rs.getString(8)!=null ? rs.getString(8):"");
				form.setSjlrr(rs.getString(9)!=null ? rs.getString(9):"");
				form.setSjlrsj(rs.getString(10)!=null ? rs.getString(10):"");
				form.setZgyq(rs.getString(11)!=null ? rs.getString(11):"");
				
				form.setG2(rs.getString(12)!=null ? rs.getString(12):"");
				form.setG3(rs.getString(13)!=null ? rs.getString(13):"");
				form.setZp(rs.getString(14)!=null ? rs.getString(14):"");
				form.setZs(rs.getString(15)!=null ? rs.getString(15):"");
				form.setChangjia(rs.getString(16)!=null ? rs.getString(16):"");
				form.setJztype(rs.getString(17)!=null ? rs.getString(17):"");
				form.setShebei(rs.getString(18)!=null ? rs.getString(18):"");
				form.setBbu(rs.getString(19)!=null ? rs.getString(19):"");
				form.setRru(rs.getString(20)!=null ? rs.getString(20):"");
				form.setGxgwsl(rs.getString(21)!=null ? rs.getString(21):"");
				form.setJdxgxsb(rs.getString(22)!=null ? rs.getString(22):"");
				form.setJydgxsb(rs.getString(23)!=null ? rs.getString(23):"");
				form.setJg2xq(rs.getString(24)!=null ? rs.getString(24):"");
				form.setJg3sq(rs.getString(25)!=null ? rs.getString(25):"");
				
				form.setXg2(rs.getString(26)!=null ? rs.getString(26):"");
				form.setXg3(rs.getString(27)!=null ? rs.getString(27):"");
				form.setXzp(rs.getInt(28));
				form.setXzs(rs.getInt(29));
				form.setXchangjia(rs.getString(30)!=null ? rs.getString(30):"");
				form.setXjztype(rs.getString(31)!=null ? rs.getString(31):"");
				form.setXshebei(rs.getString(32)!=null ? rs.getString(32):"");
				form.setXbbu(rs.getInt(33));
				form.setXrru(rs.getInt(34));
				form.setXgxgwsl(rs.getInt(35));
				form.setDxgxsb(rs.getString(36)!=null ? rs.getString(36):"");
				form.setYdgxsb(rs.getString(37)!=null ? rs.getString(37):"");
				form.setG2xq(rs.getString(38)!=null ? rs.getString(38):"");
				form.setG3sq(rs.getString(39)!=null ? rs.getString(39):"");
				
				form.setSjyqwcsj(rs.getString(40)!=null ? rs.getString(40):"");
				form.setDbds(rs.getString(41)!=null ? rs.getString(41):"");
				form.setKGDYZLFH(rs.getString(42)!=null ? rs.getString(42):"");
				form.setYDGXSBZLFH(rs.getString(43)!=null ? rs.getString(43):"");
				form.setDXGXSBZLFH(rs.getString(44)!=null ? rs.getString(44):"");
				form.setGYGXSBZLFH(rs.getString(45)!=null ? rs.getString(45):"");
				form.setZYYGSBZLFH(rs.getString(46)!=null ? rs.getString(46):"");
				form.setYyfx(rs.getString(47)!=null ? rs.getString(47):"");
				form.setCszrr(rs.getString(48)!=null ? rs.getString(48):"");
				form.setXfzgyq(rs.getString(49)!=null ? rs.getString(49):"");
				form.setWcsj(rs.getString(50)!=null ? rs.getString(50):"");
				form.setWcsm(rs.getString(51)!=null ? rs.getString(51):"");
				form.setQxwcsj(rs.getString(52)!=null ? rs.getString(52):"");
				form.setZgzrr(rs.getString(53)!=null ? rs.getString(53):"");
				form.setTdyy(rs.getString(54)!=null ? rs.getString(54):"");
				form.setFKSJ(rs.getString(55)!=null ? rs.getString(55):"");
				form.setSjyqwtgyy(rs.getString(56)!=null ? rs.getString(56):"");
				form.setSjyqwcsj(rs.getString(57)!=null ? rs.getString(57):"");
				form.setDgpch(rs.getString(58)!=null ? rs.getString(58):"");
				form.setYppch(rs.getString(59)!=null ? rs.getString(59):"");
				form.setBt(rs.getString(60)!=null ? rs.getString(60):"");
				form.setWcsj1(rs.getString(61)!=null ? rs.getString(61):"");
				form.setSjname(rs.getString(62)!=null ? rs.getString(62):"");
				form.setQxname(rs.getString(63)!=null ? rs.getString(63):"");
				form.setSjpath(rs.getString(64)!=null ? rs.getString(64):"");
				form.setQxname1(rs.getString(65)!=null ? rs.getString(65):"");
				form.setZlzfh(rs.getString(66)!=null ? rs.getString(66):"");
				form.setJlzfh(rs.getString(67)!=null ? rs.getString(67):"");
				form.setQsdb(rs.getString(68)!=null ? rs.getString(68):"");
				form.setBdhdl(rs.getString(69)!=null ? rs.getString(69):"");
				form.setNhbz(rs.getString(70)!=null ? rs.getString(70):"");
				form.setZhssdl(rs.getString(71)!=null?rs.getString(71):"");
				form.setYssdbdl(rs.getString(72)!=null?rs.getString(72):"");
			}
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return form;
	}
	
}
