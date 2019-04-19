package com.store.manager.service.impl;

import com.store.manager.dao.ManagerDao;
import com.store.manager.dao.impl.ManagerDaoImpl;
import com.store.manager.domain.Manager;
import com.store.manager.service.ManagerService;

public class ManagerServiceImpl implements ManagerService{
	private ManagerDao dao = new ManagerDaoImpl();
	@Override
	public Manager find(String name, String pwd) {
		return dao.find(name, pwd);
	}

}
