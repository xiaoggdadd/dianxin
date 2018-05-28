package com.ptac.app.financeprovisional.dao;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jsx3.net.Request;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.financeprovisional.bean.FinanceZanGuBean;

public class ZanGuDaoImp implements ZanGuDao{
	//暂估数据处理并保存
	public synchronized String zanGuShuJu(String shi,String zgsj,String zgmonth,String wherestr,String loginId){
		DataBase db = new DataBase();
		String fhxx = "0";//返回信息 0不成功  1 成功
        ResultSet rs = null;
        ResultSet rs1 = null;
        String sj="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
        String sql = "",sql1="";
        long uuid1=System.currentTimeMillis();
        String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
	    String uuid = uuid2+Long.toString(uuid1).substring(3);//uuid   lrren
   		String jzname="",address="",lastdatetime="",thisdatetime="",tianshu="",danjia="",daye="",stationtype="",dfzflxx="",
	       		dianfeizangu ="",actualpay="",cssytime="",dbid="",edhdl="",ww="",zdid="",heji="",dbname="",property="",qsdbdl="",shi1 = "";
   		String bzw1="",bzw = "0",xian = "",xiaoqu = "";
   		double count1=0.0;//合计
   		double danjia1=0.0;
   		double day1=0.0,days=0.0;//day1--周期
   		DecimalFormat mat=new DecimalFormat("0.00");
   		StringBuffer sqlpl=new StringBuffer();
   		StringBuffer sqldel=new StringBuffer();//删除上次查询的暂估内容
   		StringBuffer sqldelyj=new StringBuffer();//删除月结暂估数据重复的站点
   		StringBuffer sqldelht=new StringBuffer();//删除合同暂估数据重复的站点
   		StringBuffer sqldelyh=new StringBuffer();//删除合同月结同时重复的站点
   		//删除上次查询的暂估内容的SQL语句
   		sqldel.append("delete zangushuju z where z.zgmonth='"+zgmonth+"' and z.shi='"+shi+"'");
   		
   		//删除月结暂估数据重复的站点
   		sqldelyj.append("DELETE FROM ZANGUSHUJU Z WHERE " +
   				"Z.ZDID IN (SELECT Z.ZDID FROM ZANGUSHUJU Z " +
   				"WHERE Z.BZW = '1' AND Z.ZGMONTH = '"+zgmonth+"' AND Z.SHI='"+shi+"' " +
   				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1)  AND " +
   				"ROWID NOT IN (SELECT  MIN(Z.ROWID)FROM " +
   				"(SELECT Z.ZDID, MAX(Z.ZANGUSTARTMONTH) AS ZDMONTH FROM ZANGUSHUJU Z WHERE Z.BZW = '1' " +
   				"AND Z.ZGMONTH = '"+zgmonth+"' AND Z.SHI='"+shi+"' GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) B, " +
   				"ZANGUSHUJU Z WHERE Z.ZDID = B.ZDID AND Z.BZW = '1' AND Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"' " +
   				"AND Z.ZANGUSTARTMONTH = B.ZDMONTH   GROUP BY Z.ZDID) " +
   				"AND Z.BZW = '1' AND Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"'");
   		
   	//删除ht暂估数据重复的站点
   		sqldelht.append("DELETE FROM ZANGUSHUJU Z WHERE " +
   				"Z.ZDID IN (SELECT Z.ZDID FROM ZANGUSHUJU Z " +
   				"WHERE Z.BZW = '2' AND Z.ZGMONTH = '"+zgmonth+"' AND Z.SHI='"+shi+"' " +
   				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1)  AND " +
   				"ROWID NOT IN (SELECT  MIN(Z.ROWID)FROM " +
   				"(SELECT Z.ZDID, MAX(Z.ZANGUSTARTMONTH) AS ZDMONTH FROM ZANGUSHUJU Z WHERE Z.BZW = '2' " +
   				"AND Z.ZGMONTH = '"+zgmonth+"' AND Z.SHI='"+shi+"' GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) B, " +
   				"ZANGUSHUJU Z WHERE Z.ZDID = B.ZDID AND Z.BZW = '2' AND Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"' " +
   				"AND Z.ZANGUSTARTMONTH = B.ZDMONTH   GROUP BY Z.ZDID) " +
   				"AND Z.BZW = '2' AND Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"'");
   		
   		//删除合同月结同时重复的站点
   		sqldelyh.append("DELETE FROM ZANGUSHUJU Z WHERE Z.ZDID IN " +
   				"(SELECT Z.ZDID FROM ZANGUSHUJU Z WHERE Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"' " +
   				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) AND Z.BZW='2' " +
   				"AND Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"'");
   		
   		
   		
		//查询电费表里的暂估数据  月结和预付费
  
   		  sql="SELECT DISTINCT ZD.JZNAME,DB.ZGDJ,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AMM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AMM.ACTUALPAY, "+   //
  		"ZD.STATIONTYPE,"+
  		"DB.DFZFLX," +
  		"ZD.EDHDL,ZD.ID,DB.DBID, DB.DBNAME,ZD.PROPERTY,ZD.SCB,ZD.SHI,ZD.XIAN,ZD.XIAOQU " +
  		"  FROM (SELECT DISTINCT DD.AMMETERID_FK, DD.LASTDATETIME,DD.THISDATETIME, DF.ACTUALPAY, DD.MANUALAUDITSTATUS, B.DATETIME"+
  		" FROM DIANDUVIEW DD,DIANFEIVIEW DF, (SELECT MAX(AMM.THISDATETIME)  AS DATETIME, AMM.AMMETERID_FK   FROM DIANDUVIEW AMM,DIANFEIVIEW DF"+
  		" WHERE 1=1 and DF.MANUALAUDITSTATUS ='2'  AND AMM.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK   GROUP BY AMM.AMMETERID_FK) B"+   //
  		" WHERE DD.AMMETERID_FK = B.AMMETERID_FK  AND DD.THISDATETIME = B.DATETIME   AND DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK  and DF.MANUALAUDITSTATUS ='2') AMM, DIANBIAO DB,  ZHANDIAN ZD"+     //
  		" WHERE DB.DBID = AMM.AMMETERID_FK(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' " +
  		"AND (DB.DFZFLX='dfzflx01' OR DB.DFZFLX='dfzflx03') AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1'  AND (zd.property<>'zdsx04' AND zd.jzname NOT LIKE '%回收%') AND ZD.STATIONTYPE<>'StationType41'  "+wherestr+" ";
  		//" AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";//ORDER BY  JZNAME

		//查询预付费表里的    合同
		sql1="SELECT DISTINCT ZD.JZNAME,DB.ZGDJ,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.STARTMONTH,'yyyy-mm')||'-01',TO_CHAR(AMM.ENDMONTH,'yyyy-mm') ENDMONTH,AMM.MONEY,"+  //
        "ZD.STATIONTYPE,"+
        "DB.DFZFLX,"+
        "ZD.EDHDL,ZD.ID,DB.DBID,DB.DBNAME,ZD.PROPERTY,ZD.SCB,ZD.SHI,ZD.XIAN,ZD.XIAOQU " +
        " FROM (SELECT DISTINCT YY.PREPAYMENT_AMMETERID,YY.STARTMONTH,YY.ENDMONTH,YY.MONEY FROM YUFUFEIVIEW YY, " +
        "(SELECT MAX(YY.ENDMONTH) AS DATETIME, YY.PREPAYMENT_AMMETERID FROM YUFUFEIVIEW YY  WHERE 1=1 and FINANCEAUDIT ='2'  GROUP BY YY.PREPAYMENT_AMMETERID) B " +   //
        "WHERE YY.PREPAYMENT_AMMETERID = B.PREPAYMENT_AMMETERID AND YY.ENDMONTH = B.DATETIME  and FINANCEAUDIT ='2') AMM, DIANBIAO DB,  ZHANDIAN ZD"+    //
         " WHERE DB.DBID = AMM.PREPAYMENT_AMMETERID(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' AND DB.DFZFLX='dfzflx02' AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1' AND ZD.STATIONTYPE<>'StationType41' "+wherestr+" ";
     //   " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))"; //ORDER BY  JZNAME
		try {
		db.connectDb();
		
		System.out.println("删除上次暂估内容："+sqldel.toString());
		db.update(sqldel.toString());
		
		System.out.println("暂估月结预付费查询："+sql.toString());
		rs = db.queryAll(sql.toString());
		
		
		String thistimepd=zgsj;//用于保存最初的暂估结束时间
		
		while(rs.next()){
			     zgsj=thistimepd;//初始暂估结束时间为页面上传过来的暂估结束时间
			 	jzname =rs.getString(1)!=null?rs.getString(1):""; //站点名称	
			    danjia=rs.getString(2)!=null?rs.getString(2):"";//电表电费单价
			    cssytime=rs.getString(3)!=null?rs.getString(3):"";//电表初始使用时间
			    lastdatetime=rs.getString(4)!=null?rs.getString(4):"";//上次抄表时间    起始月份
	 		    thisdatetime=rs.getString(5)!=null?rs.getString(5):"";//本次抄表时间         结束月份   （最大值）
	 		    actualpay=rs.getString(6)!=null?rs.getString(6):"";//实际电费金额
	 		    stationtype=rs.getString(7)!=null?rs.getString(7):"";//站点类型
	 		    dfzflxx=rs.getString(8)!=null?rs.getString(8):"";//电费支付类型
	 		   //address =rs.getString(9)!=null?rs.getString(9):"";//地区
	 		   edhdl =rs.getString(9)!=null?rs.getString(9):"";//额定耗电量
	 		  zdid=rs.getString(10)!=null?rs.getString(10):"";//站点id
	 		 dbid=rs.getString(11)!=null?rs.getString(11):"";//电表id
	 		dbname =rs.getString(12)!=null?rs.getString(12):"";//电表名称
	 		property=rs.getString(13)!=null?rs.getString(13):"";//站点属性
	 		qsdbdl = rs.getString(14)!=null?rs.getString(14):"";//生产标
	 		shi1 = rs.getString(15)!=null?rs.getString(15):"";//市 编码
	 		   xian =rs.getString(16)!=null?rs.getString(16):"";//区县编码
	 		  xiaoqu =rs.getString(17)!=null?rs.getString(17):"";//乡镇编码
	 		    
	 		    if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
	            if(dbname==null||dbname.equals("null")||dbname.equals(" ")){dbname="";}
	            if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
			    
			    //计算单价（元/天） 
			    if(!"0".equals(qsdbdl)&&!"".equals(qsdbdl)&&null!=qsdbdl){
				        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//单价（元/天）：生产标*暂估单价
			    	}else{
				  danjia1 =Format.str_d(danjia)*Format.str_d(edhdl) ;// 单价（元/天）：暂估单价*额定耗电量
				  day1=0;
				  daye="0";
			     }  
			    //站点类型为小灵通基站、小灵通基站下电暂估结束时间固定为“2014-05-17” 不进行暂估（暂估天数为负数的不进行暂估）
				   if("StationType01".equals(stationtype)||"StationType27".equals(stationtype)){
					   zgsj ="2014-05-17";
				   }
			  if("".equals(danjia1)||" ".equals(danjia1)){
					   danjia1=0.0;
			  }   
			  //计算的时间 暂估天数
			  //如果符合这些判断就进入计算暂估天数
			  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
				    //暂估开始时间
			   	Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
				    //暂估结束时间
				   Calendar thisTime = Calendar.getInstance();
				   //System.out.println("22222222:"+thisdatetime);
				   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));//生成日期格式
				    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
				    //暂估天数
				    days = Math.ceil(temp/1000/60/60/24.0);
				    days=days+1.0;  //暂估天数
				    DecimalFormat day2=new DecimalFormat("0");
				    daye=day2.format(days);  
				     bzw="最近一次抄表时间";//标志是用的那个时间进行的暂估
			   }else{
					  thisdatetime=cssytime;//如果没有上次抄表时间--暂估起始时间=电表初始使用时间
					   bzw="电表初始使用时间";//标志是用的那个时间进行的暂估
					  //如果符合判断条件  进行计算 暂估天数
					  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
						 	Calendar lastTime = Calendar.getInstance();
						    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
							    //暂估结束时间
							   Calendar thisTime = Calendar.getInstance();
							   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));
							    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
							    //暂估天数
							    days = Math.ceil(temp/1000/60/60/24.0);
							    days=days+1.0;
							    DecimalFormat day2=new DecimalFormat("0");
							    daye=day2.format(days);
					  }else{ //否则暂估天数为0
						  daye="0";
						  days=0;
						  
					  }
				  
			   }    
			//  System.out.println("222222zgsj:"+zgsj+"   daye:"+daye);
			  String ddd="0";//暂估天数
			  //如果暂估天数不等于0  进行判断
			  if(daye!="0"){
				  int dddd=Integer.parseInt(daye)-1;
				  if(dddd<0){ //如果小于0 暂估天数就为0
					dddd=0;
					days=0;
				  }
				   ddd=dddd+"";//暂估天数
				  // System.out.println("ddd:"+ddd); 
			  }
			    DecimalFormat dj=new DecimalFormat("0.0000");
			   
			    danjia=dj.format(danjia1);	 
			    //System.out.println("danjia1"+danjia1+"  danjia:"+Double.parseDouble(danjia)+"  daye"+daye+"  days:"+days+"  ddd:"+Double.parseDouble(ddd));  		    
				 //System.out.println("danjia:"+danjia+" ddd"+ddd);
				dianfeizangu=mat.format(Double.parseDouble(danjia)*Double.parseDouble(ddd));//暂估金额
				dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
				String lastadtimew="";
				//日期加一天 暂估起始时间
		     if(!thisdatetime.equals("")&&!thisdatetime.equals("20101-21")){
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date dt=sdf.parse(thisdatetime);
				Calendar rightNow = Calendar.getInstance();
	            rightNow.setTime(dt);
	            rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
	            Date dt1=rightNow.getTime();
	            lastadtimew = sdf.format(dt1);
			 }
		    // System.out.println("111;"+dianfeizangu);
		     if(!"0.00".equals(dianfeizangu)&&!"".equals(dianfeizangu)&&!"0".equals(dianfeizangu)){
		   // System.out.println("222"+dianfeizangu);
		 		sqlpl.append("insert into zangushuju(JZNAME,ADDRESS,STATIONTYPE,DFZFLX,LASTACCOUNTMONTH," +
		 				"ZANGUSTARTMONTH,ZANGUENDMONTH,TIANSHU,DANJIA,ZANGUMONEY,SAVETIME,uuid,zdid," +
		 				"ENTRYPENSONNEL,DBID,DBNAME,SHI,BZW,ZGMONTH,SJBZW,XIAN,XIAOQU,PROPERTY) values('" +
		 				jzname+"','"+address+"','"+stationtype+"','"+dfzflxx+"','"+thisdatetime+"','"+lastadtimew+"','"+
		 				zgsj+"','"+ddd+"','"+danjia+"','"+dianfeizangu+"',"+sj+",'"+uuid+"'," +
						"'"+zdid+"','admins','"+dbid+"','"+dbname+"','"+shi1+"','1','"+zgmonth+"','"+bzw+"','"+xian+"','"+xiaoqu+"','"+property+"')の");
			    
			    
		     }    
			    
		}
		
		rs1 = db.queryAll(sql1.toString());
		System.out.println("暂估查询或导出2："+sql1.toString());
		while(rs1.next()){
			    zgsj=thistimepd;//初始暂估结束时间为页面上传过来的暂估结束时间
			    jzname =rs1.getString(1)!=null?rs1.getString(1):""; //站点名称	
			    danjia=rs1.getString(2)!=null?rs1.getString(2):"";//电表电费单价
			    cssytime=rs1.getString(3)!=null?rs1.getString(3):"";//电表初始使用时间
			    lastdatetime=rs1.getString(4)!=null?rs1.getString(4):"";//上次抄表时间    起始月份
	 		    thisdatetime=rs1.getString(5)!=null?rs1.getString(5):"";//本次抄表时间         结束月份   （最大值）
	 		    actualpay=rs1.getString(6)!=null?rs1.getString(6):"";//实际电费金额
	 		    stationtype=rs1.getString(7)!=null?rs1.getString(7):"";//站点类型
	 		    dfzflxx=rs1.getString(8)!=null?rs1.getString(8):"";//电费支付类型
	 		   edhdl =rs1.getString(9)!=null?rs1.getString(9):"";//额定耗电量
		 		  zdid=rs1.getString(10)!=null?rs1.getString(10):"";//站点id
		 		 dbid=rs1.getString(11)!=null?rs1.getString(11):"";//电表id
		 		dbname =rs1.getString(12)!=null?rs1.getString(12):"";//电表名称
		 		property=rs1.getString(13)!=null?rs1.getString(13):"";//站点属性
		 		qsdbdl = rs1.getString(14)!=null?rs1.getString(14):"";//生产标
		 		shi1 = rs1.getString(15)!=null?rs1.getString(15):"";//市 编码
		 		   xian =rs1.getString(16)!=null?rs1.getString(16):"";//区县编码
		 		  xiaoqu =rs1.getString(17)!=null?rs1.getString(17):"";//乡镇编码
	 		    
	 		    if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
	            if(dbname==null||dbname.equals("null")||dbname.equals(" ")){dbname="";}
	            if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
			  //判断预付合同类型 的  结束月份	后面加上天数
			    if(null!=thisdatetime&&!"".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!"0".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
			 	  if(thisdatetime.length()<=7&&thisdatetime.length()>0&&!thisdatetime.equals("20101-21")){ 
			 		    int y,m,tian; 
			 		    y=Integer.parseInt(thisdatetime.substring(0,thisdatetime.indexOf("-"))); //截取年份
			 		    m=Integer.parseInt(thisdatetime.substring(thisdatetime.indexOf("-")+1,thisdatetime.length())); //截取月份
			 		   tian=y+(y-1)/4-(y-1)/100+(y-1)/400;//没有用  可以注释掉
			 		   //判断月份的天数
			 		    if(m==4||m==6||m==9||m==11){   
			 		    	tian=30; 
			 		    }else if(m==2){ 
			 		    	if((y%4==0&&y%100!=0)||(y%400==0)){
								tian=29; 
							}else{
								tian= 28 ;
							}			
						}else{
							tian=31; 		 	
						} 
			 		   String mm="";
			 		   if(m<=9){
							mm="0"+m;
						}else{
							mm=m+"";
						}
						  
						  thisdatetime=y+"-"+mm+"-"+tian;
			 		}}
			    //计算单价（元/天） 
			    if(!"0".equals(qsdbdl)&&!"".equals(qsdbdl)&&null!=qsdbdl){
				        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//单价（元/天）：生产标*电表单价
			    	}else{
				  danjia1 =Format.str_d(danjia)*Format.str_d(edhdl) ;// 单价（元/天）：电表单价*额定耗电量
				  day1=0;
				  daye="0";
			     }  
			    
			  if("".equals(danjia1)||" ".equals(danjia1)){
					   danjia1=0.0;
			  }  
			  //站点类型为小灵通基站、小灵通基站下电暂估结束时间固定为“2014-05-17” 不进行暂估（暂估天数为负数的不进行暂估）
			   if("StationType01".equals(stationtype)||"StationType27".equals(stationtype)){
				   zgsj ="2014-05-17";
			   }
			  //计算的时间 暂估天数
			  //如果符合这些判断就进入计算暂估天数
			  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
			
				    //暂估开始时间
			   	Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
				    //暂估结束时间
				   Calendar thisTime = Calendar.getInstance();
				   //System.out.println("22222222:"+thisdatetime);
				   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));//生成日期格式
				    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
				    //暂估天数
				    days = Math.ceil(temp/1000/60/60/24.0);
				    days=days+1.0;  //暂估天数
				    DecimalFormat day2=new DecimalFormat("0");
				    daye=day2.format(days);  
				     bzw="最近一次抄表时间";//标志是用的那个时间进行的暂估
			   }else{
					  thisdatetime=cssytime;//如果没有上次抄表时间--暂估起始时间=电表初始使用时间
					   bzw="电表初始使用时间";//标志是用的那个时间进行的暂估
					  //如果符合判断条件  进行计算 暂估天数
					  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
						 	Calendar lastTime = Calendar.getInstance();
						    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
							    //暂估结束时间
							   Calendar thisTime = Calendar.getInstance();
							   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));
							    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
							    //暂估天数
							    days = Math.ceil(temp/1000/60/60/24.0);
							    days=days+1.0;
							    DecimalFormat day2=new DecimalFormat("0");
							    daye=day2.format(days);
					  }else{ //否则暂估天数为0
						  daye="0";
						  days=0;
						  
					  }
				  
			   } 
			  //System.out.println("333333zgsj:"+zgsj+"   daye:"+daye);
			  String ddd="0";//暂估天数
			  //如果暂估天数不等于0  进行判断
			  if(daye!="0"){
				  int dddd=Integer.parseInt(daye)-1;
				  if(dddd<0){ //如果小于0 暂估天数就为0
					dddd=0;
					days=0;
				  }
				   ddd=dddd+"";//暂估天数
				  // System.out.println("ddd:"+ddd); 
			  }
			 
			    DecimalFormat dj=new DecimalFormat("0.0000");
			   
			    danjia=dj.format(danjia1);	 
				dianfeizangu=mat.format(Double.parseDouble(danjia)*Double.parseDouble(ddd));//暂估金额
				dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
				String lastadtimew="";
				//日期加一天 暂估起始时间
		     if(!thisdatetime.equals("")&&!thisdatetime.equals("20101-21")){
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date dt=sdf.parse(thisdatetime);
				Calendar rightNow = Calendar.getInstance();
	            rightNow.setTime(dt);
	            rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
	            Date dt1=rightNow.getTime();
	            lastadtimew = sdf.format(dt1);
			 }
		     if(!"0.00".equals(dianfeizangu)&&!"".equals(dianfeizangu)&&!"0".equals(dianfeizangu)){
		    	 	sqlpl.append("insert into zangushuju(JZNAME,ADDRESS,STATIONTYPE,DFZFLX,LASTACCOUNTMONTH," +
		    	 			"ZANGUSTARTMONTH,ZANGUENDMONTH,TIANSHU,DANJIA,ZANGUMONEY,SAVETIME,uuid,zdid," +
		    	 			"ENTRYPENSONNEL,DBID,DBNAME,SHI,BZW,ZGMONTH,SJBZW,XIAN,XIAOQU,PROPERTY) values('" +
			 				jzname+"','"+address+"','"+stationtype+"','"+dfzflxx+"','"+thisdatetime+"','"+lastadtimew+"','"+
			 				zgsj+"','"+ddd+"','"+danjia+"','"+dianfeizangu+"',"+sj+",'"+uuid+"'," +
							"'"+zdid+"','admins','"+dbid+"','"+dbname+"','"+shi1+"','2','"+zgmonth+"','"+bzw+"','"+xian+"','"+xiaoqu+"','"+property+"')の");
			    
		     }    
			
			}
		
		//执行批量插入
		System.out.println("暂估数据保存1");
		db.updateBatchDr(sqlpl.toString().split("の"));
		System.out.println("暂估数据保存成功");
		
		System.out.println("删除月结暂估数据重复的站点"+sqldelyj.toString());
		db.update(sqldelyj.toString());
		
		System.out.println("删除合同暂估数据重复的站点"+sqldelht.toString());
		db.update(sqldelht.toString());
		
		System.out.println("删除月结合同暂估数据重复的站点"+sqldelyh.toString());
		db.update(sqldelyh.toString());
		fhxx="1";
		
	} catch (Exception e) {
		fhxx="2";
		e.printStackTrace();
		try {
			db.rollback();
		} catch (DbException e1) {
			e1.printStackTrace();
		}
	}finally{
		//db.free(null, ps, null);
		//db.free(rs, st, conn);
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		if (rs1 != null) {
			try {
				rs1.close();
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
   		
		return fhxx;
	}
	public ArrayList<FinanceZanGuBean> zanGuChaXun(String whereStr,String loginId){
		ArrayList<FinanceZanGuBean> list = new ArrayList<FinanceZanGuBean>();
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		sql.append("SELECT Z.JZNAME,Z.ZDID,Z.DBID,Z.DBNAME," +
				"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN)," +
				"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = Z.PROPERTY AND T.TYPE = 'ZDSX') AS PROPERTY," +
				"(SELECT T.NAME FROM INDEXS T WHERE T.CODE = Z.STATIONTYPE AND T.TYPE = 'stationtype') STATIONTYPE," +
				"(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = Z.DFZFLX AND T.TYPE = 'dfzflx') DFZFLX," +
				"Z.SJBZW,Z.LASTACCOUNTMONTH,Z.ZANGUSTARTMONTH,Z.ZANGUENDMONTH,Z.TIANSHU,Z.DANJIA," +
				"Z.ZANGUMONEY FROM ZANGUSHUJU Z WHERE 1=1 "+whereStr+" " +
				"AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			System.out.println("暂估信息查询："+sql.toString());
			rs=db.queryAll(sql.toString());
			while(rs.next()){
				FinanceZanGuBean bean = new FinanceZanGuBean();
				bean.setJzname(rs.getString(1));
				bean.setZdid(rs.getString(2));
				bean.setDbid(rs.getString(3));
				bean.setDbname(rs.getString(4));
				bean.setXian(rs.getString(5));
				bean.setProperty(rs.getString(6));
				bean.setStationtype(rs.getString(7));
				bean.setDfzflx(rs.getString(8));
				bean.setBzw(rs.getString(9));
				bean.setLastaccountmonth(rs.getString(10));
				bean.setZangustartmonth(rs.getString(11));
				bean.setZanguendmonth(rs.getString(12));
				bean.setTianshu(rs.getString(13));
				bean.setDianfei(rs.getString(14));
				bean.setZangumoney(rs.getString(15));
				list.add(bean);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * @param 
	 * @return 
	 * @see 计算暂估金额总和
	 * @author 
	 */
	public FinanceZanGuBean total(List<FinanceZanGuBean> list) {
		FinanceZanGuBean total = new FinanceZanGuBean();//生成一个total对象
 		double totalmoney = 0;//暂估金额合计	
		int i;
		for(i = 0; i<list.size();i++){
			//遍历求和
     		totalmoney = totalmoney + Double.parseDouble(list.get(i).getZangumoney());
		}
		//放入total
		total.setZgmoneyhj(Format.d2(totalmoney));
		return total;
	}
	
	public ArrayList<FinanceZanGuBean> zanGuFenTan(String whereStr,String loginId){
		ArrayList<FinanceZanGuBean> list = new ArrayList<FinanceZanGuBean>();
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		DataBase db = new DataBase();
		sql.append("SELECT (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = Z.SHI) AS SHI," +
				"SUM(Z.ZANGUMONEY * S.DBILI / 100 * S.XJBILI / 100) FTJE," +
				"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB," +
				"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM," +
				"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX," +
				"S.SHUOSHUZHUANYE, (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = Z.XIAN) AS XIAN" +
				" FROM  ZANGUSHUJU Z, SBGL S WHERE  Z.DBID = S.DIANBIAOID   "+whereStr+" " +
				"AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"GROUP BY S.SHUOSHUZHUANYE, S.QCBCODE, S.KJKMCODE, S.ZYMXCODE,Z.SHI,Z.XIAN");
		
		
		try {
			db.connectDb();
			System.out.println("暂估分摊信息查询："+sql.toString());
			rs=db.queryAll(sql.toString());
			while(rs.next()){
				FinanceZanGuBean dfdbean = new FinanceZanGuBean();
				dfdbean.setShi(rs.getString(1));
				dfdbean.setNetworkdf(rs.getString(2));//分摊金额
				dfdbean.setQcb(rs.getString(3));
				dfdbean.setKjkm(rs.getString(4));
				dfdbean.setZymx(rs.getString(5));
				dfdbean.setSszy(rs.getString(6));
				dfdbean.setXian(rs.getString(7));
				list.add(dfdbean);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
