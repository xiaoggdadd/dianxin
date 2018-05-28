package com.ptac.app.accounting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

/**
 * 核算单元设置
 * @author Administrator
 *
 */
public class AccountingDao {

	/**
	 * 
	 * @param costCode  成本中心
	 * @param businessType 业务类型
	 * @param elecProperty 用电属性
	 * @param dutyCode 预算责任中心
	 * @param projectCode 预算项目
	 * @param isRealSubAmmeter 是否实际分电表
	 * @param shareRatio 分摊系数
	 * @return
	 */
	 public synchronized int addAccounting(String[] costCode, String[] businessType, String[] elecProperty, String[] dutyCode ,String[] projectCode,String[] isRealSubAmmeter,String[] shareRatio, String dbcode ){
		 int cnt = 0;
		  ArrayList<String> sqlList = new ArrayList<String>();
		  StringBuffer sql = new StringBuffer();
		 for (int i = 0; i < costCode.length; i++) {
			 sql.setLength(0);
			 sql.append("insert into Accounting_Unit_info (COST_CODE,BUSINESS_TYPE,ELEC_PROPERTY,DUTY_CODE,PROJECT_CODE,IS_REAL_sub_AMMETER,SHARE_RATIO, DBCODE) values ('"+costCode[i]+"'," +
					"'"+StringUtils.trimToEmpty(businessType[i])+"'," +
					"'"+StringUtils.trimToEmpty(elecProperty[i])+"'," +
					"'"+StringUtils.trimToEmpty(dutyCode[i])+"'," +
					"'"+StringUtils.trimToEmpty(projectCode[i])+"'," +
					"'"+StringUtils.trimToEmpty(isRealSubAmmeter[i])+"'," +
					"'"+StringUtils.trimToEmpty(shareRatio[i])+"'," +
							"'"+dbcode+"')");
			 sqlList.add(sql.toString());
		}
		 DataBase db = new DataBase();
		 System.out.println("核算单元新增   ---"+ sqlList);
		 try {
			String[] sqlArr = sqlList.toArray(new String[sqlList.size()]);
			db.connectDb();
			db.updateBatch(sqlArr);
			cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		 return cnt;
	 }
	 
	 /**
	  * 根据电表删除核算单元信息
	  * @param dbcode
	  * @return
	  */
	 public synchronized int deleteAccounting(String dbcode){
		 String sql = "delete from Accounting_Unit_info where dbcode='"+dbcode+"'";
		 DataBase db = new DataBase();
		 int cnt = 0;
		 try {
			db.connectDb();
			cnt = db.update(sql);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	 }
	 
	 /**
	  * 根据电表编号查询成本中心
	  * @param dbcode
	  * @return
	  */
	 public synchronized ArrayList getAccounting(String dbcode){
		 String sql = "select cost_code,(select c.name from ring_cost_center c where c.code = cost_code) cost_name,business_type,elec_property,duty_code,(select d.name from ring_budget_duty_center d where d.code = duty_code) duty_name,project_code,is_real_sub_ammeter,to_char(share_ratio,'fm9999999990.00') share_ratio, dbcode " +
		 		"from Accounting_Unit_info where dbcode='"+dbcode+"'";
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		 ArrayList list = new ArrayList();
		 try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	 }
	 
	 /**
	  * 预算责任中心
	  * @param dutyCode
	  * @return
	  */
	 public synchronized  ArrayList getDutyCenterByCode(String costCode){
		 String sql = "select code, name from Ring_Budget_Duty_center " +
		 		"where code in(select dutyCode from Ring_cost_center where code='"+costCode+"')";
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		 ArrayList list = new ArrayList();
		 try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	 }

	 /**
	  * 成本中心信息
	  * @param 公司主体编号
	  * @return
	  */
	 public synchronized  ArrayList getCostCenterBygszt(String gszt){
		 gszt = gszt.toUpperCase();
		 String sql = "select code, name, dutycode from Ring_Cost_center " +
		 "where code like substr('"+gszt+"',0,1)||'%'";
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		 ArrayList list = new ArrayList();
		 try {
			 db.connectDb();
			 rs = db.queryAll(sql);
			 Query query = new Query();
			 list = query.query(rs);
		 } catch (DbException e) {
			 e.printStackTrace();
		 }finally{
			 try {
				 db.close();
			 } catch (DbException e) {
				 e.printStackTrace();
			 }
		 }
		 return list;
	 }

	 /**
	  * 成本中心级联预算责任中心 
	  * @param costCode 成本中心编码
	  * @return
	  */
	 public synchronized  String getDutyCenter_cbzx(String costCode){
		 StringBuffer list = new StringBuffer();
		 list.append("<response>");
		 String sql = "select code, name from Ring_Budget_Duty_center " +
		 "where code in(select dutyCode from Ring_cost_center where code='"+costCode+"')";
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		 try {
			 db.connectDb();
			 rs = db.queryAll(sql);
			 while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 try {
				 db.close();
			 } catch (DbException e) {
				 e.printStackTrace();
			 }
		 }
		 return list.toString();
	 }
	 
	 /**
	  * 公司主体级联成本中心
	  * @param 公司主体编号
	  * @return
	  */
	 public synchronized  String getCostCenter_gszt(String gszt){
		 StringBuffer list = new StringBuffer();
		 list.append("<response>");
		 gszt = gszt.toUpperCase();
		 String sql = "select code, name, dutycode from Ring_Cost_center " +
		 "where code like substr('"+gszt+"',0,1)||'%'";
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		 try {
			 db.connectDb();
			 rs = db.queryAll(sql);
			 while(rs.next()){
				 list.append("<res>" + rs.getString(1) + "</res>");
			     list.append("<res>" + rs.getString(2) + "</res>");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 try {
				 db.close();
			 } catch (DbException e) {
				 e.printStackTrace();
			 }
		 }
		 return list.toString();
	 }
	 
	 /**
	  * 根据电表中成本中心编码
	  * 批量插入核算单元   -（电表ID，成本中心编码，责任中心编码）
	  * @return
	  */
	 public synchronized int addAccountingUnitSelCostCenter(){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into accounting_unit_info (dbcode,cost_code,duty_code,ELEC_PROPERTY,BUSINESS_TYPE,PROJECT_CODE,IS_REAL_SUB_AMMETER,SHARE_RATIO) " +
				"select d.id, d.cbzxbm, r.dutycode,d.ydsx,(case when substr(r.code, 0, 1)='A' then 'dianbiao_ywlx_1 ' else 'dianbiao_ywlx_2' end) ywlx,'CW1000','0',1 " +
				" from dianbiao d " +
				"left join ring_cost_center r on d.cbzxbm = r.code " +
				"where d.cbzxbm is not null " +
				"and (select count(*) from accounting_unit_info a where a.dbcode = d.id and a.cost_code = r.code) = 0");
		System.out.println("!!!"+sql.toString());
		DataBase db = new DataBase();
		int cnt = 0;
		try {
			db.connectDb();
			System.out.println("!!!"+sql.toString());
			cnt = db.update(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	 }
	 /**
      * 
      * @param dbcode
      * @return
      */
     public synchronized String getAccountingByDbcode(String dbcode){
      
         String strs = "";
         DataBase db = new DataBase();
         ResultSet rs = null;
         ArrayList list = new ArrayList();
         String fzzdstr="";
         try {
             System.out.println("负责站点"+fzzdstr);
             db.connectDb();
             
             
             String sql = "select rc.name NAME,to_char(aui.SHARE_RATIO,'fm9999999990.00') SHARE_RATIO from Accounting_Unit_info aui,RING_COST_CENTER rc where aui.COST_CODE=rc.code and dbcode='"+dbcode+"'";
         
             System.out.println("根据ID查询报账sql："+sql);
             
               rs=db.queryAll(sql.toString());
               Query query = new Query();
               list = query.query2(rs);
               rs.close();
               if(list!=null){
                   for(int i=0;(i<list.size()-1);i+=2){
                     
                       if(i!=0){
                           strs = strs + "@_@";
                       }
                       strs = strs + list.get(i);
                       strs = strs + "#_#" + list.get(i+1);
                   }
               }
         }

         catch (DbException de) {
             de.printStackTrace();
         } catch (SQLException de) {
             de.printStackTrace();
         }

         finally {
             if (rs != null) {
                 try {
                     rs.close();
                 } catch (SQLException se) {
                     se.printStackTrace();
                 }
             }
             try {
                 db.close();
             } catch (DbException de) {
                 de.printStackTrace();
             }

         }

         return strs;
     }
	 
}
