package com.store.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ��ȡ���ն�Ӧ��ʽ�ĵ�ǰʱ�䣨�����û���¼������վ�Լ��û���������ʱ��ʱʹ�ã�
 * @author ����
 */
public class CurrentDate {
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = dateFormat.format(date);
		return time;
	}
	
}
