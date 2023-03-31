package com.ew.component.shiro.utils;


import cn.hutool.crypto.digest.DigestUtil;


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
		return DigestUtil.md5Hex(password);
	}
	
}
