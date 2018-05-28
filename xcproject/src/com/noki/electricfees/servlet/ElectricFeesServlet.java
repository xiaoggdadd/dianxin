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
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
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
					"�������");
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
					"ɾ�����");
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
					"ɾ����ѵ�");
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
					"ɾ����ѵ�");
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
			log.write(msg, account.getName(), req.getRemoteAddr(), "��ѵ�����ɾ��");
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
					"�޸ĵ��");
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
					"��ѹ���");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}
		if (action.equals("addfd")) {// ��¼�������

			// �������ڸ�ʽ
			String entrytime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			url = path + "/web/electricfees/adddianfeidannei.jsp";
			// ��������
			long uuid1 = System.currentTimeMillis();
			String uuid2 = Integer.toString((int) (100 + Math.random()
					* (999 - 100 + 1)));
			String uuid = uuid2 + Long.toString(uuid1).substring(3);
			System.out.println("��������:" + uuid);

			// ������Ϣ
			AmmeterDegreeBean beandegree = new AmmeterDegreeBean();
			AmmeterDegreeFormBean formBeandegree = new AmmeterDegreeFormBean();
			String sss = req.getParameter("pjje");// Ʊ�ݽ�
			if ("".equals(sss) || null == sss || "null".equals(sss)) {
				sss = "0";// ҳ��Ʊ�ݽ��Ϊ�� ���͸�ֵΪ ��
			}
			formBeandegree.setLastdf(req.getParameter("scdf"));// �ϴε��
			formBeandegree.setLastdl(req.getParameter("scydl")); // �ϴ��õ���
			formBeandegree.setScdj(req.getParameter("unitprice"));// ���ε���

			formBeandegree.setAmmeteridFk(req.getParameter("ammeteridFk"));// �����id���浽bean����

			formBeandegree.setLastdegree(req.getParameter("lastdegree"));// �ϴε�����
			formBeandegree.setScydl(req.getParameter("scydl")); // �ϴ��õ���
			formBeandegree.setSccbsj(req.getParameter("sccbsj"));
			formBeandegree.setBccbsj(req.getParameter("bccbsj"));
			formBeandegree.setLastdatetime(req.getParameter("lastdatetime"));// �ϴγ���ʱ��
			formBeandegree.setThisdegree(req.getParameter("thisdegree"));// ���ε�����

			formBeandegree.setThisdatetime(req.getParameter("thisdatetime"));// ���γ���ʱ��
			formBeandegree.setInputoperator(req.getParameter("inputoperator"));// �������Ա
			formBeandegree.setInputdatetime(req.getParameter("inputdatetime"));//
			formBeandegree.setFloatdegree(!req.getParameter("floatdegree")
					.equals("") ? req.getParameter("floatdegree") : "0");
			formBeandegree.setActualdegree(req.getParameter("actualdegree"));
			formBeandegree.setStartmonth(req.getParameter("startmonth"));
			formBeandegree.setEndmonth(req.getParameter("endmonth"));
			formBeandegree.setMemo(req.getParameter("memoam"));
			formBeandegree.setBlhdl(req.getParameter("blhdl"));
			// �����������̯
			formBeandegree.setScdl(req.getParameter("scdl"));
			formBeandegree.setBgdl(req.getParameter("bgdl"));
			formBeandegree.setYydl(req.getParameter("yydl"));
			formBeandegree.setXxhdl(req.getParameter("xxhdl"));
			formBeandegree.setJstzdl(req.getParameter("jstzdl"));
			formBeandegree.setDddfdl(req.getParameter("dddfdl"));// �������

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
			System.out.println("��ѯ���===" + sql1);
			PreparedStatement ps = null;
			ResultSet rss = null;
			boolean fa = false;
			try {
				ps = db.getPreparedStatement(sql1);
				rss = ps.executeQuery();
				fa = rss.next();
				System.out.println("��֤�����ϴζ������ǲ��ظ�" + fa);
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

				msg = "������Ϣ�ظ������ʵ��Ϣ��";
			} else {
				String bzw = "1";
				msg = beandegree.addDegreeFees(formBeandegree, uuid, bzw);
				log.write(msg, accountid, req.getRemoteAddr(), "��������");

				// ͨ��������ȡ����id
				List ammeterdegreeid = new ArrayList();
				ammeterdegreeid = bean.getAmUuid(uuid);
				System.out.println("�������ɲ�ѯid:" + ammeterdegreeid);
				System.out.println("����id��"
						+ formBeandegree.getAmmeterdegreeid());
				// �����Ϣ
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
				// ��ѵķ�̯
				formBean.setScdff(req.getParameter("scdff"));
				formBean.setBgdf(req.getParameter("bgdf"));
				formBean.setYydf(req.getParameter("yydf"));
				formBean.setXxhdf(req.getParameter("xxhdf"));
				formBean.setJstzdf(req.getParameter("jstzdf"));
				formBean.setDddfdf(req.getParameter("dddfdf"));// ������
				formBean.setJszq(req.getParameter("days"));// ffffffffffffffffffff
				String start = req.getParameter("startmonth");
				String end = req.getParameter("endmonth");
				// ��ѯ�ող���ĵ�����,ȡ��������flag�ֶΣ����жϵ���Ƿ���Ҫ�м����
				int flag = formBean.getFlag(ammeterdegreeid.get(0).toString());
				// ��ѯ�ող�������� ���Զ����״̬��־
				String autoauditstatus = formBean.getZdshbz(ammeterdegreeid
						.get(0).toString());
				System.out
						.println("dianfei_flag�����Ƿ���Ҫ�м���˱�ʶ-------------------------------->>>>"
								+ flag);
				System.out
						.println("AUTOAUDITSTATUS�����Զ����״̬��ʶ-------------------------------->>>>"
								+ autoauditstatus);
				formBean.setFlag(flag);
				formBean.setAutoauditstatus(autoauditstatus);//���ոյ�������Զ����״̬ ������ѱ�
				msg = bean.addFeesDegree(formBean, uuid, start, end,
						ammeterdegreeid, bzw);//���ӵ��
				log.write(msg, accountid, req.getRemoteAddr(), "�������");
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
			// ����
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
					.getRemoteAddr(), "�޸ĵ���");
			// ���

			String start = req.getParameter("startmonth");
			String end = req.getParameter("endmonth");
			formBean = formBean.getElectricDegreeFeesInfo(degreeid);
			List ammeterdegreeid = new ArrayList();
			ammeterdegreeid = bean.getAmUuid(uuid);
			System.out.println("�������ɲ�ѯid:" + ammeterdegreeid);
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
					"�޸ĵ��");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}

		if (action.equals("modifyadfee1")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
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
			// ����
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
			// �����������̯
			formBeandegree.setScdl(req.getParameter("scdl"));
			formBeandegree.setBgdl(req.getParameter("bgdl"));
			formBeandegree.setYydl(req.getParameter("yydl"));
			formBeandegree.setXxhdl(req.getParameter("xxhdl"));
			formBeandegree.setJstzdl(req.getParameter("jstzdl"));
			formBeandegree.setDddfdl(req.getParameter("dddfdl"));// �������
			String accountid = req.getParameter("accountid");
			msg = beandegree.modify(formBeandegree, uuid, id);
			log.write(msg, accountid, req.getRemoteAddr(), "�޸ĵ���");
			// ���
			String pjj = req.getParameter("pjje");
			if ("".equals(pjj) || null == pjj || "null".equals(pjj)) {
				pjj = "0";
			}
			String start = req.getParameter("startmonth");
			String end = req.getParameter("endmonth");
			formBean = formBean.getElectricDegreeFeesInfo(degreeid);
			List ammeterdegreeid = new ArrayList();
			ammeterdegreeid = bean.getAmUuid(uuid);
			System.out.println("�������ɲ�ѯid:" + ammeterdegreeid);
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
			// ��ѵķ�̯
			formBean.setScdff(req.getParameter("scdff"));
			formBean.setBgdf(req.getParameter("bgdf"));
			formBean.setYydf(req.getParameter("yydf"));
			formBean.setXxhdf(req.getParameter("xxhdf"));
			formBean.setJstzdf(req.getParameter("jstzdf"));
			formBean.setDddfdf(req.getParameter("dddfdf"));// ������
			formBean.setJszq(req.getParameter("days"));// ddddddddddddddd
			int flag = formBean.getFlag(ammeterdegreeid.get(0).toString());
			System.out.println("flag" + flag);
			formBean.setFlag(flag);
			// ȡ���������Զ���˱�־
			String autoauditstatus = formBean.getZdshbz(ammeterdegreeid.get(0)
					.toString());
			formBean.setAutoauditstatus(autoauditstatus);
			msg = bean.modifyElectricFees(formBean, ammeterdegreeid, start,
					end, id);
			log.write(msg, accountid, req.getRemoteAddr(), "�޸ĵ��");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}
		if (action.equals("qxdayin")) {
			String personnal = account.getAccountName();// ȡ����ӡ��Ա
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);// ȡ����ӡʱ��
			String dybzw = req.getParameter("dybzw");// ��ӡ��־λ��1--��ѵ���ӡ 2--Ԥ���Ѵ�ӡ��
			String chooseIdStr1 = req.getParameter("chooseIdStr1");// ��ȡuuid�ַ���
			String chooseIdStr2 = req.getParameter("chooseIdStr2");
			if (dybzw.equals("1")) {
				url = path + "/web/electricfees/dianfeidanquery.jsp";// ��ת����ҳ��
			} else if (dybzw.equals("2")) {
				url = path + "/web/electricfees/yufufeiquery.jsp";// ��ת����ҳ��
			}
			formBean.setDayintime(dateString);// ȡ����ӡʱ��
			formBean.setDayinren(personnal);// ȡ����ӡ��

			msg = bean.qxdfddy(formBean, chooseIdStr1, chooseIdStr2);
			log.write(msg, account.getName(), req.getRemoteAddr(), "ȡ����ѵ���ӡ");

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
