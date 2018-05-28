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
			//局站管理excel导出
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
				request.setAttribute("head", "局站名称,监控中心,地市,区县,乡镇,局站完整编码,业务等级,局站等级");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","局站信息管理.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//报账信息excel导出
			}else if(bz.equals("baozhang")){			
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";							
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT  z.JZNAME AS ZDNAME,d.dbname as DBNAME,(select name from indexs where upper(type) = 'FKFS'" +
						"and code = d.DFZFLX) JFFS,to_char(B.STARTTIME,'yyyy-mm-dd') STARTTIME,to_char(B.ENDTIME,'yyyy-mm-dd') ENDTIME," +
						" to_char(B.ALLMONEY, 'fm9999999990.00') ALLMONEY,to_char(B.DIANLIANG, 'fm9999999990.00') DIANLIANG," +
						" to_char(B.PRICE, 'fm9999999990.0000') PRICE," +
						"decode(B.STATE,1,'待审核',0,'未上报',2,'已通过',3,'未通过',4,'已生成sap凭证编号') as  STATE FROM " +
						"ELECTRICFEES B, DIANBIAO d, ZHANDIAN z where z.id = d.zdid and b.DIANBIAOID = d.ID and d.ssf='2'");
				
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				sql.append(" ORDER BY B.DIANBIAOID, B.STARTTIME");
				System.out.println("导出：jjj"+sql);
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.BaozhangBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "实体名称,电表名称,缴费方式,开始日期,结束日期,报账金额," +
						"电量,单价,状态");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","报账信息.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				
				//铁塔报账
			}else if(bz.equals("tietabaozhang")){			
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";							
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT  z.JZNAME AS ZDNAME,d.dbname as DBNAME,(select name from indexs where upper(type) = 'FKFS'" +
						"and code = d.DFZFLX) JFFS,to_char(B.STARTTIME,'yyyy-mm-dd') STARTTIME,to_char(B.ENDTIME,'yyyy-mm-dd') ENDTIME," +
						" to_char(B.ALLMONEY, 'fm9999999990.00') ALLMONEY,to_char(B.DIANLIANG, 'fm9999999990.00') DIANLIANG," +
						" to_char(B.PRICE, 'fm9999999990.0000') PRICE," +
						"decode(B.STATE,1,'待审核',0,'未上报',2,'已通过',3,'未通过',4,'已生成sap凭证编号') as  STATE FROM " +
						"ELECTRICFEES B, DIANBIAO d, ZHANDIAN z where z.id = d.zdid and b.DIANBIAOID = d.ID and d.ssf='1'");
				
				if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
				sql.append(" ORDER BY B.DIANBIAOID, B.STARTTIME");
				System.out.println("导出：jjj"+sql);
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.BaozhangBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "实体名称,电表名称,缴费方式,开始日期,结束日期,报账金额," +
						"电量,单价,状态");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","铁塔报账信息.xlsx");			
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
				sql.append("select decode(a.coststate,-4,'等待生成报账单中',-3,'报账单发起失败',-2,'退单',2,'生成报账单成功',3,'已生成SAP凭证',-1,'报账单删除') as COSTSTATE," +
						"b.shiname SHINAME,b.dbcode DBCODE,b.dbname DBNAME, b.fpname as FPNAME,b.zdcode ZDCODE,b.zdname ZDNAME," +
						"(select t.name from indexs t where t.code = a.jjsx and t.type = 'jjsx_dict') as COSTTYPENAME, a.costtime COSTTIME," +
						"a.costusername COSTUSERNAME,b.starttime STARTTIME, b.endtime ENDTIME,a.costnum COSTNUM," +
						"     (select t.name from indexs t where t.code = a.bztype and t.type = 'bztype_dict') as BZNAME," +
						" b.ywtype YWTYPE, a.appnum APPNUM" +
						
                        " from "+s+"   LEFT JOIN allcost a on b.allcostid = a.id " +
                        " where 1=1  "+whereStr+
                      //  "  ORDER BY a.costtime desc ");
				         	//-4-等待生成报账单中;-3-报账单发起失败；-1-报账单删除，－2-退单；2-生成报账单成功；3-已生成SAP凭证
						
            " ");					
                System.out.println("可报账数据daochu："+sql);
				request.setAttribute("beanType", "com.noki.jichuInfo.CbDownload.KbzBean");
				request.setAttribute("sql", sql.toString());
				request.setAttribute("head", "状态,分公司名称,费用对象编码,费用对象名称,票据类型,实体编码,实体名称,费用类型,报账期间,报账人,开始日期,结束日期,报账单号,报账类型,业务类型,SAP凭证账号");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","可报账数据管理.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//电表导出
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
						"decode(d.shzt,1,'待审核',0,'未上报','2','已通过') as shzt,(select t.name from indexs t where t.code=d.ydsx and t.type='ydsx')ydsx," +
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
				request.setAttribute("head", "电表编码,电表名称,地市,区县,实体名称,用户编号,责任人,报账人,电表状态,当前审核,"
						+ "用电属性,缴费方式,缴费周期,计费方式,倍率,单价,发票类型,税率,生产占比,成本中心编码,区县分公司编码");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","电表管理.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				
