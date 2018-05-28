package com.ptac.app.calibstat.daterepeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.calibstat.daterepeat.bean.DateRepeatBean;
import com.ptac.app.electricmanageUtil.Format;

public class DateRepeatImpl {

	public synchronized List<DateRepeatBean> getShi(String whereStr,String month) {		
		List<DateRepeatBean> list = new ArrayList<DateRepeatBean>();
	    String sql = "";
	    DataBase db = new DataBase();
	    Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
			Date date = sdf.parse(month); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	      
	       sql=" select t.shi,RNDIQU(t.SHI), COUNT(DISTINCT t.zdid),count(DISTINCT decode(sign(pp.ts - "+maxDate+"), 1, t.zdid)) as ddy,"
              +" count(DISTINCT decode(sign(pp.ts - "+maxDate+"), 0, t.zdid)) as dy,count(DISTINCT decode(sign(pp.ts - "+maxDate+"), -1, t.zdid)) as xy"
              +" from (select t.zdid, sum(t.tscount) as ts from zhandian z, T_ZRYTEMP t, dianbiao d  where z.id = d.zdid and z.id = t.zdid"
              +" and d.dbid = t.dbid  "+whereStr+" group by t.zdid) PP,"
              +"  T_ZRYTEMP t where t.zdid = pp.zdid  group by t.shi";
	   
	       System.out.println("�����ظ���������-������Ϣ��"+sql);
	      
	       db.connectDb();
	       conn = db.getConnection();
	       ps = conn.prepareStatement(sql);
	       rs = ps.executeQuery();
	       while(rs.next()){
	    	   DateRepeatBean bean=new DateRepeatBean();
	    	   String city = rs.getString(1)!=null?rs.getString(1):"";//����(����)
	    	   String shi = rs.getString(2)!=null?rs.getString(2):"";//����(����)
	    	   String zdcount = rs.getString(3)!=null?rs.getString(3):"0";//վ����
	    	   String cbl = rs.getString(4)!=null?rs.getString(4):"0";;//��������
	    	   String bili = rs.getString(5)!=null?rs.getString(5):"0";;//ƥ�����
	    	   String bzbl = rs.getString(6)!=null?rs.getString(6):"0";;//�������
	    	   
	    	   Double cbl1 = Double.valueOf(cbl)/Double.valueOf(zdcount)*100;
	    	   Double bili1 = Double.valueOf(bili)/Double.valueOf(zdcount)*100;
	    	   //Double bzbl1 = Double.valueOf(bzbl)/Double.valueOf(zdcount)*100;
	    	   
	    	   DecimalFormat df = new DecimalFormat("0.00"); 
	    	   String cbl2 = df.format(cbl1);
	    	   String bili2 = df.format(bili1);
	    	   Double bzbl1 = 100-Double.valueOf(cbl2)-Double.valueOf(bili2);//Ϊ�˷�ֹ����0.01%�����
	    	   String bzbl2 = df.format(bzbl1);
	    	   
	    	   bean.setCity(city);
	    	   bean.setShi(shi);
	    	   bean.setZdcount(zdcount);
	    	   bean.setCbl(cbl2);
	    	   bean.setBili(bili2);
	    	   bean.setBzbl(bzbl2);
	    	   list.add(bean);
	       }

	    }catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      db.free(rs, ps, conn);
	    }
	    return list;
	}
	
	/**
	 * ƥ�����
	 * */
	public synchronized List<DateRepeatBean> getXian1(String whereStr,String month) {		
		List<DateRepeatBean> list = new ArrayList<DateRepeatBean>();
	    String sql = "";
	    DataBase db = new DataBase();
	    Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
			Date date = sdf.parse(month); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			sql =" select RNDIQU(z.xian),z.jzname,rtname(z.stationtype),sum(t.tscount)as ts,count(DISTINCT t.eleid),t.zdid"
				+" from zhandian z,T_ZRYTEMP t,dianbiao d where z.id = d.zdid and z.id=t.zdid and d.dbid=t.dbid "
				+ whereStr + " group by t.zdid,z.stationtype,z.jzname,z.xian having sum(t.tscount) = "+maxDate;
	   
			System.out.println("�����ظ���������-������Ϣ��"+sql);
	      
	       db.connectDb();
	       conn = db.getConnection();
	       ps = conn.prepareStatement(sql);
	       rs = ps.executeQuery();
	       while(rs.next()){
	    	   DateRepeatBean bean=new DateRepeatBean();
	    	   String xian = rs.getString(1)!=null?rs.getString(1):"";//��
	    	   String zdname = rs.getString(2)!=null?rs.getString(2):"";//վ������
	    	   String zdlx = rs.getString(3)!=null?rs.getString(3):"";//վ������
	    	   String dayCount = rs.getString(4)!=null?rs.getString(4):"0";//�����ܺ�
	    	   String numCount = rs.getString(5)!=null?rs.getString(5):"0";//�����ܺ�
	    	   String zdid = rs.getString(6)!=null?rs.getString(6):"";//վ��ID
	    	   
	    	   bean.setXian(xian);
	    	   bean.setZdname(zdname);
	    	   bean.setZdlx(zdlx);
	    	   bean.setNumCount(numCount);
	    	   bean.setDayCount(dayCount);
	    	   bean.setId(zdid);
	    	   list.add(bean);
	       }

	    }catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      db.free(rs, ps, conn);
	    }
	    return list;
	}
	
	/**
	 * �������
	 * */
	public synchronized List<DateRepeatBean> getXian2(String whereStr,String month) {		
		List<DateRepeatBean> list = new ArrayList<DateRepeatBean>();
	    String sql = "";
	    DataBase db = new DataBase();
	    Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
			Date date = sdf.parse(month); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			sql =" select RNDIQU(z.xian),z.jzname,rtname(z.stationtype),sum(t.tscount)as ts,count(DISTINCT t.eleid),t.zdid"
				+" from zhandian z,T_ZRYTEMP t,dianbiao d where z.id = d.zdid and z.id=t.zdid and d.dbid=t.dbid "
				+ whereStr + " group by t.zdid,z.stationtype,z.jzname,z.xian having sum(t.tscount) < "+maxDate;
	   
			System.out.println("�����ظ���������-������Ϣ��"+sql);
	      
	       db.connectDb();
	       conn = db.getConnection();
	       ps = conn.prepareStatement(sql);
	       rs = ps.executeQuery();
	       while(rs.next()){
	    	   DateRepeatBean bean=new DateRepeatBean();
	    	   String xian = rs.getString(1)!=null?rs.getString(1):"";//��
	    	   String zdname = rs.getString(2)!=null?rs.getString(2):"";//վ������
	    	   String zdlx = rs.getString(3)!=null?rs.getString(3):"";//վ������
	    	   String dayCount = rs.getString(4)!=null?rs.getString(4):"0";//�����ܺ�
	    	   String numCount = rs.getString(5)!=null?rs.getString(5):"0";//�����ܺ�
	    	   String zdid = rs.getString(6)!=null?rs.getString(6):"";//վ��ID
	    	   
	    	   bean.setXian(xian);
	    	   bean.setZdname(zdname);
	    	   bean.setZdlx(zdlx);
	    	   bean.setNumCount(numCount);
	    	   bean.setDayCount(dayCount);
	    	   bean.setId(zdid);
	    	   list.add(bean);
	       }

	    }catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      db.free(rs, ps, conn);
	    }
	    return list;
	}
	
	/**
	 * ��������
	 * */
	public synchronized List<DateRepeatBean> getXian3(String whereStr,String month) {		
		List<DateRepeatBean> list = new ArrayList<DateRepeatBean>();
	    String sql = "";
	    DataBase db = new DataBase();
	    Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
			Date date = sdf.parse(month); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			sql =" select RNDIQU(z.xian),z.jzname,rtname(z.stationtype),sum(t.tscount)as ts,count(DISTINCT t.eleid),t.zdid"
				+" from zhandian z,T_ZRYTEMP t,dianbiao d where z.id = d.zdid and z.id=t.zdid and d.dbid=t.dbid "
				+ whereStr + " group by t.zdid,z.stationtype,z.jzname,z.xian having sum(t.tscount) > "+maxDate;
	   
			System.out.println("�����ظ���������-������Ϣ��"+sql);
	      
	       db.connectDb();
	       conn = db.getConnection();
	       ps = conn.prepareStatement(sql);
	       rs = ps.executeQuery();
	       while(rs.next()){
	    	   DateRepeatBean bean=new DateRepeatBean();
	    	   String xian = rs.getString(1)!=null?rs.getString(1):"";//��
	    	   String zdname = rs.getString(2)!=null?rs.getString(2):"";//վ������
	    	   String zdlx = rs.getString(3)!=null?rs.getString(3):"";//վ������
	    	   String dayCount = rs.getString(4)!=null?rs.getString(4):"0";//�����ܺ�
	    	   String numCount = rs.getString(5)!=null?rs.getString(5):"0";//�����ܺ�
	    	   String zdid = rs.getString(6)!=null?rs.getString(6):"";//վ��ID
	    	   
	    	   bean.setXian(xian);
	    	   bean.setZdname(zdname);
	    	   bean.setZdlx(zdlx);
	    	   bean.setNumCount(numCount);
	    	   bean.setDayCount(dayCount);
	    	   bean.setId(zdid);
	    	   list.add(bean);
	       }

	    }catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      db.free(rs, ps, conn);
	    }
	    return list;
	}
	
	public synchronized List<DateRepeatBean> getXiangXi(String sql1,String whereStr,String month,String bzw) {		
		List<DateRepeatBean> list = new ArrayList<DateRepeatBean>();
		StringBuffer sql = new StringBuffer();
	    DataBase db = new DataBase();
	    Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
	      
			sql.append(" SELECT TO_CHAR(A.LASTDATETIME,'YYYY-MM-DD'),TO_CHAR(A.THISDATETIME,'YYYY-MM-DD'),A.LASTDEGREE,A.THISDEGREE,A.ACTUALDEGREE,A.FLOATDEGREE,E.UNITPRICE,");
			sql.append(" E.ACTUALPAY,E.FLOATPAY,E.ENTRYPENSONNEL,TO_CHAR(E.ENTRYTIME,'YYYY-MM-DD'),E.CITYZRAUDITNAME,TO_CHAR(E.CITYZRAUDITTIME,'YYYY-MM-DD HH24:MI:SS'),E.FINANCIALOPERATOR,");
			sql.append(" TO_CHAR(E.FINANCIALDATETIME,'YYYY-MM-DD HH24:MI:SS'),E.LIUCHENGHAO");
			sql.append(" FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E WHERE Z.ID = D.ZDID AND ");
			sql.append(" D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND ");	
			sql.append(" E.electricfee_id in(select t.eleid from zhandian z,T_ZRYTEMP t,dianbiao d where z.id = d.zdid and z.id=t.zdid ");
			sql.append(" and d.dbid=t.dbid "+whereStr+sql1.toString());
			sql.append(" group by t.zdid,t.eleid )");
	      
			System.out.println("�����ظ���������-��ѵ��굥��"+sql);
	        db.connectDb();
	        conn = db.getConnection();
	        ps = conn.prepareStatement(sql.toString());
	        rs = ps.executeQuery();
	        while(rs.next()){
	        	DateRepeatBean bean=new DateRepeatBean();
	        	String beginTime = rs.getString(1)!=null?rs.getString(1):"";//��ʼ����
	       	  	String endTime = rs.getString(2)!=null?rs.getString(2):"";//��������
	       	  	String beginCode = rs.getString(3)!=null?rs.getString(3):"";//��ʼ��
	       	  	String endCode = rs.getString(4)!=null?rs.getString(4):"";//������
	       	  	String dl = rs.getString(5)!=null?rs.getString(5):"";//����
	       	  	String tzdl = rs.getString(6)!=null?rs.getString(6):"";//��������
	       	  	String danjia = rs.getString(7)!=null?rs.getString(7):"";//����
	       	  	String df = rs.getString(8)!=null?rs.getString(8):"";//���
	       	  	String tzdf = rs.getString(9)!=null?rs.getString(9):"";//�������
	       	  	String lry = rs.getString(10)!=null?rs.getString(10):"";//¼��Ա
	       	  	String lrtime = rs.getString(11)!=null?rs.getString(11):"";//¼������
	       	  	String shy = rs.getString(12)!=null?rs.getString(12):"";//���Ա(������)
	       	  	String shtime = rs.getString(13)!=null?rs.getString(13):"";//�������
	       	  	String spy = rs.getString(14)!=null?rs.getString(14):"";//��������Ա
	       	  	String sptime = rs.getString(15)!=null?rs.getString(15):"";//������������
	       	  	String bzdh = rs.getString(16)!=null?rs.getString(16):"";//���˵���

	       	  	bean.setBeginTime(beginTime);
	       	  	bean.setEndTime(endTime);
	       	  	bean.setBeginCode(beginCode);
	       	  	bean.setEndCode(endCode);
	       	  	bean.setDl(dl);
	       	  	bean.setTzdl(tzdl);
	       	  	bean.setDanjia(danjia);
	       	  	bean.setDf(df);
	       	  	bean.setTzdf(tzdf);
	       	  	bean.setLry(lry);
	       	  	bean.setLrtime(lrtime);
	       	  	bean.setShy(shy);
	       	  	bean.setShtime(shtime);
	       	  	bean.setSpy(spy);
	       	  	bean.setSptime(sptime);
	       	  	bean.setBzdh(bzdh);
	        	list.add(bean);
	        }

	    }catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      db.free(rs, ps, conn);
	    }
	    return list;
	}
}
