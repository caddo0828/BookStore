package com.store.web.service;

import java.util.UUID;
/**
 * ����������ַ������͵�����
 * @author ����
 */
public class UUIDImpl {
	public static String getUID() {
		return UUID.randomUUID().toString(); 
	}
}
