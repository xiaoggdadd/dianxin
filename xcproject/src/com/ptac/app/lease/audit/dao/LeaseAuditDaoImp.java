package com.ptac.app.lease.audit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.lease.query.bean.LeaseBean;

/**
 * @author �
 * @see ���޺�ͬ���
 */
public class LeaseAuditDaoImp implements LeaseAuditDao {

	/**
	 * @author �
	 * @see ���޺�ͬ��˲�ѯ��Ϣ
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
					+ "l.LEASENUM,l.LEASENAME,l.LEASEID,l.AUDITSTATUS,l.AUDITOR,l.AUDITTIME "
					+ "from zhandian zd,dianbiao am,LEASEBARGAIN l "
					+ "where zd.id=am.zdid and am.dbid=l.DBID_FK "
					+ whereStr
					+ " "
					+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId
					+ "')";
			
			System.out.println("���޺�ͬ��˲�ѯ��"+sql);
			
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
				String auditstatus = rs.getString("AUDITSTATUS")!= null ? rs.getString("AUDITSTATUS") : "";//���״̬
				String auditor = rs.getString("AUDITOR")!= null ? rs.getString("AUDITOR") : "";//�����
				String audittime = rs.getString("AUDITTIME")!= null ? rs.getString("AUDITTIME") : "";//���ʱ�� 
				            
	            bean.setJzname(jzname);
				bean.setArea(szdq);
				bean.setStationtype(stationtype);
				bean.setLeasenum(leasenum);
				bean.setLeasename(leasename);
				bean.setLeaseid(Integer.parseInt(leaseid));
				bean.setAuditor(auditor);
				bean.setAudittime(audittime);
				if("0".equals(auditstatus)){
					bean.setAuditstatus("δ���");
				}else if("1".equals(auditstatus)){
					bean.setAuditstatus("ͨ��");
				}else if("2".equals(auditstatus)){
					bean.setAuditstatus("��ͨ��");
				}
				
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
	 * @param auditor(String) �����
	 * @param auditstatus(String) ��˱�־
	 * @param chooseIdStr1(String) ���޺�ͬid
	 * @param bz(String) msg��־
	 * @see ���޺�ͬ���
	 * @param accountId(String) �˺�id
	 * @return String �����Ϣmsg
	 */
	@Override
	public String CheckZl(String auditor, String auditstatus,
			String chooseIdStr1, String bz) {
		String msg = "���������Ϣʧ�ܣ�";
		String time = "to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = db.getConnection();

			sql.append(" UPDATE LEASEBARGAIN SET AUDITSTATUS='"
					+ auditstatus + "'," + "AUDITTIME=" + time
					+ "," + "AUDITOR='" + auditor + "'"
					+ " WHERE LEASEID IN (" + chooseIdStr1 + ")");
			
			System.out.println("�������:"+sql.toString());
			
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {
				ps = conn.prepareStatement(sql.toString());
				ps.executeUpdate();
			}
			
			if (bz.equals("1")) {
				msg = "���������Ϣͨ����";
			}else {
				msg = "���������Ϣ��ͨ����";
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(null, ps, conn);
		}
		return msg;
	}

	/**
	 * @author �
	 * @see ���޺�ͬ�����ϸ��Ϣ
	 */
	@Override
	public LeaseBean getXiangQing(String leaseid, String loginId) {
		
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
				    + "l.LEASERBANK,l.LEASERACCOUNT,rtname(l.PAYWAY) PAYWAY,(select name from indexs where code = l.paycircle and type = 'ZLFKZQ') PAYCIRCLE,l.BARGAINHANDLE "
					+ "from zhandian zd,dianbiao am,LEASEBARGAIN l "
					+ "where zd.id=am.zdid and am.dbid=l.DBID_FK and l.leaseid = '"+leaseid
					+ "' and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId
					+ "')";
			
			System.out.println("���޺�ͬ�����ϸ��Ϣ��"+sql);
			
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


}
