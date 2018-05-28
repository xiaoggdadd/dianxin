package com.ptac.app.basisquery.electricdetail.dao;
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
/**
 * @see 电费缴纳明细dao层实现类
 * @author ZengJin
 */
public class ElecDetailDaoImp implements ElecDetailDao {
	/**
	 * @param whereStr (String) sql语句后半段
	 * @param loginId (String) 权限过滤条件 
	 * @param bzw (String) 权限过滤条件 
	 * @return (ElectricDetail)
	 * @see 电费缴纳明细查询和导出
	 * @author ZengJin 2014-1-14
	 * @update lijing
	 */
	
	Connection conn = null;
	Statement sta = null;
	public List<ElectricDetail> queryElectric(String whereStr,String loginId,String bzw ) {
		List<ElectricDetail> list = new ArrayList<ElectricDetail>();//声明结果集
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			//------(根据权限自动选择,无限制用第1条,有限制用第2条)------
		
				sql.append(" SELECT rndiqu(ZD.XIAN) XIAN, rndiqu(ZD.XIAOQU) XIAOQU, ZD.JZNAME, D.DBNAME, RTNAME(D.DFZFLX) AS DFZFLX, TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH," 
						 +" TO_CHAR(E.KJYF,'yyyy-mm') KJYF, RTNAME(ZD.GDFS) AS GDFS, A.LASTDEGREE, A.THISDEGREE,TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME, TO_CHAR(A.THISDATETIME,'yyyy-mm-dd') THISDATETIME, A.BLHDL, A.FLOATDEGREE,"
						 +" A.MEMO, E.EDHDL, E.UNITPRICE,E.FLOATPAY, E.MEMO, E.ACTUALPAY, ZD.ZLFH, ZD.JLFH, TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,"
						 +" (SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME = E.ENTRYPENSONNEL AND ACC.DELSIGN = 1) AS ENTRYPENSONNEL,"
						 +" A.AUTOAUDITSTATUS AS AUTOAUDITSTATUS,"
						 +" E.MANUALAUDITSTATUS, TO_CHAR(E.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,"
						 +" (SELECT AOT.NAME FROM ACCOUNT AOT WHERE AOT.ACCOUNTNAME = E.MANUALAUDITNAME AND AOT.DELSIGN = 1) AS MANUALAUDITNAME,"
						 +" E.CITYAUDIT, TO_CHAR(E.CITYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYAUDITTIME,"
						 +" (SELECT ACT.NAME FROM ACCOUNT ACT WHERE ACT.ACCOUNTNAME = E.CITYAUDITPENSONNEL AND ACT.DELSIGN = 1) AS CITYAUDITPENSONNEL,"
						 +" TO_CHAR(E.FINANCIALDATETIME,'yyyy-mm-dd hh24:mi:ss') FINANCIALDATETIME, "
						 +" (SELECT AT.NAME FROM ACCOUNT AT WHERE AT.ACCOUNTNAME = E.FINANCIALOPERATOR AND AT.DELSIGN = 1) AS FINANCIALOPERATOR,"
						 +" D.BEILV, (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, ZD.BGSIGN,"
						 +" ZD.BIEMING, E.LIUCHENGHAO, E.noteno, RTNAME(E.Notetypeid) AS Notetypeid,ZD.ID, D.DBID, D.DBZBDYHH,"
						 +" E.JSZQ,E.ELECTRICFEE_ID," 
						 +" E.NETWORKDF,E.INFORMATIZATIONDF,E.ADMINISTRATIVEDF,E.MARKETDF,E.BUILDDF,E.DDDF,rtName(ZD.Property) as property, E.QSDBDL,"
						 +" (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) AS SHI,"
						 +" E.COUNTYAUDITSTATUS, E.COUNTYAUDITNAME, TO_CHAR(E.COUNTYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') COUNTYAUDITTIME, E.CITYZRAUDITSTATUS, E.CITYZRAUDITNAME, TO_CHAR(E.CITYZRAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYZRAUDITTIME, A.ACTUALDEGREE,"
						 +" E.AUTOAUDITSTATUS DFBZ,A.LASTFLOATDEGREE,A.LASTACTUALDEGREE,E.CHANGEVALUE,E.ACTUALLINELOSSVALUE,A.BLHDL/E.JSZQ BZRJ,A.DEDHDL,A.CSDB,A.SCB,A.HBZQ "
						 +" FROM ZHANDIAN ZD, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E WHERE ZD.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND"
						 +" A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' " 
						 + whereStr+"  AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");

			System.out.println("电费缴纳明 细查询/导出:"+sql.toString());//后台打印sql
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());//运行sql
			while (rs.next()) {
				ElectricDetail bean = new ElectricDetail();//库里每有一行数据便生成一个bean
				//------接收查询到的数据(并判断是否为空)------
				String xian = rs.getString(1)!=null?rs.getString(1):"";
				String xiaoqu = rs.getString(2)!=null?rs.getString(2):"";
				String jzname = rs.getString(3)!=null?rs.getString(3):"";
				String dbname = rs.getString(4)!=null?rs.getString(4):"";
				String dfzflx = rs.getString(5)!=null?rs.getString(5):"";
				String accountmonth = rs.getString(6)!=null?rs.getString(6):"";
				String kjyf = rs.getString(7)!=null?rs.getString(7):"";
				String gdfs = rs.getString(8)!=null?rs.getString(8):"";
				String lastdegree = rs.getString(9)!=null?rs.getString(9):"";
				String thisdegree = rs.getString(10)!=null?rs.getString(10):"";
				String lastdatetime = rs.getString(11)!=null?rs.getString(11):"";
				String thisdatetime = rs.getString(12)!=null?rs.getString(12):"";
				String blhdl = rs.getString(13)!=null?rs.getString(13):"";
				String floatdegree = rs.getString(14)!=null?rs.getString(14):"";
				String memo = rs.getString(15)!=null?rs.getString(15):"";
				String edhdl = rs.getString(16)!=null?rs.getString(16):"";
				String unitprice = rs.getString(17)!=null?rs.getString(17):"";
				String floatpay = rs.getString(18)!=null?rs.getString(18):"";
				String memo1 = rs.getString(19)!=null?rs.getString(19):"";
				String actualpay = rs.getString(20)!=null?rs.getString(20):"";
				String zlfh = rs.getString(21)!=null?rs.getString(21):"";
				String jlfh = rs.getString(22)!=null?rs.getString(22):"";
				String entrytime = rs.getString(23)!=null?rs.getString(23):"";
				String entrypensonnel = rs.getString(24)!=null?rs.getString(24):"";
				String autoauditstatus = rs.getString(25)!=null?rs.getString(25):"";
				String status = rs.getString(26)!=null?rs.getString(26):"";//人工审核状态&财务审核状态 
				String manualauditdatetime = rs.getString(27)!=null?rs.getString(27):"";
				String manualauditname = rs.getString(28)!=null?rs.getString(28):"";
				String cityaudit = rs.getString(29)!=null?rs.getString(29):"";
				String cityaudittime = rs.getString(30)!=null?rs.getString(30):"";
				String cityauditpensonnel = rs.getString(31)!=null?rs.getString(31):"";
				String financialdatetime = rs.getString(32)!=null?rs.getString(32):"";
				String financialoperator = rs.getString(33)!=null?rs.getString(33):"";
				String beilv = rs.getString(34)!=null?rs.getString(34):"";
				String stationtype = rs.getString(35)!=null?rs.getString(35):"";
				String bgsign = rs.getString(36)!=null?rs.getString(36):"";
				String bieming = rs.getString(37)!=null?rs.getString(37):"";
				String liuchenghao = rs.getString(38)!=null?rs.getString(38):"";
				String noteno = rs.getString(39)!=null?rs.getString(39):"";
				String notetypeid = rs.getString(40)!=null?rs.getString(40):"";
				String id = rs.getString(41)!=null?rs.getString(41):"";
				String dbid = rs.getString(42)!=null?rs.getString(42):"";
				String dbzbdyhh = rs.getString(43)!=null?rs.getString(43):"";
				String cycle = rs.getString(44)!=null?rs.getString(44):"";
				String electricfeeid = rs.getString(45) != null ? rs.getString(45):"";
				String sc = rs.getString(46) != null ? rs.getString(46):"";
				String xxhzc = rs.getString(47) != null ? rs.getString(47):"";
				String bg = rs.getString(48) != null ? rs.getString(48):"";
				String yy = rs.getString(49) != null ? rs.getString(49):"";
				String jstz = rs.getString(50) != null ? rs.getString(50):"";
				String dddf = rs.getString(51) != null ? rs.getString(51):"";
				String zdsx = rs.getString("Property")!=null?rs.getString("Property"):"";
				String qsdbdl = rs.getString("QSDBDL")!=null?rs.getString("QSDBDL"):"";
				String shi = rs.getString("SHI")!=null?rs.getString("SHI"):"";
				String countyauditstatus = rs.getString("COUNTYAUDITSTATUS")!=null?rs.getString("COUNTYAUDITSTATUS"):"";
				String countyauditname = rs.getString("COUNTYAUDITNAME")!=null?rs.getString("COUNTYAUDITNAME"):"";
				String countyaudittime = rs.getString("COUNTYAUDITTIME")!=null?rs.getString("COUNTYAUDITTIME"):"";
				String cityzrauditstatus = rs.getString("CITYZRAUDITSTATUS")!=null?rs.getString("CITYZRAUDITSTATUS"):"";
				String cityzrauditname = rs.getString("CITYZRAUDITNAME")!=null?rs.getString("CITYZRAUDITNAME"):"";
				String cityzraudittime = rs.getString("CITYZRAUDITTIME")!=null?rs.getString("CITYZRAUDITTIME"):"";
				String actualdegree = rs.getString("ACTUALDEGREE")!=null?rs.getString("ACTUALDEGREE"):"";
				String dfbz = rs.getString("DFBZ")!=null?rs.getString("DFBZ"):"";
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0.00"; //上期电表用电量
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0.00"; //上期电量调整
				String dedhdl = rs.getString("DEDHDL")!=null?rs.getString("DEDHDL"):"0";//电费超市标比例
				String csdb = rs.getString("CSDB")!=null?rs.getString("CSDB"):"0";//电费超省标比例
				String scb = rs.getString("SCB")!=null?rs.getString("SCB"):"0";//生产标
				String hbzq = rs.getString("HBZQ")!=null?rs.getString("HBZQ"):"0";//合并周期
				
				bean.setScb(Format.str2(scb));
				bean.setHbzq(Format.str2(hbzq));
				bean.setDedhdl(Double.parseDouble(dedhdl));
				bean.setCsdb(Double.parseDouble(csdb));
				
				String autobz = "";//自动审核状态
				if(autoauditstatus!=null&&autoauditstatus.equals("1")&&dfbz!=null&&dfbz.equals("1")){
					autobz="通过";
				 }else{
					autobz="不通过";
				 }

				
				
				//------以下字段直接放入bean------
				bean.setXian(xian);
				bean.setZdsx(zdsx);
				bean.setXiaoqu(xiaoqu);
				bean.setJzname(jzname);
				bean.setDbname(dbname);
				bean.setDfzflx(dfzflx);
				bean.setGdfs(gdfs);
				bean.setBlhdl(blhdl);
			    bean.setFloatdegree(floatdegree);
			    bean.setMemo(memo);
			    bean.setFloatpay(floatpay);
			    bean.setMemo1(memo1);
			    bean.setAutoauditstatus(autobz);
			    bean.setManualauditdatetime(manualauditdatetime);
				bean.setCityaudittime(cityaudittime);
				bean.setCityauditpensonnel(cityauditpensonnel);
			    bean.setFinancialoperator(financialoperator);
			    bean.setStationtype(stationtype);  
			    bean.setBieming(bieming);
			    bean.setLiuchenghao(liuchenghao);
			    bean.setNoteno(noteno);
			    bean.setNotetypeid(notetypeid);
			    bean.setId(Integer.parseInt(id));
			    bean.setDbid(dbid);
				bean.setDbzbdyhh(dbzbdyhh);
				bean.setElectricfeeid(electricfeeid);
				bean.setShi(shi);
				bean.setSc(sc);
				bean.setXxhzc(xxhzc);
				bean.setBg(bg);
				bean.setYy(yy);
				bean.setJstz(jstz);
				bean.setDddf(dddf);
				bean.setCountyauditname(countyauditname);
				bean.setCountyaudittime(countyaudittime);
				bean.setCityzrauditname(cityzrauditname);
				bean.setCityzraudittime(cityzraudittime);
				bean.setActualdegree(actualdegree);
				
				//------以下字段再次经过过滤条件后放入bean------
				if(accountmonth.equals("null")){
					bean.setAccountmonth("");
			    }else{
			    	bean.setAccountmonth(accountmonth);
			    }
				if("null".equals(kjyf)){
					bean.setKjyf("");
				}else{
					bean.setKjyf(kjyf);
				}
				if(lastdegree.equals("null")||lastdegree.equals(" ")||lastdegree.equals("o")){
					bean.setLastdegree("");
				}else{
					bean.setLastdegree(lastdegree);
				}
				if(thisdegree.equals("null")||thisdegree.equals(" ")||thisdegree.equals("o")){
					bean.setThisdegree("");
				}else{
					bean.setThisdegree(thisdegree);
				}			
				if(lastdatetime=="0"||lastdatetime.equals("0")||lastdatetime.equals("null")||lastdatetime.equals(" ")){
					bean.setLastdatetime("");
				}else{
					bean.setLastdatetime(lastdatetime);
				}
			    if(thisdatetime=="0"||thisdatetime.equals("0")||thisdatetime.equals("null")||thisdatetime.equals(" ")){
			    	bean.setThisdatetime("");
			    }else{
			    	bean.setThisdatetime(thisdatetime);
			    }
			    if(edhdl.equals("null")||edhdl.equals(" ")||edhdl.equals("o")||edhdl.equals("")){
			    	bean.setEdhdl("0");
			    }else{
			    	bean.setEdhdl(edhdl);
			    }
			    if("".equals(unitprice)||"null".equals(unitprice)||"o".equals(unitprice)||" ".equals(unitprice)){
			    	bean.setUnitprice("0");
			    }else{
			    	bean.setUnitprice(unitprice);
			    }
			    double df=0.00;
			    DecimalFormat mat = new DecimalFormat("0.00");
			    if ("null".equals(actualpay)||"".equals(actualpay)) { 
			    	
			    	actualpay = "0";
					df = Double.parseDouble(actualpay);
					actualpay = mat.format(df);
					bean.setActualpay(actualpay);
			    }else{
			    	double last = Double.parseDouble(actualpay);
					actualpay = mat.format(last);
				    bean.setActualpay(actualpay);
			    }
			 
			    if ("null".equals(zlfh)||"".equals(zlfh)) { 
			    	bean.setZlfh(0);
			    }else{
			    	bean.setZlfh(Double.parseDouble(zlfh));
			    }
			    if ("null".equals(jlfh)||"".equals(jlfh)) { 
			    	bean.setJlfh(0);
			    }else{
			    	bean.setJlfh(Double.parseDouble(jlfh));
			    }
			    if ("null".equals(cycle)||"".equals(cycle)) { 
			    	bean.setCycle("0");
			    }else{
			    	bean.setCycle(cycle);
			    }
			    if(entrytime=="0"||entrytime.equals("0")||entrytime.equals("null")){
			    	bean.setEntrytime("");
			    }else{
			    	bean.setEntrytime(entrytime);
			    }
			    if("null".equals(entrypensonnel)){
			    	bean.setEntrypensonnel("");
			    }else{
			    	bean.setEntrypensonnel(entrypensonnel);
			    }
				if(manualauditname.equals("null")){
					bean.setManualauditname("");
				}else{
				    bean.setManualauditname(manualauditname);
				}
			    if(financialdatetime=="0"||financialdatetime.equals("0")||financialdatetime.equals("null")){
			      bean.setFinancialdatetime("");
			    }else{
			    	bean.setFinancialdatetime(financialdatetime);
			    }
			    if(qsdbdl.equals("null")||qsdbdl.equals(" ")||qsdbdl.equals("o")||qsdbdl.equals("")){
			    	bean.setQsdbdl(0);
			    }else{
			    	bean.setQsdbdl(Double.parseDouble(qsdbdl));
			    }
			    
			    if(beilv.equals("null")||beilv.equals(" ")||beilv.equals("o")||beilv.equals("")){
			    	bean.setBeilv("0");
			    }else{
			    	bean.setBeilv(beilv);
			    }

			    //------小数格式化------
         		//(2位)
         		bean.setLastdegree(Format.str2(bean.getLastdegree()));//起始电表数
         		bean.setThisdegree(Format.str2(bean.getThisdegree()));//结束电表数
         		bean.setBlhdl(Format.str2(bean.getBlhdl()));//报账用电量
         		bean.setFloatdegree(Format.str2(bean.getFloatdegree()));//用电量调整
         		bean.setJlfh(Format.d2(bean.getJlfh()));//交流负荷
         		bean.setFloatpay(Format.str2(bean.getFloatpay()));//电费调整
         		bean.setBeilv(Format.str2(bean.getBeilv()));//倍率
         		//bean.setActualpay(bean.getActualpay());//实际电费
         		bean.setZlfh(Format.d2(bean.getZlfh()));//直流负荷
         		bean.setEdhdl(Format.str2(bean.getEdhdl()));//额定耗电量
         		bean.setQsdbdl(Format.d2(bean.getQsdbdl()));//全省定标电量
         		bean.setActualdegree(Format.str2(bean.getActualdegree()));//实际用电量

         		//(4位)
         		bean.setUnitprice(Format.str4(bean.getUnitprice()));//本次单价

				//------计算 额定电费 = 额定耗电量*本次单价*结算周期------
				double rated = Double.parseDouble(bean.getEdhdl()) * Double.parseDouble(bean.getUnitprice()) * Double.parseDouble(bean.getCycle());
				bean.setRated(Format.str2(Double.toString(rated)));//小数格式化

         		//------计算 省定标电费=省定标电量*单价*结算周期------
         		double sdbdf = bean.getQsdbdl() * Double.parseDouble(bean.getUnitprice()) * Double.parseDouble(bean.getCycle());
				bean.setSdbdf(Format.d2(sdbdf));//小数格式化
				//------计算 超省定标电费额=实际电费-省定标电费------
				double csdbdfe = Double.parseDouble(bean.getActualpay()) - bean.getSdbdf();
				bean.setCsdbdfe(Format.d2(csdbdfe));//小数格式化
				
//				//------计算 电表用电量=(结束电表数-起始电表数)*倍率------2014/4/18修改
//				Double dbydl = Double.parseDouble(bean.getBeilv()) * Double.parseDouble(bean.getActualdegree());
				Double dbydl = (Double.parseDouble(bean.getThisdegree()) - Double.parseDouble(bean.getLastdegree())) * Double.parseDouble(bean.getBeilv());
				bean.setDbydl(Format.d2(dbydl));//小数格式化
				
         		//------市级审核状态------
         		if("0".equals(cityaudit)){
         			bean.setCityaudit("未审核");
         		}else if("1".equals(cityaudit)){
         			bean.setCityaudit("通过");
         		}else if("-2".equals(cityaudit)){
         			bean.setCityaudit("不通过");
         		}
         		//------人工审核状态  财务审核状态------
         		String manualauditstatus = "";//人工
         		String financialstatus = "";//财务
    		    if("0".equals(status)){
    		    	manualauditstatus = "未审核";
    		    	financialstatus = "未审核";
    		    }else if("1".equals(status)){
    		    	manualauditstatus = "通过";
    		    	financialstatus = "未审核";
    		    }else if("2".equals(status)){
    		    	manualauditstatus = "通过";
    		    	financialstatus = "通过";
    		    }else if("-1".equals(status)){
    		    	manualauditstatus = "通过";
    		    	financialstatus = "不通过";
    		    }else if("-2".equals(status)){
    		    	manualauditstatus = "不通过";
    		    	financialstatus = "未审核";
    			}
    		    //------区县主任审核状态------
         		if("0".equals(countyauditstatus)){
         			bean.setCountyauditstatus("未审核");
         		}else if("1".equals(countyauditstatus)){
         			bean.setCountyauditstatus("通过");
         		}else if("2".equals(countyauditstatus)){
         			bean.setCountyauditstatus("不通过");
         		}
         		//------市主任审核状态------
         		if("0".equals(cityzrauditstatus)){
         			bean.setCityzrauditstatus("未审核");
         		}else if("1".equals(cityzrauditstatus)){
         			bean.setCityzrauditstatus("通过");
         		}else if("2".equals(cityzrauditstatus)){
         			bean.setCityzrauditstatus("不通过");
         		}
         		
    		    bean.setManualauditstatus(manualauditstatus);
    		    bean.setFinancialstatus(financialstatus);
    		  //电量调增*倍率、上期电表用电量、上期电量调整*倍率列
				String bv = "";
				if(Format.str_d(bean.getBeilv())==0){//倍率为空 或 为 0
					bv="1";
				}else{
					bv = bean.getBeilv();
				}
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(floatdegree)*Format.str_d(bv)));//电量调增*倍率
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//上期电量调整*倍率列
				bean.setLastfloatdegreeandbl(lastfloatdegreeandbl);//上期电量调整*倍率
				bean.setLastactualdegree(lastactualdegree);//上期电表用电量
				bean.setFloatdegreeandbl(floatdegreeandbl);//电量调整*倍率
		
				//人工审核电费管理日均电量查询  效率太慢  不采用这种方式
				/*		String sqla = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +bean.getId()+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
				DataBase dba = new DataBase();
				Connection conna = dba.getConnection();
				Statement staa = conna.createStatement();
				 System.out.println("人工审核电费管理日均电量查询"+sqla.toString());
				ResultSet rsa = staa.executeQuery(sqla);
				String glbrjl="";
				if(rsa.next()){
					glbrjl=rsa.getString("GLBRJL");//管理表日均电量
				}
				dba.free(rsa, staa, conna);*/
				bean.setGlbrjl(Format.str2(""));
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //变损值
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //实际线损值
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//线变损电量
				bean.setLineandchangeandbl(changeandlineandbl);//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				list.add(bean);//把每个bean放进结果集
			}
		} catch (Exception e) {
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
	
	/**
	 * @param list List(ElectricDetail)
	 * @return (ElectricDetail)
	 * @see 计算电量合计和电费合计
	 * @author ZengJin 2014-1-16
	 */
	public ElectricDetail total(List<ElectricDetail> list) {
		ElectricDetail total = new ElectricDetail();//生成一个total对象
		double totalelectric = 0;//电量合计	
 		double totalmoney = 0;//电量合计	
		int i;
		for(i = 0; i<list.size();i++){
			//遍历求和
			ElectricDetail bean = list.get(i);
     		totalelectric = totalelectric + Double.parseDouble(bean.getBlhdl());
     		totalmoney = totalmoney + Double.parseDouble(list.get(i).getActualpay());
		}
		//放入total
		total.setTotalelectric(Format.d2(totalelectric));
		total.setTotalmoney(Format.d2(totalmoney));
		return total;
	}
}
