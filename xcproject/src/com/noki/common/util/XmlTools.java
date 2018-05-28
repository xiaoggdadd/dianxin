package com.noki.common.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;

import com.noki.common.BaseInfoReq;
import com.noki.common.I_REQUEST;
import com.noki.common.Message;

public class XmlTools {

	private static Provider prvd=null;
	private static final SSLHandler simpleVerifier=new SSLHandler();
	private static SSLSocketFactory sslFactory;

	public static String send(String url,String xml) throws Exception
	{
		OutputStream reqStream=null;
		InputStream resStream =null;
		URLConnection request = null;
		String respText=null;
		byte[] postData;
		try
		{
			postData = xml.getBytes("UTF-8");
			request = createRequest(url, "POST");
	
			request.setRequestProperty("Content-type", "application/tlt-notify");
			request.setRequestProperty("Content-length", String.valueOf(postData.length));
			request.setRequestProperty("Keep-alive", "false");
	
			reqStream = request.getOutputStream();
			reqStream.write(postData);
			reqStream.close();
	
			ByteArrayOutputStream ms = null;	
			resStream = request.getInputStream();
			ms = new ByteArrayOutputStream();
			byte[] buf = new byte[4096];
			int count;
			while ((count = resStream.read(buf, 0, buf.length)) > 0)
			{
				ms.write(buf, 0, count);
			}
			resStream.close();
			respText = new String(ms.toByteArray(), "UTF-8");
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			close(reqStream);
			close(resStream);
		}
		return respText;
	}
	
	private static URLConnection createRequest(String strUrl, String strMethod) throws Exception
	{
		
		URL url = new URL(strUrl);
		URLConnection conn = url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		if (conn instanceof HttpsURLConnection)
		{	
			HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
			httpsConn.setRequestMethod(strMethod);
			httpsConn.setSSLSocketFactory(XmlTools.getSSLSF());
			httpsConn.setHostnameVerifier(XmlTools.getVerifier());
		}
		else if (conn instanceof HttpURLConnection)
		{
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod(strMethod);
		}
		return conn;
	}
	/**
	 * 组装报账报文
	 * @param 
	 * @return
	 *日期：2017-12-09
	 */
	public static String buildXml(Object o1,Object o2)
	{
		XstreamUtil xs = new XstreamUtil();
		Message MESSAGE = new Message();
		String requestMessageXML=xs.object2Xml(o2,"requestMessage");
		MESSAGE.setBizMessage(requestMessageXML);
		I_REQUEST I_REQUEST = new I_REQUEST();
		I_REQUEST.setBASEINFO((BaseInfoReq)o1);
		I_REQUEST.setMESSAGE(MESSAGE);
		String xml="<?xml version='1.0'?><soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://services.allcommonwriteoff.ws.dakar.eshore.com/'><soapenv:Header></soapenv:Header><soapenv:Body><ser:OP_AutoCreateWriteoff>"
			+xs.object2Xml(I_REQUEST,"I_REQUEST")+"</ser:OP_AutoCreateWriteoff></soapenv:Body></soapenv:Envelope>";
		xml=xml.replaceAll("__", "_");
		return xml;
	}

	/**
	 * 组装查询报账组织编码报文
	 * @param 
	 * @return
	 *日期：2017-12-09
	 */
	public static String buildXmlZZBM(Object o1,Object o2)
	{
		XstreamUtil xs = new XstreamUtil();
		Message MESSAGE = new Message();
		String requestMessageXML=xs.object2Xml(o2,"requestMessage");
		MESSAGE.setBizMessage(requestMessageXML);
		I_REQUEST I_REQUEST = new I_REQUEST();
		I_REQUEST.setBASEINFO((BaseInfoReq)o1);
		I_REQUEST.setMESSAGE(MESSAGE);
		String xml="<?xml version='1.0'?><soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://services.allcommonwriteoff.ws.dakar.eshore.com/'><soapenv:Header></soapenv:Header><soapenv:Body><ser:OP_GetWriteoffBaseData>"
			+xs.object2Xml(I_REQUEST,"I_REQUEST")+"</ser:OP_GetWriteoffBaseData></soapenv:Body></soapenv:Envelope>";
		xml=xml.replaceAll("__", "_");
		return xml;
	}
	
	/**
	 * 组装获取报账单状态报文
	 * @param 
	 * @return
	 *日期：2017-12-09
	 */
	public static String buildXmlBZDZT(Object o1,Object o2)
	{
		XstreamUtil xs = new XstreamUtil();
		Message MESSAGE = new Message();
		String requestMessageXML=xs.object2Xml(o2,"requestMessage");
		MESSAGE.setBizMessage(requestMessageXML);
		I_REQUEST I_REQUEST = new I_REQUEST();
		I_REQUEST.setBASEINFO((BaseInfoReq)o1);
		I_REQUEST.setMESSAGE(MESSAGE);
		String xml="<?xml version='1.0'?><soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://services.allcommonwriteoff.ws.dakar.eshore.com/'><soapenv:Header></soapenv:Header><soapenv:Body><ser:OP_GainWriteoffInstStatus>"
			+xs.object2Xml(I_REQUEST,"I_REQUEST")+"</ser:OP_GainWriteoffInstStatus></soapenv:Body></soapenv:Envelope>";
		xml=xml.replaceAll("__", "_");
		return xml;
	}
	
	public static synchronized SSLSocketFactory getSSLSF() throws Exception
	{
		if(sslFactory!=null) return sslFactory; 
		SSLContext sc = prvd==null?SSLContext.getInstance("SSLv3"):SSLContext.getInstance("SSLv3");
		sc.init(null, new TrustManager[]{simpleVerifier}, null);
		sslFactory = sc.getSocketFactory();
		return sslFactory;
	}
	public static HostnameVerifier getVerifier()
	{
		return simpleVerifier;
	}
	private static class SSLHandler implements X509TrustManager,HostnameVerifier
	{	
		private SSLHandler()
		{
		}
		
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean verify(String arg0, SSLSession arg1)
		{
			return true;
		}
	}
	private static void close(InputStream c)
	{
		try
		{
			if(c!=null) c.close();
		}
		catch(Exception ex)
		{
			
		}
	}
	private static void close(OutputStream c)
	{
		try
		{
			if(c!=null) c.close();
		}
		catch(Exception ex)
		{
			
		}
	}
}
