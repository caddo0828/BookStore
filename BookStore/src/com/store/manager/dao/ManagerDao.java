package com.store.manager.dao;

import com.store.manager.domain.Manager;

public interface ManagerDao {
	/**
	 * �ж����ݿ��manager�еĹ���Ա��Ϣ�Ƿ�ƥ�䣬ƥ��ɹ��򷵻���ȷƥ��Ķ���
	 * �������Ϊ��
	 * @param name  ����Ա�˺�
	 * @param pwd   ����Ա����
	 * @return �����˺ţ����˺Ų�ѯ���Ĺ���Ա����
	 */
	Manager find(String name,String pwd);
}
