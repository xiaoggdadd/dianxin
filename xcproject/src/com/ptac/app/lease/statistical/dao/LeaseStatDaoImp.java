package com.ptac.app.lease.statistical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.lease.statistical.bean.LeaseStatBean;


/**
 * @author 李靖
 * @see 租赁费统计（查询、导出）
 */
public class LeaseStatDaoImp implements LeaseStatDao {

	/**
	 * @author 李靖
	 * @see 租赁费统计查询信息
	 */
	@Override
	public ArrayList queryZlfMsg(String whereStr, String loginId) {
		
		ArrayList list = new ArrayList();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			sql = "SELECT DISTINCT (LB.LEASEID), RNDIQU(ZD.SHI) || RNDIQU(ZD.XIAN) || RNDIQU(ZD.XIAOQU) SZDQ,"
					+ "rndiqu(zd.xian) xian,ZD.JZNAME,LB.LEASENAME,LB.BEGINTIME,LB.ENDTIME,LB.MONEY,DECODE(LL.AA, NULL, 0, LL.AA) totalPayMoney,"
					+ "DECODE(LB.MONEY - LL.AA, NULL, LB.MONEY, LB.MONEY - LL.AA) noPayMoney,"
					+ "LL.CC,DECODE(LL.BB, NULL, 0, LL.BB) COUNT "
					+ "FROM ZHANDIAN ZD,DIANBIAO DB,LEASEBARGAIN LB,LEASEBARGAINFEES LF,"
					+ "(SELECT LFF.LEASEID_FK,SUM(LFF.PAYMONEY) AA,COUNT(LFF.LEASEID_FK) BB,MAX(LFF.PAYENDTIME) CC "
					+ "FROM LEASEBARGAINFEES LFF WHERE LFF.CITYAUDITSTATUS = '1' GROUP BY LFF.LEASEID_FK) LL "
					+ "WHERE ZD.ID = DB.ZDID AND DB.DBID = LB.DBID_FK AND LB.LEASEID = LL.LEASEID_FK(+) "
					+ "AND LF.CITYAUDITSTATUS = '1' AND ZD.SHSIGN = '1' AND ZD.QYZT = '1' AND DB.DBQYZT = '1' "
					+ whereStr
					+ " "
					+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"+ loginId+ "')";
			
			System.out.println("租赁费统计查询信息："+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				LeaseStatBean bean = new LeaseStatBean();
				
				String area = rs.getString("SZDQ")!= null ? rs.getString("SZDQ") : "";
				String xian = rs.getString("xian")!= null ? rs.getString("xian") : "";
				String jzname = rs.getString("JZNAME")!= null ? rs.getString("JZNAME") : "";
				String leasename = rs.getString("LEASENAME")!= null ? rs.getString("LEASENAME") : "";
				String begintime = rs.getString("BEGINTIME")!= null ? rs.getString("BEGINTIME") : "";
				String endtime = rs.getString("ENDTIME")!= null ? rs.getString("ENDTIME") : "";
				String money = rs.getString("MONEY")!= null ? rs.getString("MONEY") : "";
				String totalPayMoney = rs.getString("totalPayMoney")!= null ? rs.getString("totalPayMoney") : "";//已缴总金额
				String noPayMoney = rs.getString("noPayMoney")!= null ? rs.getString("noPayMoney") : "";//未缴金额
				String latesttime = rs.getString("CC")!= null ? rs.getString("CC") : "";
				String count = rs.getString("COUNT")!= null ? rs.getString("COUNT") : "";
				            
				bean.setArea(area);
				bean.setXian(xian);
	            bean.setJzname(jzname);
				bean.setLeasename(leasename);
				bean.setBegintime(begintime);
				bean.setEndtime(endtime);
				bean.setMoney(Format.str_d(money));
				bean.setTotalPayMoney(Format.str_d(totalPayMoney));
				bean.setNoPayMoney(Format.str_d(noPayMoney));
				bean.setLatesttime(latesttime);
				bean.setCount(Integer.parseInt((count)));
				
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