//				private String YDSX;//用电属性：与电表的上报情况是否一致
//				private String DFZFLX;//缴费方式：与电表的上报情况是否一致
//				private String JFZQ;//缴费周期：与电表的上报情况是否一致
//				private String JFFS;//计费方式：与电表的上报情况是否一致
//				private String BEILV;//倍率：与电表的上报情况是否一致
//				private String DANJIA;//单价：与电表的上报情况是否一致(保留4位小数)
//				private String FPLX;//发票类型：与电表的上报情况是否一致
//				private String ZZSL;//税率：与电表的上报情况是否一致(例如增值税专票则为：0.17)
//				private String PRODUCTION_PROP;//生产占比：与电表的上报情况是否一致
				//分摊比例管理导出
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
				request.setAttribute("head", "序号,站点名称,年月,移动,联通,电信,其他");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","分摊比例管理.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//税负因子导入
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
				request.setAttribute("head", "序号,站点名称,电表编码,年月,税负因子");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","税负因子管理.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//站点电表查询excel导入
			}else if(bz.equals("siteDB")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,d.dbbm,d.dbname,case d.dbyt when 'dbyt01' then '结算' when 'dbyt02' then");
				sql.append(" '采集' else '管理'end dbyt,d.beilv,Rtusername(d.bzr) A from zhandian z, dianbiao d where z.id = d.zdid");
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
				request.setAttribute("head", "序号,站点名称,电表编码,电表名称,电表用途,倍率,报账人");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","站点电表查询.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
			//电价查询excel
			}else if(bz.equals("danjia")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,d.yearmonth,d.dbbm,db.dbname,case db.dbyt when 'dbyt01' then");
				sql.append(" '结算'when 'dbyt02' then'采集' else '管理'end dianyt, d.danjia from dianjia d, dianbiao db,zhandian z where d.zdid = z.id and db.zdid = z.id");
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
				request.setAttribute("head", "序号,站点名称,适用年月,电表编码,电表名称,电表用途,单价");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","电价查询.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//电价查询excel2
			}else if(bz.equals("danjiaquery")){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String keyword = request.getParameter("yearmonth")!=null?request.getParameter("yearmonth"):"0";
				StringBuffer sql = new StringBuffer();
				sql.append("select rownum,s.* from (select z.jzname,d.yearmonth,d.dbbm,db.dbname,case db.dbyt when 'dbyt01' then");
				sql.append(" '结算'when 'dbyt02' then'采集' else '管理'end dianyt, d.danjia from dianjia d, dianbiao db,zhandian z where d.zdid = z.id and db.zdid = z.id");
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
				request.setAttribute("head", "序号,站点名称,适用年月,电表编码,电表名称,电表用途,单价");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","电价查询.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//抄表计划excel
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
				request.setAttribute("head", "序号,用户账号,姓名,分管基站,抄表计划日期");
				request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","抄表计划.xlsx");			
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
			}//办公营业厅导出
			else if(bz.equals("yingyeting")){
				System.out.println("办公营业厅导出");
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
        				" decode(kongtiao,1,'是',0,'否')as kongtiao,(select name from indexs where code = z.stationtype) stationtype,zdcode," +
        				"(select name from indexs where type='GDFS' and code=z.power_id) power_id,")
        		.append("decode( is_sharing_rent,1,'是',0,'否') as is_sharing_rent,ascription_unit,")
        		.append("jzname, decode(approve_status,1,'已通过',0,'审核中','2','未上报') as approve_status from zhandian z where xiaoqu in (select t.agcode from per_area t where t.accountid='"+account.getAccountId()+"')");
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
	            request.setAttribute("head", "地市,区县,乡镇,实体名称,是否有空调,实体类型,实体编码,供电方式,是否共享外租,所属经营单位,状态");request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","办公营业厅管理.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
				}//大工业用电导出
			else if(bz.equals("DGYYD")){
				//城市默认值
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
				StringBuffer sql=new StringBuffer();
				//查询语句
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
	            request.setAttribute("head", "城市,区县,乡镇,变压器容量,功率因素,一般工商及其他电价,大工业峰谷平综合电价,价差,最大需量40%基本电价,平衡点电量,平衡点电量折算容量,占变压器比（%）");request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","大工业用电量.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
				//抄表预警
			}
			/**
			 * 18个查询导出
			 */
			else if(bz.equals("bqydfdlfx")){ //本区域电费、电量分析（bqydfdlfx）
				//获取导出条件
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
	            request.setAttribute("head", "局站名称,地市,区县,乡镇,是否监控（1为已经监控，0为未监控）,房屋类型,日均用电量,月度/季度/年度,总缴纳金额,总缴费电量,电量偏离度,平均负载总电流,负责人,备注");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","本区域电费、电量分析.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bqyjzpuemxb")){//本区域局站PUE明细表（bqyjzpuemxb）
				//获取导出条件
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
	            request.setAttribute("head", "局站名称,地市,区县,乡镇,是否监控（1为已经监控，0为未监控）,房屋类型,日均用电量,月度,实际电量,额定电量,平均负载总电流,PUE值,负责人,备注");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","本区域局站PUE明细表.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bqyyczdtj")){//本区域异常站点统计（bqyyczdtj）
				//获取导出条件
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
	            request.setAttribute("head", "局站名称,地市,区县,乡镇,是否监控（1为已经监控，0为未监控）,房屋类型,日均用电量,实际金额,实际电量,额定电量,异常偏离度,平均负载总电流,负责人,备注");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","本区域异常站点统计.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
				
			}else if(bz.equals("bqyyswcjd")){//本区域预算完成进度（bqyyswcjd）
				//获取导出条件
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
	            request.setAttribute("head", "局站名称,地市,区县,乡镇,是否监控（1为已经监控，0为未监控）,房屋类型,日均用电量,实际金额,实际电量,额定电量,预算金额,完成进度（%）,负责人,备注");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","本区域预算完成进度.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("zhdjbb")){//2-1综合单价报表（zhdjbb）
				//获取导出条件
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
	            request.setAttribute("head", "日期,地市,区县,乡镇,局站名称,电表名称,综合电价（总电费/总电量）");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-1综合单价报表.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("dfcypldbg")){//2-2电费差异偏离度报告（dfcypldbg）
				//获取导出条件
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
	            request.setAttribute("head", "时间段,地市,区县,乡镇,局站名称,电表名称,实际电费,定额电费,差异偏离度（实际电费-定额电费）/定额电费");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-2电费差异偏离度报告.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("idcjljqtlxdfbb")){//2-3IDC机楼及其它类型电费报表（idcjljqtlxdfbb）
				//获取导出条件
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
	            request.setAttribute("head", "时间段,地市,区县,乡镇,局站名称,电表名称,用电量,电费,单价,局站类型");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-3IDC机楼及其它类型电费报表.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bdsyswcjdbb")){//2-4本地市预算完成进度报表（bdsyswcjdbb）
				//获取导出条件
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,ALLMONEY FROM VIEW_BDSYSWCJDBB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bdsyswcjdbb_4");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "时间段,日期,地市,预算电费金额,报账电费费用,预算完成进行");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-4本地市预算完成进度报表.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("bsdpjpuebb")){//2-5本地市平均PUE报表（bsdpjpuebb）
				//获取导出条件
				StringBuffer sql =new StringBuffer();
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
				sql.append("SELECT COSTTIME,D1.AGNAME SHI,JZNAME,DIANLIANG,BGDL,PUEZHI FROM VIEW_BSDPJPUEBB V LEFT JOIN D_AREA_GRADE D1 ON V.SHI = D1.AGCODE WHERE 1=1");
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql.append(" AND V.SHI='"+shi+"'");
				}
				request.setAttribute("beanType", "com.noki.baobiaohuizong.beans.Bsdpjpuebb_5");
				request.setAttribute("sql", sql.toString());
	            request.setAttribute("head", "年月,地市,地市,缴费站点,缴费度数,定额电量,PUE值");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-5本地市平均PUE报表.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("zdshl")){//2-6自动审核率（zdshl）
				//获取导出条件
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
	            request.setAttribute("head", "年月,地市,区县,乡镇,缴费站点,自动审核数量,自动审核通过率");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-6自动审核率.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("ydxzzdslcx")){//2-7月度新增站点数量查询（ydxzzdslcx）
				//获取导出条件
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
	            request.setAttribute("head", "年月,地市,区县,乡镇,新增局站名称,局站等级,局站类型,产权,经度,纬度");
	            request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
				request.setAttribute("fileName","2-7月度新增站点数量查询.xlsx");			
				request.getRequestDispatcher
				("ExcelDownload").forward(request, response);
			}else if(bz.equals("ydxzzdslcx")){//错误信息返回	
				request.getRequestDispatcher("ExcelDownload").forward(request, response);
			}
		}
	}