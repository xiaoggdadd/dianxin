package com.noki.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.aliyun.api.gateway.demo.util.HttpUtils;

public class DlOcr {
	private long getDlByOcr(String imageFilePath){
		String imageFile_base64 = Img2Base64Util.getImgStr(imageFilePath);
		long dl = 0;
		String host = "http://tysbgpu.market.alicloudapi.com";
	    String path = "/api/predict/ocr_general";
	    String method = "POST";
	    String appcode = "���Լ���AppCode";
	    Map<String, String> headers = new HashMap<String, String>();
	    //�����header�еĸ�ʽ(�м���Ӣ�Ŀո�)ΪAuthorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //����API��Ҫ�󣬶������Ӧ��Content-Type
	    headers.put("Content-Type", "application/json; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
	    String bodys = "{\"image\":    \""+imageFile_base64+"\", \"configure\":\"{\\\"min_size\\\" : 16,    #ͼƬ�����ֵ���С�߶�\\\"output_prob\\\" : true  #�Ƿ�������ֿ�ĸ���}\"}";


	    try {
	    	/**
	    	* ��Ҫ��ʾ����:
	    	* HttpUtils���
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* ����
	    	*
	    	* ��Ӧ�����������
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	//HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	//��ȡresponse��body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return dl;
	}
}
