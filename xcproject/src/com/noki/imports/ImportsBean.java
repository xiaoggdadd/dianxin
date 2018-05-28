package com.noki.imports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class ImportsBean {
	public  String getSelData() {
 		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.code,t.name from indexs t where t.type = 'zjblx' ");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("code")+"|");
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
	public String impData(String zjb){
 		StringBuffer ret = new StringBuffer();
 		StringBuffer sql = new StringBuffer();
        	// �����
        	ArrayList yearList = getyear(zjb);
        	// ɾ��������
        	del(zjb);
            DataBase db = new DataBase();
            ResultSet rs = null;
			try{
				 db.connectDb();
	        	for(Object d:yearList){
	        		ZdlxSum zdlxsum = new ZdlxSum();
	        		sql = new StringBuffer();
	                if ("zjblx01".equals(zjb)){
		            	sql.append("select sum(t.actualdegree) as actualdegree,z.jztype from ammeterdegrees t ,dianbiao d,zhandian z where z.id = d.zdid and d.dbid = t.ammeterid_fk  " +
		            			"and substr(t.THISDATETIME,0,7)='"+d+"'" +
		            			"  group by  z.jztype");
	                }else if ("zjblx02".equals(zjb)){
	                	sql.append("select sum(t.actualdegree) as actualdegree,z.jztype from ammeterdegrees t ,dianbiao d,zhandian z where z.id = d.zdid and d.dbid = t.ammeterid_fk  " +
		            			"and substr(t.THISDATETIME,0,4)='"+d+"'" +
		            			"  group by  z.jztype");
	                }
	        		if (sql.length()>0){
	        				 rs = null;
	        				 rs = db.queryAll(sql.toString());
	                       while (rs.next()) {
	                    	   if ("zdlx01".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx01(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx02".equals(rs.getString("jztype"))){
	                    		   zdlxsum.setZdlx02(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx03".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx03(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx04".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx04(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx05".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx05(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx06".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx06(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx07".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx07(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx08".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx08(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx09".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx09(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx10".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx10(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx11".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx11(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx12".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx12(rs.getString("actualdegree"));
	                    	   }
	                    	   if ("zdlx19".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("actualdegree"))&&(null!=rs.getString("actualdegree")))){
	                    		   zdlxsum.setZdlx19(rs.getString("actualdegree"));
	                    	   }
	                        }                
	        		}
	        		//��������
	        		sql = new StringBuffer();
	        		String year="";
	        		if (null!=d){
	        			year = d.toString().replaceAll("-", "");
	        		}
	        		
	        		if ("zjblx01".equals(zjb)){
		        		sql.append("insert into felecconsume ( id,province,producthouse,commadroit,basestage,datacenter,connlimit,outroom,notproducthouse,managerhouse,channelhouse,other,agcode,year)values" +
		        				"((select (case when max(t.id) is null then 0 else max(t.id) end )+1 from felecconsume t),'ɽ��','0','"+getformart(zdlxsum.getZdlx12())+"','"+getformart(zdlxsum.getZdlx08())+"','"+getformart(zdlxsum.getZdlx06())+"','"+getformart(zdlxsum.getZdlx07())+"'" +
		        				",'"+getformart(zdlxsum.getZdlx09())+"','0','"+getformart(zdlxsum.getZdlx10())+"','"+getformart(zdlxsum.getZdlx11())+"','"+getformart(zdlxsum.getZdlx19())+"'," +"'137','"+year+"')");
		        	}else if ("zjblx02".equals(zjb)){
		        		sql.append("insert into elecconsume ( id,province,producthouse,commadroit,basestage,datacenter,connlimit,outroom,notproducthouse,managerhouse,channelhouse,other,agcode,year)values" +
		        				"((select (case when max(t.id) is null then 0 else max(t.id) end )+1 from elecconsume t),'ɽ��','0','"+getformart(zdlxsum.getZdlx12())+"','"+getformart(zdlxsum.getZdlx08())+"','"+getformart(zdlxsum.getZdlx06())+"','"+getformart(zdlxsum.getZdlx07())+"'" +
		        				",'"+getformart(zdlxsum.getZdlx09())+"','0','"+getformart(zdlxsum.getZdlx10())+"','"+getformart(zdlxsum.getZdlx11())+"','"+getformart(zdlxsum.getZdlx19())+"'," +"'137','"+year+"')");
		        	}
	        		db.update(sql.toString());
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
	        if ("zjblx01".equals(zjb)){
	          ret.append("AMMETERDEGREES:������|FELECCONSUME:ʡ���ĵ�������ʹ������|"+getcount(zjb));
	        }else if ("zjblx02".equals(zjb)){
	          ret.append("AMMETERDEGREES:������|ELECCONSUME:ʡ���ĵ���ͳ�Ʋ�ѯ|"+getcount(zjb));
	        }

		return ret.toString();
	}
	
	//�����
	public ArrayList getyear(String zdtype){
		ArrayList yearList = new ArrayList();
 		StringBuffer sql = new StringBuffer();
        if ("zjblx01".equals(zdtype)){
          	// ȡ������
        	sql.append("select distinct substr(t.THISDATETIME,0,7) as year  from ammeterdegrees t ,dianbiao d,zhandian z where z.id = d.zdid and d.dbid = t.ammeterid_fk" +
        			" group by substr(t.THISDATETIME,0,7)");
        }else if ("zjblx02".equals(zdtype)){
        	// ȡ����
        	sql.append("select distinct substr(t.THISDATETIME,0,4) as year  from ammeterdegrees t ,dianbiao d,zhandian z where z.id = d.zdid and d.dbid = t.ammeterid_fk" +
			" group by substr(t.THISDATETIME,0,4)");
        }
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   yearList.add(rs.getString("year"));
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
        
		return yearList;
	}
	//ɾ��������
	public String del(String zdtype){
		String ret = "0";
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
        if ("zjblx01".equals(zdtype)){
			sql.append("delete from felecconsume");
        }else if ("zjblx02".equals(zdtype)){
        	sql.append("delete from elecconsume");
        }
		if (sql.length()>0){
			try{
				 db.connectDb();
				 db.update(sql.toString()); 
				 ret = "1";
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
	//��ʽ������
	public String getformart(String tempData){
		String ret = "";
		if (null==tempData || "".equals(tempData)){
			ret = "0";
		}else{
			ret = tempData;
		}
		return ret;
	}
	//��ñ����ݵ�����
	public String getcount(String zdtype){
		String ret = "0";
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
        if ("zjblx01".equals(zdtype)){
		    sql.append("select count(*)as count  from felecconsume");
        }else if ("zjblx02".equals(zdtype)){
        	sql.append("select count(*)as count  from elecconsume");
        }
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs =db.queryAll(sql.toString()); 
                 while (rs.next()) {
                	 ret = rs.getString("count");
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
}
