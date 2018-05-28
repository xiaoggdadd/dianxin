package com.ptac.app.businesshall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.ptac.app.businesshall.bean.Businesshall;
/**
 * 营业厅持久层
 * @author Administrator
 *
 */
public class BusinesshallDaoImpl implements BusinesshallDao {

	/**
	 * 营业厅实体列表查询带分页
	 */
	@Override
	public List<Businesshall> getPageData(int page, int pageSize, Map<String, String> map) {
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Businesshall> businesshalls = new ArrayList<Businesshall>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from ( select a.*, rownum rn from (");
			sql.append("select id, company, branch_company, entity_code, entity_name, entity_type, entity_small_type, address, ")
				.append("practical_use, ownership, right_of_use, manage_department, person_liable, built_area, building_structure, ")
				.append("is_attached_entity, base_station, status, organization, certificate_number, house_value, shared_organization, ")
				.append("number_of_employees, electric_current, creater, peripheral_system_code, shared_property, environment, ")
				.append("approve_status, create_date from business_hall where 1=1");
			sql.append(whereSql(map));
			sql.append(" order by id desc");
			sql.append(") a where rownum <= ").append(page*pageSize).append(" ) where rn >").append((page-1)*pageSize);
			System.out.println("办公、营业厅分页查询  "+sql.toString());
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				Businesshall businesshall = new Businesshall();
				businesshall.setId(rs.getInt("id"));
				businesshall.setCompany(StringUtils.trimToEmpty(rs.getString("company")));
				businesshall.setBranchCompany(StringUtils.trimToEmpty(rs.getString("branch_company")));
				businesshall.setEntityCode(StringUtils.trimToEmpty(rs.getString("entity_code")));
				businesshall.setEntityName(StringUtils.trimToEmpty(rs.getString("entity_name")));
				businesshall.setEntityType(StringUtils.trimToEmpty(rs.getString("entity_type")));
				businesshall.setEntitySmallType(StringUtils.trimToEmpty(rs.getString("entity_small_type")));
				businesshall.setAddress(StringUtils.trimToEmpty(rs.getString("address")));
				businesshall.setPracticalUse(StringUtils.trimToEmpty(rs.getString("practical_use")));
				businesshall.setRightOfUse(StringUtils.trimToEmpty(rs.getString("right_of_use")));
				businesshall.setManageDepartment(StringUtils.trimToEmpty(rs.getString("manage_department")));
				businesshall.setPersonLiable(StringUtils.trimToEmpty(rs.getString("person_liable")));
				businesshall.setBuiltArea(rs.getDouble("built_area"));
				businesshall.setBuildingStructure(StringUtils.trimToEmpty(rs.getString("building_structure")));
				businesshall.setAttachedEntity(rs.getShort("is_attached_entity"));
				businesshall.setBaseStation(StringUtils.trimToEmpty(rs.getString("base_Station")));
				businesshall.setStatus(rs.getShort("status"));
				businesshall.setOrganization(StringUtils.trimToEmpty(rs.getString("organization")));
				businesshall.setCertificateNumber(StringUtils.trimToEmpty(rs.getString("certificate_number")));
				businesshall.setHouseValue(StringUtils.trimToEmpty(rs.getString("house_value")));
				businesshall.setSharedOrganization(StringUtils.trimToEmpty(rs.getString("shared_organization")));
				businesshall.setNumberOfEmployees(rs.getInt("number_of_employees"));
				businesshall.setElectricCurrent(StringUtils.trimToEmpty(rs.getString("electric_current")));
				businesshall.setCreater(StringUtils.trimToEmpty(rs.getString("creater")));
				businesshall.setPeripheralSystemCode(StringUtils.trimToEmpty(rs.getString("peripheral_system_code")));
				businesshall.setSharedProperty(StringUtils.trimToEmpty(rs.getString("shared_property")));
				businesshall.setEnvironment(StringUtils.trimToEmpty(rs.getString("environment")));
				businesshall.setApproveStatus(rs.getShort("approve_status"));
				businesshall.setCreateDate(rs.getDate("create_date"));
				
				//...
				businesshalls.add(businesshall);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return businesshalls;
	}

	/**
	 * 新增一个实体
	 */
	@Override
	public int addBusinesshall(Businesshall businesshall) {
		DataBase db = new DataBase();
		int cnt = 0;
		try {
			db.connectDb();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into BUSINESS_HALL(company, " +
					"branch_company, entity_code, entity_name, entity_type, entity_small_type, address, " +
					"practical_use, ownership, right_of_use, manage_department, person_liable, built_area, " +
					"building_structure, is_attached_entity, base_station, status, organization, certificate_number, " +
					"house_value, shared_organization, number_of_employees, electric_current, creater, " +
					"peripheral_system_code, shared_property, environment, approve_status, " +
					"create_date)");
			sql.append("values(");
			sql.append("'").append(businesshall.getCompany()).append("',");
			sql.append("'").append(businesshall.getBranchCompany()).append("',");
			sql.append("'").append(businesshall.getEntityCode()).append("',");
			sql.append("'").append(businesshall.getEntityName()).append("',");
			sql.append("'").append(businesshall.getEntityType()).append("',");
			sql.append("'").append(businesshall.getEntitySmallType()).append("',");
			sql.append("'").append(businesshall.getAddress()).append("',");
			sql.append("'").append(businesshall.getPracticalUse()).append("',");
			sql.append("'").append(businesshall.getOwnership()).append("',");
			sql.append("'").append(businesshall.getRightOfUse()).append("',");
			sql.append("'").append(businesshall.getManageDepartment()).append("',");
			sql.append("'").append(businesshall.getPersonLiable()).append("',");
			sql.append(businesshall.getBuiltArea()).append(",");
			sql.append("'").append(businesshall.getBuildingStructure()).append("',");
			sql.append(businesshall.isAttachedEntity()).append(",");
			sql.append("'").append(businesshall.getBaseStation()).append("',");
			sql.append(businesshall.getStatus()).append(",");
			sql.append("'").append(businesshall.getOrganization()).append("',");
			sql.append("'").append(businesshall.getCertificateNumber()).append("',");
			sql.append("'").append(businesshall.getHouseValue()).append("',");
			sql.append("'").append(businesshall.getSharedOrganization()).append("',");
			sql.append(businesshall.getNumberOfEmployees()).append(",");
			sql.append("'").append(businesshall.getElectricCurrent()).append("',");
			sql.append("'").append(businesshall.getCreater()).append("',");
			sql.append("'").append(businesshall.getPeripheralSystemCode()).append("',");
			sql.append("'").append(businesshall.getSharedProperty()).append("',");
			sql.append("'").append(businesshall.getEnvironment()).append("',");
			sql.append(businesshall.getApproveStatus()).append(",");
			sql.append("to_date('").append(CTime.formatWholeDate()).append("','yyyy-MM-dd hh24:mi:ss')");
			sql.append(")");
			System.out.println("新增营业厅实体 "+sql.toString());
			cnt = db.update(sql.toString());
		} catch (DbException e) {
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
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
	 * 根据主键ID删除一个实体
	 */
	@Override
	public int deleteBusinesshallById(Integer id) {
		DataBase db = new DataBase();
		int cnt = 0;
		try {
			StringBuffer sql = new StringBuffer();
			db.connectDb();
			sql.append("delete from business_hall where id=").append(id);
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

	/**
	 * 修改一个实体
	 */
	@Override
	public int modifyBusinesshall(Businesshall businesshall) {
		DataBase db = new DataBase();
		int cnt = 0;
		/**
		 * company	branch_company	entity_code	entity_name	entity_type	entity_small_type	
		 * address	practical_use	ownership	right_of_use	manage_department	person_liable	
		 * built_area	building_structure	is_attached_entity	base_station	status	organization	
		 * certificate_number	house_value	shared_organization	number_of_employees	electric_current	
		 * creater	peripheral_system_code	shared_property	environment	approve_status
		 */
		try {
			db.connectDb();
			StringBuffer sql = new StringBuffer();
			sql.append("update business_hall set company='").append(businesshall.getCompany()).append("'");
			sql.append(",branch_company='").append(businesshall.getBranchCompany()).append("'");
			sql.append(",entity_name='").append(businesshall.getEntityName()).append("'");
			sql.append(",entity_type='").append(businesshall.getEntityType()).append("'");
			sql.append(",entity_small_type='").append(businesshall.getEntitySmallType()).append("'");
			sql.append(",address='").append(businesshall.getAddress()).append("'");
			sql.append(",practical_use='").append(businesshall.getPracticalUse()).append("'");
			sql.append(",ownership='").append(businesshall.getOwnership()).append("'");
			sql.append(",right_of_use='").append(businesshall.getRightOfUse()).append("'");
			sql.append(",manage_department='").append(businesshall.getManageDepartment()).append("'");
			sql.append(",person_liable='").append(businesshall.getPersonLiable()).append("'");
			sql.append(",built_area=").append(businesshall.getBuiltArea());
			sql.append(",building_structure='").append(businesshall.getBuildingStructure()).append("'");
			sql.append(",is_attached_entity=").append(businesshall.isAttachedEntity());
			sql.append(",base_station='").append(businesshall.getBaseStation()).append("'");
			sql.append(",status=").append(businesshall.getStatus());
			sql.append(",organization='").append(businesshall.getOrganization()).append("'");
			sql.append(",certificate_number='").append(businesshall.getCertificateNumber()).append("'");
			sql.append(",house_value='").append(businesshall.getHouseValue()).append("'");
			sql.append(",shared_organization='").append(businesshall.getSharedOrganization()).append("'");
			sql.append(",number_of_employees=").append(businesshall.getNumberOfEmployees());
			sql.append(",electric_current='").append(businesshall.getElectricCurrent()).append("'");
			sql.append(",creater='").append(businesshall.getCreater()).append("'");
			sql.append(",peripheral_system_code='").append(businesshall.getPeripheralSystemCode()).append("'");
			sql.append(",shared_property='").append(businesshall.getSharedProperty()).append("'");
			sql.append(",environment='").append(businesshall.getEnvironment()).append("'");
			sql.append(",approve_status=").append(businesshall.getApproveStatus());
			sql.append("where id=").append(businesshall.getId());
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

	/**
	 * 获取总行数
	 */
	@Override
	public int getTotalCount(Map<String, String> map) {
		DataBase db = new DataBase();
		ResultSet rs = null;
		int cnt = 0;
		try {
			db.connectDb();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from business_hall where 1=1");
			sql.append(whereSql(map));
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (DbException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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

	/**
	 * 构建列表查询条件
	 * @param map 参数
	 * @return
	 */
	private String whereSql(Map<String, String> map) {
		StringBuffer whereSql = new StringBuffer();
		if (map != null) {
			String entityName = map.get("entityName");
			if (entityName != null && !entityName.isEmpty()) {
				whereSql.append(" and entity_name like '%").append(entityName).append("%'");
			}
			String entityCode = map.get("entityCode");
			if (entityCode != null && !entityCode.isEmpty()) {
				whereSql.append(" and entity_code like '%").append(entityCode).append("%'");
			}
			String company = map.get("company");
			if (company != null && !company.isEmpty()) {
				whereSql.append(" and company like '%").append(company).append("%'");
			}
			String personLiable = map.get("personLiable");
			if (personLiable != null && !personLiable.isEmpty()) {
				whereSql.append(" and person_liable like '%").append(personLiable).append("%'");
			}
			String entityType = map.get("entityType");
			if (entityType != null && !entityType.isEmpty()) {
				whereSql.append(" and entity_type = '").append(entityType).append("'");
			}
			String peripheralSystemCode = map.get("peripheralSystemCode");
			if (peripheralSystemCode != null && !peripheralSystemCode.isEmpty()) {
				whereSql.append(" and peripheral_system_code like '%").append(peripheralSystemCode).append("%'");
			}
			String status = map.get("status");
			if (status != null && !status.isEmpty()) {
				whereSql.append(" and status = '").append(status).append("'");
			}
		}
		return whereSql.toString();
	}

	/**
	 * 根据主键ID查询一个实体
	 */
	@Override
	public Businesshall getOne(Integer id) {
		DataBase db = new DataBase();
		ResultSet rs = null;
		Businesshall businesshall = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select id, company, branch_company, entity_code, entity_name, entity_type, entity_small_type, address, ")
				.append("practical_use, ownership, right_of_use, manage_department, person_liable, built_area, building_structure, ")
				.append("is_attached_entity, base_station, status, organization, certificate_number, house_value, shared_organization, ")
				.append("number_of_employees, electric_current, creater, peripheral_system_code, shared_property, environment, ")
				.append("approve_status, create_date from business_hall where id = ")
				.append(id);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				businesshall = new Businesshall();
				businesshall.setId(rs.getInt("id"));
				businesshall.setCompany(StringUtils.trimToEmpty(rs.getString("company")));
				businesshall.setBranchCompany(StringUtils.trimToEmpty(rs.getString("branch_company")));
				businesshall.setEntityCode(StringUtils.trimToEmpty(rs.getString("entity_code")));
				businesshall.setEntityName(StringUtils.trimToEmpty(rs.getString("entity_name")));
				businesshall.setEntityType(StringUtils.trimToEmpty(rs.getString("entity_type")));
				businesshall.setEntitySmallType(StringUtils.trimToEmpty(rs.getString("entity_small_type")));
				businesshall.setAddress(StringUtils.trimToEmpty(rs.getString("address")));
				businesshall.setPracticalUse(StringUtils.trimToEmpty(rs.getString("practical_use")));
				businesshall.setRightOfUse(StringUtils.trimToEmpty(rs.getString("right_of_use")));
				businesshall.setManageDepartment(StringUtils.trimToEmpty(rs.getString("manage_department")));
				businesshall.setPersonLiable(StringUtils.trimToEmpty(rs.getString("person_liable")));
				businesshall.setBuiltArea(rs.getDouble("built_area"));
				businesshall.setBuildingStructure(StringUtils.trimToEmpty(rs.getString("building_structure")));
				businesshall.setAttachedEntity(rs.getShort("is_attached_entity"));
				businesshall.setBaseStation(StringUtils.trimToEmpty(rs.getString("base_Station")));
				businesshall.setStatus(rs.getShort("status"));
				businesshall.setOrganization(StringUtils.trimToEmpty(rs.getString("organization")));
				businesshall.setCertificateNumber(StringUtils.trimToEmpty(rs.getString("certificate_number")));
				businesshall.setHouseValue(StringUtils.trimToEmpty(rs.getString("house_value")));
				businesshall.setSharedOrganization(StringUtils.trimToEmpty(rs.getString("shared_organization")));
				businesshall.setNumberOfEmployees(rs.getInt("number_of_employees"));
				businesshall.setElectricCurrent(StringUtils.trimToEmpty(rs.getString("electric_current")));
				businesshall.setCreater(StringUtils.trimToEmpty(rs.getString("creater")));
				businesshall.setPeripheralSystemCode(StringUtils.trimToEmpty(rs.getString("peripheral_system_code")));
				businesshall.setSharedProperty(StringUtils.trimToEmpty(rs.getString("shared_property")));
				businesshall.setEnvironment(StringUtils.trimToEmpty(rs.getString("environment")));
				businesshall.setApproveStatus(rs.getShort("approve_status"));
				businesshall.setCreateDate(rs.getDate("create_date"));
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
		return businesshall;
	}
}
