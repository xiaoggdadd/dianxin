package com.ptac.app.checksign.manElectricCheck.dao;

public interface CountryElecDao {
	
	public String CheckCityFees(String personnal,String manualauditstatus,String chooseIdStr1,String chooseIdStr2,String bz,String manpassmemo);

	/**
	 * @author lijing
	 * @see 济宁人工审核通过、不通过、取消通过方法
	 * @param personnal
	 * @param manualauditstatus
	 * @param chooseIdStr1
	 * @param chooseIdStr2
	 * @param bz
	 * @param manpassmemo
	 * @param cause
	 * @return
	 */
	public String CheckCityFees1(String personnal,String manualauditstatus,String chooseIdStr1,String chooseIdStr2,String bz,String manpassmemo, String cause);

}
