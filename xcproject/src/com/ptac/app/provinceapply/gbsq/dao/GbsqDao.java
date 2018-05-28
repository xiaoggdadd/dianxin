package com.ptac.app.provinceapply.gbsq.dao;

import java.util.List;

import com.ptac.app.provinceapply.gbsq.bean.GbsqBaseBean;
import com.ptac.app.provinceapply.gbsq.bean.GbsqBean;

/**
 * @author lijing
 * @see 省申请改标审核接口层
 */
public interface GbsqDao {

	/**查询列表
	 * @param string
	 * @param loginId
	 * @param shengzt
	 * @return
	 */
	List<GbsqBean> query(String string,String loginId,String shengzt);
	
	/**更新前查询基本信息
	 * @param qxkzid
	 * @return
	 */
	List<GbsqBaseBean> queryBase(String[] qxkzid);
	/**审核通过
	 * @return
	 */
	String checkPass(List<GbsqBaseBean> list,String syf,String b1,String b2,String b3,
			String bb5,String bb6,String bb7,String bb8,String bb9,String bb10,String bb11,String bb12,String bb13,String bb14,String bb15,String bb16,String bb17,String bb18,String bb19,String bb20,String bb22,
			String dd5,String dd6,String dd7,String dd8,String dd9,String dd10,String dd11,String dd12,String dd13,String dd14,String dd15,String dd16,String dd17,String dd18,String dd19,String dd20,String dd22,
			String xx5,String xx6,String xx7,String xx8,String xx9,String xx10,String xx11,String xx12,String xx13,String xx14,String xx15,String xx16,String xx17,String xx18,String xx19,String xx20,String xx22,
			String yy5,String yy6,String yy7,String yy8,String yy9,String yy10,String yy11,String yy12,String yy13,String yy14,String yy15,String yy16,String yy17,String yy18,String yy19,String yy20,String yy22,
			String qq5,String qq6,String qq7,String qq8,String qq9,String qq10,String qq11,String qq12,String qq13,String qq14,String qq15,String qq16,String qq17,String qq18,String qq19,String qq20,String qq22,
			String ii5,String ii6,String ii7,String ii8,String ii9,String ii10,String ii11,String ii12,String ii13,String ii14,String ii15,String ii16,String ii17,String ii18,String ii19,String ii20,String ii22,
			String jj5,String jj6,String jj7,String jj8,String jj9,String jj10,String jj11,String jj12,String jj13,String jj14,String jj15,String jj16,String jj17,String jj18,String jj19,String jj20,String jj22,String personnal);
	/**审核不通过
	 * @param list
	 * @param personnal 审核人
	 * @param cause 不通过原因
	 * @return
	 */
	String checkNoPass(List<GbsqBaseBean> list,String personnal,String cause);
	/**
	 * @param list
	 * @param personnal 审核人
	 * @return
	 */
	String checkQuXiao(List<GbsqBaseBean> list,String personnal);

}
