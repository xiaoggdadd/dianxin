package com.noki.chaobiaorenyuan.servlet;
import java.io.*;  
import java.util.Date;  
import java.awt.*;  
import java.awt.image.*;  
import javax.imageio.ImageIO;  
import com.sun.image.codec.jpeg.*;  
/** 
 * ͼƬѹ������  
 */  
public class ImgCompress {  
    private Image img;  
    private int width;  
    private int height;  
    @SuppressWarnings("deprecation")  
    /** 
     * ���캯�� 
     */  
    public ImgCompress(String fileName) throws IOException {  
        File file = new File(fileName);// �����ļ�  
        img = ImageIO.read(file);      // ����Image����  
        width = img.getWidth(null);    // �õ�Դͼ��  
        height = img.getHeight(null);  // �õ�Դͼ��  
    }  
    /** 
     * ���տ�Ȼ��Ǹ߶Ƚ���ѹ�� 
     *  int ����� 
     *  int ���߶� 
     */  
    public void resizeFix(String url,int w, int h) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(url,w);  
        } else {  
            resizeByHeight(url,h);  
        }  
    }  
    /** 
     * �Կ��Ϊ��׼���ȱ�������ͼƬ 
     *  int �¿�� 
     */  
    public void resizeByWidth(String url,int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(url,w, h);  
    }  
    /** 
     * �Ը߶�Ϊ��׼���ȱ�������ͼƬ 
     * int �¸߶� 
     */  
    public void resizeByHeight(String url,int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(url,w, h);  
    }  
    /** 
     * ǿ��ѹ��/�Ŵ�ͼƬ���̶��Ĵ�С 
     * int �¿�� 
     * int �¸߶� 
     */  
	public void resize(String url,int w, int h) throws IOException {  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   // SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���  
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // ������С���ͼ 2 
        File destFile = new File(url);  
        FileOutputStream out = new FileOutputStream(destFile); // ������ļ���  		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  // ��������ʵ��bmp��png��gifתjpg  
        encoder.encode(image); // JPEG����  
        out.close();  
    }  
}  