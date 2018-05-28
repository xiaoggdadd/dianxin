package com.noki.jichuInfo.CbDownload;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.noki.zdymess.dao.session;
import com.ptac.app.station.bean.StationQuery;

public class CbDownload extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String bz = request.getParameter("bz");
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
			//��վ����excel����
			if(bz.equals("site")){
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";	
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				//StringBuffer s3 = new StringBuffer();
				sql.append("select z.JZNAME, z.LSCID,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.SHI ) as SHI,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAN) as XIAN,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=z.XIAOQU) as XIAOQU,z.FULL_STATION_CODE,(select t.name from indexs t where t.code=z.SERVICE_ID and t.type='SERVICE_ID_DICT') as SERVICE_ID,(select t.name from indexs t where t.code=z.RANK_ID and t.type='RANK_ID_DICT') as RANK_ID from zhandian z  where 1=1");										
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")) {
					sql.append(" and z.jzname like '%" + keyword + "%'");
				}
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				sql.append("ORDER BY z.JZNAME");
				// s3.append("select count(*)  from (" + sql + ")");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.SiteBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "��վ����,�������,����,����,����,��վ��������,ҵ��ȼ�,��վ�ȼ�");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","��վ��Ϣ����.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//������Ϣexcel����
			}else if(bz.equals("baozhang")){			
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";							
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT  z.JZNAME AS ZDNAME,d.dbname as DBNAME,(select name from indexs where upper(type) = 'FKFS'" +
						"and code = d.DFZFLX) JFFS,to_char(B.STARTTIME,'yyyy-mm-dd') STARTTIME,to_char(B.ENDTIME,'yyyy-mm-dd') ENDTIME," +
						" to_char(B.ALLMONEY, 'fm9999999990.00') ALLMONEY,to_char(B.DIANLIANG, 'fm9999999990.00') DIANLIANG," +
						" to_char(B.PRICE, 'fm9999999990.0000') PRICE," +
						"decode(B.STATE,1,'�����',0,'δ�ϱ�',2,'��ͨ��',3,'δͨ��',4,'������sapƾ֤���') as  STATE FROM " +
						"ELECTRICFEES B, DIANBIAO d, ZHANDIAN z where z.id = d.zdid and b.DIANBIAOID = d.ID and d.ssf='2'");
				
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				sql.append(" ORDER BY B.DIANBIAOID, B.STARTTIME");
				System.out.println("������jjj"+sql);
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.BaozhangBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "ʵ������,�������,�ɷѷ�ʽ,��ʼ����,��������,���˽��," +
						"����,����,״̬");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","������Ϣ.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				
				//��������
			}else if(bz.equals("tietabaozhang")){			
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";							
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT  z.JZNAME AS ZDNAME,d.dbname as DBNAME,(select name from indexs where upper(type) = 'FKFS'" +
						"and code = d.DFZFLX) JFFS,to_char(B.STARTTIME,'yyyy-mm-dd') STARTTIME,to_char(B.ENDTIME,'yyyy-mm-dd') ENDTIME," +
						" to_char(B.ALLMONEY, 'fm9999999990.00') ALLMONEY,to_char(B.DIANLIANG, 'fm9999999990.00') DIANLIANG," +
						" to_char(B.PRICE, 'fm9999999990.0000') PRICE," +
						"decode(B.STATE,1,'�����',0,'δ�ϱ�',2,'��ͨ��',3,'δͨ��',4,'������sapƾ֤���') as  STATE FROM " +
						"ELECTRICFEES B, DIANBIAO d, ZHANDIAN z where z.id = d.zdid and b.DIANBIAOID = d.ID and d.ssf='1'");
				
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				sql.append(" ORDER BY B.DIANBIAOID, B.STARTTIME");
				System.out.println("������jjj"+sql);
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.BaozhangBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "ʵ������,�������,�ɷѷ�ʽ,��ʼ����,��������,���˽��," +
						"����,����,״̬");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","����������Ϣ.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				
			}else if(bz.equals("allcost")){				
				String loginId=account.getAccountId();
				  String s="";
				  String cthrnumber=account.getCthrnumber();
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
				
				if(cthrnumber!=null&&!cthrnumber.equals("")){
	                
                	s=" (select c.* from ac_solo c,dianbiao d where c.dbcode=d.dbbm and d.bzr='"+cthrnumber+"'  )b ";
                }else{
                	
                	s=" ac_solo b ";
                }               
				StringBuffer sql = new StringBuffer();
				sql.append("select decode(a.coststate,-4,'�ȴ����ɱ��˵���',-3,'���˵�����ʧ��',-2,'�˵�',2,'���ɱ��˵��ɹ�',3,'������SAPƾ֤',-1,'���˵�ɾ��') as COSTSTATE," +
						"b.shiname SHINAME,b.dbcode DBCODE,b.dbname DBNAME, b.fpname as FPNAME,b.zdcode ZDCODE,b.zdname ZDNAME," +
						"(select t.name from indexs t where t.code = a.jjsx and t.type = 'jjsx_dict') as COSTTYPENAME, a.costtime COSTTIME," +
						"a.costusername COSTUSERNAME,b.starttime STARTTIME, b.endtime ENDTIME,a.costnum COSTNUM," +
						"     (select t.name from indexs t where t.code = a.bztype and t.type = 'bztype_dict') as BZNAME," +
						" b.ywtype YWTYPE, a.appnum APPNUM" +
						
                        " from "+s+"   LEFT JOIN allcost a on b.allcostid = a.id " +
                        " where 1=1  "+whereStr+
                      //  "  ORDER BY a.costtime desc ");
				         	//-4-�ȴ����ɱ��˵���;-3-���˵�����ʧ�ܣ�-1-���˵�ɾ������2-�˵���2-���ɱ��˵��ɹ���3-������SAPƾ֤
						
            " ");					
                System.out.println("�ɱ�������daochu��"+sql);
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.KbzBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "״̬,�ֹ�˾����,���ö������,���ö�������,Ʊ������,ʵ�����,ʵ������,��������,�����ڼ�,������,��ʼ����,��������,���˵���,��������,ҵ������,SAPƾ֤�˺�");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","�ɱ������ݹ���.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//�����
			}
			else if(bz.equals("dianbiao")){
				
				String loginId=account.getAccountId();
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				String txtDianbiao = request.getParameter("txtDianbiao")!=null?request.getParameter("txtDianbiao"):"0";

				StringBuffer sql = new StringBuffer();
				sql.append("select d.dbbm,d.dbname,(select ag.agname from d_area_grade ag where ag.agcode = z.shi) as shi," +
						"(select ag.agname from d_area_grade ag where ag.agcode = z.xian) as xian,z.jzname, d.yhbh," +
						" d.zrr, d.bzr, (select t.name from indexs t where t.code = d.dbqyzt and t.type = 'dbzt') as dbzt," +
						"decode(d.shzt,1,'�����',0,'δ�ϱ�','2','��ͨ��') as shzt,(select t.name from indexs t where t.code=d.ydsx and t.type='ydsx')ydsx," +
						"(select t.name from indexs t where t.code=d.dfzflx and t.type='FKFS')dfzflx,(select t.name from indexs t where t.code=d.jfzq and t.type='FKZQ')jfzq," +
						"(select t.name from indexs t where t.code=d.jffs and t.type='JFFS')jffs," +
						"d.beilv,d.danjia,(select t.name from indexs t where t.code=d.fplx and t.type='PJLX')fplx," +
						"d.zzsl,d.PRODUCTION_PROP,d.cbzxbm,d.qxfgs from dianbiao d, zhandian z where d.zdid = z.id ");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")) {
					sql.append(" and z.jzname like '%" + keyword + "%'");
				}
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				if (txtDianbiao != "") {
					sql.append(" and d.DBNAME like '%" + txtDianbiao + "%'");
					
				}
				sql.append(" and ((z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"
							+loginId
							+ "')))");
				
				sql.append("order by z.sheng, z.shi, z.xian, xiaoqu, z.jzname");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.dianbiaoBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "������,�������,����,����,ʵ������,�û����,������,������,���״̬,��ǰ���,"
						+ "�õ�����,�ɷѷ�ʽ,�ɷ�����,�Ʒѷ�ʽ,����,����,��Ʊ����,˰��,����ռ��,�ɱ����ı���,���طֹ�˾����");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","������.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				
