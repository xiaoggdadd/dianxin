package com.noki.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.Cipher;

public class DES
    implements java.io.Serializable {

  SecretKey key;

  public DES() {
    try {
      String skey = "[B@ed0338";
      byte rawKeyData[] = skey.getBytes();
      DESKeySpec dks = new DESKeySpec(rawKeyData);
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      key = keyFactory.generateSecret(dks);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 解密方法输入String输出String
   */
  public String createDecryptor(String in) {
    String out = "";
    try {
      byte data[] = hex2byte(in);
      Cipher cipher = Cipher.getInstance("DES");
      // 用密匙初始化Cipher对象
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte decryptedData[] = cipher.doFinal(data);
      String s1 = new String(decryptedData, "UTF-8");
      StringBuffer sb = new StringBuffer(s1);
      out = sb.substring(1, sb.length());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return out;
  }

  /**
   * 加密方法输入String输出String
   */
  public String createEncryptor(String in) {
    String out = "";
    String sin = "a" + in;
    try {
      byte data[] = sin.getBytes("UTF-8");
      Cipher cipher = Cipher.getInstance("DES");
      // 用密匙初始化Cipher对象
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encryptData = cipher.doFinal(data);

      out = byte2hex(encryptData);
      //out=byte2hex(encryptData);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return out;
  }

  /**
   * 二进制转字符串。
   *
   * @param b
   * @return
   */
  private String byte2hex(byte[] b) {
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      }
      else {
        hs = hs + stmp;
      }
      if (n < b.length - 1) {
        hs = hs + ":";
      }
    }
    return hs.toUpperCase();
  }

  /**
   * 字符串转二进制。
   *
   * @param hex
   * @return
   */
  private byte[] hex2byte(String hex) {
    String[] h = hex.split(":");
    byte[] b = new byte[h.length];
    for (int i = 0; i < h.length; i++) {
      //System.out.println(i+":"+h[i]);
      int byteint = Integer.parseInt(h[i], 16) & 0xFF;
      b[i] = new Integer(byteint).byteValue();
    }
    //System.out.println("haha");
    return b;
  }

  public static void main(String s[]) {
    DES p = new DES();
    String se = p.createEncryptor("123");
    System.out.println(se.length() + " " + se);
    String sd = p.createDecryptor("44:C0:1D:C9:A7:E6:F9:74");
    //System.out.println("sd:"+sd);

  }

}
