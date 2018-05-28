package com.ptac.app.datastatistics.electricitybills.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.datastatistics.electricitybills.bean.ElecBillsBean;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author �
 * @see ��ѽ�����ϸʵ�ֲ�
 */
public class ElecBillsDaoImp implements ElecBillsDao {

	/**
	 * @author �
	 * @see ��ѽ�����ϸ��ѯ
	 */
	public ElecBillsBean query(List<ElecBillsBean> list) {
		
		ElecBillsBean bean = new ElecBillsBean();
		double totalMoney = 0;
		int i;
		for(i = 0; i < list.size(); i++){
			bean = list.get(i);
			totalMoney = totalMoney + Double.parseDouble(bean.getActualpay());
		}
		bean.setTotalmoney(Format.d2(totalMoney));
		return bean;
	}

	/**
	 * @author �
	 * @see ��ѽ�����ϸ����
	 */
	public List<ElecBillsBean> export(String whereStr, String loginId) {
		
		List<ElecBillsBean> list = new ArrayList<ElecBillsBean>();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
				sql.append(" SELECT rndiqu(ZD.XIAN) XIAN, rndiqu(ZD.XIAOQU) XIAOQU, ZD.JZNAME, D.DBNAME, RTNAME(D.DFZFLX) AS DFZFLX, to_char(E.ACCOUNTMONTH,'yyyy-mm')," 
						 +" E.KJYF, RTNAME(ZD.GDFS) AS GDFS, A.LASTDEGREE, A.THISDEGREE,to_char(A.LASTDATETIME,'yyyy-mm-dd'), to_char(A.THISDATETIME,'yyyy-mm-dd'), A.BLHDL, A.FLOATDEGREE,"
						 +" A.MEMO, E.EDHDL, E.UNITPRICE,E.FLOATPAY, E.MEMO, E.ACTUALPAY, ZD.ZLFH, ZD.JLFH, E.ENTRYTIME,"
						 +" (SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME = E.ENTRYPENSONNEL AND ACC.DELSIGN = 1) AS ENTRYPENSONNEL,"
						 +" A.AUTOAUDITSTATUS AS AUTOAUDITSTATUS,"
						 +" E.MANUALAUDITSTATUS, E.MANUALAUDITDATETIME,"
						 +" (SELECT AOT.NAME FROM ACCOUNT AOT WHERE AOT.ACCOUNTNAME = E.MANUALAUDITNAME AND AOT.DELSIGN = 1) AS MANUALAUDITNAME,"
						 +" E.CITYAUDIT, E.CITYAUDITTIME,"
						 +" (SELECT ACT.NAME FROM ACCOUNT ACT WHERE ACT.ACCOUNTNAME = E.CITYAUDITPENSONNEL AND ACT.DELSIGN = 1) AS CITYAUDITPENSONNEL,"
						 +" E.FINANCIALDATETIME, "
						 +" (SELECT AT.NAME FROM ACCOUNT AT WHERE AT.ACCOUNTNAME = E.FINANCIALOPERATOR AND AT.DELSIGN = 1) AS FINANCIALOPERATOR,"
						 +" D.BEILV, (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, ZD.BGSIGN,"
						 +" ZD.BIEMING, E.LIUCHENGHAO, E.noteno, RTNAME(E.Notetypeid) AS Notetypeid,ZD.ID, D.DBID, D.DBZBDYHH,"
						 +" E.JSZQ,E.ELECTRICFEE_ID," 
						 +" E.NETWORKDF,E.INFORMATIZATIONDF,E.ADMINISTRATIVEDF,E.MARKETDF,E.BUILDDF,E.DDDF,rtName(ZD.Property) as property, E.QSDBDL,"
						 +" (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) AS SHI,"
						 +" E.COUNTYAUDITSTATUS, E.COUNTYAUDITNAME, E.COUNTYAUDITTIME, E.CITYZRAUDITSTATUS, E.CITYZRAUDITNAME, E.CITYZRAUDITTIME, A.ACTUALDEGREE,"
						 +" E.AUTOAUDITSTATUS DFBZ,A.LASTFLOATDEGREE,A.LASTACTUALDEGREE,E.CHANGEVALUE,E.ACTUALLINELOSSVALUE,A.BLHDL/E.JSZQ BZRJ"
						 +" FROM ZHANDIAN ZD, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E WHERE ZD.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND"
						 +" A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' " 
						 + whereStr+"  AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");

			System.out.println("��ѽ����� ϸ����:"+sql.toString());
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			while (rs.next()) {
				ElecBillsBean bean = new ElecBillsBean();
				
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
				String status = rs.getString(26)!=null?rs.getString(26):"";//�˹����״̬&�������״̬ 
				String manualauditdatetime = rs.getString(27)!=null?rs.getString(27):"";
				String manualauditname = rs.getString(28)!=null?rs.getString(28):"";
				String cityaudit = rs.getString(29)!=null?rs.getString(29):"";
				String cityaudittime = rs.getString(30)!=null?rs.getString(30):"";
				String cityauditpensonnel = rs.getString(31)!=null?rs.getString(31):"";
				String financialdatetime = rs.getString(32)!=null?rs.getString(32):"";
				String financialoperator = rs.getString(33)!=null?rs.getString(33):"";
				String beilv = rs.getString(34)!=null?rs.getString(34):"";
				String stationtype = rs.getString(35)!=null?rs.getString(35):"";
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
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0.00"; //���ڵ���õ���
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0.00"; //���ڵ�������
				String autobz = "";//�Զ����״̬
				if(autoauditstatus!=null&&autoauditstatus.equals("1")&&dfbz!=null&&dfbz.equals("1")){
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
				
				//------�����ֶ��ٴξ����������������bean------
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

			    //------С����ʽ��------
         		//(2λ)
         		bean.setLastdegree(Format.str2(bean.getLastdegree()));//��ʼ�����
         		bean.setThisdegree(Format.str2(bean.getThisdegree()));//���������
         		bean.setBlhdl(Format.str2(bean.getBlhdl()));//�����õ���
         		bean.setFloatdegree(Format.str2(bean.getFloatdegree()));//�õ�������
         		bean.setJlfh(Format.d2(bean.getJlfh()));//��������
         		bean.setFloatpay(Format.str2(bean.getFloatpay()));//��ѵ���
         		bean.setBeilv(Format.str2(bean.getBeilv()));//����
         		bean.setZlfh(Format.d2(bean.getZlfh()));//ֱ������
         		bean.setEdhdl(Format.str2(bean.getEdhdl()));//��ĵ���
         		bean.setQsdbdl(Format.d2(bean.getQsdbdl()));//ȫʡ�������
         		bean.setActualdegree(Format.str2(bean.getActualdegree()));//ʵ���õ���
         		bean.setUnitprice(Format.str4(bean.getUnitprice()));//���ε���

				//------���� ���� = ��ĵ���*���ε���*��������------
				double rated = Double.parseDouble(bean.getEdhdl()) * Double.parseDouble(bean.getUnitprice()) * Double.parseDouble(bean.getCycle());
				bean.setRated(Format.str2(Double.toString(rated)));//С����ʽ��

         		//------���� ʡ������=ʡ�������*����*��������------
         		double sdbdf = bean.getQsdbdl() * Double.parseDouble(bean.getUnitprice()) * Double.parseDouble(bean.getCycle());
				bean.setSdbdf(Format.d2(sdbdf));//С����ʽ��
				//------���� ��ʡ�����Ѷ�=ʵ�ʵ��-ʡ������------
				double csdbdfe = Double.parseDouble(bean.getActualpay()) - bean.getSdbdf();
				bean.setCsdbdfe(Format.d2(csdbdfe));//С����ʽ��
				
				//------���� ����õ���=(���������-��ʼ�����)*����------2014/4/18�޸�
				Double dbydl = (Double.parseDouble(bean.getThisdegree()) - Double.parseDouble(bean.getLastdegree())) * Double.parseDouble(bean.getBeilv());
				bean.setDbydl(Format.d2(dbydl));//С����ʽ��
				
         		//------�м����״̬------
         		if("0".equals(cityaudit)){
         			bean.setCityaudit("δ���");
         		}else if("1".equals(cityaudit)){
         			bean.setCityaudit("ͨ��");
         		}else if("-2".equals(cityaudit)){
         			bean.setCityaudit("��ͨ��");
         		}
         		//------�˹����״̬  �������״̬------
         		String manualauditstatus = "";//�˹�
         		String financialstatus = "";//����
    		    if("0".equals(status)){
    		    	manualauditstatus = "δ���";
    		    	financialstatus = "δ���";
    		    }else if("1".equals(status)){
    		    	manualauditstatus = "ͨ��";
    		    	financialstatus = "δ���";
    		    }else if("2".equals(status)){
    		    	manualauditstatus = "ͨ��";
    		    	financialstatus = "ͨ��";
    		    }else if("-1".equals(status)){
    		    	manualauditstatus = "ͨ��";
    		    	financialstatus = "��ͨ��";
    		    }else if("-2".equals(status)){
    		    	manualauditstatus = "��ͨ��";
    		    	financialstatus = "δ���";
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
         		
    		    bean.setManualauditstatus(manualauditstatus);
    		    bean.setFinancialstatus(financialstatus);
    		  //��������*���ʡ����ڵ���õ��������ڵ�������*������
				String bv = "";
				if(Format.str_d(bean.getBeilv())==0){//����Ϊ�� �� Ϊ 0
					bv="1";
				}else{
					bv = bean.getBeilv();
				}
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(floatdegree)*Format.str_d(bv)));//��������*����
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//���ڵ�������*������
				bean.setLastfloatdegreeandbl(lastfloatdegreeandbl);//���ڵ�������*����
				bean.setLastactualdegree(lastactualdegree);//���ڵ���õ���
				bean.setFloatdegreeandbl(floatdegreeandbl);//��������*����
				
				bean.setGlbrjl(Format.str2(""));
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //����ֵ
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //ʵ������ֵ
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //�����վ�����
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//�߱������
				bean.setLineandchangeandbl(changeandlineandbl);//�߱������
				bean.setBzrj(Format.str2(bzrj));//�����վ�����
				list.add(bean);
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

}
