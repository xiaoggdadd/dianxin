package com.noki.newfunction.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.newfunction.javabean.Ktxs;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.CTime;
import com.noki.zdqxkz.javabean.XxxgBean;
import com.noki.zdqxkz.javabean.Zdqxkz;

public class cbzddao {
	// 超标站点市审核
	public synchronized List<Zdinfo>  getshishenhe(String whereStr,
			String loginId) {		
		List<Zdinfo>  list = new ArrayList<Zdinfo>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
		//	fzzdstr = getFuzeZdid(db, loginId);
			sql = "select c.id,c.zdid,c.zdname,c.shi,c.xian,c.xiaoqu,c.cbsj,c.shijsh,x.csms,x.yyfx,x.cszrr,c.cbbl,x.bt,x.dgpch,x.yppch,to_char(c.fksj,'yyyy-mm-dd'),c.tdyy,c.shengjsh,c.sjtdyy " +
					"from cbzd c,zhandian z,cbzdxx x where z.id=c.zdid and c.id=x.wjid and c.QXTJSH='1'  "+whereStr+" " +
					 "AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					 + loginId + "'))) ";
            System.out.println("超标站点市审核列表查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				Zdinfo formbean=new Zdinfo();
				formbean.setId(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setZdid(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setZdname(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setShi(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setXian(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setXiaoqu(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setCbsj(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setShijsh(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setCsms(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setYyfx(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setCszrr(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setCbbl(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setBt(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setDgpch(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setYppch(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setFKSJ(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setTdyy(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setShengjsh(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setSjtdyy(rs.getString("sjtdyy")!=null?rs.getString("sjtdyy"):"");
				list.add(formbean);
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
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	
	// 超标站点省审核
	public synchronized List<Zdinfo>  getshengshenhe(String whereStr,
			String loginId) {		
		List<Zdinfo>  list = new ArrayList<Zdinfo>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
		//	fzzdstr = getFuzeZdid(db, loginId);
			sql = "select c.id,c.zdid,c.zdname,c.shi,c.xian,c.xiaoqu,c.cbsj,c.SHENGJSH,x.csms,x.yyfx,x.cszrr,c.cbbl,x.bt,x.dgpch,x.yppch,x.sjxf " +
					"from cbzd c,zhandian z,cbzdxx x where z.id=c.zdid and c.id=x.wjid and c.QXTJSH='1' and c.SHIJSH='2' "+whereStr+" " +
					 "AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					 + loginId + "'))) ";
            System.out.println("超标站点省审核列表查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				Zdinfo formbean=new Zdinfo();
				formbean.setId(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setZdid(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setZdname(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setShi(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setXian(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setXiaoqu(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setCbsj(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setShijsh(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setCsms(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setYyfx(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setCszrr(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setCbbl(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setBt(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setDgpch(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setYppch(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setSjxf(rs.getString(16)!=null?rs.getString(16):"");
				list.add(formbean);
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
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	/**
	   * 超标站点市级审核通过
	   * @param accountId String[]
	   * @return String
	   */
	  public synchronized String CbCityFees(String chooseIdStr1,String bz,String loginName) {
		    String msg = "市级审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
		
		      String sql1="update cbzd c set c.shijsh='2',c.shengjsh='0',c.SHIJSHR='"+loginName+"',c.SHIJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where c.id in("+chooseIdStr1+")";
		      msg = "审核信息失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("超标站点市级审核通过:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "市级审核通过！";
			  }else{
			     msg = "审市级审核未通过！";
			  }
		    } catch (DbException de) {
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
	   * 超标站点市级审核不通过
	   * @param accountId String[]
	   * @return String
	   */
	  public synchronized String CbCityFeesNo(String chooseIdStr1,String bz,String loginName) {
		    String msg = "市级审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
		
		      String sql1="update cbzd c set c.shijsh='1',c.SHIJSHR='"+loginName+"',c.SHIJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where c.id in("+chooseIdStr1+")";
		      msg = "审核信息失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("超标站点市级审核未通过:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "市级审核通过！";
			  }else{
			     msg = "市级审核未通过！";
			  }
		    } catch (DbException de) {
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
	   * 超标站点市级取消审核
	   * @param accountId String[]
	   * @return String
	   */
	  public synchronized String CbCityFeesqx(String chooseIdStr1,String bz,String loginName) {
		    String msg = "市级取消审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
		
		      String sql1="update cbzd c set c.shijsh='0',c.SHIJSHR='"+loginName+"',c.SHIJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where c.id in("+chooseIdStr1+")";
		      msg = "取消审核信息失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("超标站点市级取消审核:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "市级取消审核未通过！";
			  }else{
			     msg = "市级取消审核！";
			  }
		    } catch (DbException de) {
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
	//市 审核 详细详细
	  public synchronized Zdinfo getshixx(String id,
				String loginId) {		
			
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());
			String sql = "";
			String fzzdstr = "";
			DataBase db = new DataBase();
	        ResultSet rs = null;
	        Zdinfo formbean=new Zdinfo();
			try {
				sql = "SELECT C.ZDID,C.ZDNAME,C.SHI,C.XIAN,C.XIAOQU,C.CBSJ,X.SJLRR,X.ZGYQ," +
						"C.G2,C.G3,C.ZP,C.ZS,C.CHANGJIA,C.JZTYPE,C.SHEBEI,C.BBU,C.RRU,C.YDSHEBEI,C.GXGWSL," +
						"X.G2,X.G3,X.ZP,X.ZS,X.CHANGJIA,X.JZTYPE,X.SHEBEI,X.BBU,X.RRU,X.YDSHEBEI,X.GXGWSL," +
						"X.CSMS,X.YYFX,X.CSZRR,X.SJLRSJ,C.BZPLD FROM ZHANDIAN Z, CBZD C, CBZDXX X WHERE Z.ID=C.ZDID AND C.ID = X.WJID AND C.ID='"+id+"'" +
						"AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) ";
	            System.out.println("超标站点市审核详细信息查询："+sql.toString());
				db.connectDb();		
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					formbean.setZdid(rs.getString(1)!=null?rs.getString(1):"");
					formbean.setZdname(rs.getString(2)!=null?rs.getString(2):"");
					formbean.setShi(rs.getString(3)!=null?rs.getString(3):"");
					formbean.setXian(rs.getString(4)!=null?rs.getString(4):"");
					formbean.setXiaoqu(rs.getString(5)!=null?rs.getString(5):"");
					formbean.setCbsj(rs.getString(6)!=null?rs.getString(6):"");
					formbean.setSjlrr(rs.getString(7)!=null?rs.getString(7):"");
					formbean.setZgyq(rs.getString(8)!=null?rs.getString(8):"");//整改要求
					formbean.setG2(rs.getString(9)!=null?rs.getString(9):"");
					formbean.setG3(rs.getString(10)!=null?rs.getString(10):"");
					formbean.setZp(rs.getString(11)!=null?rs.getString(11):"");
					formbean.setZs(rs.getString(12)!=null?rs.getString(12):"");
					formbean.setChangjia(rs.getString(13)!=null?rs.getString(13):"");
					formbean.setJztype(rs.getString(14)!=null?rs.getString(14):"");
					formbean.setShebei(rs.getString(15)!=null?rs.getString(15):"");
					formbean.setBbu(rs.getString(16)!=null?rs.getString(16):"");
					formbean.setRru(rs.getString(17)!=null?rs.getString(17):"");
					formbean.setYdshebei(rs.getString(18)!=null?rs.getString(18):"");
					formbean.setGxgwsl(rs.getString(19)!=null?rs.getString(19):"");
					formbean.setXg2(rs.getString(20)!=null?rs.getString(20):"");
					formbean.setXg3(rs.getString(21)!=null?rs.getString(21):"");
					formbean.setXzp(rs.getInt(22));
					formbean.setXzs(rs.getInt(23));
					formbean.setXchangjia(rs.getString(24)!=null?rs.getString(24):"");
					formbean.setXjztype(rs.getString(25)!=null?rs.getString(25):"");
					formbean.setXshebei(rs.getString(26)!=null?rs.getString(26):"");
					formbean.setXbbu(rs.getInt(27));
					formbean.setXrru(rs.getInt(28));
					formbean.setXydshebei(rs.getInt(29));
					formbean.setXgxgwsl(rs.getInt(30));
					formbean.setCsms(rs.getString(31)!=null?rs.getString(31):"");
					formbean.setYyfx(rs.getString(32)!=null?rs.getString(32):"");
					formbean.setCszrr(rs.getString(33)!=null?rs.getString(33):"");
					formbean.setSjlrsj(rs.getString(34)!=null?rs.getString(34):"");
					formbean.setBzpld(rs.getString(35)!=null?rs.getString(35):"");
				
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
		/**
	   * 超标站点省级审核通过整改
	   * @param accountId String[]
	   * @return String
	   */
	  public synchronized String Cbshengtg(String chooseIdStr1,String bz,String loginname) {
		    String msg = "省级审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

	     
		    try {
		      db.connectDb();
	
		      String sql1="update cbzd c set c.SHENGJSH='2',c.tdyy='',c.fksj='',c.SHENGJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss'),c.SHENGJSHR='"+loginname+"' where c.id in("+chooseIdStr1+")";
		      msg = "审核信息失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("超标站点省级审核通过整改:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "省级审核通过整改！";
			  }else{
			     msg = "省级审核未通过！";
			  }
		    } catch (DbException de) {
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
	   * 超标站点省级审核不通过退单
	   * @param accountId String[]
	   * @return String
	   */
	  public synchronized String Cbshino(String chooseIdStr1,String bz,String loginname,String sj,String yj) {
		    String msg = "省级审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());
			System.out.println("chooseIdStr1:"+chooseIdStr1);
		    try {
		      db.connectDb();
		      //String sql1="update cbzd c set c.SHIJSH='1',c.SHIJSHSJ=to_date('"+inserttime+"','yyyy-mm-dd hh24:mi:ss'),c.SHIJSHR='"+loginname+"',c.SJTDYY='"+yj+"',c.FKSJ=to_date('"+sj+"','yyyy-mm-dd')  where c.id in("+chooseIdStr1+")";
		      String sql1="update cbzd c set c.SHIJSH='1',c.SHIJSHSJ=to_date(?,'yyyy-mm-dd hh24:mi:ss'),c.SHIJSHR=?,c.SJTDYY=?,c.FKSJ=to_date(?,'yyyy-mm-dd')  where c.id in("+chooseIdStr1+")";
		      msg = "审核信息失败！";
		      try {
		    	  PreparedStatement prep=db.getPreparedStatement(sql1);
			      prep.setString(1, inserttime);
			      prep.setString(2, loginname);
			      prep.setString(3, yj);
			      prep.setString(4, sj);
			      if(chooseIdStr1!=""&&chooseIdStr1!=null){
			    	  System.out.println("超标站点省级审核不通过退单:"+sql1.toString());
			    	 // db.update(sql1);	
			    	  int i=0;
		    	  prep.executeUpdate();
			    	  if(i>0){
			    		 
			    	  }else{
			    		  msg = "市级审核退单失败！请联系管理员！";
			    	  }
			      } 
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		     
		      if(bz.equals("1")){
			     msg = "市级审核退单成功！";
			  }else{
			     msg = "市级审核退单失败！";
			  }
		    } catch (DbException de) {
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
	  
	  public synchronized String Cbshengno(String chooseIdStr1,String bz,String loginname,String sj,String yj) {
		    String msg = "省级审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
	
		      String sql1="update cbzd c set c.SHENGJSH='1',c.SHENGJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss'),c.SHENGJSHR='"+loginname+"',c.TDYY='"+yj+"',c.FKSJ=to_date('" + sj+ "','yyyy-mm-dd')  where c.id in("+chooseIdStr1+")";
		      msg = "审核信息失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("超标站点省级审核不通过退单:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "省级审核退单成功！";
			  }else{
			     msg = "省级审核退单失败！";
			  }
		    } catch (DbException de) {
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
	   * 超标站点省级审核结单
	   * @param accountId String[]
	   * @return String
	   */

	  public synchronized String Cbshengtd(String chooseIdStr1,String bz,String loginname,List<Zdinfo> lsy) {

		    String msg = "省级审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());
             ResultSet rs=null;
             String qsdb="",idd="";
             String sqll="";
           
		    try {
		      db.connectDb();
		     // db.setAutoCommit(false);
	          String sql="select x.qsdb,x.zdid as id from cbzdxx x where x.id in ("+chooseIdStr1+")";
	         //  String sql4="update zhandian z set z.qsdbdl='' ";
	          
	          
		      String sql1="update cbzd c set c.SHENGJSH='3',c.tdyy='',c.fksj='',c.SHENGJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss'),c.SHENGJSHR='"+loginname+"' where c.id in("+chooseIdStr1+")";
		      String sql2 = "UPDATE SCB S SET S.SCB = (SELECT CBKG.SCB FROM (SELECT CX.QSDB AS SCB, CB.ZDID," +
		      		"CB.CBSJ FROM CBZD CB, CBZDXX CX,(SELECT MAX(CX1.ID) AS MAXID FROM CBZDXX CX1" +
		      		" GROUP BY CX1.WJID) CX1 WHERE CB.ID = CX.WJID AND CX1.MAXID = CX.ID AND CB.ID IN ("+chooseIdStr1+")) CBKG  WHERE S.ZDID = CBKG.ZDID)," +
		      		"S.MODIFIER='"+loginname+"',S.MODIFICATIONTIME=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') " +
		      		"WHERE S.ID IN (SELECT S.ID FROM CBZD CB, SCB S WHERE CB.ZDID = S.ZDID AND CB.ID IN ("+chooseIdStr1+"))";
		      String sql3 = "UPDATE SCB S SET S.SCB = (SELECT CBKG.SCB FROM (SELECT CX.QSDB AS SCB, CB.ZDID," +
	      		"CB.CBSJ FROM CBZD CB, CBZDXX CX,(SELECT MAX(CX1.ID) AS MAXID FROM CBZDXX CX1" +
	      		" GROUP BY CX1.WJID) CX1 WHERE CB.ID = CX.WJID AND CX1.MAXID = CX.ID) CBKG  WHERE S.ZDID = CBKG.ZDID)," +
	      		"S.MODIFIER='"+loginname+"',S.MODIFICATIONTIME=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') " +
	      		"WHERE S.ID IN (SELECT S.ID FROM CBZD CB, SCB S WHERE CB.ZDID = S.ZDID AND CB.ID IN ("+chooseIdStr1+"))";
		    
		      // 	String sql4="update zhandian z set z.edhdl=(select x.bdedhdl from cbzd c,cbzdxx x,zhandian z where c.id=x.wjid and c.zdid=z.id and z.id in ("+zdid+"))," +
			//"z.jlfh=(select x.jlzfh from cbzd c,cbzdxx x,zhandian z where c.id=x.wjid and c.zdid=z.id and z.id in ("+zdid+") )," +
			//"z.zlfh=(select x.zlzfh from cbzd c,cbzdxx x,zhandian z where c.id=x.wjid and c.zdid=z.id and z.id in ("+zdid+")) where z.id in ("+zdid+")";
		      msg = "审核信息失败！";

		      System.out.println("查询全省定标sql："+sql.toString());
		     System.out.println("超标站点省级审核结单:"+sql1.toString());
		     System.out.println("超标站点省级审核结单--更新对应月份生产标:"+sql2.toString());
		    

		      String sql4="";

		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    		rs = db.queryAll(sql.toString());
		    		  List<ElectricFeesFormBean> ls=new ArrayList<ElectricFeesFormBean>();
						while(rs.next()){
							ElectricFeesFormBean bean=new ElectricFeesFormBean();
							qsdb=rs.getString("qsdb")!=null?rs.getString("qsdb"):"0";
							
							idd=rs.getString("id");
							bean.setQsdbdl(qsdb);
							bean.setId(idd);
							ls.add(bean);
						}
						if(ls!=null){
							for(ElectricFeesFormBean e:ls){
							// JCXS YFXS  KTXS FWXS 
								//修改站点全省定标电量
								 sqll="update zhandian z set z.qsdbdl="+e.getQsdbdl()+"*(z.jcxs+z.yfxs*z.ktxs+z.fwxs) where z.id="+e.getId()+"";
								//  System.out.println("更新站点省定标电量sqll:"+sqll.toString());
								 db.update(sqll);
							}
						}
		    	  db.update(sql1);
		    	  db.update(sql2);	
		    	  if(lsy!=null){
		    		  for(Zdinfo  lst:lsy){ 
		    			  //修改站点属性信息
		    			  sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
		    			 // System.out.println("超标站点省级审核结单--更新站点:"+sql4.toString());
		    			  db.update(sql4);
		    		  }
		    		  
		    	  }
		    	  
		    	  
		    	  
		    	 // System.out.println("更新站点本地标，直流负荷，交流负荷:"+sql4.toString());
		    	//  db.update(sql4);
		      }
		      db.commit();
		    
		      if(bz.equals("1")){
			     msg = "省级审核结单！";
			  }else{
			     msg = "省级审核未通过！";
			  }
		    } catch (Exception de) {
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
	   * 超标站点省级取消审核
	   * @param accountId String[]
	   * @return String
	   */
	  public synchronized String Cbshengqx(String chooseIdStr1,String bz,String loginname) {
		    String msg = "省级取消审核信息失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

	     
		    try {
		      db.connectDb();
	
		      String sql1="update cbzd c set c.SHENGJSH='0',c.tdyy='',c.fksj='',c.SHENGJSHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss'),c.SHENGJSHR='"+loginname+"' where c.id in("+chooseIdStr1+")";
		      msg = "审核信息失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("超标站点省级取消审核:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "省级取消审核成功！";
			  }else{
			     msg = "省级取消审核未通过！";
			  }
		    } catch (DbException de) {
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
		// 下发市审核查询
		public synchronized List<Zdinfo>  getxfshishenhe(String whereStr,
				String loginId) {		
			List<Zdinfo>  list = new ArrayList<Zdinfo>();
			CTime ct = new CTime();
			String kjnd = ct.formatShortDate().substring(0, 4);
			String sql = "";
			String fzzdstr = "";
			DataBase db = new DataBase();
	        ResultSet rs = null;
			try {
			//	fzzdstr = getFuzeZdid(db, loginId);
				sql = "select c.id,c.zdid,c.zdname,c.shi,c.xian,c.xiaoqu,c.cbsj,x.SJSHBZ,f.ZGZRR,c.cbbl,f.XFZGYQ,f.WCSJ,x.id,x.bt,x.dgpch ,x.yppch,x.sjyqwcsj,x.sjyqwtgyy,x.shengjshbz,c.NHBZ,sb.SCB " +
						"from cbzd c,zhandian z,cbzdxx x,cbzdxf f,scb sb where z.id=c.zdid and c.id=x.wjid and x.id=f.BWJID and x.QXTJSH='1' and c.ZDID=sb.ZDID  "+whereStr+" " +
						 "AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						 + loginId + "'))) ";
	            System.out.println("下发市审核列表查询："+sql.toString());
				db.connectDb();		
				rs = db.queryAll(sql.toString());
				//Query query = new Query();
				//list = query.query(rs);
				while(rs.next()){
					Zdinfo formbean=new Zdinfo();
					formbean.setId(rs.getString(1)!=null?rs.getString(1):"");
					formbean.setZdid(rs.getString(2)!=null?rs.getString(2):"");
					formbean.setZdname(rs.getString(3)!=null?rs.getString(3):"");
					formbean.setShi(rs.getString(4)!=null?rs.getString(4):"");
					formbean.setXian(rs.getString(5)!=null?rs.getString(5):"");
					formbean.setXiaoqu(rs.getString(6)!=null?rs.getString(6):"");
					formbean.setCbsj(rs.getString(7)!=null?rs.getString(7):"");
					formbean.setShijsh(rs.getString(8)!=null?rs.getString(8):"");
					formbean.setCszrr(rs.getString(9)!=null?rs.getString(9):"");
					formbean.setCbbl(rs.getString(10)!=null?rs.getString(10):"");
					formbean.setXfzgyq(rs.getString(11)!=null?rs.getString(11):"");
					formbean.setWcsj(rs.getString(12)!=null?rs.getString(12):"");
					formbean.setIdd(rs.getString(13)!=null?rs.getString(13):"");
					formbean.setBt(rs.getString(14)!=null?rs.getString(14):"");
					formbean.setDgpch(rs.getString(15)!=null?rs.getString(15):"");
					formbean.setYppch(rs.getString(16)!=null?rs.getString(16):"");
					formbean.setSjyqwcsj(rs.getString(17)!=null?rs.getString(17):"");
					formbean.setSjyqwtgyy(rs.getString(18)!=null?rs.getString(18):"");
					formbean.setShengjshbz(rs.getString(19)!=null?rs.getString(19):"");
					formbean.setNhbz(rs.getString(20)!=null?rs.getString(20):"");
					formbean.setScb(rs.getString(21)!=null?rs.getString(21):"");
					list.add(formbean);
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
			//System.out.println(list.toString() + "------------------");
			return list;
		}
		/**
		   * 下发站点 市审核 通过
		   * @param accountId String[]
		   * @return String
		   */
		  public synchronized String Xfshish(String chooseIdStr1,String bz,String loginname) {
			    String msg = "市级审核信息失败！";
			    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
			    DataBase db = new DataBase();
			    String str = "";
		    	CTime ctime = new CTime(); 
				String inserttime = ctime.formatWholeDate(new Date());

			    try {
			      db.connectDb();
		
			      String sql1="update cbzdxx x set x.SJSHBZ='1',x.sjshr='"+loginname+"',x.sjshsj=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where x.WJID in("+chooseIdStr1+")";
			      
			      
			      msg = "审核信息失败！";
			      if(chooseIdStr1!=""&&chooseIdStr1!=null){
			    	  System.out.println("下发市级审核通过:"+sql1.toString());
			    	  db.update(sql1);	   
			    
			      }
			    
			      if(bz.equals("1")){
				     msg = "市级审核通过！";
				  }else{
				     msg = "市级审核未通过！";
				  }
			    } catch (DbException de) {
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
		   * 下发站点 市审核 不通过
		   * @param accountId String[]
		   * @return String
		   */
		  public synchronized String Xfshishno(String chooseIdStr1,String bz,String loginname) {
			    String msg = "市级审核信息失败！";
			    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
			    DataBase db = new DataBase();
			    String str = "";
		    	CTime ctime = new CTime(); 
				String inserttime = ctime.formatWholeDate(new Date());

			    try {
			      db.connectDb();
		
			      String sql1="update cbzdxx x set x.SJSHBZ='2',x.sjshr='"+loginname+"',x.sjshsj=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where x.WJID in("+chooseIdStr1+")";
			      
			      
			      msg = "审核信息失败！";
			      if(chooseIdStr1!=""&&chooseIdStr1!=null){
			    	  System.out.println("下发市级审核不通过:"+sql1.toString());
			    	  db.update(sql1);	    	  
			      }
			    
			      if(bz.equals("1")){
				     msg = "市级审核不通过！";
				  }else{
				     msg = "市级审核未通过！";
				  }
			    } catch (DbException de) {
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
		// 下发省级审核查询
			public synchronized List<Zdinfo>  getxfshengshenhe(String whereStr,
					String loginId) {		
				List<Zdinfo>  list = new ArrayList<Zdinfo>();
				CTime ct = new CTime();
				String kjnd = ct.formatShortDate().substring(0, 4);
				String sql = "";
				String fzzdstr = "";
				DataBase db = new DataBase();
		        ResultSet rs = null;
				try {
				//	fzzdstr = getFuzeZdid(db, loginId);
					sql = "select c.id,c.zdid,c.zdname,c.shi,c.xian,c.xiaoqu,c.cbsj,x.SHENGJSHBZ,f.ZGZRR,c.CBBL,f.XFZGYQ,f.WCSJ,x.id,x.bt,x.dgpch ,x.yppch,c.nhbz,sb.scb " +
							"from cbzd c,scb sb,zhandian z,cbzdxx x,cbzdxf f where z.id=c.zdid and c.id=x.wjid and x.id=f.BWJID and x.QXTJSH='1' and c.zdid=sb.zdid and x.SJSHBZ='1' "+whereStr+" " +
							 "AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
							 + loginId + "'))) ";
		            System.out.println("下发省审核列表查询："+sql.toString());
					db.connectDb();		
					rs = db.queryAll(sql.toString());
					//Query query = new Query();
					//list = query.query(rs);
					while(rs.next()){
						Zdinfo formbean=new Zdinfo();
						formbean.setId(rs.getString(1)!=null?rs.getString(1):"");
						formbean.setZdid(rs.getString(2)!=null?rs.getString(2):"");
						formbean.setZdname(rs.getString(3)!=null?rs.getString(3):"");
						formbean.setShi(rs.getString(4)!=null?rs.getString(4):"");
						formbean.setXian(rs.getString(5)!=null?rs.getString(5):"");
						formbean.setXiaoqu(rs.getString(6)!=null?rs.getString(6):"");
						formbean.setCbsj(rs.getString(7)!=null?rs.getString(7):"");
						formbean.setShijsh(rs.getString(8)!=null?rs.getString(8):"");
						formbean.setCszrr(rs.getString(9)!=null?rs.getString(9):"");
						formbean.setCbbl(rs.getString(10)!=null?rs.getString(10):"");
						formbean.setXfzgyq(rs.getString(11)!=null?rs.getString(11):"");
						formbean.setWcsj(rs.getString(12)!=null?rs.getString(12):"");
						formbean.setIdd(rs.getString(13)!=null?rs.getString(13):"");
						formbean.setBt(rs.getString(14)!=null?rs.getString(14):"");
						formbean.setDgpch(rs.getString(15)!=null?rs.getString(15):"");
						formbean.setYppch(rs.getString(16)!=null?rs.getString(16):"");
						formbean.setNhbz(rs.getString(17)!=null?rs.getString(17):"");
						formbean.setScb(rs.getString(18)!=null?rs.getString(18):"");
						//System.out.println("ww:"+rs.getString(11));
			
			
						list.add(formbean);
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
				//System.out.println(list.toString() + "------------------");
				return list;
			}
			/**
			   * 下发站点 省级审核 通过
			   * @param accountId String[]
			   * @return String
			   */
			  public synchronized String Xfshengsh(String chooseIdStr1,String bz,String loginname,List<Zdinfo> ls) {
				    String msg = "省级审核信息失败！";
				    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
				    DataBase db = new DataBase();
				    String str = "";
			    	CTime ctime = new CTime(); 
					String inserttime = ctime.formatWholeDate(new Date());

				    try {
				      db.connectDb();
			
				      String sql1="update cbzdxx x set x.SHENGJSHBZ='1',x.SJYQWCSJ='',x.SJYQWTGYY='',x.SHENGJSHR='"+loginname+"',x.SHENGJISHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where x.WJID in("+chooseIdStr1+")";
				      String sql2 = "UPDATE SCB S SET S.SCB = (SELECT CBKG.SCB FROM (SELECT CX.YSSDBDL AS SCB, CB.ZDID," +
			      		"CB.CBSJ FROM CBZD CB, CBZDXX CX,(SELECT MAX(CX1.ID) AS MAXID FROM CBZDXX CX1" +
			      		" GROUP BY CX1.WJID) CX1 WHERE CB.ID = CX.WJID AND CX1.MAXID = CX.ID AND CB.ID IN ("+chooseIdStr1+")) CBKG  WHERE S.ZDID = CBKG.ZDID)," +
			      		"S.MODIFIER='"+loginname+"',S.MODIFICATIONTIME=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') " +
			      		"WHERE S.ID IN (SELECT S.ID FROM CBZD CB, SCB S WHERE CB.ZDID = S.ZDID AND CB.ID IN ("+chooseIdStr1+"))";
			      String sql3 = "UPDATE SCB S SET S.SCB = (SELECT CBKG.SCB FROM (SELECT CX.QSDB AS SCB, CB.ZDID," +
		      		"CB.CBSJ FROM CBZD CB, CBZDXX CX,(SELECT MAX(CX1.ID) AS MAXID FROM CBZDXX CX1" +
		      		" GROUP BY CX1.WJID) CX1 WHERE CB.ID = CX.WJID AND CX1.MAXID = CX.ID) CBKG  WHERE S.ZDID = CBKG.ZDID)," +
		      		"S.MODIFIER='"+loginname+"',S.MODIFICATIONTIME=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') " +
		      		"WHERE S.ID IN (SELECT S.ID FROM CBZD CB, SCB S WHERE CB.ZDID = S.ZDID AND CB.ID IN ("+chooseIdStr1+"))";
				      
				      msg = "审核信息失败！";
				      String sql4="";
				      if(chooseIdStr1!=""&&chooseIdStr1!=null){
				    	  System.out.println("下发省级审核通过:"+sql1.toString());
				    	  db.update(sql1);
				    	  System.out.println("超标站点省级审核结单--更新对应月份生产标:"+sql3.toString());
				    	  db.update(sql3);
				    	  if(ls!=null){
				    		  for(Zdinfo lst:ls){
				    			  sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
				    			  System.out.println("超标站点省级审核结单--更新站点:"+sql4.toString());
				    			  db.update(sql4);
				    		  }
				    	  }
				    	  
				    	  
				      }
				    
				      if(bz.equals("1")){
					     msg = "省级审核通过！";
					  }else{
					     msg = "省级审核未通过！";
					  }
				    } catch (DbException de) {
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
			   * 下发站点 省级审核 不通过
			   * @param accountId String[]
			   * @return String
			   */
			  public synchronized String Xfshengshno(String chooseIdStr1,String bz,String loginname,String sj,String yy) {
				    String msg = "省级审核信息失败！";
				    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
				    DataBase db = new DataBase();
				    String str = "";
			    	CTime ctime = new CTime(); 
					String inserttime = ctime.formatWholeDate(new Date());

				    try {
				      db.connectDb();
			
				      String sql1="update cbzdxx x set x.SJYQWTGYY='"+yy+"',x.SJYQWCSJ=to_date('"+sj+"','yyyy-mm-dd'),x.SHENGJSHBZ='2',x.SHENGJSHR='"+loginname+"',x.SHENGJISHSJ=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where x.WJID in("+chooseIdStr1+")";
				      
				      
				      msg = "审核信息失败！";
				      if(chooseIdStr1!=""&&chooseIdStr1!=null){
				    	  System.out.println("下发省级审核不通过:"+sql1.toString());
				    	  db.update(sql1);	    	  
				      }
				    
				      if(bz.equals("1")){
					     msg = "省级审核不通过！";
					  }else{
					     msg = "省级审核未通过！";
					  }
				    } catch (DbException de) {
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

				// 超标站点市审核
	public synchronized List<Zdinfo>  getchaxun(String whereStr,
						String loginId) {		
					List<Zdinfo>  list = new ArrayList<Zdinfo>();
					CTime ct = new CTime();
					String kjnd = ct.formatShortDate().substring(0, 4);
					String sql = "";
					String fzzdstr = "";
					DataBase db = new DataBase();
			        ResultSet rs = null;
					try {
						
						
						sql="SELECT C.ID,C.ZDID,C.ZDNAME,C.SHI,C.XIAN,C.XIAOQU,C.CBBL,C.SJPD,C.DSQS,C.DSPD,C.QXQS,C.QXPD," +
								"C.QXTJSH,C.SHIJSH,C.SHENGJSH, X.SJXF,X.SHIJXF,X.QXTJSH,X.SJSHBZ,X.SHENGJSHBZ,C.CBSJ,x.id  " +
							" FROM CBZD C,CBZDXX X,CBZDXF F,ZHANDIAN Z  WHERE  Z.ID=C.ZDID AND C.ID=X.WJID(+)  AND X.ID=F.BWJID(+) "+whereStr+""+
						    " AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))) ";
					
			            System.out.println("超标站点明细查询："+sql.toString());
						db.connectDb();		
						rs = db.queryAll(sql.toString());
						//Query query = new Query();
						//list = query.query(rs);
						while(rs.next()){
							Zdinfo formbean=new Zdinfo();
							formbean.setId(rs.getString(1)!=null?rs.getString(1):"");
							formbean.setZdid(rs.getString(2)!=null?rs.getString(2):"");
							formbean.setZdname(rs.getString(3)!=null?rs.getString(3):"");
							formbean.setShi(rs.getString(4)!=null?rs.getString(4):"");
							formbean.setXian(rs.getString(5)!=null?rs.getString(5):"");
							formbean.setXiaoqu(rs.getString(6)!=null?rs.getString(6):"");
							formbean.setCbbl(rs.getString(7)!=null?rs.getString(7):"");
							formbean.setSjpd(rs.getString(8)!=null?rs.getString(8):"");
							formbean.setDsqs(rs.getString(9)!=null?rs.getString(9):"");
							formbean.setDspd(rs.getString(10)!=null?rs.getString(10):"");
							formbean.setQxqs(rs.getString(11)!=null?rs.getString(11):"");
							formbean.setQxpd(rs.getString(12)!=null?rs.getString(12):"");
							formbean.setQxtjsh(rs.getString(13)!=null?rs.getString(13):"");
							formbean.setShijsh(rs.getString(14)!=null?rs.getString(14):"");
							formbean.setShengjsh(rs.getString(15)!=null?rs.getString(15):"");
							formbean.setSjxf(rs.getString(16)!=null?rs.getString(16):"");
							formbean.setShijxf(rs.getString(17)!=null?rs.getString(17):"");
							formbean.setQxzgtj(rs.getString(18)!=null?rs.getString(18):"");
							formbean.setSjshbz(rs.getString(19)!=null?rs.getString(19):"");
							formbean.setShengjshbz(rs.getString(20)!=null?rs.getString(20):"");
							formbean.setCbsj(rs.getString(21)!=null?rs.getString(21):"");
							formbean.setCid(rs.getString(22)!=null?rs.getString(22):"");
				
				
							list.add(formbean);
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
					//System.out.println(list.toString() + "------------------");
					return list;
				}

			  public synchronized Zdinfo getShiXx(String id,
						String loginId) {		
					
			    	CTime ctime = new CTime(); 
					String inserttime = ctime.formatWholeDate(new Date());
					String sql = "";
					String fzzdstr = "";
					DataBase db = new DataBase();
			        ResultSet rs = null;
			        Zdinfo formbean=new Zdinfo();
					try {//2个下载--添加字段sjname,qxname
						sql = "SELECT C.ZDID,C.ZDNAME,C.SHI,C.XIAN,C.XIAOQU,C.CBSJ,X.SJLRR,X.ZGYQ," +
								"C.G2,C.G3,C.ZP,C.ZS,C.CHANGJIA,C.JZTYPE,C.SHEBEI,C.BBU,C.RRU,C.YDSHEBEI,C.GXGWSL," +
								"X.G2,X.G3,X.ZP,X.ZS,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.CHANGJIA AND T.TYPE = 'cj') as CHANGJIA, (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.JZTYPE AND T.TYPE = 'jzlx2')as JZTYPE,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.SHEBEI AND T.TYPE = 'sblx')as SHEBEI,X.BBU,X.RRU,X.YDSHEBEI,X.GXGWSL, " +
								"X.CSMS,X.YYFX,X.CSZRR,to_char(X.SJLRSJ,'yyyy-mm-dd hh24:MI:SS'),C.CBBL,x.ydgxsb,x.dxgxsb,c.ydgxsb jydgxsb,c.dxgxsb jdxgxsb,c.g2xq jg2xq,c.g3sq jg3sq,x.g2xq,x.g3sq,x.dbds,x.KGDYZLFH,x.YDGXSBZLFH,x.DXGXSBZLFH,x.GYGXSBZLFH,x.ZYYGSBZLFH,X.SJNAME,X.QXNAME,X.ZLZFH,X.JLZFH,X.QSDB,X.BDEDHDL  FROM ZHANDIAN Z, CBZD C, CBZDXX X WHERE Z.ID=C.ZDID AND C.ID = X.WJID AND C.ID='"+id+"'" +
								"AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) ";
			            System.out.println("超标站点市审核详细信息查询："+sql.toString());
						db.connectDb();		
						rs = db.queryAll(sql.toString());
						while(rs.next()){
							formbean.setZdid(rs.getString(1)!=null?rs.getString(1):"");
							formbean.setZdname(rs.getString(2)!=null?rs.getString(2):"");
							formbean.setShi(rs.getString(3)!=null?rs.getString(3):"");
							formbean.setXian(rs.getString(4)!=null?rs.getString(4):"");
							formbean.setXiaoqu(rs.getString(5)!=null?rs.getString(5):"");
							formbean.setCbsj(rs.getString(6)!=null?rs.getString(6):"");
							formbean.setSjlrr(rs.getString(7)!=null?rs.getString(7):"");
							formbean.setZgyq(rs.getString(8)!=null?rs.getString(8):"");//整改要求
							formbean.setG2(rs.getString(9)!=null?rs.getString(9):"");
							formbean.setG3(rs.getString(10)!=null?rs.getString(10):"");
							formbean.setZp(rs.getString(11)!=null?rs.getString(11):"");
							formbean.setZs(rs.getString(12)!=null?rs.getString(12):"");
							formbean.setChangjia(rs.getString(13)!=null?rs.getString(13):"");
							formbean.setJztype(rs.getString(14)!=null?rs.getString(14):"");
							formbean.setShebei(rs.getString(15)!=null?rs.getString(15):"");
							formbean.setBbu(rs.getString(16)!=null?rs.getString(16):"");
							formbean.setRru(rs.getString(17)!=null?rs.getString(17):"");
							formbean.setYdshebei(rs.getString(18)!=null?rs.getString(18):"");
							formbean.setGxgwsl(rs.getString(19)!=null?rs.getString(19):"");
							formbean.setXg2(rs.getString(20)!=null?rs.getString(20):"");
							formbean.setXg3(rs.getString(21)!=null?rs.getString(21):"");
							formbean.setXzp(rs.getInt(22));
							formbean.setXzs(rs.getInt(23));
							formbean.setXchangjia(rs.getString(24)!=null?rs.getString(24):"");
							formbean.setXjztype(rs.getString(25)!=null?rs.getString(25):"");
							formbean.setXshebei(rs.getString(26)!=null?rs.getString(26):"");
							formbean.setXbbu(rs.getInt(27));
							formbean.setXrru(rs.getInt(28));
							formbean.setXydshebei(rs.getInt(29));
							formbean.setXgxgwsl(rs.getInt(30));
							formbean.setCsms(rs.getString(31)!=null?rs.getString(31):"");
							formbean.setYyfx(rs.getString(32)!=null?rs.getString(32):"");
							formbean.setCszrr(rs.getString(33)!=null?rs.getString(33):"");
							formbean.setSjlrsj(rs.getString(34)!=null?rs.getString(34):"");
							formbean.setCbbl(rs.getString(35)!=null?rs.getString(35):"");
							formbean.setDxgxsb(rs.getString(37)!=null?rs.getString(37):"0");
							formbean.setYdgxsb(rs.getString(36)!=null?rs.getString(36):"0");

							//formbean.setJydgxsb(rs.getString(38)!=null?rs.getString(38):"0");
						  //  formbean.setJdxgxsb(rs.getString(39)!=null?rs.getString(39):"0");
						  //  formbean.setJg2xq(rs.getString(40)!=null?rs.getString(40):"0");
						  //  formbean.setJg3sq(rs.getString(41)!=null?rs.getString(41):"0");
						 //   formbean.setG2xq(rs.getString(42)!=null?rs.getString(42):"0");
						//    formbean.setG3sq(rs.getString(43)!=null?rs.getString(43):"0");

						

							formbean.setJydgxsb(rs.getString(38)!=null?rs.getString(38):"0");
						    formbean.setJdxgxsb(rs.getString(39)!=null?rs.getString(39):"0");
						    formbean.setJg2xq(rs.getString(40)!=null?rs.getString(40):"0");
						    formbean.setJg3sq(rs.getString(41)!=null?rs.getString(41):"0");
						    formbean.setG2xq(rs.getString(42)!=null?rs.getString(42):"0");
						    formbean.setG3sq(rs.getString(43)!=null?rs.getString(43):"0");
						    formbean.setDbds(rs.getString(44)!=null?rs.getString(44):"0");
						    formbean.setKGDYZLFH(rs.getString(45)!=null?rs.getString(45):"0");
						    formbean.setYDGXSBZLFH(rs.getString(46)!=null?rs.getString(46):"0");
						    formbean.setDXGXSBZLFH(rs.getString(47)!=null?rs.getString(47):"0");
						    formbean.setGYGXSBZLFH(rs.getString(48)!=null?rs.getString(48):"0");
						    formbean.setZYYGSBZLFH(rs.getString(49)!=null?rs.getString(49):"0");
						    formbean.setSjname(rs.getString(50)!=null?rs.getString(50):"");
						    formbean.setQxname(rs.getString(51)!=null?rs.getString(51):"");
						    formbean.setZlzfh(rs.getString(52)!=null?rs.getString(52):"");
						    formbean.setJlzfh(rs.getString(53)!=null?rs.getString(53):"");
						    formbean.setQsdb(rs.getString(54)!=null?rs.getString(54):"");
						    formbean.setBdhdl(rs.getString(55)!=null?rs.getString(55):"");
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
	//省级市级审核下发 详细详细查询		 
			  
	public synchronized Zdinfo getShiXxxf(String id,
						String loginId) {		
					
			    	CTime ctime = new CTime(); 
					String inserttime = ctime.formatWholeDate(new Date());
					String sql = "";
					String fzzdstr = "";
					DataBase db = new DataBase();
			        ResultSet rs = null;
			        Zdinfo formbean=new Zdinfo();
					try {
						sql = "SELECT C.ZDID,C.ZDNAME,C.SHI,C.XIAN,C.XIAOQU,C.CBSJ,X.SJLRR,X.ZGYQ," +
								"C.G2,C.G3,C.ZP,C.ZS,C.CHANGJIA,C.JZTYPE,C.SHEBEI,C.BBU,C.RRU,C.YDSHEBEI,C.GXGWSL," +
								"X.G2,X.G3,X.ZP,X.ZS,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.CHANGJIA AND T.TYPE = 'cj') as CHANGJIA," +
								" (SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.JZTYPE AND T.TYPE = 'jzlx2')as JZTYPE," +
								"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = X.SHEBEI AND T.TYPE = 'sblx')as SHEBEI," +
								"X.BBU,X.RRU,X.YDSHEBEI,X.GXGWSL," +
								"X.CSMS,X.YYFX,X.CSZRR,X.SJLRSJ,C.BZPLD, " +
								"F.XFZGYQ,F.WCSJ,F.WCSM,F.QXWCSJ,F.ZGZRR,C.g2xq jg2xq,C.g3sq jg3sq,X.g2xq,X.g3sq,c.ydgxsb,c.dxgxsb,x.ydgxsb,x.dxgxsb,f.sjpath,f.qxname " +
								" FROM ZHANDIAN Z, CBZD C, CBZDXX X,CBZDXF F WHERE Z.ID=C.ZDID AND C.ID = X.WJID AND X.ID=F.BWJID AND x.ID='"+id+"'" +
								"AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) ";
			            System.out.println("超标站点市审核详细信息查询："+sql.toString());
						db.connectDb();		
						rs = db.queryAll(sql.toString());
						while(rs.next()){
							formbean.setZdid(rs.getString(1)!=null?rs.getString(1):"");
							formbean.setZdname(rs.getString(2)!=null?rs.getString(2):"");
							formbean.setShi(rs.getString(3)!=null?rs.getString(3):"");
							formbean.setXian(rs.getString(4)!=null?rs.getString(4):"");
							formbean.setXiaoqu(rs.getString(5)!=null?rs.getString(5):"");
							formbean.setCbsj(rs.getString(6)!=null?rs.getString(6):"");
							formbean.setSjlrr(rs.getString(7)!=null?rs.getString(7):"");
							formbean.setZgyq(rs.getString(8)!=null?rs.getString(8):"");//整改要求
							formbean.setG2(rs.getString(9)!=null?rs.getString(9):"");
							formbean.setG3(rs.getString(10)!=null?rs.getString(10):"");
							formbean.setZp(rs.getString(11)!=null?rs.getString(11):"");
							formbean.setZs(rs.getString(12)!=null?rs.getString(12):"");
							formbean.setChangjia(rs.getString(13)!=null?rs.getString(13):"");
							formbean.setJztype(rs.getString(14)!=null?rs.getString(14):"");
							formbean.setShebei(rs.getString(15)!=null?rs.getString(15):"");
							formbean.setBbu(rs.getString(16)!=null?rs.getString(16):"");
							formbean.setRru(rs.getString(17)!=null?rs.getString(17):"");
							formbean.setYdshebei(rs.getString(18)!=null?rs.getString(18):"");
							formbean.setGxgwsl(rs.getString(19)!=null?rs.getString(19):"");
							formbean.setXg2(rs.getString(20)!=null?rs.getString(20):"");
							formbean.setXg3(rs.getString(21)!=null?rs.getString(21):"");
							formbean.setXzp(rs.getInt(22));
							formbean.setXzs(rs.getInt(23));
							formbean.setXchangjia(rs.getString(24)!=null?rs.getString(24):"");
							formbean.setXjztype(rs.getString(25)!=null?rs.getString(25):"");
							formbean.setXshebei(rs.getString(26)!=null?rs.getString(26):"");
							formbean.setXbbu(rs.getInt(27));
							formbean.setXrru(rs.getInt(28));
							formbean.setXydshebei(rs.getInt(29));
							formbean.setXgxgwsl(rs.getInt(30));
							formbean.setCsms(rs.getString(31)!=null?rs.getString(31):"");
							formbean.setYyfx(rs.getString(32)!=null?rs.getString(32):"");
							formbean.setCszrr(rs.getString(33)!=null?rs.getString(33):"");
							formbean.setSjlrsj(rs.getString(34)!=null?rs.getString(34):"");
							formbean.setBzpld(rs.getString(35)!=null?rs.getString(35):"");
							formbean.setXfzgyq(rs.getString(36)!=null?rs.getString(36):"");
							formbean.setWcsj(rs.getString(37)!=null?rs.getString(37):"");
							formbean.setWcsm(rs.getString(38)!=null?rs.getString(38):"");
							formbean.setQxwcsj(rs.getString(39)!=null?rs.getString(39):"");
							formbean.setZgzrr(rs.getString(40)!=null?rs.getString(40):"");
							formbean.setG2xq(rs.getString(41)!=null?rs.getString(41):"");
							formbean.setG3sq(rs.getString(42)!=null?rs.getString(42):"");
							formbean.setJg2xq(rs.getString(43)!=null?rs.getString(43):"");
							formbean.setJg3sq(rs.getString(44)!=null?rs.getString(44):"");
							formbean.setJydgxsb(rs.getString(45)!=null?rs.getString(45):"");
							formbean.setJdxgxsb(rs.getString(46)!=null?rs.getString(46):"");
							formbean.setYdgxsb(rs.getString(47)!=null?rs.getString(47):"");
							formbean.setDxgxsb(rs.getString(48)!=null?rs.getString(48):"");
							formbean.setSjpath(rs.getString(49)!=null?rs.getString(49):"");
							formbean.setQxname1(rs.getString(50)!=null?rs.getString(50):"");
						
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
	//整改市级审核撤单
	  public synchronized String Xfshijichehui(String chooseIdStr1,String bz,String loginname) {
		    String msg = "市级撤单失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
	//String sql1="update cbzdxx x set x.SJSHBZ='2',x.sjshr='"+loginname+"',x.sjshsj=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where x.WJID in("+chooseIdStr1+")";

		      String sql1="update cbzdxx x set x.SJSHBZ='0',x.sjshr='',x.sjshsj='' where x.WJID in("+chooseIdStr1+")";
		      
		      
		      msg = "市级撤单失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("市级撤单:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "市级撤单成功！";
			  }else{
			     msg = "市级撤单失败！";
			  }
		    } catch (DbException de) {
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
	  //整改省级审核撤单
	  public synchronized String Xfshengjituihui(String chooseIdStr1,String bz,String loginname) {
		    String msg = "省级整改撤单失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
	//String sql1="update cbzdxx x set x.SJSHBZ='2',x.sjshr='"+loginname+"',x.sjshsj=to_date('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss') where x.WJID in("+chooseIdStr1+")";
		      String sql1="update cbzdxx x set x.SHENGJSHBZ='0',X.SJYQWCSJ='',x.SJYQWTGYY='',x.SHENGJSHR='',x.SHENGJISHSJ='' where x.WJID in("+chooseIdStr1+")";

		  //    String sql1="update cbzdxx x set x.SJSHBZ='0',x.sjshr='',x.sjshsj='' where x.WJID in("+chooseIdStr1+")";
		      
		      
		      msg = "省级整改撤单失败！";
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("省级整改撤单:"+sql1.toString());
		    	  db.update(sql1);	    	  
		      }
		    
		      if(bz.equals("1")){
			     msg = "省级整改撤单成功！";
			  }else{
			     msg = "省级整改撤单失败！";
			  }
		    } catch (DbException de) {
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
	  public synchronized int CheckQx(String id){//
			String sql ="select t.dsfj from cbzdxx t where t.wjid='"+id+"' and t.dsfj is not null";
			System.out.println("测试描述附件查询:"+sql);
			DataBase db = new DataBase();
			ResultSet rs =null;
			int i=0;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					
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
	  public synchronized int CheckZg(String id){//
			String sql ="select t.zgfj from cbzdxf t where t.bwjid='"+id+"' and t.zgfj is not null";
			System.out.println("完成整改附件查询:"+sql);
			DataBase db = new DataBase();
			ResultSet rs =null;
			int i=0;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					
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

	  //-------------------------------------------------获取直流负荷，空调系数
	  public synchronized List<Ktxs> getktxs() {
			List<Ktxs> list = new ArrayList<Ktxs>();
			CTime ct = new CTime();
			String kjnd = ct.formatShortDate().substring(0, 4);
			String sql = "";
			String fzzdstr = "";
			DataBase db = new DataBase();
			ResultSet rs = null;
			try {
				// fzzdstr = getFuzeZdid(db, loginId);
				sql = "SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,"
						+ "K.IDCJFKTXS,K.SJBZW,K.YYWDKTXS FROM KTXS K ";
				System.out.println("空调系数查询语句：" + sql.toString());
				db.connectDb();
				rs = db.queryAll(sql.toString());
				// Query query = new Query();
				// list = query.query(rs);
				while (rs.next()) {
					Ktxs formbean = new Ktxs();
					formbean.setKtxsid(rs.getString(1) != null ? rs.getString(1)
							: "");
					formbean.setKszlfh(rs.getString(2) != null ? rs.getString(2)
							: "");
					formbean.setJszlfh(rs.getString(3) != null ? rs.getString(3)
							: "");
					formbean.setJzktxs(rs.getString(4) != null ? rs.getString(4)
							: "");
					formbean.setJrwktxs(rs.getString(5) != null ? rs.getString(5)
							: "");
					formbean.setXzzjktxs(rs.getString(6) != null ? rs.getString(6)
							: "");
					formbean.setJyjfktxs(rs.getString(7) != null ? rs.getString(7)
							: "");
					formbean.setQtktxs(rs.getString(8) != null ? rs.getString(8)
							: "");
					formbean.setIdcjfktxs(rs.getString(9) != null ? rs.getString(9)
							: "");
					formbean.setSjbzw(rs.getString(10) != null ? rs.getString(10)
							: "");
					formbean.setYywdktxs(rs.getString(11) != null ? rs.getString(11)
							: "");

					list.add(formbean);
				}
			} catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
			// System.out.println(list.toString() + "------------------");
			return list;
		}
	  	
	  
	  
	//修改全省定标电量
	  public synchronized void UpdateQsdb(String yf,String kszlfh1,String jszlfh1,String kszlfh2,String jszlfh2,String kszlfh3,String jszlfh3,
			  String kszlfh4,String jszlfh4,String kszlfh5,String jszlfh5,String kszlfh6,
			  String jz1,String jz2,String jz3,String jz4,String jz5,String jz6,
			  String jrw1,String jrw2,String jrw3,String jrw4,String jrw5,String jrw6,
			  String xzzj1,String xzzj2,String xzzj3,String xzzj4,String xzzj5,String xzzj6,
			  String jyjf1, String jyjf2, String jyjf3, String jyjf4, String jyjf5, String jyjf6,
			  String qt1,String qt2,String qt3,String qt4,String qt5,String qt6,
			  String idcjf1,String idcjf2,String idcjf3,String idcjf4,String idcjf5,String idcjf6,
			  String yywd1,String yywd2,String yywd3,String yywd4,String yywd5,String yywd6,
			  String xs1,String xs2,String xs3,String id,int mon,List<Zdinfo> lsy) {
		    //String msg = "省级整改撤单失败！";
		    DataBase db = new DataBase();
			 List<Zdinfo> sb = scb(id);
		    try {
		      db.connectDb();
		   
				 if(sb!=null){
					 String property="";
				      double scb=0.0;
				      String zdid="";
		    		  for(Zdinfo  lst:sb){ 
		    			 property = lst.getProperty();
		    			  scb=Double.parseDouble(lst.getScb());
		    			  zdid=lst.getZdid();
		    			  String sql4="update qsdb q set q."+yf+" ="+scb+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jz1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jz2+" " +
					 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jz3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jz4+"" +
					 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jz5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jz6+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
					 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+xs1+", 'fwlx02',"+xs2+", 'fwlx03',"+xs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=q.zdID)) where q.zdid in ("+zdid+")";
					 //接入网sql    接入省定标电量=站点省生产电量*基础系数+月份系数*接入网空调值
					 String sql4_jrw="update qsdb q set q."+yf+" = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jrw1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jrw2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jrw3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jrw4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jrw5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jrw6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					 //乡镇支局
					 String sql4_xzzj="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+xzzj1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+xzzj2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+xzzj3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+xzzj4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+xzzj5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+xzzj6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//局用机房
					 String sql4_jyjf="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jyjf1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jyjf2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jyjf3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jyjf4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jyjf5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jyjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//其他
					 String sql4_qt="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+qt1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+qt2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+qt3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+qt4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+qt5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+qt6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//idc机房
					 String sql4_idcjf="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+idcjf1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+idcjf2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+idcjf3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+idcjf4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+idcjf5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+idcjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//营业网点
					 String sql4_yywd="update qsdb q set q."+yf+" = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+yywd1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+yywd2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+yywd3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+yywd4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+yywd5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+yywd6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					 
					 
					 String sql5="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jz1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jz2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jz2+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jz4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jz5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jz6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
				 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+xs1+", 'fwlx02',"+xs2+", 'fwlx03',"+xs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=q.zdID)) where q.zdid in ("+zdid+")";
					 //接入网sql    接入省定标电量=站点省生产电量*基础系数+月份系数*接入网空调值
					 String sql5_jrw="update qsdb q set q."+yf+" = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+jszlfh1+" THEN "+jrw1+" WHEN aa.ZLFH >="+jszlfh1+" AND aa.ZLFH <"+jszlfh2+" THEN "+jrw2+" " +
				 		"WHEN aa.ZLFH >= "+jszlfh2+" AND aa.ZLFH < "+jszlfh3+" THEN "+jrw3+" WHEN aa.ZLFH >= "+jszlfh3+" AND aa.ZLFH < "+jszlfh4+" THEN "+jrw4+"" +
				 		" WHEN aa.ZLFH >= "+jszlfh4+" AND aa.ZLFH < "+jszlfh5+" THEN "+jrw5+" WHEN aa.ZLFH >= "+jszlfh5+" THEN "+jrw6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					 //乡镇支局
					 String sql5_xzzj="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+jszlfh1+" THEN "+xzzj1+" WHEN aa.ZLFH >="+jszlfh1+" AND aa.ZLFH <"+jszlfh2+" THEN "+xzzj2+" " +
				 		"WHEN aa.ZLFH >= "+jszlfh2+" AND aa.ZLFH < "+jszlfh3+" THEN "+xzzj3+" WHEN aa.ZLFH >= "+jszlfh3+" AND aa.ZLFH < "+jszlfh4+" THEN "+xzzj4+"" +
				 		" WHEN aa.ZLFH >= "+jszlfh4+" AND aa.ZLFH < "+jszlfh5+" THEN "+xzzj5+" WHEN aa.ZLFH >= "+jszlfh5+" THEN "+xzzj6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//局用机房
					 String sql5_jyjf="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+jszlfh1+" THEN "+jyjf1+" WHEN aa.ZLFH >="+jszlfh1+" AND aa.ZLFH <"+jszlfh2+" THEN "+jyjf2+" " +
				 		"WHEN aa.ZLFH >= "+jszlfh2+" AND aa.ZLFH < "+jszlfh3+" THEN "+jyjf3+" WHEN aa.ZLFH >= "+jszlfh3+" AND aa.ZLFH < "+jszlfh4+" THEN "+jyjf4+"" +
				 		" WHEN aa.ZLFH >= "+jszlfh4+" AND aa.ZLFH < "+jszlfh5+" THEN "+jyjf5+" WHEN aa.ZLFH >= "+jszlfh5+" THEN "+jyjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//其他
					 String sql5_qt="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+jszlfh1+" THEN "+qt1+" WHEN aa.ZLFH >="+jszlfh1+" AND aa.ZLFH <"+jszlfh2+" THEN "+qt2+" " +
				 		"WHEN aa.ZLFH >= "+jszlfh2+" AND aa.ZLFH < "+jszlfh3+" THEN "+qt3+" WHEN aa.ZLFH >= "+jszlfh3+" AND aa.ZLFH < "+jszlfh4+" THEN "+qt4+"" +
				 		" WHEN aa.ZLFH >= "+jszlfh4+" AND aa.ZLFH < "+jszlfh5+" THEN "+qt5+" WHEN aa.ZLFH >= "+jszlfh5+" THEN "+qt6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//idc机房
					 String sql5_idcjf="update qsdb q set q."+yf+" = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+jszlfh1+" THEN "+idcjf1+" WHEN aa.ZLFH >="+jszlfh1+" AND aa.ZLFH <"+jszlfh2+" THEN "+idcjf2+" " +
				 		"WHEN aa.ZLFH >= "+jszlfh2+" AND aa.ZLFH < "+jszlfh3+" THEN "+idcjf3+" WHEN aa.ZLFH >= "+jszlfh3+" AND aa.ZLFH < "+jszlfh4+" THEN "+idcjf4+"" +
				 		" WHEN aa.ZLFH >= "+jszlfh4+" AND aa.ZLFH < "+jszlfh5+" THEN "+idcjf5+" WHEN aa.ZLFH >= "+jszlfh5+" THEN "+idcjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
					//营业网点
					 String sql5_yywd="update qsdb q set q."+yf+" = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+jszlfh1+" THEN "+yywd1+" WHEN aa.ZLFH >="+jszlfh1+" AND aa.ZLFH <"+jszlfh2+" THEN "+yywd2+" " +
				 		"WHEN aa.ZLFH >= "+jszlfh2+" AND aa.ZLFH < "+jszlfh3+" THEN "+yywd3+" WHEN aa.ZLFH >= "+jszlfh3+" AND aa.ZLFH < "+jszlfh4+" THEN "+yywd4+"" +
				 		" WHEN aa.ZLFH >= "+jszlfh4+" AND aa.ZLFH < "+jszlfh5+" THEN "+yywd5+" WHEN aa.ZLFH >= "+jszlfh5+" THEN "+yywd6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.zdid in ("+zdid+")";
		    			 if(mon<=8){
		    				 if(property.equals("zdsx02")){
		    					// System.out.println("1-8月宽泛更新全省定标:"+sql4.toString());
		    					 db.update(sql4);
		    				 }else if(property.equals("zdsx01")){
		    					// System.out.println("1-8月宽泛更新全省定标:"+sql4_jyjf.toString());
		    					 db.update(sql4_jyjf);
		    				 }else if(property.equals("zdsx03")){
			    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_yywd.toString());
			    				 db.update(sql4_yywd);
			    			 }else if(property.equals("zdsx04")){
			    				 //System.out.println("1-8月宽泛更新全省定标:"+sql4_qt.toString());
			    				 db.update(sql4_qt);
			    			 }else if(property.equals("zdsx05")){
			    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_jrw.toString());
			    				 db.update(sql4_jrw);
			    			 }else if(property.equals("zdsx06")){
			    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_xzzj.toString());
			    				 db.update(sql4_xzzj);
			    			 }else if(property.equals("zdsx07")){
			    				 //System.out.println("1-8月宽泛更新全省定标:"+sql4_idcjf.toString());
			    				 db.update(sql4_idcjf);
			    			 }
		    			 }else{
		    				 if(property.equals("zdsx02")){
		    					// System.out.println("9-12月宽泛更新全省定标:"+sql5.toString());
		    					 db.update(sql5);
		    				 }else if(property.equals("zdsx01")){
		    					// System.out.println("9-12月宽泛更新全省定标:"+sql5_jyjf.toString());
		    					 db.update(sql5_jyjf);
		    				 }else if(property.equals("zdsx03")){
			    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_yywd.toString());
			    				 db.update(sql5_yywd);
			    			 }else if(property.equals("zdsx04")){
			    				 //System.out.println("9-12月宽泛更新全省定标:"+sql5_qt.toString());
			    				 db.update(sql5_qt);
			    			 }else if(property.equals("zdsx05")){
			    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_jrw.toString());
			    				 db.update(sql5_jrw);
			    			 }else if(property.equals("zdsx06")){
			    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_xzzj.toString());
			    				 db.update(sql5_xzzj);
			    			 }else if(property.equals("zdsx07")){
			    				 //System.out.println("9-12月宽泛更新全省定标:"+sql5_idcjf.toString());
			    				 db.update(sql5_idcjf);
			    			 }
		    			 }
		    		  }
				 }
		    //  }
		    
		    //  if(bz.equals("1")){
			  //   msg = "省级整改撤单成功！";
			  //}else{
			    // msg = "省级整改撤单失败！";
			 // }
		    } catch (DbException de) {
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

		    //return msg;
		  } 

	//  public synchronized void UpdateQsdb(String yf,String kszlfh1,String jszlfh1,String kszlfh2,String jszlfh2,String kszlfh3,String jszlfh3,
			//  String kszlfh4,String jszlfh4,String kszlfh5,String jszlfh5,String kszlfh6,
//			  String jz1,String jz2,String jz3,String jz4,String jz5,String jz6,String id,int mon) {
//		    //String msg = "省级整改撤单失败！";
//		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
//		    DataBase db = new DataBase();
//		    String str = "";
//	    	CTime ctime = new CTime(); 
//			String inserttime = ctime.formatWholeDate(new Date());
//
//		    try {
//		      db.connectDb();
//				 String sql4="update qsdb q set q."+yf+" = (select s.scb from scb s where s.zdid = q.zdid) * (1 + " +
//				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jz1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jz2+" " +
//				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jz3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jz4+"" +
//				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jz5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jz6+"" +
//				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
//				 		" +(select z.fwxs from zhandian z where q.zdid = z.id)) where q.zdid in ("+id+")";
//				 String sql5="update qsdb q set q."+yf+" = (select s.scb from scb s where s.zdid = q.zdid) * (1 + " +
//			 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jz1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jz2+" " +
//			 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jz2+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jz4+"" +
//			 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jz5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jz6+"" +
//			 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
//			 		" +(select z.fwxs from zhandian z where q.zdid = z.id)) where q.zdid in ("+id+")";
//		  
//		      //msg = "省级整改撤单失败！";
//		      //if(chooseIdStr1!=""&&chooseIdStr1!=null){
//		    	  if(mon<=8){
//		    		  System.out.println("1-8月宽泛更新全省定标:"+sql4.toString());
//		    		  db.update(sql4);
//		    	  }else{
//		    		  System.out.println("9-12月宽泛更新全省定标:"+sql5.toString());
//		    		  db.update(sql5);
//		    	  }
//		    //  }
//		    
//		    //  if(bz.equals("1")){
//			  //   msg = "省级整改撤单成功！";
//			  //}else{
//			    // msg = "省级整改撤单失败！";
//			 // }
//		    } catch (DbException de) {
//		      try {
//		        db.rollback();
//		      }
//		      catch (DbException dee) {
//		        dee.printStackTrace();
//		      }
//		      de.printStackTrace();
//		    }
//		    finally {
//		      try {
//
//		        db.close();
//		      }
//		      catch (Exception de) {
//		        de.printStackTrace();
//		      }
//		    }
//
//		    //return msg;
//		  } 
//	  public synchronized void UpQsdb(String yf,String kszlfh1,String jszlfh1,String kszlfh2,String jszlfh2,String kszlfh3,String jszlfh3,
//			  String kszlfh4,String jszlfh4,String kszlfh5,String jszlfh5,String kszlfh6,
//			  String jz1,String jz2,String jz3,String jz4,String jz5,String jz6,String id,int mon) {
//		    //String msg = "省级整改撤单失败！";
//		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
//		    DataBase db = new DataBase();
//		    String str = "";
//	    	CTime ctime = new CTime(); 
//			String inserttime = ctime.formatWholeDate(new Date());
//
//		    try {
//		      db.connectDb();
//				 String sql4="update zhandian q set q.qsdbdl = (select s.scb from scb s where s.zdid = q.id) * (1 + " +
//				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jz1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jz2+" " +
//				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jz3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jz4+"" +
//				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jz5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jz6+"" +
//				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
//				 		" +(select z.fwxs from zhandian z where q.id = z.id)) where q.id in ("+id+")";
//				 String sql5="update zhandian q set q.qsdbdl = (select s.scb from scb s where s.zdid = q.id) * (1 + " +
//			 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jz1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jz2+" " +
//			 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jz2+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jz4+"" +
//			 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jz5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jz6+"" +
//			 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
//			 		" +(select z.fwxs from zhandian z where q.id = z.id)) where q.id in ("+id+")";
//		  
//		      
//		    	  if(mon<=8){
//		    		  System.out.println("站点1-8月宽泛更新全省定标:"+sql4.toString());
//		    		  db.update(sql4);
//		    	  }else{
//		    		  System.out.println("站点9-12月宽泛更新全省定标:"+sql5.toString());
//		    		  db.update(sql5);
//		    	  }
//		    } catch (DbException de) {
//		      try {
//		        db.rollback();
//		      }
//		      catch (DbException dee) {
//		        dee.printStackTrace();
//		      }
//		      de.printStackTrace();
//		    }
//		    finally {
//		      try {
//
//		        db.close();
//		      }
//		      catch (Exception de) {
//		        de.printStackTrace();
//		      }
//		    }
//
//		    //return msg;
//		  } 
	  public synchronized void UpQsdb(String yf,String kszlfh1,String jszlfh1,String kszlfh2,String jszlfh2,String kszlfh3,String jszlfh3,
			  String kszlfh4,String jszlfh4,String kszlfh5,String jszlfh5,String kszlfh6,
			  String jz1,String jz2,String jz3,String jz4,String jz5,String jz6,
			  String jrw1,String jrw2,String jrw3,String jrw4,String jrw5,String jrw6,
			  String xzzj1,String xzzj2,String xzzj3,String xzzj4,String xzzj5,String xzzj6,
			  String jyjf1, String jyjf2, String jyjf3, String jyjf4, String jyjf5, String jyjf6,
			  String qt1,String qt2,String qt3,String qt4,String qt5,String qt6,
			  String idcjf1,String idcjf2,String idcjf3,String idcjf4,String idcjf5,String idcjf6,
			  String yywd1,String yywd2,String yywd3,String yywd4,String yywd5,String yywd6,
			  String xs1,String xs2,String xs3,String id,int mon,List<Zdinfo> lsy) {
		    //String msg = "省级整改撤单失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
		    List<Zdinfo> sb = scb(id);
		   
		    //if(sb==""&&sb.equals("")){
		    	//scb=0.00;
		    //}else{
		    	//scb = Double.parseDouble(sb);
		   // }
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());

		    try {
		      db.connectDb();
		      String property="";
		      double scb;
		      String zdid="";
				
				 if(sb!=null){
		    		  for(Zdinfo  lst:sb){ 
		    			  property = lst.getProperty();
		    			  scb=Double.parseDouble(lst.getScb());
		    			  zdid=lst.getZdid();
		    			  String sql4="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jz1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jz2+" " +
					 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jz3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jz4+"" +
					 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jz5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jz6+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
					 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+xs1+", 'fwlx02',"+xs2+", 'fwlx03',"+xs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=q.ID)) where q.id in ("+zdid+")";
					 //接入网
					 String sql4_jrw="update zhandian q set q.qsdbdl = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jrw1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jrw2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jrw3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jrw4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jrw5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jrw6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.id in ("+zdid+")";
					//乡镇支局
					 String sql4_xzzj="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+xzzj1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+xzzj2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+xzzj3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+xzzj4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+xzzj5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+xzzj6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.id in ("+zdid+")";
					//局用机房
					 String sql4_jyjf="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jyjf1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jyjf2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jyjf3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jyjf4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jyjf5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jyjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.id in ("+zdid+")";
					//其他
					 String sql4_qt="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+qt1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+qt2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+qt3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+qt4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+qt5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+qt6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.id in ("+zdid+")";
					//idc机房
					 String sql4_idcjf="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+idcjf1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+idcjf2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+idcjf3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+idcjf4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+idcjf5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+idcjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.id in ("+zdid+")";
					//营业网点
					 String sql4_yywd="update zhandian q set q.qsdbdl = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+yywd1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+yywd2+" " +
				 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+yywd3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+yywd4+"" +
				 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+yywd5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+yywd6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		" where q.id in ("+zdid+")";
					 
					 
					 
					 String sql5="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jz1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jz2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jz2+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jz4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jz5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jz6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi)" +
				 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+xs1+", 'fwlx02',"+xs2+", 'fwlx03',"+xs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=q.ID)) where q.id in ("+zdid+")";
					 //接入网
					 String sql5_jrw="update zhandian q set q.qsdbdl = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jrw1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jrw2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jrw3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jrw4+" THEN "+jrw4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jrw5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jrw6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		"where q.id in ("+zdid+")";
					//乡镇支局
					 String sql5_xzzj="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+xzzj1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+xzzj2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+xzzj3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+xzzj4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+xzzj5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+xzzj6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		"where q.id in ("+zdid+")";
					//局用机房
					 String sql5_jyjf="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jyjf1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jyjf2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jyjf3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jyjf4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jyjf5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jyjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		"where q.id in ("+zdid+")";
					//其他
					 String sql5_qt="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+qt1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+qt2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+qt3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+qt4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+qt5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+qt6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		"where q.id in ("+zdid+")";
					//idc机房
					 String sql5_idcjf="update zhandian q set q.qsdbdl = "+scb+" * (1 + " +
				 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+idcjf1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+idcjf2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+idcjf3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+idcjf4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+idcjf5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+idcjf6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		"where q.id in ("+zdid+")";
					//营业网点
					 String sql5_yywd="update zhandian q set q.qsdbdl = "+scb+" * 1 + " +
				 		"((SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+yywd1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+yywd2+" " +
				 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+yywd3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+yywd4+"" +
				 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+yywd5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+yywd6+"" +
				 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+yf+" from yfxs y where y.shicode = q.shi))" +
				 		"where q.id in ("+zdid+")";  
		    	  if(mon<=8){
		    		  if(property.equals("zdsx02")){
		    			  //System.out.println("站点1-8月宽泛更新全省定标:"+sql4.toString());
		    			  db.update(sql4);
		    		  }else if(property.equals("zdsx01")){
	    					// System.out.println("1-8月宽泛更新全省定标:"+sql4_jyjf.toString());
	    					 db.update(sql4_jyjf);
	    				}else if(property.equals("zdsx03")){
		    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_yywd.toString());
		    				 db.update(sql4_yywd);
		    			 }else if(property.equals("zdsx04")){
		    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_qt.toString());
		    				 db.update(sql4_qt);
		    			 }else if(property.equals("zdsx05")){
		    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_jrw.toString());
		    				 db.update(sql4_jrw);
		    			 }else if(property.equals("zdsx06")){
		    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_xzzj.toString());
		    				 db.update(sql4_xzzj);
		    			 }else if(property.equals("zdsx07")){
		    				// System.out.println("1-8月宽泛更新全省定标:"+sql4_idcjf.toString());
		    				 db.update(sql4_idcjf);
		    			 }
		    	  }else{
		    		  if(property.equals("zdsx02")){
		    			 // System.out.println("站点9-12月宽泛更新全省定标:"+sql5.toString());
		    			  db.update(sql5);
		    		  }else if(property.equals("zdsx01")){
	    					// System.out.println("9-12月宽泛更新全省定标:"+sql5_jyjf.toString());
	    					 db.update(sql5_jyjf);
	    				 }else if(property.equals("zdsx03")){
		    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_yywd.toString());
		    				 db.update(sql5_yywd);
		    			 }else if(property.equals("zdsx04")){
		    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_qt.toString());
		    				 db.update(sql5_qt);
		    			 }else if(property.equals("zdsx05")){
		    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_jrw.toString());
		    				 db.update(sql5_jrw);
		    			 }else if(property.equals("zdsx06")){
		    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_xzzj.toString());
		    				 db.update(sql5_xzzj);
		    			 }else if(property.equals("zdsx07")){
		    				// System.out.println("9-12月宽泛更新全省定标:"+sql5_idcjf.toString());
		    				 db.update(sql5_idcjf);
		    			 }
		    	  }
		    		  }
		    	}
		    } catch (DbException de) {
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

		    //return msg;
		  }  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	  public synchronized String Sjshxx(String jlfh,String zlfh,String edhdl,String qsdb,String id,String loginname) {
		    String msg = "市级审核修改失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());
             ResultSet rs=null;
		    try {
		      db.connectDb();

		      String sql1="update cbzdxx x set x.ZLZFH="+zlfh+",x.JLZFH="+jlfh+",x.BDEDHDL="+edhdl+",x.QSDB="+qsdb+" where x.WJID in("+id+")";
		      
		      msg = "市级审核修改失败！ ";
		    	  System.out.println("市级撤单:"+sql1.toString());
		    	 rs=db.queryAll(sql1);
		    	try {
					if(rs.next()){
						msg="市级审核修改信息成功！";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}  db.update(sql1);	    	  
		    
		    } catch (DbException de) {
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
	  public synchronized List<Zdinfo> seachSheng(String bean,String id){
			List<Zdinfo> retList=new ArrayList<Zdinfo>();
			DataBase db = new DataBase();
			ResultSet rs = null;
			String fzzdstr="";
			try{
				// fzzdstr = getFuzeZdid(db,loginId);
				String sql = 
					 " select z.id zdid, x.bdedhdl,x.jlzfh, x.zlzfh,z.property,x.qsdb from cbzd c, cbzdxx x, zhandian z where c.id = x.wjid  and c.zdid = z.id  and z.id in ("+bean+") and c.id in("+id+") ";
				System.out.println("省审核查询新信息sql："+sql);
				db.connectDb();
				rs = db.queryAll(sql);
				while(rs.next()){
					Zdinfo ret = new Zdinfo();
					 ret.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
					 ret.setEdhdl(rs.getString("BDEDHDL")!=null?rs.getString("BDEDHDL"):"0");
					 ret.setJlzfh(rs.getString("JLZFH")!=null?rs.getString("JLZFH"):"0");
					 ret.setZlzfh(rs.getString("ZLZFH")!=null?rs.getString("ZLZFH"):"0");
					 ret.setProperty(rs.getString("PROPERTY")!=null?rs.getString("PROPERTY"):"");
					 ret.setQsdb(rs.getString("QSDB")!=null?rs.getString("QSDB"):"0");
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
	  public synchronized List<Zdinfo> seachShengb(String bean){
			List<Zdinfo> retList=new ArrayList<Zdinfo>();
			DataBase db = new DataBase();
			ResultSet rs = null;
			String fzzdstr="";
			try{
				// fzzdstr = getFuzeZdid(db,loginId);
				String sql = 
					 " select z.id zdid, x.bdedhdl,x.jlzfh, x.zlzfh,z.property,x.qsdb from cbzd c, cbzdxx x, zhandian z where c.id = x.wjid  and c.zdid = z.id  and z.id in ("+bean+")";
				System.out.println("省审核查询新信息sql："+sql);
				db.connectDb();
				rs = db.queryAll(sql);
				while(rs.next()){
					Zdinfo ret = new Zdinfo();
					 ret.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
					 ret.setEdhdl(rs.getString("BDEDHDL")!=null?rs.getString("BDEDHDL"):"0");
					 ret.setJlzfh(rs.getString("JLZFH")!=null?rs.getString("JLZFH"):"0");
					 ret.setZlzfh(rs.getString("ZLZFH")!=null?rs.getString("ZLZFH"):"0");
					 ret.setProperty(rs.getString("PROPERTY")!=null?rs.getString("PROPERTY"):"");
					 ret.setQsdb(rs.getString("QSDB")!=null?rs.getString("QSDB"):"0");
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
	  //省审核更新jzxx表查询scb
	  public synchronized List<Zdinfo> scb(String zdid){
			DataBase db = new DataBase();
			ResultSet rs = null;
			List<Zdinfo> retList=new ArrayList<Zdinfo>();
			try{
				String sql = "select s.scb,z.id,z.property from scb s,zhandian z where  z.id=s.zdid and s.zdid in("+zdid+")";
				System.out.println("省审核更新jzxx表查询scb sql："+sql);
				db.connectDb();
				rs = db.queryAll(sql);
				while(rs.next()){
					Zdinfo ret = new Zdinfo();
					ret.setScb(rs.getString(1)!=null?rs.getString(1):"0");
					ret.setZdid(rs.getString(2)!=null?rs.getString(2):"");
					ret.setProperty(rs.getString(3)!=null?rs.getString(3):"");
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
	  public synchronized void UpJzxx(String yf,String kszlfh1,String jszlfh1,String kszlfh2,String jszlfh2,String kszlfh3,String jszlfh3,
			  String kszlfh4,String jszlfh4,String kszlfh5,String jszlfh5,String kszlfh6,
			  String jz1,String jz2,String jz3,String jz4,String jz5,String jz6,
			  String jrw1,String jrw2,String jrw3,String jrw4,String jrw5,String jrw6,
			  String xzzj1,String xzzj2,String xzzj3,String xzzj4,String xzzj5,String xzzj6,
			  String jyjf1, String jyjf2, String jyjf3, String jyjf4, String jyjf5, String jyjf6,
			  String qt1,String qt2,String qt3,String qt4,String qt5,String qt6,
			  String idcjf1,String idcjf2,String idcjf3,String idcjf4,String idcjf5,String idcjf6,
			  String yywd1,String yywd2,String yywd3,String yywd4,String yywd5,String yywd6,
			  String xs1,String xs2,String xs3,String id,int mon,String bzmonth,List<Zdinfo> lsy) {
		    //String msg = "省级整改撤单失败！";
		    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		    DataBase db = new DataBase();
		    String str = "";
	    	CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());
			 List<Zdinfo> sb = scb(id);//查询生产标 
		  
		  
		    try {
		      db.connectDb();
		    
		     
		      
		      if(sb!=null){
		    	  String property="";
			      double scb;
			      String zdid="";
	    		  for(Zdinfo  lst:sb){ 
	    			  property = lst.getProperty();
	    			  scb=Double.parseDouble(lst.getScb());
	    			  zdid=lst.getZdid();
	    			  String sql4="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jz1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jz2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jz3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jz4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jz5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jz6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi)" +
	  		 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+xs1+", 'fwlx02',"+xs2+", 'fwlx03',"+xs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=q.ZDID)) where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		      //接入网
	  		      String sql4_jrw="update jzxx q set q.qsdbdl = "+scb+" * 1 + " +
	  		 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jrw1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jrw2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jrw3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jrw4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jrw5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jrw6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		    //乡镇支局
	  		      String sql4_xzzj="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+xzzj1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+xzzj2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+xzzj3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+xzzj4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+xzzj5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+xzzj6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		    //局用机房
	  		      String sql4_jyjf="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+jyjf1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+jyjf2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+jyjf3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+jyjf4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+jyjf5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+jyjf6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		    //其他
	  		      String sql4_qt="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+qt1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+qt2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+qt3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+qt4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+qt5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+qt6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		    //idc机房
	  		      String sql4_idcjf="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+idcjf1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+idcjf2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+idcjf3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+idcjf4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+idcjf5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+idcjf6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		    //营业网点
	  		      String sql4_yywd="update jzxx q set q.qsdbdl = "+scb+" * 1 + " +
	  		 		"((SELECT (CASE WHEN aa.ZLFH >="+kszlfh1+" AND aa.ZLFH <"+kszlfh2+" THEN "+yywd1+" WHEN aa.ZLFH >="+kszlfh2+" AND aa.ZLFH <"+kszlfh3+" THEN "+yywd2+" " +
	  		 		"WHEN aa.ZLFH >= "+kszlfh3+" AND aa.ZLFH < "+kszlfh4+" THEN "+yywd3+" WHEN aa.ZLFH >= "+kszlfh4+" AND aa.ZLFH < "+kszlfh5+" THEN "+yywd4+"" +
	  		 		" WHEN aa.ZLFH >= "+kszlfh5+" AND aa.ZLFH < "+kszlfh6+" THEN "+yywd5+" WHEN aa.ZLFH >= "+kszlfh6+" THEN "+yywd6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+")and q.symonth='"+bzmonth+"'";
	  		      
	  		      
	  		      
	  		      String sql5="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  	 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jz1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jz2+" " +
	  	 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jz3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jz4+"" +
	  	 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jz5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jz6+"" +
	  	 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi)" +
	  	 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+xs1+", 'fwlx02',"+xs2+", 'fwlx03',"+xs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=q.ZDID)) where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
	  		      //接入网
	  		      String sql5_jrw="update jzxx q set q.qsdbdl = "+scb+" * 1 + " +
	  		 		"((SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jrw1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jrw2+" " +
	  		 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jrw3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jrw4+"" +
	  		 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jrw5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jrw6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
	  		    //乡镇支局
	  		      String sql5_xzzj="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+xzzj1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+xzzj2+" " +
	  		 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+xzzj3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+xzzj4+"" +
	  		 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+xzzj5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+xzzj6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
	  		    //局用机房
	  		      String sql5_jyjf="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+jyjf1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+jyjf2+" " +
	  		 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+jyjf3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+jyjf4+"" +
	  		 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+jyjf5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+jyjf6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
	  		    //其他
	  		      String sql5_qt="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+qt1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+qt2+" " +
	  		 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+qt3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+qt4+"" +
	  		 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+qt5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+qt6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
	  		    //idc机房
	  		      String sql5_idcjf="update jzxx q set q.qsdbdl = "+scb+" * (1 + " +
	  		 		"(SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+idcjf1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+idcjf2+" " +
	  		 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+idcjf3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+idcjf4+"" +
	  		 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+idcjf5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+idcjf6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
	  		    //营业网点
	  		      String sql5_yywd="update jzxx q set q.qsdbdl = "+scb+" * 1 + " +
	  		 		"((SELECT (CASE WHEN aa.ZLFH >= "+kszlfh1+" AND aa.ZLFH <= "+jszlfh1+" THEN "+yywd1+" WHEN aa.ZLFH > "+jszlfh1+" AND aa.ZLFH <= "+jszlfh2+" THEN "+yywd2+" " +
	  		 		"WHEN aa.ZLFH > "+jszlfh2+" AND aa.ZLFH <= "+jszlfh3+" THEN "+yywd3+" WHEN aa.ZLFH > "+jszlfh3+" AND aa.ZLFH <= "+jszlfh4+" THEN "+yywd4+"" +
	  		 		" WHEN aa.ZLFH > "+jszlfh4+" AND aa.ZLFH <= "+jszlfh5+" THEN "+yywd5+" WHEN aa.ZLFH > "+jszlfh5+" THEN "+yywd6+"" +
	  		 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.zdid) * (select y."+yf+" from yfxs y where y.shiname = q.shi))" +
	  		 		" where q.zdid in ("+zdid+") and q.symonth='"+bzmonth+"'";
				if(mon<=8){
						if(property.equals("zdsx02")){
		 					//System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4.toString());
		 					db.update(sql4);
						}else if(property.equals("zdsx01")){
	    					// System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4_jyjf.toString());
	    					 db.update(sql4_jyjf);
	    				 }else if(property.equals("zdsx03")){
		    				// System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4_yywd.toString());
		    				 db.update(sql4_yywd);
		    			 }else if(property.equals("zdsx04")){
		    				// System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4_qt.toString());
		    				 db.update(sql4_qt);
		    			 }else if(property.equals("zdsx05")){
		    				// System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4_jrw.toString());
		    				 db.update(sql4_jrw);
		    			 }else if(property.equals("zdsx06")){
		    				// System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4_xzzj.toString());
		    				 db.update(sql4_xzzj);
		    			 }else if(property.equals("zdsx07")){
		    				// System.out.println("jzxx1-8月宽泛更新全省定标:"+sql4_idcjf.toString());
		    				 db.update(sql4_idcjf);
		    			 }
		 			}else{
		 				if(property.equals("zdsx02")){
		 					//System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5.toString());
			 				db.update(sql5);
			    		  }else if(property.equals("zdsx01")){
		    					// System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5_jyjf.toString());
		    					 db.update(sql5_jyjf);
		    				 }else if(property.equals("zdsx03")){
			    				// System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5_yywd.toString());
			    				 db.update(sql5_yywd);
			    			 }else if(property.equals("zdsx04")){
			    				// System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5_qt.toString());
			    				 db.update(sql5_qt);
			    			 }else if(property.equals("zdsx05")){
			    				// System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5_jrw.toString());
			    				 db.update(sql5_jrw);
			    			 }else if(property.equals("zdsx06")){
			    				// System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5_xzzj.toString());
			    				 db.update(sql5_xzzj);
			    			 }else if(property.equals("zdsx07")){
			    				// System.out.println("jzxx9-12月宽泛更新全省定标:"+sql5_idcjf.toString());
			    				 db.update(sql5_idcjf);
			    			 }
		 			}
		 				
		 		}
	    	 }
		    } catch (DbException de) {
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

	  public synchronized int SHIUpdateZDZ(String id,String idd,List<Zdinfo> ls,String syf,String b1,String b2,String b3,String bb5,String bb6,String bb7,String bb8,String bb9,String bb10,String bb11,String bb12,String bb13,String bb14,String bb15,String bb16,String bb17,String bb18,String bb19,String bb20,String bb22
				 ,String dd5,String dd6,String dd7,String dd8,String dd9,String dd10,String dd11,String dd12,String dd13,String dd14,String dd15,String dd16,String dd17,String dd18,String dd19,String dd20,String dd22,String xx5,String xx6,String xx7,String xx8,String xx9,String xx10,String xx11,String xx12,String xx13,String xx14,String xx15,String xx16,String xx17
				 ,String xx18,String xx19,String xx20,String xx22,String yy5,String yy6,String yy7,String yy8,String yy9,String yy10,String yy11,String yy12,String yy13,String yy14,String yy15,String yy16,String yy17,String yy18,String yy19,String yy20,String yy22,
				 String qq5,String qq6,String qq7,String qq8,String qq9,String qq10,String qq11,String qq12,String qq13,String qq14,String qq15,String qq16,String qq17,String qq18,String qq19,String qq20,String qq22,
				 String ii5,String ii6,String ii7,String ii8,String ii9,String ii10,String ii11,String ii12,String ii13,String ii14,String ii15,String ii16,String ii17,String ii18,String ii19,String ii20,String ii22,
				 String jj5,String jj6,String jj7,String jj8,String jj9,String jj10,String jj11,String jj12,String jj13,String jj14,String jj15,String jj16,String jj17,String jj18,String jj19,String jj20,String jj22,String lg,List<XxxgBean> lsbean ){
			 	int msg=0;
		    	CTime ctime = new CTime();
				String inserttime = ctime.formatWholeDate(new Date());
				String sql ="";
				DataBase db = new DataBase();
			
				// " SELECT KZ.NEWPROPERTY,KZ.NEWSTATIONTYPE, KZ.NEWYFLX,KZ.NEWGXXX,KZ.NEWGDFS,KZ.NEWZGD,KZ.NEWAREA,KZ.NEWQYZT," +
		//  " kz.newg2,kz.newg3,kz.newzpsl,kz.newzssl,kz.newchangjia,kz.newjzlx,kz.newshebei,kz.newbbu,kz.newrru,kz.newydshebei,kz.newgxgwsl" +
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String sff=df.format(new Date());
		 String ssyf=	sff.toString().substring(5, 7);
		 int j=Integer.parseInt(ssyf);
			System.out.println("月份："+sff.toString());
			String yf=sff.toString().substring(5,7);
			ResultSet rs=null;
			ResultSet rs1=null;
			ResultSet rs2=null;
			ResultSet rs3=null;
			ResultSet rs4=null;
			ResultSet rs5=null;
			ResultSet rs6=null;
			
			ResultSet rs7=null;
			ResultSet rs8=null;
			ResultSet rs9=null;
			ResultSet rs10=null;
			ResultSet rs11=null;
			ResultSet rs12=null;
			String sj="SYSDATE";
//			 ret.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
//			 ret.setEdhdl(rs.getString("BDEDHDL")!=null?rs.getString("BDEDHDL"):"0");
//			 ret.setJlzfh(rs.getString("JLZFH")!=null?rs.getString("JLZFH"):"0");
//			 ret.setZlzfh(rs.getString("ZLZFH")!=null?rs.getString("ZLZFH"):"0");
//			 ret.setProperty(rs.getString("PROPERTY")!=null?rs.getString("PROPERTY"):"");
		
			try{
				if(ls!=null){
					for(Zdinfo lst:ls){
						
//						 ret.setJlzfh(rs.getString("JLZFH")!=null?rs.getString("JLZFH"):"0");
//						 ret.setZlzfh(rs.getString("ZLZFH")!=null?rs.getString("ZLZFH"):"0");
						if(lst.getQsdb()!=null&&!"".equals(lst.getQsdb())){
							//System.out.println("zhandian shuxing:"+lst.getProperty()+" zlfh:"+lst.getZlfh());
						
	                        if("zdsx01".equals(lst.getProperty())){
	                        	String zdsx="",zlfh="",jlfh="";
	                        	String sqlsb="SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx01'";
	                        	rs7=db.queryAll(sqlsb);
	                        	while(rs7.next()){
	                        		zlfh=rs7.getString(1);
	                        		if(zlfh.equals("")||null==zlfh){
	                    				zlfh="0";
	                    			}
	                        		jlfh=rs7.getString(2);
	                        		if(jlfh.equals("")||null==jlfh){
	                        			jlfh="0";
	                    			}
	                        	}
	                    		
	                        	String sqldzl="";
	                        	double dzl=0,djl=0;
	                        	
	                        	String newzlfh=lst.getZlzfh();
	                        	if("".equals(newzlfh)||null==newzlfh){
	                        		newzlfh="0";
	                        	}
	                        	String newjlfh=lst.getJlzfh();
	                        	if("".equals(newjlfh)||null==newjlfh){
	                        		newjlfh="0";
	                        	}
	                        	dzl=Double.parseDouble(newzlfh);
	                        	djl=Double.parseDouble(newjlfh);
	                           if(dzl<=Double.parseDouble(zlfh)&&djl<=Double.parseDouble(jlfh)){

								//System.out.println("站点属性为局用机房zdsx01："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								// System.out.println("站点属性为局用机房的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlzfh();
								s2=lst.getJlzfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*54*24/1000/0.85);
								dd=(Double.parseDouble(s2)*220*24/1000);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
								
								System.out.println("生产标s："+s);
								
								//sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
							//	db.queryAll(sql3);
								int r2;
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								r2=db.update(sql3);
								if(r2==0){
									String sql22="";
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增局用机房无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * (1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+jj5+" AND aa.ZLFH <="+jj6+" THEN "+jj7+" WHEN aa.ZLFH >"+jj6+" AND aa.ZLFH <="+jj9+" THEN "+jj10+" " +
								 		"WHEN aa.ZLFH > "+jj9+" AND aa.ZLFH <= "+jj12+" THEN "+jj13+" WHEN aa.ZLFH > "+jj12+" AND aa.ZLFH <= "+jj15+" THEN "+jj16+"" +
								 		" WHEN aa.ZLFH > "+jj15+" AND aa.ZLFH <= "+jj18+" THEN "+jj19+" WHEN aa.ZLFH > "+jj18+" THEN "+jj22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		//" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		" ),q.scb="+qs+" where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+jj5+" AND aa.ZLFH <"+jj6+" THEN "+jj7+" WHEN aa.ZLFH >="+jj6+" AND aa.ZLFH <"+jj8+" THEN "+jj10+" " +
					 		"WHEN aa.ZLFH >= "+jj8+" AND aa.ZLFH <"+jj11+" THEN "+jj13+" WHEN aa.ZLFH >= "+jj11+" AND aa.ZLFH < "+jj14+" THEN "+jj16+"" +
					 		" WHEN aa.ZLFH >= "+jj14+" AND aa.ZLFH < "+jj17+" THEN "+jj19+" WHEN aa.ZLFH >= "+jj17+" THEN "+jj22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		// " +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
					 		" ),q.scb="+qs+" where q.id="+lst.getZdid()+"";
							
								}
								
								
							System.out.println("局用机房ql2："+sql2.toString());
							System.out.println("局用机房sql3："+sql3.toString());
							System.out.println("局用机房更新站点全省定标电量ssql："+ssql.toString());
							
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
								
							
	                        }
	                        }else if("zdsx02".equals(lst.getProperty())){
	                        	
	                        	String zdsx="",zlfh="",jlfh="";
	                        	String sqlsb="SELECT X.ZLFH FROM XXXG X WHERE X.ZDSX='zdsx02'";
	                        	rs8=db.queryAll(sqlsb);
	                        	while(rs8.next()){
	                        		zlfh=rs8.getString(1);
	                        		if(zlfh.equals("")||null==zlfh){
	                    				zlfh="0";
	                    			}
	                        		
	                        	}
	                        	//System.out.println("zlfh标准的:"+zlfh);
	                        	
	                        	String sqldzl="";
	                        	double dzl=0;
	                        	String newzlfh=lst.getZlzfh();
	                        	//System.out.println("newzlfh:"+newzlfh);
	                        	if("".equals(newzlfh)||null==newzlfh){
	                        		newzlfh="0";
	                        	}
	                        	dzl=Double.parseDouble(newzlfh);
	                        	
	                           if(dzl<=Double.parseDouble(zlfh)){
								//System.out.println("站点属性为基站："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								//System.out.println("站点属性为基站的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlzfh();
								s2=lst.getJlzfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*1.52);
								dd=(Double.parseDouble(s2)*5.28);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
								int r2;
								System.out.println("生产标s："+s);
								String sql22="";
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								 r2=db.update(sql3);
								// rs2=db.queryAll(sql3);
								if(r2==0){
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-08',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增基站无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * (1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+bb5+" AND aa.ZLFH <="+bb6+" THEN "+bb7+" WHEN aa.ZLFH >"+bb6+" AND aa.ZLFH <="+bb9+" THEN "+bb10+" " +
								 		"WHEN aa.ZLFH > "+bb9+" AND aa.ZLFH <= "+bb12+" THEN "+bb13+" WHEN aa.ZLFH > "+bb12+" AND aa.ZLFH <= "+bb15+" THEN "+bb16+"" +
								 		" WHEN aa.ZLFH > "+bb15+" AND aa.ZLFH <= "+bb18+" THEN "+bb19+" WHEN aa.ZLFH > "+bb18+" THEN "+bb22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		" ),q.scb="+qs+" where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+bb5+" AND aa.ZLFH <"+bb6+" THEN "+bb7+" WHEN aa.ZLFH >="+bb6+" AND aa.ZLFH <"+bb8+" THEN "+bb10+" " +
					 		"WHEN aa.ZLFH >= "+bb8+" AND aa.ZLFH <"+bb11+" THEN "+bb13+" WHEN aa.ZLFH >= "+bb11+" AND aa.ZLFH < "+bb14+" THEN "+bb16+"" +
					 		" WHEN aa.ZLFH >= "+bb14+" AND aa.ZLFH < "+bb17+" THEN "+bb19+" WHEN aa.ZLFH >= "+bb17+" THEN "+bb22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
					 		" ),q.scb="+qs+" where q.id="+lst.getZdid()+"";
							
								}
								
							//System.out.println("基站sql2："+sql2.toString());
							//System.out.println("基站sql3："+sql3.toString());
							//System.out.println("基站ssql："+ssql.toString());
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
	                        }
	                    		
	                        }else if("zdsx03".equals(lst.getProperty())){
	                        	String zdsx="",zlfh="",jlfh="";
	                        	String sqlsb="SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx03'";
	                        	rs9=db.queryAll(sqlsb);
	                        	while(rs9.next()){
	                        		zlfh=rs9.getString(1);
	                        		if(zlfh.equals("")||null==zlfh){
	                    				zlfh="0";
	                    			}
	                        		jlfh=rs9.getString(2);
	                        		if(jlfh.equals("")||null==jlfh){
	                        			jlfh="0";
	                    			}
	                        	}
	                        	String sqldzl="";
	                        	double dzl=0,djl=0;
	                        	
	                        	String newzlfh=lst.getZlzfh();
	                        	if("".equals(newzlfh)||null==newzlfh){
	                        		newzlfh="0";
	                        	}
	                        	String newjlfh=lst.getJlzfh();
	                        	if("".equals(newjlfh)||null==newjlfh){
	                        		newjlfh="0";
	                        	}
	                        	dzl=Double.parseDouble(newzlfh);
	                        	djl=Double.parseDouble(newjlfh);
	                           if(dzl<=Double.parseDouble(zlfh)&&djl<=Double.parseDouble(jlfh)){
								System.out.println("站点属性为营业网点zdsx03："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								System.out.println("站点属性为营业网点的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlfh();
								s2=lst.getJlfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*54*24/1000/0.85);
								dd=(Double.parseDouble(s2)*220*24/1000);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
								
								System.out.println("生产标s："+s);
								
								//sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								//db.queryAll(sql3);
								int r2;
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								r2=db.update(sql3);
								if(r2==0){
									String sql22="";
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增营业网点无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * 1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+yy5+" AND aa.ZLFH <="+yy6+" THEN "+yy7+" WHEN aa.ZLFH >"+yy6+" AND aa.ZLFH <="+yy9+" THEN "+yy10+" " +
								 		"WHEN aa.ZLFH > "+yy9+" AND aa.ZLFH <= "+yy12+" THEN "+yy13+" WHEN aa.ZLFH > "+yy12+" AND aa.ZLFH <= "+yy15+" THEN "+yy16+"" +
								 		" WHEN aa.ZLFH > "+yy15+" AND aa.ZLFH <= "+yy18+" THEN "+yy19+" WHEN aa.ZLFH > "+yy18+" THEN "+yy22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		//" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		",q.scb="+qs+"  where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+" *1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+yy5+" AND aa.ZLFH <"+yy6+" THEN "+yy7+" WHEN aa.ZLFH >="+yy6+" AND aa.ZLFH <"+yy8+" THEN "+yy10+" " +
					 		"WHEN aa.ZLFH >= "+yy8+" AND aa.ZLFH <"+yy11+" THEN "+yy13+" WHEN aa.ZLFH >= "+yy11+" AND aa.ZLFH < "+yy14+" THEN "+yy16+"" +
					 		" WHEN aa.ZLFH >= "+yy14+" AND aa.ZLFH < "+yy17+" THEN "+yy19+" WHEN aa.ZLFH >= "+yy17+" THEN "+yy22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		// " +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
					 		",q.scb="+qs+"  where q.id="+lst.getZdid()+"";
							
								}
								
							System.out.println("营业网点sql2："+sql2.toString());
							System.out.println("营业网点sql3："+sql3.toString());
							System.out.println("营业网点更新站点全省定标电量ssql："+ssql.toString());
						
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
	                           }
							
	                        }else if("zdsx04".equals(lst.getProperty())){
	                        	String zdsx="",zlfh="",jlfh="";
	                        	String sqlsb="SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx04'";
	                        	rs10=db.queryAll(sqlsb);
	                        	while(rs10.next()){
	                        		zlfh=rs10.getString(1);
	                        		if(zlfh.equals("")||null==zlfh){
	                    				zlfh="0";
	                    			}
	                        		jlfh=rs10.getString(2);
	                        		if(jlfh.equals("")||null==jlfh){
	                        			jlfh="0";
	                    			}
	                        	}
	                        	String sqldzl="";
	                        	double dzl=0,djl=0;
	                        	
	                        	String newzlfh=lst.getZlzfh();
	                        	if("".equals(newzlfh)||null==newzlfh){
	                        		newzlfh="0";
	                        	}
	                        	String newjlfh=lst.getJlzfh();
	                        	if("".equals(newjlfh)||null==newjlfh){
	                        		newjlfh="0";
	                        	}
	                        	dzl=Double.parseDouble(newzlfh);
	                        	djl=Double.parseDouble(newjlfh);
	                        	
	                           if(dzl<=Double.parseDouble(zlfh)&&djl<=Double.parseDouble(jlfh)){
								//System.out.println("站点属性为其他zdsx04："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								//System.out.println("站点属性为其他的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlfh();
								s2=lst.getJlfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*54*24/1000/0.85);
								dd=(Double.parseDouble(s2)*220*24/1000);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
								
								System.out.println("生产标s："+s);
								
								//sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								//db.queryAll(sql3);
								int r2;
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								r2=db.update(sql3);
								if(r2==0){
									String sql22="";
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-08',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增其他无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * (1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+qq5+" AND aa.ZLFH <="+qq6+" THEN "+qq7+" WHEN aa.ZLFH >"+qq6+" AND aa.ZLFH <="+qq9+" THEN "+qq10+" " +
								 		"WHEN aa.ZLFH > "+qq9+" AND aa.ZLFH <= "+qq12+" THEN "+qq13+" WHEN aa.ZLFH > "+qq12+" AND aa.ZLFH <= "+qq15+" THEN "+qq16+"" +
								 		" WHEN aa.ZLFH > "+qq15+" AND aa.ZLFH <= "+qq18+" THEN "+qq19+" WHEN aa.ZLFH > "+qq18+" THEN "+qq22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		//" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		" ),q.scb="+qs+" where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+qq5+" AND aa.ZLFH <"+qq6+" THEN "+qq7+" WHEN aa.ZLFH >="+qq6+" AND aa.ZLFH <"+qq8+" THEN "+qq10+" " +
					 		"WHEN aa.ZLFH >= "+qq8+" AND aa.ZLFH <"+qq11+" THEN "+qq13+" WHEN aa.ZLFH >= "+qq11+" AND aa.ZLFH < "+qq14+" THEN "+qq16+"" +
					 		" WHEN aa.ZLFH >= "+qq14+" AND aa.ZLFH < "+qq17+" THEN "+qq19+" WHEN aa.ZLFH >= "+qq17+" THEN "+qq22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		// " +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
					 		" ),q.scb="+qs+" where q.id="+lst.getZdid()+"";
							
								}
								
							System.out.println("其他sql2："+sql2.toString());
							System.out.println("其它sql3："+sql3.toString());
							System.out.println("其他属性更新站点全省定标电量ssql："+ssql.toString());
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
	                           }
							}else if("zdsx05".equals(lst.getProperty())){
								String zdsx="",zlfh="",jlfh="";
								String sqlsb="SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx05'";
	                        	rs11=db.queryAll(sqlsb);
	                        	while(rs11.next()){
	                        		zlfh=rs11.getString(1);
	                        		if(zlfh.equals("")||null==zlfh){
	                    				zlfh="0";
	                    			}
	                        		jlfh=rs11.getString(2);
	                        		if(jlfh.equals("")||null==jlfh){
	                        			jlfh="0";
	                    			}
	                        	}
	                    		
	                        	String sqldzl="";
	                        	double dzl=0,djl=0;
	                        	
	                        	String newzlfh=lst.getZlzfh();
	                        	if("".equals(newzlfh)||null==newzlfh){
	                        		newzlfh="0";
	                        	}
	                        	String newjlfh=lst.getJlzfh();
	                        	if("".equals(newjlfh)||null==newjlfh){
	                        		newjlfh="0";
	                        	}
	                        	dzl=Double.parseDouble(newzlfh);
	                        	djl=Double.parseDouble(newjlfh);
	                           if(dzl<=Double.parseDouble(zlfh)){
								System.out.println("站点属性为接入网："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								System.out.println("站点属性为接入网的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlzfh();
								s2=lst.getJlzfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*54*24/1000/0.85);
								dd=(Double.parseDouble(s2)*220*24/1000);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
							int r2;
								System.out.println("生产标s："+s);
								
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								r2=db.update(sql3);
								if(r2==0){
									String sql22="";
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-11',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增接入网无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * 1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+dd5+" AND aa.ZLFH <="+dd6+" THEN "+dd7+" WHEN aa.ZLFH >"+dd6+" AND aa.ZLFH <="+dd9+" THEN "+dd10+" " +
								 		"WHEN aa.ZLFH > "+dd9+" AND aa.ZLFH <= "+dd12+" THEN "+dd13+" WHEN aa.ZLFH > "+dd12+" AND aa.ZLFH <= "+dd15+" THEN "+dd16+"" +
								 		" WHEN aa.ZLFH > "+dd15+" AND aa.ZLFH <= "+dd18+" THEN "+dd19+" WHEN aa.ZLFH > "+dd18+" THEN "+dd22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		//" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		",q.scb="+qs+" where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+"*1 " +
					 		"+(SELECT (CASE WHEN aa.ZLFH >="+bb5+" AND aa.ZLFH <"+bb6+" THEN "+bb7+" WHEN aa.ZLFH >="+bb6+" AND aa.ZLFH <"+bb8+" THEN "+bb10+" " +
					 		"WHEN aa.ZLFH >= "+bb8+" AND aa.ZLFH <"+bb11+" THEN "+bb13+" WHEN aa.ZLFH >= "+bb11+" AND aa.ZLFH < "+bb14+" THEN "+bb16+"" +
					 		" WHEN aa.ZLFH >= "+bb14+" AND aa.ZLFH < "+bb17+" THEN "+bb19+" WHEN aa.ZLFH >= "+bb17+" THEN "+bb22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		",q.scb="+qs+" where q.id="+lst.getZdid()+"";
							
								}
								
								
							System.out.println("接入网sql2："+sql2.toString());
							System.out.println("接入网sql3："+sql3.toString());
							System.out.println("接入网更新站点全省定标电量ssql："+ssql.toString());
						
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
	                           }
							
	                        	
	                        }else if("zdsx06".equals(lst.getProperty())){
	                        	String zdsx="",zlfh="",jlfh="";
	                        	String sqlsb="SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx06'";
	                        	rs12=db.queryAll(sqlsb);
	                        	while(rs12.next()){
	                        		zlfh=rs12.getString(1);
	                        		if(zlfh.equals("")||null==zlfh){
	                    				zlfh="0";
	                    			}
	                        		jlfh=rs12.getString(2);
	                        		if(jlfh.equals("")||null==jlfh){
	                        			jlfh="0";
	                    			}
	                        	}
	                        	String sqldzl="";
	                        	double dzl=0,djl=0;
	                        	
	                        	String newzlfh=lst.getZlzfh();
	                        	if("".equals(newzlfh)||null==newzlfh){
	                        		newzlfh="0";
	                        	}
	                        	String newjlfh=lst.getJlzfh();
	                        	if("".equals(newjlfh)||null==newjlfh){
	                        		newjlfh="0";
	                        	}
	                        	dzl=Double.parseDouble(newzlfh);
	                        	djl=Double.parseDouble(newjlfh);
	                        	
	                           if(dzl<=Double.parseDouble(zlfh)&&djl<=Double.parseDouble(jlfh)){
								//System.out.println("站点属性为乡镇支局："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								//System.out.println("站点属性为乡镇的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlzfh();
								s2=lst.getJlzfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*54*24/1000/0.85);
								dd=(Double.parseDouble(s2)*220*24/1000);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
								
								System.out.println("生产标s："+s);
								
								//sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								// db.queryAll(sql3);
								int r2;
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								r2=db.update(sql3);
								if(r2==0){
									String sql22="";
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增乡镇支局无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * (1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+xx5+" AND aa.ZLFH <="+xx6+" THEN "+xx7+" WHEN aa.ZLFH >"+xx6+" AND aa.ZLFH <="+xx9+" THEN "+xx10+" " +
								 		"WHEN aa.ZLFH > "+xx9+" AND aa.ZLFH <= "+xx12+" THEN "+xx13+" WHEN aa.ZLFH > "+xx12+" AND aa.ZLFH <= "+xx15+" THEN "+xx16+"" +
								 		" WHEN aa.ZLFH > "+xx15+" AND aa.ZLFH <= "+xx18+" THEN "+xx19+" WHEN aa.ZLFH > "+xx18+" THEN "+xx22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		//" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		" ),q.scb="+qs+" where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+xx5+" AND aa.ZLFH <"+xx6+" THEN "+xx7+" WHEN aa.ZLFH >="+xx6+" AND aa.ZLFH <"+xx8+" THEN "+xx10+" " +
					 		"WHEN aa.ZLFH >= "+xx8+" AND aa.ZLFH <"+xx11+" THEN "+xx13+" WHEN aa.ZLFH >= "+xx11+" AND aa.ZLFH < "+xx14+" THEN "+xx16+"" +
					 		" WHEN aa.ZLFH >= "+xx14+" AND aa.ZLFH < "+xx17+" THEN "+xx19+" WHEN aa.ZLFH >= "+xx17+" THEN "+xx22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		// " +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
					 		" ),q.scb="+qs+" where q.id="+lst.getZdid()+"";
							
								}
							
							//System.out.println("乡镇支局sql2："+sql2.toString());
							//System.out.println("乡镇支局sql3："+sql3.toString());
							//System.out.println("乡镇支局更新站点全省定标电量ssql："+ssql.toString());
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
	                           }
							
	                        }else if("zdsx07".equals(lst.getProperty())){

								//System.out.println("站点属性为IDC机房zdsx07："+lst.getProperty());
								//===宽松
								String ssql="",sql2="",sql3="",sql12="",qs="0";
								String s=lst.getQsdb();
								//System.out.println("站点属性为IDC机房的全省定标电量："+lst.getQsdb());
								String s1="", s2="";
								double d=0.0,dd=0.0;
							if("0".equals(s)||null==s||"".equals(s)){
								s1=lst.getZlzfh();
								s2=lst.getJlzfh();
								if(null==s1||"".equals(s1)){
									s1="0";
								}
								if(null==s2||"".equals(s2)){
									s2="0";
								}
								d=(Double.parseDouble(s1)*54*24/1000/0.85);
								dd=(Double.parseDouble(s2)*220*24/1000);
								System.out.println("直流负荷："+d);
								System.out.println("交流负荷："+dd);
								if(d>=dd){
									s=(dd+"").toString().trim();
								}else if(d<=dd){
									s=(d+"").toString().trim();
								}
							}
								
								System.out.println("生产标s："+s);
								
								//sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								//db.queryAll(sql3);
								int r2;
								sql3="UPDATE SCB S SET S.SCB="+s+" WHERE S.ZDID ="+lst.getZdid()+"";
								r2=db.update(sql3);
								if(r2==0){
									String sql22="";
									sql22="INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12',"+lst.getZdid()+","+s+",'"+lg+"',"+sj+","+0+")";
									System.out.println("新增IDC无省定标点sql："+sql22.toString());
									db.queryAll(sql22);
								}
								
								sql12="(select s.scb from scb s where s.zdid = "+lst.getZdid()+")";
								System.out.println("sql12:"+sql12.toString());
								rs1=db.queryAll(sql12);
								while(rs1.next()){
									qs=rs1.getString(1);
								}
								if(j>=9){
									
							 ssql=" update zhandian q set q.qsdbdl= "+qs+" * (1 + " +
								 		"(SELECT (CASE WHEN aa.ZLFH >="+ii5+" AND aa.ZLFH <="+ii6+" THEN "+ii7+" WHEN aa.ZLFH >"+ii6+" AND aa.ZLFH <="+ii9+" THEN "+ii10+" " +
								 		"WHEN aa.ZLFH > "+ii9+" AND aa.ZLFH <= "+ii12+" THEN "+ii13+" WHEN aa.ZLFH > "+ii12+" AND aa.ZLFH <= "+ii15+" THEN "+ii16+"" +
								 		" WHEN aa.ZLFH > "+ii15+" AND aa.ZLFH <= "+ii18+" THEN "+ii19+" WHEN aa.ZLFH > "+ii18+" THEN "+ii22+"" +
								 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
								 		//" +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
								 		" ),q.scb="+qs+" where q.id=("+lst.getZdid()+")";
							 
								}else {
							//=========
							
							 ssql="update zhandian q set q.qsdbdl="+qs+" * (1 + " +
					 		"(SELECT (CASE WHEN aa.ZLFH >="+ii5+" AND aa.ZLFH <"+ii6+" THEN "+ii7+" WHEN aa.ZLFH >="+ii6+" AND aa.ZLFH <"+ii8+" THEN "+ii10+" " +
					 		"WHEN aa.ZLFH >= "+ii8+" AND aa.ZLFH <"+ii11+" THEN "+ii13+" WHEN aa.ZLFH >= "+ii11+" AND aa.ZLFH < "+ii14+" THEN "+ii16+"" +
					 		" WHEN aa.ZLFH >= "+ii14+" AND aa.ZLFH < "+ii17+" THEN "+ii19+" WHEN aa.ZLFH >= "+ii17+" THEN "+ii22+"" +
					 		" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+syf+" from yfxs y where y.shicode = q.shi)" +
					 		// " +(SELECT DECODE(aa.YFLX, 'fwlx01', "+b1+", 'fwlx02',"+b2+", 'fwlx03',"+b3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " +
					 		" ),q.scb="+qs+" where q.id="+lst.getZdid()+"";
							
								}
								
							
							//System.out.println("IDC机房ql2："+sql2.toString());
							//System.out.println("IDC机房sql3："+sql3.toString());
							//System.out.println("IDC机房更新站点全省定标电量ssql："+ssql.toString());
							sql2="update cbzd c set c.SHENGJSH='3' where c.id in("+idd+")";
							String sql4="";
							 sql4="update zhandian z set z.edhdl="+lst.getEdhdl()+",z.jlfh="+lst.getJlzfh()+",z.zlfh="+lst.getZlzfh()+" where z.id="+lst.getZdid()+"";
							 db.queryAll(sql4);
							db.queryAll(ssql);
							db.queryAll(sql2);
							msg=1;
							
							
	                        }         						
						}
					
					}
				}
					//db.connectDb();
					//db.update(sql.toString());
					//msg = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs != null) {
							try {
								rs.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs2!= null) {
							try {
								rs2.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs3!= null) {
							try {
								rs3.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs4!= null) {
							try {
								rs4.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs5!= null) {
							try {
								rs5.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs6!= null) {
							try {
								rs6.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs7!= null) {
							try {
								rs7.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs8!= null) {
							try {
								rs8.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs9!= null) {
							try {
								rs9.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs10!= null) {
							try {
								rs10.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs11!= null) {
							try {
								rs11.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						if (rs12!= null) {
							try {
								rs12.close();
							} catch (SQLException se) {
								se.printStackTrace();
							}
						}
						try {
						db.close();
					} catch (Exception de) {
						de.printStackTrace();
					}
				}
				
		    	return msg;
		 }
	  
	  
	  
	  
	  
}
