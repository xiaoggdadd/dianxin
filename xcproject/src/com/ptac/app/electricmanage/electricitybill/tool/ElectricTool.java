package com.ptac.app.electricmanage.electricitybill.tool;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanageUtil.Format;
/**
 * @see 电量，电费工具类
 * @author WangYiXiao 2014-1-20
 * @update WangYiXiao 2014-3-21 修改chayue()；新增 adjustFeeAndElec1();新增adjustFeeAndElec2()
 */
public class ElectricTool {
    
	/**电费单判断重复
	 * @param thisdegree  	(String)	本次电表读数
	 * @param thisdatetime 	(String) 	本次抄表时间 
	 * @param lastdegree 	(String) 	上次电表读数
	 * @param lastdatetime 	(String)	上次抄表时间
	 * @param ammeteridFk 	(String)	电表id
	 * @param accountmonth 	(String)	报账月份
	 * @return	(boolean)				true 重复	false 不重复
	 * @see 判断数据库中是否有这条数据(重复)，数据库中有重复数据返回true，否则返回false
	 * @author WangYiXiao 2014-1-20
	 */
	public synchronized boolean checkRepeat(String lastdegree,String lastdatetime,String ammeteridFk,String accountmonth){
		DataBase db = new DataBase();
		boolean flag = false;//是否重复标志位
		String sql = "SELECT DD.AMMETERDEGREEID FROM DIANDUVIEW DD,DIANFEIVIEW DF WHERE DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK AND LASTDEGREE='" + lastdegree
		+ "'AND TO_CHAR(LASTDATETIME,'yyyy-mm-dd')='" + lastdatetime
		+ "' AND AMMETERID_FK='" + ammeteridFk
		+ "'AND TO_CHAR(ACCOUNTMONTH,'yyyy-mm')='" + accountmonth+"'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("重复检查"+sql);
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//释放资源
				try {
					if(rs!=null){
					rs.close();
					}
					try {
						if(db!=null){
						db.close();
						}
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	
	/** 预付费判断重复
	 * @param thisdegree  	(String)	本次电表读数
	 * @param thisdatetime 	(String) 	本次抄表时间 
	 * @param lastdegree 	(String) 	上次电表读数
	 * @param lastdatetime 	(String)	上次抄表时间
	 * @param ammeteridFk 	(String)	电表id
	 * @param accountmonth 	(String)	报账月份
	 * @return	(boolean)				true 重复	false 不重复
	 * @see 判断数据库中是否有这条数据(重复)，数据库中有重复数据返回true，否则返回false
	 * @author lijing
	 */
	public synchronized boolean checkRepeat1(String thisdegree,String thisdatetime,String lastdegree,String lastdatetime,String ammeteridFk,String accountmonth){
		DataBase db = new DataBase();
		boolean flag = false;//是否重复标志位
		String sql = "SELECT y.id FROM yufufeiview y WHERE STOPDEGREE='" + thisdegree
		+ "' AND TO_CHAR(ENDDATE,'yyyy-mm-dd')='" + thisdatetime
		+ "' AND STARTDEGREE='" + lastdegree
		+ "'AND TO_CHAR(STARTDATE,'yyyy-mm-dd')='" + lastdatetime
		+ "' AND PREPAYMENT_AMMETERID='" + ammeteridFk
		+ "'AND TO_CHAR(ACCOUNTMONTH,'yyyy-mm')='" + accountmonth+"'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("重复检查"+sql);
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//释放资源
				try {
					if(rs!=null){
						rs.close();
						}
					try {
						if(db!=null){
							db.close();
							}
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	/**  合同单判断重复
	 * @author WangYiXiao 2015-1-14
	 */
	public synchronized boolean checkRepeat2(String ammeteridFk,String accountmonth,String thismonth,String endmonth){
		DataBase db = new DataBase();
		boolean flag = false;//是否重复标志位
		String sql = "select * from yufufeiview t  where  t.prepayment_ammeterid='"
			+ ammeteridFk
			+ "' and to_char(t.startmonth,'yyyy-mm') ='"
			+ thismonth
			+ "' and to_char(t.endmonth,'yyyy-mm') ='"
			+ endmonth
			+ "' and to_char(t.accountmonth,'yyyy-mm')='"
			+ accountmonth
			+ "'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("重复检查"+sql);
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//释放资源
				try {
					if(rs!=null){
					rs.close();
					}
					try {
						if(db!=null){
						db.close();
						}
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	
	//用电量调整的check
	
	/**
	 * @param  elecinfo	(ElectricityInfo)
	 * @return (String[]) 自动审核状态，自动审核描述
	 * @see																										
	 * 	C1 ：用电量调整的check
	 *	查看用电量调整是否等于0，空，或者是0.0,0.00 ，当为空的时候备注memo(电量) 可以为空，
	 *	但是当用电量调整不为空的时候 memo必须有信息，
	 *	否则：自动审核状态，autoauditstatus为0 ,
	 * 	自动审核描述：autoauditdescription = "用电调整没有说明！";
	 * 	autoauditstatus = autoaudit[0]自动审核状态
	 * 	autoauditdescription  = autoaudit[1] 自动审核描述
	 * @author WangYiXiao 2014-1-20
	 */
	public synchronized String[] checkFloatDegree(ElectricityInfo elecinfo){
		String[] autoaudit = new String[2]; 
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		String floatdegree = elecinfo.getFloatdegree();//用电量调整
		String memo = elecinfo.getMemo();//(电量)备注
		if(null == floatdegree || "0".equals(floatdegree) || "0.0".equals(floatdegree) || "0.00".equals(floatdegree) || "".equals(floatdegree)){
		//用电量调整为空 ,备注 空不空无所谓
		}else{
			if(null ==memo || "".equals(memo) || " ".equals(memo)){
				autoauditstatus = "0";
				autoauditdescription = "用电调整没有说明！";
			}
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	/**
	 * @param  ammeterid    (String) 	电表id	
	 * @param  thisdatetime	(String) 	本次抄表时间
	 * @param  lastdatetime	(String) 	上次抄表时间
	 * @param  blhdl		(String) 	倍率耗电量
	 * @return              (String[])	 自动审核状态，自动审核描述
	 * @see 本次交费日均耗电量大于最后一次交费的日均耗电量20%
	 * blhdl/时间差+1 获得本次的日均耗电量
	 * 计算 与上次的日均耗电量的比值：
	 * (本次-上次)/上次*100%>=20%
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkDayDegree(String ammeterid,String thisdatetime,String lastdatetime,String blhdl){
		ResultSet rs = null;
		String[] autoaudit = new String[2];
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		String lastadtime = "";
		double daydegree = 0;//上次的日耗电量
		try {
			dt = sdf.parse(lastdatetime);
			Calendar rightNow = Calendar.getInstance();
	         rightNow.setTime(dt);
	         rightNow.add(Calendar.DAY_OF_YEAR,-1);//页面上日期加了1天显示
	         Date dt1=rightNow.getTime();
	         lastadtime= sdf.format(dt1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		sql.append("SELECT CASE WHEN (AD.THISDATETIME - AD.LASTDATETIME) != 0 THEN AD.blhdl / (AD.THISDATETIME - AD.LASTDATETIME+1) ELSE   1 END DAYDEGREE" +
	    		" FROM DIANDUVIEW AD,DIANFEIVIEW DF " +
	    		"WHERE AD.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK AND DF.CITYAUDIT = '1' AND DF.CITYZRAUDITSTATUS = '1' AND TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') ='"+lastadtime+"' AND AD.AMMETERID_FK='"+ammeterid+"'");
		DataBase db = new DataBase();
		try {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			while (rs.next()){
		    	  daydegree=rs.getDouble(1); 
		    	  double daydegereerate = 0;//比值
					double thisdaydegree = 0;
					long diff,day;
					diff = getQuot(lastdatetime,thisdatetime);
					day = diff+1;// 计算差多少天
					thisdaydegree = Double.parseDouble(blhdl) / day;// 本次日耗电量
					daydegereerate = ((thisdaydegree - daydegree) / daydegree) * 1;// 这次与上次日耗电量比值
					if(daydegereerate >= 0.2){//>=20%
						autoauditstatus = "0";
						autoauditdescription = "本次交费日均耗电量大于最后一次交费的日均耗电量20%;";
					}	
		    }
			
		} catch (SQLException de) {
			de.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					}
				try {
					if(db!=null){
						db.close();
						}
				} catch (DbException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	
	/**
	 * @param blhdl        (String)     倍率耗电量
	 * @param thisdatetime (String)     本次抄表时间
	 * @param lastdatetime (String)     上次抄表时间
	 * @param edhdl        (String)     额定耗电量
	 * @param ecbbl 	   (String)     页面超额定耗电量比例
	 * @param addorimport  (String)     添加电费单还是导入电费单 1：添加，2：导入
	 * @return 			   (String[])	autoaudit[0]: 自动审核状态；autoaudit[1]:自动审核描述；autoaudit[2]用于市级审核状态判断（基本没用，用autoaudit[0]即可）；autoaudit[3]：额定耗电量超标比例；
	 * @return  autoaudit[4]：周期；autoaudit[5]：额定耗电量
	 * @see 本次电量上下浮动超过站点额定耗电量计算值的20% （hdl=edhdl*天数）
	 * Bchdl <hdl*(1-10/100)或者
	 * Bchdl >hdl*(1+10/100)
	 * 自动审核状态，autoauditstatus为0 ,
     * 自动审核描述：autoauditdescription = 
     * "本次电量上下浮动超过站点额定耗电量计算值的20%";
     * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkBcdl(String blhdl,String thisdatetime,String lastdatetime,String edhdl,String dbid,String ecbbl,String addorimport){ 
		String[] autoaudit = new String[6];
		String flag = "1";//电量标识
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		String edcbbl1 = "";
		double edcbbl = 0.00;
		
			long ts = getQuot(lastdatetime, thisdatetime) + 1;
			String edsql = "SELECT Z.EDHDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID='"+ dbid + "'";
			DataBase db = new DataBase();
			ResultSet rs = null;
				try {
					
					try {
						db.connectDb();
						rs = db.queryAll(edsql.toString());
					} catch (DbException e) {
						e.printStackTrace();
					}
					
					if (rs.next()) {
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						
						if("".equals(edhdl)){
							edhdl="0";
						}
						if("0".equals(edhdl)){//额定耗电量为空则不计算
							autoauditstatus = "0";
							autoauditdescription = "站点额定耗电量为空;";
							flag = "0";
							autoaudit[0] = autoauditstatus;
							autoaudit[1] = autoauditdescription;
							autoaudit[2] = flag;
							autoaudit[3] = "";
							autoaudit[4] = Long.toString(ts);
							autoaudit[5] = "";
							return autoaudit;
						}
						double hdl = Double.parseDouble(edhdl) * ts;//本地定标
						double bchdl = Double.parseDouble(blhdl);//本次耗电量
						edcbbl = Format.d2(((bchdl - hdl)/hdl)*100);
						edcbbl1 = Double.toString(edcbbl);
						if(bchdl < hdl*(1-0.1) || bchdl > hdl*(1+0.1)){
							autoauditstatus = "0";
							autoauditdescription = "本次电量上下浮动超过站点额定耗电量计算值的10%;";
							flag = "0";
						}
						}		
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						if(rs!=null){
							rs.close();
							}
						try {
							if(db!=null){
								db.close();
								}
						} catch (DbException e) {
							e.printStackTrace();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		autoaudit[2] = flag;
		autoaudit[3] = edcbbl1;
		autoaudit[4] = Long.toString(ts);
		autoaudit[5] = edhdl;
		return autoaudit;
	}
	
	/**
	 * @param scbbl          省定标电量超标比例   
	 * @param addorimport  (String)     添加电费单还是导入电费单 1：添加，2：导入
	 * @return (String[])	 autoaudit[0]:自动审核状态;autoaudit[1]:自动审核描述;
	 * @see 本次电量上下浮动超过全省定标的10%
	 * |超标比例|>10%
	 */
	public synchronized String[]checkBcdl2(String scbbl){
		String[] autoaudit = new String[2];
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		if(Math.abs(Format.str_d(scbbl))>10){
			autoauditstatus = "0";
			autoauditdescription = "本次电量超过站点省定标电量计算值的10%;";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	/**
	 * @param elecinfo (ElectricityInfo)
	 * @return (String[])	 自动审核状态，自动审核描述
	 * @see 电费自动审核1
	 * 电量审核通过
	 *	查看电费调整是否等于空 ，当为空的时候备注memo(电费) 可以为空，
	 *	但是当电费调整不为空的时候 memo必须有信息，
	 *	否则：自动审核状态，autoauditstatus为0 ,
	 * 	自动审核描述： autoauditdescription = "用电费用调整没有说明！";
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkElectric1(ElectricityInfo elecinfo){ 
		String[] autoaudit = new String[2]; 
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		String floatpay = elecinfo.getFloatpay();//电费调整
		String memo = elecinfo.getMemo1();//(电费)备注
			if("".equals(floatpay) || null == floatpay || "0".equals(floatpay) || "0.0".equals(floatpay) || "0.00".equals(floatpay)){
				//电费调整为空 ,备注 空不空无所谓
			}else if(null ==memo || "".equals(memo) || "null".equals(memo)){
					autoauditstatus = "0";
					autoauditdescription = "用电费用调整没有说明！";
			}
				

		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	 
	/**
	 * @param pjje (String) 票据金额
	 * @return  (String[])	自动审核状态，自动审核描述
	 * @see 电费自动审核2
	 * C2 票据金额是否为空：
	 * 如果票据金额为空：自动审核不通过
	 * autoauditstatus = "0";
	 * autoauditdescription = "票据金额为空！";
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkElectric2(String pjje){ 
		String[] autoaudit = new String[2]; 
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		if(null == pjje || "0.00".equals(pjje) || "".equals(pjje) || "0".equals(pjje) || "0.0".equals(pjje)){
			autoauditstatus = "0";
			autoauditdescription = "票据金额为空！";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	
	/**
	 * @author WangYiXiao 2014-2-19
	 * @param dbid 电表id
	 * @param bccbtime 本次抄表时间
	 * @param sccbtime 上次抄表时间
	 * @param actualpay 实际电费金额
	 * @param actualelectric 实际用电量
	 * @param edhdl 额定耗电量
	 * @param sqdbdl 全省定标电量
	 * @param scbbl 全省定标电量超标比例（页面）
	 * @param ecbbl 额定耗电量超标比例
	 * @param addorimport 增加or导入 1：增加，2：导入
	 * @Param jszq结算周期（计算好的）
	 * @param zqBZW: 1是导入计算好的周期，2是通过上次抄表时间和本次抄表时间计算的周期
	 * @see 是否异常及高额电费
	 * @return "1":是，"0":否
	 */
	public synchronized String[] checkExceptAndHighOld(String dbid,String qsdbdl,String actualpay,String actualelectric,String bccbtime,String sccbtime,String scbbl,String ecbbl,String addorimport,String jszq,String zqBZW){
		String[] audit = new String[3];
		String flag = "0";//是否是异常，高额，1:是，0：否
		String description = "";//描述信息
		String property ="";//站点属性
		double zhiliu = 0;//直流
		double jiaoliu = 0;//交流
		double dianfei = 0;//电费
		double chaobiaofei = 0;//超标电费
		double chaobiaobili1 = 0;//超标比例1（省定标）
		double chaobiaobili2 = 0;//超标比例2（额定耗电量）
		long zhouqi = getQuot(sccbtime,bccbtime)+1;//周期
		
		//查询出的数据
		String edhdl = null;
		String zlfh1 = null;
		String jlfh1 = null;
		String df1 = null;
		String cbdf1 = null;
		double zlfh = 0;
		double jlfh = 0;
		double df = 0;
		double cbdf = 0;
		double cbbl = 0;
		String sql = "SELECT ZLFH,JLFH,PROPERTY FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = ?"; 
		String sql1 = "SELECT ZLFH,JLFH,DF,CBDF,CBBL FROM FILTER WHERE ZDSXCODE = ?";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		DataBase db = new DataBase();
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,dbid);
			rs = ps.executeQuery();
			while(rs.next()){
				zhiliu = rs.getDouble(1);
				jiaoliu = rs.getDouble(2);
				property = rs.getString(3);
			}
			if("zdsx07".equals(property)){//IDC机房,如果是idc机房则是异常及高额点，不需计算
				flag = "1";
				description = "高额及异常：属性为IDC机房;";
			}else {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, property);
			rs = ps.executeQuery();
			while(rs.next()){
				zlfh1 = rs.getString(1);
				jlfh1 = rs.getString(2);
				df1 = rs.getString(3);
				cbdf1 = rs.getString(4);
				zlfh = rs.getDouble(1);
				jlfh = rs.getDouble(2);
				df = rs.getDouble(3);
				cbdf = rs.getDouble(4);
				cbbl = rs.getDouble(5);
			}
			dianfei = Double.parseDouble(actualpay)/zhouqi;
			chaobiaofei = dianfei - df;
			
			//if("1".equals(addorimport)){//增加
				//if("".equals(scbbl) || null == scbbl){
					//scbbl = "0";
				//}
				//chaobiaobili1 = Math.abs(Double.parseDouble(scbbl));
				//chaobiaobili2 = Math.abs(Double.parseDouble(ecbbl));
				
			//}else if("2".equals(addorimport)){//导入
			double bchdl = Double.parseDouble(actualelectric);//本次耗电量
			String edsql = "SELECT Z.QSDBDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID= ?";
			System.out.println("查询省定标");
			String edsql1 = "SELECT Z.EDHDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID= ?";
			System.out.println("查询额定耗电量");
				
						ps = conn.prepareStatement(edsql);
						ps.setString(1, dbid);
						rs = ps.executeQuery();
					if (rs.next()) {
						qsdbdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						if("".equals(qsdbdl)){
							qsdbdl="0";
						}
						double qsdbdll = Double.parseDouble(qsdbdl) * zhouqi;
						chaobiaobili1 = Math.abs((bchdl-qsdbdll)/qsdbdll); 
					}
					ps = conn.prepareStatement(edsql1);
					ps.setString(1, dbid);
					rs = ps.executeQuery();
					if(rs.next()){
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						if("".equals(edhdl)){
							edhdl="0";
						}
						double hdl = Double.parseDouble(edhdl) * zhouqi;//本地定标
						chaobiaobili2 = Math.abs((bchdl - hdl)/hdl);//取绝对值
					}
			//}		
			if("".equals(edhdl) || null == edhdl || "0".equals(edhdl) || "0.0".equals(edhdl) || "0.00".equals(edhdl)){
				flag = "1";
				description = "异常及高额:额定耗电量为空;";
			}else if("".equals(qsdbdl) || null == qsdbdl || "0".equals(qsdbdl) || "0.0".equals(qsdbdl) || "0.00".equals(qsdbdl)){
				flag = "1";
				description = "异常及高额:全省定标电量为空;";
			}else{
				if("zdsx05".equals(property)){//接入网
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "异常及高额:直流"+zlfh1+"(A)以上;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "异常及高额:电费大于"+df1+"(元/日);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "异常及高额:超标电费大于"+cbdf1+"(元/日);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "异常及高额:超全省定标电量比例大于"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "异常及高额:超额定耗电量比例大于"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx02".equals(property)){//基站
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "异常及高额:直流"+zlfh1+"(A)以上;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "异常及高额:电费大于"+df1+"(元/日);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "异常及高额:超标电费大于"+cbdf1+"(元/日);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "异常及高额:超全省定标电量比例大于"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "异常及高额:超额定耗电量比例大于"+(int)(cbbl*100)+"%";
					 }
				}else if("zdsx01".equals(property)){//局用机房
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "异常及高额:直流"+zlfh1+"(A)以上;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "异常及高额:交流"+jlfh1+"(A)以上;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "异常及高额:电费大于"+df1+"(元/日);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "异常及高额:超标电费大于"+cbdf1+"(元/日);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "异常及高额:超全省定标电量比例大于"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "异常及高额:超额定耗电量比例大于"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx03".equals(property)){//营业网点
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "异常及高额:直流"+zlfh1+"(A)以上;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "异常及高额:交流"+jlfh1+"(A)以上;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "异常及高额:电费大于"+df1+"(元/日);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "异常及高额:超标电费大于"+cbdf1+"(元/日);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "异常及高额:超全省定标电量比例大于"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "异常及高额:超额定耗电量比例大于"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx06".equals(property)){//乡镇支局
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "异常及高额:直流"+zlfh1+"(A)以上;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "异常及高额:交流"+jlfh1+"(A)以上;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "异常及高额:电费大于"+df1+"(元/日);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "异常及高额:超标电费大于"+cbdf1+"(元/日);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "异常及高额:超全省定标电量比例大于"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "异常及高额:超额定耗电量比例大于"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx04".equals(property)){//其他
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "异常及高额:直流"+zlfh1+"(A)以上;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "异常及高额:交流"+jlfh1+"(A)以上;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "异常及高额:电费大于"+df1+"(元/日);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "异常及高额:超标电费大于"+cbdf1+"(元/日);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "异常及高额:超全省定标电量比例大于"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "异常及高额:超额定耗电量比例大于"+(int)(cbbl*100)+"%;";
					 }
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		audit[0] = flag;
		audit[1] = description;
		audit[2] = property;
		return audit;
	}
	
	
	
	
	public synchronized String[] checkExceptAndHigh(String dbid,String actualpay,String actualelectric,String bccbtime,String sccbtime,String scbbl){
		String[] audit = new String[3];
		String flag = "0";//是否是异常，高额，1:是，0：否
		String description = "";//描述信息
		String property ="";//站点属性
		double zhiliu = 0;//直流
		double jiaoliu = 0;//交流
		double dianfei = 0;//电费
		double chaobiaofei = 0;//超标电费
		double chaobiaobili1 = Format.str_d(scbbl);//超标比例1（省定标）
		double chaobiaobili2 = 0;//超标比例2（额定耗电量）
		long zhouqi = getQuot(sccbtime,bccbtime)+1;//周期
		
		//查询出的数据
		String edhdl = null;
		double zlfh = 0;
		double jlfh = 0;
		double df = 0;
		double cbdf = 0;
		double cbbl = 0;
		String sql = "SELECT ZLFH,JLFH,PROPERTY FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = ?"; 
		String sql1 = "SELECT ZLFH,JLFH,DF,CBDF,CBBL FROM FILTER WHERE ZDSXCODE = ?";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		DataBase db = new DataBase();
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,dbid);
			rs = ps.executeQuery();
			while(rs.next()){
				zhiliu = rs.getDouble(1);
				jiaoliu = rs.getDouble(2);
				property = rs.getString(3);
			}
			if("zdsx07".equals(property)){//IDC机房,如果是idc机房则是异常及高额点，不需计算
				flag = "1";
				description = "高额及异常;";
			}else {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, property);
			rs = ps.executeQuery();
			while(rs.next()){
				zlfh = rs.getDouble(1);
				jlfh = rs.getDouble(2);
				df = rs.getDouble(3);
				cbdf = rs.getDouble(4);
				cbbl = rs.getDouble(5);
			}
			dianfei = Double.parseDouble(actualpay)/zhouqi;
			chaobiaofei = dianfei - df;
			
			//if("1".equals(addorimport)){//增加
				//if("".equals(scbbl) || null == scbbl){
					//scbbl = "0";
				//}
				//chaobiaobili1 = Math.abs(Double.parseDouble(scbbl));
				//chaobiaobili2 = Math.abs(Double.parseDouble(ecbbl));
				
			//}else if("2".equals(addorimport)){//导入
			double bchdl = Double.parseDouble(actualelectric);//本次耗电量
			String edsql1 = "SELECT Z.EDHDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID= ?";
			System.out.println("查询额定耗电量");

					ps = conn.prepareStatement(edsql1);
					ps.setString(1, dbid);
					rs = ps.executeQuery();
					if(rs.next()){
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						if("".equals(edhdl)){
							edhdl="0";
						}
						double hdl = Double.parseDouble(edhdl) * zhouqi;//本地定标
						chaobiaobili2 = Math.abs((bchdl - hdl)/hdl);//取绝对值
					}
			//}		
			if("".equals(edhdl) || null == edhdl || "0".equals(edhdl) || "0.0".equals(edhdl) || "0.00".equals(edhdl)){
				flag = "1";
				description = "异常及高额;";
			}else{
				if("zdsx05".equals(property)){//接入网
					 if(zhiliu>zlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl ){
						flag = "1";
						description = "异常及高额;";
					}
				}else if("zdsx02".equals(property)){//基站
					if(zhiliu>zlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl || chaobiaobili2>cbbl){
						flag = "1";
						description = "异常及高额;";
					}
				}else if("zdsx01".equals(property)){//局用机房
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl){
						flag = "1";
						description = "异常及高额";
					}
				}else if("zdsx03".equals(property)){//营业网点
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl){
						flag = "1";
						description = "异常及高额;";
					}
				}else if("zdsx06".equals(property)){//乡镇支局
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl || chaobiaobili2>cbbl ){
						flag = "1";
						description = "异常及高额;";
					}
				}else if("zdsx04".equals(property)){//其他
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl ){
						flag = "1";
						description = "异常及高额;";
					}
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		audit[0] = flag;
		audit[1] = description;
		audit[2] = property;
		return audit;
	}
	
	
	/**
	 * @param zdid
	 * @see 是否是1.2万个点
	 * @author WangYIXIao 2014-2-19
	 * @return 
	 */
	public synchronized String[] checckSite(String dbid){
		String [] site = new String[2];
		String flag = "0";//是否需要市级和市级主任审核的1.2万个点 0：否，1是
		String description = "";//描述
		PreparedStatement ps = null;
		String sql = "SELECT ZD.ID FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.SHBZW = '1' AND ZD.ID = DB.ZDID AND DB.DBID = ?";
		ResultSet rs = null;
		Connection conn = null;
		DataBase db = new DataBase();
		try {
			 conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,dbid);
			rs = ps.executeQuery();
			 if(rs.next()){
				 flag = "1";
				 description = "1.2万个点";
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		site[0] = flag;
		site[1] = description;
		return site;
	}
	
	/**
	 * @see 电费调整，电量调整检查
	 * 电费调整>60,电量调整绝对值>500
	 * @param elecfeesadjust (String) 电费调整
	 * @param elecadjust (String) 电量调整
	 * @return (boolean[]) checkstatus[0]:是否电费调整>60,1:是，0：否；checkstatus[1]:电费调整描述信息;checkstatus[2]:是否电费调整>60并且电量调整绝对值大于500，1：是，0：否；checkstatus[3]:电费，电量调整描述信息
	 * @author WangYiXiao 2014-3-4
	 */
	public synchronized String[] adjustCheck1(String elecfeesadjust,String elecadjust){
		String[] checkstatus = new String[4];
		String checkfees = "0";//电费标识
		String checkfeesdescription = "";//电费描述
		String checkelec = "0";//电量标识
		String check = "0";//电费，电量标识
		String feesandelecdescription = "";//电费，电量描述
		//电费
		elecfeesadjust = "".equals(elecfeesadjust.trim()) ? "0" : elecfeesadjust.trim();//去空格
		double fees = Double.parseDouble(elecfeesadjust);
		checkfees = fees>60?"1":"0";//电费调整状态
		checkfeesdescription = "1".equals(checkfees)?"电费调整大于60;":"";
		//电量
		elecadjust = "".equals(elecadjust.trim()) ? "0" : elecadjust.trim();//去空格
		double elec = Math.abs(Double.parseDouble(elecadjust));
		checkelec = elec>500?"1":"0";
		//电量，电费
		if("1".equals(checkfees) && "1".equals(checkelec)){
			check = "1";
			feesandelecdescription = "电费调整大于60并且电量调整绝对值大于500度";
		}
		//赋值
		checkstatus[0] = checkfees;
		checkstatus[1] = checkfeesdescription;
		checkstatus[2] = check;
		checkstatus[3] = feesandelecdescription;
		//返回状态
		return checkstatus;
	}
	
	/**
	 * @see 单价调整是否大于5%
	 * (本次-上次)/上次>0.05
	 * @param lastunitprice (String) 上次单价
	 * @param thisunitprice (String) 本次单价
	 * @return (String[]) 1:单价调整大于5%；0:单价调整未大于5%
	 * @author WangYiXiao 2014-3-4
	 */
	public synchronized String[] adjustCheck2(String lastunitprice,String thisunitprice){
		String[] check = new String[2];
		String checkstatus = "0";
		String checkdescription = "";
		//上次或本次单价为空则不进行判断
		if(null == lastunitprice || "".equals(lastunitprice.trim()) || null == thisunitprice){
			check[0] = checkstatus;
			check[1] = checkdescription;
			return check;
		}
		double lastpe = Double.parseDouble(lastunitprice.trim());
		double thispe = Double.parseDouble(thisunitprice.trim());
		checkstatus = (thispe - lastpe) / lastpe > 0.05 ? "1":"0";
		checkdescription = "1".equals(checkstatus)?"单价调整大于5%;":"";
		check[0] = checkstatus;
		check[1] = checkdescription;
		return check;
	}
	
	/**
	 * @see 票据时间对应月份-报账月份>=2
	 * @param thisdatetime (String) 本次抄表时间
	 * @param accountmonth (String) 报账月份
	 * @return (String[]) check[0]：是否票据时间对应月份-报账月份>=2，1：是，0：否；check[1]：描述信息
	 * @author WangYiXiao 2014-3-6
	 * @update WangYiXiao 2014-3-21 票据时间 改为 本次抄表时间
	 */
	public synchronized String[] chaYue(String thisdatetime,String accountmonth){
		String[] check = new String[2];
		String checkflag = "0";
		String checkdescription = "";
		//票据 时间 改为 本次抄表时间
		int byear = Integer.parseInt(thisdatetime.substring(0, 4));//年
		int byue = Integer.parseInt(thisdatetime.substring(5, 7));//月
		//报账月份
		int ayear = Integer.parseInt(accountmonth.substring(0, 4));//年
		int ayue = Integer.parseInt(accountmonth.substring(5, 7));//月
		//比较
		int yue = (byear-ayear)*12 + (byue-ayue); //差
		if(yue >= 2){
			checkflag = "1";
			checkdescription = "本次抄表时间对应月份-报账月份>=2;";
		}
		check[0] = checkflag;
		check[1] = checkdescription;
		return check;
	}
	
	/**
	 * @see 是否电费正调整大于10并且电量负调整
	 * @param fee (String) 电费调整
	 * @param elec (String) 电量调整
	 * @return (String[]) auto[0]:标志位，1：是，0：否；auto[1]：描述信息
	 */
	public synchronized String[] adjustFeeAndElec1(String fee,String elec){
		String[] auto = new String[2];
		String flag = "0";//标志
		String description = "";//描述
		if( Double.parseDouble(fee) > 10 && Double.parseDouble(elec) < 0 ){
			flag = "1";
			description = "电费正调整大于10并且电量负调整;";
		}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see 电量（正）负调整，并且电费（正）负调整：（电量调整*单价-电费调整）/电费调整>1.1 
	 * @param elec (String) 电量调整
	 * @param fee  (String)电费调整
	 * @param unitprice (String) 单价
	 * @return (String[]) auto[0]:标志位，1：是，0：否；auto[1]：描述信息
	 */
	public synchronized String[] adjustFeeAndElec2(String elec,String fee,String unitprice){
		String[] auto = new String[2];
		String flag = "0";//标志
		String description = "";//描述
		double ele = Double.parseDouble(elec);
		double fe = Double.parseDouble(fee);
		double up = Double.parseDouble(unitprice);
		//System.out.println("ele:"+ele);
		//System.out.println("fe:"+fe);
		//System.out.println("up:"+up);
			if( (ele > 0 && fe > 0 && (ele * up - fe)/fe > 1.1) || (ele < 0 && fe < 0 && (ele * up - fe)/fe > 1.1)){
				flag = "1";
				description = "（电量调整*单价-电费调整）/电费调整>1.1;";
			}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see 如果是5,6,7,8,9,10月份电量调整*倍率大于800需要四级审核  否则大于500 
	 * @param elec (String) 电量调整
	 * @return (String[]) auto[0]:标志位，1：是，0：否；auto[1]：描述信息
	 */
	public synchronized String[] adjustElec(String elec,String beilv){
		Date date = new Date();
		int yue = date.getMonth()+1;//当前月份
		String[] auto = new String[2];
		String flag = "0";//标志
		String description = "";//描述
		if(beilv==null||beilv.equals("")||beilv.equals("0")||beilv.equals("null")){
			beilv="1";
		}
		double ele = Double.parseDouble(elec)*Double.parseDouble(beilv);
		//System.out.println("ele:"+ele +"  beilv:" +beilv);
			if(yue == 5 || yue == 6 || yue == 7 || yue == 8 || yue == 9 || yue == 10 ){// yue>=5 && yue<=10
				if(ele > 800){
				flag = "1";
				description = "电量调整大于800;";
				}
			}else if(yue == 11 || yue == 12 || yue == 1 || yue == 2 || yue == 3 || yue == 4){
				if(ele > 500){
					flag = "1";
					description = "电量调整大于500;";
					}
			}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see 本次电费金额大于区县上月平均电费金额
	 * @param bcelefees (String) 本次电费金额
	 * @param (String) shi
	 * @param {String} xian
	 * @return (String[]) auto[0]:标志位;auto[1]:
	 * @author WangYiXiao 2014-3-26
	 */
	public synchronized String[] checkThisFees(String bcelefees,String shi,String xian){
		String[] auto = new String[2];
		String lastaveragefee = "";//区县上月平均电费
		String flag = "0";//标志
		String description = "";//描述
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT PAYAVG FROM (SELECT PAYAVG FROM T_PDCOUNT TECT WHERE SHI = ? AND XIAN = ? ORDER BY TECT.ACCOUNTMONTH DESC NULLS LAST ) WHERE ROWNUM = 1";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, shi);
			ps.setString(2, xian);
			System.out.println("区县上月平均电费");
			rs = ps.executeQuery();
			if(rs.next()){
				lastaveragefee = rs.getString("PAYAVG") == null ? "" : rs.getString("PAYAVG");
				if(!"".equals(lastaveragefee)){
					if(Double.parseDouble(bcelefees)>Double.parseDouble(lastaveragefee)){
						flag = "1";
						description = "本次电费金额大于区县上月平均电费金额;";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see 外借电单价大于本地上月报账平均值1.1倍以上
	 * @param unitprice (String) 外借电单价
	 * @param shi (String)
	 * @param xian (String)
	 * @return (String[]) auto[0]:标志位;auto[1]:
	 * @author WangYiXiao 2014-3-27
	 */
	public synchronized String[] OutElecUnitPrice(String unitprice,String shi,String xian){
		String[] auto = new String[2];
		String flag = "0";//标志
		String description = "外借电单价小于等于本地上月报账平均值1.1倍;";//描述
		double averageprice = 0;
		String sql = "SELECT UNITPRICEAVG FROM (SELECT UNITPRICEAVG FROM T_INPAYCOUNT TECT WHERE SHI = ?  ORDER BY TECT.ACCOUNTMONTH DESC NULLS LAST) WHERE ROWNUM = 1";//本地上月报账平均值
		DataBase db = new DataBase();
		Connection connc = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connc = db.getConnection();
			ps = connc.prepareStatement(sql);
			ps.setString(1, shi);
			//ps.setString(2, xian);
			System.out.println("本地上月报账平均值");
			rs = ps.executeQuery();
			if(rs.next()){
				averageprice = rs.getDouble("UNITPRICEAVG");
				if(Double.parseDouble(unitprice) > averageprice * 1.1){
					flag = "1";
					description = "";
				}
			}else{
				flag = "0";
			    description = "本地上月报账平均单价为空;";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, connc);
		}
		auto[0] = flag;
		auto[1] = description;
		return auto;	
	}
	
	/**
	 * @see 获取某电表所属城市，区县
	 * @param dbid
	 * @return (String[]) shi;xian
	 * @author WangYiXiao 2014-3-27
	 */
	public synchronized String[] getShiAndXian(String dbid){
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] audit = new String[2];
		String shi = "";
		String xian = "";
		String sql1= "SELECT ZD.SHI SHI,ZD.XIAN XIAN FROM ZHANDIAN ZD, DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = ?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, dbid);
			rs = ps.executeQuery();
			while(rs.next()){
				shi = rs.getString("SHI");
				xian = rs.getString("XIAN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		audit[0] = shi;
		audit[1] = xian;
		return audit;

	}
/** 
 * @author WangYiXiao 2014-10-25
 * @update WangYiXiao 2015-1-21 非生产用原超标比例公式
 * 非生产定义:
 *4.站点属性为乡镇支局站点类型除模块局的
 *2.站点属性为局用机房站点类型除核心机房
 *3.站点属性为营业网点
 *  非生产用原超标比例公式:((blhdl - qsdbdl*jszq)/qsdbdl*jszq)*100
 * @param dbydl 电表用电量 (本次读数-上次读数)*倍率
 * @param thistime 本次抄表时间
 * @param lasttime 上次抄表时间
 * @param shi 市
 * @param property 站点属性
 * @param zlfh 直流负荷
 * @param jlfh 交流负荷 
 * @param edhdl 额定耗电量
 * @param scb 生产标
 * @param dbid 电表id
 * @param blhdl 倍率耗电量
 * @param qsdbdl 全省定标电量
 * @param zdlx 站点类型
 * @return cbbl[0]超标比例  cbbl[1]合并周期 cbbl[2]标准值
 */
public synchronized String[] getQsdbdlCbbl(String dbydl,String thistime,String lasttime,
		String shi,String property,String zlfh,String jlfh,String edhdl,String scb,String dbid,String blhdl,String qsdbdl,String zdlx){
	String[] cbbl = new String[3];//超标比例,合并周期,标准值
	BigDecimal hbzq = new BigDecimal("0");//合并周期
	BigDecimal bzz = null;//标准值
	BigDecimal bl = null;//超标比例
	int ktsl = 0;//空调数
	Connection connc = null;
	Statement st = null;
	ResultSet rs = null;
	String sql = "SELECT T.YMONTH,T.EMONTH,T.SMONTH,T.SIMONTH,T.WMONTH,T.LMONTH,T.QMONTH,T.BMONTH,T.JMONTH,T.SHIMONTH,T.SYMONTH,T.SEMONTH "
			+ "FROM YFXS T WHERE T.SHICODE = '"+shi+"'";
	DataBase db = new DataBase();
	//--月份系数map
	Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
	//--空调系数
	BigDecimal ktxs = null;
	//--房屋系数
	BigDecimal fwxs = new BigDecimal("0");//只有基站有房屋系数,其他都加0
	try {
		connc = db.getConnection();
		st = connc.createStatement();
		rs = st.executeQuery(sql);
		if(rs.next()){
			map.put("1", rs.getBigDecimal("YMONTH"));
			map.put("2", rs.getBigDecimal("EMONTH"));
			map.put("3", rs.getBigDecimal("SMONTH"));
			map.put("4", rs.getBigDecimal("SIMONTH"));
			map.put("5", rs.getBigDecimal("WMONTH"));
			map.put("6", rs.getBigDecimal("LMONTH"));
			map.put("7", rs.getBigDecimal("QMONTH"));
			map.put("8", rs.getBigDecimal("BMONTH"));
			map.put("9", rs.getBigDecimal("JMONTH"));
			map.put("10", rs.getBigDecimal("SHIMONTH"));
			map.put("11", rs.getBigDecimal("SYMONTH"));
			map.put("12", rs.getBigDecimal("SEMONTH"));
		}
		BigDecimal zlfhbd = new BigDecimal(zlfh);//直流负荷
		BigDecimal bigd = new BigDecimal("100");
		String str = "";
		if("zdsx02".equals(property)){//基站
			str = " T.JZKTXS ";
			
			String sql2 = "SELECT FS.FXXS FXXS FROM FWXS FS WHERE FS.YFLXCODE=(SELECT ZD.YFLX FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = '"+dbid+"')";
			rs = st.executeQuery(sql2);
			if(rs.next()){
				fwxs = rs.getBigDecimal("FXXS");
			}
		}else if("zdsx05".equals(property)){//接入网
			str = "T.JRWKTXS";
		}else if("zdsx06".equals(property)){//乡镇支局
			str = "T.XZZJKTXS";
		}else if("zdsx01".equals(property)){//局用机房
			str = "T.JYJFKTXS";
		}else if("zdsx04".equals(property)){//其他
			str = "T.QTKTXS";
		}else if("zdsx07".equals(property)){//IDC机房
			str = "T.IDCJFKTXS";
		}else if("zdsx03".equals(property)){//营业网点
			str = "T.YYWDKTXS";
		}else {//盘活
			str = "T.QTKTXS";
		}
		String sql1 = "SELECT "+str+" FROM KTXS T WHERE "+(zlfhbd.compareTo(bigd)==-1?
				("T.KSZLFH<="+zlfh+" AND T.JSZLSH>"+zlfh):
					("T.KSZLFH<="+zlfh+" AND T.JSZLSH = 0 "));
		System.out.println(sql1);
		rs = st.executeQuery(sql1);
		if(rs.next()){
			ktxs = rs.getBigDecimal(1);
		}
		if("zdsx05".equals(property) || "zdsx03".equals(property)){//接入网/营业网点
			//---如果本地标(额定耗电量)或生产标或直流或者交流为0 ，为空  空调系数为0
			if(Format.str_d(edhdl)==0||Format.str_d(scb)==0||Format.str_d(zlfh)==0||Format.str_d(jlfh)==0){
				ktxs = new BigDecimal("0");
			}else{
				BigDecimal a = null;
				BigDecimal b = null;
				BigDecimal c = null;
				BigDecimal zldl = new BigDecimal(zlfh).multiply(new BigDecimal("1.52"));//直流电量
				BigDecimal jldl = new BigDecimal(jlfh).multiply(new BigDecimal("5.28"));//交流电量
				a = zldl.min(jldl);
				b = new BigDecimal(edhdl).min(new BigDecimal(scb));
				c = a.min(b);//最小值
				ktxs = ktxs.divide(c,2,BigDecimal.ROUND_HALF_UP);
			}
		}
		String ktssql = "SELECT ZD.KTS FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID='"+dbid+"'";//站点表kts
		rs = st.executeQuery(ktssql);
		if(rs.next()){
		ktsl = rs.getInt("KTS");	
		}
		ktxs = ktsl>0?ktxs:new BigDecimal("0");//如果空调数小于等于0则空调系数为0
		BigDecimal jbh = new BigDecimal("1").add(fwxs);//局部和 = 基础系数 + 房屋系数;
		long[][] yue = new DateUtil().getYue(lasttime, thistime);
		int  leng = yue.length;
		for(int i = 0 ; i < leng ; i++){
			hbzq = hbzq.add(new BigDecimal(yue[i][1]).multiply(jbh.add(map.get(String.valueOf(yue[i][0])).multiply(ktxs)))); //合并周期 = 合并周期 + 每月天数*(最终系数=局部和+月份系数*空调系数 )
		}
		bzz = hbzq.multiply(new BigDecimal(scb));//标准值=合并周期*生产标
		// *4.站点属性为乡镇支局站点类型除模块局的
		// *2.站点属性为局用机房站点类型除核心机房
		// *3.站点属性为营业网点
//		if( ("zdsx06".equals(property) && !"StationType12".equals(zdlx))
//				|| ("zdsx01".equals(property) && !"StationType20".equals(zdlx))
//				|| "zdsx03".equals(property) ){//非生产用原超标比例公式
//			
//		}
		//2015-2-2
		if(judgeFsc(property,zdlx)){//生产
			bl = (new BigDecimal(dbydl).subtract(bzz)).divide(bzz, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));//（电表用电量-标准值）/标准值*100%
		}else{//非生产用原超标比例公式
			long zhouqi = getQuot(lasttime,thistime)+1;//周期
			BigDecimal qsdl = new BigDecimal(qsdbdl).multiply(new BigDecimal(zhouqi));
			bl = (new BigDecimal(blhdl).subtract(qsdl)).divide(qsdl, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));//((blhdl - qsdbdl*jszq)/qsdbdl*jszq)*100
		}
		
		cbbl[0] = Format.str2(bl.toString());
		cbbl[1] = Format.str2(hbzq.toString());
		cbbl[2] = Format.str2(bzz.toString());
	} catch (DbException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		db.free(rs, st, connc);
	}
	return cbbl;
}
/**判断是否是外租回收站点
 * @author WangYiXiao 2015-1-28
 * @param property (String) 站点属性
 * @param zdname (String) 站点名称
 * @return
 */
public synchronized boolean judgeOut(String property,String zdname){
	zdname = null==zdname?"":zdname; 
	//站点属性为 zdsx04 并且 站点名称中含有 回收 即为 外租回收站点
	return ("zdsx04".equals(property) && (zdname.indexOf("回收") != -1)) ? true : false;
}

/** 判断是否为生产类
 * @param property
 * @param zdlx
 * @return
 * @author WangYiXiao 2015-1-28
 * @update WangYiXiao 2015-2-2 判断是否为非生产类改为 判断是否为生产
 */
public synchronized boolean judgeFsc(String property,String zdlx){
	//非生产:
	// *4.站点属性为乡镇支局站点类型除模块局的
	// *2.站点属性为局用机房站点类型除核心机房
	// *3.站点属性为营业网点
//	if( ("zdsx06".equals(property) && !"StationType12".equals(zdlx))
//			|| ("zdsx01".equals(property) && !"StationType20".equals(zdlx))
//			|| "zdsx03".equals(property) ){
//		flag = true;
//	}
	// 上  改为 下
/*	
  	生产类：
  	站点属性为基站、接入网、IDC下所有的站点类型						
	站点属性为局用机房下的：核心机房、局用机房整治、通信大楼						
	站点属性为其他下的：视频监控、其他	(需备注说明)					
	站点属性为乡镇支局下的模块局、乡镇支局、乡镇支局整治、其他(需备注说明)						
	非生产类：
	剩下的所有类型	
*/
	return ("zdsx02".equals(property) || "zdsx05".equals(property) || "zdsx07".equals(property)
			|| ("zdsx01".equals(property) && ("StationType20".equals(zdlx) || "StationType49".equals(zdlx) || "StationType08".equals(zdlx)))
			|| ("zdsx04".equals(property) && ("StationType13".equals(zdlx) || "StationType18".equals(zdlx)))
			|| ("zdsx06".equals(property) && ("StationType12".equals(zdlx) || "StationType25".equals(zdlx) || "StationType48".equals(zdlx) || "StationType18".equals(zdlx)))
			) ? true : false;
	
}
/**判断是否为直供电
 * @param gdfscode
 * @return
 * @author WangYiXiao 2015-1-28
 */
public synchronized boolean judgeZgd(String gdfscode){
	//供电方式为直供电1或直供电2 均为直供电
	return ("gdfs06".equals(gdfscode) || "gdfs07".equals(gdfscode)) ? true : false;
}


/**根据站点属性及类型判断查询那种标准单价
 * @param property
 * @param zdname
 * @param zdlx
 * @param gdfscode
 * @return
 * @author WangYiXiao 2015-1-28
 */
public synchronized String selectUnitprice(String property,String zdname,String zdlx, String gdfscode){
	String leixing = "";
	if(judgeOut(property,zdname)){//外租回收类
		if(judgeFsc(property,zdlx)){//生产类
			if(judgeZgd(gdfscode)){//直供电
				leixing = "WSZHI";
			}else{//非直供电(转供电)
				leixing = "WSZHUAN";
			}
		}else{//非生产类
			if(judgeZgd(gdfscode)){//直供电
				leixing = "WFZHI";
			}else{//非直供电(转供电)
				leixing = "WFZHUAN";
			}
		}
	}else{//支出类
		if(judgeFsc(property,zdlx)){//生产类
			if(judgeZgd(gdfscode)){//直供电
				leixing = "ZSZHI";
			}else{//非直供电(转供电)
				leixing = "ZSZHUAN";
			}
		}else{//非生产类
			if(judgeZgd(gdfscode)){//直供电
				leixing = "ZFZHI";
			}else{//非直供电(转供电)
				leixing = "ZFZHUAN";
			}
		}	
	}
	return leixing;
}
/**判断倍率是否是某些数字的整数倍
 * @param beilv
 * @param bei
 * return 是:false 否:true
 * @author WangYiXiao 2015-2-12
 */
public synchronized boolean beilvBei(String beilv,String[] bei){
	boolean flag = true;
	for( int i=0;i<bei.length;i++){
		flag = (Format.str_d(beilv) % Format.str_d(bei[i]) != 0 ? true:false) && flag;
	}
	return flag;
}

/**加强流程：电费超标比例：(报账电费—标准电量*标准单价) / 标准电量*标准单价*100% ，可配置
 * @param bzz (String)  标准电量 
 * @param bzdj (String) 标准单价
 * @param bzdf (String) 报账电费
 * @return (String) 电费超标比例
 * @author WangYiXiao 2015-2-13
 */
public synchronized String getOverFeesbl(String bzz,String bzdj,String bzdf){
	BigDecimal cbbl;
	BigDecimal dlanddj = new BigDecimal(bzz).multiply(new BigDecimal(bzdj));//标准电量*标准单价
	cbbl = (new BigDecimal(bzdf).subtract(dlanddj)).divide(dlanddj, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
	return Format.str2(cbbl.toString());
}

	 /**
	 * @param time1 (String) 小的时间
	 * @param time2 (String) 大的时间
	 * @return (long) 相差的天数
	 * @see 计算两个日期相差的天数
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized long getQuot(String time1, String time2){
		    long days = 0;
		    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		    try {
		     Date date1 = ft.parse( time1 );
		     Date date2 = ft.parse( time2);
		     days = date2.getTime() - date1.getTime();
		     days = days/1000/60/60/24;//一天的毫秒数
		    } catch (Exception e) {
		     e.printStackTrace();
		    }
		    return days;
	}
}
