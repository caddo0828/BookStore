package com.store.user.service;

import java.util.Random;

/**生成四位随机数验证码
 * @author 老腰
 */
public class RandomHelper {
	public static String getNumber() {
		Random random = new Random();
		//设置随机数范围
		String number = random.nextInt(9999)+"";
		
		//定义字符串缓冲区
		StringBuffer buffer = new StringBuffer();
			
	    //当生成的随机数不满足四位要求时，数值前位补0			
		for(int i=0;i<4-number.length();i++) {
			buffer.append("0");
		}
		
		//将随机数进行字符串拼接
		number = buffer.toString()+number;
		return number;
	}

}
