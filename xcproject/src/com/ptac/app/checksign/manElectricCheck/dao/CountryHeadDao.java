package com.ptac.app.checksign.manElectricCheck.dao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import com.ptac.app.checksign.manElectricCheck.bean.CountryHeadBean;
import com.ptac.app.electricmanage.electricitybill.bean.Share;

public interface CountryHeadDao {

	public List<CountryHeadBean> queryInfo(String whereStr, String cityaudit, String loginId,String whereStr1);
	public List<CountryHeadBean> dfMoreInfo(String dbid, String biaozhi);
	public List<CountryHeadBean> yffMoreInfo(String dbid, String biaozhi);
	
	/**
	 * @author lijing
	 * @see 济宁电费审核调整申请详细信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public CountryHeadBean xiangxi(String whereStr);
	
	/**
	 * @author lijing
	 * @param bean 调整申请信息 填写内容
	 * @param share 分摊信息
	 * @see 保存济宁电费审核调整申请信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public String save(CountryHeadBean bean, Share share);
	
	/**
	 * @author lijing
	 * @see 济宁电费审核调整申请详细信息(分摊)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public Share ftInfo(String dlid, String electricfeeId);
}
