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
	//�ݹ����ݴ�������
	public synchronized String zanGuShuJu(String shi,String zgsj,String zgmonth,String wherestr,String loginId){
		DataBase db = new DataBase();
		String fhxx = "0";//������Ϣ 0���ɹ�  1 �ɹ�
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
   		double count1=0.0;//�ϼ�
   		double danjia1=0.0;
   		double day1=0.0,days=0.0;//day1--����
   		DecimalFormat mat=new DecimalFormat("0.00");
   		StringBuffer sqlpl=new StringBuffer();
   		StringBuffer sqldel=new StringBuffer();//ɾ���ϴβ�ѯ���ݹ�����
   		StringBuffer sqldelyj=new StringBuffer();//ɾ���½��ݹ������ظ���վ��
   		StringBuffer sqldelht=new StringBuffer();//ɾ����ͬ�ݹ������ظ���վ��
   		StringBuffer sqldelyh=new StringBuffer();//ɾ����ͬ�½�ͬʱ�ظ���վ��
   		//ɾ���ϴβ�ѯ���ݹ����ݵ�SQL���
   		sqldel.append("delete zangushuju z where z.zgmonth='"+zgmonth+"' and z.shi='"+shi+"'");
   		
   		//ɾ���½��ݹ������ظ���վ��
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
   		
   	//ɾ��ht�ݹ������ظ���վ��
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
   		
   		//ɾ����ͬ�½�ͬʱ�ظ���վ��
   		sqldelyh.append("DELETE FROM ZANGUSHUJU Z WHERE Z.ZDID IN " +
   				"(SELECT Z.ZDID FROM ZANGUSHUJU Z WHERE Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"' " +
   				"GROUP BY Z.ZDID HAVING COUNT(Z.ZDID) > 1) AND Z.BZW='2' " +
   				"AND Z.SHI='"+shi+"' AND Z.ZGMONTH = '"+zgmonth+"'");
   		
   		
   		
		//��ѯ��ѱ�����ݹ�����  �½��Ԥ����
  
   		  sql="SELECT DISTINCT ZD.JZNAME,DB.ZGDJ,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AMM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AMM.ACTUALPAY, "+   //
  		"ZD.STATIONTYPE,"+
  		"DB.DFZFLX," +
  		"ZD.EDHDL,ZD.ID,DB.DBID, DB.DBNAME,ZD.PROPERTY,ZD.SCB,ZD.SHI,ZD.XIAN,ZD.XIAOQU " +
  		"  FROM (SELECT DISTINCT DD.AMMETERID_FK, DD.LASTDATETIME,DD.THISDATETIME, DF.ACTUALPAY, DD.MANUALAUDITSTATUS, B.DATETIME"+
  		" FROM DIANDUVIEW DD,DIANFEIVIEW DF, (SELECT MAX(AMM.THISDATETIME)  AS DATETIME, AMM.AMMETERID_FK   FROM DIANDUVIEW AMM,DIANFEIVIEW DF"+
  		" WHERE 1=1 and DF.MANUALAUDITSTATUS ='2'  AND AMM.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK   GROUP BY AMM.AMMETERID_FK) B"+   //
  		" WHERE DD.AMMETERID_FK = B.AMMETERID_FK  AND DD.THISDATETIME = B.DATETIME   AND DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK  and DF.MANUALAUDITSTATUS ='2') AMM, DIANBIAO DB,  ZHANDIAN ZD"+     //
  		" WHERE DB.DBID = AMM.AMMETERID_FK(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' " +
  		"AND (DB.DFZFLX='dfzflx01' OR DB.DFZFLX='dfzflx03') AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1'  AND (zd.property<>'zdsx04' AND zd.jzname NOT LIKE '%����%') AND ZD.STATIONTYPE<>'StationType41'  "+wherestr+" ";
  		//" AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";//ORDER BY  JZNAME

		//��ѯԤ���ѱ����    ��ͬ
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
		
		System.out.println("ɾ���ϴ��ݹ����ݣ�"+sqldel.toString());
		db.update(sqldel.toString());
		
		System.out.println("�ݹ��½�Ԥ���Ѳ�ѯ��"+sql.toString());
		rs = db.queryAll(sql.toString());
		
		
		String thistimepd=zgsj;//���ڱ���������ݹ�����ʱ��
		
		while(rs.next()){
			     zgsj=thistimepd;//��ʼ�ݹ�����ʱ��Ϊҳ���ϴ��������ݹ�����ʱ��
			 	jzname =rs.getString(1)!=null?rs.getString(1):""; //վ������	
			    danjia=rs.getString(2)!=null?rs.getString(2):"";//����ѵ���
			    cssytime=rs.getString(3)!=null?rs.getString(3):"";//����ʼʹ��ʱ��
			    lastdatetime=rs.getString(4)!=null?rs.getString(4):"";//�ϴγ���ʱ��    ��ʼ�·�
	 		    thisdatetime=rs.getString(5)!=null?rs.getString(5):"";//���γ���ʱ��         �����·�   �����ֵ��
	 		    actualpay=rs.getString(6)!=null?rs.getString(6):"";//ʵ�ʵ�ѽ��
	 		    stationtype=rs.getString(7)!=null?rs.getString(7):"";//վ������
	 		    dfzflxx=rs.getString(8)!=null?rs.getString(8):"";//���֧������
	 		   //address =rs.getString(9)!=null?rs.getString(9):"";//����
	 		   edhdl =rs.getString(9)!=null?rs.getString(9):"";//��ĵ���
	 		  zdid=rs.getString(10)!=null?rs.getString(10):"";//վ��id
	 		 dbid=rs.getString(11)!=null?rs.getString(11):"";//���id
	 		dbname =rs.getString(12)!=null?rs.getString(12):"";//�������
	 		property=rs.getString(13)!=null?rs.getString(13):"";//վ������
	 		qsdbdl = rs.getString(14)!=null?rs.getString(14):"";//������
	 		shi1 = rs.getString(15)!=null?rs.getString(15):"";//�� ����
	 		   xian =rs.getString(16)!=null?rs.getString(16):"";//���ر���
	 		  xiaoqu =rs.getString(17)!=null?rs.getString(17):"";//�������
	 		    
	 		    if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
	            if(dbname==null||dbname.equals("null")||dbname.equals(" ")){dbname="";}
	            if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
			    
			    //���㵥�ۣ�Ԫ/�죩 
			    if(!"0".equals(qsdbdl)&&!"".equals(qsdbdl)&&null!=qsdbdl){
				        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//���ۣ�Ԫ/�죩��������*�ݹ�����
			    	}else{
				  danjia1 =Format.str_d(danjia)*Format.str_d(edhdl) ;// ���ۣ�Ԫ/�죩���ݹ�����*��ĵ���
				  day1=0;
				  daye="0";
			     }  
			    //վ������ΪС��ͨ��վ��С��ͨ��վ�µ��ݹ�����ʱ��̶�Ϊ��2014-05-17�� �������ݹ����ݹ�����Ϊ�����Ĳ������ݹ���
				   if("StationType01".equals(stationtype)||"StationType27".equals(stationtype)){
					   zgsj ="2014-05-17";
				   }
			  if("".equals(danjia1)||" ".equals(danjia1)){
					   danjia1=0.0;
			  }   
			  //�����ʱ�� �ݹ�����
			  //���������Щ�жϾͽ�������ݹ�����
			  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
				    //�ݹ���ʼʱ��
			   	Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
				    //�ݹ�����ʱ��
				   Calendar thisTime = Calendar.getInstance();
				   //System.out.println("22222222:"+thisdatetime);
				   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));//�������ڸ�ʽ
				    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
				    //�ݹ�����
				    days = Math.ceil(temp/1000/60/60/24.0);
				    days=days+1.0;  //�ݹ�����
				    DecimalFormat day2=new DecimalFormat("0");
				    daye=day2.format(days);  
				     bzw="���һ�γ���ʱ��";//��־���õ��Ǹ�ʱ����е��ݹ�
			   }else{
					  thisdatetime=cssytime;//���û���ϴγ���ʱ��--�ݹ���ʼʱ��=����ʼʹ��ʱ��
					   bzw="����ʼʹ��ʱ��";//��־���õ��Ǹ�ʱ����е��ݹ�
					  //��������ж�����  ���м��� �ݹ�����
					  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
						 	Calendar lastTime = Calendar.getInstance();
						    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
							    //�ݹ�����ʱ��
							   Calendar thisTime = Calendar.getInstance();
							   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));
							    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
							    //�ݹ�����
							    days = Math.ceil(temp/1000/60/60/24.0);
							    days=days+1.0;
							    DecimalFormat day2=new DecimalFormat("0");
							    daye=day2.format(days);
					  }else{ //�����ݹ�����Ϊ0
						  daye="0";
						  days=0;
						  
					  }
				  
			   }    
			//  System.out.println("222222zgsj:"+zgsj+"   daye:"+daye);
			  String ddd="0";//�ݹ�����
			  //����ݹ�����������0  �����ж�
			  if(daye!="0"){
				  int dddd=Integer.parseInt(daye)-1;
				  if(dddd<0){ //���С��0 �ݹ�������Ϊ0
					dddd=0;
					days=0;
				  }
				   ddd=dddd+"";//�ݹ�����
				  // System.out.println("ddd:"+ddd); 
			  }
			    DecimalFormat dj=new DecimalFormat("0.0000");
			   
			    danjia=dj.format(danjia1);	 
			    //System.out.println("danjia1"+danjia1+"  danjia:"+Double.parseDouble(danjia)+"  daye"+daye+"  days:"+days+"  ddd:"+Double.parseDouble(ddd));  		    
				 //System.out.println("danjia:"+danjia+" ddd"+ddd);
				dianfeizangu=mat.format(Double.parseDouble(danjia)*Double.parseDouble(ddd));//�ݹ����
				dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
				String lastadtimew="";
				//���ڼ�һ�� �ݹ���ʼʱ��
		     if(!thisdatetime.equals("")&&!thisdatetime.equals("20101-21")){
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date dt=sdf.parse(thisdatetime);
				Calendar rightNow = Calendar.getInstance();
	            rightNow.setTime(dt);
	            rightNow.add(Calendar.DAY_OF_YEAR,1);//���ڼ�1��
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
						"'"+zdid+"','admins','"+dbid+"','"+dbname+"','"+shi1+"','1','"+zgmonth+"','"+bzw+"','"+xian+"','"+xiaoqu+"','"+property+"')��");
			    
			    
		     }    
			    
		}
		
		rs1 = db.queryAll(sql1.toString());
		System.out.println("�ݹ���ѯ�򵼳�2��"+sql1.toString());
		while(rs1.next()){
			    zgsj=thistimepd;//��ʼ�ݹ�����ʱ��Ϊҳ���ϴ��������ݹ�����ʱ��
			    jzname =rs1.getString(1)!=null?rs1.getString(1):""; //վ������	
			    danjia=rs1.getString(2)!=null?rs1.getString(2):"";//����ѵ���
			    cssytime=rs1.getString(3)!=null?rs1.getString(3):"";//����ʼʹ��ʱ��
			    lastdatetime=rs1.getString(4)!=null?rs1.getString(4):"";//�ϴγ���ʱ��    ��ʼ�·�
	 		    thisdatetime=rs1.getString(5)!=null?rs1.getString(5):"";//���γ���ʱ��         �����·�   �����ֵ��
	 		    actualpay=rs1.getString(6)!=null?rs1.getString(6):"";//ʵ�ʵ�ѽ��
	 		    stationtype=rs1.getString(7)!=null?rs1.getString(7):"";//վ������
	 		    dfzflxx=rs1.getString(8)!=null?rs1.getString(8):"";//���֧������
	 		   edhdl =rs1.getString(9)!=null?rs1.getString(9):"";//��ĵ���
		 		  zdid=rs1.getString(10)!=null?rs1.getString(10):"";//վ��id
		 		 dbid=rs1.getString(11)!=null?rs1.getString(11):"";//���id
		 		dbname =rs1.getString(12)!=null?rs1.getString(12):"";//�������
		 		property=rs1.getString(13)!=null?rs1.getString(13):"";//վ������
		 		qsdbdl = rs1.getString(14)!=null?rs1.getString(14):"";//������
		 		shi1 = rs1.getString(15)!=null?rs1.getString(15):"";//�� ����
		 		   xian =rs1.getString(16)!=null?rs1.getString(16):"";//���ر���
		 		  xiaoqu =rs1.getString(17)!=null?rs1.getString(17):"";//�������
	 		    
	 		    if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
	            if(dbname==null||dbname.equals("null")||dbname.equals(" ")){dbname="";}
	            if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
			  //�ж�Ԥ����ͬ���� ��  �����·�	�����������
			    if(null!=thisdatetime&&!"".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!"0".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
			 	  if(thisdatetime.length()<=7&&thisdatetime.length()>0&&!thisdatetime.equals("20101-21")){ 
			 		    int y,m,tian; 
			 		    y=Integer.parseInt(thisdatetime.substring(0,thisdatetime.indexOf("-"))); //��ȡ���
			 		    m=Integer.parseInt(thisdatetime.substring(thisdatetime.indexOf("-")+1,thisdatetime.length())); //��ȡ�·�
			 		   tian=y+(y-1)/4-(y-1)/100+(y-1)/400;//û����  ����ע�͵�
			 		   //�ж��·ݵ�����
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
			    //���㵥�ۣ�Ԫ/�죩 
			    if(!"0".equals(qsdbdl)&&!"".equals(qsdbdl)&&null!=qsdbdl){
				        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//���ۣ�Ԫ/�죩��������*�����
			    	}else{
				  danjia1 =Format.str_d(danjia)*Format.str_d(edhdl) ;// ���ۣ�Ԫ/�죩�������*��ĵ���
				  day1=0;
				  daye="0";
			     }  
			    
			  if("".equals(danjia1)||" ".equals(danjia1)){
					   danjia1=0.0;
			  }  
			  //վ������ΪС��ͨ��վ��С��ͨ��վ�µ��ݹ�����ʱ��̶�Ϊ��2014-05-17�� �������ݹ����ݹ�����Ϊ�����Ĳ������ݹ���
			   if("StationType01".equals(stationtype)||"StationType27".equals(stationtype)){
				   zgsj ="2014-05-17";
			   }
			  //�����ʱ�� �ݹ�����
			  //���������Щ�жϾͽ�������ݹ�����
			  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
			
				    //�ݹ���ʼʱ��
			   	Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
				    //�ݹ�����ʱ��
				   Calendar thisTime = Calendar.getInstance();
				   //System.out.println("22222222:"+thisdatetime);
				   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));//�������ڸ�ʽ
				    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
				    //�ݹ�����
				    days = Math.ceil(temp/1000/60/60/24.0);
				    days=days+1.0;  //�ݹ�����
				    DecimalFormat day2=new DecimalFormat("0");
				    daye=day2.format(days);  
				     bzw="���һ�γ���ʱ��";//��־���õ��Ǹ�ʱ����е��ݹ�
			   }else{
					  thisdatetime=cssytime;//���û���ϴγ���ʱ��--�ݹ���ʼʱ��=����ʼʹ��ʱ��
					   bzw="����ʼʹ��ʱ��";//��־���õ��Ǹ�ʱ����е��ݹ�
					  //��������ж�����  ���м��� �ݹ�����
					  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
						 	Calendar lastTime = Calendar.getInstance();
						    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
							    //�ݹ�����ʱ��
							   Calendar thisTime = Calendar.getInstance();
							   thisTime.setTime(DateFormat.getDateInstance().parse(zgsj));
							    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
							    //�ݹ�����
							    days = Math.ceil(temp/1000/60/60/24.0);
							    days=days+1.0;
							    DecimalFormat day2=new DecimalFormat("0");
							    daye=day2.format(days);
					  }else{ //�����ݹ�����Ϊ0
						  daye="0";
						  days=0;
						  
					  }
				  
			   } 
			  //System.out.println("333333zgsj:"+zgsj+"   daye:"+daye);
			  String ddd="0";//�ݹ�����
			  //����ݹ�����������0  �����ж�
			  if(daye!="0"){
				  int dddd=Integer.parseInt(daye)-1;
				  if(dddd<0){ //���С��0 �ݹ�������Ϊ0
					dddd=0;
					days=0;
				  }
				   ddd=dddd+"";//�ݹ�����
				  // System.out.println("ddd:"+ddd); 
			  }
			 
			    DecimalFormat dj=new DecimalFormat("0.0000");
			   
			    danjia=dj.format(danjia1);	 
				dianfeizangu=mat.format(Double.parseDouble(danjia)*Double.parseDouble(ddd));//�ݹ����
				dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
				String lastadtimew="";
				//���ڼ�һ�� �ݹ���ʼʱ��
		     if(!thisdatetime.equals("")&&!thisdatetime.equals("20101-21")){
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date dt=sdf.parse(thisdatetime);
				Calendar rightNow = Calendar.getInstance();
	            rightNow.setTime(dt);
	            rightNow.add(Calendar.DAY_OF_YEAR,1);//���ڼ�1��
	            Date dt1=rightNow.getTime();
	            lastadtimew = sdf.format(dt1);
			 }
		     if(!"0.00".equals(dianfeizangu)&&!"".equals(dianfeizangu)&&!"0".equals(dianfeizangu)){
		    	 	sqlpl.append("insert into zangushuju(JZNAME,ADDRESS,STATIONTYPE,DFZFLX,LASTACCOUNTMONTH," +
		    	 			"ZANGUSTARTMONTH,ZANGUENDMONTH,TIANSHU,DANJIA,ZANGUMONEY,SAVETIME,uuid,zdid," +
		    	 			"ENTRYPENSONNEL,DBID,DBNAME,SHI,BZW,ZGMONTH,SJBZW,XIAN,XIAOQU,PROPERTY) values('" +
			 				jzname+"','"+address+"','"+stationtype+"','"+dfzflxx+"','"+thisdatetime+"','"+lastadtimew+"','"+
			 				zgsj+"','"+ddd+"','"+danjia+"','"+dianfeizangu+"',"+sj+",'"+uuid+"'," +
							"'"+zdid+"','admins','"+dbid+"','"+dbname+"','"+shi1+"','2','"+zgmonth+"','"+bzw+"','"+xian+"','"+xiaoqu+"','"+property+"')��");
			    
		     }    
			
			}
		
		//ִ����������
		System.out.println("�ݹ����ݱ���1");
		db.updateBatchDr(sqlpl.toString().split("��"));
		System.out.println("�ݹ����ݱ���ɹ�");
		
		System.out.println("ɾ���½��ݹ������ظ���վ��"+sqldelyj.toString());
		db.update(sqldelyj.toString());
		
		System.out.println("ɾ����ͬ�ݹ������ظ���վ��"+sqldelht.toString());
		db.update(sqldelht.toString());
		
		System.out.println("ɾ���½��ͬ�ݹ������ظ���վ��"+sqldelyh.toString());
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
			System.out.println("�ݹ���Ϣ��ѯ��"+sql.toString());
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
	 * @see �����ݹ�����ܺ�
	 * @author 
	 */
	public FinanceZanGuBean total(List<FinanceZanGuBean> list) {
		FinanceZanGuBean total = new FinanceZanGuBean();//����һ��total����
 		double totalmoney = 0;//�ݹ����ϼ�	
		int i;
		for(i = 0; i<list.size();i++){
			//�������
     		totalmoney = totalmoney + Double.parseDouble(list.get(i).getZangumoney());
		}
		//����total
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
			System.out.println("�ݹ���̯��Ϣ��ѯ��"+sql.toString());
			rs=db.queryAll(sql.toString());
			while(rs.next()){
				FinanceZanGuBean dfdbean = new FinanceZanGuBean();
				dfdbean.setShi(rs.getString(1));
				dfdbean.setNetworkdf(rs.getString(2));//��̯���
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
