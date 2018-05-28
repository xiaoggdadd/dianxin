package com.noki.zwhd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noki.jizhan.daoru.CountForm;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.DfManageYd;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class YddfftUpload extends HttpServlet {
	private static Log log = LogFactory.getLog(WydfftUpload.class.getName());
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
		String path = request.getRealPath("/web/wyftdfsh/uploadExcels/yddfft");
		System.out.println("------------->" + path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileName_wy_date = "";
		try {
			// �����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// �������ͨ���ֶ�
					String name = item.getFieldName();
					String value = item.getString();
					fileName_wy_date = value;
				} else {
					// �ļ���
					String fileName = item.getName();
					ppath.servletInitialize(getServletConfig()
							.getServletContext());
					String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
					String filedir = dir1 + fileName + ".xls";
					// �ļ���׺��
					String fileSuffix = fileName.substring(fileName
							.lastIndexOf(".") + 1, fileName.length());
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
					log.info("��ʼ������ѵ�ģ��....");
					// ��ȡ�ļ�
					File excel_file = new File(path + "/" + fileName);
					FileInputStream input = new FileInputStream(excel_file);
					XSSFWorkbook book = new XSSFWorkbook(input);
					// int sheetNum = book.getNumberOfSheets();
					// boolean hasWydfftExcel = false;

					// for (int a = 0; a < sheetNum; a++) {
					XSSFSheet sheet = book.getSheetAt(0);
					// String sheetName = sheet.getSheetName();
					// if (sheetName.equals("��ҵ����-��ѷ�̯")) {
					// hasWydfftExcel = true;
					int rowNum = sheet.getLastRowNum();
					List<ZhandianBean> wydfftList = new ArrayList<ZhandianBean>();
					List<Vector<String>> vectors = new ArrayList<Vector<String>>();
					for (int b = 1; b <= rowNum; b++) {
						XSSFRow row = sheet.getRow(b);

						XSSFCell cell_shi = row.getCell(0);
						String shi = getValue(cell_shi);

						XSSFCell cell_xian = row.getCell(1);
						String xian = getValue(cell_xian);

						System.out.println(xian);

						XSSFCell cell_jzname = row.getCell(2);
						String jzname = getValue(cell_jzname);

						System.out.println(jzname);

						XSSFCell cell_zdbm = row.getCell(3);
						String zdbm = getValue(cell_zdbm);

						System.out.println("վ����룺" + zdbm);

						XSSFCell cell_address = row.getCell(4);
						String address = getValue(cell_address);

						System.out.println(address);

						XSSFCell cell_jwd = row.getCell(5);
						String jwd = getValue(cell_jwd);

						System.out.println(jwd);

						XSSFCell cell_zcjjzzbm = row.getCell(6);
						String zcjjzzbm = getValue(cell_zcjjzzbm);

						System.out.println(zcjjzzbm);

						XSSFCell cell_gdfs = row.getCell(7);
						String gdfs = getValue(cell_gdfs);

						System.out.println(gdfs);

						XSSFCell cell_dbbh = row.getCell(8);
						String dbbh = getValue(cell_dbbh);

						System.out.println(dbbh);

						XSSFCell cell_dh = row.getCell(9);
						String dh = getValue(cell_dh);

						System.out.println(dh);

						//XSSFCell cell_jszq = row.getCell(10);
						//String jszq = getValue(cell_jszq);

						//System.out.println(jszq);

						XSSFCell cell_bccbsj = row.getCell(10);
						String bccbsj = getDateValue(cell_bccbsj);

						System.out.println(bccbsj);

						XSSFCell cell_sccbsj = row.getCell(11);
						String sccbsj = getDateValue(cell_sccbsj);

						System.out.println(sccbsj);

						XSSFCell cell_bccbzm = row.getCell(12);
						String bccbzm = getValue(cell_bccbzm);

						System.out.println(bccbzm);

						XSSFCell cell_sccbzm = row.getCell(13);
						String sccbzm = getValue(cell_sccbzm);

						System.out.println(sccbzm);

						//XSSFCell cell_dl = row.getCell(15);
						//String dl = getValue(cell_dl);

						//System.out.println(dl);

						//XSSFCell cell_dj = row.getCell(16);
						//String dj = getValue(cell_dj);

						//System.out.println(dj);

						XSSFCell cell_sh = row.getCell(14);
						String sh = getValue(cell_sh);

						System.out.println(sh);

						XSSFCell cell_jfje = row.getCell(15);
						String jfje = getValue(cell_jfje);

						System.out.println(jfje);

					//	XSSFCell cell_jfrq = row.getCell(16);
					//	String jfrq = getDateValue(cell_jfrq);

						//System.out.println(jfrq);

						XSSFCell cell_jfpjlx = row.getCell(16);
						String jfpjlx = getValue(cell_jfpjlx);

						System.out.println(jfpjlx);

						XSSFCell cell_gdfmc = row.getCell(17);
						String gdfmc = getValue(cell_gdfmc);

						System.out.println(gdfmc);

						XSSFCell cell_ftbl = row.getCell(18);
						String ftbl = getValue(cell_ftbl);

						System.out.println(ftbl);

						XSSFCell cell_sfyz = row.getCell(19);
						String sfyz = getValue(cell_sfyz);

						System.out.println(sfyz);

						XSSFCell cell_ftje = row.getCell(20);
						String ftje = getValue(cell_ftje);

						System.out.println(ftje);

						XSSFCell cell_pc = row.getCell(21);
						String pc = getValue(cell_pc);

						System.out.println(pc);

						Vector<String> vector = new Vector<String>();
						vector.add(shi);
						vector.add(xian);
						vector.add(jzname);
						vector.add(zdbm);
						vector.add(address);
						vector.add(jwd);
						vector.add(zcjjzzbm);
						vector.add(gdfs);
						vector.add(dbbh);
						vector.add(dh);
						//vector.add(jszq);
						vector.add(bccbsj);
						vector.add(sccbsj);
						vector.add(bccbzm);
						vector.add(sccbzm);
						//vector.add(dl);
						//vector.add(dj);
						vector.add(sh);
						vector.add(jfje);
						//vector.add(jfrq);
						vector.add(jfpjlx);
						vector.add(gdfmc);
						vector.add(ftbl);
						vector.add(sfyz);
						vector.add(ftje);
						vector.add(pc);
						vectors.add(vector);

						ZhandianBean zhandian = new ZhandianBean();
						zhandian.setPc(pc);
						zhandian.setDh(dh);
						//zhandian.setJszq(jszq);
						zhandian.setLastcbsj(sccbsj);
						zhandian.setThiscbsj(bccbsj);zhandian.setQm(sccbzm);
						zhandian.setZm(bccbzm);//zhandian.setDliang(dl);
						//zhandian.setDj(dj);
						zhandian.setJfje(jfje);
						//zhandian.setJfrq(jfrq);
						zhandian.setJfpjlx(jfpjlx);
						zhandian.setGdfmc(gdfmc);zhandian.setSh(sh);
						zhandian.setDbbm(dbbh);zhandian.setZdbm(zdbm);
						zhandian.setFtbl(ftbl);zhandian.setFtje(ftje);
						zhandian.setFsyz(sfyz);zhandian.setGdfs(gdfs);

						wydfftList.add(zhandian);
					}

					for (int i = 0; i < wydfftList.size(); i++) {
						ZhandianBean wydfftBean = wydfftList.get(i);
						String dh = wydfftBean.getDh();
						String yearmonth = wydfftBean.getPc();
						String zdbm = wydfftBean.getZdbm();
						String sccbsj=wydfftBean.getLastcbsj();
						String bccbsj=wydfftBean.getThiscbsj();
						String qm=wydfftBean.getQm();
						String zm=wydfftBean.getZm();
						String dl=wydfftBean.getDliang();
						String dj=wydfftBean.getDj();
						String jfje=wydfftBean.getJfje();
						String gdfs=wydfftBean.getGdfs();
						String sh=wydfftBean.getSh();
						String dbbm=wydfftBean.getDbbm();
						
						
						String kh = "�ƶ�";
						DfManageYd df = new DfManageYd();

						ZhandianBean dbBean = df.searchWydfft(yearmonth, zdbm,
								dh, kh);

						// WydfftBean dbBean = wyManage.searchWydfft(yearmonth,
						// zdbm,dh, kh);
						if (dbBean == null) {
							Vector<String> row = vectors.get(i);
							row.add("ϵͳ��û�и�����ҵ��Ϣ��Ϊ������δ������ȷ�ϣ�");
							wrongContent.add(row);
							bcg++;
							continue;
						} else {
							dbBean.setJszq(wydfftBean.getJszq());
							dbBean.setThiscbsj(wydfftBean.getThiscbsj());
							dbBean.setLastcbsj(wydfftBean.getLastcbsj());
							dbBean.setQm(wydfftBean.getQm());
							dbBean.setZm(wydfftBean.getZm());
							dbBean.setDliang(wydfftBean.getDliang());
							dbBean.setDj(wydfftBean.getDj());
							dbBean.setSh(wydfftBean.getSh());
							dbBean.setJfrq(wydfftBean.getJfrq());
							
							
						}
						//����  ���γ���ʱ�䡢�ϴγ���ʱ�� ���+1
						SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");   
						
                      
                    	   Date ss =sdf4.parse(sccbsj);
       					   Date ee =sdf4.parse(bccbsj);
       					//����ʱ���ѯʱ���Ƿ�һ�£��ϴγ���ʱ��+1 �� �Ƿ�����ӱ����������Ƿ�һ��
       					WydfftBean dfbeana= new WydfftBean();
       					dfbeana=wyManage.searchWydfftydzjds("",zdbm,dbbm,"���޸�","�ƶ�");
       					String sc=dfbeana.getBccbsj();//���ϴγ���ʱ��
       					if(sc!=null&&!"".equals(sc)){
       					Date dt=sdf4.parse(sc);
       					if(dt.getTime()!=ss.getTime()){
       						Vector<String> row = vectors.get(i);
       						row.add("�ϴγ���ʱ����ϵͳ���� ϵͳ�ϴγ���ʱ��Ϊ��"+sc);
    						wrongContent.add(row);
    						bcg++;
    						continue;
       						
       					}
       					}
       					 if(ss.getTime()>ee.getTime()){
       						Vector<String> row = vectors.get(i);
       						row.add("���γ���ʱ���������ϴγ���ʱ��");
    						wrongContent.add(row);
    						bcg++;
    						continue;
    					 }
       					 //��������
       					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
       			        Calendar cal = Calendar.getInstance();    
       			        cal.setTime(sdf.parse(sccbsj));    
       			        long time1 = cal.getTimeInMillis();                 
       			        cal.setTime(sdf.parse(bccbsj));    
       			        long time2 = cal.getTimeInMillis();         
       			        long between_days=(time2-time1)/(1000*3600*24);  
       			        int days=Integer.parseInt(String.valueOf(between_days))+1;
       			        String s=days+"";
       			        dbBean.setJszq(s);//����
       			        //���� �� ��� �������
       			    Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
       			    Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
       			       
       			    
       			    
       			    if(pattern.matcher(qm).matches()==false||pattern.matcher(zm).matches()==false||pattern1.matcher(sh).matches()==false){
       				Vector<String> row = vectors.get(i);
					row.add("�ϴγ���ֹ��򱾴γ���ֹ����ߺ����ʽ����ȷ");
					wrongContent.add(row);
					bcg++;
					continue;
       			   }
       			//�ж϶��� ϵͳ�������Ƿ�����ӱ��һ��
       			    if(dfbeana.getZM()!=null&&!"".equals(dfbeana.getZM())){
       			    	if(Double.parseDouble(dfbeana.getZM())!=Double.parseDouble(qm)){
       			    		Vector<String> row = vectors.get(i);
       						row.add("�ϴγ���ֹ����ϵͳ��һ�� ϵͳ�ϴγ���ֹ��Ϊ��"+dfbeana.getZM());
       						wrongContent.add(row);
       						bcg++;
       						continue;
       			    		
       			    	}
       			    	
       			    }
       			    
       			   if(Double.parseDouble(qm)>=Double.parseDouble(zm)){
       				Vector<String> row = vectors.get(i);
					row.add("�ϴγ���ֹ����ڱ��γ���ֹ��");
					wrongContent.add(row);
					bcg++;
					continue;
       			   }
       			   double dll=0.0;
       			dll=(Double.parseDouble(zm)-Double.parseDouble(qm));
       			DecimalFormat mat2 = new DecimalFormat("0.00");
       			DecimalFormat mat3 = new DecimalFormat("0.0000");
       			String dl2=  mat2.format(dll);
       			dbBean.setDliang(dl2);
       			//��ѵ��ۼ���
       			double dj1=0.0;
       			String dj2="";
       			if("ֱ����".equals(gdfs)){
       				if(dll!=0){
       				dj1=Double.parseDouble(jfje)/dll;
       				dj2=mat3.format(dj1);
       				dbBean.setDj(dj2);
       			}
       			if(dj1>1){
       				Vector<String> row = vectors.get(i);
					row.add("ֱ���絥�۴���1Ԫ��������¼�룡����");
					wrongContent.add(row);
					bcg++;
					continue;
       			}
       			}
       			String dbdja="";
       			if("ת����".equals(gdfs)){
       				if(dbbm!=null&&!dbbm.equals(dbbm)){
       				dbdja=wyManage.searchYddj(dbbm);
       				dbdja=mat3.format(Double.parseDouble(dbdja));
       				dbBean.setDj(dbdja);
       				//����*����=?�ɷѽ��
       				if(mat3.format((Double.parseDouble(dbdja)*dll))!=mat3.format(Double.parseDouble(dbBean.getJfje()))){
       					Vector<String> row = vectors.get(i);
    					row.add("ת����ĵ��۳��Ե��������ڽɷѽ�����");
    					wrongContent.add(row);
    					bcg++;
    					continue;
       					
       				}
       				
       				}
       			}
       			
       			//�ɷ�����Ĭ��Ϊ ���γ���ʱ��
       			dbBean.setJfrq(bccbsj);
       			
						boolean isSaveDb = wyManage.updateWydfftyd(dbBean);
						if (isSaveDb) {
							Vector<String> row = vectors.get(i);
							row.add("���ʧ�ܣ�");
							wrongContent.add(row);
							bcg++;
							continue;
						} else {
							cg++;
						}
						
					}
					zg = rowNum;
					form.setCg(cg);
					form.setBcg(bcg);
					form.setZg(zg);
					System.out.println("�ܹ������У�" + rowNum);

					wyManage.saveWydfftHdjl(loginName, fileName_wy_date);
				}
			}

			if (!wrongContent.isEmpty()) {
				msg = "�� " + form.getZg() + "  �����ɹ����� " + form.getCg()
						+ " ������ " + form.getBcg() + "  ������δ���룡";
				WriteExcle wr = new WriteExcle();
				String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
				wr.Write(wrongContent, loginName + "�ƶ���ѷ�̯���벻�ɹ�������.xls",
						"�ƶ���ѷ�̯���벻�ɹ�������", "����벻�ɹ�����", 15, dir2);
			} else {
				msg = "ȫ������ɹ��������� " + form.getCg() + " ����";
			}
               ///web/wyftdfsh/tt_yddfftqr_search.jsp
			session.setAttribute("url", request.getContextPath()
					+ "/web/wyftdfsh/tt_yddfftqr_search.jsp");
			session.setAttribute("msg", msg);
			session.setAttribute("wfile", path + "/wrongdata/" + loginName
					+ "�ƶ���ѷ�̯���벻�ɹ�������.xls");
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

	public static String getDateValue(Cell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil
						.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				result = format.format(value);
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:
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
