package com.ptac.app.lease.query.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.lease.query.bean.Leasebargainfees;

/**×âÁÞ·ÑÓÃdao
 * @author WangYiXIao 2014-9-15 
 *
 */
public interface LeaseFeesDao {
	 @SuppressWarnings("unchecked")
	ArrayList getZhandianAndHetong(String loginId, String jzname, String str);
	Leasebargainfees getBaseInfo(String zdid,String leaseid);
	String addLeaseFees(Leasebargainfees leasefees);
	String getLastPayendtime(String leaseid_fk);
	List<Leasebargainfees> selectLeaseFees(String whereStr,String loginId);
	String delLeaseFee(String leasefeeid);
	Leasebargainfees getLeaseFeeInfo(String leasefeeid);
	String updateLeaseFees(Leasebargainfees leasefees);

}
