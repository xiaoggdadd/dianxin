package com.noki.biaogan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.biaogan.BiaoganManage;
import com.noki.biaogan.CBUserManage;
import com.noki.biaogan.model.AmmertdegreeBean;
import com.noki.biaogan.model.CbUserBean;
import com.noki.biaogan.model.VersionInfo;
import com.noki.common.oss.dao.OSSAccountDao;
import com.noki.util.MD5;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.ZhandianBean;

public class CBUserServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(CBUserServlet.class.getName());
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		System.out.println("method===" + method);
		CBUserManage userManage = new CBUserManage();
		if (method.equals("searchZhandianListByLocation")) {
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String userid = request.getParameter("userid");
			List<ZhandianBean> zhandianList = userManage
					.searchZhandianListByLocation(userid, longitude, latitude);

			JSONObject json = new JSONObject();
			try {
				json.put("zhandianList", JSONArray.fromObject(zhandianList));
				if (zhandianList != null) {
					json.put("flag", true);
				} else {
					json.put("flag", false);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			log.info(json.toString());
			out.write(json.toString());
			out.flush();
			out.close();
		} else if (method.equals("searchVersion")) {
			VersionInfo version = userManage.searchVersion();
			JSONObject json = new JSONObject();
			try {
				json.put("version", JSONObject.fromObject(version));
				if (version != null) {
					json.put("flag", true);
				} else {
					json.put("flag", false);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			log.info(json.toString());
			response.getWriter().write(json.toString());
		} else if(method.equals("doLoginwang")){
			OSSAccountDao assad = new OSSAccountDao();
			String ip = getIpAddress(request);
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			// MD5加密
			MD5 md = new MD5();
			password = md.getMD5ofStr("123456");
			boolean hasUser;
			CbUserBean user = null;
			
			String stfdf = assad.addOssPrivilegedwang(loginname);
			if(1==1){
				String hasUser1=userManage.doLoginrunphone(stfdf);
				  hasUser = userManage.doLogin(loginname);
				 user = userManage.doLogin(loginname, password);
				System.out.println(stfdf+"+++++++++++++===========");
			}else{
				hasUser=false;
			}
			JSONObject json = new JSONObject();
			try {
				json.put("hasUser", hasUser);
				json.put("user", user);
				if (hasUser) {
					if (user != null) {
						json.put("flag", true);
						json.put("message", "登录成功");
						session.setAttribute("user", user);
					} else {
						json.put("flag", false);
						json.put("message", "密码错误请重试！");
					}
				} else {
					json.put("flag", false);
					json.put("message", "该用户尚未注册为抄表人员，详情请联系管理员");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			log.info(json.toString());

			boolean flag = json.getBoolean("flag");
			String message = json.getString("message");

			boolean saveLog = userManage.saveLoginLog(ip, loginname, password,
					flag, message, json.toString());

			response.getWriter().write(json.toString());
		} else if (method.equals("doLogin")) {
			
			OSSAccountDao assad = new OSSAccountDao();
			String ip = getIpAddress(request);
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			// MD5加密
			MD5 md = new MD5();
			password = md.getMD5ofStr("123456");
			boolean hasUser;
			CbUserBean user = null;
			String stfdf = assad.addOssPrivileged(loginname);//验证oss账号返回对应手机账号
			if(stfdf!="wu"){
				String hasUser1=userManage.doLoginrunphone(stfdf);//验证account用户与oss对应部分
				  hasUser = userManage.doLogin(hasUser1);//验证是否具有分配区域是不是抄表人员
				 user = userManage.doLogin(hasUser1, password);//得到对应超标人员实体
				System.out.println(stfdf+"+++++++++++++===========");
			}else{
				hasUser=false;
			}
			JSONObject json = new JSONObject();
			try {
				json.put("hasUser", hasUser);
				json.put("user", user);
				if (hasUser) {
					if (user != null) {
						json.put("flag", true);
						json.put("message", "登录成功");
						session.setAttribute("user", user);
					} else {
						json.put("flag", false);
						json.put("message", "密码错误请重试！");
					}
				} else {
					json.put("flag", false);
					json.put("message", "该用户尚未注册为抄表人员，详情请联系管理员");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			log.info(json.toString());

			boolean flag = json.getBoolean("flag");
			String message = json.getString("message");

			boolean saveLog = userManage.saveLoginLog(ip, loginname, password,
					flag, message, json.toString());

			response.getWriter().write(json.toString());
		}
		// 根据id查基站位置
		else if (method.equals("searchuser")) {
			JSONObject json = new JSONObject();
			try {
				String userid = request.getParameter("userid");
				String zdid = request.getParameter("zdid");
				List<ZhandianBean> zdList = userManage.searchZdByUserid(userid,
						zdid);
				
				JSONArray dbListJson = JSONArray.fromObject(zdList);
				json.put("zdListJson", dbListJson);
				json.put("flag", true);
				log.info(json.toString());
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				json.put("flag", false);
				response.getWriter().write(json.toString());
			}
		} else if (method.equals("searchuserdb")) {
			JSONObject json = new JSONObject();
			try {
				String zdid = request.getParameter("zdid");
				List<AmmertdegreeBean> zdList = userManage
						.searchdbByUserid(zdid);
				JSONArray dbListJson = JSONArray.fromObject(zdList);
				json.put("zdListJson", dbListJson);
				json.put("flag", true);
				log.info(json.toString());
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				json.put("flag", false);
				response.getWriter().write(json.toString());
			}
		} else if (method.equals("searchZdBySession")) {
			JSONObject json = new JSONObject();
			try {
				CbUserBean sessionUser = (CbUserBean) session
						.getAttribute("user");
				String userid = sessionUser.getID();
				List<ZhandianBean> zdList = userManage.searchZdByUserid(userid);
				JSONArray zdListJson = JSONArray.fromObject(zdList);

				json.put("zdListJson", zdListJson);
				json.put("flag", true);
				log.info(json.toString());
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				json.put("flag", false);
				response.getWriter().write(json.toString());
			}
		} else if (method.equals("searchZd")) {
			String userid = request.getParameter("userid");
			List<ZhandianBean> zdList = userManage.searchZdByUserid(userid);
			JSONArray zdListJson = JSONArray.fromObject(zdList);
			JSONObject json = new JSONObject();
			json.put("zdListJson", zdListJson);
			log.info(json.toString());
			response.getWriter().write(json.toString());
		} else if (method.equals("searchDb")) {
			JSONObject json = new JSONObject();
			try {
				String userid = request.getParameter("userid");
				String zdid = request.getParameter("zdid");
				ZhandianBean zd = userManage.searchZdByUserid2(userid, zdid);
				List<DianbiaoBean> dbList = userManage.searchDb(zdid);
				JSONArray dbListJson = JSONArray.fromObject(dbList);
				json.put("dbListJson", dbListJson);
				json.put("zd", zd);
				json.put("flag", true);
				log.info(json.toString());
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				json.put("flag", false);
				response.getWriter().write(json.toString());
			}
		} else if (method.equals("searchCbuser")) {
			// HttpSession session = request.getSession();
			// if (session.getAttribute("loginUser") == null) {
			// response.sendRedirect("/TTMobil_Web/login.jsp");
			// } else {
			// CbUserBean user = (CbUserBean) session
			// .getAttribute("loginUser");
			// user.setSSDWNAME(UnicodeUtil.string2Unicode(user.getSHENG()
			// + user.getSHI() + user.getXIAN() + user.getXIAOQU()));
			// SimpleDateFormat sdf = new SimpleDateFormat(
			// "yyyy-MM-dd HH:mm:ss");
			// user.setDATETIME(sdf.format(new Date()));
			// user.setUSERNAME(UnicodeUtil.string2Unicode(user
			// .getACCOUNTNAME()));
			//
			// JSONObject json = JSONObject.fromObject(user);
			// log.info(json.toString());
			// response.getWriter().write(json.toString());
			// }
		} else if (method.equals("searchErroMessage")) {
			if (session.getAttribute("errorMessage") == null) {
				response.getWriter().write("0");
			} else {
				String errorMessage = (String) session
						.getAttribute("errorMessage");
				session.removeAttribute("errorMessage");
				response.getWriter().write(errorMessage);
			}
		}
		// 电表的第二次查询
		else if (method.equals("searchLastDbsj")) {
			//没用
			JSONObject json = new JSONObject();
			try {
				String dbId = request.getParameter("dbId");
				BiaoganManage biaoganManage = new BiaoganManage();
				List<AmmertdegreeBean> lastDbdl = biaoganManage
						.searchLastDbdl(dbId);
				JSONObject dbljson = JSONObject.fromObject(lastDbdl);
				log.info("dbljson=" + dbljson);
				json.put("flag", true);
				json.put("dbljson", dbljson);
				log.info(json.toString());
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				json.put("flag", false);
				response.getWriter().write(json.toString());
			}
		}

		else if (method.equals("searchLastDbdl")) {
			JSONObject json = new JSONObject();
			try {
				String dbId = request.getParameter("dbId");
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 定义日期显示格式
				String dateValue = sdf.format(new Date());
				BiaoganManage biaoganManage = new BiaoganManage();
				AmmertdegreeBean lastDbdl = biaoganManage.searchLastDbdl(dbId,
						dateValue);
				
				int ia = biaoganManage.searchLastDbdlbgdlpjz(dbId);
				DianbiaoBean lastDbdlDIANBIAO =biaoganManage.searchLastDbdldianbiao(dbId, dateValue);
				
				if (lastDbdl.getTHISDEGREE() == null||lastDbdl.getTHISDEGREE()=="") {
					lastDbdl.setTHISDEGREE(lastDbdlDIANBIAO.getCSDS());
					lastDbdl.setTHISDATETIME(lastDbdlDIANBIAO.getCSSYTIME());
					lastDbdl.setBEILV(String.valueOf(ia));
				}else{
					lastDbdl.setBEILV(String.valueOf(ia));
				}

				JSONObject dljson = JSONObject.fromObject(lastDbdl);
				json.put("dljson", dljson);
				json.put("flag", true);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(json.toString());
			response.getWriter().write(json.toString());
		} else if (method.equals("searchZdByXian")) {
			String xian = request.getParameter("xian");
			BiaoganManage biaoganManage = new BiaoganManage();
			List<ZhandianBean> zdList = biaoganManage.searchZdListByXian(xian);
			JSONArray jsons = JSONArray.fromObject(zdList);
			response.getWriter().write(jsons.toString());
		} else if (method.equals("searchDbByzdid")) {
			String zdid = request.getParameter("zdid");
			BiaoganManage biaoganManage = new BiaoganManage();
			List<DianbiaoBean> dbList = biaoganManage.searchDbListByZd(zdid);
			JSONArray jsons = JSONArray.fromObject(dbList);
			response.getWriter().write(jsons.toString());
		} else if (method.equals("searchLastCbDate")) {
			String dbid = request.getParameter("dbid");
			BiaoganManage biaoganManage = new BiaoganManage();
			String lastCbDate = biaoganManage.searchLastCbDate(dbid);
			JSONObject json = new JSONObject();
			json.put("LastCbDate", lastCbDate);
			response.getWriter().write(json.toString());
		} else if (method.equals("searchZdl")) {
			String dbid = request.getParameter("dbid");
			String cbTime = request.getParameter("cbTime");
			SimpleDateFormat sdf_ymdhms = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf_ym = new SimpleDateFormat("yyyy-MM");
			String yearmonth = null;
			try {
				yearmonth = sdf_ym.format(sdf_ymdhms.parse(cbTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			BiaoganManage biaoganManage = new BiaoganManage();
			String ZDL = biaoganManage.searchZdl(dbid, yearmonth);

			JSONObject json = new JSONObject();
			json.put("ZDL", ZDL);
			response.getWriter().write(json.toString());
		} else if (method.equals("searchDj")) {
			String dbid = request.getParameter("dbid");
			String cbTime = request.getParameter("cbTime");
			SimpleDateFormat sdf_ymdhms = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf_ym = new SimpleDateFormat("yyyy-MM");
			String yearmonth = null;
			try {
				yearmonth = sdf_ym.format(sdf_ymdhms.parse(cbTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			BiaoganManage biaoganManage = new BiaoganManage();
			String DANJIA = biaoganManage.searchDj(dbid, yearmonth);

			JSONObject json = new JSONObject();
			json.put("DANJIA", DANJIA);
			response.getWriter().write(json.toString());
		}else if(method.equals("searchZDID")){
			JSONObject json = new JSONObject();
			try {
				String userid = request.getParameter("userid");
				String zdname = request.getParameter("zdname");
				String zdid = userManage.searchZDID2(userid,zdname);
				json.put("zdid", zdid);
				json.put("flag", true);
				log.info(json.toString());
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				json.put("flag", false);
				response.getWriter().write(json.toString());
			}
		}
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 参考文章：
	 * http://developer.51cto.com/art/201111/305181.htm
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
