package com.ptac.app.priceover.provincesend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.priceover.provincesend.bean.ProvSendBean;

/**
 * @author lijing
 * @see ���۳���ʡ�ɵ�ʵ�ֲ�
 */
public class ProvSendDaoImp implements ProvSendDao {

	@Override
	public List<ProvSendBean> queryExport(String string, String bl) {
		
		List<ProvSendBean> list = new ArrayList<ProvSendBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(SHI),JZNAME,PLD,BZPRICE,SJPRICE,ACTUALPAY,BLHDL,RTNAME(PROPERTY),"
				 + "(SELECT NAME FROM INDEXS WHERE CODE = STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				 + " rtname(GDFS),ZDID,RNDIQU(XIAN),RNDIQU(XIAOQU),PROVINCEPD,ID,TO_CHAR(ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH"
				 + " FROM PRICEPROCEDURE WHERE 1=1 AND CITYSIGN='0' "
				 + string
				 + (bl==""?"":(" AND PLD >="+bl)));
		
		System.out.println("ʡ�ɵ���Ϣ��ѯ��"+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ProvSendBean bean = new ProvSendBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//��
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//վ������
				String pld = rs.getString(3) != null ? rs.getString(3) : "";//ƫ���
				String bzdj = rs.getString(4) != null ? rs.getString(4) : "";//��׼����
				String sjdj = rs.getString(5) != null ? rs.getString(5) : "";//ʵ�ʵ���
				String bzdf = rs.getString(6) != null ? rs.getString(6) : "";//���˵��
				String bzdl = rs.getString(7) != null ? rs.getString(7) : "";//���˵���
				String zdsx = rs.getString(8) != null ? rs.getString(8) : "";//վ������
				String zdlx = rs.getString(9) != null ? rs.getString(9) : "";//վ������
				String gdfs = rs.getString(10) != null ? rs.getString(10) : "";//���緽ʽ
				String zdid = rs.getString(11) != null ? rs.getString(11) : "";//վ��ID
				String xian = rs.getString(12) != null ? rs.getString(12) : "";//����
				String xiaoqu = rs.getString(13) != null ? rs.getString(13) : "";//����
				String state = rs.getString(14) != null ? rs.getString(14) : "";//ʡ�ɵ�״̬
				String id = rs.getString(15) != null ? rs.getString(15) : "";
				String accountmonth = rs.getString(16) != null ? rs.getString(16) : "";
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setPld(pld);
				bean.setBzdj(bzdj);
				bean.setSjdj(sjdj);
				bean.setBzdf(bzdf);
				bean.setBzdl(bzdl);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setGdfs(gdfs);
				bean.setZdid(zdid);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				if("0".equals(state)){
					bean.setState("δ�ɵ�");
				}else if("1".equals(state)){
					bean.setState("���ɵ�");
				}
				bean.setId(id);
				bean.setAccountmonth(accountmonth);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public int pdUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update PRICEPROCEDURE set PROVINCEPD='1',PROVINCEPERSON='"+loginName+"',PROVINCETIME=sysdate where ID in ("+id+")";
		System.out.println("ʡ�ɵ���"+sql.toString());
		DataBase db = new DataBase();
		try {	
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return msg;
	}

	@Override
	public List<ProvSendBean> getInfo(String zdid,String bzyf) {
		
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		List<ProvSendBean> list = new ArrayList<ProvSendBean>();
		db = new DataBase();
		
		String sql = " SELECT ZD.JZNAME,to_char(AM.LASTDATETIME,'yyyy-mm-dd') LASTTIME,to_char( AM.THISDATETIME,'yyyy-mm-dd') THISTIME,AM.LASTDEGREE,AM.THISDEGREE," 
				   + " DB.BEILV,ROUND(AM.DBYDL,2) DBYDL,EF.ACTUALPAY,ROUND(AM.BLHDL,2) BLHDL,to_char( EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH" 
			       + " FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK"
			       + " AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND EF.MANUALAUDITSTATUS = '2' AND EF.ACTUALPAY>0 AND BLHDL>0 AND ZD.ID = '"+zdid+"'"
			       + (bzyf==""?"":(" AND to_char( EF.ACCOUNTMONTH,'yyyy-mm') = '"+bzyf+"'"))+" ORDER BY ACCOUNTMONTH DESC";
		
		System.out.println("ʡ�ɵ���ϸ��Ϣ��ѯ��"+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				ProvSendBean os = new ProvSendBean();
				String zdname = rs.getString(1) != null ? rs.getString(1) : "";//վ������
				String lasttime = rs.getString(2) != null ? rs.getString(2) : "";//�ϴγ���ʱ��
				String thistime = rs.getString(3) != null ? rs.getString(3) : "";//���γ���ʱ��
				String lastdegree = rs.getString(4) != null ? rs.getString(4) : "";//�ϴε�����
				String thisdegree = rs.getString(5) != null ? rs.getString(5) : "";//���ε�����
				String beilv = rs.getString(6) != null ? rs.getString(6) : "";//����
				String dbydl = rs.getString(7) != null ? rs.getString(7) : "";//����õ���
				String bzdf = rs.getString(8) != null ? rs.getString(8) : "";//���˽��
				String bzdl = rs.getString(9) != null ? rs.getString(9) : "";//���˵���
				String accountmonth = rs.getString(10) != null ? rs.getString(10) : "";//�����·�
				
				os.setZdname(zdname);
				os.setLasttime(lasttime);
				os.setThistime(thistime);
				os.setLastdegree(lastdegree);
				os.setThisdegree(thisdegree);
				os.setBeilv(beilv);
				os.setDbydl(dbydl);
				os.setBzdf(bzdf);
				os.setBzdl(bzdl);
				os.setAccountmonth(accountmonth);
				
				list.add(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

}
