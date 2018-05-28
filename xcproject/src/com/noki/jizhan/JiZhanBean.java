package com.noki.jizhan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.ammeterdegree.javabean.DaoFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.function.CityQueryBean;
import com.noki.mobi.common.CommonAccountBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import java.util.Date;
import com.noki.util.MD5;

public class JiZhanBean {

	private int allPage;
	Connection conn = null;
	Statement sta = null;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}
	
	//站点管理信息列表查询   虚拟站点查询
	public synchronized ArrayList getPageData(int start, String sheng,
			String shi, String xian, String xiaoqu, String sname,
			String szdcode, String loginName, String xuni,String loginId,String jztype1,String jzproperty1,String bgsign,String rgsh1,String rgsh2) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		if (xuni.equals("3")) {
			cxtj.append(" and shsign!='1'");
		} else {
			cxtj.append(" and xuni='" + xuni + "'");
		}
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		if (sname.length() > 0 && sname != null) {
			cxtj.append(" and z.jzname like '%" + sname + "%'");
		}
		if (szdcode.length() > 0 && szdcode != null) {
			cxtj.append(" and z.zdcode like '%" + szdcode + "%'");
		}
		//新加
		if (!jztype1.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype1 + "'");
			}
		if (!jzproperty1.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty1 + "'");
			}
		if (!bgsign.equals("-1")) {
			cxtj.append(" and z.bgsign='" + bgsign + "'");
			}
		
		if (rgsh1!=null&&!rgsh1.equals("null")) {//首页传的值 审核通过的
			cxtj.append(" and z.SHSIGN='" + rgsh1 + "'");
			}
		if (rgsh2!=null&&!rgsh2.equals("null")) {//首页传的值 采集站点
			cxtj.append(" and z.caiji='" + rgsh2 + "'");
			
			}		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
        ResultSet rs3 = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 ");
		strall.append(cxtj.toString());

		try {
			db.connectDb();
			//String fzarea = "", fzzdid = "";
			//fzarea = getFuzeArea(db, loginName);
            
			//fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");
           
			StringBuffer s2 = new StringBuffer();
			
			StringBuffer s3 = new StringBuffer();
			s2.append("select z.id,z.jzname"+
					   ",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)"+
					   "||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)"+
					   "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,"+
					   "(select t.name from indexs t where t.code=z.property ) as property"+
					   ",(select t.name from indexs t where t.code=z.jztype ) as jztype"+
					   ",(select t.name from indexs t where t.code=z.yflx and t.type='FWLX') as yflx,"+
					   "(select t.name from indexs t where t.code=z.gdfs  ) as gdfs"+
					   ",(case when xuni='0' then '否' else '是' end) as xunisign"+
					   ",z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu,z.shsign from zhandian z where 1=1 and z.qyzt='1'" +
					   " "+cxtj.toString()+" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') " +
					   " order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
			System.out.println("站点管理信息列表查询（虚拟）:"+s2.toString()); 
			/*if (!fzarea.equals("'0'") || !fzzdid.equals("0")) { // 有负责分配负责区域或者负责站点

				if (!fzarea.equals("'0'")) { // 有分配负责区域
					s2.append(" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') ");
					strall.append(" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') ");

				} else {
					s2.append(" and " + fzzdid);
					strall.append(" and " + fzzdid);
				}
			} else { // 没有 负责分配负责区域或者负责站点
				s2.append(" and z.xiaoqu in('0')");
				strall.append(" and z.xiaoqu in('0')");
			}

			s2.append(cxtj.toString());
			if (!fzarea.equals("'0'") && !fzzdid.equals("0")) {
				querystr.append(getQueryStr_zd(fzzdid, s2.toString()));
				querystr_all.append(getQueryStr_zd_all(fzzdid, strall
						.toString()));

			} else {
				s2.append(" order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
				querystr = s2;
				querystr_all.setLength(0);
				querystr_all
						.append("select count(*) from zhandian z where 1=1 ");
				querystr_all.append(cxtj.toString());
			}

			System.out.println("querystr_all:" + querystr_all.toString());
			querystr_all.setLength(0);
			querystr_all.append("select count(*) from (" + querystr + ")");
			System.out.println(">>>>>>>>>>>>>" + querystr_all.toString());
			rs = db.queryAll(querystr_all.toString());
			if (rs.next()) {
				if (rs.getInt(1) % 15 == 0) {
					this.setAllPage(rs.getInt(1) / 15);
				} else {
					this.setAllPage(rs.getInt(1) / 15 + 1);
				}
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, querystr.toString()));
			Query query = new Query();
			list = query.query(rs);*/
			 s3.append("select count(*)  from (" + s2 + ")");
             System.out.println("站点管理查询分页（虚拟）:"+s3); 

             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+14)/15);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close(); 
            db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	
	//地市基站用电量信息汇总表的查询语句
	public synchronized ArrayList getPageData2(String whereStr,String loginId,String loginName) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql=
				"select II.NAME,II.CODE,AA.ID id1 ,AA.DL dl1,AA.DF df1,bb.id id2,bb.df df2,bb.dl dl2,cc.id id3,cc.dl dl3,cc.df df3,dd.id id4,dd.dl dl4,dd.df df4"+
				" from(select zd.xlx, count( DISTINCT zd.id) as id, sum(am.blhdl)as dl,sum(e.actualpay)as df from zhandian zd,dianbiao db,ammeterdegrees am,electricfees   e "+
				 "where  zd.id = db.zdid(+) and db.dbid = am.ammeterid_fk(+) and am.ammeterdegreeid = e.ammeterdegreeid_fk AND E.MANUALAUDITSTATUS >= '1' and db.dbyt='dbyt01' and zd.bgsign = '1' "+whereStr+" and zd.jnglmk = '0' group by zd.xlx)aa,"+
				 "(select zd.xlx,count( DISTINCT zd.id) as id,sum(am.blhdl)as dl,sum(e.actualpay)as df from zhandian zd,dianbiao db,ammeterdegrees am,electricfees e "+
				 "where  zd.id = db.zdid(+) and db.dbid = am.ammeterid_fk(+) and am.ammeterdegreeid = e.ammeterdegreeid_fk AND E.MANUALAUDITSTATUS >= '1' and db.dbyt='dbyt01' and zd.bgsign = '1' "+whereStr+" and zd.jnglmk = '1'group by zd.xlx)bb,"+
				 "(select zd.xlx,count( DISTINCT zd.id) as id,sum(am.blhdl)as dl,sum(e.actualpay)as df from zhandian zd,dianbiao db,ammeterdegrees am,electricfees   e "+
				 "where  zd.id = db.zdid(+) and db.dbid = am.ammeterid_fk(+) and am.ammeterdegreeid = e.ammeterdegreeid_fk AND E.MANUALAUDITSTATUS >= '1' and db.dbyt='dbyt01' and zd.bgsign = '0' "+whereStr+" and zd.jnglmk = '0' group by zd.xlx)cc,"+
				 "(select zd.xlx,count(DISTINCT zd.id) as id,sum(am.blhdl)as dl,sum(e.actualpay)as df from zhandian zd,dianbiao db,ammeterdegrees am, electricfees   e "+
				 "where  zd.id = db.zdid(+) and db.dbid = am.ammeterid_fk(+) and am.ammeterdegreeid = e.ammeterdegreeid_fk AND E.MANUALAUDITSTATUS >= '1' and db.dbyt='dbyt01' and zd.bgsign = '0' "+whereStr+" and zd.jnglmk = '1' group by zd.xlx)dd,indexs ii "+
				 "where ii.code=aa.xlx(+) and ii.code=bb.xlx(+) and ii.code=cc.xlx(+) and ii.code=dd.xlx(+) " +
				// whereStr+
				 " and ii.type='XLX' ORDER BY II.CODE";
				
				
				
				
				
				
				
				
				
				
				
				
			/*String sql="select xlx as xlxa,sum(actualpay) as actualpay,sum(actualdegree) as actualdegree,count(id) as id,(select name from indexs where type='XLX'  and code=xlx ) as xlx  " +
					"from(select count(zd.id) as id, am.actualdegree, e.actualpay, zd.xlx " +
					"from zhandian zd, dianbiao db, ammeterdegrees am, electricfees e " +
					"where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = e.ammeterdegreeid_fk " +
					whereStr+
					" and zd.bgsign = '1' and zd.jnglmk = '否' group by zd.id, actualdegree, actualpay, zd.xlx) " +					
					" group by (xlx) having xlx is not null and xlx<>'0' order by xlxa asc";*/
			System.out.println("11111111111"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//=====省级分摊汇总
	public synchronized List<ZhanDianForm> getPageData23(String whereStr,String loginId,String loginName) {
		List<ZhanDianForm> list = new ArrayList<ZhanDianForm>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			conn = db.getConnection();
			String sql=
				" SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.shi )AS shi, COUNT(DISTINCT d.id )AS zynum, COUNT(DISTINCT (CASE WHEN aa.bili=100 THEN d.id END ))AS zywc, COUNT(DISTINCT (CASE WHEN aa.bili=0 OR aa.bili IS NULL THEN d.id END))AS zywf, "+
				" COUNT(DISTINCT (CASE WHEN aa.bili<>0 AND aa.bili <>100 AND aa.bili IS NOT NULL  THEN d.id END))AS zyyc,COUNT(DISTINCT (CASE WHEN aa.bili=100 THEN d.id END ))/COUNT(DISTINCT d.id )*100 as zybl, COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))AS xxwc, "+
				" COUNT(DISTINCT d.id )-COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))- COUNT(DISTINCT(CASE WHEN cc.bili<>0 AND cc.bili <>100 AND cc.bili IS NOT NULL  THEN d.id END))as xxwf,COUNT(DISTINCT(CASE WHEN cc.bili<>0 AND cc.bili <>100 AND cc.bili IS NOT NULL  THEN d.id END))AS xxyc,"+
				" COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))/COUNT(DISTINCT d.id )*100 as xxbl,Z.SHI FROM (SELECT sss.dianbiaoid,SUM(sss.dbili)AS bili from(SELECT  DISTINCT s.dianbiaoid AS dianbiaoid,S.SHEIEBANID, S.DBILI FROM sbgl s)sss GROUP BY sss.dianbiaoid)aa,(SELECT S.DIANBIAOID,S.SHEIEBANID,S.XJID,S.SHUOSHUZHUANYE,S.KJKMCODE,S.QCBCODE,S.ZYMXCODE,s.xjbili FROM SBGL S) BB,(SELECT s.dianbiaoid,sum(s.xjbili)/count(DISTINCT s.sheiebanid)AS bili FROM sbgl s  GROUP  BY s.dianbiaoid)cc,zhandian z,dianbiao d "+
				" WHERE z.id=d.zdid AND d.dbid=aa.DIANBIAOID(+) AND d.dbid=bb.DIANBIAOID(+) AND d.dbid=cc.dianbiaoid(+)" +whereStr+
				" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"' )"+
				" AND d.dbyt='dbyt01' AND z.shsign='1' AND z.qyzt='1' AND d.dbqyzt='1'  GROUP  BY z.shi ";
			System.out.println("省级分摊汇总查询："+sql.toString());
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			
			while(rs.next()){
				ZhanDianForm zdbean=new ZhanDianForm();
				zdbean.setShi(rs.getString(1));
				zdbean.setZynum(Integer.parseInt(rs.getString(2)));
				zdbean.setZywc(rs.getString(3));
				zdbean.setZywf(rs.getString(4));
				zdbean.setZyyc(rs.getString(5));
				zdbean.setZybl(Double.parseDouble(rs.getString(6)));
				zdbean.setXxwc(rs.getString(7));
				zdbean.setXxwf(rs.getString(8));
				zdbean.setXxyc(rs.getString(9));
				zdbean.setXxbl(Double.parseDouble(rs.getString(10)));
				zdbean.setShicode(rs.getString(11));
				list.add(zdbean);
			}
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}
		finally {
			db.free(rs, sta, conn);
		}
		return list;
	}
	public synchronized List<ZhanDianForm> getPageData234(String whereStr,String loginId,String loginName) {
		List<ZhanDianForm> list = new ArrayList<ZhanDianForm>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql="";
		try {
			db.connectDb();
			sql=
				" SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.shi )AS shi, COUNT(DISTINCT d.id )AS zynum, COUNT(DISTINCT (CASE WHEN aa.bili=100 THEN d.id END ))AS zywc, COUNT(DISTINCT (CASE WHEN aa.bili=0 OR aa.bili IS NULL THEN d.id END))AS zywf, "+
				"  COUNT(DISTINCT (CASE WHEN aa.bili<>0 AND aa.bili <>100 AND aa.bili IS NOT NULL  THEN d.id END))AS zyyc,COUNT(DISTINCT (CASE WHEN aa.bili=100 THEN d.id END ))/COUNT(DISTINCT d.id )*100 as zybl, COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))AS xxwc, "+
				"  COUNT(DISTINCT d.id )-COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))- COUNT(DISTINCT(CASE WHEN cc.bili<>0 AND cc.bili <>100 AND cc.bili IS NOT NULL  THEN d.id END))as xxwf,COUNT(DISTINCT(CASE WHEN cc.bili<>0 AND cc.bili <>100 AND cc.bili IS NOT NULL  THEN d.id END))AS xxyc,"+
				"  COUNT(DISTINCT(CASE WHEN   BB.KJKMCODE IS NOT NULL AND  BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN  d.id END))/COUNT(DISTINCT d.id )*100 as xxbl" +
				",(SELECT da.agname FROM d_area_grade da WHERE da.agcode = z.XIAN) AS XIAN "+
				" FROM (SELECT sss.dianbiaoid,SUM(sss.dbili)AS bili from(SELECT  DISTINCT s.dianbiaoid AS dianbiaoid,S.SHEIEBANID, S.DBILI FROM sbgl s)sss GROUP BY sss.dianbiaoid)aa,(SELECT S.DIANBIAOID,S.SHEIEBANID,S.XJID,S.SHUOSHUZHUANYE,S.KJKMCODE,S.QCBCODE,S.ZYMXCODE,s.xjbili FROM SBGL S) BB,(SELECT s.dianbiaoid,sum(s.xjbili)/count(DISTINCT s.sheiebanid)AS bili FROM sbgl s  GROUP  BY s.dianbiaoid)cc,zhandian z,dianbiao d "+
				"  WHERE z.id=d.zdid AND d.dbid=aa.DIANBIAOID(+) AND d.dbid=bb.DIANBIAOID(+) AND d.dbid=cc.dianbiaoid(+)" +whereStr+
				"  and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"' )"+
				"  AND d.dbyt='dbyt01' AND z.shsign='1' AND z.qyzt='1' AND d.dbqyzt='1'  GROUP  BY z.shi, Z.XIAN ";
			System.out.println("省级分摊汇总查询详细信息："+sql.toString());
			//rs = db.queryAll(sql.toString());
			rs=db.queryAll(sql.toString());
			// private int zynum;//专业站点数
		  //   private String zywc;//专业完成
		   //  private String zywf;//专业未分
		   //  private String zyyc;//专业异常
		   //  private double zybl;//专业比例
			
		//	 private int xxnum;//详细站点数
		 //    private String xxwc;//详细完成
		  //   private String xxwf;//详细未分
		  //   private String xxyc;//详细异常
		  //   private double xxbl;//详细比例
			
			while(rs.next()){
				ZhanDianForm zdbean=new ZhanDianForm();
				zdbean.setShi(rs.getString(1));
				zdbean.setZynum(Integer.parseInt(rs.getString(2)));
				zdbean.setZywc(rs.getString(3));
				zdbean.setZywf(rs.getString(4));
			
				zdbean.setZyyc(rs.getString(5));
				zdbean.setZybl(Double.parseDouble(rs.getString(6)));
				zdbean.setXxwc(rs.getString(7));
				zdbean.setXxwf(rs.getString(8));
				zdbean.setXxyc(rs.getString(9));
				zdbean.setXxbl(Double.parseDouble(rs.getString(10)));
				zdbean.setXian(rs.getString(11));
				list.add(zdbean);
			}
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
				if(db!=null){
				
					try {
						db.close();
					}
				
			 catch (DbException de) {
				de.printStackTrace();
			}

		}
		}
		return list;
	}
	//详细信息分摊汇总
	public synchronized List<ZhanDianForm> getPageData2345(String whereStr,String loginId,String loginName) {
		List<ZhanDianForm> list = new ArrayList<ZhanDianForm>();
		DataBase db = new DataBase();//(SELECT I.NAME  FROM INDEXS I WHERE I.CODE = ACC.POSITION AND I.TYPE = 'zw') POSITION,(SELECT T.DEPTNAME FROM DEPARTMENT T WHERE T.DEPTCODE = ACC.BUMEN) BUMEN
		ResultSet rs = null;
		String sql="";
		try {
			db.connectDb();
//			sql=" SELECT DISTINCT (SELECT da.agname FROM d_area_grade da WHERE da.agcode = z.XIAN) AS XIAN,BB.NAME AS ENTRYPENSONNEL, BB.MOBILE, BB.EMAIL, BB.PS,BB.BM "+
//            " ,(COUNT(DISTINCT(CASE WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL THEN d.id END))) xxwc "+
//            " FROM (SELECT A.NAME,(SELECT I.NAME FROM INDEXS I  WHERE I.CODE = A.POSITION  AND I.TYPE = 'zw') PS,  (SELECT T.DEPTNAME  FROM DEPARTMENT T  WHERE T.DEPTCODE = A.BUMEN) BM,"+
//            " A.MOBILE,A.EMAIL,A.POSITION,A.BUMEN,S.DIANBIAOID,S.SHEIEBANID,S.XJID,S.SHUOSHUZHUANYE,S.KJKMCODE,S.QCBCODE,S.ZYMXCODE,s.xjbili "+
//            " FROM SBGL S, ACCOUNT A WHERE A.ACCOUNTNAME = S.ENTRYPENSONNEL) BB,zhandian z,dianbiao d "+
//			" WHERE z.id = d.zdid AND d.dbid = bb.DIANBIAOID AND d.dbyt = 'dbyt01' AND z.shsign = '1' AND z.qyzt = '1' AND d.dbqyzt = '1' "+whereStr+	
//			" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"' )"+
//			"  GROUP BY z.shi,Z.XIAN,BB.NAME,BB.MOBILE,BB.EMAIL, BB.PS,BB.BM ";
//			
			sql=" select (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = ACC.XIAN)  XIAN, (SELECT DISTINCT AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = ACC.XIAOQU) XIAOQU,"+
			"ACC.ZIP,ACC.NAME,ACC.SEX,ACC.ACCOUNTNAME,ACC.ROLENAME,(SELECT T.DEPTNAME  FROM DEPARTMENT T  WHERE T.DEPTCODE = ACC.BUMEN) BUMEN,"+
			"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ACC.POSITION AND I.TYPE = 'zw') POSITION, ACC.MOBILE, ACC.TEL,ACC.EMAIL,ACC.ADDRESS,ACC.MEMO,"+
			" (COUNT(DISTINCT(CASE WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL  THEN BB.DIANBIAOID  END))) xxwc " +
			" FROM SBGL BB, ACCOUNT ACC,ZHANDIAN ZD WHERE  ZD.SHI=ACC.SHI AND ZD.XIAN=ACC.XIAN AND ZD.XIAOQU=ACC.XIAOQU AND BB.ENTRYPENSONNEL = ACC.ACCOUNTNAME AND BB.ENTRYPENSONNEL IS NOT NULL "+whereStr+
			" GROUP BY  ACC.XIAN, ACC.XIAOQU,ACC.ZIP, ACC.NAME,ACC.SEX,ACC.ACCOUNTNAME, ACC.ROLENAME, ACC.BUMEN, ACC.POSITION,ACC.MOBILE,ACC.TEL,ACC.EMAIL,ACC.ADDRESS,ACC.MEMO";
			
			
			
//			sql1="SELECT DISTINCT (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE= Z.XIAN) AS XIAN,  ACC.NAME,ACC.MOBILE,ACC.EMAIL,"
//           + " (SELECT I.NAME FROM INDEXS I WHERE I.CODE = acc.POSITION AND I.TYPE = 'zw') PS,"+
//             " (SELECT T.DEPTNAME  FROM DEPARTMENT T WHERE T.DEPTCODE = acc.BUMEN) BM,"+
//             " (COUNT(DISTINCT(CASE WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND  BB.ZYMXCODE IS NOT NULL THEN  d.id END))) xxwc "+
//             " FROM ZHANDIAN Z,SBGL BB,DIANBIAO D,ACCOUNT ACC "+
//             " WHERE Z.ID=D.ZDID AND ACC.ACCOUNTNAME=BB.ENTRYPENSONNEL AND D.DBID=BB.DIANBIAOID AND D.DBYT='dbyt01'  AND Z.SHSIGN= '1'  AND Z.QYZT='1' AND D.DBQYZT='1' "+whereStr+	
//             " AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginId+"')" +
//             //	"and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"' )"+
//             " GROUP BY Z.XIAN, ACC.NAME, ACC.MOBILE, ACC.EMAIL, ACC.POSITION,ACC.BUMEN "; 
			
//			sql2="SELECT (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = A.XIAN) xian,"+
//       " A.NAME,a.mobile,a.email,(SELECT I.NAME FROM INDEXS I WHERE I.CODE = a.POSITION AND I.TYPE = 'zw') PS,(SELECT T.DEPTNAME  FROM DEPARTMENT T WHERE T.DEPTCODE = a.BUMEN) BM,COUNT(DISTINCT AA.DIANBIAOID) "+
// " FROM (SELECT S.DIANBIAOID,S.SHEIEBANID, SUM(S.XJBILI) AS BILI, S.ENTRYPENSONNEL,S.ENTRYTIME FROM SBGL S WHERE S.KJKMCODE IS NOT NULL AND S.QCBCODE IS NOT NULL "+
//  "         AND S.ZYMXCODE IS NOT NULL GROUP BY S.DIANBIAOID, S.SHEIEBANID, S.ENTRYPENSONNEL, S.ENTRYTIME) AA, ACCOUNT A "+
// " WHERE AA.BILI = '100' AND A.ACCOUNTNAME = AA.ENTRYPENSONNEL "+whereStr+
// " GROUP BY A.NAME, A.SHI, A.XIAN,a.address,a.mobile,a.email,a.position,a.bumen ";
//			
			//ACC.XIAN, ACC.XIAOQU,ACC.ZIP, ACC.NAME,ACC.SEX,ACC.ACCOUNTNAME, ACC.ROLENAME, 
			//ACC.BUMEN, ACC.POSITION,ACC.MOBILE,ACC.TEL,ACC.EMAIL,ACC.ADDRESS,ACC.MEMO
			
			System.out.println("省级分摊汇总查询详细信息:详细站点负责人："+sql.toString());
			//rs = db.queryAll(sql.toString());
			rs=db.queryAll(sql.toString());
			while(rs.next()){
				ZhanDianForm zdbean=new ZhanDianForm();
				zdbean.setXian(rs.getString(1));
				zdbean.setXiaoqu(rs.getString(2));
				zdbean.setZhan(rs.getString(3));//邮编
				zdbean.setEntrypensonnel(rs.getString(4));//姓名
				zdbean.setSex(rs.getString(5));//性别
				zdbean.setAccname(rs.getString(6));//账号
				zdbean.setRolename(rs.getString(7));//角色
				zdbean.setBUMEN(rs.getString(8));
				zdbean.setPOSITION(rs.getString(9));
				zdbean.setMOBILE(rs.getString(10));
				zdbean.setEMAIL(rs.getString(11));
				zdbean.setBieming(rs.getString(12));
				zdbean.setAddress(rs.getString(13));
				zdbean.setMemo(rs.getString(14));
				zdbean.setXxwc(rs.getString(15));
				list.add(zdbean);
			}
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}		
			if(db!=null){				
				try {
					db.close();
				}catch (DbException de) {
					de.printStackTrace();
				}
			}
		}
		return list;
	}
	//未应用节能技术的标杆基站
	public synchronized ArrayList getPageData21(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select distinct zd.id, zd.zdcode, am.lastdegree,am.thisdegree,to_char(am.lastdatetime,'yyyy-mm-dd') lastdatetime,to_char(am.thisdatetime,'yyyy-mm-dd') thisdatetime,zd.dianfei,to_char(am.startmonth,'yyyy-mm') startmonth,to_char(am.endmonth,'yyyy-mm') endmonth," +
					   "zd.jzname,db.dbid,db.dbname, am.blhdl, e.actualpay, zd.xlx from zhandian zd, dianbiao db, ammeterdegrees am, electricfees e"+
                       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk  and am.ammeterdegreeid = e.ammeterdegreeid_fk AND e.manualauditstatus>='1'  and zd.bgsign = '1' " +
                       ""+whereStr+"  and zd.jnglmk = '0' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("未应用节能技术的标杆基站:"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//应用节能技术的标杆基站
	public synchronized ArrayList getPageData22(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select distinct zd.id, zd.zdcode, am.lastdegree,am.thisdegree,to_char(am.lastdatetime,'yyyy-mm-dd') lastdatetime,to_char(am.thisdatetime,'yyyy-mm-dd') thisdatetime,zd.dianfei,to_char(am.startmonth,'yyyy-mm') startmonth,to_char(am.endmonth,'yyyy-mm') endmonth," +
			   "zd.jzname,db.dbid,db.dbname, am.blhdl, e.actualpay, zd.xlx from zhandian zd, dianbiao db, ammeterdegrees am, electricfees e"+
            " where zd.id = db.zdid and db.dbid = am.ammeterid_fk  and am.ammeterdegreeid = e.ammeterdegreeid_fk AND e.manualauditstatus>='1'  and zd.bgsign = '1' " +
            ""+whereStr+"  and zd.jnglmk = '1' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("应用节能技术的标杆基站:"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//未应用节能技术的非标杆基站
	public synchronized ArrayList getPageData23(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select distinct zd.id, zd.zdcode, am.lastdegree,am.thisdegree,to_char(am.lastdatetime,'yyyy-mm-dd') lastdatetime,to_char(am.thisdatetime,'yyyy-mm-dd') thisdatetime,zd.dianfei,to_char(am.startmonth,'yyyy-mm') startmonth,to_char(am.endmonth,'yyyy-mm') endmonth," +
			   "zd.jzname,db.dbid,db.dbname, am.blhdl, e.actualpay, zd.xlx from zhandian zd, dianbiao db, ammeterdegrees am, electricfees e"+
            " where zd.id = db.zdid and db.dbid = am.ammeterid_fk  and am.ammeterdegreeid = e.ammeterdegreeid_fk AND e.manualauditstatus>='1'  and zd.bgsign = '0' "+whereStr+"  " +
            		"and zd.jnglmk = '0' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("未应用节能技术的非标杆基站:"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//应用节能技术的非标杆基站
	public synchronized ArrayList getPageData24(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select distinct zd.id, zd.zdcode, am.lastdegree,am.thisdegree,to_char(am.lastdatetime,'yyyy-mm-dd') lastdatetime,to_char(am.thisdatetime,'yyyy-mm-dd') thisdatetime,zd.dianfei,to_char(am.startmonth,'yyyy-mm') startmonth,to_char(am.endmonth,'yyyy-mm') endmonth," +
			   "zd.jzname,db.dbid,db.dbname, am.blhdl, e.actualpay, zd.xlx from zhandian zd, dianbiao db,ammeterdegrees am, electricfees e"+
            " where zd.id = db.zdid and db.dbid = am.ammeterid_fk  and am.ammeterdegreeid = e.ammeterdegreeid_fk AND e.manualauditstatus>='1'  and zd.bgsign = '0' "+whereStr+" " +
            		" and zd.jnglmk = '1' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("应用节能技术的非标杆基站:"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getPageData5(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select DISTINCT zd.zdcode,zd.jzname, (case  when zd.shi is not null then  (select agname from d_area_grade where agcode = zd.shi) else ''end) "+
				       " || (case  when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian) else  ''end) "+
				       " || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,"+
				       " (select name from indexs i where i.code = zd.stationtype and i.type = 'stationtype') as stationtype,"+      
				       " (select name from indexs i where i.code = zd.property and i.type = 'ZDSX') as property,"+
				       " (case when zd.bgsign = '0' then '否' else  '是' end) bgsign,"+
				       " (select name from indexs i where i.code = zd.yflx and i.type = 'FWLX') as yflx,"+
				       " (select name from indexs i where i.code = zd.gdfs and i.type = 'GDFS') as gdfs," +
				       "zd.dianfei from zhandian zd,dianbiao db,ammeterdegrees am,electricfees e " +					  
                       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk  AND AM.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND e.manualauditstatus>='1'  and zd.bgsign = '1' "+whereStr+"  and zd.jnglmk = '0' " +
                       		"and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("22222222222"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getPageData6(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select DISTINCT zd.zdcode,zd.jzname, (case  when zd.shi is not null then  (select agname from d_area_grade where agcode = zd.shi) else ''end) "+
				       " || (case  when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian) else  ''end) "+
				       " || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,"+
				       " (select name from indexs i where i.code = zd.stationtype and i.type = 'stationtype') as stationtype,"+      
				       " (select name from indexs i where i.code = zd.property and i.type = 'ZDSX') as property,"+
				       " (case when zd.bgsign = '0' then '否' else  '是' end) bgsign,"+
				       " (select name from indexs i where i.code = zd.yflx and i.type = 'FWLX') as yflx,"+
				       " (select name from indexs i where i.code = zd.gdfs and i.type = 'GDFS') as gdfs," +
				       "zd.dianfei from zhandian zd,dianbiao db,ammeterdegrees am, ELECTRICFEES E " +					  
                       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND AM.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND e.manualauditstatus>='1' and zd.bgsign = '1' " +
                       ""+whereStr+"  and zd.jnglmk = '1' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("22222222222"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getPageData7(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select DISTINCT zd.zdcode,zd.jzname, (case  when zd.shi is not null then  (select agname from d_area_grade where agcode = zd.shi) else ''end) "+
				       " || (case  when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian) else  ''end) "+
				       " || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,"+
				       " (select name from indexs i where i.code = zd.stationtype and i.type = 'stationtype') as stationtype,"+      
				       " (select name from indexs i where i.code = zd.property and i.type = 'ZDSX') as property,"+
				       " (case when zd.bgsign = '0' then '否' else  '是' end) bgsign,"+
				       " (select name from indexs i where i.code = zd.yflx and i.type = 'FWLX') as yflx,"+
				       " (select name from indexs i where i.code = zd.gdfs and i.type = 'GDFS') as gdfs," +
				       "zd.dianfei from zhandian zd,dianbiao db,ammeterdegrees am, ELECTRICFEES E " +					  
                       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND AM.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND e.manualauditstatus>='1'  and zd.bgsign = '0' " +
                       ""+whereStr+"  and zd.jnglmk = '0' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("22222222222"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getPageData8(String whereStr,String loginId,String loginName,String xlxa) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select DISTINCT zd.zdcode,zd.jzname, (case  when zd.shi is not null then  (select agname from d_area_grade where agcode = zd.shi) else ''end) "+
				       " || (case  when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian) else  ''end) "+
				       " || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,"+
				       " (select name from indexs i where i.code = zd.stationtype and i.type = 'stationtype') as stationtype,"+      
				       " (select name from indexs i where i.code = zd.property and i.type = 'ZDSX') as property,"+
				       " (case when zd.bgsign = '0' then '否' else  '是' end) bgsign,"+
				       " (select name from indexs i where i.code = zd.yflx and i.type = 'FWLX') as yflx,"+
				       " (select name from indexs i where i.code = zd.gdfs and i.type = 'GDFS') as gdfs," +
				       "zd.dianfei from zhandian zd,dianbiao db,ammeterdegrees am,ELECTRICFEES E " +					  
                       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk  AND AM.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND e.manualauditstatus>='1' and zd.bgsign = '0' " +
                       ""+whereStr+"  and zd.jnglmk = '1' and xlx='"+xlxa+"' order by zd.jzname";
			System.out.println("22222222222"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//人工站点审核总数
	  public String getCountt(String sheng,
				String shi, String xian, String xiaoqu, String sname,
				String szdcode, String loginName, String jzproperty1,String jztype1,String stationtype,String xuni,String loginId,String lrrq){
			
			StringBuffer cxtj = new StringBuffer();
			if (xuni.equals("3")) {
				cxtj.append(" and (shsign<> '1' or z.provauditstatus<>'1') ");
			} else {
				cxtj.append(" and xuni='" + xuni + "'");
			}
			if (!xiaoqu.equals("0")) {
				cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
			} else if (!xian.equals("0")) {
				cxtj.append(" and z.xian='" + xian + "'");
			} else if (!shi.equals("0")) {
				cxtj.append(" and z.shi='" + shi + "'");
			} else if (!sheng.equals("0")) {
				cxtj.append(" and z.sheng='" + sheng + "'");
			}
			if (sname.length() > 0 && sname != null) {
				cxtj.append(" and z.jzname like '%" + sname + "%'");
			}
			if (szdcode.length() > 0 && szdcode != null) {
				cxtj.append(" and z.id = '" + szdcode + "'");
			}
			//新加
			if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
				cxtj.append(" and z.STATIONTYPE='" + stationtype + "'");
				}
			if (jztype1 != null && !jztype1.equals("") && !jztype1.equals("0")) {
				cxtj.append(" and z.jztype='" + jztype1 + "'");
				}
			if (jzproperty1 != null && !jzproperty1.equals("") && !jzproperty1.equals("0")) {
				cxtj.append(" and z.property='" + jzproperty1 + "'");
			}
			String ss="";
			if (lrrq != null && !lrrq.equals("") && !lrrq.equals("null")) {
				ss=ss+" and to_char(z.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
			}
			String sql = "";
		    String count="";
			DataBase db = new DataBase();
			String fzzdstr="";
			ResultSet rs = null;
			
			
			
			try {
				db.connectDb();
				fzzdstr = getFuzeZdid(db,loginId);
				try {
				
				StringBuffer strall = new StringBuffer();
			      strall.append(" select count(*) "+
				          " from zhandian z where 1=1 and z.qyzt='1' "+cxtj+ss+
				          " and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
				System.out.println("人工站点审核总数"+strall.toString());
				rs = db.queryAll(strall.toString());
	         
	          		 while(rs.next()){
						count=rs.getString(1);
	          		 }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	          }
			}
			catch (Exception de) {
				de.printStackTrace();
			} 
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
			}
			return count;
		}
	  //人工站点审核  审核通过条数,审核未通过条数，未审核条数
	  public String getCountt2(String sheng,
				String shi, String xian, String xiaoqu, String sname,
				String szdcode, String loginName, String jzproperty1,String jztype1,String stationtype,String xuni,String loginId,String str,String lrrq){
			
			StringBuffer cxtj = new StringBuffer();
			if (xuni.equals("3")) {
				//cxtj.append(" and shsign!='1'");//此过滤条件因没有实际意义，且起冲突，去掉。
				cxtj.append("");
			} else {
				cxtj.append(" and xuni='" + xuni + "'");
			}
			if (!xiaoqu.equals("0")) {
				cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
			} else if (!xian.equals("0")) {
				cxtj.append(" and z.xian='" + xian + "'");
			} else if (!shi.equals("0")) {
				cxtj.append(" and z.shi='" + shi + "'");
			} else if (!sheng.equals("0")) {
				cxtj.append(" and z.sheng='" + sheng + "'");
			}
			if (sname.length() > 0 && sname != null) {
				cxtj.append(" and z.jzname like '%" + sname + "%'");
			}
			if (szdcode.length() > 0 && szdcode != null) {
				cxtj.append(" and z.id = '" + szdcode + "'");
			}
			//新加
			if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
				cxtj.append(" and z.STATIONTYPE='" + stationtype + "'");
				}
			if (jztype1 != null && !jztype1.equals("") && !jztype1.equals("0")) {
				cxtj.append(" and z.jztype='" + jztype1 + "'");
				}
			if (jzproperty1 != null && !jzproperty1.equals("") && !jzproperty1.equals("0")) {
				cxtj.append(" and z.property='" + jzproperty1 + "'");
			}
			String ss="";
			if (lrrq != null && !lrrq.equals("") && !lrrq.equals("null")) {
				ss=ss+" and to_char(z.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
			}
			String sql = "";
		    String count="";
			DataBase db = new DataBase();
			String fzzdstr="";
			ResultSet rs = null;
			
			
			
			try {
				db.connectDb();
				fzzdstr = getFuzeZdid(db,loginId);
				try {
				
				StringBuffer strall = new StringBuffer();
			      strall.append(" select count(*) "+
				          " from zhandian z where 1=1 and z.qyzt='1' "+cxtj+str+ss+
				          " and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
				System.out.println("人工站点审核  审核通过条数"+strall.toString());
				rs = db.queryAll(strall.toString());
	         
	          		 while(rs.next()){
						count=rs.getString(1);
	          		 }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	          }
			}
			catch (Exception de) {
				de.printStackTrace();
			} 
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
			}
			return count;
		}
//人工站点审核 查询,
	public synchronized ArrayList getPageData1(int start,String sheng,
			String shi, String xian, String xiaoqu, String sname,
			String szdcode, String loginName, String jzproperty1,String jztype1,String stationtype,String xuni,String loginId,String lrrq,String keyword) throws SQLException {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		if(!keyword.equals("0")){
			cxtj.append(" and z.jzname like '%"+keyword+"%'");
		}
		//新加
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
			cxtj.append(" and z.STATIONTYPE='" + stationtype + "'");
			}
		if (jztype1 != null && !jztype1.equals("") && !jztype1.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype1 + "'");
			}
		if (jzproperty1 != null && !jzproperty1.equals("") && !jzproperty1.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty1 + "'");
		}
		String ss="";
		if (lrrq != null && !lrrq.equals("") && !lrrq.equals("null")) {
			ss=ss+" and to_char(z.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 ");
		strall.append(cxtj.toString());

		try {
			db.connectDb();
			//String fzarea = "", fzzdid = "";
			//fzarea = getFuzeArea(db, loginName);
            
			//fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");
           
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append(" select z.id,z.jzname, case z.qyzt when '0' then '未启用' else '启用' end qyzt"+
					",case z.spzy when '00' then '区域管理员待批' when '01' then '区域主任待批' when '02' then '建维部门待批' when '03' then '运发部待批' else '审批通过' end spzy"+
			            ",(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)"+
			          	"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,"+
		            	"(select t.name from indexs t where t.code=z.stationtype and t.type='stationtype' ) as stationtype"+
		            	",(select t.name from indexs t where t.code=z.property and t.type = 'ZDSX') as property"+		
		            	",(select t.name from indexs t where t.code=z.jztype and t.type = 'ZDLX') as jztype"+
		            	",(select t.name from indexs t where t.code=z.yflx and t.type = 'FWLX' ) as yflx,"+
		            	"(select t.name from indexs t where t.code=z.gdfs and t.type = 'ZDSX'  ) as gdfs"+
		            	",(case when xuni='0' then '否' else '是' end) as xunisign"+
						",z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu,z.shsign,z.PROVAUDITSTATUS from zhandian z where 1=1 "+ cxtj.toString()+
		            	//" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') " +
		                " order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
			
			
//			System.out.println("人工站点审核 查询："+s2.toString());
//			rs = db.queryAll(s2.toString());
//		    Query query=new Query();
//		    list = query.query(rs);
			
			System.out.println("人工站点审核 查询："+s2.toString());
		    s3.append("select count(*)  from (" + s2 + ")");
            System.out.println("站点查询分页"+s3); 
            ResultSet rs3 = null;
            rs3=db.queryAll(s3.toString());
            if(rs3.next()){
            	this.setAllPage((rs3.getInt(1)+9)/10);
            }
           NPageBean nbean = new NPageBean();
           rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
           Query query = new Query();
           list = query.query(rs);
           rs3.close();
			
			
			
		}

		catch (DbException de) {
			de.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//人工站点审核 弹出框 查询
	public synchronized ArrayList getPageDatashow(String zdcode) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String s2="";
		ResultSet rs = null;
		try {
			s2=" select zd.jzname, (case when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian)  else '' end) "+
			 " || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,"+
			" (select t.name  from indexs t where t.code = zd.property and TYPE = 'ZDSX') as property, "+
			  "(select t.name from indexs t where t.code = zd.stationtype and TYPE = 'stationtype') as stationtype, "+
			  "(select t.name from indexs t  where t.code = zd.jztype  and type = 'ZDLX') as jztype,(select t.name from indexs t where t.code = zd.yflx  and t.type = 'FWLX') as yflx, "+
			  "(select t.name from indexs t  where t.code = zd.gdfs  and t.type = 'GDFS') as gdfs, "+
			  " zd.dianfei,zd.jnglmk,zd.xuni,zd.zdcode,zd.g2,zd.g3,zd.yysb,zd.yysbsl,zd.kdsb,zd.kdsbsl,zd.kt1,zd.kt2,zd.zgd "+
			   " from zhandian zd where zd.zdcode='"+zdcode+"'"; 
			System.out.println("人工站点审核查询详细信息"+s2);
		}
		catch (Exception de) {
			de.printStackTrace();
		}
		    try {
		      db.connectDb();	    
		      rs = db.queryAll(s2.toString());
		      Query query=new Query();
		      list = query.query(rs);
		    }catch (DbException de) {
		      de.printStackTrace();
		    } catch (Exception de) {
		      de.printStackTrace();
		    }
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return list;
	}
	//添加人工站点审核导出
/*	public synchronized ArrayList getPageData1dc(String sheng,
		String shi, String xian, String xiaoqu, String sname,
		String szdcode, String loginName, String jztype1,String jzproperty1,String stationtype,String xuni,String loginId) {
	ArrayList list = new ArrayList();
	CTime ct = new CTime();
	StringBuffer cxtj = new StringBuffer();
	if (xuni.equals("3")) {
		cxtj.append(" and shsign!='1'");
	} else {
		cxtj.append(" and xuni='" + xuni + "'");
	}
	if (!xiaoqu.equals("0") && xiaoqu != null && !xiaoqu.equals("")) {
		cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
	} else if (!xian.equals("0") && xian != null && !xian.equals("")) {
		cxtj.append(" and z.xian='" + xian + "'");
	} else if (!shi.equals("0") && shi != null && !shi.equals("")) {
		cxtj.append(" and z.shi='" + shi + "'");
	} else if (!sheng.equals("0") && sheng != null && !sheng.equals("")) {
		cxtj.append(" and z.sheng='" + sheng + "'");
	}
	if (sname.length() > 0 && sname != null && !sname.equals("")) {
		cxtj.append(" and z.jzname like '%" + sname + "%'");
	}
	if (szdcode.length() > 0 && szdcode != null && !sname.equals("")) {
		cxtj.append(" and z.zdcode like '%" + szdcode + "%'");
	}
	//新加
	if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
		cxtj.append(" and z.STATIONTYPE='" + stationtype + "'");
		}
	if (jztype1 != null && !jztype1.equals("") && !jztype1.equals("0")) {
		cxtj.append(" and z.jztype='" + jztype1 + "'");
		}
	if (jzproperty1 != null && !jzproperty1.equals("") && !jzproperty1.equals("0")) {
		cxtj.append(" and z.property='" + jzproperty1 + "'");
	}
	
	
	
	DataBase db = new DataBase();
	ResultSet rs = null;
	StringBuffer strall = new StringBuffer();
	strall.append("select z.id from zhandian z where 1=1 ");
	strall.append(cxtj.toString());

	try {
		db.connectDb();
		String fzarea = "", fzzdid = "";
		fzarea = getFuzeArea(db, loginName);
        
		fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");
       
		StringBuffer s2 = new StringBuffer();
		StringBuffer querystr = new StringBuffer();
		StringBuffer querystr_all = new StringBuffer();
		s2.append("select z.id,z.jzname");
		s2
				.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
		s2
				.append("||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)");
		s2
				.append("||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,");
		s2
				.append("(select t.name from indexs t where t.code=z.stationtype ) as stationtype");
		s2
				.append(",(select t.name from indexs t where t.code=z.property ) as property");			
		s2
				.append(",(select t.name from indexs t where t.code=z.jztype ) as jztype");
		s2
				.append(",(select t.name from indexs t where t.code=z.yflx ) as yflx,");
		s2
				.append("(select t.name from indexs t where t.code=z.gdfs  ) as gdfs");
		s2
				.append(",(case when xuni='0' then '否' else '是' end) as xunisign");
		s2
				.append(",z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu,z.shsign from zhandian z where 1=1 ");
		if (!fzarea.equals("'0'") || !fzzdid.equals("0")) { // 有负责分配负责区域或者负责站点

			if (!fzarea.equals("'0'")) { // 有分配负责区域
				s2.append(" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') ");
				strall.append(" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') ");

			} else {
				s2.append(" and " + fzzdid);
				strall.append(" and " + fzzdid);
			}
		} else { // 没有 负责分配负责区域或者负责站点
			s2.append(" and z.xiaoqu in('0')");
			strall.append(" and z.xiaoqu in('0')");
		}

		s2.append(cxtj.toString());
		if (!fzarea.equals("'0'") && !fzzdid.equals("0")) {
			querystr.append(getQueryStr_zd(fzzdid, s2.toString()));
			querystr_all.append(getQueryStr_zd_all(fzzdid, strall
					.toString()));

		} else {
			s2.append(" order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
			querystr = s2;
			querystr_all.setLength(0);
			querystr_all
					.append("select count(*) from zhandian z where 1=1 ");
			querystr_all.append(cxtj.toString());
		}

		System.out.println("s2:" + s2.toString());
		System.out.println("querystr_all:" + querystr_all.toString());
		querystr_all.setLength(0);
		querystr_all.append("select count(*) from (" + querystr + ")");
		System.out.println(">>>>>>>>>>>>>2" + querystr_all.toString());
		rs = db.queryAll(querystr_all.toString());

		Query query = new Query();
		list = query.query(rs);
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}

	return list;
}	
	*/
    //标杆站点
	public synchronized ArrayList getPageData_bg(int start, String sheng,
			String shi, String xian, String xiaoqu, String sname,
			String szdcode, String loginName, String roles,String loginId,String jztype1,String jzproperty1,String caiji) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		
		//新加
		if (!jztype1.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype1 + "'");}
		if (!jzproperty1.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty1 + "'");}
		if (!caiji.equals("-1")) {
			cxtj.append(" and z.caiji='" + caiji + "'");}
		
		
		if (sname.length() > 0 && sname != null) {
			cxtj.append(" and z.jzname like '%" + sname + "%'");
		}
		if (szdcode.length() > 0 && szdcode != null) {
			cxtj.append(" and z.zdcode like '%" + szdcode + "%'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			//String fzarea = "", fzzdid = "";
			//fzarea = getFuzeArea(db, loginName);

			//fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");
			StringBuffer s2 = new StringBuffer();
			//StringBuffer querystr = new StringBuffer();
			s2.append(" select z.id,sheng,shi,xian,xiaoqu,z.jzname,"+
						"(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)"+
						"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,"+
						"(select t.name from indexs t where t.code=z.property ) as property"+
						",(select t.name from indexs t where t.code=z.jztype ) as jztype"+
						",(select t.name from indexs t where t.code=z.yflx and t.type='FWLX') as yflx,"+
						"(select t.name from indexs t where t.code=z.gdfs  ) as gdfs"+
						",z.jnglmk,z.area,z.dianfei,z.zdcode,z.bgsign from zhandian z where shsign='1' and qyzt='1' "+cxtj.toString()+"" +
						" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
			System.out.println("标杆站点查询;"+s2.toString());
			/*if (!fzarea.equals("'0'") || !fzzdid.equals("0")) { // 有负责分配负责区域或者负责站点

				if (!fzarea.equals("'0'")) { // 有分配负责区域
					s2.append(" and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') ");

				} else {
					s2.append(" and " + fzzdid);
				}
			} else { // 没有 负责分配负责区域或者负责站点
				s2.append(" and z.xiaoqu in('0')");
			}
			s2.append(cxtj.toString());
			// s2.append(" and z.jztype='zdlx08' ");
			System.out.println("负责区域:" + fzarea);
			System.out.println("负责站点:" + fzzdid);
			if (!fzarea.equals("'0'") && !fzzdid.equals("0")) {
				querystr.append(getQueryStr(fzzdid, s2.toString()));

			} else {
				s2.append(" order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
				querystr = s2;
			}*/

			/*
			 * StringBuffer strall = new StringBuffer();
			 * strall.append("select count(*) from zhandian z where z.bgsign='1' "
			 * ); strall.append(cxtj.toString()); rs =
			 * db.queryAll(strall.toString()); if (rs.next()) {
			 * this.setAllPage(rs.getInt(1) / 15 + 1); } NPageBean nbean = new
			 * NPageBean(); rs = db.queryAll(nbean.getQueryStr(start,
			 * s2.toString())); Query query = new Query(); list =
			 * query.query(rs);
			 */
			System.out.println(s2.toString());
			rs = db.queryAll(s2.toString());
			Query query = new Query();
			list = query.query(rs);
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//标杆站点查询总数
	public String getCountt1(int start, String sheng,
			String shi, String xian, String xiaoqu, String sname,
			String szdcode, String loginName, String roles,String loginId,String jztype1,String jzproperty1,String caiji) {
		CTime ct = new CTime();
		String count="";
		StringBuffer cxtj = new StringBuffer();
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		
		//新加
		if (!jztype1.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype1 + "'");}
		if (!jzproperty1.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty1 + "'");}
		if (!caiji.equals("-1")) {
			cxtj.append(" and z.caiji='" + caiji + "'");}
		
		
		if (sname.length() > 0 && sname != null) {
			cxtj.append(" and z.jzname like '%" + sname + "%'");
		}
		if (szdcode.length() > 0 && szdcode != null) {
			cxtj.append(" and z.zdcode like '%" + szdcode + "%'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			String fzarea = "", fzzdid = "";
			//fzarea = getFuzeArea(db, loginName);

			//fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");
			StringBuffer s2 = new StringBuffer();
			//StringBuffer querystr = new StringBuffer();
		
			s2.append(" select count(z.id) from zhandian z where shsign='1' and qyzt='1'  "+cxtj.toString()+" " +
					   " and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')" +
					   " order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");

			System.out.println("标杆站点查询总数:"+s2.toString());
			rs = db.queryAll(s2.toString());
			
			while(rs.next()){
				count=rs.getString(1);
      		 }
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return count;
	}

	private String getQueryStr(String fzzdid, String querystr) {
		StringBuffer s = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		s2.append("select z.id,sheng,shi,xian,xiaoqu,z.jzname");
		s2
				.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
		s2
				.append("||(case when z.xian is not null then (select agname from d_area_grade where agcode=z.xian) else '' end) as szdq,");
		s2
				.append("(select t.name from indexs t where t.code=z.property ) as property");
		s2
				.append(",(select t.name from indexs t where t.code=z.jztype ) as jztype");
		s2
				.append(",(select t.name from indexs t where t.code=z.yflx ) as yflx,");
		s2
				.append("(select t.name from indexs t where t.code=z.gdfs  ) as gdfs");
		s2
				.append(",z.jnglmk,z.area,z.dianfei,z.zdcode,z.bgsign from zhandian z where ");
		s2.append(" z.id in(" + fzzdid + ")");

		s.append("select * from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) order by sheng,shi,xian,xiaoqu,jzname");
		System.out.println("标杆完整语句：" + s.toString());
		return s.toString();
	}

	private String getQueryStr_zd(String fzzdid, String querystr) {
		StringBuffer s = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		s2.append("select z.id,z.jzname");
		s2
				.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
		s2
				.append("||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)");
		s2
				.append("||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,");
		s2
				.append("(select t.name from indexs t where t.code=z.property ) as property");
		s2
				.append(",(select t.name from indexs t where t.code=z.jztype ) as jztype");
		s2
				.append(",(select t.name from indexs t where t.code=z.yflx ) as yflx,");
		s2
				.append("(select t.name from indexs t where t.code=z.gdfs  ) as gdfs");
		s2.append(",(case when xuni='0' then '否' else '是' end) as xunisign");
		s2
				.append(",z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu from zhandian z where ");

		s2.append(" z.id in(" + fzzdid + ")");

		s.append("select * from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) order by sheng,shi,xian,xiaoqu,jzname");
		System.out.println("站点完整语句：" + s.toString());
		return s.toString();
	}

	private String getQueryStr_zd_djtz(String fzzdid, String querystr) {
		StringBuffer s = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		s2.append("select id,jzname,sydate,zdcode,fzr,dianfei");
		s2
				.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
		s2
				.append("||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)");
		s2
				.append("||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq");
		s2.append(",z.sheng,z.shi,z.xian,z.xiaoqu from zhandian z ");

		s2.append(" where z.id in(" + fzzdid + ")");

		s.append("select * from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) order by sheng,shi,xian,xiaoqu,jzname");
		System.out.println("站点完整语句：" + s.toString());
		return s.toString();
	}

	private String getQueryStr_zd_all(String fzzdid, String querystr) {
		StringBuffer s = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		s2.append("select z.id from zhandian z where ");

		s2.append(" z.id in(" + fzzdid + ")");

		s.append("select count(*) from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) ");
		System.out.println("站点完整语句：" + s.toString());
		return s.toString();
	}

private String getFuzeArea(DataBase db, String loginName)
			throws DbException, SQLException {
		StringBuffer s = new StringBuffer();
		ResultSet rs = db
				.queryAll("select agcode from per_area where accountname='"
						+ loginName + "' order by agcode");
		ResultSet rs2 = null;
		while (rs.next()) {
			if (rs.getString(1).length() == 9) {
				s.append("'" + rs.getString(1) + "',");
			} else {
				rs2 = db
						.queryAll("select agcode from d_area_grade where agcode like'"
								+ rs.getString(1) + "%'");
				while (rs2.next()) {
					s.append("'" + rs2.getString(1) + "',");
				}
			}
		}
		s.append("'-1'");

		
		    if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		    }
		return s.toString();
	}

	private String getFuzeZdid(DataBase db, String loginName, String fzarea,
			String jztype) throws DbException, SQLException {
		StringBuffer s = new StringBuffer("0");
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountname='"
						+ loginName
						+ "' and begincode is not null and endcode is not null");
		StringBuffer cxtj = new StringBuffer("(");
		int csnum = 0;
		while (rs.next()) {
			if (csnum == 0) {
				cxtj.append(" (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')");
			} else {
				cxtj.append(" or ( zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')");
			}
			csnum++;
		}
		if (csnum == 0) {
			return s.toString();
		}
		cxtj.append(")");
		if (fzarea.equals("'0'")) { // 没有设置负责区域 返回查询负责站点的查询条件
			s.setLength(0);
			s.append(cxtj.toString());
		} else { // 有设置负责区域 返回没有在负责区域内的负责站点id
			StringBuffer s3 = new StringBuffer();
			s3.append("select id from zhandian where ");
			if (cxtj.equals("()")) {
				s3.append(" id not in (");
			} else {
				s3.append(cxtj.toString());
				s3.append(" and id not in (");
			}

			s3.append("select id from zhandian where xiaoqu in (" + fzarea
					+ ") ");
			if (!jztype.equals("0")) {
				s3.append(" and jztype='" + jztype + "'");
			}
			s3.append(")");
			if (!jztype.equals("0")) {
				s3.append(" and jztype='" + jztype + "'");
			}
			System.out.println(s3.toString());
			rs = db.queryAll(s3.toString());
			StringBuffer ids = new StringBuffer();
			while (rs.next()) {

				ids.append("," + rs.getString(1));

			}
			s.append(ids.toString());
		}
		      if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }

		return s.toString();
	}

	public synchronized int bgjz(String[] id, String loginName, String allids) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		//CTime ctime = new CTime();
		String ids = "";
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				if (i == 0) {
					ids = id[i];
				} else {
					ids += "," + id[i];
				}
			}
		}
		StringBuffer sql = new StringBuffer();
		StringBuffer s2 = new StringBuffer();

		DataBase db = new DataBase();
		try {
			db.connectDb();
			sql.append("update zhandian set bgsign='0' where id in (" + allids
					+ ")");
			s2
					.append("update zhandian set bgsign='1' where id in(" + ids
							+ ")");
			/*
			 * String agcode = this.getFuzeArea(db, loginName);
			 * sql.append("update zhandian set bgsign='0' where xiaoqu in(" +
			 * agcode + ") ");
			 * s2.append("update zhandian set bgsign='1' where xiaoqu in(" +
			 * agcode + ")  and id in(" + ids + ")");
			 */
			db.setAutoCommit(false);
			db.update(sql.toString());
			if (id != null) {
				db.update(s2.toString());
			}
			db.commit();
			db.setAutoCommit(true);

			msg = 1;
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
//站点单价调整
	public synchronized ArrayList getPageData_danjiatiaozheng(int start,
			String sheng, String shi, String xian, String xiaoqu, String sname,
			String szdcode, String loginId,String jztype1,String jzproperty1,String bgsign) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		
		//新加
		if (!jztype1.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype1 + "'");
			}
		
		if (!jzproperty1.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty1 + "'");
			}
		
		if (!bgsign.equals("0")) {
			cxtj.append(" and z.bgsign='" + bgsign + "'");
			}
		
		
		
		if (sname.length() > 0 && sname != null) {
			cxtj.append(" and z.jzname like '%" + sname.trim() + "%'");
		}
		if (szdcode.length() > 0 && szdcode != null) {
			cxtj.append(" and z.zdcode like '%" + szdcode.trim() + "%'");
		}
		
		
	/*	StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where shsign='1' ");
		strall.append(cxtj.toString());*/
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
		
			StringBuffer s2 = new StringBuffer();
			CommonAccountBean bean=new CommonAccountBean();
		
			s2.append("select id,jzname,sydate,zdcode,fzr,dianfei");
			s2.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
			s2.append("||(case when z.xian is not null then (select  distinct agname from d_area_grade where agcode=z.xian) else '' end)");
			s2.append("||(case when z.xiaoqu is not null then (select  distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq");
			s2.append(" from zhandian z where shsign='1' and qyzt='1' and z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"')");
			s2.append(cxtj);
			StringBuffer s3 = new StringBuffer();
			s3.append("Select count(id) from ("+s2.toString()+")");
              System.out.println(s2.toString());
			rs = db.queryAll(s3.toString());
			if (rs.next()) {
				this.setAllPage((rs.getInt(1)+14)/15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
//电表管理新增时的站点查询
	public synchronized ArrayList getPageData_select(int start, String sheng,
			String shi, String xian, String xiaoqu, String sname,
			String szdcode, String loginName,String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		cxtj.append("  shsign='1'");
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		if (sname.length() > 0 && sname != null) {
			cxtj.append(" and z.jzname like '%" + sname + "%'");
		}
		if (szdcode.length() > 0 && szdcode != null) {
			cxtj.append(" and z.id ='" + szdcode + "'");
		}

		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 ");
		strall.append(cxtj.toString());

		DataBase db = new DataBase();
		ResultSet rs = null;
        ResultSet rs3 = null;
		try {
			db.connectDb();
			String fzarea = "", fzzdid = "";
			//fzarea = getFuzeArea(db, loginName);

			//fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");

			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select z.shi,z.id,z.jzname"+
					   ",(case when z.xian is not null then (select  distinct agname from d_area_grade where agcode=z.xian) else '' end)"+
					   "||(case when z.xiaoqu is not null then (select  distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq"+
					   ",(select i.name from indexs i where i.code=z.jztype and i.type='ZDLX')as jztype"+
					   ",(case when z.bgsign='1' then '是' else '否' end ) as sfbg,z.bieming,z.zdcode,z.COUNTBZW"+
					   "  from zhandian z where  "+cxtj.toString()+"  and qyzt='1' and z.SHSIGN='1' and z.PROVAUDITSTATUS='1' and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') " +
					   " order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
			System.out.println("新增电表站点查询:"+s2); 
			 s3.append("select count(*)  from (" + s2 + ")");
             System.out.println("新增电表站点查询分页2:"+s3); 

             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+14)/15);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();
            rs.close();
            db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	// 电表对比分析   选择电表
	public synchronized ArrayList getPageData_select(int start, String sheng,
			String shi, String xian, String xiaoqu, String sname,
			 String loginName,String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		cxtj.append(" and shsign='1'");
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		if (sname.length() > 0 && sname != null) {
			cxtj.append(" and z.jzname like '%" + sname + "%'");
		}
		

		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 ");
		strall.append(cxtj.toString());

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			String fzarea = "", fzzdid = "";
			//fzarea = getFuzeArea(db, loginName);

			//fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");

			StringBuffer s2 = new StringBuffer();
			
			StringBuffer s3 = new StringBuffer();
			s2.append(" select z.id,z.jzname,d.dbid,d.dbname"+
						",(case when z.shi is not null then (select distinct agname from d_area_grade where agcode=z.shi) else '' end)"+
						"||(case when z.xian is not null then (select  distinct agname from d_area_grade where agcode=z.xian) else '' end)"+
						"||(case when z.xiaoqu is not null then (select  distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq"+
						",t.name as jztype"+
						",(case when z.bgsign='1' then '是' else '否' end ) as sfbg,z.bieming,z.zdcode"+
						"  from zhandian z,indexs t,dianbiao d where z.jztype=t.code and  d.zdid=z.id  and d.dbqyzt='1' "+cxtj.toString()+" " +
					    " and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') " +
					    " order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
			
			 s3.append("select count(*)  from (" + s2 + ")");
             System.out.println("站点查询分页2:"+s3); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+14)/15);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	
	public synchronized int addData(String shi, String xian, String xiaoqu,
			String jztype, String jzproperty, String yflx, String jzname,
			String bieming, String address, String bgsign, String jnglmk,
			String gdfs, String area, String memo, String fzr, String sheng,
			String dianfei, String zdcode, String cjr, KZForm kzform) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql
				.append("INSERT INTO ZHANDIAN(SHI,XIAN,JZTYPE,PROPERTY,YFLX,JZNAME,BIEMING,ADDRESS,BGSIGN,JNGLMK,GDFS,AREA,MEMO,FZR,SHENG,CJR,CJTIME,DIANFEI,ZDCODE,XIAOQU)");

		sql.append("VALUES ('" + shi + "','" + xian + "','" + jztype + "','"
				+ jzproperty + "','" + yflx + "','" + jzname + "','" + bieming
				+ "',");
		sql.append("'" + address + "','" + bgsign + "','" + jnglmk + "','"
				+ gdfs + "','" + area + "','" + memo + "','" + fzr + "','"
				+ sheng + "'");
		sql.append(",'" + cjr + "',to_date('" + inserttime
				+ "','yyyy-mm-dd hh24:mi:ss'),'" + dianfei + "','" + zdcode
				+ "','" + xiaoqu + "')");

		// sql.append(",0,'" "')");
		System.out.println(sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateZdCode(db, zdcode) == 0) {
				db.setAutoCommit(false);
				db.update(sql.toString());
				if (jztype.trim().equals("zdlx01")
						|| jztype.trim().equals("zdlx03")) {

					StringBuffer s2 = new StringBuffer();
					s2
							.append("insert into zhandiankz(zdid,ckkd,ysymj,jggs,ysygs,jfgd,sdwd,sffs,lyfs)");
					s2.append(" select id,'" + kzform.getCkkd() + "','"
							+ kzform.getYsymj() + "','" + kzform.getJggs()
							+ "','" + kzform.getYsygs() + "'");
					s2.append(",'" + kzform.getJfgd() + "','"
							+ kzform.getSdwd() + "','" + kzform.getSffs()
							+ "','" + kzform.getLyfs() + "'");
					s2.append(" from zhandian where cjr='" + cjr
							+ "' and cjtime=to_date('" + inserttime
							+ "','yyyy-mm-dd hh24:mi:ss')");
					db.update(s2.toString());
				}
				db.commit();
				db.setAutoCommit(true);
				msg = 1;

			} else {
				msg = 2;
			}

		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	
	public synchronized ArrayList SelectJzocde(String shi,String str){	
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select max(to_number(substr(zdcode,4))) as jzcode from zhandian where shi='"+shi+"' and zdcode like '%"+str+"%'");
		System.out.println(sql.toString());
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		try {
			db.connectDb();
			rs=db.queryAll(sql.toString());
			try {
				while (rs.next()) {
					
					list.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs.close();
				

		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}	
			return list;		
		}
	
	public synchronized int addData(String shi, String xian, String xiaoqu,
			String jztype, String jzproperty, String yflx, String jflx,
			String jzname, String bieming, String address, String bgsign,
			String jnglmk, String gdfs, String area, String memo, String fzr,
			String sheng, String dianfei, String zdcode, String jzxlx,
			String cjr, KZForm kzform, String PUE, String zzjgbm, String xuni,
			String czzd, String gczt, String gcxm, String zdcq, String zdcb,
			String zlfh, String jnjsms, String czzdid, String nhjcdy,
			String ERPbh, String dhID, String zhwgID, String dzywID,
			String edhdl, String longitude, String latitude, String caiji,String jrwtype,String kongtiao,String gsf,String entrypensonnel,String entrytime,String stationtype,String signtypenum) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		 String str="";
	        if(shi.equals("13701")){
	        	str="jn_";
	        }if(shi.equals("13702")){
	        	str="qd_";
	        }if(shi.equals("13703")){
	        	str="zb_";
	        }if(shi.equals("13716")){
	        	str="bz_";
	        }
	        
	        
	        
			ArrayList<String> list=SelectJzocde(shi,str);
			System.out.println("第一次"+shi);
		     	System.out.println("list"+list.toString()+list.size());
		     	
			if(list.get(0)==null){
				System.out.println("第二次"+shi);
				if(shi.equals("13701")){
					 str="jn_10000";
					 str=str.substring(3);
		        }		 
				 if(shi.equals("13702")){
		        	str="qd_10000";	    		
		    		str=str.substring(3);
		    		
		        }
				 if(shi.equals("13703")){
		        	str="zb_10000";	        	
		    		str=str.substring(3);
		        }
				 
				 if(shi.equals("13716")){
		        	str="bz_10000";	        	
		    		str=str.substring(3);
		        }
				 
			}else{			
				str=list.get(0).toString();
			}
			System.out.println(str+"第一个值");
			
			
			int zd=Integer.parseInt(str)+1;
			if(shi.equals("13701")){
				zdcode="jn_"+zd;
	        }if(shi.equals("13702")){
	        	zdcode="qd_"+zd;
	        }if(shi.equals("13703")){
	        	zdcode="zb_"+zd;
	        }if(shi.equals("13716")){
	        	zdcode="bz_"+zd;
	        }
			
			System.out.println("站点加一"+zdcode);
					String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ZHANDIAN(SHI,XIAN,JZTYPE,PROPERTY,YFLX,JZNAME,BIEMING,ADDRESS,BGSIGN,JNGLMK,GDFS,AREA,MEMO,FZR,SHENG,CJR,CJTIME,DIANFEI,ZDCODE,XIAOQU");
		sql.append(",PUE,ZZJGBM,XUNI,CZZD,GCZT,GCXM,ZDCQ,ZDCB,ZLFH,JNJSMS,CZZDID");
		sql.append(",ERPBH,DHID,ZHWGID,DZYWID,LONGITUDE,LATITUDE,JZCODE,CAIJI,XLX,JFLX,JRWTYPE,kongtiao,gsf,entrypensonnel,entrytime,stationtype,signtypenum)");
		sql.append("VALUES ('" + shi + "','" + xian + "','" + jztype + "','"
				+ jzproperty + "','" + yflx + "','" + jzname + "','" + bieming
				+ "',");
		sql.append("'" + address + "','" + bgsign + "','" + jnglmk + "','"
				+ gdfs + "','" + area + "','" + memo + "','" + fzr + "','"
				+ sheng + "'");
		sql.append(",'" + cjr + "',to_date('" + inserttime
				+ "','yyyy-mm-dd hh24:mi:ss'),'" + dianfei + "','" + zdcode
				+ "','" + xiaoqu + "'");
		sql.append(",'" + PUE + "','" + zzjgbm + "','" + xuni + "','" + czzd
				+ "','" + gczt + "'");
		sql.append(",'" + gcxm + "','" + zdcq + "','" + zdcb + "','" + zlfh
				+ "','" + jnjsms + "','" + czzdid + "'");
		sql.append(",'" + ERPbh + "','" + dhID + "','"
				+ zhwgID + "','" + dzywID + "','" + longitude + "','"
				+ latitude + "'");
		sql.append(",'" + zdcode + "','" + caiji + "','" + jzxlx + "','" + jflx
				+ "','"+jrwtype+"','"+kongtiao+"','"+gsf+"','"+entrypensonnel+"',"+s+",'"+stationtype+"','"+signtypenum+"')");

		// sql.append(",0,'" "')");
		System.out.println(sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateZdCode(db, zdcode) == 0) {
				db.setAutoCommit(false);
				db.update(sql.toString());
				System.out.println(jztype + "j基站leixing ");
				if (jztype.trim().equals("zdlx01")
						|| jztype.trim().equals("zdlx03")
						|| jztype.trim().equals("zdlx08")
						|| jztype.trim().equals("zdlx10")
						|| jztype.trim().equals("zdlx11")
						|| jztype.trim().equals("zdlx12")) {

					StringBuffer s2 = new StringBuffer();
					s2
							.append("insert into zhandiankz(zdid,ckkd,ysymj,jggs,ysygs,jfgd,sdwd,sffs,lyfs,gzqk,nhzb,zpsl,zgry,ktsl,pcsl,rll,ljzs,txj,ugs,ysyugs,jnjslx,ydlx)");
					s2.append("values( (select id from zhandian where cjr='"
							+ cjr + "' and cjtime=to_date('" + inserttime
							+ "','yyyy-mm-dd hh24:mi:ss')),'"
							+ kzform.getCkkd() + "','" + kzform.getYsymj()
							+ "','" + kzform.getJggs() + "','"
							+ kzform.getYsygs() + "'");
					s2.append(",'" + kzform.getJfgd() + "','"
							+ kzform.getSdwd() + "','" + kzform.getSffs()
							+ "','" + kzform.getLyfs() + "'");
					s2.append(",'" + kzform.getGzqk() + "','"
							+ kzform.getNhzb() + "','" + kzform.getZpsl()
							+ "','" + kzform.getZgry() + "'");
					s2.append(",'" + kzform.getKtsl() + "','"
							+ kzform.getPcsl() + "','" + kzform.getRll()
							+ "','" + kzform.getLjzs() + "','"
							+ kzform.getTxj() + "'" + ",'" + kzform.getUgs()
							+ "','" + kzform.getYsyugs() + "','"
							+ kzform.getJnjslx() + "'" + ",'"
							+ kzform.getYdlx() + "')");

					System.out.println(s2.toString());
					db.update(s2.toString());
				}
				db.commit();
				db.setAutoCommit(true);
				msg = 1;

			} else {
				msg = 2;
			}

		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	public synchronized int midifyData(String shi, String xian, String xiaoqu,
			String jztype, String jzproperty, String yflx, String jzname,
			String bieming, String address, String bgsign, String jnglmk,
			String gdfs, String area, String memo, String fzr, String sheng,
			String dianfei, String zdcode, String jzxlx, String jflx,
			String id, String cjr, KZForm kzform, String kzid, String PUE,
			String zzjgbm, String xuni, String czzd, String gczt, String gcxm,
			String zdcq, String zdcb, String zlfh, String jnjsms,
			String czzdid, String nhjcdy, String ERPbh, String dhID,
			String zhwgID, String dzywID, String longitude, String latitude,
			String caiji,String edhdl,String jrwtype,String kongtiao,String gsf,String stationtype,String signtypenum) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());

		StringBuffer sql = new StringBuffer();
		sql.append("update zhandian set SHI='" + shi + "',XIAN='" + xian
				+ "',JZTYPE='" + jztype + "',PROPERTY='" + jzproperty
				+ "',YFLX='" + yflx + "',JZNAME='" + jzname + "',BIEMING='"
				+ bieming + "',ADDRESS='" + address + "'");
		sql.append(",BGSIGN='" + bgsign + "',JNGLMK='" + jnglmk + "',GDFS='"
				+ gdfs + "',AREA='" + area + "',MEMO='" + memo + "',FZR='"
				+ fzr + "'");
		sql.append(",SHENG='" + sheng + "',DIANFEI='" + dianfei + "',ZDCODE='"
				+ zdcode + "',xiaoqu='" + xiaoqu + "'");
		// if (xuni.equals("1")) {
		sql.append(",PUE='" + PUE + "',ZZJGBM='" + zzjgbm + "',XUNI='" + xuni
				+ "',CZZD='" + czzd + "',GCZT='" + gczt + "',GCXM='" + gcxm
				+ "',");
		sql.append("ZDCQ='" + zdcq + "',ZDCB='" + zdcb + "',ZLFH='" + zlfh
				+ "',JNJSMS='" + jnjsms + "',CZZDID='" + czzdid + "'");
		sql.append(",NHJCDY='" + nhjcdy + "',ERPBH='" + ERPbh + "',DHID='"
				+ dhID + "',ZHWGID='" + zhwgID + "',DZYWID='" + dzywID + "'");
		sql
				.append(",LONGITUDE='" + longitude + "',LATITUDE='" + latitude
						+ "'");
		sql.append(",jzcode='" + zdcode + "',caiji='" + caiji + "',xlx='"
				+ jzxlx + "',jflx='" + jflx + "',edhdl='" + edhdl + "',jrwtype='" + jrwtype + "'" +
						",kongtiao='" + kongtiao + "',gsf='" + gsf + "',stationtype='" + stationtype + "',signtypenum='"+signtypenum+"'");
		
		sql.append(" where id=" + id);
		System.out.println(sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();

			db.update(sql.toString());
			StringBuffer s2 = new StringBuffer();
			System.out.println("kzid=" + kzid + "//jztype=" + jztype + "<");
			if (kzid.equals("0")) {
				if (jztype.trim().equals("zdlx01")
						|| jztype.trim().equals("zdlx03")
						|| jztype.trim().equals("zdlx08")
						|| jztype.trim().equals("zdlx10")
						|| jztype.trim().equals("zdlx11")
						|| jztype.trim().equals("zdlx12")) {

					s2.setLength(0);
					s2
							.append("insert into zhandiankz(zdid,ckkd,ysymj,jggs,ysygs,jfgd,sdwd,sffs,lyfs,gzqk,nhzb,zpsl,zgry,ktsl,pcsl,rll,ljzs,txj,ugs,ysyugs,jnjslx,ydlx)");
					s2.append(" values(" + id + ",'" + kzform.getCkkd() + "','"
							+ kzform.getYsymj() + "','" + kzform.getJggs()
							+ "','" + kzform.getYsygs() + "'");
					s2.append(",'" + kzform.getJfgd() + "','"
							+ kzform.getSdwd() + "','" + kzform.getSffs()
							+ "','" + kzform.getLyfs() + "'");
					s2.append(",'" + kzform.getGzqk() + "','"
							+ kzform.getNhzb() + "','" + kzform.getZpsl()
							+ "','" + kzform.getZgry() + "'");
					s2.append(",'" + kzform.getKtsl() + "','"
							+ kzform.getPcsl() + "','" + kzform.getRll()
							+ "','" + kzform.getLjzs() + "','"
							+ kzform.getTxj() + "'" + ",'" + kzform.getUgs()
							+ "','" + kzform.getYsyugs() + "','"
							+ kzform.getJnjslx() + "','" + kzform.getYdlx()
							+ "')");
					System.out.println("+" + s2.toString());
					db.update(s2.toString());
				}

			} else {
				if (jztype.trim().equals("zdlx01")
						|| jztype.trim().equals("zdlx03")
						|| jztype.trim().equals("zdlx08")
						|| jztype.trim().equals("zdlx10")
						|| jztype.trim().equals("zdlx11")
						|| jztype.trim().equals("zdlx12")) {

					s2.setLength(0);
					System.out.println("$$$$$$" + kzform.getYsymj());
					s2.append("update zhandiankz set ckkd='" + kzform.getCkkd()
							+ "' ,ysymj='" + kzform.getYsymj() + "' ,jggs='"
							+ kzform.getJggs() + "',");
					s2.append("ysygs='" + kzform.getYsygs() + "' ,jfgd='"
							+ kzform.getJfgd() + "' ,sdwd='" + kzform.getSdwd()
							+ "' ,sffs='" + kzform.getSffs() + "' ,lyfs='"
							+ kzform.getLyfs() + "'");
					s2.append(",gzqk='" + kzform.getGzqk() + "' ,nhzb='"
							+ kzform.getNhzb() + "' ,zpsl='" + kzform.getZpsl()
							+ "' ,zgry='" + kzform.getZgry() + "' ,ktsl='"
							+ kzform.getKtsl() + "'");
					s2.append(",pcsl='" + kzform.getPcsl() + "' ,rll='"
							+ kzform.getRll() + "' ,ljzs='" + kzform.getLjzs()
							+ "',txj='" + kzform.getTxj() + "'" + ",ugs='"
							+ kzform.getUgs() + "',ysyugs='"
							+ kzform.getYsyugs() + "'" + ",jnjslx='"
							+ kzform.getJnjslx() + "',ydlx='"
							+ kzform.getYdlx() + "'");
					s2.append(" where zdid=" + kzid);
					System.out.println("----" + s2.toString());
					db.update(s2.toString());

				} else {
					s2.setLength(0);
					s2.append("delete from zhandiankz where zdid=" + id);
					db.update(s2.toString());
				}
			}
			msg = 1;
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	public synchronized int delData(String id) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();

		StringBuffer sql = new StringBuffer();
		sql.append("delete from zhandian where id=" + id);
		System.out.println(sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateDelZhandian(db, id).equals("0")) {
				return 2;
			}
			db.setAutoCommit(false);
			db.update(sql.toString());
			db.update("delete from zhandiankz where zdid=" + id);
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} catch (SQLException de) {

			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	private String validateDelZhandian(DataBase db, String zdid)
			throws DbException, SQLException {
		String msg = "1";
		ResultSet rs = db
				.queryAll("select id from dianbiao where zdid=" + zdid);
		if (rs.next()) {
			msg = "0";
		}
		      if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		return msg;
	}

	public synchronized String modifyDanJia(String id, String ndianfei) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();
		DecimalFormat df=new DecimalFormat("0.0000");
		if(ndianfei==null||"".equals(ndianfei)){ndianfei="0";}
         ndianfei=df.format(Double.parseDouble(ndianfei));		
		String retstr = "修改站点单价失败！";
		StringBuffer sql = new StringBuffer();
		sql.append("update zhandian set dianfei='" + ndianfei + "' where id="
				+ id);
		// System.out.println(sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			ResultSet rs = db
					.queryAll("select jzname,dianfei from zhandian where id="
							+ id);
			if (rs.next()) {
				retstr = "站点名称为：" + rs.getString(1) + " 的单价由原先的："
						+ rs.getString(2) + "修改为：" + ndianfei;
			}
			db.setAutoCommit(false);
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
		} catch (SQLException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return retstr;
	}

	public synchronized String getChildrenArea_shi(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	public synchronized String getChildrenArea_xian(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	public synchronized String getChildrenArea_xian(String pid, String account) {
		int sublen = pid.length() + 2;
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'");
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			s2.append("select id from per_area t where t.accountname='"
					+ account + "' and t.agcode='" + pid + "'");
			rs = db.queryAll(s2.toString());
			if (!rs.next()) {
				s2.setLength(0);
				s2.append("select id from per_area t where t.accountname='"
						+ account + "' and t.agcode='" + pid.substring(0, 3)
						+ "'");
				ResultSet rs2 = db.queryAll(s2.toString());
				if (!rs2.next()) {
					StringBuffer s3 = new StringBuffer();
					s3.append("select distinct(substr(agcode,0," + sublen
							+ ")) from per_area where accountname='" + account
							+ "'");
					sql.append(" and agcode in(" + s3.toString() + ")");
				}
			}
			System.out.println(sql.toString());
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		System.out.println(list.toString());
		return list.toString();
	}

	public synchronized String getChildrenArea_xiaoqu(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	public synchronized String getChildrenArea_xiaoqu(String pid, String account) {
		int sublen = pid.length() + 2;
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'");

		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			s2.append("select id from per_area t where t.accountname='"
					+ account + "' and t.agcode='" + pid + "'");
			rs = db.queryAll(s2.toString());
			if (!rs.next()) {
				s2.setLength(0);
				s2.append("select id from per_area t where t.accountname='"
						+ account + "' and (t.agcode='" + pid.substring(0, 3)
						+ "' or t.agcode='" + pid.substring(0, 5) + "')");
				ResultSet rs2 = db.queryAll(s2.toString());
				if (!rs2.next()) {

					StringBuffer s3 = new StringBuffer();
					s3.append("select distinct(substr(agcode,0," + sublen
							+ ")) from per_area where accountname='" + account
							+ "'");
					sql.append(" and agcode in(" + s3.toString() + ")");
				}
			}
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	public synchronized String getJizhan(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select zdcode,jzname from zhandian where xiaoqu='" + pid
				+ "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	public synchronized String getShi() {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where agrade='2'";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	private synchronized int validateZdCode(DataBase db, String zdcode) {
		int zdid = 0;

		String sql = "select id from zhandian where zdcode='" + zdcode.trim()
				+ "'";

		ResultSet rs = null;

		try {

			rs = db.queryAll(sql);
			while (rs.next()) {
				zdid = rs.getInt(1);
			}

		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}finally {
		      if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		 }

		return zdid;
	}
	
//人工站点审核
	public synchronized int SHData(String ids, String shsign,String entrypensonnelrg,String entrytimerg) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		StringBuffer sql = new StringBuffer();
		System.out.println(shsign);
		String spzt="",qyzt="";
		if(shsign.equals("区域管理员待批")){
			spzt="01";
			qyzt="0";
		}else if(shsign.equals("区域主任待批")){
			spzt="02";
			qyzt="0";
		}else if(shsign.equals("建维部门待批")){
			spzt="03";qyzt="0";
		}else{
			spzt="04";qyzt="1";
		}
		sql.append("update zhandian set qyzt='"+qyzt+"', spzy='" + spzt + "',MANUALAUDITNAME_STATION='"+entrypensonnelrg+"',MANUALAUDITDATETIME_STATION="+s+" where id ='"+ids+"'");
		System.out.println(sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			msg = 1;
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	//站点明细统计
	public synchronized ArrayList getbean1(String whereStr,String str,String dfzflx,String where,String loginId) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="";
			
			sql="SELECT COUNT(ZD.ID) ZHAN,B.DIANBIAO,C.JIAOFEI,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=B.SHI) AS SHI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.SHI FROM ZHANDIAN ZD,"+
                "(SELECT Z.SHI,COUNT(*) DIANBIAO FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND Z.SHSIGN='1' AND Z.QYZT='1' AND D.DBYT='dbyt01' GROUP BY SHI)B,"+
                " (SELECT Z.SHI,COUNT(*) JIAOFEI, SUM(CASE WHEN YF.CITYAUDIT='1' AND YF.MANUALAUDITSTATUS='1' THEN 1 ELSE 0 END) SHIJI,SUM(DECODE(YF.caiwu, '2', 1)) CAIWU,COUNT(YF.LIUCHENGHAO) LIUCHENGHAO," +
                " SUM(DECODE(YF.AUTOAUDITSTATUS, '1', 1)) ZIDONG,SUM(DECODE(YF.MANUALAUDITSTATUS, '1', 1)) RENGONG"+
                " FROM ZHANDIAN Z,DIANBIAO D,dianfeidandayin YF WHERE Z.ID=D.ZDID AND D.DBID=yf.ammeterid_fk AND Z.SHSIGN='1' AND Z.QYZT='1' AND D.DBYT='dbyt01' "+where+whereStr+" GROUP BY SHI)C"+
                " WHERE B.SHI(+)=ZD.SHI AND C.SHI(+)=ZD.SHI AND ZD.SHSIGN='1' AND ZD.QYZT='1' "+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  GROUP BY B.DIANBIAO,C.JIAOFEI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.SHI";
		 System.out.println("站点明细统计1："+sql.toString()); 
		 rs = db.queryAll(sql.toString());
		
		while(rs.next()){
			ZhanDianForm formbean=new ZhanDianForm();
			formbean.setZhan(rs.getString(1)!=null?rs.getString(1):"");
			formbean.setDianbiao(rs.getString(2)!=null?rs.getString(2):"");
			formbean.setJiaofei(rs.getString(3)!=null?rs.getString(3):"");
			formbean.setShi(rs.getString(4)!=null?rs.getString(4):"");
			formbean.setZidong(rs.getString(5)!=null?rs.getString(5):"");
			formbean.setRengong(rs.getString(6)!=null?rs.getString(6):"");
			formbean.setShiji(rs.getString(7)!=null?rs.getString(7):"");
			formbean.setCaiwu(rs.getString(8)!=null?rs.getString(8):"");
			formbean.setLiuchenghao(rs.getString(9)!=null?rs.getString(9):"");
			formbean.setCode(rs.getString(10)!=null?rs.getString(10):"");
			list.add(formbean);
			}
		
		
		
			/*if(dfzflx.equals("dfzflx01")||dfzflx.equals("dfzflx03")){
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
			sql="select (select d.agname from d_area_grade d where d.agcode=zd.shi)shii,zd.shi,co, cou, coun,counn,counn1,counn2,counn3 from zhandian zd,dianbiao db, dianduview dd,dianfeiview df,(select count(distinct zd.id) co, zd.shi from zhandian zd,dianbiao db where zd.shsign = '1' and zd.id=db.zdid and db.dfzflx='"+dfzflx+"' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.shi) aa,"+
					   "(select count(distinct db.id) cou, zd.shi from dianbiao db, zhandian zd where db.dbyt = 'dbyt01' and dfzflx='"+dfzflx+"'and zd.id = db.zdid and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by shi) bb,"+
					   "(select count(df.ammeterdegreeid_fk) coun, zd.shi from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk  and dd.ammeterdegreeid = df.ammeterdegreeid_fk "+whereStr+" and db.dbyt = 'dbyt01' and db.dfzflx='"+dfzflx+"'and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.shi) cc,"+ 
					   "(select count(df.ammeterdegreeid_fk) counn,zd.shi from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and db.dfzflx='"+dfzflx+"' and df.manualauditstatus = '1' "+whereStr+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.shi) dd,"+
					   "(select count(df.ammeterdegreeid_fk) counn1,zd.shi from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and db.dfzflx='"+dfzflx+"' and df.manualauditstatus = '2' "+whereStr+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.shi) ee,"+
					   "(select count(df.ammeterdegreeid_fk) counn2,zd.shi from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and db.dfzflx='"+dfzflx+"' and df.cityaudit = '1' "+whereStr+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.shi) ff," +
					   "(select count(df.ammeterdegreeid_fk) counn3, zd.shi from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and db.dfzflx = 'dfzflx01' and df.autoauditstatus = '1' "+whereStr+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.shi) ss"+
					   " where zd.id = db.zdid and db.DFZFLX='"+dfzflx+"' and zd.shsign='1' and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and aa.shi(+) = zd.shi and bb.shi(+) = zd.shi and cc.shi(+) = zd.shi and dd.shi(+)=zd.shi and ee.shi(+)=zd.shi and ff.shi(+)=zd.shi and ss.shi(+)=zd.shi"+str+" group by zd.shi, co, cou, coun,counn,counn1,counn2,counn3";
				
			System.out.println("11111111111"+sql.toString());
			}else{
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
				sql="select (select d.agname from d_area_grade d where d.agcode=zd.shi)shii,zd.shi,co, cou, coun,counn1,counn2 from zhandian zd,dianbiao db, yufufeiview pr,(select count(distinct zd.id) co, zd.shi from zhandian zd,dianbiao db where zd.id=db.zdid and db.dfzflx='"+dfzflx+"' and zd.shsign = '1' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')   group by zd.shi) aa,"+
				    "(select count(distinct db.id) cou, zd.shi from dianbiao db, zhandian zd where db.dbyt = 'dbyt01' and db.dfzflx='"+dfzflx+"' and zd.id = db.zdid and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by shi) bb,"+
					"(select count(pr.prepayment_ammeterid) counn1,zd.shi from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid  and pr.FINANCEAUDIT = '1'  and pr.cityaudit = '1' and db.dfzflx='"+dfzflx+"' and db.dbyt = 'dbyt01' "+where+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.shi) cc,"+
				    "(select count(pr.prepayment_ammeterid) coun, zd.shi from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid and db.dfzflx='"+dfzflx+"' "+where+" and db.dbyt = 'dbyt01' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.shi) dd,"+	 
					"(select count(pr.prepayment_ammeterid) counn2,zd.shi from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid  and pr.cityaudit = '1' and db.dfzflx='"+dfzflx+"' and db.dbyt = 'dbyt01' "+where+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.shi) ee"+
					" where zd.id = db.zdid and db.DFZFLX='"+dfzflx+"' and db.dbid = pr.prepayment_ammeterid and db.dbyt = 'dbyt01' and aa.shi(+) = zd.shi and bb.shi(+) = zd.shi and cc.shi(+) = zd.shi and dd.shi(+)=zd.shi and ee.shi(+)=zd.shi "+str+" group by zd.shi, co, cou, coun,counn1,counn2";
				System.out.println("2222222"+sql.toString());
			}
			rs = db.queryAll(sql.toString());			
			Query query = new Query();
			list = query.query(rs);*/
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	
	public synchronized ArrayList getbean2(String whereStr,String str,String dfzflx,String where,String loginId) {
		ArrayList<ZhanDianForm> list = new ArrayList();	
		ArrayList<ZhanDianForm> list1 =new ArrayList();	
		ArrayList list2 = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null,rs1=null;
		try {
			db.connectDb();
			String sql="",sql1="";
			sql="SELECT COUNT(ZD.ID) ZHAN,B.DIANBIAO,C.JIAOFEI,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=B.XIAN) AS XIAN,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.XIAN,A.COUNN3 FROM ZHANDIAN ZD,"+
            "(SELECT ZD.XIAN,COUNT(*) DIANBIAO FROM ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND ZD.SHSIGN='1' AND ZD.QYZT='1' AND D.DBYT='dbyt01' "+str+" GROUP BY XIAN)B,"+
            " (SELECT ZD.XIAN,COUNT(*) JIAOFEI,SUM(DECODE(DF.CITYAUDIT, '1', 1)) SHIJI,SUM(DECODE(DF.MANUALAUDITSTATUS, '2', 1)) CAIWU,COUNT(DF.LIUCHENGHAO) LIUCHENGHAO," +
            " SUM(DECODE(DF.AUTOAUDITSTATUS, '1', 1)) ZIDONG,SUM(DECODE(DF.MANUALAUDITSTATUS, '1', 1)) RENGONG"+
            " FROM ZHANDIAN ZD,DIANBIAO D, DIANFEIVIEW DF,DIANDUVIEW DD WHERE ZD.ID=D.ZDID  AND D.DBID = DD.AMMETERID_FK AND DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK AND ZD.SHSIGN='1' AND ZD.QYZT='1' AND D.DBYT='dbyt01' "+whereStr+" GROUP BY XIAN)C," +
            "(SELECT COUNT(ZD.ID) COUNN3,ZD.XIAN FROM ZHANDIAN ZD, DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01'  AND NOT EXISTS "+
            " (SELECT DISTINCT Z.ZDCODE  FROM ZHANDIAN Z, DIANBIAO DB, DIANDUVIEW DL, DIANFEIVIEW DF WHERE Z.ID = DB.ZDID AND DB.DBID = DL.AMMETERID_FK "+
            " AND DL.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK  AND DB.DBYT = 'dbyt01'  AND Z.ZDCODE = ZD.ZDCODE)  GROUP BY ZD.XIAN	) A"+
            " WHERE B.XIAN(+)=ZD.XIAN AND C.XIAN(+)=ZD.XIAN AND A.XIAN(+)=ZD.XIAN  AND  ZD.SHSIGN='1' AND ZD.QYZT='1' "+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  GROUP BY A.COUNN3,B.DIANBIAO,C.JIAOFEI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.XIAN";
		
			sql1="SELECT COUNT(ZD.ID) ZHAN,B.DIANBIAO,C.JIAOFEI,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=B.XIAN) AS XIAN,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.XIAN,A.COUNN3 FROM ZHANDIAN ZD,"+
            "(SELECT ZD.XIAN,COUNT(*) DIANBIAO FROM ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND ZD.SHSIGN='1' AND ZD.QYZT='1' AND D.DBYT='dbyt01' "+str+" GROUP BY XIAN)B,"+
            " (SELECT ZD.XIAN,COUNT(*) JIAOFEI,SUM(DECODE(YF.CITYAUDIT, '1', 1)) SHIJI,SUM(DECODE(YF.FINANCEAUDIT, '2', 1)) CAIWU,COUNT(YF.LIUCHENGHAO) LIUCHENGHAO,COUNT(*) ZIDONG,COUNT(*) RENGONG"+
            " FROM ZHANDIAN ZD,DIANBIAO D,YUFUFEIVIEW  YF WHERE ZD.ID=D.ZDID  AND D.DBID = YF.PREPAYMENT_AMMETERID AND ZD.SHSIGN='1' AND ZD.QYZT='1' AND D.DBYT='dbyt01' "+where+" GROUP BY XIAN)C," +
            "(SELECT COUNT(ZD.ID) COUNN3,ZD.XIAN FROM ZHANDIAN ZD, DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01'  AND NOT EXISTS "+
            " (SELECT DISTINCT Z.ZDCODE  FROM ZHANDIAN Z, DIANBIAO DB, yufufeiview yf WHERE Z.ID = DB.ZDID AND DB.DBID = YF.PREPAYMENT_AMMETERID "+
            " AND DB.DBYT = 'dbyt01'  AND Z.ZDCODE = ZD.ZDCODE)  GROUP BY ZD.XIAN	) A"+
            " WHERE B.XIAN(+)=ZD.XIAN AND C.XIAN(+)=ZD.XIAN  AND A.XIAN(+)=ZD.XIAN AND  ZD.SHSIGN='1' AND ZD.QYZT='1' "+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  GROUP BY A.counn3,B.DIANBIAO,C.JIAOFEI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.XIAN";
			
			System.out.println("站点明细统计2："+sql.toString());
			System.out.println("站点明细统计3："+sql1.toString());
	        rs = db.queryAll(sql.toString());
	 while(rs.next()){
			ZhanDianForm formbean=new ZhanDianForm();
			formbean.setZhan(rs.getString(1)!=null?rs.getString(1):"");
			formbean.setDianbiao(rs.getString(2)!=null?rs.getString(2):"");
			formbean.setJiaofei(rs.getString(3)!=null?rs.getString(3):"");
			formbean.setXian(rs.getString(4)!=null?rs.getString(4):"");
			formbean.setZidong(rs.getString(5)!=null?rs.getString(5):"");
			formbean.setRengong(rs.getString(6)!=null?rs.getString(6):"");
			formbean.setShiji(rs.getString(7)!=null?rs.getString(7):"");
			formbean.setCaiwu(rs.getString(8)!=null?rs.getString(8):"");
			formbean.setLiuchenghao(rs.getString(9)!=null?rs.getString(9):"");
			formbean.setCode(rs.getString(10)!=null?rs.getString(10):"");
			formbean.setCounn3(rs.getString(11)!=null?rs.getString(11):"");
			list.add(formbean);
			}
	 rs1 = db.queryAll(sql1.toString());
	 while(rs1.next()){
			ZhanDianForm formbean=new ZhanDianForm();
			formbean.setZhan(rs1.getString(1)!=null?rs1.getString(1):"");
			formbean.setDianbiao(rs1.getString(2)!=null?rs1.getString(2):"");
			formbean.setJiaofei(rs1.getString(3)!=null?rs1.getString(3):"");
			formbean.setXian(rs1.getString(4)!=null?rs1.getString(4):"");
			formbean.setZidong(rs1.getString(5)!=null?rs1.getString(5):"");
			formbean.setRengong(rs1.getString(6)!=null?rs1.getString(6):"");
			formbean.setShiji(rs1.getString(7)!=null?rs1.getString(7):"");
			formbean.setCaiwu(rs1.getString(8)!=null?rs1.getString(8):"");
			formbean.setLiuchenghao(rs1.getString(9)!=null?rs1.getString(9):"");
			formbean.setCode(rs1.getString(10)!=null?rs1.getString(10):"");
			formbean.setCounn3(rs1.getString(11)!=null?rs1.getString(11):"");
			list1.add(formbean);
			}
		String name="",name1="",zhan="",dianbiao="",counn333="";	
	 for(ZhanDianForm llii:list){
		 ZhanDianForm formbean=new ZhanDianForm();
		 String namee=llii.getCode();
		 System.out.println("namee:"+namee);
		 for(ZhanDianForm llyy:list1){
			 System.out.println("llyy.getCode():"+llyy.getCode());
			 if(llyy.getCode().equals(namee)){
				 name=llii.getCode();
				 name1=llii.getXian();
				 zhan=llii.getZhan();
				 dianbiao=llii.getDianbiao();
				 int jiaofei=0,jiaofei1=0,jiaofei11=0,
				 zidong=0,zidong1=0,zidong11=0,rengong=0,rengong1=0,rengong11=0,shiji=0,shiji1=0,shiji11=0,caiwu=0,
				 caiwu1=0,caiwu11=0,liuchenghao=0,liuchenghao1=0,liuchenghao11=0,counn=0,counn3=0,counn33=0;
				 if(llii.getJiaofei()!=null&&llii.getJiaofei()!=""){
					  jiaofei1=Integer.parseInt(llii.getJiaofei());
				 }
				 if(llyy.getJiaofei()!=null&&llyy.getJiaofei()!=""){
					  jiaofei11=Integer.parseInt(llyy.getJiaofei());
				 }
				      jiaofei=jiaofei1+jiaofei11;
				 if(llii.getZidong()!=null&&llii.getZidong()!=""){
					  zidong1=Integer.parseInt(llii.getZidong());
				 }
				 if(llyy.getZidong()!=null&&llyy.getZidong()!=""){
					  zidong11=Integer.parseInt(llyy.getZidong());
				 }
				       zidong=zidong1+zidong11;
				 if(llii.getRengong()!=null&&llii.getRengong()!=""){
					  rengong1=Integer.parseInt(llii.getRengong());
				 }if(llyy.getRengong()!=null&&llyy.getRengong()!=""){
					  rengong11=Integer.parseInt(llyy.getRengong());
				 }
				 	   rengong=rengong1+rengong11;
				 if(llii.getShiji()!=null&&llii.getShiji()!=""){
					  shiji1=Integer.parseInt(llii.getShiji());
				 }
				 if(llyy.getShiji()!=null&&llyy.getShiji()!=""){
					  shiji11=Integer.parseInt(llyy.getShiji());
				 }
				 	  shiji=shiji1+shiji11;
				 if(llii.getCaiwu()!=null&&llii.getCaiwu()!=""){
					  caiwu1=Integer.parseInt(llii.getCaiwu());
				 }
				 if(llyy.getCaiwu()!=null&&llyy.getCaiwu()!=""){
					  caiwu11=Integer.parseInt(llyy.getCaiwu());
				 }
				 	  caiwu=caiwu1+caiwu11;
				 if(llii.getLiuchenghao()!=null&&llii.getLiuchenghao()!=""){
					  liuchenghao1=Integer.parseInt(llii.getLiuchenghao());
				 }
				 if(llyy.getLiuchenghao()!=null&&llyy.getLiuchenghao()!=""){
					  liuchenghao11=Integer.parseInt(llyy.getLiuchenghao());
				 }
				 	  liuchenghao=liuchenghao1+liuchenghao11;
				 if(llii.getCounn3()!=null&&llii.getCounn3()!=""){
					  counn3=Integer.parseInt(llii.getCounn3());
				 }
				 if(llyy.getCounn3()!=null&&llyy.getCounn3()!=""){
					  counn33=Integer.parseInt(llyy.getCounn3());
				 }
				      counn=counn3+counn33;
System.out.println("counn:  "+counn+"  counn3:  "+counn3+"  counn33:  "+counn33+"  name1  "+name1);
				formbean.setZhan(zhan);
				formbean.setDianbiao(dianbiao);
				formbean.setJiaofei(jiaofei+"");
				formbean.setXian(name1);
				formbean.setZidong(zidong+"");
				formbean.setRengong(rengong+"");
				formbean.setShiji(shiji+"");
				formbean.setCaiwu(caiwu+"");
				formbean.setLiuchenghao(liuchenghao+"");
				formbean.setCounn3(counn+"");
				formbean.setCode(name);
				list2.add(formbean);
			 }
		 }
	 }
			/*if(dfzflx.equals("dfzflx01")||dfzflx.equals("dfzflx03")){
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
			sql="select (select d.agname from d_area_grade d where d.agcode=zd.xian)xiann,zd.xian,co, cou, coun,counn,counn1,counn2,counn3 from zhandian zd,dianbiao db, dianduview dd,dianfeiview df,(select count(distinct zd.id) co, zd.xian from zhandian zd,dianbiao db where zd.id=db.zdid and db.dfzflx='"+dfzflx+"' and zd.shsign = '1' "+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.xian) aa,"+
					   "(select count(distinct db.id) cou, zd.xian from dianbiao db, zhandian zd where db.dbyt = 'dbyt01' and db.dfzflx='"+dfzflx+"' "+str+" and zd.id = db.zdid and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by xian) bb,"+
					   "(select count(df.ammeterdegreeid_fk) coun, zd.xian from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk  and dd.ammeterdegreeid = df.ammeterdegreeid_fk "+whereStr+" and db.DFZFLX='"+dfzflx+"' and db.dbyt = 'dbyt01' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.xian) cc,"+
					   "(select count(df.ammeterdegreeid_fk) counn,zd.xian from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and df.manualauditstatus = '1' "+whereStr+" and db.DFZFLX='"+dfzflx+"' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.xian) dd,"+
					   "(select count(df.ammeterdegreeid_fk) counn1,zd.xian from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and df.manualauditstatus = '2' "+whereStr+" and db.DFZFLX='"+dfzflx+"' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.xian) ee,"+
					   "(select count(df.ammeterdegreeid_fk) counn2,zd.xian from zhandian zd, dianbiao db, dianduview dd, dianfeiview df where zd.id = db.zdid and db.dbid = dd.ammeterid_fk and dd.ammeterdegreeid = df.ammeterdegreeid_fk and db.dbyt = 'dbyt01' and cityaudit = '1' "+whereStr+" and db.DFZFLX='"+dfzflx+"' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') group by zd.xian) ff," +
					   " (select count(zd.id) counn3,zd.xian from zhandian zd, dianbiao db where zd.id = db.zdid and db.dbyt = 'dbyt01' and db.dfzflx = 'dfzflx01' and NOT EXISTS "+
					   "(select distinct z.zdcode  from zhandian z, dianbiao db, dianduview dl, dianfeiview df where z.id = db.zdid and db.dbid = dl.ammeterid_fk and dl.ammeterdegreeid = df.ammeterdegreeid_fk  and db.dbyt = 'dbyt01' "+
					   "and db.dfzflx = 'dfzflx01'  and z.zdcode = zd.zdcode) and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.xian) ss"+	
					   " where zd.id = db.zdid and db.DFZFLX='"+dfzflx+"' "+whereStr+" and db.dbid = dd.ammeterid_fk(+) and dd.ammeterdegreeid = df.ammeterdegreeid_fk(+) and zd.shsign='1' and db.dbyt = 'dbyt01' and aa.xian(+) = zd.xian and bb.xian(+) = zd.xian and cc.xian(+) = zd.xian and dd.xian(+)=zd.xian and ee.xian(+)=zd.xian and ff.xian(+)=zd.xian and ss.xian(+)=zd.xian  group by zd.xian," +
					   	" co, cou, coun,counn,counn1,counn2,counn3";
				
			System.out.println("3333333333"+sql.toString());
			}else{
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
				sql="select (select d.agname from d_area_grade d where d.agcode=zd.xian)xiann,zd.xian,co, cou, coun,counn1,counn2 from zhandian zd,dianbiao db, yufufeiview pr,(select count(distinct zd.id) co, zd.xian from zhandian zd,dianbiao db where zd.id=db.zdid and db.dfzflx='"+dfzflx+"' and zd.shsign = '1' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')   group by zd.xian) aa,"+
				    "(select count(distinct db.id) cou, zd.xian from dianbiao db, zhandian zd where db.dbyt = 'dbyt01' and zd.id = db.zdid and db.DFZFLX='"+dfzflx+"' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by xian) bb,"+
				    "(select count(pr.prepayment_ammeterid) coun, zd.xian from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid "+where+" and db.DFZFLX='"+dfzflx+"'"+" and db.dbyt = 'dbyt01' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.xian) cc,"+	 
					"(select count(pr.prepayment_ammeterid) counn1,zd.xian from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid and pr.FINANCEAUDIT = '1'  and pr.cityaudit = '1' and  db.DFZFLX='"+dfzflx+"'"+" and db.dbyt = 'dbyt01' "+where+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.xian) dd,"+
					"(select count(pr.prepayment_ammeterid) counn2,zd.xian from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid and pr.cityaudit = '1' and db.DFZFLX='"+dfzflx+"'"+" and db.dbyt = 'dbyt01' "+where+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')  group by zd.xian) ee"+
					" where zd.id = db.zdid and db.DFZFLX='"+dfzflx+"'"+str+" and zd.shsign='1' and db.dbid = pr.prepayment_ammeterid(+) and db.dbyt = 'dbyt01' and aa.xian(+) = zd.xian and bb.xian(+) = zd.xian and cc.xian(+) = zd.xian and dd.xian(+)=zd.xian and ee.xian(+)=zd.xian  group by zd.xian, co, cou, coun,counn1,counn2";
				System.out.println("44444444444"+sql.toString());
			}
			rs = db.queryAll(sql.toString());			
			Query query = new Query();
			list = query.query(rs);*/
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list2;
	}
	public synchronized ArrayList getbean3(String whereStr,String str,String dfzflx,String where,String loginId) {
		//str 县code whereStr人工审核加是审核  where市审核 dfzflx 电费支付类型
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null,rs1=null;
		try {
			db.connectDb();
			String sql="",sql1="";
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
			sql="select zd.zdcode,zd.jzname,(select t.name from indexs t where t.code=zd.stationtype ) as stationtype," +
				"(select t.name from indexs t where t.code=db.dfzflx and t.type='dfzflx') as dfzflx," +
				"(select t.name from indexs t where t.code=info.fkzq and t.type='FKZQ') as fkzq," +
				"count(df.electricfee_id) dfel,to_char(max(df.paydatetime),'yyyy-mm-dd') dfpay," +
				"(case when zd.shi is not null then (select agname from d_area_grade where agcode=zd.shi) else '' end)"+
				"||(case when zd.xian is not null then (select distinct agname from d_area_grade where agcode=zd.xian) else '' end)"+
				"||(case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=zd.xiaoqu) else '' end) as szdq "+
				" from zhandian zd,dianbiao db,zddfinfo info,dianduview dl,dianfeiview df "+
				" where zd.id=db.zdid and zd.id=info.zdid(+) and db.dbid=dl.ammeterid_fk(+) and dl.ammeterdegreeid = df.ammeterdegreeid_fk(+) "+whereStr+"  and zd.qyzt='1' and zd.shsign='1' and db.dbyt='dbyt01' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') "+
				"group by zd.zdcode,zd.jzname,zd.stationtype,db.dfzflx,info.fkzq,zd.shi,zd.xian,zd.xiaoqu order by zd.jzname";
				
			System.out.println("55555555555"+sql.toString());
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
				sql1="select zd.zdcode,zd.jzname,(select t.name from indexs t where t.code=zd.stationtype ) as stationtype," +
				"(select t.name from indexs t where t.code=db.dfzflx and t.type='dfzflx') as dfzflx," +
				"(select t.name from indexs t where t.code=info.fkzq and t.type='FKZQ') as fkzq," +
				" count(pr.prepayment_ammeterid) dfel,to_char(max(pr.paydatetime),'yyyy-mm-dd') dfpay," +
				"(case when zd.shi is not null then (select agname from d_area_grade where agcode=zd.shi) else '' end)"+
				"||(case when zd.xian is not null then (select distinct agname from d_area_grade where agcode=zd.xian) else '' end)"+
				"||(case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=zd.xiaoqu) else '' end) as szdq "+
				" from zhandian zd,dianbiao db,zddfinfo info,yufufeiview pr "+
				" where zd.id=db.zdid and zd.id=info.zdid(+) and db.dbid = pr.prepayment_ammeterid "+where+str+"  and zd.qyzt='1' and zd.shsign='1' and db.dbyt='dbyt01' and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') "+
				"group by zd.zdcode,zd.jzname,zd.stationtype,db.dfzflx,info.fkzq,zd.shi,zd.xian,zd.xiaoqu";
				
				System.out.println("66666666"+sql1.toString());
				rs = db.queryAll(sql.toString());	
				 while(rs.next()){
						ZhanDianForm formbean=new ZhanDianForm();
						formbean.setZdcode(rs.getString(1)!=null?rs.getString(1):"");
						formbean.setJzname(rs.getString(2)!=null?rs.getString(2):"");
						formbean.setStationtype(rs.getString(3)!=null?rs.getString(3):"");
						formbean.setDfzflx(rs.getString(4)!=null?rs.getString(4):"");
						formbean.setFkzq(rs.getString(5)!=null?rs.getString(5):"");
						formbean.setDfel(rs.getString(6)!=null?rs.getString(6):"");
						formbean.setDfpay(rs.getString(7)!=null?rs.getString(7):"");
						formbean.setAddress(rs.getString(8)!=null?rs.getString(8):"");
						list.add(formbean);
				 }
				 rs1 = db.queryAll(sql1.toString());	
				 while(rs.next()){
						ZhanDianForm formbean=new ZhanDianForm();
						formbean.setZdcode(rs1.getString(1)!=null?rs1.getString(1):"");
						formbean.setJzname(rs1.getString(2)!=null?rs1.getString(2):"");
						formbean.setStationtype(rs1.getString(3)!=null?rs1.getString(3):"");
						formbean.setDfzflx(rs1.getString(4)!=null?rs1.getString(4):"");
						formbean.setFkzq(rs1.getString(5)!=null?rs1.getString(5):"");
						formbean.setDfel(rs1.getString(6)!=null?rs1.getString(6):"");
						formbean.setDfpay(rs1.getString(7)!=null?rs1.getString(7):"");
						formbean.setAddress(rs1.getString(8)!=null?rs1.getString(8):"");
						list.add(formbean);
				 }
				
//			rs = db.queryAll(sql.toString());			
//			Query query = new Query();
//			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getbean4(String whereStr,String str,String dfzflx,String where,String loginId) {
		//str 县code whereStr人工审核加是审核  where市审核 dfzflx 电费支付类型
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null,rs1=null;
		try {
			db.connectDb();
			String sql="",sql1="";
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
			sql="select db.dbname,zd.jzname,zd.dianfei,(select t.name from indexs t where t.code=db.dfzflx and t.type='dfzflx') as dfzflx,dl.lastdegree," +
				"dl.thisdegree,dl.actualdegree, df.actualpay,dl.manualauditname as aman,df.manualauditname as eman,dl.floatdegree,df.floatpay," +
				"to_char(dl.lastdatetime,'yyyy-mm-dd') lastdatetime,to_char(dl.thisdatetime,'yyyy-mm-dd') thisdatetime,to_char(dl.startmonth,'yyyy-mm') startmonth,to_char(dl.endmonth,'yyyy-mm') endmonth,to_char(max(df.paydatetime),'yyyy-mm-dd') lasttime"+
				" from zhandian zd,dianbiao db,dianduview dl,dianfeiview df "+
				" where zd.id=db.zdid and db.dbid=dl.ammeterid_fk and dl.ammeterdegreeid=df.ammeterdegreeid_fk "+whereStr+"  and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')"+
				" group by df.ammeterdegreeid_fk,db.dbname,zd.jzname,db.dfzflx,dl.lastdegree,zd.dianfei,dl.actualdegree, df.actualpay," +
				"dl.thisdegree,dl.startmonth,dl.endmonth,dl.lastdatetime,dl.manualauditname,df.manualauditname,dl.floatdegree,df.floatpay,dl.thisdatetime";
				
			System.out.println("777777777"+sql.toString());
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
				
				rs = db.queryAll(sql.toString());		
				 while(rs.next()){
					  DaoFormBean formbean=new DaoFormBean();
						formbean.setDbname(rs.getString(1)!=null?rs.getString(1):"");
						formbean.setJzname(rs.getString(2)!=null?rs.getString(2):"");
						formbean.setDianfei(rs.getString(3)!=null?rs.getString(3):"");
						formbean.setDfzflx(rs.getString(4)!=null?rs.getString(4):"");
						formbean.setLastdegree(rs.getString(5)!=null?rs.getString(5):"");
						formbean.setThisdegree(rs.getString(6)!=null?rs.getString(6):"");
						formbean.setActualdegree(rs.getString(7)!=null?rs.getString(7):"");
						formbean.setActualpay(rs.getString(8)!=null?rs.getString(8):"");
						formbean.setAman(rs.getString(9)!=null?rs.getString(9):"");
						formbean.setEman(rs.getString(10)!=null?rs.getString(10):"");
						formbean.setFloatdegree(rs.getString(11)!=null?rs.getString(11):"");
						formbean.setFloatpay(rs.getString(12)!=null?rs.getString(12):"");
						formbean.setLastdatetime(rs.getString(13)!=null?rs.getString(13):"");
						formbean.setThisdatetime(rs.getString(14)!=null?rs.getString(14):"");
						formbean.setStartmonth(rs.getString(15)!=null?rs.getString(15):"");
						formbean.setEndmonth(rs.getString(16)!=null?rs.getString(16):"");
						formbean.setLasttime(rs.getString(17)!=null?rs.getString(17):"");
						list.add(formbean);
				 }
				 sql1="select db.dbname, zd.jzname,zd.dianfei,(select t.name from indexs t where t.code = db.dfzflx and t.type='dfzflx') as dfzflx,pr.startdegree," +
				 "pr.stopdegree,pr.money, pr.financialoperator,pr.cityauditpensonnel,to_char(pr.startdate,'yyyy-mm-dd') startdate,to_char(pr.enddate,'yyyy-mm-dd') enddate,to_char(pr.startmonth,'yyyy-mm') startmonth,to_char(pr.endmonth,'yyyy-mm') endmonth "+    
			     " from zhandian zd, dianbiao db, yufufeiview pr where zd.id = db.zdid and db.dbid = pr.prepayment_ammeterid"+where+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')"+
			     " group by  db.dbname,zd.jzname,zd.dianfei,db.dfzflx,pr.startdegree,pr.stopdegree,pr.startmonth,  pr.financialoperator,pr.cityauditpensonnel,pr.startdate,pr.enddate,pr.money,pr.endmonth";
			
			System.out.println("88888888888"+sql1.toString());
				 rs1 = db.queryAll(sql1.toString());		
				 while(rs1.next()){
					  DaoFormBean formbean=new DaoFormBean();
						formbean.setDbname(rs1.getString(1)!=null?rs1.getString(1):"");
						formbean.setJzname(rs1.getString(2)!=null?rs1.getString(2):"");
						formbean.setDianfei(rs1.getString(3)!=null?rs1.getString(3):"");
						formbean.setDfzflx(rs1.getString(4)!=null?rs1.getString(4):"");
						formbean.setLastdegree(rs1.getString(5)!=null?rs1.getString(5):"");
						formbean.setThisdegree(rs1.getString(6)!=null?rs1.getString(6):"");
						formbean.setActualpay(rs1.getString(7)!=null?rs1.getString(7):"");
						formbean.setAman(rs1.getString(8)!=null?rs1.getString(8):"");
						formbean.setEman(rs1.getString(9)!=null?rs1.getString(9):"");
						formbean.setLastdatetime(rs1.getString(10)!=null?rs1.getString(10):"");
						formbean.setThisdatetime(rs1.getString(11)!=null?rs1.getString(11):"");
						formbean.setStartmonth(rs1.getString(12)!=null?rs1.getString(12):"");
						formbean.setEndmonth(rs1.getString(13)!=null?rs1.getString(13):"");
						list.add(formbean);
				 }
//			rs = db.queryAll(sql.toString());			
//			Query query = new Query();
//			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getbean5(String whereStr,String str,String dfzflx,String where,String xian,String loginId) {
		//str 县code whereStr人工审核加是审核  where市审核 dfzflx 电费支付类型
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null,rs1=null;
		try {
			db.connectDb();
			String sql="",sql1="";
			
			//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
			sql="select zd.jzname,zd.dianfei,db.dbname,db.dbid,db.beilv,(select t.name from indexs t where t.code = db.linelosstype and t.type='xslx') as linelosstype,db.linelossvalue from zhandian zd,dianbiao db "+
				 " where zd.id=db.zdid  and db.dbyt='dbyt01' and zd.xian='"+xian+"' and NOT EXISTS"+
				 "(select distinct z.zdcode from zhandian z, dianbiao db, dianduview dl, dianfeiview df"+
				 " where z.id = db.zdid and db.dbid = dl.ammeterid_fk and dl.ammeterdegreeid = df.ammeterdegreeid_fk and " +
				 "db.dbyt='dbyt01' and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+" and z.xian='"+xian+"' and z.zdcode=zd.zdcode)";
				 
			sql1="select zd.jzname,zd.dianfei,db.dbname,db.dbid,db.beilv,(select t.name from indexs t where t.code = db.linelosstype and t.type='xslx') as linelosstype,db.linelossvalue from zhandian zd,dianbiao db "+
				" where zd.id=db.zdid  and db.dbyt='dbyt01' and zd.xian='"+xian+"' and NOT EXISTS"+
				"(select distinct z.zdcode from zhandian z, dianbiao db, yufufeiview dl "+
				" where z.id = db.zdid and db.dbid = dl.prepayment_ammeterid and " +
				"db.dbyt='dbyt01' and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"') "+whereStr+" and z.xian='"+xian+"' and z.zdcode=zd.zdcode)";
			 	
			System.out.println("999999999999"+sql.toString());
			System.out.println("111111111111"+sql1.toString());
			rs = db.queryAll(sql.toString());			
			 while(rs.next()){
				  DianBiaoForm formbean=new DianBiaoForm();
					formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
					formbean.setDianfei(rs.getString(2)!=null?rs.getString(2):"");
					formbean.setDbname(rs.getString(3)!=null?rs.getString(3):"");
					formbean.setDbid(rs.getString(4)!=null?rs.getString(4):"");
					formbean.setBeilv(rs.getString(5)!=null?rs.getString(5):"");
					formbean.setLinelosstype(rs.getString(6)!=null?rs.getString(6):"");
					formbean.setLinelossvalue(rs.getString(7)!=null?rs.getString(7):"");					
					list.add(formbean);
			 }
			 rs1 = db.queryAll(sql1.toString());			
			 while(rs1.next()){
				  DianBiaoForm formbean=new DianBiaoForm();
					formbean.setJzname(rs1.getString(1)!=null?rs1.getString(1):"");
					formbean.setDianfei(rs1.getString(2)!=null?rs1.getString(2):"");
					formbean.setDbname(rs1.getString(3)!=null?rs1.getString(3):"");
					formbean.setDbid(rs1.getString(4)!=null?rs1.getString(4):"");
					formbean.setBeilv(rs1.getString(5)!=null?rs1.getString(5):"");
					formbean.setLinelosstype(rs1.getString(6)!=null?rs1.getString(6):"");
					formbean.setLinelossvalue(rs1.getString(7)!=null?rs1.getString(7):"");					
					list.add(formbean);
			 }
			
//			rs = db.queryAll(sql.toString());			
//			Query query = new Query();
//			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {			
				cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";			

		}
		  if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		  }
		System.out.println("负责站点条件："+cxtj);

		return cxtj.toString();
	}
	
	//获取站点最大抄表时间
	public synchronized String getThisTime(String zdid) throws DbException, SQLException {
		
		DataBase db = new DataBase();
		ResultSet rs = null;

		rs = db.queryAll(" select to_char(max(a.thisdatetime),'yyyy-mm-dd') from zhandian z ,dianbiao d ,ammeterdegrees a ,electricfees e "
						+" where z.id = d.zdid and d.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk and z.id = '"
						+ zdid
						+ "' and d.dbyt='dbyt01' and e.CITYZRAUDITSTATUS = '1' ");
		String thistime = new String("");
		//System.out.println(rs);
		if(rs.next()) {			
			thistime = rs.getString(1)!= null ? rs.getString(1):"";			
		}
		  if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		  }
		return thistime;
	}
}
