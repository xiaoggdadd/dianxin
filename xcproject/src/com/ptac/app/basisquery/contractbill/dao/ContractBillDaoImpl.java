package com.ptac.app.basisquery.contractbill.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.basisquery.contractbill.bean.ContractBill;
import com.ptac.app.electricmanageUtil.Format;

public class ContractBillDaoImpl implements ContractBillDao {

	Connection conn = null;
	Statement sta = null;
	ResultSet rs = null;
	
	@Override
	public List<ContractBill> getConBill(String whereStr, String loginId) {
		
		List<ContractBill> list = new ArrayList<ContractBill>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Z.ID,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI) AS SHI,rndiqu(Z.XIAN) XIAN,"
				 	+"rndiqu(Z.XIAOQU) XIAOQU, Z.JZNAME,Z.QSDBDL,Z.EDHDL,"
				 	+"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE,"
				 	+"rtName(Z.Property) as property, D.DBID,D.DBNAME,RTNAME(D.DFZFLX) AS DFZFLX,"
				 	+"P.DANJIA,P.BARGAINID,P.MONEY,P.DIANLIANG,TO_CHAR(P.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(P.ENDMONTH,'yyyy-mm') ENDMONTH,P.NOTENO,RTNAME(P.Notetypeid) AS Notetypeid,"
				 	+"TO_CHAR(P.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,TO_CHAR(P.KJYF,'yyyy-mm') KJYF,P.MEMO,D.BEILV,P.STARTDEGREE,P.STOPDEGREE,TO_CHAR(P.STARTDATE,'yyyy-mm-dd') STARTDATE,TO_CHAR(P.ENDDATE,'yyyy-mm-dd') ENDDATE,"
				 	+"TO_CHAR(P.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,P.ENTRYPENSONNEL,P.AUTOAUDITSTATUS,P.MANUALAUDITSTATUS,TO_CHAR(P.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,P.MANUALAUDITNAME,"
				 	+"P.COUNTYAUDITSTATUS,P.COUNTYAUDITNAME,TO_CHAR(P.COUNTYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') COUNTYAUDITTIME,P.CITYAUDIT,P.CITYAUDITPENSONNEL,TO_CHAR(P.CITYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYAUDITTIME,"
				 	+"P.CITYZRAUDITSTATUS,P.CITYZRAUDITNAME,TO_CHAR(P.CITYZRAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYZRAUDITTIME,P.FINANCEAUDIT,TO_CHAR(P.FINANCIALDATE,'yyyy-mm-dd hh24:mi:ss') FINANCIALDATE,P.FINANCIALOPERATOR,P.LIUCHENGHAO"
				 	+" FROM ZHANDIAN Z, DIANBIAO D,PREPAYMENT P"
					+" WHERE Z.ID = D.ZDID AND D.DBID = PREPAYMENT_AMMETERID and D.dfzflx='dfzflx02' "
					+ whereStr + " AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
		
		System.out.println("合同单查询："+sql);
		
