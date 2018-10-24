package com.woodare.sealblock.util;


/**
 * 
 * @author Luke
 *
 */
public class Tool {
	
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		try {
			for (int i = 0; i < len; i += 2) {
				data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			}
		} catch (Exception e) {
		}
		return data;
	}
}
