package com.ptac.app.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.noki.jizhan.model.DianBiaoErrorBean;

public class importexceldaoutil {
	/** 
     * �� Map����ת��ΪJavaBean   �˷����Ѿ�����ͨ��
     * @author wyply115 
     * @param type Ҫת�������� 
     * @param map 
     * @return Object����
     * @version 2016��3��20�� 11:03:01
     */  
    @SuppressWarnings({ "unchecked", "hiding", "rawtypes" })
	public DianBiaoErrorBean convertMap2Bean(String asd,Map map, Class clazz) throws Exception {  
         if(map==null || map.size()==0){
             return null;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);  
        DianBiaoErrorBean bean=new DianBiaoErrorBean();
         bean = (DianBiaoErrorBean)clazz.newInstance(); 
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
        for (int i = 0, n = propertyDescriptors.length; i <n ; i++) {  
            PropertyDescriptor descriptor = propertyDescriptors[i];  
            String propertyName = descriptor.getName(); 
            String upperPropertyName = propertyName.toUpperCase();
            if (map.containsKey(upperPropertyName.toLowerCase())) { 
                Object value = map.get(upperPropertyName.toLowerCase());  
                //����������ᱨ�������Ͳ�ƥ��Ĵ���
                BeanUtils.copyProperty(bean, propertyName, value);
//�����������int�����ͻᱨ�������Ͳ�ƥ�������Ҫ�����ֶ��ж����ͽ���ת�����Ƚ��鷳��
//descriptor.getWriteMethod().invoke(bean, value);
//�����������ʱ������ͻᱨ�������Ͳ�ƥ�����Ҳ��Ҫ�����ֶ��ж����ͽ���ת�����Ƚ��鷳��
//BeanUtils.setProperty(bean, propertyName, value);
            }  
            bean.set������Ϣ(asd);
        }  
        return bean;  
    } 
}
