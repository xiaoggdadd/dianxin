package com.noki.elecconsume;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.noki.database.DataBase;
import com.noki.util.CTime;
import com.noki.util.Query;

public class Shenghdlquery {
	//省级耗电量统计 查询
	
	DataBase db = new DataBase();
	Connection conn = null;
	Statement sta = null;
	ResultSet rs = null;
	
	public synchronized ArrayList getPageData( String begintime,
			String endtime, String shi) {

		ArrayList list = new ArrayList();
        String whereStr="and sj>='"+begintime+"' and sj<='"+endtime+"'";
		StringBuffer zd = new StringBuffer();
		if (shi.equals("0")) {
			zd.append("");
		} else {
			zd.append(" and z.shi='" + shi + "'");
		} 
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		StringBuffer s2 = new StringBuffer();
	
        s2.append("select  dag.agname,zdlxhdl.sj sj," +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0))+" +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0))+sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SUM_HJ," +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx01',zdlxhdl.blhdl),0)) SCYFHDL,sum(nvl(decode(zdlxhdl.property,'zdsx02',zdlxhdl.blhdl),0)) TXJFHDL," +
        				"sum(nvl(decode(zdlxhdl.property,'zdsx03',zdlxhdl.blhdl),0)) JZHDL,sum(nvl(decode(zdlxhdl.property,'zdsx04',zdlxhdl.blhdl),0)) SJZXHDL " +
        				"from (select zd.sheng,zd.property,ad.blhdl,to_char(ad.endmonth,'yyyy-mm') sj from zhandian zd, ammeterdegrees ad, dianbiao am,electricfees e " +
        				"where zd.id=am.zdid and zd.xuni='0' and zd.qyzt='1' and am.dbyt='dbyt01' and am.dbid = ad.ammeterid_fk and ad.ammeterdegreeid=e.ammeterdegreeid_fk and e.manualauditstatus>='1') zdlxhdl,d_area_grade dag " +
        				"where zdlxhdl.sheng=dag.agcode "+whereStr+"group by dag.agname ,zdlxhdl.sheng,zdlxhdl.sj order by zdlxhdl.sj");

		try {
			db.connectDb();
			conn = db.getConnection();
			System.out.println("省级耗电量统计 :"+s2.toString());
			sta = conn.createStatement();
			rs = sta.executeQuery(s2.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (Exception de) {
			de.printStackTrace();
		} finally {
			db.free(rs, sta, conn);
		}

		return list;
	}
}
