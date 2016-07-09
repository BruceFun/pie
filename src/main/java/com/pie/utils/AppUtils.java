package com.pie.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.mysql.fabric.xmlrpc.base.Array;


public class AppUtils {
	/**
	 * 生成一个UUID(无链接符)
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * MD5加密 生成32位md5码
	 * @param str 要加密的字符串
	 * @return
	 */
	public static String getEncoderByMD5(String str){
		MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = str.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
	}
	
	/**
	 * 
	 * @param plainText 加密的信息
	 * @param length 加密位数 16/32 默认32位
	 * @param encryptType 加密类型  MD5,SHA-1,SHA-256
	 * @return
	 */
	public static final String GetMD5(String plainText,Integer length,String encryptType) {
		String str = "";
		try {
			MessageDigest md = MessageDigest.getInstance(encryptType);
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (length == 16) {
				str = buf.toString().substring(8, 24);// 16位的加密
			} else {
				str = buf.toString();// 32位的加密
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 
	 * @param plainText // 要加密的源
	 * @param enkryptType // 加密类型
	 * @param bl // true加密为16进制，false为Base64加密
	 * @return
	 */
	public static final String getHashedCredentials(String plainText,String enkryptType,Boolean bl){
		String et = enkryptType.toUpperCase();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(et);
		} catch (Exception e) {
			e = new Exception("没有你输入的加密类型");
			e.printStackTrace();
			return "";
		}
		
		byte[] bytes = plainText.getBytes();
		md.update(bytes);
		if(bl){
			return bytes2Hex(md.digest());
		}else{
			return bytes2Base64(md.digest());
		}
	}
	
	/**
	 * 16进制加密转化
	 * @param bts byte数组
	 * @return 加密一次
	 */
	public static String bytes2Hex(byte[] bts){
		Hex hex = new Hex();
		byte[] encode = hex.encode(bts);
		return new String(encode);
	}
	
	/**
	 * base64加密
	 * @param bts byte数组
	 * @return 加密一次
	 */
	public static String bytes2Base64(byte[] bts){
		Base64 base64 = new Base64();
		byte[] encode = base64.encode(bts);
		return new String(encode);
	}
	
	/**
	 * 把一个字符串日期转化成标准日期
	 * @param date  字符串日期
	 * @return
	 */
	public static Date formatStringToDate(String date){
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 得到一个shiro的Subject主体
	 * @return
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
}