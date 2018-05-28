package com.ptac.app.checksign.manElectricCheck.dao;

public interface CountryHeadActDao {
	public String CheckCityFees(String personnal,String countryheadstatus,String chooseIdStr1,String chooseIdStr2,String bz);

	/**
	 * @author lijing
	 * @see 济宁区县主任审核通过、不通过、取消通过方法
	 * @param personnal
	 * @param countryheadstatus
	 * @param chooseIdStr1
	 * @param chooseIdStr2
	 * @param string
	 * @param cause
	 * @return
	 */
	public String CheckCityFees1(String personnal, String countryheadstatus,
			String chooseIdStr1, String chooseIdStr2, String bz, String cause);

}
