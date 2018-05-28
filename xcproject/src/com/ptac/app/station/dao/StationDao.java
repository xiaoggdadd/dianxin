package com.ptac.app.station.dao;

import java.util.List;

import com.ptac.app.station.bean.Station;
import com.ptac.app.station.bean.StationQuery;
import com.ptac.app.util.Page;
/**
 * ��վ������Ϣ�־ò�
 */
public interface StationDao {

	public int addStation(Station station);

	public int deleteById(Integer id);

	public int modifyStation(Station station);

	public Station getOne(Integer id);

	public List<Station> findPage(StationQuery query, Page page);

	public int findTotalRow(StationQuery query);
	public int findTotalRowBgyy(StationQuery query);
}
