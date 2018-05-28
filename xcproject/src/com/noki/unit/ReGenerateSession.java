package com.noki.unit;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReGenerateSession {
	/** 
	 * ����sessionid��ԭsession�е������Զ�ת�浽��session�� 
	 * @param request 
	 */  
	public  void reGenerateSessionId(HttpServletRequest request){  
	      
	    HttpSession session = request.getSession();  
	      
	    //���Ƚ�ԭsession�е�����ת����һ��ʱmap��  
	    Map<String,Object> tempMap = new HashMap<String, Object>();  
	    Enumeration<String> sessionNames = session.getAttributeNames();  
	    while(sessionNames.hasMoreElements()){  
	        String sessionName = sessionNames.nextElement();  
	        tempMap.put(sessionName, session.getAttribute(sessionName));  
	    }  
	      
	    //ע��ԭsession��Ϊ��������sessionId  
	    session.invalidate();  
	      
	    //����ʱmap�е�����ת������session  
	    session = request.getSession();  
	    for(Map.Entry<String, Object> entry : tempMap.entrySet()){  
	        session.setAttribute(entry.getKey(), entry.getValue());  
	    }  
	}  
}
