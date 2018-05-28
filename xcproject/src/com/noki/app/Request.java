/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.noki.app;


import java.util.List;
import java.util.Map;

import com.noki.app.enums.Method;

/**
 * Request
 */
public class Request {

    public Request() {
    }

    public Request(Method method, String host, String path, String appKey, String appSecret, int timeout) {
        this.method = method;
        this.host = host;
        this.path = path;        
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.timeout = timeout;
    }

    /**
     * ����ѡ�����󷽷�
     */
    private Method method;

    /**
     * ����ѡ��Host
     */
    private String host;
    
    /**
     * ����ѡ��Path
     */
    private String path;

    /**
     * ����ѡ)APP KEY
     */
    private String appKey;

    /**
     * ����ѡ��APP��Կ
     */
    private String appSecret;

    /**
     * ����ѡ����ʱʱ��,��λ����,������Ĭ��ʹ��com.aliyun.apigateway.demo.constant.Constants.DEFAULT_TIMEOUT
     */
    private int timeout;

    /**
     * ����ѡ�� HTTPͷ
     */
    private Map<String, String> headers;
    
    /**
     * ����ѡ�� Querys
     */
    private Map<String, String> querys;

    /**
     * ����ѡ��������
     */
    private Map<String, String> bodys;

    /**
     * ����ѡ���ַ���Body��
     */
    private String stringBody;

    /**
     * ����ѡ���ֽ���������Body��
     */
    private byte[] bytesBody;

    /**
     * ����ѡ���Զ������ǩ��Headerǰ׺
     */
    private List<String> signHeaderPrefixList;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    public Map<String, String> getQuerys() {
        return querys;
    }

    public void setQuerys(Map<String, String> querys) {
        this.querys = querys;
    }
    
    public Map<String, String> getBodys() {
        return bodys;
    }

    public void setBodys(Map<String, String> bodys) {
        this.bodys = bodys;
    }

    public String getStringBody() {
        return stringBody;
    }

    public void setStringBody(String stringBody) {
        this.stringBody = stringBody;
    }

    public byte[] getBytesBody() {
        return bytesBody;
    }

    public void setBytesBody(byte[] bytesBody) {
        this.bytesBody = bytesBody;
    }

    public List<String> getSignHeaderPrefixList() {
        return signHeaderPrefixList;
    }

    public void setSignHeaderPrefixList(List<String> signHeaderPrefixList) {
        this.signHeaderPrefixList = signHeaderPrefixList;
    }
}
