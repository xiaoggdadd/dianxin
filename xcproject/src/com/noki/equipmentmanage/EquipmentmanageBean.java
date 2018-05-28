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
		
		//�����ѯ
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
	            // System.out.println("�����ѯ��"+s2.toString());
	             rs = db.queryAll(s2.toString());
	             if (rs.next()) {
	 				if (rs.getInt(1) % 15 == 0) {
	 					this.setAllPage(rs.getInt(1) / 15);
	 				} else {
	 					this.setAllPage(rs.getInt(1) / 15 + 1);
	 				}
	 			}
	             System.out.println("��������ѯ��"+sql.toString());
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
		
		//�����ѯ(addҳ��)
		ArrayList list=new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select z.jzname, t.dianbiaoid,t.xjid,t.mingcheng,t.guige,t.shuoshuzhuanye, t.shuoshuwangyuan,t.kjkmcode,t.qcbcode,t.zymxcode,t.xjbili, t.sccj,t.zcbh,t.bccd,t.beizhu from sbgl t, dianbiao d, zhandian z" +
				" where z.id = d.zdid and t.dianbiaoid = d.dbid "+whereStr+" and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))");
        DataBase db = new DataBase();
        System.out.println("��ѯ����8888"+sql.toString());
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
	//������߸��¼�¼
	public String insertData(EquipmentmanageViewBean bean,String sc,String yy,String bg,String xxhzc,String jstz,String accountname,String dddf){
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//¼��ʱ��
		String ret = "";
		String sql = "select t.* from sbgl t where 1=1 ";
		if (!"".equals(bean.getSheiebanid())){
			sql = sql+"and t.xjid = '"+bean.getSheiebanid()+"'";//�¼�id
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("�����ѯ"+sql);
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
		System.out.println("��̯��"+bean.getShuoshuzhuanye()+"�豸id��"+bean.getDianbiaoid());
		
		try{
			db.connectDb();
			
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into sbgl (dianbiaoid,xjid,mingcheng,guige,shuoshuzhuanye,shuoshuwangyuan,xjbili,sccj,zcbh,bccd,beizhu,kjkmcode,qcbcode,zymxcode,ftbzw,sheiebanid,dbili,ENTRYPENSONNEL,ENTRYTIME)values( ";
				insertSql = insertSql+"'"+bean.getDianbiaoid()+"','"+bean.getSheiebanid()+"','"+bean.getMingcheng()+"','"+bean.getGuige()
				+"','"+bean.getShuoshuzhuanye()+"','"+bean.getShuoshuwangyuan()+"','"+bean.getBili()+"','"+bean.getSccj()+"','"+bean.getZcbh()
				+"','"+bean.getBccd()+"','"+bean.getBeizhu()+"','"+bean.getKjkmcode()+"','"+bean.getQcbcode()+"','"+bean.getZymxcode()+"','1','"+shebeiid+"','"+ft+"','"+accountname+"',"+s+")";
				System.out.println("�������"+insertSql);
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
				System.out.println("�����޸�"+updateSql);
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
  //���ID����
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
  
  //����רҵ��̬�����
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
  //����רҵ��̬�����
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

  
  //��ƿ�Ŀ����
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
  
  //��ƿ�Ŀ�����±�
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
  //��ƿ�Ŀ����
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
  //ȫ�ɱ������±�
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
  //ȫ�ɱ�����
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
	//ɾ��
	public String delData(String dianbiaoid){
		String ret = "";
		String sql = "delete from sbgl t  where t.dianbiaoid = '"+dianbiaoid+"'";
		DataBase db = new DataBase();
		System.out.println("����ɾ����"+sql);
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
	//��õ�����ڵ�վ������
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
	public String getzhandianshuxing(String dbid){//��������ҳ��Ĳ�ѯ
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.jztype,t.yflx,t.bgsign,t.gdfs,t.jnglmk ,t.jzname," +
				"(case when t.shi is not null then (select agname from d_area_grade where agcode=t.shi) else '' end)||" +
				"(case when t.xian is not null then (select distinct agname from d_area_grade where agcode=t.xian and fagcode=t.shi) else '' end)||" +
				"(case when t.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=t.xiaoqu and fagcode=t.xian) else '' end) as area " +
				" from zhandian t,dianbiao d where 1=1 and t.id = d.zdid");
		System.out.println("���ID"+dbid);
		if(dbid!=null&& !"".equals(dbid)){
			sql.append(" and d.dbid ='"+dbid+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
			try{
				 db.connectDb();
				 System.out.println("����վ����Ϣ"+sql.toString());
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
            		   ret.append("��|");
            	   }else{
            		   ret.append("��|");
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
          		     ret.append("��|");
          	        }else if("1".equals(rs.getString("jnglmk"))){
          	        	ret.append("��|");
          	        } else{ret.append("��|");}
            	 System.out.println(ret.toString()+"   5");

            	   //ret.append(rs.getString("jnglmk")+"|");
            	    ret.append(rs.getString("jzname")+"|");
            	    System.out.println(ret.toString());
            	    ret.append(rs.getString("area")+"|");
            	    System.out.println(ret.toString());
            	
            	    SiteModifyBean bean=new SiteModifyBean();
            	  //��̯ ����
            	    StringBuffer sql1=new StringBuffer();
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx01' and s.dianbiaoid='"+dbid+"'");
                    System.out.println("������"+sql1);
                    rs = db.queryAll(sql1.toString());
                    if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    	System.out.println(rs.getString("dbili")+"   ddddddddddddddddddddddddddd");
                    }else {
                    	ret.append("0|");
                    }
                    
                 /*  if(rs.next()){
                    	bean.setSc(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("����"+bean.getSc());
                   }*/
                    //��̯ Ӫҵ
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx02' and s.dianbiaoid='"+dbid+"'");
                    System.out.println("Ӫҵ��"+sql1);
                    rs = db.queryAll(sql1.toString());
                   if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    	System.out.println(rs.getString("dbili")+"   mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
                    }else {
                    	ret.append("0|");
                    }
                 /* if(rs.next()){
                    	bean.setYy(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("Ӫҵ"+bean.getSc());
                    }*/
                    //��̯ �칫
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx03' and s.dianbiaoid='"+dbid+"'");
                    System.out.println("�칫��"+sql1);
                    rs = db.queryAll(sql1.toString());
                    if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    }else {
                    	ret.append("0|");
                    }
                    /*if(rs.next()){
                    	bean.setBg(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("�칫"+bean.getSc());
                    }*/
                    //��̯ ��Ϣ��֧��
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx04' and s.dianbiaoid='"+dbid+"' ");
                    System.out.println("��Ϣ��֧�ţ�"+sql1);
                    rs = db.queryAll(sql1.toString());
                   if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    }else {
                    	ret.append("0|");
                    }
                 /*  if(rs.next()){
                    	bean.setXxhzc(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("��Ϣ��֧��"+bean.getSc());
                    }*/
                    //��̯ ����Ͷ��
                    sql1.setLength(0);
                    sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx05' and s.dianbiaoid='"+dbid+"' ");
                    System.out.println("����Ͷ�ʣ�"+sql1);
                    rs = db.queryAll(sql1.toString());
                  if(rs.next()){
                    	ret.append(rs.getString("dbili")+"|");
                    }else {
                    	ret.append("0|");
                    }
                  /* if(rs.next()){
                    	bean.setJstz(rs.getString(1)!=null?rs.getString(1):"");
                    	System.out.println("����Ͷ��"+bean.getSc());
                    }  */
                  //��̯ ����Ͷ��
                  sql1.setLength(0);
                  sql1.append("select distinct s.dbili from sbgl s,dianbiao db where db.dbid=s.dianbiaoid and s.shuoshuzhuanye='zylx06' and s.dianbiaoid='"+dbid+"' ");
                  System.out.println("�����ѣ�"+sql1);
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
            		   ret.append("�ü���ID���ظ�");
            	   }else{
            		   ret.append("�ü���ID�ظ�������������");
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
        System.out.println("�����޸��ύ"+sql.toString());
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	 /*  if (null!=rs.getString("sumBili")&&!"".equals(rs.getString("sumBili"))){
            		   Float  dd =new Float(rs.getString("sumBili"));
            		   System.out.println("��       ��"+dd);
            		   dd=aa+dd;
            		   System.out.println("����"+dd+" "+aa+"--"+dd);
            		   if (dd>100 && "1".equals(flg)){
            			   ret.append("�õ����������ռ�ȴ���100%������������");
            		   }if (dd<100 && "1".equals(flg)){
            			   ret.append("С��100");
            		   }else if (dd>100&& "2".equals(flg)){
            			   ret.append("�õ����������ռ�ȴ�����100%������������");
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
		System.out.println("ռ��"+ret.toString());
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
            		   ret.append("�ü������Ʋ��ظ�");
            	   }else{
            		   ret.append("�ü�������ظ�������������");
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
