package com.noki.zwhd.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.noki.zwhd.manage.CwManage;
import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.CwybxBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class CwybxWydfftHd extends HttpServlet {
	private String msg = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cwYearMonth = request.getParameter("cwYearMonth");
		String wyYearMonth = request.getParameter("wyYearMonth");
		DecimalFormat df = new DecimalFormat("#0.00");
		DecimalFormat df4 = new DecimalFormat("#0.0000");
		if (cwYearMonth == null || cwYearMonth.equals("0")) {
			msg = "û��ѡ����Ч�Ĳ����ѱ������κ�";
		} else if (wyYearMonth == null || wyYearMonth.equals("0")) {
			msg = "û��ѡ����Ч����ҵ���ݵ�ѷ�̯���κ�";
		} else if (!cwYearMonth.equals(wyYearMonth)) {
			msg = "��ҵ���ݵ�ѷ�̯������ѱ�������ͬһ���κ�";
		} else {
			CwManage cwManage = new CwManage();
			WyManage wyManage = new WyManage();
			SystemManage systemManage = new SystemManage();
			List<CwybxBean> firstList = cwManage.searchCwybx(cwYearMonth);
			for (int i = 0; i < firstList.size(); i++) {
				CwybxBean cw = firstList.get(i);
				String cw_dh = cw.getJSDH();
				String cw_zdbm = cw.getZDBH();
				String cwybx_fphsje = cw.getFPHSJE();
				String cwybx_id = cw.getID();
				List<WydfftBean> wyList = wyManage.searchWydfftBy(wyYearMonth,
						cw_zdbm, cw_dh);
				List<CwybxBean> cwList = cwManage.searchCwybxByWydfft(
						cwYearMonth, cw_dh, cw_zdbm);
				int wySize = wyList.size();
				int cwSize = cwList.size();
				if (wySize == 0) {
					// ��ҵ
					String wydfft_ftje = cwybx_fphsje;
					String wydfft_jfje = cwybx_fphsje;
					String wydfft_fsyz = "0";
					String wydfft_ftbl = "100";
					String wydfft_id = wyManage.searchWydfftId();
					String wydfft_bz = "�������1����ҵ����0��,�Զ����";
					String wydfft_dybxdje = df.format(Double
							.parseDouble(cwybx_fphsje)
							* (1 + Double.parseDouble(wydfft_fsyz) / 100)
							* (Double.parseDouble(wydfft_ftbl) / 100));// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
					String wydfft_dyhxdje = "0";// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
					String wydfft_jgje = Double.parseDouble(wydfft_dybxdje)
							- Double.parseDouble(wydfft_dyhxdje) > 0 ? wydfft_dybxdje
							: wydfft_dyhxdje;
					String wydfft_cy = df.format(Double
							.parseDouble(wydfft_ftje)
							- Double.parseDouble(wydfft_jgje));
					WydfftBean wydfft = new WydfftBean();
					wydfft.setJFJE(wydfft_jfje);
					wydfft.setFTJE(wydfft_ftje);
					wydfft.setFSYZ(wydfft_fsyz);
					wydfft.setFTBL(wydfft_ftbl);
					wydfft.setID(wydfft_id);
					wydfft.setBZ(wydfft_bz);
					wydfft.setDYCWBXJE(wydfft_dybxdje);
					wydfft.setDYCWHXJE(wydfft_dyhxdje);
					wydfft.setCY(wydfft_cy);
					wydfft.setJGJE(wydfft_jgje);
					ZhandianBean zhandian = systemManage
							.searchZhandianByJcode(cw_zdbm);
					wydfft.setZDBM(cw_zdbm);
					wydfft.setZDMC(zhandian.getJZNAME());
					System.out.println("---------------->"
							+ zhandian.getJZNAME());
					wydfft.setDH(cw_dh);
					wydfft.setSFGS(zhandian.getSHENG());
					wydfft.setDSFGS(zhandian.getSHI());
					wydfft.setZQ("");
					wydfft.setXZBS("δ����");
					wydfft.setJFPJLX("17%��ֵ˰ר�÷�Ʊ");
					wydfft.setKH("");
					wydfft.setGDFMC("");
					wydfft.setKPLX("��ֵ˰ר�÷�Ʊ");
					wydfft.setYEARMONTH(cwYearMonth);
					wyManage.insertWydfft(wydfft);
					// ��ҵ
					String cwybx_cwyfthydcwybxje = df.format(Double
							.parseDouble(wydfft_ftje)
							/ (1 + Double.parseDouble(wydfft_fsyz) / 100)
							/ (Double.parseDouble(wydfft_ftbl) / 100));
					String cwybx_cy = df.format(Double
							.parseDouble(cwybx_fphsje)
							- Double.parseDouble(cwybx_cwyfthydcwybxje));
					String cwybx_fsyz = df4.format((Double
							.parseDouble(wydfft_ftje)
							/ Double.parseDouble(cwybx_cwyfthydcwybxje) - 1));
					String cwybx_bz = "�������1����ҵ����0��";
					cwManage.updateCwybx(cwybx_id, cwybx_cwyfthydcwybxje,
							cwybx_cy, wydfft_ftje, cwybx_fsyz, cwybx_bz);
				} else if (wySize == cwSize) {
					for (int j = 0; j < wyList.size(); j++) {
						// ��ҵ
						WydfftBean wy = wyList.get(j);
						String wydfft_ftje = wy.getFTJE();
						String wydfft_fsyz = wy.getFSYZ();
						String wydfft_ftbl = wy.getFTBL();
						String wydfft_id = wy.getID();
						String wydfft_bz = "";
						String wydfft_dybxdje = df.format(Double
								.parseDouble(cwybx_fphsje)
								* (1 + Double.parseDouble(wydfft_fsyz) / 100)
								* (Double.parseDouble(wydfft_ftbl) / 100));// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
						String wydfft_dyhxdje = "0";// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
						String wydfft_jgje = Double.parseDouble(wydfft_dybxdje)
								- Double.parseDouble(wydfft_dyhxdje) > 0 ? wydfft_dybxdje
								: wydfft_dyhxdje;
						String wydfft_cy = df.format(Double
								.parseDouble(wydfft_ftje)
								- Double.parseDouble(wydfft_jgje));
						wyManage.updateWydfft(wydfft_id, wydfft_dybxdje,
								wydfft_dyhxdje, wydfft_jgje, wydfft_cy,
								wydfft_bz);
						// ����
						String cwybx_cwyfthydcwybxje = df.format(Double
								.parseDouble(wydfft_ftje)
								/ (1 + Double.parseDouble(wydfft_fsyz) / 100)
								/ (Double.parseDouble(wydfft_ftbl) / 100));
						String cwybx_cy = df.format(Double
								.parseDouble(cwybx_fphsje)
								- Double.parseDouble(cwybx_cwyfthydcwybxje));
						String cwybx_fsyz = df4
								.format((Double.parseDouble(wydfft_ftje)
										/ Double.parseDouble(cwybx_cwyfthydcwybxje) - 1));
						String cwybx_bz = "";
						cwManage.updateCwybx(cwybx_id, cwybx_cwyfthydcwybxje,
								cwybx_cy, wydfft_ftje, cwybx_fsyz, cwybx_bz);
					}
				} else if (wySize < cwSize) {
					// ��ҵ
					WydfftBean wy = wyList.get(0);
					String wydfft_ftje = wy.getFTJE();
					String wydfft_fsyz = wy.getFSYZ();
					String wydfft_ftbl = wy.getFTBL();
					String wydfft_id = wy.getID();
					double cwybx_fphsje_w1toc2 = 0;
					
					for (int j = 0; j < cwList.size(); j++) {
						CwybxBean cw_w1toc2 = cwList.get(j);
						String _cwybx_fphsje_w1toc2 = cw_w1toc2.getFPHSJE();
						cwybx_fphsje_w1toc2 += Double.parseDouble(_cwybx_fphsje_w1toc2);
					}
					
					for (int j = 0; j < cwList.size(); j++) {
						CwybxBean cw_w1toc2 = cwList.get(j);
						String cwybx_id_w1toc2 = cw_w1toc2.getID();
						// ����
						String cwybx_cwyfthydcwybxje = df.format(Double.parseDouble(wydfft_ftje)/ (1 + Double.parseDouble(wydfft_fsyz) / 100)/ (Double.parseDouble(wydfft_ftbl) / 100));
						String cwybx_cy = df.format(cwybx_fphsje_w1toc2- Double.parseDouble(cwybx_cwyfthydcwybxje));
						String cwybx_fsyz = df4.format((Double.parseDouble(wydfft_ftje)/ Double.parseDouble(cwybx_cwyfthydcwybxje) - 1));
						String cwybx_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"+ cwList.size() + "��";
						cwManage.updateCwybx(cwybx_id_w1toc2, cwybx_cwyfthydcwybxje,cwybx_cy, wydfft_ftje, cwybx_fsyz, cwybx_bz);
					}
					
					String wydfft_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"+ cwList.size() + "��";
					String wydfft_dybxdje = df.format(cwybx_fphsje_w1toc2* (1 + Double.parseDouble(wydfft_fsyz) / 100)* (Double.parseDouble(wydfft_ftbl) / 100));// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
					String wydfft_dyhxdje = "0";// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
					String wydfft_jgje = Double.parseDouble(wydfft_dybxdje)- Double.parseDouble(wydfft_dyhxdje) > 0 ? wydfft_dybxdje: wydfft_dyhxdje;
					String wydfft_cy = df.format(Double.parseDouble(wydfft_ftje)- Double.parseDouble(wydfft_jgje));
					wyManage.updateWydfft(wydfft_id, wydfft_dybxdje,wydfft_dyhxdje, wydfft_jgje, wydfft_cy, wydfft_bz);

				} 
//				else if (wySize < cwSize) {
//					for (int j = 0; j < wyList.size(); j++) {
//						// ��ҵ
//						WydfftBean wy = wyList.get(j);
//						String wydfft_ftje = wy.getFTJE();
//						String wydfft_fsyz = wy.getFSYZ();
//						String wydfft_ftbl = wy.getFTBL();
//						String wydfft_id = wy.getID();
//						String wydfft_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"
//								+ cwList.size() + "��";
//						String wydfft_dybxdje = df.format(Double
//								.parseDouble(cwybx_fphsje)
//								* (1 + Double.parseDouble(wydfft_fsyz) / 100)
//								* (Double.parseDouble(wydfft_ftbl) / 100));// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
//						String wydfft_dyhxdje = "0";// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
//						String wydfft_jgje = Double.parseDouble(wydfft_dybxdje)
//								- Double.parseDouble(wydfft_dyhxdje) > 0 ? wydfft_dybxdje
//								: wydfft_dyhxdje;
//						String wydfft_cy = df.format(Double
//								.parseDouble(wydfft_ftje)
//								- Double.parseDouble(wydfft_jgje));
//						wyManage.updateWydfft(wydfft_id, wydfft_dybxdje,
//								wydfft_dyhxdje, wydfft_jgje, wydfft_cy,
//								wydfft_bz);
//						// ����
//						String cwybx_cwyfthydcwybxje = df.format(Double
//								.parseDouble(wydfft_ftje)
//								/ (1 + Double.parseDouble(wydfft_fsyz) / 100)
//								/ (Double.parseDouble(wydfft_ftbl) / 100));
//						String cwybx_cy = df.format(Double
//								.parseDouble(cwybx_fphsje)
//								- Double.parseDouble(cwybx_cwyfthydcwybxje));
//						String cwybx_fsyz = df4
//								.format((Double.parseDouble(wydfft_ftje)
//										/ Double.parseDouble(cwybx_cwyfthydcwybxje) - 1));
//						String cwybx_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"
//								+ cwList.size() + "��";
//						cwManage.updateCwybx(cwybx_id, cwybx_cwyfthydcwybxje,
//								cwybx_cy, wydfft_ftje, cwybx_fsyz, cwybx_bz);
//					}
//				} 
				else if (wySize > cwSize) {
					for (int j = 0; j < wyList.size(); j++) {
						// ��ҵ
						WydfftBean wy = wyList.get(j);
						String wydfft_ftje = wy.getFTJE();
						String wydfft_fsyz = wy.getFSYZ();
						String wydfft_ftbl = wy.getFTBL();
						String wydfft_id = wy.getID();
						String wydfft_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"+ cwList.size() + "��";
						String wydfft_dybxdje = df.format(Double.parseDouble(cwybx_fphsje)* (1 + Double.parseDouble(wydfft_fsyz) / 100)* (Double.parseDouble(wydfft_ftbl) / 100));// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
						String wydfft_dyhxdje = "0";// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
						String wydfft_jgje = Double.parseDouble(wydfft_dybxdje)- Double.parseDouble(wydfft_dyhxdje) > 0 ? wydfft_dybxdje: wydfft_dyhxdje;
						String wydfft_cy = df.format(Double.parseDouble(wydfft_ftje)- Double.parseDouble(wydfft_jgje));
						wyManage.updateWydfft(wydfft_id, wydfft_dybxdje,wydfft_dyhxdje, wydfft_jgje, wydfft_cy,wydfft_bz);
					}

					double wydfft_ftje_w2toc1 = 0;
					for (int j = 0; j < wyList.size(); j++) {
						wydfft_ftje_w2toc1 += Double.parseDouble(wyList.get(j).getFTJE());
					}
					String wydfft_ftje2 = df.format(wydfft_ftje_w2toc1);
					String wydfft_fsyz2 = wyList.get(0).getFSYZ();
					// ����
					String cwybx_cwyfthydcwybxje = df.format(Double.parseDouble(wydfft_ftje2)/ (1 + Double.parseDouble(wydfft_fsyz2) / 100));
					String cwybx_cy = df.format(Double.parseDouble(cwybx_fphsje)- Double.parseDouble(cwybx_cwyfthydcwybxje));
					String cwybx_fsyz = df4.format((Double.parseDouble(wydfft_ftje2)/ Double.parseDouble(cwybx_cwyfthydcwybxje) - 1));
					String cwybx_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"+ cwList.size() + "��";
					cwManage.updateCwybx(cwybx_id, cwybx_cwyfthydcwybxje,cwybx_cy, wydfft_ftje2, cwybx_fsyz, cwybx_bz);
				} 
//				else if (wySize > cwSize) {
//					for (int j = 0; j < wyList.size(); j++) {
//						// ��ҵ
//						WydfftBean wy = wyList.get(j);
//						String wydfft_ftje = wy.getFTJE();
//						String wydfft_fsyz = wy.getFSYZ();
//						String wydfft_ftbl = wy.getFTBL();
//						String wydfft_id = wy.getID();
//						String wydfft_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"
//								+ cwList.size() + "��";
//						String wydfft_dybxdje = df.format(Double
//								.parseDouble(cwybx_fphsje)
//								* (1 + Double.parseDouble(wydfft_fsyz) / 100)
//								* (Double.parseDouble(wydfft_ftbl) / 100));// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
//						String wydfft_dyhxdje = "0";// ��Ʊ��˰���*(1+˰������/100)*(��̯����/100)
//						String wydfft_jgje = Double.parseDouble(wydfft_dybxdje)
//								- Double.parseDouble(wydfft_dyhxdje) > 0 ? wydfft_dybxdje
//								: wydfft_dyhxdje;
//						String wydfft_cy = df.format(Double
//								.parseDouble(wydfft_ftje)
//								- Double.parseDouble(wydfft_jgje));
//						wyManage.updateWydfft(wydfft_id, wydfft_dybxdje,
//								wydfft_dyhxdje, wydfft_jgje, wydfft_cy,
//								wydfft_bz);
//						// ����
//						String cwybx_cwyfthydcwybxje = df.format(Double
//								.parseDouble(wydfft_ftje)
//								/ (1 + Double.parseDouble(wydfft_fsyz) / 100)
//								/ (Double.parseDouble(wydfft_ftbl) / 100));
//						String cwybx_cy = df.format(Double
//								.parseDouble(cwybx_fphsje)
//								- Double.parseDouble(cwybx_cwyfthydcwybxje));
//						String cwybx_fsyz = df4
//								.format((Double.parseDouble(wydfft_ftje)
//										/ Double.parseDouble(cwybx_cwyfthydcwybxje) - 1));
//						String cwybx_bz = "��ҵϵͳ����" + wyList.size() + "������ϵͳ����"
//								+ cwList.size() + "��";
//						cwManage.updateCwybx(cwybx_id, cwybx_cwyfthydcwybxje,
//								cwybx_cy, wydfft_ftje, cwybx_fsyz, cwybx_bz);
//					}
//				}
			}

			if (wyManage.updateWydffthdjl(wyYearMonth)
					&& wyManage.updateWydfft_shzt(wyYearMonth)
					&& cwManage.updateCwybx_shzt(cwYearMonth)) {
				msg = "�˶Գɹ�";
			} else {
				msg = "�˶Լ�¼";
			}
		}

		String sendPath = request.getContextPath();
		String sendUrl = sendPath + "/web/msgdr.jsp";

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		session.setAttribute("msg", msg);
		session.setAttribute("url", sendPath
				+ "/web/wyftdfsh/tt_cwybx_wydfft_hd.jsp");

		response.sendRedirect(sendUrl);
	}
}
