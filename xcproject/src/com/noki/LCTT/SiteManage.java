package com.noki.LCTT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.SiteFieldBean;
import com.noki.jizhan.SiteModifyBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.ptac.app.electricmanageUtil.Format;

public class SiteManage {
	private int allPage;
	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}
	//查找站点
	public synchronized ArrayList getPageData(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from tbl_lcta_zdinfo z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			System.out.println("负责站点"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select z.id,z.jzname,(select t.name from indexs t where t.code=z.property and t.type='ZDSX') as property," +
					"(select t.name from indexs t where t.code=z.jztype and t.type='ZDLX') as jztype," +
					"(select t.name from indexs t where t.code=z.yflx and t.type='FWLX') as yflx," +
					"(select t.name from indexs t where t.code=z.gdfs  and t.type='GDFS') as gdfs," +
					"(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
					"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,"+
					"z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu,z.shsign ," +
					"(case when xuni='0' then '否' else '是' end) as xunisign,(select t.name from indexs t where t.code=z.stationtype  and t.type='stationtype') as xlx,z.zlfh,z.jlfh,z.powerpole,z.zldy from zhandian z " +
					" where 1=1  "+whereStr+
					" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ORDER BY z.JZNAME");
                    System.out.println("站点查询"+s2);    
			
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("站点查询分页"+s3); 
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
    System.out.println("负责站点条件："+cxtj);
    if(rs != null){
    	rs.close();
    }
    if(db != null){
    	db.close();
    }
    
		return cxtj.toString();
	}
	//===查找站点===
	public String getCountt(String sheng,
			String shi, String xian, String xiaoqu, String sname,
		String szdcode, String loginName, String xuni,String loginId,String jztype1,String jzproperty1,String bgsign){
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
		
		
		String sql = "";
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
			
				//"select count(*)
				 // from zhandian z
				// where 1 = 1
				 //  and z.xiaoqu in
				     //  (select t.agcode from per_area t where t.accountid = '263')
				 //  and xuni = '0'
				  // and z.sheng = '137'"

			StringBuffer strall = new StringBuffer();
		      strall.append(" select count(*) "+
			          " from zhandian z where 1=1 "+cxtj+
			          " and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '263'))"+fzzdstr+")");
			System.out.println("++++++"+strall.toString() + "***********************");
			rs = db.queryAll(strall.toString());
         
          		 while(rs.next()){
					count=rs.getString(1);
          		 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
          }
		}
		catch (DbException de) {
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
	//查询标杆类型信息
	public synchronized ArrayList getSigntypenum(String loginId){
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,NAME,(select name from indexs where code = b.STATIONTYPEID and type = 'stationtype') STATIONTYPEID,(select name from indexs where code = b.REGION and type = 'dytype') REGION,G2,G3,OLT,AIR_CONDITION FROM BENCHMARKING b order by id");
		ResultSet rs = null;
		try {
			db.connectDb();
			rs= db.queryAll(sql.toString());
			Query query=new Query();
		      list = query.query(rs);
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return list;
	}
	
	
	private String getFuzeArea(DataBase db, String loginName)
	throws DbException, SQLException {
    StringBuffer s = new StringBuffer();
    ResultSet rs = db.queryAll("select agcode from per_area where accountname='"+ loginName + "' order by agcode");
    ResultSet rs2 = null;
    while (rs.next()) {
	if (rs.getString(1).length() == 9) {
		s.append("'" + rs.getString(1) + "',");
	} else {
		rs2 = db.queryAll("select agcode from d_area_grade where agcode like'"
						+ rs.getString(1) + "%'");
		while (rs2.next()) {
			s.append("'" + rs2.getString(1) + "',");
		}
	}
  }
s.append("'-1'");
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
	
	
	

	//新添加站点
	public synchronized int addData(String shi, String xian, String xiaoqu,
			String jztype, String jzproperty, String yflx, String jflx,
			String jzname, String bieming, String address, String bgsign,
			String jnglmk, String gdfs, String area, String fzr,
			String sheng, String dianfei, String zdcode, String jzxlx,
			String cjr, SiteFieldBean siteform,String zzjgbm,
			String gczt, String gcxm, String zdcq, String zdcb,
			String zlfh, String czzdid, String nhjcdy,
			String ERPbh, String dhID, String zhwgID, String dzywID,
			String edhdl, String longitude, String latitude, String jrwtype,
			String jlfh,String gsf,String entrypensonnel,String entrytime,
			String stationtype,String dfzflx,String fkfs,String fkzq,String zbdyhh,
			String watchcost,String beilv,String linelosstype,String linelossvalue,
			String dbid,String gldb,String dbyt,String dytype,String g2,
			String g3,String kdsb,String yysb,String zpsl,String zssl,String kdsbsl,String yysbsl,
			String kt1,String kt2,String zgd,String sydate,String sc,String bg,String yy,String xxhzc,
			String jstz,String csds,String cssytime,String qyzt,String lyjhjgs,String gxxx,String ydbid
			,String jskssj,String jsjssj,String xmh,String ygd,String ysd,String dddf,String changjia,
			String jzlx,String shebei,String bbu,String rru,String ydshebei,String gxgwsl,String twgx,
			String bm,String g2xqs,String g3sqsl,String ydgxsbsl,String dxgxsbsl,String kts,String ktzgl,
			String ysjts,String wjts,String yybgkt,String jfsckt,String ktyps,String kteps,String ktps,
			String zybyqlx,String bsdl,String sxd_a,String sxd_b,String sxd_c,String zldy) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		ResultSet rs = null;
		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());//即:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		inserttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
		//System.out.println(inserttime+"   ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
		//String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String jsname="结算电表";//结算类电表名称
		String glname="管理电表";//管理类电表名称
		Random rnd = new Random();//随机数
   	    zdcode = (String) (rnd.nextInt(999999999)+"");//给定范围内的随机数   产生站点的编码
     	dbid = (String) (rnd.nextInt(999999999)+"");   
     	//查询虚拟站点 不是采集点  站点启用 是  站点审核 未通过 市电费管理员审核标识--->0未审核；1：通过；  市   的总数的SQL
     	StringBuffer sqlcount = new StringBuffer("SELECT COUNT(T.ID) COUNTID FROM ZHANDIAN T WHERE T.XUNI='0' AND T.CAIJI = '0' AND T.QYZT='1' AND T.SHSIGN='1' AND T.PROVAUDITSTATUS='1' AND T.SHI='").append(shi).append("'");
     	//根据分类标识和 配置名查找配置 表PERMISSION_CONFIGURATION
     	StringBuffer sqlbzcount = new StringBuffer("SELECT T.ITEMVALUE,T.ITEMVALUE2 FROM PERMISSION_CONFIGURATION T WHERE T.ITEMLLAG='4' AND T.ITEMNAME='").append(shi).append("'");

		DataBase db = new DataBase();
		
		try {
			db.connectDb();
			if (this.validateZdCode(db, zdcode) == 0) {//随机编码可用(站点表中不存在该值id)
				db.connectDb();
				//2015-2-2
				String nowcount = "0",bzzcount = "0",bzzbl = "0";
				rs = null;
				System.out.println("现有站点数量:"+sqlcount.toString());
				rs = db.queryAll(sqlcount.toString());
				try {
					if(rs.next()){//获取现有数量
						nowcount = rs.getString("COUNTID")==null?"0":rs.getString("COUNTID");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				rs=null;
				System.out.println("站点数量标准值:"+sqlbzcount.toString());
				rs = db.queryAll(sqlbzcount.toString());
				try {
					if(rs.next()){//获取配置数量 / 标准值
						bzzcount = rs.getString("ITEMVALUE")==null?"0":rs.getString("ITEMVALUE");
						bzzbl = rs.getString("ITEMVALUE2")==null?"0":rs.getString("ITEMVALUE2");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				double bzz = Math.ceil(Format.str_d(bzzcount.trim()) * (1 + Format.str_d(bzzbl.trim())/100));
				String shengbz ,shengshr ,shengshsj;
				if(Format.str_d(nowcount)>bzz){//到省级
					shengbz = "'0'";//省级审核标志
					shengshr = null;//省级新增站点审核人
					shengshsj = null;//省级新增站点审核时间
				}else{
					shengbz = "'1'";
					shengshr = "'"+entrypensonnel+"'";
					shengshsj = s;
				}

				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO ZHANDIAN(SHI,XIAN,JZTYPE,PROPERTY,YFLX,JZNAME,BIEMING,ADDRESS,BGSIGN,JNGLMK,GDFS,AREA,FZR,SHENG,CJR,CJTIME,DIANFEI,ZDCODE,XIAOQU");
				sql.append(",ZZJGBM,GCZT,GCXM,ZDCQ,ZDCB,ZLFH,CZZDID");
				sql.append(",ERPBH,DHID,ZHWGID,DZYWID,LONGITUDE,LATITUDE,XLX,JFLX,JRWTYPE," +
						"jlfh,gsf,entrypensonnel,entrytime,stationtype,EDHDL,DYTYPE,G2,G3," +
						"KDSB,YYSB,ZPSL,ZSSL,KDSBSL,YYSBSL,KT1,KT2,ZGD,SYDATE,QYZT,ZDBZW,LYJHJGS," +
						"GXXX,JSKSSJ,JSJSSJ,XMH,YGD,YSD,CHANGJIA,JZLX,SHEBEI,BBU,RRU,YDSHEBEI,GXGWSL," +
						"TWGX,BM,G2XQS,G3SQSL,YDGXSBSL,DXGXSBSL,kts,ktzgl,ysjts,wjts,yybgkt,jfsckt,ktyps," +
						"kteps,ktps,SCB,QSDBDL,SHBZW,ZYBYQLX,COUNTBZW,CSLRR,CSLRSJ,PROVAUDITSTATUS,PROVAUDITNAME,PROVAUDITTIME,KTSPS,KTWPS,KTSHIPS,KTJMPS,KTYPZGL,KTYWZGL,KTEPZGL,KTSZGL,KTWZGL,KTSHIPZGL,KTJMZGL,SXD_A,SXD_B,SXD_C,ZLDY)");
				sql.append("VALUES ('" + shi + "','" + xian + "','" + jztype + "','"
						+ jzproperty + "','" + yflx + "','" + jzname + "','" + bieming
						+ "',");
				sql.append("'" + address + "','" + bgsign + "','" + jnglmk + "','"
						+ gdfs + "','" + area + "','" + fzr + "','"
						+ sheng + "'");
				sql.append(",'" + cjr + "',to_timestamp('" + inserttime
						+ "','yyyy-mm-dd hh24:mi:ss:ff6'),'" + dianfei + "','" + zdcode
						+ "','" + xiaoqu + "'");
				sql.append(",'" + zzjgbm + "','" + gczt + "'");
				sql.append(",'" + gcxm + "','" + zdcq + "','" + zdcb + "','" + zlfh
						+ "','" + czzdid + "'");
				sql.append(",'" + ERPbh + "','" + dhID + "','"
						+ zhwgID + "','" + dzywID + "','" + longitude + "','"
						+ latitude + "'");
				sql.append(",'" + jzxlx + "','" + jflx
						+ "','"+jrwtype+"','"+jlfh+"','"+gsf+"','"+entrypensonnel+"',"+s+",'"+stationtype+"','"+edhdl+"'");
				sql.append(",'" + dytype + "','" + g2 + "','" + g3 + "','" + kdsb + "','" + yysb + "','" + zpsl + "','"
						+ zssl + "','" + kdsbsl + "','" + yysbsl + "','" + kt1 + "','" + kt2 + "','" + zgd + "','" + sydate + "'," +
						"'" +qyzt+ "','1','"+lyjhjgs+"','"+gxxx+"',to_date('"+jskssj+"','yyyy-mm-dd'),to_date('"+jsjssj+"','yyyy-mm-dd'),'"+xmh+"'," +
						"'"+ygd+"','"+ysd+"','"+changjia+"','"+jzlx+"','"+shebei+"','"+bbu+"','"+rru+"'," +
						"'"+ydshebei+"','"+gxgwsl+"','"+twgx+"','"+bm+"','"+g2xqs+"','"+g3sqsl+"'," +
						"'"+ydgxsbsl+"','"+dxgxsbsl+"','"+kts+"','"+ktzgl+"','"+ysjts+"','"+wjts+"'," +
						"'"+yybgkt+"','"+jfsckt+"','"+ktyps+"','"+kteps+"','"+ktps+"','0.01','0.01','0','"+zybyqlx+"','0','"+entrypensonnel+"',"+s+","+shengbz+","+shengshr+","+shengshsj+","+siteform.getKtsps()+","+siteform.getKtwps()+","+siteform.getKtships()+","+siteform.getKtjmps()+","+siteform.getKtypzgl()+","+siteform.getKtywzgl()+","+siteform.getKtepzgl()+","+siteform.getKtspzgl()+","+siteform.getKtwpzgl()+","+siteform.getKtshipzgl()+","+siteform.getKtjmzgl()+","+sxd_a+","+sxd_b+","+sxd_c+","+zldy+" )");
				
				System.out.println("添加站点"+sql.toString());
				
				db.setAutoCommit(false);
				db.update(sql.toString());
				db.commit();
				db.setAutoCommit(true);
				if (jztype.trim().equals("zdlx01")//集团报类型
						|| jztype.trim().equals("zdlx03")
						|| jztype.trim().equals("zdlx08")
						|| jztype.trim().equals("zdlx10")
						|| jztype.trim().equals("zdlx11")
						|| jztype.trim().equals("zdlx12")) {

					StringBuffer s2 = new StringBuffer();
					s2.append("insert into zhandiankz(zdid,ckkd,ysymj,jggs,ysygs,jfgd,sdwd,sffs,lyfs,gzqk,nhzb,zpsl,zgry,ktsl,pcsl,rll,ljzs,txj,ugs,ysyugs,jnjslx,ydlx)");
					s2.append("values( (select id from zhandian where cjr='"
							+ cjr + "' and cjtime=to_timestamp('" + inserttime
							+ "','yyyy-mm-dd hh24:mi:ss:ff6')),'"
							+ siteform.getCkkd() + "','" + siteform.getYsymj()
							+ "','" + siteform.getJggs() + "','"
							+ siteform.getYsygs() + "'");
					s2.append(",'" + siteform.getJfgd() + "','"
							+ siteform.getSdwd() + "','" + siteform.getSffs()
							+ "','" + siteform.getLyfs() + "'");
					s2.append(",'" + siteform.getGzqk() + "','"
							+ siteform.getNhzb() + "','" + siteform.getZpsl()
							+ "','" + siteform.getZgry() + "'");
					s2.append(",'" + siteform.getKtsl() + "','"
							+ siteform.getPcsl() + "','" + siteform.getRll()
							+ "','" + siteform.getLjzs() + "','"
							+ siteform.getTxj() + "'" + ",'" + siteform.getUgs()
							+ "','" + siteform.getYsyugs() + "','"
							+ siteform.getJnjslx() + "'" + ",'"
							+ siteform.getYdlx() + "')");

					System.out.println(s2.toString());
					db.update(s2.toString());
				}
					//电费
					StringBuffer sqldf = new StringBuffer();
					sqldf.append("insert into zddfinfo(zdid,fkzq,fkfs,watchcost)");

					sqldf.append(" values( (select id from zhandian where cjr='"   //?????
							+ cjr + "' and zdcode='"+zdcode+"' and cjtime=to_timestamp('" + inserttime
							+ "','yyyy-mm-dd hh24:mi:ss:ff6')),'"
							 + fkzq + "','" + fkfs + "','" + watchcost + "')");
			                   
			        StringBuffer s3 = new StringBuffer();
			        System.out.println(sqldf.toString());
			            db.update(sqldf.toString());
                       //电表
			           System.out.println("电表id："+dbid);
			          
			           	//六条专业线
		                String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values((select id from dianbiao where dbid='"+dbid+"'),"
		                             +" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '01"+"','zylx01','"+sc+"','1',to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0101"+"','100',"+s+",'"+entrypensonnel+"')";//生产
		                String ft2="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values((select id from dianbiao where dbid='"+dbid+"'),"
		                             +" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '02"+"','zylx02','"+yy+"','1',to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0202"+"','100',"+s+",'"+entrypensonnel+"')";//营业
		                String ft3="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values((select id from dianbiao where dbid='"+dbid+"'),"
                                    +" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '03"+"','zylx03','"+bg+"','1',to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0303"+"','100',"+s+",'"+entrypensonnel+"')";//办公
		                String ft4="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values((select id from dianbiao where dbid='"+dbid+"'),"
                                    +" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '04"+"','zylx04','"+xxhzc+"','1',to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0404"+"','100',"+s+",'"+entrypensonnel+"')";//信息化支撑
		                String ft5="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values((select id from dianbiao where dbid='"+dbid+"'),"
                                    +" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '05"+"','zylx05','"+jstz+"','1',to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0505"+"','100',"+s+",'"+entrypensonnel+"')";//建设投资
		                String ft6="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values((select id from dianbiao where dbid='"+dbid+"'),"
                        			+" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '06"+"','zylx06','"+dddf+"','1',to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0606"+"','100',"+s+",'"+entrypensonnel+"')";//建设投资

		                
			            StringBuffer sql2 = new StringBuffer();
			            sql2.append("INSERT INTO DIANBIAO(zdid,dbid,beilv,dfzflx,linelosstype,linelossvalue,dbyt,entrypensonnel,entrytime,dbname,csds,cssytime,bzw,dbqyzt,ydbid,DBZBDYHH,danjia,CHANGEVALUE)");
			            sql2.append("VALUES ((select id from zhandian where cjr='"   //?????
							+ cjr + "' and zdcode='"+zdcode+"' and cjtime=to_timestamp('" + inserttime
							+ "','yyyy-mm-dd hh24:mi:ss:ff6')),'" + dbid + "','" + beilv + "','" +dfzflx+"','"+ linelosstype + "','" +
			            		linelossvalue + "','" + "dbyt01" + "','"+ entrypensonnel + "',to_date('"+entrytime+"','yyyy-mm-dd hh24:mi:ss'),'"+jsname+"','"+csds+"',to_date('"+cssytime+"','yyyy-mm-dd'),'1','1','"+ydbid+"','"+zbdyhh+"','"+dianfei+"','"+bsdl+"')");
			            System.out.println("结算电表"+sql2.toString());
			            
			                db.update(sql2.toString());
			              //为结算电表添加分摊
			                if(dbyt.equals("dbyt01")){
			                //添加生产线	
			                if(!"0".equals(sc)&&sc!=null&&!"".equals(sc)&&!" ".equals(sc)){
			                	System.out.println("生产------"+jcdsql);
			                		db.update(jcdsql.toString());
			                		
			                	}
			                System.out.println("是否为100：："+sc);
			                if(!"100".equals(sc)){
			                	if(!"0".equals(yy)&&yy!=null&&!"".equals(yy)&&!" ".equals(yy)){
			                		System.out.println("营业------"+ft2);
			                		db.update(ft2.toString());
			                		
			                	}
			                	
			                	if(!"0".equals(bg)&&bg!=null&&!"".equals(bg)&&!" ".equals(bg)){
			                		db.update(ft3.toString());
			                		System.out.println("办公------"+ft3);
			                	}
			                	if(!"0".equals(xxhzc)&&xxhzc!=null&&!"".equals(xxhzc)&&!" ".equals(xxhzc)){
			                		db.update(ft4.toString());
			                		System.out.println("信息化支撑------"+ft4);
			                	}
			                	if(!"0".equals(jstz)&&jstz!=null&&!"".equals(jstz)&&!" ".equals(jstz)){
			                		db.update(ft5.toString());
			                		System.out.println("建设投资------"+ft5);
			                	}
			                	if(!"0".equals(dddf)&&dddf!=null&&!"".equals(dddf)&&!" ".equals(dddf)){
			                		db.update(ft6.toString());
			                		System.out.println("代垫电费------"+ft6);
			                	}
			                 }	
			               	 
			               }
			                
			                String sql4="update dianbiao set dbid=(select id from dianbiao where dbid='"+dbid+"') where id=(select id from dianbiao where dbid='"+dbid+"')";
			                 System.out.println("修改电表id-------"+sql4);
			                 rs=null;
			                 try {  	 
			           		rs = db.queryAll(sql4);	
			                 } catch (Exception e) {
			             		e.printStackTrace();
			             	 }
			                
			                 
			                
			                
			                 
			           if(gldb.equals("on")){
			        	   StringBuffer sql3 = new StringBuffer();
				            sql3.append("INSERT INTO DIANBIAO(zdid,dbid,beilv,dfzflx,linelosstype,linelossvalue,dbyt,entrypensonnel,entrytime,dbname,bzw,dbqyzt,danjia,CHANGEVALUE)");
				            sql3.append("VALUES ((select id from zhandian where cjr='"   //?????
								+ cjr + "' and zdcode='"+zdcode+"' and cjtime=to_timestamp('" + inserttime
								+ "','yyyy-mm-dd hh24:mi:ss:ff6')),'" + dbid + "','" + beilv + "','" +dfzflx+"','"+ linelosstype + "','" +
				            		linelossvalue + "','" + "dbyt03" + "','"+ entrypensonnel + "',to_date('"+entrytime+"','yyyy-mm-dd hh24:mi:ss'),'"+glname+"','1','1','"+dianfei+"','"+bsdl+"' )");
				            System.out.println("管理电表："+sql3.toString());
				            
				            db.update(sql3.toString());
			           }
			           String sql6="update dianbiao set dbid=(select id from dianbiao where dbid='"+dbid+"') where id=(select id from dianbiao where dbid='"+dbid+"')";
		                 System.out.println("sql6-------"+sql6);
		                 rs=null;
		                 try {  	 
		           		rs = db.queryAll(sql6);	
		                 } catch (Exception e) {
		             		e.printStackTrace();
		             	 }
		                
		                 
		                 String sql5="update zhandian set zdcode=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from zhandian where zdcode='"+zdcode+"')";
		                 String sql7="insert into scb(id,dbmonth,zdid,scb,MODIFIER,MODIFICATIONTIME,yscb) values('','2013-12',(select id from zhandian" +
		                 		" where cjr='"+cjr+"' and cjtime=to_timestamp('"+inserttime+"','yyyy-mm-dd hh24:mi:ss:ff6')),'0.01','','','')";
		                 System.out.println("sql5-----------"+sql5);
		                 System.out.println("sql7-----------"+sql7);
		                 rs=null;
		                 try {  	 
		           		 rs = db.queryAll(sql5);
		           		 
		                 } catch (Exception e) {
		             		e.printStackTrace();
		             	 }
			           
				db.commit();
				db.update(sql7);
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
				if(rs != null){
				rs.close();
				}
				if(db != null){
				db.close();
				}
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	private synchronized int validateZdCode(DataBase db, String zdcode) {
		int zdid = 0;

		String sql = "select id from zhandian where zdcode='" + zdcode.trim()    //trim()此字符串移除了前导和尾部空白的副本；如果没有前导和尾部空白，则返回此字符串。

				+ "'";

		ResultSet rs = null;

		try {

			rs = db.queryAll(sql);
			while (rs.next()) {
				zdid = rs.getInt(1);
			}
			if(rs != null){
				rs.close();
			}
			if(db != null){
				db.close();
			}
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		return zdid;
	}

	
    //让电表id自动生成   查出电表的最大DBID 然后+1 
    public synchronized ArrayList<String> SelectDBID(String shi,String str){	
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select max(to_number(substr(b.dbid,4))) as dbid from dianbiao b,zhandian z  where shi='"+shi+"' and b.zdid=z.id and b.dbyt<>'dbyt02' and dbid like '%"+str+"%'");
		
		System.out.println(sql.toString());
		ArrayList<String> list = new ArrayList<String>();
		DataBase db = new DataBase();
		try {
			db.connectDb();
			rs=db.queryAll(sql.toString());
			try {
				while (rs.next()) {
					System.out.println(rs.getString(1));
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
			
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}	
			return list;		
		}
    //修改站点
    public synchronized int midifyData(String shi,String xian,String xiaoqu,String jztype,String jzproperty,
    		String yflx,String jflx,String jzname,String bieming,String address,String bgsign,String jnglmk,String gdfs,
    		String area,String fzr,String accountSheng,String dianfei,String zdcode,String jzxlx,
    		String accountid,SiteFieldBean kzform,String zzjgbm,String gczt,String gcxm,
    		String zdcq, String zdcb,String zlfh,String czzdid,String nhjcdy,String ERPbh,String dhID,
    		String zhwgID, String dzywID,String edhdl,String longitude,String latitude,String jrwtype,String jlfh,String gsf,
    		String entrypensonnel,String entrytime,String stationtype,String dfzflx,String fkfs,String fkzq,String zbdyhh,
    		String watchcost,String beilv,String linelosstype,String linelossvalue,String dbid,String dbid1,String gldb,String dbyt,String id,
    		String kzid,String dytype,String g2,String g3,String kdsb,String yysb,String zpsl,String zssl,
    		String kdsbsl,String yysbsl,String kt1,String kt2,String zgd,String sydate,String sc,String bg,String yy,String xxhzc,
    		String jstz,String dbidft,String csds,String cssytime,String qyzt,String lyjhjgs,String gxxx,String ydbid
    		,String jskssj,String jsjssj,String xmh,String ygd,String ysd,String dddf,String changjia,String jzlx,String shebei,
    		String bbu,String rru,String ydshebei,String gxgwsl,String twgx,String bm,String g2xqs,String g3sqsl,String ydgxsbsl,
    		String dxgxsbsl,String ysjts,String wjts,String yybgkt,String jfsckt,String ktyps,String kteps,String ktps,String zybyqlx,String bsdl,String kts,String ktzgl,String zldy,String powerpole) {
		// birthday = birthday.length()>0?birthday:null;
    	ResultSet rs = null;
    	String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
    	int msg = 0;
		CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update zhandian  set ZLDY='"+zldy+"',POWERPOLE='"+powerpole+"', SHI='" + shi + "',XIAN='" + xian
				+ "',JZTYPE='" + jztype + "',PROPERTY='" + jzproperty
				+ "',YFLX='" + yflx + "',JZNAME='" + jzname + "',BIEMING='"
				+ bieming + "',ADDRESS='" + address + "'");
		sql.append(",BGSIGN='" + bgsign + "',JNGLMK='" + jnglmk + "',GDFS='"
				+ gdfs + "',AREA='" + area + "',FZR='"
				+ fzr + "'");
		//CJTIME=to_date('" + inserttime + "','yyyy-mm-dd hh24:mi:ss'),  jzcode='" + zdcode + "',    edhdl    CJR='" + cjr + "',  用不用添加
		sql.append(",SHENG='" + accountSheng + "',DIANFEI='" + dianfei + "',xiaoqu='" + xiaoqu + "'");
		sql.append(",ZZJGBM='" + zzjgbm + "',GCZT='" + gczt + "',GCXM='" + gcxm+ "',");
		sql.append("ZDCQ='" + zdcq + "',ZDCB='" + zdcb + "',ZLFH='" + zlfh
				+ "',CZZDID='" + czzdid + "'");
		sql.append(",ERPBH='" + ERPbh + "',DHID='"
				+ dhID + "',ZHWGID='" + zhwgID + "',DZYWID='" + dzywID + "'");
		sql.append(",LONGITUDE='" + longitude + "',LATITUDE='" + latitude
						+ "'");
		sql.append(",xlx='"
				+ jzxlx + "',jflx='" + jflx + "',edhdl='" + edhdl + "',jrwtype='" + jrwtype + "'" +
						",jlfh='" + jlfh + "',gsf='" + gsf + "',entrypensonnel='" + entrypensonnel + "',entrytime=" + s + ",stationtype='" + stationtype + "'");
		sql.append(",dytype='" + dytype + "',g2='" + g2 + "',g3='" + g3 + "',kdsb='" + kdsb + "',yysb='" + yysb + "',zpsl='" 
				+ zpsl + "',zssl='" + zssl + "',kdsbsl='" + kdsbsl + "',yysbsl='" + yysbsl + "'," +
						"kt1='" + kt1 + "',kt2='" + kt2 + "',zgd='" + zgd + "',sydate='" + sydate + "'," +
				"qyzt='" +qyzt+ "',zdbzw='1',lyjhjgs='"+lyjhjgs+"',gxxx='"+gxxx+"',jskssj=to_date('"+jskssj+"','yyyy-mm-dd'),jsjssj=to_date('"+jsjssj
				+"','yyyy-mm-dd'),xmh='"+xmh+"',ygd='"+ygd+"',ysd='"+ysd+"',changjia='"+changjia+"',jzlx='"+jzlx+"',shebei= '"+shebei
				+"',bbu='"+bbu+"',rru= '"+rru+"',ydshebei= '"+ydshebei+"',gxgwsl= '"+gxgwsl+"',twgx= '"+twgx+"',bm ='"+bm
				+"' ,g2xqs='"+g2xqs+"',g3sqsl='"+g3sqsl+"',ydgxsbsl='"+ydgxsbsl+"',dxgxsbsl='"+dxgxsbsl+"',ysjts='"+ysjts
				+"',wjts='"+wjts+"',yybgkt='"+yybgkt+"',jfsckt='"+jfsckt+"',ktyps='"+ktyps+"',kteps=' "+kteps+"',ktps='"+ktps
				+"',ZYBYQLX='"+zybyqlx+"'");
		sql.append(",KTS='"+kts+"',KTZGL='"+ktzgl+"'");
		sql.append(" where id=" + id);
		System.out.println("修改站点信息"+sql.toString());
		
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
			
			//电费附属信息
			StringBuffer sqldf1 = new StringBuffer();
			StringBuffer sqldf = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			sqldf1.append("select * from zddfinfo where zdid="+id);//查询是否有数据
			s3.append("insert into zddfinfo(zdid,fkzq,fkfs,watchcost)");//如果没有数据就插入
			s3.append(" values( '"+id+"','"+fkzq + "','" + fkfs + "','" + watchcost + "')");
			
			sqldf.append("update zddfinfo set fkzq='"+fkzq+"',fkfs='"+fkfs+"',watchcost='"+watchcost+"'");//有数据就修改
            sqldf.append(" where zdid="+id);
            
            rs = db.queryAll(sqldf1.toString());
            try {
				if(rs.next()){
					 System.out.println("修改电费信息："+sqldf.toString());
				     db.update(sqldf.toString());
				}else{
					System.out.println("插入电费附属信息"+s3.toString());
		            db.update(s3.toString());	
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	                   
	        
	      
			//电表zdid,dbid, ,dbyt)
	         StringBuffer sql2 = new StringBuffer();
	            sql2.append("UPDATE DIANBIAO SET BEILV='"+beilv+"',dfzflx='"+dfzflx+"',linelosstype='"+linelosstype+"'," +
	            		"linelossvalue='"+linelossvalue+"',csds='"+csds+"',cssytime=to_date('"+cssytime+"','yyyy-mm-dd'),bzw='1',ydbid='"+ydbid
	            		+"',DBZBDYHH='"+zbdyhh+"',danjia='"+dianfei+"',CHANGEVALUE='"+bsdl+"'");
			    sql2.append(" where zdid='"+id+"' and dbyt='dbyt01' and dbid='"+dbid1+"'");
			    System.out.println("修改电表信息："+sql2.toString());
			    db.update(sql2.toString());
			   // db.commit(); 
			    //生产数
			    SiteModifyBean count=new SiteModifyBean();
			    StringBuffer tj1 = new StringBuffer();
			    tj1.append("select count(*) from sbgl where SHUOSHUZHUANYE='zylx01' and dianbiaoid='"+dbidft+"'");
			    rs = db.queryAll(tj1.toString()); 
			    try {
					if(rs.next()){
						count.setCountsc(rs.getInt(1));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int zs1=count.getCountsc();
				System.out.println("1数量："+zs1);
			    //分摊 营业数
			   
				 StringBuffer tj2 = new StringBuffer();
				    tj2.append("select count(*)from sbgl where SHUOSHUZHUANYE='zylx02' and dianbiaoid='"+dbidft+"'");
				    rs = db.queryAll(tj2.toString());
				    System.out.println("结果"+rs);
				    try {
						if(rs.next()){
							count.setCountyy(rs.getInt(1));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int zs2=count.getCountyy();
					System.out.println("222222数量"+zs2);
			    
			    //办公数
					 StringBuffer tj3 = new StringBuffer();
					    tj3.append("select count(*)from sbgl where SHUOSHUZHUANYE='zylx03' and dianbiaoid='"+dbidft+"'");
					    rs = db.queryAll(tj3.toString());
					    //System.out.println("结果"+rs);
					    try {
							if(rs.next()){
								count.setCountbg(rs.getInt(1));
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int zs3=count.getCountbg();
						System.out.println("333数量"+zs3);
			    //信息化支撑数
					 StringBuffer tj4 = new StringBuffer();
					   tj4.append("select count(*)from sbgl where SHUOSHUZHUANYE='zylx04' and dianbiaoid='"+dbidft+"'");
						  rs = db.queryAll(tj4.toString());
						   // System.out.println("结果"+rs);
						    try {
								if(rs.next()){
									count.setCountxxhzc(rs.getInt(1));
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int zs4=count.getCountxxhzc();
							System.out.println("444数量"+zs4);
							
				//建设投资数
							StringBuffer tj5 = new StringBuffer();
						    tj5.append("select count(*)from sbgl where SHUOSHUZHUANYE='zylx05' and dianbiaoid='"+dbidft+"'");
						    rs = db.queryAll(tj5.toString());
						    //System.out.println("结果"+rs);
						    try {
								if(rs.next()){
									count.setCountjstz(rs.getInt(1));
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int zs5=count.getCountjstz();
							System.out.println("55数量"+zs5);
				//代垫电费数
							StringBuffer tj6 = new StringBuffer();
						    tj6.append("select count(*)from sbgl where SHUOSHUZHUANYE='zylx06' and dianbiaoid='"+dbidft+"'");
						    rs = db.queryAll(tj6.toString());
						    //System.out.println("结果"+rs);
						    try {
								if(rs.next()){
									count.setCountdddf(rs.getInt(1));
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int zs6=count.getCountdddf();
							System.out.println("666数量"+zs6);	
					//如果生产为100 其他分摊数据条数为1 就删除  其他专业的分摊
							
		/*			if(sc.equals("100") && (zs2>=1 || zs3>=1 || zs4>=1|| zs5>=1)){//再议有没有用
						
						StringBuffer schu = new StringBuffer();
						schu.append("delete sbgl where (SHUOSHUZHUANYE='zylx05' or SHUOSHUZHUANYE='zylx04' or SHUOSHUZHUANYE='zylx03' or SHUOSHUZHUANYE='zylx02') and dianbiaoid='"+dbidft+"'");
						 System.out.println("删除其他专业的分摊 "+schu.toString());
						db.update(schu.toString());
						  //db.commit();
					}*/
					
			    //分摊 生产 条数为0就插入  否则就修改11111111111111
			    if(zs1==0&&!"0".equals(sc)){
			    	String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values('"+dbidft+"',"
                    +" to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '01"+"','zylx01','"+sc+"','1',to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '0101"+"','100',"+s+",'"+entrypensonnel+"')";//生产
			    	System.out.println("插入生产"+jcdsql.toString());
			    	db.update(jcdsql.toString());
			    	//db.commit();
			    }else if(zs1!=0&&"0".equals(sc)){
			    	StringBuffer bgft = new StringBuffer();
			    	bgft.append("delete sbgl where SHUOSHUZHUANYE='zylx01' and dianbiaoid='"+dbidft+"'");
					 System.out.println("删除生产分摊 "+bgft.toString());
					 db.update(bgft.toString());
			    	
			    }else if(zs1!=0&&!"0".equals(sc)){
				StringBuffer ft1 = new StringBuffer();
				ft1.append("update sbgl set dbili='"+sc+"',FTBZW='1',ENTRYTIME="+s+",ENTRYPENSONNEL='"+entrypensonnel+"'  where SHUOSHUZHUANYE='zylx01' and dianbiaoid='"+dbidft+"'");
				db.update(ft1.toString());
				System.out.println("分摊 生产"+ft1.toString());
				 }
				//分摊 营业     if(zs2==0&&yy!="0"){插入}else if(zs2!=0&&"0".equals(yy)){删除}else修改}
				    if(zs2==0&&!"0".equals(yy)){
				    	String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values('"+dbidft+"',"
	                    +" to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '02"+"','zylx02','"+yy+"','1',to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '0202"+"','100',"+s+",'"+entrypensonnel+"')";//营业
				    	System.out.println("插入营业"+jcdsql.toString());
				    	db.update(jcdsql.toString());
				    	//db.commit();
				    }else if(zs2!=0&&"0".equals(yy)){
				    	StringBuffer bgft = new StringBuffer();
				    	bgft.append("delete sbgl where SHUOSHUZHUANYE='zylx02' and dianbiaoid='"+dbidft+"'");
						 System.out.println("删除营业分摊 "+bgft.toString());
						 db.update(bgft.toString());
				    	
				    }else if(zs2!=0&&!"0".equals(yy)){   
				     StringBuffer ft2 = new StringBuffer();
				     ft2.append("update sbgl set dbili='"+yy+"',FTBZW='1',ENTRYTIME="+s+",ENTRYPENSONNEL='"+entrypensonnel+"'  where SHUOSHUZHUANYE='zylx02' and dianbiaoid='"+dbidft+"'");
				     db.update(ft2.toString());
				    System.out.println("分摊 营业"+ft2.toString());
				    //db.commit();
				    }
				//分摊 办公
				   
				    if(zs3==0&&!"0".equals(bg)){
				    	String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values('"+dbidft+"',"
	                    +" to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '03"+"','zylx03','"+bg+"','1',to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '0303"+"','100',"+s+",'"+entrypensonnel+"')";//办公
				    	System.out.println("插入办公"+jcdsql.toString());
				    	db.update(jcdsql.toString());
				    	//db.commit();
				    }else if(zs3!=0&&"0".equals(bg)){
				    	StringBuffer bgft = new StringBuffer();
				    	bgft.append("delete sbgl where SHUOSHUZHUANYE='zylx03' and dianbiaoid='"+dbidft+"'");
						 System.out.println("删除办公分摊 "+bgft.toString());
						 db.update(bgft.toString());
				    	
				    }else if(zs3!=0&&!"0".equals(bg)){   
				    	StringBuffer ft3 = new StringBuffer();
				    	ft3.append("update sbgl set dbili='"+bg+"',FTBZW='1',ENTRYTIME="+s+",ENTRYPENSONNEL='"+entrypensonnel+"'  where SHUOSHUZHUANYE='zylx03' and dianbiaoid='"+dbidft+"'");
				    	db.update(ft3.toString());
				    	System.out.println("分摊 办公"+ft3.toString());
				    	//db.commit();
				 }
				//分摊 信息化支撑
				   
				    if(zs4==0&&!"0".equals(xxhzc)){
				    	String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values('"+dbidft+"',"
	                    +" to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '04"+"','zylx04','"+xxhzc+"','1',to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '0404"+"','100',"+s+",'"+entrypensonnel+"')";//信息化支撑
				    	System.out.println("信息化支撑"+jcdsql.toString());
				    	db.update(jcdsql.toString());
				    	//db.commit();
				    }else if(zs4!=0&&"0".equals(xxhzc)){
				    	StringBuffer bgft = new StringBuffer();
				    	bgft.append("delete sbgl where SHUOSHUZHUANYE='zylx04' and dianbiaoid='"+dbidft+"'");
						 System.out.println("删除信息化支撑分摊 "+bgft.toString());
						 db.update(bgft.toString());
				    	
				    }else if(zs4!=0&&!"0".equals(xxhzc)){   
				    	StringBuffer ft4 = new StringBuffer();
				    	ft4.append("update sbgl set dbili='"+xxhzc+"',FTBZW='1',ENTRYTIME="+s+",ENTRYPENSONNEL='"+entrypensonnel+"'  where SHUOSHUZHUANYE='zylx04' and dianbiaoid='"+dbidft+"'");
				    	db.update(ft4.toString());
				    	System.out.println("分摊信息化支撑"+ft4.toString());
				    	//db.commit();
				 }
				//分摊 建设投资
				    
				    if(zs5==0&&!"0".equals(jstz)){
				    	String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values('"+dbidft+"',"
	                    +" to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '05"+"','zylx05','"+jstz+"','1',to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '0505"+"','100',"+s+",'"+entrypensonnel+"')";//建设投资
				    	System.out.println("建设投资插入"+jcdsql.toString());
				    	db.update(jcdsql.toString());
				    	//db.commit();
				    }else if(zs5!=0&&"0".equals(jstz)){
				    	StringBuffer bgft = new StringBuffer();
				    	bgft.append("delete sbgl where SHUOSHUZHUANYE='zylx05' and dianbiaoid='"+dbidft+"'");
						 System.out.println("删除建设投资分摊 "+bgft.toString());
						 db.update(bgft.toString());
				    	
				    }else if(zs5!=0&&!"0".equals(jstz)){   
				    	StringBuffer ft5 = new StringBuffer();
				    	ft5.append("update sbgl set dbili='"+jstz+"',FTBZW='1',ENTRYTIME="+s+",ENTRYPENSONNEL='"+entrypensonnel+"'  where SHUOSHUZHUANYE='zylx05' and dianbiaoid='"+dbidft+"'");
				    	System.out.println("分摊 建设投资"+ft5.toString());
				    	db.update(ft5.toString());
				    	
				    	//db.commit();
				    }
				   //分摊 代垫电费
				    
				    if(zs6==0&&!"0".equals(dddf)){//如果数据库里为空 前台数据不为零 就插入
				    	String jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI,ENTRYTIME,ENTRYPENSONNEL)values('"+dbidft+"',"
	                    +" to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '06"+"','zylx06','"+dddf+"','1',to_char((select id from dianbiao where dbid = '"+dbidft+"')) || '0606"+"','100',"+s+",'"+entrypensonnel+"')";//建设投资
				    	System.out.println("代垫电费插入"+jcdsql.toString());
				    	db.update(jcdsql.toString());
				    	//db.commit();
				    }else if(zs6!=0&&"0".equals(dddf)){
				    	StringBuffer ddft = new StringBuffer();
				    	ddft.append("delete sbgl where SHUOSHUZHUANYE='zylx06' and dianbiaoid='"+dbidft+"'");
						 System.out.println("删除代垫电费分摊 "+ddft.toString());
						 db.update(ddft.toString());
				    	
				    }else if(zs6!=0&&!"0".equals(dddf)){   
				    	StringBuffer ft6 = new StringBuffer();
				    	ft6.append("update sbgl set dbili='"+dddf+"',FTBZW='1',ENTRYTIME="+s+",ENTRYPENSONNEL='"+entrypensonnel+"'  where SHUOSHUZHUANYE='zylx06' and dianbiaoid='"+dbidft+"'");
				    	System.out.println("分摊 代垫电费"+ft6.toString());
				    	db.update(ft6.toString());
				    	
				    	//db.commit();
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
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
    //删除站点
    public synchronized int delData(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		sql1.append("delete zddfinfo df where df.zdid in(select zd.id from zhandian zd where zd.id='"+id+"')");//删除zddfinfo表里的内容
		sql2.append("delete sbgl sb where sb.dianbiaoid in(select d.dbid from zhandian z,dianbiao d where z.id=d.zdid and d.dbyt='dbyt01' and z.id='"+id+"')");//删除分摊信息   结算表有分摊
		sql3.append("delete dianbiao d where d.dbid in (select d.dbid from zhandian z,dianbiao d where z.id=d.zdid and z.id='"+id+"')");//删除电表 两个
		sql.append("delete from zhandian where id=" + id);
		System.out.println("删除站点信息："+sql.toString());
		System.out.println("删除电费信息："+sql1.toString());
		System.out.println("删除分摊信息："+sql2.toString());
		System.out.println("删除电表信息："+sql3.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateDelZhandian(db, id).equals("0")||this.validateDeldf(db, id).equals("0")) {
				return 2;
			}
			
			db.setAutoCommit(false);
			db.update(sql1.toString());//删除zddfinfo表里的内容
			
			db.update(sql2.toString());//删除分摊信息   结算表有分摊
			
			db.update(sql3.toString());//删除电表
			db.update("delete from zhandiankz where zdid=" + id);//扩展信息
			db.update(sql.toString());//删除站点
			
			
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
    System.out.println("22222222");
    StringBuffer sql = new StringBuffer();
    sql.append("select a.ammeterdegreeid from ammeterdegrees a, dianbiao d, zhandian z where z.id = d.zdid and d.dbid = a.ammeterid_fk and z.id=" + zdid);
    System.out.println("电量查询"+sql.toString());
    ResultSet rs = db.queryAll(sql.toString());//删除的站点里是否有电量如果有就不能删除
    if (rs.next()) {
	msg = "0";
    }
    
    return msg;
    }
    
    private String validateDeldf(DataBase db, String zdid)
	throws DbException, SQLException {
    String msg = "1";
    StringBuffer sql = new StringBuffer();
    sql.append("select e.electricfee_id " +
    		"from ammeterdegrees a, electricfees e, dianbiao d, zhandian z"+
            " where z.id = d.zdid"+
             " and d.dbid = a.ammeterid_fk"+
             " and a.ammeterdegreeid = e.ammeterdegreeid_fk"+
             " and z.id=" + zdid);
    System.out.println("查询电费信息："+sql.toString());
    ResultSet rs = db.queryAll(sql.toString());//删除的站点里是否有电费 如果有就不能删除         预付费里没有电量
    if (rs.next()) {
	msg = "0";
    }
    return msg;
    }
    
    
    
    
    //添加标杆类型
    public synchronized int addSign(String name,String type,String olt,String g2,String g3,String ktnum,String dytype){
    	int msg = 0;
    	StringBuffer sql = new StringBuffer();
    	sql.append("INSERT INTO benchmarking(NAME,STATIONTYPEID,OLT,G2,G3,AIR_CONDITION,REGION)");
    	sql.append("VALUES ('" + name + "','" + type + "','" + olt + "','" + g2 + "','" + g3 + "','" + ktnum + "','" + dytype + "')");
    	System.out.println("添加标杆类型"+sql.toString());
    	DataBase db = new DataBase();
    	try {
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	
    	
    	return msg;
    }
    //修改标杆类型
    public synchronized int modifySign(String id,String name,String type,String olt,String g2,String g3,String ktnum,String dytype){
    	int msg = 0;
    	CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update BENCHMARKING set name='" + name + "',STATIONTYPEID='" + type + "',REGION='" + dytype + "',G2='" + g2 + "',G3='" + g3 + "',OLT='" + olt + "',AIR_CONDITION='" + ktnum + "'  where id='"+id+"' ");
		DataBase db = new DataBase();
		System.out.println("修改标杆类型："+sql.toString());
		try {
			
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
			
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		
    	return msg;
    }
    
    //删除标杆类型
    public synchronized int delSign(String id){
    	int msg=0;
    	CTime ctime = new CTime();
    	StringBuffer sql = new StringBuffer();
    	sql.append("delete BENCHMARKING  where id="+id);
    	DataBase db = new DataBase();
    	try {
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return msg;
    }
  //添加公告
    public synchronized int addGgao(String xxtype,String ggtime,String dqtime,String bt,String nr,String lrr,String lrrId){
    	int msg = 0;
    	StringBuffer sql = new StringBuffer();
    	sql.append("INSERT INTO gonggao(xxtype,ggtime,dqtime,bt,nr,lrr,userid)");
    	sql.append("VALUES ('" + xxtype + "','" + ggtime + "','" + dqtime + "','" + bt + "','" + nr + "','" + lrr + "'," + lrrId + 

")");
    	System.out.println("公告："+sql.toString());
    	DataBase db = new DataBase();
    	try {
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	
    	
    	return msg;
    }
  //查询公告信息  前5条信息
	public synchronized ArrayList getGgao(){
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (SELECT ID,bt,nr,dqtime,ggtime,(select name from indexs where code = g.xxtype and type = 'xxtype')xxtype,lrr FROM gonggao g ORDER BY G.GGTIME DESC) where rownum<6 ");
		ResultSet rs = null;
		try {
			db.connectDb();
			rs= db.queryAll(sql.toString());
			Query query=new Query();
		      list = query.query(rs);
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return list;
	}
	
	 //查询全部公告信息
	public synchronized ArrayList getAGgao(){
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,bt,nr,dqtime,ggtime,(select name from indexs where code = g.xxtype and type = 'xxtype')xxtype,lrr FROM gonggao g ORDER BY G.GGTIME DESC");
		ResultSet rs = null;
		try {
			db.connectDb();
			rs= db.queryAll(sql.toString());
			Query query=new Query();
		      list = query.query(rs);
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return list;
	}
	//修改公告
    public synchronized int modifyGgao(String id,String xxtype, String ggtime,String dqtime,String bt,String nr,String lrr,String lrrId){
    	int msg = 0;
    	CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update gonggao set xxtype='" + xxtype + "',ggtime='" + ggtime + "',dqtime='" + dqtime + "',bt='" + bt + "',nr='" + nr + "',lrr='" + lrr +"',userid=" + lrrId +  "  where id='"+id+"' ");
		DataBase db = new DataBase();
		System.out.println("修改公告："+sql.toString());
		try {
			
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
			
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		
    	return msg;
    }
  //删除公告
    public synchronized int delggao(String id){
    	int msg=0;
    	CTime ctime = new CTime();
    	StringBuffer sql = new StringBuffer();
    	sql.append("delete gonggao  where id="+id);
    	DataBase db = new DataBase();
    	try {
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return msg;
    }
    //查询上传文件
	public synchronized ArrayList getFiles(){
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT F.ID,F.FILENAME,F.UPLOADDATE,(SELECT A.NAME FROM ACCOUNT A WHERE A.ACCOUNTID = F.UPLOADPEOPLE)AS NAME FROM FILETABLE F ORDER BY F.UPLOADDATE DESC");
		ResultSet rs = null;
		System.out.println("查询上传的文件："+sql.toString());
		try {
			db.connectDb();
			rs= db.queryAll(sql.toString());
			Query query=new Query();
		      list = query.query(rs);
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return list;
	}
	 public synchronized int dlUpdate(String month){
		 int msg = 0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("update zhandian z set z.dzbm = (select h.lxbh from zdnhbz h where h.g2= z.g2 and h.g3 = z.g3 and h.zp = z.zpsl and h.zs = z.zssl and h.changjia=(select i.name from indexs i where i.type='cj'and i.code=z.changjia)),z.dzyf = '"+month+"' where to_char(z.ID) in (SELECT j.zdid from jzxx j where j.symonth = '"+month+"')");
			DataBase db = new DataBase();
			System.out.println("修改省级定标电量："+sql.toString());
			try {
				
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
				
				
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
	 public synchronized void nhdel(String month){
			StringBuffer sql = new StringBuffer();
			sql.append("delete jzxx z where z.symonth='"+month+"'");
			DataBase db = new DataBase();
			System.out.println("基站能耗重复删除："+sql.toString());
			try {
				db.connectDb();
				db.update(sql.toString());
				db.commit();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	 }
	 public synchronized int nhupdate(String month){
		 int msg = 0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("insert into jzxx select '"+month+"',d.agname as shi,dd.agname as xian,ddd.agname as xiaoqu," +
					"z.id,z.jzname,'', '', '', '', '', '', '', '', '', '', aa.js, ss.gl, z.qsdbdl from zhandian z " +
					"left join d_area_grade d  on d.agcode = z.shi left join d_area_grade dd on dd.agcode = z.xian " +
					"left join d_area_grade ddd on ddd.agcode = z.xiaoqu left join " +
					"(select z.id, (sum((decode(df.accountmonth,'"+month+"', am.blhdl /(CASE  WHEN LENGTH(am.THISDATETIME) = 10 " +
					"AND LENGTH(am.LASTDATETIME) = 10 THEN TO_CHAR(CEIL(TO_DATE(am.THISDATETIME,'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd')) + 1) END),0)))/ sum(decode(df.accountmonth, '"+month+"', 1, 0))) as js " +
							"from zhandian z, dianbiao db,dianduview am, dianfeiview df " +
							"where z.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = df.ammeterdegreeid_fk " +
							"and db.dbyt = 'dbyt01' and df.accountmonth = '"+month+"' and am.manualauditstatus > '0' group by z.id, z.jzname) aa " +
							" on z.id = aa.id  left join (select dll.id, decode(dll.tiaoshu, '0', 0, 0, 0, dll.dl / dll.tiaoshu) gl" +
							" from (select z.id,sum((decode(am.manualauditstatus, '1', am.blhdl / (CASE WHEN LENGTH(am.THISDATETIME) = 10 " +
							"AND  LENGTH(am.LASTDATETIME) = 10 THEN  TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') -  TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd')) + 1) END), 0))) dl," +
							"sum(decode(am.manualauditstatus, '1', 1, 0)) tiaoshu from " +
							"zhandian z, dianbiao db, dianduview am where z.id = db.zdid and db.dbid = am.ammeterid_fk and db.dbyt = 'dbyt03' " +
							"and am.startmonth = '"+month+"' group by z.id, z.jzname) dll) ss" +
							" on z.id = ss.id  where z.qyzt = '1' and z.property = 'zdsx02'");
			DataBase db = new DataBase();
			System.out.println("基站能耗插入："+sql.toString());
			try {
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
	    	return msg;
	 }
		public synchronized int nhchenk(String month){//区县派单是否签收查询
			String sql ="select z.* from jzxx z where z.symonth ='"+month+"'";
			System.out.println("基站能耗重复判断:"+sql);
			DataBase db = new DataBase();
			ResultSet rs =null;
			int i=0;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					i=1;
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
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
			return i;		
			
		}
	 
}
