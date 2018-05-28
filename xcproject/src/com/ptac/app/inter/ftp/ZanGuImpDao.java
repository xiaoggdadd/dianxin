package com.ptac.app.inter.ftp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.function.CityQueryBean;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.inter.bean.KdgcElectrictypeBean;
import com.ptac.app.inter.bean.KdgcZhandianBean;
import com.ptac.app.inter.service.KdgcInterfaceDaoImp;

public class ZanGuImpDao {
	public void updateCityAverageUnitPrice(){
		DataBase db = new DataBase();
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InetAddress addr;
		String sql ="SELECT ZD.SHI SHI, ROUND(SUM(EF.ACTUALPAY) / SUM(AM.BLHDL), 4) AVERAGEUNITPRICE FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK"
			+" AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND EF.MANUALAUDITSTATUS = '2' AND TO_CHAR(EF.ACCOUNTMONTH,'YYYY-MM') >= DECODE(TO_CHAR(SYSDATE, 'MM'),'01',TO_CHAR(ADD_MONTHS(SYSDATE,-11-TO_CHAR(SYSDATE, 'MM')),"
			+"'YYYY-MM'),TO_CHAR(TRUNC(ADD_MONTHS(SYSDATE, -0), 'YEAR'), 'YYYY-MM')) AND TO_CHAR(EF.ACCOUNTMONTH,'YYYY-MM') <= DECODE(TO_CHAR(SYSDATE, 'MM'), '01', TO_CHAR(ADD_MONTHS(SYSDATE,-TO_CHAR(SYSDATE, 'MM')),'YYYY-MM'),"
			+"TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'YYYY-MM')) GROUP BY ZD.SHI";
		try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();
			String pcname = addr.getHostName().toString();
			if ("134.33.107.153".equals(ip)) {//134.33.107.153
				conn = db.getConnection();
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				System.out.println("各地市平均单价:"+sql);
				String sql1 = "UPDATE CITYAVERAGEUNITPRICE SET AVERAGEUNITPRICE=? WHERE CITY=?";
				ps = conn.prepareStatement(sql1);
				while(rs.next()){
					String shi = rs.getString("SHI");
					String averageunitprice = rs.getString("AVERAGEUNITPRICE")==null?"0":rs.getString("AVERAGEUNITPRICE");
					ps.setString(1, averageunitprice);
					ps.setString(2, shi);
					System.out.println("地市平均单价添加:"+shi+":"+averageunitprice+":"+sql1);
					ps.executeUpdate();
				}
				System.out.println(pcname);
				System.out.println(ip);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(null, ps, null);
			db.free(rs, st, conn);
		}
	}
//自动暂估数据保存  每月3号12点执行 内网地址执行
	public static void zanGu(){
		String ip = "";
		String pcname = "";
        InetAddress addr;
        try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();//获取ip
			pcname = addr.getHostName().toString();
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//如果ip如下就执行  因为项目部署到三个服务器地址   只需要一个地址上的项目执行 就可以
		if ("134.33.107.153".equals(ip)) {   //  134.33.107.153
		DataBase db = new DataBase();
        ResultSet rs = null;
        ResultSet rs1 = null;
        String sj="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";//录入时间
        String sql = "",sql1="";
        long uuid1=System.currentTimeMillis();
        String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
	    String uuid = uuid2+Long.toString(uuid1).substring(3);//唯一值uuid 自动生成
        //获取系统当前时间的上个月份
	    Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String datetime = dateFormat.format(c.getTime());
      //日期默认为上个月月底
        Date tt1 = new Date(); //得到当前时间
   		tt1 = new Date(tt1.getYear(), tt1.getMonth(), 0); //得到上个月最后一天
   		String s=datetime+"-"+tt1.getDate();//得到上个月最后一天
   		String bijiao = "";//暂估月份
   		if(s!=null){
   			bijiao=s.substring(0,7);
   		}
   		String jzname="",address="",lastdatetime="",thisdatetime="",tianshu="",danjia="",daye="",stationtype="",dfzflxx="",
	       		dianfeizangu ="",actualpay="",cssytime="",dbid="",edhdl="",zdid="",dbname="",property="",qsdbdl="",shi = "";
   		String bzw1="",bzw = "0";
   		double danjia1=0.0;
   		double day1=0.0,days=0.0;//day1--周期
   		DecimalFormat mat=new DecimalFormat("0.00");
   		StringBuffer sqldelyj=new StringBuffer();//删除月结暂估数据重复的站点
   		StringBuffer sqldelht=new StringBuffer();//删除合同暂估数据重复的站点
   		StringBuffer sqldelyh=new StringBuffer();//删除合同月结同时重复的站点
   		
		sqldelyj.append("DELETE FROM ZANGU Z WHERE Z.ZDID IN " +
				"(SELECT Z.ZDID FROM ZANGU Z WHERE Z.BZW = '1' AND Z.ZGMONTH = '"+bijiao+"' " +
				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1)  " +
				"AND ROWID NOT IN (SELECT  MIN(Z.ROWID)FROM (SELECT Z.ZDID, MAX(Z.ZANGUSTARTMONTH) AS ZDMONTH " +
				"FROM ZANGU Z WHERE Z.BZW = '1' AND Z.ZGMONTH = '"+bijiao+"'  GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) B," +
				"ZANGU Z WHERE Z.ZDID = B.ZDID AND Z.BZW = '1' AND Z.ZGMONTH = '"+bijiao+"' AND Z.ZANGUSTARTMONTH = B.ZDMONTH  " +
				"GROUP BY Z.ZDID) AND Z.BZW = '1' AND  Z.ZGMONTH = '"+bijiao+"'");
		
		sqldelht.append("DELETE FROM ZANGU Z WHERE Z.ZDID IN " +
				"(SELECT Z.ZDID FROM ZANGU Z WHERE Z.BZW = '2' AND Z.ZGMONTH = '"+bijiao+"' " +
				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1)  " +
				"AND ROWID NOT IN (SELECT  MIN(Z.ROWID)FROM (SELECT Z.ZDID, MAX(Z.ZANGUSTARTMONTH) AS ZDMONTH " +
				"FROM ZANGU Z WHERE Z.BZW = '2' AND Z.ZGMONTH = '"+bijiao+"'  GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) B," +
				"ZANGU Z WHERE Z.ZDID = B.ZDID AND Z.BZW = '2' AND Z.ZGMONTH = '"+bijiao+"' AND Z.ZANGUSTARTMONTH = B.ZDMONTH  " +
				"GROUP BY Z.ZDID) AND Z.BZW = '2' AND  Z.ZGMONTH = '"+bijiao+"'");
		
