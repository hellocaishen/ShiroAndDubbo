package com.wsbg.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class AESUtil {

	public static String DEFALUT_KEY = "ABCDEFGHIJKLMN";

	public static String encrypt(String strKey, String strIn) throws Exception {
		SecretKeySpec skeySpec = getKey(strKey);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(strIn.getBytes());

		// return new BASE64Encoder().encode(encrypted);
		return parseByte2HexStr(encrypted);
	}

	public static String decrypt(String strKey, String strIn) throws Exception {
		SecretKeySpec skeySpec = getKey(strKey);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		// byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);
		byte[] encrypted1 = parseHexStr2Byte(strIn);

		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);
		return originalString;
	}

	private static SecretKeySpec getKey(String strKey) throws Exception {
		if (strKey == null || "".equals(strKey)) {
			strKey = DEFALUT_KEY;
		}
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[16]; // ����һ���յ�16λ�ֽ����飨Ĭ��ֵΪ0��

		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
		return skeySpec;
	}

	/**
	 * ��16����ת��Ϊ������
	 * 
	 * @param hexStr
	 * 
	 * @return
	 */

	public static byte[] parseHexStr2Byte(String hexStr) {

		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}

		return result;

	}

	/**
	 * ��������ת����16����
	 * 
	 * @param buf
	 * 
	 * @return
	 */

	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String arg[]) {
		String key="AqManager_encrypt_key";
		String parameter="com.mysql.jdbc.Driver";
		try {
			parameter = encrypt(key, parameter);
			System.out.println("driver= "+parameter );
			String name = encrypt(key, "root");
			
			System.out.println("name="+name);
			String pwd = encrypt(key, "root");
			System.out.println("pw="+pwd);
			parameter = encrypt(key, "jdbc:mysql://localhost:3306/wsbg?useUnicode=true&characterEncoding=utf-8&useSSL=false");
			System.out.println("url="+parameter );


			//System.out.println("parameter=" + decrypt(key, parameter));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			parameter = encrypt(key, parameter);
			System.out.println("parameter=" + parameter);
			System.out.println("parameter=" + decrypt(key, parameter));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
