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
 * @author WangYiXiao 2014-9-19 站点数据的增加，导入完成型检查
 */
public class CheckStationInputAvailable {
	private String accountname,accountSheng;
	//----基础条件----
	//省，市，县，乡镇，站点属性，集团报表类型，站点类型，站点名称，是否标杆
	private String sheng, shi,xian,xiaoqu,property,jztype,stationtype,jzname,bgsign,
	//地址，供电方式，用房类型，站点负责人，基站类型1，站点产权，共享信息，局房类型，接入网类型
	address,gdfs,yflx,fuzeren,jzxlx,zdcq,gxxx,jflx,jrwtype,
	//站点启用状态，远供电，建设开始时间，建设结束时间，项目号，2G，3G,载频数量，载扇数量，厂家，设备类型,站点面积,楼宇交换机个数
	qyzt,ygd,jskssj,jsjssj,xmh,g2,g3,zpsl,zssl,changjia,sblx,area,lyjhjgs;
	//单价,额定耗电量,交流负荷,直流负荷,载频数量，载扇数量;
	private double danjia,edhdl,jlfh,zlfh;
	//----结算信息----
	//电费支付类型,付款方式,自报电用户号，原电表id，线损类型，初始使用时间,自有变压器类型,付款周期，线损值，变损电量
	private String dfzflx,fkfs,zbdyhh,ydbid,linelosstype,cssytime,zybyqlx,fkzq,linelossvalue,bsdl;
	//倍率,初始读数
	private double beilv,csds;
	//站点附加信息
	//地域属性,宽带设备，语音设备，基站类型2，直供电，部门
	private String dytype,kdsb,yysb,jzlx2,zgd,bumen;
	//宽带端口实占数量，语音端口实占数量,空调数,空调总功率,拉远bbu,远供rru，他网共享设备数量,固移共享设备数量,2G小区数量
	private String kddkszsl,yydkszsl,kts, ktzgl,bbu,rru,twgxsbsl,gygxsbsl,g2xqsl,
	//3G扇区数量,移动共享设备数量,电信共享设备数量,饮水机台数,微机台数,营业办公空调台数,机房生产空调台数,空调一匹数,空调二匹数,空调匹数
	g3sqsl,ydgxsbsl,dxgxsbsl,ysjts,wjts,yybgktts,jfscktts,ktyps,kteps,ktps;

	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		accountname = account.getAccountName();
		accountSheng = account.getSheng();
		Vector<String> row = new Vector<String>();
		System.out.println("验证;;;;;;;;;;;;;;;;;;;");
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
	
