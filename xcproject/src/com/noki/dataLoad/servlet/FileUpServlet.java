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
 * Title: 数据文件上传Action Copyright: Copyright (c) 2010 Company: CVIC SE
 * 
 * @version 1.0
 * @author sang
 * @created at 2010-07-28
 */
public class FileUpServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";
	private static String DIANBIAO_DEFAULT_FILE_NAME = "电表基础表导入模板";

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
		// 数据文件下载查看
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
					 //电表导入所需列
					String[] columns = { "fgs", "分公司名称", "qxfgs", "DBBM", "物理编码", "DBNAME", "实体编号 ",
							"LJID", "全省局站编码", "实体名称", "maxds", "CSDS",
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
					ImportExcel ie = new ImportExcel(1);//1级标题
					
					ArrayList values = ie.readExcel(file);
					
					ArrayList<DianBiaoErrorBean> errorMsg = ie.check1(values, columns); //验证异常
					
					ArrayList result =  ie.getChecked();
					// 所用字典类型
					String[] dictypes = { "dwjslx", "dbyt","YDSX", "ydlx", "fkfs",
							"fkzq", "jffs","JLDW", "dbzt", "PJLX","IS_CONT", "CONT_TYPE_DICT",
							"gdfs", "isdblx_dict", "shifou", "ISGLF", "shzt" };
					CommonBean commBean = new CommonBean();
					ArrayList list = commBean.getSelctOptions(dictypes);
					//转换字典数据
					for (int i = 0; i < result.size(); i++) {
						Map<String, Object> map = (Map<String, Object>)result.get(i);
						for (int j = 0; j < dictypes.length; j++) {
							String key = dictypes[j].toLowerCase();
							String label = String.valueOf(map.get(key));
							if(label !=null && !label.isEmpty()){//字典数据
								String value = ie.adjust(list, key, label);
								map.put(key, value);
							}
						}
						System.out.println("map-transform"+map);
					}
					//数据库操作
					DianBiaoBean dianBiaoBean = new DianBiaoBean();
					//批量导入数据库中
					dianBiaoBean.updateBatch(result);   //步骤1
					//修改对应本系统局站id
					dianBiaoBean.updateBatchName();
					//批量插入核算单元
					AccountingDao dao = new AccountingDao();
					dao.addAccountingUnitSelCostCenter();
					//将错误信息返回
					String title = "错误信息返回";
					String[] rowName = new String[] { "序号","分公司", "区县分公司", "电表编码（电表编号）", " 实体编号","局站ID","局站名称","全省局站编码","实体名称","电表最大读数","启用时读数","对外结算类型","区县分公司","成本中心编码","用途（ 电表用途类型","是否有管理费","管理部门","责任人","报账人","用电属性","用电类型","缴费方式（（支付方式：现金、转账、支票））","缴费周期","计费方式","计量单位","用户编号","倍率|（ 电表倍率）","状态（电表状态）","包干金额","单价(电费单价)","供应商名称","供应商编码","收款方名称","银行帐号","所属银行","开户银行","银行帐户所属省","备注","创建人","审核状态","发票类型","税率","是否有合同","合同号(合同编号)","合同类型","供电方式","供电方式","生产占比(%)","核定电量","是否智能电表","电表类型","错误反馈",};
					ExportExcelError.importExecl(resp,title,rowName,errorMsg);	//将错误信息生成Excel
					//end
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		// log.write(msg, payformBean.getElectricfeeId(), req.getRemoteAddr(),
		// "账户维护");
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
