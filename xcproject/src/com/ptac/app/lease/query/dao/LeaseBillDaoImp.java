package com.ptac.app.lease.query.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.lease.query.bean.LeaseBean;

/**
 * @author �
 * @see ���޺�ͬά�����������޸ġ�ɾ����
 */
public class LeaseBillDaoImp implements LeaseBillDao {

	/**
	 * @author lijing
	 * @see վ�����Ƶ�ģ����ѯ
	 * @param loginId String Ȩ��,jzname String ����ģ����ѯ,str String ��������
	 * @return list ����һ��վ�����Ƶ�list
	 */
	@Override
	public ArrayList checkMh(String loginId, String jzname, String str) {

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT JZNAME,d.dbid,d.dbname,z.zdcode FROM ZHANDIAN Z, DIANBIAO D WHERE "
				+ "Z.ID = D.ZDID AND Z.SHSIGN = '1' AND Z.QYZT='1' AND Z.XUNI = '0' AND Z.CAIJI = '0' AND D.DBYT = 'dbyt01'  AND D.DBQYZT='1' "
				+ str
				+ "AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"
				+ loginId + "')");
		if (!"������".equals(jzname)) {
				sql.append(" AND JZNAME LIKE '%" + jzname + "%'");
		}
		sql.append(" ORDER BY JZNAME");
		System.out.println("ģ����ѯվ�����ƣ�"+sql);
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	/**
	 * @author lijing
	 * @see ���޺�ͬά������ҳ�������Ϣ�Ĳ�ѯ
	 * @param dbid String ��������
	 * @return bean ����һ��LeaseBean��bean
	 */
	@Override
	public LeaseBean bas(String dbid) {
		
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LeaseBean bean = new LeaseBean();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT (SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.SHI) ||"
						+ "(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.XIAN) ||"
						+ "(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.XIAOQU) AREA,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') STATIONTYPE,"
						+ "ZD.JZNAME,ZD.ADDRESS,ZD.AREA MJ"
						+ " FROM DIANBIAO D,ZHANDIAN ZD"
						+ " WHERE d.zdid=zd.id and d.dbid = '"
						+ dbid
						+ "' and d.dbyt='dbyt01'");

