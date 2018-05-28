package com.ptac.app.statisticcollect.city.unitAveShi.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.city.unitAveShi.bean.UnitAveShiBean;
/**
 * @see 电费缴纳明细dao层实现类
 * @author ZengJin
 */
public class UnitAveShiDaoImp implements UnitAveShiDao {
	/**
	 * @param whereStr (String)	//sql语句后半段
	 * @param loginId (String) //权限过滤条件 
	 * @return List(UnitAveShiBean)
	 * @see 市级单价平均和平均单价
	 * @author ZengJin 2014-3-31
	 */
	public List<UnitAveShiBean> queryElectric(String whereStr, String loginId, String shi, String bzyfstart, String bzyfend) {
		List<UnitAveShiBean> list = new ArrayList<UnitAveShiBean>();//声明结果集
		StringBuffer sql0 = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			sql0.append(" (SELECT AGNAME AS SHI FROM D_AREA_GRADE WHERE AGCODE='"+ shi +"')");
			
			
			sql.append(" SELECT RNDIQU(ZD.XIAN) XIAN," 
					 +" SUM(E.UNITPRICE) AS BZDJZH,"
					 +" COUNT(E.ELECTRICFEE_ID) AS DFDTS,"
					 +" SUM(E.ACTUALPAY) AS ZDF,"
					 +" SUM(A.BLHDL) AS ZDL,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', E.UNITPRICE, NULL)) AS SQBZDJZH,"
					 +" COUNT(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', E.ELECTRICFEE_ID, NULL)) AS SQDFDTS,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', E.UNITPRICE, NULL)) AS MQBZDJZH,"
					 +" COUNT(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', E.ELECTRICFEE_ID, NULL)) AS MQDFDTS,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', E.ACTUALPAY, NULL)) AS SQZDF,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', A.BLHDL, NULL)) AS SQZDL,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', E.ACTUALPAY, NULL)) AS MQZDF,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', A.BLHDL, NULL)) AS MQZDL"
					 +" FROM ZHANDIAN ZD, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E"
					 +" WHERE ZD.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK"
					 +" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') >= '"+ bzyfstart +"'"
					 +" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') <= '"+ bzyfend +"'"
					 + whereStr
					 +" AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId +"'))"
					 +" GROUP BY ZD.XIAN");
				
			System.out.println("地市单价平均与平均单价:" + sql);//后台打印sql
			
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			
			rs = sta.executeQuery(sql0.toString());//运行sql
			String shiCh = "";
			while(rs.next()){
				shiCh = rs.getString("SHI") != null ? rs.getString("SHI") : "";
			}
			
			rs = sta.executeQuery(sql.toString());//运行sql
			while (rs.next()) {
				UnitAveShiBean bean = new UnitAveShiBean();//库里每有一行数据便生成一个bean
				//------接收查询到的数据(并判断是否为空)------
				String xian = rs.getString("XIAN")!=null?rs.getString("XIAN"):"";			//县
				double bzdjzh = Format.str_d(	rs.getString("BZDJZH")!=null?rs.getString("BZDJZH"):"");	//报账单价总和
				double dfdts = Format.str_d(	rs.getString("DFDTS")!=null?rs.getString("DFDTS"):"");		//电费单条数
				double zdf = Format.str_d(		rs.getString("ZDF")!=null?rs.getString("ZDF"):"");			//总电费
				double zdl = Format.str_d(		rs.getString("ZDL")!=null?rs.getString("ZDL"):"");			//总电量
				double sqbzdjzh	= Format.str_d(	rs.getString("SQBZDJZH")!=null?rs.getString("SQBZDJZH"):"");//首期报账单价总和
				double sqdfdts = Format.str_d(	rs.getString("SQDFDTS")!=null?rs.getString("SQDFDTS"):"");	//首期电费单条数
				double mqbzdjzh	= Format.str_d(	rs.getString("MQBZDJZH")!=null?rs.getString("MQBZDJZH"):"");//末期报账单价总和
				double mqdfdts = Format.str_d(	rs.getString("MQDFDTS")!=null?rs.getString("MQDFDTS"):"");	//末期电费单条数
				double sqzdf = Format.str_d(	rs.getString("SQZDF")!=null?rs.getString("SQZDF"):"");		//首期总电费
				double sqzdl = Format.str_d(	rs.getString("SQZDL")!=null?rs.getString("SQZDL"):"");		//首期总电量
				double mqzdf = Format.str_d(	rs.getString("MQZDF")!=null?rs.getString("MQZDF"):"");		//末期总电费
				double mqzdl = Format.str_d(	rs.getString("MQZDL")!=null?rs.getString("MQZDL"):"");		//末期总电量
				
				//------以下字段直接放入bean------
				bean.setShi(shiCh);
				bean.setXian(xian);
				bean.setBzyfstart(bzyfstart);
				bean.setBzyfend(bzyfend);
				
				//------计算 单价平均 = 报账单价总和/电费单条数------
				double danjiapj = 0;
				if( dfdts != 0 && dfdts != 0.00 ){
					danjiapj = bzdjzh / dfdts;
				}
				bean.setDanjiapj(Format.str4(Double.toString(danjiapj)));
				
				//------计算 平均单价 = 总电费/总电量------
				double pingjundj = 0;
				if( zdl !=0 && zdl != 0.00 ){
					pingjundj = zdf / zdl;
				}
				bean.setPingjundj(Format.str4(Double.toString(pingjundj)));
				
				//------计算 首期单价平均 = 首期报账单价总和/首期电费单条数------
				double sqdanjiapj = 0;
				if( sqdfdts != 0 && sqdfdts != 0.00 ){
					sqdanjiapj = sqbzdjzh / sqdfdts;
				}
				//------计算 末期单价平均 = 末期报账单价总和/末期电费单条数------
				double mqdanjiapj = 0;
				if( mqdfdts != 0 && mqdfdts != 0.00 ){
					mqdanjiapj = mqbzdjzh / mqdfdts;
				}
				//------计算 单价平均趋势 = (末期单价平均-首期单价平均)/首期单价平均------
				double danjiapjqs = 0;
				if( sqdanjiapj != 0 && sqdanjiapj != 0.00 ){
					danjiapjqs = ( mqdanjiapj - sqdanjiapj ) / sqdanjiapj;
				}
				bean.setDanjiapjqs(Format.str2(Double.toString(danjiapjqs)));
				
				//------计算 首期平均单价 = 首期总电费/首期总电量------
				double sqpingjundj = 0;
				if( sqzdl !=0 && sqzdl != 0.00 ){
					sqpingjundj = sqzdf / sqzdl;
				}
				//------计算 末期平均单价 = 末期总电费/末期总电量------
				double mqpingjundj = 0;
				if( mqzdl !=0 && mqzdl != 0.00 ){
					mqpingjundj = mqzdf / mqzdl;
				}
				//------计算 平均单价趋势 = (末期平均单价-首期平均单价)/首期平均单价------
				double pingjundjqs = 0;
				if( sqpingjundj != 0 && sqpingjundj != 0.00 ){
					pingjundjqs = ( mqpingjundj - sqpingjundj ) / sqpingjundj;
				}
				bean.setPingjundjqs(Format.str2(Double.toString(pingjundjqs)));
				list.add(bean);//把每个bean放进结果集
			}
		} catch (Exception e) {
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
