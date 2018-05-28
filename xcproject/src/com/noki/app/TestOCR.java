package com.noki.app;  
import java.io.File;  
import java.io.IOException;  
  
public class TestOCR {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String path = "d://test4.jpg";  
        try {  
            String valCode = new OCR().recognizeText(new File(path), "jpg");  
            System.out.println(valCode);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static String getCodeValue(String path){
         try {  
             String valCode = new OCR().recognizeText(new File(path), "jpg");  
             return valCode;  
         } catch (IOException e) {  
             e.printStackTrace();  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
         return null;
    }
  
}  