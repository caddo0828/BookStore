package com.store.order.service;

import java.util.ArrayList;

import com.store.order.domain.History;

public interface HistoryService {
	/**
	 * �����û�id��ѯ��ʷ��������
	 * @param userID �û�id
	 * @return
	 */
	ArrayList<History> getHistoryList(String userID);
}
