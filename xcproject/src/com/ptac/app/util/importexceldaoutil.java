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
     * 将 Map对象转化为JavaBean   此方法已经测试通过
     * @author wyply115 
     * @param type 要转化的类型 
     * @param map 
     * @return Object对象
     * @version 2016年3月20日 11:03:01
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
                //这个方法不会报参数类型不匹配的错误。
                BeanUtils.copyProperty(bean, propertyName, value);
//用这个方法对int等类型会报参数类型不匹配错误，需要我们手动判断类型进行转换，比较麻烦。
//descriptor.getWriteMethod().invoke(bean, value);
//用这个方法对时间等类型会报参数类型不匹配错误，也需要我们手动判断类型进行转换，比较麻烦。
//BeanUtils.setProperty(bean, propertyName, value);
            }  
            bean.set错误信息(asd);
        }  
        return bean;  
    } 
}
