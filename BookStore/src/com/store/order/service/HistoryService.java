package com.store.order.service;

import java.util.ArrayList;

import com.store.order.domain.History;

public interface HistoryService {
	/**
	 * 根据用户id查询历史订单数据
	 * @param userID 用户id
	 * @return
	 */
	ArrayList<History> getHistoryList(String userID);
}
