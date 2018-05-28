package com.ptac.app.businesshall.dao;

import java.util.List;
import java.util.Map;

import com.ptac.app.businesshall.bean.Businesshall;

public interface BusinesshallDao {

	public List<Businesshall> getPageData(int page, int pageSize, Map<String, String> map);
	
	public int getTotalCount(Map<String, String> map);
	
	public int addBusinesshall(Businesshall businesshall);
	
	public int modifyBusinesshall(Businesshall businesshall);
	
	public int deleteBusinesshallById(Integer id);
	
	public Businesshall getOne(Integer id);
}
