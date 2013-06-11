package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataEncrypt {
	MessageDigest md;
	 String strSRCData = "";
	 String strENCData = "";
	 
	 public DataEncrypt() {}
	 
	public String encrypt(String strData) { // 암호화 시킬 데이터
		strENCData = "";
	  try {
	   MessageDigest md = MessageDigest.getInstance("MD5"); // "MD5 형식으로 암호화"
	   byte[] bytData = strData.getBytes();  
	   md.update(bytData);
	   byte[] digest = md.digest();  //배열로 저장을 한다.
	   for (int i = 0; i < digest.length; i++) {
	    strENCData = strENCData + Integer.toHexString(digest[i] & 0xFF).toUpperCase();
	   }
	  } catch (NoSuchAlgorithmException e) {
	   System.out.print("암호화 에러" + e.toString());
	  }
	  return strENCData;  // 암호화된 데이터를 리턴...
	 }
	
	public static void main(String ar[]){
		System.out.println(new DataEncrypt().encrypt("haha"));
	}
}
