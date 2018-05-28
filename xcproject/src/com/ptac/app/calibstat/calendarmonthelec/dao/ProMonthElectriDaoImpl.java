package com.ptac.app.calibstat.calendarmonthelec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.calibstat.calendarmonthelec.bean.ProMonthElectriBean;
import com.ptac.app.electricmanageUtil.Format;

public class ProMonthElectriDaoImpl implements ProMonthElectriDao{
	
	public List<Object> proMonthSele(String whereStr,Date begindate,Date enddate,String beginTime,String endTime,int l ){
		List<Object> list = new ArrayList<Object>();
	    StringBuffer sql = new StringBuffer();
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    Connection conn = null;
		Statement sta = null; 
		//转换日期格式
		  DateFormat fmt =new SimpleDateFormat("yyyy-MM");
		  Calendar g = Calendar.getInstance();
		 
		 Date datetime = begindate;
		 String cxTime = beginTime;
		String where = "";
		//解决sql语句拼接最后放不放逗号的问题
		if(l==1){
			where = "";
		}else if(l>1){
			where = ",";
		}

		 sql.append("SELECT RNDIQU(Z.SHI)," );
		for(int i=1;i<=l;i++){
			if(i==l){
				where = "";
			}
			sql.append(" COUNT(DISTINCT(decode(TO_CHAR(T.YFMONTH,'YYYY-MM'),'"+cxTime+"',CONCAT(T.ZDID, T.YFMONTH)))) AS ZDS"+i+"," +
					" SUM(decode(TO_CHAR(T.YFMONTH,'YYYY-MM'),'"+cxTime+"',t.dlcount))AS dl"+i+"," +
					" SUM(decode(TO_CHAR(T.YFMONTH,'YYYY-MM'),'"+cxTime+"',t.dfcount))AS df"+i+"," +
					" COUNT(DISTINCT(decode(TO_CHAR(T.YFMONTH,'YYYY-MM'),'"+cxTime+"',t.eleid)))AS dfd"+i+""+where+"");
		
		//获取起始月份 循环递增 用于sql字段值的判断条件
		g.setTime(datetime);
		g.add(Calendar.MONTH, 1);
		datetime = g.getTime();
		cxTime = fmt.format(datetime);

		}
		
	    sql.append(" FROM T_ZRYTEMP T,ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND Z.ID=T.ZDID AND D.DBID=T.DBID  "+whereStr+" " +
		    		" AND TO_CHAR(T.YFMONTH,'YYYY-MM')>='"+beginTime+"' " +
		    		" AND TO_CHAR(T.YFMONTH,'YYYY-MM')<='"+endTime+"' GROUP BY Z.SHI");
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			System.out.println("省级自然月用电统计查询:"+sql.toString());
			rs = sta.executeQuery(sql.toString());//运行sql
			//int i = 0;
			while(rs.next()){//数据条数				
/*			   Map<Integer,Object> m = new HashMap<Integer,Object>();  
				//Object[] o = (Object[])rs.get;//获取第几行的数据
			   ResultSetMetaData meta = rs.getMetaData();
			   int num = meta.getColumnCount();

				System.out.println("城市存储值："+rs.getString(1));
				m.put(0,ObjectUtils.toString(rs.getString(1)));//城市放到list
				int key = (num-1)/4;
				for(int j = 0;j < key;j++){//	
					System.out.println("月份循环次数"+key+"  循环数据"+rs.getString(j*4+2));
					//StringUtils.isNotEmpty(where);//判断string为空
					ProMonthElectriBean bean = new ProMonthElectriBean();
					bean.setZds(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+2))));
					bean.setDl(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+3))));
					bean.setDf(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+4))));
					bean.setDfdts(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+5))));
					m.put(j+1,bean);			
				}*/
				   List<Object> m = new ArrayList<Object>();
				   ResultSetMetaData meta = rs.getMetaData();//
				   int num = meta.getColumnCount();
				   m.add(ObjectUtils.toString(rs.getString(1)));//城市放到list
					int key = (num-1)/4;
					for(int j = 0;j < key;j++){//	
						//StringUtils.isNotEmpty(where);//判断string为空
						ProMonthElectriBean bean = new ProMonthElectriBean();
						bean.setZds(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+2))));
						bean.setDl(Format.str2(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+3)))));
						bean.setDf(Format.str2(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+4)))));
						bean.setDfdts(ObjectUtils.toString(ObjectUtils.toString(rs.getString(j*4+5))));
						m.add(bean);			
					}
				list.add(m);
				//i++;
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
			db.free(rs,sta,conn);
		}	 
		return list;
	}

}
