package com.ptac.app.electricmanage.electricitybill.tool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanageUtil.Format;
/**
 * @see 电量，电费工具类
 * @author WangYiXiao 2014-1-20
 */
public class ElectricityTool {
    
	/**
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
	public synchronized boolean checkRepeat(String thisdegree,String thisdatetime,String lastdegree,String lastdatetime,String ammeteridFk,String accountmonth){
		DataBase db = new DataBase();
		boolean flag = false;//是否重复标志位
		String sql = "SELECT DD.AMMETERDEGREEID FROM DIANDUVIEW DD,DIANFEIVIEW DF WHERE THISDEGREE='" + thisdegree
		+ "' AND  DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK  AND THISDATETIME='" + thisdatetime
		+ "' AND LASTDEGREE='" + lastdegree
		+ "'AND LASTDATETIME='" + lastdatetime
		+ "' AND AMMETERID_FK='" + ammeteridFk
		+ "'AND ACCOUNTMONTH='" + accountmonth+"'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("重复语句"+sql.toString());
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//释放资源
				try {
					rs.close();
					try {
						db.close();
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
	 *	查看用电量调整是否等于0，空，或者是0.0 ，当为空的时候备注memo(电量) 可以为空，
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
		sql.append("SELECT CASE WHEN (TO_DATE(AD.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AD.LASTDATETIME, 'YYYY-MM-DD')) != '0' THEN AD.blhdl / (TO_DATE(AD.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AD.LASTDATETIME, 'YYYY-MM-DD')+1) ELSE   1 END DAYDEGREE" +
	    		" FROM DIANDUVIEW AD " +
	    		"WHERE AD.THISDATETIME ='"+lastadtime+"' AND AD.AMMETERID_FK='"+ammeterid+"'");
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
						autoauditdescription = "本次交费日均耗电量大于最后一次交费的日均耗电量20%";
					}	
		    }
			
		} catch (SQLException de) {
			de.printStackTrace();
		}finally{
			try {
				rs.close();
				try {
					db.close();
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
	 * @return 			   (String[])	 自动审核状态，自动审核描述
	 * @see 本次电量上下浮动超过站点额定耗电量计算值的20% （hdl=edhdl*天数）
	 * Bchdl <hdl*(1-20/100)或者
	 * Bchdl >hdl*(1+20/100)
	 * 自动审核状态，autoauditstatus为0 ,
     * 自动审核描述：autoauditdescription = 
     * "本次电量上下浮动超过站点额定耗电量计算值的20%";
     * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkBcdl(String blhdl,String thisdatetime,String lastdatetime,String edhdl,String dbid){ 
		String[] autoaudit = new String[4];
		String flag = "1";//电量标识
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
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
					}		
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					try {
						db.close();
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		Double hdl = Double.parseDouble(edhdl) * ts;//本地定标
		Double bchdl = Double.parseDouble(blhdl);//本次耗电量
		Double edcbbl = Format.d2((bchdl-hdl)/hdl);
		if((bchdl-hdl)/hdl >=0.1 && (bchdl-hdl)/ hdl <=0.9){
			autoauditstatus = "0";
			autoauditdescription = "本次电量上下浮动超过站点额定耗电量计算值的10%";
			flag = "0";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		autoaudit[2] = flag;
		autoaudit[3] = edcbbl+"";
		return autoaudit;
	}
	
	/**
	 * @param blhdl (String) 倍率耗电量
	 * @param qsdbdl (String)全省定标电量
	 * @param thisdatetime   本次抄表时间
	 * @param lastdatetime   上次抄表时间
	 * @return (String[])	 自动审核状态，自动审核描述
	 * @see 本次电量上下浮动超过全省定标的10%
	 */
	public synchronized String[]checkBcdl2(String blhdl,String qsdbdl,String thisdatetime,String lastdatetime,String dbid){
		String[] autoaudit = new String[3];
		String flag = "1";//电量标识
		String autoauditstatus = "1";//自动审核状态
		String autoauditdescription = "";// 自动审核描述
		Double bchdl = Double.parseDouble(blhdl);//本次耗电量
		long ts = getQuot(lastdatetime,thisdatetime)+1;//时间差
		String edsql = "SELECT Z.QSDBDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID='"+ dbid + "'";
		ResultSet rs = null;
		DataBase db = new DataBase();
		System.out.println("省定标"+edsql);
			try {
				try {
					db.connectDb();
					rs = db.queryAll(edsql.toString());
				} catch (DbException e) {
					e.printStackTrace();
				}
				if (rs.next()) {
					qsdbdl = (null == rs.getString(1) ? "0" : rs
							.getString(1));
					if("".equals(qsdbdl)){
						qsdbdl="0";
					}
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					try {
						db.close();
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
			
		Double qsdbdll = Double.parseDouble(qsdbdl) * ts;
		if((bchdl-qsdbdll)/qsdbdll >=0.1 && (bchdl-qsdbdll)/ qsdbdll <=0.9){
			autoauditstatus = "0";
			autoauditdescription = "本次电量超过站点省定标电量计算值的10%";
			flag = "0";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		autoaudit[2] = flag;
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
					
				}else{
					if(null ==memo || "".equals(memo) || "null".equals(memo)){
						autoauditstatus = "0";
						autoauditdescription = "用电费用调整没有说明！";
					}
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
	public static void main(String[] args) {
		ElectricityTool t = new ElectricityTool();
		System.out.println(t.getQuot("2013-10-19","2014-02-14" ));
		//t.checkBcdl("2031.4", "2014-02-14", "2013-10-19", "75.00", dbid);
	}
}
