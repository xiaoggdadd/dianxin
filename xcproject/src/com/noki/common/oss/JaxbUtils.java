package com.noki.common.oss;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

/**
 * javaBean��xml�ַ����໥ת��������
 * @author guol
 *
 */
public class JaxbUtils {
	public final static String CHARSET_NAME = "UTF-8";
	public static Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	/**
	 * javabeanת��Ϊxml�ַ���
	 * @param obj ʵ�����
	 * @param formatted �����Ƿ��ʽ��
	 * @param charsetName ���뷽ʽ
	 * @param fragment �Ƿ��б���ͷ trueȥ��
	 * @return
	 * @throws JAXBException
	 */
	public static String toXml(Object obj, boolean formatted, String charsetName, boolean fragment) throws JAXBException {
		JAXBContext jaxbContext = jaxbContextMap.get(obj.getClass().getName());
		if(jaxbContext == null){
			jaxbContext = JAXBContext.newInstance(obj.getClass());
			jaxbContextMap.put(obj.getClass().getName(), jaxbContext);
		}
		Marshaller marshaller = jaxbContext.createMarshaller();
		// output pretty printed
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);//��ʽ��xml
		marshaller.setProperty(Marshaller.JAXB_ENCODING, charsetName);//
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);//
		StringWriter writer = new StringWriter();
		try {
			marshaller.marshal(obj, writer);
			return writer.toString();
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	public static String toXml(Object obj) throws JAXBException {
		return toXml(obj, true, CHARSET_NAME, Boolean.TRUE);
	}
	
	/**
	 * xml�ַ���ת����Ӧ��Javabean
	 * @param xml �ַ���
	 * @param cls java�����
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T fromXml(String xml, Class<T> cls)
			throws JAXBException {
		JAXBContext jaxbContext = jaxbContextMap.get(cls.getName());
		if(jaxbContext == null){
			jaxbContext = JAXBContext.newInstance(cls);
			jaxbContextMap.put(cls.getName(), jaxbContext);
		}
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = null;
		try {
			reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}
}