	/** 省 空，存在
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
			row.add("省不能为空！");
		}else{
			String shengcode = Util.getAreaCode(content[0].toString());
			if("".equals(shengcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("省名称不存在！");
			}else{
				sheng = shengcode;
			}
		}
		return row;
	}
	
	/** 市 空，存在
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
			row.add("市不能为空！");
		}else{
			String shicode = Util.getAreaCode(content[1].toString().trim(),content[0].toString().trim());
			if("".equals(shicode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("对应省下的市名称不存在！");
			}else{
				shi = shicode;
			}
		}
		return row;
	}
	
	/** 县 空，存在
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
			row.add("区县不能为空！");
		}else{
			String xiancode = Util.getAreaCode(content[2].toString().trim(),content[1].toString().trim());
			if("".equals(xiancode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("对应市下的区县名称不存在！");
			}else{
				xian = xiancode;
			}
		}
		return row;
	}
	/** 乡镇 空，存在
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
			row.add("乡镇不能为空！");
		}else{
			String xiaoqucode = Util.getAreaCode(content[3].toString().trim(),content[2].toString().trim());
			if("".equals(xiaoqucode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("对应区县下的乡镇名称不存在！");
			}else{
				xiaoqu = xiaoqucode;
			}
		}
		return row;
	}
	
	/** 地区权限判断
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
				row.add("您没有添加该地区站点的权限！");
			}
		}
		return row;
	}
	/** 站点属性  空，存在
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
			row.add("站点属性不能为空！");
		}else{
			String abc = Util.getProperty(content[5].toString().trim(), "ZDSX");
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("站点属性不存在！");
			}else{
				property = abc;
			}
		}
		return row;
	}
	
	/** 集团报表类型  空，存在
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
			row.add("集团报表类型不能为空！");
		}else{
			String abc = Util.getProperty(content[6].toString().trim(), "ZDLX");
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("集团报表类型不存在！");
			}else{
				jztype = abc;
			}
		}
		return row;
	}
	
	/** 站点类型  空，存在 ，对应站点属性
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
			row.add("站点类型不能为空！");
		}else{
			String abc = Util.getPropertyCode(content[7].toString().trim(),content[5].toString().trim() );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("站点属性"+content[5].toString().trim()+"下不存在站点类型"+content[7].toString().trim()+"！");
			}else{
				stationtype = abc;
			}
		}
		return row;
	}
	
	/** 站点名称 空
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
			row.add("站点名称不能为空！");
		}else{
			jzname = content[4].toString().trim();
		}
		return row;
	}
	/** 是否标杆 空
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check10(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[8].toString().trim())) {
			bgsign = "0";//默认否
		}else{
			if("是".equals(content[8].toString().trim())){
				bgsign = "1";
			}else if("否".equals(content[8].toString().trim())){
				bgsign = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("是否标杆内容填写错误！");
			}
		}
		return row;
	}
	/** 供电方式  空，存在 
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
			row.add("供电方式不能为空！");
		}else{
			String abc = Util.getProperty(content[10].toString().trim(),"GDFS" );
			if("".equals(abc)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("供电方式不存在！");
			}else{
				gdfs = abc;
			}
		}
		return row;
	}
	
	/** 用房类型 存在 
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
				row.add("用房类型不存在！");
			}else{
				yflx = abc;
			}
		}else{
			yflx = "fwlx03";
		}
		return row;
	}
	/** 站点面积 数字 ，不能 小于0
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
				row.add("站点面积格式不正确！");
			}else if(Format.str_d(content[12].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("站点面积不能小于0！");
			}else{
				area = content[12].toString().trim();
			}
		}else{
			area="";
		}
		return row;
	}
	
	/** 单价 空， 数字 ，不能 小于0
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
			row.add("单价不能为空！");
		}else{
			if(!Format.isNumber(content[14].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("单价格式不正确！");
			}else if(Format.str_d(content[14].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("单价不能小于0！");
			}else{
				danjia = Format.str_d(Format.str4(content[14].toString().trim()));
			}
		}
		return row;
	}
	
	/** 局房类型（站点属性为局用机房则必填）
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check15(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("局用机房".equals(content[5].toString().trim())){
			if("".equals(content[15].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("站点属性为局用机房，局房类型不能为空！");
			}else{
				String jflxcode = Util.getProperty(content[15].toString().trim(), "JFLX");
				if("".equals(jflxcode)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add("局房类型不正确！");
				}else{
					jflx = jflxcode;
				}
			}
		}else{
			jflx = "";
		}
		return row;
	}
	
	/** 站点产权
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check16(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("".equals(content[16].toString().trim())){
			zdcq = "zdcq04";//默认其他
		}else{
			String zdcqcode = Util.getProperty(content[16].toString().trim(), "ZDCQ");
			if("".equals(zdcqcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("站点产权不存在！");
			}else{
				zdcq = zdcqcode;
			}
		}
		return row;
	}
	
	/** 共享信息
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
				row.add("共享信息不存在！");
			}else{
				gxxx = gxxxcode;
			}
		}else{
			gxxx = "";
		}
		return row;
	}
	
	/** 额定耗电量 空， 数字 ，不能 小于0
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
			row.add("额定耗电量不能为空！");
		}else{
			if(!Format.isNumber(content[18].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("额定耗电量格式不正确！");
			}else if(Format.str_d(content[18].toString().trim())<0 || Format.str_d(content[18].toString().trim())==0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("额定耗电量必须大于0！");
			}else{
				edhdl = Format.str_d(content[18].toString().trim());
			}
		}
		return row;
	}
	

	
	/** 交流负荷  空， 数字 ，不能 小于0
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
			row.add("交流负荷不能为空！");
		}else{
			if(!Format.isNumber(content[19].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("交流负荷格式不正确！");
			}else if(Format.str_d(content[19].toString().trim())<0 ){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("交流负荷必须大于0！");
			}else{
				jlfh = Format.str_d(content[19].toString().trim());
			}
		}
		return row;
	}
	/** 直流负荷  空， 数字 ，不能 小于0
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
			row.add("直流负荷不能为空！");
		}else{
			if(!Format.isNumber(content[20].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("直流负荷格式不正确！");
			}else if(Format.str_d(content[20].toString().trim())<0 || Format.str_d(content[20].toString().trim())==0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("直流负荷必须大于0！");
			}else{
				zlfh = Format.str_d(content[20].toString().trim());
			}
		}
		return row;
	}
	/** 楼宇交换机个数  数字 ，不能 小于0
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
				row.add("楼宇交换机个数格式不正确！");
			}else if(Format.str_d(content[21].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("楼宇交换机个数不能小于0！");
			}else{
				lyjhjgs = content[21].toString().trim();
			}
		}else{
			lyjhjgs = "0";
		}
		return row;
	}
	/** 站点启用状态 空， 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check22(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[22].toString().trim())) {
			qyzt = "1";//默认启用
		}else{
			if("是".equals(content[22].toString().trim())){
				qyzt = "1";
			}else if("否".equals(content[22].toString().trim())){
				qyzt = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("站点启用状态填写有误！");
			}
		}
		return row;
	}
	
	/** 远供电  空， 
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check23(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[23].toString().trim())) {
			ygd = "0";//默认否
		}else{
			if("是".equals(content[23].toString().trim())){
				ygd = "1";
			}else if("否".equals(content[23].toString().trim())){
				ygd = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("远供电填写有误！");
			}
		}
		return row;
	}
	
	/** 建设开始时间 格式 
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
				row.add("建设开始时间格式不正确");
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
	
	/** 建设结束时间 格式 
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
				row.add("建设开始时间格式不正确");
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
	/** 建设结束时间  不能小于 建设开始时间
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
					row.add("建设开始时间不能晚于建设结束时间！");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("建设开始时间 或 建设结束时间 格式不正确！");
			}
		}
		return row;
	}
	
	/** 2G 空， 错误
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check27(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[27].toString().trim())) {
			g2 = "0";//默认否
		}else{
			if("是".equals(content[27].toString().trim())){
				g2 = "1";
			}else if("否".equals(content[27].toString().trim())){
				g2 = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("2G填写有误！");
			}
		}
		return row;
	}
	/** 3G 空， 错误
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> check28(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[28].toString().trim())) {
			g3 = "0";//默认否
		}else{
			if("是".equals(content[28].toString().trim())){
				g3 = "1";
			}else if("否".equals(content[28].toString().trim())){
				g3 = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("3G填写有误！");
			}
		}
		return row;
	}
	/** 载频数量  数字 ，不能 小于0
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
				row.add("载频数量格式不正确！");
			}else if(Format.str_d(content[29].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("载频数量不能小于0！");
			}else{
				zpsl = content[29].toString().trim();
			}
		}else{
			zpsl = "0";
		}
		return row;
	}
	
	/** 载扇数量  数字 ，不能 小于0
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
				row.add("载扇数量格式不正确！");
			}else if(Format.str_d(content[30].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("载扇数量不能小于0！");
			}else{
				zssl = content[30].toString().trim();
			}
		}else{
			zssl = "0";
		}
		return row;
	}
	/** 厂家
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
				row.add("厂家不存在！");
			}else{
				changjia = changjiacode;
			}
		}else{
			changjia = "";
		}
		return row;
	}
	
	/** 设备类型
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
				row.add("设备类型不存在！");
			}else{
				sblx = sblxcode;
			}
		}else{
			sblx = "";
		}
		return row;
	}
	
	/** 电费支付类型  空，存在 
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
			row.add("电费支付类型不能为空！");
		}else{
			String dfzflxcode = Util.getProperty(content[33].toString().trim(),"dfzflx" );
			if("".equals(dfzflxcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("电费支付类型不存在！");
			}else{
				dfzflx = dfzflxcode;
			}
		}
		return row;
	}
	
	/** 付款方式
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
				row.add("付款方式不存在！");
			}else{
				fkfs = fkfscode;
			}
		}else{
			fkfs = "";
		}
		return row;
	}
	
	/** 付款周期  空，存在 
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
			row.add("付款周期不能为空！");
		}else{
			String fkzqcode = Util.getProperty(content[35].toString().trim(),"FKZQ" );
			if("".equals(fkzqcode)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("付款周期不存在！");
			}else{
				fkzq = fkzqcode;
			}
		}
		return row;
	}
	/** 倍率 空， 数字 ，不能 小于0
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
			row.add("倍率不能为空！");
		}else{
			if(!Format.isNumber(content[37].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("倍率格式不正确！");
			}else if(Format.str_d(content[37].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("倍率不能小于0！");
			}else{
				beilv = Format.str_d(content[37].toString().trim());
			}
		}
		return row;
	}
	
	/** 线损类型
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
				row.add("线损类型不存在！");
			}else{
				linelosstype = linelosstypecode;
			}
		}else{
			linelosstype = "";
		}
		return row;
	}
	
	/** 线损值 空， 数字 ，不能 小于0
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
				row.add("线损值格式不正确！");
			}else if(Format.str_d(content[40].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("线损值不能小于0！");
			}else{
				linelossvalue = content[40].toString().trim();
			}
		}else{
			linelossvalue = "";
		}
		return row;
	}
	
	/** 初始读数 空， 数字 ，不能 小于0
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
			row.add("初始读数不能为空！");
		}else{
			if(!Format.isNumber(content[41].toString().trim())){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("初始读数格式不正确！");
			}else if(Format.str_d(content[41].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("初始读数不能小于0！");
			}else{
				csds = Format.str_d(content[41].toString().trim());
			}
		}
		return row;
	}
	
	/** 初始使用时间  不能 为空 ，格式
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
			row.add("初始使用时间不能为空！");
		}else{
			if (!Format.isTime(content[42].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("初始使用时间格式不正确");
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
						row.add("导入失败！电表初始使用时间要大于等于2014-01-01!");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return row;
	}
	
	/** 变损电量 数字 ，不能 小于0
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
				row.add("变损电量格式不正确！");
			}else if(Format.str_d(content[44].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("变损电量不能小于0！");
			}else{
				bsdl = content[44].toString().trim();
			}
		}else{
			bsdl = "0";
		}
		return row;
	}
	
	/** 地域属性 存在 
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
				row.add("地域属性不存在！");
			}else{
				dytype = abc;
			}
		}else{
			dytype = "";
		}
		return row;
	}
	
	/** 宽带设备 空，正确
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check43(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[46].toString().trim())) {
			kdsb = "0";//默认无
		}else{
			if("有".equals(content[46].toString().trim())){
				kdsb = "1";
			}else if("无".equals(content[46].toString().trim())){
				kdsb = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("宽带设备内容填写错误！");
			}
		}
		return row;
	}
	
	/** 语音设备 空，正确
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check44(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[47].toString().trim())) {
			yysb = "0";//默认无
		}else{
			if("有".equals(content[47].toString().trim())){
				yysb = "1";
			}else if("无".equals(content[47].toString().trim())){
				yysb = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("语音设备内容填写错误！");
			}
		}
		return row;
	}
	/** 基站类型2 存在 
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
				row.add("基站类型2 不存在！");
			}else{
				jzlx2 = abc;
			}
		}else{
			jzlx2 = "";
		}
		return row;
	}
	
	/** 宽带端口实占数量  数字 ，不能 小于0
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
				row.add("宽带端口实占数量 格式不正确！");
			}else if(Format.str_d(content[49].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("宽带端口实占数量 不能小于0！");
			}else{
				kddkszsl = content[49].toString().trim();
			}
		}else{
			kddkszsl = "0";
		}
		return row;
	}
	/**语音端口实占数量 数字 ，不能 小于0
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
				row.add("语音端口实占数量格式不正确！");
			}else if(Format.str_d(content[50].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("语音端口实占数量不能小于0！");
			}else{
				yydkszsl = content[50].toString().trim();
			}
		}else{
			yydkszsl = "0";
		}
		return row;
	}
	/** 空调数  数字 ，不能 小于0
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
				row.add("空调数格式不正确！");
			}else if(Format.str_d(content[51].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("空调数不能小于0！");
			}else{
				kts = content[51].toString().trim();
			}
		}else{
			kts = "0";
		}
		return row;
	}
	/** 空调总功率  数字 ，不能 小于0
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
				row.add("空调总功率格式不正确！");
			}else if(Format.str_d(content[52].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("空调总功率不能小于0！");
			}else{
				ktzgl = content[52].toString().trim();
			}
		}else{
			ktzgl = "0";
		}
		return row;
	}
	/** 直供电 空，正确
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-22
	 */
	synchronized Vector<String> check50(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(content[53].toString().trim())) {
			zgd = "0";//默认否
		}else{
			if("是".equals(content[53].toString().trim())){
				zgd = "1";
			}else if("否".equals(content[53].toString().trim())){
				zgd = "0";
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("直供电内容填写错误！");
			}
		}
		return row;
	}
	/** 拉远bbu  数字 ，不能 小于0
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
				row.add("拉远bbu格式不正确！");
			}else if(Format.str_d(content[54].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("拉远bbu不能小于0！");
			}else{
				bbu = content[54].toString().trim();
			}
		}else{
			bbu = "0";
		}
		return row;
	}
	/** 远供rru  数字 ，不能 小于0
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
				row.add("远供rru格式不正确！");
			}else if(Format.str_d(content[55].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("远供rru不能小于0！");
			}else{
				rru = content[55].toString().trim();
			}
		}else{
			rru = "0";
		}
		return row;
	}
	/** 他网共享设备数量  数字 ，不能 小于0
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
				row.add("他网共享设备数量格式不正确！");
			}else if(Format.str_d(content[56].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("他网共享设备数量不能小于0！");
			}else{
				twgxsbsl = content[56].toString().trim();
			}
		}else{
			twgxsbsl = "0";
		}
		return row;
	}
	/** 固移共享设备数量  数字 ，不能 小于0
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
				row.add("固移共享设备数量格式不正确！");
			}else if(Format.str_d(content[57].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("固移共享设备数量不能小于0！");
			}else{
				gygxsbsl = content[57].toString().trim();
			}
		}else{
			gygxsbsl = "0";
		}
		return row;
	}
	/** 2G小区数量  数字 ，不能 小于0
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
				row.add("2G小区数量格式不正确！");
			}else if(Format.str_d(content[59].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("2G小区数量不能小于0！");
			}else{
				g2xqsl = content[59].toString().trim();
			}
		}else{
			g2xqsl = "0";
		}
		return row;
	}
	/** 3G扇区数量  数字 ，不能 小于0
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
				row.add("3G扇区数量 格式不正确！");
			}else if(Format.str_d(content[60].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("3G扇区数量 不能小于0！");
			}else{
				g3sqsl = content[60].toString().trim();
			}
		}else{
			g3sqsl = "0";
		}
		return row;
	}
	/** 移动共享设备数量  数字 ，不能 小于0
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
				row.add("移动共享设备数量格式不正确！");
			}else if(Format.str_d(content[61].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("移动共享设备数量不能小于0！");
			}else{
				ydgxsbsl = content[61].toString().trim();
			}
		}else{
			ydgxsbsl = "0";
		}
		return row;
	}
	/** 电信共享设备数量  数字 ，不能 小于0
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
				row.add("电信共享设备数量格式不正确！");
			}else if(Format.str_d(content[62].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("电信共享设备数量不能小于0！");
			}else{
				dxgxsbsl = content[62].toString().trim();
			}
		}else{
			dxgxsbsl = "0";
		}
		return row;
	}
	/** 饮水机台数  数字 ，不能 小于0
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
				row.add("饮水机台数格式不正确！");
			}else if(Format.str_d(content[63].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("饮水机台数不能小于0！");
			}else{
				ysjts = content[63].toString().trim();
			}
		}else{
			ysjts = "0";
		}
		return row;
	}
	/** 微机台数  数字 ，不能 小于0
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
				row.add("微机台数格式不正确！");
			}else if(Format.str_d(content[64].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("微机台数不能小于0！");
			}else{
				wjts = content[64].toString().trim();
			}
		}else{
			wjts = "0";
		}
		return row;
	}
	/** 营业办公空调台数  数字 ，不能 小于0
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
				row.add("营业办公空调台数格式不正确！");
			}else if(Format.str_d(content[65].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("营业办公空调台数不能小于0！");
			}else{
				yybgktts = content[65].toString().trim();
			}
		}else{
			yybgktts = "0";
		}
		return row;
	}
	/** 机房生产空调台数  数字 ，不能 小于0
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
				row.add("机房生产空调台数格式不正确！");
			}else if(Format.str_d(content[66].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("机房生产空调台数不能小于0！");
			}else{
				jfscktts = content[66].toString().trim();
			}
		}else{
			jfscktts = "0";
		}
		return row;
	}
	/** 空调一匹数  数字 ，不能 小于0
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
				row.add("空调一匹数 格式不正确！");
			}else if(Format.str_d(content[67].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("空调一匹数 不能小于0！");
			}else{
				ktyps = content[67].toString().trim();
			}
		}else{
			ktyps = "0";
		}
		return row;
	}
	/** 空调二匹数  数字 ，不能 小于0
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
				row.add("空调二匹数格式不正确！");
			}else if(Format.str_d(content[68].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("空调二匹数不能小于0！");
			}else{
				kteps = content[68].toString().trim();
			}
		}else{
			kteps = "0";
		}
		return row;
	}
	/** 空调匹数  数字 ，不能 小于0
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
				row.add("空调匹数格式不正确！");
			}else if(Format.str_d(content[69].toString().trim())<0){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("空调匹数不能小于0！");
			}else{
				ktps = content[69].toString().trim();
			}
		}else{
			ktps = "0";
		}
		return row;
	}
	
	/** 确定 基站类型1 ，接入网类型
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-9-23
	 */
	synchronized Vector<String> getJzlxAndJwrlx(Object[] content) {
		Vector<String> row = new Vector<String>();
		
		if("zdsx02".equals(property)){//基站
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
			}else{//用房类型 为 其他 或 空
				jzxlx="";
			}
		}else if("zdsx05".equals(property)){//接入网
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
			}else{//用房类型 为 其他 或 空
				jrwtype="";
			}
		}else{
			jzxlx="";
			jrwtype="";
		}
		return row;
	}

	/**
	 * 开始添加 相应的电费
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
		bumen = content[58].toString().trim();//部门
		zbdyhh = content[36].toString().trim();//电表自报电用户号
		zybyqlx= content[43].toString().trim();//自有变压器类型
		xmh = content[26].toString().trim();//项目号
		String sxd_a=content[content.length-4].toString().trim();
		String sxd_b=content[content.length-3].toString().trim();
		String sxd_c=content[content.length-2].toString().trim();
		String zldy=content[content.length-1].toString().trim();
		System.out.println(sxd_c+"sfsfsfsffffffffffffff");
		if(sxd_a==null||sxd_a=="")sxd_a="0";
		if(sxd_b==null||sxd_b=="")sxd_b="0";
		if(sxd_c==null||sxd_c=="")sxd_c="0";
		if(zldy==null||zldy=="")zldy="0";
		
		System.out.println(sxd_a+"  "+sxd_b+"  "+sxd_c+" "+zldy+"  wp;alaile来了");
		
		String bieming = "";
		String zdcode = "";
		String entrypensonnel=accountname;//录入人员
        String sydate = "";//投入使用时间
        String gsf="";//归属方
        //站点附属信息
        String kt1="0";//空调1
        String kt2="0";//空调2
        String twgx = "";//他网共享
        
        // 分摊
        String sc="100";//生成%  
        String bg="0";//办公%
        String yy="0";//营业%
        String xxhzc="0";//信息化支撑%
        String jstz="0";//建设投资%
        String dddf="0";//代垫电费%
        
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
		
		String watchcost = "";//套表计费
		String dbid = "";//电表ID
		String gldb = "on";//管理电表
		String dbyt = "dbyt01";//电表用途   
		String ysd= "0";//远受电
		
		
		
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
			row.add("添加失败！站点代码重复！");
		}else{
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add("Error！");
		}
		return row;
	}
	

}
