package com.ptac.app.checksign.manElectricCheck.dao;

public interface CountryHeadActDao {
	public String CheckCityFees(String personnal,String countryheadstatus,String chooseIdStr1,String chooseIdStr2,String bz);

	/**
	 * @author lijing
	 * @see ���������������ͨ������ͨ����ȡ��ͨ������
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