//				private String YDSX;//�õ����ԣ�������ϱ�����Ƿ�һ��
//				private String DFZFLX;//�ɷѷ�ʽ��������ϱ�����Ƿ�һ��
//				private String JFZQ;//�ɷ����ڣ�������ϱ�����Ƿ�һ��
//				private String JFFS;//�Ʒѷ�ʽ��������ϱ�����Ƿ�һ��
//				private String BEILV;//���ʣ�������ϱ�����Ƿ�һ��
//				private String DANJIA;//���ۣ�������ϱ�����Ƿ�һ��(����4λС��)
//				private String FPLX;//��Ʊ���ͣ�������ϱ�����Ƿ�һ��
//				private String ZZSL;//˰�ʣ�������ϱ�����Ƿ�һ��(������ֵ˰רƱ��Ϊ��0.17)
//				private String PRODUCTION_PROP;//����ռ�ȣ�������ϱ�����Ƿ�һ��
				//��̯����������
			}else if(bz.equals("ftbl")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname, f.yearmonth, f.yidong, f.liantong, f.dianxin, f.qita");
				sql.append(" from ftbl f, zhandian z  where f.zdid = z.id");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")) {
					sql.append(" and z.jzname like '%" + keyword + "%'");
				}
				sql.append(") s ");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.FtblBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "���,վ������,����,�ƶ�,��ͨ,����,����");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","��̯��������.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//˰�����ӵ���
			}else if(bz.equals("sfyz")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,s.dbbm,s.yearmonth,s.sfyz from SFYZ s,zhandian z where s.zdid=z.id ");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")) {
					sql.append(" and z.jzname like '%" + keyword + "%'");
				}
				sql.append(") s ");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.SfyzBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "���,վ������,������,����,˰������");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","˰�����ӹ���.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//վ�����ѯexcel����
			}else if(bz.equals("siteDB")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,d.dbbm,d.dbname,case d.dbyt when 'dbyt01' then '����' when 'dbyt02' then");
				sql.append(" '�ɼ�' else '����'end dbyt,d.beilv,Rtusername(d.bzr) A from zhandian z, dianbiao d where z.id = d.zdid");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")) {
					sql.append(" and z.jzname like '%" + keyword + "%'");
				}
				sql.append(") s ");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.SiteDbBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "���,վ������,������,�������,�����;,����,������");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","վ�����ѯ.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
			//��۲�ѯexcel
			}else if(bz.equals("danjia")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,d.yearmonth,d.dbbm,db.dbname,case db.dbyt when 'dbyt01' then");
				sql.append(" '����'when 'dbyt02' then'�ɼ�' else '����'end dianyt, d.danjia from dianjia d, dianbiao db,zhandian z where d.zdid = z.id and db.zdid = z.id");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")) {
					sql.append(" and z.jzname like '%" + keyword + "%'");
				}
				sql.append(") s ");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.DianjiaBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "���,վ������,��������,������,�������,�����;,����");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","��۲�ѯ.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//��۲�ѯexcel2
			}else if(bz.equals("danjiaquery")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("yearmonth")!=null?request.getParameter("yearmonth"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,d.yearmonth,d.dbbm,db.dbname,case db.dbyt when 'dbyt01' then");
				sql.append(" '����'when 'dbyt02' then'�ɼ�' else '����'end dianyt, d.danjia from dianjia d, dianbiao db,zhandian z where d.zdid = z.id and db.zdid = z.id");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")&&keyword!=null&&keyword!="") {
					sql.append(" and d.yearmonth='"+keyword+"'");
				}
				sql.append(") s ");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.DianjiaBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "���,վ������,��������,������,�������,�����;,����");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","��۲�ѯ.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//����ƻ�excel
			}else if(bz.equals("cbjh")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum, r.* from (select a.accountname,a.name, z.jzname,to_char(c.cbjhrq, 'yyyy-mm-dd') as cbjhrq");
				sql.append(" from cbjh c, account a, zhandian z where c.userid = a.accountid and c.zdid = z.id");
				if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!keyword.equals("0")&&keyword!=null&&keyword!="") {
					sql.append(" and z.jzname like '%"+keyword+"%'");
				}
				sql.append(") r ");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.CbjhBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "���,�û��˺�,����,�ֹܻ�վ,����ƻ�����");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","����ƻ�.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
			}//�칫Ӫҵ������
			else if(bz.equals("yingyeting")){
				System.out.println("�칫Ӫҵ������");
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
                String stationName= request.getParameter("stationName")!=null?request.getParameter("stationName"):"0";
                String stationType = request.getParameter("stationType")!=null?request.getParameter("stationType"):"";
                String ascriptionUnit = request.getParameter("ascriptionUnit")!=null?request.getParameter("ascriptionUnit"):"";
                String power = request.getParameter("power")!=null?request.getParameter("power"):"";
                String approveStatus = request.getParameter("approveStatus")!=null?request.getParameter("approveStatus"):"";

                  
                StringBuffer sql = new StringBuffer();
        		sql.append("select(select agname from d_area_grade where agcode = z.shi ) shi," +
        				"(select agname from d_area_grade where agcode = z.xian ) xian," +
        				"(select agname from d_area_grade where agcode = z.xiaoqu ) xiaoqu," +
        				" decode(kongtiao,1,'��',0,'��')as kongtiao,(select name from indexs where code = z.stationtype) stationtype,zdcode," +
        				"(select name from indexs where type='GDFS' and code=z.power_id) power_id,")
        		.append("decode( is_sharing_rent,1,'��',0,'��') as is_sharing_rent,ascription_unit,")
        		.append("jzname, decode(approve_status,1,'��ͨ��',0,'�����','2','δ�ϱ�') as approve_status from zhandian z where xiaoqu in (select t.agcode from per_area t where t.accountid='"+account.getAccountId()+"')");
			    sql.append(" and z.collect_mode = '1'");			   
        		if (!xiaoqu.equals("0")) {
					sql.append(" and z.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and z.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and z.shi='"+shi+"'");
				    }
				if (!stationName.equals("0")) {
					sql.append(" and z.jzname like '%" + stationName + "%'");
				}
				if(stationType!=null && !stationType.equals("")){
					sql.append(" and stationtype = '").append(stationType).append("'");
				}
				if (!ascriptionUnit.equals("0")) {
					sql.append(" and ascription_unit like '%" + ascriptionUnit + "%'");
				}
				if (!power.equals("")) {
					sql.append(" and ( select name from indexs where type = 'GDFS' and code = z.power_id) = '").append(power).append("'");					
				}
				if(approveStatus!=null && !approveStatus.isEmpty() && !"".equals(approveStatus)){
					sql.append(" and approve_status = '"+xiaoqu+"'");
				}
        		   sql.append("order by id desc");
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.ShiTiDCBean");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "����,����,����,ʵ������,�Ƿ��пյ�,ʵ������,ʵ�����,���緽ʽ,�Ƿ�������,������Ӫ��λ,״̬");request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","�칫Ӫҵ������.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
				}//��ҵ�õ絼��
			else if(bz.equals("DGYYD")){
				//����Ĭ��ֵ
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
				StringBuffer sql=new StringBuffer();
				//��ѯ���
				sql.append("select(select ag.agname from d_area_grade ag where ag.agcode =d.shi) as shi," +
						"(select  ag.agname from d_area_grade ag where ag.agcode =d.xian) as xian,(select  ag.agname from d_area_grade ag where ag.agcode =d.xiaoqu) as xiaoqu,d.byqrl,to_char(d.glys,'fm9999999990.00') glys," +
						"to_char(d.dj,'fm9999999990.0000') dj,to_char(d.zhdj,'fm9999999990.0000') zhdj,to_char(d.jc,'fm9999999990.0000') jc,to_char(d.zddj,'fm9999999990.00') zddj,d.phddl,d.rl,to_char(d.zb,'fm9999999990.00') zb from dgyydgl d where 1=1"); 
				if (!xiaoqu.equals("0")) {
					sql.append(" and d.xiaoqu='"+xiaoqu+"'");
				    } else if (!xian.equals("0")) {
					sql.append(" and d.xian='"+xian+"'");
				    } else if (!shi.equals("0")) {
					sql.append(" and d.shi='"+shi+"'");
				    }
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				request.setAttribute("beanType", "com.noki.dgyydgl.javabean.DgyydDaocBean");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "����,����,����,��ѹ������,��������,һ�㹤�̼��������,��ҵ���ƽ�ۺϵ��,�۲�,�������40%�������,ƽ������,ƽ��������������,ռ��ѹ���ȣ�%��");request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","��ҵ�õ���.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
				//����Ԥ��
			}
			/**
			 * 18����ѯ����
			 */
			else if(bz.equals("bqydfdlfx")){ //�������ѡ�����������bqydfdlfx��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT JZNAME,D1.AGNAME SHI,D2.AGNAME XIAN, D3.AGNAME XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,COSTTIME,ALLMONEY,DIANLIANG,DLPLS,PJFZZDL,A.NAME BZR,MEMO FROM VIEW_BQYDFDLFX V LEFT JOIN D_AREA_GRADE D1 ON V.SHI= D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE LEFT JOIN ACCOUNT A ON V.BZR = A.CTHRNUMBER WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bqydfdlfx");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "��վ����,����,����,����,�Ƿ��أ�1Ϊ�Ѿ���أ�0Ϊδ��أ�,��������,�վ��õ���,�¶�/����/���,�ܽ��ɽ��,�ܽɷѵ���,����ƫ���,ƽ�������ܵ���,������,��ע");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","�������ѡ���������.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bqyjzpuemxb")){//�������վPUE��ϸ��bqyjzpuemxb��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT JZNAME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,COSTTIME,YUEDU,EDHDL,PJFZZDL,PUEZHI,A.NAME BZR,MEMO FROM VIEW_BQYJZPUEMXB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE LEFT JOIN ACCOUNT A ON V.BZR = A.CTHRNUMBER WHERE 1 = 1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bqyjzpuemxb");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "��վ����,����,����,����,�Ƿ��أ�1Ϊ�Ѿ���أ�0Ϊδ��أ�,��������,�վ��õ���,�¶�,ʵ�ʵ���,�����,ƽ�������ܵ���,PUEֵ,������,��ע");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","�������վPUE��ϸ��.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bqyyczdtj")){//�������쳣վ��ͳ�ƣ�bqyyczdtj��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT JZNAME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,ALLMONEY,SJDIANLIANG,EDHDL,YCPLD,PJFZZDL,A.NAME BZR,MEMO FROM VIEW_BQYYCZDTJ V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE LEFT JOIN ACCOUNT A ON V.BZR = A.CTHRNUMBER WHERE 1 = 1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bqyyczdtj");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "��վ����,����,����,����,�Ƿ��أ�1Ϊ�Ѿ���أ�0Ϊδ��أ�,��������,�վ��õ���,ʵ�ʽ��,ʵ�ʵ���,�����,�쳣ƫ���,ƽ�������ܵ���,������,��ע");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","�������쳣վ��ͳ��.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
				
			}else if(bz.equals("bqyyswcjd")){//������Ԥ����ɽ��ȣ�bqyyswcjd��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT JZNAME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,ALLMONEY,SJDIANLIANG,EDHDL,A.NAME BZR,MEMO FROM VIEW_BQYYSWCJD V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE LEFT JOIN ACCOUNT A ON V.BZR = A.CTHRNUMBER WHERE 1 = 1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bqyyswcjd");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "��վ����,����,����,����,�Ƿ��أ�1Ϊ�Ѿ���أ�0Ϊδ��أ�,��������,�վ��õ���,ʵ�ʽ��,ʵ�ʵ���,�����,Ԥ����,��ɽ��ȣ�%��,������,��ע");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","������Ԥ����ɽ���.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("zhdjbb")){//2-1�ۺϵ��۱���zhdjbb��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,JZNAME,DBNAME,ZHDANJIA  FROM VIEW_ZHDJBB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Zhdjbb_1");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "����,����,����,����,��վ����,�������,�ۺϵ�ۣ��ܵ��/�ܵ�����");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-1�ۺϵ��۱���.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("dfcypldbg")){//2-2��Ѳ���ƫ��ȱ��棨dfcypldbg��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,JZNAME,DBNAME,ALLMONEY,DEDF,CYPLD FROM VIEW_DFCYPLDBG V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Dfcypldbg_2");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "ʱ���,����,����,����,��վ����,�������,ʵ�ʵ��,������,����ƫ��ȣ�ʵ�ʵ��-�����ѣ�/������");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-2��Ѳ���ƫ��ȱ���.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("idcjljqtlxdfbb")){//2-3IDC��¥���������͵�ѱ���idcjljqtlxdfbb��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,JZNAME,DBNAME,DIANLIANG,ALLMONEY,PRICE,JZTYPE FROM VIEW_IDCJLJQTLXDFBB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Idcjljqtlxdfbb_3");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "ʱ���,����,����,����,��վ����,�������,�õ���,���,����,��վ����");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-3IDC��¥���������͵�ѱ���.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bdsyswcjdbb")){//2-4������Ԥ����ɽ��ȱ���bdsyswcjdbb��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,ALLMONEY FROM VIEW_BDSYSWCJDBB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bdsyswcjdbb_4");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "ʱ���,����,����,Ԥ���ѽ��,���˵�ѷ���,Ԥ����ɽ���");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-4������Ԥ����ɽ��ȱ���.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bsdpjpuebb")){//2-5������ƽ��PUE����bsdpjpuebb��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,JZNAME,DIANLIANG,BGDL,PUEZHI FROM VIEW_BSDPJPUEBB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bsdpjpuebb_5");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "����,����,����,�ɷ�վ��,�ɷѶ���,�������,PUEֵ");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-5������ƽ��PUE����.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("zdshl")){//2-6�Զ�����ʣ�zdshl��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT CREATEDATE,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,JZNAME,ZDSHS,ZDSHL FROM VIEW_ZDSHL V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Zdshl_6");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "����,����,����,����,�ɷ�վ��,�Զ��������,�Զ����ͨ����");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-6�Զ������.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("ydxzzdslcx")){//2-7�¶�����վ��������ѯ��ydxzzdslcx��
				//��ȡ��������
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				sql.append("SELECT CJTIME,D1.AGNAME SHI,D2.AGNAME XIAN,D3.AGNAME XIAOQU,JZNAME, JFDJ, STATIONTYPE, ZDCQ, LONGITUDE, LATITUDE FROM VIEW_YDXZZDSLCX V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE LEFT JOIN D_AREA_GRADE D2 ON V.XIAN = D2.AGCODE LEFT JOIN D_AREA_GRADE D3 ON V.XIAOQU = D3.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql.append(" AND V.XIAN='"+xian+"'");
				}
				if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
					sql.append(" AND V.XIAOQU='"+xiaoqu+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Ydxzzdslcx_7");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "����,����,����,����,������վ����,��վ�ȼ�,��վ����,��Ȩ,����,γ��");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-7�¶�����վ��������ѯ.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("ydxzzdslcx")){//������Ϣ����	
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
			}
		}
	}