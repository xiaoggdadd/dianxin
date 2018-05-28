package com.ptac.app.statisticcollect.province.estimatecontrast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.tongjichaxu.YuejieBean;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.statisticcollect.province.estimatecontrast.bean.EstimateContrastBean;

/**确定后暂估数据对照查询 daoImpl
 * @author WangYiXiao 2014-4-29
 *
 */
public class EstimateContrastDaoImpl implements EstimateContrastDao {

	@Override
	public List<EstimateContrastBean> query(String zangumonth) {
		System.out.println("zangumonth:"+zangumonth);
		
		String sql = "SELECT COUNT(ZGG.ID) ZCOUNT, COUNT(DISTINCT(CONCAT( ZGG.ZDID,ZGG.ZGMONTH))) ZDCOUNT," +
				"(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE=ZGG.SHI) CITY, " +
				"FF.NETWORKDF  AS WLYY, FF.MARKETDF AS SCJY,FF.ADMINISTRATIVEDF AS XZZH, FF.INFORMATIZATIONDF  AS XXHZC," +
				"FF.BUILDDF AS JSTZ, FF.DDDF  AS DDDF, AB.YDZY2, AB.YDZY3, AB.GWZY, AB.GYGTZY, AB.YDGTZY, AB.BKFTZY , " +
				"SUM(ZGG.TIANSHU)AS ZGZQ,SUM(ZANGUMONEY)AS ZGJE " +
				"FROM (SELECT G.SHI, SUM(CASE WHEN S.ZYMXCODE = '11' THEN G.ZANGUMONEY * S.DBILI * S.XJBILI / 10000 END) AS YDZY2,"
	            +"SUM(CASE WHEN S.ZYMXCODE = '12' THEN G.ZANGUMONEY * S.DBILI * S.XJBILI / 10000 END) AS YDZY3, SUM(CASE WHEN S.ZYMXCODE = '21' THEN G.ZANGUMONEY * S.DBILI * S.XJBILI / 10000 END) AS GWZY, SUM(CASE WHEN S.ZYMXCODE = '09' THEN G.ZANGUMONEY * S.DBILI * S.XJBILI / 10000 END) AS GYGTZY, SUM(CASE WHEN S.ZYMXCODE = '19' THEN G.ZANGUMONEY * S.DBILI * S.XJBILI / 10000  END) AS YDGTZY,"
	           +"SUM(CASE WHEN S.ZYMXCODE = '00' THEN G.ZANGUMONEY * S.DBILI * S.XJBILI / 10000 END) AS BKFTZY FROM ZANGU G, SBGL S WHERE S.DIANBIAOID = G.DBID AND G.ZGMONTH = ? GROUP BY G.SHI) AB, (SELECT Z.SHI,SUM(CASE WHEN ZG.SHUOSHUZHUANYE = 'zylx01' THEN Z.ZANGUMONEY * ZG.DBILI / 100 END) AS NETWORKDF, SUM(CASE WHEN ZG.SHUOSHUZHUANYE = 'zylx02' THEN  Z.ZANGUMONEY * ZG.DBILI / 100 END) AS MARKETDF,"
	           +"SUM(CASE WHEN ZG.SHUOSHUZHUANYE = 'zylx03' THEN Z.ZANGUMONEY * ZG.DBILI / 100 END) AS ADMINISTRATIVEDF, SUM(CASE WHEN ZG.SHUOSHUZHUANYE = 'zylx04' THEN Z.ZANGUMONEY * ZG.DBILI / 100 END) AS INFORMATIZATIONDF, SUM(CASE WHEN ZG.SHUOSHUZHUANYE = 'zylx05' THEN Z.ZANGUMONEY * ZG.DBILI / 100 END) AS BUILDDF, SUM(CASE WHEN ZG.SHUOSHUZHUANYE = 'zylx06' THEN Z.ZANGUMONEY * ZG.DBILI / 100 END) AS DDDF "
	           +"FROM  ZANGU Z, (SELECT DISTINCT G.SHUOSHUZHUANYE, G.DIANBIAOID, G.DBILI FROM SBGL G) ZG WHERE  Z.DBID = ZG.DIANBIAOID AND Z.ZGMONTH = ? GROUP BY Z.SHI) FF,ZANGU ZGG WHERE AB.SHI = FF.SHI AND  FF.SHI = ZGG.SHI AND ZGG.ZGMONTH = ?"
	           +"GROUP BY zgg.SHI, FF.NETWORKDF, FF.MARKETDF,FF.ADMINISTRATIVEDF, FF.INFORMATIZATIONDF, FF.BUILDDF, FF.DDDF,AB.YDZY2,AB.YDZY3, AB.GWZY,AB.GYGTZY, AB.YDGTZY, AB.BKFTZY";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		DataBase db = new DataBase();
		List<EstimateContrastBean> list = new ArrayList<EstimateContrastBean>();
		String city = null;//地市
		String zdcount = null;//站点数量
		String zcount = null;//总条数
		String wlyy = null;//网络运营
		String scjy = null;//市场经营
		String xzzh = null;//行政综合
		String xxhzc = null;//信息化支撑
		String jstz = null;//建设投资
		String dddf  = null;//代垫电费
		String ydzy2 = null;//移动专业-2G
		String ydzy3 = null;// 移动专业-3G
		String gwzy = null;// 固网专业  
		String gygtzy = null;//固移共同专业
		String ydgtzy = null;//移动共同专业
		String bkftzy = null;//不可分摊专业
		String money = null;//暂估金额
		String zgzq = null;//暂估总周期
		String zgje = null;//暂估总金额
		
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, zangumonth);
			ps.setString(2, zangumonth);
			ps.setString(3, zangumonth);

