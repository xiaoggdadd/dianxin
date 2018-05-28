package com.ptac.app.electricmanage.liuchenghaobill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanage.liuchenghaobill.bean.LiuchBean;
import com.ptac.app.electricmanageUtil.Format;

public class LiuchDaoImp implements LiuchDao {

	@Override
	public List<LiuchBean> query(String string, String loginId) {
		
		List<LiuchBean> list = new ArrayList<LiuchBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT DY.LIUCHENGHAO LIUCHENGHAO,RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,DY.DAYINREN DAYINREN,DY.DAYINTIMEOLD DAYINTIMEOLD,SUM(DY.ACTUALPAY) ZJE,"
				 + " abs(SUM(decode(ZD.stationtype,'StationType41', DY.ACTUALPAY,0))) ZSE,(SUM(DY.ACTUALPAY)-SUM(decode(ZD.stationtype,'StationType41', DY.ACTUALPAY,0))) JSHJ"
				 + " FROM ZHANDIAN ZD, DIANBIAO D, AMMETERDEGREES A,ELECTRICFEES DY WHERE ZD.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = DY.AMMETERDEGREEID_FK "
				 + " AND ZD.QYZT = '1' AND DY.CITYZRAUDITSTATUS = '1' AND D.DBQYZT = '1' AND DY.CITYAUDIT = '1'"
				 + string
				 + " AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))"
				 + " group by LIUCHENGHAO,SHI,XIAN,DAYINREN,DAYINTIMEOLD");
		
