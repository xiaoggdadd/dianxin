package com.ptac.app.tjhz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.tjhz.bean.GdfsBean;

public class GdfsDaoImp implements GdfsDao {
	/**
	 * @param whereStr (String) sql语句后半段
	 * @param loginId (String) 权限过滤条件 
	 * @return (GdfsBean)
	 * @see 供电方式查询和导出
	 * @author MingQingYong 2014-02-19
	 * 
	 */
	Connection conn = null;
	Statement sta = null;
	@Override
	public List<GdfsBean> queryGdfs(String whereStr, String loginId,String str) {
		List<GdfsBean> list = new ArrayList<GdfsBean>();//声明结果集
		StringBuffer sql = new StringBuffer();//声明sql
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			
		sql.append(" select rndiqu(Z.SHI)as shi,COUNT( DISTINCT Z.ID) AS zdsum," +
				"COUNT( DISTINCT DECODE(Z.GDFS, 'gdfs06', Z.ID, 'gdfs07', Z.ID)) AS zgdsum, " +
				"COUNT(DISTINCT DECODE(Z.GDFS, 'gdfs05', Z.ID)) AS zhgdsum," +
				"ROUND(SUM(ZGD.ACTUALPAY)/10000,2)AS moneysum,ROUND( SUM(ZGD.ZGDDF)/10000,2)AS zgdmoneysum, " +
				"ROUND( SUM(ZGD.ZZGDDF)/10000,2)AS zhgdmoneysum," +
				"ROUND(SUM(ZGD.ZZGDDL)/10000,2)AS zhgddlsum, ROUND( SUM(ZGD.ZGDDL)/10000,2)AS zgddlsum " +
				"FROM ZHANDIAN Z LEFT JOIN " +
				"(SELECT ZD.ID,E.ACTUALPAY,DECODE(ZD.GDFS, 'gdfs06', E.ACTUALPAY, 'gdfs07', E.ACTUALPAY) AS ZGDDF, " +
				"DECODE(ZD.GDFS, 'gdfs05', E.ACTUALPAY) AS ZZGDDF," +
				"DECODE(ZD.GDFS, 'gdfs06', A.BLHDL, 'gdfs07', A.BLHDL) AS ZGDDL," +
				"DECODE(ZD.GDFS, 'gdfs05', A.BLHDL) AS ZZGDDL  " +
				"FROM ZHANDIAN  ZD, DIANBIAO  D,ELECTRICFEES E,AMMETERDEGREES A " +
				" WHERE ZD.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK " +
				" AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1'  AND ZD.STATIONTYPE<>'StationType41'  "+whereStr+") ZGD ON Z.ID=ZGD.ID" +
				" WHERE Z.XUNI='0' AND Z.CAIJI='0' AND Z.SHSIGN='1' AND Z.STATIONTYPE<>'StationType41'  "+str+" GROUP BY Z.SHI ORDER BY Z.SHI ");           
		System.out.println("供电方式查询/导出:" + sql.toString());//后台打印sql
		
		db.connectDb();	 
		conn = db.getConnection();
		sta = conn.createStatement();
		rs = sta.executeQuery(sql.toString());//运行sql
		DecimalFormat   dff   =new   DecimalFormat("0.00");
		while(rs.next()){
			GdfsBean gdfs=new GdfsBean();
			gdfs.setShi(rs.getString("shi")!=null?rs.getString("shi"):"");//市
			gdfs.setZdslsum(rs.getString("zdsum")!=null?rs.getString("zdsum"):"0");//站点总数
			gdfs.setZgdsum(rs.getString("zgdsum")!=null?rs.getString("zgdsum"):"0");//直供电数量
			gdfs.setZhgdsum(rs.getString("zhgdsum")!=null?rs.getString("zhgdsum"):"0");//转供电数量	
			String s=rs.getString("moneysum");
			if("".equals(s)||null==s){s="0";}
            gdfs.setMoneysum(dff.format(Double.parseDouble(s)));//总金额
            String s1=rs.getString("zgdmoneysum");
            if("".equals(s1)||null==s1){s1="0";}
            gdfs.setZgdmoneysum(dff.format(Double.parseDouble(s1)));//直供电金额
            String s2=rs.getString("zhgdmoneysum");
            if("".equals(s2)||null==s2){s2="0";}
			gdfs.setZhgdmoneysum(dff.format(Double.parseDouble(s2)));//转供电金额
			String s3=rs.getString("zgddlsum");
			 if("".equals(s3)||null==s3){s3="0";}
            gdfs.setZgddlsum(dff.format(Double.parseDouble(s3)));//直供电电量
            String s4=rs.getString("zhgddlsum");
            if("".equals(s4)||null==s4){s4="0";}
			gdfs.setZhgddlsum(dff.format(Double.parseDouble(s4)));//转供电电量
			list.add(gdfs);
		}
/*			if("1".equals(bz)){
				sql.append("SELECT BB.SHI shi, COUNT(BB.ID) zdsum,COUNT(BB.ZGD) zgdsum,COUNT(BB.ZHGD) zhgdsum," +
						"  ROUND((SUM(BB.B) / SUM(BB.M)) * SUM(BB.M) / 10000, 2) MONEYSUM, ROUND((SUM(BB.C) / SUM(BB.M)) * SUM(BB.M) / 10000, 2) DLSUM,ROUND((SUM(BB.F) / SUM(BB.M)) * SUM(BB.M) / 10000, 2) ZGDMONEYSUM,ROUND((SUM(BB.I) / SUM(BB.M)) * SUM(BB.M) / 10000, 2) ZHGDMONEYSUM,"+
		                 " ROUND((SUM(BB.G) / SUM(BB.M)) * SUM(BB.M) / 10000, 2) ZHGDDLSUM,ROUND((SUM(BB.H) / SUM(BB.M)) * SUM(BB.M) / 10000, 2) ZGDDLSUM "+
		                 "FROM (SELECT AA.SHI,AA.ID,SUM(AA.ACTUALPAY) B,SUM(AA.BLHDL) C,AA.ZGD,AA.ZHGD,"+
		                   "SUM(AA.ZGDDF) F,SUM(AA.ZHGDDL) G,SUM(AA.ZGDDL) H,SUM(AA.ZHGDDF) I,SUM(AA.ZQ) M "+
		                	"FROM (SELECT DISTINCT (SELECT B.AGNAME  FROM D_AREA_GRADE B  WHERE B.AGCODE = Z.SHI) SHI, Z.ID,DF.ACTUALPAY, DV.BLHDL,"+
		                	"(TO_CHAR(CEIL(TO_DATE(DV.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(DV.LASTDATETIME,'yyyy-mm-dd'))) + 1) AS ZQ,"+
		                	"(CASE WHEN Z.GDFS = 'gdfs04' THEN Z.ID END) ZGD,"+
		                	"(CASE WHEN Z.GDFS = 'gdfs05' THEN Z.ID END) ZHGD,"+
		                	"(CASE WHEN Z.GDFS = 'gdfs04' THEN DF.ACTUALPAY END) ZGDDF,"+
		                	"(CASE WHEN Z.GDFS = 'gdfs05' THEN DV.BLHDL END) ZHGDDL,"+
		                	"(CASE WHEN Z.GDFS = 'gdfs05' THEN DF.ACTUALPAY END) ZHGDDF,"+
		                	" (CASE WHEN Z.GDFS = 'gdfs04' THEN DV.BLHDL END) ZGDDL"+
		                	" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW DV, DIANFEIVIEW DF WHERE Z.ID = D.ZDID  AND LENGTH(DV.LASTDATETIME) = 10 AND LENGTH(DV.THISDATETIME) = 10 AND D.DBID = DV.AMMETERID_FK AND DV.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK  "+whereStr+") AA "+
		                	"GROUP BY AA.ID, AA.SHI,AA.ZGD,AA.ZHGD) BB GROUP BY BB.SHI ");           
				System.out.println("供电方式查询/导出:" + sql.toString());//后台打印sql
				
				db.connectDb();	 
				conn = db.getConnection();
				sta = conn.createStatement();
				rs = sta.executeQuery(sql.toString());//运行sql
				while(rs.next()){
					GdfsBean gdfs=new GdfsBean();
					gdfs.setShi(rs.getString("shi")!=null?rs.getString("shi"):"");//市
					gdfs.setZdslsum(rs.getString("zdsum")!=null?rs.getString("zdsum"):"0");//站点总数
					gdfs.setZgdsum(rs.getString("zgdsum")!=null?rs.getString("zgdsum"):"0");//直供电数量
					gdfs.setZhgdsum(rs.getString("zhgdsum")!=null?rs.getString("zhgdsum"):"0");//转供电数量
					String s=rs.getString("moneysum");
					if("".equals(s)||null==s){s="0";}
					DecimalFormat   dff   =new   DecimalFormat("0.00");
		            gdfs.setMoneysum(dff.format(Double.parseDouble(s)));//总金额
		            String s1=rs.getString("zgdmoneysum");
		            if("".equals(s1)||null==s1){s1="0";}
		            gdfs.setZgdmoneysum(dff.format(Double.parseDouble(s1)));//直供电金额
		            String s2=rs.getString("zhgdmoneysum");
		            if("".equals(s2)||null==s2){s2="0";}
					gdfs.setZhgdmoneysum(dff.format(Double.parseDouble(s2)));//转供电金额
					String s3=rs.getString("zgddlsum");
					 if("".equals(s3)||null==s3){s3="0";}
		            gdfs.setZgddlsum(dff.format(Double.parseDouble(s3)));//直供电电量
		            String s4=rs.getString("zhgddlsum");
		            if("".equals(s4)||null==s4){s4="0";}
					gdfs.setZhgddlsum(dff.format(Double.parseDouble(s4)));//转供电电量
					list.add(gdfs);
				}
					}*/
		}catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
	}

	@Override
	public GdfsBean total(List<GdfsBean> list) {
		GdfsBean total = new GdfsBean();//生成一个total对象
		double zdzlhj=0,//总站点数量合计
		zgdzdhj=0,//直供电站点数量合计
		zhgdzdhj=0,//转供电站点数量合计
		moneyhj=0,//总金额合计
		zgdmoneyhj=0,//直供电金额合计
		zhgdmoneyhj=0,//转供电金额合计
		zgddlhj=0,//直供电电量合计
		zhgddlhj=0;//转供电电量合计
//		private String zdzlhj;//总站点数量合计
//		private String zgdzdhj;//直供电站点数量合计
//		private String zhgdzdhj;//转供电站点数量合计
//		private String moneyhj;//总金额合计
//		private String zgdmoneyhj;//直供电金额合计
//		private String zhgdmoneyhj;//转供电金额合计
//		private String zgddlhj;//直供电电量合计
//		private String zhgddlhj;//转供电电量合计
//----------------------------------------------
//		private String zdslsum;//总站点数量
//		private String zgdsum;//直供电数量
//		private String zhgdsum;//转供电站点数量
//		private String moneysum;//总金额
//		private String zgdmoneysum;//直供电金额
//		private String zhgdmoneysum;//转供电金额
//		private String zgddlsum;//直供电电量
//		private String zhgddlsum;//转供电电量
		
		
		int i;
		for(i = 0; i<list.size();i++){
			//遍历求和
			GdfsBean bean = list.get(i);
			zdzlhj = zdzlhj + Double.parseDouble(bean.getZdslsum());
			zgdzdhj = zgdzdhj + Double.parseDouble(bean.getZgdsum());
			zhgdzdhj=zhgdzdhj+Double.parseDouble(bean.getZhgdsum());
			moneyhj=moneyhj+Double.parseDouble(bean.getMoneysum());
			zgdmoneyhj=zgdmoneyhj+Double.parseDouble(bean.getZgdmoneysum());
			zhgdmoneyhj=zhgdmoneyhj+Double.parseDouble(bean.getZhgdmoneysum());
			zgddlhj=zgddlhj+Double.parseDouble(bean.getZgddlsum());
			zhgddlhj=zhgddlhj+Double.parseDouble(bean.getZhgddlsum());
		}
		//放入total
		total.setZdzlhj(zdzlhj+"");
		total.setZgdzdhj(zgdzdhj+"");
		total.setZhgdzdhj(zhgdzdhj+"");
		total.setMoneyhj(moneyhj+"");
		total.setZgdmoneyhj(zgdmoneyhj+"");
		total.setZhgdmoneyhj(zhgdmoneyhj+"");
		total.setZgddlhj(zgddlhj+"");
		total.setZhgddlhj(zhgddlhj+"");
		return total;
	}

}
