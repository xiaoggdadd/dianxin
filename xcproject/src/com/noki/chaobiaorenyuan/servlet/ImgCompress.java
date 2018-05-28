package com.noki.chaobiaorenyuan.servlet;
import java.io.*;  
import java.util.Date;  
import java.awt.*;  
import java.awt.image.*;  
import javax.imageio.ImageIO;  
import com.sun.image.codec.jpeg.*;  
/** 
 * 图片压缩处理  
 */  
public class ImgCompress {  
    private Image img;  
    private int width;  
    private int height;  
    @SuppressWarnings("deprecation")  
    /** 
     * 构造函数 
     */  
    public ImgCompress(String fileName) throws IOException {  
        File file = new File(fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     *  int 最大宽度 
     *  int 最大高度 
     */  
    public void resizeFix(String url,int w, int h) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(url,w);  
        } else {  
            resizeByHeight(url,h);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     *  int 新宽度 
     */  
    public void resizeByWidth(String url,int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(url,w, h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * int 新高度 
     */  
    public void resizeByHeight(String url,int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(url,w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * int 新宽度 
     * int 新高度 
     */  
	public void resize(String url,int w, int h) throws IOException {  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图 2 
        File destFile = new File(url);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  // 可以正常实现bmp、png、gif转jpg  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }  
}  