package com.noki.biaogan.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import com.noki.app.DlOcrServer;

public class CBUserDlImageContentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String imgStr = request.getParameter("imgStr");
		//System.out.println("[imgStr]" + imgStr);
		String path = request.getRealPath("/TTMobil_Web/temp_image");
		boolean flag = generateImage(imgStr,path+"/"+UUID.randomUUID().toString()+".jpg");
		JSONObject json = new JSONObject();
		if(flag){
			String uuid = UUID.randomUUID().toString();
			DlOcrServer dlOcr = new DlOcrServer(uuid, imgStr);
			String a = dlOcr.getDlByOcr(imgStr);
			json.put("imgStr", a);
		}
		json.put("flag", flag);
		System.out.println(json.toString());
		response.getWriter().write(json.toString());

	}

	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author:
	 * @CreateTime:
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
