package com.zimo.personal.util;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.regex.Pattern;

public class AesUtil {

    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static void main(String args[]) throws Exception {

        String originBody = "X8H+/RfuRqR4islvF92M1OgLRtqo7HP/eZDnnbcfHhkMJkVfGH+7DwanD9VSmiuUIQ6+QNBqXnMH\n" +
                "ONUAkQuHXJQmhSsDVGJpqJ+nltLi38+4Q66gOmk07ge1XqIJ5/mYiiF4DmA0ams75mraRrtOCzkd\n" +
                "dcivhz+qmAAu57tNsslC00A5RTYlC3DzVQ3igkBkqRMor4GO7exuECw+xSU1cG72jpNTycDhsOUR\n" +
                "b0SCkSW8qJmBfS5LiEchxGDD1hAL\n";
        String transid = "0731161157397826";
        String desc = aesDecryptByBytes(base64Decode(originBody), transid);
        System.out.println(desc);
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String aesEncryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static String base64Encode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Encoder().encode(base64Code.getBytes());
    }

    private static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        return str.matches(regEx);
    }

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]{11}");
        return pattern.matcher(str).matches();
    }

    private static boolean validatePassword(String str) {
        String regEx = "[A-Za-z0-9]{6,10}";
        return str.matches(regEx);
    }

    private static boolean isAccount(String str) {

        String regEx = "[A-Za-z0-9\\u4e00-\\u9fa5]+";
        /*for (int i=0; i< str.length(); i++){
            String a = str.substring(i, i+1);
            System.out.println(i + "----------"+a);
        }*/
        return str.matches(regEx);
    }

    public static float getfloat2(float a) {
        return (float) (Math.round(a * 100)) / 100;
    }

    // MD5加码。32位
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    // 可逆的加密算法
    public static String KL(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 'a');    //a是加密的密钥,可变
        }
        String s = new String(a);
        return s;
    }

    // 加密后解密
    public static String JM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 'a');     //a解密的密钥,与加密相同即可
        }
        String k = new String(a);
        return k;
    }
}
