package com.ptac.app.station.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.station.bean.Station;
import com.ptac.app.station.bean.StationQuery;
import com.ptac.app.util.Page;

/**
 * 局站基本信息持久层实现
 * @author guoli
 */
public class StationDaoImpl implements StationDao {

	@Override
	public int addStation(Station station) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into zhandian (");//2018/3/20 为了查询页面 显示 把 full_station_code 字段改为zdcode<<<sgn>>>
		sql.append("shi,xian,xiaoqu,jzname,longitude,latitude,kongtiao,stationtype,station_code,zdcode,rank_id,property_id,power_id,issupervisor,")
		.append("status,idc_rack_num,is_sharing_rent,ascription_unit,shared_num,shared_name,contract_type,current_num,current_type,")
		.append("account_period,branch_bureau,is_idstl_elec_apply,is_apply_idstl_elec,is_idstl_elec,is_power_direct_transaction,")
		.append("idstl_elec_top_price,idstl_elec_normal_price,idstl_elec_bottom_price,gene_other_busi_elec_price,transformer_cd,")
		.append("transformer_capacity,power_element,station_full_name,approve_status,collect_mode,station_no");
		sql.append(") values (");
		sql.append("'").append(station.getShi()).append("',");
		sql.append("'").append(station.getXian()).append("',");
		sql.append("'").append(station.getXiaoqu()).append("',");
		sql.append("'").append(station.getJzName()).append("',");
		sql.append("'").append(station.getLongitude()).append("',");
		sql.append("'").append(station.getLatitude()).append("',");
		sql.append("'").append(station.getKongtiao()).append("',");//是否有空调
		sql.append("'").append(station.getStationType()).append("',");//局站类型
		sql.append("'").append(station.getStationCode()).append("',");//局站编码
		sql.append("'").append(station.getStationFullCode()).append("',");//局站编码
		sql.append("'").append(station.getRank()).append("',");//局站等级
		sql.append("'").append(station.getProperty()).append("',");//局站产权类型
		sql.append("'").append(station.getPower()).append("',");//供电方式
		sql.append("'").append(station.getIssupervisor()).append("',");//是否有动环监控
		sql.append("'").append(station.getStatus()).append("',");//局站状态
		sql.append("'").append(station.getIDCRackNum()).append("',");//IDC机架数量
		sql.append("'").append(station.getIsSharingRent()).append("',");//是否共享外租
		sql.append("'").append(station.getAscriptionUnit()).append("',");//所归属经营单位
		sql.append("'").append(station.getSharedNum()).append("',");//共享方数量
		sql.append("'").append(station.getSharedName()).append("',");//共享方名称
		sql.append("'").append(station.getContractType()).append("',");//合同类型
		sql.append("'").append(station.getCurrentNum()).append("',");//电流数量
		sql.append("'").append(station.getCurrentType()).append("',");//电流类型
		sql.append("'").append(station.getAccountPeriod()).append("',");//账期
		sql.append("'").append(station.getBranchBureau()).append("',");//支局
		sql.append("'").append(station.getIsIdstlElecApply()).append("',");//是否满足大工业用电申请
		sql.append("'").append(station.getIsApplyIdstlElec()).append("',");//是否申请大工业用电
		sql.append("'").append(station.getIsIdstlElec()).append("',");//是否是大工业用电
		sql.append("'").append(station.getIsPowerDirectTransaction()).append("',");//是否电力直接交易
		sql.append("'").append(station.getIdstlElecTopPrice()).append("',");//大工业高峰电价
		sql.append("'").append(station.getIdstlElecNormalPrice()).append("',");//大工业平段电价
		sql.append("'").append(station.getIdstlElecBottomPrice()).append("',");//大工业低谷电价
		sql.append("'").append(station.getGeneOtherBusiElecPrice()).append("',");//一般工商及其他电价
		sql.append("'").append(station.getTransformerCd()).append("',");//变压器编号
		sql.append("'").append(station.getTransformerCapacity()).append("',");//变压器容量
		sql.append("'").append(station.getPowerElement()).append("',");//功率因素
		sql.append("'").append(station.getStationFullName()).append("',");//局站全称
		sql.append("'2',");//审核状态2未上报
		sql.append("'").append(station.getCollectMode()).append("',");//
		sql.append("'0'");//对应动环局站ID,新增局站为0
		sql.append(") ");
		System.out.println("局站信息新增ssssss " +sql.toString());
		DataBase db = new DataBase();
		int pk = 0;
		try {
			db.connectDb();
			pk = db.update(sql.toString(), new String[]{"id"});
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return pk;
	}

	@Override
	public int deleteById(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from zhandian where id=").append(id);
		DataBase db = new DataBase();
		int cnt = 0;
		try {
			db.connectDb();
			cnt = db.update(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public List<Station> findPage(StationQuery query, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select id,(select agname from d_area_grade where agcode = z.shi ) shi," +
				"(select agname from d_area_grade where agcode = z.xian ) xian," +
				 " (select a.name from account a,s_workflow w where w.auditorid = to_char(a.accountid) and w.id=(select max(w.id) from account a, s_workflow w  where  w.auditorid = to_char(a.accountid)   and w.task_id = z.id  group by w.task_id)) as accountname,"
				+"(select agname from d_area_grade where agcode = z.xiaoqu ) xiaoqu," +
				"jzname,longitude,latitude,kongtiao,(select name from indexs where code = z.stationtype) stationtype,station_code,zdcode,full_station_code,rank_id,property_id," +
				"(select name from indexs where type='GDFS' and code=z.power_id) power_id,issupervisor,")
		.append("(select name from indexs where type='STATUS_DICT' and code=z.status) status,idc_rack_num,is_sharing_rent,ascription_unit,shared_num,shared_name,contract_type,current_num,current_type,")
		.append("account_period,branch_bureau,is_idstl_elec_apply,is_apply_idstl_elec,is_idstl_elec,is_power_direct_transaction,")
		.append("idstl_elec_top_price,idstl_elec_normal_price,idstl_elec_bottom_price,gene_other_busi_elec_price,transformer_cd,")
		.append("transformer_capacity,power_element,station_full_name,approve_status from zhandian z where xiaoqu in (select t.agcode from per_area t where t.accountid='"+query.getAccountId()+"')");
/*		where xiaoqu in (select t.agcode from per_area t where t.accountid='"+query.getAccountId()+"')
*/		
		sql.append(whereSql(query));
		sql.append(" order by id desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		System.out.println("局站信息查询ssssss " +sql.toString());
		DataBase db = new DataBase();
		List<Station> list = new ArrayList<Station>();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				Station station = new Station();
				station.setId(rs.getInt("ID"));
				station.setShi(StringUtils.trimToEmpty(rs.getString("shi")));
				station.setXian(StringUtils.trimToEmpty(rs.getString("xian")));
				station.setXiaoqu(StringUtils.trimToEmpty(rs.getString("xiaoqu")));
				station.setJzName(StringUtils.trimToEmpty(rs.getString("jzname")));
				station.setLongitude(StringUtils.trimToEmpty(rs.getString("longitude")));
				station.setLatitude(StringUtils.trimToEmpty(rs.getString("latitude")));
				station.setKongtiao(StringUtils.trimToEmpty(rs.getString("kongtiao")));
				station.setStationType(StringUtils.trimToEmpty(rs.getString("stationtype")));
				station.setStationCode(StringUtils.trimToEmpty(rs.getString("station_code")));
				station.setStationFullCode(StringUtils.trimToEmpty(rs.getString("full_station_code")));
				station.setZdcode(StringUtils.trimToEmpty(rs.getString("zdcode")));
				station.setRank(StringUtils.trimToEmpty(rs.getString("rank_id")));
				station.setProperty(StringUtils.trimToEmpty(rs.getString("property_id")));
				station.setPower(StringUtils.trimToEmpty(rs.getString("power_id")));
				station.setIssupervisor(StringUtils.trimToEmpty(rs.getString("issupervisor")));
				station.setStatus(StringUtils.trimToEmpty(rs.getString("status")));
				station.setIDCRackNum(rs.getInt("idc_rack_num"));
				station.setIsSharingRent(StringUtils.trimToEmpty(rs.getString("is_sharing_rent")));
				station.setAscriptionUnit(StringUtils.trimToEmpty(rs.getString("ascription_unit")));
				station.setSharedNum(rs.getInt("shared_num"));
				station.setSharedName(StringUtils.trimToEmpty(rs.getString("shared_name")));
				station.setContractType(StringUtils.trimToEmpty(rs.getString("contract_type")));
				station.setCurrentNum(rs.getInt("current_num"));
				station.setCurrentType(StringUtils.trimToEmpty(rs.getString("current_type")));
				station.setAccountPeriod(StringUtils.trimToEmpty(rs.getString("account_period")));
				station.setBranchBureau(StringUtils.trimToEmpty(rs.getString("branch_bureau")));
				station.setIsIdstlElecApply(StringUtils.trimToEmpty(rs.getString("is_idstl_elec_apply")));
				station.setIsApplyIdstlElec(StringUtils.trimToEmpty(rs.getString("is_apply_idstl_elec")));
				station.setIsIdstlElec(StringUtils.trimToEmpty(rs.getString("is_idstl_elec")));
				station.setIsPowerDirectTransaction(StringUtils.trimToEmpty(rs.getString("is_power_direct_transaction")));
				station.setIdstlElecTopPrice(rs.getDouble("idstl_elec_top_price"));
				station.setIdstlElecNormalPrice(rs.getDouble("idstl_elec_normal_price"));
				station.setIdstlElecBottomPrice(rs.getDouble("idstl_elec_bottom_price"));
				station.setGeneOtherBusiElecPrice(rs.getDouble("gene_other_busi_elec_price"));
				station.setTransformerCd(StringUtils.trimToEmpty(rs.getString("transformer_cd")));
				station.setTransformerCapacity(rs.getDouble("transformer_capacity"));
				station.setPowerElement(StringUtils.trimToEmpty(rs.getString("power_element")));
				station.setStationFullName(StringUtils.trimToEmpty(rs.getString("station_full_name")));
				station.setApproveStatus(StringUtils.trimToEmpty(rs.getString("approve_status")));
				station.setAccountname(StringUtils.trimToEmpty(rs.getString("accountname")));
				list.add(station);
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
		return list;
	}

	
	
	@Override
	public int findTotalRow(StationQuery query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from zhandian z where xiaoqu in (select t.agcode from per_area t where t.accountid='"+query.getAccountId()+"')");
		sql.append(whereSql(query));
		DataBase db = new DataBase();
		ResultSet rs = null;
		int cnt = 0;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if(rs.next()){
				cnt = rs.getInt(1);
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
		return cnt;
	}

	@Override
	public Station getOne(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("id,shi,xian,xiaoqu,jzname,longitude,latitude,kongtiao,stationtype,station_code,zdcode,full_station_code,rank_id,property_id,power_id,issupervisor,")
		.append("status,idc_rack_num,is_sharing_rent,ascription_unit,shared_num,shared_name,contract_type,current_num,current_type,")
		.append("account_period,branch_bureau,is_idstl_elec_apply,is_apply_idstl_elec,is_idstl_elec,is_power_direct_transaction,")
		.append("idstl_elec_top_price,idstl_elec_normal_price,idstl_elec_bottom_price,gene_other_busi_elec_price,transformer_cd,")
		.append("transformer_capacity,power_element,station_full_name,approve_status");
		sql.append(" from zhandian where id = ").append(id); 
		DataBase db = new DataBase();
		ResultSet rs = null;
		Station station = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				station = new Station();
				station.setId(rs.getInt("ID"));
				station.setShi(StringUtils.trimToEmpty(rs.getString("shi")));
				station.setXian(StringUtils.trimToEmpty(rs.getString("xian")));
				station.setXiaoqu(StringUtils.trimToEmpty(rs.getString("xiaoqu")));
				station.setJzName(StringUtils.trimToEmpty(rs.getString("jzname")));
				station.setLongitude(StringUtils.trimToEmpty(rs.getString("longitude")));
				station.setLatitude(StringUtils.trimToEmpty(rs.getString("latitude")));
				station.setKongtiao(StringUtils.trimToEmpty(rs.getString("kongtiao")));
				station.setStationType(StringUtils.trimToEmpty(rs.getString("stationtype")));
				station.setStationCode(StringUtils.trimToEmpty(rs.getString("station_code")));
				station.setZdcode(StringUtils.trimToEmpty(rs.getString("zdcode")));

				station.setStationFullCode(StringUtils.trimToEmpty(rs.getString("full_station_code")));
				station.setRank(StringUtils.trimToEmpty(rs.getString("rank_id")));
				station.setProperty(StringUtils.trimToEmpty(rs.getString("property_id")));
				station.setPower(StringUtils.trimToEmpty(rs.getString("power_id")));
				station.setIssupervisor(StringUtils.trimToEmpty(rs.getString("issupervisor")));
				station.setStatus(StringUtils.trimToEmpty(rs.getString("status")));
				station.setIDCRackNum(rs.getInt("idc_rack_num"));
				station.setIsSharingRent(StringUtils.trimToEmpty(rs.getString("is_sharing_rent")));
				station.setAscriptionUnit(StringUtils.trimToEmpty(rs.getString("ascription_unit")));
				station.setSharedNum(rs.getInt("shared_num"));
				station.setSharedName(StringUtils.trimToEmpty(rs.getString("shared_name")));
				station.setContractType(StringUtils.trimToEmpty(rs.getString("contract_type")));
				station.setCurrentNum(rs.getInt("current_num"));
				station.setCurrentType(StringUtils.trimToEmpty(rs.getString("current_type")));
				station.setAccountPeriod(StringUtils.trimToEmpty(rs.getString("account_period")));
				station.setBranchBureau(StringUtils.trimToEmpty(rs.getString("branch_bureau")));
				station.setIsIdstlElecApply(StringUtils.trimToEmpty(rs.getString("is_idstl_elec_apply")));
				station.setIsApplyIdstlElec(StringUtils.trimToEmpty(rs.getString("is_apply_idstl_elec")));
				station.setIsIdstlElec(StringUtils.trimToEmpty(rs.getString("is_idstl_elec")));
				station.setIsPowerDirectTransaction(StringUtils.trimToEmpty(rs.getString("is_power_direct_transaction")));
				station.setIdstlElecTopPrice(rs.getDouble("idstl_elec_top_price"));
				station.setIdstlElecNormalPrice(rs.getDouble("idstl_elec_normal_price"));
				station.setIdstlElecBottomPrice(rs.getDouble("idstl_elec_bottom_price"));
				station.setGeneOtherBusiElecPrice(rs.getDouble("gene_other_busi_elec_price"));
				station.setTransformerCd(StringUtils.trimToEmpty(rs.getString("transformer_cd")));
				station.setTransformerCapacity(rs.getDouble("transformer_capacity"));
				station.setPowerElement(StringUtils.trimToEmpty(rs.getString("power_element")));
				station.setStationFullName(StringUtils.trimToEmpty(rs.getString("station_full_name")));
				station.setApproveStatus(StringUtils.trimToEmpty(rs.getString("approve_status")));
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
		return station;
	}

	/**
	 * 修改一个局站实体
	 */
	@Override
	public int modifyStation(Station station) {
		StringBuffer setSql = new StringBuffer();
	    if(StringUtils.isNotBlank(station.getShi())){
	    	setSql.append("shi = '").append(station.getShi()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getXian())){
	    	setSql.append("xian = '").append(station.getXian()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getXiaoqu())){
	    	setSql.append("xiaoqu = '").append(station.getXiaoqu()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getJzName())){
	    	setSql.append("jzname = '").append(station.getJzName()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getLongitude())){
	    	setSql.append("longitude = '").append(station.getLongitude()).append("',");
	    }//添加修改实体编码一项   18/03/20
	    if(StringUtils.isNotBlank(station.getStationFullCode())){
	    	setSql.append("zdcode = '").append(station.getStationFullCode()).append("',");
	    }//
	    if(StringUtils.isNotBlank(station.getLatitude())){
	    	setSql.append("latitude = '").append(station.getLatitude()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getKongtiao())){
	    	setSql.append("kongtiao = '").append(station.getKongtiao()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getStationType())){
	    	setSql.append("stationtype = '").append(station.getStationType()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getStationCode())){
	    	setSql.append("station_code = '").append(station.getStationCode()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getRank())){
	    	setSql.append("rank_id = '").append(station.getRank()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getProperty())){
	    	setSql.append("property_id = '").append(station.getProperty()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getPower())){
	    	setSql.append("power_id = '").append(station.getPower()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getIssupervisor())){
	    	setSql.append("issupervisor = '").append(station.getIssupervisor()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getStatus())){
	    	setSql.append("status = '").append(station.getStatus()).append("',");
	    }
	    if(station.getIDCRackNum() != 0){
	    	setSql.append("idc_rack_num = '").append(station.getIDCRackNum()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getIsSharingRent())){
	    	setSql.append("is_sharing_rent = '").append(station.getIsSharingRent()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getAscriptionUnit())){
	    	setSql.append("ascription_unit = '").append(station.getAscriptionUnit()).append("',");
	    }
	    if(station.getSharedNum() != 0){
	    	setSql.append("shared_num = '").append(station.getSharedNum()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getSharedName())){
	    	setSql.append("shared_name = '").append(station.getSharedName()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getContractType())){
	    	setSql.append("contract_type = '").append(station.getContractType()).append("',");
	    }
	    if(station.getCurrentNum() !=0){
	    	setSql.append("current_num = '").append(station.getCurrentNum()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getCurrentType())){
	    	setSql.append("current_type = '").append(station.getCurrentType()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getAccountPeriod())){
	    	setSql.append("account_period = '").append(station.getAccountPeriod()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getBranchBureau())){
	    	setSql.append("branch_bureau = '").append(station.getBranchBureau()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getIsIdstlElecApply())){
	    	setSql.append("is_idstl_elec_apply = '").append(station.getIsIdstlElecApply()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getIsApplyIdstlElec())){
	    	setSql.append("is_apply_idstl_elec = '").append(station.getIsApplyIdstlElec()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getIsIdstlElec())){
	    	setSql.append("is_idstl_elec = '").append(station.getIsIdstlElec()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getIsPowerDirectTransaction())){
	    	setSql.append("is_power_direct_transaction = '").append(station.getIsPowerDirectTransaction()).append("',");
	    }
	    if(station.getIdstlElecTopPrice() != 0){
	    	setSql.append("idstl_elec_top_price = '").append(station.getIdstlElecTopPrice()).append("',");
	    }
	    if(station.getIdstlElecNormalPrice()!= 0){
	    	setSql.append("idstl_elec_normal_price = '").append(station.getIdstlElecNormalPrice()).append("',");
	    }
	    if(station.getIdstlElecNormalPrice() != 0){
	    	setSql.append("idstl_elec_bottom_price = '").append(station.getIdstlElecBottomPrice()).append("',");
	    }
	    if(station.getGeneOtherBusiElecPrice() != 0){
	    	setSql.append("gene_other_busi_elec_price = '").append(station.getGeneOtherBusiElecPrice()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getTransformerCd())){
	    	setSql.append("transformer_cd = '").append(station.getTransformerCd()).append("',");
	    }
	    if(station.getTransformerCapacity() != 0){
	    	setSql.append("transformer_capacity = '").append(station.getTransformerCapacity()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getPowerElement())){
	    	setSql.append("power_element = '").append(station.getPowerElement()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getStationFullName())){
	    	setSql.append("station_full_name = '").append(station.getStationFullName()).append("',");
	    }
	    if(StringUtils.isNotBlank(station.getApproveStatus())){
	    	setSql.append("approve_status = '").append(station.getApproveStatus()).append("',");
	    }
	    StringBuffer sql = new StringBuffer();
	    int cnt = 0;
		if(setSql.length() == 0){
			return cnt;
		}
		String str = StringUtils.substringBeforeLast(setSql.toString(), ",");
		System.out.println("局站信息修改字段 " +str);
		sql.append("update zhandian set ");
		sql.append(str);
		sql.append("where id = ").append(station.getId());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			cnt = db.update(sql.toString());
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
	
	private String whereSql(StationQuery query){
		StringBuffer whereSql = new StringBuffer();
		if(query.getCollectMode() !=null && !query.getCollectMode().isEmpty()){
			whereSql.append(" and collect_mode = '").append(query.getCollectMode()).append("'");
		}
		if(query.getShi() !=null && !query.getShi().isEmpty() && !"0".equals(query.getShi())){
			whereSql.append(" and shi = '").append(query.getShi()).append("'");
		}
		if(query.getXian() !=null && !query.getXian().isEmpty() && !"0".equals(query.getXian())){
			whereSql.append(" and xian = '").append(query.getXian()).append("'");
		}
		if(query.getXiaoqu() !=null && !query.getXiaoqu().isEmpty() && !"0".equals(query.getXiaoqu())){
			whereSql.append(" and xiaoqu = '").append(query.getXiaoqu()).append("'");
		}
		if(query.getStationname() !=null && !query.getStationname().isEmpty()){
			whereSql.append(" and jzname like '%").append(query.getStationname()).append("%'");
			
		}
		if(query.getStationType() !=null && !query.getStationType().isEmpty()&& !"0".equals(query.getStationType())){
			whereSql.append(" and stationtype = '").append(query.getStationType()).append("'");
		}		
		if(query.getAscriptionUnit() !=null && !query.getAscriptionUnit().isEmpty()){
			whereSql.append(" and ascription_unit like '%").append(query.getAscriptionUnit()).append("%'");
		}
		if(query.getPower() !=null && !query.getPower().isEmpty() && !"0".equals(query.getPower())){
			whereSql.append(" and ( select name from indexs where type = 'GDFS' and code = z.power_id) = '").append(query.getPower()).append("'");
		}
		if(query.getApproveStatus() !=null && !query.getApproveStatus().isEmpty() && !"".equals(query.getApproveStatus())){
			whereSql.append(" and approve_status = '").append(query.getApproveStatus()).append("'");
		}
		return whereSql.toString();
	}
//-----2018-03-01 修改营业办公查询页面 去除xiaoqu条件，
	@Override
	public int findTotalRowBgyy(StationQuery query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from zhandian z ");
		sql.append(whereSql(query));
		DataBase db = new DataBase();
		ResultSet rs = null;
		int cnt = 0;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if(rs.next()){
				cnt = rs.getInt(1);
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
		return cnt;
	
	}

}
