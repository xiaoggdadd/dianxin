package com.noki.common.oss.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.noki.common.oss.DateUtils;
import com.noki.common.oss.FileUtils;
import com.noki.common.oss.IntUtils;
import com.noki.common.oss.JaxbUtils;
import com.noki.common.oss.dao.OSSAccountDao;
import com.noki.common.oss.model.OSSAccount;
import com.noki.common.oss.model.RequestMessage;
import com.noki.common.oss.model.ResponseMessage;


public class OneOSSCustomerService {

	public static final String OSS_URL = "http://137.0.28.20:9911/IomInterface/services/OneOSSCustomerService";
	
	public static final String OSS_QUERY_SYSTEM = "��������";
	
	public ResponseMessage soapRequest(String url) throws Exception{
		
		RequestMessage reqMessage = new RequestMessage();
		reqMessage.setQuerytime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		reqMessage.setQuerysystem(OSS_QUERY_SYSTEM);
		String xml = buildXml(reqMessage);
		//���ͱ���
	    //String resp = StringEscapeUtils.unescapeHtml(XmlTools.send(url, xml));
	    String resp = send(url, xml);//��Ӧ��������û��<![CDATA[]]>, unescapeHtmlת����������<?xml version="1.0">����ʧ�� 
	    System.out.println("��Ӧ����: " + resp);
	    ResponseMessage respMessage  = new ResponseMessage();
	    if(resp != null){
	    	respMessage = parseXml(resp);
	    }
		return respMessage;
	}

	public String send(String url, String soapXml) throws IOException{
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("SOAPAction", "");//axis ��������
		byte[] postData = soapXml.getBytes("UTF-8");
		ByteArrayInputStream content = new ByteArrayInputStream(postData, 0, postData.length);
		InputStreamRequestEntity requestEntity = new InputStreamRequestEntity(content, postData.length, "application/soap+xml;charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
		String respText=null;
		try {
			HttpClient httpClient = new HttpClient();
			int status = httpClient.executeMethod(postMethod);
			if(status == HttpStatus.SC_OK){
				respText = postMethod.getResponseBodyAsString();
			}
		}finally {
			postMethod.releaseConnection();
		}
		return respText;
		
	}

	
    /**
     * ����soap������
     * @param o
     * @return
     * @throws JAXBException
     */
	public String buildXml(Object o) throws JAXBException{
		String xml = JaxbUtils.toXml(o);
		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:one='http://oneoss.sando.ztesoft.com'><soapenv:Header/>" +
				"<soapenv:Body><one:staffSync><one:requestXML><![CDATA[<?xml version='1.0' encoding='UTF-8'?>");
		sb.append(xml);
		sb.append("]]></one:requestXML></one:staffSync></soapenv:Body></soapenv:Envelope>");
		return sb.toString();
	}

	/**
	 * dom4j����soap����
	 * @param soapXML
	 * @return
	 * @throws DocumentException
	 * @throws JAXBException
	 */
	public ResponseMessage parseXml(String soapXml) throws DocumentException, JAXBException {
		Document document = DocumentHelper.parseText(soapXml);
		Element root = document.getRootElement(); 
		return JaxbUtils.fromXml(root.getStringValue(), ResponseMessage.class);
	}
	
