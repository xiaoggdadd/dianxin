package com.ptac.app.datastatistics.monitoringshare.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.ptac.app.datastatistics.monitoringshare.bean.MonShareBean;

/**
 * @author 李靖
 * @see 监测点分摊实现层
 */
public class MonShareDaoImp implements MonShareDao {

	@Override
	public ArrayList<MonShareBean> queryExport(String whereStr, String loginId) {
		
		ArrayList<MonShareBean> list = new ArrayList<MonShareBean>();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta  = null;
		ResultSet rs = null;
	 
		sql.append("SELECT RNDIQU(ZD.SHI),RNDIQU(ZD.XIAN),RNDIQU(ZD.XIAOQU),ZD.JZNAME,RTNAME(ZD.PROPERTY),RTNAME(ZD.STATIONTYPE),"
				 + "ZD.ID,D.DBID,(SELECT QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.SHUOSHUZHUANYE) SHUOSHUZHUANYE,"
				 + "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.QCBCODE) QCBCODE,"
				 + "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.KJKMCODE) KJKMCODE,"
				 + "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.ZYMXCODE) ZYMXCODE,S.DBILI,S.XJBILI"
				 + " FROM ZHANDIAN ZD, DIANBIAO D, SBGL S"
				 + " WHERE ZD.ID = D.ZDID AND D.DBID = S.DIANBIAOID"
				 + whereStr
				 + " AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND ZD.SHSIGN = '1' AND D.DBYT = 'dbyt01'");
		
		System.out.println("监测点分摊:"+sql);
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			
			while(rs.next()){
				MonShareBean bean = new MonShareBean();
				
				String shi = rs.getString(1)!=null?rs.getString(1):"";//市
				String xian = rs.getString(2)!=null?rs.getString(2):"";//区县	
				String xiaoqu = rs.getString(3)!=null?rs.getString(3):"";//乡镇	
				String zdname = rs.getString(4)!=null?rs.getString(4):"";//站点名称
				String zdsx = rs.getString(5)!=null?rs.getString(5):"";//站点属性
				String zdlx = rs.getString(6)!=null?rs.getString(6):"";//站点类型	
				String id = rs.getString(7)!=null?rs.getString(7):"";//站点ID
				String dbid = rs.getString(8)!=null?rs.getString(8):"";//电表ID
				String sszy = rs.getString(9)!=null?rs.getString(9):"";//所属专业
				String qcb = rs.getString(10)!=null?rs.getString(10):"";//全成本
				String kjkm = rs.getString(11)!=null?rs.getString(11):"";//会计科目
				String zymx = rs.getString(12)!=null?rs.getString(12):"";//专业明细
				String yjbl = rs.getString(13)!=null?rs.getString(13):"";//一级比例
				String ejbl = rs.getString(14)!=null?rs.getString(14):"";//二级比例
				
				bean.setShi(shi);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setZdname(zdname);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setId(id);
				bean.setDbid(dbid);
				bean.setSszy(sszy);
				bean.setQcb(qcb);
				bean.setKjkm(kjkm);
				bean.setZymx(zymx);
				bean.setYjbl(yjbl);
				bean.setEjbl(ejbl);
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, sta, conn);
		}
		return list;
	}

}