		sqldelyh.append("DELETE FROM ZANGU Z WHERE Z.ZDID IN " +
				"(SELECT Z.ZDID FROM ZANGU Z WHERE Z.ZGMONTH = '"+bijiao+"'  " +
				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) AND Z.BZW='2' AND Z.ZGMONTH ='"+bijiao+"' ");
   		
   		for(int i=13701;i<=13719;i++){
		
   			System.out.println("地市编码"+i+"bijiao:"+bijiao);
   			StringBuffer sqlpl=new StringBuffer();
   		//查询电费表里的暂估数据  月结和预付费
   	
   	        	sql="SELECT DISTINCT ZD.JZNAME,DB.ZGDJ,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AMM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AMM.ACTUALPAY, "+
   	        	"(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = ZD.STATIONTYPE  AND T.TYPE = 'stationtype') STATIONTYPE,"+
   	        	" (SELECT T.NAME  FROM INDEXS T  WHERE T.CODE = DB.DFZFLX  AND T.TYPE = 'dfzflx') DFZFLX," +
   	        	" (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) XIAN, "+
   	        	"  ZD.EDHDL,ZD.ID,DB.DBID, DB.DBNAME,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND T.TYPE = 'ZDSX') AS PROPERTY,ZD.SCB,ZD.SHI " +
   	        	"  FROM (SELECT DISTINCT DD.AMMETERID_FK, DD.LASTDATETIME,DD.THISDATETIME, DF.ACTUALPAY, DD.MANUALAUDITSTATUS, B.DATETIME"+
   	        	" FROM DIANDUVIEW DD,DIANFEIVIEW DF, (SELECT MAX(AMM.THISDATETIME)  AS DATETIME, AMM.AMMETERID_FK   FROM DIANDUVIEW AMM,DIANFEIVIEW DF"+
   	        	" WHERE 1=1 and DF.MANUALAUDITSTATUS ='2'  AND AMM.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK   GROUP BY AMM.AMMETERID_FK) B"+
   	        	" WHERE DD.AMMETERID_FK = B.AMMETERID_FK  AND DD.THISDATETIME = B.DATETIME   AND DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK  and DF.MANUALAUDITSTATUS ='2') AMM, DIANBIAO DB,  ZHANDIAN ZD"+
   	        	" WHERE DB.DBID = AMM.AMMETERID_FK(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' " +
   	        	"AND (DB.DFZFLX='dfzflx01' OR DB.DFZFLX='dfzflx03') AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1'  AND (zd.property<>'zdsx04' AND zd.jzname NOT LIKE '%回收%') AND ZD.STATIONTYPE<>'StationType41' and zd.shi='"+i+"'  " ;//and zd.shi='13701' and zd.xian = '1370130'
   	   		
  
   	       //查询预付费表里的    合同
		sql1="SELECT DISTINCT ZD.JZNAME,DB.ZGDJ,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.STARTMONTH,'yyyy-mm')||'-01',TO_CHAR(AMM.ENDMONTH,'yyyy-mm') ENDMONTH,AMM.MONEY,"+
        "(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = ZD.STATIONTYPE  AND T.TYPE = 'stationtype') STATIONTYPE,"+
        " (SELECT T.NAME  FROM INDEXS T  WHERE T.CODE = DB.DFZFLX  AND T.TYPE = 'dfzflx') DFZFLX,"+
        " (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) XIAN, "+
        "  ZD.EDHDL,ZD.ID,DB.DBID,DB.DBNAME,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND T.TYPE = 'ZDSX') AS PROPERTY ,ZD.SCB,ZD.SHI " +
        " FROM (SELECT DISTINCT YY.PREPAYMENT_AMMETERID,YY.STARTMONTH,YY.ENDMONTH,YY.MONEY FROM YUFUFEIVIEW YY, " +
        "(SELECT MAX(YY.ENDMONTH) AS DATETIME, YY.PREPAYMENT_AMMETERID FROM YUFUFEIVIEW YY  WHERE 1=1 and YY.FINANCEAUDIT ='2'  GROUP BY YY.PREPAYMENT_AMMETERID) B " +
        "WHERE YY.PREPAYMENT_AMMETERID = B.PREPAYMENT_AMMETERID AND YY.ENDMONTH = B.DATETIME  and YY.FINANCEAUDIT ='2' ) AMM, DIANBIAO DB,  ZHANDIAN ZD"+
         " WHERE DB.DBID = AMM.PREPAYMENT_AMMETERID(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' AND DB.DFZFLX='dfzflx02' AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1' AND ZD.STATIONTYPE<>'StationType41' and zd.shi='"+i+"'  " ;// and zd.shi='13701' and zd.xian = '1370130'
		
		try {
		 	System.out.println("暂估月结预付费查询："+sql.toString());
		 	db.connectDb();	
		 	rs = db.queryAll(sql.toString());
		 	String zgsj=datetime+"-"+tt1.getDate();//用于保存最初的暂估结束时间
		 	while(rs.next()){
		 		s=datetime+"-"+tt1.getDate();//初始暂估结束时间为页面上传过来的暂估结束时间
			 	jzname =rs.getString(1)!=null?rs.getString(1):""; //站点名称		
			    danjia=rs.getString(2)!=null?rs.getString(2):"";//电表电费单价
			    cssytime=rs.getString(3)!=null?rs.getString(3):"";//电表初始使用时间
			    lastdatetime=rs.getString(4)!=null?rs.getString(4):"";//上次抄表时间    起始月份
	 		    thisdatetime=rs.getString(5)!=null?rs.getString(5):"";//本次抄表时间         结束月份   （最大值）
	 		    actualpay=rs.getString(6)!=null?rs.getString(6):"";//实际电费金额
	 		    stationtype=rs.getString(7)!=null?rs.getString(7):"";//站点类型
	 		    dfzflxx=rs.getString(8)!=null?rs.getString(8):"";//电费支付类型
	 		    address =rs.getString(9)!=null?rs.getString(9):"";//地区
	 		    edhdl=rs.getString(10)!=null?rs.getString(10):"";//额定耗电量
	 		    zdid=rs.getString(11)!=null?rs.getString(11):"";//站点id
	 		    dbid=rs.getString(12)!=null?rs.getString(12):"";//电表id
	 		    dbname=rs.getString(13)!=null?rs.getString(13):"";
	 		    property = rs.getString(14)!=null?rs.getString(14):"";//站点属性
	 		    qsdbdl = rs.getString(15)!=null?rs.getString(15):"";//生产标
	 		    shi=rs.getString(16)!=null?rs.getString(16):"";//地市编码
	 		    
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
			    
			  if("".equals(danjia1)||" ".equals(danjia1)){
					   danjia1=0.0;
			  }  
			  //站点类型为小灵通基站、小灵通基站下电暂估结束时间固定为“2014-05-17” 不进行暂估（暂估天数为负数的不进行暂估）
			   if("小灵通基站".equals(stationtype)||"小灵通基站下电".equals(stationtype)){
				   s ="2014-05-17";
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
				   thisTime.setTime(DateFormat.getDateInstance().parse(s));//生成日期格式
				    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
				    //暂估天数
				    days = Math.ceil(temp/1000/60/60/24.0);
				    days=days+1.0;  //暂估天数
				    DecimalFormat day2=new DecimalFormat("0");
				    daye=day2.format(days);  
				     bzw="1";//标志是用的那个时间进行的暂估
			   }else{
					  thisdatetime=cssytime;//如果没有上次抄表时间--暂估起始时间=电表初始使用时间
					   bzw="2";//标志是用的那个时间进行的暂估
						  //如果符合判断条件  进行计算 暂估天数
					  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
						 	Calendar lastTime = Calendar.getInstance();
						    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
						  //暂估结束时间
							   Calendar thisTime = Calendar.getInstance();
							   thisTime.setTime(DateFormat.getDateInstance().parse(s));
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
		//	  System.out.println("222222zgsj:"+s+"   daye:"+daye);
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
		 		sqlpl.append("insert into zangu(JZNAME,ADDRESS,STATIONTYPE,DFZFLX,LASTACCOUNTMONTH,ZANGUSTARTMONTH,ZANGUENDMONTH,TIANSHU,DANJIA,ZANGUMONEY,SAVETIME,uuid,zdid,ENTRYPENSONNEL,DBID,DBNAME,SHI,ZGMONTH,BZW) values('" +
		 				jzname+"','"+address+"','"+stationtype+"','"+dfzflxx+"','"+thisdatetime+"','"+lastadtimew+"','"+
						s+"','"+ddd+"','"+danjia+"','"+dianfeizangu+"',"+sj+",'"+uuid+"'," +
						"'"+zdid+"','admins','"+dbid+"','"+dbname+"','"+shi+"','"+bijiao+"','1')の");
			    
			    
		     }    
			    
		}
		
		rs1 = db.queryAll(sql1.toString());
		System.out.println("暂估查询或导出2："+sql1.toString());
		while(rs1.next()){
			    s=datetime+"-"+tt1.getDate();//初始暂估结束时间为页面上传过来的暂估结束时间
			    jzname =rs1.getString(1)!=null?rs1.getString(1):""; //站点名称
			    danjia=rs1.getString(2)!=null?rs1.getString(2):"";//电表电费单价
			    cssytime=rs1.getString(3)!=null?rs1.getString(3):"";//电表初始使用时间
			    lastdatetime=rs1.getString(4)!=null?rs1.getString(4):"";//上次抄表时间    起始月份
	 		    thisdatetime=rs1.getString(5)!=null?rs1.getString(5):"";//本次抄表时间         结束月份   （最大值）
	 		    actualpay=rs1.getString(6)!=null?rs1.getString(6):"";//实际电费金额
	 		    stationtype=rs1.getString(7)!=null?rs1.getString(7):"";//站点类型
	 		    dfzflxx=rs1.getString(8)!=null?rs1.getString(8):"";//电费支付类型
	 		    address =rs1.getString(9)!=null?rs1.getString(9):"";//地区
	 		    edhdl=rs1.getString(10)!=null?rs1.getString(10):"";//额定耗电量
	 		    zdid=rs1.getString(11)!=null?rs1.getString(11):"";//站点id
	 		    dbid=rs1.getString(12)!=null?rs1.getString(12):"";//电表id
	 		    dbname=rs1.getString(13)!=null?rs1.getString(13):"";
	 		    property = rs1.getString(14)!=null?rs1.getString(14):"";//站点属性
	 		    qsdbdl = rs1.getString(15)!=null?rs1.getString(15):"";//生产标
	 		    shi=rs1.getString(16)!=null?rs1.getString(16):"";//地市编码
	 		    
	 		    if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
	            if(dbname==null||dbname.equals("null")||dbname.equals(" ")){dbname="";}
	            if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
			  ////判断预付合同类型 的  结束月份	后面加上天数
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
				        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//单价（元/天）：生产标*暂估单价
			    	}else{
				  danjia1 =Format.str_d(danjia)*Format.str_d(edhdl) ;// 单价（元/天）：暂估单价*额定耗电量
				  day1=0;
				  daye="0";
			     }  
			    
			  if("".equals(danjia1)||" ".equals(danjia1)){
					   danjia1=0.0;
			  } 
			  //站点类型为小灵通基站、小灵通基站下电暂估结束时间固定为“2014-05-17” 不进行暂估（暂估天数为负数的不进行暂估）
			  if("小灵通基站".equals(stationtype)||"小灵通基站下电".equals(stationtype)){
				   s ="2014-05-17";
			   }
			//计算的时间 暂估天数
			  //如果符合这些判断就进入计算暂估天数
			  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
			
				  //暂估开始时间
			   	Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
			  //暂估结束时间
				   Calendar thisTime = Calendar.getInstance();
				   thisTime.setTime(DateFormat.getDateInstance().parse(s));//生成日期格式
				    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
				  //暂估天数
				    days = Math.ceil(temp/1000/60/60/24.0);
				    days=days+1.0;  //暂估天数
				    DecimalFormat day2=new DecimalFormat("0");
				    daye=day2.format(days);  
				     bzw="1";//标志是用的那个时间进行的暂估
			   }else{
					  thisdatetime=cssytime;//如果没有上次抄表时间--暂估起始时间=电表初始使用时间
					   bzw="2";//标志是用的那个时间进行的暂估
						  //如果符合判断条件  进行计算 暂估天数
					  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
						 	Calendar lastTime = Calendar.getInstance();
						    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
						    //暂估结束时间
							   Calendar thisTime = Calendar.getInstance();
							   thisTime.setTime(DateFormat.getDateInstance().parse(s));
							    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
							    //暂估天数
							    days = Math.ceil(temp/1000/60/60/24.0);
							    days=days+1.0;
							    DecimalFormat day2=new DecimalFormat("0");
							    daye=day2.format(days);
					  }else{  //否则暂估天数为0
						  daye="0";
						  days=0;
						  
					  }
				  
			   }   
			 // System.out.println("3333zgsj:"+s+"   daye:"+daye);
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
		    	 	sqlpl.append("insert into zangu(JZNAME,ADDRESS,STATIONTYPE,DFZFLX,LASTACCOUNTMONTH,ZANGUSTARTMONTH,ZANGUENDMONTH,TIANSHU,DANJIA,ZANGUMONEY,SAVETIME,uuid,zdid,ENTRYPENSONNEL,DBID,DBNAME,SHI,ZGMONTH,BZW) values('" +
			 				jzname+"','"+address+"','"+stationtype+"','"+dfzflxx+"','"+thisdatetime+"','"+lastadtimew+"','"+
							s+"','"+ddd+"','"+danjia+"','"+dianfeizangu+"',"+sj+",'"+uuid+"'," +
							"'"+zdid+"','admins','"+dbid+"','"+dbname+"','"+shi+"','"+bijiao+"','2')の");
			    
		     }    
			
			}
		
		//执行批量插入
		System.out.println("暂估数据保存1");
		db.updateBatchDr(sqlpl.toString().split("の"));
		System.out.println("暂估数据保存成功");
		if(i==13719){//13719
			System.out.println("删除月结暂估数据重复的站点"+sqldelyj.toString());
			db.update(sqldelyj.toString());
			
			System.out.println("删除合同暂估数据重复的站点"+sqldelht.toString());
			db.update(sqldelht.toString());
			
			System.out.println("删除月结合同暂估数据重复的站点"+sqldelyh.toString());
			db.update(sqldelyh.toString());
		}
		
		//}
	} catch (Exception e) {
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
   		}
		}
}
	
	

	public static void main(String[] args) {
		
//	//	NFDFlightDataTimerTask nf=new NFDFlightDataTimerTask();
//		//Date date = new Date();
//		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.add(Calendar.DATE, -1);
//		String enterTime = dateFormat.format(cal.getTime());
//		System.out.println("时间："+enterTime);
//		System.out.println("现在时间："+date+" 是否月末："+nf.isym(date));
		//new FileWriterUtil().KdgcInterface(enterTime);//测试点
		InetAddress addr;
		//zanGu();
		try {
			
			System.out.println(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}
