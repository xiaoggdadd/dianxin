package com.noki.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
   
import org.apache.commons.lang.StringEscapeUtils;

import com.noki.common.BZItem;
import com.noki.common.BZLineItem;
import com.noki.common.BaseInfoReq;
import com.noki.common.PayMentItem;
import com.noki.common.RelateSupplier;
import com.noki.common.RequestMessage;
import com.noki.common.RequestMessageCode;
import com.noki.common.RequestMessageMenu;
import com.noki.common.ResponseMessage;
import com.noki.common.ResponseMessageCode;
import com.noki.common.ResponseMessageMenu;

public class BZServiceImpl {

	
	public static void main(String[] args) throws Exception {
		String url="http://137.0.30.163:9898/mssproxy";
		String xml="";
		RequestMessage requestMessage = new RequestMessage();
/*		requestMessage.setProcessCode("");
		requestMessage.setProcessSuffixName("�ܺ�ϵͳ����");*/
		
		BZItem item = new BZItem();//���˶���
		
		item.setOtherSystemMainId("8a0c9eb85fb3fb0f015fb45664fa02b7");
		item.setAccount("71040677@SD");
		//item.setFillInName("����");
		item.setFillInOrgCode("2903000005");
		item.setSapCompayCode("B015");
		item.setEconomicItemCode("100803");
		//item.setEconomicItemName("���");
		item.setPaymentType("8");
		item.setHappenDate("2017-11-01");
		item.setBizType("2");
		item.setBudgetSet("2017-11");
		item.setIsStaffPayment("1");
		//item.setContractNo("");
		//item.setContractName("");
		item.setSum("200.02");
		item.setDesc("��ɽ���������̺���ʱ���㳡Ӫҵ�����");
		item.setInvoiceType("8");
		item.setIsNeedImage("1");
		
		BZLineItem lineItem = new BZLineItem();
		lineItem.setOtherSystemDetailId("2890");
		lineItem.setUsageCode("U165");
		//lineItem.setUsageName("");
		lineItem.setBudgetType("1");
		lineItem.setSum("10");
		lineItem.setDesc("�ɷݼ����̺��طֹ�˾�������ɱ������̺���ʱ���㳡Ӫҵ���̺���ʱ���㳡Ӫҵ�����");
		lineItem.setBudgetItemCode("CW1000");
		lineItem.setBudgetItemName("��Դ��");
		lineItem.setBudgetOrgCode("2901080000");
		lineItem.setBudgetOrgName("�̺��طֹ�˾����");
		lineItem.setSapCostCenterCode("A370108000");
		lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
		lineItem.setCount("3090.0");
		lineItem.setPrice("1.1");
		lineItem.setUnit("01");
		lineItem.setCurrency("CNY");
		lineItem.setExchangeRate("1.0");
		lineItem.setCurrencySum("3400.0");
		//lineItem.setChargecode("");
		
		List<BZLineItem> lineItems = new ArrayList<BZLineItem>();
		lineItems.add(lineItem);
		item.setLineItems(lineItems);
		
		RelateSupplier relateSupplier = new RelateSupplier();
		relateSupplier.setSupplierCode("G100211634");
		relateSupplier.setSupplierName("��˫");
		relateSupplier.setAccountType("2");
		relateSupplier.setSum("3400.0");
		relateSupplier.setInputTaxSum("0.0");
		
		List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();//��Ӧ�̱���
		relateSuppliers.add(relateSupplier);
		item.setRelateSuppliers(relateSuppliers);
		
		List<PayMentItem> payMents = new ArrayList<PayMentItem>();
		PayMentItem payMent = new PayMentItem();
		payMent.setEmployeeBankAccount("");
		payMent.setEmployeeName("");
		payMent.setBank("");
		payMent.setBankCode("");
		payMent.setPayeeCode("");
		payMent.setAccountType("");
		payMent.setPayeeType("");
		payMent.setRowno("");
		payMent.setBankAddress("");
		payMent.setProvince("");
		payMent.setCity("");
		payMent.setCurrency("");
		payMent.setSum("");
		payMents.add(payMent);
		
		item.setPayMentItems(payMents);
		
//		List<PayMent> payMents = new ArrayList<PayMent>();
//		PayMent payMent = new PayMent();
//		payMent.setEmployeeBankAccount(employeeBankAccount);
//		payMent.setEmployeeName(employeeName);
//		payMent.setBank(bank);
//		payMent.setBankCode(bankCode);
//		payMent.setPayeeCode(payeeCode);
//		payMent.setAccountType(accountType);
//		payMent.setPayeeType(payeeType);
//		payMent.setRowno(rowno);
//		payMent.setBankAddress(bankAddress);
//		payMent.setProvince(province);
//		payMent.setCity(city);
//		payMent.setCurrency(currency);
//		payMent.setSum(sum);
//		payMents.add(payMent);
//		
//		item.setPayMents(payMents);
//		
		
		
		item.setPickingMode("0");
		item.setBusinessHappenTimeFlag("2");
		item.setIsRealGift("0");
		item.setRealGiftTaxSum("0.0");
		item.setCurrency("CNY");
		
		List<BZItem> writeoffItems = new ArrayList<BZItem>();
		writeoffItems.add(item);
		
		requestMessage.setWriteoffItems(writeoffItems);
		/*bizMessage.setRequestMessage(requestMessage);
		MESSAGE.setBizMessage(bizMessage);*/
		
		//��װ���˱���ͷ��
		BaseInfoReq BASEINFO = makeInfo();
		//xml��ʽ����
	    xml=XmlTools.buildXml(BASEINFO,requestMessage);
	    System.out.println("======================���ͱ���======================��\n"+xml);
	    //���ͱ���
	    String resp=StringEscapeUtils.unescapeHtml(XmlTools.send(url,xml));
	    System.out.println("======================��Ӧ����======================��\n"+resp) ;
	    ResponseMessage responseMessage = SoapUtil.parseSoapMessage(resp);
	    System.out.println("111"+responseMessage.getItems().get(0).getOtherSystemMainId()+responseMessage.getItems().get(0).getResultCode()+responseMessage.getItems().get(0).getErrorMsg());
	    
		//���Ա���
	    ResponseMessage responseMessage1 =baoZhang(url,requestMessage);
	    //System.out.println("222"+responseMessage1.getItems().get(0).getOtherSystemMainId()+responseMessage1.getItems().get(0).getResultCode()+responseMessage1.getItems().get(0).getErrorMsg());	    
	
	  /*  //���Ի�ȡ������֯����
	    RequestMessageCode requestMessage2 = new RequestMessageCode();
	    List<CodeItem> items = new ArrayList<CodeItem>();
	    CodeItem codeItem = new CodeItem();
	    codeItem.setAccount("W0070077@SD");
	    items.add(codeItem);
	    requestMessage2.setItems(items);
	    ResponseMessageCode responseMessage2 = GetOrgCode(url,requestMessage2);

	    //���Ի�ȡ������֯����
	    RequestMessageMenu requestMessage3 = new RequestMessageMenu();
	    List<MenuItem> menuItems = new ArrayList<MenuItem>();
	    MenuItem menuItem = new MenuItem();
	    menuItem.setOtherSystemMainId("8a0c9eb85fb3fb0f015fb45664fa02b7"); //8a0c9eb85fb3fb0f015fb45664fa02b7
	    menuItem.setWriteoffInstanceCode("");
	    menuItems.add(menuItem);
	    requestMessage3.setItems(menuItems);
	    ResponseMessageMenu responseMessage3 = GetMenuStatus(url,requestMessage3);*/
	
	}
	
