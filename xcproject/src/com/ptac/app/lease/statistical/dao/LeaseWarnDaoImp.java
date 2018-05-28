package com.ptac.app.lease.statistical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.lease.statistical.bean.LeaseStatBean;

/**
 * @author 李靖
 * @see 租赁费缴费预警实现层（查询、导出）
 */
public class LeaseWarnDaoImp implements LeaseWarnDao {

	/**
	 * @author 李靖
	 * @see 租赁费缴费预警查询信息
	 */
	@Override
	public ArrayList queryZlyjMsg(String whereStr, String loginId, String jlts) {
		
		DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList list = new ArrayList();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if("".equals(jlts) || jlts == null){
			jlts = "0";
		}
		Double jlts1 = Format.str_d(jlts); 
		
		try {
			
			sql = "select * from (SELECT DISTINCT (LB.LEASEID),RNDIQU(ZD.SHI) SHI,rndiqu(zd.xian) xian,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = LB.PAYCIRCLE AND TYPE = 'ZLFKZQ') PAYCIRCLE,"
				+ " ZD.JZNAME, LB.LEASENAME, LB.BEGINTIME,LB.ENDTIME, LB.MONEY,"
			    + "DECODE(LL.AA, NULL, 0, LL.AA) totalPayMoney, DECODE(LB.MONEY - LL.AA,NULL,LB.MONEY,LB.MONEY - LL.AA) noPayMoney,"
			    + "DECODE(LL.BB, NULL, 0, LL.BB) count,LL.CC,"
			    + " DECODE(LL.CC,NULL,LB.BEGINTIME, TO_CHAR(ADD_MONTHS(TO_DATE(LL.CC, 'yyyy-MM-dd'),(SELECT IE.NAME FROM INDEXS IE"
			    + " WHERE IE.CODE = LB.PAYCIRCLE AND IE.TYPE = 'ZLFKZQ')),'yyyy-MM-dd')) xcjfsj,"
			    + " TO_DATE(DECODE(TO_CHAR(ADD_MONTHS(TO_DATE(LL.CC,'yyyy-MM-dd'),(SELECT IE.NAME FROM INDEXS IE  WHERE IE.CODE = LB.PAYCIRCLE"
			    + " AND IE.TYPE = 'ZLFKZQ')), 'yyyy-MM-dd'), NULL, LB.BEGINTIME, TO_CHAR(ADD_MONTHS(TO_DATE(LL.CC, 'yyyy-MM-dd'),"
			    + " (SELECT IE.NAME FROM INDEXS IE WHERE IE.CODE = LB.PAYCIRCLE AND IE.TYPE = 'ZLFKZQ')),'yyyy-MM-dd')),'yyyy-MM-dd') -"
			    + " to_date(to_char(SYSDATE, 'yyyy-MM-dd'), 'yyyy-MM-dd') jfts" 
			    + " FROM ZHANDIAN ZD, DIANBIAO DB, LEASEBARGAIN LB,LEASEBARGAINFEES LF,(SELECT LFF.LEASEID_FK,SUM(LFF.PAYMONEY) AA,"
			    + " COUNT(LFF.LEASEID_FK) BB,MAX(LFF.Payendtime) CC FROM LEASEBARGAINFEES LFF WHERE LFF.CITYAUDITSTATUS = '1'"
			    + " GROUP BY LFF.LEASEID_FK) LL WHERE ZD.ID = DB.ZDID AND DB.DBID = LB.DBID_FK AND LB.LEASEID = LL.LEASEID_FK(+)"
			    + "AND LF.CITYAUDITSTATUS = '1' AND ZD.SHSIGN = '1' AND ZD.QYZT = '1' AND DB.DBQYZT = '1' "
				+ whereStr
				+ " "
				+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"+ loginId+ "')"
			    + " AND ZD.SHI = '13701') ddddd  where ddddd.jfts < "+jlts1;

			System.out.println("租赁费缴费预警查询信息："+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				LeaseStatBean bean = new LeaseStatBean();
				
				String shi = rs.getString("SHI")!= null ? rs.getString("SHI") : "";
				String xian = rs.getString("xian")!= null ? rs.getString("xian") : "";
				String jzname = rs.getString("JZNAME")!= null ? rs.getString("JZNAME") : "";
				String leasename = rs.getString("LEASENAME")!= null ? rs.getString("LEASENAME") : "";
				String begintime = rs.getString("BEGINTIME")!= null ? rs.getString("BEGINTIME") : "";
				String endtime = rs.getString("ENDTIME")!= null ? rs.getString("ENDTIME") : "";
				String money = rs.getString("MONEY")!= null ? rs.getString("MONEY") : "";
				String latesttime = rs.getString("CC")!= null ? rs.getString("CC") : "";//最近一次缴费时间
				String paycircle = rs.getString("PAYCIRCLE")!= null ? rs.getString("PAYCIRCLE") : "";//周期
				String totalPayMoney = rs.getString("totalPayMoney")!= null ? rs.getString("totalPayMoney") : "";//已缴总金额
				String noPayMoney = rs.getString("noPayMoney")!= null ? rs.getString("noPayMoney") : "";//未缴金额
				String count = rs.getString("count")!= null ? rs.getString("count") : "";//缴费次数
				String xcjfsj = rs.getString("xcjfsj")!= null ? rs.getString("xcjfsj") : "";//下次缴费时间
				String jfts = rs.getString("jfts")!= null ? rs.getString("jfts") : "";//距缴费天数
				            
				bean.setCity(shi);
				bean.setXian(xian);
	            bean.setJzname(jzname);
				bean.setLeasename(leasename);
				bean.setEndtime(endtime);
				bean.setMoney(Format.str_d(money));
				if("".equals(latesttime)||latesttime==null){
					bean.setLatesttime("");
				}else{
					bean.setLatesttime(latesttime);
				}
				bean.setBegintime(begintime);
				bean.setPaycircle(paycircle);
				bean.setTotalPayMoney(Format.str_d(totalPayMoney));
				bean.setNoPayMoney(Format.str_d(noPayMoney));
				bean.setCount(Integer.parseInt(count));
				bean.setXcjfsj(xcjfsj);
				bean.setJfts(Long.parseLong(jfts));

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
