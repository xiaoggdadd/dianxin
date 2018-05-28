package com.noki.dataLoad.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.jizhan.DianBiaoBean;
import com.noki.jizhan.model.DianBiaoErrorBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonBean;
import com.ptac.app.accounting.AccountingDao;
import com.ptac.app.util.FileUploadUtil;
import com.ptac.app.util.ImportExcel;

/**
 * Title: �����ļ��ϴ�Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28
 */
public class FileUpServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";
	private static String DIANBIAO_DEFAULT_FILE_NAME = "����������ģ��";

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		DbLog log = new DbLog();
		Account account = new Account();
		String url = path + "/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp", msg = "";
		HttpSession session = req.getSession();
		account = (Account) session.getAttribute("account");

		String action = req.getParameter("action");
		String templetname = req.getParameter("templetname");
		if (templetname == null) {
			templetname = DIANBIAO_DEFAULT_FILE_NAME;
		} else {
			DIANBIAO_DEFAULT_FILE_NAME = templetname;
		}
		FileInputStream fis = null;

		String forward = "success";
		// �����ļ����ز鿴
		if ("download".equals(action)) {

			resp.setContentType(Content_type);
			resp.setHeader("Content-disposition", "attachment;filename="
					+ toUtf8String(templetname + ".xls"));
			String filePath = req.getRealPath("/exceltemplet/" + templetname
					+ ".xls");

			System.out.println("11111111filePath(download)11111111111:"
					+ filePath);
			try {
				fis = new FileInputStream(new File(filePath));
				OutputStream out = resp.getOutputStream();
				byte[] buff = new byte[1024];
				int readCount = 0;
				readCount = fis.read(buff);
				while (readCount != -1) {
					out.write(buff, 0, readCount);
					readCount = fis.read(buff);
				}
				fis.close();
				out.close();
				// return null;
			} catch (Exception e) {
				e.printStackTrace();
				forward = "failure";
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} else if ("upload".equals(action)) {
			
			  
			String resultMsg = "";
			
			try {
				FileUploadUtil fileUtil = new FileUploadUtil();
				File file = fileUtil.upload(req, resp);
				if(file != null){
					 //�����������
					String[] columns = { "fgs", "�ֹ�˾����", "qxfgs", "DBBM", "�������", "DBNAME", "ʵ���� ",
							"LJID", "ȫʡ��վ����", "ʵ������", "maxds", "CSDS",
							"dwjslx", "CBZX", "CBZXBM", "DBYT", "ISGLF",
							"GLBM", "ZRR", "BZR", "YDSX", "YDLX", "fkfs",
							"fkzq", "JFFS", "JLDW", "YHBH", "BEILV", "dbzt",
							"bgje", "DANJIA", "gysmc", "gysbm", "skfmc",
							"yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo",
							"CSLRR", "SHZT", "PJLX", "ZZSL", "IS_CONT",
							"CONT_ID", "CONT_TYPE_DICT", "gdfs",
							"PRODUCTION_PROP", "TOTAL_ELECTRICITY", "shifou",
							"isdblx_dict"};
					System.out.println(file.getAbsolutePath());
					ImportExcel ie = new ImportExcel(1);//1������
					
					ArrayList values = ie.readExcel(file);
					
					ArrayList<DianBiaoErrorBean> errorMsg = ie.check1(values, columns); //��֤�쳣
					
					ArrayList result =  ie.getChecked();
					// �����ֵ�����
					String[] dictypes = { "dwjslx", "dbyt","YDSX", "ydlx", "fkfs",
							"fkzq", "jffs","JLDW", "dbzt", "PJLX","IS_CONT", "CONT_TYPE_DICT",
							"gdfs", "isdblx_dict", "shifou", "ISGLF", "shzt" };
					CommonBean commBean = new CommonBean();
					ArrayList list = commBean.getSelctOptions(dictypes);
					//ת���ֵ�����
					for (int i = 0; i < result.size(); i++) {
						Map<String, Object> map = (Map<String, Object>)result.get(i);
						for (int j = 0; j < dictypes.length; j++) {
							String key = dictypes[j].toLowerCase();
							String label = String.valueOf(map.get(key));
							if(label !=null && !label.isEmpty()){//�ֵ�����
								String value = ie.adjust(list, key, label);
								map.put(key, value);
							}
						}
						System.out.println("map-transform"+map);
					}
					//���ݿ����
					DianBiaoBean dianBiaoBean = new DianBiaoBean();
					//�����������ݿ���
					dianBiaoBean.updateBatch(result);   //����1
					//�޸Ķ�Ӧ��ϵͳ��վid
					dianBiaoBean.updateBatchName();
					//����������㵥Ԫ
					AccountingDao dao = new AccountingDao();
					dao.addAccountingUnitSelCostCenter();
					//��������Ϣ����
					String title = "������Ϣ����";
					String[] rowName = new String[] { "���","�ֹ�˾", "���طֹ�˾", "�����루����ţ�", " ʵ����","��վID","��վ����","ȫʡ��վ����","ʵ������","���������","����ʱ����","�����������","���طֹ�˾","�ɱ����ı���","��;�� �����;����","�Ƿ��й����","������","������","������","�õ�����","�õ�����","�ɷѷ�ʽ����֧����ʽ���ֽ�ת�ˡ�֧Ʊ����","�ɷ�����","�Ʒѷ�ʽ","������λ","�û����","����|�� ����ʣ�","״̬�����״̬��","���ɽ��","����(��ѵ���)","��Ӧ������","��Ӧ�̱���","�տ����","�����ʺ�","��������","��������","�����ʻ�����ʡ","��ע","������","���״̬","��Ʊ����","˰��","�Ƿ��к�ͬ","��ͬ��(��ͬ���)","��ͬ����","���緽ʽ","���緽ʽ","����ռ��(%)","�˶�����","�Ƿ����ܵ��","�������","������",};
					ExportExcelError.importExecl(resp,title,rowName,errorMsg);	//��������Ϣ����Excel
					//end
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		// log.write(msg, payformBean.getElectricfeeId(), req.getRemoteAddr(),
		// "�˻�ά��");
		// session.setAttribute("url", url);
		// session.setAttribute("msg", msg);
		// resp.sendRedirect(path + "/web/msg.jsp");
	}

	public String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

}
