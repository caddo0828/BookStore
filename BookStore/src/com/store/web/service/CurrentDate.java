package com.store.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 获取按照对应格式的当前时间（用于用户登录访问网站以及用户订单生成时间时使用）
 * @author 老腰
 */
public class CurrentDate {
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = dateFormat.format(date);
		return time;
	}
	
}