	/**
	 * ���˽ӿ�1
	 * @param 
	 * @return
	 *���ڣ�2017-12-09
	 * @throws Exception 
	 */
	public static ResponseMessage baoZhang(String url,RequestMessage requestMessage) throws Exception{
		String xml="";
		//��װ����ͷ��
		BaseInfoReq BASEINFO = makeInfo();
		//xml��ʽ����
	    xml=XmlTools.buildXml(BASEINFO,requestMessage);
	    System.out.println("xml>>>>"+xml);
		//���ͱ���
	    String resp=StringEscapeUtils.unescapeHtml(XmlTools.send(url,xml));
	    //�������صı���
	    ResponseMessage responseMessage = SoapUtil.parseSoapMessage(resp);
	    return responseMessage;
	}

	/**
	 * ��ȡ������֯����ӿ�
	 * @param 
	 * @return
	 *���ڣ�2017-12-09
	 * @throws Exception 
	 */
	public static ResponseMessageCode GetOrgCode(String url,RequestMessageCode requestMessage) throws Exception{
		String xml="";
		//��װ����ͷ��
		BaseInfoReq BASEINFO = makeInfoZZBM();
		//xml��ʽ����
	    xml=XmlTools.buildXmlZZBM(BASEINFO,requestMessage);
	    System.out.println("111   \n"+xml);
		//���ͱ���
	    String resp=StringEscapeUtils.unescapeHtml(XmlTools.send(url,xml));
	    System.out.println("222   \n"+resp);
	    //�������صı���
	    ResponseMessageCode responseMessage = SoapUtil.parseSoapMessageCode(resp);
	    return responseMessage;
	}
	
