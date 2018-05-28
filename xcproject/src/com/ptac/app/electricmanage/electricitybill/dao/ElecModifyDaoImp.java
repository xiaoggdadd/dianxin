package com.ptac.app.electricmanage.electricitybill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @see 电费单修改.dao层实现类
 * @author ZengJin
 */
public class ElecModifyDaoImp implements ElecModifyDao{
	DataBase db = new DataBase();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	/**
	 * @param degreeid (String) //电费id
	 * @return (List)
	 * @see 查询电量电费基本信息
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public List getElectricFeesInfo(String degreeid) {
		
    	List list = new ArrayList();	//声明结果集
		ElectricityInfo elec = new ElectricityInfo();	//电量电费bean
		Share share = new Share();	//声明分摊信息bean

	    StringBuffer sql = new StringBuffer();
	    sql.append("select ad.*,AD.AMMETERDEGREEID,TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd') LASTDATETIMEA,TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') THISDATETIMEA,TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTHA,EF.ELECTRICFEE_ID,AD.DDDF AS DDDFDL,AD.MEMO AS MEMOAM,ef.pjje,ef.*,EF.DDDF AS DDDFDF,EF.MEMO AS MEMOEF," +
	    		"z.edhdl edhdla,z.qsdbdl qsdbdla,Z.ZLFH zlfha,Z.JLFH jlfha,Z.SCB SCBA,Z.PROPERTY PROPERTYCODEA,Z.SHI,D.DFZFLX DFZFLXA,Z.STATIONTYPE STATIONTYPEA,Z.GDFS GDFSA," +
	    		"EF.UNITPRICE AS UNITPRICE ,EF.YZLIUCHENGHAO AS YZLIUCHENGHAO from zhandian z,dianbiao d, ammeterdegrees ad,electricfees ef " +
	    		"where z.id=d.zdid and d.dbid= ad.AMMETERID_FK and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ELECTRICFEE_ID="+degreeid);
    	System.out.println("电费单(修改)查询基本信息:"+sql);

	    try {
	    	db.connectDb();	 
	    	conn = db.getConnection();
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql.toString());//运行sql
	    	while (rs.next()) {
	    		
				//------接收查询到的数据(并判断是否为空)------
	    		//站点
	    		elec.setShi(rs.getString("shi") !=null ? rs.getString("shi"):"");
	    		//电表
	    		elec.setDbid(rs.getString("ammeterid_fk") != null ? rs.getString("ammeterid_fk") : "");
	    		//电量
				elec.setAmmeterdegree_id(rs.getString("AMMETERDEGREEID") != null ? rs.getString("AMMETERDEGREEID") : "");
				elec.setFloatdegree(rs.getString("floatdegree") != null ? rs.getString("floatdegree") : "");
				elec.setActualdegree(rs.getString("actualdegree") != null ? rs.getString("actualdegree") : "");	    	
				elec.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
				elec.setLastdatetime(rs.getString("lastdatetimea") != null ? rs.getString("lastdatetimea") : "");
				elec.setThisdegree(rs.getString("thisdegree") != null ? rs.getString("thisdegree") : "");
				elec.setThisdatetime(rs.getString("thisdatetimea") != null ? rs.getString("thisdatetimea") : "");
				elec.setMemo(rs.getString("memoam") != null ? rs.getString("memoam") : "");
				elec.setBlhdl(rs.getString("blhdl") != null ? rs.getString("blhdl") : "");
				elec.setDedhdl(rs.getString("dedhdl") != null ? rs.getString("dedhdl") : "");
				elec.setCsdb(rs.getString("CSDB") != null ? rs.getString("CSDB") : "");
				elec.setActualdegree(Format.str2(elec.getActualdegree()));	    	
				elec.setBlhdl(Format.str2(elec.getBlhdl()));
	    		//电费
				elec.setElectricfee_id(rs.getString("ELECTRICFEE_ID") != null ? rs.getString("ELECTRICFEE_ID") : "");
				elec.setPjje(rs.getDouble("pjje"));
				elec.setAccountmonth(rs.getString("accountmontha") != null ? rs.getString("accountmontha") : "");
				elec.setActualpay(rs.getString("actualpay") != null ? Double.parseDouble(rs.getString("actualpay")) : 0);
				elec.setFloatpay(rs.getString("floatpay") != null ? rs.getString("floatpay") : "");
				elec.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");	    
				elec.setMemo1(rs.getString("memoef") != null ? rs.getString("memoef") : "");
				elec.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : "");
				elec.setPayoperator(rs.getString("payoperator") != null ? rs.getString("payoperator") : "");
				elec.setUnitprice(rs.getString("unitprice") != null ? rs.getString("unitprice") : "");
				elec.setLiuchenghao(rs.getString("yzliuchenghao") != null ? rs.getString("yzliuchenghao") : "");
				
				//电量分摊
				share.setNetworkhdl(rs.getString("NETWORKHDL")!= null ? Double.parseDouble(rs.getString("NETWORKHDL")) : 0);
				share.setAdministrativehdl(rs.getString("ADMINISTRATIVEHDL")!= null ? Double.parseDouble(rs.getString("ADMINISTRATIVEHDL")) : 0);
				share.setMarkethdl(rs.getString("MARKETHDL")!= null ? Double.parseDouble(rs.getString("MARKETHDL")) : 0);
				share.setInformatizationhdl(rs.getString("INFORMATIZATIONHDL")!= null ? Double.parseDouble(rs.getString("INFORMATIZATIONHDL")) : 0);
				share.setBuildhdl(rs.getString("BUILDHDL")!= null ? Double.parseDouble(rs.getString("BUILDHDL")) : 0);
				share.setDddl(rs.getString("DDDFDL")!= null ? Double.parseDouble(rs.getString("DDDFDL")) : 0);//代垫电量
				//电费分摊
				share.setNetworkdf(rs.getString("NETWORKDF")!= null ? Double.parseDouble(rs.getString("NETWORKDF")) : 0);
				share.setAdministrativedf(rs.getString("ADMINISTRATIVEDF")!= null ? Double.parseDouble(rs.getString("ADMINISTRATIVEDF")) : 0);
				share.setMarketdf(rs.getString("MARKETDF")!= null ? Double.parseDouble(rs.getString("MARKETDF")) : 0);
				share.setInformatizationdf(rs.getString("INFORMATIZATIONDF")!= null ? Double.parseDouble(rs.getString("INFORMATIZATIONDF")) : 0);
				share.setBuilddf(rs.getString("BUILDDF")!= null ? Double.parseDouble(rs.getString("BUILDDF")) : 0);
				
				share.setDddf(rs.getString("DDDFDF")!= null ? Double.parseDouble(rs.getString("DDDFDF")) : 0);//代垫电费
				elec.setEdhdl(rs.getString("edhdla") != null ? rs.getString("edhdla") : "");
				elec.setQsdbdl(rs.getString("qsdbdla") != null ? rs.getString("qsdbdla") : "");
				elec.setZlfh(rs.getString("zlfha") != null ? rs.getString("zlfha") : "");
				elec.setJlfh(rs.getString("jlfha")!= null ? rs.getString("jlfha") : "");
				elec.setPropertycode(rs.getString("propertycodea")!=null?rs.getString("propertycodea") : "");
				elec.setScb(rs.getString("scba")!=null?rs.getString("scba"):"");
				elec.setStationtypecode(rs.getString("stationtypea")!=null?rs.getString("stationtypea"):"");
				elec.setDfzflxcode(rs.getString("dfzflxa")!=null?rs.getString("dfzflxa"):"");
				elec.setGdfscode(rs.getString("gdfsa")!=null?rs.getString("gdfsa"):"");
				//------小数格式化------
				elec.setUnitprice(Format.str4(elec.getUnitprice()));
				elec.setActualpay(Format.d2(elec.getActualpay()));
				elec.setPjje(Format.d2(elec.getPjje()));
			}
	    	//------将含有数据的bean放入结果集------
	    	list.add(elec);
	    	list.add(share);
	    } catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,stmt,conn);
		}
		return list;
	}
	
	
	/**
	 * @param dbid (String) //电表id
	 * @return (ArrayList)
	 * @see 查询电量电费附加信息
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getOtherInfo(String dbid) {
		
		String sql = "select beilv,linelosstype,linelossvalue,bgsign from dianbiao where dbid='" + dbid + "'";
		System.out.println("电费单(修改)查询附加信息:");
		ArrayList list = new ArrayList();
		try {
			db.connectDb();	 
	    	conn = db.getConnection();
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);	//运行sql
			Query query = new Query();
			list = query.query(rs);	//接收结果集
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,stmt,conn);
		}
		return list;
	}
	
	
	/**
	 * @param elec (ElectricityInfo) //电量电费bean
	 * @param share (Share) //分摊信息bean
	 * @return (ArrayList)
	 * @see 保存已修改信息
	 * @author ZengJin 2014-2-15
	 */
		public String modifyElectricFees(ElectricityInfo elec,Share share,String dfms,String dfbz,String dlms,String dlbz,String flag,String edhdlbz,String qsdbdlbz,String qxzr,String cityzr, String jszq,String edhdl,String qsdbdl,String hbzq,String bzz,String scb) {
			
			String msg = "修改电费信息失败！";	//提示信息,默认为失败状态
			
			String nowtime="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			//String nowtime= df.format(new Date());//将系统时间赋予"录入时间"
			String countyaudittime = "1".equals(qxzr) ? nowtime : "NULL";//区县主任审核时间
			String cityaudittime = "1".equals(flag) ? nowtime : "NULL";//市级审核时间
			String cityzraudittime = "1".equals(cityzr) ? nowtime : "NULL";//市级主任审核时间
			
			String sql1 = "UPDATE AMMETERDEGREES SET LASTDEGREE='"+elec.getLastdegree()+"', THISDEGREE='"+elec.getThisdegree()+"', LASTDATETIME=TO_DATE('"+elec.getLastdatetime()+"','yyyy-mm-dd'), THISDATETIME=TO_DATE('"+elec.getThisdatetime()+"','yyyy-mm-dd'), "
				 + "FLOATDEGREE='"+elec.getFloatdegree()+"', ACTUALDEGREE='"+elec.getActualdegree()+"', BLHDL='"+elec.getBlhdl()+"', MEMO='"+elec.getMemo()+"', ENTRYPENSONNEL='"+elec.getEntrypensonnel()+"', ENTRYTIME="+nowtime+"," 
				 +	"AUTOAUDITDESCRIPTION='"+dlms+"',AUTOAUDITSTATUS='"+dlbz+"',DEDHDL='"+edhdlbz+"',NETWORKHDL='"+share.getNetworkhdl()+"',INFORMATIZATIONHDL='"+share.getInformatizationhdl()+"',ADMINISTRATIVEHDL='"+share.getAdministrativehdl()+"'," 
				 +  "MARKETHDL='"+share.getMarkethdl()+"',BUILDHDL='"+share.getBuildhdl()+"',DDDF='"+share.getDddl()+"',INPUTOPERATOR='"+elec.getEntrypensonnel()+"',FLAG='"+flag+"',CSDB='"+qsdbdlbz+"',COUNTYFLAG='"+qxzr+"',CITYFLAG='"+cityzr+"',MANUALAUDITSTATUS='0',HBZQ='"+hbzq+"',BZZ='"+bzz+"',SCB='"+scb+"',BEILV='"+elec.getBeilv()+"',DBYDL='"+elec.getDbydl()+"' "
				 + "WHERE AMMETERDEGREEID=" + elec.getAmmeterdegree_id();
			System.out.println("修改电量sql："+sql1);
			
			String sql2 = "update electricfees SET UNITPRICE='"+elec.getUnitprice()+"', ACTUALPAY='"+elec.getActualpay()+"', FLOATPAY='"+elec.getFloatpay()+"', NOTETYPEID='"+elec.getNotetypeid()+"', "
				 + "ACCOUNTMONTH=TO_DATE('"+elec.getAccountmonth()+"','yyyy-mm'), PAYOPERATOR='"+elec.getPayoperator()+"', MEMO='"+elec.getMemo1()+"', PJJE='"+elec.getPjje()+"',AUTOAUDITDESCRIPTION='"+dfms+"',AUTOAUDITSTATUS='"+dfbz+"'," 
				 + "NETWORKDF='"+share.getNetworkdf()+"',INFORMATIZATIONDF='"+share.getInformatizationdf()+"',ADMINISTRATIVEDF='"+share.getAdministrativedf()+"',MARKETDF='"+share.getMarketdf()+"',BUILDDF='"+share.getBuilddf()+"',DDDF='"+share.getDddf()+"',CITYAUDIT='"+flag+"',COUNTYAUDITSTATUS='"+qxzr+"'," 
				 + "CITYZRAUDITSTATUS='"+cityzr+"',JSZQ='"+jszq+"',MANUALAUDITSTATUS='0',EDHDL='"+edhdl+"',QSDBDL='"+qsdbdl+"',YZLIUCHENGHAO='"+elec.getLiuchenghao()+"',COUNTYAUDITTIME="+countyaudittime+",CITYAUDITTIME="+cityaudittime+",CITYZRAUDITTIME="+cityzraudittime+",ZLFH='"+elec.getZlfh()+"',JLFH='"+elec.getJlfh()+"',DFZFLXCODE='"+elec.getDfzflxcode()+"',STATIONTYPECODE='"+elec.getStationtypecode()+"',GDFSCODE='"+elec.getGdfscode()+"',PROPERTYCODE='"+elec.getPropertycode()+"' "
				 + "WHERE ELECTRICFEE_ID=" + elec.getElectricfee_id();
			System.out.println("修改电费sql："+sql2);

			try {
				db.connectDb();	 
		    	conn = db.getConnection();
		    	ps = conn.prepareStatement(sql1);
//		    	ps.setString(1,elec.getLastdegree());
//		    	ps.setString(2,elec.getThisdegree());
//		    	ps.setString(3,elec.getLastdatetime());
//		    	ps.setString(4,elec.getThisdatetime());
//		    	ps.setString(5,elec.getFloatdegree());
//		    	ps.setString(6,elec.getActualdegree());
//		    	ps.setString(7,elec.getBlhdl());
//		    	ps.setString(8,elec.getMemo());
//		    	ps.setString(9,elec.getEntrypensonnel());
//		    	ps.setString(10,elec.getEntrytime());
//		    	ps.setString(11,dlms);
//		    	ps.setString(12,dlbz);
//		    	ps.setString(13,edhdlbz);
//			    ps.setDouble(14,share.getNetworkhdl()); 
//			    ps.setDouble(15,share.getInformatizationhdl());  
//			    ps.setDouble(16,share.getAdministrativehdl()); 
//			    ps.setDouble(17,share.getMarkethdl()); 
//			    ps.setDouble(18,share.getBuildhdl()); 
//			    ps.setDouble(19,share.getDddl()); 
//			    ps.setString(20,elec.getEntrypensonnel());
//			    ps.setString(21, flag);
//			    ps.setString(22, qsdbdlbz);
//			    ps.setString(23, qxzr);
//			    ps.setString(24, cityzr);
//			    ps.setString(25, "0");
//		    	ps.setString(26, hbzq);
//		    	ps.setString(27,bzz);
//			    ps.setString(28, scb);
//			    ps.setString(29, elec.getBeilv());
//			    ps.setString(30, elec.getDbydl());
				int count1 = 0;
				count1 = ps.executeUpdate(); //返回对数据库的影响行数
				
		    	ps = conn.prepareStatement(sql2);
//		    	ps.setString(1,elec.getUnitprice());
//		    	ps.setDouble(2,elec.getActualpay());
//		    	ps.setString(3,elec.getFloatpay());
//		    	ps.setString(4,elec.getNotetypeid());
//		    	ps.setString(5,elec.getAccountmonth());
//		    	ps.setString(6,elec.getPayoperator());
//		    	ps.setString(7,elec.getMemo1());
//		    	ps.setDouble(8,elec.getPjje());
//		    	ps.setString(9,dfms);
//		    	ps.setString(10,dfbz);
//		    	ps.setDouble(11,share.getNetworkdf()); 
//			    ps.setDouble(12,share.getInformatizationdf());  
//			    ps.setDouble(13,share.getAdministrativedf()); 
//			    ps.setDouble(14,share.getMarketdf()); 
//			    ps.setDouble(15,share.getBuilddf()); 
//			    ps.setDouble(16,share.getDddf()); 
//			    ps.setString(17, flag);
//			    ps.setString(18, qxzr);
//			    ps.setString(19, cityzr);
//			    ps.setString(20, jszq);
//			    ps.setString(21, "0");
//			    ps.setString(22, edhdl);
//			    ps.setString(23, qsdbdl);
//			    ps.setString(24, elec.getLiuchenghao());
//			    ps.setString(25, countyaudittime);
//			    ps.setString(26, cityaudittime);
//			    ps.setString(27, cityzraudittime);
//			    ps.setString(28, elec.getZlfh());
//			    ps.setString(29, elec.getJlfh());
//			    ps.setString(30, elec.getDfzflxcode());
//			    ps.setString(31, elec.getStationtypecode());
//			    ps.setString(32, elec.getGdfscode());
//			    ps.setString(33, elec.getPropertycode());
				int count2 = 0;
				count2 = ps.executeUpdate(); //返回对数据库的影响行数
				
				if(count1==1&count2==1){
					msg = "电费单修改成功!!";	//如果都是修改了一条,则提示成功
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					db.rollback();
				} catch (DbException e1) {
					e1.printStackTrace();
				}
			} finally {
				db.free(null,ps,conn);
			}
			return msg;
		}
}
