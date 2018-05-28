package com.ptac.app.checkstandard.countyverify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.checkstandard.countyverify.bean.CountyVerifyBean;

/**
 * @author lijing
 * @see 区县签收及核实信息实现层
 */
public class CountyVerifyDaoImp implements CountyVerifyDao {

	/**
	 * @see 查询区县签收及核实信息
	 * @return list
	 */
	@Override
	public List<CountyVerifyBean> queryCountyVerify(String string, String loginId, String beginbl, String endbl) {
		
		List<CountyVerifyBean> list = new ArrayList<CountyVerifyBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT AA.SHI,AA.JZNAME,AA.SCBNEW,AA.JYSCB,AA.ZLFHNEW,AA.JLFHNEW,AA.EDHDLNEW,"
				 + " AA.PROPERTY,AA.XIAN,AA.XIAOQU,AA.COUNTYSIGN,AA.BL,AA.ZDID,AA.ID"
				 + " FROM (SELECT RNDIQU(Z.SHI) SHI,Z.JZNAME,C.SCBNEW,C.JYSCB,C.ZLFHNEW,C.JLFHNEW,C.EDHDLNEW,"
				 + " RTNAME(Z.PROPERTY) PROPERTY,RNDIQU(Z.XIAN) XIAN,RNDIQU(Z.XIAOQU) XIAOQU,"
				 + " C.COUNTYSIGN,((C.SCBNEW-C.JYSCB)/C.JYSCB*100) BL,C.ZDID,C.ID"
				 + " FROM ZHANDIAN Z,CHECKSTANDARD C WHERE Z.ID = C.ZDID "
				 + string
				 + " AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				 + loginId + "'))) AA WHERE 1=1 "+ (beginbl==""?"":(" AND BL >"+beginbl))+ (endbl==""?"":(" AND BL <"+endbl)));
		
		System.out.println("区县签收及核实信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CountyVerifyBean bean = new CountyVerifyBean();
				String city = rs.getString(1) != null ? rs.getString(1) : "";//市
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//站点名称
				String scb = rs.getString(3) != null ? rs.getString(3) : "";//生产标(度)
				String jyscb = rs.getString(4) != null ? rs.getString(4) : "";//建议生产标(度)
				String zlfh = rs.getString(5) != null ? rs.getString(5) : "";//直流负荷(度)
				String jlfh = rs.getString(6) != null ? rs.getString(6) : "";//交流负荷(度)
				String bdb = rs.getString(7) != null ? rs.getString(7) : "";//本地标(度)
				String zdsx = rs.getString(8) != null ? rs.getString(8) : "";//站点属性
				String xian = rs.getString(9) != null ? rs.getString(9) : "";//区县
				String xiaoqu = rs.getString(10) != null ? rs.getString(10) : "";//乡镇
				String state = rs.getString(11) != null ? rs.getString(11) : "";//状态
				String xczb = rs.getString(12) != null ? rs.getString(12) : "0";//相差比例
				String zdid = rs.getString(13) != null ? rs.getString(13) : "";//站点ID
				String id = rs.getString(14) != null ? rs.getString(14) : "";
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setScb(scb);
				bean.setJyscb(jyscb);
				bean.setZlfh(zlfh);
				bean.setJlfh(jlfh);
				bean.setBdb(bdb);
				bean.setZdsx(zdsx);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setState(state);
				DecimalFormat df = new DecimalFormat("0.00");
				xczb = df.format(Double.parseDouble(xczb));
				bean.setXczb(xczb);
				bean.setZdid(zdid);
				bean.setId(id);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * @see 区县未签收查询
	 * @param loginId
	 * @return
	 */
	public synchronized int Check(String loginId){
		 
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i =0;
		
		String sql =" select c.id from zhandian z,CHECKSTANDARD c where z.id = c.zdid and c.CITYPD='1' and c.COUNTYSIGN='0' and (z.xiaoqu in (select t.agcode from per_area t where t.accountid='"+loginId+"'))";
		System.out.println("区县未签收："+sql);
		
		try{
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				i=1;
			}
		}catch(Exception e){
			 e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		 return i;
	}
	
	/**
	 * @see 区县签收修改
	 * @param loginId
	 * @param loginName
	 * @return
	 */
	public synchronized int Update(String loginId,String loginName){
		 	
		int msg=1;
		StringBuffer sql = new StringBuffer();
		sql.append("update CHECKSTANDARD c set c.COUNTYSIGN = '1',c.COUNTYSIGNPERSON='"+loginName+"',c.COUNTYSIGNTIME=sysdate where c.id in (select c.id from zhandian z, CHECKSTANDARD c where z.id = c.zdid and c.CITYPD='1' and c.COUNTYSIGN='0' and (z.xiaoqu in (select t.agcode from per_area t where t.accountid ='"+loginId+"')))");
		DataBase db = new DataBase();
		System.out.println("区县签收修改："+sql.toString());
		try {	
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 0;
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

	/**
	 * @see 区县退单
	 * @return int
	 */
	@Override
	public int TDUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update CHECKSTANDARD c set c.COUNTYSIGN='2',c.COUNTYSIGNPERSON='"+loginName+"',c.COUNTYSIGNTIME=sysdate where c.id in ("+id+")";
		
		System.out.println("区县退单："+sql.toString());
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

	/**
	 * @see 区县核实信息查询
	 * @param zdid
	 * @return
	 */
	@Override
	public List<CountyVerifyBean> getInfo(String zdid) {
		
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		List<CountyVerifyBean> list = new ArrayList<CountyVerifyBean>();
		db = new DataBase();
		
		String sql = " SELECT ZD.JZNAME,to_char(AM.LASTDATETIME,'yyyy-mm-dd') LASTTIME,to_char( AM.THISDATETIME,'yyyy-mm-dd') THISTIME,AM.LASTDEGREE,AM.THISDEGREE," 
				   + " DB.BEILV,ROUND(AM.DBYDL,2) DBYDL,ROUND(EF.ACTUALPAY,2),ROUND(AM.ACTUALDEGREE,2) ACTUALDEGREE,to_char( EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,EF.CITYZRAUDITSTATUS" 
			       + " FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK"
			       + " AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND ZD.ID = '"+zdid+"'"
			       + " ORDER BY ACCOUNTMONTH DESC";
		System.out.println("区县核实信息查询："+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				CountyVerifyBean os = new CountyVerifyBean();
				String zdname = rs.getString(1) != null ? rs.getString(1) : "";//站点名称
				String lasttime = rs.getString(2) != null ? rs.getString(2) : "";//上次抄表时间
				String thistime = rs.getString(3) != null ? rs.getString(3) : "";//本次抄表时间
				String lastdegree = rs.getString(4) != null ? rs.getString(4) : "";//上次电表读数
				String thisdegree = rs.getString(5) != null ? rs.getString(5) : "";//本次电表读数
				String beilv = rs.getString(6) != null ? rs.getString(6) : "";//倍率
				String dbydl = rs.getString(7) != null ? rs.getString(7) : "";//电表用电量
				String bzdf = rs.getString(8) != null ? rs.getString(8) : "";//报账金额
				String bzdl = rs.getString(9) != null ? rs.getString(9) : "";//报账电量
				String accountmonth = rs.getString(10) != null ? rs.getString(10) : "";//报账月份
				String citystate = rs.getString(11) != null ? rs.getString(11) : "";//市级主任审核状态
				
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
				if("0".equals(citystate)){
					os.setCitystate("未审核");
				}else if("1".equals(citystate)){
					os.setCitystate("审核通过");
				}else if("2".equals(citystate)){
					os.setCitystate("不通过");
				}
				list.add(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

	/**
	 * @see 区县核实信息保存
	 * @return
	 */
	@Override
	public String commit(CountyVerifyBean bean, String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

}
