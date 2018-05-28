package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.tongjichaxu.javabean.cbJzBean;
import com.noki.util.CTime;
import com.noki.util.MD5;

public class CbzdQuery {
	public synchronized List<Zdinfo> seachDatada(String  bean,String loginId){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr="";
		try{
			fzzdstr = getFuzeZdid(db,loginId);
			String sql = 
				"SELECT ZD.ID IDD,CD.ID,CD.ZDID,CX.SJXF,CD.ZDNAME,CD.SHI,CD.XIAN,CD.XIAOQU,CD.CBSJ,CD.CBDL,CD.CBBL,CX.CSMS,CX.YYFX,CX.CSZRR,CX.SHIJXF FROM CBZD CD, ZHANDIAN ZD,CBZDXX CX WHERE CD.ID = CX.WJID AND CD.ZDID = ZD.id "+bean+" "+
			    " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+") ORDER BY ZD.JZNAME";
			System.out.println("超标站点整改sql："+sql);
			db.connectDb();
			rs = db.queryAll(sql);
			
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setId(rs.getString("ID"));
				ret.setZdid((rs.getString("ZDID")+"").toString().trim());
				ret.setZdname(rs.getString("ZDNAME"));
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setCbdl(rs.getString("CBDL"));
				ret.setCbsj(rs.getString("CBSJ"));
				ret.setCbbl(rs.getString("CBBL"));
				ret.setCsms(rs.getString("CSMS"));
				ret.setYyfx(rs.getString("YYFX"));
				ret.setCszrr(rs.getString("CSZRR"));
				ret.setSjpd(rs.getString("SJXF"));
			    ret.setIdd(rs.getString("IDD"));
			    ret.setShijxf(rs.getString("SHIJXF"));
				retList.add(ret);
			}
		}catch(Exception e){
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
		return retList;
	}
	 //检索数据
	public synchronized List<Zdinfo> seachData(String  bean,String loginId){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr="";
		try{
			fzzdstr = getFuzeZdid(db,loginId);
			String sql = 
				"SELECT ZD.ID IDD,CD.ID,CD.ZDID,CD.ZDNAME,CD.SHI,CD.XIAN,CD.XIAOQU,CD.CBSJ,CD.SJPD,CD.CBDL,CD.SJDL*zd.dianfei,CD.BZPLD,CD.DSQS,CD.NHBZ,CD.SCB FROM CBZD CD, ZHANDIAN ZD, SCB SB WHERE CD.ZDID = ZD.id AND CD.ZDID=SB.ZDID "+bean+" "+
			    " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+") ORDER BY ZD.JZNAME";
			System.out.println("超标基站查询sql："+sql);
			db.connectDb();
			rs = db.queryAll(sql);
			
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setId(rs.getString("ID"));
				ret.setZdid((rs.getString("ZDID")+"").toString().trim());
				ret.setZdname(rs.getString("ZDNAME"));
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setCbdl(rs.getString("CBDL"));
				ret.setCbsj(rs.getString("CBSJ"));
				ret.setSjpd(rs.getString("SJPD"));
				ret.setIdd(rs.getString("IDD"));
	           // ret.setSjlrr(rs.getString("ENTRYPENSONNEL"));
	            // ret.setSjlrsj(rs.getString("ENTRYTIME"));

				ret.setDsqs(rs.getString("DSQS"));
				
				ret.setNhbz(rs.getString("NHBZ"));//当月省定标电量,能耗标准
				ret.setScb(rs.getString("SCB"));//站点生产标（省定标电量（不含空调））
				retList.add(ret);
			}
		}catch(Exception e){
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
		return retList;
	}
	public synchronized List<Zdinfo> seachDatag2(String  bean){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		// SELECT cd.zdid,cd.zdname,cd.shi,cd.xian,cd.cbsj,cd.bzpld cbbl,cd.lrr,cd.lrsj FROM cbzd cd 
		String sql = "SELECT cd.ydgxsb,cd.dxgxsb,cd.g2xq,cd.g3sq,cd.zdid,cd.zdname,cd.shi,cd.xian,xiaoqu,cd.cbsj,cd.cbbl cbbl,CD.G2,CD.G3,CD.CHANGJIA,CD.ZP,CD.ZS,CD.JZTYPE,CD.SHEBEI,CD.BBU,CD.RRU,CD.YDSHEBEI,CD.GXGWSL FROM cbzd cd where cd.id='"+bean+"'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("超标基站查询sqlAAA："+sql);
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setZdid((rs.getString("ZDID")+"").toString().trim());
				ret.setZdname(rs.getString("ZDNAME"));
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setCbdl(rs.getString("CBBL"));
				ret.setCbsj(rs.getString("CBSJ"));
	           // ret.setSjlrr(rs.getString("ENTRYPENSONNEL"));
				// CD.G2,CD.G3,CD.CHANGJIA,CD.ZP,CD.ZS,CD.JZTYPE,CD.SHEBEI,CD.BBU,CD.RRU,CD.YDSHEBEI,CD.GXGWSL
				ret.setG2(rs.getString("G2"));
				ret.setG3(rs.getString("G3"));
				ret.setChangjia(rs.getString("CHANGJIA"));
				ret.setZp(rs.getString("ZP"));
				ret.setZs(rs.getString("ZS"));
				ret.setJztype(rs.getString("JZTYPE"));
				ret.setShebei(rs.getString("SHEBEI"));
				ret.setBbu(rs.getString("BBU"));
				ret.setRru(rs.getString("RRU"));
				ret.setYdshebei(rs.getString("YDSHEBEI"));
				ret.setGxgwsl(rs.getString("GXGWSL"));
				ret.setYdgxsb(rs.getString("YDGXSB"));
				ret.setDxgxsb(rs.getString("DXGXSB"));
				ret.setG2xq(rs.getString("G2XQ"));
				ret.setG3sq(rs.getString("G3SQ"));
	           // ret.setSjlrsj(rs.getString("ENTRYTIME"));
				retList.add(ret);
			}
		}catch(Exception e){
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
		return retList;
	}
	public synchronized List<Zdinfo> seachData1(String zdid){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		int i=zdid.split(",").length;//长度
		String a[]=zdid.split(",");//组成数组
		String idda=""; 

		for(int d=1;d<=i;d++){
			idda=idda+a[d-1]+",";
		if((i/500==0&&d==i)||((i/500>=1)&&(i/500)>=(d/500)&&(d%500==0))||((d/500==i/500)&&(d==i))){	
			idda=idda.substring(0,idda.length()-1);
			
		String sql = "SELECT CD.ID,CD.ZDID,CD.ZDNAME,CD.SHI,CD.XIAN,CD.XIAOQU,CD.CBSJ,CD.SJPD,CD.CBDL, (SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND T.TYPE = 'ZDSX') AS PROPERTY, (CASE WHEN ZD.SHI IS NOT NULL THEN  (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) ELSE '' END) SHICODE FROM CBZD CD, ZHANDIAN ZD WHERE CD.ZDID = ZD.id AND CD.ID in ("+idda+")  ORDER BY ZD.SHI";
		DataBase db = new DataBase();
		ResultSet rs = null;
		//System.out.println("超标基站查询sql2："+sql);
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setId(rs.getString("ID"));
				ret.setZdid((rs.getString("ZDID")+"").toString().trim());
				ret.setZdname(rs.getString("ZDNAME"));
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setCbdl(rs.getString("CBDL"));
				ret.setCbsj(rs.getString("CBSJ"));
				ret.setSjpd(rs.getString("SJPD"));
				ret.setJztype(rs.getString("PROPERTY"));
				ret.setShicode(rs.getString("SHICODE"));
				retList.add(ret);
			}
		}catch(Exception e){
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
		idda="";
		}
	}
		return retList;
	
	}
	public int  seachDataMax(){
		int i=0;
		String sql = " SELECT MAX(l.lch) FROM cbzdxx l ";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("超标基站查询sql2："+sql);
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
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
		return i;
	}
	public String  chehui(String id){
		String msg="撤单失败！";
		String sql ="",sql1="";
		DataBase db = new DataBase();
		ResultSet rs = null;
		
		try{
			sql1=" UPDATE CBZD CC SET CC.SJPD='0' WHERE CC.ID IN ("+id+")";
		sql	= " DELETE FROM CBZDXX CX WHERE CX.WJID IN ("+id+")";
		System.out.println("修改派单："+sql1);
		System.out.println("修改派单："+sql);
			db.connectDb();
	        db.update(sql1);
			db.update(sql);
		     msg="撤单成功！";
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return msg;
	}
	public String  chehui2(List<Zdinfo> ls){
		String msg="撤单失败！";
		String sql ="",sql1="";
		DataBase db = new DataBase();
		ResultSet rs = null;
		if(ls!=null){
			for(Zdinfo lst:ls){
		try{
			sql1=" UPDATE CBZDXX CD SET CD.SJXF='0' WHERE CD.ID IN ("+lst.getId()+")";
		sql	= " DELETE FROM CBZDXF CX WHERE CX.BWJID IN ("+lst.getId()+")";
		System.out.println("修改派单："+sql1);
		System.out.println("修改派单："+sql);
			db.connectDb();
	        db.update(sql1);
			db.update(sql);
		     msg="撤单成功！";
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
			}
		}
		return msg;
	}
	public synchronized List<Zdinfo> seachData3(String zdid){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		
		String sql = "SELECT CX.ID,CX.DGPCH FROM CBZDXX CX WHERE CX.WJID in ("+zdid+") ";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("超标基站查询sql2："+sql);
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setId(rs.getString("ID"));
				ret.setLch(rs.getString("DGPCH"));
				//ret.setZdid((rs.getString("ZDID")+"").toString().trim());
				//ret.setZdname(rs.getString("ZDNAME"));
				//ret.setShi(rs.getString("SHI"));
			//	ret.setXian(rs.getString("XIAN"));
			//	ret.setXiaoqu(rs.getString("XIAOQU"));
			//	ret.setCbdl(rs.getString("CBDL"));
			//	ret.setCbsj(rs.getString("CBSJ"));
			//	ret.setSjpd(rs.getString("SJPD"));
	          //  ret.setSjlrr(rs.getString("ENTRYPENSONNEL"));
	           //  ret.setSjlrsj(rs.getString("ENTRYTIME"));
				retList.add(ret);
			}
		}catch(Exception e){
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
		return retList;
	}
	public synchronized List<Zdinfo> seachData8(String zdid){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		
		String sql = "SELECT CX.ZDID FROM CBZD CX WHERE CX.ID in ("+zdid+") ";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("超标基站查询sql2："+sql);
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setId(rs.getString("ZDID"));
				
				//ret.setZdid((rs.getString("ZDID")+"").toString().trim());
				//ret.setZdname(rs.getString("ZDNAME"));
				//ret.setShi(rs.getString("SHI"));
			//	ret.setXian(rs.getString("XIAN"));
			//	ret.setXiaoqu(rs.getString("XIAOQU"));
			//	ret.setCbdl(rs.getString("CBDL"));
			//	ret.setCbsj(rs.getString("CBSJ"));
			//	ret.setSjpd(rs.getString("SJPD"));
	          //  ret.setSjlrr(rs.getString("ENTRYPENSONNEL"));
	           //  ret.setSjlrsj(rs.getString("ENTRYTIME"));
				retList.add(ret);
			}
		}catch(Exception e){
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
		return retList;
	}
	public synchronized int seachData2(String zdid){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		int i=0;
		
		String sql = "SELECT count(cd.id) ts FROM CBZD CD, ZHANDIAN ZD WHERE CD.ZDID = ZD.id AND CD.ID in ("+zdid+")  ORDER BY ZD.SHI";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("超标基站查询sql2："+sql);
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				i=Integer.parseInt(rs.getString("ts"));
			}
		}catch(Exception e){
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
		return i;
	}
	public synchronized List<Zdinfo> seachShowzdinfo(String zdid,String month){
		List<Zdinfo> retList=new ArrayList<Zdinfo>();
		
	String sql1=		
		" SELECT dl.*,rownum  FROM (SELECT DISTINCT CD.G2,CD.G3,CD.ZP,CD.ZS,CD.CHANGJIA,CD.JZTYPE,CD.SHEBEI,CD.BBU,CD.RRU,CD.YDSHEBEI,CD.GXGWSL,"+
		" (CASE WHEN LENGTH(DV.LASTDATETIME) = 10 AND LENGTH(DV.THISDATETIME) = 10 "+
		" THEN NVL(CEIL(DV.THISDATETIME - DV.LASTDATETIME), 0) END) ZQ,"+
		" DV.LASTDEGREE QM, DV.THISDEGREE ZM,to_char(MAX(DV.LASTDATETIME),'yyyy-mm-dd') SCCBSJ,to_char(MAX(DV.THISDATETIME),'yyyy-mm-dd') BCCBSJ,DV.BLHDL HDL,DF.ACTUALPAY FY,to_char(DF.ACCOUNTMONTH,'yyyy-mm') BZYF "+
		" FROM CBZD CD, ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW DV, DIANFEIVIEW DF "+
		" WHERE ZD.ID = DB.ZDID AND CD.ZDID = ZD.ID "+
		" AND DB.ZDID = CD.ZDID AND DB.DBID = DV.AMMETERID_FK AND DV.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK " +
		"AND CD.CBSJ = '"+month+"' AND CD.ZDID = '"+zdid+"' AND to_char(df.accountmonth,'yyyy-mm')>='2013-01'  AND to_char(df.accountmonth,'yyyy-mm')<='"+month+"' "+
		" GROUP BY CD.G2,CD.G3, CD.ZP, CD.ZS, CD.CHANGJIA, CD.JZTYPE, CD.SHEBEI, CD.BBU,CD.RRU,CD.YDSHEBEI,CD.GXGWSL,DV.LASTDEGREE,DV.THISDEGREE,DV.LASTDATETIME,"+
		" DV.THISDATETIME, DV.BLHDL,DF.ACTUALPAY, DF.ACCOUNTMONTH  ORDER BY BZYF DESC) DL WHERE rownum<4";
		
		
		// 2G设备	3G设备	载频数量	载扇数量	厂家	基站类型	设备编码	BBU数量	RRU数量	移动设备数量	共享固网设备数量
	// 起码	止码	上次抄表时间	本次抄表时间	周期	用电量	费用	 报账月份

		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("站点名称链接详细信息sql："+sql1);
		try{
			db.connectDb();
			rs = db.queryAll(sql1);
			while(rs.next()){
				Zdinfo ret = new Zdinfo();
				ret.setG2(rs.getString("G2"));
				ret.setG3(rs.getString("G3"));
				ret.setZp(rs.getString("ZP"));
				ret.setZs(rs.getString("ZS"));
				ret.setChangjia(rs.getString("CHANGJIA"));
				ret.setJztype(rs.getString("JZTYPE"));
				ret.setShebei(rs.getString("SHEBEI"));
				ret.setBbu(rs.getString("BBU"));
				ret.setRru(rs.getString("RRU"));
				ret.setYdshebei(rs.getString("YDSHEBEI"));
				ret.setGxgwsl(rs.getString("GXGWSL"));
				ret.setQm(rs.getString("QM"));
				ret.setZm(rs.getString("ZM"));
				ret.setSccbsj(rs.getString("SCCBSJ"));
				ret.setBccbsj(rs.getString("BCCBSJ"));
				ret.setZq(rs.getString("ZQ"));
				ret.setFy(rs.getString("FY"));
				ret.setBzyf(rs.getString("BZYF"));
				ret.setYdl(rs.getString("HDL"));
				retList.add(ret);
			}
		}catch(Exception e){
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
		return retList;
	}
	
	
	
	  public synchronized String spd(String id,String accountname) {
		    String msg = "派单失败！";
		    
		    DataBase db = new DataBase();
		    int i=0;
		    try {
		      db.connectDb();
		      String sql ="UPDATE CBZD CD SET CD.DSQS='0',CD.SJPD='1',CD.SJPDSJ=SYSDATE,CD.SJPDR='"+accountname+"' WHERE CD.ID  in ("+id+")";
		      System.out.println(sql.toString());
		      msg = "派单失败！";
		        i=db.update(sql);
		      if(i!=0){
			     msg = "派单成功！";  
			  }
		    }
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
	  public synchronized String spd1(String id,String accountname) {
		    String msg = "派单失败！";
		    DataBase db = new DataBase();
		    int i=0;
		    int m=0;
		    int j=id.split(",").length;//长度
			String a[]=id.split(",");//组成数组
			String idda="";
			StringBuffer sql=new StringBuffer();
			for(int d=0;d<a.length;d++){
				i++;
		  		idda=a[d]+",";
		    			idda=idda.substring(0,idda.length()-1);
		    			
		       sql.append("UPDATE CBZD CD SET CD.DSQS='0',CD.SJPD='1',CD.DSPD='0',SJPDSJ=SYSDATE,SJPDR='"+accountname+"' WHERE CD.ID  in ("+idda+")の");
		      //System.out.println(sql.toString());
			} 
		      try {
		    	  System.out.println("超标站点查询更新sql--："+sql);
		    	  System.out.println("单独更新条数:"+i);
					db.connectDb();
					 msg = "派单失败！";
				       // i=db.update(sql);
					 db.updateBatchDr(sql.toString().split("の"));//批量处理大量数据插入
				     m=1;
					 
				}		
			    	
		    
		    
		      
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	  
			  if(m!=0){
				     msg = "派单成功！";  
				  }
		    return msg;
		  }
	  public synchronized String spd3(String id,String accountname) {
		    String msg = "派单失败！";
		    DataBase db = new DataBase();
		    int i=0;
	 
	
		    try {
		      db.connectDb();
		      String sql ="UPDATE CBZDXX CD SET CD.SJXF='1',SJXFSJ=SYSDATE,SJXFR='"+accountname+"' WHERE CD.WJID  in ("+id+")";
		      System.out.println(sql.toString());
		      msg = "派单失败！";
		        i=db.update(sql);
		        System.out.println("单独更新L:"+i);
		      if(i!=0){
			     msg = "派单成功！";  
			  }
		    }
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		public synchronized String addzdxx(List<Zdinfo> ls) {
			// birthday = birthday.length()>0?birthday:null;
				String lch="1000";
				  UUID uuid = UUID.randomUUID();
                String l=lch+uuid.toString().substring(0, 2);
			String msg = "保存数据失败！请重试或与管理员联系！";
			MD5 md = new MD5();
			CTime ctime = new CTime();
			DataBase db = new DataBase();
				// idStr.append(ammeterdegreeid.get(i)+",");
				//update="update ammeterdegrees set feesbz='1',manualauditstatus='1'  where ammeterdegreeid in("+idStr.toString().substring(0,idStr.length()-1)+")";
				StringBuffer sql = new StringBuffer();
				if(ls!=null){
				for(Zdinfo z:ls){
				sql.append("INSERT INTO CBZDXX(WJID,ZDID,CBSJ)");
				sql.append(" VALUES('" + z.getId() + "'");
				sql.append(",'" + z.getZdid() + "','"+z.getCbsj()+"') ");
				System.out.println("sql插入："+sql);
				  try {
						db.connectDb();
						db.update(sql.toString());
						msg = "派单成功！";
					} catch (DbException e) {
						try{
							db.rollback();
						}catch (Exception dee) {
							dee.printStackTrace();
						}
						e.printStackTrace();
					}finally{
						try{
							db.close();
						}catch(Exception a){
							a.printStackTrace();
						}
					}
		}}
				return msg;
}
		public synchronized String addzdxx2(List<Zdinfo> ls,int i) {
			// birthday = birthday.length()>0?birthday:null;
				String lch="1000";
				  UUID uuid = UUID.randomUUID();
                String l=lch+uuid.toString().substring(0, 2);
			String msg = "保存数据失败！请重试或与管理员联系！";
			MD5 md = new MD5();
			CTime ctime = new CTime();
			DataBase db = new DataBase();
				// idStr.append(ammeterdegreeid.get(i)+",");
				//update="update ammeterdegrees set feesbz='1',manualauditstatus='1'  where ammeterdegreeid in("+idStr.toString().substring(0,idStr.length()-1)+")";
				StringBuffer sql = new StringBuffer();
				String sqla="";
				// for(int b=0; b<i;b++){
				if(ls!=null){
				for(Zdinfo z:ls){
					sqla="INSERT INTO CBZDXX(WJID,ZDID,CBSJ) VALUES('"+z.getId()+"','"+z.getZdid()+"','"+z.getCbsj()+"')";
				sql.append("INSERT INTO CBZDXX(WJID,ZDID,CBSJ)");
				sql.append(" VALUES('" + z.getId() + "'");
				sql.append(",'" + z.getZdid() + "','"+z.getCbsj()+"') ");
				System.out.println("sqla插入："+sqla);
				  try {
						db.connectDb();
						db.update(sqla.toString());
						msg = "派单成功！";
					} catch (DbException e) {
						e.printStackTrace();
					}finally{
						try{
							db.close();
						}catch(Exception a){
							a.printStackTrace();
						}
					}
	}}
				return msg;
}
		//负责站点条件
		private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
			ResultSet rs = null;

			rs = db
					.queryAll("select begincode,endcode from per_zhandian where accountid='"
							+ loginid
							+ "' and begincode is not null and endcode is not null");
			String cxtj = new String("");
			while (rs.next()) {			
					cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
							+ "' and zdcode <='" + rs.getString(2) + "')";			
			}
			rs.close();
			db.close();
	    System.out.println("负责站点条件："+cxtj);
			return cxtj.toString();
		}
		
		public String getnamea(String code){
			DataBase db = new DataBase();
			String msg="";
		ResultSet rs=null;
			String sql="";
			sql="SELECT I.NAME FROM INDEXS I WHERE I.CODE='"+code+"' AND I.TYPE='cj'";
			try{
			db.connectDb();
			rs=db.queryAll(sql);
			if(rs.next()){
			msg=rs.getString("NAME");
			}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					rs.close();
					db.close();
				}catch(Exception a){
					a.printStackTrace();
				}
				
			}
			return msg;
		}
		public String getnameab(String code){
			DataBase db = new DataBase();
			String msg="";
		ResultSet rs=null;
			String sql="";
			sql="SELECT I.NAME FROM INDEXS I WHERE I.CODE='"+code+"' AND I.TYPE='jzlx2'";
			try{
			db.connectDb();
			rs=db.queryAll(sql);
			if(rs.next()){
			msg=rs.getString("NAME");
			}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					rs.close();
					db.close();
				}catch(Exception a){
					a.printStackTrace();
				}
				
			}
			return msg;
		}
		public String getnameabc(String code){
			DataBase db = new DataBase();
			String msg="";
		ResultSet rs=null;
			String sql="";
			sql="SELECT I.NAME FROM INDEXS I WHERE I.CODE='"+code+"' AND I.TYPE='sblx'";
			try{
				System.out.println("sql:"+sql);
			db.connectDb();
			rs=db.queryAll(sql);
			if(rs.next()){
			msg=rs.getString("NAME");
			}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					rs.close();
					db.close();
				}catch(Exception a){
					a.printStackTrace();
				}
				
			}
			return msg;
		}
