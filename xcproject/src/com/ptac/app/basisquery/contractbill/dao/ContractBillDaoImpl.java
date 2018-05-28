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
		
		System.out.println("��ͬ����ѯ��"+sql);
		
		DataBase db = new DataBase();
		
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			while(rs.next()){
				
				ContractBill bean = new ContractBill();
				
				 String id = rs.getString(1)!=null?rs.getString(1):"";//վ��ID
				 String shi = rs.getString(2)!=null?rs.getString(2):"";//��
				 String xian = rs.getString(3)!=null?rs.getString(3):"";//����	
				 String xiaoqu = rs.getString(4)!=null?rs.getString(4):"";//����	
				 String jzname = rs.getString(5)!=null?rs.getString(5):"";//վ������
				 String qsdbdl = rs.getString(6)!=null?rs.getString(6):"";//ȫʡ�������
				 String edhdl = rs.getString(7)!=null?rs.getString(7):"";//��ĵ���
				 String stationtype = rs.getString(8)!=null?rs.getString(8):"";//վ������	
				 String zdsx = rs.getString(9)!=null?rs.getString(9):"";//վ������
				 String dbid = rs.getString(10)!=null?rs.getString(10):"";//���ID
				 String dbname = rs.getString(11)!=null?rs.getString(11):"";//�������	
				 String dfzflx = rs.getString(12)!=null?rs.getString(12):"";//���֧������
				 String danjia = rs.getString(13)!=null?rs.getString(13):"";//����
				 String htbianhao = rs.getString(14)!=null?rs.getString(14):"";//��ͬ���
				 String money = rs.getString(15)!=null?rs.getString(15):"";//���
				 String dianliang = rs.getString(16)!=null?rs.getString(16):"";//�õ���
				 String startmonth = rs.getString(17)!=null?rs.getString(17):"";//��ʼ�·�		
				 String endmonth = rs.getString(18)!=null?rs.getString(18):"";//�����·�
				 String noteno = rs.getString(19)!=null?rs.getString(19):"";//Ʊ�ݱ��	
				 String notetypeid = rs.getString(20)!=null?rs.getString(20):"";//Ʊ������	
				 String accountmonth = rs.getString(21)!=null?rs.getString(21):"";//�����·�
				 String kjyf = rs.getString(22)!=null?rs.getString(22):"";//�����·�(����·�)
				 String memo = rs.getString(23)!=null?rs.getString(23):"";//��ע	
				 String beilv = rs.getString(24)!=null?rs.getString(24):"";//����
				 String lastdegree = rs.getString(25)!=null?rs.getString(25):"";//��ʼ�����	
				 String thisdegree = rs.getString(26)!=null?rs.getString(26):"";//���������
				 String lastdatetime = rs.getString(27)!=null?rs.getString(27):"";//�ϴγ���ʱ��	
				 String thisdatetime = rs.getString(28)!=null?rs.getString(28):"";//���γ���ʱ��
				 String entrytime = rs.getString(29)!=null?rs.getString(29):"";//¼��ʱ��
				 String entrypensonnel = rs.getString(30)!=null?rs.getString(30):"";//¼����Ա	
				 String autoauditstatus = rs.getString(31)!=null?rs.getString(31):"";//�Զ����״̬
				 String manualauditstatus = rs.getString(32)!=null?rs.getString(32):"";//�˹����״̬
				 String manualauditdatetime = rs.getString(33)!=null?rs.getString(33):"";//�˹����ʱ��	
				 String manualauditname = rs.getString(34)!=null?rs.getString(34):"";//�˹����Ա	
				 String countyauditstatus = rs.getString(35)!=null?rs.getString(35):"";//�����������״̬
				 String countyauditname = rs.getString(36)!=null?rs.getString(36):"";//�������������
				 String countyaudittime = rs.getString(37)!=null?rs.getString(37):"";//�����������ʱ��
				 String cityaudit = rs.getString(38)!=null?rs.getString(38):"";//�м����״̬	
				 String cityauditpensonnel = rs.getString(39)!=null?rs.getString(39):"";//�м����Ա	
				 String cityaudittime = rs.getString(40)!=null?rs.getString(40):"";//�м����ʱ��
				 String cityzrauditstatus = rs.getString(41)!=null?rs.getString(41):"";//���������״̬
				 String cityzrauditname = rs.getString(42)!=null?rs.getString(42):"";//�����������
				 String cityzraudittime = rs.getString(43)!=null?rs.getString(43):"";//���������ʱ��
				 String financialstatus = rs.getString(44)!=null?rs.getString(44):"";//�������״̬
				 String financialdatetime = rs.getString(45)!=null?rs.getString(45):"";//�������ʱ��	
				 String financialoperator = rs.getString(46)!=null?rs.getString(46):"";//�������Ա(�������Ա)
				 String liuchenghao = rs.getString(47)!=null?rs.getString(47):"";//���̺�
				
				String autobz = "";//�Զ����״̬
				if(autoauditstatus!=null&&autoauditstatus.equals("1")){
					autobz="ͨ��";
				 }else{
					autobz="��ͨ��";
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

         		bean.setLastdegree(Format.str2(bean.getLastdegree()));//��ʼ�����
         		bean.setThisdegree(Format.str2(bean.getThisdegree()));//���������
         		bean.setBeilv(Format.str2(bean.getBeilv()));//����
         		bean.setEdhdl(Format.str2(bean.getEdhdl()));//��ĵ���
         		bean.setQsdbdl(Format.str2(bean.getQsdbdl()));//ȫʡ�������
         		bean.setDanjia(Format.str4(bean.getDanjia()));//���ε���
				
         		//------�м����״̬------
         		if("0".equals(cityaudit)){
         			bean.setCityaudit("δ���");
         		}else if("1".equals(cityaudit)){
         			bean.setCityaudit("ͨ��");
         		}else if("-2".equals(cityaudit)){
         			bean.setCityaudit("��ͨ��");
         		}
         		//------�˹����״̬ ------
         		if("0".equals(manualauditstatus)){
         			bean.setManualauditstatus("δ���");
         		}else if("1".equals(manualauditstatus)){
         			bean.setManualauditstatus("ͨ��");
         		}else if("-2".equals(manualauditstatus)){
         			bean.setManualauditstatus("��ͨ��");
         		}
         		//------�������״̬------
         		if("1".equals(financialstatus)){
         			bean.setFinancialstatus("δ���");
         		}else if("2".equals(financialstatus)){
         			bean.setFinancialstatus("ͨ��");
         		}else if("-1".equals(financialstatus)){
         			bean.setFinancialstatus("��ͨ��");
         		}

    		    //------�����������״̬------
         		if("0".equals(countyauditstatus)){
         			bean.setCountyauditstatus("δ���");
         		}else if("1".equals(countyauditstatus)){
         			bean.setCountyauditstatus("ͨ��");
         		}else if("2".equals(countyauditstatus)){
         			bean.setCountyauditstatus("��ͨ��");
         		}
         		//------���������״̬------
         		if("0".equals(cityzrauditstatus)){
         			bean.setCityzrauditstatus("δ���");
         		}else if("1".equals(cityzrauditstatus)){
         			bean.setCityzrauditstatus("ͨ��");
         		}else if("2".equals(cityzrauditstatus)){
         			bean.setCityzrauditstatus("��ͨ��");
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
