package com.ptac.app.calibstat.compared.dao;

import java.util.List;
import com.ptac.app.calibstat.compared.bean.ComparedBean;

/**
 * @author �
 * @see ������������������ԱȲ�ѯ�ӿڲ�
 */
public interface ComparedDao {

	List<ComparedBean> queryExport(String str, String loginId);

}
