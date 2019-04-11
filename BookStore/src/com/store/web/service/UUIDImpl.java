package com.store.web.service;

import java.util.UUID;
/**
 * 生成随机的字符串类型的数据
 * @author 老腰
 */
public class UUIDImpl {
	public static String getUID() {
		return UUID.randomUUID().toString(); 
	}
}