			System.out.println("暂估数据对照查询:"+sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
	
				city =  rs.getString("CITY");
				zdcount = rs.getString("ZDCOUNT");
				zcount = rs.getString("ZCOUNT");
				wlyy = rs.getString("WLYY");
				scjy = rs.getString("SCJY");
				xzzh = rs.getString("XZZH");
				xxhzc = rs.getString("XXHZC");
				jstz = rs.getString("JSTZ");
				dddf  = rs.getString("DDDF");
				ydzy2 = rs.getString("YDZY2");
				ydzy3 = rs.getString("YDZY3");
				gwzy = rs.getString("GWZY");
				gygtzy = rs.getString("GYGTZY");
				ydgtzy = rs.getString("YDGTZY");
				bkftzy = rs.getString("BKFTZY");
				ydgtzy = rs.getString("YDGTZY");
				bkftzy = rs.getString("BKFTZY");
				zgzq = rs.getString("ZGZQ");
				zgje = rs.getString("ZGJE");
				money = Format.str2(String.valueOf(Double.parseDouble(wlyy==null?"0":wlyy) + Double.parseDouble(scjy==null?"0":scjy) + Double.parseDouble(xzzh==null?"0":xzzh) + Double.parseDouble(xxhzc==null?"0":xxhzc) + Double.parseDouble(jstz==null?"0":jstz) + Double.parseDouble(dddf==null?"0":dddf)));

				EstimateContrastBean ecb = new EstimateContrastBean();
				
				ecb.setCity(city);
				ecb.setZdcount(zdcount == null ? "0" : zdcount);
				ecb.setZcount(zcount == null ? "0" : zcount);
				ecb.setWlyy(Format.str2(wlyy));
				ecb.setScjy(Format.str2(scjy));
				ecb.setXzzh(Format.str2(xzzh));
				ecb.setXxhzc(Format.str2(xxhzc));
				ecb.setJstz(Format.str2(jstz));
				ecb.setDddf(Format.str2(dddf));
				
				ecb.setYdzy2(Format.str2(ydzy2));
				ecb.setYdzy3(Format.str2(ydzy3));
				ecb.setGwzy(Format.str2(gwzy));
				ecb.setGygtzy(Format.str2(gygtzy));
				ecb.setYdgtzy(Format.str2(ydgtzy));
				ecb.setBkftzy(Format.str2(bkftzy));
				ecb.setZgzq(zgzq);
				ecb.setZgje(Format.str2(zgje));
				ecb.setMoney(money);
				list.add(ecb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
}