	/**
	 * ��ȡ���˵�״̬�ӿ�
	 * @param 
	 * @return
	 *���ڣ�2017-12-09
	 * @throws Exception 
	 */
	public static ResponseMessageMenu GetMenuStatus(String url,RequestMessageMenu requestMessage) throws Exception{
		String xml="";
		//��װ����ͷ��
		BaseInfoReq BASEINFO = makeInfoBZDZT();
		//xml��ʽ����
	    xml=XmlTools.buildXmlBZDZT(BASEINFO,requestMessage);
	    System.out.println("333   \n"+xml);
		//���ͱ���
	    String resp=StringEscapeUtils.unescapeHtml(XmlTools.send(url,xml));
	    
	    System.out.println("444   \n"+resp);
	    
	    //�������صı���
	    ResponseMessageMenu responseMessage = SoapUtil.parseSoapMessageMenu(resp);
	    return responseMessage;
	}
	
	/**
	 * ��װ���˽ӿڱ���ͷ��
	 * @param 
	 * @return
	 *���ڣ�2017-12-09
	 */
	private static BaseInfoReq makeInfo(){
		BaseInfoReq BASEINFO = new BaseInfoReq();
		String dateStr = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
		dateStr = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬Ҳ��ʹ�õ�ǰʱ���
		String msgId="NH_OP_AutoCreateWriteoff_";
		//msgid ��׺ ʱ���+6λ�����
		msgId=msgId+dateStr+"_"+(int)((Math.random()*9+1)*100000);
		BASEINFO.setMSGID(msgId);
		BASEINFO.setPMSGID("");
		BASEINFO.setSENDTIME(dateStr);
		BASEINFO.setS_PROVINCE("29");
		BASEINFO.setS_SYSTEM("NH");
		BASEINFO.setSERVICENAME("SI_CF_ESB_INTERGRATED_OUT_Syn_OP_AutoCreateWriteoff");
		BASEINFO.setT_PROVINCE("29");
		BASEINFO.setT_SYSTEM("CW-CFBZ-BFSS");
		BASEINFO.setRETRY("1");		
		return BASEINFO;
	}

	/**
	 * ��װ��ȡ������֯���뱨��ͷ��
	 * @param 
	 * @return
	 *���ڣ�2017-12-09
	 */
	private static BaseInfoReq makeInfoZZBM(){
		BaseInfoReq BASEINFO = new BaseInfoReq();
		String dateStr = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
		dateStr = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬Ҳ��ʹ�õ�ǰʱ���
		String msgId="NH_OP_GetWriteoffBaseData_";
		//msgid ��׺ ʱ���+6λ�����
		msgId=msgId+dateStr+"_"+(int)((Math.random()*9+1)*100000);
		BASEINFO.setMSGID(msgId);
		BASEINFO.setPMSGID("");
		BASEINFO.setSENDTIME(dateStr);
		BASEINFO.setS_PROVINCE("29");
		BASEINFO.setS_SYSTEM("NH");
		BASEINFO.setSERVICENAME("SI_CF_ESB_INTERGRATED_OUT_Syn_OP_GetWriteoffBaseData");
		BASEINFO.setT_PROVINCE("29");
		BASEINFO.setT_SYSTEM("CW-CFBZ-BFSS");
		BASEINFO.setRETRY("1");		
		return BASEINFO;
	}
	
	/**
	 * ��װ��ȡ���˵�״̬����ͷ��
	 * @param 
	 * @return
	 *���ڣ�2017-12-09
	 */
	private static BaseInfoReq makeInfoBZDZT(){
		BaseInfoReq BASEINFO = new BaseInfoReq();
		String dateStr = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
		dateStr = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬Ҳ��ʹ�õ�ǰʱ���
		String msgId="NH_OP_GainWriteoffInstStatus_";
		//msgid ��׺ ʱ���+6λ�����
		msgId=msgId+dateStr+"_"+(int)((Math.random()*9+1)*100000);
		BASEINFO.setMSGID(msgId);
		BASEINFO.setPMSGID("");
		BASEINFO.setSENDTIME(dateStr);
		BASEINFO.setS_PROVINCE("29");
		BASEINFO.setS_SYSTEM("NH");
		BASEINFO.setSERVICENAME("SI_CF_ESB_INTERGRATED_OUT_Syn_OP_GainWriteoffInstStatus");
		BASEINFO.setT_PROVINCE("29");
		BASEINFO.setT_SYSTEM("CW-CFBZ-BFSS");
		BASEINFO.setRETRY("1");		
		return BASEINFO;
	}
	
	//������Χϵͳid ����32λuuid
	  public String getUUID() {
	      UUID uuid  =  UUID.randomUUID(); 
	      String s = uuid.toString();
	      return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	  }
}
