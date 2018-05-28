package com.ptac.app.inter.service;

import java.util.List;

import com.ptac.app.inter.bean.KdgcElectrictypeBean;
import com.ptac.app.inter.bean.KdgcZhandianBean;
import com.ptac.app.inter.bean.SjInterFace;

public interface KdgcInterfaceDao {
	public List<KdgcZhandianBean> getZhandianInterface();

	public List<KdgcElectrictypeBean> getElectrictypeInterface(String bzTime);
	
	public SjInterFace getCountKd(String entertime);
}
 