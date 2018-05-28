package com.noki.common.util;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.noki.common.BZItemRsp;
import com.noki.common.CodeItemRsp;
import com.noki.common.MenuItemRsp;
import com.noki.common.OrgAndRole;
import com.noki.common.ResponseMessage;
import com.noki.common.ResponseMessageCode;
import com.noki.common.ResponseMessageMenu;

public class SoapUtil {
    
    public static void main(String[] args) {
        System.out.println("��ʼ����soap...");

        String deptXML = "<SOAP:Envelope xmlns:SOAP='http://schemas.xmlsoap.org/soap/envelope/'><SOAP:Header/><SOAP:Body><ns2:OP_AutoCreateWriteoffResponse xmlns:ns2='http://services.allcommonwriteoff.ws.dakar.eshore.com/' xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'><E_RESPONSE><BASEINFO><MSGID>NH_OP_AutoCreateWriteoff_20171214110638_319520</MSGID><PMSGID/><RETRY>1</RETRY><SENDTIME>20171214111608</SENDTIME><SERVICENAME>SI_CF_ESB_INTERGRATED_OUT_Syn_OP_AutoCreateWriteoff</SERVICENAME><S_PROVINCE>29</S_PROVINCE><S_SYSTEM>CW-CFBZ-BFSS</S_SYSTEM><T_PROVINCE>29</T_PROVINCE><T_SYSTEM>NH</T_SYSTEM></BASEINFO><MESSAGE><bizMessage><![CDATA[<responseMessage><TYPE>S</TYPE><items><item><otherSystemMainId>8a0c9eb85fb3fb0f015fb45664fa02b7</otherSystemMainId><resultCode>1</resultCode></item></items></responseMessage>]]></bizMessage></MESSAGE></E_RESPONSE></ns2:OP_AutoCreateWriteoffResponse></SOAP:Body></SOAP:Envelope>";
        //String deptXML = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP:Header/><SOAP:Body><ns:OP_SDMS_Consume_Material_SynResponse xmlns:ns=\"http://toSDMS.material.service.ffcs.com\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><ns:return><ns:BASEINFO><MSGID>?</MSGID><PMSGID>?</PMSGID><SENDTIME>20140212094609</SENDTIME><S_PROVINCE>?</S_PROVINCE><S_SYSTEM>?</S_SYSTEM><SERVICENAME>?</SERVICENAME><T_PROVINCE>?</T_PROVINCE><T_SYSTEM>?</T_SYSTEM><RETRY>?</RETRY></ns:BASEINFO><ns:MESSAGE><RESULT>E</RESULT><REMARK/><XMLDATA><![CDATA[<response><RESULT>E</RESULT><MESSAGE>ƽ̨ϵͳ����ʱ�����쳣!����ӿڽ������ݳ���!</MESSAGE></response>]]></XMLDATA></ns:MESSAGE></ns:return></ns:OP_SDMS_Consume_Material_SynResponse></SOAP:Body></SOAP:Envelope>";
        String deptXML1 = "<SOAP:Envelope xmlns:SOAP='http://schemas.xmlsoap.org/soap/envelope/'><SOAP:Header/><SOAP:Body><ns2:OP_GainWriteoffInstStatusResponse xmlns:ns2='http://services.allcommonwriteoff.ws.dakar.eshore.com/' xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'><E_RESPONSE><BASEINFO><MSGID>NH_OP_GainWriteoffInstStatus_20171215165927_556470</MSGID><PMSGID/><RETRY>1</RETRY><SENDTIME>20171215170905</SENDTIME><SERVICENAME>SI_CF_ESB_INTERGRATED_OUT_Syn_OP_GainWriteoffInstStatus</SERVICENAME><S_PROVINCE>29</S_PROVINCE><S_SYSTEM>CW-CFBZ-BFSS</S_SYSTEM><T_PROVINCE>29</T_PROVINCE><T_SYSTEM>NH</T_SYSTEM></BASEINFO><MESSAGE><bizMessage><![CDATA[<responseMessage><TYPE>S</TYPE><items><item><otherSystemMainId>8a0c9eb85fb3fb0f0115fb45664fa02b7</otherSystemMainId><status>-3</status><abstract_>��Χϵͳ[NH],��Χϵͳ����Id[8a0c9eb85fb3fb0f0115fb45664fa02b7]δ�ҵ���Ӧ�ı��˼�¼</abstract_></item></items></responseMessage>]]></bizMessage></MESSAGE></E_RESPONSE></ns2:OP_GainWriteoffInstStatusResponse></SOAP:Body></SOAP:Envelope>";
        String deptXML2 ="<SOAP:Envelope xmlns:SOAP='http://schemas.xmlsoap.org/soap/envelope/'><SOAP:Header/><SOAP:Body><ns2:OP_GainWriteoffInstStatusResponse xmlns:ns2='http://services.allcommonwriteoff.ws.dakar.eshore.com/' xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'><E_RESPONSE><BASEINFO><MSGID>NH_OP_GainWriteoffInstStatus_20171215170932_714815</MSGID><PMSGID/><RETRY>1</RETRY><SENDTIME>20171215171903</SENDTIME><SERVICENAME>SI_CF_ESB_INTERGRATED_OUT_Syn_OP_GainWriteoffInstStatus</SERVICENAME><S_PROVINCE>29</S_PROVINCE><S_SYSTEM>CW-CFBZ-BFSS</S_SYSTEM><T_PROVINCE>29</T_PROVINCE><T_SYSTEM>NH</T_SYSTEM></BASEINFO><MESSAGE><bizMessage><![CDATA[<responseMessage><TYPE>S</TYPE><items><item><otherSystemMainId>8a0c9eb85fb3fb0f015fb45664fa02b7</otherSystemMainId><status>2</status><writeoffInstanceCode>TYA01529011000051712000002</writeoffInstanceCode></item></items></responseMessage>]]></bizMessage></MESSAGE></E_RESPONSE></ns2:OP_GainWriteoffInstStatusResponse></SOAP:Body></SOAP:Envelope>";
        String deptXML3 = "<SOAP:Envelope xmlns:SOAP='http://schemas.xmlsoap.org/soap/envelope/'><SOAP:Header/><SOAP:Body><ns2:OP_GetWriteoffBaseDataResponse xmlns:ns2='http://services.allcommonwriteoff.ws.dakar.eshore.com/' xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'><E_RESPONSE><BASEINFO><MSGID>NH_OP_GetWriteoffBaseData_20171215165859_320201</MSGID><PMSGID/><RETRY>1</RETRY><SENDTIME>20171215170837</SENDTIME><SERVICENAME>SI_CF_ESB_INTERGRATED_OUT_Syn_OP_GetWriteoffBaseData</SERVICENAME><S_PROVINCE>29</S_PROVINCE><S_SYSTEM>CW-CFBZ-BFSS</S_SYSTEM><T_PROVINCE>29</T_PROVINCE><T_SYSTEM>NH</T_SYSTEM></BASEINFO><MESSAGE><bizMessage><![CDATA[<responseMessage><TYPE>S</TYPE><items><item><principalAccount>W0070077@SD</principalAccount><principalName>��Ⱥ</principalName><orgAndRoles><orgAndRole><orgCode>2902000005</orgCode><orgName>��������ά����</orgName><roleId>2902000005-EmployeeWriteoffor</roleId><roleName>������</roleName><description>������</description></orgAndRole></orgAndRoles></item></items></responseMessage>]]></bizMessage></MESSAGE></E_RESPONSE></ns2:OP_GetWriteoffBaseDataResponse></SOAP:Body></SOAP:Envelope>";
        //WebserviceResultBean ret = parseSoapMessage(deptXML);
        try {
            SOAPMessage msg = formatSoapString(deptXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            PrintBody(iterator, null);
            
            ResponseMessage responseMessage = parseSoapMessage(deptXML);
            ResponseMessageMenu responseMessageMenu = parseSoapMessageMenu(deptXML1);
            ResponseMessageMenu responseMessageMenu1 = parseSoapMessageMenu(deptXML2);
            ResponseMessageCode responseMessageCode = parseSoapMessageCode(deptXML3);
            System.out.println("222");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("����soap����...");
    }

    /**
     * ��soap�ַ�����ʽ��ΪSOAPMessage
     * 
     * @param soapString
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    private static SOAPMessage formatSoapString(String soapString) {
        MessageFactory msgFactory;
        try {
            msgFactory = MessageFactory.newInstance();
            SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(),
                    new ByteArrayInputStream(soapString.getBytes("UTF-8")));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ����soapXML
     * @param soapXML
     * @return
     */
    public static ResponseMessage parseSoapMessage(String soapXML) {
    	ResponseMessage resultBean = new ResponseMessage();
        try {
            SOAPMessage msg = formatSoapString(soapXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            parse(iterator,resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }
        
    /**
     * ����soap xml
     * @param iterator
     * @param resultBean
     * @return 
     * @throws DocumentException 
     */
    private static void parse(Iterator<SOAPElement> iterator,ResponseMessage resultBean) throws DocumentException {
        while (iterator.hasNext()) {
            SOAPElement element = iterator.next();
            if ("BASEINFO".equals(element.getNodeName())) {
                continue;
            } else if ("MESSAGE".equals(element.getNodeName())) {
                Iterator<SOAPElement> it = element.getChildElements();
                SOAPElement el = null;
                while (it.hasNext()) {
                    el = it.next();
                    if ("bizMessage".equals(el.getLocalName())) {
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                        String resp = el.getValue();
                        Document dom=DocumentHelper.parseText(resp);  
                        Element root=dom.getRootElement();  
                        resultBean.setTYPE(root.element("TYPE").getText());
                        //listNodes(root);
                        Element e = root.element("items");  
                        Element ele = e.element("item"); 
                        String otherSystemMainId = ele.element("otherSystemMainId").getText();
                        String resultCode = ele.element("resultCode").getText();
                        String errorMsg ="";
                        if(resultCode.equals("0")){
                        	errorMsg = ele.element("errorMsg").getText();	
                        }
                        List<BZItemRsp> items = new ArrayList<BZItemRsp>();
                        BZItemRsp item = new BZItemRsp();
                        item.setResultCode(resultCode);
                        item.setOtherSystemMainId(otherSystemMainId);
                        item.setErrorMsg(errorMsg);
                        items.add(item);
                        resultBean.setItems(items);
                    } 
                }
            } else if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
                parse(element.getChildElements(), resultBean);
            }
        }
    }

    /**
     * ����soapXML
     * @param soapXML
     * @return
     */
    public static ResponseMessageCode parseSoapMessageCode(String soapXML) {
    	ResponseMessageCode resultBean = new ResponseMessageCode();
        try {
            SOAPMessage msg = formatSoapString(soapXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            parseCode(iterator,resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * ����soap xml
     * @param iterator
     * @param resultBean
     * @return 
     * @throws DocumentException 
     */
    private static void parseCode(Iterator<SOAPElement> iterator,ResponseMessageCode resultBean) throws DocumentException {
        while (iterator.hasNext()) {
            SOAPElement element = iterator.next();
            if ("BASEINFO".equals(element.getNodeName())) {
                continue;
            } else if ("MESSAGE".equals(element.getNodeName())) {
                Iterator<SOAPElement> it = element.getChildElements();
                SOAPElement el = null;
                while (it.hasNext()) {
                    el = it.next();
                    if ("bizMessage".equals(el.getLocalName())) {
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                        String resp = el.getValue();
                        Document dom=DocumentHelper.parseText(resp);  
                        Element root=dom.getRootElement();
                        String type = root.element("TYPE").getText();
                        resultBean.setTYPE(type);
                        String errorMsg ="";
                        if(type.equals("E")){
                        	errorMsg = root.element("errorMsg").getText();
                        	resultBean.setErrorMsg(errorMsg);
                        }else{
                            Element e = root.element("items");  
                            Element ele = e.element("item"); 
                            List<CodeItemRsp> items = new ArrayList<CodeItemRsp>();
                            CodeItemRsp item = new CodeItemRsp();
                            String principalAccount = ele.element("principalAccount").getText();
                            String principalName = ele.element("principalName").getText();
                            item.setPrincipalAccount(principalAccount);
                            item.setPrincipalName(principalName);
                            
                            Element elem = ele.element("orgAndRoles"); 
                            Element eleme = elem.element("orgAndRole");
                            List<OrgAndRole> orgAndRoles = new ArrayList<OrgAndRole>();
                            OrgAndRole orgAndRole = new OrgAndRole();
                            String orgCode = eleme.element("orgCode").getText();
                            String orgName = eleme.element("orgName").getText();
                            String roleId = eleme.element("roleId").getText();
                            String roleName = eleme.element("roleName").getText();
                            String description = eleme.element("description").getText();
                            
                            orgAndRole.setOrgCode(orgCode);
                            orgAndRole.setOrgName(orgName);
                            orgAndRole.setRoleId(roleId);
                            orgAndRole.setRoleName(roleName);
                            orgAndRole.setDescription(description);
                            
                            Element elCostCenterCode = eleme.element("costCenterCode");
                            Element elCostCenterName = eleme.element("costCenterCode");
                            if(elCostCenterCode != null){
                            	String costCenterCode = eleme.element("costCenterCode").getText();
                            	orgAndRole.setCostCenterCode(costCenterCode);
                            }
                            if(elCostCenterName != null){
                            	String costCenterName = eleme.element("costCenterName").getText();
                            	orgAndRole.setCostCenterName(costCenterName);
                            }

                            orgAndRoles.add(orgAndRole);
                            item.setOrgAndRoles(orgAndRoles);
                            items.add(item);
                            resultBean.setItems(items);	
                        }
                    } 
                }
            } else if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
            	parseCode(element.getChildElements(), resultBean);
            }
        }
    }
    
    /**
     * ����soapXML
     * @param soapXML
     * @return
     */
    public static ResponseMessageMenu parseSoapMessageMenu(String soapXML) {
    	ResponseMessageMenu resultBean = new ResponseMessageMenu();
        try {
            SOAPMessage msg = formatSoapString(soapXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            parseMenu(iterator,resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * ����soap xml
     * @param iterator
     * @param resultBean
     * @return 
     * @throws DocumentException 
     */
    private static void parseMenu(Iterator<SOAPElement> iterator,ResponseMessageMenu resultBean) throws DocumentException {
        while (iterator.hasNext()) {
            SOAPElement element = iterator.next();
            if ("BASEINFO".equals(element.getNodeName())) {
                continue;
            } else if ("MESSAGE".equals(element.getNodeName())) {
                Iterator<SOAPElement> it = element.getChildElements();
                SOAPElement el = null;
                while (it.hasNext()) {
                    el = it.next();
                    if ("bizMessage".equals(el.getLocalName())) {
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                        String resp = el.getValue();
                        Document dom=DocumentHelper.parseText(resp);  
                        Element root=dom.getRootElement();
                        String type = root.element("TYPE").getText();
                        resultBean.setTYPE(type);
                        String errorMsg ="";
                        if(type.equals("E")){
                        	errorMsg = root.element("errorMsg").getText();
                        	resultBean.setErrorMsg(errorMsg);
                        }else{
                            Element e = root.element("items");  
                            Element ele = e.element("item"); 
                            List<MenuItemRsp> items = new ArrayList<MenuItemRsp>();
                            MenuItemRsp item = new MenuItemRsp();
                            String otherSystemMainId = ele.element("otherSystemMainId").getText();
                            String status = ele.element("status").getText();
                            item.setOtherSystemMainId(otherSystemMainId);
                            item.setStatus(status);
                            if(status.equals("2")){
                            	String writeoffInstanceCode = ele.element("writeoffInstanceCode").getText();
                            	item.setWriteoffInstanceCode(writeoffInstanceCode);
                            }else if(status.equals("-3")){
                            	String abstract_ = ele.element("abstract_").getText();
                            	item.setAbstract_(abstract_);
                            }else if(status.equals("3")){
                            	String writeoffInstanceCode = ele.element("writeoffInstanceCode").getText();
                            	String sapCertificateCode = ele.element("sapCertificateCode").getText();
                            	item.setWriteoffInstanceCode(writeoffInstanceCode);
                            	item.setSpCertificateCode(sapCertificateCode);
                            }                           
                            items.add(item);
                            resultBean.setItems(items);	
                        }
                    } 
                }
            } else if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
            	parseMenu(element.getChildElements(), resultBean);
            }
        }
    }
    
    /** 
     * ������ǰ�ڵ�Ԫ�����������(Ԫ�ص�)�ӽڵ� 
     *  
     * @param node 
     */  
    public static void listNodes(Element node) {  
        System.out.println("��ǰ�ڵ�����ƣ���" + node.getName());  
        // ��ȡ��ǰ�ڵ���������Խڵ�  
        List<Attribute> list = node.attributes();  
        // �������Խڵ�  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("�ı����ݣ�������" + node.getText());  
        }  
  
        // ��ǰ�ڵ������ӽڵ������  
        Iterator<Element> it = node.elementIterator();  
        // ����  
        while (it.hasNext()) {  
            // ��ȡĳ���ӽڵ����  
            Element e = it.next();  
            // ���ӽڵ���б���  
            listNodes(e);  
        }  
    }  
    private static void PrintBody(Iterator<SOAPElement> iterator, String side) {
        while (iterator.hasNext()) {
            SOAPElement element = (SOAPElement) iterator.next();
            System.out.println("Local Name:" + element.getLocalName());
            System.out.println("Node Name:" + element.getNodeName());
            System.out.println("Tag Name:" + element.getTagName());
            System.out.println("Value:" + element.getValue());
            if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
                PrintBody(element.getChildElements(), side + "-----");
            }
        }
    }
}