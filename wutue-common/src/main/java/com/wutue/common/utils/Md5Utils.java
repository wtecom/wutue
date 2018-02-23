package com.wutue.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Utils {
	public String EncoderByMd5(String str) throws Exception{
		try {
			   //确定计算方法
		       MessageDigest md5 = MessageDigest.getInstance("MD5");
		       md5.update(str.getBytes());
		       return new BigInteger(1, md5.digest()).toString(16);
		} catch (Exception e) {
	        throw new Exception("MD5加密出现错误");
	    }
	}
}
