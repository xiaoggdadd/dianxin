package com.noki.jizhan;

/**
 * 2018��3��7�� 16:41:53
 * Ф��
 * �ߴ��ٸ�
 * ���ڲƸ�����
 */
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import oracle.net.aso.r;

import com.noki.baobiaohuizong.qitabean.GYS;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.model.AcSoloBean;
import com.noki.jizhan.model.BasicDataBean;
import com.noki.jizhan.model.ChengbBean;
import com.noki.jizhan.model.DianbBean;
import com.noki.jizhan.model.DianbiaoBean;
import com.noki.jizhan.model.ElecBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.util.Constant;

public class SiteManage {
	private int allPage;
	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}
	
	//����ƻ���ѯ
		public synchronized ArrayList getCbjihua1(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
	        String fzzdstr="";
	      
	        
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				
				
				s2.append("select c.id,a.accountname,a.mobile,c.userid,to_char(c.cbjhrq,'yyyy-mm-dd')as cbjhrq,a.name,z.jzname from cbjh c,account a,zhandian z where c.userid=a.accountid and c.zdid=z.id and abs(trunc(sysdate)-trunc(cbjhrq))<3");
	                    System.out.println("վ���ѯ"+s2);
				
	        
	               
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
	
	
	//����ƻ���ѯ
	public synchronized ArrayList getCbjihua(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select c.id,a.accountname, c.userid,to_char(c.cbjhrq,'yyyy-mm-dd')as cbjhrq,a.name,z.jzname from cbjh c,account a,zhandian z where c.userid=a.accountid and c.zdid=z.id "+whereStr);
                    System.out.println("վ���ѯ"+s2);
			
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
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
	//����ͨ����ID���в�����ѯ
	

	public synchronized ArrayList getquyuguanli(String id,String bz) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
	
        String fzzdstr="";
		try {
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			
			s2.append("SELECT D.AGID, D.AGCODE,D.AGNAME FROM D_AREA_GRADE D WHERE 1=1 AND D.AGID='"+id+"'");
		
			System.out.println("��ѯ"+s2);
			
               rs=db.queryAll(s2.toString());
              Query query = new Query();
              list = query.query(rs);
              rs.close();		
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
	}	//����ͨ����ID���в�����ѯ
	

	public synchronized ArrayList getbumenguanli(String id,String bz) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
	
        String fzzdstr="";
		try {
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			s2.append("SELECT d1.ID,d1.DEPTCODE,d1.DEPTNAME,d1.FDEPTCODE,(select d2.deptname from department d2 where d2.deptcode = d1.fdeptcode) fdeptname, d1.shi FROM DEPARTMENT d1 WHERE ID='" +id+ "' ");
			System.out.println("��ѯ"+s2);
			
              rs=db.queryAll(s2.toString());
              Query query = new Query();
              list = query.query(rs);
              rs.close();		
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
	public synchronized String getDeptByShi(String shi, String fdeptcode) {
		StringBuffer sql = new StringBuffer();
		sql.append("select d.deptcode, d.deptname from department d where d.isused='01' and d.fdeptcode='"+fdeptcode+"' and d.shi='"+shi+"'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer  list = new StringBuffer() ;
		list.append("[");
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				list.append(rs.getString(1) +"|");
				list.append(rs.getString(2) +",");
			}
			list.append("]");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list.toString();
	}
	/**
	 * 
	 * @param start
	 * @param whereStr ��ѯ����
	 * @return
	 */
	public synchronized ArrayList getDept(int start, String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		DataBase db = new DataBase();
		ResultSet rs = null;
        String fzzdstr="";
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select d2.id, d1.deptcode fdeptcode, d1.deptname fdeptname, d2.deptcode, d2.deptname," +
					"(select a.agname from d_area_grade a where a.agcode = d2.shi) shidisplay " +
					"from department d1, department d2 where d1.deptcode = d2.fdeptcode and d1.isused='01' and d2.isused='01' "+whereStr);
	        s2.append(" order by d1.deptcode ");
                    System.out.println("���Ų�ѯ"+s2);
                    
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("���Ų�ѯ��ҳ"+s3); 
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
	//����
		public synchronized ArrayList getbumen(int start,String shi,String bianma,String whereStr,String name) {
			System.out.println(whereStr+"111111111111111111");
			ArrayList list = new ArrayList();
			CTime ct = new CTime();
			DataBase db = new DataBase();
			ResultSet rs = null;
	        String fzzdstr="";
			try {
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("SELECT ID,DEPTCODE,DEPTNAME,FDEPTCODE, DEPTGRADE FROM DEPARTMENT a where 1=1 "+whereStr);
				
			 if(name!=null&&name!=""&&!name.equals("0")&&!name.equals("")){
	            	
			           	
		        s2.append(" AND DEPTNAME LIKE '%"+name+"%' ");
		            	
		            }
		        // s2.append(" ORDER BY DEPTCODE ");
	                    System.out.println("վ���ѯ"+s2);
	                    
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
	
	
	//����
		public synchronized ArrayList getquyu(int start,String agcode,String agname) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();
			
			DataBase db = new DataBase();
			ResultSet rs = null;
		
	        String fzzdstr="";
			try {
				
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				
				StringBuffer s3 = new StringBuffer();
				s2.append("SELECT AGID,AGCODE,AGNAME FROM D_AREA_GRADE WHERE 1=1 ");
	             if(agname!=null&&agname!=""&&!agname.equals("0")&&!agname.equals("")){
	            	
	            	s2.append(" AND AGNAME LIKE '%"+agname+"%'");
	            	
	            }
	            
	            
				System.out.println("��ѯ"+s2);
				
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("��ѯ��ҳ"+s3); 
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
		}//ɾ���ò�����Ϣ
		  public synchronized int delDatabumenguanli(String id) {
				int msg = 0;
				
				StringBuffer sql = new StringBuffer();
				
				
				StringBuffer sql1 = new StringBuffer();
				sql1.append("DELETE FROM DEPARTMENT WHERE ID="+id+" ");
				System.out.println("ɾ��������Ϣ��"+sql.toString());
				
				
				DataBase db = new DataBase();
				try {
					db.connectDb();
					
					
					db.setAutoCommit(false);
					db.update(sql1.toString());//ɾ��zddfinfo���������
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

		//ɾ����������Ϣ
		  public synchronized int delData1(String id) {
				int msg = 0;
				
				StringBuffer sql = new StringBuffer();
				
				
				StringBuffer sql1 = new StringBuffer();
				sql1.append("DELETE FROM D_AREA_GRADE D WHERE D.AGID="+id+" ");
				System.out.println("ɾ����Ϣ��Ϣ��"+sql.toString());
				
				
				DataBase db = new DataBase();
				try {
					db.connectDb();
					
					
					db.setAutoCommit(false);
					db.update(sql1.toString());//ɾ��zddfinfo���������
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
		//ɾ����ϵͳ��Ϣ
		  public synchronized int delData2(String id) {
				int msg = 0;
				
				StringBuffer sql = new StringBuffer();
				
				
				StringBuffer sql1 = new StringBuffer();
				sql1.append("DELETE FROM GONGGAO WHERE ID="+id+" ");
				System.out.println("ɾ����Ϣ��Ϣ��"+sql.toString());
				
				
				DataBase db = new DataBase();
				try {
					db.connectDb();
					
					
					db.setAutoCommit(false);
					db.update(sql1.toString());//ɾ��zddfinfo���������
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
		  
	//����ϵͳ��Ϣ
	public synchronized ArrayList getxx1(String xxlx) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		
		DataBase db = new DataBase();
		ResultSet rs = null;
	
        String fzzdstr="";
		try {
		
			
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select i.name from gonggao g,indexs i where g.xxtype=i.code");
            if(xxlx!=null&&xxlx!=""&&!xxlx.equals("0")&&!xxlx.equals("")){
            	s2.append(" and xxtype='"+xxlx+"'");
            }
			System.out.println("վ���ѯ"+s2);
			
			   s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
               ResultSet rs3 = null;
               rs3=db.queryAll(s2.toString());
               
               System.out.println("�����"+rs3);
              
              NPageBean nbean = new NPageBean();
            //  rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
              Query query = new Query();
              list = query.query(rs3);
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
	
	
	
	//ϵͳ��Ϣչ��
	public synchronized ArrayList getxx(int start,String loginName,String loginId,String xxlx) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		
		DataBase db = new DataBase();
		ResultSet rs = null;
	
        String fzzdstr="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select g.id, g.xxtype,i.name,g.nr,g.bt from gonggao g,indexs i where g.xxtype=i.code");
            if(xxlx!=null&&xxlx!=""&&!xxlx.equals("0")&&!xxlx.equals("")){
            	s2.append(" and xxtype='"+xxlx+"'");
            }
			System.out.println("��ѯ"+s2);
			
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
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
	
	
	
	//������ѵ渶com.noki.jizhan.SiteManage
	public synchronized ArrayList getPageDatadfdc(String loginName,String loginId,String whereStr){


		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("SELECT Z.ID FROM DFDFHS Z WHERE 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="",shi="",ss="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			//shi=getShi(db,loginId);
			
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("SELECT Z.ID,Z.DFWHSZJ,Z.DFWHSZJ_BYHS,Z.DHSZJ,Z.DHSZJ_YYSHD,Z.DHSZJ_YKP,RTNAME(Z.DFHS_YYS) DFHS_YYS,Z.DFHS_HDZB,Z.DFHS_KPZB,Z.ENTRYTIME,RNDIQU(Z.SHI) SHI FROM DFDFHS Z " +
					" WHERE 1=1 "+whereStr+
					"  ORDER BY Z.ENTRYTIME");
                    System.out.println("վ���ѯ"+s2);   
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
               ResultSet rs3 = null;
               rs3=db.queryAll(s2.toString());
              
              NPageBean nbean = new NPageBean();
            //  rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
              Query query = new Query();
              list = query.query(rs3);
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
	//-----------------
	public synchronized ArrayList getPageDatadfid(int start,String loginName,String loginId,String whereStr){

		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("SELECT Z.ID FROM DFDFHS Z WHERE 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="";
      //  String shi="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			//shi=getShi(db,loginId);
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			//StringBuffer s3 = new StringBuffer();
			s2.append("SELECT Z.ID,Z.DFWHSZJ,Z.DFWHSZJ_BYHS,Z.DHSZJ,Z.DHSZJ_YYSHD,Z.DHSZJ_YKP,Z.DFHS_YYS,Z.DFHS_HDZB,Z.DFHS_KPZB,Z.ENTRYTIME,Z.SHI FROM DFDFHS Z " +

					" WHERE 1=1 "+whereStr);
		
                    System.out.println("վ���ѯ"+s2);
			
        
               
              // s3.append("select count(*)  from (" + s2 + ")");
              // System.out.println("վ���ѯ��ҳ"+s3); 
             
              
             // NPageBean nbean = new NPageBean();
              rs = db.queryAll(s2.toString());
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
	
	//��ѵ渶��ѯ
	public synchronized ArrayList getPageDatadf(int start,String loginName,String loginId,String whereStr){

		ArrayList list = new ArrayList();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("SELECT Z.ID FROM DFDFHS Z WHERE 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="",shi="",ss="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			//shi=getShi(db,loginId);
			
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("SELECT Z.ID,Z.DFWHSZJ,Z.DFWHSZJ_BYHS,Z.DHSZJ,Z.DHSZJ_YYSHD,Z.DHSZJ_YKP,RTNAME(Z.DFHS_YYS) DFHS_YYS,Z.DFHS_HDZB,Z.DFHS_KPZB,Z.ENTRYTIME,RNDIQU(Z.SHI) SHI FROM DFDFHS Z " +

					" WHERE 1=1 "+whereStr);
					
					//"  ORDER BY Z.ENTRYTIME");
                    System.out.println("վ���ѯ"+s2);
			
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
               ResultSet rs3 = null;
               rs3=db.queryAll(s3.toString());
               if(rs3.next()){
               	this.setAllPage((rs3.getInt(1)+16)/17);
               }
              NPageBean nbean = new NPageBean();
              rs = db.queryAll(nbean.getQueryStrdf(start, s2.toString()));
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
	//����վ��
	public synchronized ArrayList getPageData(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select z.id,z.jzname,z.qyzt,(select t.name from indexs t where t.code=z.property and t.type='ZDSX') as property," +
					"(select t.name from indexs t where t.code=z.jztype and t.type='ZDLX') as jztype," +
					"(select t.name from indexs t where t.code=z.yflx and t.type='FWLX') as yflx," +
					"(select t.name from indexs t where t.code=z.gdfs  and t.type='GDFS') as gdfs," +
					"(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
					"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,"+
					"z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu,z.shsign ," +
					"z.LSCID,z.FULL_STATION_CODE,z.STATION_CODE,z.SERVICE_ID,z.RANK_ID," +
					"(case when xuni='0' then '��' else '��' end) as xunisign,(select t.name from indexs t where t.code=z.stationtype  and t.type='stationtype') as xlx,z.zlfh,z.jlfh,z.powerpole,z.zldy from zhandian z " +
					" where 1=1  "+whereStr+
					"  ORDER BY z.jzname");
                    System.out.println("վ���ѯ"+s2);
			
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
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
	//����վ��
	public synchronized ArrayList getPageData_new(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
//		StringBuffer strall = new StringBuffer();
//		strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
        String fzzdstr="";
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			System.out.println("����վ��"+fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select z.id as STATION_NO,z.LSCID,z.QYZT as QYZT,z.FULL_STATION_CODE," +
					"z.JZNAME,z.STATION_CODE," +
					"(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.SHI ) as SHI," +
					"(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAN) as XIAN," +
					"(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAOQU) as XIAOQU," +
					"z.SERVICE_ID, z.SERVICE_NAME, z.RANK_ID, z.RANK_NAME" +
					/*"(select t.name from indexs t where t.code=z.SERVICE_ID and t.type='SERVICE_ID_DICT') as SERVICE_ID," +
					"(select t.name from indexs t where t.code=z.RANK_ID and t.type='RANK_ID_DICT') as RANK_ID" +*/
					" from zhandian z " +
					" where 1=1  "+whereStr+
					"  ORDER BY z.JZNAME");
                    System.out.println("վ���ѯ2"+s2);
                    
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
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
	
	    
	//����baozhang
	public synchronized ArrayList getPageData_baozhang(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
//		StringBuffer strall = new StringBuffer();
//		strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("SELECT B.ELECTRICFEE_ID ID,B.DIANBIAOID DIANBIAOID,D.DBBM," +
					"d.dbname as DBNAME,B.CBZX CBZX," +
					"z.JZNAME AS ZDNAME," +
					"(select name from indexs where upper(type)='PJLX' and code=d.FPLX) as PJLX," +
					"(select name from indexs where upper(type)='FKFS' and code=d.DFZFLX) JFFS," +
					"B.STARTTIME STARTTIME,B.ENDTIME ENDTIME,to_char(B.SQDS,'fm9999999990.00') SQDS," +
					"to_char(B.BQDS,'fm9999999990.00') BQDS,B.BEILV BEILV,to_char(B.DIANLIANG,'fm9999999990.00') DIANLIANG," +
					"to_char(B.ALLMONEY,'fm9999999990.00') ALLMONEY,to_char(B.DIANSUN,'fm9999999990.00') DIANSUN," +
					"to_char(B.MONEY,'fm9999999990.00') MONEY,to_char(B.TAX,'fm9999999990.00') TAX,to_char(B.SQDJ,'fm9999999990.0000') SQDJ," +
					"to_char(B.PRICE,'fm9999999990.0000') PRICE," +
					"B.FTXS FTXS,B.STATE STATE," +					
					"(select a.name from account a, s_workflow w  where w.auditorid = to_char(a.accountid) and  w.id=(select max(w.id) from account a, s_workflow w where w.auditorid = to_char(a.accountid) and w.task_id = B.Electricfee_Id group by w.task_id)) as accountname,"
					+"(select name from indexs where upper(type)='GSZT' and code=d.GSZT) GSZT" +
					" FROM ELECTRICFEES B,DIANBIAO d,ZHANDIAN z " +
					" where z.id=d.zdid and b.DIANBIAOID = d.ID and d.ssf='2' and b.entrypensonnel <> 'import' "+whereStr+
					"  ORDER BY B.entrytimeold desc , B.DIANBIAOID,B.STARTTIME");//d.ssf='2'���������˱�־
                    System.out.println("���˲�ѯ..."+s2);
                    
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("���˲�ѯ��ҳ"+s3); 
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
	//�������˷�ҳ��ѯ
	public synchronized ArrayList getPageData_Tietabaozhang(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
//		StringBuffer strall = new StringBuffer();
//		strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("SELECT B.ELECTRICFEE_ID ID,B.DIANBIAOID DIANBIAOID,D.DBBM," +
					"d.dbname as DBNAME,B.CBZX CBZX," +
					"z.JZNAME AS ZDNAME," +
					"(select name from indexs where upper(type)='PJLX' and code=d.FPLX) as PJLX," +
					"(select name from indexs where upper(type)='FKFS' and code=d.DFZFLX) JFFS," +
					"B.STARTTIME STARTTIME,B.ENDTIME ENDTIME,to_char(B.SQDS,'fm9999999990.00') SQDS," +
					"to_char(B.BQDS,'fm9999999990.00') BQDS,B.BEILV BEILV,to_char(B.DIANLIANG,'fm9999999990.00') DIANLIANG," +
					"to_char(B.ALLMONEY,'fm9999999990.00') ALLMONEY,to_char(B.DIANSUN,'fm9999999990.00') DIANSUN," +
					"to_char(B.MONEY,'fm9999999990.00') MONEY,to_char(B.TAX,'fm9999999990.00') TAX,to_char(B.SQDJ,'fm9999999990.0000') SQDJ," +
					"to_char(B.PRICE,'fm9999999990.0000') PRICE,B.FTXS FTXS,B.STATE STATE," +					
					"(select a.name from account a, s_workflow w where w.auditorid = to_char(a.accountid) and w.task_id = B.Electricfee_Id) as accountname,"
					+"(select name from indexs where upper(type)='GSZT' and code=d.GSZT) GSZT" +
					" FROM ELECTRICFEES B,DIANBIAO d,ZHANDIAN z " +
					" where z.id=d.zdid and b.DIANBIAOID = d.ID and d.ssf='1' "+whereStr+
					"  ORDER BY B.DIANBIAOID,B.STARTTIME");//d.ssf='1'�������˱�־
                    System.out.println("�������˲�ѯ..."+s2);
                    
        
               
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("�������˲�ѯ��ҳ"+s3); 
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
	//��ѯҵ������
	public synchronized ArrayList getYeZhuData(int start,String loginName,String loginId,String whereStr) throws SQLException {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer s2 = new StringBuffer();
		s2.append("select id,name,((case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end))" +
					" as szdq from yezhu z" +
					" where 1=1" +whereStr+
					"order by id");
        System.out.println("ҵ����ѯ"+s2);
		//StringBuffer strall = new StringBuffer();
		try {
			db.connectDb();
			
			StringBuffer s3 = new StringBuffer();
			
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
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
	
	public synchronized ArrayList getFTBLData(int start,String loginName,String loginId,String whereStr) throws SQLException {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer s2 = new StringBuffer();
		s2.append("select z.jzname,f.kehu,f.ftbl,f.id from ftblquery f,zhandian z where f.zdid=z.jzcode ");
        System.out.println("ҵ����ѯ"+s2);
		//StringBuffer strall = new StringBuffer();
		try {
			db.connectDb();			
			StringBuffer s3 = new StringBuffer();			
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("վ���ѯ��ҳ"+s3); 
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
	
	
	private String getShi(DataBase db, String loginid)throws DbException, SQLException{

		ResultSet rs = null;
		String sql="select t.agcode from per_area t where length(t.agcode)=5 and accountid='"+ loginid+"'";
		System.out.println("��ѯ����sql��"+sql.toString());
		rs = db.queryAll(sql.toString());
		String cxtj = "";
		if(rs.next()){
		cxtj=rs.getString("AGCODE");
		}
    System.out.println("����վ��������"+cxtj);
    if(rs != null){
    	rs.close();
    }
    if(db != null){
    	db.close();
    }
    
		return cxtj.toString();
	
	}
	
	//����������
		private String getbumenid(DataBase db) throws DbException, SQLException {
			ResultSet rs = null;

			rs = db
					.queryAll("select begincode,endcode from per_zhandian where accountid='"
							
							+ "' and begincode is not null and endcode is not null");
			String cxtj = new String("");
			while (rs.next()) {			
					cxtj=cxtj+" or (agcode>='" + rs.getString(1)
							+ "' and agcode <='" + rs.getString(2) + "')";			
			}	
	    System.out.println("����������"+cxtj);
	    if(rs != null){
	    	rs.close();
	    }
	    if(db != null){
	    	db.close();
	    }
	    
			return cxtj.toString();
		}
		
		
		
	//����վ������
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
    System.out.println("����վ��������"+cxtj);
    if(rs != null){
    	rs.close();
    }
    if(db != null){
    	db.close();
    }
    
		return cxtj.toString();
	}
	//===����վ��===
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
		//�¼�
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
	//��ѯ���������Ϣ
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
	throws DbException, SQLException{
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
		if (fzarea.equals("'0'")) { // û�����ø������� ���ز�ѯ����վ��Ĳ�ѯ����
			s.setLength(0);
			s.append(cxtj.toString());
		} else { // �����ø������� ����û���ڸ��������ڵĸ���վ��id
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
		System.out.println("վ��������䣺" + s.toString());
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
		s2.append(",(case when xuni='0' then '��' else '��' end) as xunisign");
		s2
				.append(",z.jnglmk,z.area,z.dianfei,z.zdcode,z.sheng,z.shi,z.xian,z.xiaoqu from zhandian z where ");

		s2.append(" z.id in(" + fzzdid + ")");

		s.append("select * from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) order by sheng,shi,xian,xiaoqu,jzname");
		System.out.println("վ��������䣺" + s.toString());
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
	public synchronized int modifydadf(DianbiaoBean dbb){

		int msg = 0;
		ResultSet rs = null;
		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());//��:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		inserttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
		String inserttime1 = ctime.formatWholeDate(new Date());
		inserttime1=new SimpleDateFormat("yyyy-MM").format(new Date());
		String s1="to_date(to_char(sysdate, 'yyyy-mm'),'yyyy-mm')";
		String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		Random rnd = new Random();//�����
		DataBase db = new DataBase();
		try {
			db.connectDb();
				db.connectDb();
				rs=null;
				StringBuffer sql = new StringBuffer();
				sql.append(" UPDATE DFDFHS T SET T.DFWHSZJ='"+dbb.getDfwhszj()+"',T.DFWHSZJ_BYHS='"+dbb.getDfwhszj_byhs()+"',T.DHSZJ='"+dbb.getDhszj()+"',T.DHSZJ_YYSHD='"+dbb.getDhszj_yyshd()+"'," +
						" T.DHSZJ_YKP='"+dbb.getDhszj_ykp()+"',T.DFHS_YYS='"+dbb.getDfhs_yys()+"',T.DFHS_HDZB='"+dbb.getDfhs_hdzb()+"'," +
						" T.DFHS_KPZB='"+dbb.getDfhs_kpzb()+"',T.UPDATETIME="+s+",T.UPDATEPERSON='"+dbb.getEntrypensonnel()+"' WHERE T.ID='"+dbb.getId()+"'");

				System.out.println("�����޸ĵ��֧��sql��"+sql.toString());
				
				db.setAutoCommit(false);
				db.update(sql.toString());
				db.commit();
				db.setAutoCommit(true);
			    msg=1;
        

		} catch (DbException de) {
			msg=2;
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
	public synchronized int adddatadf(DianbiaoBean dbb){
		int msg = 0;
		ResultSet rs = null;
		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());//��:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		inserttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
		String inserttime1 = ctime.formatWholeDate(new Date());
		inserttime1=new SimpleDateFormat("yyyy-MM").format(new Date());
		String s1="to_date(to_char(sysdate, 'yyyy-mm'),'yyyy-mm')";
		String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String jsname="������";//������������
		String glname="������";//������������
		Random rnd = new Random();//�����
     	//��ѯ����վ�� ���ǲɼ���  վ������ ��  վ����� δͨ�� �е�ѹ���Ա��˱�ʶ--->0δ��ˣ�1��ͨ����  ��   ��������SQL
     	//���ݷ����ʶ�� �������������� ��PERMISSION_CONFIGURATION
//		DFWHSZJ      ���µ渶δ�����ʽ�(Ԫ)	DFWHSZJ_BYHS ���µ渶δ�����ʽ𣬱����ѻؿ�(Ԫ)
//		DHSZJ        �������ʽ�(Ԫ)		    DHSZJ_YYSHD	 �������ʽ��Ѻ���Ӫ�������ϸ�˶�
//		DHSZJ_YKP	�������ʽ��Ѿ���Ʊ              DFHS_YYS	��Ӫ��
//		DFHS_HDZB	�Ѻ˶�ռ�ȣ����������ʽ��Ѻ���Ӫ�������ϸ�˶ԡ�/���������ʽ�
//		DFHS_KPZB	�ѿ�Ʊռ�ȣ����������ʽ��ѿ�Ʊ��/���������ʽ�
//		ENTRYTIME	¼��ʱ��                        		UPDATETIME	����ʱ��
//		SHI	                ����	ENTRYPERSON	     ¼����     UPDATEPERSON	������Ա

		
		
		DataBase db = new DataBase();
		
		try {
			db.connectDb();
				db.connectDb();
				rs=null;
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO DFDFHS(YUE,DFWHSZJ,DFWHSZJ_BYHS,DHSZJ,DHSZJ_YYSHD,DHSZJ_YKP,DFHS_YYS,DFHS_HDZB,DFHS_KPZB,ENTRYTIME,UPDATETIME,SHI,ENTRYPERSON,UPDATEPERSON)");
				sql.append("VALUES ("+s1+",'" + dbb.getDfwhszj() + "','" + dbb.getDfwhszj_byhs() + "','"+ dbb.getDhszj() + "','" + dbb.getDhszj_yyshd() + "','" + dbb.getDhszj_ykp() + 
						"','"+dbb.getDfhs_yys()+"','" +dbb.getDfhs_hdzb()+"','"+dbb.getDfhs_kpzb()+"',"+s+",'','"+dbb.getShi()+"','"+dbb.getEntrypensonnel()+"','')");
				
				
				System.out.println("������ӵ��֧��sql��"+sql.toString());
				
				db.setAutoCommit(false);
				db.update(sql.toString());
				db.commit();
				db.setAutoCommit(true);
			    msg=1;
        

		} catch (DbException de) {
			msg=2;
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
	

	//�����վ��
	public synchronized int addData(String shi, String xian, String xiaoqu,
			String jztype, String jzproperty, String yflx, String jflx,
			String jzname, String jzcode, String zzlx,String zdcq2,String wlzdbm,String ltqx,String ydqx,String xtid,
			String jingdu,String weidu, String phone,String gdfname,String bumen,String bieming, String address, String bgsign,
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
			String zybyqlx,String bsdl,String sxd_a,String sxd_b,String sxd_c,String zldy,
			
			String LSCID, String FULL_STATION_CODE, String STATION_CODE,String SERVICE_ID,
			String RANK_ID, String PROPERTY_ID, String MAINTAIN_ID, String ADDRESS, String COVERED_AREA,
			String OCCUPIED_AREA, String RENT, String HIRE_TIME,String PERIOD,String RENT_ID,
			String LEASE_ID,String LATEST_DATE,String POWER_ID,String PRICE,String ELECTRICITYRATIO,
			String ISSUPERVISOR,String STATUS,String BATTERYUSELENGTH,String ISCOUNTRY,String SOURCESYSTEMSTATIONTYPEID,
			String SOURCESYSTEMSTATIONLEVELID,String HOUSETYPEID,String ISUSERCONFIRM,String USERSTATIONNAME,String LEAF_AREAID,
			String EDITTIME,String USEDSPECIALTY,String GROUPRANK_ID,String CC08_SUPERVISORY,String STATION_ID_INSERT_DATE, 
			String SUPERVISOR_DATE,String SUPERVISOR_TYPE,String TRANS_IP,String SUPERVISOR_EQU_IP,String TRANS_TYPE,
			String OLT_PORT_ADDRE) {
		// birthday = birthday.length()>0?birthday:null;
		System.out.println(jzcode);
		int msg = 0;
		ResultSet rs = null;
		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());//��:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		inserttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
		//System.out.println(inserttime+"   ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
		//String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
		String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String jsname="������";//������������
		String glname="������";//������������
		Random rnd = new Random();//�����
   	    zdcode = (String) (rnd.nextInt(999999999)+"");//������Χ�ڵ������   ����վ��ı���
     	dbid = (String) (rnd.nextInt(999999999)+"");   //�Զ����ɵ��id
     	
     	//��ѯ����վ�� ���ǲɼ���  վ������ ��  վ����� δͨ�� �е�ѹ���Ա��˱�ʶ--->0δ��ˣ�1��ͨ����  ��   ��������SQL
     	StringBuffer sqlcount = new StringBuffer("SELECT COUNT(T.ID) COUNTID FROM ZHANDIAN T WHERE T.XUNI='0' AND T.CAIJI = '0' AND T.QYZT='1' AND T.SHSIGN='1' AND T.PROVAUDITSTATUS='1' AND T.SHI='").append(shi).append("'");
     	//���ݷ����ʶ�� �������������� ��PERMISSION_CONFIGURATION
     	StringBuffer sqlbzcount = new StringBuffer("SELECT T.ITEMVALUE,T.ITEMVALUE2 FROM PERMISSION_CONFIGURATION T WHERE T.ITEMLLAG='4' AND T.ITEMNAME='").append(shi).append("'");

		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateZdCode(db, zdcode) == 0) {//����������(վ����в����ڸ�ֵid)
				db.connectDb();
				//2015-2-2
				String nowcount = "0",bzzcount = "0",bzzbl = "0";
				rs = null;
				System.out.println("����վ������:"+sqlcount.toString());
				rs = db.queryAll(sqlcount.toString());
				try {
					if(rs.next()){//��ȡ��������
						nowcount = rs.getString("COUNTID")==null?"0":rs.getString("COUNTID");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				rs=null;
				System.out.println("վ��������׼ֵ:"+sqlbzcount.toString());
				
				rs = db.queryAll(sqlbzcount.toString());
				try {
					if(rs.next()){//��ȡ�������� / ��׼ֵ
						bzzcount = rs.getString("ITEMVALUE")==null?"0":rs.getString("ITEMVALUE");
						bzzbl = rs.getString("ITEMVALUE2")==null?"0":rs.getString("ITEMVALUE2");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				double bzz = Math.ceil(Format.str_d(bzzcount.trim()) * (1 + Format.str_d(bzzbl.trim())/100));
				String shengbz ,shengshr ,shengshsj;
				if(Format.str_d(nowcount)>bzz){//��ʡ��
					shengbz = "'0'";//ʡ����˱�־
					shengshr = null;//ʡ������վ�������
					shengshsj = null;//ʡ������վ�����ʱ��
				}else{
					shengbz = "'1'";
					shengshr = "'"+entrypensonnel+"'";
					shengshsj = s;
				}

				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO ZHANDIAN(QYZT,SPZY,SHI,XIAN,PROPERTY,YFLX,JZNAME," +
						"JZCODE,ZZLX,ZDCQ,WLZDBM,LTQX,YDQX,NHXTID,LONGITUDE," +
						"LATITUDE,FZRPHONE,CHANGJIA,BM,BIEMING,ADDRESS,GDFS,FZR,CJTIME,ZDCODE,XIAOQU");
				sql.append(",entrypensonnel,entrytime,stationtype,GXXX,GDFNAME," +
						"LSCID, FULL_STATION_CODE,  STATION_CODE, SERVICE_ID, RANK_ID," +
						" PROPERTY_ID, MAINTAIN_ID,  COVERED_AREA," +
						" OCCUPIED_AREA, RENT, HIRE_TIME, PERIOD, RENT_ID, LEASE_ID, " +
						"LATEST_DATE, POWER_ID, PRICE, ELECTRICITYRATIO, ISSUPERVISOR, " +
						"STATUS, BATTERYUSELENGTH, ISCOUNTRY, SOURCESYSTEMSTATIONTYPEID, " +
						"SOURCESYSTEMSTATIONLEVELID, HOUSETYPEID, ISUSERCONFIRM," +
						" USERSTATIONNAME, LEAF_AREAID, EDITTIME, USEDSPECIALTY," +
						" GROUPRANK_ID, CC08_SUPERVISORY, STATION_ID_INSERT_DATE, " +
						"SUPERVISOR_DATE, SUPERVISOR_TYPE, TRANS_IP, " +
						"SUPERVISOR_EQU_IP, TRANS_TYPE, OLT_PORT_ADDRE)");
				sql.append("VALUES ('0','00','" + shi + "','" + xian + "','"+ jzproperty + "','" + yflx + "','" + jzname + "','"+jzcode+"','" +zzlx+"','"+zdcq+"','"+wlzdbm
						+"','"+ltqx+"','"+ydqx+"','"+xtid+"','"+jingdu+"','"+weidu+"','"+phone+"','"+gdfname+"','"+bumen+"','"+ bieming
									+ "',");
							sql.append("'" + address +"','"+ gdfs + "','" + fzr + "'");
							sql.append(",to_timestamp('" + inserttime+ "','yyyy-mm-dd hh24:mi:ss:ff6'),'" + zdcode+ "','" + xiaoqu + "'");
							sql.append(",'" + entrypensonnel+"',"+s+",'"+stationtype+"','"+gxxx+"','"+gdfname+"',"+
									Integer.parseInt(LSCID)+ ",'" +FULL_STATION_CODE+ "','" +STATION_CODE+ "','" +
									SERVICE_ID+ "','" + RANK_ID+ "'," + PROPERTY_ID+ "," + MAINTAIN_ID+ "," +
									COVERED_AREA+ "," +
									OCCUPIED_AREA+ "," + RENT+ ",to_date('"+HIRE_TIME+"','yyyy-mm-dd')," + PERIOD+ "," +
									RENT_ID+ "," + LEASE_ID+ ",'" + LATEST_DATE+ "'," + POWER_ID+ "," + PRICE+ "," +
									ELECTRICITYRATIO+ ",'" + ISSUPERVISOR+ "','" + STATUS+ "'," + BATTERYUSELENGTH+ ",'" +
									ISCOUNTRY+ "'," + SOURCESYSTEMSTATIONTYPEID+ "," +
									SOURCESYSTEMSTATIONLEVELID+ "," + HOUSETYPEID+ ",'" + ISUSERCONFIRM+ "','" +
									USERSTATIONNAME+ "'," + LEAF_AREAID+ ",to_date('"+EDITTIME+"','yyyy-mm-dd'),'" + USEDSPECIALTY+ "'," + GROUPRANK_ID+ ",'" + CC08_SUPERVISORY+
									"',to_date('"+STATION_ID_INSERT_DATE+"','yyyy-mm-dd'),to_date('"+SUPERVISOR_DATE+"','yyyy-mm-dd')," + SUPERVISOR_TYPE+ ",'" + TRANS_IP+ 
									"','" + SUPERVISOR_EQU_IP+ "'," + TRANS_TYPE+ ",'" +OLT_PORT_ADDRE+ "')");
				System.out.println("���վ��"+sql.toString());
				
				db.setAutoCommit(false);
				db.update(sql.toString());
				db.commit();
				db.setAutoCommit(true);
					//���
					StringBuffer sqldf = new StringBuffer();
			           // db.update(sqldf.toString());
                       //���
			           System.out.println("���id��"+dbid);
			            StringBuffer sql2 = new StringBuffer();
			            System.out.println("������"+sql2.toString());
		                 String sql5="update zhandian set zdcode=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from zhandian where zdcode='"+zdcode+"')";
		                 System.out.println("sql5-----------"+sql5);
		                 rs=null;
		                 try {  	 
		           		 rs = db.queryAll(sql5);
		           		 
		                 } catch (Exception e) {
		             		e.printStackTrace();
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
	//������
	/**
	 *  ���ӵ�ѵ����ʶ 
	 * 
	 * @param DFBZW 0������, 1��¼��
	 * @return
	 */
	public synchronized int addDataBaoZhang(String TAXchange,String TAXSTATE,String TAX,String STARTTIME_C,String ENDTIME_C,
			String SQDS_C,String BQDS_C,String DIANLIANG_C,String DAYNUM_C,
			String RJYDL_C,String DIANBIAOID,String STARTTIME,
			String ENDTIME,String SQDS,String BQDS,String DIANLIANG,
			String ALLMONEY,String DIANSUN,String SQDJ,String PRICE,
			String DAYNUM,String RJYDL,String SQRJYDL,String ACCOUNTID,
			String ACCTIME,String BEILV,String STATE,String DianLiangPianLiShu,
			String RiQiPianLiShu,String PUEZHI,String BGDL,String BGPLL, String DFBZW) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		int bzId=0;
		ResultSet rs = null;
		System.out.println("acctime+++++++++++++++++++++++++++" + ACCTIME);
		DataBase db = new DataBase();
		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//createdate��ʱ���޸�Ϊʱ����
         String yymm =sdf2.format(new Date());
		try {
			db.connectDb();
			//2015-2-2
			rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ELECTRICFEES(TAXSATAE,TAXXIUGAIQIAN,DIANBIAOID,STARTTIME_C,ENDTIME_C,SQDS_C,BQDS_C,DIANLIANG_C,DAYNUM_C,RJYDL_C,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,STATE,DAYNUM,RJYDL,SQRJYDL,ENTRYPENSONNEL,ENTRYTIMEOLD,BEILV,CREATEDATE,DLPLS,RQPLS,PUEZHI,TAX,BGDL,BGPLL");
			sql.append(", DFBZW)");
			sql.append("VALUES ("+TAXSTATE+","+TAXchange+","+DIANBIAOID+",to_date('"+STARTTIME_C+"','yyyy-mm-dd'),to_date('"+ENDTIME_C+"','yyyy-mm-dd'),"+SQDS_C+","+BQDS_C+","+DIANLIANG_C+","+DAYNUM_C+","+RJYDL_C+",to_date('"+STARTTIME+"','yyyy-mm-dd'),to_date('"+ENDTIME+"','yyyy-mm-dd'),"+SQDS+","+
					BQDS+","+DIANLIANG+","+ALLMONEY+","+
					DIANSUN+","+SQDJ+","+PRICE+","+STATE+","+DAYNUM+",'"+RJYDL+"','"+SQRJYDL+"','"+ACCOUNTID+"','"+ACCTIME+"',"+BEILV+",'"+yymm+"','"+DianLiangPianLiShu+"','"+RiQiPianLiShu+"','"+PUEZHI+"','"+TAX+"','"+BGDL+"','"+BGPLL+"', '"+DFBZW+"')");
			System.out.println("��ӱ���sql:"+sql.toString());
			
			db.setAutoCommit(false);
			bzId = db.update(sql.toString(),new String[]{"ELECTRICFEE_ID"});
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
			msg = 2;
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

		return bzId;
	}
	///18-3-22liushuo ����ģ����������
	public synchronized int addDataBaoZhangtieta(String TAX,String STARTTIME_C,String ENDTIME_C,
			String SQDS_C,String BQDS_C,String DIANLIANG_C,String DAYNUM_C,
			String RJYDL_C,String DIANBIAOID,String STARTTIME,
			String ENDTIME,String SQDS,String BQDS,String DIANLIANG,
			String ALLMONEY,String DIANSUN,String SQDJ,String PRICE,
			String DAYNUM,String RJYDL,String SQRJYDL,String ACCOUNTID,
			String ACCTIME,String BEILV,String STATE,String DianLiangPianLiShu,
			String RiQiPianLiShu,String PUEZHI,String FTJE,String SFYZ,String FTBL,String BZQJ,String BGPLL,String BGDL) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		int bzId=0;
		ResultSet rs = null;

		DataBase db = new DataBase();
		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
         String yymm =sdf2.format(new Date());
		try {
			db.connectDb();
			//2018-3-22
			rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ELECTRICFEES(DIANBIAOID,STARTTIME_C,ENDTIME_C,SQDS_C,BQDS_C,DIANLIANG_C,DAYNUM_C,RJYDL_C,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,STATE,DAYNUM,RJYDL,SQRJYDL,ENTRYPENSONNEL,ENTRYTIMEOLD,BEILV,CREATEDATE,DLPLS,RQPLS,PUEZHI,TAX,ftje,BGPLL,BGDL");
			sql.append(")");
			sql.append("VALUES ("+DIANBIAOID+",to_date('"+STARTTIME_C+"','yyyy-mm-dd'),to_date('"+ENDTIME_C+"','yyyy-mm-dd'),"+SQDS_C+","+BQDS_C+","+DIANLIANG_C+","+DAYNUM_C+","+RJYDL_C+",to_date('"+STARTTIME+"','yyyy-mm-dd'),to_date('"+ENDTIME+"','yyyy-mm-dd'),"+SQDS+","+
					BQDS+","+DIANLIANG+","+ALLMONEY+","+
					DIANSUN+","+SQDJ+","+PRICE+","+STATE+","+DAYNUM+",'"+RJYDL+"','"+SQRJYDL+"','"+ACCOUNTID+"','"+ACCTIME+"',"+BEILV+",'"+yymm+"','"+DianLiangPianLiShu+"','"+RiQiPianLiShu+"','"+PUEZHI+"','"+TAX+"','"+FTJE+"','"+BGPLL+"','"+BGDL+"')");
			System.out.println("������ӱ���sql:"+sql.toString());
			
			db.setAutoCommit(false);
			bzId = db.update(sql.toString(),new String[]{"ELECTRICFEE_ID"});
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
			msg = 2;
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

		return bzId;
	}
	//������
		public synchronized int addAnalysis(String BZID,String ADDTIME,
				String CREATETIME,String SHIBAODL,String SHEBEIDL,
				String DBID,String ZDID,String STARTTIME,String ENDTIME,
				String VALUE,String JIAOLIU,String ZHILIU,String DAYNUM) {
			// birthday = birthday.length()>0?birthday:null;
			int msg = 0;
			int id=0;
			ResultSet rs = null;

			DataBase db = new DataBase();
			
			try {
				db.connectDb();
				//2015-2-2
				rs = null;
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO ANALYSIS(BZID,ADDTIME," +
						"CREATETIME,SHIBAODL,SHEBEIDL,DBID,ZDID,STARTTIME,ENDTIME,VALUE,JIAOLIU,ZHILIU,DAYNUM");
				sql.append(")");
				sql.append("VALUES ('"+BZID+"','"+ADDTIME+"','"+CREATETIME+"','"+
				SHIBAODL+"','"+SHEBEIDL+"','"+DBID+"','"+ZDID+"','"+STARTTIME+"','"+
						ENDTIME+"','"+VALUE+"','"+JIAOLIU+"','"+ZHILIU+"','"+DAYNUM+"')");
				System.out.println("���pue��Ϣsql:"+sql.toString());
				
				db.setAutoCommit(false);
				id = db.update(sql.toString(),new String[]{"ID"});
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
				msg = 2;
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

			return id;
		}
		
		//������
				public synchronized int  updateAnalysis(String BZID,String ADDTIME,
						String CREATETIME,String SHIBAODL,String SHEBEIDL,
						String DBID,String ZDID,String STARTTIME,String ENDTIME,
						String VALUE,String JIAOLIU,String ZHILIU,String DAYNUM) {
					// birthday = birthday.length()>0?birthday:null;
					int msg = 0;
					int id=0;
					ResultSet rs = null;

					DataBase db = new DataBase();
					
					try {
						db.connectDb();
						rs = null;
						StringBuffer sql = new StringBuffer();
						sql.append("update ANALYSIS set SHIBAODL='"+SHIBAODL+
								"',SHEBEIDL='"+SHEBEIDL+"',DBID='"+DBID+
								"',ZDID='"+ZDID+"',STARTTIME='"+STARTTIME+"',ENDTIME='"+ENDTIME+
								"',VALUE='"+VALUE+"',JIAOLIU='"+JIAOLIU+"',ZHILIU='"+ZHILIU+"',DAYNUM='"+DAYNUM+
								"' where BZID='"+BZID+"'");
						System.out.println("�޸�pue��Ϣsql:"+sql.toString());
						
						db.setAutoCommit(false);
						msg = db.update(sql.toString());
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
						msg = 2;
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
	   //������
    public synchronized int tsAllbz(String COSTTYPE,String COSTSTATE,
            String COSTTIME,String COSTUSERNAME,String COSTUSERCODE,String COSTNUM,
            String BZTYPE,String APPNUM,String RSNAME,String RSCODE,
            String PAYTYPE,String BZFS,String GANGWEI,String BZYWSJ,String JJSX,
            String YONGTU,String CREATTIME,String OTHERSYSTEMMAINID,String FILLINORGCODE) {
        // birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        int acId=0;
        ResultSet rs = null;

        DataBase db = new DataBase();
        
        try {
            db.connectDb();
            //2015-2-2
            rs = null;
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ALLCOST(COSTTYPE,COSTSTATE,COSTTIME,COSTUSERNAME," +
            		"COSTUSERCODE,COSTNUM,BZTYPE,APPNUM,RSNAME,RSCODE,PAYTYPE,BZFS," +
            		"GANGWEI,BZYWSJ,JJSX,YONGTU,CREATTIME,OTHERSYSTEMMAINID,FILLINORGCODE");
            sql.append(")");
            sql.append("VALUES ('"+COSTTYPE+"','"+COSTSTATE+"','"+COSTTIME+"','"+COSTUSERNAME+"','"+COSTUSERCODE+"','"+
                    COSTNUM+"','"+BZTYPE+"','"+APPNUM+"','"+RSNAME+"','"+RSCODE+"','"+PAYTYPE+"','"+BZFS+"','"+
                    GANGWEI+"','"+BZYWSJ+"','"+JJSX+"','"+YONGTU+"','"+CREATTIME+"','"+OTHERSYSTEMMAINID+"','"+FILLINORGCODE+"')");
            System.out.println("���allcost��sql:"+sql.toString());
            
            db.setAutoCommit(false);
            acId = db.update(sql.toString(),new String[]{"ID"});
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
            msg = 2;
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

        return acId;
    }
	//������
    public synchronized int tsbz(String ALLCOSTID,String SHINAME,
            String DBNAME,String DBCODE,String ZDNAME,String ZDCODE,
            String FPTYPE,String FPNAME,String STARTTIME,String ENDTIME,
            String YWTYPE,String RCNAME,String RCCODE,String RBNAME,
            String RBCODE,String ALLMONEY,String FAXMONEY,String COMPNAME,
            String BZ_SOLO_ID,String SHICODE,String DIANLIANG) {
        int msg = 0;
        int asId=0;
        ResultSet rs = null;

        DataBase db = new DataBase();
        
        try {
            db.connectDb();
            //2015-2-2
            //����AC_SOLO
            rs = null;
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO AC_SOLO( ALLCOSTID, " +
            		"SHINAME,  DBNAME, DBCODE, ZDNAME, ZDCODE, " +
            		"FPTYPE, FPNAME, STARTTIME, ENDTIME, " +
            		"YWTYPE, RCNAME, RCCODE, RBNAME, RBCODE," +
            		" ALLMONEY, FAXMONEY, COMPNAME,BZID,SHICODE,DIANLIANG");
            sql.append(")");
            sql.append("VALUES ("+ALLCOSTID+",'"+SHINAME+"','"+DBNAME+"','"+
                    DBCODE+"','"+ZDNAME+"','"+ZDCODE+"','"+FPTYPE+"','"+FPNAME+"','"+STARTTIME+"','"+
                    ENDTIME+"','"+YWTYPE+"','"+RCNAME+"','"+RCCODE+
                    "','"+RBNAME+"','"+RBCODE+"','"+ALLMONEY+"','"+FAXMONEY+"','"+COMPNAME+"','"+BZ_SOLO_ID+"','"+SHICODE+"','"+DIANLIANG+"')");
            System.out.println("��ӱ���sql:"+sql.toString());
            
            db.setAutoCommit(false);
            asId = db.update(sql.toString(),new String[]{"ID"});
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
            msg = 2;
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

        return asId;
    }
	//������
    public synchronized int addbz_solo(String COMPNAME,String DBNAME,String DBCODE,String ZDNAME,String ZDCODE,String FPTYPE,
            String  JFFS,String STARTTIME,String ENDTIME,String RCNAME,String RCCODE,String MONEY,String ALLMONEY,String FAXMONEY,String DIANLIANG,String PRICE,
            String  YWTYPE,String YWCODE,String YSNAME,String YSCODE,String SHI,String SHICODE,String ELEID,String ftje) {
        int msg = 0;
        int bz_solo_id=0;
        ResultSet rs = null;

        DataBase db = new DataBase();
        
        try {
            db.connectDb();
            //2015-2-2
            //����bz_solo
            rs = null;
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO BZ_SOLO(STATE,COMPNAME,DBNAME,DBCODE,ZDNAME,ZDCODE,FPTYPE," +
            		"JFFS,STARTTIME,ENDTIME,RCNAME,RCCODE,MONEY,ALLMONEY,FAXMONEY,DIANLIANG,PRICE," +
            		"YWTYPE,YWCODE,YSNAME,YSCODE,SHI,SHICODE,ELEID,FTJE)");
            sql.append(" VALUES ('0','"+COMPNAME+"','"+DBNAME+"','"+
                    DBCODE+"','"+ZDNAME+"','"+ZDCODE+"','"+FPTYPE+"','"+JFFS+"','"
                    +STARTTIME+"','"+ENDTIME+"','"+RCNAME+"','"+RCCODE+"','"+MONEY+"','"+
                    ALLMONEY+"','"+FAXMONEY+"','"+DIANLIANG+"','"+PRICE+"','"+YWTYPE+"','"+
                    YWCODE+"','"+YSNAME+"','"+YSCODE+"','"+SHI+"','"+SHICODE+"','"+ELEID+"','"+ftje+"')");
            System.out.println("���bz_solo��sql��"+sql);
            
            db.setAutoCommit(false);
            bz_solo_id = db.update(sql.toString(),new String[]{"ID"});
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
            msg = 2;
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

        return bz_solo_id;
    }
	//��ѯѡ��ı�����Ϣ
    public synchronized ArrayList getListByIds(String ids) {
        
        String[] arrs = ids.split(",");
        String idStrs = "";
        for(int i=0;i<arrs.length;i++){
            System.out.println(i+"<<accountId��������������������������"+arrs[i]);
           
            if(i==0){
                idStrs += arrs[i];
            }else{
                idStrs += ","+arrs[i];
            }
        }
       
        DataBase db = new DataBase();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            db.connectDb();
            StringBuffer s2 = new StringBuffer();
            
            s2.append("select b.id ID,b.state STATE,(select t.name from indexs t where t.code=b.compname and t.type='gszt') as COMPNAME,b.compname COMPCODE,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                    "(select t.name from indexs t where t.code=b.fptype and t.type='PJLX') as FPTYPE,b.fptype FPCODE," +
                    "b.jffs JFFS,b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME,b.rccode RCCODE,b.money MONEY,b.allmoney ALLMONEY," +
                    "b.faxmoney FAXMONEY,b.dianliang DIANLIANG,b.price PRICE,b.isbz ISBZ,b.YWTYPE YWTYPE,b.YWCODE YWCODE,b.YSNAME YSNAME,b.YSCODE YSCODE,b.SHI SHINAME,b.SHICODE SHICODE,b.ELEID eleid " +
                    " from bz_solo b " +
                    " where 1=1  and b.id in ("+idStrs+") ORDER BY b.starttime");
            
//            s2.append("select b.id ID,b.allcostid ALLCOSTID,b.shiname SHINAME,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
//                    "(select t.name from indexs t where t.code=b.fptype and t.type='PJLX') as FPNAME,b.fptype FPTYPE," +
//                    "b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME,b.rccode RCCODE,b.allmoney ALLMONEY," +
//                    "b.faxmoney FAXMONEY,b.compname COMPNAME" +
//                    " from bz_solo b " +
//                    " where 1=1  and b.id in ("+idStrs+") ORDER BY b.starttime ");
        
            System.out.println("����IDs��ѯ�ɱ��˼�¼sql��"+s2);
            
              rs=db.queryAll(s2.toString());
              Query query = new Query();
              list = query.query(rs);
              rs.close();
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

	
	public synchronized int updateDataBaoZhang(String TAXchange,String TAXSTATE,String TAX,String ID,String DIANBIAOID,String STARTTIME,
            String ENDTIME,String SQDS,String BQDS,String DIANLIANG,
            String ALLMONEY,String DIANSUN,String SQDJ,String PRICE,
            String DAYNUM,String RJYDL,String SQRJYDL,String STATE,
            String ENTRYPENSONNEL,String ENTRYTIMEOLD,String PUEZHI, String FTJE,String BGDL) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		ResultSet rs = null;

		DataBase db = new DataBase();
		
		try {
			db.connectDb();
			//2015-2-2
			rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append("update ELECTRICFEES set STARTTIME=to_date('"+STARTTIME+"','yyyy-mm-dd')," +
					"ENDTIME=to_date('"+ENDTIME+"','yyyy-mm-dd')," +
							"SQDS="+SQDS+",BQDS="+BQDS+",DIANLIANG="+DIANLIANG+"," +
							"ALLMONEY="+ALLMONEY+",DIANSUN="+DIANSUN+"," +
							"SQDJ="+SQDJ+",PRICE="+PRICE+",DAYNUM="+DAYNUM+"," +
							"RJYDL="+RJYDL+",SQRJYDL="+SQRJYDL+",STATE="+STATE+"," +
							"TAXXIUGAIQIAN="+TAXchange+",TAXSATAE="+TAXSTATE+"," +
							"ENTRYPENSONNEL='"+ENTRYPENSONNEL+"',ENTRYTIMEOLD='"+
							ENTRYTIMEOLD+"',PUEZHI='"+PUEZHI+"',TAX='"+TAX+"',FTJE='"+FTJE+"',BGDL='"+BGDL+"' where ELECTRICFEE_ID="+ID);
			System.out.println("�޸ı���------sql:"+sql.toString());
			
			db.setAutoCommit(false);
			db.update(sql.toString());
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
			msg = 2;
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
	
//	public synchronized int addbz_solo(String state,String ID) {
//        // birthday = birthday.length()>0?birthday:null;
//        int msg = 0;
//        ResultSet rs = null;
//
//        DataBase db = new DataBase();
//        
//        try {
//            db.connectDb();
//            //2015-2-2
//            rs = null;
//            StringBuffer sql = new StringBuffer();
//            sql.append("update ELECTRICFEES set state="+state+" where ELECTRICFEE_ID="+ID);
//            System.out.println("�޸ı���sql:"+sql.toString());
//            
//            db.setAutoCommit(false);
//            db.update(sql.toString());
//            db.commit();
//            db.setAutoCommit(true);
//            msg = 1;
//    
//        
//
//        } catch (DbException de) {
//            try {
//                
//                db.rollback();
//            } catch (DbException dee) {
//                dee.printStackTrace();
//            }
//            de.printStackTrace();
//            msg = 2;
//        } finally {
//            
//            try {
//                if(rs != null){
//                rs.close();
//                }
//                if(db != null){
//                db.close();
//                }
//            } catch (Exception de) {
//                de.printStackTrace();
//            }
//        }
//
//        return msg;
//    }
	  //����
    public synchronized DianbBean getBzByID(String id) {
        ArrayList list = new ArrayList();
        DianbBean dianb = new DianbBean();
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            StringBuffer s2 = new StringBuffer();
            s2.append("SELECT B.TAX,B.FTJE FTJE,B.ELECTRICFEE_ID ID,B.DIANBIAOID DIANBIAOID," +
                    "(select d.dbname  from DIANBIAO d  where d.id = B.DIANBIAOID) as DBNAME,to_char(d.ZZSL,'fm9999999990.00') ZZSL," +
                    "B.CBZX CBZX,d.GSZT COMPNAME,d.dbbm DBCODE,d.FPLX FPLX,d.DFZFLX DFZFLX," +
                    "B.STARTTIME STARTTIME,B.ENDTIME ENDTIME,to_char(B.SQDS,'fm9999999990.00') SQDS," +
                    "to_char(B.BQDS,'fm9999999990.00') BQDS,B.BEILV BEILV,to_char(B.DIANLIANG,'fm9999999990.00') DIANLIANG," +
                    "to_char(B.ALLMONEY,'fm9999999990.00') ALLMONEY,to_char(B.DIANSUN,'fm9999999990.00') DIANSUN," +
                    "to_char(B.MONEY,'fm9999999990.00') MONEY,to_char(B.TAX,'fm9999999990.00') TAX,to_char(B.SQDJ,'fm9999999990.0000') SQDJ," +
                    "to_char(B.PRICE,'fm9999999990.0000') PRICE,B.FTXS FTXS,z.JZNAME JZNAME,z.JZCODE JZCODE," + 
                    "(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.SHI ) as SHI,z.SHI SHICODE, " + 
                    "(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAN) as XIAN,b.DAYNUM DAYNUM,b.RJYDL RJYDL,b.SQRJYDL SQRJYDL,d.ELECTRIC_SUPPLY_WAY FANGSHI " +
                    " FROM ELECTRICFEES B,DIANBIAO d,ZHANDIAN z " +
                    " where 1=1 and b.DIANBIAOID = d.ID and z.ID=d.ZDID and B.ELECTRICFEE_ID = "+id+
                    "  ORDER BY B.DIANBIAOID");
                    System.out.println("�ɱ������ݲ�ѯ��"+s2);
    
                    rs=db.queryAll(s2.toString());
                    while(rs.next()){
                    	dianb.setFaxmoney(rs.getString("TAX")==null?"":rs.getString("TAX"));
                    	dianb.setFTJE(rs.getString("FTJE")==null?"":rs.getString("FTJE"));
                        dianb.setId(rs.getString("DIANBIAOID")==null?"":rs.getString("DIANBIAOID"));
                        dianb.setCompname(rs.getString("COMPNAME")==null?"":rs.getString("COMPNAME"));
                        dianb.setDbname(rs.getString("DBNAME")==null?"":rs.getString("DBNAME"));
                        dianb.setDbcode(rs.getString("DBCODE")==null?"":rs.getString("DBCODE"));
                        dianb.setZdname(rs.getString("JZNAME")==null?"":rs.getString("JZNAME"));
                        dianb.setZdcode(rs.getString("JZCODE")==null?"":rs.getString("JZCODE"));
                        dianb.setFptype(rs.getString("FPLX")==null?"":rs.getString("FPLX"));
                        dianb.setJffs(rs.getString("DFZFLX")==null?"":rs.getString("DFZFLX"));
                        dianb.setStarttime(rs.getString("STARTTIME")==null?"":rs.getString("STARTTIME"));
                        dianb.setEndtime(rs.getString("ENDTIME")==null?"":rs.getString("ENDTIME"));
                        dianb.setAllmoney(rs.getString("ALLMONEY")==null?"":rs.getString("ALLMONEY"));
                        dianb.setDianliang(rs.getString("DIANLIANG")==null?"":rs.getString("DIANLIANG"));
                        dianb.setPrice(rs.getString("PRICE")==null?"":rs.getString("PRICE"));
                        dianb.setZzsl(rs.getString("ZZSL")==null?"0":rs.getString("ZZSL"));
                        dianb.setShi(rs.getString("SHI")==null?"0":rs.getString("SHI"));
                        dianb.setShicode(rs.getString("SHICODE")==null?"0":rs.getString("SHICODE"));
                        DecimalFormat df = new DecimalFormat("######0.00");   
                        if(dianb.getAllmoney()!=""){//{gcl-����˰����㹫ʽ =�ܽ�� / (1 + ˰��) * ˰��
                        	//dianb.setFaxmoney(df.format(Double.valueOf(dianb.getAllmoney()) / (1+Double.valueOf(dianb.getZzsl())) * Double.valueOf(dianb.getZzsl())));
                            //������//dianb.setFaxmoney(df.format(Double.valueOf(dianb.getAllmoney()) * Double.valueOf(dianb.getZzsl())));
                            dianb.setMoney(df.format(Double.valueOf(dianb.getAllmoney()) - (Double.valueOf(dianb.getAllmoney()) * Double.valueOf(dianb.getZzsl()))));
                        }else{
                            dianb.setMoney("0");
                           // dianb.setFaxmoney("0");
                        }
                       
                        
                    }
            }
        catch (DbException de) {
            de.printStackTrace();
        }catch(SQLException s){
            s.printStackTrace();
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

        return dianb;
    }
    //����
    public synchronized ArrayList<ChengbBean> getCbByID(String id) {
        DataBase db = new DataBase();
        ResultSet rs = null;
       
        ArrayList<ChengbBean> chengbList = new ArrayList<ChengbBean>();
        try {
            db.connectDb();
            for(int i=0;i<chengbList.size();i++){
            	chengbList.get(i);
            }
            
            String sql = "select rc.name RCNAME,rc.code RCCODE,to_char(aui.SHARE_RATIO,'fm9999999990.00') SHARE_RATIO,aui.BUSINESS_TYPE BUSINESS_TYPE," +
            		"(select t.name from indexs t where t.code=aui.BUSINESS_TYPE and t.type='dianbiao_ywlx') as BUSINESS_NAME,aui.DUTY_CODE DUTY_CODE,rb.NAME DUTY_NAME  " +
            		"from Accounting_Unit_info aui left join RING_COST_CENTER rc on aui.COST_CODE=rc.code " +
            		"left join RING_BUDGET_DUTY_CENTER rb on  rb.code=aui.DUTY_CODE where  aui.dbcode='"+id+"'";
        
            System.out.println("����ID��ѯ����sql��"+sql);
            
              rs=db.queryAll(sql.toString());
              while(rs.next()){
                  ChengbBean chengb = new ChengbBean();
                  chengb.setId(id);
                  chengb.setRcname(rs.getString("RCNAME")==null?"":rs.getString("RCNAME"));
                  chengb.setRccode(rs.getString("RCCODE")==null?"":rs.getString("RCCODE"));
                  chengb.setFtxs(rs.getString("SHARE_RATIO")==null?"0":rs.getString("SHARE_RATIO"));
                  chengb.setYscode(rs.getString("DUTY_CODE")==null?"":rs.getString("DUTY_CODE"));
                  chengb.setYsname(rs.getString("DUTY_NAME")==null?"":rs.getString("DUTY_NAME"));
                  chengb.setYwcode(rs.getString("BUSINESS_TYPE")==null?"":rs.getString("BUSINESS_TYPE"));
                  chengb.setYwtype(rs.getString("BUSINESS_NAME")==null?"":rs.getString("BUSINESS_NAME"));
                  chengbList.add(chengb);
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

        return chengbList;
    }
    //������
  	public synchronized int updateBaoZhangStateEle(String year ,String state,String ID) {
          // birthday = birthday.length()>0?birthday:null;
          int msg = 0;
          ResultSet rs = null;

          DataBase db = new DataBase();
          
          try {
              db.connectDb();
              //2015-2-2
              rs = null;
              StringBuffer sql = new StringBuffer();
              StringBuffer sql1 = new StringBuffer();
              sql.append("update ELECTRICFEES set state="+state+" where ELECTRICFEE_ID="+ID);
              sql1.append("update ELECTRICFEES set accountmonthold="+year+" where ELECTRICFEE_ID="+ID);
              System.out.println("�޸ı���+++++++sql:"+sql.toString());
              
              db.setAutoCommit(false);
              db.update(sql.toString());
              db.update(sql1.toString());
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
              msg = 2;
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
    //������
	public synchronized int updateBaoZhangState(String state,String ID) {
        // birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        ResultSet rs = null;

        DataBase db = new DataBase();
        
        try {
            db.connectDb();
            //2015-2-2
            rs = null;
            StringBuffer sql = new StringBuffer();
            sql.append("update ELECTRICFEES set state="+state+" where ELECTRICFEE_ID="+ID);
            System.out.println("�޸ı���+++++++sql:"+sql.toString());
            
            db.setAutoCommit(false);
            db.update(sql.toString());
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
            msg = 2;
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
	//bz_solo��
   public synchronized int updateBz_soloState(String state,String ID) {
        // birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        ResultSet rs = null;

        DataBase db = new DataBase();
        
        try {
            db.connectDb();
            //2015-2-2
            rs = null;
            StringBuffer sql = new StringBuffer();
            sql.append("update BZ_SOLO set state="+state+" where ID="+ID);
            System.out.println("�޸� �ɱ��˼�¼��״̬sql:"+sql.toString());
            
            db.setAutoCommit(false);
            db.update(sql.toString());
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
            msg = 2;
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
	//�����վ��
	public synchronized int addData_new(String LSCID, String AREA_ID, String DISTRICT_ID, String COUNTRY_ID, 
			String FULL_STATION_CODE, String STATION_NAME, String STATION_CODE,
			String SERVICE_ID, String RANK_ID, String PROPERTY_ID, String MAINTAIN_ID, String ADDRESS, 
			String LONGITUDE, String LATITUDE, String COVERED_AREA,
			String OCCUPIED_AREA, String RENT, String HIRE_TIME,String PERIOD,String RENT_ID,String LEASE_ID,String LATEST_DATE,String POWER_ID,String PRICE,
			String ELECTRICITYRATIO,String ISSUPERVISOR,String STATUS,String BATTERYUSELENGTH,String ISCOUNTRY,String SOURCESYSTEMSTATIONTYPEID,
			String SOURCESYSTEMSTATIONLEVELID,String HOUSETYPEID,String ISUSERCONFIRM,String USERSTATIONNAME,String LEAF_AREAID,
			String EDITTIME,String USEDSPECIALTY,String GROUPRANK_ID,String CC08_SUPERVISORY,String STATION_ID_INSERT_DATE, 
			String SUPERVISOR_DATE,String SUPERVISOR_TYPE,String TRANS_IP,String SUPERVISOR_EQU_IP,String TRANS_TYPE,String OLT_PORT_ADDRE) {
		int msg = 0;
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			db.connectDb();
				db.connectDb();
				rs=null;
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO ZHANDIAN_NEW(LSCID, AREA_ID, DISTRICT_ID, COUNTRY_ID, " +
						"FULL_STATION_CODE, STATION_NAME, STATION_CODE, SERVICE_ID, RANK_ID," +
						" PROPERTY_ID, MAINTAIN_ID, ADDRESS, LONGITUDE, LATITUDE, COVERED_AREA," +
						" OCCUPIED_AREA, RENT, HIRE_TIME, PERIOD, RENT_ID, LEASE_ID, " +
						"LATEST_DATE, POWER_ID, PRICE, ELECTRICITYRATIO, ISSUPERVISOR, " +
						"STATUS, BATTERYUSELENGTH, ISCOUNTRY, SOURCESYSTEMSTATIONTYPEID, " +
						"SOURCESYSTEMSTATIONLEVELID, HOUSETYPEID, ISUSERCONFIRM," +
						" USERSTATIONNAME, LEAF_AREAID, EDITTIME, USEDSPECIALTY," +
						" GROUPRANK_ID, CC08_SUPERVISORY, STATION_ID_INSERT_DATE, " +
						"SUPERVISOR_DATE, SUPERVISOR_TYPE, TRANS_IP, " +
						"SUPERVISOR_EQU_IP, TRANS_TYPE, OLT_PORT_ADDRE) VALUES ("+Integer.parseInt(LSCID)+ "," +AREA_ID+ ","
						+DISTRICT_ID+ "," +COUNTRY_ID+ ",'" +FULL_STATION_CODE+ "','" +STATION_NAME+ "','" +STATION_CODE+ "','" +
					SERVICE_ID+ "','" + RANK_ID+ "'," + PROPERTY_ID+ "," + MAINTAIN_ID+ ",'" +
					ADDRESS+ "'," + LONGITUDE+ "," + LATITUDE+ "," + COVERED_AREA+ "," +
					OCCUPIED_AREA+ "," + RENT+ ",'" + HIRE_TIME+ "'," + PERIOD+ "," +
					RENT_ID+ "," + LEASE_ID+ ",'" + LATEST_DATE+ "'," + POWER_ID+ "," + PRICE+ "," +
					ELECTRICITYRATIO+ "," + ISSUPERVISOR+ "," + STATUS+ "," + BATTERYUSELENGTH+ "," +
					ISCOUNTRY+ "," + SOURCESYSTEMSTATIONTYPEID+ "," +
					SOURCESYSTEMSTATIONLEVELID+ "," + HOUSETYPEID+ "," + ISUSERCONFIRM+ ",'" +
					USERSTATIONNAME+ "'," + LEAF_AREAID+ ",'" +
					EDITTIME+ "','" + USEDSPECIALTY+ "'," + GROUPRANK_ID+ ",'" + CC08_SUPERVISORY+ "','" + STATION_ID_INSERT_DATE+ "','" + 
					SUPERVISOR_DATE+ "'," + SUPERVISOR_TYPE+ ",'" + TRANS_IP+ "','" + SUPERVISOR_EQU_IP+ "'," + TRANS_TYPE+ ",'" +OLT_PORT_ADDRE+ "')" );
				System.out.println("��վ���_new��sql��"+sql.toString());
				
				db.setAutoCommit(false);
				db.update(sql.toString());
				db.commit();
				db.setAutoCommit(true);
			    msg=1;
        

		} catch (DbException de) {
			msg=2;
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

		String sql = "select id from zhandian where zdcode='" + zdcode.trim()    //trim()���ַ����Ƴ���ǰ����β���հ׵ĸ��������û��ǰ����β���հף��򷵻ش��ַ�����

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

	
    //�õ��id�Զ�����   ����������DBID Ȼ��+1 
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
    //�޸�վ��
    public synchronized int midifyData(String shi,String xian,String xiaoqu,String ycq,String jztype,String jzproperty,
    		String yflx,String jflx,String jzname,String jzcode, String zzlx,String zdcq2,String wlzdbm,String ltqx,String ydqx,String xtid,
			String jingdu,String weidu, String phone,String gdfname,String bumen,
    		String bieming,String address,String bgsign,String jnglmk,String gdfs,
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
		//String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update zhandian  set  PROPERTY='" + jzproperty
				+ "',YFLX='" + yflx + "',JZNAME='" + jzname +"',JZCODE='"+jzcode+"',ZZLX='"+zzlx+"',ZDCQ='"+zdcq2+"',WLZDBM='"+wlzdbm+"',ltqx='"+ltqx
				+ "',YDQX='"+ydqx+"',NHXTID='"+xtid+"',LONGITUDE='"+jingdu+"',LATITUDE='"+weidu+"',FZRPHONE='"+phone+"',CHANGJIA='"+gdfname+"',BM='"+bumen+"',BIEMING='"
				+ bieming + "',ADDRESS='" + address + "'");
		sql.append(",GDFS='"
				+ gdfs + "',AREA='" + area + "',FZR='"
				+ fzr + "'");
		sql.append(",SHENG='" + accountSheng  + "'");
		//sql.append("XIAN='"+xian+"'");
		sql.append(",ENTRYPENSONNEL='" + entrypensonnel + "',ENTRYTIME=" + s + ",STATIONTYPE='" + stationtype + "'");
		sql.append(" where id=" + id);
		System.out.println("�޸�վ����Ϣ"+sql.toString());
		
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			StringBuffer s2 = new StringBuffer();
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
    //ɾ����ѵ渶��Ϣ
    public synchronized int delDatadf(String id){

		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("DELETE FROM DFDFHS DF WHERE DF.ID='"+id+"'");//ɾ��zddfinfo���������
		System.out.println("ɾ��վ����Ϣ��"+sql1.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql1.toString());//ɾ��zddfinfo���������
			db.setAutoCommit(true);
			msg = 1;
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} catch (Exception de) {

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
    //ɾ��վ��
    public synchronized int delData(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		sql1.append("delete zddfinfo df where df.zdid in(select zd.id from zhandian zd where zd.id='"+id+"')");//ɾ��zddfinfo���������
		sql2.append("delete sbgl sb where sb.dianbiaoid in(select d.dbid from zhandian z,dianbiao d where z.id=d.zdid and d.dbyt='dbyt01' and z.id='"+id+"')");//ɾ����̯��Ϣ   ������з�̯
		sql3.append("delete dianbiao d where d.dbid in (select d.dbid from zhandian z,dianbiao d where z.id=d.zdid and z.id='"+id+"')");//ɾ����� ����
		sql.append("delete from zhandian where id=" + id);
		System.out.println("ɾ��վ����Ϣ��"+sql.toString());
		System.out.println("ɾ�������Ϣ��"+sql1.toString());
		System.out.println("ɾ����̯��Ϣ��"+sql2.toString());
		System.out.println("ɾ�������Ϣ��"+sql3.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateDelZhandian(db, id).equals("0")||this.validateDeldf(db, id).equals("0")) {
				return 2;
			}
			
			db.setAutoCommit(false);
			db.update(sql1.toString());//ɾ��zddfinfo���������
			
			db.update(sql2.toString());//ɾ����̯��Ϣ   ������з�̯
			
			db.update(sql3.toString());//ɾ�����
			db.update("delete from zhandiankz where zdid=" + id);//��չ��Ϣ
			db.update(sql.toString());//ɾ��վ��
			
			
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
  //���ر���
    public synchronized int chBaozhang(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("update electricfees e set e.state=0 where  ELECTRICFEE_ID=" + id);
		System.out.println("���ر�����Ϣ��"+sql.toString());
		sql1.append("delete s_workflow s  where s.current_no is not null and s.task_id =" + id);
		System.out.println("ɾ����Ӧ���̣�"+sql1.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql.toString());//�޸�״̬
			db.update(sql1.toString());//ɾ������
			
			
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
  //������������
    public synchronized int chttba(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("update electricfees e set e.state=0 where  ELECTRICFEE_ID=" + id);//�޸�״̬Ϊδ�ϱ�
		System.out.println("���ر�����Ϣ��"+sql.toString());
		sql1.append("delete s_workflow s  where s.current_no is not null and s.task_id =" + id);
		System.out.println("ɾ����Ӧ���̣�"+sql1.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql.toString());//�޸�״̬
			db.update(sql1.toString());//ɾ������
			
			
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
  //���ذ칫Ӫҵ����
    public synchronized int chbg(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("update zhandian z set z.approve_status=2 where  z.id=" + id);//�޸�״̬Ϊδ�ϱ�
		System.out.println("���ر�����Ϣ��"+sql.toString());
		sql1.append("delete s_workflow s  where s.current_no is not null and s.task_id =" + id);
		System.out.println("ɾ����Ӧ���̣�"+sql1.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql.toString());//�޸�״̬
			db.update(sql1.toString());//ɾ������
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
    //���ص����������
    public synchronized int chdianbiao(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("update dianbiao d set d.shzt=0 where  id=" + id);
		System.out.println("���ص���ύ��Ϣ��"+sql.toString());
		sql1.append("delete s_workflow s  where s.current_no is not null and s.task_id =" + id);
		System.out.println("ɾ�������Ӧ���̣�"+sql1.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql.toString());//�޸�״̬
			db.update(sql1.toString());//ɾ������
			
			
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
  //ɾ��վ��
    public synchronized int delDataBaozhang(String id) {
		int msg = 0;
		CTime ctime = new CTime();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("delete from ELECTRICFEES where ELECTRICFEE_ID=" + id);
		System.out.println("ɾ��������Ϣ��"+sql.toString());
		sql1.append("delete from ANALYSIS where bzid=" + id);
		System.out.println("ɾ����Ӧ����PUE��Ϣ��"+sql1.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql.toString());//ɾ��վ��
			db.update(sql1.toString());//ɾ��PUE
			
			
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
  //����ID��ѯ���ڶ���
  	public String selectSQDS(String id){
  		String HZSTATE= ""; 		//����String
  		String sql = "select e.sqds from electricfees  e where e.electricfee_id = '"+id+"'";
  		System.out.println("��ȡString��sql��"+sql);
  		ResultSet rs = null;
  		DataBase db = new DataBase();
  		try {
  			db.connectDb();
  			rs = db.queryAll(sql);
  			while(rs.next()){
  				HZSTATE = rs.getString("sqds");
  			}
  		} catch (Exception de) {
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
  		return HZSTATE;
  	}//����ID��ѯ���ڶ���
	public String selectBQDS(String id){
		String HZSTATE= ""; 		//����String
		String sql = "select e.bqds from electricfees  e where e.electricfee_id = '"+id+"'";
		System.out.println("��ȡString��sql��"+sql);
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				HZSTATE = rs.getString("bqds");
			}
		} catch (Exception de) {
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
		return HZSTATE;
	}
   //����ID��ѯ��ת״̬
	public String selectHZSTATE(String id){
		String HZSTATE= ""; 		//����String
		String sql = "SELECT D.HZSTATE FROM DIANBIAO D WHERE D.ID = (select e.dianbiaoid from electricfees  e where e.electricfee_id = '"+id+"')";
		System.out.println("��ȡString��sql��"+sql);
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				HZSTATE = rs.getString("HZSTATE");
			}
		} catch (Exception de) {
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
		return HZSTATE;
	}
    //����ת״̬�޸�
	public int updateHZSTATE(String ID,int HZSTATE) {
		int rs = 0;
		String sql = "UPDATE DIANBIAO D SET D.HZSTATE = '"+HZSTATE+"' WHERE D.ID in (select e.dianbiaoid from electricfees e where e.electricfee_id = '"+ID+"')";		
		DataBase db = new DataBase();
		System.out.println("����תHZSTATE�ֶ��޸�sql��"+sql);
		try {
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	
   private String validateDelZhandian(DataBase db, String zdid)
	throws DbException, SQLException {
    String msg = "1";
    System.out.println("22222222");
    StringBuffer sql = new StringBuffer();
    sql.append("select a.ammeterdegreeid from ammeterdegrees a, dianbiao d, zhandian z where z.id = d.zdid and d.dbid = a.ammeterid_fk and z.id=" + zdid);
    System.out.println("������ѯ"+sql.toString());
    ResultSet rs = db.queryAll(sql.toString());//ɾ����վ�����Ƿ��е�������оͲ���ɾ��
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
    System.out.println("��ѯ�����Ϣ��"+sql.toString());
    ResultSet rs = db.queryAll(sql.toString());//ɾ����վ�����Ƿ��е�� ����оͲ���ɾ��         Ԥ������û�е���
    if (rs.next()) {
	msg = "0";
    }
    return msg;
    }
    
    
    
    
    //��ӱ������
    public synchronized int addSign(String name,String type,String olt,String g2,String g3,String ktnum,String dytype){
    	int msg = 0;
    	StringBuffer sql = new StringBuffer();
    	sql.append("INSERT INTO benchmarking(NAME,STATIONTYPEID,OLT,G2,G3,AIR_CONDITION,REGION)");
    	sql.append("VALUES ('" + name + "','" + type + "','" + olt + "','" + g2 + "','" + g3 + "','" + ktnum + "','" + dytype + "')");
    	System.out.println("��ӱ������"+sql.toString());
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
    //�޸ı������
    public synchronized int modifySign(String id,String name,String type,String olt,String g2,String g3,String ktnum,String dytype){
    	int msg = 0;
    	CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update BENCHMARKING set name='" + name + "',STATIONTYPEID='" + type + "',REGION='" + dytype + "',G2='" + g2 + "',G3='" + g3 + "',OLT='" + olt + "',AIR_CONDITION='" + ktnum + "'  where id='"+id+"' ");
		DataBase db = new DataBase();
		System.out.println("�޸ı�����ͣ�"+sql.toString());
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
    
    //ɾ���������
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

    /**
     * ���ݵ��зֹ�˾��ѯ����������
     * @param shi
     * @param fdeptcode
     * @return
     */
    public synchronized String getMaxDeptcode(String shi, String fdeptcode ){
    	StringBuffer sql = new StringBuffer();
    	sql.append("select max(d.deptcode) deptcode from department d where d.isused='01'and d.shi='"+shi+"' and d.fdeptcode='"+fdeptcode+"' group by d.fdeptcode");
    	DataBase db = new DataBase();
    	ResultSet rs = null;
    	try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
    /**
     * ����������Ϣ
     * @param shi  ��
     * @param deptcode ���ű���
     * @param deptname ��������
     * @param fdeptcode �й�˾����
     * @param is 
     * @return
     */
    public synchronized int addDept(String shi, String deptcode, String deptname, String fdeptcode){
    	StringBuffer sql = new StringBuffer();
    	sql.append("insert into department(deptcode,deptname,fdeptcode,shi,isused)");
    	sql.append("values ('" + deptcode + "','"  + deptname + "','"+fdeptcode+"','"+shi+"','"+Constant.ISUSED_DEFAULT_VALUE+"')");
    	System.out.println("��Ϣ��"+sql.toString());
    	DataBase db = new DataBase();
    	int cnt = 0;
    	try {
			db.connectDb();
			cnt = db.update(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return cnt;
    }

    /**
     * ���ݲ���id���²�������
     * @param deptname ��������
     * @param id 
     * @return
     */
    public synchronized int updateDept(String deptname, String id){
    	StringBuffer sql = new StringBuffer();
    	sql.append("update department set deptname='"+deptname+"' where id='"+id+"'");
    	System.out.println("��Ϣ��"+sql.toString());
    	DataBase db = new DataBase();
    	int cnt = 0;
    	try {
			db.connectDb();
			cnt = db.update(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return cnt;
    }
    //��Ӳ���
    public synchronized int addbumenguanli1(String bumen,String mingcheng,String bianma,String fglength){
    	int msg = 0;
    	StringBuffer sql = new StringBuffer();
    	sql.append("INSERT INTO DEPARTMENT(DEPTCODE,DEPTNAME,FDEPTCODE,DEPTGRADE)");
    	sql.append("VALUES ('" + bumen + "','"  + mingcheng + "','"+bianma+"','"+fglength+"')");
    	System.out.println("��Ϣ��"+sql.toString());
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
    
  //���
    public synchronized int addquyuguanli1(String quyu,String mingcheng){
    	int msg = 0;
    	StringBuffer sql = new StringBuffer();
    	sql.append("INSERT INTO D_AREA_GRADE(AGID,AGCODE,AGNAME)");
    	sql.append("VALUES (AREA_SEQ.NEXTVAL,'" + quyu + "','"  + mingcheng + "')");
    	System.out.println("��Ϣ��"+sql.toString());
    	System.out.println("��Ϣ����"+sql); 
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
    
  //��ӹ���
    public synchronized int addGgao(String xxlx,String bt,String nr){
    	int msg = 0;
    	StringBuffer sql = new StringBuffer();
    	sql.append("INSERT INTO gonggao(xxtype,bt,nr)");
    	sql.append("VALUES ('" + xxlx + "','"  + bt + "','" + nr + "')");
    	System.out.println("���棺"+sql.toString());
    	System.out.println("��������"+sql); 
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
  //��ѯ������Ϣ  ǰ5����Ϣ
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
	
	 //��ѯȫ��������Ϣ
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
	//�޸Ĺ���
    public synchronized int modifyGgao(String id,String xxtype, String ggtime,String dqtime,String bt,String nr,String lrr,String lrrId){
    	int msg = 0;
    	CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update gonggao set xxtype='" + xxtype + "',ggtime='" + ggtime + "',dqtime='" + dqtime + "',bt='" + bt + "',nr='" + nr + "',lrr='" + lrr +"',userid=" + lrrId +  "  where id='"+id+"' ");
		DataBase db = new DataBase();
		System.out.println("�޸Ĺ��棺"+sql.toString());
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
  //ɾ������
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
    //��ѯ�ϴ��ļ�
	public synchronized ArrayList getFiles(){
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT F.ID,F.FILENAME,F.UPLOADDATE,(SELECT A.NAME FROM ACCOUNT A WHERE A.ACCOUNTID = F.UPLOADPEOPLE)AS NAME FROM FILETABLE F ORDER BY F.UPLOADDATE DESC");
		ResultSet rs = null;
		System.out.println("��ѯ�ϴ����ļ���"+sql.toString());
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
			System.out.println("�޸�ʡ�����������"+sql.toString());
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
			System.out.println("��վ�ܺ��ظ�ɾ����"+sql.toString());
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
			System.out.println("��վ�ܺĲ��룺"+sql.toString());
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
		public synchronized int nhchenk(String month){//�����ɵ��Ƿ�ǩ�ղ�ѯ
			String sql ="select z.* from jzxx z where z.symonth ='"+month+"'";
			System.out.println("��վ�ܺ��ظ��ж�:"+sql);
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
		public synchronized ArrayList getSiteDB(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("select z.jzname,d.dbbm,d.dbname,case d.dbyt when 'dbyt01' then '����' when 'dbyt02' then '�ɼ�' else '����' end dbyt,d.beilv ,Rtusername(d.bzr) A  from zhandian z,dianbiao d where z.id=d.zdid"+whereStr);
	                    System.out.println("վ���ѯ"+s2);
				
	        
	               
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
		public synchronized ArrayList getDanjia(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("select z.jzname, d.yearmonth,d.dbbm,d.danjia,db.dbname,db.beilv,case db.dbyt when 'dbyt01' then '����' when 'dbyt02' then '�ɼ�' else '����' end dianyt,d.id from dianjia d ,dianbiao db,zhandian z where d.zdid=z.id and db.zdid=z.id"+whereStr);
	               System.out.println("վ���ѯ"+s2);           
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
		public synchronized ArrayList getDj(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("select z.jzname,d.yearmonth,d.dbbm,d.danjia,db.dbname,db.beilv,case db.dbyt when 'dbyt01' then '����' when 'dbyt02' then '�ɼ�' else '����' end dianyt,d.id from dianjia d ,dianbiao db,zhandian z where d.zdid=z.id and db.zdid=z.id "+whereStr);
	               System.out.println("վ���ѯ"+s2);           
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
		public synchronized ArrayList getFtbl(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("select f.id,z.jzname,f.yearmonth,f.yidong,f.liantong,f.dianxin,f.qita from ftbl f,zhandian z where f.zdid=z.id "+whereStr);
	               System.out.println("վ���ѯ"+s2);
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
		public synchronized ArrayList getSfyz(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("select s.id, z.jzname,s.dbbm,s.yearmonth,s.sfyz from SFYZ s,zhandian z where s.zdid=z.id "+whereStr);
	                    System.out.println("վ���ѯ"+s2);
				
	        
	               
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
		public synchronized ArrayList getCbyuan(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("select c.id,a.accountname, c.userid,a.name,z.jzname from cbyuan c,account a,zhandian z where c.userid=a.accountid and c.zdid=z.id "+whereStr);
	                    System.out.println("վ���ѯ"+s2);
				
	        
	               
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("վ���ѯ��ҳ"+s3); 
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
		public synchronized ArrayList getPageDataZD(int start,String loginName,String loginId,String whereStr) {
			ArrayList list = new ArrayList();
			CTime ct = new CTime();

			
			DataBase db = new DataBase();
			ResultSet rs = null;
			StringBuffer strall = new StringBuffer();
			//strall.append("select z.id from zhandian z where 1=1 "+whereStr);
			//strall.append(cxtj.toString());
	        String fzzdstr="";
			try {
				fzzdstr = getFuzeZdid(db,loginId);
				System.out.println("����վ��"+fzzdstr);
				db.connectDb();
				StringBuffer s2 = new StringBuffer();
				StringBuffer s3 = new StringBuffer();
				s2.append("SELECT I.ID,I.CODE,I.NAME,I.TYPE,I.INDEXS1 FROM INDEXS I WHERE 1=1 "+whereStr+" ORDER BY I.CODE  ");
	                    System.out.println("�ֵ��ѯ"+s2);
				
	        
	               
	               s3.append("select count(*)  from (" + s2 + ")");
	               System.out.println("�ֵ��ѯ��ҳ"+s3); 
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
		public synchronized int adddatadfzd(DianbiaoBean dbb){
			int msg = 0;
			ResultSet rs = null;
			CTime ctime = new CTime(); 
			String inserttime = ctime.formatWholeDate(new Date());//��:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			inserttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
			String inserttime1 = ctime.formatWholeDate(new Date());
			inserttime1=new SimpleDateFormat("yyyy-MM").format(new Date());
			String s1="to_date(to_char(sysdate, 'yyyy-mm'),'yyyy-mm')";
			String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
			String jsname="������";//������������
			String glname="������";//������������
			Random rnd = new Random();//�����
			
			DataBase db = new DataBase();
			
			try {
				db.connectDb();
					db.connectDb();
					rs=null;
					StringBuffer sql = new StringBuffer();
					sql.append("INSERT INTO INDEXS(CODE,NAME,TYPE,INDEXS1)");
					sql.append("VALUES ('"+dbb.getXmbm()+"','" + dbb.getXmname() + "','" + dbb.getXmlx() + "','"+ dbb.getXmlxsm() + "')" );
					
					
					System.out.println("�ֵ���Ϣ���sql��"+sql.toString());
					
					db.setAutoCommit(false);
					db.update(sql.toString());
					db.commit();
					db.setAutoCommit(true);
				    msg=1;
	        

			} catch (DbException de) {
				msg=2;
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
		  public synchronized int delDatazd(String id) {
				int msg = 0;
				CTime ctime = new CTime();
				StringBuffer sql = new StringBuffer();
				
				sql.append("DELETE INDEXS I WHERE I.ID='"+id+"'");
				
				System.out.println("ɾ��վ����Ϣ��"+sql.toString());
				DataBase db = new DataBase();
				try {
					db.connectDb();
					db.setAutoCommit(false);
					db.update(sql.toString());//ɾ��վ��
					db.setAutoCommit(true);
					msg = 1;
				} catch (DbException de) {
					try {
						db.rollback();
					} catch (DbException dee) {
						dee.printStackTrace();
					}
					de.printStackTrace();
				}  finally {
					try {

						db.close();
					} catch (Exception de) {
						de.printStackTrace();
					}
				}

				return msg;
			}
		  public synchronized ArrayList getPageDataZDXG(String bz,String id) {
				ArrayList list = new ArrayList();
				CTime ct = new CTime();

				
				DataBase db = new DataBase();
				ResultSet rs = null;
				StringBuffer strall = new StringBuffer();
				//strall.append("select z.id from zhandian z where 1=1 "+whereStr);
				//strall.append(cxtj.toString());
		        String fzzdstr="";
				try {
					//fzzdstr = getFuzeZdid(db,loginId);
					System.out.println("����վ��"+fzzdstr);
					db.connectDb();
					StringBuffer s2 = new StringBuffer();
					s2.append("SELECT I.ID,I.CODE,I.NAME,I.TYPE,I.INDEXS1 FROM INDEXS I WHERE I.ID="+id);
		                    System.out.println("վ���ѯ"+s2);
					
		        
		               
		             
		               rs=db.queryAll(s2.toString());
		           Query query = new Query();
		              list = query.query(rs);
		              rs.close();		
				}

				catch (DbException de) {
					de.printStackTrace();
				} catch(SQLException ef){
					ef.printStackTrace();
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
			public synchronized int adddatadfxgzd(DianbiaoBean dbb){
				int msg = 0;
				ResultSet rs = null;
				CTime ctime = new CTime(); 
				String inserttime = ctime.formatWholeDate(new Date());//��:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				inserttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
				String inserttime1 = ctime.formatWholeDate(new Date());
				inserttime1=new SimpleDateFormat("yyyy-MM").format(new Date());
				String s1="to_date(to_char(sysdate, 'yyyy-mm'),'yyyy-mm')";
				String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
				String jsname="������";//������������
				String glname="������";//������������
				Random rnd = new Random();//�����
				
				DataBase db = new DataBase();
				
				try {
					db.connectDb();
						db.connectDb();
						rs=null;
						StringBuffer sql = new StringBuffer();
						sql.append(" UPDATE INDEXS I SET I.CODE='"+dbb.getXmbm()+"',I.NAME='"+dbb.getXmname()+"',I.TYPE='"+dbb.getXmlx()+"',I.INDEXS1='"+dbb.getXmlxsm()+"' WHERE I.ID='"+dbb.getId()+"'");
						
						System.out.println("�ֵ���Ϣ���sql��"+sql.toString());
						
						db.setAutoCommit(false);
						db.update(sql.toString());
						db.commit();
						db.setAutoCommit(true);
					    msg=1;
		        

				} catch (DbException de) {
					msg=2;
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
			public synchronized ArrayList getxxxg(String id,String bz) {
				ArrayList list = new ArrayList();
				CTime ct = new CTime();
				
				DataBase db = new DataBase();
				ResultSet rs = null;
			
		        String fzzdstr="";
				try {
					System.out.println("����վ��"+fzzdstr);
					db.connectDb();
					StringBuffer s2 = new StringBuffer();
					StringBuffer s3 = new StringBuffer();
					s2.append("select g.id, g.xxtype,i.name,g.nr,g.bt from gonggao g,indexs i where g.xxtype=i.code AND G.ID='"+id+"'");
		           
					System.out.println("վ���ѯ"+s2);
					
		               rs=db.queryAll(s2.toString());
		              Query query = new Query();
		              list = query.query(rs);
		              rs.close();		
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
			 public synchronized int addGgaoxg(String xxlx,String bt,String nr,String id){
			    	int msg = 0;
			    	StringBuffer sql = new StringBuffer();
			    	sql.append("UPDATE GONGGAO G SET G.BT='"+bt+"',G.NR='"+nr+"' WHERE G.ID="+id);
			  System.out.println("�޸�sql��"+sql.toString());
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
			
			 
			 public synchronized int updatequyu(String quyu,String mingcheng,String id){
			    	int msg = 0;
			    	StringBuffer sql = new StringBuffer();
			    	sql.append("UPDATE D_AREA_GRADE D SET D.AGCODE='"+quyu+"',D.AGNAME='"+mingcheng+"' WHERE D.AGID="+id);
			     System.out.println("�޸�sql��"+sql.toString());
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
			 //xiugai����
			 public synchronized int updatebumen(String mingcheng,String id){
			    	int msg = 0;
			    	StringBuffer sql = new StringBuffer();
			    	sql.append("UPDATE DEPARTMENT SET DEPTNAME='"+mingcheng+"' WHERE ID="+id);
			     System.out.println("�޸�sql��"+sql.toString());
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
			 /**
			  * ���������ȡ���ids
			  * @param shi_s
			  * @param xian_s
			  * @param xiaoqu_s
			  * @param zdmc_s
			  * @return
			  */
			 public synchronized ArrayList getDianbiaoList(String shi_s,String xian_s,String xiaoqu_s,String zdmc_s){
			    	String id="0";
			        ArrayList list = new ArrayList();
			        String sql = "select d.ID from dianbiao d inner join zhandian z on z.id = d.zdid where 1=1 and d.SHZT = 2 and d.dbqyzt ='zt01'  ";
			        if(zdmc_s!=null && !zdmc_s.equals("") && !zdmc_s.equals("0")){
			        	sql = sql + " and z.id='"+zdmc_s+"' ";
			        }else if(xiaoqu_s!=null && !xiaoqu_s.equals("") && !xiaoqu_s.equals("0")){
			        	sql = sql + " and z.XIAOQU='"+xiaoqu_s+"' ";
			        }else if(xian_s!=null && !xian_s.equals("") && !xian_s.equals("0")){
			        	sql = sql + " and z.XIAN='"+xian_s+"' ";
			        }else if(shi_s!=null && !shi_s.equals("") && !shi_s.equals("0")){
			        	sql = sql + " and z.SHI='"+shi_s+"' ";
			        }
			        System.out.println("Ԥ����pue��sql��"+sql);
			        DataBase db = new DataBase();
			        ResultSet rs = null;
			        try {
			            db.connectDb();
			            rs = db.queryAll(sql);
			            Query query = new Query();
			            list = query.query2(rs);
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			            list = new ArrayList();
			        }finally{
						try {
							db.close();
						} catch (DbException e) {
							e.printStackTrace();
						}
					}
			        return list;
			    }
			 public synchronized List<ElecBean> getBzByDbid(String dbid){
				 List<ElecBean> listStr = new ArrayList<ElecBean>();
				 
				
						 String sql = " select * from (";
			         sql = sql+ "select to_char(e.DIANLIANG,'fm9999999990.00') DIANLIANG," +
			         		"e.DAYNUM, to_char(e.PRICE,'fm9999999990.0000') PRICE," +
			         		"to_char(e.ENDTIME,'yyyy-MM-dd') ENDTIME,e.ELECTRICFEE_ID   " +
			         		" from ELECTRICFEES e where e.STATE=4 and e.dianbiaoid="+dbid+" order by E.ENDTIME desc";
					
			         sql = sql+ ") where rownum<3";
			        
			        DataBase db = new DataBase();
			        ResultSet rs = null;
			        try {
			            db.connectDb();
			            rs = db.queryAll(sql);
			            while (rs.next()) {
			            	ElecBean elecBean = new ElecBean();
			            	elecBean.setElecid(rs.getString("ELECTRICFEE_ID"));
			            	//elecBean.setMoney(rs.getString("MONEY"));
			            	elecBean.setDianliang("DIANLIANG");
			            	elecBean.setDaynum(rs.getString("DAYNUM"));
			            	elecBean.setPrice(rs.getString("PRICE"));
			            	elecBean.setEndtime(rs.getString("ENDTIME"));
			            	listStr.add(elecBean);
			            }
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			            listStr = new ArrayList<ElecBean>();
			        }finally{
						try {
							db.close();
						} catch (DbException e) {
							e.printStackTrace();
						}
					}
			        return listStr;
			    }
			 
				public synchronized String getDianbiaoById(String dianbiaoID) {
					String strs = "";
					DataBase db = new DataBase();
					ResultSet rs = null;
					ArrayList list = new ArrayList();
			        String fzzdstr="";
					try {
						System.out.println("����վ��"+fzzdstr);
						db.connectDb();
						StringBuffer s2 = new StringBuffer();
						
						s2.append("select to_char(d.danjia,'fm9999999990.0000') DANJIA,d.ELECTRIC_SUPPLY_WAY ESW, " +
								"z.JZNAME JZNAME,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.SHI ) as SHI," +
								"(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAN) as XIAN,d.BEILV BEILV,to_char(d.ZZSL,'fm9999999990.00'), " +
								"(select t.name from indexs t where t.code=d.ELECTRIC_SUPPLY_WAY and t.type='GDFS') as FANGSHINAME," +
								"z.xian as XIANCODE,d.CSDS CSDS,d.CSSYTIME CSSYTIME,d.dbbm,(select i.name from indexs i where i.type='ydsx' and i.code=d.ydsx)ydsx,(select i.name from indexs i where i.type='gszt' and i.code=d.gszt)gszt,d.cbzx,d.cbzxbm,d.ftbl ftbl,d.sfyz sfyz,"
								+ "(select i.name"
								+ " from indexs i"
								+ " where i.type='PJLX'"
								+ " and i.code = d.fplx) FPLX ,d.ydsx ydsxbm,d.MAXDS MAXDS,d.HZSTATE HZSTATE" +
								" from dianbiao d join zhandian z "
								+ "on z.id=d.zdid"
								+ " and d.id="+dianbiaoID);
					//,s.name FPLX
						System.out.println("����ID��ѯ���sql---gcl(getDianbiaoById)��"+s2);
			              rs=db.queryAll(s2.toString());
			              Query query = new Query();
			              list = query.query2(rs);
			              rs.close();
			              if(list!=null){
			            	  for(int i=0;i<list.size();i++){
			            		
			            		  if(i!=0){
			            			  strs = strs + "@_@";
			            		  }
			            		  strs = strs + list.get(i);
			            	  }
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

					return strs;
				}
				public synchronized String getshiById(String dianbiaoID) {
					String strs = "";
					DataBase db = new DataBase();
					ResultSet rs = null;
					ArrayList list = new ArrayList();
					try {
						db.connectDb();
						StringBuffer s2 = new StringBuffer();
						
						s2.append("select z.SHI as SHI " +
								"from dianbiao d,zhandian z where z.id=d.zdid and d.id="+dianbiaoID);
					
						System.out.println("����ID��ѯ���sql��"+s2);
						
			              rs=db.queryAll(s2.toString());
			              while (rs.next()) {
			            	  strs = rs.getString(1);
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

					return strs;
				}
				public synchronized String getBaozhangByDianbiaoId(String dianbiaoID) {
					String strs = "";
					DataBase db = new DataBase();
					ResultSet rs = null;
					ArrayList list = new ArrayList();
			        String fzzdstr="";
					try {
						System.out.println("����վ��"+fzzdstr);
						db.connectDb();
						StringBuffer s2 = new StringBuffer();
						
						//�޸Ĳ�ѯ��� ����ֻ������� ���ֻ�����ʼʱ�䣬�ֻ������ڶ������ֻ������վ��õ�����ENDTIME_C,BQDS_C,RJYDL_C��
						s2.append("select e.ENDTIME,e.BQDS, to_char(e.PRICE,'fm9999999990.0000') PRICE," +
								"e.RJYDL RJYDL,e.ENDTIME_C,e.BQDS_C,e.RJYDL_C " +
								"from ELECTRICFEES e where e.dianbiaoid="+dianbiaoID+" and STATE = 4 order by E.ENDTIME desc");
						//���STATE = 4 ��֤��ȷ��������SAP  
						System.out.println("����ID��ѯ����sql��"+s2);
						
			              rs=db.queryAll(s2.toString());
			              Query query = new Query();
			              list = query.query2(rs);
			              rs.close();
			              if(list!=null){
			            	  for(int i=0;(i<list.size()&&i<7);i++){
			            		
			            		  if(i!=0){
			            			  strs = strs + "@_@";
			            		  }
			            		  strs = strs + list.get(i);
			            	  }
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

					return strs;
				}
				
				/**
				 * ���ݵ���ȡ��Ӧ���µĵ����Ϣ
				 * @param dbcode
				 * @return
				 */
				public synchronized ArrayList getLatestAccountData(String dbcode){
					StringBuffer sql = new StringBuffer();
					sql.append("select a.*, rownum rn from ( ");
					sql.append("select e.ENDTIME,e.BQDS,e.PRICE , d.beilv BEILV,d.danjia DANJIA, d.id from ELECTRICFEES e left join dianbiao d on d.id = e.dianbiaoid ");
					sql.append("where d.id = '").append(dbcode).append("'");
					sql.append("order by e.endtime desc");
					sql.append(") a where rownum = 1");
					System.out.println("�ɱ�����Ϣ��ѯ" + sql);
					
					ArrayList list = new ArrayList();
					
					return list;
					
					
				}
				
				//������������������Ϣ 2018/03/22 sgn 
			    public synchronized ArrayList getAllTietabz(int start,String loginName,String loginId,String whereStr) {
			        ArrayList list = new ArrayList();
			        CTime ct = new CTime();

			        
			        DataBase db = new DataBase();
			        ResultSet rs = null;
//			      StringBuffer strall = new StringBuffer();
//			      strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
			        //strall.append(cxtj.toString());
			        try {
			            db.connectDb();
			            StringBuffer s2 = new StringBuffer();
			            StringBuffer s3 = new StringBuffer();
			            s2.append("select b.FTJE FTJE,d.sfyz SFYZ,d.FTBL FTBL,b.id ID,b.state STATE,(select t.name from indexs t where t.code=b.compname and t.type='gszt') as COMPNAME," +
			            		"b.compname COMPCODE,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                                "(select t.name from indexs t where t.code=b.fptype and t.type='PJLX') as FPTYPE," +
                                "(select t.name from indexs t where t.code=b.jffs and t.type='FKFS') as JFFS," +
                                "b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME,b.rccode RCCODE,b.money MONEY,b.allmoney ALLMONEY," +
                                "b.faxmoney FAXMONEY,b.dianliang DIANLIANG,b.price PRICE,b.isbz ISBZ" +
                                " from bz_solo b,dianbiao d " +
                                " where 1=1 and d.dbbm = b.dbcode and d.ssf='1' "+whereStr+
                                " and b.state!=1  ORDER BY b.starttime ");//2018/3/22 sgn ���������ѯ�ɱ��˼�¼������dianbiao d and d.dbbm = b.dbcode and d.ssf='1'
			                    System.out.println("�����ɱ������ݲ�ѯ��"+s2);			                   			        
			               s3.append("select count(*)  from (" + s2 + ")");
			               System.out.println("�����ɱ������ݲ�ѯ��ҳ��"+s3); 
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
				/**
				 * allbz�ò�ѯ
				 * @param ls
				 * @return
				 */
			    public synchronized ArrayList getAllbz(int aa,int start,String loginName,String loginId,String whereStr) {
			        ArrayList list = new ArrayList();
			        CTime ct = new CTime();

			        
			        DataBase db = new DataBase();
			        ResultSet rs = null;
//			      StringBuffer strall = new StringBuffer();
//			      strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
			        //strall.append(cxtj.toString());
			        try {
			            db.connectDb();
			            StringBuffer s2 = new StringBuffer();
			            StringBuffer s3 = new StringBuffer();
			            s2.append("select b.id ID,b.state STATE,(select t.name from indexs t where t.code=b.compname and t.type='gszt') as COMPNAME," +
			            		"b.compname COMPCODE,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                                "(select t.name from indexs t where t.code=b.fptype and t.type='PJLX') as FPTYPE," +
                                "(select t.name from indexs t where t.code=b.jffs and t.type='FKFS') as JFFS," +
                                "b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME,b.rccode RCCODE,b.money MONEY,b.allmoney ALLMONEY," +
                                "b.faxmoney FAXMONEY,b.dianliang DIANLIANG,b.price PRICE,b.isbz ISBZ" +
                                " from bz_solo b,dianbiao d " +
                                " where 1=1 and d.dbbm = b.dbcode and d.ssf='2' "+whereStr+
                                " and b.state!=1  ORDER BY b.starttime ");//2018/3/22 sgn ��ӷ�������ѯ�ɱ��˼�¼������dianbiao d and d.dbbm = b.dbcode and d.ssf='2'
			                    System.out.println("�ɱ������ݲ�ѯ��"+s2);
			                    
			        
			               s3.append("select count(*)  from (" + s2 + ")");
			               System.out.println("�ɱ������ݲ�ѯ��ҳ��"+s3); 
			               ResultSet rs3 = null;
			               rs3=db.queryAll(s3.toString());
			               if(rs3.next()){
			                this.setAllPage((rs3.getInt(1)+9)/aa);
			               }
			              NPageBean nbean = new NPageBean();
			              rs = db.queryAll(nbean.getQueryStr(aa,start, s2.toString()));
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
				//���ҿɱ�����Ϣ
			    public synchronized ArrayList getAllbz(int start,String loginName,String loginId,String whereStr) {
			        ArrayList list = new ArrayList();
			        CTime ct = new CTime();

			        
			        DataBase db = new DataBase();
			        ResultSet rs = null;
//			      StringBuffer strall = new StringBuffer();
//			      strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
			        //strall.append(cxtj.toString());
			        try {
			            db.connectDb();
			            StringBuffer s2 = new StringBuffer();
			            StringBuffer s3 = new StringBuffer();
			            s2.append("select b.id ID,b.state STATE,(select t.name from indexs t where t.code=b.compname and t.type='gszt') as COMPNAME," +
			            		"b.compname COMPCODE,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                                "(select t.name from indexs t where t.code=b.fptype and t.type='PJLX') as FPTYPE," +
                                "(select t.name from indexs t where t.code=b.jffs and t.type='FKFS') as JFFS," +
                                "b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME,b.rccode RCCODE,b.money MONEY,b.allmoney ALLMONEY," +
                                "b.faxmoney FAXMONEY,b.dianliang DIANLIANG,b.price PRICE,b.isbz ISBZ" +
                                " from bz_solo b,dianbiao d " +
                                " where 1=1 and d.dbbm = b.dbcode and d.ssf='2' "+whereStr+
                                " and b.state!=1  ORDER BY b.starttime ");//2018/3/22 sgn ��ӷ�������ѯ�ɱ��˼�¼������dianbiao d and d.dbbm = b.dbcode and d.ssf='2'
			                    System.out.println("�ɱ������ݲ�ѯ��"+s2);
			                    
			        
			               s3.append("select count(*)  from (" + s2 + ")");
			               System.out.println("�ɱ������ݲ�ѯ��ҳ��"+s3); 
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
			  //�����ѱ�����Ϣ
                public synchronized ArrayList getAllcost(int start,String loginName,String loginId,String whereStr,String cthrnumber) {
                    ArrayList list = new ArrayList();
                    CTime ct = new CTime();

                    String s="";
                    DataBase db = new DataBase();
                    ResultSet rs = null;
//                StringBuffer strall = new StringBuffer();
//                strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
                    //strall.append(cxtj.toString());
                    if(cthrnumber!=null&&!cthrnumber.equals("")){
                
                    	s=" (select c.* from ac_solo c,dianbiao d where c.dbcode=d.dbbm and d.bzr='"+cthrnumber+"'  )b ";
                    }else{
                    	
                    	s=" ac_solo b ";
                    }
                    
                    try {
                        db.connectDb();
                        StringBuffer s2 = new StringBuffer();
                        StringBuffer s3 = new StringBuffer();
                        /*s2.append("select b.id ID,b.allcostid ALLCOST,b.shiname SHINAME,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                                "b.fpname as FPNAME,b.fptype FPTYPE,b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME," +
                                "b.rccode RCCODE,b.ywtype YWTYPE,b.rbname RBNAME,b.rbcode RBCODE,b.allmoney ALLMONEY,b.faxmoney FAXMONEY,b.compname COMPNAME," +
                                "a.costtype COSTTYPE,(select t.name from indexs t where t.code=a.jjsx and t.type='jjsx_dict') as COSTTYPENAME," +
                                "a.coststate COSTSTATE,a.costtime COSTTIME,a.costusername COSTUSERNAME,a.costusercode COSTUSERCODE," +
                                "a.costnum COSTNUM,a.bztype BZTYPE,(select t.name from indexs t where t.code=a.bztype and t.type='bztype_dict') as BZNAME," +
                                "a.appnum APPNUM,a.rsname RSNAME,a.rscode RSCODE,a.paytype PAYTYPE," +
                                "a.bzfs BZFS,a.gangwei GANGWEI,a.bzywsj BZYWSJ,a.jjsx JJSX,a.yongtu YONGTU "
                                + ", zd.shi, ZD.XIAN, zd.xiaoqu" +
                                " from "+s+"   LEFT JOIN allcost a on b.allcostid = a.id "
                                		+ " left JOIN ZHANDIAN ZD ON ZD.zdcode = B.zdcode" +
                                " where 1=1  "+whereStr+
                              //  "  ORDER BY a.costtime desc ");
                    " ");*/
                        s2.append("select b.id ID,b.allcostid ALLCOST,b.shiname SHINAME,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                                "b.fpname as FPNAME,b.fptype FPTYPE,b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME," +
                                "b.rccode RCCODE,b.ywtype YWTYPE,b.rbname RBNAME,b.rbcode RBCODE,b.allmoney ALLMONEY,b.faxmoney FAXMONEY,b.compname COMPNAME," +
                                "a.costtype COSTTYPE,(select t.name from indexs t where t.code=a.jjsx and t.type='jjsx_dict') as COSTTYPENAME," +
                                "a.coststate COSTSTATE,a.costtime COSTTIME,a.costusername COSTUSERNAME,a.costusercode COSTUSERCODE," +
                                "a.costnum COSTNUM,a.bztype BZTYPE,(select t.name from indexs t where t.code=a.bztype and t.type='bztype_dict') as BZNAME," +
                                "a.appnum APPNUM,a.rsname RSNAME,a.rscode RSCODE,a.paytype PAYTYPE," +
                                "a.bzfs BZFS,a.gangwei GANGWEI,a.bzywsj BZYWSJ,a.jjsx JJSX,a.yongtu YONGTU " +
                                " from "+s+"   LEFT JOIN allcost a on b.allcostid = a.id " +
                                " where 1=1  "+whereStr+
                              //  "  ORDER BY a.costtime desc ");
                    " ");
                                System.out.println("�ɱ������ݲ�ѯ��"+s2);
                    
                           s3.append("select count(*)  from (" + s2 + ")");
                           System.out.println("�ɱ������ݲ�ѯ��ҳ��"+s3); 
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
                
                //�����ѱ�����Ϣ  
                public synchronized AcSoloBean getAllcostByIs(String id) {
                    ArrayList list = new ArrayList();
                    AcSoloBean acSoloBean = new AcSoloBean();
                    DataBase db = new DataBase();
                    ResultSet rs = null;
//                StringBuffer strall = new StringBuffer();
//                strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
                    //strall.append(cxtj.toString());
                    try {
                        db.connectDb();
                        StringBuffer s2 = new StringBuffer();
                        s2.append("select b.id ID,b.allcostid ALLCOST,b.shiname SHINAME,b.dbname DBNAME,b.dbcode DBCODE,b.zdname ZDNAME,b.zdcode ZDCODE," +
                                "b.fpname as FPNAME,b.fptype FPTYPE,b.starttime STARTTIME,b.endtime ENDTIME,b.rcname RCNAME," +
                                "b.rccode RCCODE,b.ywtype YWTYPE,b.rbname RBNAME,b.rbcode RBCODE,b.allmoney ALLMONEY,b.faxmoney FAXMONEY,b.compname COMPNAME," +
                                "a.costtype COSTTYPE,(select t.name from indexs t where t.code=a.jjsx and t.type='jjsx_dict') as COSTTYPENAME," +
                                "a.coststate COSTSTATE,a.costtime COSTTIME,a.costusername COSTUSERNAME,a.costusercode COSTUSERCODE," +
                                "a.costnum COSTNUM,a.bztype BZTYPE,a.appnum APPNUM,a.rsname RSNAME,a.rscode RSCODE,a.paytype PAYTYPE," +
                                "a.bzfs BZFS,a.gangwei GANGWEI,a.bzywsj BZYWSJ,a.jjsx JJSX,a.yongtu YONGTU,b.bzid BZID,a.othersystemmainid OTHERSYSTEMMAINID " +
                                " from ac_solo b LEFT JOIN allcost a on b.allcostid = a.id " +
                                " where 1=1 and b.id="+id+
                                "  ORDER BY b.starttime ");
		                        rs=db.queryAll(s2.toString());
		                        while(rs.next()){
		                        	acSoloBean.setId(rs.getString("ID")==null?"":rs.getString("ID"));
		                        	acSoloBean.setBzid(rs.getString("BZID")==null?"":rs.getString("BZID"));
		                        	acSoloBean.setAllcostid(rs.getString("ALLCOST")==null?"":rs.getString("ALLCOST"));
		                        	acSoloBean.setCoststate(rs.getString("COSTSTATE")==null?"":rs.getString("COSTSTATE"));
		                        	acSoloBean.setCostnum(rs.getString("COSTNUM")==null?"":rs.getString("COSTNUM"));
		                        	acSoloBean.setAppnum(rs.getString("APPNUM")==null?"":rs.getString("APPNUM"));
		                        	acSoloBean.setOthersystemmainid(rs.getString("OTHERSYSTEMMAINID")==null?"":rs.getString("OTHERSYSTEMMAINID"));
		                        }
         		              rs.close();		  
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

                    return acSoloBean;
                }
                //���±���ʱ�޸ı��˵�״̬
                public synchronized int updateAllCost(String allcostid,String tsbz_state,String tsbz_costsum,String tsbz_appsum) {
                    // birthday = birthday.length()>0?birthday:null;
                    int msg = 0;
                    ResultSet rs = null;

                    DataBase db = new DataBase();
                    
                    try {
                    	
                        db.connectDb();
                        rs = null;
                        StringBuffer sql = new StringBuffer();
                        sql.append("update ALLCOST set COSTSTATE='"+tsbz_state+"',COSTNUM='"+tsbz_costsum+"',APPNUM='"+tsbz_appsum+"' where ID="+allcostid);
                        System.out.println("�޸ı��˵�״̬sql:"+sql.toString());
                        
                        db.setAutoCommit(false);
                        db.update(sql.toString());
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
                        msg = 2;
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
                
                
                
                public synchronized int validateElectricfees(String dianbiaoId,String yymm) {
					int msg = 0;
					DataBase db = new DataBase();
					ResultSet rs = null;
					ArrayList list = new ArrayList();
					try {
						db.connectDb();
						StringBuffer s2 = new StringBuffer();
						
						s2.append("select count(1) as COUNT from ELECTRICFEES where DIANBIAOID="+dianbiaoId+" and CREATEDATE='"+yymm+"'");
					
						System.out.println("��֤���sql��"+s2);
						  rs=db.queryAll(s2.toString());
						while (rs.next()) {
							msg =  rs.getInt("COUNT");
			              }
			         //   msg = rs.getString("COUNT").toString();
			            
			            
					}

					catch (DbException de) {
						msg = 0;
						de.printStackTrace();
					} catch (SQLException de) {
						msg = 0;
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

					return msg;
		}
                public synchronized int geteledianbiao(String dianbiaoId) {
                	int msg = 0;
                	DataBase db = new DataBase();
                	ResultSet rs = null;
                	ArrayList list = new ArrayList();
                	try {
                		db.connectDb();
                		StringBuffer s2 = new StringBuffer();
                		
                		s2.append("select count(1) as COUNT from dianbiao d , electricfees  e where d.id = e.dianbiaoid and  e.state != 4 and  d.id="+dianbiaoId);
                		
                		System.out.println("��ѯ�˵���Ƿ��д����˵�sql��"+s2);
                		rs=db.queryAll(s2.toString());
                		while (rs.next()) {
                			msg =  rs.getInt("COUNT");
                		}
                		//   msg = rs.getString("COUNT").toString();
                		
                		
                	}
                	
                	catch (DbException de) {
                		msg = 0;
                		de.printStackTrace();
                	} catch (SQLException de) {
                		msg = 0;
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
                	
                	return msg;
                }
      /**
       * ��д������������
       * name: FuXiuLi
       * time: 2018/1/15
       */
                public synchronized int getCostBybzid(String bzid) {
					int msg = 0;
					String cs="";
					DataBase db = new DataBase();
					ResultSet rs = null;
					ArrayList list = new ArrayList();
					try {
						db.connectDb();
						StringBuffer s2 = new StringBuffer();
						
						s2.append("select a.COSTSTATE from allcost a where a.id=(select max(al.id) from ac_solo ac " +
								"inner join allcost al on ac.ALLCOSTID = al.id  " +
								"where ac.BZID in( " +
								"SELECT bz2.id FROM bz_solo bz2 where bz2.ELEID in (" +
								"SELECT bz.ELEID FROM bz_solo bz where bz.id='"+bzid+"' )))");
						System.out.println("�Ƹ����ز�ѯ���"+s2.toString());
						  rs=db.queryAll(s2.toString());
						while (rs.next()) {
							cs =  rs.getString("COSTSTATE");
							if(cs==null || cs.equals("") || !cs.equals("3")){
								msg = 1;
							}
			              }
			         //   msg = rs.getString("COUNT").toString();
			            
			            
					}

					catch (DbException de) {
						msg = 1;
						de.printStackTrace();
					} catch (SQLException de) {
						msg = 1;
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

					return msg;
				}
                public synchronized String getELEIDBybzid(String bzid) {
					int msg = 0;
					String cs="";
					DataBase db = new DataBase();
					ResultSet rs = null;
					ArrayList list = new ArrayList();
					try {
						db.connectDb();
						StringBuffer s2 = new StringBuffer();
						
						s2.append("SELECT bz.ELEID FROM bz_solo bz where bz.id='"+bzid+"'");
						System.out.println("�Ƹ���ѯ������ID"+s2.toString());
						  rs=db.queryAll(s2.toString());
						while (rs.next()) {
							cs =  rs.getString("ELEID");
							
			              }
			         //   msg = rs.getString("COUNT").toString();
			            
			            
					}

					catch (DbException de) {
						cs="";
						de.printStackTrace();
					} catch (SQLException de) {
						cs="";
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

					return cs;
				}
       //��ȡBasicData���ж����У����Է�Ϊ��ҳ,����ģ����ѯ
       public int CountBasicData(String txtKeyword) {
            int a = 0; 		//���干������
            int b = 10;		//����ÿҳ����
            int c = 0;		//���干�м�ҳ
            String sql = "SELECT  COUNT(0)  FROM BASICDATA  WHERE NAME like '%" + txtKeyword + "%'";
            System.out.println(sql);
            DataBase db = new DataBase();
            ResultSet rs = null;
            try {
            	db.connectDb();
            	NPageBean nbean = new NPageBean();
            	rs = db.queryAll(sql);
            	if(rs.next()){
            		a = rs.getInt(1);
            	}
            } catch (Exception de) {
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
            //�����Ƿ�����
            int x = a % b;

            if(x != 0){
            	c = a / b + 1;
            }else{
           		c = a / b;
            }
           	//���ع��м�ҳ
           	return c; 
           }        
               
       //�������ݹ�����
       public ArrayList<BasicDataBean> ListBasicData(int curpage,String txtKeyword) {
            ArrayList<BasicDataBean> al = null;
    		//����Ӧ��ת������
    		int x = curpage * 10;		
    		int y = curpage * 10 - 9;
            String sql = "SELECT * FROM (SELECT ROWNUM rm,ID,NAME,CODE,DESCRIBE FROM BASICDATA WHERE ROWNUM <= "+x+" AND NAME LIKE '%"+txtKeyword+"%') WHERE rm >= "+y+"";
            DataBase db = new DataBase();
            ResultSet rs = null;
            try {
            	db.connectDb();
            	NPageBean nbean = new NPageBean();
            	rs = db.queryAll(sql);
            	al = new ArrayList<BasicDataBean>();
            	while(rs.next()){
            		BasicDataBean basicdata = new BasicDataBean();
            		basicdata.setId(rs.getInt("ID"));			//ID
            		basicdata.setName(rs.getString("NAME"));	//NAME
            		basicdata.setCode(rs.getString("CODE"));	//CODE
            		basicdata.setDescribe(rs.getString("DESCRIBE"));//DESCRIBE
            		
            		al.add(basicdata);
            	}
            } catch (Exception de) {
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
          	return al;
      }
       //����ID��ѯ�ֵ��һ������
       public ArrayList<BasicDataBean> getBasicData(String ID) {
           ArrayList<BasicDataBean> al = null;
           String sql = "SELECT * FROM BASICDATA WHERE ID = "+ID+"";
           System.out.println(sql.toString());
           DataBase db = new DataBase();
           ResultSet rs = null;
           try {
           	db.connectDb();
           	NPageBean nbean = new NPageBean();
           	rs = db.queryAll(sql);
           	al = new ArrayList<BasicDataBean>();
           	while(rs.next()){
           		BasicDataBean basicdata = new BasicDataBean();
           		basicdata.setId(rs.getInt("ID"));			//ID
           		basicdata.setName(rs.getString("NAME"));	//NAME
           		basicdata.setCode(rs.getString("CODE"));	//CODE
           		basicdata.setDescribe(rs.getString("DESCRIBE"));//DESCRIBE
           		al.add(basicdata);
           	}
           } catch (Exception de) {
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
         	return al;
     }
   	//ִ���޸ķ���
   	public int updeatBasicData(String ID,String NAME,String DESCRIBE) {
   		String sql = "UPDATE BASICDATA SET NAME = '"+NAME+"',DESCRIBE= '"+DESCRIBE+"' WHERE ID = "+ID+"";	
   		System.out.println(sql.toString());
   		DataBase db = new DataBase();
   		int rs = 0;
   		try {
   			db.connectDb();
   			rs = db.update(sql);
   			
   		} catch (Exception de) {
   			de.printStackTrace();
   		} finally {
   			try {
   				db.close();
   			} catch (DbException de) {
   				de.printStackTrace();
   			}
   		}
   		return rs;
   	}

/**
 *���Ԥ���� 
 * @return
 */
	public synchronized int addAdvance(String DBID,String ADVTIME,
			String ONEID,String TWOID,String MONEY,String PRICE,
			String DAYNUM,String ACCOUNTID) {
		int msg = 0;
		ResultSet rs = null;

		DataBase db = new DataBase();
		
		try {
			db.connectDb();
			//2015-2-2
			rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ADVANCE(DBID,ADVTIME, ONEID, TWOID, MONEY, PRICE, DAYNUM, ACCOUNTID");
			sql.append(")");
			sql.append("VALUES ('"+DBID+"','"+ADVTIME+"','"+ONEID+"','"+
					TWOID+"','"+MONEY+"','"+PRICE+"','"+
					DAYNUM+"','"+ACCOUNTID+"')");
			System.out.println("���Ԥ����sql:"+sql.toString());
			
			db.setAutoCommit(false);
			msg = db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
    
		

		} catch (DbException de) {
			try {
				
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
			msg = 0;
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
	
	public static long DateDays(String date1, String date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		long myTime;
		Date aDate2;
		Date aDate;
		long myTime2;
		long days = 0;
		try {
		    aDate = formatter.parse(date1);// �������ڣ�������ǰ����
		    myTime = (aDate.getTime() / 1000);
		
		    // SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		    aDate2 = formatter.parse(date2);// �������ڣ�������ǰ����
		    myTime2 = (aDate2.getTime() / 1000);
		
		    if (myTime > myTime2) {
		        days = (myTime - myTime2) / (1 * 60 * 60 * 24);
		    } else {
		        days = (myTime2 - myTime) / (1 * 60 * 60 * 24);
		    }
		
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return (int)days;
	}
	 /**
     * �����к�ʱ���ȡpue��Ϣ
     * @param shi
     * @param addtime
     * @return
     */
    public synchronized String getAnalysisByShi(String shi,String addtime){
 		ArrayList list = new ArrayList();
 		StringBuffer sql = new StringBuffer();
 		String zhi="";
 		sql.append("select sum((nvl(an.shibaodl,0))/((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)/0.9*an.daynum*24/1000 * ( (nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)) / sum((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu) as PUE from analysis an inner join zhandian z on z.id = an.zdid where  1=1 and  an.daynum is not null and an.daynum!='0' ");
 		sql.append(" and z.shi='"+shi+"'and an.addtime='"+addtime+"' "  );
 		System.out.println("�����к�ʱ���ȡpue��Ϣ��"+sql);
 		DataBase db = new DataBase();
 		ResultSet rs = null;
 		try {
 			db.connectDb();
 			rs = db.queryAll(sql.toString());
 			while (rs.next()) {
 				zhi = rs.getString(1);
 			}
 		} catch (Exception e) {
 			zhi="";
 			e.printStackTrace();
 		}finally{
 			try {
 				db.close();
 			} catch (DbException e) {
 				e.printStackTrace();
 			}
 		}
 		return zhi;
 	}
    /**
     * ��ȡ2017�� �����к�ʱ���ȡpue��Ϣ
     * @param shi
     * @param addtime
     * @return
     */
    public synchronized String getAnalysis_2017_ByShi(String shi,String addtime){
 		ArrayList list = new ArrayList();
 		StringBuffer sql = new StringBuffer();
 		String zhi="";
 		sql.append("select an.addtime"+addtime+" from analysis_2017 an where  1=1 ");
 		sql.append(" and an.SHICODE='"+shi+"' "  );
 		System.out.println("�����к�ʱ���ȡpue��Ϣ��"+sql);
 		DataBase db = new DataBase();
 		ResultSet rs = null;
 		try {
 			db.connectDb();
 			rs = db.queryAll(sql.toString());
 			while (rs.next()) {
 				zhi = rs.getString(1);
 			}
 		} catch (Exception e) {
 			zhi="";
 			e.printStackTrace();
 		}finally{
 			try {
 				db.close();
 			} catch (DbException e) {
 				e.printStackTrace();
 			}
 		}
 		return zhi;
 	}
    /**
     * ��ȡ2017�� ����վ��id��ʱ���ȡ�վ�������Ϣ
     * @param shi
     * @param addtime
     * @return
     */
    public synchronized String getRjdl_2017_ByZdid(String STATION_NO,String addtime){
 		ArrayList list = new ArrayList();
 		StringBuffer sql = new StringBuffer();
 		String zhi="";
 		sql.append("select r.EPDAVG from RING_STATION_EPD_AVG r where  1=1 ");
 		sql.append(" and r.STATION_NO='"+STATION_NO+"' and r.STATISTICS_DATE='"+addtime+"'"  );
 		System.out.println("����վ��id��ʱ���ȡ�վ�������Ϣ��"+sql);
 		DataBase db = new DataBase();
 		ResultSet rs = null;
 		try {
 			db.connectDb();
 			rs = db.queryAll(sql.toString());
 			while (rs.next()) {
 				zhi = rs.getString(1);
 			}
 		} catch (Exception e) {
 			zhi="";
 			e.printStackTrace();
 		}finally{
 			try {
 				db.close();
 			} catch (DbException e) {
 				e.printStackTrace();
 			}
 		}
 		return zhi;
 	}
    /**
     * ���ݵ��id��ȡ���ʮ�����ѱ��˵�pueֵ
     * @param dbid
     * @return
     */
    public synchronized ArrayList getPUEByDbid(String dbid){
		 ArrayList list = new ArrayList();
		
				 String sql = " select * from (";
	         sql = sql+ "select e.PUEZHI,e.CREATEDATE " +
	         		" from ELECTRICFEES e where e.dianbiaoid='"+dbid+"' order by E.CREATEDATE asc";
			
	         sql = sql+ ") where rownum<13";
	        System.out.println("���ݵ��id��ȡ���ʮ�����ѱ��˵�pueֵ:"+sql);
	        DataBase db = new DataBase();
	        ResultSet rs = null;
	        try {
	            db.connectDb();
	            rs = db.queryAll(sql);
	            Query query = new Query();
                list = query.query(rs);
	            
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
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
	        return list;
	    }
    /**
	 * 
	 * @param start
	 * @param whereStr ��ѯ����
	 * @return
	 */
	public synchronized ArrayList getElectric_tieta(int start, String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		DataBase db = new DataBase();
		ResultSet rs = null;
        String fzzdstr="";
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select e.area, e.district, e.jzname, e.power_name, e.ELECTRICITY,e.PRICE, e.PAYMENT_AMOUNT," +
					"e.PAYMENT_DATE,e.OWNER,e.PAPER_TYPE,e.SHARE_PROPORTION,e.SHARE_AMOUNT,e.BILLNO from electricfees_tieta e");
	        s2.append(" order by e.jzcode ");
                    System.out.println("������Ѳ�ѯ"+s2);
                    
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("������Ѳ�ѯ��ҳ"+s3); 
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
    
	/**
	 * ��ȡ����ʧ�ܼ�¼(ʧ�ܡ��˵���ɾ��)
	 * @return
	 */
	public synchronized ArrayList getAcSoloFailure(String ids) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer s1 = new StringBuffer();
			StringBuffer s2 = new StringBuffer();
			s2.append("select a.id from allcost a where a.coststate in ('-1','-2','-3') " +
					"and a.id in(select max(b.allcostid) from ac_solo b group by b.bzid)");
			
			s1.append("select b.id, b.allcostid, b.shiname, b.dbname, b.dbcode, b.zdname, b.zdcode, b.bzid from ac_solo b where b.id in ( "+ids+")");
			s1.append(" and b.allcostid in ("+s2+")");
			
			System.out.println("����ʧ�ܼ�¼��ѯ "+s1);
			rs = db.queryAll(s1.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
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
	//��ȡ
	//�ж��Ƿ���С���㲢�Ҹ�����
	public  String number(String num){
		Boolean strResult = num.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?");    
        if(strResult == true){
        	DecimalFormat format = new DecimalFormat("0.00");
            String a = format.format(new BigDecimal( num));
            return a ;
                }else{
                	 return num ;   
                }    
        }
	
	//���ݵ������ȡ��Ӧ�����ƺ͹�Ӧ�̱���
	
	public ArrayList<GYS> SELECTGYS(String DBBM){  
		
		ArrayList<GYS> al = null;
		
		String sql = "SELECT GYSBM,GYSMC FROM DIANBIAO WHERE DBBM = '"+DBBM+"'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			al = new ArrayList<GYS>();
			while(rs.next()){
				GYS cbr = new GYS();
				cbr.setGysbm(rs.getString("GYSBM")); 	//��Ӧ�̱���
				cbr.setGysmc(rs.getString("GYSMC"));	//��Ӧ������
				
				al.add(cbr);
			}
		} catch (Exception de) {
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
		return al;
    }
	
	//���ݵ������޸Ĺ�Ӧ�����ƺ͹�Ӧ�̱���
	
	public int UPDATEGYS(String DBBM,String GYSBM,String GYSMC) {
		int rs = 0;
		String sql = "UPDATE DIANBIAO SET GYSBM = '"+GYSBM+"' ,GYSMC = '"+GYSMC+"' WHERE DBBM = '"+DBBM+"'";
		DataBase db = new DataBase();
		System.out.println("ͨ�����ͲƸ��޸ĵ��sql��"+sql);
		try {
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
}