		System.out.println("��Ϣ��ѯ��"+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				LiuchBean bean = new LiuchBean();
				
				String liuchenghao = rs.getString("LIUCHENGHAO")==null?"":rs.getString("LIUCHENGHAO");
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//��
				String xian = rs.getString("XIAN")==null?"":rs.getString("XIAN");//����
				String dyr = rs.getString("DAYINREN")==null?"":rs.getString("DAYINREN");
				String drtime = rs.getString("DAYINTIMEOLD")==null?"":rs.getString("DAYINTIMEOLD");
				String zje = rs.getString("ZJE")==null?"":rs.getString("ZJE");//�ܽ��
				String zse = rs.getString("ZSE")==null?"":rs.getString("ZSE");//��ֵ˰��
				String jshj = rs.getString("JSHJ")==null?"":rs.getString("JSHJ");//��˰�ϼ�
				
				bean.setLiuchenghao(liuchenghao);
				bean.setCity(city);
				bean.setXian(xian);
				bean.setDyr(dyr);
				bean.setDrtime(drtime);
				bean.setZje(Format.str2(zje));
				bean.setZse(Format.str2(zse));
				bean.setJshj(Format.str2(jshj));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<LiuchBean> xiangxi(String string, String str) {
		
		List<LiuchBean> list = new ArrayList<LiuchBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		
		sql="select (case when zd.xian is not null then(select distinct agname from d_area_grade where agcode = zd.xian) else''end) " +
	 		"|| (case when zd.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,zd.jzname," +
	 		"(select i.name from zhandian z,indexs i where z.id=zd.id and z.STATIONTYPE=i.code and i.type='stationtype')as jztype, " +
	 		"TO_CHAR(DY.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(DY.THISDATETIME,'yyyy-mm-dd') THISDATETIME,DY.LASTDEGREE,DY.THISDEGREE," +
	 		"am.beilv,DY.FLOATDEGREE,DY.blhdl,DY.UNITPRICE,DY.FLOATPAY,DY.actualpay,TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH," +
	 		"(SELECT INS.NAME  FROM INDEXS INS WHERE AM.DFZFLX = INS.CODE  AND AM.ZDID = ZD.ID  AND INS.TYPE = 'dfzflx') AS FFFS,DY.PJJE," +
	 		"DY.LASTELECFEES, DY.LASTELECDEGREE,DY.LASTUNITPRICE,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=ZD.GDFS AND I.TYPE='GDFS') GDFS,dy.memo" +
	 		" FROM ZHANDIAN ZD, DIANBIAO AM, ELEPRINT_VIEW DY WHERE ZD.ID = AM.ZDID AND AM.DBID = DY.AMMETERID_FK" +
	 		" AND ZD.QYZT = '1' AND DY.CITYZRAUDITSTATUS = '1' AND AM.DBQYZT = '1' AND DY.CITYAUDIT = '1' "+string+str+" ORDER BY JZTYPE,ZD.JZNAME ";
		
		System.out.println("��ϸ:"+sql);
	
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				LiuchBean bean = new LiuchBean();
				
				String diqu = rs.getString(1)==null?"":rs.getString(1);//����
				String zdname = rs.getString(2)==null?"":rs.getString(2);
				String zdlx = rs.getString(3)==null?"":rs.getString(3);
				String lasttime = rs.getString(4)==null?"":rs.getString(4);//�ϴγ���ʱ��
				String thistime = rs.getString(5)==null?"":rs.getString(5);//���γ���ʱ��
				String lastdegree = rs.getString(6)==null?"":rs.getString(6);//�ϴε�����
				String thisdegree = rs.getString(7)==null?"":rs.getString(7);//���ε�����
				String beilv = rs.getString(8)==null?"":rs.getString(8);//����
				String floatdegree = rs.getString(9)==null?"":rs.getString(9);//�õ�������	
				String blhdl = rs.getString(10)==null?"":rs.getString(10);//���˵���
				String price = rs.getString(11)==null?"":rs.getString(11);//����
				String floatpay = rs.getString(12)==null?"":rs.getString(12);//��ѵ���
				String actualpay = rs.getString(13)==null?"":rs.getString(13);//���˵��
				String accountmonth = rs.getString(14)==null?"":rs.getString(14);//�����·�
				String zflx = rs.getString(15)==null?"":rs.getString(15);//֧������
				String pjje = rs.getString(16)==null?"":rs.getString(16);//Ʊ�ݽ��
				String sqdf = rs.getString(17)==null?"":rs.getString(17);//���ڵ��
				String sqdl = rs.getString(18)==null?"":rs.getString(18);//���ڵ���
				String lastprice = rs.getString(19)==null?"":rs.getString(19);//���ڵ���
				String gdfs = rs.getString(20)==null?"":rs.getString(20);//���緽ʽ
				String memo = rs.getString(21)==null?"":rs.getString(21);//��ע
				
				bean.setDiqu(diqu);
				bean.setZdname(zdname);
				bean.setZdlx(zdlx);
				bean.setLasttime(lasttime);
				bean.setThistime(thistime);
				bean.setLastdegree(lastdegree);
				bean.setThisdegree(thisdegree);
				bean.setBeilv(beilv);
				bean.setFloatdegree(floatdegree);
				bean.setBlhdl(blhdl);
				bean.setPrice(Format.str4(price));
				bean.setFloatpay(floatpay);
				bean.setActualpay(Format.str2(actualpay));
				bean.setAccountmonth(accountmonth);
				bean.setZflx(zflx);
				bean.setPjje(pjje);
				bean.setSqdf(sqdf);
				bean.setSqdl(sqdl);
				bean.setLastprice(lastprice);
				bean.setGdfs(gdfs);
				bean.setMemo(memo);
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<LiuchBean> xiangxift(String liuch) {
		
		List<LiuchBean> list = new ArrayList<LiuchBean>();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql="SELECT  (SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.shi)AS shi, " +
				"(SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.xian)AS xian," +
				"SUM(E.NETWORKDF * S.XJBILI / 100) E,SUM(E.INFORMATIZATIONDF * S.XJBILI / 100) A," +
				"SUM(E.ADMINISTRATIVEDF * S.XJBILI / 100) B,SUM(E.MARKETDF * S.XJBILI / 100) C, " +
				"SUM(E.BUILDDF * S.XJBILI / 100) D, (SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB, " +
				"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM, " +
				"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX, " +
				" S.SHUOSHUZHUANYE,SUM(E.DDDf * S.XJBILI / 100) F " +
				"FROM ZHANDIAN Z, DIANBIAO D, SBGL S, CAIWUDAYIN_DDV A, CAIWUDAYIN_DDF E WHERE Z.ID = D.ZDID AND D.DBID = S.DIANBIAOID" +
				" AND D.DBID = A.AMMETERID_FK  AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND e.LIUCHENGHAO = '"+liuch+"' " +
				"GROUP BY S.SHUOSHUZHUANYE, S.QCBCODE, S.KJKMCODE, S.ZYMXCODE,z.shi,z.xian";
		System.out.println("��̯���ã�"+sql.toString());
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				LiuchBean bean=new LiuchBean();
				bean.setCity(rs.getString(1));
				bean.setXian(rs.getString(2));
				bean.setNETWORKDF(rs.getString(3));
				bean.setINFORMATIZATIONDF(rs.getString(4));
				bean.setADMINISTRATIVEDF(rs.getString(5));
				bean.setMARKETDF(rs.getString(6));
				bean.setBUILDDF(rs.getString(7));
				bean.setQcb(rs.getString(8));
				bean.setKjkm(rs.getString(9));
				bean.setZymx(rs.getString(10));
				bean.setSszy(rs.getString(11));
				bean.setDddf(rs.getString(12));
				list.add(bean);
			}
		}
		catch (Exception de) {
			de.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public String[] getSum(List<LiuchBean> list) {
		
		String[] sum = new String[5];
		int length = list.size();
		LiuchBean bean = null;
		Double zjesum = 0.00;//�ܽ��sum
		Double zsesum = 0.00;//��ֵ˰��sum
		
		for( int i = 0 ; i < length ; i++ ){
			bean = list.get(i);
			Double zjehj = Double.parseDouble(bean.getActualpay());
			Double zsehj = 0.00;
			
			if("��ֵ˰վ��".equals(bean.getZdlx())){
				zsehj = Double.parseDouble(bean.getActualpay());
			}
			zjesum += zjehj;
			zsesum += zsehj;
		}
		sum[0] = Format.str2(String.valueOf(zjesum));
		sum[1] = Format.str2(String.valueOf(zsesum));
		return sum;
	}

}
