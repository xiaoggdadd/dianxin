package com.noki.newfunction.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.KtxsDao;
import com.noki.newfunction.javabean.Ktxs;

public class KtxsServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		String loginName = account.getAccountName();
		DbLog log = new DbLog();
		String action = request.getParameter("action");
		String url = "";
		String msg = "";

		if (action.equals("addKtxs")) {
			url = path + "/web/newgn/ktxs.jsp";
			String jn1 = request.getParameter("jn1");
			String jn2 = request.getParameter("jn2");
			String jn3 = request.getParameter("jn3");
			String jn4 = request.getParameter("jn4");
			String jn5 = request.getParameter("jn5");
			String jn6 = request.getParameter("jn6");
			String jn7 = request.getParameter("jn7");
			String jn8 = request.getParameter("jn8");
			String jn9 = request.getParameter("jn9");
			String jn10 = request.getParameter("jn10");
			String jn11 = request.getParameter("jn11");
			String jn12 = request.getParameter("jn12");

			String qd1 = request.getParameter("qd1");
			String qd2 = request.getParameter("qd2");
			String qd3 = request.getParameter("qd3");
			String qd4 = request.getParameter("qd4");
			String qd5 = request.getParameter("qd5");
			String qd6 = request.getParameter("qd6");
			String qd7 = request.getParameter("qd7");
			String qd8 = request.getParameter("qd8");
			String qd9 = request.getParameter("qd9");
			String qd10 = request.getParameter("qd10");
			String qd11 = request.getParameter("qd11");
			String qd12 = request.getParameter("qd12");

			String zb1 = request.getParameter("zb1");
			String zb2 = request.getParameter("zb2");
			String zb3 = request.getParameter("zb3");
			String zb4 = request.getParameter("zb4");
			String zb5 = request.getParameter("zb5");
			String zb6 = request.getParameter("zb6");
			String zb7 = request.getParameter("zb7");
			String zb8 = request.getParameter("zb8");
			String zb9 = request.getParameter("zb9");
			String zb10 = request.getParameter("zb10");
			String zb11 = request.getParameter("zb11");
			String zb12 = request.getParameter("zb12");

			String zz1 = request.getParameter("zz1");
			String zz2 = request.getParameter("zz2");
			String zz3 = request.getParameter("zz3");
			String zz4 = request.getParameter("zz4");
			String zz5 = request.getParameter("zz5");
			String zz6 = request.getParameter("zz6");
			String zz7 = request.getParameter("zz7");
			String zz8 = request.getParameter("zz8");
			String zz9 = request.getParameter("zz9");
			String zz10 = request.getParameter("zz10");
			String zz11 = request.getParameter("zz11");
			String zz12 = request.getParameter("zz12");

			String dy1 = request.getParameter("dy1");
			String dy2 = request.getParameter("dy2");
			String dy3 = request.getParameter("dy3");
			String dy4 = request.getParameter("dy4");
			String dy5 = request.getParameter("dy5");
			String dy6 = request.getParameter("dy6");
			String dy7 = request.getParameter("dy7");
			String dy8 = request.getParameter("dy8");
			String dy9 = request.getParameter("dy9");
			String dy10 = request.getParameter("dy10");
			String dy11 = request.getParameter("dy11");
			String dy12 = request.getParameter("dy12");

			String yt1 = request.getParameter("yt1");
			String yt2 = request.getParameter("yt2");
			String yt3 = request.getParameter("yt3");
			String yt4 = request.getParameter("yt4");
			String yt5 = request.getParameter("yt5");
			String yt6 = request.getParameter("yt6");
			String yt7 = request.getParameter("yt7");
			String yt8 = request.getParameter("yt8");
			String yt9 = request.getParameter("yt9");
			String yt10 = request.getParameter("yt10");
			String yt11 = request.getParameter("yt11");
			String yt12 = request.getParameter("yt12");

			String wf1 = request.getParameter("wf1");
			String wf2 = request.getParameter("wf2");
			String wf3 = request.getParameter("wf3");
			String wf4 = request.getParameter("wf4");
			String wf5 = request.getParameter("wf5");
			String wf6 = request.getParameter("wf6");
			String wf7 = request.getParameter("wf7");
			String wf8 = request.getParameter("wf8");
			String wf9 = request.getParameter("wf9");
			String wf10 = request.getParameter("wf10");
			String wf11 = request.getParameter("wf11");
			String wf12 = request.getParameter("wf12");

			String Jn1 = request.getParameter("Jn1");
			String Jn2 = request.getParameter("Jn2");
			String Jn3 = request.getParameter("Jn3");
			String Jn4 = request.getParameter("Jn4");
			String Jn5 = request.getParameter("Jn5");
			String Jn6 = request.getParameter("Jn6");
			String Jn7 = request.getParameter("Jn7");
			String Jn8 = request.getParameter("Jn8");
			String Jn9 = request.getParameter("Jn9");
			String Jn10 = request.getParameter("Jn10");
			String Jn11 = request.getParameter("Jn11");
			String Jn12 = request.getParameter("Jn12");

			String ta1 = request.getParameter("ta1");
			String ta2 = request.getParameter("ta2");
			String ta3 = request.getParameter("ta3");
			String ta4 = request.getParameter("ta4");
			String ta5 = request.getParameter("ta5");
			String ta6 = request.getParameter("ta6");
			String ta7 = request.getParameter("ta7");
			String ta8 = request.getParameter("ta8");
			String ta9 = request.getParameter("ta9");
			String ta10 = request.getParameter("ta10");
			String ta11 = request.getParameter("ta11");
			String ta12 = request.getParameter("ta12");

			String wh1 = request.getParameter("wh1");
			String wh2 = request.getParameter("wh2");
			String wh3 = request.getParameter("wh3");
			String wh4 = request.getParameter("wh4");
			String wh5 = request.getParameter("wh5");
			String wh6 = request.getParameter("wh6");
			String wh7 = request.getParameter("wh7");
			String wh8 = request.getParameter("wh8");
			String wh9 = request.getParameter("wh9");
			String wh10 = request.getParameter("wh10");
			String wh11 = request.getParameter("wh11");
			String wh12 = request.getParameter("wh12");

			String rz1 = request.getParameter("rz1");
			String rz2 = request.getParameter("rz2");
			String rz3 = request.getParameter("rz3");
			String rz4 = request.getParameter("rz4");
			String rz5 = request.getParameter("rz5");
			String rz6 = request.getParameter("rz6");
			String rz7 = request.getParameter("rz7");
			String rz8 = request.getParameter("rz8");
			String rz9 = request.getParameter("rz9");
			String rz10 = request.getParameter("rz10");
			String rz11 = request.getParameter("rz11");
			String rz12 = request.getParameter("rz12");

			String lw1 = request.getParameter("lw1");
			String lw2 = request.getParameter("lw2");
			String lw3 = request.getParameter("lw3");
			String lw4 = request.getParameter("lw4");
			String lw5 = request.getParameter("lw5");
			String lw6 = request.getParameter("lw6");
			String lw7 = request.getParameter("lw7");
			String lw8 = request.getParameter("lw8");
			String lw9 = request.getParameter("lw9");
			String lw10 = request.getParameter("lw10");
			String lw11 = request.getParameter("lw11");
			String lw12 = request.getParameter("lw12");

			String ly1 = request.getParameter("ly1");
			String ly2 = request.getParameter("ly2");
			String ly3 = request.getParameter("ly3");
			String ly4 = request.getParameter("ly4");
			String ly5 = request.getParameter("ly5");
			String ly6 = request.getParameter("ly6");
			String ly7 = request.getParameter("ly7");
			String ly8 = request.getParameter("ly8");
			String ly9 = request.getParameter("ly9");
			String ly10 = request.getParameter("ly10");
			String ly11 = request.getParameter("ly11");
			String ly12 = request.getParameter("ly12");

			String dz1 = request.getParameter("dz1");
			String dz2 = request.getParameter("dz2");
			String dz3 = request.getParameter("dz3");
			String dz4 = request.getParameter("dz4");
			String dz5 = request.getParameter("dz5");
			String dz6 = request.getParameter("dz6");
			String dz7 = request.getParameter("dz7");
			String dz8 = request.getParameter("dz8");
			String dz9 = request.getParameter("dz9");
			String dz10 = request.getParameter("dz10");
			String dz11 = request.getParameter("dz11");
			String dz12 = request.getParameter("dz12");

			String lc1 = request.getParameter("lc1");
			String lc2 = request.getParameter("lc2");
			String lc3 = request.getParameter("lc3");
			String lc4 = request.getParameter("lc4");
			String lc5 = request.getParameter("lc5");
			String lc6 = request.getParameter("lc6");
			String lc7 = request.getParameter("lc7");
			String lc8 = request.getParameter("lc8");
			String lc9 = request.getParameter("lc9");
			String lc10 = request.getParameter("lc10");
			String lc11 = request.getParameter("lc11");
			String lc12 = request.getParameter("lc12");

			String bz1 = request.getParameter("bz1");
			String bz2 = request.getParameter("bz2");
			String bz3 = request.getParameter("bz3");
			String bz4 = request.getParameter("bz4");
			String bz5 = request.getParameter("bz5");
			String bz6 = request.getParameter("bz6");
			String bz7 = request.getParameter("bz7");
			String bz8 = request.getParameter("bz8");
			String bz9 = request.getParameter("bz9");
			String bz10 = request.getParameter("bz10");
			String bz11 = request.getParameter("bz11");
			String bz12 = request.getParameter("bz12");

			String hz1 = request.getParameter("hz1");
			String hz2 = request.getParameter("hz2");
			String hz3 = request.getParameter("hz3");
			String hz4 = request.getParameter("hz4");
			String hz5 = request.getParameter("hz5");
			String hz6 = request.getParameter("hz6");
			String hz7 = request.getParameter("hz7");
			String hz8 = request.getParameter("hz8");
			String hz9 = request.getParameter("hz9");
			String hz10 = request.getParameter("hz10");
			String hz11 = request.getParameter("hz11");
			String hz12 = request.getParameter("hz12");

			String kszlfh1 = request.getParameter("kszlfh1");
			String jszlfh1 = request.getParameter("jszlfh1");
			String jz1 = request.getParameter("jz1");
			String jrw1 = request.getParameter("jrw1");
			String xzzj1 = request.getParameter("xzzj1");
			String jyjf1 = request.getParameter("jyjf1");
			String qt1 = request.getParameter("qt1");
			String idcjf1 = request.getParameter("idcjf1");

			String kszlfh2 = request.getParameter("kszlfh2");
			String jszlfh2 = request.getParameter("jszlfh2");
			String jz2 = request.getParameter("jz2");
			String jrw2 = request.getParameter("jrw2");
			String xzzj2 = request.getParameter("xzzj2");
			String jyjf2 = request.getParameter("jyjf2");
			String qt2 = request.getParameter("qt2");
			String idcjf2 = request.getParameter("idcjf2");

			String kszlfh3 = request.getParameter("kszlfh3");
			String jszlfh3 = request.getParameter("jszlfh3");
			String jz3 = request.getParameter("jz3");
			String jrw3 = request.getParameter("jrw3");
			String xzzj3 = request.getParameter("xzzj3");
			String jyjf3 = request.getParameter("jyjf3");
			String qt3 = request.getParameter("qt3");
			String idcjf3 = request.getParameter("idcjf3");

			String kszlfh4 = request.getParameter("kszlfh4");
			String jszlfh4 = request.getParameter("jszlfh4");
			String jz4 = request.getParameter("jz4");
			String jrw4 = request.getParameter("jrw4");
			String xzzj4 = request.getParameter("xzzj4");
			String jyjf4 = request.getParameter("jyjf4");
			String qt4 = request.getParameter("qt4");
			String idcjf4 = request.getParameter("idcjf4");

			String kszlfh5 = request.getParameter("kszlfh5");
			String jszlfh5 = request.getParameter("jszlfh5");
			String jz5 = request.getParameter("jz5");
			String jrw5 = request.getParameter("jrw5");
			String xzzj5 = request.getParameter("xzzj5");
			String jyjf5 = request.getParameter("jyjf5");
			String qt5 = request.getParameter("qt5");
			String idcjf5 = request.getParameter("idcjf5");

			String kszlfh6 = request.getParameter("kszlfh6");
			String jz6 = request.getParameter("jz6");
			String jrw6 = request.getParameter("jrw6");
			String xzzj6 = request.getParameter("xzzj6");
			String jyjf6 = request.getParameter("jyjf6");
			String qt6 = request.getParameter("qt6");
			String idcjf6 = request.getParameter("idcjf6");

			String xs1 = request.getParameter("xs1");
			String jcxs1 = request.getParameter("jcxs1");
			String xs2 = request.getParameter("xs2");
			String jcxs2 = request.getParameter("jcxs2");
			String xs3 = request.getParameter("xs3");
			String jcxs3 = request.getParameter("jcxs3");

			KtxsDao dao = new KtxsDao();
			int s = dao.UpdateKtxs1(jn1, jn2, jn3, jn4, jn5, jn6, jn7, jn8,
					jn9, jn10, jn11, jn12, qd1, qd2, qd3, qd4, qd5, qd6, qd7,
					qd8, qd9, qd10, qd11, qd12, zb1, zb2, zb3, zb4, zb5, zb6,
					zb7, zb8, zb9, zb10, zb11, zb12, zz1, zz2, zz3, zz4, zz5,
					zz6, zz7, zz8, zz9, zz10, zz11, zz12, dy1, dy2, dy3, dy4,
					dy5, dy6, dy7, dy8, dy9, dy10, dy11, dy12, yt1, yt2, yt3,
					yt4, yt5, yt6, yt7, yt8, yt9, yt10, yt11, yt12, wf1, wf2,
					wf3, wf4, wf5, wf6, wf7, wf8, wf9, wf10, wf11, wf12, Jn1,
					Jn2, Jn3, Jn4, Jn5, Jn6, Jn7, Jn8, Jn9, Jn10, Jn11, Jn12,
					ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8, ta9, ta10, ta11,
					ta12, wh1, wh2, wh3, wh4, wh5, wh6, wh7, wh8, wh9, wh10,
					wh11, wh12, rz1, rz2, rz3, rz4, rz5, rz6, rz7, rz8, rz9,
					rz10, rz11, rz12, lw1, lw2, lw3, lw4, lw5, lw6, lw7, lw8,
					lw9, lw10, lw11, lw12, ly1, ly2, ly3, ly4, ly5, ly6, ly7,
					ly8, ly9, ly10, ly11, ly12, dz1, dz2, dz3, dz4, dz5, dz6,
					dz7, dz8, dz9, dz10, dz11, dz12, lc1, lc2, lc3, lc4, lc5,
					lc6, lc7, lc8, lc9, lc10, lc11, lc12, bz1, bz2, bz3, bz4,
					bz5, bz6, bz7, bz8, bz9, bz10, bz11, bz12, hz1, hz2, hz3,
					hz4, hz5, hz6, hz7, hz8, hz9, hz10, hz11, hz12,loginName);
			int ss = dao.UpdateKtxs2(s,kszlfh1, jszlfh1, jz1, jrw1, xzzj1, jyjf1,
					qt1, idcjf1, kszlfh2, jszlfh2, jz2, jrw2, xzzj2, jyjf2,
					qt2, idcjf2, kszlfh3, jszlfh3, jz3, jrw3, xzzj3, jyjf3,
					qt3, idcjf3, kszlfh4, jszlfh4, jz4, jrw4, xzzj4, jyjf4,
					qt4, idcjf4, kszlfh5, jszlfh5, jz5, jrw5, xzzj5, jyjf5,
					qt5, idcjf5, kszlfh6, jz6, jrw6, xzzj6, jyjf6, qt6, idcjf6,
					xs1, jcxs1, xs2, jcxs2, xs3, jcxs3,loginName);
			log.write(msg, account.getName(), request.getRemoteAddr(),
					"月份空调系数修改");
			int r = s + ss;
			if (r == 2) {
				msg = "修改成功！";
			} else {
				msg = "修改失败！";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		
		
		
		if (action.equals("update")) {
			url = path + "/web/newgn/ktxs.jsp";
			List<Ktxs> list = new ArrayList<Ktxs>();
			KtxsDao dao = new KtxsDao();
			DecimalFormat mat = new DecimalFormat("0.00");
			String ss = request.getParameter("gxsj");
			System.out.println("---------------" + ss);
			String s = ss.substring(5, 7);
			String month = "";
			if (s.equals("01")) {
				month = "ymonth";
			} else if (s.equals("02")) {
				month = "emonth";
			} else if (s.equals("03")) {
				month = "smonth";
			} else if (s.equals("04")) {
				month = "wmonth";
			} else if (s.equals("06")) {
				month = "lmonth";
			} else if (s.equals("07")) {
				month = "qmonth";
			} else if (s.equals("08")) {
				month = "bmonth";
			} else if (s.equals("09")) {
				month = "jmonth";
			} else if (s.equals("10")) {
				month = "shimonth";
			} else if (s.equals("11")) {
				month = "symonth";
			} else if (s.equals("12")) {
				month = "semonth";
			}
			String yfxs = "", yfbz = "", jnxs = "", qdxs = "", zbxs = "", zzxs = "", dyxs = "", ytxs = "", wfxs = "", Jnxs = "", taxs = "", whxs = "";
			String rzxs = "", lwxs = "", lyxs = "", dzxs = "", lcxs = "", bzxs = "", hzxs = "";
			list = dao.getyfxs1(month);
			if (list != null) {
				for (Ktxs bean2 : list) {
					yfbz = bean2.getYfbzw();
					yfxs = bean2.getYmonth();
					yfxs = mat.format(Double.parseDouble(yfxs));
					if (yfbz.equals("1")) {
						jnxs = yfxs;
						System.out.println("--jn--" + jnxs);
					} else if (yfbz.equals("2")) {
						qdxs = yfxs;
					} else if (yfbz.equals("3")) {
						zbxs = yfxs;
					} else if (yfbz.equals("4")) {
						zzxs = yfxs;
					} else if (yfbz.equals("5")) {
						dyxs = yfxs;
					} else if (yfbz.equals("6")) {
						ytxs = yfxs;
					} else if (yfbz.equals("7")) {
						wfxs = yfxs;
					} else if (yfbz.equals("8")) {
						Jnxs = yfxs;
					} else if (yfbz.equals("9")) {
						taxs = yfxs;
					} else if (yfbz.equals("10")) {
						whxs = yfxs;
					} else if (yfbz.equals("11")) {
						rzxs = yfxs;
					} else if (yfbz.equals("12")) {
						lwxs = yfxs;
					} else if (yfbz.equals("13")) {
						lyxs = yfxs;
					} else if (yfbz.equals("14")) {
						dzxs = yfxs;
					} else if (yfbz.equals("15")) {
						lcxs = yfxs;
					} else if (yfbz.equals("16")) {
						bzxs = yfxs;
					} else if (yfbz.equals("17")) {
						hzxs = yfxs;
					}
				}
				List<Ktxs> fylist = new ArrayList<Ktxs>();
				fylist = dao.getfwxs();
				String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
				String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
				String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
				String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
				if (fylist != null) {
					for (Ktxs bean3 : fylist) {
						fwid = bean3.getFwxsid();
						fwlx = bean3.getYfname();
						xs = bean3.getFxxs();
						jcxs = bean3.getJcxs();
						fwbzw = bean3.getFwsjbzw();
						xs = mat.format(Double.parseDouble(xs));
						jcxs = mat.format(Double.parseDouble(jcxs));
						if (fwbzw.equals("1")) {
							xs1 = xs;
							jcxs1 = jcxs;
						} else if (fwbzw.equals("2")) {
							xs2 = xs;
							jcxs2 = jcxs;
						} else if (fwbzw.equals("3")) {
							xs3 = xs;
							jcxs3 = jcxs;
						}
					}
				}
				List<Ktxs> fylist1 = new ArrayList<Ktxs>();
				fylist1 = dao.getktxs();
				String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "", id1 = "", zlfh = "";
				String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "", id11 = "", zlfh1 = "";
				String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "", id12 = "", zlfh2 = "";
				String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "", id13 = "", zlfh3 = "";
				String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "", id14 = "", zlfh4 = "";
				String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "", id15 = "", zlfh5 = "";
				String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "", id16 = "", zlfh6 = "";
				if (fylist1 != null) {
					for (Ktxs bean2 : fylist1) {
						id1 = bean2.getKtxsid();
						kszlfh = bean2.getKszlfh();
						jszlfh = bean2.getJszlfh();
						jz = bean2.getJzktxs();
						jrw = bean2.getJrwktxs();
						xzzj = bean2.getXzzjktxs();
						jyjf = bean2.getJyjfktxs();
						qt = bean2.getQtktxs();
						idcjf = bean2.getIdcjfktxs();
						jz = mat.format(Double.parseDouble(jz));
						jrw = mat.format(Double.parseDouble(jrw));
						xzzj = mat.format(Double.parseDouble(xzzj));
						jyjf = mat.format(Double.parseDouble(jyjf));
						qt = mat.format(Double.parseDouble(qt));
						idcjf = mat.format(Double.parseDouble(idcjf));
						if (id1.equals("1")) {
							kszlfh1 = kszlfh;
							jszlfh1 = jszlfh;
							jz1 = jz;
							jrw1 = jrw;
							xzzj1 = xzzj;
							jyjf1 = jyjf;
							qt1 = qt;
							idcjf1 = idcjf;
						} else if (id1.equals("2")) {
							kszlfh2 = kszlfh;
							jszlfh2 = jszlfh;
							jz2 = jz;
							jrw2 = jrw;
							xzzj2 = xzzj;
							jyjf2 = jyjf;
							qt2 = qt;
							idcjf2 = idcjf;
						} else if (id1.equals("3")) {
							kszlfh3 = kszlfh;
							jszlfh3 = jszlfh;
							jz3 = jz;
							jrw3 = jrw;
							xzzj3 = xzzj;
							jyjf3 = jyjf;
							qt3 = qt;
							idcjf3 = idcjf;
						} else if (id1.equals("4")) {
							kszlfh4 = kszlfh;
							jszlfh4 = jszlfh;
							jz4 = jz;
							jrw4 = jrw;
							xzzj4 = xzzj;
							jyjf4 = jyjf;
							qt4 = qt;
							idcjf4 = idcjf;
						} else if (id1.equals("5")) {
							kszlfh5 = kszlfh;
							jszlfh5 = jszlfh;
							jz5 = jz;
							jrw5 = jrw;
							xzzj5 = xzzj;
							jyjf5 = jyjf;
							qt5 = qt;
							idcjf5 = idcjf;
						} else if (id1.equals("6")) {
							kszlfh6 = kszlfh;
							jz6 = jz;
							jrw6 = jrw;
							xzzj6 = xzzj;
							jyjf6 = jyjf;
							qt6 = qt;
							idcjf6 = idcjf;
						}
					}
				}
				int s1 = dao.UpKtxs(kszlfh1, jszlfh1, kszlfh2, jszlfh2,
						kszlfh3, jszlfh3, kszlfh4, jszlfh4, kszlfh5, jszlfh5,
						kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6, jrw1, jrw2,
						jrw3, jrw4, jrw5, jrw6, xzzj1, xzzj2, xzzj3, xzzj4,
						xzzj5, xzzj6, jyjf1, jyjf2, jyjf3, jyjf4, jyjf5, jyjf6,
						qt1, qt2, qt3, qt4, qt5, qt6, idcjf1, idcjf2, idcjf3,
						idcjf4, idcjf5, idcjf6, xs1, jcxs1, xs2, jcxs2, xs3,
						jcxs3, jnxs, qdxs, zbxs, zzxs, dyxs, ytxs, wfxs, Jnxs,
						taxs, whxs, rzxs, lwxs, lyxs, dzxs, lcxs, bzxs, hzxs);
				
				System.out.println(s1 + "sdadadadadad");
				
				if (s1 == 1) {
					msg = "更新成功！";
				} else {
					msg = "更新失败！";
				}
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");
			}
		}
	}
}
