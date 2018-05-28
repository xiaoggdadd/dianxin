package com.noki.query.basequery.javabean;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;

//基础查询站点和导出
public class StationPointQueryBean {
    Connection conn = null;
    Statement sta = null;
	public synchronized List<ElectricFeesFormBean> getPageDatap(String whereStr,String loginId) {		
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	    ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db,loginId);
	        sql =" SELECT JZ.SHSIGN,(CASE WHEN XUNI='0' THEN '否' ELSE '是' END) AS XUNISIGN,"+
	          "(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME = JZ.MANUALAUDITNAME_STATION AND DELSIGN = 1) AS MANUALAUDITNAME_STATION,"+
	          "TO_CHAR(JZ.MANUALAUDITDATETIME_STATION,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME_STATION,JZ.ID,JZ.JZNAME,JZ.ZDCODE,JZ.ZLFH,JZ.BGSIGN,JZ.EDHDL,JZ.KONGTIAO,"+
	          "(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME = JZ.ENTRYPENSONNEL AND DELSIGN = 1) AS ENTRYPENSONNEL,TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,"+
	          "(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.PROPERTY AND TYPE='ZDSX') AS PROPERTY,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.JZTYPE AND TYPE='ZDLX') AS JZTYPE,"+
	          "(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.YFLX AND T.TYPE='FWLX') AS YFLX,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.GDFS AND T.TYPE='GDFS') AS GDFS,"+
	          "TO_CHAR(JZ.SYDATE,'yyyy-mm-dd') SYDATE,JZ.BIEMING,JZ.ERPCODE,JZ.FZR,JZ.JNGLMK,JZ.AREA,JZ.DIANFEI," +
	          "(SELECT NAME FROM INDEXS WHERE CODE = JZ.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
	          "(SELECT NAME FROM INDEXS WHERE CODE = JZ.GSF AND TYPE='gsf') AS GSF," +
		      "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=JZ.XIAN) AS XIAN,"+
		      "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=JZ.XIAOQU)AS XQ,JZ.JLFH,JZ.ZLFH,JZ.QSDBDL," +
		      "JZ.YSJTS,JZ.WJTS,JZ.YYBGKT,JZ.JFSCKT,JZ.RRU,JZ.YDGXSBSL,JZ.DXGXSBSL,JZ.KTS,JZ.KTZGL,JZ.KTYPS,JZ.KTEPS,JZ.QYZT,JZ.SCB " +
	          " FROM ZHANDIAN JZ WHERE ((JZ.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")"+whereStr+
	          " ORDER BY JZ.JZNAME";
	      System.out.println("站点查询和导出："+sql);
	      db.connectDb();	 
	      conn = db.getConnection();
	      sta = conn.createStatement();
	      rs = sta.executeQuery(sql.toString());
	      while(rs.next()){
			ElectricFeesFormBean formbean=new ElectricFeesFormBean();
			formbean.setFeebz(rs.getString(1)!=null?rs.getString(1):"");
			formbean.setXunisign(rs.getString(2)!=null?rs.getString(2):"");
			formbean.setManualauditname(rs.getString(3)!=null?rs.getString(3):"");
			formbean.setManualauditdatetime(rs.getString(4)!=null?rs.getString(4):"");
			formbean.setStationid(rs.getString(5)!=null?rs.getString(5):"");
			formbean.setJzname(rs.getString(6)!=null?rs.getString(6):"");
			formbean.setZdcode(rs.getString(7)!=null?rs.getString(7):"");
			formbean.setZlfh(rs.getString(8)!=null?rs.getString(8):"");
			formbean.setBzdy(rs.getString(9)!=null?rs.getString(9):"");
			formbean.setEdhdl(rs.getString(10)!=null?rs.getString(10):"");
			formbean.setKongtiao(rs.getString(11)!=null?rs.getString(11):"");
			formbean.setEntrypensonnel(rs.getString(12)!=null?rs.getString(12):"");
			formbean.setEntrytime(rs.getString(13)!=null?rs.getString(13):"");
			formbean.setProperty(rs.getString(14)!=null?rs.getString(14):"");
			formbean.setJztype(rs.getString(15)!=null?rs.getString(15):"");
			formbean.setYflx(rs.getString(16)!=null?rs.getString(16):"");
			formbean.setGdfs(rs.getString(17)!=null?rs.getString(17):"");
			formbean.setSydate(rs.getString(18)!=null?rs.getString(18):"");
			formbean.setBieming(rs.getString(19)!=null?rs.getString(19):"");
			formbean.setErpcode(rs.getString(20)!=null?rs.getString(20):"");
			formbean.setFzr(rs.getString(21)!=null?rs.getString(21):"");
			formbean.setJnglmk(rs.getString(22)!=null?rs.getString(22):"");
			formbean.setArea(rs.getString(23)!=null?rs.getString(23):"");
			formbean.setDianfei(rs.getString(24)!=null?rs.getString(24):"");
			formbean.setStationtype(rs.getString(25)!=null?rs.getString(25):"");
			formbean.setGsf(rs.getString(26)!=null?rs.getString(26):"");
			formbean.setXian(rs.getString(27)!=null?rs.getString(27):"");
			formbean.setXiaoqu(rs.getString(28)!=null?rs.getString(28):"");
			formbean.setJlfh(rs.getString(29)!=null?rs.getString(29):"");
			formbean.setZlfh(rs.getString(30)!=null?rs.getString(30):"");
			formbean.setSdbdl(rs.getString(31)!=null?rs.getString(31):"");
			formbean.setYsjts(rs.getString(32)!=null?rs.getString(32):"");
			formbean.setWjts(rs.getString(33)!=null?rs.getString(33):"");
			formbean.setYybgkt(rs.getString(34)!=null?rs.getString(34):"");
			formbean.setJfsckt(rs.getString(35)!=null?rs.getString(35):"");
			formbean.setRru(rs.getString(36)!=null?rs.getString(36):"");
			formbean.setDxgxsbsl(rs.getString(37)!=null?rs.getString(37):"");
			formbean.setYdgxsbsl(rs.getString(38)!=null?rs.getString(38):"");
			formbean.setKts(rs.getString(39)!=null?rs.getString(39):"");
			formbean.setKtzgl(rs.getString(40)!=null?rs.getString(40):"");
			formbean.setKtyps(rs.getString(41)!=null?rs.getString(41):"");
			formbean.setKteps(rs.getString(42)!=null?rs.getString(42):"");
			formbean.setZdqyzt(rs.getString(43)!=null?rs.getString(43):"");
			formbean.setScb(rs.getString(44)!=null?rs.getString(44):"");
			list.add(formbean);
	      }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	    	db.free(rs,sta,conn);
	    }
	    return list;
	  }

	
  private int allPage;
  public void setAllPage(int allpage ){
	  this.allPage=allpage;
	  
  }
  public int getAllPage(){
	  return this.allPage;
  }
  
