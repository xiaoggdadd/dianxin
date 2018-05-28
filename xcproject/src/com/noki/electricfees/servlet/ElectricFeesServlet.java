package com.noki.electricfees.servlet;

import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import jsx3.net.Request;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.daoruelectricfees.javabean.DaoruElectricfees;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonAccountBean;
import com.noki.mobi.sys.javabean.AccountBean;
import com.ptac.app.util.FileUploadUtil;
import com.ptac.app.util.ImportExcel;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class ElectricFeesServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		DbLog log = new DbLog();
		Account account = new Account();
		ElectricFeesFormBean formBean = new ElectricFeesFormBean();
		String url = path + "/web/electricfees/electricFeesList.jsp", msg = "";
		HttpSession session = req.getSession();
		account = (Account) session.getAttribute("account");

		ElectricFeesBean bean = new ElectricFeesBean();
		String action = req.getParameter("action");
		String status = "";
		String adbz = "";
		String deladfee = "";
		if (req.getParameter("status") != null) {
			status = req.getParameter("status");
		}
		if (req.getParameter("adbz") != null) {
			adbz = req.getParameter("adbz");
		}
		if (req.getParameter("deladfee") != null) {
			deladfee = req.getParameter("deladfee");
		}

		if (action.equals("add")) {
			if (adbz.equals("ad")) {
				url = path + "/web/dianliang/dianliangList.jsp";
			} else {
				url = path + "/web/electricfees/electricFeesList.jsp";
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());

			formBean.setAmmeterdegreeid(req.getParameter("ammeterdegreeid"));
			formBean.setUnitprice(req.getParameter("unitprice"));
			formBean.setFloatpay(req.getParameter("floatpay"));
			formBean.setActualpay(req.getParameter("actualpay"));
			formBean.setNotetypeid(req.getParameter("notetypeid"));
			System.out.println(req.getParameter("notetypeid"));
			formBean.setNoteno(req.getParameter("noteno"));
			formBean.setNotetime(req.getParameter("notetime"));
			formBean.setPayoperator(req.getParameter("payoperator"));
			formBean.setPaydatetime(req.getParameter("paydatetime"));
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setMemo(req.getParameter("memo"));
			formBean.setAmmererid(req.getParameter("ammererid"));
			formBean.setKptime(req.getParameter("kptime"));
			formBean.setEntrypensonnel(req.getParameter("accountname"));
			formBean.setEntrytime(entrytime);
			String start = req.getParameter("startmonth");
			String end = req.getParameter("endmonth");

			int flag = formBean.getFlag(formBean.getAmmeterdegreeid());
			formBean.setFlag(flag);
			System.out.println(start + "/*****" + end);
			msg = bean.addElectricFees(formBean, start, end);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"新增电费");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		} else if (action.equals("del")) {
			if (deladfee.equals("deladfee")) {
				url = path + "/web/electricfees/electricFeesInput.jsp";
			} else {
				url = path + "/web/electricfees/electricFeesList.jsp";
			}
			String degreeid = req.getParameter("degreeid");

			msg = bean.delElectricFees1(degreeid);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"删除电费");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		} else if (action.equals("delete")) {
			if (deladfee.equals("deladfee")) {
				url = path + "/web/electricfees/electricFeesInput.jsp";
			} else {
				url = path + "/web/electricfees/electricFeesList.jsp";
			}
			String degreeid = req.getParameter("degreeid");

			msg = bean.delElectricFees(degreeid);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"删除电费单");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		} else if (action.equals("delete1")) {
			if ("jiningnewdelete".equals(deladfee)) {
				url = path
						+ "/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBill.jsp";
			} else if (deladfee.equals("deladfee")) {
				url = path + "/web/electricfees/newelectricFeesInput.jsp";
			} else if ("newdelete".equals(deladfee)) {
				url = path
						+ "/web/appJSP/electricmanage/electricitybill/eleBill.jsp";
			} else {
				url = path + "/web/electricfees/newelectricFeesList.jsp";
			}
			String degreeid = req.getParameter("degreeid");

			msg = bean.delElectricFees(degreeid);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"删除电费单");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		} else if (action.equals("deletes")) {
			String chooseIdStr = req.getParameter("chooseIdStr");
			String biaozhi = req.getParameter("biaozhi");
			if ("jining".equals(biaozhi)) {
				url = path
						+ "/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBill.jsp";
			} else if ("xin".equals(biaozhi)) {
				url = path
						+ "/web/appJSP/electricmanage/electricitybill/eleBill.jsp";
			} else {
				url = path + "/web/electricfees/newelectricFeesInput.jsp";
			}
			msg = bean.deletesElectricFees(chooseIdStr);
			log.write(msg, account.getName(), req.getRemoteAddr(), "电费单批量删除");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
		} else if (action.equals("modify")) {
			String degreeid = req.getParameter("ammeterdegreeid");
			if (status.equals("qmodify")) {
				url = path + "/web/electricfees/electricFeesList.jsp?degreeid="
						+ degreeid;
				// url = path +
				// "/web/check/checkFeesAuto.jsp?degreeid="+degreeid;
			} else {
				url = path + "/web/electricfees/electricFeesList.jsp?degreeid="
						+ degreeid;
			}

			formBean = formBean.getElectricFeesInfo(degreeid);

			formBean
					.setAmmeterdegreeidFk(req.getParameter("ammeterdegreeidFk"));
			formBean.setUnitprice(req.getParameter("unitprice"));
			formBean.setFloatpay(req.getParameter("floatpay"));
			formBean.setActualpay(req.getParameter("actualpay"));
			formBean.setNotetypeid(req.getParameter("notetypeid"));
			formBean.setNoteno(req.getParameter("noteno"));
			formBean.setNotetime(req.getParameter("notetime"));
			formBean.setPayoperator(req.getParameter("payoperator"));
			formBean.setPaydatetime(req.getParameter("paydatetime"));
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setMemo(req.getParameter("memo"));
			formBean.setKptime(req.getParameter("kptime"));
			List ammeterdegreeid = new ArrayList();
			ammeterdegreeid = bean.getAmmeter(req
					.getParameter("ammeterdegreeidFk"));
			String month = bean.getMonth(req.getParameter("ammeterdegreeidFk"));
			String start = month.substring(0, 7);
			String end = month.substring(8, 15);
			System.out.println(ammeterdegreeid.toString());
			int flag = formBean.getFlag(ammeterdegreeid.get(0).toString());
			formBean.setFlag(flag);
			msg = bean.modifyElectricFees1(formBean, ammeterdegreeid, degreeid,
					start, end);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"修改电费");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		} else if (action.equals("showall")) {
			String degreeid = req.getParameter("ammeterdegreeid");
			url = path + "/web/electricfees/addElectricFeesAll.jsp?degreeid="
					+ degreeid;
			formBean = formBean.getElectricFeesInfo(degreeid);

			formBean
					.setAmmeterdegreeidFk(req.getParameter("ammeterdegreeidFk"));
			formBean.setUnitprice(req.getParameter("unitprice"));
			formBean.setFloatpay(req.getParameter("floatpay"));
			formBean.setActualpay(req.getParameter("actualpay"));
			formBean.setNotetypeid(req.getParameter("notetypeid"));
			formBean.setNoteno(req.getParameter("noteno"));
			formBean.setPayoperator(req.getParameter("payoperator"));
			formBean.setPaydatetime(req.getParameter("paydatetime"));
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setMemo(req.getParameter("memo"));

			List aa = new ArrayList();
			String bb = "", cc = "", id = "";
			msg = bean.modifyElectricFees(formBean, aa, bb, cc, id);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"电费管理");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}
		if (action.equals("addfd")) {// 入录电量电费

			// 设置日期格式
			String entrytime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			url = path + "/web/electricfees/adddianfeidannei.jsp";
			// 主键生成
			long uuid1 = System.currentTimeMillis();
			String uuid2 = Integer.toString((int) (100 + Math.random()
					* (999 - 100 + 1)));
			String uuid = uuid2 + Long.toString(uuid1).substring(3);
			System.out.println("主键生成:" + uuid);

			// 电量信息
			AmmeterDegreeBean beandegree = new AmmeterDegreeBean();
			AmmeterDegreeFormBean formBeandegree = new AmmeterDegreeFormBean();
			String sss = req.getParameter("pjje");// 票据金额：
			if ("".equals(sss) || null == sss || "null".equals(sss)) {
				sss = "0";// 页面票据金额为空 ，就赋值为 零
			}
			formBeandegree.setLastdf(req.getParameter("scdf"));// 上次电费
			formBeandegree.setLastdl(req.getParameter("scydl")); // 上次用电量
			formBeandegree.setScdj(req.getParameter("unitprice"));// 本次单价

			formBeandegree.setAmmeteridFk(req.getParameter("ammeteridFk"));// 将电表id保存到bean里面

			formBeandegree.setLastdegree(req.getParameter("lastdegree"));// 上次电表读数
			formBeandegree.setScydl(req.getParameter("scydl")); // 上次用电量
			formBeandegree.setSccbsj(req.getParameter("sccbsj"));
			formBeandegree.setBccbsj(req.getParameter("bccbsj"));
			formBeandegree.setLastdatetime(req.getParameter("lastdatetime"));// 上次抄表时间
			formBeandegree.setThisdegree(req.getParameter("thisdegree"));// 本次电表读数

			formBeandegree.setThisdatetime(req.getParameter("thisdatetime"));// 本次抄表时间
			formBeandegree.setInputoperator(req.getParameter("inputoperator"));// 抄表操作员
			formBeandegree.setInputdatetime(req.getParameter("inputdatetime"));//
			formBeandegree.setFloatdegree(!req.getParameter("floatdegree")
					.equals("") ? req.getParameter("floatdegree") : "0");
			formBeandegree.setActualdegree(req.getParameter("actualdegree"));
			formBeandegree.setStartmonth(req.getParameter("startmonth"));
			formBeandegree.setEndmonth(req.getParameter("endmonth"));
			formBeandegree.setMemo(req.getParameter("memoam"));
			formBeandegree.setBlhdl(req.getParameter("blhdl"));
			// 电量的五个分摊
			formBeandegree.setScdl(req.getParameter("scdl"));
			formBeandegree.setBgdl(req.getParameter("bgdl"));
			formBeandegree.setYydl(req.getParameter("yydl"));
			formBeandegree.setXxhdl(req.getParameter("xxhdl"));
			formBeandegree.setJstzdl(req.getParameter("jstzdl"));
			formBeandegree.setDddfdl(req.getParameter("dddfdl"));// 代垫电量

			formBeandegree.setEntrypensonnel(req.getParameter("accountid"));
			formBeandegree.setEntrytime(entrytime);
			String accountid = req.getParameter("accountid");
			String ad2_bz = req.getParameter("ad2_bz");
			formBeandegree.setAd2_bz(ad2_bz);
			;

			// ====================
			DataBase db = new DataBase();
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setActualpay(req.getParameter("actualpay"));

			String sql1 = "SELECT * FROM DIANDUVIEW dd,DIANFEIVIEW df WHERE THISDEGREE='"
					+ formBeandegree.getThisdegree()
					+ "' and  dd.ammeterdegreeid=df.ammeterdegreeid_fk  AND TO_CHAR(THISDATETIME,'yyyy-mm-dd')='"
					+ formBeandegree.getThisdatetime()
					+ "' AND LASTDEGREE='"
					+ formBeandegree.getLastdegree()
					+ "'AND TO_CHAR(LASTDATETIME,'yyyy-mm-dd')='"
					+ formBeandegree.getLastdatetime()
					+ "' AND AMMETERID_FK='"
					+ formBeandegree.getAmmeteridFk()
					+ "'AND TO_CHAR(ACCOUNTMONTH,'yyyy-mm-dd')='"
					+ formBean.getAccountmonth() + "'";
			System.out.println("查询语句===" + sql1);
			PreparedStatement ps = null;
			ResultSet rss = null;
			boolean fa = false;
			try {
				ps = db.getPreparedStatement(sql1);
				rss = ps.executeQuery();
				fa = rss.next();
				System.out.println("验证数据上次读表数是不重复" + fa);
			} catch (DbException e2) {
				e2.printStackTrace();
			} catch (SQLException ee) {
				ee.printStackTrace();
			} finally {
				try {
					db.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
			if (fa) {

				msg = "输入信息重复，请核实信息！";
			} else {
				String bzw = "1";
				msg = beandegree.addDegreeFees(formBeandegree, uuid, bzw);
				log.write(msg, accountid, req.getRemoteAddr(), "新增电量");

				// 通过主键获取电量id
				List ammeterdegreeid = new ArrayList();
				ammeterdegreeid = bean.getAmUuid(uuid);
				System.out.println("主键生成查询id:" + ammeterdegreeid);
				System.out.println("电量id："
						+ formBeandegree.getAmmeterdegreeid());
				// 电费信息
				// formBean.setAmmeterdegreeid(ammeterdegreeid);
				formBean.setUnitprice(req.getParameter("unitprice"));
				formBean.setFloatpay(!req.getParameter("floatpay").equals("") ? req
								.getParameter("floatpay")
								: "0");

				formBean.setNotetypeid(req.getParameter("notetypeid"));
				formBean.setPjje(Double.parseDouble(sss));
				// System.out.println(req.getParameter("notetypeid"));
				formBean.setNoteno(req.getParameter("noteno"));
				formBean.setNotetime(req.getParameter("notetime"));
				formBean.setPayoperator(req.getParameter("payoperator"));
				formBean.setPaydatetime(req.getParameter("paydatetime"));

				formBean.setMemo(req.getParameter("memo"));
				formBean.setAmmererid(req.getParameter("ammeteridFk"));
				formBean.setKptime(req.getParameter("kptime"));
				formBean.setEntrypensonnel(req.getParameter("accountid"));
				formBean.setEntrytime(entrytime);
				// 电费的分摊
				formBean.setScdff(req.getParameter("scdff"));
				formBean.setBgdf(req.getParameter("bgdf"));
				formBean.setYydf(req.getParameter("yydf"));
				formBean.setXxhdf(req.getParameter("xxhdf"));
				formBean.setJstzdf(req.getParameter("jstzdf"));
				formBean.setDddfdf(req.getParameter("dddfdf"));// 代垫电费
				formBean.setJszq(req.getParameter("days"));// ffffffffffffffffffff
				String start = req.getParameter("startmonth");
				String end = req.getParameter("endmonth");
				// 查询刚刚插入的电量表,取电量表中flag字段，来判断电费是否需要市级审核
				int flag = formBean.getFlag(ammeterdegreeid.get(0).toString());
				// 查询刚刚插入电量表 中自动审核状态标志
				String autoauditstatus = formBean.getZdshbz(ammeterdegreeid
						.get(0).toString());
				System.out
						.println("dianfei_flag电量是否需要市级审核标识-------------------------------->>>>"
								+ flag);
				System.out
						.println("AUTOAUDITSTATUS电量自动审核状态标识-------------------------------->>>>"
								+ autoauditstatus);
				formBean.setFlag(flag);
				formBean.setAutoauditstatus(autoauditstatus);//将刚刚电量表的自动审核状态 赋给电费表
				msg = bean.addFeesDegree(formBean, uuid, start, end,
						ammeterdegreeid, bzw);//增加电费
				log.write(msg, accountid, req.getRemoteAddr(), "新增电费");
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}
		if (action.equals("modifyadfee")) {
			String id = req.getParameter("id");
			String degreeid = req.getParameter("ammeterdegreeid");
			if (status.equals("qmodify")) {
				// url = path +
				// "/web/check/checkFeesAuto.jsp?degreeid="+degreeid;
				url = path
						+ "/web/electricfees/electricFeesInput.jsp?degreeid="
						+ degreeid;
			} else {
				url = path
						+ "/web/electricfees/electricFeesInput.jsp?degreeid="
						+ degreeid;
			}
			String uuid = UUID.randomUUID().toString().replace("-", "");
			// 电量
			String ad2_bz = req.getParameter("ad2_bz");
			AmmeterDegreeBean beandegree = new AmmeterDegreeBean();
			AmmeterDegreeFormBean formBeandegree = new AmmeterDegreeFormBean();

			formBeandegree.setAd2_bz(ad2_bz);
			formBeandegree.setAmmeterdegreeid(degreeid);
			formBeandegree.setAmmeteridFk(req.getParameter("ammeteridFk"));
			formBeandegree.setLastdegree(req.getParameter("lastdegree"));
			formBeandegree.setLastdatetime(req.getParameter("lastdatetime"));
			formBeandegree.setThisdegree(req.getParameter("thisdegree"));
			formBeandegree.setThisdatetime(req.getParameter("thisdatetime"));
			formBeandegree.setInputoperator(req.getParameter("inputoperator"));
			formBeandegree.setInputdatetime(req.getParameter("inputdatetime"));
			// formBeandegree.setFloatdegree(req.getParameter("floatdegree"));
			formBeandegree.setFloatdegree(!req.getParameter("floatdegree")
					.equals("") ? req.getParameter("floatdegree") : "0");
			formBeandegree.setActualdegree(req.getParameter("actualdegree"));
			formBeandegree.setStartmonth(req.getParameter("startmonth"));
			formBeandegree.setEndmonth(req.getParameter("endmonth"));
			formBeandegree.setMemo(req.getParameter("memo"));
			formBeandegree.setAutoauditstatus(req
					.getParameter("autoauditstatus"));
			formBeandegree.setAutoauditdescription(req
					.getParameter("autoauditdescription"));
			formBeandegree.setBlhdl(req.getParameter("blhdl"));

			msg = beandegree.modify(formBeandegree, uuid, id);
			log.write(msg, formBeandegree.getAmmeterdegreeid(), req
					.getRemoteAddr(), "修改电量");
			// 电费

			String start = req.getParameter("startmonth");
			String end = req.getParameter("endmonth");
			formBean = formBean.getElectricDegreeFeesInfo(degreeid);
			List ammeterdegreeid = new ArrayList();
			ammeterdegreeid = bean.getAmUuid(uuid);
			System.out.println("主键生成查询id:" + ammeterdegreeid);
			formBean
					.setAmmeterdegreeidFk(req.getParameter("ammeterdegreeidFk"));
			formBean.setUnitprice(req.getParameter("unitprice"));
			formBean.setFloatpay(!req.getParameter("floatpay").equals("") ? req
					.getParameter("floatpay") : "0");
			// formBean.setFloatpay(req.getParameter("floatpay"));
			formBean.setActualpay(req.getParameter("actualpay"));
			formBean.setNotetypeid(req.getParameter("notetypeid"));
			formBean.setNoteno(req.getParameter("noteno"));
			formBean.setPayoperator(req.getParameter("payoperator"));
			formBean.setPaydatetime(req.getParameter("paydatetime"));
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setMemo(req.getParameter("memo"));
			formBean.setKptime(req.getParameter("kptime"));
			formBean.setNotetime(req.getParameter("notetime"));
			int flag = formBean.getFlag(ammeterdegreeid.get(0).toString());
			System.out.println("flag" + flag);
			formBean.setFlag(flag);
			msg = bean.modifyElectricFees(formBean, ammeterdegreeid, start,
					end, id);
			log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(),
					"修改电费");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}

		if (action.equals("modifyadfee1")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());
			String id = req.getParameter("id");
			String degreeid = req.getParameter("ammeterdegreeid");
			String biaozhi = req.getParameter("biaozhi");
			if (biaozhi == "newpage" || biaozhi.equals("newpage")) {
				url = path
						+ "/web/appJSP/electricmanage/electricitybill/eleBill.jsp?degreeid="
						+ degreeid;
			} else {
				if (status.equals("qmodify")) {
					// url = path +
					// "/web/check/checkFeesAuto.jsp?degreeid="+degreeid;
					url = path
							+ "/web/electricfees/newelectricFeesInput.jsp?degreeid="
							+ degreeid;
				} else {
					url = path
							+ "/web/electricfees/newelectricFeesInput.jsp?degreeid="
							+ degreeid;
				}
			}
			// String uuid = UUID.randomUUID().toString().replace("-","");
			long uuid1 = System.currentTimeMillis();
			String uuid2 = Integer.toString((int) (100 + Math.random()
					* (999 - 100 + 1)));
			String uuid = uuid2 + Long.toString(uuid1).substring(3);
			// 电量
			String ad2_bz = req.getParameter("ad2_bz");
			AmmeterDegreeBean beandegree = new AmmeterDegreeBean();
			AmmeterDegreeFormBean formBeandegree = new AmmeterDegreeFormBean();

			formBeandegree.setAd2_bz(ad2_bz);
			formBeandegree.setAmmeterdegreeid(degreeid);
			formBeandegree.setAmmeteridFk(req.getParameter("ammeteridFk"));
			formBeandegree.setLastdegree(req.getParameter("lastdegree"));
			formBeandegree.setLastdatetime(req.getParameter("lastdatetime"));
			formBeandegree.setThisdegree(req.getParameter("thisdegree"));
			formBeandegree.setThisdatetime(req.getParameter("thisdatetime"));
			formBeandegree.setInputoperator(req.getParameter("inputoperator"));
			formBeandegree.setInputdatetime(req.getParameter("inputdatetime"));
			// formBeandegree.setFloatdegree(req.getParameter("floatdegree"));
			formBeandegree.setFloatdegree(!req.getParameter("floatdegree")
					.equals("") ? req.getParameter("floatdegree") : "0");
			formBeandegree.setActualdegree(req.getParameter("actualdegree"));
			formBeandegree.setStartmonth(req.getParameter("startmonth"));
			formBeandegree.setEndmonth(req.getParameter("endmonth"));
			formBeandegree.setMemo(req.getParameter("memoam"));
			formBeandegree.setAutoauditstatus(req
					.getParameter("autoauditstatus"));
			formBeandegree.setAutoauditdescription(req
					.getParameter("autoauditdescription"));
			formBeandegree.setBlhdl(req.getParameter("blhdl"));
			formBeandegree.setEntrypensonnel(req.getParameter("accountid"));
			formBeandegree.setEntrytime(entrytime);
			// 电量的五个分摊
			formBeandegree.setScdl(req.getParameter("scdl"));
			formBeandegree.setBgdl(req.getParameter("bgdl"));
			formBeandegree.setYydl(req.getParameter("yydl"));
			formBeandegree.setXxhdl(req.getParameter("xxhdl"));
			formBeandegree.setJstzdl(req.getParameter("jstzdl"));
			formBeandegree.setDddfdl(req.getParameter("dddfdl"));// 代垫电量
			String accountid = req.getParameter("accountid");
			msg = beandegree.modify(formBeandegree, uuid, id);
			log.write(msg, accountid, req.getRemoteAddr(), "修改电量");
			// 电费
			String pjj = req.getParameter("pjje");
			if ("".equals(pjj) || null == pjj || "null".equals(pjj)) {
				pjj = "0";
			}
			String start = req.getParameter("startmonth");
			String end = req.getParameter("endmonth");
			formBean = formBean.getElectricDegreeFeesInfo(degreeid);
			List ammeterdegreeid = new ArrayList();
			ammeterdegreeid = bean.getAmUuid(uuid);
			System.out.println("主键生成查询id:" + ammeterdegreeid);
			formBean
					.setAmmeterdegreeidFk(req.getParameter("ammeterdegreeidFk"));
			formBean.setUnitprice(req.getParameter("unitprice"));
			formBean.setFloatpay(!req.getParameter("floatpay").equals("") ? req
					.getParameter("floatpay") : "0");
			// formBean.setFloatpay(req.getParameter("floatpay"));
			formBean.setPjje(Double.parseDouble(pjj));
			formBean.setActualpay(req.getParameter("actualpay"));
			formBean.setNotetypeid(req.getParameter("notetypeid"));
			formBean.setNoteno(req.getParameter("noteno"));
			formBean.setPayoperator(req.getParameter("payoperator"));
			formBean.setPaydatetime(req.getParameter("paydatetime"));
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setMemo(req.getParameter("memo"));
			formBean.setKptime(req.getParameter("kptime"));
			formBean.setNotetime(req.getParameter("notetime"));
			formBean.setEntrypensonnel(req.getParameter("accountid"));
			formBean.setEntrytime(entrytime);
			formBean.setDfuuid(uuid);
			// 电费的分摊
			formBean.setScdff(req.getParameter("scdff"));
			formBean.setBgdf(req.getParameter("bgdf"));
			formBean.setYydf(req.getParameter("yydf"));
			formBean.setXxhdf(req.getParameter("xxhdf"));
			formBean.setJstzdf(req.getParameter("jstzdf"));
			formBean.setDddfdf(req.getParameter("dddfdf"));// 代垫电费
			formBean.setJszq(req.getParameter("days"));// ddddddddddddddd
			int flag = formBean.getFlag(ammeterdegreeid.get(0).toString());
			System.out.println("flag" + flag);
			formBean.setFlag(flag);
			// 取电量表中自动审核标志
			String autoauditstatus = formBean.getZdshbz(ammeterdegreeid.get(0)
					.toString());
			formBean.setAutoauditstatus(autoauditstatus);
			msg = bean.modifyElectricFees(formBean, ammeterdegreeid, start,
					end, id);
			log.write(msg, accountid, req.getRemoteAddr(), "修改电费");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}
		if (action.equals("qxdayin")) {
			String personnal = account.getAccountName();// 取消打印人员
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);// 取消打印时间
			String dybzw = req.getParameter("dybzw");// 打印标志位（1--电费单打印 2--预付费打印）
			String chooseIdStr1 = req.getParameter("chooseIdStr1");// 获取uuid字符串
			String chooseIdStr2 = req.getParameter("chooseIdStr2");
			if (dybzw.equals("1")) {
				url = path + "/web/electricfees/dianfeidanquery.jsp";// 跳转回主页面
			} else if (dybzw.equals("2")) {
				url = path + "/web/electricfees/yufufeiquery.jsp";// 跳转回主页面
			}
			formBean.setDayintime(dateString);// 取消打印时间
			formBean.setDayinren(personnal);// 取消打印人

			msg = bean.qxdfddy(formBean, chooseIdStr1, chooseIdStr2);
			log.write(msg, account.getName(), req.getRemoteAddr(), "取消电费单打印");

			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
