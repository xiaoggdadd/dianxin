package com.ptac.app.sitemanage.stationmanage.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.JiZhanBean;
import com.noki.jizhan.KZForm;
import com.noki.jizhan.SiteFieldBean;
import com.noki.jizhan.SiteManage;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.electricitybill.bean.DomainOther;
import com.ptac.app.electricmanage.electricitybill.bean.DomainType;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecModifyDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecModifyDaoImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.sitemanage.stationmanage.util.Uitl.Util;

/**
 * @author WangYiXiao 2014-9-19 վ�����ݵ����ӣ���������ͼ��
 */
public class CheckStationInputAvailable {
	private String accountname,accountSheng;
	//----��������----
	//ʡ���У��أ�����վ�����ԣ����ű������ͣ�վ�����ͣ�վ�����ƣ��Ƿ���
	private String sheng, shi,xian,xiaoqu,property,jztype,stationtype,jzname,bgsign,
	//��ַ�����緽ʽ���÷����ͣ�վ�㸺���ˣ���վ����1��վ���Ȩ��������Ϣ���ַ����ͣ�����������
	address,gdfs,yflx,fuzeren,jzxlx,zdcq,gxxx,jflx,jrwtype,
	//վ������״̬��Զ���磬���迪ʼʱ�䣬�������ʱ�䣬��Ŀ�ţ�2G��3G,��Ƶ�������������������ң��豸����,վ�����,¥���������
	qyzt,ygd,jskssj,jsjssj,xmh,g2,g3,zpsl,zssl,changjia,sblx,area,lyjhjgs;
	//����,��ĵ���,��������,ֱ������,��Ƶ��������������;
	private double danjia,edhdl,jlfh,zlfh;
	//----������Ϣ----
	//���֧������,���ʽ,�Ա����û��ţ�ԭ���id���������ͣ���ʼʹ��ʱ��,���б�ѹ������,�������ڣ�����ֵ���������
	private String dfzflx,fkfs,zbdyhh,ydbid,linelosstype,cssytime,zybyqlx,fkzq,linelossvalue,bsdl;
	//����,��ʼ����
	private double beilv,csds;
	//վ�㸽����Ϣ
	//��������,����豸�������豸����վ����2��ֱ���磬����
	private String dytype,kdsb,yysb,jzlx2,zgd,bumen;
	//����˿�ʵռ�����������˿�ʵռ����,�յ���,�յ��ܹ���,��Զbbu,Զ��rru�����������豸����,���ƹ����豸����,2GС������
	private String kddkszsl,yydkszsl,kts, ktzgl,bbu,rru,twgxsbsl,gygxsbsl,g2xqsl,
	//3G��������,�ƶ������豸����,���Ź����豸����,��ˮ��̨��,΢��̨��,Ӫҵ�칫�յ�̨��,���������յ�̨��,�յ�һƥ��,�յ���ƥ��,�յ�ƥ��
	g3sqsl,ydgxsbsl,dxgxsbsl,ysjts,wjts,yybgktts,jfscktts,ktyps,kteps,ktps;

	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		accountname = account.getAccountName();
		accountSheng = account.getSheng();
		Vector<String> row = new Vector<String>();
		System.out.println("��֤;;;;;;;;;;;;;;;;;;;");
		row = this.check01(content);
		int a=1;
		if (row.isEmpty()) {
			System.out.println(a++);
			row = this.check02(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check03(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check04(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check05(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check06(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check07(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check08(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check09(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check10(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check11(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check12(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check13(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check14(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check15(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check16(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check17(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check18(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check19(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check20(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check21(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check22(content);
		}
		if (row.isEmpty()) {System.out.println(a++);
			row = this.check23(content);
		}
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check24(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check25(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check26(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check27(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check28(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check29(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check30(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check31(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check32(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check33(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check34(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check35(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check36(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check37(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check38(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check39(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check40(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check41(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check42(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check43(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check44(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check45(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check46(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check47(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check48(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check49(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check50(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check51(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check52(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check53(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check54(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check55(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check56(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check57(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check58(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check59(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check60(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check61(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check62(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check63(content);
        }	
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check64(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.check65(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.getJzlxAndJwrlx(content);
        }
		if(row.isEmpty()){System.out.println(a++);
        	row = this.addZDMessage(content);
        }
		return row;
	}
	
	/** ʡ �գ�����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check01(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[0].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("ʡ����Ϊ�գ�");
		}else{
			String shengcode = Util.getAreaCode(content[0].toString());
			if("".equals(shengcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("ʡ���Ʋ����ڣ�");
			}else{
				sheng = shengcode;
			}
		}
		return row;
	}
	
	/** �� �գ�����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check02(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[1].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("�в���Ϊ�գ�");
		}else{
			String shicode = Util.getAreaCode(content[1].toString().trim(),content[0].toString().trim());
			if("".equals(shicode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Ӧʡ�µ������Ʋ����ڣ�");
			}else{
				shi = shicode;
			}
		}
		return row;
	}
	
	/** �� �գ�����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check03(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[2].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���ز���Ϊ�գ�");
		}else{
			String xiancode = Util.getAreaCode(content[2].toString().trim(),content[1].toString().trim());
			if("".equals(xiancode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Ӧ���µ��������Ʋ����ڣ�");
			}else{
				xian = xiancode;
			}
		}
		return row;
	}
	/** ���� �գ�����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check04(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[3].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("������Ϊ�գ�");
		}else{
			String xiaoqucode = Util.getAreaCode(content[3].toString().trim(),content[2].toString().trim());
			if("".equals(xiaoqucode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Ӧ�����µ��������Ʋ����ڣ�");
			}else{
				xiaoqu = xiaoqucode;
			}
		}
		return row;
	}
	
	/** ����Ȩ���ж�
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check05(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[3].toString().trim())) {
			if("".equals(Util.getLimitCode(accountname, xiaoqu))){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��û����Ӹõ���վ���Ȩ�ޣ�");
			}
		}
		return row;
	}
	/** վ������  �գ�����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check06(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("վ�����Բ���Ϊ�գ�");
		}else{
			String abc = Util.getProperty(content[5].toString().trim(), "ZDSX");
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ�����Բ����ڣ�");
			}else{
				property = abc;
			}
		}
		return row;
	}
	
	/** ���ű�������  �գ�����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check07(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[6].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���ű������Ͳ���Ϊ�գ�");
		}else{
			String abc = Util.getProperty(content[6].toString().trim(), "ZDLX");
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���ű������Ͳ����ڣ�");
			}else{
				jztype = abc;
			}
		}
		return row;
	}
	
	/** վ������  �գ����� ����Ӧվ������
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check08(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("վ�����Ͳ���Ϊ�գ�");
		}else{
			String abc = Util.getPropertyCode(content[7].toString().trim(),content[5].toString().trim() );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ������"+content[5].toString().trim()+"�²�����վ������"+content[7].toString().trim()+"��");
			}else{
				stationtype = abc;
			}
		}
		return row;
	}
	
	/** վ������ ��
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check09(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[4].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("վ�����Ʋ���Ϊ�գ�");
		}else{
			jzname = content[4].toString().trim();
		}
		return row;
	}
	/** �Ƿ��� ��
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check10(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[8].toString().trim())) {
			bgsign = "0";//Ĭ�Ϸ�
		}else{
			if("��".equals(content[8].toString().trim())){
				bgsign = "1";
			}else if("��".equals(content[8].toString().trim())){
				bgsign = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�Ƿ���������д����");
			}
		}
		return row;
	}
	/** ���緽ʽ  �գ����� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check11(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[10].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���緽ʽ����Ϊ�գ�");
		}else{
			String abc = Util.getProperty(content[10].toString().trim(),"GDFS" );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���緽ʽ�����ڣ�");
			}else{
				gdfs = abc;
			}
		}
		return row;
	}
	
	/** �÷����� ���� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check12(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[11].toString().trim())){
			String abc = Util.getProperty(content[11].toString().trim(),"FWLX" );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�÷����Ͳ����ڣ�");
			}else{
				yflx = abc;
			}
		}else{
			yflx = "fwlx03";
		}
		return row;
	}
	/** վ����� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check13(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[12].toString().trim())) {
			if(!Format.isNumber(content[12].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ�������ʽ����ȷ��");
			}else if(Format.str_d(content[12].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ���������С��0��");
			}else{
				area = content[12].toString().trim();
			}
		}else{
			area="";
		}
		return row;
	}
	
	/** ���� �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check14(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[14].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���۲���Ϊ�գ�");
		}else{
			if(!Format.isNumber(content[14].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���۸�ʽ����ȷ��");
			}else if(Format.str_d(content[14].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���۲���С��0��");
			}else{
				danjia = Format.str_d(Format.str4(content[14].toString().trim()));
			}
		}
		return row;
	}
	
	/** �ַ����ͣ�վ������Ϊ���û�������
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check15(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("���û���".equals(content[5].toString().trim())){
			if("".equals(content[15].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ������Ϊ���û������ַ����Ͳ���Ϊ�գ�");
			}else{
				String jflxcode = Util.getProperty(content[15].toString().trim(), "JFLX");
				if("".equals(jflxcode)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add("�ַ����Ͳ���ȷ��");
				}else{
					jflx = jflxcode;
				}
			}
		}else{
			jflx = "";
		}
		return row;
	}
	
	/** վ���Ȩ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check16(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("".equals(content[16].toString().trim())){
			zdcq = "zdcq04";//Ĭ������
		}else{
			String zdcqcode = Util.getProperty(content[16].toString().trim(), "ZDCQ");
			if("".equals(zdcqcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ���Ȩ�����ڣ�");
			}else{
				zdcq = zdcqcode;
			}
		}
		return row;
	}
	
	/** ������Ϣ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check17(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[17].toString().trim())){
			String gxxxcode = Util.getProperty(content[17].toString().trim(), "gxxx");
			if("".equals(gxxxcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("������Ϣ�����ڣ�");
			}else{
				gxxx = gxxxcode;
			}
		}else{
			gxxx = "";
		}
		return row;
	}
	
	/** ��ĵ��� �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check18(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[18].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("��ĵ�������Ϊ�գ�");
		}else{
			if(!Format.isNumber(content[18].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ĵ�����ʽ����ȷ��");
			}else if(Format.str_d(content[18].toString().trim())<0 || Format.str_d(content[18].toString().trim())==0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ĵ����������0��");
			}else{
				edhdl = Format.str_d(content[18].toString().trim());
			}
		}
		return row;
	}
	

	
	/** ��������  �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check19(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[19].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("�������ɲ���Ϊ�գ�");
		}else{
			if(!Format.isNumber(content[19].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�������ɸ�ʽ����ȷ��");
			}else if(Format.str_d(content[19].toString().trim())<0 ){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�������ɱ������0��");
			}else{
				jlfh = Format.str_d(content[19].toString().trim());
			}
		}
		return row;
	}
	/** ֱ������  �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check20(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[20].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("ֱ�����ɲ���Ϊ�գ�");
		}else{
			if(!Format.isNumber(content[20].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("ֱ�����ɸ�ʽ����ȷ��");
			}else if(Format.str_d(content[20].toString().trim())<0 || Format.str_d(content[20].toString().trim())==0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("ֱ�����ɱ������0��");
			}else{
				zlfh = Format.str_d(content[20].toString().trim());
			}
		}
		return row;
	}
	/** ¥���������  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check21(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[21].toString().trim())) {
			if(!Format.isNumber(content[21].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("¥�����������ʽ����ȷ��");
			}else if(Format.str_d(content[21].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("¥�������������С��0��");
			}else{
				lyjhjgs = content[21].toString().trim();
			}
		}else{
			lyjhjgs = "0";
		}
		return row;
	}
	/** վ������״̬ �գ� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check22(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[22].toString().trim())) {
			qyzt = "1";//Ĭ������
		}else{
			if("��".equals(content[22].toString().trim())){
				qyzt = "1";
			}else if("��".equals(content[22].toString().trim())){
				qyzt = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("վ������״̬��д����");
			}
		}
		return row;
	}
	
	/** Զ����  �գ� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check23(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[23].toString().trim())) {
			ygd = "0";//Ĭ�Ϸ�
		}else{
			if("��".equals(content[23].toString().trim())){
				ygd = "1";
			}else if("��".equals(content[23].toString().trim())){
				ygd = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("Զ������д����");
			}
		}
		return row;
	}
	
	/** ���迪ʼʱ�� ��ʽ 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check24(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[24].toString().trim())){
			if (!Format.isTime(content[24].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���迪ʼʱ���ʽ����ȷ");
			}else{
				if(Format.isTime02(content[24].toString().trim())){
					jskssj = Format.getTime(content[24].toString().trim());
				}else{
					jskssj = content[24].toString().trim();
				}
			}
		}else{
			jskssj = "";
		}
		return row;
	}
	
	/** �������ʱ�� ��ʽ 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check25(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[25].toString().trim())){
			if (!Format.isTime(content[25].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���迪ʼʱ���ʽ����ȷ");
			}else{
				if(Format.isTime02(content[25].toString().trim())){
					jsjssj = Format.getTime(content[25].toString().trim());
				}else{
					jsjssj = content[25].toString().trim();
				}
			}
		}else{
			jsjssj = "";
		}
		return row;
	}
	/** �������ʱ��  ����С�� ���迪ʼʱ��
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check26(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(jskssj)&&!"".equals(jsjssj)){
			try {
				Date ks = new SimpleDateFormat("yyyy-MM-dd").parse(jskssj);
				Date js = new SimpleDateFormat("yyyy-MM-dd").parse(jsjssj);
				if(ks.after(js)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add("���迪ʼʱ�䲻�����ڽ������ʱ�䣡");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���迪ʼʱ�� �� �������ʱ�� ��ʽ����ȷ��");
			}
		}
		return row;
	}
	
	/** 2G �գ� ����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check27(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[27].toString().trim())) {
			g2 = "0";//Ĭ�Ϸ�
		}else{
			if("��".equals(content[27].toString().trim())){
				g2 = "1";
			}else if("��".equals(content[27].toString().trim())){
				g2 = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("2G��д����");
			}
		}
		return row;
	}
	/** 3G �գ� ����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check28(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[28].toString().trim())) {
			g3 = "0";//Ĭ�Ϸ�
		}else{
			if("��".equals(content[28].toString().trim())){
				g3 = "1";
			}else if("��".equals(content[28].toString().trim())){
				g3 = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("3G��д����");
			}
		}
		return row;
	}
	/** ��Ƶ����  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check29(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[29].toString().trim())) {
			if(!Format.isNumber(content[29].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Ƶ������ʽ����ȷ��");
			}else if(Format.str_d(content[29].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Ƶ��������С��0��");
			}else{
				zpsl = content[29].toString().trim();
			}
		}else{
			zpsl = "0";
		}
		return row;
	}
	
	/** ��������  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check30(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[30].toString().trim())) {
			if(!Format.isNumber(content[30].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("����������ʽ����ȷ��");
			}else if(Format.str_d(content[30].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("������������С��0��");
			}else{
				zssl = content[30].toString().trim();
			}
		}else{
			zssl = "0";
		}
		return row;
	}
	/** ����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check31(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[31].toString().trim())){
			String changjiacode = Util.getProperty(content[31].toString().trim(), "cj");
			if("".equals(changjiacode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���Ҳ����ڣ�");
			}else{
				changjia = changjiacode;
			}
		}else{
			changjia = "";
		}
		return row;
	}
	
	/** �豸����
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check32(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[32].toString().trim())){
			String sblxcode = Util.getProperty(content[32].toString().trim(), "sblx");
			if("".equals(sblxcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�豸���Ͳ����ڣ�");
			}else{
				sblx = sblxcode;
			}
		}else{
			sblx = "";
		}
		return row;
	}
	
	/** ���֧������  �գ����� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check33(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[33].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���֧�����Ͳ���Ϊ�գ�");
		}else{
			String dfzflxcode = Util.getProperty(content[33].toString().trim(),"dfzflx" );
			if("".equals(dfzflxcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���֧�����Ͳ����ڣ�");
			}else{
				dfzflx = dfzflxcode;
			}
		}
		return row;
	}
	
	/** ���ʽ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check34(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[34].toString().trim())){
			String fkfscode = Util.getProperty(content[34].toString().trim(), "FKFS");
			if("".equals(fkfscode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���ʽ�����ڣ�");
			}else{
				fkfs = fkfscode;
			}
		}else{
			fkfs = "";
		}
		return row;
	}
	
	/** ��������  �գ����� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check35(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[35].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("�������ڲ���Ϊ�գ�");
		}else{
			String fkzqcode = Util.getProperty(content[35].toString().trim(),"FKZQ" );
			if("".equals(fkzqcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�������ڲ����ڣ�");
			}else{
				fkzq = fkzqcode;
			}
		}
		return row;
	}
	/** ���� �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check36(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[37].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���ʲ���Ϊ�գ�");
		}else{
			if(!Format.isNumber(content[37].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���ʸ�ʽ����ȷ��");
			}else if(Format.str_d(content[37].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���ʲ���С��0��");
			}else{
				beilv = Format.str_d(content[37].toString().trim());
			}
		}
		return row;
	}
	
	/** ��������
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check37(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[39].toString().trim())){
			String linelosstypecode = Util.getProperty(content[39].toString().trim(), "xslx");
			if("".equals(linelosstypecode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�������Ͳ����ڣ�");
			}else{
				linelosstype = linelosstypecode;
			}
		}else{
			linelosstype = "";
		}
		return row;
	}
	
	/** ����ֵ �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check38(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[40].toString().trim())) {
			if(!Format.isNumber(content[40].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("����ֵ��ʽ����ȷ��");
			}else if(Format.str_d(content[40].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("����ֵ����С��0��");
			}else{
				linelossvalue = content[40].toString().trim();
			}
		}else{
			linelossvalue = "";
		}
		return row;
	}
	
	/** ��ʼ���� �գ� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check39(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[41].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("��ʼ��������Ϊ�գ�");
		}else{
			if(!Format.isNumber(content[41].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ʼ������ʽ����ȷ��");
			}else if(Format.str_d(content[41].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ʼ��������С��0��");
			}else{
				csds = Format.str_d(content[41].toString().trim());
			}
		}
		return row;
	}
	
	/** ��ʼʹ��ʱ��  ���� Ϊ�� ����ʽ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check40(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("".equals(content[42].toString().trim())){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("��ʼʹ��ʱ�䲻��Ϊ�գ�");
		}else{
			if (!Format.isTime(content[42].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ʼʹ��ʱ���ʽ����ȷ");
			}else{
				if(Format.isTime02(content[42].toString().trim())){
					cssytime = Format.getTime(content[42].toString().trim());
				}else{
					cssytime = content[42].toString().trim();
				}
				try {
					Date date1 = new Date();
					Date date2 = new Date();
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					date1 = sf.parse("2014-01-01");
					date2 = sf.parse(cssytime);
					if(date2.before(date1)){
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add("����ʧ�ܣ�����ʼʹ��ʱ��Ҫ���ڵ���2014-01-01!");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}
	
	/** ������� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check41(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[44].toString().trim())) {
			if(!Format.isNumber(content[44].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���������ʽ����ȷ��");
			}else if(Format.str_d(content[44].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�����������С��0��");
			}else{
				bsdl = content[44].toString().trim();
			}
		}else{
			bsdl = "0";
		}
		return row;
	}
	
	/** �������� ���� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check42(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[45].toString().trim())){
			String abc = Util.getProperty(content[45].toString().trim(),"dytype" );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�������Բ����ڣ�");
			}else{
				dytype = abc;
			}
		}else{
			dytype = "";
		}
		return row;
	}
	
	/** ����豸 �գ���ȷ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check43(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[46].toString().trim())) {
			kdsb = "0";//Ĭ����
		}else{
			if("��".equals(content[46].toString().trim())){
				kdsb = "1";
			}else if("��".equals(content[46].toString().trim())){
				kdsb = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("����豸������д����");
			}
		}
		return row;
	}
	
	/** �����豸 �գ���ȷ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check44(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[47].toString().trim())) {
			yysb = "0";//Ĭ����
		}else{
			if("��".equals(content[47].toString().trim())){
				yysb = "1";
			}else if("��".equals(content[47].toString().trim())){
				yysb = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�����豸������д����");
			}
		}
		return row;
	}
	/** ��վ����2 ���� 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check45(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(!"".equals(content[48].toString().trim())){
			String abc = Util.getProperty(content[48].toString().trim(),"jzlx2" );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��վ����2 �����ڣ�");
			}else{
				jzlx2 = abc;
			}
		}else{
			jzlx2 = "";
		}
		return row;
	}
	
	/** ����˿�ʵռ����  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check46(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[49].toString().trim())) {
			if(!Format.isNumber(content[49].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("����˿�ʵռ���� ��ʽ����ȷ��");
			}else if(Format.str_d(content[49].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("����˿�ʵռ���� ����С��0��");
			}else{
				kddkszsl = content[49].toString().trim();
			}
		}else{
			kddkszsl = "0";
		}
		return row;
	}
	/**�����˿�ʵռ���� ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check47(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[50].toString().trim())) {
			if(!Format.isNumber(content[50].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�����˿�ʵռ������ʽ����ȷ��");
			}else if(Format.str_d(content[50].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�����˿�ʵռ��������С��0��");
			}else{
				yydkszsl = content[50].toString().trim();
			}
		}else{
			yydkszsl = "0";
		}
		return row;
	}
	/** �յ���  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check48(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[51].toString().trim())) {
			if(!Format.isNumber(content[51].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ�����ʽ����ȷ��");
			}else if(Format.str_d(content[51].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ�������С��0��");
			}else{
				kts = content[51].toString().trim();
			}
		}else{
			kts = "0";
		}
		return row;
	}
	/** �յ��ܹ���  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check49(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[52].toString().trim())) {
			if(!Format.isNumber(content[52].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ��ܹ��ʸ�ʽ����ȷ��");
			}else if(Format.str_d(content[52].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ��ܹ��ʲ���С��0��");
			}else{
				ktzgl = content[52].toString().trim();
			}
		}else{
			ktzgl = "0";
		}
		return row;
	}
	/** ֱ���� �գ���ȷ
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check50(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[53].toString().trim())) {
			zgd = "0";//Ĭ�Ϸ�
		}else{
			if("��".equals(content[53].toString().trim())){
				zgd = "1";
			}else if("��".equals(content[53].toString().trim())){
				zgd = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("ֱ����������д����");
			}
		}
		return row;
	}
	/** ��Զbbu  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check51(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[54].toString().trim())) {
			if(!Format.isNumber(content[54].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Զbbu��ʽ����ȷ��");
			}else if(Format.str_d(content[54].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��Զbbu����С��0��");
			}else{
				bbu = content[54].toString().trim();
			}
		}else{
			bbu = "0";
		}
		return row;
	}
	/** Զ��rru  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check52(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[55].toString().trim())) {
			if(!Format.isNumber(content[55].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("Զ��rru��ʽ����ȷ��");
			}else if(Format.str_d(content[55].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("Զ��rru����С��0��");
			}else{
				rru = content[55].toString().trim();
			}
		}else{
			rru = "0";
		}
		return row;
	}
	/** ���������豸����  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check53(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[56].toString().trim())) {
			if(!Format.isNumber(content[56].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���������豸������ʽ����ȷ��");
			}else if(Format.str_d(content[56].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���������豸��������С��0��");
			}else{
				twgxsbsl = content[56].toString().trim();
			}
		}else{
			twgxsbsl = "0";
		}
		return row;
	}
	/** ���ƹ����豸����  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check54(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[57].toString().trim())) {
			if(!Format.isNumber(content[57].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���ƹ����豸������ʽ����ȷ��");
			}else if(Format.str_d(content[57].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���ƹ����豸��������С��0��");
			}else{
				gygxsbsl = content[57].toString().trim();
			}
		}else{
			gygxsbsl = "0";
		}
		return row;
	}
	/** 2GС������  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check55(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[59].toString().trim())) {
			if(!Format.isNumber(content[59].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("2GС��������ʽ����ȷ��");
			}else if(Format.str_d(content[59].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("2GС����������С��0��");
			}else{
				g2xqsl = content[59].toString().trim();
			}
		}else{
			g2xqsl = "0";
		}
		return row;
	}
	/** 3G��������  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check56(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[60].toString().trim())) {
			if(!Format.isNumber(content[60].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("3G�������� ��ʽ����ȷ��");
			}else if(Format.str_d(content[60].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("3G�������� ����С��0��");
			}else{
				g3sqsl = content[60].toString().trim();
			}
		}else{
			g3sqsl = "0";
		}
		return row;
	}
	/** �ƶ������豸����  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check57(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[61].toString().trim())) {
			if(!Format.isNumber(content[61].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�ƶ������豸������ʽ����ȷ��");
			}else if(Format.str_d(content[61].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�ƶ������豸��������С��0��");
			}else{
				ydgxsbsl = content[61].toString().trim();
			}
		}else{
			ydgxsbsl = "0";
		}
		return row;
	}
	/** ���Ź����豸����  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check58(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[62].toString().trim())) {
			if(!Format.isNumber(content[62].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���Ź����豸������ʽ����ȷ��");
			}else if(Format.str_d(content[62].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���Ź����豸��������С��0��");
			}else{
				dxgxsbsl = content[62].toString().trim();
			}
		}else{
			dxgxsbsl = "0";
		}
		return row;
	}
	/** ��ˮ��̨��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check59(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[63].toString().trim())) {
			if(!Format.isNumber(content[63].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ˮ��̨����ʽ����ȷ��");
			}else if(Format.str_d(content[63].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("��ˮ��̨������С��0��");
			}else{
				ysjts = content[63].toString().trim();
			}
		}else{
			ysjts = "0";
		}
		return row;
	}
	/** ΢��̨��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check60(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[64].toString().trim())) {
			if(!Format.isNumber(content[64].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("΢��̨����ʽ����ȷ��");
			}else if(Format.str_d(content[64].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("΢��̨������С��0��");
			}else{
				wjts = content[64].toString().trim();
			}
		}else{
			wjts = "0";
		}
		return row;
	}
	/** Ӫҵ�칫�յ�̨��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check61(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[65].toString().trim())) {
			if(!Format.isNumber(content[65].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("Ӫҵ�칫�յ�̨����ʽ����ȷ��");
			}else if(Format.str_d(content[65].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("Ӫҵ�칫�յ�̨������С��0��");
			}else{
				yybgktts = content[65].toString().trim();
			}
		}else{
			yybgktts = "0";
		}
		return row;
	}
	/** ���������յ�̨��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check62(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[66].toString().trim())) {
			if(!Format.isNumber(content[66].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���������յ�̨����ʽ����ȷ��");
			}else if(Format.str_d(content[66].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("���������յ�̨������С��0��");
			}else{
				jfscktts = content[66].toString().trim();
			}
		}else{
			jfscktts = "0";
		}
		return row;
	}
	/** �յ�һƥ��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check63(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[67].toString().trim())) {
			if(!Format.isNumber(content[67].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ�һƥ�� ��ʽ����ȷ��");
			}else if(Format.str_d(content[67].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ�һƥ�� ����С��0��");
			}else{
				ktyps = content[67].toString().trim();
			}
		}else{
			ktyps = "0";
		}
		return row;
	}
	/** �յ���ƥ��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check64(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[68].toString().trim())) {
			if(!Format.isNumber(content[68].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ���ƥ����ʽ����ȷ��");
			}else if(Format.str_d(content[68].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ���ƥ������С��0��");
			}else{
				kteps = content[68].toString().trim();
			}
		}else{
			kteps = "0";
		}
		return row;
	}
	/** �յ�ƥ��  ���� ������ С��0
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check65(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (!"".equals(content[69].toString().trim())) {
			if(!Format.isNumber(content[69].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ�ƥ����ʽ����ȷ��");
			}else if(Format.str_d(content[69].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("�յ�ƥ������С��0��");
			}else{
				ktps = content[69].toString().trim();
			}
		}else{
			ktps = "0";
		}
		return row;
	}
	
	/** ȷ�� ��վ����1 ������������
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> getJzlxAndJwrlx(Object[] content) {
		Vector<String> row = new Vector<String>();
		
		if("zdsx02".equals(property)){//��վ
			if("fwlx01".equals(yflx)){
				if(zlfh>=0&&zlfh<=10){
				jzxlx="jzlx01";
				}else if(10<zlfh&&zlfh<=20){
					jzxlx="jzlx02";
				}else if(20<zlfh&&zlfh<=30){
					jzxlx="jzlx03";
				}else if(30<zlfh&&zlfh<=40){
					jzxlx="jzlx04";
				}else if(40<zlfh&&zlfh<=50){
					jzxlx="jzlx05";
				}else if(50<zlfh&&zlfh<=60){
					jzxlx="jzlx06";
				}else if(60<zlfh&&zlfh<=70){
					jzxlx="jzlx07";
				}else if(70<zlfh&&zlfh<=80){
					jzxlx="jzlx08";
				}else if(80<zlfh&&zlfh<=90){
					jzxlx="jzlx09";
				}else if(90<zlfh&&zlfh<=100){
					jzxlx="jzlx10";
				}else if(zlfh>100){
					jzxlx="jzlx11";
				}else{
					jzxlx="";
				}
			}else if("fwlx02".equals(yflx)){
			    if(zlfh>=0&&zlfh<=10){
			    	jzxlx="jzlx12";
				}else if(10<zlfh&&zlfh<=20){
					jzxlx="jzlx13";
				}else if(20<zlfh&&zlfh<=30){
					jzxlx="jzlx14";
				}else if(30<zlfh&&zlfh<=40){
					jzxlx="jzlx15";
				}else if(40<zlfh&&zlfh<=50){
					jzxlx="jzlx16";
				}else if(50<zlfh&&zlfh<=60){
					jzxlx="jzlx17";
				}else if(60<zlfh&&zlfh<=70){
					jzxlx="jzlx18";
				}else if(70<zlfh&&zlfh<=80){
					jzxlx="jzlx19";
				}else if(80<zlfh&&zlfh<=90){
					jzxlx="jzlx20";
				}else if(90<zlfh&&zlfh<=100){
					jzxlx="jzlx21";
				}else if(zlfh>100){
					jzxlx="jzlx22";
				}else{
					jzxlx="";
				}
			}else{//�÷����� Ϊ ���� �� ��
				jzxlx="";
			}
		}else if("zdsx05".equals(property)){//������
			if("fwlx01".equals(yflx)){
				if(zlfh>=0&&zlfh<=20){
		        jrwtype="jrwlx01";
		        }else if(20<zlfh&&zlfh<=40){
		        	jrwtype="jrwlx02";
		        }else if(40<zlfh&&zlfh<=60){
		        	jrwtype="jrwlx03";
				}else if(60<zlfh&&zlfh<=80){
					jrwtype="jrwlx04";
				}else if(80<zlfh&&zlfh<=100){
					jrwtype="jrwlx05";
				}else if(zlfh>100){
					jrwtype="jrwlx06";
				}else{
					jrwtype="";
				}
			}else if("fwlx02".equals(yflx)){
				if(zlfh>=0&&zlfh<=20){
					jrwtype="jrwlx07";
		        }else if(20<zlfh&&zlfh<=40){
		        	jrwtype="jrwlx08";
		        }else if(40<zlfh&&zlfh<=60){
		        	jrwtype="jrwlx09";
				}else if(60<zlfh&&zlfh<=80){
					jrwtype="jrwlx10";
				}else if(80<zlfh&&zlfh<=100){
					jrwtype="jrwlx11";
				}else if(zlfh>100){
					jrwtype="jrwlx12";
				}else{
					jrwtype="";
				}
			}else{//�÷����� Ϊ ���� �� ��
				jrwtype="";
			}
		}else{
			jzxlx="";
			jrwtype="";
		}
		return row;
	}

	/**
	 * ��ʼ��� ��Ӧ�ĵ��
	 * 
	 * @author gt_web
	 * @param content
	 * @return
	 */
	synchronized Vector<String> addZDMessage(Object[] content) {
		Vector<String> row = new Vector<String>();
		SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String entrytime=mat.format(new Date());
		
		address = content[9].toString().trim();
		fuzeren = content[13].toString().trim();
		ydbid = content[38].toString().trim();
		bumen = content[58].toString().trim();//����
		zbdyhh = content[36].toString().trim();//����Ա����û���
		zybyqlx= content[43].toString().trim();//���б�ѹ������
		xmh = content[26].toString().trim();//��Ŀ��
		String sxd_a=content[content.length-4].toString().trim();
		String sxd_b=content[content.length-3].toString().trim();
		String sxd_c=content[content.length-2].toString().trim();
		String zldy=content[content.length-1].toString().trim();
		System.out.println(sxd_c+"sfsfsfsffffffffffffff");
		if(sxd_a==null||sxd_a=="")sxd_a="0";
		if(sxd_b==null||sxd_b=="")sxd_b="0";
		if(sxd_c==null||sxd_c=="")sxd_c="0";
		if(zldy==null||zldy=="")zldy="0";
		
		System.out.println(sxd_a+"  "+sxd_b+"  "+sxd_c+" "+zldy+"  wp;alaile����");
		
		String bieming = "";
		String zdcode = "";
		String entrypensonnel=accountname;//¼����Ա
        String sydate = "";//Ͷ��ʹ��ʱ��
        String gsf="";//������
        //վ�㸽����Ϣ
        String kt1="0";//�յ�1
        String kt2="0";//�յ�2
        String twgx = "";//��������
        
        // ��̯
        String sc="100";//����%  
        String bg="0";//�칫%
        String yy="0";//Ӫҵ%
        String xxhzc="0";//��Ϣ��֧��%
        String jstz="0";//����Ͷ��%
        String dddf="0";//������%
        
		String jnglmk = "0";
		SiteFieldBean kzform = new SiteFieldBean();
		kzform.setCkkd("");
		kzform.setYsymj("");
		kzform.setJggs("");
		kzform.setYsygs("");
		kzform.setJfgd("");
		kzform.setSdwd("");
		kzform.setLyfs("");
		kzform.setSffs("");
		kzform.setGzqk("");
		kzform.setNhzb("");
		kzform.setZpsl("1");
		kzform.setZgry("");
		kzform.setKtsl("");
		kzform.setPcsl("");			
		kzform.setRll("");
		kzform.setLjzs("1");
		kzform.setTxj("");
        kzform.setUgs("");
        kzform.setYsyugs("");
        kzform.setJnjslx("");
        kzform.setYdlx("");
        
		String zzjgbm = "";
		String gczt = "";
		String gcxm = "";
		String zdcb = "0";
		String czzdid = "";

		String nhjcdy = "";
		String ERPbh = "";
		String dhID = "";
		String zhwgID = "";
		String dzywID = "";
		String longitude ="";
		String latitude = "";
		
		String watchcost = "";//�ױ�Ʒ�
		String dbid = "";//���ID
		String gldb = "on";//������
		String dbyt = "dbyt01";//�����;   
		String ysd= "0";//Զ�ܵ�
		
		
		
		SiteManage bean = new SiteManage();
		int retsign =0;
//		bean.addData(shi, xian, xiaoqu, jztype, property,
//				yflx, jflx, jzname, bieming, address, bgsign, jnglmk, gdfs,
//				area, fuzeren, accountSheng, String.valueOf(danjia), zdcode, jzxlx,
//				accountname, kzform,zzjgbm, gczt, gcxm,
//				zdcq, zdcb, String.valueOf(zlfh),  czzdid, nhjcdy, ERPbh, dhID,
//				zhwgID, dzywID, String.valueOf(edhdl), longitude, latitude,jrwtype,String.valueOf(jlfh),gsf,
//				entrypensonnel,entrytime,stationtype,dfzflx,fkfs,fkzq,zbdyhh,
//				watchcost,String.valueOf(beilv),linelosstype,linelossvalue,dbid,gldb,dbyt,
//				dytype,g2,g3,kdsb,yysb,zpsl,zssl,kddkszsl,yydkszsl,kt1,kt2,zgd,sydate,
//				sc,bg,yy,xxhzc,jstz,String.valueOf(csds),cssytime,qyzt,lyjhjgs,gxxx,ydbid,jskssj,
//				jsjssj,xmh,ygd,ysd,dddf,changjia,jzlx2,sblx,bbu,rru,twgxsbsl,gygxsbsl,
//				twgx,bumen,g2xqsl,g3sqsl,ydgxsbsl,dxgxsbsl,kts,ktzgl,ysjts,wjts,yybgktts,
//				jfscktts,ktyps,kteps,ktps,zybyqlx,bsdl,sxd_a,sxd_b,sxd_c,zldy,"");

		if (retsign == 1) {
		} else if (retsign == 2) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("���ʧ�ܣ�վ������ظ���");
		}else{
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("Error��");
		}
		return row;
	}
	

}
