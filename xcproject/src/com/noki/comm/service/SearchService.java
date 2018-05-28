package com.noki.comm.service;

import java.util.List;
import java.util.UUID;

import com.noki.comm.bean.SeachItemBean;
import com.noki.comm.dao.SearchDAO;


public class SearchService {

	public  List toGetDate(String tablename) {
		SearchDAO searchdao = new SearchDAO();
		List dateList = searchdao.toGetDate(tablename);
		return dateList;
	}
	public void insertData(SeachItemBean seachBean){
		SearchDAO searchdao = new SearchDAO();
		searchdao.insertData(seachBean);
	}
	public List<SeachItemBean> seachData(SeachItemBean seachBean) {
		SearchDAO searchdao = new SearchDAO();
		List<SeachItemBean> rebean =searchdao.seachData(seachBean);
		return rebean;
	}
	public void  searchdel(SeachItemBean seachBean){
		SearchDAO searchdao = new SearchDAO();
		searchdao.searchdel(seachBean);
	}
}
