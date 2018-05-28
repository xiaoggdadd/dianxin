package com.noki.common.util;

import java.io.Writer;

import com.noki.common.BZItem;
import com.noki.common.BZLineItem;
import com.noki.common.BaseInfoReq;
import com.noki.common.CodeItem;
import com.noki.common.I_REQUEST;
import com.noki.common.MenuItem;
import com.noki.common.Message;
import com.noki.common.PayMentItem;
import com.noki.common.RelateSupplier;
import com.noki.common.RequestMessage;
import com.noki.common.RequestMessageCode;
import com.noki.common.RequestMessageMenu;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
  
public class XstreamUtil {  
      
    //xstream扩展  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点都增加CDATA标记  
                boolean cdata = false;  
  
                public void startNode(String name, Class clazz) {  
                	if(name.equals("MESSAGE")){
                		cdata = true;
                	}
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });  
      
      
      
  public String object2Xml(Object obj,Object child,String alias,String aliasForChild){  
      xstream.alias(alias, obj.getClass());  
      xstream.alias(aliasForChild, child.getClass());  
      String xml=xstream.toXML(obj);  
      return xml;  
  }  
    
  public String object2Xml(Object obj,String alias){  
	  xstream.alias("I_REQUEST", I_REQUEST.class);
	  xstream.alias("BASEINFO", BaseInfoReq.class);
	  xstream.alias("MESSAGE", Message.class);
	  xstream.alias("requestMessage", RequestMessage.class);
	  xstream.alias("item", BZItem.class);
	  xstream.alias("lineItem", BZLineItem.class);
	  xstream.alias("relateSupplier", RelateSupplier.class);
	  xstream.alias("payMentItem", PayMentItem.class);
	  xstream.alias("requestMessage", RequestMessageCode.class);
	  xstream.alias("item", CodeItem.class);
	  xstream.alias("requestMessage", RequestMessageMenu.class);
	  xstream.alias("item", MenuItem.class);
	  
	  xstream.alias(alias, obj.getClass());  
       
      String xml=xstream.toXML(obj);  
      return xml;  
  }  
}  
