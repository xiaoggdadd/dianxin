package com.ptac.app.systemconfiguration.zdcountconfig.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.systemconfiguration.zdcountconfig.bean.zdCountConfigBean;

public class zdCountConfigurationDaoImpl implements zdCountConfigurationDao {
	private Logger logger = Logger.getLogger(zdCountConfigurationDaoImpl.class);
	private DataBase db;
	private Connection connc;
	private Statement st;
	private ResultSet rs;

	@Override
	public List<zdCountConfigBean> getPageData() {
		
		List<zdCountConfigBean> list = new ArrayList<zdCountConfigBean>();
		String sql = " SELECT T.ITEMID,RNDIQU(T.ITEMNAME) ITEMNAME,T.ITEMVALUE,T.ITEMVALUE2,T.ITEMDESCRIPTION FROM PERMISSION_CONFIGURATION T WHERE ITEMLLAG = 4 ORDER BY ITEMID";
		db = new DataBase();
		logger.info("查询站点数量配置信息:"+sql.toString());
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				zdCountConfigBean zcb = new zdCountConfigBean();
				zcb.setItemid(rs.getString("ITEMID"));
				zcb.setItemname(rs.getString("ITEMNAME"));
				zcb.setItemvalue(rs.getString("ITEMVALUE"));
				zcb.setItemvalue2(rs.getString("ITEMVALUE2"));
				zcb.setItemdescription(rs.getString("ITEMDESCRIPTION"));
				list.add(zcb);
			}
		} catch (Exception ee){
			logger.error("查询站点数量配置信息失败:"+ee.getMessage());
			ee.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

	@Override
	public String modifyAutoAudit(zdCountConfigBean formBean){
		String msg = "修改站点数量配置信息失败！";
		int a= 0;
        StringBuffer sql = new StringBuffer("UPDATE PERMISSION_CONFIGURATION SET ITEMVALUE='")
        	.append(formBean.getItemvalue()).append("',ITEMVALUE2='")
        	.append(formBean.getItemvalue2()).append("' WHERE ITEMID='").append(formBean.getItemid()).append("'");
        db = new DataBase();
        logger.info("修改站点数量配置信息:"+sql.toString());
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			a = st.executeUpdate(sql.toString());
			msg = a>0?"修改站点数量配置信息成功！":msg;
		} catch (Exception e) {
			logger.error("修改站点数量配置信息失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(null, st, connc);
		}  
	      return msg;
	}


}
