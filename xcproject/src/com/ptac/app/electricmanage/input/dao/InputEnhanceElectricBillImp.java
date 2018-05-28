package com.ptac.app.electricmanage.input.dao;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanageUtil.EnhanceCheckInputAvailable;

public class InputEnhanceElectricBillImp implements InputElectricBill {
	@SuppressWarnings("unchecked")
	Vector wrongContent = new Vector();

	@SuppressWarnings("unchecked")
	public Vector input(Vector content, CountForm cform, Account account,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// 错误数据保存
		Vector<String> wcell = new Vector<String>();
		// 转换成 行数组
		Object[] rows = content.toArray();
		int inputdata = rows.length;
		int inputerror = 0;
		int inputcount = 0;
		int inputlast = rows.length;
		session.setAttribute("inputdata", rows.length);
		for (int temp = 1; temp < rows.length; temp++) {
			// 每个行中的数据的遍历
			Object[] cells = ((Vector) rows[temp]).toArray();
			System.out.println(cells.length+"===========");
			wcell = new EnhanceCheckInputAvailable().inputCheck(cells, account);
			if (!wcell.isEmpty()) {
				wrongContent.add(wcell);
				inputerror++;
			}else{
				inputcount++;
			}
			inputlast = inputdata - inputerror -inputcount;
			if(session.getAttribute("inputerror")!=null||session.getAttribute("inputcount")!=null
					||session.getAttribute("inputlast")!=null){
				session.removeAttribute("inputerror");
				session.removeAttribute("inputcount");
				session.removeAttribute("inputlast");
			}
			session.setAttribute("inputerror", inputerror);
			session.setAttribute("inputcount", inputcount);
			session.setAttribute("inputlast", inputlast);

		}
		session.removeAttribute("inputerror");
		session.removeAttribute("inputcount");
		session.removeAttribute("inputlast");
		session.removeAttribute("inputdata");
		

		cform.setCg((content.size() - 1) - wrongContent.size());
		cform.setBcg(wrongContent.size());
		cform.setZg((content.size() - 1));

		System.out.println(cform.getCg() + "   " + cform.getBcg() + "   "
				+ cform.getZg());
		return wrongContent;
	}

}
