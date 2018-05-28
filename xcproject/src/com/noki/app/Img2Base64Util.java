package com.noki.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;


/**
 * ��ͼƬת��ΪBase64<br>
 * ��base64�����ַ��������imgͼƬ
 * @����ʱ�� 2015-06-01 15:50
 *
 */
public class Img2Base64Util {

    public static void main(String[] args) {
        String imgFile = "d:\\3.jpg";//�������ͼƬ
        String imgbese=getImgStr(imgFile);
        System.out.println(imgbese.length());
        System.out.println(imgbese);
        String imgFilePath = "d:\\332.jpg";//�����ɵ�ͼƬ
        generateImage(imgbese,imgFilePath);
    }
    /**
     * ��ͼƬת����Base64����
     * @param imgFile ������ͼƬ
     * @return
     */
    public static String getImgStr(String imgFile){
        //��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
  
        
        InputStream in = null;
        byte[] data = null;
        //��ȡͼƬ�ֽ�����
        try 
        {
            in = new FileInputStream(imgFile);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }
    
    /**
     * ���ֽ������ַ�������Base64���벢����ͼƬ
     * @param imgStr ͼƬ����
     * @param imgFilePath ����ͼƬȫ·����ַ
     * @return
     */
    public static boolean generateImage(String imgStr,String imgFilePath){
        //
        if (imgStr == null) //ͼ������Ϊ��
            return false;
     
        try 
        {
            //Base64����
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//�����쳣����
                    b[i]+=256;
                }
            }
            //����jpegͼƬ

            OutputStream out = new FileOutputStream(imgFilePath);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
}