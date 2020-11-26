package com.ew.component.shiro.utils;

import java.security.NoSuchAlgorithmException;

import com.ew.common.utils.MD5Util;

/**
 * 密码工具
 * @author Mr`Huang
 * @Date 2020-11-9 11:51:35
 */
public class PasswordUtil {

	/**
	 * 密码是否合法
	 * @param password			明文密码
	 * @param encryptPassWord	加密密码
	 * @return
	 */
	public static boolean verify(String password,String encryptPassWord) {
		return encrypt(password).equals(encryptPassWord);
	} 
	
	
	/**
	 * 获取密码的密码
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
		try {
			return MD5Util.MD5_32bit(password);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
	
}