		DataBase db = new DataBase();
		
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			while(rs.next()){
				
				ContractBill bean = new ContractBill();
				
				 String id = rs.getString(1)!=null?rs.getString(1):"";//站点ID
				 String shi = rs.getString(2)!=null?rs.getString(2):"";//市
				 String xian = rs.getString(3)!=null?rs.getString(3):"";//区县	
				 String xiaoqu = rs.getString(4)!=null?rs.getString(4):"";//乡镇	
				 String jzname = rs.getString(5)!=null?rs.getString(5):"";//站点名称
				 String qsdbdl = rs.getString(6)!=null?rs.getString(6):"";//全省定标电量
				 String edhdl = rs.getString(7)!=null?rs.getString(7):"";//额定耗电量
				 String stationtype = rs.getString(8)!=null?rs.getString(8):"";//站点类型	
				 String zdsx = rs.getString(9)!=null?rs.getString(9):"";//站点属性
				 String dbid = rs.getString(10)!=null?rs.getString(10):"";//电表ID
				 String dbname = rs.getString(11)!=null?rs.getString(11):"";//电表名称	
				 String dfzflx = rs.getString(12)!=null?rs.getString(12):"";//电费支付类型
				 String danjia = rs.getString(13)!=null?rs.getString(13):"";//单价
				 String htbianhao = rs.getString(14)!=null?rs.getString(14):"";//合同编号
				 String money = rs.getString(15)!=null?rs.getString(15):"";//金额
				 String dianliang = rs.getString(16)!=null?rs.getString(16):"";//用电量
				 String startmonth = rs.getString(17)!=null?rs.getString(17):"";//起始月份		
				 String endmonth = rs.getString(18)!=null?rs.getString(18):"";//结束月份
				 String noteno = rs.getString(19)!=null?rs.getString(19):"";//票据编号	
				 String notetypeid = rs.getString(20)!=null?rs.getString(20):"";//票据类型	
				 String accountmonth = rs.getString(21)!=null?rs.getString(21):"";//报账月份
				 String kjyf = rs.getString(22)!=null?rs.getString(22):"";//财务月份(会计月份)
				 String memo = rs.getString(23)!=null?rs.getString(23):"";//备注	
				 String beilv = rs.getString(24)!=null?rs.getString(24):"";//倍率
				 String lastdegree = rs.getString(25)!=null?rs.getString(25):"";//起始电表数	
				 String thisdegree = rs.getString(26)!=null?rs.getString(26):"";//结束电表数
				 String lastdatetime = rs.getString(27)!=null?rs.getString(27):"";//上次抄表时间	
				 String thisdatetime = rs.getString(28)!=null?rs.getString(28):"";//本次抄表时间
				 String entrytime = rs.getString(29)!=null?rs.getString(29):"";//录入时间
				 String entrypensonnel = rs.getString(30)!=null?rs.getString(30):"";//录入人员	
				 String autoauditstatus = rs.getString(31)!=null?rs.getString(31):"";//自动审核状态
				 String manualauditstatus = rs.getString(32)!=null?rs.getString(32):"";//人工审核状态
				 String manualauditdatetime = rs.getString(33)!=null?rs.getString(33):"";//人工审核时间	
				 String manualauditname = rs.getString(34)!=null?rs.getString(34):"";//人工审核员	
				 String countyauditstatus = rs.getString(35)!=null?rs.getString(35):"";//区县主任审核状态
				 String countyauditname = rs.getString(36)!=null?rs.getString(36):"";//区县主任审核人
				 String countyaudittime = rs.getString(37)!=null?rs.getString(37):"";//区县主任审核时间
				 String cityaudit = rs.getString(38)!=null?rs.getString(38):"";//市级审核状态	
				 String cityauditpensonnel = rs.getString(39)!=null?rs.getString(39):"";//市级审核员	
				 String cityaudittime = rs.getString(40)!=null?rs.getString(40):"";//市级审核时间
				 String cityzrauditstatus = rs.getString(41)!=null?rs.getString(41):"";//市主任审核状态
				 String cityzrauditname = rs.getString(42)!=null?rs.getString(42):"";//市主任审核人
				 String cityzraudittime = rs.getString(43)!=null?rs.getString(43):"";//市主任审核时间
				 String financialstatus = rs.getString(44)!=null?rs.getString(44):"";//财务审核状态
				 String financialdatetime = rs.getString(45)!=null?rs.getString(45):"";//财务审核时间	
				 String financialoperator = rs.getString(46)!=null?rs.getString(46):"";//财务审核员(财务操作员)
				 String liuchenghao = rs.getString(47)!=null?rs.getString(47):"";//流程号
				
				String autobz = "";//自动审核状态
				if(autoauditstatus!=null&&autoauditstatus.equals("1")){
					autobz="通过";
				 }else{
					autobz="不通过";
				 }

				bean.setXian(xian);
				bean.setZdsx(zdsx);
				bean.setXiaoqu(xiaoqu);
				bean.setJzname(jzname);
				bean.setDbname(dbname);
				bean.setDfzflx(dfzflx);
			    bean.setMemo(memo);
			    bean.setAutoauditstatus(autobz);
			    bean.setManualauditdatetime(manualauditdatetime);
				bean.setCityaudittime(cityaudittime);
				bean.setCityauditpensonnel(cityauditpensonnel);
			    bean.setFinancialoperator(financialoperator);
			    bean.setStationtype(stationtype);  
			    if(noteno.equals("null")||noteno.equals(" ")||noteno.equals("o")||noteno.equals("")||null==noteno){
					bean.setNoteno("");
			    }else{
			    	bean.setNoteno(noteno);
			    }
			    if(liuchenghao.equals("null")||liuchenghao.equals(" ")||liuchenghao.equals("")||null==noteno){
					bean.setLiuchenghao("");
			    }else{
			    	bean.setLiuchenghao(liuchenghao);
			    }
			    bean.setNotetypeid(notetypeid);
			    bean.setId(id);
			    bean.setDbid(dbid);
				bean.setShi(shi);
				bean.setCountyauditname(countyauditname);
				bean.setCountyaudittime(countyaudittime);
				bean.setCityzrauditname(cityzrauditname);
				bean.setCityzraudittime(cityzraudittime);
				bean.setHtbianhao(htbianhao);
				bean.setMoney(Format.str2(money));
				bean.setDianliang(Format.str2(dianliang));
				bean.setStartmonth(startmonth);
				bean.setEndmonth(endmonth);
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
			    if(edhdl.equals("null")||edhdl.equals(" ")||edhdl.equals("o")||edhdl.equals("")||null==edhdl){
			    	bean.setEdhdl("");
			    }else{
			    	bean.setEdhdl(edhdl);
			    }
			    if("".equals(danjia)||"null".equals(danjia)||"o".equals(danjia)||" ".equals(danjia)){
			    	bean.setDanjia("0.0000");
			    }else{
			    	bean.setDanjia(danjia);
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
			    if(qsdbdl.equals("null")||qsdbdl.equals(" ")||qsdbdl.equals("o")||qsdbdl.equals("")||null==qsdbdl){
			    	bean.setQsdbdl("");
			    }else{
			    	bean.setQsdbdl(qsdbdl);
			    }
			    
			    if(beilv.equals("null")||beilv.equals(" ")||beilv.equals("o")||beilv.equals("")){
			    	bean.setBeilv("0");
			    }else{
			    	bean.setBeilv(beilv);
			    }

         		bean.setLastdegree(Format.str2(bean.getLastdegree()));//起始电表数
         		bean.setThisdegree(Format.str2(bean.getThisdegree()));//结束电表数
         		bean.setBeilv(Format.str2(bean.getBeilv()));//倍率
         		bean.setEdhdl(Format.str2(bean.getEdhdl()));//额定耗电量
         		bean.setQsdbdl(Format.str2(bean.getQsdbdl()));//全省定标电量
         		bean.setDanjia(Format.str4(bean.getDanjia()));//本次单价
				
         		//------市级审核状态------
         		if("0".equals(cityaudit)){
         			bean.setCityaudit("未审核");
         		}else if("1".equals(cityaudit)){
         			bean.setCityaudit("通过");
         		}else if("-2".equals(cityaudit)){
         			bean.setCityaudit("不通过");
         		}
         		//------人工审核状态 ------
         		if("0".equals(manualauditstatus)){
         			bean.setManualauditstatus("未审核");
         		}else if("1".equals(manualauditstatus)){
         			bean.setManualauditstatus("通过");
         		}else if("-2".equals(manualauditstatus)){
         			bean.setManualauditstatus("不通过");
         		}
         		//------财务审核状态------
         		if("1".equals(financialstatus)){
         			bean.setFinancialstatus("未审核");
         		}else if("2".equals(financialstatus)){
         			bean.setFinancialstatus("通过");
         		}else if("-1".equals(financialstatus)){
         			bean.setFinancialstatus("不通过");
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
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