	/**
	 * ����Զ���ļ�������
	 * @param url Զ���ļ�
	 * @param filename �ļ���
	 * @param localDir �ļ�·��
	 * @return
	 * @throws IOException
	 */
	public String dowload(String url, String filename, String localDir) throws IOException{
		PostMethod postMethod = null;
		try {
			HttpClient httpClient = new HttpClient();
			postMethod = new PostMethod(url);
			int status = httpClient.executeMethod(postMethod);
			if(status == HttpStatus.SC_OK){
				InputStream ins = postMethod.getResponseBodyAsStream();
				// ���浽����Ŀ¼
				File dirfile = new File(localDir);
				if(!dirfile.exists()){
					dirfile.mkdirs();
				}
				File localfile = new File(localDir, filename);
				OutputStream os = new FileOutputStream(localfile);
				IOUtils.copy(ins, os);
				return localfile.getPath();
			}
		}finally{
			postMethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * ͬ��ossaccount����
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String synOssAccount(ArrayList<?> list) throws Exception{
	    if(list == null || list.size()==0){
	    	return "error";
	    }
		//���ݷ�װ��ҵ��ʵ����
		//ArrayList<OSSAccount> addOssAccounts = new ArrayList<OSSAccount>();//��װ��������
		//ArrayList<OSSAccount> updateOssAccounts = new ArrayList<OSSAccount>();//��װ�޸�����
		ArrayList<OSSAccount> deleteOssAccounts = new ArrayList<OSSAccount>();//��װɾ������
		ArrayList<OSSAccount> ossAccounts = new ArrayList<OSSAccount>();//��װ��Ч����
		for (int i = 1; i < list.size(); i++) { //i=1  ȥ������
			String data = (String)list.get(i);
			String[] columns = data.split(",", -1);//�ָ�������������ָ׼ȷ, ��Ҫ���õڶ�������
			OSSAccount ossAccount = new OSSAccount();
			try {
				//AREA_NAME	AREA_ID	ORG_NAME	STAFF_ID	STAFF_NAME	USERNAME
				ossAccount.setAreaName(columns[0]);
				ossAccount.setAreaId(IntUtils.toInt(columns[1]));
				ossAccount.setOrgName(columns[2]);
				ossAccount.setStaffId(IntUtils.toInt(columns[3]));
				ossAccount.setStaffName(columns[4]);
				ossAccount.setUsername(columns[5]);
				//PASS_WORD	MOBILE_TEL	EMAIL	OFFICE_TEL	JOB_NAME	SEX	NATION	
				ossAccount.setPassWord(columns[6]);
				ossAccount.setMobileTel(columns[7]);
				ossAccount.setEmail(columns[8]);
				ossAccount.setOfficeTel(columns[9]);
				ossAccount.setJobName(columns[10]);
				ossAccount.setSex(columns[11]);
				//BIRTHDAY	ID_CARD	STAFF_LEVEL	WORK_AREA	BEGINWORKDATE	NOWWORKDATE	
				ossAccount.setNation(columns[12]);
				ossAccount.setBirthday(DateUtils.toDate(columns[13]));
				ossAccount.setIdCard(columns[14]);
				ossAccount.setStaffLevel(columns[15]);
				ossAccount.setWorkArea(columns[16]);
				ossAccount.setBeginworkdate(DateUtils.toDate(columns[17]));
				ossAccount.setNowworkdate(columns[18]);
				//WORK_TYPE	HOME_ADDRESS	EDU_LEVEL	GETEDUDATE	STATE	IS_DW	
				ossAccount.setWorkType(columns[19]);
				ossAccount.setHomeAddress(columns[20]);
				ossAccount.setEduLevel(columns[21]);
				ossAccount.setGetedudate(DateUtils.toDate(columns[22]));
				ossAccount.setState(IntUtils.toInt(columns[23]));
				ossAccount.setIsDw(IntUtils.toInt(columns[24]));
				//UPDATE_DATE	CREATE_DATE	ISONWORKTEXT	AREA_CODE	ORG_ID	ORG_CODE
				ossAccount.setUpdateDate(DateUtils.toDateLong(columns[25]));
				ossAccount.setCreateDate(DateUtils.toDateLong(columns[26]));
				ossAccount.setIsonworktext(columns[27]);
				ossAccount.setAreaCode(columns[28]);
				ossAccount.setOrgId(Integer.parseInt(columns[29]));
				ossAccount.setOrgCode(columns[30]);
				
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			//��װ����ͬ�ļ�����
			if(ossAccount.getState() == 1){//add update
				ossAccounts.add(ossAccount);
			}else if(ossAccount.getState() == 0 || ossAccount.getState() == 9){//delete
				deleteOssAccounts.add(ossAccount);
			}
		}
		//ִ�����ݿ����
		int addOssAccountNum = 0;
		int updateOssAccountNum = 0;
		int deleteOssAccountNum = 0;
		long startTime = System.currentTimeMillis();//��ȡ��ǰʱ��
		OSSAccountDao dao = new OSSAccountDao();
		ArrayList<OSSAccount> dbOssAccounts = dao.queryOssAccount();//state=1
		//add update
		for(OSSAccount ossAccount: ossAccounts){
			boolean isExist = false;
			boolean isUpdate = false;
			//У���Ƿ���������
			for(OSSAccount dbOssAccount: dbOssAccounts){
				if(dbOssAccount.getStaffId() == ossAccount.getStaffId()){
					isExist = true;
					break;
				}
			}
			if(!isExist){
				dao.addOssAccount(ossAccount);
				addOssAccountNum++;
			}
			//У���Ƿ��޸�����
			for(OSSAccount dbOssAccount: dbOssAccounts){
				if(dbOssAccount.getStaffId() == ossAccount.getStaffId() 
						&& dbOssAccount.getUpdateDate().before(ossAccount.getUpdateDate())){
					isUpdate = true;
					break;
				}
			}
			if(isUpdate){
			    dao.updateOSSAccount(ossAccount);
			    updateOssAccountNum++;
			}
		}
		//delete
		for(OSSAccount deleteOssAccount: deleteOssAccounts){
			boolean isExist = false;
			for(OSSAccount dbOssAccount: dbOssAccounts){
				if(dbOssAccount.getStaffId() == deleteOssAccount.getStaffId()){
					isExist = true;
					break;
				}
			}
			if(isExist){
				dao.reviseState(deleteOssAccount.getStaffId(), "0");//ɾ����������
				deleteOssAccountNum++;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("-����������" + addOssAccountNum);
		System.out.println("-�޸�������" + updateOssAccountNum);
		System.out.println("-ɾ��������" + deleteOssAccountNum);
		System.out.println("ͬ�����ݿ����ʱ��" + (endTime-startTime)/1000);
		return "ok";
		
	}

	public HttpClient getHttpClient(){
		HttpClient httpClient = null;//HttpClients.custom().setSSLSocketFactory(sslSocketFactory);
		return httpClient;
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void execute() {
		String saveDir = "OSS��Ա����ͬ��";
		try {
			ResponseMessage resp = soapRequest(OSS_URL);
			if(resp.getReturncode().equals("0")){//����ɹ�
				String cvsurl = resp.getCsvurl();
				String filename = cvsurl.substring(cvsurl.lastIndexOf("/") + 1, cvsurl.length());
				System.out.println("saveDir: " + saveDir);
				System.out.println("filename: " + filename);
				String pathname = dowload(cvsurl, filename, saveDir);
				String result = synOssAccount(FileUtils.readcsv(pathname));
				if(result.equals("ok")){
					System.out.println("ͬ���ɹ�");
				}
			}
		} catch (Exception e) {
			System.out.println("oss����������ʧ��" + e);
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		OneOSSCustomerService oo = new OneOSSCustomerService();
		
		String xml = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
				   "<soapenv:Body>"+ 
				      "<staffSyncResponse xmlns='http://oneoss.sando.ztesoft.com'>"+
				         "<staffSyncReturn><![CDATA[<?xml version='1.0' encoding='UTF-8'?>"+
				"<root><returntime>2018-03-14 13:55:51</returntime><returncode>0</returncode><faultdesc>����ɹ�</faultdesc><csvurl>http://137.0.25.96:9911/IomInterface/files/2018031413.csv</csvurl></root>]]></staffSyncReturn>"+
				      "</staffSyncResponse>"+
				   "</soapenv:Body>"+
				"</soapenv:Envelope>";
		

		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement(); 
		System.out.println(root.getStringValue());
		
		ResponseMessage resp = JaxbUtils.fromXml(root.getStringValue(), ResponseMessage.class);
		System.out.println("returntime = "+resp.getReturntime());
		System.out.println("returncode = " + resp.getReturncode());
		System.out.println("faultdesc = " + resp.getFaultdesc());
		System.out.println("csvurl = "+resp.getCsvurl());
		
		//�����ļ�
		//String pathname = oo.dowload("http://127.0.0.1:8080/tieta/aa.csv", "1212.csv","d:\\aa\\");
		//ArrayList list = FileUtils.readcsv("e:/2018032015.csv");
		//oo.synOssAccount(list);
		//oo.test();
		
		
	}
	
}