//负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;
		db.connectDb();	 
		String cxtj = new String("");
		try {
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery("select begincode,endcode from per_zhandian where accountid='"
					+ loginid
					+ "' and begincode is not null and endcode is not null");
			while (rs.next()) {			
					cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
							+ "' and zdcode <='" + rs.getString(2) + "')";			
			}	
			System.out.println("负责站点条件："+cxtj);
		}catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	    	db.free(rs,sta,conn);
	    }
		return cxtj.toString();
	}
	//站点查询数量汇总
	  public ElectricFeesFormBean getCountt(String whereStr,String loginId){
		    String count1="";//总数
		    String sumcj="";//采集站点数
		    String sumwtg="";//审核未通过站点数
		    String sumsh="";//审核通过站点数
			DataBase db = new DataBase();
			ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
			String fzzdstr="";
			ResultSet rs = null;
			try {
				db.connectDb();
				StringBuffer strall = new StringBuffer();
			    strall.append(" SELECT COUNT(*) SUM1,SUM(DECODE(JZ.CAIJI,'1',1)) SUMCJ,SUM(DECODE(JZ.SHSIGN,'0',1)) SUMWSTG,SUM(DECODE(JZ.SHSIGN,'1',1)) SUMSH"+
				          " FROM ZHANDIAN JZ WHERE 1=1 "+whereStr+
				          " AND ((JZ.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")");
				System.out.println("站点查询数量汇总："+strall.toString());
				
				conn = db.getConnection();
				sta = conn.createStatement();
				rs = sta.executeQuery(strall.toString());
	          	while(rs.next()){
	          		count1 = rs.getString(1)!=null?rs.getString(1):"0";
	          		sumcj = rs.getString(2)!=null?rs.getString(2):"0";
	          		sumwtg = rs.getString(3)!=null?rs.getString(3):"0";
	          		sumsh = rs.getString(4)!=null?rs.getString(4):"0";
	          	}
	          	bean1.setCount(count1);
	          	bean1.setCaijizd(sumcj);
	          	bean1.setJdno(sumwtg);
	          	bean1.setZd(sumsh);
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				db.free(rs,sta,conn);
			}
			return bean1;
		}
  
}
