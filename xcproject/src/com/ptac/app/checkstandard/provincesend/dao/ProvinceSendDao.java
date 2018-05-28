package com.ptac.app.checkstandard.provincesend.dao;

import java.util.List;

import com.ptac.app.checkstandard.provincesend.bean.DetailBean;
import com.ptac.app.checkstandard.provincesend.bean.ProvinceSendBean;

/** ��ʵ���-ʡ�����¼��ɵ�dao
 * @author WangYiXiao 2015-2-28
 *
 */
public interface ProvinceSendDao {
	/**
	 * @param shi
	 * @param xian
	 * @param zdsx
	 * @param sjzt ʡ��״̬
	 * @param bl1
	 * @param bl2
	 * @param zdname
	 * @param zdid
	 * @return
	 */
	List<ProvinceSendBean>selectProvinceSend(String wherestr1,String wherestr2,String accountid);
	/**��ȡ������վ��ɷ�����
	 * @param zdid
	 * @return
	 */
	List<DetailBean> getDetail(String zdid);
	/**
	 * @param bgid CHECKSTANDARD id
	 * @param personnal ʡ���ɵ���
	 * @return
	 */
	String paiDan(String[] bgid,String personnal);

}
