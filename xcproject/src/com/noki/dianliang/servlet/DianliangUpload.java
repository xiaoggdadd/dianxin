package com.noki.dianliang.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.noki.biaogan.CBUserManage;
import com.noki.biaogan.model.AmmertdegreeBean;
import com.noki.dianliang.manage.DianLiangManage;
import com.noki.dianliang.model.AmmeterdegreesBean;
import com.noki.dianliang.model.DianbiaoBean;
import com.noki.dianliang.model.ElectricfeesBean;
import com.noki.dianliang.model.ZhandianBean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.WyManage;

public class DianliangUpload extends HttpServlet {

	private static Log log = LogFactory.getLog(DianliangUpload.class.getName());
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg;
		int cg = 0, bcg = 0, zg = 0;
		Vector<Vector<String>> wrongContent = new Vector<Vector<String>>();
		CountForm form = new CountForm();
		Path ppath = new Path();
		HttpSession session = request.getSession();
		String cpath = request.getContextPath();
		String url = cpath + "/web/msgdr.jsp";
		WyManage wyManage = new WyManage();
		response.setContentType(CONTENT_TYPE);
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request
				.getRealPath("/web/sdttWeb/DianliangManager/uploadExcels");
		System.out.println("------------->" + path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);

		DianLiangManage dianliangManage = new DianLiangManage();

		try {
			// �����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// �������ͨ���ֶ�
					String name = item.getFieldName();
					String value = item.getString();
				} else {
					// �ļ���
					String fileName = item.getName();
					ppath.servletInitialize(getServletConfig()
							.getServletContext());
					String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
					String filedir = dir1 + fileName + ".xls";
					// �ļ���׺��
					String fileSuffix = fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length());
					// String[] fileName_wy = fileName.split("��ҵ����-��ѷ�̯-");
					// if (fileName_wy.length > 0) {
					// �����ļ��������ļ���
					// fileName_wy_date = fileName_wy[1].replaceAll("."
					// + fileSuffix, "");
					OutputStream out = new FileOutputStream(new File(path,
							fileName));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					// } else {
					// msg = "ģ�����ƴ���,�밴���ص�ģ������޸�";
					// }
					log.info("��ʼ������Ѻ˶�ģ��....");
					// ��ȡ�ļ�
					File excel_file = new File(path + "/" + fileName);
					FileInputStream input = new FileInputStream(excel_file);
					HSSFWorkbook book = new HSSFWorkbook(input);
					// int sheetNum = book.getNumberOfSheets();
					// boolean hasWydfftExcel = false;

