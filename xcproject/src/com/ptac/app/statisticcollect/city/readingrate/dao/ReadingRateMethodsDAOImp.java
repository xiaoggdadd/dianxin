package com.ptac.app.statisticcollect.city.readingrate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Version;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateMessageBean;
import com.ptac.app.statisticcollect.city.readingrate.bean.ReadingRateSelectBean;

public class ReadingRateMethodsDAOImp implements ReadingRateMethodsDAO {

	@Override
	public List<ReadingRateMessageBean> findReadingRate(
			ReadingRateSelectBean bean, String loginID,String sql1,String sql2) {
		
		List<ReadingRateMessageBean> ls=null;
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	//	String whereSQL = getWhereSQL(bean);//不用这种方式了
		
		try{
			
			StringBuffer sql = new StringBuffer();
		/*	sql.append(" SELECT rndiqu(ZD.SHI),rndiqu(ZD.XIAN)," +
						"COUNT(ZD.ID) AS ZDSL," +
						"COUNT(CASE WHEN DB.DBYT='dbyt01' THEN DB.DBID END) AS JIESUAN," +
						"COUNT(CASE WHEN DB.DBYT='dbyt03' THEN DB.DBID END) AS GUANLI," +
						"COUNT(CASE WHEN DB.DBYT='dbyt01' AND DD.THISDATETIME IS NOT NULL AND DD.LASTDATETIME IS NOT NULL THEN DB.DBID END) AS JIESUANCS," +
						"COUNT(CASE WHEN DB.DBYT='dbyt03' AND DD.THISDATETIME IS NOT NULL AND DD.LASTDATETIME IS NOT NULL THEN DB.DBID END) AS GUANLICS " +
						" FROM ZHANDIAN ZD,DIANBIAO DB,AMMETERDEGREES DD " +
						" WHERE ZD.ID = DB.ZDID " +
						" AND DB.DBID = DD.AMMETERID_FK " +
						" AND ZD.QYZT = '1' " +
						" AND DB.DBQYZT = '1' " +whereSQL+
						" and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = ? )) "+fzzdstr+" )"+
						"  GROUP BY ZD.SHI,ZD.XIAN ");*/
			sql.append("SELECT rndiqu(ZD.SHI), rndiqu(ZD.XIAN), COUNT(distinct  ZD.ID) AS ZDSL,  " +
					"COUNT(distinct (CASE WHEN DB.DBYT = 'dbyt01' and DB.DBQYZT = '1' THEN DB.DBID END)) AS JIESUAN," +
					"COUNT(distinct (CASE WHEN DB.DBYT = 'dbyt03' and DB.DBQYZT = '1' THEN DB.DBID END)) AS GUANL," +
					"COUNT(CASE WHEN DB.DBYT = 'dbyt01' "+sql2+" and  e.electricfee_id IS NOT NULL THEN DB.DBID END) AS JIESUANCS," +
					"COUNT(CASE WHEN DB.DBYT = 'dbyt03' "+sql2+" and DD.AMMETERDEGREEID IS NOT NULL THEN DB.DBID END) AS GUANLICS" +
					" FROM ZHANDIAN ZD left join dianbiao db on zd.id = db.zdid left join ammeterdegrees dd on db.dbid = dd.ammeterid_fk" +
					" left join electricfees e on dd.ammeterdegreeid = e.ammeterdegreeid_fk" +
					" WHERE ZD.QYZT = '1' "+sql1+" " +
					" and (zd.xiaoqu in (select t.agcode from per_area t where t.accountid = ? ))"+
							"GROUP BY ZD.SHI, ZD.XIAN");
			
			System.out.println("抄表率SQL："+sql.toString());
			
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, loginID);
			rs = ps.executeQuery();
			ls = new ArrayList<ReadingRateMessageBean>();
			while(rs.next()){
				
				String shi = rs.getString(1);
				String xian = rs.getString(2);
				String zdsl = rs.getString(3);
				String jiesuan = rs.getString(4);
				String guanli = rs.getString(5);
				String jiesuancs = rs.getString(6);
				String guanlics = rs.getString(7);
				
				ReadingRateMessageBean mess = new ReadingRateMessageBean(shi, xian, zdsl, jiesuan, guanli, jiesuancs, guanlics);
				
				ls.add(mess);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		
		return ls;
	}
	
	/**
	 * 用于获取where语句
	 * @author rock
	 * @param bean
	 * @return
	 */
	private String getWhereSQL(ReadingRateSelectBean bean){
		StringBuffer SQL = new StringBuffer();
		
		String shi =bean.getShi();
		String xian =bean.getXian();
		String startmonth =bean.getStartmonth();
		String endmonth =bean.getEndmonth();
		String zdlx =bean.getZdsx();
		String zdsx =bean.getZdlx();
		String gdfs = bean.getGdfs();
		
		if(shi!=null&&!"".equals(shi)){
			SQL.append(" and zd.shi='");
			SQL.append(shi);
			SQL.append("' ");
		}
		if(xian!=null&&!"".equals(xian)){
			SQL.append(" and zd.xian='");
			SQL.append(xian);
			SQL.append("' ");
		}
		if(startmonth!=null&&!"".equals(startmonth)){
			SQL.append(" and DD.THISDATETIME >'");
			SQL.append(getBeginTime(startmonth));
			SQL.append("' ");
		}
		if(endmonth!=null&&!"".equals(endmonth)){
			SQL.append(" and DD.THISDATETIME<'");
			SQL.append(getEndTime(endmonth));
			SQL.append("' ");
		}
		if(zdlx!=null&&!"".equals(zdlx)){
			SQL.append(" and zd.STATIONTYPE='");
			SQL.append(zdlx);
			SQL.append("' ");
		}
		if(zdsx!=null&&!"".equals(zdsx)){
			SQL.append(" and zd.PROPERTY='");
			SQL.append(zdsx);
			SQL.append("' ");
		}
		if(gdfs!=null&&!"".equals(gdfs)){
			SQL.append(" and zd.GDFS='");
			SQL.append(gdfs);
			SQL.append("' ");
		}
		
		
		return SQL.toString();
	}
	

	
	/**
	 * 获取起始时间
	 * @param time
	 * @return
	 */
	public  String getBeginTime(String time) {
		String qssj = time + "-01";
		return qssj;
	}

	/**
	 * 获取结束时间
	 * @param time
	 * @return
	 */
	@Override
	public String getEndTime(String time) {
		String jssj = "";
		try {
			String year = time.substring(0, 4);
			String mon = time.substring(5, 7);

			double y = Double.parseDouble(year);
			int m = Integer.parseInt(mon);
			String day = "";

			switch (m) {
			case 1:
				day = "-31";
				break;
			case 2:
				if (y % 4 == 0) {
					day = "-29";
				} else {
					day = "-28";
				}

				break;
			case 3:
				day = "-31";
				break;
			case 4:
				day = "-30";
				break;
			case 5:
				day = "-31";
				break;
			case 6:
				day = "-30";
				break;
			case 7:
				day = "-31";
				break;
			case 8:
				day = "-31";
				break;
			case 9:
				day = "-30";
				break;
			case 10:
				day = "-31";
				break;
			case 11:
				day = "-30";
				break;
			case 12:
				day = "-31";
				break;
			default:
				jssj = "日期输入格式不正确";
			}
			jssj = time + day;

		} catch (Exception e) {
			jssj = "日期输入格式不正确！";
		}
		return jssj;
	}
	
}
