package com.store.user.service;

import java.util.Random;

/**������λ�������֤��
 * @author ����
 */
public class RandomHelper {
	public static String getNumber() {
		Random random = new Random();
		//�����������Χ
		String number = random.nextInt(9999)+"";
		
		//�����ַ���������
		StringBuffer buffer = new StringBuffer();
			
	    //�����ɵ��������������λҪ��ʱ����ֵǰλ��0			
		for(int i=0;i<4-number.length();i++) {
			buffer.append("0");
		}
		
		//������������ַ���ƴ��
		number = buffer.toString()+number;
		return number;
	}

}