					// for (int a = 0; a < sheetNum; a++) {
					HSSFSheet sheet = book.getSheetAt(0);
					// String sheetName = sheet.getSheetName();
					// if (sheetName.equals("��ҵ����-��ѷ�̯")) {
					// hasWydfftExcel = true;
					int rowNum = sheet.getLastRowNum() - 4;
					List<AmmeterdegreesBean> AmmeterdegreesList = new ArrayList<AmmeterdegreesBean>();
					List<Vector<String>> vectors = new ArrayList<Vector<String>>();
					for (int b = 0; b < rowNum; b++) {
						HSSFRow row = sheet.getRow(5 + b);
						// ���继��
						HSSFCell cell_gdyhh = row.getCell(0);
						String gdyhh = getValue(cell_gdyhh);
						// ������ʼ����
						HSSFCell cell_gdqsds = row.getCell(1);
						String gdqsds = getValue(cell_gdqsds);
						// �����ֹ����
						HSSFCell cell_gdjzds = row.getCell(2);
						String gdjzds = getValue(cell_gdjzds);
						// �õ���
						HSSFCell cell_ydl = row.getCell(3);
						String ydl = getValue(cell_ydl);
						// �õ���
						HSSFCell cell_ydje = row.getCell(4);
						String ydje = getValue(cell_ydje);
						// ������
						HSSFCell cell_xsje = row.getCell(5);
						String xsje = getValue(cell_xsje);
						// ֧���ܽ��
						HSSFCell cell_zfzje = row.getCell(7);
						String zfzje = getValue(cell_zfzje);
						// ���糭������
						HSSFCell cell_gdcbrq = row.getCell(8);
						String gdcbrq = parseExcel(cell_gdcbrq);
						// ������ʼ����
						HSSFCell cell_gdqsrq = row.getCell(9);
						String gdqsrq = parseExcel(cell_gdqsrq);
						// �����ֹ����
						HSSFCell cell_gdjzrq = row.getCell(10);
						String gdjzrq = parseExcel(cell_gdjzrq);

						Vector<String> vector = new Vector<String>();
						vector.add(gdyhh);
						vector.add(gdqsds);
						vector.add(gdjzds);
						vector.add(ydl);
						vector.add(ydje);
						vector.add(xsje);
						vector.add(zfzje);
						vector.add(gdcbrq);
						vector.add(gdqsrq);
						vector.add(gdjzrq);
						vectors.add(vector);

						DianbiaoBean dianbiao = new DianbiaoBean();
						dianbiao.setDBZBDYHH(gdyhh);

						AmmeterdegreesBean ammeterdegrees = new AmmeterdegreesBean();
						ammeterdegrees.setLASTDEGREE(gdqsds);
						ammeterdegrees.setTHISDEGREE(gdjzds);
						ammeterdegrees.setBLHDL(ydl);
						ammeterdegrees.setGDCBDATE(gdcbrq);
						ammeterdegrees.setLASTDATE(gdqsrq);
						ammeterdegrees.setTHISDATE(gdjzrq);

						ElectricfeesBean electricfees = new ElectricfeesBean();
						electricfees.setYDDF(ydje);
						electricfees.setLINELOSS(xsje);
						electricfees.setACTUALPAY(zfzje);

						ammeterdegrees.setElectricffess(electricfees);
						ammeterdegrees.setDianbiao(dianbiao);

						if (gdyhh != null && !gdyhh.equals("")) {
							if (gdqsds != null && !gdqsds.equals("")) {
								try {
									Double.parseDouble(gdqsds);
									if (gdjzds != null && !gdjzds.equals("")) {
										try {
											Double.parseDouble(gdjzds);
											AmmeterdegreesList
													.add(ammeterdegrees);
										} catch (Exception e) {
											vector.add("���������������Ϊ���֣�");
											wrongContent.add(vector);
											bcg++;
											continue;
										}

									} else {
										vector.add("���������������Ϊ�գ�");
										wrongContent.add(vector);
										bcg++;
										continue;
									}
								} catch (Exception e) {
									vector.add("������ʼ��������Ϊ���֣�");
									wrongContent.add(vector);
									bcg++;
									continue;
								}

							} else {
								vector.add("������ʼ��������Ϊ�գ�");
								wrongContent.add(vector);
								bcg++;
								continue;
							}
						} else {
							vector.add("����Ų���Ϊ�գ�");
							wrongContent.add(vector);
							bcg++;
							continue;
						}

						if (gdcbrq != null && !gdcbrq.equals("")) {
							if (gdqsrq != null && !gdqsrq.equals("")) {
								if (gdjzrq != null && !gdjzrq.equals("")) {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Date d_gdcbrq;
									Date d_gdqsrq;
									Date d_gdjzrq;
									try {
										d_gdjzrq = sdf.parse(gdjzrq);
										try {
											d_gdcbrq = sdf.parse(gdcbrq);
											try {
												d_gdqsrq = sdf.parse(gdqsrq);
												if(d_gdjzrq.getTime()>d_gdqsrq.getTime()){
													
												}else{
													vector.add("�����������Ӧ���ڹ�����ʼ���ڣ�");
													wrongContent.add(vector);
													bcg++;
													continue;
												}
											} catch (Exception e) {
												vector.add("������ʼ���ڸ�ʽ����");
												wrongContent.add(vector);
												bcg++;
												continue;
											}
										} catch (Exception e) {
											vector.add("���糭�����ڸ�ʽ����");
											wrongContent.add(vector);
											bcg++;
											continue;
										}
									} catch (Exception e) {
										vector.add("����������ڸ�ʽ����");
										wrongContent.add(vector);
										bcg++;
										continue;
									}
									
								} else {
									vector.add("����������ڲ���Ϊ�գ�");
									wrongContent.add(vector);
									bcg++;
									continue;
								}
							} else {
								vector.add("������ʼ���ڲ���Ϊ�գ�");
								wrongContent.add(vector);
								bcg++;
								continue;
							}
						} else {
							vector.add("���糭�����ڲ���Ϊ�գ�");
							wrongContent.add(vector);
							bcg++;
							continue;
						}
					}