/**
 * 超标站点查询--生成超标站点单据
 * @param chooseIdStr:选择的站点的zdid
 * @param zdsx:条件--站点属性
 * @param bzmonth：条件--报账月份
 * @return：是否执行成功
 */
		public synchronized String getCbzdxx(String chooseIdStr,String zdsx,String bzmonth) {
		    String msg = "生成超标站点单据失败！";
		    DataBase db = new DataBase();
		    int i=0; 
		    
		    String str = "";
		    if(zdsx!=null||zdsx!=""||"".equals(zdsx)){
		    	str = " AND ZD.PROPERTY = '"+zdsx+"'";
		    }
		    try {
		      db.connectDb();
		      //现用sql
		      String sql1 ="INSERT INTO CBZD(ID,SHI,XIAN,XIAOQU,ZDNAME,ZDID,G2,G3,CHANGJIA,ZP,ZS,JZTYPE,SHEBEI,BBU,RRU,YDSHEBEI," +
	      		"GXGWSL,NHBZ,CHANQUAN,SJDL,CBDL,BZPLD,ZLFH,JLFH,EDHDL,CBSJ,SJPD,SJPDSJ,DSQS,DSQSSJ,DSPD,DSPDSJ,QXQS,QXQSSJ," +
	      		"QXPD,QXPDSJ,QXTJSH,SHIJSH,SHIJSHSJ,SHENGJSH,SHENGJSHSJ,GXXX,SJPDR,DSQSR,DSPDR,QXQSR,QXPDR,QXTJR,SHENGJSHR," +
	      		"SHIJSHR,SJLRR,SJLRSJ,QXTJSHSJ,TDYY,FKSJ,G2XQ,G3SQ,YDGXSB,DXGXSB,SJTDYY,PROPERTY,scb,CBBL) SELECT '',S.AGNAME AS SHI,X.AGNAME AS XIAN," +
	      		"XQ.AGNAME AS XIAOQU," +
	      		"ZD.JZNAME,ZD.ID,ZD.G2,ZD.G3,CJ.NAME AS CHANGJIA,ZD.ZPSL,ZD.ZSSL,JZLX.NAME AS JZLX,SB.NAME AS SHEBEI," +
	      		"ZD.BBU,ZD.RRU,ZD.YDSHEBEI,ZD.GXGWSL,ZDDL.BZ,'',TRUNC(ZDDL.DL,2),TRUNC(TO_NUMBER(ZDDL.DL/ZDDL.hbzq-zddl.scb),2) AS CBDL," +
	      		"TRUNC((CASE WHEN ZDDL.BZZ<>0 THEN (ZDDL.DBYDL - ZDDL.BZZ) / ZDDL.BZZ END),4) AS BL," +
	      		 "DECODE(ZDDL.ZLFH, '', ZD.ZLFH, ZDDL.ZLFH),DECODE(ZDDL.JLFH, '', ZD.JLFH, ZDDL.JLFH),DECODE(ZDDL.EDHDL, '', ZD.EDHDL, ZDDL.EDHDL),"+
	      		"'"+bzmonth+"','0','','0','','0','','0','','0','','0','0','','0','','0','','','','','',''," +
	      		"'','','','','','','',ZD.G2XQS,ZD.G3SQSL,ZD.YDGXSBSL,ZD.DXGXSBSL,'',ZD.PROPERTY,zddl.scb,ZDDL.CBBL FROM ZHANDIAN ZD " +
	      		"LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE" +
	      		" LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE LEFT JOIN INDEXS JZLX ON ZD.JZLX = JZLX.CODE" +
	      		" AND JZLX.TYPE = 'jzlx2' LEFT JOIN INDEXS SB ON ZD.SHEBEI = SB.CODE AND SB.TYPE = 'sblx' " +
	      		"LEFT JOIN INDEXS GX ON ZD.GXXX = GX.CODE AND GX.TYPE = 'gxxx' LEFT JOIN INDEXS CJ ON ZD.CHANGJIA=CJ.CODE AND CJ.TYPE='cj'," +
	      		" (SELECT AB.ZDID, AB.DL,AB.SCB,AB.HBZQ,AB.BZ,AB.DBYDL,AB.BZZ,TRUNC((CASE WHEN AB.BZZ<>0 THEN (AB.DL-AB.BZZ)/AB.BZZ END),4) CBBL,AB.EDHDL,AB.JLFH,AB.ZLFH  " +
	      		"FROM (SELECT ZD.ID ZDID, AM.SCB,SUM(AM.HBZQ) HBZQ,SUM(AM.BLHDL) DL, SUM(AM.BZZ)BZZ, SUM(AM.BLHDL) DBYDL, DECODE(EF.QSDBDL, '0', '0.01', EF.QSDBDL) BZ, " +
	      		" EF.EDHDL,EF.JLFH, EF.ZLFH FROM ZHANDIAN ZD, DIANBIAO  DB,AMMETERDEGREES AM, ELECTRICFEES   EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK " +
	      		"AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND DB.DBYT = 'dbyt01' AND EF.MANUALAUDITSTATUS = '2' AND ZD.STATIONTYPE<>'StationType41' " +
	      		"AND ZD.JZNAME NOT LIKE '%回收%' AND to_char(EF.ACCOUNTMONTH, 'yyyy-mm') = '"+bzmonth+"' AND ZD.ID IN("+chooseIdStr+") GROUP BY ZD.ID,AM.SCB,EF.QSDBDL,EF.EDHDL,EF.JLFH, EF.ZLFH) AB) ZDDL " +
	      		" WHERE to_char(ZD.ID) = ZDDL.ZDID AND ZD.ID IN ("+chooseIdStr+")";
		      //以前规则的sql 已经不用
		      String sql3 ="INSERT INTO CBZD(ID,SHI,XIAN,XIAOQU,ZDNAME,ZDID,G2,G3,CHANGJIA,ZP,ZS,JZTYPE,SHEBEI,BBU,RRU,YDSHEBEI," +
		      		"GXGWSL,NHBZ,CHANQUAN,SJDL,CBDL,BZPLD,ZLFH,JLFH,EDHDL,CBSJ,SJPD,SJPDSJ,DSQS,DSQSSJ,DSPD,DSPDSJ,QXQS,QXQSSJ," +
		      		"QXPD,QXPDSJ,QXTJSH,SHIJSH,SHIJSHSJ,SHENGJSH,SHENGJSHSJ,GXXX,SJPDR,DSQSR,DSPDR,QXQSR,QXPDR,QXTJR,SHENGJSHR," +
		      		"SHIJSHR,SJLRR,SJLRSJ,QXTJSHSJ,TDYY,FKSJ,G2XQ,G3SQ,YDGXSB,DXGXSB,SJTDYY,PROPERTY,scb,CBBL) SELECT '',S.AGNAME AS SHI,X.AGNAME AS XIAN," +
		      		"XQ.AGNAME AS XIAOQU," +
		      		"ZD.JZNAME,ZD.ID,ZD.G2,ZD.G3,CJ.NAME AS CHANGJIA,ZD.ZPSL,ZD.ZSSL,JZLX.NAME AS JZLX,SB.NAME AS SHEBEI," +
		      		"ZD.BBU,ZD.RRU,ZD.YDSHEBEI,ZD.GXGWSL,ZDDL.BZ,'',TRUNC(ZDDL.DL,2),TRUNC(TO_NUMBER(ZDDL.DL/ZDDL.hbzq-zddl.scb),2) AS CBDL," +
		      		"TRUNC((CASE WHEN ZDDL.BZZ<>0 THEN (ZDDL.DBYDL - ZDDL.BZZ) / ZDDL.BZZ END),4) AS BL,ZD.ZLFH," +
		      		"ZD.JLFH,ZD.EDHDL,'"+bzmonth+"','0','','0','','0','','0','','0','','0','0','','0','','0','','','','','',''," +
		      		"'','','','','','','',ZD.G2XQS,ZD.G3SQSL,ZD.YDGXSBSL,ZD.DXGXSBSL,'',ZD.PROPERTY,zddl.scb,ZDDL.CBBL FROM ZHANDIAN ZD " +
		      		"LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE" +
		      		" LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE LEFT JOIN INDEXS JZLX ON ZD.JZLX = JZLX.CODE" +
		      		" AND JZLX.TYPE = 'jzlx2' LEFT JOIN INDEXS SB ON ZD.SHEBEI = SB.CODE AND SB.TYPE = 'sblx' " +
		      		"LEFT JOIN INDEXS GX ON ZD.GXXX = GX.CODE AND GX.TYPE = 'gxxx' LEFT JOIN INDEXS CJ ON ZD.CHANGJIA=CJ.CODE AND CJ.TYPE='cj' " +
		      		"LEFT JOIN (SELECT AB.ZDID, AB.DL,AB.SCB,AB.HBZQ,AB.BZ,AB.DBYDL,AB.BZZ,TRUNC((CASE WHEN AB.BZZ<>0 THEN (AB.DL-AB.BZZ)/AB.BZZ END),4) CBBL " +
		      		"FROM (SELECT JZ.ZDID,JZ.SCB,JZ.daycount hbzq,DECODE(JZ.dbydl, '', JZ.dbgldl, '0', JZ.dbgldl, JZ.dbydl) DL,JZ.BZZ,JZ.DBYDL," +
		      		"DECODE(JZ.zdqsdbdl, '0', '0.01', JZ.zdqsdbdl) BZ FROM JZXX JZ WHERE 1 = 1 AND SYMONTH = '"+bzmonth+"') AB) ZDDL " +
		      		"ON to_char(ZD.ID) = ZDDL.ZDID WHERE ZD.ID IN ("+chooseIdStr+")";
		     
		      //以前规则的sql 已经不用
		      String sql ="INSERT INTO CBZD(ID,SHI,XIAN,XIAOQU,ZDNAME,ZDID,G2,G3,CHANGJIA,ZP,ZS,JZTYPE,SHEBEI,BBU,RRU,YDSHEBEI," +
		      		"GXGWSL,NHBZ,CHANQUAN,SJDL,CBDL,BZPLD,ZLFH,JLFH,EDHDL,CBSJ,SJPD,SJPDSJ,DSQS,DSQSSJ,DSPD,DSPDSJ,QXQS,QXQSSJ," +
		      		"QXPD,QXPDSJ,QXTJSH,SHIJSH,SHIJSHSJ,SHENGJSH,SHENGJSHSJ,GXXX,SJPDR,DSQSR,DSPDR,QXQSR,QXPDR,QXTJR,SHENGJSHR," +
		      		"SHIJSHR,SJLRR,SJLRSJ,QXTJSHSJ,TDYY,FKSJ,G2XQ,G3SQ,YDGXSB,DXGXSB,SJTDYY,PROPERTY) SELECT '',S.AGNAME AS SHI,X.AGNAME AS XIAN," +
		      		"XQ.AGNAME AS XIAOQU," +
		      		"ZD.JZNAME,ZD.ID,ZD.G2,ZD.G3,CJ.NAME AS CHANGJIA,ZD.ZPSL,ZD.ZSSL,JZLX.NAME AS JZLX,SB.NAME AS SHEBEI," +
		      		"ZD.BBU,ZD.RRU,ZD.YDSHEBEI,ZD.GXGWSL,ZD.QSDBDL,'',TRUNC(ZDDL.DL,2),TRUNC(TO_NUMBER(ZDDL.DL-ZD.QSDBDL),2) AS CBDL," +
		      		"TRUNC(DECODE(TO_NUMBER(ZD.QSDBDL),0,ZDDL.DL,(ZDDL.DL - ZD.QSDBDL) / ZD.QSDBDL),4) AS BL,ZD.ZLFH," +
		      		"ZD.JLFH,ZD.EDHDL,'"+bzmonth+"','0','','0','','0','','0','','0','','0','0','','0','','0','','','','','',''," +
		      		"'','','','','','','',ZD.G2XQS,ZD.G3SQSL,ZD.YDGXSBSL,ZD.DXGXSBSL,'','"+zdsx+"' FROM ZHANDIAN ZD " +
		      		"LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE" +
		      		" LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE LEFT JOIN INDEXS JZLX ON ZD.JZLX = JZLX.CODE" +
		      		" AND JZLX.TYPE = 'jzlx2' LEFT JOIN INDEXS SB ON ZD.SHEBEI = SB.CODE AND SB.TYPE = 'sblx' " +
		      		"LEFT JOIN INDEXS GX ON ZD.GXXX = GX.CODE AND GX.TYPE = 'gxxx' LEFT JOIN INDEXS CJ ON ZD.CHANGJIA=CJ.CODE AND CJ.TYPE='cj' " +
		      		"LEFT JOIN (SELECT ZD.ID,SUM(AM.BLHDL /" +
		      		"(CASE WHEN LENGTH(AM.THISDATETIME) = 10 AND LENGTH(AM.LASTDATETIME) = 10 " +
		      		"THEN TO_CHAR(CEIL(TO_DATE(AM.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AM.LASTDATETIME, 'YYYY-MM-DD')) + 1) END)) / COUNT(ZD.ID) AS DL" +
		      		" FROM ZHANDIAN ZD,DIANBIAO DB,DIANDUVIEW AM,DIANFEIVIEW EF WHERE ZD.ID = DB.ZDID " +
		      		"AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND DB.DBYT = 'dbyt01'" +str+
		      		" AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') = '"+bzmonth+"' AND EF.MANUALAUDITSTATUS > '0' GROUP BY ZD.ID) ZDDL " +
		      		"ON ZD.ID = ZDDL.ID WHERE ZD.ID IN ("+chooseIdStr+")";
		      String sql2="INSERT INTO qsdb(ID,shi,zdid,ymonth,emonth,smonth,simonth,wmonth,lmonth,qmonth,bmonth,jmonth,shimonth,symonth,semonth) SELECT '',ZD.shi,ZD.ID,'0','0','0','0', '0', '0', '0', '0','0', '0', '0', '0' FROM ZHANDIAN ZD WHERE ZD.ID IN ("+chooseIdStr+")";

		      	msg = "生成超标站点单据失败！";
		      	//if("zdsx02".equals(zdsx)||zdsx=="zdsx02"){
		      		System.out.println("超标站点查询--生成超标站点单据1:"+sql1.toString());
		      		i=db.update(sql1);
		      		//System.out.println("超标站点查询--生成超标站点单据2:"+sql2.toString());
		      		//db.update(sql2);
		      	//}else{
		      		//System.out.println("超标站点查询--生成超标站点单据3:"+sql1.toString());
		      		//i=db.update(sql1); 
		      		
		      //	}
		      if(i!=0){
			     msg = "生成超标站点单据成功！";  
			  }	
		    }
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
	}
