package com.noki.equipmentmanage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.SiteModifyBean;
import com.noki.page.NPageBean;
import com.noki.sysconfig.javabean.StaticDataBean;
import com.noki.util.Query;

public class EquipmentmanageBean {
	public  ArrayList getPageData(int start,String whereStr,String loginId) {
		
		//监测点查询
		ArrayList list=new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct z.jzname,"+
					" (case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) else ''  end) || (case  when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu) "+
      				" else '' end) as szdq, " +
      				" (select t.name  from indexs t where t.code = z.property  and t.type = 'ZDSX') as property, (select t.name from indexs t where t.code = z.jztype and t.type = 'ZDLX') as jztype,"+
      				" (select t.name from indexs t  where t.code = z.stationtype  and t.type = 'stationtype') as stationtype,"+
      				" d.danjia, d.dbname,(select t.name from indexs t where t.code = d.dfzflx and t.type = 'dfzflx') as dfzflx,"+
      				" (select t.name from indexs t where t.code = d.dbyt and t.type = 'DBYT') as dbyt,"+
      				" d.beilv, d.dbid  "+
      				" from  dianbiao d, zhandian z "+
      				" where z.id = d.zdid and z.qyzt='1' and d.dbyt='dbyt01'  and dbqyzt='1' "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");
		DataBase db = new DataBase();
        ResultSet rs = null;
		try{
				 db.connectDb();
	             StringBuffer s2 = new StringBuffer();
	             s2.append("Select count(*) from sbgl t, dianbiao d, zhandian z where z.id = d.zdid and z.qyzt='1' and d.dbyt='dbyt01'  and t.dianbiaoid = d.dbid and dbqyzt='1' "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");
	            // System.out.println("监测点查询："+s2.toString());
	             rs = db.queryAll(s2.toString());
	             if (rs.next()) {
	 				if (rs.getInt(1) % 15 == 0) {
	 					this.setAllPage(rs.getInt(1) / 15);
	 				} else {
	 					this.setAllPage(rs.getInt(1) / 15 + 1);
	 				}
	 			}
	             System.out.println("监测点管理查询："+sql.toString());
				  NPageBean nbean=new NPageBean();
				 // System.out.println(nbean.getQueryStr(start, sql.toString()));
			      rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
				  Query query=new Query();
			      list = query.query(rs);
			      db.close();   

			}catch (Exception de) {
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
	
public  ArrayList getPageData1(String whereStr,String loginId) {
		
		//监测点查询(add页面)
		ArrayList list=new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select z.jzname, t.dianbiaoid,t.xjid,t.mingcheng,t.guige,t.shuoshuzhuanye, t.shuoshuwangyuan,t.kjkmcode,t.qcbcode,t.zymxcode,t.xjbili, t.sccj,t.zcbh,t.bccd,t.beizhu from sbgl t, dianbiao d, zhandian z" +
				" where z.id = d.zdid and t.dianbiaoid = d.dbid "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");
        DataBase db = new DataBase();
        System.out.println("查询比例8888"+sql.toString());
        ResultSet rs = null;
		try{
				 db.connectDb();
			      rs = db.queryAll(sql.toString());
				  Query query=new Query();
			      list = query.query(rs);
			      db.close();
			}catch (Exception de) {
	            de.printStackTrace();
	        } finally {
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
  	                } catch (DbException de) {
  	                de.printStackTrace();
	  	            }
	            }
	         }
		return list;
	}
	  private int allPage;
	  public void setAllPage(int allpage ){
		  this.allPage=allpage;
		  
	  }
	  public int getAllPage(){
		  return this.allPage;
	  }
	//插入或者更新纪录
	public String insertData(EquipmentmanageViewBean bean,String sc,String yy,String bg,String xxhzc,String jstz,String accountname,String dddf){
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//录入时间
		String ret = "";
		String sql = "select t.* from sbgl t where 1=1 ";
		if (!"".equals(bean.getSheiebanid())){
			sql = sql+"and t.xjid = '"+bean.getSheiebanid()+"'";//下级id
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("监测点查询"+sql);
		String shebeiid="";
		String ft="";
		String zy=bean.getShuoshuzhuanye();
		String dbid=bean.getDianbiaoid();
		if("zylx01".equals(bean.getShuoshuzhuanye())){
			shebeiid=bean.getDianbiaoid()+"01";
			ft=sc;
		}else if("zylx02".equals(bean.getShuoshuzhuanye())){
			shebeiid=bean.getDianbiaoid()+"02";
			ft=yy;
		}else if("zylx03".equals(bean.getShuoshuzhuanye())){
			shebeiid=bean.getDianbiaoid()+"03";
			ft=bg;
		}else if("zylx04".equals(bean.getShuoshuzhuanye())){
			shebeiid=bean.getDianbiaoid()+"04";
			ft=xxhzc;
		}else if("zylx05".equals(bean.getShuoshuzhuanye())){
			shebeiid=bean.getDianbiaoid()+"05";
			ft=jstz;
		}else if("zylx06".equals(bean.getShuoshuzhuanye())){
			shebeiid=bean.getDianbiaoid()+"06";
			ft=dddf;
		}
		System.out.println("分摊："+bean.getShuoshuzhuanye()+"设备id："+bean.getDianbiaoid());
		
		try{
			db.connectDb();
			
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into sbgl (dianbiaoid,xjid,mingcheng,guige,shuoshuzhuanye,shuoshuwangyuan,xjbili,sccj,zcbh,bccd,beizhu,kjkmcode,qcbcode,zymxcode,ftbzw,sheiebanid,dbili,ENTRYPENSONNEL,ENTRYTIME)values( ";
				insertSql = insertSql+"'"+bean.getDianbiaoid()+"','"+bean.getSheiebanid()+"','"+bean.getMingcheng()+"','"+bean.getGuige()
				+"','"+bean.getShuoshuzhuanye()+"','"+bean.getShuoshuwangyuan()+"','"+bean.getBili()+"','"+bean.getSccj()+"','"+bean.getZcbh()
				+"','"+bean.getBccd()+"','"+bean.getBeizhu()+"','"+bean.getKjkmcode()+"','"+bean.getQcbcode()+"','"+bean.getZymxcode()+"','1','"+shebeiid+"','"+ft+"','"+accountname+"',"+s+")";
				System.out.println("监测点插入"+insertSql);
				db.setAutoCommit(false);
				db.update(insertSql);
				db.commit();
				db.setAutoCommit(true);

			}else{
				String updateSql = "update sbgl set dianbiaoid='"
				+bean.getDianbiaoid()+"', mingcheng = '"+bean.getMingcheng()+"', guige = '"+bean.getGuige()
				+"', shuoshuzhuanye = '"+bean.getShuoshuzhuanye()+"', shuoshuwangyuan = '"+bean.getShuoshuwangyuan()+"', xjbili = '"+bean.getBili()
				+"', sccj = '"+bean.getSccj()+"', zcbh = '"+bean.getZcbh()
				+"', bccd = '"+bean.getBccd()+"', beizhu = '"+bean.getBeizhu()+"',kjkmcode = '"+bean.getKjkmcode()+"'," +
				"qcbcode = '"+bean.getQcbcode()+"',zymxcode = '"+bean.getZymxcode()+"',ftbzw='1',sheiebanid='"+shebeiid+"',dbili='"+ft+"',ENTRYPENSONNEL='"+accountname+"',ENTRYTIME="+s+"  where 1=1 ";
				updateSql = updateSql+" and  xjid ='" + bean.getSheiebanid()+"'";
				System.out.println("监测点修改"+updateSql);
				db.setAutoCommit(false);
				db.update(updateSql.toString());
				db.commit();
				db.setAutoCommit(true);

			}	
			
			
			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return ret;
	 }
  //电表ID检索
  public String SelDianBiaoId(){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.dbid from dianbiao t group by t.dbid ");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("dbid")+",");
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
  }
  
  //所属专业静态表检索
  public String SelPermissionCconfiguration(String item,String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select name from indexs  where type='"+item+"' and code='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql1.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql1.toString());
               while (rs.next()) {
            	   result=!rs.getString("name").equals(null)?rs.getString("name"):"";
                }                
			}catch (Exception de) {
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
		}
		return result;
  }
  //所属专业静态表检索
  public String SelPermissionCconfiguration12(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select qcbname from qcbdf  where qcbcode='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql1.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql1.toString());
               while (rs.next()) {
            	   result=!rs.getString("qcbname").equals(null)?rs.getString("qcbname"):"";
                }                
			}catch (Exception de) {
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
		}
		return result;
  }
  public String SelPermissionCconfiguration1(String itemname,String code){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select code,type,name from indexs  where 1=1 ");
		if(itemname!=null&& !"".equals(itemname)){
			sql1.append(" and type ='"+itemname+"'");
		}
		if(code!=null&& !"".equals(code)){
			sql1.append(" and code ='"+code+"'");
		}
      DataBase db = new DataBase();
      ResultSet rs = null;
		if (sql1.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql1.toString());
             while (rs.next()) {
          	   ret.append(rs.getString("code")+"|");
          	   ret.append(rs.getString("type")+"|");
          	   ret.append(rs.getString("name")+",");
              }                
			}catch (Exception de) {
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
		}
		return ret.toString();
}

  
  //会计科目检索
  public String getKJKM(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("select kmname from DF_KJKM where kmcode='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 System.out.println(sql1.toString());
				 rs = db.queryAll(sql1.toString());
               if (rs.next()) {
            	   result=!rs.getString("kmname").equals(null)?rs.getString("kmname"):"";
                }                
			}catch (Exception de) {
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
		return result;
  }
  
  //会计科目检索新表
  public String getKJKM1(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("select qcbname from qcbdf where qcbcode='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 System.out.println(sql1.toString());
				 rs = db.queryAll(sql1.toString());
               if (rs.next()) {
            	   result=!rs.getString("qcbname").equals(null)?rs.getString("qcbname"):"";
                }                
			}catch (Exception de) {
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
		return result;
  }
  //会计科目检索
  public String getKJKM12(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("select qcbname from qcbdf where qcbcode='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 System.out.println(sql1.toString());
				 rs = db.queryAll(sql1.toString());
               if (rs.next()) {
            	   result=!rs.getString("qcbname").equals(null)?rs.getString("qcbname"):"";
                }                
			}catch (Exception de) {
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
		return result;
  }
  //全成本检索新表
  public String getQCB1(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select qcbname from qcbdf where qcbcode='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 rs = db.queryAll(sql1.toString());
               if (rs.next()) {
            	   result=!rs.getString("qcbname").equals(null)?rs.getString("qcbname"):"";
                }                
			}catch (Exception de) {
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
		return result;
  }
  //全成本检索
  public String getQCB(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select qcbname from DF_QCB  where qcbcode='"+code+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 rs = db.queryAll(sql1.toString());
               if (rs.next()) {
            	   result=!rs.getString("qcbname").equals(null)?rs.getString("qcbname"):"";
                }                
			}catch (Exception de) {
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
		return result;
  }
  
  public String getQCB12(String code){
		String result = new String();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("select qcbname from qcbdf  where qcbcode='"+code+"'");
      DataBase db = new DataBase();
      ResultSet rs = null;
			try{
				 db.connectDb();
				 rs = db.queryAll(sql1.toString());
             if (rs.next()) {
          	   result=!rs.getString("qcbname").equals(null)?rs.getString("qcbname"):"";
              }                
			}catch (Exception de) {
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
		return result;
}
	//删除
	public String delData(String dianbiaoid){
		String ret = "";
		String sql = "delete from sbgl t  where t.dianbiaoid = '"+dianbiaoid+"'";
		DataBase db = new DataBase();
		System.out.println("监测点删除："+sql);
		try{
			db.connectDb();
			System.out.println(sql.toString());/////////////////////
			db.update(sql.toString());

			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return ret;
	 }
	//获得电表所在的站点名称
	public String getzhandianName(String dianbiaoId){
		String ret = "";
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zdname from dianbiao t where 1=1");
		if(dianbiaoId!=null&& !"".equals(dianbiaoId)){
			sql.append(" and DBID ='"+dianbiaoId+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret = rs.getString("zdname");
                }                
			}catch (Exception de) {
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
		}
		return ret;
	}
	//
	public String getzhandianshuxing(String dbid){//监测点新增页面的查询
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.jztype,t.yflx,t.bgsign,t.gdfs,t.jnglmk ,t.jzname," +
				"(case when t.shi is not null then (select agname from d_area_grade where agcode=t.shi) else '' end)||" +
				"(case when t.xian is not null then (select distinct agname from d_area_grade where agcode=t.xian and fagcode=t.shi) else '' end)||" +
				"(case when t.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=t.xiaoqu and fagcode=t.xian) else '' end) as area " +
				" from zhandian t,dianbiao d where 1=1 and t.id = d.zdid");
		System.out.println("电表ID"+dbid);
		if(dbid!=null&& !"".equals(dbid)){
			sql.append(" and d.dbid ='"+dbid+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 System.out.println("监测点站点信息"+sql.toString());
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   String yflx=rs.getString("yflx");
            	   if(!"".equals(rs.getString("jztype"))&&!"null".equals(rs.getString("jztype"))&&rs.getString("jztype")!=null&&!rs.getString("jztype").equals("0")){
            		   StaticDataBean staticBean = new StaticDataBean();
            		   String typeName = staticBean.getModfyStaticData("ZDLX", rs.getString("jztype"));
            		   String[] temp = typeName.split("\\|");
            		   ret.append(temp[3]+"|");
            		   System.out.println(ret.toString()+"1dongddddddddddddddd");
            	   }else{
            		   ret.append("  |");
            		   System.out.println(ret.toString()+"1dongddddddddddddddd");
            	   }
            	   if(!"".equals(yflx)&&!"null".equals(yflx)&&yflx!=null&&!yflx.equals("0")){
            		   System.out.println("--"+yflx+"--");
            		   StaticDataBean staticBean = new StaticDataBean();
            		   String typeName = staticBean.getModfyStaticData("FWLX", rs.getString("yflx"));
            		   
            		   String[] temp = typeName.split("\\|");
            		   ret.append(temp[3]+"|");
            		   System.out.println(ret.toString()+"2dongddddddddddddddd");
            	   }else{
            		   ret.append(" |");
            		   System.out.println(ret.toString()+"2dongddddddddddddddd");
            	   }
            	   if ("1".equals(rs.getString("bgsign"))){
            		   ret.append("是|");
            	   }else{
            		   ret.append("否|");
            	   }
            	   System.out.println(ret.toString()+"3dongddddddddddddddd");
            	 if(!"".equals(rs.getString("gdfs"))&&!"null".equals(rs.getString("gdfs"))&&rs.getString("gdfs")!=null&&!rs.getString("gdfs").equals("0")){
            		   StaticDataBean staticBean = new StaticDataBean();
            		   String typeName = staticBean.getModfyStaticData("GDFS", rs.getString("gdfs"));
            		   String[] temp = typeName.split("\\|");
            		   ret.append(temp[3]+"|");
            	   }else{
            		   ret.append(" |");
            	   }
            	 System.out.println(ret.toString()+"    4");
            	    //
            	 if ("0".equals(rs.getString("jnglmk"))){
          		     ret.append("否|");
          	        }else if("1".equals(rs.getString("jnglmk"))){
          	        	ret.append("是|");
          	        } else{ret.append("否|");}
            	 System.out.println(ret.toString()+"   5");

            	   //ret.append(rs.getString("jnglmk")+"|");
            	    ret.append(rs.getString("jzname")+"|");
            	    System.out.println(ret.toString());
            	    ret.append(rs.getString("area")+"|");
            	    System.out.println(ret.toString());
            	
            	    SiteModifyBean bean=new SiteModifyBean();
            	  //分摊 生产
            	    StringBuffer sql1=new StringBuffer();
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx01' and s.dianbiaoid='"+dbid+"'");
                    System.out.println("生产："+sql1);
                    rs = db.queryAll(sql1.toString());
                    if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    	System.out.println(rs.getString("dbili")+"   ddddddddddddddddddddddddddd");
                    }else {
                    	ret.append("0|");
                    }
                    
                 /*  if(rs.next()){
                    	bean.setSc(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("生产"+bean.getSc());
                   }*/
                    //分摊 营业
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx02' and s.dianbiaoid='"+dbid+"'");
                    System.out.println("营业："+sql1);
                    rs = db.queryAll(sql1.toString());
                   if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    	System.out.println(rs.getString("dbili")+"   mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
                    }else {
                    	ret.append("0|");
                    }
                 /* if(rs.next()){
                    	bean.setYy(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("营业"+bean.getSc());
                    }*/
                    //分摊 办公
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx03' and s.dianbiaoid='"+dbid+"'");
                    System.out.println("办公："+sql1);
                    rs = db.queryAll(sql1.toString());
                    if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    }else {
                    	ret.append("0|");
                    }
                    /*if(rs.next()){
                    	bean.setBg(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("办公"+bean.getSc());
                    }*/
                    //分摊 信息化支撑
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx04' and s.dianbiaoid='"+dbid+"' ");
                    System.out.println("信息化支撑："+sql1);
                    rs = db.queryAll(sql1.toString());
                   if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    }else {
                    	ret.append("0|");
                    }
                 /*  if(rs.next()){
                    	bean.setXxhzc(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("信息化支撑"+bean.getSc());
                    }*/
                    //分摊 建设投资
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx05' and s.dianbiaoid='"+dbid+"' ");
                    System.out.println("建设投资："+sql1);
                    rs = db.queryAll(sql1.toString());
                  if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    }else {
                    	ret.append("0|");
                    }
                  /* if(rs.next()){
                    	bean.setJstz(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("建设投资"+bean.getSc());
                    }  */
                  //分摊 建设投资
                  sql1.setLength(0);
                  sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx06' and s.dianbiaoid='"+dbid+"' ");
                  System.out.println("代垫电费："+sql1);
                  rs = db.queryAll(sql1.toString());
                if(rs.next()){
                  	ret.append(rs.getString("dbili")+"|");
                  }else {
                  	ret.append("0|");
                  }  
            	    
            	    System.out.println("ret11111111"+ret.toString());
            	    
            	    
            	 
                }                
			}catch (Exception de) {
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
	        System.out.println(ret.toString());
		return ret.toString();
	}
	public String chekId(String shebeiId){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select  count(*) as maxnum from sbgl t where 1=1");
		if(shebeiId!=null&& !"".equals(shebeiId)){
			sql.append(" and t.sheiebanid ='"+shebeiId+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   if ("0".equals(rs.getString("maxnum"))){
            		   ret.append("该监测点ID不重复");
            	   }else{
            		   ret.append("该监测点ID重复，请重新输入");
            	   }
            	   
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
	}

	
	public String chekBili(String dianbiao,String flg ,String inbili,String shebei){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		 float aa =  Float.valueOf("0");
		if(inbili!=null && !"".equals(inbili)){
			aa =  Float.valueOf(inbili);
		}
		
		sql.append("select sum(xjbili) as sumBili from sbgl t where 1=1");
		if(dianbiao!=null&& !"".equals(dianbiao)){
			sql.append(" and t.dianbiaoid ='"+dianbiao+"'");
		}
		if(shebei!=null&& !"".equals(shebei)){
			sql.append(" and t.xjid not in('"+shebei+"')");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println("监测点修改提交"+sql.toString());
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	 /*  if (null!=rs.getString("sumBili")&&!"".equals(rs.getString("sumBili"))){
            		   Float  dd =new Float(rs.getString("sumBili"));
            		   System.out.println("测       试"+dd);
            		   dd=aa+dd;
            		   System.out.println("测试"+dd+" "+aa+"--"+dd);
            		   if (dd>100 && "1".equals(flg)){
            			   ret.append("该电表的所属电表占比大于100%，请重新输入");
            		   }if (dd<100 && "1".equals(flg)){
            			   ret.append("小于100");
            		   }else if (dd>100&& "2".equals(flg)){
            			   ret.append("该电表的所属电表占比大于了100%，请重新输入");
            		   }else{
            			   ret.append("");
            		   }
            	   }else{*/
            		   ret.append("");
            	   //}     	   
                }                
			}catch (Exception de) {
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
		}
		System.out.println("占比"+ret.toString());
		return ret.toString();
	}
	
	public String chekname(String shebeiId){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select  count(*) as maxnum from sbgl t where 1=1");
		if(shebeiId!=null&& !"".equals(shebeiId)){
			sql.append(" and t.mingcheng ='"+shebeiId+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   if ("0".equals(rs.getString("maxnum"))){
            		   ret.append("该监测点名称不重复");
            	   }else{
            		   ret.append("该监测名称重复，请重新输入");
            	   }
            	   
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
	}
//
	public String alertbili(String dianbiao){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();


		
		sql.append("select sum(dbili) as sumBili from sbgl t where 1=1");
		if(dianbiao!=null&& !"".equals(dianbiao)){
			sql.append(" and t.dianbiaoid ='"+dianbiao+"'");
		}

        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   if (null!=rs.getString("sumBili")&&!"0".equals(rs.getString("sumBili"))){
                      	   ret.append(rs.getString("sumBili"));
            	   }else{
            		   ret.append("");
            	   }     	   
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
	}
}