					for (int i = 0; i < AmmeterdegreesList.size(); i++) {
						AmmeterdegreesBean ammeterdegrees = AmmeterdegreesList
								.get(i);
						String thisdate = ammeterdegrees.getTHISDATE();
						String lastdate = ammeterdegrees.getLASTDATE();
						String lineloss = ammeterdegrees.getElectricffess()
								.getLINELOSS();

						String dbzbdyhh = ammeterdegrees.getDianbiao()
								.getDBZBDYHH();
						String lastdegree = ammeterdegrees.getLASTDEGREE();
						String thisdegree = ammeterdegrees.getTHISDEGREE();
						double _lastdegree = Double.parseDouble(lastdegree);
						double _thisdegree = Double.parseDouble(thisdegree);
						double dianliang = _thisdegree - _lastdegree;
						double blhdl = Double.parseDouble(ammeterdegrees
								.getBLHDL());
						if (dianliang != blhdl) {
							Vector<String> row = vectors.get(i);
							row.add("EXCEL���õ������̨������õ�����һ�£�");
							wrongContent.add(row);
							bcg++;
							continue;
						}

						DianbiaoBean dianbiao = dianliangManage
								.searchDianbiao(dbzbdyhh);
						if (dianbiao == null) {
							Vector<String> row = vectors.get(i);
							row.add("ϵͳ��û��¼��õ��");
							wrongContent.add(row);
							bcg++;
							continue;
						} else {
							log.info("����Ѿ���ȡ");
							String zdid = dianbiao.getZDID();
							String dbid = dianbiao.getDBID();

							if (zdid != null && !zdid.equals("")) {
								ZhandianBean zhandian = dianliangManage
										.searchZhandianByZdid(zdid);
								if (zhandian == null) {
									Vector<String> row = vectors.get(i);
									row.add("ϵͳ��û��¼���վ�㣡");
									wrongContent.add(row);
									bcg++;
									continue;
								} else {

									// DianjiaBean dianjiaBean =
									// dianliangManage.searchDianjiaByDBid(dbid);
									// double dianjia = 0;
									// if(dianjiaBean!=null){
									// dianjia =
									// Double.parseDouble(dianjiaBean.getDANJIA());
									// }

									log.info("վ���Ѿ���ȡ");

									ElectricfeesBean electricfeesBean = new ElectricfeesBean();
									electricfeesBean.setNOTETYPEID("pjlx03");

									com.noki.biaogan.model.AmmertdegreeBean ammertdegree = new AmmertdegreeBean();
									ammertdegree.setNOTETYPEID("pjlx03");

									ammertdegree.setAMMETERID_FK(dbid);
									ammertdegree.setTHISDEGREE(thisdegree);
									ammertdegree.setTHISDATE(thisdate);
									ammertdegree.setTHISDATETIME(thisdate);
									ammertdegree.setLASTDEGREE(lastdegree);
									ammertdegree.setLASTDATE(lastdate);
									ammertdegree.setLASTDATETIME(lastdate);
									ammertdegree.setBLHDL(blhdl + "");
									ammertdegree.setINPUTDATETIME(thisdate);
									ammertdegree.setLINELOSS(lineloss);
									ammertdegree.setDFBZW("0");
									ammertdegree.setISLD("");

									CBUserManage cbuser = new CBUserManage();
									boolean flag = cbuser
											.saveCbValue(ammertdegree);
									if (flag) {
										Vector<String> row = vectors.get(i);
										row.add("���ʧ�ܣ�");
										wrongContent.add(row);
										bcg++;
										continue;
									} else {
										cg++;
									}
								}
							} else {
								Vector<String> row = vectors.get(i);
								row.add("ϵͳ��û��¼���վ�㣡");
								wrongContent.add(row);
								bcg++;
								continue;
							}
						}

					}

					zg = rowNum;
					form.setCg(cg);
					form.setBcg(bcg);
					form.setZg(zg);
					System.out.println("�ܹ������У�" + rowNum);
				}
			}

			if (!wrongContent.isEmpty()) {
				msg = "�� " + form.getZg() + "  �����ɹ����� " + form.getCg()
						+ " ������ " + form.getBcg() + "  ������δ���룡";
				WriteExcle wr = new WriteExcle();
				String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
				wr.Write(wrongContent, loginName + "������ѵ��벻�ɹ�������.xls",
						"������ѵ��벻�ɹ�������", "������ѵ��벻�ɹ�����", 15, dir2);
			} else {
				msg = "ȫ������ɹ��������� " + form.getCg() + " ����";
			}

			session.setAttribute("url", request.getContextPath()
					+ "/web/sdttWeb/DianliangManager/dianliangImport.jsp");
			session.setAttribute("msg", msg);
			session.setAttribute("wfile", path + "/wrongdata/" + loginName
					+ "������ѵ��벻�ɹ�������.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(url);
	}

	public static String getValue(Cell cell) {
		if (cell == null) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue());
		}
	}

	private String parseExcel(Cell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// ��������
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// �������ڸ�ʽ��ʱ���ʽ
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// ����
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil
						.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// ��Ԫ�����óɳ���
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				result = format.format(value);
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String����
			result = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = "";
			break;
		}
		return result;
	}

}