		System.out.println("������Ϣ:"+sql);

		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				bean.setArea(rs.getString("AREA") != null ? rs.getString("AREA"):"");
				bean.setStationtype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE"):"");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME"): "");
				bean.setAddress(rs.getString("ADDRESS") != null ?  rs.getString("ADDRESS"):"");
				bean.setMj(rs.getString("MJ") != null ?  rs.getString("MJ"):"");
			}
			
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			db.free(rs, ps, conn);
		}
		return bean;
	}

	/**
	 * @author lijing
	 * @see ���޺�ͬά��������Ϣ
	 */
	@Override
	public String addZlht(String dbid, LeaseBean lb) {

		String msg = "�������޺�ͬ��ʧ�ܣ������Ի������Ա��ϵ��";
		
		String sql = "INSERT INTO LEASEBARGAIN(DBID_FK,LEASENUM,LEASENAME,BEGINTIME,ENDTIME,MONEY,AGELIMIT,"
				   + "LEASERNAME,LEASERPHONE,LEASERBANK,LEASERACCOUNT,PAYWAY,PAYCIRCLE,BARGAINHANDLE,AUDITSTATUS)"
				   + " VALUES('"
				   + dbid+ "','"+lb.getLeasenum()+ "','"+lb.getLeasename()+ "','"+ lb.getBegintime()+ "','"
				   + lb.getEndtime()+ "',"+ lb.getMoney()+ ","+ lb.getAgelimit()+ ",'"+ lb.getLeasername()
				   + "','"+ lb.getLeaserphone()+ "','"+lb.getLeaserbank()+ "','"+lb.getLeaseraccount()+ "','"
				   + lb.getPayway()+ "','"+lb.getPaycircle()+"','"+lb.getBargainhandler()+"','0')";

		System.out.println("�������޺�ͬ����"+sql);
		
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		   db.connectDb();
		   conn = db.getConnection();
		   ps = conn.prepareStatement(sql.toString());
		   rs =  ps.executeQuery();
		   msg = "�������޺�ͬ���ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(rs, ps, null);
		}
		return msg;
	}

	/**
	 * @author lijing
	 * @see ���޺�ͬά����ѯ��Ϣ
	 */
	@Override
	public ArrayList queryZlMsg(String whereStr, String loginId) {
		
		ArrayList list = new ArrayList();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			sql = "select zd.jzname,(decode(zd.xian,null,'',(select distinct agname from d_area_grade where agcode = zd.xian))||"
					+ "decode(zd.xiaoqu,null,'',(select distinct agname from d_area_grade where agcode = zd.xiaoqu))) as szdq,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "l.LEASENUM,l.LEASENAME,l.LEASEID "
					+ "from zhandian zd,dianbiao am,LEASEBARGAIN l "
					+ "where zd.id=am.zdid and am.dbid=l.DBID_FK and (l.AUDITSTATUS='0' or l.AUDITSTATUS='2') "
					+ whereStr
					+ " "
					+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId
					+ "')";
			
			System.out.println("���޺�ͬά����ѯ��"+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				LeaseBean bean = new LeaseBean();
				
				String jzname = rs.getString("JZNAME")!= null ? rs.getString("JZNAME") : "";
				String szdq = rs.getString("SZDQ")!= null ? rs.getString("SZDQ") : "";
				String stationtype = rs.getString("STATIONTYPE")!= null ? rs.getString("STATIONTYPE") : "";
				String leasenum = rs.getString("LEASENUM")!= null ? rs.getString("LEASENUM") : "";//���޺�ͬ���
				String leasename = rs.getString("LEASENAME")!= null ? rs.getString("LEASENAME") : "";//���޺�ͬ����
				String leaseid = rs.getString("LEASEID")!= null ? rs.getString("LEASEID") : "";//���޺�ͬid
				            
	            bean.setJzname(jzname);
				bean.setArea(szdq);
				bean.setStationtype(stationtype);
				bean.setLeasenum(leasenum);
				bean.setLeasename(leasename);
				bean.setLeaseid(Integer.parseInt(leaseid));
				
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
	 * @author lijing
	 * @see ���޺�ͬά��ɾ��������Ϣ
	 */
	@Override
	public String deleteZl(String leaseid) {
		String msg = "ɾ�����޺�ͬ����Ϣʧ�ܣ�";
		DataBase db = new DataBase();

		try {
			db.connectDb();
            String sql = "delete from LEASEBARGAIN where leaseid='"+leaseid+"'";
			System.out.println("ɾ���������޺�ͬ����"+sql);
			db.update(sql);
			msg = "ɾ�����޺�ͬ����Ϣ�ɹ���";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	/**
	 * @author lijing
	 * @see ��ѯ���޺�ͬά���޸�ҳ�����Ϣ
	 */
	@Override
	public LeaseBean getZl(String leaseid,String loginId) {
		
		ArrayList list = new ArrayList();
		LeaseBean bean = new LeaseBean();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			sql = "select zd.jzname,(decode(zd.xian,null,'',(select distinct agname from d_area_grade where agcode = zd.xian))||"
					+ "decode(zd.xiaoqu,null,'',(select distinct agname from d_area_grade where agcode = zd.xiaoqu))) as szdq,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,zd.ADDRESS,"
					+ "zd.AREA MJ,l.DBID_FK,l.LEASENUM,l.LEASENAME,l.BEGINTIME,l.ENDTIME,l.MONEY,l.AGELIMIT,l.LEASERNAME,l.LEASERPHONE,"
				    + "l.LEASERBANK,l.LEASERACCOUNT,l.PAYWAY,l.PAYCIRCLE,l.BARGAINHANDLE "
					+ "from zhandian zd,dianbiao am,LEASEBARGAIN l "
					+ "where zd.id=am.zdid and am.dbid=l.DBID_FK and l.leaseid = '"+leaseid
					+ "' and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId
					+ "')";
			
			System.out.println("���޺�ͬά���޸�ҳ���ѯ��"+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				String jzname = rs.getString("JZNAME")!= null ? rs.getString("JZNAME") : "";
				String szdq = rs.getString("SZDQ")!= null ? rs.getString("SZDQ") : "";
				String stationtype = rs.getString("STATIONTYPE")!= null ? rs.getString("STATIONTYPE") : "";
				String address = rs.getString("ADDRESS")!= null ? rs.getString("ADDRESS") : "";
				String mj = rs.getString("MJ")!= null ? rs.getString("MJ") : "";
				String dbid = rs.getString("DBID_FK")!= null ? rs.getString("DBID_FK") : "";
				String leasenum = rs.getString("LEASENUM")!= null ? rs.getString("LEASENUM") : "";
				String leasename = rs.getString("LEASENAME")!= null ? rs.getString("LEASENAME") : "";
				String begintime = rs.getString("BEGINTIME")!= null ? rs.getString("BEGINTIME") : "";
				String endtime = rs.getString("ENDTIME")!= null ? rs.getString("ENDTIME") : "";
				String money = rs.getString("MONEY")!= null ? rs.getString("MONEY") : "";
				String agelimit = rs.getString("AGELIMIT")!= null ? rs.getString("AGELIMIT") : "";
				String leasername = rs.getString("LEASERNAME")!= null ? rs.getString("LEASERNAME") : "";
				String leaserphone = rs.getString("LEASERPHONE")!= null ? rs.getString("LEASERPHONE") : "";
				String leaserbank = rs.getString("LEASERBANK")!= null ? rs.getString("LEASERBANK") : "";
				String leaseraccount = rs.getString("LEASERACCOUNT")!= null ? rs.getString("LEASERACCOUNT") : "";
				String payway = rs.getString("PAYWAY")!= null ? rs.getString("PAYWAY") : "";
				String paycircle = rs.getString("PAYCIRCLE")!= null ? rs.getString("PAYCIRCLE") : "";
				String bargainhandler = rs.getString("BARGAINHANDLE")!= null ? rs.getString("BARGAINHANDLE") : "";
				            
	            bean.setJzname(jzname);
				bean.setArea(szdq);
				bean.setStationtype(stationtype);
				bean.setAddress(address);
				bean.setMj(mj);
				bean.setDbid_fk(dbid);
				bean.setLeasenum(leasenum);
				bean.setLeasename(leasename);
				bean.setBegintime(begintime);
				bean.setEndtime(endtime);
				bean.setMoney(Format.str_d(money));
				bean.setAgelimit(Format.str_d(agelimit));
				bean.setLeasername(leasername);
				bean.setLeaserphone(leaserphone);
				bean.setLeaserbank(leaserbank);
				bean.setLeaseraccount(leaseraccount);
				bean.setPayway(payway);
				bean.setPaycircle(paycircle);
				bean.setBargainhandler(bargainhandler);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return bean;
	}

	/**
	 * @author lijing
	 * @see �޸����޺�ͬά����Ϣ
	 */
	@Override
	public String addModify(String leaseid, String dbid, LeaseBean lb) {

		String msg = "�޸����޺�ͬ��ʧ�ܣ������Ի������Ա��ϵ��";
		
		String sql = "UPDATE LEASEBARGAIN SET LEASENUM=?,LEASENAME=?,BEGINTIME=?,ENDTIME=?,MONEY=?,AGELIMIT=?,"
				   + "LEASERNAME=?,LEASERPHONE=?,LEASERBANK=?,LEASERACCOUNT=?,PAYWAY=?,PAYCIRCLE=?,BARGAINHANDLE=? "
				   + "WHERE LEASEID = "+leaseid;
				  
		System.out.println("�޸����޺�ͬ����"+sql);
		
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
		   db.connectDb();
		   conn = db.getConnection();
		   ps = conn.prepareStatement(sql.toString());
		   
		   ps.setString(1, lb.getLeasenum());
		   ps.setString(2, lb.getLeasename());
		   ps.setString(3, lb.getBegintime());
		   ps.setString(4, lb.getEndtime());
		   ps.setDouble(5, lb.getMoney());
		   ps.setDouble(6, lb.getAgelimit());
		   ps.setString(7, lb.getLeasername());
		   ps.setString(8, lb.getLeaserphone());
		   ps.setString(9, lb.getLeaserbank());
		   ps.setString(10, lb.getLeaseraccount());
		   ps.setString(11, lb.getPayway());
		   ps.setString(12, lb.getPaycircle());
		   ps.setString(13, lb.getBargainhandler());
		   
		   int count = 0;
		   count = ps.executeUpdate();
		   if(count == 1){
			   msg = "�޸����޺�ͬ���ɹ���";
		   }
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(null, ps, conn);
		}
		return msg;
	}
}