/**
 * 超标站点查询--删除超标站点重复单据
 * @param bzmonth：条件--报账月份
 * @return：是否执行成功
 */
		public synchronized void delCbzdxx(String bzmonth) {
		    DataBase db = new DataBase();
		    try {
		      db.connectDb();
		      
		      String sqldel = "DELETE FROM CBZD CB WHERE CB.CBSJ = '"+bzmonth+"' AND ROWID IN " +
		      		"(SELECT MAX(ROWID) FROM CBZD CB WHERE CB.CBSJ = '"+bzmonth+"' GROUP BY CB.ZDID HAVING COUNT(*) > 1)" +
		      		" AND CB.ZDID IN (SELECT CB.ZDID FROM CBZD CB WHERE CB.CBSJ = '"+bzmonth+"' GROUP BY CB.ZDID HAVING COUNT(*) > 1)";
			  db.update(sqldel);
			  System.out.println("超标站点查询--删除重复站点信息:"+sqldel.toString());
		    }
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	}
/**
 * 基础数据真实性
 * @param bzmonth：保存月份
 * @param zdsxcw
 * @param scnhft
 * @param mycbgk
 * @param cbzd
 * @param mycbjscb
 * @param lxcb
 * @param lxcbdlzj
 * @param pld
 * @param cbbl
 * @param cbjzzg
 * @return
 */
	public synchronized String setSaveqztz(String bzmonth,String property,String zdsxcw,String scnhft,String mycbgk,
			String cbzd,String mycbjscb,String lxcb,String lxcbdlzj,String pld,
			String cbbl,String cbjzzg,String array,List<cbJzBean> fylist) {
		    String msg = "0";
		    DecimalFormat p=new DecimalFormat("0");
		    DataBase db = new DataBase();
		    
		    String sql1 = "DELETE FROM QZTZ Q WHERE Q.BZMONTH='"+bzmonth+"'";
		    String sql2 = "INSERT INTO QZTZ Q (BZMONTH,SXCWQZ,FTQZ,CBGKQZ,CBZDQZ,JSDLCBQZ,LXCBQZ,LXDLZJQZ,PLDTZ,CBBLTZ,CBJZXGTZ) " +
		    		"VALUES('"+bzmonth+"','"+zdsxcw+"','"+scnhft+"','"+mycbgk+"','"+cbzd+"','"+mycbjscb+"','"+lxcb+"','"+lxcbdlzj+"','"+pld+"','"+cbbl+"','"+cbjzzg+"')";
		    String sql3 = "DELETE FROM KHXX Q WHERE Q.BZMONTH='"+bzmonth+"' AND Q.PROPERTY='"+property+"'";
		    
		    int i=0; 
		    try {
		      db.connectDb();
		      db.update(sql1);
		      System.out.println("基础数据真实性--删除本月份保存过的数据:"+sql1.toString());
		      db.update(sql3);
		      System.out.println("基础数据真实性--删除本月份保存过的数据详细信息:"+sql3.toString());
      		  i=db.update(sql2);
      		  System.out.println("基础数据真实性--插入本月份保存过的数据:"+sql2.toString());
		      if(i!=0){
			     msg = "1";  
			  }
		      for(cbJzBean bean:fylist){
		    	  String[] arr = array.split(",");		    	  
		    	  for(int j=0;j<arr.length;j++){
		    		  String[] arr1 = arr[j].split(";");
		    		  if(bean.getShicode()==arr1[0]||bean.getShicode().equals(arr1[0])){
		    	             String jzsxcount = bean.getJzsxcount() != null ? bean.getJzsxcount() : "0";
		    	             String scnhcount = bean.getScnhcount() != null ? bean.getScnhcount() : "0";
		    	             String cbgkcount = bean.getCbgkcount() != null ? bean.getCbgkcount() : "0";
		    	             String cbzdcount = bean.getCbzdcount() != null ? bean.getCbzdcount() : "0";
		    	             String jsdbcount = bean.getJsdbcount() != null ? bean.getJsdbcount() : "0";		    	             
		    	             String lxcbcount = bean.getLxcbcount() != null ? bean.getLxcbcount() : "0";
		    	             String xzzcbcount = bean.getLxzzcbcount() != null ? bean.getLxzzcbcount() : "0";
		    	             String zsxcount  = bean.getZsxcount() != null ? bean.getZsxcount() : "0";
		    	             String bzdlcount = bean.getBzdlcount() != null ? bean.getBzdlcount() : "0";
		    	             String szdlcount = bean.getSzdlcount() != null ? bean.getSzdlcount() : "0";
		    	             String bzpldu = bean.getBzpldu() != null ? bean.getBzpldu() : "0";
		    	             String waqzgcount = bean.getWaqzgcount()!=null ? bean.getWaqzgcount() : "0";
		    	             String zsx = bean.getZsx()!=null ? bean.getZsx() : "0";
		    	             String pldcount = bean.getPld()!=null ? bean.getPld() : "0";
		    	             String cbjzzgcount = bean.getCbjzzg() != null ? bean.getCbjzzg() : "0";
		    	             String cbdlzz = arr1[1] != null ? arr1[1] : "0";
		    	             String xzsgsdldb = arr1[2] != null ? arr1[2] : "0";
		    	             String zf = arr1[3] != null ? arr1[3] : "0";

				    	  String sql4 = "INSERT INTO KHXX (BZMONTH,SHICODE,SHINAME,SXCW,FT,CBGK," +
			    	  		"CBZD,JSDLCB,LXCB,CBDLZJ,ZSXL,CBZDHCWTG,TCSJYZCW,SCDLYW,BZDLH," +
			    	  		"SJDLH,BZPLD,WAQZG,ZSX,PLD,CBJZZG,CBDLZZ,XZSGSDLDB,ZF,PROPERTY) VALUES('"+bzmonth+"','"+bean.getShicode()+"','"+bean.getShiname()+
			    	  		"','"+jzsxcount+"','"+scnhcount+"','"+cbgkcount+"','"+cbzdcount+"','"+jsdbcount+
			    	  		"','"+lxcbcount+"','"+xzzcbcount+"','"+zsxcount+"','','','','"+bzdlcount+
			    	  		"','"+szdlcount+"','"+bzpldu+"','"+waqzgcount+
			    	  		"','"+zsx+"','"+pldcount+"','"+cbjzzgcount+"','"+cbdlzz+"','"+xzsgsdldb+"','"+zf+"','"+property+"')"; 
			      		  i=db.update(sql4);
					      System.out.println("基础数据真实性--插入本月份保存过的数据详细信息:"+sql4.toString());		      

		    		  }
		    	  }
		      }
		      if(i!=0){
				 msg = "2";  
		      }
		    }
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
			}
		    finally {
		      try {
		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
	}
}